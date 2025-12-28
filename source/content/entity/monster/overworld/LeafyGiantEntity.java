package net.tslat.aoa3.content.entity.monster.overworld;

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
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.content.entity.ai.mob.TelegraphedMeleeAttackGoal;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;

public class LeafyGiantEntity extends AoAMeleeMob<LeafyGiantEntity> {
	private int nextBushBaby;

	public LeafyGiantEntity(EntityType<? extends LeafyGiantEntity> entityType, Level world) {
		super(entityType, world);

		this.nextBushBaby = RandomUtil.numberBetween(150, 500);
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
	protected float nextStep() {
		return this.moveDist + 1.5f;
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
			List<BushBabyEntity> bushBabies = EntityRetrievalUtil.getEntities(this, 40, BushBabyEntity.class, Entity::isAlive);

			if (bushBabies.isEmpty()) {
				setInvulnerable(false);
			}
			else {
				EntityUtil.applyPotions(bushBabies, this, new EffectBuilder(MobEffects.GLOWING, 35).isAmbient());
			}
		}

		if (nextBushBaby <= tickCount) {
			LivingEntity target = getTarget();

			if (target == null)
				return;

			nextBushBaby = tickCount + RandomUtil.numberBetween(150, 500);

			if (EntityRetrievalUtil.getEntities(this, 10, BushBabyEntity.class, LivingEntity::isAlive).size() < 5)
				spawnBushBaby(target);
		}
	}

	protected void spawnBushBaby(@NotNull LivingEntity target) {
		if (level().isClientSide())
			return;

		BushBabyEntity bushBaby = EntitySpawningUtil.spawnEntity((ServerLevel)level(), AoAMonsters.BUSH_BABY.get(), BlockPos.containing(getX(), getBoundingBox().maxY, getZ()), MobSpawnType.MOB_SUMMONED);

		if (bushBaby == null)
			return;

		bushBaby.setDeltaMovement(Mth.clamp((target.getX() - getX()) * 0.2f, -0.85, 0.5f), 0.7, Mth.clamp((target.getZ() - getZ()) * 0.2f, -0.85, 0.85));
		BrainUtils.setTargetOfEntity(bushBaby, target);
		bushBaby.fallDistance = -10;

		if (isOnFire())
			bushBaby.igniteForSeconds(getRemainingFireTicks() / 20);

		if (getHealth() < 0.5f * getMaxHealth()) {
			setInvulnerable(true);
			EntityUtil.applyPotions(bushBaby, this, new EffectBuilder(MobEffects.GLOWING, 35).isAmbient());
		}
	}

	@Override
	protected void onAttack(Entity target) {
		if (isOnFire())
			target.igniteForSeconds(getRemainingFireTicks() / 20);
	}

	public static SpawnPlacements.SpawnPredicate<LeafyGiantEntity> spawnRules(EntityType<LeafyGiantEntity> entityType) {
		return EntitySpawnConditions.createDayMonster(entityType).spawnChance(1 / 15f);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<LeafyGiantEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(135)
				.moveSpeed(0.32)
				.meleeStrength(9)
				.knockbackResist(1)
				.followRange(40)
				.stepHeight(1.5f);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SLAM).transitionLength(0));
	}
}
