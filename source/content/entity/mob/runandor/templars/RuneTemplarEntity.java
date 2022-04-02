/*
package net.tslat.aoa3.content.entity.mob.runandor.templars;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LootUtil;

import javax.annotation.Nullable;
import java.util.HashSet;

public abstract class RuneTemplarEntity extends PathfinderMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private static final EntityDataAccessor<Boolean> DISABLED = SynchedEntityData.<Boolean>defineId(RuneTemplarEntity.class, EntityDataSerializers.BOOLEAN);
	private final HashSet<RunicLifeformEntity> lifeforms = new HashSet<RunicLifeformEntity>();

	public RuneTemplarEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
		super(entityType, world);

		bossInfo.setVisible(false);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.8125f;
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		this.entityData.define(DISABLED, true);
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ANVIL_LAND;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ANVIL_LAND;
	}

	private void changeState(boolean disabled) {
		this.entityData.set(DISABLED, disabled);
		this.bossInfo.setVisible(!disabled);
	}

	public boolean isDisabled() {
		return this.entityData.get(DISABLED);
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source != DamageSource.OUT_OF_WORLD;
	}

	@Override
	public boolean isInvulnerable() {
		return true;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected boolean canRide(Entity entityIn) {
		return false;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public void push(double x, double y, double z) {}

	protected abstract RunicLifeformEntity getLifeForm();

	protected abstract Item getActivationRune();

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (hasCustomName())
			bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void setCustomName(@Nullable TextComponent name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);

		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		bossInfo.removePlayer(player);
	}

	@Override
	public boolean ignoreExplosion() {
		return true;
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (isDisabled() && heldStack.getItem() == getActivationRune()) {
			if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.RUNIC_ENERGY.get()), true, 1)) {
				changeState(false);

				return InteractionResult.CONSUME;
			}

			return InteractionResult.SUCCESS;
		}
		else {
			return super.mobInteract(player, hand);
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide) {
			if (!isDisabled()) {
				setHealth(getHealth() - 0.25f);

				if (getHealth() < 1) {
					doDrops();
					changeState(true);
					setHealth(getMaxHealth());

					for (RunicLifeformEntity lifeforms : lifeforms) {
						lifeforms.remove();
					}
				}
				else if (random.nextInt(125) == 0) {
					if (level.getEntitiesOfClass(RunicLifeformEntity.class, getBoundingBox().inflate(6, 3, 6)).size() <= 4) {
						RunicLifeformEntity lifeform = getLifeForm();

						int coordX = (int)getX() - 3 + random.nextInt(6);
						int coordZ = (int)getZ() - 3 + random.nextInt(6);

						lifeform.moveTo(coordX, getY(), coordZ, random.nextFloat() * 360, 0);
						level.addFreshEntity(lifeform);
						lifeforms.add(lifeform);
					}
				}
			}

			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getPercent())
				bossInfo.setPercent(healthPercent);
		}
	}

	@Override
	public void die(DamageSource cause) {}

	@Override
	protected void dropFromLootTable(DamageSource damageSourceIn, boolean p_213354_2_) {
		super.dropFromLootTable(damageSourceIn, p_213354_2_);
	}

	private void doDrops() {
		float luck = 0;

		for (Player pl : level.getEntitiesOfClass(Player.class, getBoundingBox().inflate(8))) {
			float plLuck = pl.getLuck();

			if (plLuck > luck)
				luck = plLuck;
		}

		if (!level.isClientSide()) {
			LootTable table = LootUtil.getTable((ServerLevel)level, getDefaultLootTable());

			for (ItemStack stack : table.getRandomItems(createLootContext(false, DamageSource.GENERIC).create(LootContextParamSets.ENTITY))) {
				spawnAtLocation(stack, 0);
			}
		}
	}
}
*/
