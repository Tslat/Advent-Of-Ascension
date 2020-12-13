package net.tslat.aoa3.util.skill;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.Logging;
import org.apache.logging.log4j.Level;

import java.util.HashSet;
import java.util.UUID;

public class InnervationUtil {
	public static final UUID INNERVATION_HEALTH_BUFF = UUID.fromString("81746891-97cf-4eef-9c67-13c120f40032");
	private static HashSet<String> heartstoneBlacklist = new HashSet<String>(1);

	public static int getExpDenominator(final int lvl) {
		if (lvl < 5)
			return 4;

		if (lvl < 15)
			return 7;

		if (lvl < 30)
			return 11;

		if (lvl < 45)
			return 25;

		if (lvl < 60)
			return 40;

		if (lvl < 75)
			return 60;

		if (lvl < 90)
			return 80;

		if (lvl < 95)
			return 100;

		return 150;
	}

	public static double getHealthBuff(final int lvl) {
		return Math.floor(Math.min(100, lvl) / 5d);
	}

	public static float getHeartstoneHealAmount(final int lvl) {
		if (lvl < 8)
			return 0.0f;

		if (lvl < 15)
			return 1.0f;

		if (lvl < 37)
			return 2.0f;

		if (lvl < 70)
			return 3.0f;

		return 4.0f;
	}

	public static AttributeModifier getHealthModifier(int level) {
		return new AttributeModifier(INNERVATION_HEALTH_BUFF, "AoAInnervationHealthBuff", getHealthBuff(level), AttributeModifier.Operation.ADDITION);
	}

	public static boolean canEntitySpawnHeartstone(Entity entity) {
		ResourceLocation entityId = ForgeRegistries.ENTITIES.getKey(entity.getType());

		if (entityId == null)
			return false;

		return !heartstoneBlacklist.contains(entityId.toString());
	}

	public static void blacklistEntityFromHeartstones(Entity entity) {
		ResourceLocation entityId = ForgeRegistries.ENTITIES.getKey(entity.getType());

		if (entityId == null) {
			Logging.logMessage(Level.WARN, "Unable to find registry ID for heartstone blacklist: " + entity.getDisplayName());

			return;
		}

		heartstoneBlacklist.add(entityId.toString());
	}

	public static void blacklistEntityIdFromHeartstones(ResourceLocation entityId) {
		if (!ForgeRegistries.ENTITIES.containsKey(entityId))
			Logging.logMessage(Level.DEBUG, "Entity ID: " + entityId.toString() + " is not mapped to a registered ID. There might be an error in the heartstones blacklist config.");

		heartstoneBlacklist.add(entityId.toString());
	}
}
