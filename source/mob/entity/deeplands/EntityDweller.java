package net.nevermine.mob.entity.deeplands;

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

public class EntityDweller extends EntityMob {
	private int CloneCount;

	public EntityDweller(final World par1World) {
		super(par1World);
		CloneCount = 160;
		setSize(1.2f, 2.4f);
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getDeathSound() {
		return null;
	}

	protected String getHurtSound() {
		return null;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY < 120.0 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (CloneCount == 2) {
			CloneCount = 160;
			addPotionEffect(new PotionEffect(Potion.invisibility.id, 60, 2));
			if (!worldObj.isRemote) {
				final EntityDwellerClone var2 = new EntityDwellerClone(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0f, 0.0f);
				worldObj.spawnEntityInWorld(var2);
			}
		}
		--CloneCount;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
		if (rand.nextInt(100) == 35) {
			dropItem(Itemizer.UpgradeKitPredator, 1);
		}
		if (rand.nextInt(40) == 16) {
			dropItem(Itemizer.UnchargedStone, 1);
		}
		if (rand.nextInt(7) == 2) {
			dropItem(dropBanner(), 1);
		}
		if (rand.nextInt(40) == 25) {
			dropItem(Itemizer.RealmstoneIromine, 2);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.DeepBanner);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
	}
}
