package com.gandhithedergrawr.disruptionsystems.tileentity;
import com.gandhithedergrawr.disruptionsystems.data.recipes.AlloySmelterRecipe;
import com.gandhithedergrawr.disruptionsystems.data.recipes.ModRecipeTypes;
import com.gandhithedergrawr.disruptionsystems.tools.AlloySmelterEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.concurrent.Executors;

public class AlloySmelterTile extends TileEntity implements ITickableTileEntity{
    public static boolean isProcessing = false;
    public static int processingTime;
    public static final int MAX_POWER = 160000;
    public static final int RF_PER_TICK = 20;


    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public AlloySmelterTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public AlloySmelterTile() {
        this(ModTileEntities.ALLOY_SMELTER_TILE.get());
    }

    private AlloySmelterEnergyStorage energyStorage = new AlloySmelterEnergyStorage(MAX_POWER, 1000);


    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        energyStorage.setEnergy(nbt.getInt("energy"));
        super.read(state, nbt);
    }



    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        compound.putInt("energy", energyStorage.getEnergyStored());
        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(3) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {

                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 64;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        if (cap == CapabilityEnergy.ENERGY) {
            return LazyOptional.of(() -> energyStorage).cast();
        }
        return super.getCapability(cap, side);
    }

    private void craft(){
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
        }

        Optional<AlloySmelterRecipe> recipe = world.getRecipeManager()
                .getRecipe(ModRecipeTypes.ALLOYING_RECIPE, inv, world);
        isProcessing = false;
        recipe.ifPresent(iRecipe -> {
            {
                isProcessing = true;
                ItemStack output = iRecipe.getRecipeOutput();
                if (processingTime >= 480 ) {
                    itemHandler.extractItem(0, 1, false);
                    itemHandler.extractItem(1, 1, false);
                    itemHandler.insertItem(2, output, false);
                    isProcessing = false;
                    processingTime = 0;
                    markDirty();
                }
            }
        });
    }

    @Override
    public void tick() {
        if (world.isRemote) {
            if (energyStorage.getEnergyStored() < RF_PER_TICK) {
                System.out.println("Smaller BITCH");
                return;
            }
            else if(isProcessing) {
                energyStorage.consumePower(RF_PER_TICK);
                processingTime++;
                return;
            }

        }
        Executors.newCachedThreadPool().execute(this::craft);
    }
}