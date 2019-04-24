package net.nevermine.mob.entity.overworld;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.corallus.EntityCorallus;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.mob.ai.HuntAttempt;
import net.nevermine.mob.placement.EntityHunter;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityAmphibiyte extends EntityMob implements EntityHunter {
	private int angerLevel;
	private int randomSoundDelay;

	public EntityAmphibiyte(final World var1) {
		super(var1);
		angerLevel = 0;
		randomSoundDelay = 5;
		setSize(1.95f, 1.25f);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.7);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return HuntAttempt.Hunt(entity, getLevReq(), par1DamageSource) && super.attackEntityFrom(par1DamageSource, par2);
	}

	private Item dropBanner() {
		return Item.getItemFromBlock(SpecialBlockizer.SeaBanner);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Itemizer.CopperCoin, 5 + rand.nextInt(10));
		dropItem(Itemizer.AmphibiyteLung, 1);

		if (rand.nextInt(7) == 0) {
			dropItem(dropBanner(), 1);
		}

	}

	protected float getSoundVolume() {
		return 0.4f;
	}

	public boolean getCanSpawnHere() {
		final int var1 = MathHelper.floor_double(posX);
		final int var2 = MathHelper.floor_double(boundingBox.minY);
		final int var3 = MathHelper.floor_double(posZ);
		return worldObj.handleMaterialAcceleration(boundingBox.expand(0.0, -0.6000000238418579, 0.0), Material.water, this);
	}

	protected String getLivingSound() {
		return "nevermine:AmphibiyteLiving";
	}

	protected String getDeathSound() {
		return "nevermine:AmphibiyteDeath";
	}

	protected String getHurtSound() {
		return "nevermine:AmphibiyteHit";
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
			if (entityToAttack != null && entityToAttack.posY > posY) {
				motionY = 0.25;
			}
			fallDistance = -0.5f;
		}

		if (isInWater()) {
			if (motionX > -1.100000023841858 && motionX < 1.100000023841858) {
				motionX *= 1.2000000476837158;
			}
			if (motionZ > -1.100000023841858 && motionZ < 1.100000023841858) {
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
				if (var6 instanceof EntityAmphibiyte) {
					((EntityAmphibiyte)var6).becomeAngryAt(var3);
				}
			}

			becomeAngryAt(var3);
		}

		return super.attackEntityFrom(var1, (float)var2);
	}

	private void becomeAngryAt(final Entity var1) {
		entityToAttack = var1;
		angerLevel = 400 + rand.nextInt(400);
		randomSoundDelay = rand.nextInt(40);
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);

		if (d.getEntity() != null && !worldObj.isRemote && d.getEntity() instanceof EntityPlayer) {
			PlayerContainer.getProperties((EntityPlayer)d.getEntity()).addExperience(25.0f, Hunter);
		}

		if (rand.nextInt(50) == 32 && worldObj.provider.dimensionId != ConfigurationHelper.immortallis && worldObj.provider.dimensionId != ConfigurationHelper.ancientcavern) {
			if (!worldObj.isRemote) {
				final EntityCorallus var2 = new EntityCorallus(worldObj);
				var2.setLocationAndAngles(posX, posY + 7.0, posZ, rand.nextFloat() * 360.0f, 0.0f);
				playSound("nevermine:CorallusSpawn", 0.85f, 1.0f);
				worldObj.spawnEntityInWorld(var2);

				IChatComponent msg = StringUtil.getLocale("message.mob.corallus.spawn");

				for (final EntityPlayer e : (List<EntityPlayer>)d.getEntity().worldObj.getEntitiesWithinAABB(EntityPlayer.class, d.getEntity().boundingBox.expand(50.0, 50.0, 50.0))) {
					e.addChatMessage(msg);
				}
			}
		}
	}

	public int getLevReq() {
		return 30;
	}
}
