package net.nevermine.minion.entity;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.fx.trail.OrangeTrail;
import net.nevermine.fx.trail.WhiteTrail;
import net.nevermine.izer.Itemizer;

public class EntityAlluricorn extends EntityTameable {
	public EntityAlluricorn(final World par1World) {
		super(par1World);
		setSize(1.0f, 1.0f);
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
		targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IMob.mobSelector));
	}

	public boolean attackEntityAsMob(final Entity par1Entity) {
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 13.0f);
	}

	public double getMoveSpeed() {
		return getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
	}

	public boolean isAIEnabled() {
		return true;
	}

	public EntityAlluricorn(final World par1World, final EntityPlayer p) {
		this(par1World);
		func_152115_b(p.getUniqueID().toString());
		setTamed(true);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5199999809265137);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0);
	}

	public boolean interact(final EntityPlayer par1EntityPlayer) {
		final ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		if (var2 != null && var2.getItem() == Items.cookie) {
			heal(20.0f);
			playLivingSound();
			final ItemStack itemStack = var2;
			--itemStack.stackSize;
		}
		if (isTamed()) {
			if (var2 != null && var2.getItem() == Itemizer.KineticRune) {
				par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 150, 3));
				par1EntityPlayer.inventory.consumeInventoryItem(Itemizer.KineticRune);
			}
		}
		else {
			setTamed(true);
			func_152115_b(par1EntityPlayer.getUniqueID().toString());
		}
		return super.interact(par1EntityPlayer);
	}

	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			for (int var3 = 0; var3 < 8; ++var3) {
				final OrangeTrail var4 = new OrangeTrail(worldObj, posX, posY - 1.5, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var4);
				final WhiteTrail var5 = new WhiteTrail(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var5);
				final OrangeTrail var6 = new OrangeTrail(worldObj, posX, posY + 1.5, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var6);
				final OrangeTrail var7 = new OrangeTrail(worldObj, posX, posY + 0.5, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var7);
				final OrangeTrail var8 = new OrangeTrail(worldObj, posX, posY - 0.5, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var8);
				final WhiteTrail var9 = new WhiteTrail(worldObj, posX, posY + 1.0, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var9);
				final WhiteTrail var10 = new WhiteTrail(worldObj, posX, posY - 1.0, posZ, 0.0, 0.0, 0.0, 5);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var10);
			}
		}
	}

	public EntityAgeable createChild(final EntityAgeable var1) {
		return null;
	}

	protected String getLivingSound() {
		return "nevermine:RainicornLiving";
	}

	protected String getDeathSound() {
		return "nevermine:RainicornDeath";
	}

	protected String getHurtSound() {
		return "nevermine:RainicornHit";
	}
}
