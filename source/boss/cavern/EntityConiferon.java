package net.nevermine.boss.cavern;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.assist.AddPackets;
import net.nevermine.boss.EntityBoss;
import net.nevermine.boss.cavern.holder.EntityHolderConiferon;
import net.nevermine.container.AncientBossesContainer;
import net.nevermine.gui.MobHitPacket;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.placement.EntityNoRange;

public class EntityConiferon extends EntityMob implements EntityNoRange, EntityBoss, CavernBoss {
	public EntityConiferon(final World par1World) {
		super(par1World);
		setSize(4.0f, 5.5f);
	}

	protected String getLivingSound() {
		return "nevermine:ConiferonLiving";
	}

	protected String getDeathSound() {
		return "nevermine:ConiferonDeath";
	}

	protected String getHurtSound() {
		return "nevermine:ConiferonHit";
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		if (rand.nextInt(10) == 4) {
			dropItem(Weaponizer.SelyanScythe, 1);
		}

		if (rand.nextInt(10) == 4) {
			dropItem(Weaponizer.SelyanStickler, 1);
		}

		if (rand.nextInt(5) == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.SelyanBanner), 1);
		}

		if (rand.nextInt(5) == 3) {
			dropItem(Item.getItemFromBlock(SpecialBlockizer.ConiferonStatue), 1);
		}
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 30.0);

		if (var1 == null || var1.getDistanceToEntity(this) > 30.0f) {
			return;
		}

		if (rand.nextInt(150) == 37) {
			playSound("nevermine:ConiferonSpawn", 1.85f, 1.0f);
			if (!worldObj.isRemote) {
				var1.addPotionEffect(new PotionEffect(Potion.poison.id, 60, 0));
			}

			if (var1 instanceof EntityPlayerMP) {
				AddPackets.network.sendTo(new MobHitPacket(70, 9), (EntityPlayerMP)var1);
			}
		}

		var1.addVelocity(Math.signum(posX - var1.posX) * 0.029, 0.0, Math.signum(posZ - var1.posZ) * 0.029);

		if (rand.nextInt(250) == 137) {
			var1.setPositionAndUpdate(posX, posY, posZ);
		}
	}

	protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
		playSound("mob.pig.step", 0.55f, 1.0f);
	}

	protected boolean canDespawn() {
		return false;
	}

	public void onDeath(final DamageSource var1) {
		super.onDeath(var1);

		AncientBossesContainer.killConiferon(this);
	}

	private void transform() {
		if (!worldObj.isRemote) {
			final EntityHolderConiferon var2 = new EntityHolderConiferon(worldObj);
			var2.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(var2);
			if (!worldObj.isRemote) {
				setDead();
			}
		}
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(25.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.3);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4000.0);
	}

	public boolean attackEntityAsMob(final Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);
		if (par1Entity != null) {
			if (par1Entity instanceof EntityPlayer && ((EntityPlayer)entityToAttack).getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue() != 1.0) {
				par1Entity.addVelocity(motionX * 10.5, motionY * 0.5, motionZ * 22.0);
			}
			if (par1Entity instanceof EntityLiving) {
				((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 140, 2));
			}
			return true;
		}
		return false;
	}
}
