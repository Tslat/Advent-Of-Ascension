package net.tslat.aoa3.content.entity.mob.precasia;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.content.entity.brain.task.temp.*;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.SequentialBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.CustomHeldBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.NearbyBlocksSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.ItemTemptingSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import net.tslat.smartbrainlib.util.BrainUtils;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.RawAnimation;

import java.util.List;

public class DeinotheriumEntity extends AoAAnimal<DeinotheriumEntity> {
	private static final RawAnimation EAT_ANIM = RawAnimation.begin().thenPlay("misc.eat");

	public DeinotheriumEntity(EntityType<? extends DeinotheriumEntity> entityType, Level world) {
		super(entityType, world);

		setParts(
				new AoAEntityPart<>(this, getBbWidth(), 1.625f, 0, 1.3125f, getBbWidth()),
				new AoAEntityPart<>(this, 1, 1, 0, 1.9375f, getBbWidth() + 1.5f).setDamageMultiplier(1.25f),
				new AoAEntityPart<>(this, getBbWidth(), 1.75f, 0, 1.3125f, -getBbWidth() * 0.5f).setDamageMultiplier(0.9f)
		);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return dimensions.height * 0.8f;
	}

	@Override
	public int getMaxHeadYRot() {
		return 35;
	}

	@Override
	public int getMaxHeadXRot() {
		return 20;
	}

	@Override
	public int getHeadRotSpeed() {
		return 7;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_DEINOTHERIUM_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_DEINOTHERIUM_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_DEINOTHERIUM_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return this.age >= 0 ? AoASounds.ENTITY_GENERIC_HEAVY_STEP.get() : super.getStepSound(pos, blockState);
	}

	@Override
	public List<ExtendedSensor<? extends DeinotheriumEntity>> getSensors() {
		return ObjectArrayList.of(
				new ItemTemptingSensor<DeinotheriumEntity>().setTemptingItems(getTemptItem() == null ? Ingredient.EMPTY : Ingredient.of(getTemptItem())),
				new NearbyPlayersSensor<>(),
				new NearbyLivingEntitySensor<DeinotheriumEntity>().setScanRate(entity -> 40),
				new HurtBySensor<>(),
				new NearbyBlocksSensor<DeinotheriumEntity>()
						.setRadius(5, 3)
						.setPredicate((state, entity) -> state.is(BlockTags.LEAVES))
						.setScanRate(entity -> 80));
	}

	@Override
	public BrainActivityGroup<? extends DeinotheriumEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new BreedWithPartner<>().startCondition(entity -> canBreed()),
				new Panic<>(),
				new FirstApplicableBehaviour<>(
						new FollowParent<>(),
						new FollowTemptation<>().startCondition(entity -> getTemptItem() != null),
						new OneRandomBehaviour<>(
								new SequentialBehaviour<>(
										new SetWalkTargetToBlock<>()
												.predicate((entity, block) -> block.getSecond().is(BlockTags.LEAVES))
												.closeEnoughWhen((entity, pos) -> 5)
												.cooldownFor(entity -> entity.getRandom().nextInt(100, 200)),
										new CustomHeldBehaviour<DeinotheriumEntity>(entity -> {})
												.stopIf(entity -> entity.getNavigation().isDone())
												.whenStopping(entity -> {
													if (BrainUtils.getMemory(entity, MemoryModuleType.LOOK_TARGET) instanceof BlockPosTracker posTracker) {
														final BlockPos pos = posTracker.currentBlockPosition();

														if (pos.distSqr(entity.blockPosition()) <= 20 && level().getBlockState(pos).is(BlockTags.LEAVES)) {
															if (level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
																triggerAnim("Walk/Run/Idle", "Eat");
																setDeltaMovement(getDeltaMovement().multiply(0, 1, 0));
																entity.getNavigation().stop();
																BrainUtils.clearMemory(entity, MemoryModuleType.PATH);

																AoAScheduler.scheduleSyncronisedTask(() -> {
																	if (pos.distSqr(entity.blockPosition()) <= 20 && level().getBlockState(pos).is(BlockTags.LEAVES))
																		level().destroyBlock(pos, false, entity);
																}, 40);
															}
														}
													}
												}),
										new Idle<>().runFor(entity -> 60)
								),
								new SetRandomWalkTarget<>().speedModifier(0.9f),
								new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60)))
				).startCondition(entity -> !BrainUtils.hasMemory(entity, MemoryModuleType.IS_PANICKING)));
	}

	@Nullable
	@Override
	protected Item getTemptItem() {
		return AoABlocks.LUCALUS_LEAVES.get().asItem();
	}

	@Override
	public boolean isMultipartEntity() {
		return this.age >= 0 && super.isMultipartEntity();
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
		return new DeinotheriumEntity(AoAAnimals.DEINOTHERIUM.get(), level);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkRunIdleController(this)
				.triggerableAnim("Eat", EAT_ANIM));
	}
}
