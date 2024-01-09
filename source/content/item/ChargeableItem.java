package net.tslat.aoa3.content.item;

import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.registration.AoADataAttachments;

public interface ChargeableItem {
    default float minRequiredCharge() {
        return 0;
    }

    default float maxCharge() {
        return Float.MAX_VALUE;
    }

    default boolean hasEnoughCharge(ItemStack stack) {
        return getCharge(stack) >= minRequiredCharge();
    }

    default float getCharge(ItemStack stack) {
        return stack.getData(AoADataAttachments.CHARGE);
    }

    default void setCharge(ItemStack stack, float charge) {
        stack.setData(AoADataAttachments.CHARGE, Mth.clamp(charge, 0, maxCharge()));
    }

    default void addCharge(ItemStack stack, float charge) {
        stack.setData(AoADataAttachments.CHARGE, Math.min(getCharge(stack) + charge, maxCharge()));
    }

    default float subtractCharge(ItemStack stack, float charge, boolean force) {
        float currentCharge = getCharge(stack);

        if (currentCharge < charge && !force)
            return 0;

        float newValue = Math.max(0, currentCharge - charge);

        stack.setData(AoADataAttachments.CHARGE, newValue);

        return currentCharge - newValue;
    }
}
