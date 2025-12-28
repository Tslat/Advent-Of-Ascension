package net.tslat.aoa3.content.entity.monster.overworld;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.content.entity.ai.movehelper.UnderwaterWalkingMovementController;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import net.tslat.aoa3.util.AttributeUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.WalkOrRunToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SeekRandomNearbyPosition;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;

import static net.tslat.aoa3.library.builder.MultipartBuilder.Part;

public class ChomperEntity extends AoAMeleeMob<ChomperEntity> {
	public ChomperEntity(EntityType<? extends ChomperEntity> entityType, Level world) {
		super(entityType, world);

		moveControl = new UnderwaterWalkingMovementController(this);
	}

	@Nullable
	@Override
	public MultipartBuilder<? extends ChomperEntity> definePartEntities() {
		return MultipartBuilder.of(this,
										  Part.sized(getBbWidth(), 0.65f).up(0.2f).adjacentForward().damageMod(1.25f),
										  Part.sized(getBbWidth(), 0.4375f).up(0.225f).adjacentBehind().damageMod(0.9f).then(
												  Part.sized(0.4375f, 0.3125f).up(0.025f).adjacentBehind().damageMod(0.75f)));
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
	public List<ExtendedSensor<? extends ChomperEntity>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<>(),
				new NearbyLivingEntitySensor<ChomperEntity>().setPredicate((target, entity) -> {
					EntityDimensions entitySize = target.getDimensions(Pose.STANDING);

					if (entitySize.width() <= 0.5f && entitySize.height() <= 0.75f)
						return true;

					return target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null;
				}).setScanRate(entity -> 40),
				new HurtBySensor<>());
	}

	@Override
	public BrainActivityGroup<ChomperEntity> getCoreTasks() {
		return BrainActivityGroup.coreTasks(
				new LookAtTarget<>(),
				new WalkOrRunToWalkTarget<>());
	}

	@Override
	public BrainActivityGroup<ChomperEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new TargetOrRetaliate<>()
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> DamageUtil.isAttackable(target) && !isAlliedTo(target)),
				new OneRandomBehaviour<>(
						new FirstApplicableBehaviour<>(
								new SeekRandomNearbyPosition<>().speedModifier(0.8f).validPositions((entity, state) -> state.getFluidState().is(FluidTags.WATER)).startCondition(entity -> entity.level().isDay() && !entity.isInWater()),
								new SetRandomWalkTarget<>().speedModifier(0.7f)),
						new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60))));
	}

	@Override
	public BrainActivityGroup<ChomperEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Mth.square(getAttributeValue(Attributes.FOLLOW_RANGE))),
				new SetWalkTargetToAttackTarget<>().speedMod((entity, target) -> 1.05f),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration() + 2));
	}

	@Override
	public float getWaterSlowDown() {
		return 1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return EntityUtil.isEntityMoving(this) ? null : AoASounds.ENTITY_CHOMPER_AMBIENT.get();
	}

	@Override
	protected int getAttackSwingDuration() {
		return 12;
	}

	@Override
	protected int getPreAttackTime() {
		return 5;
	}

	public static SpawnPlacements.SpawnPredicate<ChomperEntity> spawnRules(EntityType<ChomperEntity> entityType) {
		return EntitySpawnConditions.createDayNightMonster(entityType).difficultyBasedSpawnChance(0.1f);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<ChomperEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(30)
				.moveSpeed(0.3)
				.meleeStrength(7)
				.knockbackResist(0.3)
				.followRange(14)
				.aggroRange(8);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkRunIdleController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE).transitionLength(0));
	}
}
