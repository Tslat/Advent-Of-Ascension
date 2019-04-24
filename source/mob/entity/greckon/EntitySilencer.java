package net.nevermine.mob.entity.greckon;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntitySilencer extends EntityMob {
	public EntitySilencer(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.8f);
	}

	protected String getLivingSound() {
		return "nevermine:SilencerLiving";
	}

	protected String getDeathSound() {
		return "nevermine:SilencerDeath";
	}

	protected String getHurtSound() {
		return "nevermine:SilencerHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && rand.nextInt(5) == 2 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.SilverCoin, 5 + rand.nextInt(10));
		if (rand.nextInt(2) == 1) {
			dropItem(Weaponizer.GhoulStaff, 1);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 64.0);
		if (var1 == null || var1.getHeldItem() == null || var1.capabilities.isCreativeMode) {
			return;
		}

		if (!worldObj.isRemote && var1.canEntityBeSeen(this)) {
			final Vec3 var2 = var1.getLook(1.0f).normalize();
			Vec3 var3 = Vec3.createVectorHelper(posX - var1.posX, (boundingBox.minY + height / 2.0f) - (var1.posY + var1.getEyeHeight() / 2.0f), posZ - var1.posZ);
			final double var4 = var3.lengthVector();
			var3 = var3.normalize();
			final double var5 = var2.dotProduct(var3);

			if (var5 > 0.93 - 0.025 / var4) {
				var1.entityDropItem(var1.getHeldItem(), 0.5f);
				var1.inventory.mainInventory[var1.inventory.currentItem] = null;
			}
		}
	}
}
