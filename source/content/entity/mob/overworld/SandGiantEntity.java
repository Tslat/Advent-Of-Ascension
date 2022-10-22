package net.tslat.aoa3.content.entity.mob.overworld;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.util.RandomPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.ai.ExtendedGoal;
import net.tslat.aoa3.content.entity.ai.mob.ExtendedMeleeAttackGoal;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.misc.SandGiantPitTrapEntity;
import net.tslat.aoa3.content.entity.misc.SandGiantSpikeTrapEntity;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.RandomUtil;
import software.bernie.geckolib3.core.manager.AnimationData;

import java.util.EnumSet;
import java.util.function.Function;

public class SandGiantEntity extends AoAMeleeMob<SandGiantEntity> {
	public SandGiantEntity(EntityType<? extends SandGiantEntity> entityType, Level world) {
		super(entityType, world);

		this.maxUpStep = 1.5f;
	}

	@Override
	protected Brain.Provider<SandGiantEntity> brainProvider() { // TODO
		return Brain.provider(ImmutableList.of(), ImmutableList.of());
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new TrapChaseGoal<>(this, pos -> RandomUtil.fiftyFifty() ? new SandGiantPitTrapEntity(level, pos) : new SandGiantSpikeTrapEntity(level, pos))
				.attackEvenIfMidair()
				.spawnExtraTrapsOnStart(20)
				.spawnFrequency(10)
				.maxRuntime(UniformInt.of(160, 240))
				.cooldown(UniformInt.of(300, 500))
				.chargeUpTime(15)
				.onStart(goal -> setAttackState(1))
				.onStop(goal -> setAttackState(0)));
		goalSelector.addGoal(3, new ExtendedMeleeAttackGoal<>(this).attackInterval(ConstantInt.of(getCurrentSwingDuration())).actionTelegraphTicks(getPreAttackTime()).maxRuntime(UniformInt.of(500, 700)));
		goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 2.9375f;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.DENSE_SAND_POUR.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.SANDY_HIT.get();
	}

	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.SANDY_THUD.get();
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	@Override
	protected int getPreAttackTime() {
		return 6;
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericWalkController(this));
		animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_SLAM));
		animationData.addAnimationController(AoAAnimations.genericHeldPoseController(this, AoAAnimations.ATTACK_CHARGE, AoAAnimations.ATTACK_CHARGE_END, entity -> entity.getAttackState() == 1));
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (tickCount % 30 == 0 && getAttackState() == 0 && EntityUtil.getCurrentHealthPercent(this) < 0.5f)
			spawnTrap();
	}

	private void spawnTrap() {
		if (getTarget() == null)
			return;

		BlockPos.MutableBlockPos pos = getTarget().blockPosition().mutable();
		BlockState block = level.getBlockState(pos);

		if (block.isAir()) {
			int x = 10;

			while (x-- >= 0 && pos.getY() > level.getMinBuildHeight() && (block = level.getBlockState(pos.move(Direction.DOWN))).isAir()) {}

			if (block.isAir())
				return;

			level.addFreshEntity(RandomUtil.fiftyFifty() ? new SandGiantPitTrapEntity(level, Vec3.atBottomCenterOf(pos.move(Direction.UP))) : new SandGiantSpikeTrapEntity(level, Vec3.atBottomCenterOf(pos.move(Direction.UP))));
		}
	}

	private static class TrapChaseGoal<T extends Mob> extends ExtendedGoal<T> {
		private final Function<Vec3, Entity> trapFactory;

		protected int trapSpawnFrequency = 20;
		protected int initialTrapSpawns = 0;
		protected boolean onlyIfOnGround = true;

		private int nextSpawnTime = 0;

		TrapChaseGoal(T entity, Function<Vec3, Entity> trapFactory) {
			super(entity);

			this.trapFactory = trapFactory;

			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		public TrapChaseGoal<T> spawnFrequency(int ticks) {
			this.trapSpawnFrequency = ticks;

			return this;
		}

		public TrapChaseGoal<T> spawnExtraTrapsOnStart(int amount) {
			this.initialTrapSpawns = amount;

			return this;
		}

		public TrapChaseGoal<T> attackEvenIfMidair() {
			this.onlyIfOnGround = false;

			return this;
		}

		@Override
		public boolean canUse() {
			if (!super.canUse())
				return false;

			if (entity.level.getDifficulty() == Difficulty.PEACEFUL)
				return false;

			LivingEntity target = entity.getTarget();

			return EntityPredicate.TARGETABLE_ENTITIES.test(target);
		}

		@Override
		public boolean canContinueToUse() {
			if (!super.canContinueToUse())
				return false;

			return EntityPredicate.TARGETABLE_ENTITIES.test(this.entity.getTarget());
		}

		@Override
		public void start() {
			super.start();

			this.entity.getNavigation().stop();
			this.entity.setAggressive(true);
		}

		@Override
		public void stop() {
			super.stop();

			this.entity.setAggressive(false);
		}

		@Override
		public void tick() {
			super.tick();

			if (hasChargedUp()) {
				LivingEntity target = entity.getTarget();

				ServerParticlePacket packet = new ServerParticlePacket();
				double centerX = entity.position().x();
				double centerZ = entity.position().z();

				for (double angle = 0; angle < 2 * Math.PI; angle += 2 / 180d * (2 * Math.PI)) {
					packet.particle(new CustomisableParticleType.Data(AoAParticleTypes.SANDSTORM.get(), 0.5f, 4f, 0, 0, 0, 0, entity.getId()),
							centerX + 4 * Math.cos(angle),
							entity.position().y(),
							centerZ + 4 * Math.sin(angle), 0, 0.25f, 0, 1);
				}

				AoAPackets.messageNearbyPlayers(packet, (ServerLevel)entity.level, entity.position(), 20);

				if (entity.tickCount % 20 == 0 && (taskExpiresAt == Integer.MAX_VALUE || runningTime + 20 < taskExpiresAt))
					entity.playSound(AoASounds.SAND_WIND.get(), 1, 0.5f);

				if (!onlyIfOnGround && !target.isOnGround()) {
					resetActionTelegraph();

					return;
				}

				if (entity.tickCount < nextSpawnTime)
					return;

				if (!isTelegraphingAction()) {
					startTelegraphingNextAction();
				}
				else if (hasActionTelegraphFinished()) {
					Entity trap = trapFactory.apply(target.position());

					if (trap != null)
						entity.level.addFreshEntity(trap);

					nextSpawnTime = entity.tickCount + trapSpawnFrequency;

					startTelegraphingNextAction();
				}
			}
			else if (chargeUpTime - 1 == runningTime) {
				for (int i = 0; i < this.initialTrapSpawns; i++) {
					BlockPos.MutableBlockPos pos = RandomPos.generateRandomDirection(RandomUtil.RANDOM, 5, 5).offset(entity.getTarget().blockPosition()).mutable();
					BlockState block = entity.level.getBlockState(pos);

					if (block.isAir()) {
						int x = 10;

						while (x-- >= 0 && pos.getY() > entity.level.getMinBuildHeight() && (block = entity.level.getBlockState(pos.move(Direction.DOWN))).isAir()) {}

						if (block.isAir())
							continue;

						Entity trap = trapFactory.apply(Vec3.atBottomCenterOf(pos.move(Direction.UP)));

						if (trap != null)
							entity.level.addFreshEntity(trap);
					}
				}
			}
		}
	}
}
