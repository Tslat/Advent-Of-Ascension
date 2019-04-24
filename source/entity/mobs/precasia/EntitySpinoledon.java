package net.tslat.aoa3.entity.mobs.precasia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntitySpinoledon extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 0.7f;

	public EntitySpinoledon(World world) {
		super(world, entityWidth, 1.5625f);
	}

	@Override
	public float getEyeHeight() {
		return 1.40625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.75;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 150;
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
		return SoundsRegister.mobSpinoledonLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobSpinoledonDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobSpinoledonHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.dinoStep;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensPrecasian, 1 + rand.nextInt(2 + lootingMod));

		if (rand.nextInt(200 - lootingMod) == 0)
			dropItem(WeaponRegister.gunSquadGun, 1);

		if (rand.nextInt(200 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitPrecasian, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase && getHealth() < 60)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 20, 2, true, true));
	}

	@Override
	public int getHunterReq() {
		return 59;
	}

	@Override
	public float getHunterXp() {
		return 240;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
