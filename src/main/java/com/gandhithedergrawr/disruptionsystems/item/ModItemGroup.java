package com.gandhithedergrawr.disruptionsystems.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup MAIN_GROUP = new ItemGroup("disruptionsystemstab")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ModItems.HYDERMANIUM_INGOT.get());
        }
    };
}
