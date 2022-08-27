package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.ai.mob.MultiTypeAttackGoal;
import net.tslat.aoa3.content.entity.ai.mob.TelegraphedMeleeAttackGoal;
import net.tslat.aoa3.content.entity.ai.mob.TelegraphedRangedAttackGoal;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.StoneGiantRock;
import net.tslat.aoa3.util.PositionAndMotionUtil;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class StoneGiantEntity extends AoAMeleeMob implements RangedAttackMob, AoARangedAttacker {
	public StoneGiantEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		this.maxUpStep = 1.5f;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 4.34375f;
	}

	@Override
	protected void registerGoals() {
		Goal meleeGoal = new TelegraphedMeleeAttackGoal<>(this).preAttackTime(getPreAttackTime()).attackInterval(getCurrentSwingDuration()).ignoreLineOfSight();
		Goal rangedGoal = new TelegraphedRangedAttackGoal<>(this).windUpTime(32).attackFrequency(41, 50).moveSpeedMod(0.75f);

		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new MultiTypeAttackGoal(currentGoal -> {
			LivingEntity target = getTarget();

			if (target == null)
				return 0;

			if (target.getY() > getEyeY())
				return 1;

			if (distanceToSqr(target) > 100)
				return 1;

			return getNavigation().createPath(target, 0) == null ? 1 : 0;
		}, meleeGoal, rangedGoal).onChange(goal -> setAttackState(goal == meleeGoal ? 0 : 1)));
		goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	@Override
	public float getStepHeight() {
		return super.getStepHeight();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.STONE_CRUMBLE.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ICE_HIT.get();
	}

	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
	}

	@Override
	protected float getWaterSlowDown() {
		return 1;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected int getAttackSwingDuration() {
		return getAttackState() == 0 ? 13 : 41;
	}

	@Override
	protected int getPreAttackTime() {
		return getAttackState() == 0 ? 7 : 32;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		Entity attacker = source.getEntity();

		if (attacker instanceof Silverfish) {
			if (!level.isClientSide()) {
				heal(level.getDifficulty().getId() * 7);
				AoAPackets.messageNearbyPlayers(new ServerParticlePacket().particle(ParticleTypes.HEART, attacker), (ServerLevel)level, attacker.position(), 50);
				playSound(SoundEvents.SILVERFISH_AMBIENT);
				attacker.discard();
			}

			return false;
		}

		boolean success = super.hurt(source, amount);

		if (success && amount > 1 && !level.isClientSide() && !isNoAi())
			level.addFreshEntity(makeSilverfish(this, attacker));

		return success;
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericWalkController(this));
		animationData.addAnimationController(AoAAnimations.dynamicAttackController(this, event -> getAttackState() == 0 ? AoAAnimations.ATTACK_SLAM : AoAAnimations.ATTACK_THROW));
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		BaseMobProjectile projectile = new StoneGiantRock(AoAProjectiles.STONE_GIANT_ROCK.get(), level, this, BaseMobProjectile.Type.PHYSICAL);

		projectile.setYRot(getYHeadRot());
		PositionAndMotionUtil.moveRelativeToFacing(projectile, -1, 0, 1.5f);
		PositionAndMotionUtil.moveTowards(projectile, target.getEyePosition(), 1.6d, 4 - level.getDifficulty().getId());
		projectile.setDeltaMovement(PositionAndMotionUtil.accountForGravity(projectile.position(), projectile.getDeltaMovement(), target.position(), projectile.getGravity()));
		PositionAndMotionUtil.faceTowardsMotion(projectile);

		level.addFreshEntity(projectile);
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		target.hurt(DamageSource.thrown(this, projectile), (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));

		ServerParticlePacket packet = new ServerParticlePacket()
				.particle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()), projectile, true, 0, 0, 0, 1, 3)
				.particle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.DIRT.defaultBlockState()), projectile, true, 0, 0, 0, 1, 3);

		projectile.playSound(AoASounds.ROCK_SMASH.get());
		AoAPackets.messageNearbyPlayers(packet, (ServerLevel)level, position(), 20);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		ServerParticlePacket packet = new ServerParticlePacket()
				.particle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()), projectile, true, 0, 0, 0, 1, 3)
				.particle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.DIRT.defaultBlockState()), projectile, true, 0, 0, 0, 1, 3);

		projectile.playSound(AoASounds.ROCK_SMASH.get());
		AoAPackets.messageNearbyPlayers(packet, (ServerLevel)level, position(), 20);}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {}

	private static Silverfish makeSilverfish(StoneGiantEntity stoneGiant, @Nullable Entity target) {
		Silverfish silverfish = new Silverfish(EntityType.SILVERFISH, stoneGiant.level) {
			@Override
			protected void customServerAiStep() {
				super.customServerAiStep();

				if (tickCount > 200 && stoneGiant != null && stoneGiant.isAlive() && stoneGiant.getHealth() < stoneGiant.getMaxHealth())
					setTarget(stoneGiant);
			}

			@Override
			protected boolean shouldDropLoot() {
				return false;
			}
		};

		silverfish.setPos(stoneGiant.position());

		if (target instanceof LivingEntity && (!(target instanceof Player pl) || !pl.isCreative()))
			silverfish.setTarget((LivingEntity)target);

		return silverfish;
	}
}
