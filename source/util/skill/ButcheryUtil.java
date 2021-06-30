package net.tslat.aoa3.util.skill;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.misc.BloodlustEntity;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import org.apache.logging.log4j.Level;

import java.util.HashSet;

public class ButcheryUtil {
	private static HashSet<String> bloodlustBlacklist = new HashSet<String>(1);

	public static float getTickRegen(int lvl) {
		if (lvl <= 19)
			return 0.05f;

		if (lvl <= 39)
			return 0.1f;

		if (lvl <= 59)
			return 0.15f;

		if (lvl <= 79)
			return 0.2f;

		if (lvl <= 94)
			return 0.25f;

		return 0.3f;
	}

	public static float getCriticalMultiplier(int lvl) {
		if (lvl <= 14)
			return 1.1f;

		if (lvl <= 29)
			return 1.2f;

		if (lvl <= 49)
			return 1.3f;

		if (lvl <= 69)
			return 1.4f;

		if (lvl <= 89)
			return 1.5f;

		return 1.6f;
	}

	public static int getExpDenominator(final int lvl) {
		if (lvl <= 4)
			return 4;

		if (lvl <= 14)
			return 7;

		if (lvl <= 29)
			return 11;

		if (lvl <= 44)
			return 20;

		if (lvl <= 59)
			return 35;

		if (lvl <= 74)
			return 45;

		if (lvl <= 89)
			return 60;

		if (lvl <= 94)
			return 80;

		return 110;
	}

	public static boolean canMobSpawnBloodlust(Entity entity) {
		ResourceLocation entityId = ForgeRegistries.ENTITIES.getKey(entity.getType());

		if (entityId == null)
			return false;

		return !bloodlustBlacklist.contains(entityId.toString());
	}

	public static void tryCritical(LivingHurtEvent event, PlayerDataManager plData) {
		if (!AoAConfig.COMMON.resourcesEnabled.get() || !AoAConfig.COMMON.skillsEnabled.get())
			return;

		if (RandomUtil.randomNumberBetween(51, 101) <= plData.stats().getResourceValue(Resources.RAGE)) {
			float multiplier = 1.0f;
			multiplier *= getCriticalMultiplier(plData.stats().getLevel(Skills.BUTCHERY));

			if (plData.stats().getResourceValue(Resources.RAGE) >= 180)
				multiplier *= 1.3f;

			event.setAmount(event.getAmount() * multiplier);

			if (plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.BUTCHERY && RandomUtil.fiftyFifty())
				return;

			plData.stats().consumeResource(Resources.RAGE, 200.0f, true);
		}
	}

	public static void tryBloodlustSpawn(PlayerEntity pl, LivingEntity target) {
		if (AoAConfig.COMMON.skillsEnabled.get() && RandomUtil.oneInNChance(30) && canMobSpawnBloodlust(target)) {
			BloodlustEntity bloodlust = new BloodlustEntity(AoAEntities.Misc.BLOODLUST.get(), pl.level);

			bloodlust.setPos(target.getX(), target.getY(), target.getZ());
			pl.level.addFreshEntity(bloodlust);
		}
	}

	public static void blacklistEntityFromBloodlusts(Entity entity) {
		ResourceLocation entityId = ForgeRegistries.ENTITIES.getKey(entity.getType());

		if (entityId == null) {
			Logging.logMessage(Level.WARN, "Unable to find registry ID for bloodlust blacklist: " + entity.getDisplayName());

			return;
		}

		bloodlustBlacklist.add(entityId.toString());
	}

	public static void blacklistEntityIdFromBloodlusts(ResourceLocation entityId) {
		if (!ForgeRegistries.ENTITIES.containsKey(entityId))
			Logging.logMessage(Level.DEBUG, "Entity ID: " + entityId.toString() + " is not mapped to a registered ID. There might be an error in the bloodlusts blacklist config.");

		bloodlustBlacklist.add(entityId.toString());
	}
}
