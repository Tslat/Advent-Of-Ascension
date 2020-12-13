package net.tslat.aoa3.entity.mob.lelyetia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.util.*;

import javax.annotation.Nullable;

public class FlyeEntity extends AoAFlyingMeleeMob {
	private static final DataParameter<BlockPos> ALTAR_POS = EntityDataManager.<BlockPos>createKey(FlyeEntity.class, DataSerializers.BLOCK_POS);
	private BlockPos altarPos = null;

	public FlyeEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	public FlyeEntity(World world, BlockPos altarPos) {
		this(AoAEntities.Mobs.FLYE.get(), world);

		this.dataManager.set(ALTAR_POS, altarPos);

		BlockPos spawnPos;

		do {
			spawnPos = RandomUtil.getRandomPositionWithinRange(altarPos, 20, 20, 20);
		}
		while (world.getBlockState(spawnPos).getMaterial().blocksMovement());

		setPosition(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
		EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.GLOWING, PotionUtil.MAX_POTION_DURATION).isAmbient().hideParticles());
	}

	@Override
	protected void registerData() {
		super.registerData();

		dataManager.register(ALTAR_POS, BlockPos.ZERO);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.2f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 50;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
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
	public void notifyDataManagerChange(DataParameter<?> key) {
		super.notifyDataManagerChange(key);

		if (key == ALTAR_POS) {
			altarPos = dataManager.get(ALTAR_POS);

			if (altarPos == BlockPos.ZERO)
				altarPos = null;
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);

		if (altarPos != null)
			compound.putLong("GrawAltarPos", altarPos.toLong());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);

		if (compound.contains("GrawAltarPos"))
			altarPos = BlockPos.fromLong(compound.getLong("GrawAltarPos"));
	}

	@Override
	public void livingTick() {
		super.livingTick();

		if (!world.isRemote && altarPos != null && world.getGameTime() % 40 == 0 && !altarPos.withinDistance(getPosition(), 30)) {
			double posX = ((altarPos.getX() + rand.nextFloat() * 2f - 1f) * 10f);
			double posY = ((altarPos.getY() + rand.nextFloat() * 2f - 1f) * 10f);
			double posZ = ((altarPos.getZ() + rand.nextFloat() * 2f - 1f) * 10f);

			getMoveHelper().setMoveTo(posX, posY, posZ, 1d);
		}
	}

	@Override
	public int getTeamColor() {
		if (getGrawAltarPos() != null)
			return NumberUtil.RGB(0, 255, 0);

		return super.getTeamColor();
	}

	@Nullable
	public BlockPos getGrawAltarPos() {
		return altarPos;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			if (world.getDimension().getType() == AoADimensions.LELYETIA.type() && DamageUtil.isMeleeDamage(cause) && cause.getTrueSource() instanceof PlayerEntity) {
				PlayerEntity pl = (PlayerEntity)cause.getTrueSource();

				if (pl.getPosY() >= 80 && ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
					ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.HAVEN_REALMSTONE.get()));
			}

			if (altarPos != null && recentlyHit > 0)
				entityDropItem(new ItemStack(AoAItems.GUARDIANS_EYE.get()), 0);
		}
	}
}
