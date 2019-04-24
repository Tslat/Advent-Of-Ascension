package net.nevermine.mob.entity.overworld;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.mob.ai.HuntAttempt;
import net.nevermine.mob.placement.EntityHunter;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntitySkipper extends EntityMob implements EntityHunter {
	private int angerLevel;
	private int randomSoundDelay;

	public EntitySkipper(final World var1) {
		super(var1);
		angerLevel = 0;
		randomSoundDelay = 5;
		setSize(1.0f, 1.0f);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0);
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return HuntAttempt.Hunt(entity, getLevReq(), par1DamageSource) && super.attackEntityFrom(par1DamageSource, par2);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Items.clay_ball, 2 + rand.nextInt(6));
	}

	protected float getSoundVolume() {
		return 0.4f;
	}

	public boolean getCanSpawnHere() {
		final int counter = worldObj.getEntitiesWithinAABB(EntitySkipper.class, boundingBox.expand(20.0, 10.0, 20.0)).size();
		return counter <= 3 && worldObj.difficultySetting != EnumDifficulty.PEACEFUL && rand.nextInt(12) == 4 && dimension == 0 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty();
	}

	protected String getLivingSound() {
		return "nevermine:SkipperLiving";
	}

	protected String getDeathSound() {
		return "nevermine:SkipperHit";
	}

	protected String getHurtSound() {
		return "nevermine:SkipperHit";
	}

	public boolean handleWaterMovement() {
		return false;
	}

	public boolean isInWater() {
		return worldObj.handleMaterialAcceleration(boundingBox.expand(0.0, -0.6000000238418579, 0.0), Material.water, this);
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (isInWater()) {
			motionY += 0.07000000029802322;
			if (motionX > -1.2000000476837158 && motionX < 1.2000000476837158) {
				motionX *= 1.2000000476837158;
			}
			if (motionZ > -1.2000000476837158 && motionZ < 1.2000000476837158) {
				motionZ *= 1.2000000476837158;
			}
		}
	}

	public void onEntityUpdate() {
		super.onEntityUpdate();
		setAir(300);
	}

	protected void fall(final float var1) {
	}

	public boolean attackEntityFrom(final DamageSource var1, final int var2) {
		final Entity var3 = var1.getEntity();

		if (var3 instanceof EntityPlayer) {
			final List<Entity> var4 = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(32.0, 32.0, 32.0));

			for (int var5 = 0; var5 < var4.size(); ++var5) {
				final Entity var6 = var4.get(var5);

				if (var6 instanceof EntitySkipper) {
					((EntitySkipper)var6).becomeAngryAt(var3);
				}
			}

			becomeAngryAt(var3);
		}

		return super.attackEntityFrom(var1, (float)var2);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		if (!worldObj.isRemote && var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(20.0f, Hunter);
		}
	}

	private void becomeAngryAt(final Entity var1) {
		entityToAttack = var1;
		angerLevel = 400 + rand.nextInt(400);
		randomSoundDelay = rand.nextInt(40);
	}

	public int getLevReq() {
		return 5;
	}
}
