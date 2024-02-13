package net.tslat.aoa3.integration.jei.recipe;

import mezz.jei.api.gui.builder.IIngredientAcceptor;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IModIdHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.tslat.aoa3.util.LocaleUtil;

public abstract class ContainerRecipeCategory<T> implements IRecipeCategory<T> {
    protected final RecipeType<T> recipeType;
    protected final Component title;
    protected final IGuiHelper guiHelper;
    protected final IModIdHelper idHelper;
    protected final IIngredientManager ingredientManager;
    protected final IDrawable background;
    protected final IDrawable icon;

    public ContainerRecipeCategory(RecipeType<T> recipeType, IGuiHelper guiHelper, IModIdHelper idHelper, IIngredientManager ingredientManager) {
        this.recipeType = recipeType;
        this.title = LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("recipe", recipeType.getUid().getPath()));
        this.guiHelper = guiHelper;
        this.idHelper = idHelper;
        this.ingredientManager = ingredientManager;
        this.background = createBackgroundDrawRegion(guiHelper, getBackgroundTexture());
        this.icon = guiHelper.createDrawableItemStack(getRecipeCatalyst().asItem().getDefaultInstance());
    }

    protected abstract IDrawable createBackgroundDrawRegion(IGuiHelper guiHelper, ResourceLocation backgroundTexture);
    protected abstract ItemLike getRecipeCatalyst();
    protected abstract int backgroundTextureU();
    protected abstract int backgroundTextureV();
    protected abstract void createRecipeLayout(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses);
    protected abstract ResourceLocation getBackgroundTexture();

    @Override
    public final void setRecipe(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses) {
        createRecipeLayout(new TextureBasedRecipeLayoutBuilder(builder, backgroundTextureV(), backgroundTextureV()), recipe, focuses);
    }

    @Override
    public Component getTitle() {
        return this.title;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    public record TextureBasedRecipeLayoutBuilder(IRecipeLayoutBuilder wrappedBuilder, int xOffset, int yOffset) implements IRecipeLayoutBuilder {
        @Override
        public IRecipeSlotBuilder addSlot(RecipeIngredientRole recipeIngredientRole, int x, int y) {
            return this.wrappedBuilder.addSlot(recipeIngredientRole, x - this.xOffset, y - this.yOffset);
        }

        @Override
        public IIngredientAcceptor<?> addInvisibleIngredients(RecipeIngredientRole recipeIngredientRole) {
            return this.wrappedBuilder.addInvisibleIngredients(recipeIngredientRole);
        }

        @Override
        public void moveRecipeTransferButton(int posX, int posY) {
            this.wrappedBuilder.moveRecipeTransferButton(posX - this.xOffset, posY - this.yOffset);
        }

        @Override
        public void setShapeless() {
            this.wrappedBuilder.setShapeless();
        }

        @Override
        public void setShapeless(int posX, int posY) {
            this.wrappedBuilder.setShapeless(posX - this.xOffset, posY - this.yOffset);
        }

        @Override
        public void createFocusLink(IIngredientAcceptor<?>... slots) {
            this.wrappedBuilder.createFocusLink(slots);
        }
    }
}
