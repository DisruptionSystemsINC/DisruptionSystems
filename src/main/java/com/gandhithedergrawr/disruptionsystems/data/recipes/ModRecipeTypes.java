package com.gandhithedergrawr.disruptionsystems.data.recipes;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.gandhithedergrawr.disruptionsystems.disruptionsystems.MOD_ID;

public class ModRecipeTypes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID);

    public static final RegistryObject<AlloySmelterRecipe.Serializer> ALLOYING_SERIALIZER
            = RECIPE_SERIALIZER.register("alloying", AlloySmelterRecipe.Serializer::new);

    public static IRecipeType<AlloySmelterRecipe> ALLOYING_RECIPE
            = new AlloySmelterRecipe.AlloyingRecipeType();

    public static final RegistryObject<ThermiteFurnaceRecipe.Serializer> THERMITE_BLASTING_SERIALIZER
            = RECIPE_SERIALIZER.register("thermite_blasting", ThermiteFurnaceRecipe.Serializer::new);

    public static IRecipeType<ThermiteFurnaceRecipe> THERMITE_BLASTING_RECIPE
            = new ThermiteFurnaceRecipe.ThermiteBlastingRecipeType();

    public static final RegistryObject<PowderMillRecipe.Serializer> CRUSHING_SERIALIZER
            = RECIPE_SERIALIZER.register("crushing", PowderMillRecipe.Serializer::new);

    public static IRecipeType<PowderMillRecipe> CRUSHING_RECIPE
            = new PowderMillRecipe.CrushingRecipeType();

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);

        Registry.register(Registry.RECIPE_TYPE, AlloySmelterRecipe.TYPE_ID, ALLOYING_RECIPE);
        Registry.register(Registry.RECIPE_TYPE, ThermiteFurnaceRecipe.TYPE_ID, THERMITE_BLASTING_RECIPE);
        Registry.register(Registry.RECIPE_TYPE, PowderMillRecipe.TYPE_ID, CRUSHING_RECIPE);
    }

}
