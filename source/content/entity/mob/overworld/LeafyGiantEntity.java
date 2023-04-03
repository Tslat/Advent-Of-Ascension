package net.tslat.aoa3.content.entity.mob.overworld;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.ai.mob.TelegraphedMeleeAttackGoal;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class LeafyGiantEntity extends AoAMeleeMob<LeafyGiantEntity> {
	private int nextBushBaby;

	public LeafyGiantEntity(EntityType<? extends LeafyGiantEntity> entityType, Level world) {
		super(entityType, world);

		this.nextBushBaby = RandomUtil.randomNumberBetween(150, 500);
		this.maxUpStep = 1.5f;
	}

	@Override
	protected Brain.Provider<LeafyGiantEntity> brainProvider() { // TODO
		return Brain.provider(List.of(MemoryModuleType.ATTACK_TARGET), ImmutableList.of());
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new TelegraphedMeleeAttackGoal<>(this).preAttackTime(getPreAttackTime()).attackInterval(getCurrentSwingDuration()));
		goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 12f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 3f;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.BRANCHES_BREAKING.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.LEAFY_THUD.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.HEAVY_FOLIAGE_THUMP.get();
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
	public boolean hurt(DamageSource source, float amount) {
		double preAttackHealth = getHealth();
		double halfHealth = getMaxHealth() * 0.5d;
		boolean success = super.hurt(source, amount);

		if (success && getHealth() <= halfHealth && preAttackHealth > halfHealth) {
			setInvulnerable(true);

			LivingEntity target = getTarget() != null ? getTarget() : this;

			for (int i = 0; i < 10; i++) {
				spawnBushBaby(target);
			}
		}

		return success;
	}

	@Override
	protected void customServerAiStep() {
		if (isInvulnerable() && tickCount % 30 == 0) {
			List<BushBabyEntity> bushBabies = EntityRetrievalUtil.getEntities(this, 40, new EntityPredicate<>().isAlive().is(AoAMobs.BUSH_BABY.get()));

			if (bushBabies.isEmpty()) {
				setInvulnerable(false);
			}
			else {
				bushBabies.forEach(bushBaby -> bushBaby.addEffect(new EffectBuilder(MobEffects.GLOWING, 35).isAmbient().build()));
			}
		}

		if (nextBushBaby <= tickCount) {
			LivingEntity target = getTarget();

			if (target == null)
				return;

			nextBushBaby = tickCount + RandomUtil.randomNumberBetween(150, 500);

			if (EntityRetrievalUtil.getEntities(this, 10, new EntityPredicate<>(this).isAlive().is(AoAMobs.BUSH_BABY.get())).size() < 5)
				spawnBushBaby(target);
		}
	}

	protected void spawnBushBaby(@Nonnull LivingEntity target) {
		if (level.isClientSide())
			return;

		BushBabyEntity bushBaby = EntitySpawningUtil.spawnEntity((ServerLevel)level, AoAMobs.BUSH_BABY.get(), BlockPos.containing(getX(), getBoundingBox().maxY, getZ()), MobSpawnType.MOB_SUMMONED);

		if (bushBaby == null)
			return;

		bushBaby.setDeltaMovement(Mth.clamp((target.getX() - getX()) * 0.2f, -0.85, 0.5f), 0.7, Mth.clamp((target.getZ() - getZ()) * 0.2f, -0.85, 0.85));
		BrainUtils.setTargetOfEntity(bushBaby, target);
		bushBaby.fallDistance = -10;

		if (isOnFire())
			bushBaby.setSecondsOnFire(getRemainingFireTicks() / 20);

		if (getHealth() < 0.5f * getMaxHealth()) {
			setInvulnerable(true);
			bushBaby.addEffect(new EffectBuilder(MobEffects.GLOWING, 35).isAmbient().build());
		}

		level.addFreshEntity(bushBaby);
	}

	@Override
	protected void onAttack(Entity target) {
		if (isOnFire())
			target.setSecondsOnFire(getRemainingFireTicks() / 20);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkController(this),
				AoAAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SLAM));
	}
}
