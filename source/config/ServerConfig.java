package net.tslat.aoa3.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ServerConfig {
	public final StructureConfig STRUCTURES;
	public final OreConfig ORES;

	public final ForgeConfigSpec.BooleanValue disableOverworldMobs;
	public final ForgeConfigSpec.BooleanValue allowUnsafeInfusion;
	public final ForgeConfigSpec.IntValue portalSearchRadius;
	public final ForgeConfigSpec.BooleanValue easyCorruptedTravellers;
	public final ForgeConfigSpec.BooleanValue allowNonPlayerPortalTravel;
	public final ForgeConfigSpec.DoubleValue globalXpModifier;
	public final ForgeConfigSpec.IntValue maxMinions;
	public final ForgeConfigSpec.BooleanValue saveLootFromExplosions;
	public final ForgeConfigSpec.BooleanValue overworldEventsEnabled;
	public final ForgeConfigSpec.BooleanValue fullMoonEventEnabled;
	public final ForgeConfigSpec.DoubleValue bigDayEventChance;
	public final ForgeConfigSpec.DoubleValue creepDayEventChance;
	public final ForgeConfigSpec.DoubleValue deathDayEventChance;
	public final ForgeConfigSpec.DoubleValue soulScurryEventChance;
	public final ForgeConfigSpec.DoubleValue bloodHuntEventChance;
	public final ForgeConfigSpec.DoubleValue lunarInvasionEventChance;

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
		configBuilder.comment("Entity-related settings").push("Entity Settings");

		configBuilder.pop();
		configBuilder.comment("Configure random events that happen in the overworld each day or night").push("Overworld Events");

		overworldEventsEnabled = configBuilder
				.comment("Set to false to disable all overworld events entirely.")
				.translation("config.aoa3.overworldEventsEnabled")
				.define("overworldEventsEnabled", true);

		bigDayEventChance = configBuilder
				.comment("Chance per day for the Big Day Overworld event to occur. Chance is a percentage (0.5 = 50%. 1 = 100%). Set to 0 to disable entirely.")
				.translation("config.aoa3.bigDayEventChance")
				.defineInRange("bigDayEventChance", 0.02, 0, 1);

		creepDayEventChance = configBuilder
				.comment("Chance per day for the Creep Day Overworld event to occur. Chance is a percentage (0.5 = 50%. 1 = 100%). Set to 0 to disable entirely.")
				.translation("config.aoa3.creepDayEventChance")
				.defineInRange("creepDayEventChance", 0.022, 0, 1);

		deathDayEventChance = configBuilder
				.comment("Chance per day for the Death Day Overworld event to occur. Chance is a percentage (0.5 = 50%. 1 = 100%). Set to 0 to disable entirely.")
				.translation("config.aoa3.deathDayEventChance")
				.defineInRange("deathDayEventChance", 0.027, 0, 1);

		soulScurryEventChance = configBuilder
				.comment("Chance per day for the Soul Scurry Overworld event to occur. Chance is a percentage (0.5 = 50%. 1 = 100%). Set to 0 to disable entirely.")
				.translation("config.aoa3.soulScurryEventChance")
				.defineInRange("soulScurryEventChance", 0.01695, 0, 1);

		bloodHuntEventChance = configBuilder
				.comment("Chance per day for the Blood Hunt Overworld event to occur. Chance is a percentage (0.5 = 50%. 1 = 100%). Set to 0 to disable entirely.")
				.translation("config.aoa3.bloodHuntEventChance")
				.defineInRange("bloodHuntEventChance", 0.01695, 0, 1);

		lunarInvasionEventChance = configBuilder
				.comment("Chance per day for the Lunar Invasion Overworld event to occur. Chance is a percentage (0.5 = 50%. 1 = 100%). Set to 0 to disable entirely.")
				.translation("config.aoa3.lunarInvasionEventChance")
				.defineInRange("lunarInvasionEventChance", 0.03846, 0, 1);

		fullMoonEventEnabled = configBuilder
				.comment("Set to false to disable the Full Moon event on full moon nights in the Overworld")
				.translation("config.aoa3.fullMoonEventEnabled")
				.define("fullMoonEventEnabled", true);

		configBuilder.pop();

		STRUCTURES = new StructureConfig(configBuilder);
		ORES = new OreConfig(configBuilder);
	}
}
