package net.nevermine.mob.entity.mysterium;

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

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityMushroomSpider extends EntityMob implements EntityHunter {
	public int getLevReq() {
		return 70;
	}

	public EntityMushroomSpider(final World par1World) {
		super(par1World);
		setSize(1.0f, 0.9f);
	}

	protected String getLivingSound() {
		return "nevermine:NightmareSpiderLiving";
	}

	protected String getDeathSound() {
		return "nevermine:NightmareSpiderDeath";
	}

	protected String getHurtSound() {
		return "nevermine:NightmareSpiderHit";
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(700.0f, Hunter);
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return HuntAttempt.Hunt(entity, getLevReq(), par1DamageSource) && super.attackEntityFrom(par1DamageSource, par2);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.HavenShrooms, 2);

		if (rand.nextInt(70) == 35) {
			dropItem(Weaponizer.Vivo, 1);
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.4);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.poison.id, 80, 1));
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.blindness.id, 80, 1));
			}
			return true;
		}
		return false;
	}
}
