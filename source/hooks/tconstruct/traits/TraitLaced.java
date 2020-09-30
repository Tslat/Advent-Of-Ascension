package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitLaced extends AbstractTrait {
	public TraitLaced() {
		super("laced", 0x6BFFBE);
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		if (wasHit)
			target.addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 0, true, false));
	}
}
