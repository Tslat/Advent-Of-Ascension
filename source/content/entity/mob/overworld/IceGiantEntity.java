package net.tslat.aoa3.content.entity.mob.overworld;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.content.entity.ai.mob.MultiTypeAttackGoal;
import net.tslat.aoa3.content.entity.ai.mob.TelegraphedMeleeAttackGoal;
import net.tslat.aoa3.content.entity.ai.mob.TelegraphedRangedAttackGoal;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.networking.packet.TELParticlePacket;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.List;

public class IceGiantEntity extends AoAMeleeMob<IceGiantEntity> implements AoARangedAttacker {
	private int lastAttackTime = 0;

	public IceGiantEntity(EntityType<? extends IceGiantEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected Brain.Provider<IceGiantEntity> brainProvider() { // TODO
		return Brain.provider(List.of(MemoryModuleType.ATTACK_TARGET), ImmutableList.of());
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
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
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

	protected void customServerAiStep() {
		super.customServerAiStep();

		if (isUnderWater()) {




			AABB boundingBox = getBoundingBox();
			double width = boundingBox.maxX - boundingBox.minX;
			double depth = boundingBox.maxZ - boundingBox.minZ;
			double height = boundingBox.maxY - boundingBox.minY;
			int particleCount = (int)Math.ceil(3 + (10 * width * depth * height));
			TELParticlePacket packet = new TELParticlePacket();

			for (int i = 0; i < particleCount; i++) {
				packet.particle(ParticleBuilder.forRandomPosInEntity(new CustomisableParticleType.Data(AoAParticleTypes.FREEZING_SNOWFLAKE.get(), 0.3f, 3, 0), this)
						.velocity(RandomUtil.randomScaledGaussianValue(0.05d), 0, RandomUtil.randomScaledGaussianValue(0.05d)));
			}

			packet.sendToAllNearbyPlayers((ServerLevel)level(), position(), 10);
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
			if (source.getDirectEntity() instanceof LivingEntity attacker && DamageUtil.isMeleeDamage(source) && (!(attacker instanceof Player pl) || pl.getAbilities().invulnerable))
				attacker.setTicksFrozen(attacker.getTicksFrozen() + 100);

			return true;
		}

		return false;
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		double baseX = getX();
		double baseY = getEyeY();
		double baseZ = getZ();
		TELParticlePacket packet = new TELParticlePacket(5);

		for (int i = 0; i < 5; i++) {
			double x = baseX + RandomUtil.randomScaledGaussianValue(0.5f);
			double y = baseY + RandomUtil.randomScaledGaussianValue(0.5f);
			double z = baseZ + RandomUtil.randomScaledGaussianValue(0.5f);
			double targetX = target.getX() + RandomUtil.randomScaledGaussianValue(0.5f);
			double targetY = target.getEyeY() + RandomUtil.randomScaledGaussianValue(0.5f);
			double targetZ = target.getZ() + RandomUtil.randomScaledGaussianValue(0.5f);

			packet.particle(ParticleBuilder.forPosition(new CustomisableParticleType.Data(AoAParticleTypes.FREEZING_SNOWFLAKE.get(), 0.4f, 12, 0, 0, 0, 0, getId()), baseX, baseY, baseZ).velocity((targetX - x) * 0.1, (targetY - y) * 0.1, (targetZ - z) * 0.1));
		}

		if (tickCount % 5 == 0)
			playSound(AoASounds.ICE_WIND.get(), 1.5f, 1f);

		packet.sendToAllNearbyPlayers((ServerLevel)level(), getEyePosition(), 200);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkController(this),
				AoAAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SLAM));
	}

	@Override
	public void doRangedAttackEntity(@org.jetbrains.annotations.Nullable BaseMobProjectile projectile, Entity target) {
		if (projectile == null) {
			if (DamageUtil.safelyDealDamage(DamageUtil.positionedEntityDamage(AoADamageTypes.MOB_ICEBEAM, this, target.position()), target, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get())) && target.getTicksFrozen() <= target.getTicksRequiredToFreeze() * 2.5f)
				target.setTicksFrozen(target.getTicksFrozen() + 34);
		}
	}

	@Override
	public void doRangedAttackBlock(@org.jetbrains.annotations.Nullable BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {}
}
