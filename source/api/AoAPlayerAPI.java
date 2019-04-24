package net.tslat.aoa3.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import org.apache.logging.log4j.Level;

public final class AoAPlayerAPI {

	/**
	 * Returns the current level in a given AoA skill for the provided player.<br>
	 * Will return -1 if unable to find the requested skill or if the method otherwise fails.
	 *
	 * @param skillName the name of the skill you are requesting the level for.<br>
	 *                  Skill names should be in ALL CAPITALS.<br>
	 *                  Valid options are: ALCHEMY, ANIMA, AUGURY, BUTCHERY, CREATION,
	 *                  ENGINEERING, EXPEDITION, EXTRACTION, FORAGING, HAULING, HUNTER,
	 *                  INFUSION, INNERVATION, LOGGING, RUNATION
	 * @param player the {@code EntityPlayer} player to find the level for.
	 *
	 * @return an int representing the level in the player's given skill or -1 if the method fails.
	 */
	public static int getLevel(String skillName, EntityPlayer player) {
		if (skillName == null || player == null)
			return -1;

		try {
			Enums.Skills skill = Enums.Skills.valueOf(skillName.toUpperCase());
			AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (cap == null)
				return -1;

			return cap.getLevel(skill);
		}
		catch (IllegalArgumentException ex) {
			AdventOfAscension.getLogger().log(Level.ERROR, "Unknown skill name: " + skillName.toUpperCase() + ", dropping AoAPlayerAPI#getLevel() request.");
			ex.printStackTrace();
		}

		return -1;
	}

	/**
	 * Returns the player's current armour set name in string form if applicable.<br>
	 * Will only return full sets, and takes into account set-exclusive armour pieces.
	 *
	 * @param player the {@code EntityPlayer} player in which to check for armour
	 *
	 * @return A @Nonnull String object representing the player's current armour set if
	 * at all applicable. All other situations return an empty string.
	 */
	public static String getArmourSet(EntityPlayer player) {
		if (player == null)
			return "";

		AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

		if (cap == null)
			return "";

		Enums.ArmourSets set = cap.getArmourSetType();

		if (set == null)
			return "";

		return set.toString();
	}

	/**
	 * Returns a float value representing the current value of the given resource type for the provided player.
	 *
	 * @param resource the {@code String} name of the resource you are requesting the value for.<br>
	 *                 Resource names should be ALL CAPITALS.<br>
	 *                 Valid options are: CREATION, SOUL, ENERGY, RAGE
	 * @param player the {@code EntityPlayer} player to find the resource value for.
	 *
	 * @return a float value representing the player's current resource amount.<br>
	 * Will return -1 if unable to find the requested resource or if the method otherwise fails.
	 */
	public static float getResourceValue(String resource, EntityPlayer player) {
		if (resource == null || player == null)
			return -1;

		try {
			Enums.Resources res = Enums.Resources.valueOf(resource.toUpperCase());
			AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (cap == null)
				return -1;

			return cap.getResourceValue(res);
		}
		catch (IllegalArgumentException ex) {
			AdventOfAscension.getLogger().log(Level.ERROR, "Unknown resource name: " + resource.toUpperCase() + ", dropping AoAPlayerAPI#getResourceValue() request.");
			ex.printStackTrace();
		}

		return -1;
	}

	/**
	 * Returns a float value representing the current value of the given resource type for the provided player.<br>
	 * Client-side values are never as accurate as server side. Use this method for display purposes only.<br>
	 * This method is <u>client side only</u><br>
	 * <br>
	 * NOTE: Creation is divided by 600 to give the gui bar value.<br>
	 *       Soul Power is divided by 3000 to give the gui bar value.
	 *
	 * @param resource the {@code String} name of the resource you are requesting the value for.<br>
	 *                 Resource names should be ALL CAPITALS.<br>
	 *                 Valid options are: CREATION, SOUL, ENERGY, RAGE
	 *
	 * @return a float value representing the player's current client-side resource amount.<br>
	 * Will return -1 if unable to find the requested resource or if the method otherwise fails.
	 */
	@SideOnly(Side.CLIENT)
	public static float getClientResourceValue(String resource) {
		if (resource == null)
			return -1;

		try {
			Enums.Resources res = Enums.Resources.valueOf(resource.toUpperCase());

			switch (res) {
				case CREATION:
					return AdventGuiTabPlayer.resourceCreation;
				case ENERGY:
					return AdventGuiTabPlayer.resourceEnergy;
				case RAGE:
					return AdventGuiTabPlayer.resourceRage;
				case SOUL:
					return AdventGuiTabPlayer.resourceSoul;
			}
		}
		catch (IllegalArgumentException ex) {
			AdventOfAscension.getLogger().log(Level.ERROR, "Unknown resource name: " + resource.toUpperCase() + ", dropping AoAPlayerAPI#getResourceValue() request.");
			ex.printStackTrace();
		}

		return -1;
	}

