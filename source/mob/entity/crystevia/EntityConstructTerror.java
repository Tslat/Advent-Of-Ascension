package net.nevermine.mob.entity.crystevia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.projectiles.enemy.EntityConstructTerrorShot;

public class EntityConstructTerror extends EntityFlying implements IMob {
	public int courseChangeCooldown;
	public double waypointX;
	public double waypointY;
	public double waypointZ;
	private Entity targetedEntity;
	private int aggroCooldown;
	public int prevAttackCounter;
	public int attackCounter;
	private int explosionStrength;
	private static final String __OBFID = "CL_00001689";

	public EntityConstructTerror(final World par1World) {
		super(par1World);
		explosionStrength = 3;
		setSize(1.0f, 1.0f);
		isImmuneToFire = false;
		experienceValue = 5;
	}

	@SideOnly(Side.CLIENT)
	public boolean func_110182_bF() {
		return dataWatcher.getWatchableObjectByte(16) != 0;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(3) == 2) {
			dropItem(Itemizer.CoinsCrystevia, 2);
		}
		if (rand.nextInt(2) == 1) {
			dropItem(Itemizer.GemstonesGreen, 3);
		}
		if (rand.nextInt(6) == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.CrystalBanner), 1);
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox) && rand.nextInt(4) == 2;
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (isEntityInvulnerable()) {
			return false;
		}
		if ("fireball".equals(par1DamageSource.getDamageType()) && par1DamageSource.getEntity() instanceof EntityPlayer) {
			super.attackEntityFrom(par1DamageSource, 1000.0f);
			((EntityPlayer)par1DamageSource.getEntity()).triggerAchievement(AchievementList.ghast);
			return true;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, (byte)0);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0);
	}

	protected void updateEntityActionState() {
		if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			setDead();
		}

		if (!worldObj.isRemote && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox)) {
			setDead();
		}
		despawnEntity();
		prevAttackCounter = attackCounter;
		final double d0 = waypointX - posX;
		final double d2 = waypointY - posY;
		final double d3 = waypointZ - posZ;
		double d4 = d0 * d0 + d2 * d2 + d3 * d3;
		if (d4 < 1.0 || d4 > 3600.0) {
			waypointX = posX + (rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
			waypointY = posY + (rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
			waypointZ = posZ + (rand.nextFloat() * 2.0f - 1.0f) * 16.0f;
		}
		if (courseChangeCooldown-- <= 0) {
			courseChangeCooldown += rand.nextInt(5) + 2;
			d4 = MathHelper.sqrt_double(d4);
			if (isCourseTraversable(waypointX, waypointY, waypointZ, d4)) {
				motionX += d0 / d4 * 0.1;
				motionY += d2 / d4 * 0.1;
				motionZ += d3 / d4 * 0.1;
			}
			else {
				waypointX = posX;
				waypointY = posY;
				waypointZ = posZ;
			}
		}
		if (targetedEntity != null && targetedEntity.isDead) {
			targetedEntity = null;
		}
		if (targetedEntity == null || aggroCooldown-- <= 0) {
			targetedEntity = worldObj.getClosestVulnerablePlayerToEntity(this, 100.0);
			if (targetedEntity != null) {
				aggroCooldown = 20;
			}
		}
		final double d5 = 64.0;
		if (targetedEntity != null && targetedEntity.getDistanceSqToEntity(this) < d5 * d5) {
			final double d6 = targetedEntity.posX - posX;
			final double d7 = targetedEntity.boundingBox.minY + targetedEntity.height / 2.0f - (posY + height / 2.0f);
			final double d8 = targetedEntity.posZ - posZ;
			final float n = -(float)Math.atan2(d6, d8) * 180.0f / 3.1415927f;
			rotationYaw = n;
			renderYawOffset = n;
			if (canEntityBeSeen(targetedEntity)) {
				++attackCounter;
				if (attackCounter == 10) {
					final EntityConstructTerrorShot entitylargefireball = new EntityConstructTerrorShot(worldObj, this);
					final double var3 = targetedEntity.posX - posX;
					final double var4 = targetedEntity.posY + targetedEntity.getEyeHeight() - 1.100000023841858 - entitylargefireball.posY;
					final double var5 = targetedEntity.posZ - posZ;
					final float var6 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2f;
					entitylargefireball.setThrowableHeading(var3, var4 + var6, var5, 1.6f, 12.0f);
					worldObj.spawnEntityInWorld(entitylargefireball);
					attackCounter = -10;
				}
			}
			else if (attackCounter > 0) {
				--attackCounter;
			}
		}
		else {
			final float n2 = -(float)Math.atan2(motionX, motionZ) * 180.0f / 3.1415927f;
			rotationYaw = n2;
			renderYawOffset = n2;
			if (attackCounter > 0) {
				--attackCounter;
			}
		}
		if (!worldObj.isRemote) {
			final byte b1 = dataWatcher.getWatchableObjectByte(16);
			final byte b2 = (byte)((attackCounter > 10) ? 1 : 0);
			if (b1 != b2) {
				dataWatcher.updateObject(16, b2);
			}
		}
	}

	private boolean isCourseTraversable(final double par1, final double par3, final double par5, final double par7) {
		final double d4 = (waypointX - posX) / par7;
		final double d5 = (waypointY - posY) / par7;
		final double d6 = (waypointZ - posZ) / par7;
		final AxisAlignedBB axisalignedbb = boundingBox.copy();
		for (int i = 1; i < par7; ++i) {
			axisalignedbb.offset(d4, d5, d6);
			if (!worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	protected String getLivingSound() {
		final int pick = rand.nextInt(6);
		if (pick == 1) {
			return "nevermine:CrystalConstructLiving1";
		}
		if (pick == 2) {
			return "nevermine:CrystalConstructLiving2";
		}
		if (pick == 3) {
			return "nevermine:CrystalConstructLiving3";
		}
		if (pick == 4) {
			return "nevermine:CrystalConstructLiving4";
		}
		if (pick == 5) {
			return "nevermine:CrystalConstructLiving6";
		}
		return "nevermine:CrystalConstructLiving5";
	}

	protected String getDeathSound() {
		return "nevermine:ConstructDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ConstructHit";
	}
}
