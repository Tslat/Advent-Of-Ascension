package net.nevermine.mob.entity.mysterium;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityEeoRunning extends EntityMob {
	private int cooldown;
	private EntityAITempt aiTempt;
	private static final String __OBFID = "CL_00001646";

	public EntityEeoRunning(final World par1World) {
		super(par1World);
		cooldown = 200;
		setSize(0.6f, 2.0f);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.6, 32.0f));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.6));
		tasks.addTask(4, new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0f, 0.8, 1.33));
		tasks.addTask(10, new EntityAIWander(this, 0.8));
		tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0f));
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(65) == 32) {
			dropItem(Weaponizer.DoomBringer, 1);
		}
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.40000001192092893);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		--cooldown;
		if (cooldown == 1) {
			transform();
		}
	}

	private void transform() {
		if (!worldObj.isRemote) {
			final EntityEeo var2 = new EntityEeo(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			var2.setHealth(getHealth());
			worldObj.spawnEntityInWorld(var2);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
	}

	protected String getLivingSound() {
		return "nevermine:HunchLiving";
	}

	protected String getDeathSound() {
		return "nevermine:HunchDeath";
	}

	protected String getHurtSound() {
		return "nevermine:HunchHit";
	}
}
