package net.nevermine.mob.entity.dustopia;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.AddPackets;
import net.nevermine.gui.MobHitPacket;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityStalkerPrime extends EntityMob {
	public EntityStalkerPrime(final World par1World) {
		super(par1World);
		setSize(0.6f, 2.5f);
	}

	protected String getLivingSound() {
		return "nevermine:StalkerPrimeLiving";
	}

	protected String getDeathSound() {
		return "nevermine:StalkerDeath";
	}

	protected String getHurtSound() {
		return "nevermine:StalkerHit";
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && rand.nextInt(5) == 2 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.SilverCoin, 5 + rand.nextInt(10));
		if (rand.nextInt(2) == 1) {
			dropItem(Weaponizer.Dustometer, 1);
		}
		dropItem(Itemizer.RealmstoneDustopia, 3);
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(320.0);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 64.0);
		if (var1 == null || var1.capabilities.isCreativeMode) {
			return;
		}

		final Vec3 var2 = var1.getLook(1.0f).normalize();
		Vec3 var3 = Vec3.createVectorHelper(posX - var1.posX, boundingBox.minY + height / 2.0f - var1.posY + var1.getEyeHeight() / 2, posZ - var1.posZ);
		final double var4 = var3.lengthVector();
		var3 = var3.normalize();
		final double var5 = var2.dotProduct(var3);
		if (var5 > 0.75 - 0.025 / var4 && var1.canEntityBeSeen(this)) {
			motionX = 0.0;
			motionY = 0.0;
			motionZ = 0.0;
			if (!worldObj.isRemote && var1 instanceof EntityPlayerMP) {
				AddPackets.network.sendTo(new MobHitPacket(30, 3), (EntityPlayerMP)var1);
			}
		}
	}
}
