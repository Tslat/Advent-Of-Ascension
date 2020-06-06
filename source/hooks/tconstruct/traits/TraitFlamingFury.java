package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitFlamingFury extends AbstractTrait {
	public TraitFlamingFury() {
		super("flaming_fury", 0xFF5D00);
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		if (player.isBurning())
			return newDamage + 3;

		return newDamage;
	}
}
