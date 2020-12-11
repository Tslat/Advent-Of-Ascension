package net.tslat.aoa3.entity.mob.haven;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class AngelicaEntity extends AoAFlyingMeleeMob {
	public AngelicaEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.75f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 84;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 1;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ANGELICA_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ANGELICA_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ANGELICA_HURT.get();
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return world.getDimension().getType() == AoADimensions.ANCIENT_CAVERN.type() ? null : super.getLootTable();
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.UNDEAD;
	}

	@Override
	public boolean canBeHitWithPotion() {
		return false;
	}

	@Override
	public boolean addPotionEffect(EffectInstance effect) {
		return false;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && world.getDimension().getType() == AoADimensions.ANCIENT_CAVERN.type()) {
			Entity source = cause.getTrueSource();
			ServerPlayerEntity killer = null;

			if (source != null) {
				if (source instanceof PlayerEntity) {
					killer = (ServerPlayerEntity)source;
				}
				else if (source instanceof TameableEntity && ((TameableEntity)source).getOwner() instanceof PlayerEntity) {
					killer = (ServerPlayerEntity)((TameableEntity)source).getOwner();
				}
			}

			if (killer != null)
				PlayerUtil.addTributeToPlayer(killer, Deities.LUXON, 8);
		}
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity) {
			EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.WEAKNESS, 60).level(2).hideParticles());
			EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.LEVITATION, 60).hideParticles());
			EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.LEVITATION, 60).hideParticles());
		}
	}
}
