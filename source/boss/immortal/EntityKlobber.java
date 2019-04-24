package net.nevermine.boss.immortal;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.boss.EntityBoss;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class EntityKlobber extends EntityMob implements EntityBoss {
	public EntityKlobber(final World par1World) {
		super(par1World);
		setSize(0.8f, 2.4f);
	}

	protected String getLivingSound() {
		final int pick = rand.nextInt(3);
		if (pick == 1) {
			return "nevermine:ImmortalLiving1";
		}
		if (pick == 2) {
			return "nevermine:ImmortalLiving2";
		}
		return "nevermine:ImmortalLiving3";
	}

	protected String getDeathSound() {
		return "nevermine:ImmortalDeath";
	}

	protected String getHurtSound() {
		return "random.anvil_land";
	}

	protected boolean canDespawn() {
		return true;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 1.0f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.ProgressCoin1, 1);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(11.0, 11.0, 11.0))) {
			if (e.capabilities.isCreativeMode)
				continue;

			e.addVelocity(Math.signum(posX - e.posX) * 0.019, 0.0, Math.signum(posZ - e.posZ) * 0.019);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(20.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000.0);
	}
}
