package net.tslat.aoa3.entity.mob.runandor.templars;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LootUtil;

import javax.annotation.Nullable;
import java.util.HashSet;

public abstract class RuneTemplarEntity extends CreatureEntity {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);
	private static final DataParameter<Boolean> DISABLED = EntityDataManager.<Boolean>createKey(RuneTemplarEntity.class, DataSerializers.BOOLEAN);
	private final HashSet<RunicLifeformEntity> lifeforms = new HashSet<RunicLifeformEntity>();
	public static final float entityWidth = 1.125f;
	public static final float entityHeight = 2f;

	public RuneTemplarEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.8125f;
	}

	@Override
	protected void registerData() {
		super.registerData();

		this.dataManager.register(DISABLED, true);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();

		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(400);
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
		getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_ANVIL_LAND;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.BLOCK_ANVIL_LAND;
	}

	private void changeState(boolean disabled) {
		this.dataManager.set(DISABLED, disabled);
	}

	public boolean isDisabled() {
		return this.dataManager.get(DISABLED);
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
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected boolean canBeRidden(Entity entityIn) {
		return false;
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public void addVelocity(double x, double y, double z) {}

	protected abstract RunicLifeformEntity getLifeForm();

	protected abstract RuneItem getActivationRune();

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);

		if (hasCustomName())
			bossInfo.setName(getType().getName().deepCopy().appendSibling(getDisplayName()));
	}

	@Override
	public void setCustomName(@Nullable ITextComponent name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getName().deepCopy().appendSibling(getDisplayName()));
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();

		bossInfo.setPercent(getHealth() / getMaxHealth());
	}

	@Override
	public void addTrackingPlayer(ServerPlayerEntity player) {
		super.addTrackingPlayer(player);

		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		bossInfo.removePlayer(player);
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (isDisabled() && heldStack.getItem() == getActivationRune()) {
			if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.RUNIC_ENERGY.get()), true, 1))
				changeState(false);

			return true;
		}
		else {
			return super.processInteract(player, hand);
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (!world.isRemote && !isDisabled()) {
			setHealth(getHealth() - 0.25f);

			if (getHealth() < 1) {
				doDrops();
				changeState(true);
				setHealth(getMaxHealth());

				for (RunicLifeformEntity lifeforms : lifeforms) {
					lifeforms.remove();
				}
			}
			else if (rand.nextInt(125) == 0) {
				if (world.getEntitiesWithinAABB(RunicLifeformEntity.class, getBoundingBox().grow(6, 3, 6)).size() <= 4) {
					RunicLifeformEntity lifeform = getLifeForm();

					int coordX = (int)getPosX() - 3 + rand.nextInt(6);
					int coordZ = (int)getPosZ() - 3 + rand.nextInt(6);

					lifeform.setLocationAndAngles(coordX, getPosY(), coordZ, rand.nextFloat() * 360, 0);
					world.addEntity(lifeform);
					lifeforms.add(lifeform);
				}
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {}

	@Override
	protected void dropLoot(DamageSource damageSourceIn, boolean p_213354_2_) {
		super.dropLoot(damageSourceIn, p_213354_2_);
	}

	private void doDrops() {
		float luck = 0;

		for (PlayerEntity pl : world.getEntitiesWithinAABB(PlayerEntity.class, getBoundingBox().grow(8))) {
			float plLuck = pl.getLuck();

			if (plLuck > luck)
				luck = plLuck;
		}

		if (!world.isRemote()) {
			LootTable table = LootUtil.getTable((ServerWorld)world, getLootTable());

			for (ItemStack stack : table.generate(getLootContextBuilder(false, DamageSource.GENERIC).build(LootParameterSets.ENTITY))) {
				entityDropItem(stack, 0);
			}
		}
	}
}
