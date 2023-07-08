package com.gandhithedergrawr.disruptionsystems.integration.jei;

import com.gandhithedergrawr.disruptionsystems.data.recipes.AlloySmelterRecipe;
import com.gandhithedergrawr.disruptionsystems.data.recipes.ModRecipeTypes;
import com.gandhithedergrawr.disruptionsystems.data.recipes.PowderMillRecipe;
import com.gandhithedergrawr.disruptionsystems.data.recipes.ThermiteFurnaceRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.stream.Collectors;

import static com.gandhithedergrawr.disruptionsystems.disruptionsystems.MOD_ID;
@JeiPlugin
public class DisruptionSystemsJEI implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AlloySmelterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ThermiteFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new PowderMillRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }



    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().world).getRecipeManager();
        registration.addRecipes(rm.getRecipesForType(ModRecipeTypes.ALLOYING_RECIPE).stream().filter(r -> r instanceof AlloySmelterRecipe).collect(Collectors.toList()),AlloySmelterRecipeCategory.UID);
        registration.addRecipes(rm.getRecipesForType(ModRecipeTypes.THERMITE_BLASTING_RECIPE).stream().filter(r -> r instanceof ThermiteFurnaceRecipe).collect(Collectors.toList()), ThermiteFurnaceRecipeCategory.UID);
        registration.addRecipes(rm.getRecipesForType(ModRecipeTypes.CRUSHING_RECIPE).stream().filter(r -> r instanceof PowderMillRecipe).collect(Collectors.toList()), PowderMillRecipeCategory.UID);
    }
}

