package net.tslat.aoa3.entity.mobs.precasia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityMegatherium extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 1.7f;

	public EntityMegatherium(World world) {
		super(world, entityWidth, 3f);

		this.mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
		this.mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 2.5625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 250;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 12;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobMegatheriumLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobMegatheriumDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobMegatheriumHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.veryHeavyStep;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensPrecasian, 1);

		if (rand.nextInt(200 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitAncient, 1);
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isGunDamage(source) || EntityUtil.isRangedDamage(source, this, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.ivory, 1 + rand.nextInt(4 + lootingMod));
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
