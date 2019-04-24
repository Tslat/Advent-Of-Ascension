package net.nevermine.mob.entity.overworld;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.ai.HuntAttempt;
import net.nevermine.mob.placement.EntityHunter;
import net.nevermine.mob.placement.EntityNoBows;
import net.nevermine.mob.placement.EntityNoRange;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityPincher extends EntityMob implements EntityHunter, EntityNoRange, EntityNoBows {
	private int angerLevel;
	private int randomSoundDelay;

	public int getLevReq() {
		return 93;
	}

	public EntityPincher(final World var1) {
		super(var1);
		angerLevel = 0;
		randomSoundDelay = 5;
		setSize(1.7f, 0.75f);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(150.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(17.0);
	}

	public boolean handleWaterMovement() {
		return false;
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(75) == 43) {
			dropItem(Weaponizer.AtlanticStaff, 1);
		}

		if (rand.nextInt(75) == 58) {
			dropItem(Weaponizer.AtlanticBow, 1);
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && posY < 55.0 && rand.nextInt(5) == 2 && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty();
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.SeaBanner);
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);
		if (var1.getEntity() != null && var1.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)var1.getEntity()).addExperience(6000.0f, Hunter);
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
		return "nevermine:PincherLiving";
	}

	protected String getDeathSound() {
		return "nevermine:PincherHit";
	}

	protected String getHurtSound() {
		return "nevermine:PincherHit";
	}

	public boolean attackEntityAsMob(final Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);

		if (entityToAttack != null) {
			if (entityToAttack instanceof EntityPlayer && ((EntityPlayer)entityToAttack).getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue() != 1.0) {
				if (rand.nextInt(5) < 4) {
					entityToAttack.addVelocity(motionX * -5.0, -2.0, motionZ * 0.0);
				}
				else {
					worldObj.playSoundAtEntity(entityToAttack, "nevermine:AtlanticStaff", 3.0f, 1.0f);
					((EntityPlayer)entityToAttack).setPositionAndUpdate(entityToAttack.posX - 4.0 + rand.nextInt(8), entityToAttack.posY + 35.0, entityToAttack.posZ - 4.0 + rand.nextInt(8));
				}
			}

			if (entityToAttack instanceof EntityLiving) {
				((EntityLiving)entityToAttack).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 0));
			}

			return true;
		}

		return false;
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
