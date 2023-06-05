package org._9636dev.autosmithingtablerewrite.integration.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org._9636dev.autosmithingtablerewrite.AutoSmithingTableMod;
import org._9636dev.autosmithingtablerewrite.common.recipe.AutoSmithingRecipe;
import org.jetbrains.annotations.NotNull;

public class AutoSmithingRecipeCategory implements IRecipeCategory<AutoSmithingRecipe> {
    static final RecipeType<AutoSmithingRecipe> recipeType = new RecipeType<>(new ResourceLocation(AutoSmithingTableMod.MODID, "auto_smithing"), AutoSmithingRecipe.class);

    private final IJeiHelpers helpers;
    public AutoSmithingRecipeCategory(IJeiHelpers helpers) {
        this.helpers = helpers;
    }

    @Override
    public RecipeType<AutoSmithingRecipe> getRecipeType() {
        return recipeType;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("title.autosmithingtable.jei.auto_smithing");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return null;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return null;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull AutoSmithingRecipe recipe, @NotNull IFocusGroup focuses) {

    }
}
