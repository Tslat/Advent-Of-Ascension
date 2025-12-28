package net.tslat.aoa3.content.entity.monster.precasia;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoAFluidTypes;
import net.tslat.aoa3.common.registration.entity.AoAEntityDataSerializers;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.common.registration.entity.variant.VeloraptorVariant;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.animal.precasia.OpteryxEntity;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyLivingEntitySensor;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.content.entity.brain.task.temp.FixedTargetOrRetaliate;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.AttributeUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.MathUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.particle.ParticleBuilder;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;

import static net.tslat.aoa3.library.builder.MultipartBuilder.Part;

public class VeloraptorEntity extends AoAMeleeMob<VeloraptorEntity> {
	private static final EntityDataAccessor<VeloraptorVariant> VARIANT = makeSynchedData(VeloraptorEntity.class, AoAEntityDataSerializers.VELORAPTOR_VARIANT.get());
	private static final AttributeModifier LUNGE_DAMAGE_MODIFIER = new AttributeModifier(AdventOfAscension.id("lunge_damage"), 3, AttributeModifier.Operation.ADD_VALUE);

	private static final int ATTACK_BITE = 0;
	private static final int ATTACK_POUNCE = 1;

	public VeloraptorEntity(EntityType<? extends VeloraptorEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Nullable
	@Override
	public MultipartBuilder<? extends VeloraptorEntity> definePartEntities() {
		return MultipartBuilder.of(this,
										  Part.sized(getBbWidth(), 0.9375f).up(1).adjacentForward().damageMod(1.1f));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);

		builder.define(VARIANT, VeloraptorVariant.GREEN.get());
	}

	@Override
	protected double getAttackReach() {
		return 1.11f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_RAPTOR_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_SPINOLEDON_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_RAPTOR_DEATH.get();
	}

