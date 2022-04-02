package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;
import java.util.HashSet;

public class TricksterEntity extends AoAMeleeMob {
	private int invisCooldown = 160;
	private int cloneCooldown = 0;
	private HashSet<TricksterCloneEntity> clones = new HashSet<TricksterCloneEntity>();

	public TricksterEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.65f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_TRICKSTER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_TRICKSTER_HURT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_TRICKSTER_HURT.get();
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (!isAlive())
			return;

		if (invisCooldown > 0)
			--invisCooldown;

		if (cloneCooldown > 1)
			--cloneCooldown;

		if (!level.isClientSide) {
			if (invisCooldown == 0) {
				cloneCooldown = 60;
				invisCooldown = 240;

				EntityUtil.applyPotions(this, new EffectBuilder(MobEffects.INVISIBILITY, 60));
				playSound(AoASounds.ENTITY_TRICKSTER_HIDE.get(), 1.0f, 1.0f);
			}

			if (cloneCooldown == 1 && level.getEntitiesOfClass(TricksterCloneEntity.class, getBoundingBox().inflate(10)).size() < 5) {
				TricksterCloneEntity clone = new TricksterCloneEntity(AoAMobs.TRICKSTER_CLONE.get(), level);

				clone.setPos(getX(), getY(), getZ());

				level.addFreshEntity(clone);
				clones.add(clone);
				cloneCooldown = 0;
			}
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			for (TricksterCloneEntity clone : clones) {
				clone.discard();
			}
		}
	}

}
