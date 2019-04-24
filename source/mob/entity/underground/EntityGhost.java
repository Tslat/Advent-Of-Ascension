package net.nevermine.mob.entity.underground;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

public class EntityGhost extends EntityMob {
	public EntityGhost(final World par1World) {
		super(par1World);
		setSize(0.8f, 2f);
	}

	protected String getLivingSound() {
		return "nevermine:GhostLiving";
	}

	protected String getDeathSound() {
		return "nevermine:GhostDeath";
	}

	protected String getHurtSound() {
		return "nevermine:GhostHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.15f, 1.0f);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY < 50.0 && isValidLightLevel() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty();
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(9) == 0) {
			dropItem(Itemizer.IngotLimonite, 5);
		}
		if (rand.nextInt(33) == 24) {
			dropItem(Weaponizer.SoulStorm, 1);
		}
		if (rand.nextInt(25) == 4) {
			dropItem(Itemizer.RealmstoneDeeplands, 2);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 64.0);
		if (var1 == null) {
			return;
		}
		final Vec3 var2 = var1.getLook(1.0f).normalize();
		Vec3 var3 = Vec3.createVectorHelper(posX - var1.posX, boundingBox.minY + height / 2.0f - var1.posY + var1.getEyeHeight(), posZ - var1.posZ);
		final double var4 = var3.lengthVector();
		var3 = var3.normalize();
		final double var5 = var2.dotProduct(var3);
		if (var5 > 0.75 - 0.025 / var4 && var1.canEntityBeSeen(this)) {
			addPotionEffect(new PotionEffect(Potion.invisibility.id, 200, 1));
		}
	}
}
