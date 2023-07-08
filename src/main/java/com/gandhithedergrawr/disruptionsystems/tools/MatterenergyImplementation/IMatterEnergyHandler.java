package com.gandhithedergrawr.disruptionsystems.tools.MatterenergyImplementation;
public interface IMatterEnergyHandler {
    int receiveEnergy(int maxReceive, boolean simulate);

    int extractEnergy(int maxExtract, boolean simulate);

    int getEnergyStored();

    int getMaxEnergyStored();

    boolean canExtract();

    boolean canReceive();
}