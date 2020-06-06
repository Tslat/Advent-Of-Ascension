package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.tslat.aoa3.library.Enums;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitToxic extends AbstractTrait {
	public TraitToxic() {
		super("toxic", 0x6BFFBE);
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		if (wasHit && wasCritical) {
			EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(player.world);

			cloud.setDuration(3);
			cloud.setRadius(2);
			cloud.setRadiusPerTick(-0.015f);
			cloud.setColor(Enums.RGBIntegers.TOXIC_GREEN);
			cloud.addEffect(new PotionEffect(MobEffects.POISON, 60, 1, true, false));
			cloud.setPosition(target.posX, target.posY, target.posZ);
			player.world.spawnEntity(cloud);
		}
	}
}
