package net.nevermine.mob.entity.mysterium;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.placement.EntityNoBows;
import net.nevermine.mob.placement.EntityNoRange;
import net.nevermine.projectiles.cannon.EntityRunicBomb;

public class EntityRunicGolem extends EntityMob implements EntityNoRange, EntityNoBows {
	private int shieldCounter;
	private int form;

	public EntityRunicGolem(final World par1World) {
		super(par1World);
		shieldCounter = 120;
		form = 0;
		setSize(0.6f, 1.5f);
	}

	protected String getLivingSound() {
		return "mob.irongolem.walk";
	}

	protected String getDeathSound() {
		return "nevermine:RunicGolemHit";
	}

	protected String getHurtSound() {
		return "nevermine:RunicGolemHit";
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.RuneStone, 1);
		if (rand.nextInt(200) == 155) {
			dropItem(Itemizer.UpgradeKitRunic, 1);
		}
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.irongolem.walk", 1.0f, 1.0f);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (form == 0) {
			--shieldCounter;
			if (shieldCounter == 1) {
				form = 1;
				playSound("nevermine:RunicGolemChange", 1.0f, 1.0f);
			}
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return (entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null;
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0);
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		if (form == 0 && (par1DamageSource.isProjectile() || entity instanceof EntityArrow || entity instanceof EntityThrowable)) {
			return false;
		}
		if (form == 1) {
			if (par1DamageSource.isProjectile() || entity instanceof EntityArrow || entity instanceof EntityThrowable) {
				return false;
			}
			form = 2;
			playSound("nevermine:RunicGolemChange", 1.0f, 1.0f);
			if (!worldObj.isRemote) {
				dropItem(Itemizer.RuneStoneActive, 1);
			}
		}
		else if (form == 2) {
			if (!(entity instanceof EntityRunicBomb)) {
				return false;
			}
			form = 0;
			shieldCounter = 120;
			playSound("nevermine:RunicGolemChange", 1.0f, 1.0f);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}
}
