package net.tslat.aoa3.integration.jei.ingredient.type.imbuing;

import com.google.common.base.MoreObjects;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.common.platform.IPlatformRegistry;
import mezz.jei.common.platform.Services;
import mezz.jei.common.util.TagUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public class ImbuingIngredientHelper implements IIngredientHelper<EnchantmentInstance> {
    private final IIngredientType<EnchantmentInstance> ingredientType;
    private final IPlatformRegistry<Enchantment> registry;

    public ImbuingIngredientHelper(IIngredientType<EnchantmentInstance> ingredientType) {
        this.ingredientType = ingredientType;
        this.registry = Services.PLATFORM.getRegistry(Registries.ENCHANTMENT);
    }

    @Override
    public IIngredientType<EnchantmentInstance> getIngredientType() {
        return this.ingredientType;
    }

    @Override
    public String getDisplayName(EnchantmentInstance ingredient) {
        return ingredient.enchantment.getFullname(ingredient.level).getString();
    }

    @Override
    public String getUniqueId(EnchantmentInstance ingredient, UidContext context) {
        return new StringBuilder("enchantment:").append(getResourceLocation(ingredient).toString() + "_" + ingredient.level).toString();
    }

    @Override
    public ResourceLocation getResourceLocation(EnchantmentInstance ingredient) {
        return this.registry.getRegistryName(ingredient.enchantment)
                .orElseThrow(() -> {
                    String ingredientInfo = getErrorInfo(ingredient);

                    return new IllegalStateException("null registry name for: " + ingredientInfo);
                });
    }

    @Override
    public ItemStack getCheatItemStack(EnchantmentInstance ingredient) {
        return EnchantedBookItem.createForEnchantment(ingredient);
    }

    @Override
    public EnchantmentInstance copyIngredient(EnchantmentInstance ingredient) {
        return new EnchantmentInstance(ingredient.enchantment, ingredient.level);
    }

    @Override
    public EnchantmentInstance normalizeIngredient(EnchantmentInstance ingredient) {
        return new EnchantmentInstance(ingredient.enchantment, 1);
    }

    @Override
    public Stream<ResourceLocation> getTagStream(EnchantmentInstance ingredient) {
        return BuiltInRegistries.ENCHANTMENT.getResourceKey(ingredient.enchantment)
                .flatMap(BuiltInRegistries.ENCHANTMENT::getHolder)
                .map(Holder::tags)
                .orElse(Stream.of())
                .map(TagKey::location);
    }

    @Override
    public Optional<ResourceLocation> getTagEquivalent(Collection<EnchantmentInstance> ingredients) {
        return TagUtil.getTagEquivalent(ingredients, instance -> instance.enchantment, BuiltInRegistries.ENCHANTMENT::getTags);
    }

    @Override
    public String getErrorInfo(@Nullable EnchantmentInstance ingredient) {
        if (ingredient == null)
            return "null";

        MoreObjects.ToStringHelper toStringHelper = MoreObjects.toStringHelper(ingredient.getClass());
        Enchantment enchant = ingredient.enchantment;

        if (enchant != null) {
            toStringHelper.add("Enchantment", getDisplayName(ingredient));
        }
        else {
            toStringHelper.add("Enchantment", "null");
        }

        toStringHelper.add("Level", ingredient.level);

        return toStringHelper.toString();
    }
}
