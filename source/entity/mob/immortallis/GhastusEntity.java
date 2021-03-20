package net.tslat.aoa3.entity.mob.immortallis;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class GhastusEntity extends AoAMeleeMob {
	public GhastusEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.5625f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_APPARITION_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_APPARITION_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_APPARITION_HURT.get();
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
	public void aiStep() {
		super.aiStep();

		heal(1f);
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

	if (!level.isClientSide && WorldUtil.isWorld(level, AoADimensions.IMMORTALLIS.key)) {
			Entity attacker = cause.getEntity();

			if (attacker instanceof PlayerEntity || attacker instanceof TameableEntity) {
				PlayerEntity pl = null;

				if (attacker instanceof TameableEntity) {
					if (((TameableEntity)attacker).getOwner() instanceof PlayerEntity)
						pl = (PlayerEntity)((TameableEntity)attacker).getOwner();
				}
				else {
					pl = (PlayerEntity)attacker;
				}

				if (pl instanceof ServerPlayerEntity) {
					PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)pl);

					plData.stats().addTribute(Deities.EREBON, 4);

					if (plData.stats().getTribute(Deities.EREBON) == 200)
						plData.sendThrottledChatMessage("message.feedback.immortallisProgression.evilSpiritsEnd");
				}
			}
		}
	}
}
