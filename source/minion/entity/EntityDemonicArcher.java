package net.nevermine.minion.entity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityDemonicArcher extends EntityTameable implements IRangedAttackMob {
	private static final ItemStack defaultHeldItem;

	public EntityDemonicArcher(final World par1World, final EntityPlayer par2EntityPlayer) {
		this(par1World);
		setTamed(true);
		func_152115_b(par2EntityPlayer.getDisplayName());
	}

	public EntityDemonicArcher(final World par1World) {
		super(par1World);
		setSize(1.0f, 2.0f);
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
		return EntityDemonicArcher.defaultHeldItem;
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

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		final EntityArrow var2 = new EntityArrow(worldObj, this, var1, 1.6f, 12.0f);
		var2.setDamage(3.0);
		final int var3 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, getHeldItem());
		final int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, getHeldItem());
		if (var3 > 0) {
			var2.setDamage(var2.getDamage() + var3 * 0.5 + 0.5);
		}
		if (var4 > 0) {
			var2.setKnockbackStrength(var4);
		}
		var2.setFire(4);
		playSound("random.bow", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(var2);
	}

	static {
		defaultHeldItem = new ItemStack(Weaponizer.DemonBow, 1);
	}
}
