package net.nevermine.mob.entity.night;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.ai.EntityAIHop;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityScrubby extends EntityMob {
	public EntityScrubby(final World par1World) {
		super(par1World);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(3, new EntityAIHop(this, 0.9f));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.9f, false));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 0.9f, true));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.9f));
		tasks.addTask(6, new EntityAIMoveThroughVillage(this, 0.9f, false));
		tasks.addTask(7, new EntityAIWander(this, 1.0));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(8, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
		setSize(1.0f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.getCurrentMoonPhaseFactor() == 1.0 && !worldObj.isDaytime() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(15.0f, Hunter);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.SoulBanner);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));

		if (rand.nextBoolean()) {
			dropItem(Itemizer.Moonstone, 1);
		}

		if (rand.nextInt(10) == 0) {
			dropItem(Weaponizer.HotShot, 1);
		}

		if (rand.nextInt(50) == 41) {
			dropItem(Weaponizer.MoonShiner, 1);
		}

		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}
	}

	protected String getLivingSound() {
		return "nevermine:ScrubbyLiving";
	}

	protected String getDeathSound() {
		return "nevermine:ScrubbyHit";
	}

	protected String getHurtSound() {
		return "nevermine:ScrubbyHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 32.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.44);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				(par1).setFire(3);
			}
			return true;
		}
		return false;
	}
}
