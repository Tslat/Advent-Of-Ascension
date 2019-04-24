package net.nevermine.mob.entity.night;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityDarkBeast extends EntityMob {
	public EntityDarkBeast(final World par1World) {
		super(par1World);
		setSize(1.0f, 2.2f);
	}

	protected String getDeathSound() {
		return "nevermine:DarkBeastDeath";
	}

	protected String getLivingSound() {
		return "nevermine:DarkBeastLiving";
	}

	protected String getHurtSound() {
		return "nevermine:DarkBeastHit";
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(15.0f, Hunter);
		}
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.SoulBanner);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));

		if (rand.nextBoolean()) {
			dropItem(Itemizer.Moonstone, 1);
		}

		if (rand.nextInt(50) == 36) {
			dropItem(Weaponizer.MoonShiner, 1);
		}

		if (rand.nextInt(14) == 0) {
			dropItem(Itemizer.ScreamShield, 1);
		}

		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.getCurrentMoonPhaseFactor() == 1.0 && !worldObj.isDaytime() && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
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
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(150.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 4));
			}
			return true;
		}
		return false;
	}
}
