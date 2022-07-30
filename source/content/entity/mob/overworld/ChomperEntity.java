package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.ai.mob.ExtendedFindNearbyTargetGoal;
import net.tslat.aoa3.content.entity.ai.mob.ExtendedMeleeAttackGoal;
import net.tslat.aoa3.content.entity.ai.mob.MoveToWaterGoal;
import net.tslat.aoa3.content.entity.ai.movehelper.UnderwaterWalkingMovementController;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.aoa3.util.EntityUtil;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class ChomperEntity extends AoAMeleeMob {
	public ChomperEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		moveControl = new UnderwaterWalkingMovementController(this);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new ExtendedMeleeAttackGoal<>(this).attackInterval(ConstantInt.of(getCurrentSwingDuration())).actionTelegraphTicks(getPreAttackTime()).onStart(goal -> setSharedFlag(3, true)).onStop(goal -> setSharedFlag(3, false)));
		goalSelector.addGoal(2, new MoveToWaterGoal<>(this, 0.8d));
		goalSelector.addGoal(7, new RandomStrollGoal(this, 0.7d));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new ExtendedFindNearbyTargetGoal<>(this, true, EntityPredicate.SURVIVAL_PLAYER));
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
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
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericWalkRunIdleController(this));
		animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_BITE));
	}
}
