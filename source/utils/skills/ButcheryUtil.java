package net.tslat.aoa3.utils.skills;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.misc.EntityBloodlust;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
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
		ResourceLocation entityId = EntityList.getKey(entity);

		if (entityId == null)
			return false;

		return !bloodlustBlacklist.contains(entityId.toString());
	}

	public static void tryCritical(LivingHurtEvent event, PlayerDataManager plData) {
		if (!ConfigurationUtil.MainConfig.resourcesEnabled || !ConfigurationUtil.MainConfig.skillsEnabled)
			return;

		if (AdventOfAscension.rand.nextInt(100) + 1 <= plData.stats().getResourceValue(Enums.Resources.RAGE)) {
			float multiplier = 1.0f;
			multiplier *= getCriticalMultiplier(plData.stats().getLevel(Enums.Skills.BUTCHERY));

			if (plData.stats().getResourceValue(Enums.Resources.RAGE) >= 180)
				multiplier *= 1.3f;

			event.setAmount(event.getAmount() * multiplier);

			if (plData.equipment().getCurrentFullArmourSet() == Enums.ArmourSets.BUTCHERY && AdventOfAscension.rand.nextBoolean())
				return;

			plData.stats().consumeResource(Enums.Resources.RAGE, 200.0f, true);
		}
	}

	public static void tryBloodlustSpawn(EntityPlayer pl, EntityLivingBase target) {
		if (ConfigurationUtil.MainConfig.skillsEnabled && AdventOfAscension.rand.nextInt(30) == 0 && canMobSpawnBloodlust(target))
			pl.world.spawnEntity(new EntityBloodlust(pl.world, target.getPosition()));
	}

	public static void blacklistEntityFromBloodlusts(Entity entity) {
		ResourceLocation entityId = EntityList.getKey(entity);

		if (entityId == null) {
			AdventOfAscension.logMessage(Level.WARN, "Unable to find registry ID for bloodlust blacklist: " + entity.getDisplayName());

			return;
		}

		bloodlustBlacklist.add(entityId.toString());
	}

	public static void blacklistEntityIdFromBloodlusts(ResourceLocation entityId) {
		if (!EntityList.isRegistered(entityId))
			AdventOfAscension.logOptionalMessage("Entity ID: " + entityId.toString() + " is not mapped to a registered ID. There might be an error in the bloodlusts blacklist config.");

		bloodlustBlacklist.add(entityId.toString());
	}
}
