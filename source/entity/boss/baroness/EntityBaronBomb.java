package net.tslat.aoa3.entity.boss.baroness;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.WorldUtil;

import java.util.UUID;

public class EntityBaronBomb extends Entity {
	private int timer = 150;
	private UUID ownerUUID = null;
	private EntityPlayer ownerPlayer = null;
	private EntityBaroness baroness = null;

	public EntityBaronBomb(EntityPlayer pl) {
		this(pl.world);

		ownerUUID = pl.getUniqueID();
		setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
	}

	public EntityBaronBomb(EntityBaroness baroness) {
		this(baroness.world);

		this.baroness = baroness;

		setPositionAndUpdate(baroness.posX, baroness.posY, baroness.posZ);
	}

	@Override
	public float getEyeHeight() {
		return 0.25f;
	}

	public 	EntityBaronBomb(World world) {
		super(world);

		setSize(1f, 0.8125f);
	}

	@Override
	public void addVelocity(double x, double y, double z) {}

	@Override
	public boolean canBePushed() {
		return !isDead;
	}

	@Override
	protected void entityInit() {}

	@Override
	public void onCollideWithPlayer(EntityPlayer entity) {
		if (!world.isRemote && checkEntityCollision(entity)) {
			if (ownerUUID == null) {
				WorldUtil.createExplosion(baroness, world, posX, posY + 1, posZ, 3.5f, WorldUtil.checkGameRule(world, "doStrongerMobGriefing"), false);
			}
			else {
				WorldUtil.createExplosion(ownerPlayer, world, posX, posY + 1, posZ, 2f);
			}

			setDead();
		}
	}

	@Override
	public boolean canBeCollidedWith() {
		return !isDead;
	}

	@Override
	public void applyEntityCollision(Entity entity) {
		if (!world.isRemote && checkEntityCollision(entity)) {
			if (ownerPlayer != null) {
				WorldUtil.createExplosion(ownerPlayer, world, posX, posY + 1, posZ, 2f);
			}
			else {
				WorldUtil.createExplosion(baroness, world, posX, posY + 1, posZ, 3.5f, WorldUtil.checkGameRule(world, "doStrongerMobGriefing"), false);
			}

			setDead();
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!world.isRemote) {
			setDead();

			if (source.isExplosion() && source.getTrueSource() != baroness && source.getTrueSource() != ownerPlayer) {
				if (ownerPlayer != null) {
					WorldUtil.createExplosion(ownerPlayer, world, posX, posY + 1, posZ, 2f);
				}
				else {
					WorldUtil.createExplosion(baroness, world, posX, posY + 1, posZ, 3.5f, WorldUtil.checkGameRule(world, "doStrongerMobGriefing"), false);
				}
			}

			return false;
		}

		return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean hitByEntity(Entity attacker) {
		if (!world.isRemote && checkEntityCollision(attacker))
			setDead();

		return true;
	}

	private boolean checkEntityCollision(Entity target) {
		if (ownerUUID == null) {
			return !(target instanceof EntityBaroness);
		}
		else {
			if (target instanceof EntityPlayer) {
				updateOwner();

				if (ownerPlayer != null && !EntityUtil.canPvp(ownerPlayer, (EntityPlayer)target))
					return false;
			}
			else if (target instanceof EntityTameable) {
				UUID ownerUUID = ((EntityTameable)target).getOwnerId();

				if (ownerUUID != null && ownerUUID.equals(this.ownerUUID))
					return false;
			}
		}

		return true;
	}

	private void updateOwner() {
		ownerPlayer = ownerPlayer == null ? world.getMinecraftServer().getPlayerList().getPlayerByUUID(ownerUUID) : ownerPlayer;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		BlockPos pos = new BlockPos(posX, 0, posZ);

		if (!world.isRemote || world.isBlockLoaded(pos) && world.getChunk(pos).isLoaded()) {
			if (!hasNoGravity())
				motionY -= 0.08D;
		}
		else if (this.posY > 0.0D) {
			motionY = -0.1D;
		}
		else {
			motionY = 0.0D;
		}

		motionX = 0;
		motionZ = 0;

		move(MoverType.SELF, motionX, motionY, motionZ);

		if (!world.isRemote) {
			if (ownerUUID == null && world.getDifficulty() == EnumDifficulty.PEACEFUL) {
				setDead();

				return;
			}

			timer--;

			if (timer == 16) {
				world.playSound(null, posX, posY, posZ, SoundsRegister.baronBombPriming, SoundCategory.HOSTILE, 1.0f, 1.0f);
			}
			else if (timer <= 0) {
				updateOwner();

				if (ownerPlayer != null) {
					WorldUtil.createExplosion(ownerPlayer, world, posX, posY + 1, posZ, 2f);
				}
				else {
					WorldUtil.createExplosion(baroness, world, posX, posY + 1, posZ, 4f);
				}

				setDead();
			}
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}
}
