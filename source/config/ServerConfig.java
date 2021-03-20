package net.tslat.aoa3.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ServerConfig {
	public final ForgeConfigSpec.BooleanValue disableOverworldMobs;
	public final ForgeConfigSpec.BooleanValue allowUnsafeInfusion;
	public final ForgeConfigSpec.IntValue portalSearchRadius;
	public final ForgeConfigSpec.BooleanValue easyCorruptedTravellers;
	public final ForgeConfigSpec.BooleanValue allowNonPlayerPortalTravel;
	public final ForgeConfigSpec.DoubleValue globalXpModifier;
	public final ForgeConfigSpec.IntValue maxMinions;
	public final ForgeConfigSpec.BooleanValue saveLootFromExplosions;

	protected ServerConfig(ForgeConfigSpec.Builder configBuilder) {
		configBuilder.comment("AoA server-side configuration options").push("General Settings");

		disableOverworldMobs = configBuilder
				.comment("Set this to true to disable all overworld natural entity spawns.",
						"WARNING: This will make a lot of content inaccessible without further modifications.",
						"Use at your own risk!")
				.translation("config.aoa3.disableOverworldMobs")
				.define("disableOverworldMobs", false);

		allowUnsafeInfusion = configBuilder
				.comment("Set this to false to disable enchantments that breach level caps")
				.translation("config.aoa3.allowUnsafeInfusion")
				.define("allowUnsafeInfusion", true);

		portalSearchRadius = configBuilder
				.comment("Adjust this value to modify how far to look for safe/existing portal locations.",
						"The lower the value, the faster it runs")
				.translation("config.aoa3.portalSearchRadius")
				.defineInRange("portalSearchRadius", 24, 1, 128);

		easyCorruptedTravellers = configBuilder
				.comment("Set this to true to make Corrupted Travellers easier to find, causing them to glow through blocks when nearby.")
				.translation("config.aoa3.easyCorruptedTravellers")
				.define("easyCorruptedTravellers", false);

		allowNonPlayerPortalTravel = configBuilder
				.comment("Set to false to stop non-player entities from using the AoA portal system.")
				.translation("config.aoa3.allowNonPlayerPortalTravel")
				.define("allowNonPlayerPortalTravel", true);

		globalXpModifier = configBuilder
				.comment("Modifier for global xp gain for all players for AoA skills.",
						"Higher numbers means more xp gained")
				.translation("config.aoa3.globalXpModifier")
				.defineInRange("globalXpModifier", 1d, 0d, 1000d);

		maxMinions = configBuilder
				.comment("Configure the maximum amount of minions a player can have at a given time")
				.translation("config.aoa3.maxMinions")
				.defineInRange("maxMinions", 10, 1, 200);

		saveLootFromExplosions = configBuilder
				.comment("Set to false to stop AoA saving loot-drops from explosions.")
				.translation("config.aoa3.saveLootFromExplosions")
				.define("saveLootFromExplosions", true);

		configBuilder.pop();
	}
}
