package net.nevermine.mob.entity.precasia;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityGiantSlug extends EntityMob {
	public EntityGiantSlug(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.5f);
	}

	protected String getLivingSound() {
		return "nevermine:GiantSlugLiving";
	}

	protected String getDeathSound() {
		return "nevermine:GiantSlugDeath";
	}

	protected String getHurtSound() {
		return "nevermine:GiantSlugHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:SlugStep", 0.55f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));

		if (rand.nextBoolean()) {
			dropItem(Itemizer.CoinsPrecasian, 1);
		}

		if (rand.nextInt(15) == 3) {
			dropItem(Itemizer.RealmstoneGardencia, 2);
		}

		if (rand.nextInt(55) == 15) {
			dropItem(Weaponizer.UltraCannon, 1);
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
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(70.0);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (!worldObj.isRemote) {
			int var1 = MathHelper.floor_double(posX);
			int var2 = MathHelper.floor_double(posZ);
			for (var1 = 0; var1 < 4; ++var1) {
				var2 = MathHelper.floor_double(posX + (var1 % 2 * 2 - 1) * 0.25f);
				final int var3 = MathHelper.floor_double(posY);
				final int var4 = MathHelper.floor_double(posZ + (var1 / 2 % 2 * 2 - 1) * 0.25f);
				Block bl = worldObj.getBlock(var2, var3, var4);
				if (worldObj.getBlock(var2, var3 - 1, var4).isNormalCube() && !bl.getMaterial().blocksMovement() && bl.getMaterial() != Material.portal) {
					worldObj.setBlock(var2, var3, var4, Blockizer.blockAcid);
				}
			}
		}
	}
}
