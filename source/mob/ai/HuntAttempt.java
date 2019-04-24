package net.nevermine.mob.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.nevermine.assist.StringUtil;
import net.nevermine.container.PlayerContainer;

import static net.nevermine.container.PlayerContainer.Skills.Hunter;

public class HuntAttempt {
	public static Boolean Hunt(final Entity entity, final int hunterReq, final DamageSource dmgSrc) {
		if ((entity instanceof EntityPlayer || projectileTest(entity) || dmgSrc.getEntity() instanceof EntityPlayer) && !entity.worldObj.isRemote) {
			EntityPlayer p;

			if (entity instanceof EntityPlayer) {
				p = (EntityPlayer)entity;
			}
			else if (dmgSrc.getEntity() instanceof EntityPlayer) {
				p = (EntityPlayer)dmgSrc.getEntity();
			}
			else {
				p = (EntityPlayer)((EntityThrowable)entity).getThrower();
			}

			if (p.capabilities.isCreativeMode || PlayerContainer.getProperties(p).getLevel(Hunter) >= hunterReq) {
				return true;
			}
			else {
				p.addChatMessage(StringUtil.getLocaleWithArguments("message.feedback.hunterRequirement", Integer.toString(hunterReq)));
			}
		}
		return false;
	}

	private static boolean projectileTest(final Entity entity) {
		return entity instanceof EntityThrowable && ((EntityThrowable)entity).getThrower() instanceof EntityPlayer;
	}
}
