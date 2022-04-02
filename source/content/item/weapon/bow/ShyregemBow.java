package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.registration.item.AoAItems;

public class ShyregemBow extends BaseBow {
	public ShyregemBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack repairMaterial) {
		Item repairItem = repairMaterial.getItem();

		return repairItem == AoAItems.SHYRESTONE_INGOT.get() || repairItem == AoAItems.SHYREGEM.get() || super.isValidRepairItem(stack, repairMaterial);
	}
}