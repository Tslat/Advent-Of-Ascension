package net.nevermine.mob.entity.crystevia;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;

public class EntityConstructMind extends EntityMob {
	public EntityConstructMind(final World par1World) {
		super(par1World);
		setSize(2.5f, 2.5f);
	}

	protected String getLivingSound() {
		final int pick = rand.nextInt(6);
		if (pick == 1) {
			return "nevermine:CrystalConstructLiving1";
		}
		if (pick == 2) {
			return "nevermine:CrystalConstructLiving2";
		}
		if (pick == 3) {
			return "nevermine:CrystalConstructLiving3";
		}
		if (pick == 4) {
			return "nevermine:CrystalConstructLiving4";
		}
		if (pick == 5) {
			return "nevermine:CrystalConstructLiving6";
		}
		return "nevermine:CrystalConstructLiving5";
	}

	protected String getDeathSound() {
		return "nevermine:ConstructDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ConstructHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 1.0f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.RealmstoneCrystevia, 2);

		if (rand.nextInt(3) == 2) {
			dropItem(Itemizer.CoinsCrystevia, 2);
		}

		if (rand.nextInt(2) == 1) {
			dropItem(Weaponizer.Orbocron, 1);
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && rand.nextInt(5) == 3 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(16.0, 16.0, 16.0))) {
			if (e.capabilities.isCreativeMode)
				continue;

			e.addPotionEffect(new PotionEffect(Potion.blindness.id, 65, 1));
			e.addPotionEffect(new PotionEffect(Potion.confusion.id, 65, 5));
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
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0);
	}
}
