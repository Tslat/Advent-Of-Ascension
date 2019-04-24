package net.nevermine.mob.entity.nether;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.placement.EntityNoFire;

public class EntityEmbrake extends EntityMob implements EntityNoFire {
	public EntityEmbrake(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.5f);
		isImmuneToFire = true;
	}

	protected String getLivingSound() {
		return "nevermine:EmbrakeLiving";
	}

	protected String getDeathSound() {
		return "nevermine:EmbrakeDeath";
	}

	protected String getHurtSound() {
		return "nevermine:EmbrakeHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:DinoStep", 0.85f, 1.0f);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(3));
		dropItem(Itemizer.FireRune, 20);
		if (rand.nextInt(2) == 0) {
			dropItem(Itemizer.CoinsNether, 3);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(120.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				par1.setFire(5);
				if (rand.nextInt(7) == 3) {
					int var1 = MathHelper.floor_double(par1.posX);
					int var2 = MathHelper.floor_double(par1.posZ);
					for (var1 = 0; var1 < 4; ++var1) {
						var2 = MathHelper.floor_double(par1.posX + (var1 % 2 * 2 - 1) * 0.25f);
						final int var3 = MathHelper.floor_double(par1.posY);
						final int var4 = MathHelper.floor_double(par1.posZ + (var1 / 2 % 2 * 2 - 1) * 0.25f);
						worldObj.setBlock(var2, var3, var4, Blocks.lava);
						worldObj.setBlock(var2, var3 + 1, var4, Blocks.lava);
					}
				}
			}
			return true;
		}
		return false;
	}
}
