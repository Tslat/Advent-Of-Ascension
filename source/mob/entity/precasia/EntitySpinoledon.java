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
import net.nevermine.assist.EntityUtil;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.ai.HuntAttempt;
import net.nevermine.mob.placement.EntityHunter;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntitySpinoledon extends EntityMob implements EntityHunter {
	public int getLevReq() {
		return 59;
	}

	public EntitySpinoledon(final World par1World) {
		super(par1World);
		setSize(1.5f, 1.85f);
	}

	protected String getLivingSound() {
		return "nevermine:SpinoledonLiving";
	}

	protected String getDeathSound() {
		return "nevermine:SpinoledonDeath";
	}

	protected String getHurtSound() {
		return "nevermine:SpinoledonHit";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:DinoStep", 0.85f, 1.0f);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(240.0f, Hunter);
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return HuntAttempt.Hunt(entity, getLevReq(), par1DamageSource) && super.attackEntityFrom(par1DamageSource, par2);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));

		if (rand.nextBoolean()) {
			dropItem(Itemizer.CoinsPrecasian, 2);
		}

		if (rand.nextInt(200) == 145) {
			dropItem(Weaponizer.SquadGun, 1);
		}

		if (rand.nextInt(200) == 43) {
			dropItem(Itemizer.UpgradeKitPrecasian, 1);
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
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.75);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(150.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				final float current = getHealth();

				if (current < 60.0f) {
					((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.weakness.id, 20, 2));
				}

				final float theircurrent = ((EntityLivingBase)par1).getHealth();

				if (EntityUtil.getPercentageOfMaxHealth((EntityLivingBase)par1) < 50) {
					(par1).attackEntityFrom(DamageSource.causeMobDamage(this), 12.0f);
				}
			}
			return true;
		}
		return false;
	}
}
