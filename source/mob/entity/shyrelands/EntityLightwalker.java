package net.nevermine.mob.entity.shyrelands;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.gui.MobHitPacket;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityLightwalker extends EntityMob {
	public EntityLightwalker(World par1World) {
		super(par1World);
		setSize(1.0F, 1.2F);
	}

	protected String getLivingSound() {
		return "nevermine:VoidWalkerLiving";
	}

	protected String getDeathSound() {
		return "nevermine:VoidWalkerDeath";
	}

	protected String getHurtSound() {
		return "nevermine:VoidWalkerHit";
	}

	protected void dropFewItems(boolean par1, int par2) {
		if (this.rand.nextInt(7) == 2) {
			dropItem(net.minecraft.item.Item.getItemFromBlock(SpecialBlockizer.ShinyBanner), 1);
		}
		if (this.rand.nextInt(50) == 25) {
			dropItem(Weaponizer.ShyreSword, 1);
		}
	}

	protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
		playSound("mob.pig.step", 0.85F, 1.0F);
	}

	protected Entity findPlayerToAttack() {
		net.minecraft.entity.player.EntityPlayer entityPlayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return (entityPlayer != null) && (canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public boolean getCanSpawnHere() {
		return (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL) && (this.worldObj.checkNoEntityCollision(this.boundingBox)) && (this.posY < 35.0D) && (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) && (!this.worldObj.isAnyLiquid(this.boundingBox));
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.7D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(70.0D);
	}

	public boolean attackEntityAsMob(Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if ((par1 instanceof EntityLivingBase)) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.blindness.id, 120, 4));
				if ((!this.worldObj.isRemote) && ((par1 instanceof EntityPlayerMP)))
					net.nevermine.assist.AddPackets.network.sendTo(new MobHitPacket(120, 13), (EntityPlayerMP)par1);
			}
			return true;
		}
		return false;
	}
}
