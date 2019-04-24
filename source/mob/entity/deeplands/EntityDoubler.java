package net.nevermine.mob.entity.deeplands;

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

public class EntityDoubler extends EntityMob {
	public EntityDoubler(final World par1World) {
		super(par1World);
		setSize(1.4f, 2.2f);
	}

	protected String getLivingSound() {
		return "nevermine:DoublerLiving";
	}

	protected String getDeathSound() {
		return "nevermine:DoublerDeath";
	}

	protected String getHurtSound() {
		return "nevermine:DoublerHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY < 120.0 && rand.nextInt(5) == 2 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(2) == 1) {
			dropItem(Weaponizer.SkeletalArchergun, 1);
		}
		dropItem(Itemizer.SilverCoin, 5 + rand.nextInt(10));
		dropItem(Itemizer.RealmstoneDeeplands, 3);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 10.0);
		if (var1 == null || var1.capabilities.isCreativeMode || var1.getDistanceToEntity(this) > 10.0f) {
			return;
		}
		var1.addPotionEffect(new PotionEffect(Potion.blindness.id, 30, 1));
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(21.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0);
	}
}
