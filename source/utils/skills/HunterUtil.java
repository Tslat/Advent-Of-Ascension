package net.tslat.aoa3.utils.skills;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Tuple;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;

public final class HunterUtil {
	private static final HashMap<Class<? extends EntityLivingBase>, Tuple<Integer, Float>> hunterCreatureMap = new HashMap<Class<? extends EntityLivingBase>, Tuple<Integer, Float>>();

	public static void registerHunterCreature(Class<? extends EntityLivingBase> creatureClass, int requiredLevel, float xpForKill) {
		if (!hunterCreatureMap.containsKey(creatureClass)) {
			hunterCreatureMap.put(creatureClass, new Tuple<Integer, Float>(requiredLevel, xpForKill));
		}
		else {
			AdventOfAscension.logOptionalMessage("Creature " + creatureClass.toString() + " is already registered as a hunter creature. Skipping duplicate entry");
		}
	}

	public static boolean canAttackTarget(@Nonnull EntityLivingBase target, @Nullable Entity attacker, boolean notifyPlayer) {
		if (attacker == null || !hunterCreatureMap.containsKey(target.getClass()))
			return true;

		int lvl = hunterCreatureMap.get(target.getClass()).getFirst();
		EntityPlayer player = attacker instanceof EntityPlayer ? (EntityPlayer)attacker : attacker instanceof EntityTameable ? (((EntityTameable)attacker).getOwner() instanceof EntityPlayer ? (EntityPlayer)((EntityTameable)attacker).getOwner() : null) : null;

		if (player == null)
			return false;

		boolean success = player.capabilities.isCreativeMode || (!target.world.isRemote && PlayerUtil.doesPlayerHaveLevel(player, Enums.Skills.HUNTER, lvl));

		if (!success && notifyPlayer && !player.world.isRemote)
			PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.HUNTER, lvl);

		return success;
	}

	public static boolean doesPlayerMeetHunterReq(@Nonnull EntityLivingBase hunterCreature, @Nonnull EntityPlayer player) {
		if (!hunterCreatureMap.containsKey(hunterCreature.getClass()))
			return true;

		return PlayerUtil.doesPlayerHaveLevel(player, Enums.Skills.HUNTER, hunterCreatureMap.get(hunterCreature.getClass()).getFirst());
	}

	public static boolean isHunterCreature(@Nonnull EntityLivingBase entity) {
		return hunterCreatureMap.containsKey(entity.getClass());
	}

	public static int getHunterLevel(@Nullable EntityLivingBase entity) {
		if (entity == null || !hunterCreatureMap.containsKey(entity.getClass()))
			return -1;

		return hunterCreatureMap.get(entity.getClass()).getFirst();
	}

	public static float getHunterXp(@Nullable EntityLivingBase entity) {
		if (entity == null || !hunterCreatureMap.containsKey(entity.getClass()))
			return -1;

		return hunterCreatureMap.get(entity.getClass()).getSecond();
	}
}
