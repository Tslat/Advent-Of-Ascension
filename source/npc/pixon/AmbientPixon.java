package net.nevermine.npc.pixon;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.event.creature.PixonCollection;
import net.nevermine.fx.trail.WhiteTrail;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.placement.EntityNoRange;
import net.nevermine.mob.placement.EntityObject;

public class AmbientPixon extends EntityAnimal implements EntityNoRange, EntityObject {
	public AmbientPixon(final World par1World) {
		super(par1World);
		setSize(0.9f, 1.3f);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.55));
		tasks.addTask(3, new EntityAITempt(this, 0.9, Items.wheat, false));
		tasks.addTask(5, new EntityAIWander(this, 0.55));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0f));
		tasks.addTask(7, new EntityAILookIdle(this));
		isImmuneToFire = true;
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected boolean canDespawn() {
		return true;
	}

	protected String getLivingSound() {
		return "nevermine:Pixon";
	}

	protected String getHurtSound() {
		return null;
	}

	protected String getDeathSound() {
		return null;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		final int add = rand.nextInt(5);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(19.0 + 5 * add);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.40000000298023225);
	}

	public boolean isEntityInvulnerable() {
		return true;
	}

	public boolean interact(final EntityPlayer par1EntityPlayer) {
		PixonCollection.collect(this, par1EntityPlayer, 40, 20, Itemizer.iStoneAmbient);
		return super.interact(par1EntityPlayer);
	}

	@Override
	public float getSoundVolume() {
		return 0.10f;
	}

	protected void func_145780_a(int posX, int posY, int posZ, Block block) {
	}

	public EntityAgeable createChild(final EntityAgeable var1) {
		return new AmbientPixon(worldObj);
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 8; ++var3) {
				final WhiteTrail var4 = new WhiteTrail(worldObj, posX, posY + 1.0, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
	}
}
