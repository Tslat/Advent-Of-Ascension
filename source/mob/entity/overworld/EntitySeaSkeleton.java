package net.nevermine.mob.entity.overworld;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.equipment.Weaponizer;

public class EntitySeaSkeleton extends EntityMob implements IRangedAttackMob {
	private ChunkCoordinates currentFlightTarget;
	private int flyTimer;
	private static final ItemStack defaultHeldItem;
	private EntityAIArrowAttack aiArrowAttack;

	public EntitySeaSkeleton(final World var1) {
		super(var1);
		aiArrowAttack = new EntityAIArrowAttack(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 10, 50, 11.0f);
		final double moveSpeed = 0.3499999940395355;
		tasks.addTask(7, new EntityAIArrowAttack(this, 0.25, 20, 64.0f));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(3, new EntityAIFleeSun(this, moveSpeed));
		tasks.addTask(5, new EntityAIWander(this, moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		flyTimer = 0;
		setSize(0.6f, 2.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3499999940395355);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95f;
	}

	public ItemStack getHeldItem() {
		return EntitySeaSkeleton.defaultHeldItem;
	}

	public void attackEntityWithRangedAttack(final EntityLivingBase var1, final float f) {
		final EntityArrow var2 = new EntityArrow(worldObj, this, var1, 1.6f, 12.0f);
		var2.setDamage(4.0);
		playSound("random.bow", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		worldObj.spawnEntityInWorld(var2);
	}

	protected String getLivingSound() {
		return "mob.skeleton.say";
	}

	protected String getHurtSound() {
		return "mob.skeleton.hurt";
	}

	protected String getDeathSound() {
		return "mob.skeleton.death";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.skeleton.step", 0.15f, 1.0f);
	}

	static {
		defaultHeldItem = new ItemStack(Weaponizer.SpeedBow, 1);
	}
}
