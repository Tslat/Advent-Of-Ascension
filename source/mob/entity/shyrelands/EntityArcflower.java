package net.nevermine.mob.entity.shyrelands;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityArcflower extends EntityMob {
	public EntityArcflower(World par1World) {
		super(par1World);
		setSize(0.98F, 0.98F);
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

	public boolean getCanSpawnHere() {
		return (this.worldObj.difficultySetting != net.minecraft.world.EnumDifficulty.PEACEFUL) && (this.worldObj.checkNoEntityCollision(this.boundingBox)) && (this.posY < 35.0D) && (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) && (!this.worldObj.isAnyLiquid(this.boundingBox));
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected net.minecraft.entity.Entity findPlayerToAttack() {
		EntityPlayer entityPlayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return (entityPlayer != null) && (canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0D);
	}

	public void onDeath(DamageSource var1) {
		super.onDeath(var1);
		transform();
	}

	private void transform() {
		if (!this.worldObj.isRemote) {
			EntityArcworm var2 = new EntityArcworm(this.worldObj);
			var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.worldObj.spawnEntityInWorld(var2);
			if (!this.worldObj.isRemote) {
				setDead();
			}
		}
	}

	public void onLivingUpdate() {
		if (this.posY > 31.0D) {
			this.motionY = -1.0D;
		}
		super.onLivingUpdate();
		EntityPlayer var1 = this.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);

		if (var1 == null) {
			return;
		}

		Vec3 var3 = var1.getLook(1.0F).normalize();
		Vec3 var4 = Vec3.createVectorHelper(this.posX - var1.posX, this.boundingBox.minY + this.height / 2.0F - var1.posY + var1.getEyeHeight(), this.posZ - var1.posZ);
		double var5 = var4.lengthVector();
		var4 = var4.normalize();
		double var7 = var3.dotProduct(var4);
		if ((var7 > 0.75D - 0.025D / var5) && (var1.canEntityBeSeen(this))) {
			this.motionX = 0.0D;
			this.motionY = 0.0D;
			this.motionZ = 0.0D;
		}
	}
}
