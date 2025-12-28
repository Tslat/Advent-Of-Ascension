package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.Tags;

public class CrystalCarver extends AoAGun {
	public CrystalCarver(Item.Properties properties) {
		super(properties);
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack repairMaterial) {
		return repairMaterial.is(Tags.Items.GEMS) || super.isValidRepairItem(stack, repairMaterial);
	}
}
