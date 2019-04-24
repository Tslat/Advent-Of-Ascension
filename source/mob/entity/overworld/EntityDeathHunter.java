package net.nevermine.mob.entity.overworld;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.event.dimensional.overworld.DeathDayEvent;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class EntityDeathHunter extends EntityMob {
	public EntityDeathHunter(final World par1World) {
		super(par1World);
		setSize(3.5f, 3.5f);
	}

	protected String getLivingSound() {
		return "nevermine:DeathHunterLiving";
	}

	protected String getDeathSound() {
		return "nevermine:DeathHunterDeath";
	}

	protected String getHurtSound() {
		return "nevermine:DeathHunterHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:VeryHeavyStep", 2.0f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.RealmstoneImmortallis, 1);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && DeathDayEvent.isDeadly() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (rand.nextInt(200) == 15) {
			for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(15.0, 15.0, 15.0))) {
				if (e.capabilities.isCreativeMode)
					continue;

				e.addVelocity(Math.signum(e.posX - posX) * 3.049, 0.0, Math.signum(e.posZ - posZ) * 3.049);
				playSound("nevermine:DeathHunterLiving", 4.0f, 1.0f / (rand.nextFloat() * 0.4f + 0.8f));
			}
		}

		for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(15.0, 15.0, 15.0))) {
			if (e.capabilities.isCreativeMode)
				continue;

			e.addVelocity(Math.signum(posX - e.posX) * 0.049, 0.07000000029802322, Math.signum(posZ - e.posZ) * 0.049);
			e.fallDistance = -0.5f;
		}
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0);
	}
}
