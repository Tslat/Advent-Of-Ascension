package net.tslat.aoa3.content.entity.mob.overworld;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.ai.movehelper.UnderwaterWalkingMovementController;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
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
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.List;

public class ChomperEntity extends AoAMeleeMob<ChomperEntity> {
	public ChomperEntity(EntityType<? extends ChomperEntity> entityType, Level world) {
		super(entityType, world);

		moveControl = new UnderwaterWalkingMovementController(this);

		setParts(
				new AoAEntityPart<>(this, getBbWidth(), 0.65f, 0, 0.2f, getBbWidth()).setDamageMultiplier(1.25f),
				new AoAEntityPart<>(this, getBbWidth(), 7 / 16f, 0, 0.225f, -getBbWidth()).setDamageMultiplier(0.9f),
				new AoAEntityPart<>(this, 7 / 16f, 5 / 16f, 0, 0.25f, -getBbWidth() * 1.775f).setDamageMultiplier(0.75f)
		);
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
	public List<ExtendedSensor<? extends ChomperEntity>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<>(),
				new NearbyLivingEntitySensor<ChomperEntity>().setPredicate((target, entity) -> {
					EntityDimensions entitySize = target.getDimensions(Pose.STANDING);

					if (entitySize.width <= 0.5f && entitySize.height <= 0.75f)
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
						.attackablePredicate(target -> target.isAlive() && (!(target instanceof Player player) || !player.getAbilities().invulnerable) && !isAlliedTo(target)),
				new OneRandomBehaviour<>(
						new FirstApplicableBehaviour<>(
								new SeekRandomNearbyPosition<>().speedModifier(0.8f).validPositions((entity, state) -> state.getFluidState().is(FluidTags.WATER)).startCondition(entity -> entity.level().isDay() && !entity.isInWater()),
								new SetRandomWalkTarget<>().speedModifier(0.7f)),
						new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60))));
	}

	@Override
	public BrainActivityGroup<ChomperEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> (target instanceof Player pl && pl.getAbilities().invulnerable) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
				new SetWalkTargetToAttackTarget<>().speedMod((entity, target) -> 1.05f),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()));
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 0.75f;
	}

	@Override
	protected float getWaterSlowDown() {
		return 1;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return type != ForgeMod.WATER_TYPE.get();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return EntityUtil.isEntityMoving(this) ? null : AoASounds.ENTITY_CHOMPER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
	}

	@Override
	protected int getAttackSwingDuration() {
		return 12;
	}

	@Override
	protected int getPreAttackTime() {
		return 5;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkRunIdleController(this),
				AoAAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE));
	}
}
