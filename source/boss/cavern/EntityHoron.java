package net.nevermine.boss.cavern;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.boss.cavern.holder.EntityHolderHoron;
import net.nevermine.container.AncientBossesContainer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.placement.EntityNoRange;
import net.nevermine.resource.creation.creationHelper;
import net.nevermine.resource.energy.energyHelper;
import net.nevermine.resource.soulpower.soulPowerHelper;

public class EntityHoron extends EntityMob implements EntityNoRange, CavernBoss {
	public EntityHoron(final World par1World) {
		super(par1World);
		setSize(1.8f, 2.3f);
	}

	protected String getLivingSound() {
		return "nevermine:HoronLiving";
	}

	protected String getDeathSound() {
		return "nevermine:HoronDeath";
	}

	protected String getHurtSound() {
		return "nevermine:HoronHit";
	}

	protected boolean canDespawn() {
		return false;
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		AncientBossesContainer.killHoron(this);
	}

	private void transform() {
		if (!worldObj.isRemote) {
			final EntityHolderHoron var2 = new EntityHolderHoron(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var2);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(10) == 4) {
			dropItem(Weaponizer.LuxonScythe, 1);
		}

		if (rand.nextInt(10) == 4) {
			dropItem(Weaponizer.LuxonStickler, 1);
		}

		if (rand.nextInt(5) == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.LuxonBanner), 1);
		}

		if (rand.nextInt(5) == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.HoronStatue), 1);
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (rand.nextInt(60) == 13) {
			worldObj.createExplosion(this, posX, posY, posZ, 2.0f, false);
		}

		if (rand.nextInt(400) == 35) {
			final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 64.0);
			if (var1 == null) {
				return;
			}
			setPositionAndUpdate(var1.posX, var1.posY, var1.posZ);
			var1.addPotionEffect(new PotionEffect(Potion.weakness.id, 120, 3));
		}
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("nevermine:HeavyStep", 0.85f, 1.0f);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(24.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(3000.0);
	}

	public boolean attackEntityAsMob(final Entity par1) {
		if (super.attackEntityAsMob(par1)) {
			if (par1 instanceof EntityPlayer) {
				energyHelper.getProperties((EntityPlayer)par1).removeBarValue(60.0f);
				creationHelper.getProperties((EntityPlayer)par1).removeBarValue(600.0f);
				soulPowerHelper.getProperties((EntityPlayer)par1).removeBarValue(2000.0f);
			}
			return true;
		}
		return false;
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return ((!par1DamageSource.isProjectile() && !(entity instanceof EntityArrow) && !(entity instanceof EntityThrowable)) || rand.nextInt(3) != 2) && super.attackEntityFrom(par1DamageSource, par2);
	}
}
