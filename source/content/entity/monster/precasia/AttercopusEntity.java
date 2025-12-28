package net.tslat.aoa3.content.entity.monster.precasia;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.worldgen.AoABiomes;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

public class AttercopusEntity extends AoAMeleeMob<AttercopusEntity> {
	public static final EntityDataAccessor<Boolean> CLIMBING = makeSynchedData(AttercopusEntity.class, EntityDataSerializers.BOOLEAN);

	private boolean climbing = false;

	public AttercopusEntity(EntityType<? extends AttercopusEntity> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);

		builder.define(CLIMBING, false);
	}

	@Override
	protected int getAttackSwingDuration() {
		return 10;
	}

	@Override
	protected int getPreAttackTime() {
		return 7;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SPIDER_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundEvents.SPIDER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SPIDER_DEATH;
	}

	@Override
	protected @Nullable SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return SoundEvents.SPIDER_STEP;
	}

	@Override
	protected PathNavigation createNavigation(Level pLevel) {
		return new WallClimberNavigation(this, pLevel);
	}

	@Override
	public boolean onClimbable() {
		return getSynchedData(CLIMBING);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level().isClientSide)
			setSynchedData(CLIMBING, this.horizontalCollision);
	}

	@Override
	public void makeStuckInBlock(BlockState state, Vec3 motionModifier) {
		if (!state.is(Blocks.COBWEB))
			super.makeStuckInBlock(state, motionModifier);
	}

	@Override
	protected DamageSource getAttackDamageSource(Entity target) {
		return target instanceof LivingEntity livingTarget ? damageSources().sting(livingTarget) : super.getAttackDamageSource(target);
	}

	@Override
	public boolean canBeAffected(MobEffectInstance effect) {
		return effect.getEffect() != MobEffects.POISON && super.canBeAffected(effect);
	}

	@Override
	protected int calculateFallDamage(float pFallDistance, float pDamageMultiplier) {
		return Math.max(0, super.calculateFallDamage(pFallDistance, pDamageMultiplier) - 10) / 2;
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity)
			EntityUtil.applyPotions(target, this, new EffectBuilder(MobEffects.POISON, level().getDifficulty().getId() * 8 * 20));
	}

	public static SpawnPlacements.SpawnPredicate<AttercopusEntity> spawnRules(EntityType<AttercopusEntity> entityType) {
		return EntitySpawnConditions.createDayNightMonster(entityType).and((entityType2, level, spawnType, pos, rand) -> {
			if (level.getLevel().dimension() != AoADimensions.PRECASIA)
				return true;

			if (pos.getY() <= 50)
				return !EntitySpawnConditions.isNaturalSpawn(spawnType) || rand.nextFloat() < 0.05f;

			return level.getBiome(pos).is(AoABiomes.PRECASIAN_DESERT) && level.getSkyDarken() >= 4 && rand.nextFloat() < 0.05f * level.getCurrentDifficultyAt(pos).getEffectiveDifficulty();
		});
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<AttercopusEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(29)
				.moveSpeed(0.3)
				.meleeStrength(5.5f)
				.aggroRange(16)
				.followRange(32);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkIdleController(this));
		controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_STRIKE));
	}
}
