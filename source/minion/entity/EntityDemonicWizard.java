package net.nevermine.minion.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.staff.EntityStaffFire;

public class EntityDemonicWizard extends EntityTameable implements IRangedAttackMob {
	private static final ItemStack defaultHeldItem;

	public EntityDemonicWizard(final World par1World, final EntityPlayer par2EntityPlayer) {
		this(par1World);
		setTamed(true);
		func_152115_b(par2EntityPlayer.getDisplayName());
	}

	public EntityDemonicWizard(final World par1World) {
		super(par1World);
		setSize(2.0f, 2.0f);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4f));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, getMoveSpeed(), true));
		tasks.addTask(5, new EntityAIFollowOwner(this, getMoveSpeed(), 10.0f, 2.0f));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		tasks.addTask(5, new EntityAIAttackOnCollide(this, EntityPlayer.class, getMoveSpeed(), false));
		targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	public double getMoveSpeed() {
		return getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5199999809265137);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
	}

	protected String getLivingSound() {
		return "nevermine:FiendLiving";
	}

	protected String getDeathSound() {
		return "nevermine:FiendDeath";
	}

	protected String getHurtSound() {
		return "nevermine:FiendHit";
	}

	public ItemStack getHeldItem() {
		return EntityDemonicWizard.defaultHeldItem;
	}

	public boolean interact(final EntityPlayer par1EntityPlayer) {
		final ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		if (isTamed()) {
			if (var2 != null && var2.getItem() instanceof ItemFood) {
				final ItemFood var3 = (ItemFood)var2.getItem();
				if (var3.isWolfsFavoriteMeat() && dataWatcher.getWatchableObjectInt(18) < 20) {
					if (!par1EntityPlayer.capabilities.isCreativeMode) {
						final ItemStack itemStack = var2;
						--itemStack.stackSize;
					}
					heal((float)var3.func_150905_g(var2));
					if (var2.stackSize <= 0) {
						par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, null);
					}
					return true;
				}
			}
		}
		else {
			setTamed(true);
			func_152115_b(par1EntityPlayer.getDisplayName());
		}
		return super.interact(par1EntityPlayer);
	}

	public EntityAgeable createChild(final EntityAgeable var1) {
		return null;
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase entityliving, final float f) {
		final EntityStaffFire var2 = new EntityStaffFire(worldObj, this, 8.0f, 0, 0.075f);
		final double var3 = entityliving.posX - posX;
		final double var4 = entityliving.posY + entityliving.getEyeHeight() - 1.100000023841858 - var2.posY;
		final double var5 = entityliving.posZ - posZ;
		final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
		var2.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
		playSound("nevermine:Wizard", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(var2);
	}

	static {
		defaultHeldItem = new ItemStack(Weaponizer.FireStaff, 1);
	}
}
