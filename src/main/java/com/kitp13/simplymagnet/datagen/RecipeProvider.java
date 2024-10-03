package com.kitp13.simplymagnet.datagen;

import com.kitp13.simplymagnet.SimplyMagnet;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider implements IConditionBuilder {


    public RecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        super.buildRecipes(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SimplyMagnet.PULLER_MAGNET.get())
                .pattern(" LI").pattern("R I").pattern("IDI")
                .define('L', Items.LAPIS_LAZULI).define('I', Items.IRON_INGOT).define('R', Items.REDSTONE).define('D', Items.DIAMOND)
                .unlockedBy("has_diamond", has(SimplyMagnet.PULLER_MAGNET)).save(recipeOutput);

    }
}
