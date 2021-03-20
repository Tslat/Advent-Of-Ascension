package net.tslat.aoa3.entity.minion;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class HiveSoldierEntity extends AoAMinion {
	public HiveSoldierEntity(EntityType<? extends TameableEntity> entityType, final World world){
		super(entityType, world, 250);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.59375f;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_PARASECT_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_PARASECT_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_PARASECT_DEATH.get();
	}
}
