package net.nevermine.mob.entity.overworld;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class EntityPolarUrsa extends EntityMob {
	public EntityPolarUrsa(final World par1World) {
		super(par1World);
		final float moveSpeed = 0.46f;
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, (double)moveSpeed, false));
		tasks.addTask(6, new EntityAIWander(this, (double)moveSpeed));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0f));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(64.0);
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3f));
		setSize(1.4f, 1.6f);
	}

	public void onDeath(final DamageSource var1) {
		if (!worldObj.isRemote) {
			if (var1.isFireDamage() || isBurning()) {
				dropItem(Itemizer.UrsaCooked, 2);
			}
			else {
				dropItem(Itemizer.UrsaRaw, 2);
			}
		}
		super.onDeath(var1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte)0));
	}

	public void onUpdate() {
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((entityToAttack != null) ? 0.5 : 0.5);
		if (!worldObj.isRemote) {
			setBesideClimbableBlock(isCollidedHorizontally);
		}
		super.onUpdate();
	}

	public boolean isBesideClimbableBlock() {
		return (dataWatcher.getWatchableObjectByte(16) & 0x1) != 0x0;
	}

	public void setBesideClimbableBlock(final boolean par1) {
		byte b0 = dataWatcher.getWatchableObjectByte(16);
		if (par1) {
			b0 |= 0x1;
		}
		else {
			b0 &= 0xFFFFFFFE;
		}
		dataWatcher.updateObject(16, b0);
	}

	protected String getLivingSound() {
		return "nevermine:UrsaLiving";
	}

	protected String getDeathSound() {
		return "nevermine:UrsaDeath";
	}

	protected String getHurtSound() {
		return "nevermine:UrsaHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:HeavyStep", 0.85f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.45);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
	}
}
