package com.gandhithedergrawr.disruptionsystems.tileentity;

import com.gandhithedergrawr.disruptionsystems.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.gandhithedergrawr.disruptionsystems.disruptionsystems.MOD_ID;

public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);


   public static RegistryObject<TileEntityType<AlloySmelterTile>> ALLOY_SMELTER_TILE =
            TILE_ENTITIES.register("alloy_smelter_tile", () -> TileEntityType.Builder.create(
                    AlloySmelterTile::new, ModBlocks.ALLOY_SMELTER.get()).build(null));


    public static RegistryObject<TileEntityType<PowderMillTile>> POWDER_MILL_TILE =
            TILE_ENTITIES.register("powder_mill_tile", () -> TileEntityType.Builder.create(
                    PowderMillTile::new, ModBlocks.POWDER_MILL.get()).build(null));


    public static RegistryObject<TileEntityType<ThermiteFurnaceTile>> THERMITE_FURNACE_TILE =
            TILE_ENTITIES.register("thermite_furnace_tile", () -> TileEntityType.Builder.create(
                    ThermiteFurnaceTile::new, ModBlocks.THERMITE_FURNACE.get()).build(null));

    public static RegistryObject<TileEntityType<MatterEnergyNetworkInputNodeTile>> MATTER_ENERGY_NETWORK_INPUT_NODE_TILE =
            TILE_ENTITIES.register("matter_energy_network_node_input_tile", () -> TileEntityType.Builder.create(
                    MatterEnergyNetworkInputNodeTile::new, ModBlocks.MATTER_ENERGY_NETWORK_INPUT_NODE.get()).build(null));




    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }

}
