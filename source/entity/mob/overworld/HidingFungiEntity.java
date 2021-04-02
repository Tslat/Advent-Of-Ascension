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

		xpReward = 0;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.4f;
	}

	@Override
	public boolean isPushable() {
		return true;
	}

	@Override
	protected void registerGoals() {}

	@Override
	protected void onHit(DamageSource source, float amount) {
		if (!level.isClientSide && HunterUtil.canAttackTarget(this, source.getEntity(), true)) {
			LivingFungiEntity livingFungi = new LivingFungiEntity(AoAEntities.Mobs.LIVING_FUNGI.get(), level);

			livingFungi.moveTo(getX(), getY(), getZ(), yRot, xRot);
			level.addFreshEntity(livingFungi);
			livingFungi.hurt(source, amount);
			playSound(AoASounds.ENTITY_LIVING_FUNGI_SPAWN.get(), 1.0f, 1.0f);
			remove();
		}
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public void playerTouch(PlayerEntity entityIn) {}

	@Override
	protected void doPush(Entity entityIn) {}

	@Override
	public void push(double x, double y, double z) {}

}