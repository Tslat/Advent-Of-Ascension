package net.nevermine.npc.auxil;

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
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.fx.circular.RedCircular;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.placement.EntityNoRange;
import net.nevermine.mob.placement.EntityObject;
import net.nevermine.skill.butchery.butcheryHelper;

import static net.nevermine.container.PlayerContainer.Skills.Butchery;

public class EntityBloodlust extends EntityAnimal implements EntityNoRange, EntityObject {
	public EntityBloodlust(final World par1World) {
		super(par1World);
		setSize(0.5f, 0.5f);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.55));
		tasks.addTask(3, new EntityAITempt(this, 0.9, Items.wheat, false));
		tasks.addTask(5, new EntityAIWander(this, 0.55));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0f));
		tasks.addTask(7, new EntityAILookIdle(this));
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
		return null;
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
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.40000000298023225);
	}

	public boolean isEntityInvulnerable() {
		return true;
	}

	public boolean interact(final EntityPlayer player) {
		final ItemStack var2 = player.inventory.getCurrentItem();
		if (!player.worldObj.isRemote && var2 != null && var2.getItem() == Itemizer.BloodAccumulator) {
			player.worldObj.playSoundAtEntity(player, "nevermine:BloodlustCollect", 1.85f, 1.0f);
			PlayerContainer cont = PlayerContainer.getProperties(player);

			cont.addExperience(cont.getExpRequired(Butchery) / butcheryHelper.getExpDenominator(cont.getLevel(Butchery)), Butchery);
			setDead();
		}
		return super.interact(player);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (ticksExisted == 200) {
			setDead();
		}
	}

	public EntityAgeable createChild(final EntityAgeable var1) {
		return new EntityBloodlust(worldObj);
	}

	protected void func_145780_a(int posX, int posY, int posZ, Block block) {
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 8; ++var3) {
				final RedCircular var4 = new RedCircular(worldObj, posX, posY + 0.5, posZ, 0.0, 0.0, 0.0, 25);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
			}
		}
	}
}
