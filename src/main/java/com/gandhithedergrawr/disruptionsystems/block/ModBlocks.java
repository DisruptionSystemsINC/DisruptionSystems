package com.gandhithedergrawr.disruptionsystems.block;

import com.gandhithedergrawr.disruptionsystems.block.custom.AlloySmelterBlock;
import com.gandhithedergrawr.disruptionsystems.block.custom.ThermiteFurnaceBlock;
import com.gandhithedergrawr.disruptionsystems.disruptionsystems;
import com.gandhithedergrawr.disruptionsystems.item.ModItemGroup;
import com.gandhithedergrawr.disruptionsystems.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, disruptionsystems.MOD_ID);



    //Ores

    public static final RegistryObject<Block> HYDERMANIUM_ORE = registerBlock("hydermanium_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(10, 410)));

    public static final RegistryObject<Block> LITHIUM_ORE = registerBlock("lithium_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(5, 5)));

    public static final RegistryObject<Block> ALUMINIUM_ORE = registerBlock("aluminium_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(15, 25)));







    //Machines

    public static final RegistryObject<Block> ALLOY_SMELTER = registerBlock("alloy_smelter",
            () -> new AlloySmelterBlock(AbstractBlock.Properties.create(Material.IRON).harvestTool(ToolType.PICKAXE).setRequiresTool()));

    public static final RegistryObject<Block> THERMITE_FURNACE = registerBlock("thermite_furnace",
            () -> new ThermiteFurnaceBlock(AbstractBlock.Properties.create(Material.IRON).harvestTool(ToolType.PICKAXE).setRequiresTool()));







    //Material_Blocks
    public static final RegistryObject<Block> LITHIUM_BLOCK = registerBlock("lithium_block",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(1).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(2, 1)));

    public static final RegistryObject<Block> HYDERMANIUM_BLOCK = registerBlock("hydermanium_block",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(13, 600)));










    private static  <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        registerBlockItem(name, toReturn);

        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(ModItemGroup.MAIN_GROUP)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
