package net.tslat.aoa3.hooks;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabBestiary;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabGuides;
import net.tslat.aoa3.client.gui.mainwindow.AdventMainGui;
import net.tslat.aoa3.hooks.crafttweaker.CraftTweakerCompat;
import net.tslat.aoa3.hooks.ic2.IC2Compat;
import net.tslat.aoa3.hooks.immersiveengineering.IECompat;
import net.tslat.aoa3.hooks.jer.JerHooks;
import net.tslat.aoa3.hooks.tconstruct.TinkersMaterialRegistry;
import net.tslat.aoa3.hooks.tconstruct.traits.Traits;
import net.tslat.aoa3.hooks.thaumcraft.ThaumcraftCompat;
import net.tslat.aoa3.hooks.thermalexpansion.ThermalExpansionCompat;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.skills.ButcheryUtil;
import net.tslat.aoa3.utils.skills.HunterUtil;
import net.tslat.aoa3.utils.skills.InnervationUtil;
import org.apache.logging.log4j.Level;

import java.util.Optional;
import java.util.function.Function;

public final class ThirdPartyInteractions {
	static boolean tinkersConstructActive = false;
	static boolean jeiActive = false;
	static boolean jerActive = false;
	static boolean ic2Active = false;
	static boolean craftTweakerActive = false;
	static boolean immersiveEngineeringActive = false;
	static boolean thaumcraftActive = false;
	static boolean thermalExpansionActive = false;

	public static boolean isIc2Active() {
		return ic2Active;
	}

	public static boolean isTinkersConstructActive() {
		return tinkersConstructActive;
	}

	public static boolean isJEIActive() {
		return jeiActive;
	}

	public static boolean isJERActive() {
		return jerActive;
	}

	public static boolean isCraftTweakerActive() {
		return craftTweakerActive;
	}

	public static boolean isImmersiveEngineeringActive() {
		return immersiveEngineeringActive;
	}

	public static boolean isThaumcraftActive() {
		return thaumcraftActive;
	}

	public static boolean isThermalExpansionActive() {
		return thermalExpansionActive;
	}

	public static void preInit() {
		AdventOfAscension.logOptionalMessage("Checking for third-party integrations");

		if (Loader.isModLoaded("tconstruct") && ConfigurationUtil.IntegrationsConfig.tinkersConstruct.enabled)
			tinkersPreInit();

		if (Loader.isModLoaded("jeresources") && ConfigurationUtil.IntegrationsConfig.jer.enabled)
			jeResourcesPreInit();

		if (Loader.isModLoaded("ic2") && ConfigurationUtil.IntegrationsConfig.ic2.enabled)
			ic2PreInit();

		if (Loader.isModLoaded("crafttweaker") && ConfigurationUtil.IntegrationsConfig.craftTweaker.enabled)
			craftTweakerPreInit();

		if (Loader.isModLoaded("jei") && ConfigurationUtil.IntegrationsConfig.jei.enabled)
			jeiActive = true;

		if (Loader.isModLoaded("immersiveengineering") && ConfigurationUtil.IntegrationsConfig.immersiveEngineering.enabled)
			immersiveEngineeringActive = true;

		if (Loader.isModLoaded("thaumcraft") && ConfigurationUtil.IntegrationsConfig.thaumcraft.enabled)
			thaumcraftPreInit();

		if (Loader.isModLoaded("thermalexpansion") && ConfigurationUtil.IntegrationsConfig.thermalExpansion.enabled)
			thermalExpansionActive = true;
	}

	public static void init() {
		if (tinkersConstructActive) {
			TinkersMaterialRegistry.init();
			Traits.init();
		}

		if (jerActive)
			JerHooks.init();

		if (ic2Active)
			IC2Compat.init();

		if (immersiveEngineeringActive)
			IECompat.init();

		if (thermalExpansionActive)
			ThermalExpansionCompat.init();
	}

	private static void jeResourcesPreInit() {
		AdventOfAscension.logOptionalMessage("Found JEResources, integrating");

		jerActive = true;
	}