	/**
	 * Consume an amount of a provided resource type for the given player.<br>
	 * If using forceConsume = true, it is recommended to check whether the player has enough resource available
	 * beforehand as the method could return an indistinguishable false if the method fails or the player does not
	 * have enough resource remaining.
	 *
	 * @param resource the {@code String} name of the resource you are requesting the value for.<br>
	 *                 Resource names should be ALL CAPITALS.<br>
	 *                 Valid options are: CREATION, SOUL, ENERGY, RAGE
	 * @param amount a {@code float} value representing how much resource to consume in the provided resource type.<br>
	 *                  Min value: 0
	 * @param forceConsume a {@code boolean} to decide whether the method should consume the resource even if the player
	 *                     does not have enough remaining to complete the request.
	 * @param player the {@code EntityPlayer} player to consume the resource amount for.
	 *
	 * @return a boolean representing whether the given player had enough resource to successfully complete the method.
	 * If forceConsume is true, the method will consume whatever resource is available down to 0 and return as normal.<br>
	 * Will return false if unable to find the requested resource or if the method otherwise fails
	 */
	public static boolean consumeResource(String resource, float amount, boolean forceConsume, EntityPlayer player) {
		if (resource == null || player == null)
			return false;

		try {Enums.Resources res = Enums.Resources.valueOf(resource.toUpperCase());
			AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (cap == null)
				return false;

			return cap.consumeResource(res, amount, forceConsume);
		}
		catch (IllegalArgumentException ex) {
			AdventOfAscension.getLogger().log(Level.ERROR, "Unknown resource name: " + resource.toUpperCase() + ", dropping AoAPlayerAPI#regenResource() request.");
			ex.printStackTrace();
		}

		return false;
	}

	/**
	 * Regenerate a given amount of resource value in the provided resource for a given player.
	 *
	 * @param resource a {@code String} name of the resource you are wanting to regenerate.<br>
	 *                 Resource names should be ALL CAPITALS.<br>
	 *                 Valid options are: CREATION, SOUL, ENERGY, RAGE
	 * @param amount a {@code float} value representing how much resource to regen in the provided resource type.<br>
	 *                  Min value: 0
	 * @param player the {@code EntityPlayer} player to regenerate the resource amount for.
	 */
	public static void regenResource(String resource, float amount, EntityPlayer player) {
		if (resource == null || player == null)
			return;

		try {
			Enums.Resources res = Enums.Resources.valueOf(resource.toUpperCase());
			AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (cap == null)
				return;

			cap.resourceRegen(res, Math.max(amount, 0));
		}
		catch (IllegalArgumentException ex) {
			AdventOfAscension.getLogger().log(Level.ERROR, "Unknown resource name: " + resource.toUpperCase() + ", dropping AoAPlayerAPI#regenResource() request.");
			ex.printStackTrace();
		}
	}

	/**
	 * Returns an int value representing the current tribute amount for given deity for the provided player.<br>
	 * Will return -1 if unable to find the deity, or if the method otherwise fails
	 *
	 * @param deityName the name of the deity in which to retrieve the player's current tribute value.<br>
	 *                  Deity names should be ALL CAPITALS.<br>
	 *                  Valid options are: EREBON, LUXON, PLUTON, SELYAN
	 * @param player the {@code EntityPlayer} player to find the tribute value for.
	 *
	 * @return an int value representing the player's current tribute amount.<br>
	 * Will return -1 if the method fails.
	 */
	public static int getTributeValue(String deityName, EntityPlayer player) {
		if (deityName == null || player == null)
			return -1;

		try {
			Enums.Deities deity = Enums.Deities.valueOf(deityName.toUpperCase());
			AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (cap == null)
				return -1;

			return cap.getTribute(deity);
		}
		catch (IllegalArgumentException ex) {
			AdventOfAscension.getLogger().log(Level.ERROR, "Unknown deity name: " + deityName.toUpperCase() + ", dropping AoAPlayerAPI#getTributeValue() request.");
			ex.printStackTrace();
		}

		return -1;
	}

