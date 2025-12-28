package net.tslat.aoa3.content.entity.animal.precasia;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.block.AoAFluidTypes;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.content.entity.brain.task.temp.FixedFollowParent;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.BreedWithPartner;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FollowTemptation;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.particle.ParticleBuilder;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;

import static net.tslat.aoa3.library.builder.MultipartBuilder.Part;

public class HorndronEntity extends AoAAnimal<HorndronEntity> {
	private static final RawAnimation STRUT_ANIM = RawAnimation.begin().thenPlay("misc.charge_up");

	public HorndronEntity(EntityType<? extends HorndronEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public MultipartBuilder<? extends HorndronEntity> definePartEntities() {
		return MultipartBuilder.of(this,
										  Part.sized(1, 1).adjacentForward().up(0.755f).damageMod(1.25f),
										  Part.sized(getBbWidth(), 1.1875f).back(0).up(0.6875f).damageMod(0.9f));
	}

	@Override
	public int getMaxHeadYRot() {
		return 32;
	}

	@Override
	public int getMaxHeadXRot() {
		return 15;
	}

	@Override
	public int getHeadRotSpeed() {
		return 5;
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return this.age >= 0 ? AoASounds.ENTITY_GENERIC_HEAVY_STEP.get() : super.getStepSound(pos, blockState);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.POLAR_BEAR_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_HORNDRON_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_HORNDRON_HURT.get();
	}

	@Override
	public BrainActivityGroup<? extends HorndronEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new FirstApplicableBehaviour<>(
						new BreedWithPartner<>().startCondition(entity -> canBreed()),
						new FixedFollowParent<>(),
						new FollowTemptation<>().startCondition(entity -> getTemptationTag() != null),
						new OneRandomBehaviour<>(
								new SetRandomWalkTarget<>().speedModifier(0.9f),
								new Idle<>()
										.runFor(entity -> entity.getRandom().nextInt(30, 60))
										.whenStarting(entity -> {
											if (this.age == 0 && this.random.nextFloat() < 0.01f)
												triggerAnim("Walk/Run/Idle", "Charge");
										}))
				));
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

	@Override
	public void push(Entity entity) {
		super.push(entity);

		if (BrainUtils.memoryOrDefault(this, MemoryModuleType.IS_PANICKING, () -> false) && entity instanceof LivingEntity && entity.getBoundingBox().getSize() < getBoundingBox().getSize())
			entity.hurt(level().damageSources().cramming(), 6);
	}

	@Override
	public boolean isMultipartEntity() {
		return this.age >= 0 && super.isMultipartEntity();
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
		return new HorndronEntity(AoAAnimals.HORNDRON.get(), level);
	}

	@Nullable
	@Override
	protected TagKey<Item> getFoodTag() {
		return AoATags.Items.HORNDRON_FOOD;
	}

	public static SpawnPlacements.SpawnPredicate<HorndronEntity> spawnRules(EntityType<HorndronEntity> entityType) {
		return EntitySpawnConditions.createAnimal(entityType);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<HorndronEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.create(entityType)
				.health(58)
				.moveSpeed(0.25f)
				.followRange(16)
				.knockbackResist(0.75f);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkRunIdleController(this)
				.triggerableAnim("Charge", STRUT_ANIM));
	}
}
