package net.tslat.aoa3.content.entity.mob.precasia;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.content.entity.base.AoAWaterMeleeMob;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyLivingEntitySensor;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;

public class DunkleosteusEntity extends AoAWaterMeleeMob<DunkleosteusEntity> {
	private static final int ATTACK_BITE = 0;
	private static final int ATTACK_SHOOT = 1;

	public DunkleosteusEntity(EntityType<? extends DunkleosteusEntity> entityType, Level level) {
		super(entityType, level);

		setParts(
				new AoAEntityPart<>(this, getBbWidth(), getBbHeight(), 0, 0, getBbWidth()),
				new AoAEntityPart<>(this, getBbWidth(), getBbHeight(), 0, 0, -getBbWidth()),
				new AoAEntityPart<>(this, getBbWidth() - 0.25f, 1.125f, 0, 0.375f, -getBbWidth() * 2)
		);
	}

	@Override
	protected float getAttackVectorPositionOffset() {
		return 2.75f;
	}

	@Override
	public List<ExtendedSensor<? extends DunkleosteusEntity>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<DunkleosteusEntity>().setPredicate((player, entity) -> player.isInWater()),
				new AggroBasedNearbyLivingEntitySensor<DunkleosteusEntity>()
						.setPredicate((target, entity) -> target.isInWater() && ((target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null) || target instanceof Animal))
						.setScanRate(entity -> 40),
				new HurtBySensor<>());
	}

	@Override
	public BrainActivityGroup<? extends DunkleosteusEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> (target instanceof Player pl && pl.getAbilities().invulnerable) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
				new SetWalkTargetToAttackTarget<>()
						.speedMod((entity, target) -> target instanceof Player ? 1.25f : 1),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()));
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.09375f;
	}

	@Override
	public int getMaxHeadXRot() {
		return 1;
	}

	@Override
	public int getMaxHeadYRot() {
		return 1;
	}

	@Override
	public int getMaxAirSupply() {
		return 2400;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return super.getAmbientSound();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return super.getHurtSound(source);
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return super.getDeathSound();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return super.getStepSound(pos, blockState);
	}

	@Override
	public int getCurrentSwingDuration() {
		return ATTACK_STATE.is(this, ATTACK_BITE) ? 11 : 31;
	}

	@Override
	protected int getPreAttackTime() {
		return ATTACK_STATE.is(this, ATTACK_BITE) ? 4 : 11;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "Swim/SwimSprint", 0, state -> {
			if (state.isMoving())
				return state.setAndContinue(isSprinting() ? AoAAnimations.SWIM_SPRINT : DefaultAnimations.SWIM);

			return PlayState.STOP;
		}));
		controllers.add(AoAAnimations.dynamicAttackController(this, state -> ATTACK_STATE.is(this, ATTACK_BITE) ? DefaultAnimations.ATTACK_BITE : DefaultAnimations.ATTACK_SHOOT));
	}
}
