package net.tslat.aoa3.entity.mob.runandor.templars;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LootUtil;

import javax.annotation.Nullable;
import java.util.HashSet;

public abstract class RuneTemplarEntity extends CreatureEntity {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private static final DataParameter<Boolean> DISABLED = EntityDataManager.<Boolean>defineId(RuneTemplarEntity.class, DataSerializers.BOOLEAN);
	private final HashSet<RunicLifeformEntity> lifeforms = new HashSet<RunicLifeformEntity>();

	public RuneTemplarEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);

		bossInfo.setVisible(false);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);

		if (hasCustomName())
			bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void setCustomName(@Nullable ITextComponent name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);

		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		bossInfo.removePlayer(player);
	}

	@Override
	public boolean ignoreExplosion() {
		return true;
	}

	@Override
	protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (isDisabled() && heldStack.getItem() == getActivationRune()) {
			if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.RUNIC_ENERGY.get()), true, 1)) {
				changeState(false);

				return ActionResultType.CONSUME;
			}

			return ActionResultType.SUCCESS;
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

		for (PlayerEntity pl : level.getEntitiesOfClass(PlayerEntity.class, getBoundingBox().inflate(8))) {
			float plLuck = pl.getLuck();

			if (plLuck > luck)
				luck = plLuck;
		}

		if (!level.isClientSide()) {
			LootTable table = LootUtil.getTable((ServerWorld)level, getDefaultLootTable());

			for (ItemStack stack : table.getRandomItems(createLootContext(false, DamageSource.GENERIC).create(LootParameterSets.ENTITY))) {
				spawnAtLocation(stack, 0);
			}
		}
	}
}
