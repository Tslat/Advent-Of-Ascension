package net.nevermine.mob.entity.mysterium;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityEeo extends EntityMob {
	public EntityEeo(final World par1World) {
		super(par1World);
		setSize(0.9f, 2.0f);
	}

	protected String getLivingSound() {
		return "nevermine:HunchLiving";
	}

	protected String getDeathSound() {
		return "nevermine:HunchDeath";
	}

	protected String getHurtSound() {
		return "nevermine:HunchHit";
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && rand.nextInt(3) == 2 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.85f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.blindness.id, 80, 2));
			}
			return true;
		}
		return false;
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		transform();
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float dmg) {
		if (!worldObj.isRemote && dmg >= 1) {
			if (rand.nextBoolean()) {
				final EntitySpiritGuardian var2 = new EntitySpiritGuardian(worldObj);
				var2.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
				worldObj.spawnEntityInWorld(var2);
			}
			else {
				final EntitySpiritProtector var3 = new EntitySpiritProtector(worldObj);
				var3.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
				worldObj.spawnEntityInWorld(var3);
			}
		}

		return super.attackEntityFrom(par1DamageSource, dmg);
	}

	private void transform() {
		if (!worldObj.isRemote) {
			final EntityEeoRunning var2 = new EntityEeoRunning(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var2);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
	}
}
