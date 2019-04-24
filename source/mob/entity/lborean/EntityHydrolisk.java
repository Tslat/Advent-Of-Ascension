package net.nevermine.mob.entity.lborean;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.boss.EntityBoss;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Armorizer;

public class EntityHydrolisk extends EntityMob implements EntityBoss {
	public EntityHydrolisk(final World par1World) {
		super(par1World);
		setSize(1.7f, 3.6f);
	}

	protected String getLivingSound() {
		return "nevermine:HydroliskLiving";
	}

	protected String getDeathSound() {
		return "nevermine:HydroliskDeath";
	}

	protected String getHurtSound() {
		return "nevermine:HydroliskHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:EmperorBeastStep", 0.55f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.SilverCoin, 5 + rand.nextInt(10));
		final int choose = rand.nextInt(4);
		if (choose == 1) {
			dropItem(Armorizer.HydroplateBoots, 1);
		}
		else if (choose == 2) {
			dropItem(Armorizer.HydroplateHelmet, 1);
		}
		else if (choose == 3) {
			dropItem(Armorizer.HydroplateLeggings, 1);
		}
		else {
			dropItem(Armorizer.HydroplateChestplate, 1);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (isInWater()) {
			heal(1.0f);
		}
		if (rand.nextInt(130) == 43) {
			final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 20.0);
			if (var1 == null || var1.getDistanceToEntity(this) > 20.0f) {
				return;
			}
			if (!worldObj.isRemote) {
				setPosition(var1.posX, var1.posY, var1.posZ);
			}
		}
		if (rand.nextInt(120) == 17 && worldObj.provider.dimensionId != -1) {
			int var2 = MathHelper.floor_double(posX);
			int var3 = MathHelper.floor_double(posZ);
			for (var2 = 0; var2 < 4; ++var2) {
				var3 = MathHelper.floor_double(posX + (var2 % 2 * 2 - 1) * 0.25f);
				final int var4 = MathHelper.floor_double(posY);
				final int var5 = MathHelper.floor_double(posZ + (var2 / 2 % 2 * 2 - 1) * 0.25f);
				if (worldObj.getBlock(var3, var4, var5) == Blocks.air) {
					worldObj.setBlock(var3, var4, var5, Blocks.water);
				}
			}
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(16.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(800.0);
	}
}
