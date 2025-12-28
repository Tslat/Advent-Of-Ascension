package net.tslat.aoa3.content.entity.animal.precasia;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.content.entity.brain.task.temp.FixedFollowParent;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.SequentialBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.BreedWithPartner;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.CustomHeldBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Panic;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FollowTemptation;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToBlock;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.NearbyBlocksSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.ItemTemptingSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyPlayersSensor;
import net.tslat.smartbrainlib.util.BrainUtils;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;
import java.util.Map;

import static net.tslat.aoa3.library.builder.MultipartBuilder.Part;

public class DeinotheriumEntity extends AoAAnimal<DeinotheriumEntity> {
	private static final RawAnimation EAT_ANIM = RawAnimation.begin().thenPlay("misc.eat");

	public DeinotheriumEntity(EntityType<? extends DeinotheriumEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public MultipartBuilder<? extends DeinotheriumEntity> definePartEntities() {
		return MultipartBuilder.of(this,
										  Part.sized(getBbWidth(), 1.625f).up(1.3125f).adjacentForward().then(
												  Part.sized(1, 1).up(0.616f).adjacentForward().damageMod(1.25f)),
										  Part.sized(getBbWidth(), 1.75f).up(1.3125f).back(0).damageMod(0.5f));
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

	@Override
	public int getAmbientSoundInterval() {
		return 480;
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
				new ItemTemptingSensor<DeinotheriumEntity>().temptedWith((entity, stack) -> isFood(stack)),
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
				new FirstApplicableBehaviour<>(
						new BreedWithPartner<>().startCondition(entity -> canBreed()),
						new FixedFollowParent<>(),
						new FollowTemptation<>().startCondition(entity -> getTemptationTag() != null),
						new OneRandomBehaviour<>(
								new SequentialBehaviour<>(
										new SetWalkTargetToBlock<>()
												.predicate((entity, block) -> block.getFirst().distSqr(entity.blockPosition()) < 25 && block.getSecond().is(BlockTags.LEAVES))
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

																AoAScheduler.schedule(40, tick -> {
																	if (pos.distSqr(entity.blockPosition()) <= 20 && level().getBlockState(pos).is(BlockTags.LEAVES))
																		level().destroyBlock(pos, false, entity);
																});
															}
														}
													}
												}),
										new Idle<>().runFor(entity -> 60)),
								new SetRandomWalkTarget<>().speedModifier(0.9f),
								new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60)))
				).noTimeout().startCondition(entity -> !BrainUtils.memoryOrDefault(entity, MemoryModuleType.IS_PANICKING, () -> false)));
	}

	@Override
	public Map<Activity, BrainActivityGroup<? extends DeinotheriumEntity>> getAdditionalTasks() {
		return Map.of(Activity.PANIC, new BrainActivityGroup<DeinotheriumEntity>(Activity.PANIC)
				.behaviours(new Panic<>()
						.setRadius(15, 10)
						.speedMod(entity -> 2f))
				.requireAndWipeMemoriesOnUse(MemoryModuleType.HURT_BY_ENTITY));
	}

	@Override
	public void push(Entity entity) {
		super.push(entity);

		if (BrainUtils.memoryOrDefault(this, MemoryModuleType.IS_PANICKING, () -> false) && entity instanceof LivingEntity && entity.getBoundingBox().getSize() < getBoundingBox().getSize())
			entity.hurt(level().damageSources().cramming(), 10);
	}

	@Nullable
	@Override
	protected TagKey<Item> getFoodTag() {
		return AoATags.Items.DEINOTHERIUM_FOOD;
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

	public static SpawnPlacements.SpawnPredicate<DeinotheriumEntity> spawnRules(EntityType<DeinotheriumEntity> entityType) {
		return EntitySpawnConditions.create(entityType).and((entityType2, level, spawnType, pos, rand) -> {
			if (!MobSpawnType.ignoresLightRequirements(spawnType) && level.getRawBrightness(pos, 0) <= 8)
				return false;

			BlockState block = level.getBlockState(pos.below());

			return block.is(BlockTags.ANIMALS_SPAWNABLE_ON) || block.is(Tags.Blocks.SANDS);
		});
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<DeinotheriumEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.create(entityType)
				.health(95)
				.moveSpeed(0.2f)
				.followRange(16)
				.meleeStrength(8)
				.knockback(1)
				.knockbackResist(0.9f);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkRunIdleController(this)
				.triggerableAnim("Eat", EAT_ANIM));
	}
}
