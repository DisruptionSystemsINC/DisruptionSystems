package com.gandhithedergrawr.disruptionsystems.tileentity;

import com.gandhithedergrawr.disruptionsystems.data.recipes.AlloySmelterRecipe;
import com.gandhithedergrawr.disruptionsystems.data.recipes.ModRecipeTypes;
import com.gandhithedergrawr.disruptionsystems.item.ModItems;
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
import org.jline.utils.InfoCmp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Optional;

import static com.gandhithedergrawr.disruptionsystems.item.ModItems.HYDERMANIUM_INGOT;
import static com.gandhithedergrawr.disruptionsystems.item.ModItems.LITHIUM_INGOT;

public class AlloySmelterTile extends TileEntity implements ITickableTileEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public AlloySmelterTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    public AlloySmelterTile() {
        this(ModTileEntities.ALLOY_SMELTER_TILE.get());
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
        return new ItemStackHandler(3) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0:
                        return stack.getItem() == HYDERMANIUM_INGOT.get();
                    case 1:
                        return stack.getItem() == LITHIUM_INGOT.get();

                    default:
                        return false;
                }
            }

            @Override
            public int getSlotLimit(int slot) {
                    return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }
            return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }
    public void IsAlloyableMaterialAvailible() {
        boolean HasHydermaniumInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() > 0
                && this.itemHandler.getStackInSlot(0).getItem() == HYDERMANIUM_INGOT.get();
        boolean HasLithiumInSecondSlot = this.itemHandler.getStackInSlot(1).getCount() > 0
                && this.itemHandler.getStackInSlot(1).getItem() == LITHIUM_INGOT.get();

        if(HasHydermaniumInFirstSlot && HasLithiumInSecondSlot) {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);

            this.itemHandler.insertItem(3, new ItemStack(ModItems.HYDRANIUM_INGOT.get()), false);
        }
    }
    public void craft(){
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
        }

        Optional<AlloySmelterRecipe> recipe = world.getRecipeManager()
                .getRecipe(ModRecipeTypes.ALLOYING_RECIPE, inv, world);
    }
    @Override
    public void tick() {
        if (world.isRemote){
            return;

            craft();
        }
    }
}
