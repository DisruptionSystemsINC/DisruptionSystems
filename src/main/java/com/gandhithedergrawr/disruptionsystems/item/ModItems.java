package com.gandhithedergrawr.disruptionsystems.item;

import com.gandhithedergrawr.disruptionsystems.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.gandhithedergrawr.disruptionsystems.item.ModItemGroup.MAIN_GROUP;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, disruptionsystems.MOD_ID);


    public static final RegistryObject<Item> HYDERMANIUM_INGOT = ITEMS.register("hydermanium_ingot",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> LITHIUM_INGOT = ITEMS.register("lithium_ingot",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> HYDRANIUM_INGOT = ITEMS.register("hydranium_ingot",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> HYDERMANIUM_PICKAXE = ITEMS.register("hydermanium_pickaxe",
            () -> new PickaxeItem(ModItemTier.HYDERMANIUM,2, 1f, new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> MATTERENERGY_SWORD = ITEMS.register("matterenergy_sword",
            () -> new SwordItem(ModItemTier.HYDERMANIUM,10, 2f, new Item.Properties().group(MAIN_GROUP)));


    public static final RegistryObject<Item> DISRUPTION_CORE = ITEMS.register("disruption_core",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> MATTERENERY_CORE = ITEMS.register("matterenergy_core",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));

    public static final RegistryObject<Item> MACHINE_CASING = ITEMS.register("machine_casing",
            () -> new Item(new Item.Properties().group(MAIN_GROUP)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }



}
