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

public class EntityDyrehorn extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 1f;

	public EntityDyrehorn(World world) {
		super(world, entityWidth, 2f);
		setSlipperyMovement();
		setAIMoveSpeed(1.8f);

		mobProperties.add(Enums.MobProperties.MAGIC_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 1.625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 110;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobDyrehornLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobDyrehornDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobDyrehornHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensPrecasian, 1);

		if (rand.nextInt(15 - lootingMod) == 0)
			dropItem(ItemRegister.realmstoneGardencia, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isMagicDamage(source, this, 1);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
