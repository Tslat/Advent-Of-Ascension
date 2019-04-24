package net.nevermine.mob.entity.runandor.templar;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.placement.*;

public class EntityRuneTemplarYellow extends EntityMob implements EntityNoRange, EntityNoBows, EntityNoMelee, EntityNoMagic, EntityNoFire, EntityNoExplosions, EntityObject {
	public EntityRuneTemplarYellow(final World par1World) {
		super(par1World);
		setSize(1.79f, 2.2f);
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getDeathSound() {
		return "random.anvil_land";
	}

	protected String getHurtSound() {
		return "random.anvil_land";
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		transform();
	}

	private void transform() {
		if (!worldObj.isRemote) {
			final EntityRuneTemplarYellowDisabled var2 = new EntityRuneTemplarYellowDisabled(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var2);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
	}

	public boolean canBePushed() {
		return false;
	}

	protected boolean canDespawn() {
		return false;
	}

	public void addVelocity(final double x, final double y, final double z) {
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		final int multiplier = worldObj.getEntitiesWithinAABB(EntityRunicLifeformYellow.class, boundingBox.expand(10.0, 10.0, 10.0)).size();
		final int peopleMultiplier = worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(30.0, 30.0, 30.0)).size();
		heal(0.075f * multiplier * peopleMultiplier);
		if (getHealth() < 2.5f) {
			if (!worldObj.isRemote) {
				dropItem(Itemizer.MegaRuneFragmentY, 1);
			}
			transform();
		}
		else {
			setHealth(getHealth() - 0.25f);
		}
		if (rand.nextInt(100) == 25 && !worldObj.isRemote) {
			final EntityRunicLifeformYellow var2 = new EntityRunicLifeformYellow(worldObj);
			var2.setLocationAndAngles(posX - 3.0 + rand.nextInt(6), posY, posZ - 3.0 + rand.nextInt(6), rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var2);
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400.0);
	}

	public boolean isEntityInvulnerable() {
		return true;
	}
}
