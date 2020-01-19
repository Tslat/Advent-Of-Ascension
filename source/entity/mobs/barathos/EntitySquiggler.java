package net.tslat.aoa3.entity.mobs.barathos;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntitySquiggler extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.5625f;

	public EntitySquiggler(World world) {
		super(world, entityWidth, 1.6875f);

		mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
		isImmuneToFire = true;
	}

	@Override
	public float getEyeHeight() {
		return 1.4375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 76;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.27;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobSquigglerLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobSquigglerDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobSquigglerDeath;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entitySquiggler;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 27 && super.getCanSpawnHere();
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return source.isFireDamage();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (getAttackTarget() != null && getAttackTarget().getDistance(this) < 20 && getAttackTarget().canEntityBeSeen(this))
			getAttackTarget().setFire(8);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
