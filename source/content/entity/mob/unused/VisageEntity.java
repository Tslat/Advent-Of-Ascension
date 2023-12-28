/*
package net.tslat.aoa3.content.entity.mob.nowhere;

import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;


public class VisageEntity extends AoAMeleeMob<VisageEntity> {
	private VisageEntity mirageHost = null;

	public VisageEntity(EntityType<? extends VisageEntity> entityType, Level world) {
		super(entityType, world);
	}

	public VisageEntity(VisageEntity mirageHost) {
		this(AoAMobs.VISAGE.get(), mirageHost.level);

		this.mirageHost = mirageHost;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.46875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ELUSIVE_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ELUSIVE_HURT.get();
	}

	@Nullable
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return null;
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (mirageHost != null) {
			remove();

			return false;
		}
		else {
			return super.hurt(source, amount);
		}
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (level.isClientSide) {
			if (mirageHost != null) {
				if (tickCount >= 600 || !mirageHost.isAlive())
					remove();
			}
			else if (RandomUtil.oneInNChance(200)) {
				VisageEntity visage = new VisageEntity(this);
				BlockPos newPos = RandomUtil.getRandomPositionWithinRange(blockPosition(), 5, 0, 5, true, level);

				visage.setPos(newPos.getX(), newPos.getY(), newPos.getZ());
				level.addFreshEntity(visage);
			}
		}
	}
}
*/
