package net.nevermine.mob.entity.creeponia;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class EntityCreepCow extends EntityAnimal {
	public EntityCreepCow(final World par1World) {
		super(par1World);
		setSize(0.9f, 1.3f);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.55));
		tasks.addTask(3, new EntityAITempt(this, 0.9, Items.wheat, false));
		tasks.addTask(5, new EntityAIWander(this, 0.55));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0f));
		tasks.addTask(7, new EntityAILookIdle(this));
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && rand.nextInt(13) == 1 && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox) && rand.nextInt(15) == 9;
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected String getLivingSound() {
		return "mob.cow.say";
	}

	protected String getHurtSound() {
		return "mob.cow.hurt";
	}

	protected String getDeathSound() {
		return "mob.cow.hurt";
	}

	public void onDeath(final DamageSource var1) {
		if (!worldObj.isRemote) {
			if (var1.isFireDamage() || isBurning()) {
				dropItem(Itemizer.HalyconBeef, 2);
			}
			else {
				dropItem(Itemizer.HalyconBeefRaw, 2);
			}
		}
		super.onDeath(var1);
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.cow.step", 0.55f, 1.0f);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.40000000298023225);
	}

	public boolean interact(final EntityPlayer par1EntityPlayer) {
		final ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
		if (itemstack != null && itemstack.getItem() == Items.bucket && !par1EntityPlayer.capabilities.isCreativeMode) {
			if (rand.nextInt(3) == 2) {
				worldObj.createExplosion(this, posX, posY, posZ, 1.5f, false);
			}
			if (itemstack.stackSize-- == 1) {
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, new ItemStack(Itemizer.BucketHalyconMilk));
			}
			else if (!par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Itemizer.BucketHalyconMilk))) {
				par1EntityPlayer.dropPlayerItemWithRandomChoice(new ItemStack(Itemizer.BucketHalyconMilk, 1, 0), false);
			}
			return true;
		}
		return super.interact(par1EntityPlayer);
	}

	protected boolean canDespawn() {
		return true;
	}

	public EntityAgeable createChild(final EntityAgeable var1) {
		return new EntityCreepCow(worldObj);
	}
}
