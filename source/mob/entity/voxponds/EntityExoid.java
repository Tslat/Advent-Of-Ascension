package net.nevermine.mob.entity.voxponds;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class EntityExoid extends EntityMob {
	private int level;
	private int age;

	public EntityExoid(final World par1World) {
		super(par1World);
		age = 0;
		setSize(0.95f, 1.8f);
	}

	public EntityExoid(final World par1World, final int lev) {
		this(par1World);
		level = lev;
	}

	protected String getLivingSound() {
		return "nevermine:GadgetoidLiving";
	}

	protected String getDeathSound() {
		return "nevermine:GadgetoidDeath";
	}

	protected String getHurtSound() {
		return "nevermine:GadgetoidHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean canDespawn() {
		return false;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		++age;
		if (age == 900) {
			worldObj.createExplosion(this, posX, posY, posZ, 3.0f, false);
			setDead();
		}
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (level == 5) {
			dropItem(Itemizer.DoomStone, 1);
		}
		if (level == 5 && rand.nextInt(3) == 1) {
			dropItem(Itemizer.RealmstoneVoxPonds, 4);
		}
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(14.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		transform();
	}

	private void transform() {
		if (!worldObj.isRemote && level != 5) {
			int x = 45;
			int z = 45;
			if (rand.nextInt(2) == 1) {
				z *= -1;
			}
			if (rand.nextInt(2) == 1) {
				x *= -1;
			}
			final EntityExoid var2 = new EntityExoid(worldObj, level + 1);
			var2.setLocationAndAngles(posX + x, posY + 3.0, posZ + z, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var2);
		}
	}
}
