package net.nevermine.mob.entity.dustopia;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;

public class EntityLostSoul extends EntityMob {
	public EntityLostSoul(final World par1World) {
		super(par1World);
		setSize(0.6f, 2.0f);
	}

	protected String getLivingSound() {
		return "nevermine:LostSoulLiving";
	}

	protected String getDeathSound() {
		return "nevermine:LostSoulDeath";
	}

	protected String getHurtSound() {
		return "nevermine:LostSoulHit";
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));

		if (rand.nextInt(2) == 1) {
			dropItem(Itemizer.CoinsDustopia, 2);
		}
		if (rand.nextInt(200) == 135) {
			dropItem(Itemizer.UpgradeKitDarkly, 1);
		}
		if (rand.nextInt(7) == 2) {
			dropItem(dropBanner(), 1);
		}
		if (rand.nextInt(40) == 1) {
			dropItem(Itemizer.RealmstoneShyrelands, 1);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.DustopianBanner);
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 64.0);
		if (var1 == null) {
			return;
		}
		final Vec3 var2 = var1.getLook(1.0f).normalize();
		Vec3 var3 = Vec3.createVectorHelper(posX - var1.posX, boundingBox.minY + height / 2.0f - var1.posY + var1.getEyeHeight() + 0.25 / 2.0f, posZ - var1.posZ);
		final double var4 = var3.lengthVector();
		var3 = var3.normalize();
		final double var5 = var2.dotProduct(var3);
		if (var5 > 1.0 - 0.025 / var4 && var1.canEntityBeSeen(this)) {
			heal(3.0f);
		}
	}
}
