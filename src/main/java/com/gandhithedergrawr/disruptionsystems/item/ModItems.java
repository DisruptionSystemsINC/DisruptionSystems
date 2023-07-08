package com.gandhithedergrawr.disruptionsystems.item;

import com.gandhithedergrawr.disruptionsystems.*;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.gandhithedergrawr.disruptionsystems.item.ModItemGroup.MAIN_GROUP;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, disruptionsystems.MOD_ID);

    //Ingots
    public static final RegistryObject<Item> HYDERMANIUM_INGOT = ITEMS.register("hydermanium_ingot",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> LITHIUM_INGOT = ITEMS.register("lithium_ingot",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> HYDRANIUM_INGOT = ITEMS.register("hydranium_ingot",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> ALUMINIUM_INGOT = ITEMS.register("aluminium_ingot",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));


    //Powered Tools


    public static final RegistryObject<Item> PPMESS_UNIT= ITEMS.register("ppmess_unit",
            () -> new BowItem(new Item.Properties().group(MAIN_GROUP).maxStackSize(1).maxDamage(50000)));



    //Tools

    public static final RegistryObject<Item> HYDERMANIUM_PICKAXE = ITEMS.register("hydermanium_pickaxe",
            () -> new PickaxeItem(ModItemTier.HYDERMANIUM,2, 1f, new Item.Properties().group(MAIN_GROUP).maxStackSize(1)));

    public static final RegistryObject<Item> MATTERENERGY_SWORD = ITEMS.register("matterenergy_sword",
            () -> new SwordItem(ModItemTier.HYDERMANIUM,10, 2f, new Item.Properties().group(MAIN_GROUP).maxStackSize(1)));

    public static final RegistryObject<Item> HYDERMANIUM_STIFFENED_BOW = ITEMS.register("hydermanium_stiffened_bow",
            () -> new BowItem(new Item.Properties().group(MAIN_GROUP).maxStackSize(1).maxDamage(50000)));








    //Crafting Items
    public static final RegistryObject<Item> DISRUPTION_CORE = ITEMS.register("disruption_core",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));


    public static final RegistryObject<Item> MATTERENERY_CORE = ITEMS.register("matterenergy_core",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> MACHINE_CASING = ITEMS.register("machine_casing",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));




    //Intermediates
    public static final RegistryObject<Item> ENRICHED_COAL = ITEMS.register("enriched_coal",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> HYDERMANIUM_WIRE = ITEMS.register("hydermanium_wire",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> ALUMINIUM_DUST = ITEMS.register("aluminium_dust",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> IRON_DUST = ITEMS.register("iron_dust",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }



}