	/**
	 * Increases the tribute value for the provided deity for a given player.
	 *
	 * @param deityName the name of the deity in which to increase the player's current tribute value.<br>
	 *                  Deity names should be ALL CAPITALS.<br>
	 *                  Valid options are: EREBON, LUXON, PLUTON, SELYAN
	 * @param amount the {@code int} amount of tribute to add to the player's tribute bar for the provided deity.<br>
	 *               Min value: 0<br>
	 *               Max value: 200
	 * @param player the {@code EntityPlayer} player to increase the tribute value for.
	 */
	public static void increaseTribute(String deityName, int amount, EntityPlayer player) {
		if (deityName == null || player == null)
			return;

		try {
			Enums.Deities deity = Enums.Deities.valueOf(deityName.toUpperCase());
			AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (cap == null)
				return;

			cap.addTribute(deity, Math.max(0, amount));
		}
		catch (IllegalArgumentException ex) {
			AdventOfAscension.getLogger().log(Level.ERROR, "Unknown deity name: " + deityName.toUpperCase() + ", dropping AoAPlayerAPI#increaseTribute() request.");
			ex.printStackTrace();
		}
	}

	/**
	 * Returns the revenge target for the provider player if applicable.
	 *
	 * @param player the {@code EntityPlayer} player for which to find the revenge target
	 *
	 * @return An {@code EntityLivingBase} object representing the current revenge target.<br>
	 * Will return {@code null} if no target found or the method otherwise fails
	 */
	public static EntityLivingBase getVulcaneTarget(EntityPlayer player) {
		if (player == null)
			return null;

		AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

		if (cap == null)
			return null;

		return cap.getRevengeTarget();
	}

	/**
	 * Sets the provided target as the revenge target for the provided player.
	 *
	 * @param target an {@code EntityLivingBase} object to set as the player's revenge target.
	 * @param player the {@code EntityPlayer} player for which to set the revenge target for.
	 */
	public static void setVulcanismTarget(EntityLivingBase target, EntityPlayer player) {
		if (target == null || player == null)
			return;

		AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

		if (cap == null)
			return;

		cap.enableRevenge(target);
	}

	/**
	 * Ends the revenge period for the provided player.
	 *
	 * @param player the {@code EntityPlayer} player for which to end the revenge for.
	 */
	public static void endRevenge(EntityPlayer player) {
		if (player == null)
			return;

		AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

		if (cap == null)
			return;

		cap.disableRevenge();
	}

	/**
	 * Returns a float value representing the provided player's current xp gained at the current level.<br>
	 * Xp resets to 0 at each level up.
	 *
	 * @param skillName a {@code String} object representing the name of the skill to find the xp value for.<br>
	 *                  Skill names should be in ALL CAPITALS.<br>
	 *                  Valid options are: ALCHEMY, ANIMA, AUGURY, BUTCHERY, CREATION,
	 *                  ENGINEERING, EXPEDITION, EXTRACTION, FORAGING, HAULING, HUNTER,
	 *                  INFUSION, INNERVATION, LOGGING, RUNATION
	 * @param player the {@code EntityPlayer} player for which to find the xp value for.
	 *
	 * @return a float value representing the player's current xp at the current level.
	 * Will return -1 if unable to find the given skill or if the method otherwise fails.
	 */
	public static float getCurrentXp(String skillName, EntityPlayer player) {
		if (skillName == null || player == null)
			return -1;

		try {
			Enums.Skills skill = Enums.Skills.valueOf(skillName.toUpperCase());
			AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (cap == null)
				return -1;

			return cap.getExp(skill);
		}
		catch (IllegalArgumentException ex) {
			AdventOfAscension.getLogger().log(Level.ERROR, "Unknown skill name: " + skillName.toUpperCase() + ", dropping AoAPlayerAPI#getCurrentXp() request.");
			ex.printStackTrace();
		}

		return -1;
	}

	/**
	 * Returns a float value representing the provided player's required xp amount until next level-up.<br>
	 *
	 * @param skillName a {@code String} object representing the name of the skill to find the xp value for.<br>
	 *                  Skill names should be in ALL CAPITALS.<br>
	 *                  Valid options are: ALCHEMY, ANIMA, AUGURY, BUTCHERY, CREATION,
	 *                  ENGINEERING, EXPEDITION, EXTRACTION, FORAGING, HAULING, HUNTER,
	 *                  INFUSION, INNERVATION, LOGGING, RUNATION
	 * @param player the {@code EntityPlayer} player for which to find the xp value for.
	 *
	 * @return a float value representing the xp the player needs to gain to level up in the given skill.<br>
	 * Will return -1 if unable to find the given skill or if the method otherwise fails.
	 */
	public static float getXpToNextLevel(String skillName, EntityPlayer player) {
		if (skillName == null || player == null)
			return -1;

		try {
			Enums.Skills skill = Enums.Skills.valueOf(skillName.toUpperCase());
			AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (cap == null)
				return -1;

			return cap.getXpRemaining(skill);
		}
		catch (IllegalArgumentException ex) {
			AdventOfAscension.getLogger().log(Level.ERROR, "Unknown skill name: " + skillName.toUpperCase() + ", dropping AoAPlayerAPI#getCurrentXp() request.");
			ex.printStackTrace();
		}

		return -1;
	}

