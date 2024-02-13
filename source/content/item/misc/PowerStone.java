package net.tslat.aoa3.content.item.misc;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.tslat.aoa3.common.registration.entity.variant.PixonVariant;
import org.jetbrains.annotations.Nullable;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class PowerStone extends Item {
    private final int enchantLevel;
    private final int burnTime;
    private final IntSupplier colour;

    public PowerStone(int enchantLevel, int burnTime, Supplier<PixonVariant> pixonVariant) {
        super(new Item.Properties());

        this.enchantLevel = enchantLevel;
        this.burnTime = burnTime;
        this.colour = () -> pixonVariant.get().primaryColour();
    }

    public int getEnchantLevel() {
        return this.enchantLevel;
    }

    public int getColour() {
        return this.colour.getAsInt();
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burnTime;
    }
}
