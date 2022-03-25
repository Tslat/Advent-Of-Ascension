package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.misc.FakeTntEntity;

import javax.annotation.Nullable;

public class BombCarrierEntity extends AoAMeleeMob {
	private int cooldown = 150;

	public BombCarrierEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
