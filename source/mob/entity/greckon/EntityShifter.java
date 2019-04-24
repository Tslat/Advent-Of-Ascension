package net.nevermine.mob.entity.greckon;

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
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;

public class EntityShifter extends EntityMob {
	private int cloakTick;
	private int waitCloak;
	private boolean isCloak;

	public EntityShifter(final World par1World) {
		super(par1World);
		cloakTick = 80;
		waitCloak = 80;
		isCloak = false;
		setSize(0.7f, 1.5f);
	}

	protected String getLivingSound() {
		return "nevermine:ShifterLiving";
	}

	protected String getDeathSound() {
		return "nevermine:ShifterDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ShifterHit";
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
		if (rand.nextInt(2) == 1) {
			dropItem(Itemizer.CoinsGreckon, 2);
		}
		if (rand.nextInt(7) == 2) {
			dropItem(dropBanner(), 1);
		}
		if (rand.nextInt(200) == 135) {
			dropItem(Itemizer.UpgradeKitHaunted, 1);
		}
		if (rand.nextInt(20) == 3) {
			dropItem(Itemizer.RealmstoneDustopia, 2);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.GhoulBanner);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		--cloakTick;
		if (cloakTick == 1) {
			cloakTick = 160;
			addPotionEffect(new PotionEffect(Potion.invisibility.id, 40, 2));
			playSound("nevermine:ShifterLiving", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
	}
}
