package com.gandhithedergrawr.disruptionsystems.tileentity;
import com.gandhithedergrawr.disruptionsystems.data.recipes.ModRecipeTypes;
import com.gandhithedergrawr.disruptionsystems.data.recipes.PowderMillRecipe;
import com.gandhithedergrawr.disruptionsystems.tools.MatterenergyImplementation.MatterEnergyCapability;
import com.gandhithedergrawr.disruptionsystems.tools.PowderMillEnergyStorage;
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
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.concurrent.Executors;

import static com.gandhithedergrawr.disruptionsystems.tileentity.Helper.inputNodeHasPower;

public class PowderMillTile extends TileEntity implements ITickableTileEntity {
    public static boolean isProcessingPowderMill = false;
    public static int processingTimePowderMill;
    public static final int MAX_POWER = 160000;
    public static final int RF_PER_TICK = 20;


    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public PowderMillTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public PowderMillTile() {
        this(ModTileEntities.POWDER_MILL_TILE.get());
    }

    private PowderMillEnergyStorage energyStorage = new PowderMillEnergyStorage(MAX_POWER, 1000);


    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        energyStorage.setEnergy(nbt.getByte("energy"));
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
        if (cap == MatterEnergyCapability.MATTER_ENERGY) {
            return LazyOptional.of(() -> energyStorage).cast();
        }
        return super.getCapability(cap, side);
    }

    private void craft() {
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
        }

        Optional<PowderMillRecipe> recipe = world.getRecipeManager()
                .getRecipe(ModRecipeTypes.CRUSHING_RECIPE, inv, world);
        isProcessingPowderMill = false;
        recipe.ifPresent(iRecipe -> {
            {
                isProcessingPowderMill = true;
                ItemStack output = iRecipe.getRecipeOutput();
                if (processingTimePowderMill >= 100) {
                    itemHandler.extractItem(0, 1, false);
                    itemHandler.insertItem(1, output, false);
                    isProcessingPowderMill = false;
                    processingTimePowderMill = 0;
                    markDirty();
                }
            }
        });
    }

    @Override
    public void tick() {
        if (Helper.adjacentToMatterEnergyNetworkInputNode(world, pos)) {
            MatterEnergyNetworkInputNodeTile tile = Helper.getMatterEnergyNetworkInputNode(world, pos);
            if (inputNodeHasPower(Helper.getMatterEnergyNetworkInputNode(world, pos), RF_PER_TICK)) {
                tile.getMatterEnergyStorage().extractEnergy(10, false);
                processingTimePowderMill++;
                Executors.newCachedThreadPool().execute(this::craft);
            }
        }
    }
}