package net.tslat.aoa3.content.entity.monster.precasia;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoAFluidTypes;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.animal.precasia.DeinotheriumEntity;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyLivingEntitySensor;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.AttributeUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntitySpawningUtil;
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
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.particle.ParticleBuilder;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;

import static net.tslat.aoa3.library.builder.MultipartBuilder.Part;

public class SpinoledonEntity extends AoAMeleeMob<SpinoledonEntity> {
	public static final EntityDataAccessor<Boolean> LUNGING = makeSynchedData(SpinoledonEntity.class, EntityDataSerializers.BOOLEAN);
	private static final AttributeModifier LUNGE_DAMAGE_BONUS = new AttributeModifier(AdventOfAscension.id("lunge_damage"), 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

	public SpinoledonEntity(EntityType<? extends SpinoledonEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public MultipartBuilder<? extends SpinoledonEntity> definePartEntities() {
		return MultipartBuilder.of(this,
										  Part.sized(getBbWidth(), 0.875f).up(1.0625f).adjacentForward().then(
												  Part.sized(0.5f, 0.8125f).up(0.3125f).adjacentForward().damageMod(1.1f).then(
														  Part.sized(0.5f, 0.5f).up(0.4375f).adjacentForward().damageMod(1.1f).then(
																  Part.sized(0.5f, 0.5f).adjacentForward().damageMod(1.25f)))),
										  Part.sized(getBbWidth(), 0.625f).up(1.1875f).adjacentBehind().damageMod(0.9f).then(
												  Part.sized(0.625f, 0.5625f).down(0.0625f).adjacentBehind().damageMod(0.75f)));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);

		builder.define(LUNGING, false);
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
	public List<ExtendedSensor<? extends SpinoledonEntity>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<>(),
				new AggroBasedNearbyLivingEntitySensor<SpinoledonEntity>()
						.setPredicate((target, entity) -> (target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null) || target instanceof DeinotheriumEntity)
						.setScanRate(entity -> 40),
				new HurtBySensor<>());
	}

	@Override
	public BrainActivityGroup<? extends SpinoledonEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new TargetOrRetaliate<>()
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> DamageUtil.isAttackable(target) && !isAlliedTo(target)),
				new OneRandomBehaviour<>(
						new SetRandomWalkTarget<>().speedModifier(0.9f),
						new Idle<>().runFor(entity -> entity.level().isDay() ? entity.getRandom().nextInt(30, 60) : entity.getRandom().nextInt(60, 120))));
	}

	@Override
	public BrainActivityGroup<? extends SpinoledonEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Mth.square(getAttributeValue(Attributes.FOLLOW_RANGE))),
				new SetWalkTargetToAttackTarget<>().closeEnoughDist((entity, target) -> 2),
				new AnimatableMeleeAttack<>(getPreAttackTime())
						.attackInterval(entity -> getAttackSwingDuration())
						.whenStarting(entity -> {
							setSynchedData(LUNGING, entity.distanceToSqr(BrainUtils.getTargetOfEntity(entity)) < 4);

							if (getSynchedData(LUNGING)) {
								AttributeUtil.applyTransientModifier(entity, Attributes.ATTACK_DAMAGE, LUNGE_DAMAGE_BONUS);
							}
							else {
								AttributeUtil.removeModifier(entity, Attributes.ATTACK_DAMAGE, LUNGE_DAMAGE_BONUS);
							}
						}));
	}

	@Override
	public int getMaxHeadXRot() {
		return 65;
	}

	@Override
	public int getMaxHeadYRot() {
		return 40;
	}

	@Override
	public int getHeadRotSpeed() {
		return 40;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_SPINOLEDON_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_SPINOLEDON_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_SPINOLEDON_HURT.get();
	}

	@Override
	protected float getStepWeight() {
		return 5f;
	}

	@Override
	protected float nextStep() {
		return this.moveDist + 1.4f;
	}

	@Override
	public int getCurrentSwingDuration() {
		return getSynchedData(LUNGING) ? 24 : 11;
	}

	@Override
	protected int getPreAttackTime() {
		return getSynchedData(LUNGING) ? 13 : 4;
	}

	@Override
	protected double getAttackReach() {
		return 3.05f;
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof Animal animal && animal.getHealth() <= 0)
			heal(animal.getMaxHealth() / 10f);
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

	public static SpawnPlacements.SpawnPredicate<SpinoledonEntity> spawnRules(EntityType<SpinoledonEntity> entityType) {
		return EntitySpawnConditions.createDayNightMonster(entityType).difficultyBasedSpawnChance(0.05f)
				.and((entityType2, level, spawnType, pos, random) ->
							 EntitySpawnConditions.levelDependentCondition(AoADimensions.PRECASIA, level, pos.getY() >= 60 || pos.getY() <= -13));
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<SpinoledonEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(51)
				.armour(4, 4)
				.moveSpeed(0.3)
				.meleeStrength(9.5f)
				.knockbackResist(0.7f)
				.aggroRange(16)
				.followRange(32);
	}

	@Override
	protected int getAttackSwingDuration() {
		return getSynchedData(LUNGING) ? 25 : 11;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkRunIdleController(this));
		controllers.add(new AnimationController<GeoAnimatable>(this, "Attack", 0, state -> {
			if (this.swinging) {
				if (getSynchedData(LUNGING)) {
					state.setControllerSpeed(2f);

					return state.setAndContinue(AoAAnimations.ATTACK_POUNCE);
				}

				state.setControllerSpeed(1);

				return state.setAndContinue(DefaultAnimations.ATTACK_BITE);
			}

			state.resetCurrentAnimation();

			return PlayState.STOP;
		}));
	}
}
