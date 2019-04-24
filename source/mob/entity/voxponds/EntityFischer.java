package net.nevermine.mob.entity.voxponds;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.mob.ai.HuntAttempt;
import net.nevermine.mob.placement.EntityHunter;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityFischer extends EntityMob implements EntityHunter {
	private int angerLevel;
	private int randomSoundDelay;

	public int getLevReq() {
		return 33;
	}

	public EntityFischer(final World var1) {
		super(var1);
		angerLevel = 0;
		randomSoundDelay = 5;
		setSize(1.0f, 1.0f);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0);
	}

	public boolean handleWaterMovement() {
		return false;
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY < 30.0 && rand.nextInt(150) == 2 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty();
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		if (var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(95.0f, Hunter);
		}
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return HuntAttempt.Hunt(entity, getLevReq(), par1DamageSource) && super.attackEntityFrom(par1DamageSource, par2);
	}

	protected float getSoundVolume() {
		return 0.4f;
	}

	protected String getLivingSound() {
		return "nevermine:GadgetoidLiving";
	}

	protected String getDeathSound() {
		return "nevermine:GadgetoidDeath";
	}

	protected String getHurtSound() {
		return "nevermine:GadgetoidHit";
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public boolean isInWater() {
		return worldObj.handleMaterialAcceleration(boundingBox.expand(0.0, -0.6000000238418579, 0.0), Material.water, this);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater()) {
			if (entityToAttack != null && entityToAttack.posY > posY) {
				motionY = 0.25;
			}

			fallDistance = -0.5f;
		}

		final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 15.0);

		if (var1 == null || var1.getDistanceToEntity(this) > 15.0f) {
			return;
		}

		if (PlayerContainer.getProperties(var1).getLevel(Hunter) >= 93 && !var1.isInWater()) {
			var1.addVelocity(Math.signum(posX - var1.posX) * 0.029, Math.signum(posY - var1.posY) * 0.015, Math.signum(posZ - var1.posZ) * 0.029);
		}

		if (isInWater()) {
			if (motionX > -1.100000023841858 && motionX < 1.100000023841858) {
				motionX *= 1.100000023841858;
			}

			if (motionZ > -1.100000023841858 && motionZ < 1.100000023841858) {
				motionZ *= 1.100000023841858;
			}
		}
	}

	public void onEntityUpdate() {
		super.onEntityUpdate();
		setAir(300);
	}

	protected void fall(final float var1) {
	}

	private void becomeAngryAt(final Entity var1) {
		entityToAttack = var1;
		angerLevel = 400 + rand.nextInt(400);
		randomSoundDelay = rand.nextInt(40);
	}
}
