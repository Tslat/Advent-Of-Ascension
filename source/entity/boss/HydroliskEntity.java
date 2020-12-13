package net.tslat.aoa3.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.mob.misc.HydrolonEntity;

import javax.annotation.Nullable;

public class HydroliskEntity extends AoAMeleeMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);
	private static final DataParameter<Boolean> SHIELDED = EntityDataManager.<Boolean>createKey(HydroliskEntity.class, DataSerializers.BOOLEAN);
	private boolean shielded = true;

	public HydroliskEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, false));
		goalSelector.addGoal(7, new RandomWalkingGoal(this, 1));
		goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8f));
		goalSelector.addGoal(8, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<AoAMinion>(this, AoAMinion.class, true));
		targetSelector.addGoal(2, new HurtByTargetGoal(this));
		targetSelector.addGoal(3, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 2.625f;
	}

	@Override
	protected void registerData() {
		super.registerData();

		this.dataManager.register(SHIELDED, true);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 609;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 12;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_HYDROLISK_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_HYDROLISK_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_HYDROLISK_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_EMPEROR_BEAST_STEP.get();
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public boolean isInvulnerable() {
		return this.dataManager.get(SHIELDED);
	}

	public boolean isShielded() {
		return this.dataManager.get(SHIELDED);
	}

	private void disableShield() {
		this.dataManager.set(SHIELDED, false);
		this.shielded = false;

		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(800);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(16);
		this.setHealth(800);
	}

	@Override
	public void tick() {
		super.tick();

		if (isAIDisabled())
			return;

		if (isInWater())
			heal(1);

		if (!world.isRemote) {
			if (shielded) {
				if (rand.nextInt(80) == 0)
					world.addEntity(new HydrolonEntity(this));
			}
			else {
				if (rand.nextInt(120) == 0 && !world.getDimension().doesWaterVaporize() && world.getBlockState(getPosition()).getMaterial().isReplaceable())
					world.setBlockState(getPosition(), Blocks.WATER.getDefaultState().with(FlowingFluidBlock.LEVEL, 14));
			}
		}
	}

	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		if (shielded) {
			ItemStack heldItem = player.getHeldItem(hand);

			if (heldItem.getItem() == AoAItems.HYDRO_STONE.get()) {
				if (!player.isCreative())
					heldItem.shrink(1);

				if (getHealth() <= 50) {
					disableShield();
				}
				else {
					setHealth(getHealth() - 50);
				}

				return true;
			}
		}

		return super.processInteract(player, hand);
	}

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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.HYDROLISK_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.HYDROLISK_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
