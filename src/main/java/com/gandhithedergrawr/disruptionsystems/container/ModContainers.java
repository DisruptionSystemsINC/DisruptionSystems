package com.gandhithedergrawr.disruptionsystems.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.gandhithedergrawr.disruptionsystems.disruptionsystems.MOD_ID;

public class ModContainers {

    public static DeferredRegister<ContainerType<?>> CONTAINERS
            =DeferredRegister.create(ForgeRegistries.CONTAINERS, MOD_ID);

    public static final RegistryObject<ContainerType<AlloySmelterContainer>> ALLOY_SMELTER_CONTAINER
             = CONTAINERS.register("alloy_smelter_container",
               () -> IForgeContainerType.create(((windowId, inv, data) -> {
               BlockPos pos = data.readBlockPos();
               World world = inv.player.getEntityWorld();
              return new AlloySmelterContainer(windowId, world, pos, inv, inv.player);
          })));//
    public static final RegistryObject<ContainerType<ThermiteFurnaceContainer>> THERMITE_FURNACE_CONTAINER
            = CONTAINERS.register("thermite_furnace_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new ThermiteFurnaceContainer(windowId, world, pos, inv, inv.player);
            })));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}