	private static void tinkersPreInit() {
		AdventOfAscension.logOptionalMessage("Found Tinkers Construct, integrating");

		if (ConfigurationUtil.IntegrationsConfig.tinkersConstruct.materials)
			TinkersMaterialRegistry.preInit();

		Traits.preInit();

		tinkersConstructActive = true;
	}

	private static void ic2PreInit() {
		AdventOfAscension.logOptionalMessage("Found IC2, integrating");
		IC2Compat.preInit();

		ic2Active = true;
	}

	private static void craftTweakerPreInit() {
		AdventOfAscension.logOptionalMessage("Found CraftTweaker, integrating");
		CraftTweakerCompat.preInit();

		craftTweakerActive = true;
	}

	private static void thaumcraftPreInit() {
		AdventOfAscension.logOptionalMessage("Found Thaumcraft, integrating");
		ThaumcraftCompat.preInit();

		thaumcraftActive = true;
	}

	public static void handleInterModComms(ImmutableList<FMLInterModComms.IMCMessage> messages) {
		for (FMLInterModComms.IMCMessage message : messages) {
			switch (message.key) {
				case "mod_provides_guides":
					AdventGuiTabGuides.registerGuidesMod(message.getSender());
					break;
				case "mod_handles_bestiaries":
					if (!message.isFunctionMessage()) {
						AdventOfAscension.logMessage(Level.WARN, "Received invalidly formatted bestiary handling IMC from mod: " + message.getSender() + ", skipping.");

						return;
					}

					Optional<Function<EntityLivingBase, Tuple>> bestiaryFunction = message.getFunctionValue(EntityLivingBase.class, Tuple.class);

					if (!bestiaryFunction.isPresent()) {
						AdventOfAscension.logMessage(Level.WARN, "Received invalidly formatted bestiary handling IMC from mod: " + message.getSender() + ", skipping.");

						return;
					}

					AdventGuiTabBestiary.registerThirdPartyEntryHandler(message.getSender(), bestiaryFunction.get());
					break;
				case "add_advent_gui_theme":
					if (!message.isStringMessage()) {
						AdventOfAscension.logMessage(Level.WARN, "Received invalidly formatted advent gui theme IMC from mod: " + message.getSender() + ", skipping.");

						return;
					}

					Optional<Function<String, String>> themeFunction = message.getFunctionValue(String.class, String.class);

					if (!themeFunction.isPresent()) {
						AdventOfAscension.logMessage(Level.WARN, "Received invalidly formatted advent gui theme IMC from mod: " + message.getSender() + ", skipping.");

						return;
					}

					Function<String, String> resourceGrabberFunction = themeFunction.get();
					String name = resourceGrabberFunction.apply("name");
					ResourceLocation background = new ResourceLocation(message.getSender(), resourceGrabberFunction.apply("background"));
					ResourceLocation menuButtons = new ResourceLocation(message.getSender(), resourceGrabberFunction.apply("buttons"));
					ResourceLocation overlay = new ResourceLocation(message.getSender(), resourceGrabberFunction.apply("overlay"));

					AdventMainGui.addTheme(name, background, menuButtons, overlay);
					break;
				case "register_hunter_entity":
					if (!message.isStringMessage()) {
						AdventOfAscension.logMessage(Level.WARN, "Received invalidly formatted hunter entity registration IMC from mod: " + message.getSender() + ", skipping.");

						return;
					}

					HunterUtil.parseHunterCreatureRegistration(message.getStringValue());
					break;
				case "blacklist_heartstone_entity":
					if (!message.isStringMessage()) {
						AdventOfAscension.logMessage(Level.WARN, "Received invalidly formatted heartstone blacklist entity registration IMC from mod: " + message.getSender() + ", skipping.");

						return;
					}

					InnervationUtil.blacklistEntityIdFromHeartstones(new ResourceLocation(message.getStringValue()));
					break;
				case "blacklist_bloodlust_entity":
					if (!message.isStringMessage()) {
						AdventOfAscension.logMessage(Level.WARN, "Received invalidly formatted bloodlust blacklist entity registration IMC from mod: " + message.getSender() + ", skipping.");

						return;
					}

					ButcheryUtil.blacklistEntityIdFromBloodlusts(new ResourceLocation(message.getStringValue()));
					break;
				default:
					break;
			}
		}
	}
}
