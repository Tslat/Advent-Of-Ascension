package net.tslat.aoa3.entity.boss.baroness;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;

public class EntityBaronBomb extends Entity {
	private int timer = 150;

	public EntityBaronBomb(EntityBaroness baroness) {
		this(baroness.world);

		setPositionAndUpdate(baroness.posX, baroness.posY, baroness.posZ);
	}

	@Override
	public float getEyeHeight() {
		return 0.25f;
	}

	public EntityBaronBomb(World world) {
		super(world);
		setSize(1f, 0.8125f);
	}

	@Override
	public void addVelocity(double x, double y, double z) {}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void entityInit() {}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {
		if (!world.isRemote) {
			world.createExplosion(this, posX, posY, posZ, 7f, false);
			setDead();
		}
	}

	@Override
	public boolean hitByEntity(Entity attacker) {
		if (!world.isRemote) {
			world.createExplosion(this, posX, posY, posZ, 7f, false);
			setDead();
		}

		return true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!world.isRemote) {
			timer--;

			if (timer == 16) {
				world.playSound(null, posX, posY, posZ, SoundsRegister.baronBombPriming, SoundCategory.HOSTILE, 1.0f, 1.0f);
			} else if (timer == 0) {
				world.createExplosion(this, posX, posY, posZ, 7f, false);

				setDead();
			}
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}
}
