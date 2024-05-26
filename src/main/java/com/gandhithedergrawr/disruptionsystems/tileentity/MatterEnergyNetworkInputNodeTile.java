package com.gandhithedergrawr.disruptionsystems.tileentity;
import com.gandhithedergrawr.disruptionsystems.tools.EnergyStorageManager;
import com.gandhithedergrawr.disruptionsystems.tools.MatterEnergyStorageManager;
import com.gandhithedergrawr.disruptionsystems.tools.MatterenergyImplementation.IMatterEnergyHandler;
import com.gandhithedergrawr.disruptionsystems.tools.MatterenergyImplementation.MatterEnergyCapability;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class MatterEnergyNetworkInputNodeTile extends TileEntity implements ITickableTileEntity {
    public static  int MAX_POWER = 100000;
    public static  int MAX_MATTERENERGY_STORAGE = 100000;
    public static int RF_PER_TICK = 1600;





    public MatterEnergyNetworkInputNodeTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public MatterEnergyNetworkInputNodeTile() {
        this(ModTileEntities.MATTER_ENERGY_NETWORK_INPUT_NODE_TILE.get());
    }


    private EnergyStorageManager energyStorage = new EnergyStorageManager(MAX_POWER, 1000);
    private static MatterEnergyStorageManager matterEnergyStorage = new MatterEnergyStorageManager(MAX_MATTERENERGY_STORAGE, 1000);

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        energyStorage.setEnergy(nbt.getByte("energy"));
        matterEnergyStorage.setEnergy(nbt.getByte("matter_energy"));
        super.read(state, nbt);
    }


    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("energy", energyStorage.getEnergyStored());
        compound.putInt("matter_energy", matterEnergyStorage.getEnergyStored());
        return super.write(compound);
    }

    public static void sendMessageToChat(PlayerEntity player) {
        StringTextComponent chatComponent = new StringTextComponent(String.valueOf(matterEnergyStorage.getEnergyStored()));
        player.sendMessage(chatComponent, UUID.randomUUID());
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return LazyOptional.of(() -> energyStorage).cast();
        }
        if (cap == MatterEnergyCapability.MATTER_ENERGY) {
            return LazyOptional.of(() -> matterEnergyStorage).cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
            if (matterEnergyStorage.getEnergyStored() < MAX_MATTERENERGY_STORAGE){
                if (energyStorage.getEnergyStored() > RF_PER_TICK){
                    energyStorage.consumePower(RF_PER_TICK);
                    matterEnergyStorage.receiveEnergy(energyStorage.getEnergyStored()/4, false);
                    System.out.println(matterEnergyStorage.getEnergyStored());
                    matterEnergyStorage.extractEnergy(100, false);
                }
        }
    }
    public IMatterEnergyHandler getMatterEnergyStorage() {
        return matterEnergyStorage;
    }
}