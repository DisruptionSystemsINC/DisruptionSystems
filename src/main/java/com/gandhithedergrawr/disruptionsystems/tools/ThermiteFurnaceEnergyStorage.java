package com.gandhithedergrawr.disruptionsystems.tools;

import net.minecraftforge.energy.EnergyStorage;

public class ThermiteFurnaceEnergyStorage extends EnergyStorage {
    public ThermiteFurnaceEnergyStorage(int capacity, int maxReceive) {
        super(capacity, maxReceive, 0);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void consumePower (int energy) {
        this.energy -= energy;
    }
}