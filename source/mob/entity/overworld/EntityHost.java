package net.nevermine.mob.entity.overworld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.boss.EntityBoss;
import net.nevermine.event.dimensional.overworld.CreepDayEvent;
import net.nevermine.izer.Itemizer;

public class EntityHost extends EntityMob implements EntityBoss {
	public EntityHost(final World par1World) {
		super(par1World);
		setSize(3.5f, 2.0f);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.55));
		tasks.addTask(3, new EntityAITempt(this, 0.9, Items.wheat, false));
		tasks.addTask(5, new EntityAIWander(this, 0.55));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0f));
		tasks.addTask(7, new EntityAILookIdle(this));
	}

	protected String getLivingSound() {
		return "nevermine:HostLiving";
	}

	protected String getDeathSound() {
		return "nevermine:HostDeath";
	}

	protected String getHurtSound() {
		return "nevermine:HostLiving";
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && CreepDayEvent.isCreep() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.RealmstoneCreeponia, 1);
		dropItem(Items.gunpowder, 32);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (rand.nextInt(10) == 3) {
			motionY += 0.30000001192092896;
			fallDistance--;
		}
		final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 20.0);
		if (var1 == null || var1.getDistanceToEntity(this) > 20.0f) {
			return;
		}
		if (rand.nextInt(80) == 34 && !worldObj.isRemote) {
			worldObj.playSoundAtEntity(this, "nevermine:HostDrop", 2.0f, 1.0f);
			final EntityCreeper var2 = new EntityCreeper(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
			worldObj.spawnEntityInWorld(var2);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(230.0);
	}
}
