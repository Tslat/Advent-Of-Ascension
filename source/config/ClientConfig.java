package net.tslat.aoa3.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.tslat.aoa3.client.gui.hud.HealthStatusRenderer;
import net.tslat.aoa3.client.render.custom.AoAResourceRenderer;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
import net.tslat.aoa3.util.AoAHaloUtil;

public final class ClientConfig {
	public final ForgeConfigSpec.BooleanValue showXpParticles;
	public final ForgeConfigSpec.BooleanValue showWelcomeMessage;
	public final ForgeConfigSpec.BooleanValue showPlayerHalos;
	public final ForgeConfigSpec.EnumValue<AoAHaloUtil.Type.Choosable> personalHaloPreference;
	public final ForgeConfigSpec.ConfigValue<String> adventGuiTheme;
	public final ForgeConfigSpec.BooleanValue thirdPartyBestiary;
	public final ForgeConfigSpec.EnumValue<AoASkillRenderer.ProgressRenderType> hudSkillProgressRenderType;
	public final ForgeConfigSpec.EnumValue<AoAResourceRenderer.HudResourcesPosition> hudResourcesPosition;
	public final ForgeConfigSpec.BooleanValue hudResourcesHorizontal;
	public final ForgeConfigSpec.BooleanValue disableHudPotionOffset;
	public final ForgeConfigSpec.BooleanValue useToasts;
	public final ForgeConfigSpec.BooleanValue rotatingTrophies;
	public final ForgeConfigSpec.BooleanValue partyDeaths;
	public final ForgeConfigSpec.EnumValue<HealthStatusRenderer.HealthRenderType> healthRenderType;

	ClientConfig(ForgeConfigSpec.Builder specBuilder) {
		specBuilder.comment("AoA client-side configuration options").push("General Settings");

		showXpParticles = specBuilder
				.comment("Set this to false to disable the small scrolling popups that appear when you gain xp in a skill")
				.translation("config.aoa3.client.showXpParticles")
				.define("showXpParticles", true);

		showWelcomeMessage = specBuilder
				.comment("Set this to false to disable the message that welcomes you to Advent of Ascension when logging in")
				.translation("config.aoa3.client.showWelcomeMessage")
				.define("showWelcomeMessage", true);

		showPlayerHalos = specBuilder
				.comment("Set this to false to hide player halos for those that have them")
				.translation("config.aoa3.client.showPlayerHalos")
				.define("showPlayerHalos", true);

		personalHaloPreference = specBuilder
				.comment("If multiple halos are available, which one would you prefer to have on?")
				.translation("config.aoa3.client.personalHaloPreference")
				.defineEnum("personalHaloPreference", AoAHaloUtil.Type.Choosable.Donator);

		adventGuiTheme = specBuilder
				.comment("The current theme for the main Advent of Ascension window.")
				.translation("config.aoa3.client.adventGuiTheme")
				.define("adventGuiTheme", "Default");

		thirdPartyBestiary = specBuilder
				.comment("Set this to false to disable third party and vanilla entities from showing in the bestiary")
				.translation("config.aoa3.client.thirdPartyBestiary")
				.define("thirdPartyBestiary", true);

		hudResourcesPosition = specBuilder
				.comment("Choose a corner to have your resources indicators render in.")
				.translation("config.aoa3.client.hudResourcesPosition")
				.defineEnum("hudResourcesPosition", AoAResourceRenderer.HudResourcesPosition.Top_Right);

		hudResourcesHorizontal = specBuilder
				.comment("Set this to false to switch to a vertical layout for the HUD resources")
				.translation("config.aoa3.client.hudResourcesHorizontal")
				.define("hudResourcesHorizontal", true);

		disableHudPotionOffset = specBuilder
				.comment("Set this to true to stop the skills and resources HUD elements shifting down when players have potion effects.")
				.translation("config.aoa3.client.disableHudPotionOffset")
				.define("disableHudPotionOffset", false);

		hudSkillProgressRenderType = specBuilder
				.comment("Choose which type of progress indication you would prefer for skills in the HUD")
				.translation("config.aoa3.client.hudSkillProgressRenderType")
				.defineEnum("hudSkillProgressRenderType", AoASkillRenderer.ProgressRenderType.Bar);

		useToasts = specBuilder
				.comment("Set this to false to use chat messages for mod feedback instead of toasts")
				.translation("config.aoa3.client.useToasts")
				.define("useToasts", true);

		rotatingTrophies = specBuilder
				.comment("Set this to false to disable rotation of entities on trophy blocks.")
				.translation("config.aoa3.client.rotatingTrophies")
				.define("rotatingTrophies", true);

		healthRenderType = specBuilder
				.comment("Select what type of rendering AoA replaces the vanila health bar with")
				.translation("config.aoa3.client.healthRenderType")
				.defineEnum("healthRenderType", HealthStatusRenderer.HealthRenderType.BAR_NUMERIC);

		specBuilder.pop();
		specBuilder.comment("Just for fun :)").push("Fun Options");

		partyDeaths = specBuilder
				.comment("Set this to true to enable party deaths")
				.translation("config.aoa3.client.partyDeaths")
				.define("partyDeaths", false);

		specBuilder.pop();
	}

	public void changeAdventGuiTheme(String themeKey) {
		adventGuiTheme.set(themeKey);
		adventGuiTheme.save();
	}
}
