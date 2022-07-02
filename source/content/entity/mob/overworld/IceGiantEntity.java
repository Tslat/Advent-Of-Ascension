package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.ai.mob.MultiTypeAttackGoal;
import net.tslat.aoa3.content.entity.ai.mob.TelegraphedMeleeAttackGoal;
import net.tslat.aoa3.content.entity.ai.mob.TelegraphedRangedAttackGoal;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.RandomUtil;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class IceGiantEntity extends AoAMeleeMob implements RangedAttackMob {
	private int lastAttackTime = 0;

	public IceGiantEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		this.maxUpStep = 1.5f;
	}

	@Override
	protected void registerGoals() {
		Goal meleeGoal = new TelegraphedMeleeAttackGoal<>(this).preAttackTime(getPreAttackTime()).attackInterval(getCurrentSwingDuration());
		Goal rangedGoal = new TelegraphedRangedAttackGoal<>(this).attackFrequency(0, 1).moveSpeedMod(0).windUpTime(1);

		goalSelector.addGoal(1, new MultiTypeAttackGoal(currentGoal -> {
				Entity target = getTarget();

				if (target == null)
					return 0;

				if (tickCount - lastAttackTime > 120)
					return distanceToSqr(target) < 16 ? 0 : 1;

				if (distanceToSqr(target) > 100 && !target.isFullyFrozen())
					return 1;

				return getNavigation().createPath(target, 0) == null ? 1 : 0;
			}, meleeGoal, rangedGoal));

		goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.6f));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 12f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 2.65625f;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ICE_BREAK.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ICE_HIT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (isUnderWater()) {
			ServerParticlePacket packet = new ServerParticlePacket();
			AABB boundingBox = getBoundingBox();
			double width = boundingBox.maxX - boundingBox.minX;
			double depth = boundingBox.maxZ - boundingBox.minZ;
			double height = boundingBox.maxY - boundingBox.minY;

			for (int i = 0; i < 3 + (10 * width * depth * height); i++) {
				packet.particle(new CustomisableParticleType.Data(AoAParticleTypes.FREEZING_SNOWFLAKE.get(), 0.3f, 3, 0), boundingBox.minX + RandomUtil.randomValueUpTo(width), boundingBox.minY + RandomUtil.randomValueUpTo(height), boundingBox.minZ + RandomUtil.randomValueUpTo(depth), RandomUtil.randomScaledGaussianValue(0.05d), 0, RandomUtil.randomScaledGaussianValue(0.05d));
			}

			AoAPackets.messageNearbyPlayers(packet, (ServerLevel)level, position(), 10);
			remove(RemovalReason.KILLED);
		}

		Entity target = getTarget();

		if (target != null && target.isFullyFrozen())
			lastAttackTime = tickCount;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public boolean canFreeze() {
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
	protected void onAttack(Entity target) {
		this.lastAttackTime = tickCount;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (super.hurt(source, amount)) {
			if (source.getDirectEntity() instanceof LivingEntity attacker && DamageUtil.isMeleeDamage(source))
				attacker.setTicksFrozen(attacker.getTicksFrozen() + 100);

			return true;
		}

		return false;
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericWalkController(this));
		animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_SLAM));
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		double baseX = getX();
		double baseY = getEyeY();
		double baseZ = getZ();
		ServerParticlePacket packet = new ServerParticlePacket(true);

		for (int i = 0; i < 5; i++) {
			double x = baseX + RandomUtil.randomScaledGaussianValue(0.5f);
			double y = baseY + RandomUtil.randomScaledGaussianValue(0.5f);
			double z = baseZ + RandomUtil.randomScaledGaussianValue(0.5f);
			double targetX = target.getX() + RandomUtil.randomScaledGaussianValue(0.5f);
			double targetY = target.getEyeY() + RandomUtil.randomScaledGaussianValue(0.5f);
			double targetZ = target.getZ() + RandomUtil.randomScaledGaussianValue(0.5f);

			packet.particle(new CustomisableParticleType.Data(AoAParticleTypes.FREEZING_SNOWFLAKE.get(), 0.4f, 12, 0, 0, 0, 0, getId()), baseX, baseY, baseZ, (targetX - x) * 0.1, (targetY - y) * 0.1, (targetZ - z) * 0.1);
		}

		if (tickCount % 5 == 0)
			playSound(AoASounds.ICE_WIND.get(), 1.5f, 1f);

		AoAPackets.messageNearbyPlayers(packet, (ServerLevel)level, getEyePosition(), 200);
	}
}
