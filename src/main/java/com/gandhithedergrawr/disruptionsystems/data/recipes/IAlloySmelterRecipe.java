package com.gandhithedergrawr.disruptionsystems.data.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import static com.gandhithedergrawr.disruptionsystems.disruptionsystems.MOD_ID;

public interface IAlloySmelterRecipe extends IRecipe<IInventory>{

    ResourceLocation TYPE_ID = new ResourceLocation(MOD_ID, "alloying");

    @Override
    default IRecipeType<?> getType(){
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    @Override
    default boolean canFit(int width, int height){
        return true;
    }

    @Override
    default boolean isDynamic() {
        return true;
    }
}
