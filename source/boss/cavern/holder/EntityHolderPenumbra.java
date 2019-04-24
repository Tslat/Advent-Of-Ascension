package net.nevermine.boss.cavern.holder;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import net.nevermine.boss.cavern.CavernBoss;
import net.nevermine.boss.cavern.EntityPenumbra;

public class EntityHolderPenumbra extends EntityAnimal implements CavernBoss {
	private int countDown;

	public EntityHolderPenumbra(final World par1World) {
		super(par1World);
		countDown = 700;
		setSize(0.1f, 0.1f);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.55));
		tasks.addTask(3, new EntityAITempt(this, 0.9, Items.wheat, false));
		tasks.addTask(5, new EntityAIWander(this, 0.55));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0f));
		tasks.addTask(7, new EntityAILookIdle(this));
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected String getLivingSound() {
		return "nevermine:Holder";
	}

	protected String getHurtSound() {
		return null;
	}

	protected String getDeathSound() {
		return null;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000.0);
	}

	public boolean isEntityInvulnerable() {
		return true;
	}

	public EntityAgeable createChild(final EntityAgeable var1) {
		return new EntityHolderPenumbra(worldObj);
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		--countDown;
		if (countDown == 0) {
			if (!worldObj.isRemote) {
				final EntityPenumbra var2 = new EntityPenumbra(worldObj);
				var2.setLocationAndAngles(posX, posY + 2, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var2);
				playSound("nevermine:PenumbraSpawn", 0.85f, 1.0f);
			}
			setDead();
		}
	}
}
