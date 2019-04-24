package net.nevermine.minion.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityDemonicWarrior extends EntityMob {
	private ChunkCoordinates currentFlightTarget;
	private float field_70926_e;
	private float field_70924_f;
	private boolean isShaking;
	private boolean field_70928_h;
	private float timeWolfIsShaking;
	private float prevTimeWolfIsShaking;
	private static final ItemStack defaultHeldItem;

	public EntityDemonicWarrior(final World var1) {
		super(var1);
		final float moveSpeed = 0.56f;
		setSize(1.0f, 2.0f);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3f));
		tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0, true));
		tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.3, 32.0f));
		tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6, true));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0f));
		tasks.addTask(8, new EntityAILookIdle(this));
		tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0f));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IMob.mobSelector));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5199999809265137);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(25.0);
	}

	public ItemStack getHeldItem() {
		return EntityDemonicWarrior.defaultHeldItem;
	}

	public boolean isAIEnabled() {
		return true;
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

	public boolean canBePushed() {
		return true;
	}

	protected void collideWithEntity(final Entity par1Entity) {
		if (par1Entity instanceof IMob && getRNG().nextInt(20) == 0) {
			setAttackTarget((EntityLivingBase)par1Entity);
		}
		super.collideWithEntity(par1Entity);
	}

	protected void fall(final float par1) {
	}

	protected void updateFallState(final double par1, final boolean par3) {
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final int par2) {
		return !isEntityInvulnerable() && super.attackEntityFrom(par1DamageSource, (float)par2);
	}

	static {
		defaultHeldItem = new ItemStack(Weaponizer.RositeSword, 1);
	}
}
