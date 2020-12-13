package net.tslat.aoa3.entity.minion;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class ShadowStalkerEntity extends AoAMinion {
	public ShadowStalkerEntity(World world, LivingEntity caster) {
		this(AoAEntities.Minions.SHADOW_STALKER.get(), world);

		if (caster instanceof PlayerEntity)
			setTamedBy((PlayerEntity)caster);

		setPosition(caster.getPosX(), caster.getPosY(), caster.getPosZ());
	}

	public ShadowStalkerEntity(EntityType<? extends TameableEntity> entityType, final World world){
		super(entityType, world, 250);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 0.9375f;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25.0d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 15.0d;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_DISTORTER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_DISTORTER_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_DISTORTER_DEATH.get();
	}
}
