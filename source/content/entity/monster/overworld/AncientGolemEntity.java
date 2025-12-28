package net.tslat.aoa3.content.entity.monster.overworld;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidType;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.WalkOrRunToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;


public class AncientGolemEntity extends AoAMeleeMob<AncientGolemEntity> {
	public AncientGolemEntity(EntityType<? extends AncientGolemEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public BrainActivityGroup<AncientGolemEntity> getCoreTasks() {
		return BrainActivityGroup.coreTasks(
				new LookAtTarget<>(),
				new WalkOrRunToWalkTarget<>());
	}

	@Override
	public BrainActivityGroup<AncientGolemEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new TargetOrRetaliate<>()
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> DamageUtil.isAttackable(target) && !isAlliedTo(target)),
				new OneRandomBehaviour<>(
						Pair.of(new SetRandomWalkTarget<>().speedModifier(0.9f), 1),
						Pair.of(new Idle<>().runFor(entity -> entity.getRandom().nextInt(60, 120)), 2)));
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.IRON_GOLEM_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.IRON_GOLEM_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GOLEM_STEP.get();
	}

	@Override
	protected float nextStep() {
		return this.moveDist + 0.25f * getSpeed() / 0.15f;
	}

	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return false;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	@Override
	protected int getPreAttackTime() {
		return 1;
	}

	public static SpawnPlacements.SpawnPredicate<AncientGolemEntity> spawnRules(EntityType<AncientGolemEntity> entityType) {
		return EntitySpawnConditions.createDayMonster(entityType).noLowerThanY(AoADimensions.OVERWORLD, 65);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<AncientGolemEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(50)
				.moveSpeed(0.15)
				.meleeStrength(15)
				.knockbackResist(1)
				.knockback(5)
				.armour(20, 40)
				.followRange(40)
				.aggroRange(16);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SWING).transitionLength(0));
	}
}
