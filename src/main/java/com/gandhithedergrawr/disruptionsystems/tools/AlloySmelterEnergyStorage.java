package com.gandhithedergrawr.disruptionsystems.tools;

import com.gandhithedergrawr.disruptionsystems.tools.MatterenergyImplementation.MatterEnergyHandler;
import net.minecraftforge.energy.EnergyStorage;

public class AlloySmelterEnergyStorage extends MatterEnergyHandler {
    public AlloySmelterEnergyStorage(int capacity, int maxReceive) {
        super(capacity, maxReceive, 0);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void consumePower (int energy) {
     this.energy -= energy;
    }
}
