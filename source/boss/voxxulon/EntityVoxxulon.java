package net.nevermine.boss.voxxulon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.EntityBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.event.creature.BossClear;
import net.nevermine.izer.SpecialBlockizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.mob.placement.EntityNoRange;
import net.nevermine.projectiles.staff.EntityMeteorFall;

import java.util.List;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityVoxxulon extends EntityMob implements EntityNoRange, EntityBoss {
	private int musicTick;

	public EntityVoxxulon(final World par1World) {
		super(par1World);
		musicTick = 1;
		setSize(2.5f, 2.5f);
	}

	protected String getLivingSound() {
		return "nevermine:VoxxulonLiving";
	}

	protected String getDeathSound() {
		return "nevermine:VoxxulonDeath";
	}

	protected String getHurtSound() {
		return "nevermine:VoxxulonHit";
	}

	public void onDeath(final DamageSource d) {
		super.onDeath(d);

		if (!worldObj.isRemote && d.getEntity() != null && d.getEntity() instanceof EntityPlayer) {
			final EntityPlayer p = (EntityPlayer)d.getEntity();

			IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.voxxulon.kill", p.getDisplayName());

			for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
				e.addChatMessage(msg);
			}

			PlayerContainer cont = PlayerContainer.getProperties(p);

			if (cont.getLevel(Hunter) >= 30)
				cont.addExperience(3200, Hunter);
		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		--musicTick;
		if (musicTick == 0) {
			musicTick = 130;
			playSound("nevermine:MusicVoxxulon", 2.8f, 1.0f);
		}
		motionX = 0.0;
		motionY = 0.0;
		motionZ = 0.0;
		if (rand.nextInt(200) == 43) {
			final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 60.0);
			if (var1 == null || var1.getDistanceToEntity(this) > 60.0f) {
				return;
			}
			if (!worldObj.isRemote) {
				setPosition(var1.posX, var1.posY, var1.posZ);
			}
			final float rotationPitch = var1.rotationPitch;
			final float rotationYaw = var1.rotationYaw;
			final double x = var1.posX;
			final double y = var1.posY + 1.62 - var1.yOffset;
			final double z = var1.posZ;
			final Vec3 worldVector = Vec3.createVectorHelper(x, y, z);
			final float yawAngleCos = MathHelper.cos(-rotationYaw * 0.01745329f - 3.1415927f);
			final float yawAngleSin = MathHelper.sin(-rotationYaw * 0.01745329f - 3.1415927f);
			final float pitchAngle = -MathHelper.cos(-rotationPitch * 0.01745329f);
			final float yVec = MathHelper.sin(-rotationPitch * 0.01745329f);
			final float xVec = yawAngleSin * pitchAngle;
			final float zVec = yawAngleCos * pitchAngle;
			final double multiplyer = 30.0;
			final Vec3 worldVector2 = worldVector.addVector(xVec * multiplyer, yVec * multiplyer, zVec * multiplyer);
			final MovingObjectPosition objPos = worldObj.rayTraceBlocks(worldVector, worldVector2);
			if (objPos != null && objPos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				int blockX = objPos.blockX;
				int blockY = objPos.blockY;
				int blockZ = objPos.blockZ;
				final int side = objPos.sideHit;
				if (side == 0) {
					--blockY;
				}
				if (side == 1) {
					++blockY;
				}
				if (side == 2) {
					--blockZ;
				}
				if (side == 3) {
					++blockZ;
				}
				if (side == 4) {
					--blockX;
				}
				if (side == 5) {
					++blockX;
				}
				if (!worldObj.isRemote) {
					for (int i = 0; i < 8; ++i) {
						worldObj.spawnEntityInWorld(new EntityMeteorFall(worldObj, blockX + 0.5, blockY + 25.0, blockZ + 0.5, this));
					}
				}
				var1.getLook(1.0f);
			}
		}
		if (rand.nextInt(200) == 77) {
			for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(40.0, 40.0, 40.0))) {
				if (e.capabilities.isCreativeMode)
					continue;

				e.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 4));
				playSound("nevermine:VoxxulonLiving", 1.65f, 1.0f);
			}
		}
		if (rand.nextInt(200) == 84) {
			for (final EntityPlayer e : (List<EntityPlayer>)worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(40.0, 40.0, 40.0))) {
				if (e.capabilities.isCreativeMode)
					continue;

				e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 4));
				playSound("nevermine:VoxxulonLiving", 1.65f, 1.0f);
			}
		}
		if (rand.nextInt(10) == 7) {
			final EntityPlayer var1 = worldObj.getClosestVulnerablePlayerToEntity(this, 30.0);
			if (var1 == null || var1.getDistanceToEntity(this) > 30.0f) {
				return;
			}
			final float rotationPitch = var1.rotationPitch;
			final float rotationYaw = var1.rotationYaw;
			final double x = var1.posX;
			final double y = var1.posY + 1.62 - var1.yOffset;
			final double z = var1.posZ;
			final Vec3 worldVector = Vec3.createVectorHelper(x, y, z);
			final float yawAngleCos = MathHelper.cos(-rotationYaw * 0.01745329f - 3.1415927f);
			final float yawAngleSin = MathHelper.sin(-rotationYaw * 0.01745329f - 3.1415927f);
			final float pitchAngle = -MathHelper.cos(-rotationPitch * 0.01745329f);
			final float yVec = MathHelper.sin(-rotationPitch * 0.01745329f);
			final float xVec = yawAngleSin * pitchAngle;
			final float zVec = yawAngleCos * pitchAngle;
			final double multiplyer = 30.0;
			final Vec3 worldVector2 = worldVector.addVector(xVec * multiplyer, yVec * multiplyer, zVec * multiplyer);
			final MovingObjectPosition objPos = worldObj.rayTraceBlocks(worldVector, worldVector2);
			if (objPos != null && objPos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				int blockX = objPos.blockX;
				int blockY = objPos.blockY;
				int blockZ = objPos.blockZ;
				final int side = objPos.sideHit;
				if (side == 0) {
					--blockY;
				}
				if (side == 1) {
					++blockY;
				}
				if (side == 2) {
					--blockZ;
				}
				if (side == 3) {
					++blockZ;
				}
				if (side == 4) {
					--blockX;
				}
				if (side == 5) {
					++blockX;
				}
				if (!worldObj.isRemote) {
					for (int i = 0; i < 8; ++i) {
						worldObj.spawnEntityInWorld(new EntityMeteorFall(worldObj, blockX + 0.5, blockY + 25.0, blockZ + 0.5, this));
					}
				}
				var1.getLook(1.0f);
			}
		}
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting != EnumDifficulty.PEACEFUL && worldObj.checkNoEntityCollision(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void dropFewItems(final boolean par1, final int par2) {
		dropItem(Item.getItemFromBlock(SpecialBlockizer.VoxxulonStatue), 1);
		final int choose = rand.nextInt(4);

		if (choose == 1) {
			dropItem(Weaponizer.ToxinBow, 1);
		}
		else if (choose == 2) {
			dropItem(Weaponizer.VileVanquisher, 1);
		}
		else if (choose == 3) {
			dropItem(Weaponizer.SludgeSniper, 1);
		}
		else {
			dropItem(Weaponizer.NoxiousStaff, 1);
		}

	}

	protected Entity findPlayerToAttack() {
		final EntityPlayer entityPlayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0);
		return ((entityPlayer != null && canEntityBeSeen(entityPlayer)) ? entityPlayer : null);
	}

	public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
		if (par1DamageSource == DamageSource.inWall) {
			BossClear.clear((int)posX, (int)posY, (int)posZ, worldObj, this);
		}
		final Entity entity = par1DamageSource.getSourceOfDamage();
		return !(entity instanceof EntityMeteorFall) && super.attackEntityFrom(par1DamageSource, par2);
	}

	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(30.0);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(52.0);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2000.0);
	}
}
