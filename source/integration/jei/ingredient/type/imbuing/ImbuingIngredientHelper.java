package net.tslat.aoa3.integration.jei.ingredient.type.imbuing;

import com.google.common.base.MoreObjects;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.common.util.RegistryUtil;
import mezz.jei.common.util.TagUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.tslat.aoa3.advent.Logging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public record ImbuingIngredientHelper(IIngredientType<EnchantmentInstance> ingredientType) implements IIngredientHelper<EnchantmentInstance> {
    @Override
    public IIngredientType<EnchantmentInstance> getIngredientType() {
        return this.ingredientType;
    }

    @NotNull
    @Override
    public String getDisplayName(EnchantmentInstance ingredient) {
        return Enchantment.getFullname(ingredient.enchantment, ingredient.level).getString();
    }

    @Override
    public Object getUid(EnchantmentInstance ingredient, UidContext context) {
        return "enchantment:" + getResourceLocation(ingredient) + "_" + ingredient.level;
    }

    // TODO
    @SuppressWarnings("removal")
    @Override
    public String getUniqueId(EnchantmentInstance ingredient, UidContext context) {
        return null;
    }

    @NotNull
    @Override
    public ResourceLocation getResourceLocation(EnchantmentInstance ingredient) {
        ResourceLocation id = RegistryUtil.getRegistry(Registries.ENCHANTMENT).getKey(ingredient.enchantment.value());

        if (id == null) {
            String ingredientInfo = getErrorInfo(ingredient);

            Logging.error("Found unregistered enchantment: " + ingredientInfo);

            return ResourceLocation.withDefaultNamespace("");
        }

        return id;
    }

    @NotNull
    @Override
    public ItemStack getCheatItemStack(EnchantmentInstance ingredient) {
        return EnchantedBookItem.createForEnchantment(ingredient);
    }

    @NotNull
    @Override
    public EnchantmentInstance copyIngredient(EnchantmentInstance ingredient) {
        return new EnchantmentInstance(ingredient.enchantment, ingredient.level);
    }

    @NotNull
    @Override
    public EnchantmentInstance normalizeIngredient(EnchantmentInstance ingredient) {
        return ingredient;
    }

    @NotNull
    @Override
    public Stream<ResourceLocation> getTagStream(EnchantmentInstance ingredient) {
        final Registry<Enchantment> registry = RegistryUtil.getRegistry(Registries.ENCHANTMENT);

        return registry.getResourceKey(ingredient.enchantment.value())
                .flatMap(registry::getHolder)
                .map(Holder::tags)
                .orElse(Stream.of())
                .map(TagKey::location);
    }

    @NotNull
    @Override
    public Optional<TagKey<?>> getTagKeyEquivalent(Collection<EnchantmentInstance> ingredients) {
        return TagUtil.getTagEquivalent(ingredients, instance -> instance.enchantment.value(), RegistryUtil.getRegistry(Registries.ENCHANTMENT)::getTags);
    }

    @NotNull
    @Override
    public String getErrorInfo(@Nullable EnchantmentInstance ingredient) {
        if (ingredient == null)
            return "null";

        MoreObjects.ToStringHelper toStringHelper = MoreObjects.toStringHelper(ingredient.getClass());
        Enchantment enchant = ingredient.enchantment.value();

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
