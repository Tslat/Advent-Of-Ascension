package net.tslat.aoa3.content.entity.monster.overworld;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidType;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyLivingEntitySensor;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.TreeSpiritSpriteEntity;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableRangedAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.CustomHeldBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRetaliateTarget;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

import java.util.List;

public class TreeSpiritEntity extends AoARangedMob<TreeSpiritEntity> {
	private static final RawAnimation ACTIVATE = RawAnimation.begin().thenPlay("misc.activate").thenLoop("misc.active_idle");
	private static final RawAnimation DEACTIVATE = RawAnimation.begin().thenPlay("misc.deactivate");

	public TreeSpiritEntity(EntityType<? extends TreeSpiritEntity> entityType, Level world) {
		super(entityType, world);

		setImmobile(true);
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.WOOD_HIT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.TREE_FALL.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockState) {}

	@Override
	public List<ExtendedSensor<? extends TreeSpiritEntity>> getSensors() {
		return ObjectArrayList.of(
				new AggroBasedNearbyPlayersSensor<TreeSpiritEntity>(),
				new AggroBasedNearbyLivingEntitySensor<TreeSpiritEntity>()
						.setPredicate((target, entity) -> target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null).setScanRate(entity -> 40),
				new HurtBySensor<>());
	}

	@Override
	public BrainActivityGroup<TreeSpiritEntity> getCoreTasks() {
		return BrainActivityGroup.coreTasks(
				new CustomHeldBehaviour<>(owner -> {
					if (isAttackState(1) && BrainUtils.getTargetOfEntity(owner) == null)
						setAttackState(0);
				})
		);
	}

	@Override
	public BrainActivityGroup<TreeSpiritEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new SetRetaliateTarget<>()
						.attackablePredicate(target -> DamageUtil.isAttackable(target) && distanceToSqr(target.position()) < Mth.square(getAttributeValue(AoAAttributes.AGGRO_RANGE)))
						.whenStarting(owner -> isAttackState(1))
						.startCondition(entity -> level().getDifficulty() != Difficulty.PEACEFUL));
	}

	@Override
	public BrainActivityGroup<TreeSpiritEntity> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>()
						.invalidateIf((entity, target) -> !target.isAlive() || level().getDifficulty() == Difficulty.PEACEFUL || (target instanceof Player pl && (pl.isCreative() || pl.isSpectator())) || distanceToSqr(target.position()) > Mth.square(getAttributeValue(Attributes.FOLLOW_RANGE))),
				new AnimatableRangedAttack<>(15)
						.attackInterval(entity -> RandomUtil.numberBetween(15, 35))
						.startCondition(entity -> level().getDifficulty() != Difficulty.PEACEFUL));
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		absMoveTo(Math.floor(getX()) + 0.5d, getY(), Math.floor(getZ()) + 0.5d, 0, 0);
		setOldPosAndRot();

		return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
	}

	@Override
	public Component getName() {
		if (isAttackState(0))
			return Component.translatable("entity." + AdventOfAscension.MOD_ID + ".dead_tree");

		return super.getName();
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(Tags.DamageTypes.IS_TECHNICAL))
			return super.hurt(source, amount);

		if (source.is(DamageTypeTags.IS_FIRE))
			amount *= 1.25f;

		boolean wasMaxHealth = isAlive() && getHealth() == getMaxHealth();

		if (super.hurt(source, amount)) {
			if (!level().isClientSide && wasMaxHealth && PlayerUtil.getPlayerOrOwnerIfApplicable(source.getEntity()) instanceof ServerPlayer pl) {
				if (getHealth() <= 0)
					AdvancementUtil.grantCriterion(pl, AdventOfAscension.id("completionist/mightiest_tree_in_the_forest"), "tree_spirit_instakill");
			}

			return true;
		}

		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public void playerTouch(Player player) {}

	@Override
	protected void doPush(Entity entity) {}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_TREE_SPIRIT_SHOOT.get();
	}

	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return false;
	}

	@Override
	public void doRangedAttackEntity(@Nullable BaseMobProjectile projectile, Entity target) {
		if (target instanceof TreeSpiritEntity)
			return;

		super.doRangedAttackEntity(projectile, target);
	}

	@Override
	public void onProjectileAttack(@Nullable BaseMobProjectile projectile, Entity target) {
		level().playSound(null, projectile.getX(), projectile.getY(), projectile.getZ(), AoASounds.ENTITY_TREE_SPIRIT_SPRITE_IMPACT.get(), SoundSource.HOSTILE, 1, 1);
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		this.level().addFreshEntity(getNewProjectileInstance());
		this.level().playSound(null, getX(), getY(), getZ(), getShootSound(), SoundSource.HOSTILE, 1.0f, 1.0f);
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new TreeSpiritSpriteEntity(this, getTarget());
	}

	public static SpawnPlacements.SpawnPredicate<TreeSpiritEntity> spawnRules(EntityType<TreeSpiritEntity> entityType) {
		return EntitySpawnConditions.createDayMonster(entityType).noLowerThanY(AoADimensions.OVERWORLD, 55).spawnChance(1 / 10f);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<TreeSpiritEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(35)
				.moveSpeed(0)
				.projectileDamage(6)
				.knockbackResist(1)
				.aggroRange(16)
				.followRange(32);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "Living", 0, state -> {
			if (isAttackState(1))
				return state.setAndContinue(ACTIVATE);

			if (state.getController().getCurrentRawAnimation() != null)
				return state.setAndContinue(DEACTIVATE);

			return PlayState.STOP;
		}));
	}
}