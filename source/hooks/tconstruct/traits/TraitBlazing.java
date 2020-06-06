package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitBlazing extends AbstractTrait {
	public TraitBlazing() {
		super("blazing", 0x6B1919);
	}

	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		target.setFire(3);
	}
}
