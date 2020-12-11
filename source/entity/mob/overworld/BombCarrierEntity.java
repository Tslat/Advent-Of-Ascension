package net.tslat.aoa3.entity.mob.overworld;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.misc.FakeTntEntity;

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

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 2;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
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
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	public void livingTick() {
		super.livingTick();

		if (!isAlive())
			return;

		if (cooldown > 0)
			--cooldown;

		if (cooldown < 3 && !world.isRemote) {
			if (getAttackTarget() == null && getRevengeTarget() == null)
				return;

			cooldown = 150;
			FakeTntEntity tnt = new FakeTntEntity(world, getPosition(), this);

			tnt.setFuse(80);
			world.addEntity(tnt);
		}
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
