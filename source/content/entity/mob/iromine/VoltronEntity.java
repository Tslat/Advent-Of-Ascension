package net.tslat.aoa3.content.entity.mob.iromine;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class VoltronEntity extends AoAMeleeMob {
	public VoltronEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.78125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_VOLTRON_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_VOLTRON_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_VOLTRON_HURT.get();
	}

	@Override
	public void thunderHit(ServerLevel world, LightningBolt lightningBolt) {}

	@Override
	protected void onAttack(Entity target) {
		if (level instanceof ServerLevel)
			WorldUtil.spawnLightning((ServerLevel)level, null, target.getX(), target.getY(), target.getZ(), false, false);
	}
}
