package net.tslat.aoa3.integration.jei.ingredient.type.imbuing;

import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

public class ImbuingIngredientType implements IIngredientTypeWithSubtypes<Enchantment, EnchantmentInstance> {
    public static ImbuingIngredientType INSTANCE = null;

    public ImbuingIngredientType() {
        INSTANCE = this;
    }

    @Override
    public Class<? extends EnchantmentInstance> getIngredientClass() {
        return EnchantmentInstance.class;
    }

    @Override
    public Class<? extends Enchantment> getIngredientBaseClass() {
        return Enchantment.class;
    }

    @Override
    public Enchantment getBase(EnchantmentInstance ingredient) {
        return ingredient.enchantment;
    }
}
