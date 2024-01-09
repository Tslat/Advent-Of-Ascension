package net.tslat.aoa3.content.entity.mob.precasia;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoAFluidTypes;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.animal.precasia.HorndronEntity;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyLivingEntitySensor;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.List;

public class SmilodonEntity extends AoAMeleeMob<SmilodonEntity> {
	public SmilodonEntity(EntityType<? extends SmilodonEntity> entityType, Level level) {
		super(entityType, level);

		setParts(
				new AoAEntityPart<>(this, 0.4375f, 0.5625f, 0, 0.6875f, getBbWidth() * 2 - 0.125f).setDamageMultiplier(1.25f),
				new AoAEntityPart<>(this, getBbWidth(), 0.8125f, 0, 0.5625f, getBbWidth()),
				new AoAEntityPart<>(this, 0.5625f, 0.8125f, 0, 0.5625f, -getBbWidth() + 0.0625f).setDamageMultiplier(0.9f));
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.0625f;
	}

	@Override
	public List<ExtendedSensor<? extends SmilodonEntity>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<>(),
				new AggroBasedNearbyLivingEntitySensor<SmilodonEntity>()
						.setPredicate((target, entity) -> (target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null) || target instanceof HorndronEntity)
						.setScanRate(entity -> 40),
				new HurtBySensor<>());
	}

	@Override
	public BrainActivityGroup<? extends SmilodonEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new TargetOrRetaliate<>()
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> target.isAlive() && (!(target instanceof Player player) || !player.getAbilities().invulnerable) && !isAlliedTo(target)),
				new OneRandomBehaviour<>(
						new SetRandomWalkTarget<>().speedModifier(0.9f),
						new Idle<>().runFor(entity -> entity.level().isDay() ? entity.getRandom().nextInt(30, 60) : entity.getRandom().nextInt(60, 120))));
	}

	@Override
	public BrainActivityGroup<? extends SmilodonEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> (target instanceof Player pl && pl.getAbilities().invulnerable) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
				new SetWalkTargetToAttackTarget<>().speedMod((entity, target) -> entity.distanceToSqr(target) < 8 ? 1f : 1.5f),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()));
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (this.tickCount % 100 == 0) {
			if (level().isDay()) {
				EntityUtil.applyAttributeModifierSafely(this, AoAAttributes.AGGRO_RANGE.get(), AoAAttributes.NIGHT_AGGRO_MODIFIER, false);
			}
			else {
				EntityUtil.removeAttributeModifier(this, AoAAttributes.AGGRO_RANGE.get(), AoAAttributes.NIGHT_AGGRO_MODIFIER);
			}
		}
	}

	@Override
	protected void onHurt(DamageSource source, float amount) {
		if (level() instanceof ServerLevel level && source.is(DamageTypeTags.IS_FIRE) && level().getFluidState(BlockPos.containing(getEyePosition())).getFluidType() == AoAFluidTypes.TAR.get() && level().getFluidState(blockPosition().above()).getFluidType() == AoAFluidTypes.TAR.get()) {
			ParticleBuilder.forRandomPosInEntity(ParticleTypes.LARGE_SMOKE, this)
					.colourOverride(255, 255, 255, 255)
					.spawnNTimes(20)
					.sendToAllPlayersTrackingEntity(level,this);

			if (isDeadOrDying()) {
				AoAScheduler.scheduleSyncronisedTask(() -> {
					EntitySpawningUtil.spawnEntity(level, AoAMobs.SKELETAL_ABOMINATION.get(), position(), MobSpawnType.CONVERSION, abomination -> {
						abomination.setXRot(getXRot());
						abomination.setYRot(getYRot());
						abomination.setYHeadRot(getYHeadRot());
					});
				}, 19 - this.deathTime);
			}
		}
	}

	@Override
	protected void onAttack(Entity target) {
		if (target.level() instanceof ServerLevel level && target instanceof LivingEntity livingTarget && rand().oneInNChance(10)) {
			EntityUtil.applyPotions(livingTarget, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 20).level(3).hideParticles().hideEffectIcon());
			ParticleBuilder.forRandomPosInEntity(ParticleTypes.CRIT, livingTarget)
					.spawnNTimes(10)
					.sendToAllPlayersTrackingEntity(level, livingTarget);
			level().playSound(null, livingTarget.blockPosition(), SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.HOSTILE);
		}
	}

	@Override
	protected float getAttackVectorPositionOffset() {
		return 1.5625f;
	}

	@Override
	protected double getAttackReach() {
		return isSprinting() ? super.getAttackReach() * 1.1f : super.getAttackReach();
	}

	@Override
	protected int getAttackSwingDuration() {
		return 18;
	}

	@Override
	protected int getPreAttackTime() {
		return 9;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_SMILODON_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_SMILODON_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_SMILODON_DEATH.get();
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkRunIdleController(this));
		controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_STRIKE));
	}
}
