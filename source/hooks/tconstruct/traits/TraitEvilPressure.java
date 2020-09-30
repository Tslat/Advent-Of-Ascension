package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.ArrayList;

public class TraitEvilPressure extends AbstractTrait {
	public TraitEvilPressure() {
		super("evil_pressure", 0xC68A2F);
	}

	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
		if (!player.world.isRemote && random.nextInt(Math.max(1, (int)Math.ceil(target.getMaxHealth() / damage))) == 0) {
			ArrayList<PotionEffect> negativeEffects = new ArrayList<PotionEffect>();

			target.getActivePotionEffects().forEach(effect -> {
				if (effect.getPotion().isBadEffect())
					negativeEffects.add(effect);
			});

			if (negativeEffects.isEmpty())
				return;

			PotionEffect effect = negativeEffects.get(random.nextInt(negativeEffects.size()));

			effect.combine(new PotionEffect(effect.getPotion(), effect.getDuration(), effect.getAmplifier() + 2, effect.getIsAmbient(), effect.doesShowParticles()));
			target.addPotionEffect(effect);
		}
	}
}
