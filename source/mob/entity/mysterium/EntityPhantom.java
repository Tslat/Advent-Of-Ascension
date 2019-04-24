package net.nevermine.mob.entity.mysterium;

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
import net.nevermine.mob.ai.HuntAttempt;
import net.nevermine.mob.placement.EntityHunter;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityPhantom extends EntityMob implements EntityHunter {
	public int getLevReq() {
		return 20;
	}

	public EntityPhantom(final World par1World) {
		super(par1World);
		setSize(1.0f, 2.0f);
	}

	protected String getLivingSound() {
		return "nevermine:PhantomLiving";
	}

	protected String getDeathSound() {
		return "nevermine:PhantomHit";
	}

	protected String getHurtSound() {
		return "nevermine:PhantomHit";
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(60.0f, Hunter);
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

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.HauntedBanner);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));

		if (rand.nextInt(3) == 0) {
			dropItem(Itemizer.Phantasm, 1);
		}

		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(70.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityLivingBase) {
				((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.weakness.id, 130, 3));
				if (rand.nextInt(6) == 2) {
					((EntityLivingBase)par1).addPotionEffect(new PotionEffect(Potion.wither.id, 130, 6));
					addPotionEffect(new PotionEffect(Potion.invisibility.id, 80, 3));
				}
			}
			return true;
		}
		return false;
	}
}
