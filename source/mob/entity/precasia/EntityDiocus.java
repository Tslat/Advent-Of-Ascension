package net.nevermine.mob.entity.precasia;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.ai.HuntAttempt;
import net.nevermine.mob.placement.EntityHunter;
import net.nevermine.mob.placement.EntityNoExplosions;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityDiocus extends EntityMob implements EntityHunter, EntityNoExplosions {
	public int getLevReq() {
		return 23;
	}

	public EntityDiocus(final World par1World) {
		super(par1World);
		setSize(1.0f, 1.1f);
	}

	protected String getLivingSound() {
		return "nevermine:DiocusLiving";
	}

	protected String getDeathSound() {
		return "nevermine:DiocusDeath";
	}

	protected String getHurtSound() {
		return "nevermine:DiocusHit";
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(50.0f, Hunter);
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return HuntAttempt.Hunt(entity, getLevReq(), par1DamageSource) && super.attackEntityFrom(par1DamageSource, par2);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));

		if (rand.nextInt(100) == 83) {
			dropItem(Weaponizer.PowerRay, 1);
		}

		if (rand.nextInt(7) == 0) {
			dropItem(Itemizer.ExplosiveIdol, 1);
		}

		if (rand.nextInt(200) == 97) {
			dropItem(Itemizer.UpgradeKitPrecasian, 1);
		}

		if (rand.nextBoolean()) {
			dropItem(Itemizer.CoinsPrecasian, 1 + rand.nextInt(2));
		}
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
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.75);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(160.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.confusion.id, 60, 5));
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.poison.id, 200, 2));
				worldObj.createExplosion(this, posX, posY, posZ, 1.5f, false);
			}
			return true;
		}
		return false;
	}
}
