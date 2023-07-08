package com.gandhithedergrawr.disruptionsystems.tools.MatterenergyImplementation;/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class MatterEnergyCapability
{
    @CapabilityInject(IMatterEnergyHandler.class)
    public static Capability<IMatterEnergyHandler> MATTER_ENERGY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IMatterEnergyHandler.class, new IStorage<IMatterEnergyHandler>()
                {
                    @Override
                    public INBT writeNBT(Capability<IMatterEnergyHandler> capability, IMatterEnergyHandler instance, Direction side)
                    {
                        return IntNBT.valueOf(instance.getEnergyStored());
                    }

                    @Override
                    public void readNBT(Capability<IMatterEnergyHandler> capability, IMatterEnergyHandler instance, Direction side, INBT nbt)
                    {
                        if (!(instance instanceof MatterEnergyHandler))
                            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                        ((MatterEnergyHandler)instance).energy = ((IntNBT)nbt).getInt();
                    }
                },
                () -> new MatterEnergyHandler(1000));
    }
}