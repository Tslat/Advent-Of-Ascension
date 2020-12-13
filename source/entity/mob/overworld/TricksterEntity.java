package net.tslat.aoa3.entity.mob.overworld;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.HashSet;

public class TricksterEntity extends AoAMeleeMob {
	private int invisCooldown = 160;
	private int cloneCooldown = 0;
	private HashSet<TricksterCloneEntity> clones = new HashSet<TricksterCloneEntity>();

	public TricksterEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.65f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 35;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Override
	public int getMaxSpawnHeight() {
		return 20;
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
	public void livingTick() {
		super.livingTick();

		if (!isAlive())
			return;

		if (invisCooldown > 0)
			--invisCooldown;

		if (cloneCooldown > 1)
			--cloneCooldown;

		if (!world.isRemote) {
			if (invisCooldown == 0) {
				cloneCooldown = 60;
				invisCooldown = 240;

				EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.INVISIBILITY, 60));
				playSound(AoASounds.ENTITY_TRICKSTER_HIDE.get(), 1.0f, 1.0f);
			}

			if (cloneCooldown == 1 && world.getEntitiesWithinAABB(TricksterCloneEntity.class, getBoundingBox().grow(10)).size() < 5) {
				TricksterCloneEntity clone = new TricksterCloneEntity(AoAEntities.Mobs.TRICKSTER_CLONE.get(), world);

				clone.setPosition(getPosX(), getPosY(), getPosZ());

				world.addEntity(clone);
				clones.add(clone);
				cloneCooldown = 0;
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			for (TricksterCloneEntity clone : clones) {
				clone.remove();
			}
		}
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
