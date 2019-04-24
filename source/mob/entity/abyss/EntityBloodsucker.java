package net.nevermine.mob.entity.abyss;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import static net.nevermine.container.PlayerContainer.Deities.Erebon;

public class EntityBloodsucker extends EntityMob {
	public EntityBloodsucker(final World par1World) {
		super(par1World);
		setSize(1.3f, 1.0f);
	}

	protected String getLivingSound() {
		return "nevermine:BloodsuckerLiving";
	}

	protected String getDeathSound() {
		return "nevermine:BloodsuckerDeath";
	}

	protected String getHurtSound() {
		return "nevermine:BloodsuckerHit";
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));

		if (rand.nextInt(40) == 17) {
			dropItem(Itemizer.RealmstoneAncientCavern, 2);
		}

		if (rand.nextInt(100) == 98) {
			dropItem(Weaponizer.BloodDrainer, 1);
		}

		if (rand.nextInt(100) == 43) {
			dropItem(Weaponizer.BloodIron, 1);
		}

		if (rand.nextBoolean()) {
			dropItem(Itemizer.CoinsAbyss, 1);
		}

		if (rand.nextInt(150) == 35) {
			dropItem(Itemizer.UpgradeKitAbyssal, 1);
		}
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.85f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(130.0);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (dimension == ConfigurationHelper.ancientcavern && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).adjustTribute(Erebon, 8);
		}
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 80, 2));
				heal(25.0f);
			}
			return true;
		}
		return false;
	}
}
