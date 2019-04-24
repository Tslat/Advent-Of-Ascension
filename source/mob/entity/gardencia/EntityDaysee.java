package net.nevermine.mob.entity.gardencia;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.mob.ai.HuntAttempt;
import net.nevermine.mob.placement.EntityHunter;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityDaysee extends EntityMob implements EntityHunter {
	public int getLevReq() {
		return 34;
	}

	public EntityDaysee(final World par1World) {
		super(par1World);
		setSize(1.2f, 1.6f);
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getDeathSound() {
		return "nevermine:PlantThump";
	}

	protected String getHurtSound() {
		return "nevermine:PlantThump";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(120.0f, Hunter);
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();

		if (HuntAttempt.Hunt(entity, getLevReq(), par1DamageSource))
			return super.attackEntityFrom(par1DamageSource, par2);

		if (entity instanceof EntityThrowable && ((EntityThrowable)entity).getThrower() != null) {
			((EntityThrowable)entity).getThrower().addVelocity(Math.signum(posX - ((EntityThrowable)entity).posX) * 0.329, 0.0, Math.signum(posZ - ((EntityThrowable)entity).posZ) * 0.329);
		}
		else if (entity instanceof EntityArrow) {
			par1DamageSource.getEntity().addVelocity(Math.signum(posX - par1DamageSource.getEntity().posX) * 0.329, 0.0, Math.signum(posZ - par1DamageSource.getEntity().posZ) * 0.329);
		}

		return (par1DamageSource.isProjectile() || entity instanceof EntityArrow || entity instanceof EntityThrowable || par1DamageSource.isMagicDamage()) && super.attackEntityFrom(par1DamageSource, par2);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (worldObj.isRaining()) {
			heal(0.2f);
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {

		if (rand.nextInt(4) == 2) {
			dropItem(Items.emerald, 1);
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(90.0);
	}
}
