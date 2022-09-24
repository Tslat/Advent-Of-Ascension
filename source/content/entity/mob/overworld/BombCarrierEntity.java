package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.misc.FakeTntEntity;

import javax.annotation.Nullable;

public class BombCarrierEntity extends AoAMeleeMob {
	private int cooldown = 150;

	public BombCarrierEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.2f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_BOMB_CARRIER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_BOMB_CARRIER_HURT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_BOMB_CARRIER_HURT.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (!isAlive())
			return;

		if (cooldown > 0)
			--cooldown;

		if (cooldown < 3 && !level.isClientSide) {
			if (getTarget() == null && getLastHurtByMob() == null)
				return;

			cooldown = 150;
			FakeTntEntity tnt = new FakeTntEntity(level, blockPosition(), this);

			tnt.setFuse(80);
			level.addFreshEntity(tnt);
		}
	}

}
