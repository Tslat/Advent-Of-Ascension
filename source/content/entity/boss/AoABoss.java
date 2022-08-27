package net.tslat.aoa3.content.entity.boss;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ForgeHooks;
import net.tslat.aoa3.common.misc.AoAAnimatable;
import net.tslat.aoa3.common.misc.AoAAnimationFactory;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.util.BrainUtils;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public abstract class AoABoss extends Monster implements AoAAnimatable<AoABoss>, SmartBrainOwner<AoABoss> {
	protected static final EntityDataAccessor<Integer> ATTACK_STATE = SynchedEntityData.defineId(AoABoss.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Boolean> INVULNERABLE = SynchedEntityData.defineId(AoABoss.class, EntityDataSerializers.BOOLEAN);

	private final ServerBossEvent bossStatusTracker = (ServerBossEvent)new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20).setDarkenScreen(false).setCreateWorldFog(false);
	private final AoAAnimationFactory<AoABoss> animationFactory = new AoAAnimationFactory<>(this);
	private Int2ObjectOpenHashMap<SwingData> attackStates;

	protected AoABoss(EntityType<? extends Monster> entityType, Level level) {
		super(entityType, level);

		this.bossStatusTracker.id = getUUID();
	}

	private void checkAndPrepAttackStates() {
		if (this.attackStates == null) {
			this.attackStates = new Int2ObjectOpenHashMap<>();

			addSwingData(this.attackStates);
		}
	}

	protected void addSwingData(Int2ObjectOpenHashMap<SwingData> states) {}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		getEntityData().define(ATTACK_STATE, 0);
		getEntityData().define(INVULNERABLE, false);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
		xpReward = reason == MobSpawnType.MOB_SUMMONED ? 0 : 2 * (int)(5 + (getAttributeValue(Attributes.MAX_HEALTH) + getAttributeValue(Attributes.ARMOR) * 1.75f + getAttributeValue(Attributes.ATTACK_DAMAGE) * 2) / 10f);

		return super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
	}

	@Override
	protected abstract float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn);

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return null;
	}

	@Nullable
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		if (!blockState.getMaterial().isLiquid()) {
			BlockState state = this.level.getBlockState(pos.above());
			SoundType blockSound = state.getBlock() == Blocks.SNOW ? state.getSoundType(level, pos, this) : blockState.getSoundType(level, pos, this);
			return blockSound.getStepSound();
		}

		return null;
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
		return true;
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected boolean canRide(Entity entity) {
		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (hasCustomName())
			this.bossStatusTracker.setName(getDisplayName());

		this.bossStatusTracker.id = getUUID();
	}

	@Override
	public void setCustomName(@org.jetbrains.annotations.Nullable Component name) {
		super.setCustomName(name);

		this.bossStatusTracker.setName(getDisplayName());
	}

	@Override
	protected void customServerAiStep() {
		this.bossStatusTracker.setProgress(getHealth() / getMaxHealth());
		tickBrain(this);
	}

	protected AnimationBuilder getSwingAnim(int state) {
		checkAndPrepAttackStates();

		return this.attackStates.get(state).anim;
	}

	protected int getSwingDurationTicks(int state) {
		checkAndPrepAttackStates();

		return this.attackStates.get(state).animLength;
	}

	protected int getSwingWarmupTicks(int state) {
		checkAndPrepAttackStates();

		return this.attackStates.get(state).warmupTicks;
	}

	@Override
	public int getCurrentSwingDuration() {
		return getSwingDurationTicks(getAttackState());
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);

		this.bossStatusTracker.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		this.bossStatusTracker.removePlayer(player);
	}

	@Override
	public void setInvulnerable(boolean isInvulnerable) {
		super.setInvulnerable(isInvulnerable);

		getEntityData().set(INVULNERABLE, isInvulnerable);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		super.onSyncedDataUpdated(key);

		if (key.equals(INVULNERABLE))
			setInvulnerable(getEntityData().get(INVULNERABLE));
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);

		setInvulnerable(isInvulnerable());
	}

	public boolean isAttackState(int state) {
		return getAttackState() == state;
	}

	public int getAttackState() {
		return this.getEntityData().get(ATTACK_STATE);
	}

	public void setAttackState(int state) {
		this.getEntityData().set(ATTACK_STATE, state);
	}

	@Override
	protected boolean shouldDropLoot() {
		return super.shouldDropLoot() && xpReward > 0;
	}

	@Nullable
	@Override
	public LivingEntity getTarget() {
		return BrainUtils.getTargetOfEntity(this, super.getTarget());
	}

	@Override
	public abstract void registerControllers(AnimationData data);

	@Override
	public AoAAnimationFactory<AoABoss> getFactory() {
		return this.animationFactory;
	}

	@Override
	protected Brain.Provider<AoABoss> brainProvider() {
		return new SmartBrainProvider<>(this);
	}

	protected record SwingData(int animLength, int warmupTicks, AnimationBuilder anim) {}

	@Override
	public void die(DamageSource cause) {
		if (ForgeHooks.onLivingDeath(this, cause))
			return;

		if (!isRemoved() && !this.dead) {
			Entity lastAttacker = cause.getEntity();
			LivingEntity killer = getKillCredit();

			if (this.deathScore >= 0 && killer != null)
				killer.awardKillScore(this, this.deathScore, cause);

			if (isSleeping())
				stopSleeping();

			this.dead = true;

			if (this.level instanceof ServerLevel) {
				if (lastAttacker == null || lastAttacker.wasKilled((ServerLevel)this.level, this)) {
					gameEvent(GameEvent.ENTITY_DIE);
					dropAllDeathLoot(cause);
					createWitherRose(killer);
				}

				this.level.broadcastEntityEvent(this, (byte)3);
			}

			getCombatTracker().recheckStatus();
			setPose(Pose.DYING);
		}
	}
}
