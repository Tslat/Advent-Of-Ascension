package net.tslat.aoa3.entity.mob.overworld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.skill.HunterUtil;

public class HidingFungiEntity extends AoAMeleeMob {
	public HidingFungiEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.4f;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	protected void registerGoals() {}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 0;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected void onHit(DamageSource source, float amount) {
		if (!world.isRemote && HunterUtil.canAttackTarget(this, source.getTrueSource(), true)) {
			LivingFungiEntity livingFungi = new LivingFungiEntity(AoAEntities.Mobs.LIVING_FUNGI.get(), world);

			livingFungi.setLocationAndAngles(getPosX(), getPosY(), getPosZ(), rotationYaw, rotationPitch);
			world.addEntity(livingFungi);
			livingFungi.attackEntityFrom(source, amount);
			playSound(AoASounds.ENTITY_LIVING_FUNGI_SPAWN.get(), 1.0f, 1.0f);
			remove();
		}
	}

	@Override
	public boolean isPushedByWater() {
		return false;
	}

	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn) {}

	@Override
	protected void collideWithEntity(Entity entityIn) {}

	@Override
	public void addVelocity(double x, double y, double z) {}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}