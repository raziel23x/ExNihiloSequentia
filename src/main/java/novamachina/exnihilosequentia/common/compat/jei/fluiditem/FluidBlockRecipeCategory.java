package novamachina.exnihilosequentia.common.compat.jei.fluiditem;

import novamachina.exnihilosequentia.api.crafting.fluidItem.FluidItemRecipe;
import novamachina.exnihilosequentia.common.utility.Constants;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class FluidBlockRecipeCategory implements IRecipeCategory<FluidItemRecipe> {
    private static final ResourceLocation texture = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_fluid_block_transform.png");
    public static ResourceLocation UID = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "fluiditem");
    private final IDrawableStatic background;
    private final IDrawableStatic slotHighlight;

    public FluidBlockRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(texture, 0, 0, 166, 63);
        this.slotHighlight = guiHelper.createDrawable(texture, 166, 0, 18, 18);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends FluidItemRecipe> getRecipeClass() {
        return FluidItemRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Fluid Item Transformation";
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void setIngredients(FluidItemRecipe recipe, IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, recipe.getInputs());
        ingredients.setInput(VanillaTypes.FLUID, recipe.getFluidInBarrel());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResult());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, FluidItemRecipe recipe, IIngredients ingredients) {
        recipeLayout.getFluidStacks().init(0, true, 48, 37);
        recipeLayout.getItemStacks().init(0, true, 74, 9);
        recipeLayout.getItemStacks().init(1, false, 101, 36);

        recipeLayout.getFluidStacks().set(0, recipe.getFluidInBarrel());
        recipeLayout.getItemStacks().set(0, recipe.getInputs());
        recipeLayout.getItemStacks().set(1, recipe.getResult());
    }
}
