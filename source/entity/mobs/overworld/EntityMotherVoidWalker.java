package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityMotherVoidWalker extends AoAMeleeMob {
	public static final float entityWidth = 1.0f;

	public EntityMotherVoidWalker(World world) {
		super(world, entityWidth, 1.62f);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobMotherVoidWalkerLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobMotherVoidWalkerDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobMotherVoidWalkerDeath;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityMotherVoidWalker;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 35 && super.getCanSpawnHere();
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200, 0, true, true));
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
