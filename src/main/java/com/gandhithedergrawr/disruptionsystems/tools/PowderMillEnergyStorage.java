package com.gandhithedergrawr.disruptionsystems.tools;

import com.gandhithedergrawr.disruptionsystems.tools.MatterenergyImplementation.MatterEnergyHandler;
import net.minecraftforge.energy.EnergyStorage;

public class PowderMillEnergyStorage extends MatterEnergyHandler {
    public PowderMillEnergyStorage(int capacity, int maxReceive) {
        super(capacity, maxReceive, 0);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void consumePower (int energy) {
        this.energy -= energy;
    }

    @Override
    public int getEnergyStored() {
        return energy;
    }
}