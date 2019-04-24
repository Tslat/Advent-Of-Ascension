package net.nevermine.mob.entity.nether;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Armorizer;
import net.nevermine.mob.placement.EntityNoFire;
import net.nevermine.mob.placement.EntityNoRange;

public class EntityInfernal extends EntityMob implements EntityNoFire, EntityNoRange {
	public EntityInfernal(final World par1World) {
		super(par1World);
		setSize(2.3f, 2.5f);
		isImmuneToFire = true;
	}

	protected String getLivingSound() {
		return "nevermine:InfernalLiving";
	}

	protected String getDeathSound() {
		return "nevermine:InfernalHit";
	}

	protected String getHurtSound() {
		return "nevermine:InfernalHit";
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && rand.nextInt(5) == 2 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:VeryHeavyStep", 1.25f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(35.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(190.0);
	}

	private Item dropblock() {
		return Item.getItemFromBlock(Blockizer.FireLamp);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.IngotEmberstone, 4);
		if (rand.nextInt(1) == 0) {
			dropItem(Itemizer.CoinsNether, 2);
		}
		dropItem(Itemizer.SilverCoin, 15 + rand.nextInt(10));
		if (rand.nextInt(4) == 0) {
			dropItem(Armorizer.InfernalChestplate, 1);
		}
		if (rand.nextInt(4) == 0) {
			dropItem(Armorizer.InfernalHelmet, 1);
		}
		if (rand.nextInt(4) == 0) {
			dropItem(Armorizer.InfernalBoots, 1);
		}
		if (rand.nextInt(4) == 0) {
			dropItem(Armorizer.InfernalLeggings, 1);
		}
		if (rand.nextInt(9) > 4) {
			dropItem(dropblock(), 1);
		}
		if (rand.nextInt(200) == 0) {
			dropItem(Itemizer.UpgradeKitNether, 1);
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return !par1DamageSource.isProjectile() && !(entity instanceof EntityArrow) && !(entity instanceof EntityThrowable) && super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(final Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);
		if (entityToAttack != null) {
			if (entityToAttack instanceof EntityPlayer && ((EntityPlayer)entityToAttack).getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue() != 1.0) {
				entityToAttack.addVelocity(motionX * 10.5, motionY * 0.5, motionZ * 22.0);
			}
			if (entityToAttack instanceof EntityLiving) {
				((EntityLiving)entityToAttack).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 0));
			}
			return true;
		}
		return false;
	}
}
