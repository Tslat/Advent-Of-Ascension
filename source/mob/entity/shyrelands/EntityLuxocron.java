package net.nevermine.mob.entity.shyrelands;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;

public class EntityLuxocron extends EntityMob {
	public EntityLuxocron(World par1World) {
		super(par1World);
		setSize(1.2F, 1.6F);
	}

	protected String getLivingSound() {
		return "nevermine:LuxocronLiving";
	}

	protected String getDeathSound() {
		return "nevermine:LuxocronDeath";
	}

	protected String getHurtSound() {
		return "nevermine:LuxocronHit";
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		playSound("mob.pig.step", 0.55F, 1.0F);
	}

	public boolean getCanSpawnHere() {
		return (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL) && (this.worldObj.checkNoEntityCollision(this.boundingBox)) && (this.posY < 35.0D) && (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) && (!this.worldObj.isAnyLiquid(this.boundingBox));
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(boolean par1, int par2) {
		if (this.rand.nextInt(7) == 2) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.ShinyBanner), 1);
		}
		if (this.rand.nextInt(50) == 25) {
			dropItem(Weaponizer.Amplifier, 1);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		for (EntityPlayer e : (List<EntityPlayer>)this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(7.0D, 7.0D, 7.0D))) {
			e.addPotionEffect(new PotionEffect(Potion.weakness.id, 30, 2));
		}
	}

	protected Entity findPlayerToAttack() {
		EntityPlayer entityPlayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return (entityPlayer != null) && (canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
	}
}
