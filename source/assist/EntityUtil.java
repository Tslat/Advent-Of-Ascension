package net.nevermine.assist;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.nevermine.boss.EntityBoss;
import net.nevermine.boss.cavern.CavernBoss;
import net.nevermine.container.PlayerContainer;
import net.nevermine.mob.placement.EntityHunter;
import net.nevermine.mob.placement.EntityNoRange;
import net.nevermine.mob.placement.EntitySpecialRange;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class EntityUtil {
	public static int getPercentageOfMaxHealth(EntityLivingBase entity) {
		float percentage = (entity.getHealth() / entity.getMaxHealth()) * 100;
		return (int)percentage;
	}

	public static boolean safelyConsumeItems(EntityPlayer pl, Item... items) {
		for (Item item : items) {
			if (!pl.inventory.hasItem(item)) {
				return false;
			}
		}

		for (Item item : items) {
			pl.inventory.consumeInventoryItem(item);
		}

		return true;
	}

	public static boolean isPowerfulSoul(Entity e) {
		return (e instanceof EntityBoss || e instanceof CavernBoss || e instanceof EntityWither || e instanceof IEntityMultiPart || e instanceof EntityPlayer);
	}

	public static boolean shootEntity(final Entity targ, final EntityLivingBase shooter, final EntityThrowable projectile, final float dmg) {
		if (targ == null || targ instanceof EntityNoRange || targ.isEntityInvulnerable())
			return false;

		EntityLivingBase target;
		if (targ instanceof EntityLivingBase) {
			target = (EntityLivingBase)targ;
		}
		else if (targ instanceof EntityDragonPart) {
			((EntityDragonPart)targ).entityDragonObj.attackEntityFromPart((EntityDragonPart)targ, DamageSource.causeThrownDamage(projectile, shooter), dmg);
			return true;
		}
		else if (targ instanceof EntityEnderCrystal) {
			targ.attackEntityFrom(DamageSource.causeThrownDamage(projectile, shooter), dmg);
			return true;
		}
		else {
			return false;
		}

		if (target instanceof EntitySpecialRange && !((EntitySpecialRange)target).canDamage(projectile, target, shooter, dmg))
			return false;

		if (target instanceof EntityHunter) {
			if (shooter instanceof EntityPlayer) {
				if (((EntityPlayer)shooter).capabilities.isCreativeMode || PlayerContainer.getProperties((EntityPlayer)shooter).getLevel(Hunter) >= ((EntityHunter)target).getLevReq()) {
					if (target.getHealth() > dmg) {
						target.setHealth(target.getHealth() - dmg);
						target.attackEntityFrom(DamageSource.causeThrownDamage(projectile, shooter), 0.0f);
					}
					else {
						target.attackEntityFrom(DamageSource.causeThrownDamage(projectile, shooter), dmg);
					}

					return true;
				}
				else {
					((EntityPlayer)shooter).addChatMessage(StringUtil.getLocaleWithArguments("message.feedback.hunterRequirement", Integer.toString(((EntityHunter)target).getLevReq())));
				}
			}
		}
		else {
			if (target.getHealth() > dmg) {
				target.setHealth(target.getHealth() - dmg);
				target.attackEntityFrom(DamageSource.causeThrownDamage(projectile, shooter), 0.0f);
			}
			else {
				target.attackEntityFrom(DamageSource.causeThrownDamage(projectile, shooter), dmg);
			}

			return true;
		}

		return false;
	}

	public static int[] getBlockAimingAt(World world, EntityPlayer player, double distance) {
		if (!world.isRemote) {
			float rotPitch = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch);
			float rotYaw = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw);
			double baseX = player.prevPosX + (player.posX - player.prevPosX);
			double baseY = player.prevPosY + (player.posY - player.prevPosY) + 1.62D - player.yOffset;
			double baseZ = player.prevPosZ + (player.posZ - player.prevPosZ);
			Vec3 fromVec = Vec3.createVectorHelper(baseX, baseY, baseZ);

			float cosYaw = MathHelper.cos(-rotYaw * 0.017453292F - (float)Math.PI);
			float sinYaw = MathHelper.sin(-rotYaw * 0.017453292F - (float)Math.PI);
			float cosPitch = -MathHelper.cos(-rotPitch * 0.017453292F);
			float sinPitch = MathHelper.sin(-rotPitch * 0.017453292F);
			float angleX = sinYaw * cosPitch;
			float angleZ = cosYaw * cosPitch;
			Vec3 toVec = fromVec.addVector(angleX * distance, sinPitch * distance, angleZ * distance);
			MovingObjectPosition ray = world.rayTraceBlocks(fromVec, toVec, true);

			if (ray == null)
				return null;

			int[] blockPos = new int[]{ray.blockX, ray.blockY, ray.blockZ};
			return blockPos;
		}

		return null;
	}
}
