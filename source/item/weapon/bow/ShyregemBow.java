package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.ItemRegister;

public class ShyregemBow extends BaseBow {
	public ShyregemBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("ShyregemBow");
		setRegistryName("aoa3:shyregem_bow");
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		Item repairItem = repairMaterial.getItem();

		return repairItem == ItemRegister.ingotShyrestone || repairItem == ItemRegister.gemShyregem || super.getIsRepairable(stack, repairMaterial);
	}
}