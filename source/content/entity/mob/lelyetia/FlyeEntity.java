package net.tslat.aoa3.content.entity.mob.lelyetia;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.*;

import javax.annotation.Nullable;

public class FlyeEntity extends AoAFlyingMeleeMob {
	private static final EntityDataAccessor<BlockPos> ALTAR_POS = SynchedEntityData.<BlockPos>defineId(FlyeEntity.class, EntityDataSerializers.BLOCK_POS);
	private BlockPos altarPos = null;

	public FlyeEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);
	}

	public FlyeEntity(Level world, BlockPos altarPos) {
		this(AoAMobs.FLYE.get(), world);

		this.entityData.set(ALTAR_POS, altarPos);

		BlockPos spawnPos;

		do {
			spawnPos = RandomUtil.getRandomPositionWithinRange(altarPos, 20, 20, 20);
		}
		while (world.getBlockState(spawnPos).getMaterial().blocksMotion());

		setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
		EntityUtil.applyPotions(this, new EffectBuilder(MobEffects.GLOWING, PotionUtil.MAX_POTION_DURATION).isAmbient().hideParticles());
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		entityData.define(ALTAR_POS, BlockPos.ZERO);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 1.375f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_FLYE_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_FLYE_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_FLYE_HURT.get();
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		super.onSyncedDataUpdated(key);

		if (key == ALTAR_POS) {
			altarPos = entityData.get(ALTAR_POS);

			if (altarPos == BlockPos.ZERO)
				altarPos = null;
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		if (altarPos != null)
			compound.putLong("GrawAltarPos", altarPos.asLong());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("GrawAltarPos"))
			altarPos = BlockPos.of(compound.getLong("GrawAltarPos"));
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (!level.isClientSide && altarPos != null && level.getGameTime() % 40 == 0 && !altarPos.closerThan(blockPosition(), 30)) {
			double posX = ((altarPos.getX() + random.nextFloat() * 2f - 1f) * 10f);
			double posY = ((altarPos.getY() + random.nextFloat() * 2f - 1f) * 10f);
			double posZ = ((altarPos.getZ() + random.nextFloat() * 2f - 1f) * 10f);

			getMoveControl().setWantedPosition(posX, posY, posZ, 1d);
		}
	}

	@Override
	public int getTeamColor() {
		if (getGrawAltarPos() != null)
			return ColourUtil.GREEN;

		return super.getTeamColor();
	}

	@Nullable
	public BlockPos getGrawAltarPos() {
		return altarPos;
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			if (WorldUtil.isWorld(level, AoADimensions.LELYETIA.key) && DamageUtil.isMeleeDamage(cause) && cause.getEntity() instanceof Player) {
				Player pl = (Player)cause.getEntity();

				if (pl.getY() >= 120 && ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
					ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.HAVEN_REALMSTONE.get()));
			}

			if (altarPos != null && lastHurtByPlayerTime > 0)
				spawnAtLocation(new ItemStack(AoAItems.GUARDIANS_EYE.get()), 0);
		}
	}
}
