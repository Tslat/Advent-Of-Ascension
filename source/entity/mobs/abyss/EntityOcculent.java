package net.tslat.aoa3.entity.mobs.abyss;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.penumbra.EntityPenumbra;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityOcculent extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;

	public EntityOcculent(World world) {
		super(world, entityWidth, 1.5f);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobOcculentLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobOcculentDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobOcculentHit;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof EntityPenumbra)
			return;

		super.setAttackTarget(target);
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensAbyss, 1 + rand.nextInt(1 + lootingMod));

		if (rand.nextInt(75 - lootingMod) == 0)
			dropItem(WeaponRegister.blasterSoulSpark, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote && getAttackTarget() instanceof EntityPlayer && getAttackTarget().isEntityAlive() && getDistance(getAttackTarget()) < 10) {
			if (EntityUtil.isPlayerLookingAtEntity((EntityPlayer)getAttackTarget(), this) && canEntityBeSeen(getAttackTarget()))
				getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), 6.0f);
		}
	}
}
