package net.nevermine.mob.entity.runandor.templar;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.placement.*;

public class EntityRuneTemplarBlueDisabled extends EntityCreature implements EntityNoRange, EntityNoBows, EntityNoMelee, EntityNoMagic, EntityNoFire, EntityNoExplosions, EntityObject {
	public EntityRuneTemplarBlueDisabled(final World par1World) {
		super(par1World);
		setSize(1.79f, 2.2f);
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getDeathSound() {
		return "random.anvil_land";
	}

	protected String getHurtSound() {
		return "random.anvil_land";
	}

	public void addVelocity(final double x, final double y, final double z) {
	}

	protected boolean canDespawn() {
		return false;
	}

	public boolean canBePushed() {
		return false;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean interact(final EntityPlayer par1EntityPlayer) {
		final ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		if (par1EntityPlayer.inventory.hasItem(Itemizer.RunicEnergy) && par1EntityPlayer.inventory.hasItem(Itemizer.WaterRune) && par1EntityPlayer.inventory.consumeInventoryItem(Itemizer.WaterRune) && par1EntityPlayer.inventory.consumeInventoryItem(Itemizer.RunicEnergy) && !worldObj.isRemote) {
			final EntityRuneTemplarBlue var3 = new EntityRuneTemplarBlue(worldObj);
			var3.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var3);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
		return super.interact(par1EntityPlayer);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0);
	}

	public boolean isEntityInvulnerable() {
		return true;
	}
}
