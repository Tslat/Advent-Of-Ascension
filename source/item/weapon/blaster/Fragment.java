package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityFragmentShot;

import javax.annotation.Nullable;

public class Fragment extends BaseBlaster {
	public Fragment(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("Fragment");
		setRegistryName("aoa3:fragment");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.ILLUSION_SMG_FIRE;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityFragmentShot(shooter, this, 60));
	}
}
