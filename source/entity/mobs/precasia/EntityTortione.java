package net.tslat.aoa3.entity.mobs.precasia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityTortione extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 1.75f;

	public EntityTortione(World world) {
		super(world, entityWidth, 1.875f);

		this.mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
		this.mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
		this.mobProperties.add(Enums.MobProperties.MELEE_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 1.5625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.6;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
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
		return SoundsRegister.mobTortioneLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobTortioneDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobTortioneHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.heavyStep;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensPrecasian, 2 + rand.nextInt(4 + lootingMod));

		if (rand.nextInt(30 - lootingMod) == 0)
			dropItem(WeaponRegister.blasterProton, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isGunDamage(source) || EntityUtil.isMeleeDamage(source) || EntityUtil.isRangedDamage(source, this, 1);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
