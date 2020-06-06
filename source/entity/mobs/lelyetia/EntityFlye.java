package net.tslat.aoa3.entity.mobs.lelyetia;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;

public class EntityFlye extends AoAFlyingMeleeMob {
	public static final float entityWidth = 1.75f;
	private static final DataParameter<BlockPos> ALTAR_POS = EntityDataManager.<BlockPos>createKey(EntityFlye.class, DataSerializers.BLOCK_POS);
	private BlockPos altarPos = null;

	public EntityFlye(World world, BlockPos altarPos) {
		this(world);

		this.dataManager.set(ALTAR_POS, altarPos);

		BlockPos spawnPos;

		do {
			spawnPos = new BlockPos(altarPos.getX() + rand.nextDouble() * 40 - 20, altarPos.getY() + rand.nextDouble() * 40 - 20, altarPos.getZ() + rand.nextDouble() * 40 - 20);
		}
		while (world.getBlockState(spawnPos).getMaterial().blocksMovement());

		setPosition(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());

		addPotionEffect(new PotionEffect(MobEffects.GLOWING, 9999999, 0, true, false));
	}

	public EntityFlye(World world) {
		super(world, entityWidth, 1.75f);
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		dataManager.register(ALTAR_POS, BlockPos.ORIGIN);
	}

	@Override
	public float getEyeHeight() {
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
		return SoundsRegister.MOB_FLYE_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_FLYE_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_FLYE_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityFlye;
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		super.notifyDataManagerChange(key);

		if (key == ALTAR_POS) {
			altarPos = dataManager.get(ALTAR_POS);

			if (altarPos == BlockPos.ORIGIN)
				altarPos = null;
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		if (altarPos != null)
			compound.setLong("GrawAltarPos", altarPos.toLong());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		if (compound.hasKey("GrawAltarPos"))
			altarPos = BlockPos.fromLong(compound.getLong("GrawAltarPos"));
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();

		if (!world.isRemote && altarPos != null && world.getTotalWorldTime() % 40 == 0 && altarPos.getDistance((int)posX, (int)posY, (int)posZ) > 30) {
			double posX = ((altarPos.getX() + rand.nextFloat() * 2f - 1f) * 10f);
			double posY = ((altarPos.getY() + rand.nextFloat() * 2f - 1f) * 10f);
			double posZ = ((altarPos.getZ() + rand.nextFloat() * 2f - 1f) * 10f);

			getMoveHelper().setMoveTo(posX, posY, posZ, 1d);
		}
	}

	@Nullable
	public BlockPos getGrawAltarPos() {
		return altarPos;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			if (world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.lelyetia && EntityUtil.isMeleeDamage(cause) && cause.getTrueSource() instanceof EntityPlayer) {
				EntityPlayer pl = (EntityPlayer)cause.getTrueSource();

				if (pl.posY >= 80 && ItemUtil.consumeItem(pl, new ItemStack(ItemRegister.BLANK_REALMSTONE)))
					ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(ItemRegister.HAVEN_REALMSTONE));
			}

			if (altarPos != null && recentlyHit > 0)
				entityDropItem(new ItemStack(ItemRegister.GUARDIANS_EYE), 0);
		}
	}
}
