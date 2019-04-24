package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityLivingFungi extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 1.1f;

	public EntityLivingFungi(World world) {
		super(world, entityWidth, 2.4375f);
		this.setSlipperyMovement();
		this.setAIMoveSpeed(1.8f);
	}

	@Override
	public float getEyeHeight() {
		return 1.3125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobFungiLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobFungiDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobFungiHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.heavyStep;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.85d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 200;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 16;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(60 - lootingMod) == 0)
			dropItem(ArmourRegister.BiogenicBody, 1);

		if (rand.nextInt(60 - lootingMod) == 0)
			dropItem(ArmourRegister.BiogenicLegs, 1);

		if (rand.nextInt(60 - lootingMod) == 0)
			dropItem(ArmourRegister.BiogenicHelmet, 1);

		if (rand.nextInt(60 - lootingMod) == 0)
			dropItem(ArmourRegister.BiogenicBoots, 1);

		if (rand.nextInt(100 - lootingMod) == 0)
			dropItem(WeaponRegister.gunMK, 1);

	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinSilver, 5 + rand.nextInt(4 + lootingMod));
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
		if (super.attackEntityAsMob(target)) {
			if (target instanceof EntityLivingBase) {
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 1, true, true));
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 3, true, true));

				if (EntityUtil.getCurrentHealthPercent(this) <= 50)
					heal(30f);
			}

			return true;
		}

		return false;
	}

	@Override
	public int getHunterReq() {
		return 77;
	}

	@Override
	public float getHunterXp() {
		return 1500;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
