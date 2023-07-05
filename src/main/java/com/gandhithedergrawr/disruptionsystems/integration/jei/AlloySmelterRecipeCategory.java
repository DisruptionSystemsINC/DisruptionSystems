package com.gandhithedergrawr.disruptionsystems.integration.jei;
import com.gandhithedergrawr.disruptionsystems.block.ModBlocks;
import com.gandhithedergrawr.disruptionsystems.data.recipes.AlloySmelterRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static com.gandhithedergrawr.disruptionsystems.disruptionsystems.MOD_ID;
public class AlloySmelterRecipeCategory implements IRecipeCategory<AlloySmelterRecipe> {

    public final static ResourceLocation UID = new ResourceLocation(MOD_ID, "alloying");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(MOD_ID, "textures/gui/alloy_smelter_gui.png");
    private final IDrawable background;
    private final IDrawable icon;

    public AlloySmelterRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.ALLOY_SMELTER.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends AlloySmelterRecipe> getRecipeClass() {
        return AlloySmelterRecipe.class;
    }

    @Override
    public String getTitle() {
        return ModBlocks.ALLOY_SMELTER.get().getTranslatedName().getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(AlloySmelterRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, AlloySmelterRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 25, 31);
        recipeLayout.getItemStacks().init(1, true, 47, 31);
        recipeLayout.getItemStacks().init(2, false, 120, 31);
        recipeLayout.getItemStacks().set(ingredients);
    }
}
