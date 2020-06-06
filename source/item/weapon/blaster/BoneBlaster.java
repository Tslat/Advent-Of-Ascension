package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityBonePellet;

import javax.annotation.Nullable;

public class BoneBlaster extends BaseBlaster {
	public BoneBlaster(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("BoneBlaster");
		setRegistryName("aoa3:bone_blaster");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.MINI_PISTOL_FIRE;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityBonePellet(shooter, this, 60));
	}
}
