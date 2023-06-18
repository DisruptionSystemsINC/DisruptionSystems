package com.gandhithedergrawr.disruptionsystems.world;

import com.gandhithedergrawr.disruptionsystems.world.gen.ModOreGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.gandhithedergrawr.disruptionsystems.disruptionsystems.MOD_ID;


@Mod.EventBusSubscriber(modid = MOD_ID)
public class ModWorldEvents {
     @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
         ModOreGeneration.generateOres(event);
     }
}
