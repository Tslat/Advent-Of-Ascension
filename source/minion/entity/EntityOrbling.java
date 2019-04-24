package net.nevermine.minion.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.nevermine.assist.AddPackets;
import net.nevermine.event.player.HealthMessage;

public class EntityOrbling extends EntityTameable {
	public EntityOrbling(final World par1World) {
		super(par1World);
		setSize(1.0f, 1.0f);
	}

	public double getMoveSpeed() {
		return getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
	}

	public boolean isAIEnabled() {
		return true;
	}

	public EntityOrbling(final World par1World, final EntityPlayer p) {
		this(par1World);
		func_152115_b(p.getUniqueID().toString());
		setTamed(true);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
	}

	public boolean interact(final EntityPlayer par1EntityPlayer) {
		if (isTamed()) {
			if (par1EntityPlayer == getOwner()) {
				par1EntityPlayer.heal(5.0f);
			}
			setDead();
		}
		else {
			setTamed(true);
			func_152115_b(par1EntityPlayer.getUniqueID().toString());
		}
		return super.interact(par1EntityPlayer);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		motionY = 0.0;
		final EntityLivingBase play = getOwner();
		if (play == null) {
			setDead();
		}
		if (play != null) {
			if (play.getDistanceToEntity(this) < 8.0f && play.getHealth() > 0) {
				if (play instanceof EntityPlayerMP)
					AddPackets.network.sendTo(new HealthMessage(play.getHealth() + 0.025f), (EntityPlayerMP)play);

				play.setHealth(play.getHealth() + 0.025f);
			}

			setHealth(getHealth() - 0.05f);
		}
	}

	public EntityAgeable createChild(final EntityAgeable var1) {
		return null;
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getDeathSound() {
		return null;
	}

	protected String getHurtSound() {
		return null;
	}
}
