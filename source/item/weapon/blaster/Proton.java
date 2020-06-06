package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityProton;

import javax.annotation.Nullable;

public class Proton extends BaseBlaster {
	public Proton(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("Proton");
		setRegistryName("aoa3:proton");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.ILLUSION_SMG_FIRE;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityProton(shooter, this, 60));
	}
}
