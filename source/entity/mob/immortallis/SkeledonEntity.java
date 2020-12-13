package net.tslat.aoa3.entity.mob.immortallis;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class SkeledonEntity extends AoAMeleeMob {
	private int cloakCooldown = 80;

	public SkeledonEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.1875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 120;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11.5f;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SKELETON_AMBIENT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SKELETON_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_SKELETON_HURT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return null;
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.UNDEAD;
	}

	@Override
	public void livingTick() {
		super.livingTick();

		cloakCooldown--;

		if (cloakCooldown == 0) {
			cloakCooldown = 80;

			setMotion(getMotion().mul(0.5f, 1, 0.5f));
			EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.INVISIBILITY, 20));
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && world.getDimension().getType() == AoADimensions.IMMORTALLIS.type()) {
			Entity attacker = cause.getTrueSource();

			if (attacker instanceof PlayerEntity || attacker instanceof TameableEntity) {
				ServerPlayerEntity pl = null;

				if (attacker instanceof TameableEntity) {
					if (((TameableEntity)attacker).getOwner() instanceof ServerPlayerEntity)
						pl = (ServerPlayerEntity)((TameableEntity)attacker).getOwner();
				}
				else {
					pl = (ServerPlayerEntity)attacker;
				}

				if (pl != null) {
					PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

					if (plData.stats().getTribute(Deities.EREBON) < 100)
						plData.stats().addTribute(Deities.EREBON, Math.min(4, 100 - plData.stats().getTribute(Deities.EREBON)));

					if (plData.stats().getTribute(Deities.EREBON) >= 100)
						plData.sendThrottledChatMessage("message.feedback.immortallisProgression.skeletalSpiritsEnd");
				}
			}
		}
	}
}
