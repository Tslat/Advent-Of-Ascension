package net.tslat.aoa3.content.entity.mob.nowhere;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class VisageEntity extends AoAMeleeMob {
	private VisageEntity mirageHost = null;

	public VisageEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	public VisageEntity(VisageEntity mirageHost) {
		this(AoAEntities.Mobs.VISAGE.get(), mirageHost.level);

		this.mirageHost = mirageHost;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
	public CreatureAttribute getMobType() {
		return CreatureAttribute.UNDEAD;
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
