package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityLightBlasterShot;

import javax.annotation.Nullable;

public class LightBlaster extends BaseBlaster {
	public LightBlaster(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("LightBlaster");
		setRegistryName("aoa3:light_blaster");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.MIND_BLASTER_FIRE;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityLightBlasterShot(shooter, this, 60));
	}
}
