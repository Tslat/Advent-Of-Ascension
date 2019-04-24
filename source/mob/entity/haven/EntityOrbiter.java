package net.nevermine.mob.entity.haven;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class EntityOrbiter extends EntityMob {
	public EntityOrbiter(final World par1World) {
		super(par1World);
		setSize(2.2f, 2.2f);
	}

	protected String getLivingSound() {
		return "nevermine:OrbiterLiving";
	}

	protected String getDeathSound() {
		return "nevermine:OrbiterDeath";
	}

	protected String getHurtSound() {
		return "nevermine:OrbiterHit";
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
		if (rand.nextInt(2) == 0) {
			dropItem(Itemizer.CoinsHaven, 1);
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void onLivingUpdate() {
		for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(8.0, 8.0, 8.0))) {
			if (e.capabilities.isCreativeMode)
				continue;

			e.addVelocity(motionX * 0.05, 0.1, motionZ * 0.0);
			e.fallDistance = 0.0f;
		}

		super.onLivingUpdate();
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0);
	}
}