	@Override
	public List<ExtendedSensor<? extends VeloraptorEntity>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<>(),
				new AggroBasedNearbyLivingEntitySensor<VeloraptorEntity>()
						.setPredicate((target, entity) -> (target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null) || target instanceof OpteryxEntity || target instanceof Sniffer)
						.setScanRate(entity -> 40),
				new HurtBySensor<>());
	}

	@Override
	public BrainActivityGroup<VeloraptorEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Mth.square(getAttributeValue(Attributes.FOLLOW_RANGE))),
				new SetWalkTargetToAttackTarget<>().speedMod((entity, target) -> entity.distanceToSqr(target) < 8 ? 1f : 1.2f),
				new OneRandomBehaviour<>(
						Pair.of(new AnimatableMeleeAttack<>(7).attackInterval(entity -> 8)
								.whenStarting(entity -> setAttackState(ATTACK_BITE))
								.whenStopping(entity -> BrainUtils.setSpecialCooldown(this, 8)), 5),
						Pair.of(new LungeMeleeAttack(15)
								.startCondition(Entity::onGround), 1)
				).startCondition(entity -> !BrainUtils.isOnSpecialCooldown(this))
		);
	}

	@Override
	public BrainActivityGroup<? extends VeloraptorEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new FixedTargetOrRetaliate<>()
						.alertAlliesWhen((entity, target) -> target instanceof Player || target instanceof Animal)
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> DamageUtil.isAttackable(target) && !isAlliedTo(target)),
				new OneRandomBehaviour<>(
						new SetRandomWalkTarget<>().speedModifier(0.9f),
						new Idle<>().runFor(entity -> entity.level().isDay() ? entity.getRandom().nextInt(30, 60) : entity.getRandom().nextInt(60, 120))));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		compound.putString("Variant", AoARegistries.VELORAPTOR_VARIANTS.getKey(getSynchedData(VARIANT)).toString());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("Variant", Tag.TAG_STRING)) {
			setSynchedData(VARIANT, VeloraptorVariant.getOrDefault(ResourceLocation.tryParse(compound.getString("Variant"))));
		}
		else if (getVariant() == null) {
			setSynchedData(VARIANT, VeloraptorVariant.GREEN.get());
		}
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (this.tickCount % 100 == 0) {
			if (!level().isDay()) {
				AttributeUtil.applyTransientModifier(this, AoAAttributes.AGGRO_RANGE, AoAAttributes.NIGHT_AGGRO_MODIFIER);
			}
			else {
				AttributeUtil.removeModifier(this, AoAAttributes.AGGRO_RANGE, AoAAttributes.NIGHT_AGGRO_MODIFIER);
			}
		}
	}

	@Override
	public void onDamageTaken(DamageContainer damageContainer) {
		if (level() instanceof ServerLevel level && damageContainer.getSource().is(DamageTypeTags.IS_FIRE) && level().getFluidState(BlockPos.containing(getEyePosition())).getFluidType() == AoAFluidTypes.TAR.get() && level().getFluidState(blockPosition().above()).getFluidType() == AoAFluidTypes.TAR.get()) {
			ParticleBuilder.forRandomPosInEntity(ParticleTypes.LARGE_SMOKE, this)
					.colourTint(255, 255, 255, 255)
					.spawnNTimes(20)
					.sendToAllPlayersTrackingEntity(this);

			if (isDeadOrDying()) {
				AoAScheduler.schedule(19 - this.deathTime, tick -> {
					EntitySpawningUtil.spawnEntity(level, AoAMonsters.SKELETAL_ABOMINATION.get(), position(), MobSpawnType.CONVERSION, abomination -> {
						abomination.setXRot(getXRot());
						abomination.setYRot(getYRot());
						abomination.setYHeadRot(getYHeadRot());
					});
				});
			}
		}
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData) {
		spawnData = super.finalizeSpawn(world, difficulty, reason, spawnData);

		setSynchedData(VARIANT, VeloraptorVariant.getVariantForSpawn(world.getLevel(), difficulty, reason, this, Suppliers.memoize(() -> level().getBiome(blockPosition())), spawnData));

		return spawnData;
	}

	public VeloraptorVariant getVariant() {
		return getSynchedData(VARIANT);
	}

	@Override
	protected int getAttackSwingDuration() {
		return isAttackState(ATTACK_BITE) ? 8 : 40;
	}

	@Override
	protected int getPreAttackTime() {
		return isAttackState(ATTACK_BITE) ? 4 : 21;
	}

	@Override
	protected ResourceKey<LootTable> getDefaultLootTable() {
		return getVariant().lootTable().orElseGet(super::getDefaultLootTable);
	}

	public static SpawnPlacements.SpawnPredicate<VeloraptorEntity> spawnRules(EntityType<VeloraptorEntity> entityType) {
		return EntitySpawnConditions.createDayNightMonster(entityType).noLowerThanY(AoADimensions.PRECASIA, 60).difficultyBasedSpawnChance(0.1f);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<VeloraptorEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(37)
				.moveSpeed(0.35)
				.meleeStrength(7.5f)
				.knockbackResist(0.2f)
				.aggroRange(16)
				.followRange(32);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkRunIdleController(this));
		controllers.add(new AnimationController<>(this, "attacking", 0, state -> {
			if (this.swinging) {
				if (isAttackState(ATTACK_BITE)) {
					state.setControllerSpeed(1);

					return state.setAndContinue(DefaultAnimations.ATTACK_BITE);
				}
				else {
					state.setControllerSpeed(1.5f);

					return state.setAndContinue(AoAAnimations.ATTACK_POUNCE);
				}
			}

			state.getController().forceAnimationReset();

			return PlayState.STOP;
		}));
	}

	public static class LungeMeleeAttack extends AnimatableMeleeAttack<VeloraptorEntity> {
		public LungeMeleeAttack(int delayTicks) {
			super(delayTicks);

			attackInterval(entity -> 30);
		}

		@Override
		protected boolean checkExtraStartConditions(ServerLevel level, VeloraptorEntity entity) {
			this.target = BrainUtils.getTargetOfEntity(entity);
			double targetDist = BrainUtils.getTargetOfEntity(entity).distanceToSqr(entity);

			return entity.isSprinting() && targetDist > 8 && targetDist <= 30 && entity.getSensing().hasLineOfSight(this.target);
		}

		@Override
		protected void start(VeloraptorEntity entity) {
			super.start(entity);

			entity.setAttackState(ATTACK_POUNCE);
			entity.getNavigation().stop();
			Vec3 oldPos = this.target.position();

			AoAScheduler.schedule(9, tick -> {
				final Entity target = BrainUtils.getTargetOfEntity(entity);
				final Vec3 lungePos = target != null ? target.position().add(oldPos.vectorTo(target.position())) : entity.position().add(entity.getLookAngle().scale(4));

				entity.setDeltaMovement(
						MathUtil.clampVec(entity.getDeltaMovement()
										.add(entity.position()
												.vectorTo(lungePos).scale(7 / 9f)
												.add(0, 0.1f, 0)),
								new Vec3(-1.2, -0.1f, -1.2f),
								new Vec3(1.2, 0.5f, 1.2f)));
			});
		}

		@Override
		protected void stop(VeloraptorEntity entity) {
			super.stop(entity);

			BrainUtils.setSpecialCooldown(entity, 40);
		}

		@Override
		protected void doDelayedAction(VeloraptorEntity entity) {
			AttributeUtil.applyTransientModifier(entity, Attributes.ATTACK_DAMAGE, LUNGE_DAMAGE_MODIFIER);
			super.doDelayedAction(entity);
			AttributeUtil.removeModifier(entity, Attributes.ATTACK_DAMAGE, LUNGE_DAMAGE_MODIFIER);
		}
	}
}
