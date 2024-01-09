package net.tslat.aoa3.content.recipe.infusiontable;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.tslat.aoa3.common.container.InfusionTableContainer;
import net.tslat.aoa3.common.registration.block.AoABlocks;

public abstract class InfusionTableRecipe implements Recipe<InfusionTableContainer.InfusionInventory> {
    protected static <T extends InfusionTableRecipe> Products.P2<RecordCodecBuilder.Mu<T>, Integer, FloatProvider> infusionTableRecipe(RecordCodecBuilder.Instance<T> builder) {
        return builder.group(
                ExtraCodecs.strictOptionalField(Codec.intRange(1, 1000), "infusion_level", 1).forGetter(InfusionTableRecipe::getInfusionLevelReq),
                ExtraCodecs.strictOptionalField(FloatProvider.CODEC, "infusion_xp", ConstantFloat.of(0)).forGetter(InfusionTableRecipe::getXpProvider));
    }

    private final int infusionLevelReq;
    private final FloatProvider xp;

    protected InfusionTableRecipe(int infusionLevelReq, FloatProvider xp) {
        this.infusionLevelReq = infusionLevelReq;
        this.xp = xp;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(AoABlocks.INFUSION_TABLE.get());
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= getIngredients().size() + 1;
    }

    public int getInfusionLevelReq() {
        return this.infusionLevelReq;
    }

    public FloatProvider getXpProvider() {
        return this.xp;
    }

    public float getXp(RandomSource random) {
        return this.xp.sample(random);
    }
}
