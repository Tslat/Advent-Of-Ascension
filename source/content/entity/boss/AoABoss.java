package net.tslat.aoa3.content.entity.boss;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoAMonster;
import net.tslat.aoa3.data.server.AoANowhereBossArenaListener;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.util.WorldUtil;
import software.bernie.geckolib.core.animation.RawAnimation;

import javax.annotation.Nullable;

public abstract class AoABoss extends AoAMonster<AoABoss>{
	private final ServerBossEvent bossStatusTracker = (ServerBossEvent)new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.NOTCHED_20).setDarkenScreen(false).setCreateWorldFog(false);
	private SwingData swingData;

	private int lastArenaBoundTick = -1;

	protected AoABoss(EntityType<? extends AoABoss> entityType, Level level) {
		super(entityType, level);

		this.bossStatusTracker.id = getUUID();
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		addSwingData(this.swingData = new SwingData());
	}

	protected void addSwingData(SwingData swings) {}

	@Override
	public int calculateKillXp() {
		return !this.hasDrops ? 0 : 2 * (int)(5 + (getAttributeValue(Attributes.MAX_HEALTH) + getAttributeValue(Attributes.ARMOR) * 1.75f + getAttributeValue(Attributes.ATTACK_DAMAGE) * 2) / 10f);
	}

	@Nullable
	public abstract SoundEvent getMusic();

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
		super.customServerAiStep();

		this.bossStatusTracker.setProgress(getHealth() / getMaxHealth());

		if (this.level().dimension() == AoADimensions.NOWHERE.key && this.tickCount % 60 == 0) {
			if (this.level().getHeight(Heightmap.Types.MOTION_BLOCKING, (int)getX(), (int)getZ()) == this.level().getMinBuildHeight()) {
				if (this.lastArenaBoundTick != -1 && this.tickCount - this.lastArenaBoundTick >= 180) {
					this.lastArenaBoundTick = -1;

					AoANowhereBossArenaListener.NowhereBossArena arena = AoANowhereBossArenaListener.getClosestArena((ServerLevel)this.level(), this.position());

					if (arena != null) {
						resetFallDistance();
						setPos(arena.getRandomBossSpawn());
					}
					else {
						discard();
					}
				}
				else if (this.lastArenaBoundTick == -1) {
					this.lastArenaBoundTick = this.tickCount;
				}
			}
			else if (this.lastArenaBoundTick != -1) {
				this.lastArenaBoundTick = -1;
			}
		}
	}

	public SwingData getSwingData() {
		return this.swingData;
	}

	protected RawAnimation getSwingAnimation() {
		return getSwingAnimation(ATTACK_STATE.get(this));
	}

	protected RawAnimation getSwingAnimation(int state) {
		return this.swingData.getSwingAnimation(state);
	}

	protected int getSwingDurationTicks() {
		return getSwingDurationTicks(ATTACK_STATE.get(this));
	}

	protected int getSwingDurationTicks(int state) {
		return this.swingData.getSwingLength(state);
	}

	protected int getSwingWarmupTicks() {
		return getSwingWarmupTicks(ATTACK_STATE.get(this));
	}

	protected int getSwingWarmupTicks(int state) {
		return this.swingData.getSwingPreHurtTime(state);
	}

	@Override
	public int getCurrentSwingDuration() {
		int time = getSwingDurationTicks();

		if (MobEffectUtil.hasDigSpeed(this))
			time -= 1 + MobEffectUtil.getDigSpeedAmplification(this);

		if (hasEffect(MobEffects.DIG_SLOWDOWN))
			time += (1 + getEffect(MobEffects.DIG_SLOWDOWN).getAmplifier()) * 2;

		return time;
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);

		this.bossStatusTracker.addPlayer(player);

		if (getMusic() != null && level().dimension() != AoADimensions.NOWHERE.key)
			new SoundBuilder(getMusic()).isMusic().include(player).execute();
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		this.bossStatusTracker.removePlayer(player);

		if (getMusic() != null)
			new SoundBuilder(getMusic()).isMusic().stopSound().include(player).execute();
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.FELL_OUT_OF_WORLD) && WorldUtil.isWorld(this.level(), AoADimensions.NOWHERE.key) && !this.level().isClientSide() && getY() < this.level().getMinBuildHeight()) {
			AoANowhereBossArenaListener.NowhereBossArena arena = AoANowhereBossArenaListener.getClosestArena((ServerLevel)this.level(), this.position());

			if (arena != null) {
				resetFallDistance();
				setPos(arena.getRandomBossSpawn());

				return false;
			}
		}

		return super.hurt(source, amount);
	}

	public record SwingData(Int2ObjectMap<Swing> data) {
		public record Swing(int animLength, int warmupTicks, RawAnimation anim) {}

		public SwingData() {
			this(Util.make(new Int2ObjectOpenHashMap<>(), map -> map.defaultReturnValue(new Swing(0, 0, RawAnimation.begin()))));
		}

		public void put(int key, Swing swing) {
			this.data.put(key, swing);
		}

		public Swing getSwing(int key) {
			return this.data.get(key);
		}

		public int getSwingLength(int key) {
			return getSwing(key).animLength();
		}

		public int getSwingPreHurtTime(int key) {
			return getSwing(key).warmupTicks();
		}

		public RawAnimation getSwingAnimation(int key) {
			return getSwing(key).anim();
		}
	}
}
