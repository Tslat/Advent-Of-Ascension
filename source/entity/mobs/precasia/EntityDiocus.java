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

public class EntityDiocus extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 0.75f;

	public EntityDiocus(World world) {
		super(world, entityWidth, 1f);

		this.mobProperties.add(Enums.MobProperties.EXPLOSION_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 0.84375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.75;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 160;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobDiocusLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobDiocusDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobDiocusHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(100 - lootingMod) == 0)
			dropItem(WeaponRegister.blasterPowerRay, 1);

		if (rand.nextInt(7) == 0)
			dropItem(ItemRegister.explosiveIdol, 1);

		if (rand.nextInt(200 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitPrecasian, 1);

		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensPrecasian, 1 + rand.nextInt(2 + lootingMod));
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 60, 5, true, true));
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 2, true, true));
		}

		world.createExplosion(this, posX, posY, posZ, 1.5f, false);
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return source.isExplosion();
	}

	@Override
	public int getHunterReq() {
		return 23;
	}

	@Override
	public float getHunterXp() {
		return 50;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
