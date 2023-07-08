package com.gandhithedergrawr.disruptionsystems.tools;

import com.gandhithedergrawr.disruptionsystems.tools.MatterenergyImplementation.MatterEnergyHandler;

public class MatterEnergyStorageManager extends MatterEnergyHandler {
    public MatterEnergyStorageManager(int capacity, int maxReceive) {
        super(capacity, maxReceive, 0);
    }

    public void setEnergy(int energy) {
        this.energy = energy/4;
    }

    public void consumePower (int energy) {
        this.energy -= energy;
    }


    @Override
    public int getEnergyStored() {
        return energy;
    }
}