package net.nevermine.minion.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityRosid extends EntityMob {
	public EntityRosid(final World var1) {
		super(var1);
		setSize(1.0f, 1.3f);
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
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
	}

	public boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		setHealth(getHealth() - 0.1f);
	}

	@Override
	public boolean canAttackClass(Class cl) {
		return cl != EntityShadowStalker.class && cl != EntityRosid.class && cl != EntityHiveSoldier.class;
	}

	protected String getLivingSound() {
		return "mob.bat.hurt";
	}

	protected String getHurtSound() {
		return "mob.bat.hurt";
	}

	protected String getDeathSound() {
		return "mob.bat.death";
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
}
