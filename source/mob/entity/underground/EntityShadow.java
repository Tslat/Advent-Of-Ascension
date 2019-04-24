package net.nevermine.mob.entity.underground;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityShadow extends EntityMob {
	public EntityShadow(final World par1World) {
		super(par1World);
		setSize(0.6f, 1.8f);
	}

	protected String getLivingSound() {
		return "nevermine:ShadowLiving";
	}

	protected String getDeathSound() {
		return "nevermine:ShadowHit";
	}

	protected String getHurtSound() {
		return "nevermine:ShadowHit";
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY < 45.0 && isValidLightLevel() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty();
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.15f, 1.0f);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(2.1);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		transform();
	}

	private void transform() {
		if (!worldObj.isRemote) {
			final EntityShade var2 = new EntityShade(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var2);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
	}
}