	/**
	 * Returns a int percentage value representing how far through a skill level the player is.<br>
	 * Will always return a value between 0 and 100 (inclusive), except if the method fails.<br>
	 * This method is <u>client side only</u>.
	 *
	 * @param skillName a {@code String} representing the skill name to provide the percentage for.<br>
	 *                  Skill names should be in ALL CAPITALS.<br>
	 *                  Valid options are: ALCHEMY, ANIMA, AUGURY, BUTCHERY, CREATION,
	 *                  ENGINEERING, EXPEDITION, EXTRACTION, FORAGING, HAULING, HUNTER,
	 *                  INFUSION, INNERVATION, LOGGING, RUNATION
	 *
	 * @return an int value representing the percentage complete for the current level of the provided skill.<br>
	 * Will return -1 if unable to find the skill by name or if the method otherwise fails
	 */
	@SideOnly(Side.CLIENT)
	public static int getPercentLevelComplete(String skillName) {
		if (skillName == null)
			return -1;
		
		try {
			Enums.Skills skill = Enums.Skills.valueOf(skillName.toUpperCase());

			return AdventGuiTabPlayer.getPercentCompleteLevel(skill);
		}
		catch (IllegalArgumentException ex) {
			AdventOfAscension.getLogger().log(Level.WARN, "Invalid skill name request getPercentLevelComplete API call: " + skillName);

			return -1;
		}
	}

	/**
	 * Returns a int value representing the current level a player is in a given skill.<br>
	 * Will always return a value between 1 and 1000 (inclusive), except if the method fails.<br>
	 * This method is <u>client side only</u>.
	 *
	 * @param skillName a {@code String} representing the skill name to provide the level for.<br>
	 *                  Skill names should be in ALL CAPITALS.<br>
	 *                  Valid options are: ALCHEMY, ANIMA, AUGURY, BUTCHERY, CREATION,
	 *                  ENGINEERING, EXPEDITION, EXTRACTION, FORAGING, HAULING, HUNTER,
	 *                  INFUSION, INNERVATION, LOGGING, RUNATION
	 *
	 * @param includeVanityLevels a {@code boolean} to decide whether the method should return vanity levels or not.<br>
	 *                            Also takes into account whether the player is currently viewing vanity levels.
	 *
	 * @return an int value representing the current level in a given skill.<br>
	 * Will return -1 if unable to find the skill by name or if the method otherwise fails
	 */
	@SideOnly(Side.CLIENT)
	public static int getClientSkillLevel(String skillName, boolean includeVanityLevels) {
		if (skillName == null)
			return -1;

		try {
			Enums.Skills skill = Enums.Skills.valueOf(skillName.toUpperCase());
			int lvl = AdventGuiTabPlayer.getSkillLevel(skill);

			return ConfigurationUtil.vanityLevels && includeVanityLevels ? lvl : Math.min(lvl, 100);
		}
		catch (IllegalArgumentException ex) {
			AdventOfAscension.getLogger().log(Level.WARN, "Invalid skill name request getClientSkillLevel API call: " + skillName);

			return -1;
		}
	}

	/**
	 * Returns a float value representing the current xp a player has in a given skill.<br>
	 * This method is <u>client side only</u>.
	 *
	 * @param skillName a {@code String} representing the skill name to provide the xp for.<br>
	 *                  Skill names should be in ALL CAPITALS.<br>
	 *                  Valid options are: ALCHEMY, ANIMA, AUGURY, BUTCHERY, CREATION,
	 *                  ENGINEERING, EXPEDITION, EXTRACTION, FORAGING, HAULING, HUNTER,
	 *                  INFUSION, INNERVATION, LOGGING, RUNATION
	 *
	 * @return a float value representing the current xp in a given skill.<br>
	 * Will return -1 if unable to find the skill by name or if the method otherwise fails
	 */
	@SideOnly(Side.CLIENT)
	public static float getClientSkillXp(String skillName) {
		if (skillName == null)
			return -1;

		try {
			Enums.Skills skill = Enums.Skills.valueOf(skillName.toUpperCase());

			return AdventGuiTabPlayer.getSkillXp(skill);
		}
		catch (IllegalArgumentException ex) {
			AdventOfAscension.getLogger().log(Level.WARN, "Invalid skill name request getClientSkillXp API call: " + skillName);

			return -1;
		}
	}
}
