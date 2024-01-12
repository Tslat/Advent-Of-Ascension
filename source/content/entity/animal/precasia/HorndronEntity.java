package net.tslat.aoa3.content.entity.animal.precasia;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.block.AoAFluidTypes;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.content.entity.brain.task.temp.FixedFollowParent;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.BreedWithPartner;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FollowTemptation;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.RawAnimation;

public class HorndronEntity extends AoAAnimal<HorndronEntity> {
	private static final RawAnimation STRUT_ANIM = RawAnimation.begin().thenPlay("misc.charge_up");

	public HorndronEntity(EntityType<? extends HorndronEntity> entityType, Level world) {
		super(entityType, world);

		setParts(new AoAEntityPart<>(this, 1, 1f, 0, 0.755f, getBbWidth() / 2f + 0.5f).setDamageMultiplier(1.25f),
				new AoAEntityPart<>(this, 0.75f, 1.1875f, 0.3725f, 0.75f, -getBbWidth() / 2f - 0.3725f).setDamageMultiplier(0.9f),
				new AoAEntityPart<>(this, 0.75f, 1.1875f, -0.3725f, 0.75f, -getBbWidth() / 2f - 0.3725f).setDamageMultiplier(0.9f)
		);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return dimensions.height * 0.671875f;
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
						new FollowTemptation<>().startCondition(entity -> getTemptItem() != null),
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
	protected void onHurt(DamageSource source, float amount) {
		if (level() instanceof ServerLevel level && source.is(DamageTypeTags.IS_FIRE) && level().getFluidState(BlockPos.containing(getEyePosition())).getFluidType() == AoAFluidTypes.TAR.get() && level().getFluidState(blockPosition().above()).getFluidType() == AoAFluidTypes.TAR.get()) {
			ParticleBuilder.forRandomPosInEntity(ParticleTypes.LARGE_SMOKE, this)
					.colourOverride(255, 255, 255, 255)
					.spawnNTimes(20)
					.sendToAllPlayersTrackingEntity(level,this);

			if (isDeadOrDying()) {
				AoAScheduler.scheduleSyncronisedTask(() -> {
					EntitySpawningUtil.spawnEntity(level, AoAMobs.SKELETAL_ABOMINATION.get(), position(), MobSpawnType.CONVERSION, abomination -> {
						abomination.setXRot(getXRot());
						abomination.setYRot(getYRot());
						abomination.setYHeadRot(getYHeadRot());
					});
				}, 19 - this.deathTime);
			}
		}
	}

	@Override
	public boolean isMultipartEntity() {
		return this.age >= 0 && super.isMultipartEntity();
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkRunIdleController(this)
				.triggerableAnim("Charge", STRUT_ANIM));
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
		return new HorndronEntity(AoAAnimals.HORNDRON.get(), level);
	}

	@Nullable
	@Override
	protected Item getTemptItem() {
		return AoABlocks.CALAB_GRASS.get().asItem();
	}
}
