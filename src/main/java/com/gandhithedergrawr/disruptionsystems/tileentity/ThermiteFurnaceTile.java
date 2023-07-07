package com.gandhithedergrawr.disruptionsystems.tileentity;

import com.gandhithedergrawr.disruptionsystems.data.recipes.ModRecipeTypes;
import com.gandhithedergrawr.disruptionsystems.data.recipes.ThermiteFurnaceRecipe;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.concurrent.Executors;

public class ThermiteFurnaceTile extends TileEntity implements ITickableTileEntity, Runnable{
    public static boolean isProcessingThermiteFurnace = false;
    public static int processingTimeThermiteFurnace;

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public ThermiteFurnaceTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public ThermiteFurnaceTile() {
        this(ModTileEntities.THERMITE_FURNACE_TILE.get());
    }


    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(4) {
            @Override
            protected void onContentsChanged(int slot) {markDirty();}

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
        return super.getCapability(cap, side);
    }


    private void craft(){
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
        }

        Optional<ThermiteFurnaceRecipe> recipe = world.getRecipeManager()
                .getRecipe(ModRecipeTypes.THERMITE_BLASTING_RECIPE, inv, world);
        isProcessingThermiteFurnace = false;
        recipe.ifPresent(iRecipe -> {
            {
                isProcessingThermiteFurnace = true;
                ItemStack output = iRecipe.getRecipeOutput();
                if (processingTimeThermiteFurnace >= 800 ) {
                    itemHandler.extractItem(0, 1, false);
                    itemHandler.extractItem(1, 1, false);
                    itemHandler.extractItem(2, 1, false);
                    itemHandler.insertItem(3, output, false);
                    isProcessingThermiteFurnace = false;
                    processingTimeThermiteFurnace = 0;
                    markDirty();
                }
            }

        });
    }

    @Override
    public void tick() {
        if (world.isRemote) {
            if (isProcessingThermiteFurnace) {
                processingTimeThermiteFurnace++;
                return;
            }

        }
        Executors.newCachedThreadPool().execute(this::craft);
    }

    @Override
    public void run() {

    }
}