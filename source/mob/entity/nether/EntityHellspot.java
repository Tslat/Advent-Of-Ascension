package net.nevermine.mob.entity.nether;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.placement.EntityNoFire;

public class EntityHellspot extends EntityMob implements EntityNoFire {
	public EntityHellspot(final World par1World) {
		super(par1World);
		setSize(0.97f, 1.2f);
		isImmuneToFire = true;
	}

	protected String getLivingSound() {
		return "nevermine:HellspotLiving";
	}

	protected String getDeathSound() {
		return "nevermine:HellspotDeath";
	}

	protected String getHurtSound() {
		return "nevermine:HellspotHit";
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
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
		if (rand.nextInt(2) == 0) {
			dropItem(Itemizer.CoinsNether, 3);
		}
		if (rand.nextInt(80) == 34) {
			dropItem(Weaponizer.LaserBlaster, 1);
		}
		dropItem(Itemizer.CopperCoin, 7);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.blindness.id, 150, 2));
			}
			return true;
		}
		return false;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 15.0);
		if (var1 == null) {
			return;
		}
		final Vec3 var2 = var1.getLook(1.0f).normalize();
		Vec3 var3 = Vec3.createVectorHelper(posX - var1.posX, boundingBox.minY + height / 2.0f - var1.posY + var1.getEyeHeight() / 2, posZ - var1.posZ);
		final double var4 = var3.lengthVector();
		var3 = var3.normalize();
		final double var5 = var2.dotProduct(var3);
		if (var5 > 1.0 - 0.025 / var4 && var1.canEntityBeSeen(this)) {
			var1.setPositionAndUpdate(posX, posY, posZ);
		}
	}
}
