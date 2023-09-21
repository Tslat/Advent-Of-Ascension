package net.tslat.aoa3.content.entity.mob.unused;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;

public class HidingFungiEntity extends AoAMeleeMob<HidingFungiEntity> {
	public HidingFungiEntity(EntityType<? extends HidingFungiEntity> entityType, Level world) {
		super(entityType, world);

		xpReward = 0;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 0.4f;
	}

	@Override
	public boolean isPushable() {
		return true;
	}

	/*@Override
	protected void registerGoals() {}

	@Override
	protected void onHurt(DamageSource source, float amount) {
		if (!level().isClientSide) {
			LivingFungiEntity livingFungi = new LivingFungiEntity(AoAMobs.LIVING_FUNGI.get(), level());

			livingFungi.moveTo(getX(), getY(), getZ(), getYRot(), getXRot());
			level().addFreshEntity(livingFungi);
			livingFungi.hurt(source, amount);
			playSound(AoASounds.ENTITY_LIVING_FUNGI_SPAWN.get(), 1.0f, 1.0f);
			discard();
		}
	}*/

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public void playerTouch(Player entityIn) {}

	@Override
	protected void doPush(Entity entityIn) {}

	@Override
	public void push(double x, double y, double z) {}

}