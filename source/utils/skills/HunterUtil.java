package net.tslat.aoa3.utils.skills;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;

public final class HunterUtil {
	private static final HashMap<Class<? extends EntityLivingBase>, Tuple<Integer, Float>> hunterCreatureMap = new HashMap<Class<? extends EntityLivingBase>, Tuple<Integer, Float>>();

	public static void parseHunterCreatureRegistration(String registrationEntry) {
		String[] entryParts = registrationEntry.split(" ");

		if (entryParts.length < 3) {
			AdventOfAscension.logOptionalMessage("Invalid format for hunter entity entry: \"" + registrationEntry + "\"");

			return;
		}

		try {
			String registryName = entryParts[0];
			int levelReq = MathHelper.clamp(Integer.parseInt(entryParts[1].replace("lvl:", "")), 1, 100);
			float hunterXp = MathHelper.clamp(Float.parseFloat(entryParts[2].replace("xp:", "")), 0, Float.MAX_VALUE);
			EntityEntry entityEntry = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(registryName));

			if (entityEntry == null) {
				AdventOfAscension.logOptionalMessage("Unable to find entity registered as: \"" + registryName + "\"");

				return;
			}

			Class<? extends Entity> entityClass = entityEntry.getEntityClass();

			if (EntityLivingBase.class.isAssignableFrom(entityClass)) {
				HunterUtil.registerHunterCreature((Class<? extends EntityLivingBase>)entityClass, levelReq, hunterXp);
				AdventOfAscension.logOptionalMessage("Registered hunter entity: " + registryName + ", Lvl: " + levelReq + ", Xp: " + hunterXp);
			}
			else {
				AdventOfAscension.logOptionalMessage("Entity \"" + registryName + "\" is does not extend EntityLivingBase. Hunter entities must be alive to apply");
			}
		}
		catch (NumberFormatException ex) {
			AdventOfAscension.logOptionalMessage("Invalid format for value: \"" + entryParts[1] + "\"", ex);
		}
	}

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
		if (!hunterCreatureMap.containsKey(hunterCreature.getClass()) || player.capabilities.isCreativeMode)
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
