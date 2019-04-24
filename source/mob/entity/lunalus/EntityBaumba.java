package net.nevermine.mob.entity.lunalus;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Armorizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.enemy.EntityBamShot;

public class EntityBaumba extends EntityMob {
	private int pick;
	private int jumpcount;

	public EntityBaumba(final World par1World) {
		super(par1World);
		jumpcount = 0;
		setSize(0.6f, 2.5f);
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getDeathSound() {
		return "nevermine:PlantThump";
	}

	protected String getHurtSound() {
		return "nevermine:PlantThump";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 1.0f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(2) == 0) {
			dropItem(Itemizer.SilverCoin, 1 + rand.nextInt(1));
		}
		if (rand.nextInt(100) == 35) {
			dropItem(Itemizer.MoltenUpgrader, 1);
		}
		if (rand.nextInt(150) == 35) {
			dropItem(Weaponizer.Revolution, 1);
		}
		if (rand.nextInt(30) == 17) {
			pick = rand.nextInt(4);
			if (pick == 1) {
				dropItem(Armorizer.SpaceKingHelmet, 1);
			}
			else if (pick == 2) {
				dropItem(Armorizer.SpaceKingBoots, 1);
			}
			else if (pick == 2) {
				dropItem(Armorizer.SpaceKingLeggings, 1);
			}
			else {
				dropItem(Armorizer.SpaceKingChestplate, 1);
			}
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (rand.nextInt(80) == 37 && entityToAttack != null) {
			final Entity var1 = entityToAttack;
			final EntityBamShot var2 = new EntityBamShot(worldObj, this, 7.5f);
			final double var3 = var1.posX - posX;
			final double var4 = var1.posY + var1.getEyeHeight() - 1.100000023841858 - var2.posY;
			final double var5 = var1.posZ - posZ;
			final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
			var2.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
			playSound("nevermine:BaumbaFire", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
			worldObj.spawnEntityInWorld(var2);
		}
		++jumpcount;
		if (jumpcount == 50) {
			playSound("nevermine:BaumbaBounce", 1.0f, 1.0f / (getRNG().nextFloat() * 0.4f + 0.8f));
			motionY = 0.5;
			if (entityToAttack != null) {
				motionZ = (entityToAttack.posZ - posZ) * 0.06499999761581421;
				motionX = (entityToAttack.posX - posX) * 0.06499999761581421;
			}
			jumpcount = 0;
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0);
	}
}
