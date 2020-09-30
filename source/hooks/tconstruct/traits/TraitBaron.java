package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitBaron extends AbstractTrait {
	public TraitBaron() {
		super("baron", 0xCE0000);
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		if (random.nextFloat() < 0.2f)
			return newDamage + 3;

		return newDamage;
	}
}
