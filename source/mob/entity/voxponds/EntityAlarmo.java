package net.nevermine.mob.entity.voxponds;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;

public class EntityAlarmo extends EntityMob {
	public EntityAlarmo(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.6f);
	}

	protected String getLivingSound() {
		return "nevermine:AlarmoLiving";
	}

	protected String getDeathSound() {
		return "nevermine:AlarmoDeath";
	}

	protected String getHurtSound() {
		return "nevermine:AlarmoHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(20) == 13) {
			dropItem(Weaponizer.DestructionStaff, 1);
		}
		if (rand.nextInt(100) == 77) {
			dropItem(Itemizer.UpgradeKitApoco, 1);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		for (EntityPlayer p : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(2.0, 2.0, 2.0))) {
			if (!p.capabilities.isCreativeMode) {
				if (!canEntityBeSeen(p))
					continue;

				worldObj.createExplosion(this, posX, posY, posZ, 3.0f, false);

				if (!worldObj.isRemote) {
					setDead();
				}

				return;
			}
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
	}
}
