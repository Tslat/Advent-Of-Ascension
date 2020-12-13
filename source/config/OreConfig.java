package net.tslat.aoa3.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class OreConfig {
	public final ForgeConfigSpec.IntValue bloodstoneVeinsPerChunk;
	public final ForgeConfigSpec.IntValue bloodstoneMinOresPerVein;
	public final ForgeConfigSpec.IntValue bloodstoneMaxOresPerVein;
	public final ForgeConfigSpec.IntValue varsiumVeinsPerChunk;
	public final ForgeConfigSpec.IntValue varsiumMinOresPerVein;
	public final ForgeConfigSpec.IntValue varsiumMaxOresPerVein;
	public final ForgeConfigSpec.IntValue baronyteVeinsPerChunk;
	public final ForgeConfigSpec.IntValue baronyteMinOresPerVein;
	public final ForgeConfigSpec.IntValue baronyteMaxOresPerVein;
	public final ForgeConfigSpec.IntValue blaziumVeinsPerChunk;
	public final ForgeConfigSpec.IntValue blaziumMinOresPerVein;
	public final ForgeConfigSpec.IntValue blaziumMaxOresPerVein;
	public final ForgeConfigSpec.IntValue blueGemstoneVeinsPerChunk;
	public final ForgeConfigSpec.IntValue blueGemstoneMinOresPerVein;
	public final ForgeConfigSpec.IntValue blueGemstoneMaxOresPerVein;
	public final ForgeConfigSpec.IntValue yellowGemstoneVeinsPerChunk;
	public final ForgeConfigSpec.IntValue yellowGemstoneMinOresPerVein;
	public final ForgeConfigSpec.IntValue yellowGemstoneMaxOresPerVein;
	public final ForgeConfigSpec.IntValue whiteGemstoneVeinsPerChunk;
	public final ForgeConfigSpec.IntValue whiteGemstoneMinOresPerVein;
	public final ForgeConfigSpec.IntValue whiteGemstoneMaxOresPerVein;
	public final ForgeConfigSpec.IntValue purpleGemstoneVeinsPerChunk;
	public final ForgeConfigSpec.IntValue purpleGemstoneMinOresPerVein;
	public final ForgeConfigSpec.IntValue purpleGemstoneMaxOresPerVein;
	public final ForgeConfigSpec.IntValue redGemstoneVeinsPerChunk;
	public final ForgeConfigSpec.IntValue redGemstoneMinOresPerVein;
	public final ForgeConfigSpec.IntValue redGemstoneMaxOresPerVein;
	public final ForgeConfigSpec.IntValue greenGemstoneVeinsPerChunk;
	public final ForgeConfigSpec.IntValue greenGemstoneMinOresPerVein;
	public final ForgeConfigSpec.IntValue greenGemstoneMaxOresPerVein;
	public final ForgeConfigSpec.IntValue crystalliteVeinsPerChunk;
	public final ForgeConfigSpec.IntValue crystalliteMinOresPerVein;
	public final ForgeConfigSpec.IntValue crystalliteMaxOresPerVein;
	public final ForgeConfigSpec.IntValue chargedRuniumVeinsPerChunk;
	public final ForgeConfigSpec.IntValue chargedRuniumMinOresPerVein;
	public final ForgeConfigSpec.IntValue chargedRuniumMaxOresPerVein;
	public final ForgeConfigSpec.IntValue ornamyteVeinsPerChunk;
	public final ForgeConfigSpec.IntValue ornamyteMinOresPerVein;
	public final ForgeConfigSpec.IntValue ornamyteMaxOresPerVein;
	public final ForgeConfigSpec.IntValue jewelyteVeinsPerChunk;
	public final ForgeConfigSpec.IntValue jewelyteMinOresPerVein;
	public final ForgeConfigSpec.IntValue jewelyteMaxOresPerVein;
	public final ForgeConfigSpec.IntValue gemenyteVeinsPerChunk;
	public final ForgeConfigSpec.IntValue gemenyteMinOresPerVein;
	public final ForgeConfigSpec.IntValue gemenyteMaxOresPerVein;
	public final ForgeConfigSpec.IntValue mystiteVeinsPerChunk;
	public final ForgeConfigSpec.IntValue mystiteMinOresPerVein;
	public final ForgeConfigSpec.IntValue mystiteMaxOresPerVein;
	public final ForgeConfigSpec.IntValue lyonVeinsPerChunk;
	public final ForgeConfigSpec.IntValue lyonMinOresPerVein;
	public final ForgeConfigSpec.IntValue lyonMaxOresPerVein;
	public final ForgeConfigSpec.IntValue elecaniumVeinsPerChunk;
	public final ForgeConfigSpec.IntValue elecaniumMinOresPerVein;
	public final ForgeConfigSpec.IntValue elecaniumMaxOresPerVein;
	public final ForgeConfigSpec.IntValue ghastlyVeinsPerChunk;
	public final ForgeConfigSpec.IntValue ghastlyMinOresPerVein;
	public final ForgeConfigSpec.IntValue ghastlyMaxOresPerVein;
	public final ForgeConfigSpec.IntValue ghoulishVeinsPerChunk;
	public final ForgeConfigSpec.IntValue ghoulishMinOresPerVein;
	public final ForgeConfigSpec.IntValue ghoulishMaxOresPerVein;
	public final ForgeConfigSpec.IntValue legboneFragmentVeinsPerChunk;
	public final ForgeConfigSpec.IntValue legboneFragmentMinOresPerVein;
	public final ForgeConfigSpec.IntValue legboneFragmentMaxOresPerVein;
	public final ForgeConfigSpec.IntValue skullboneFragmentVeinsPerChunk;
	public final ForgeConfigSpec.IntValue skullboneFragmentMinOresPerVein;
	public final ForgeConfigSpec.IntValue skullboneFragmentMaxOresPerVein;
	public final ForgeConfigSpec.IntValue footboneFragmentVeinsPerChunk;
	public final ForgeConfigSpec.IntValue footboneFragmentMinOresPerVein;
	public final ForgeConfigSpec.IntValue footboneFragmentMaxOresPerVein;
	public final ForgeConfigSpec.IntValue chestboneFragmentVeinsPerChunk;
	public final ForgeConfigSpec.IntValue chestboneFragmentMinOresPerVein;
	public final ForgeConfigSpec.IntValue chestboneFragmentMaxOresPerVein;
	public final ForgeConfigSpec.IntValue shyregemMinOresPerChunk;
	public final ForgeConfigSpec.IntValue shyregemMaxOresPerChunk;
	public final ForgeConfigSpec.IntValue shyrestoneMinOresPerChunk;
	public final ForgeConfigSpec.IntValue shyrestoneMaxOresPerChunk;

	protected OreConfig(ForgeConfigSpec.Builder configBuilder) {
		configBuilder.comment("Configure Ore spawn rates & sizes.").push("Ores");

		bloodstoneVeinsPerChunk = configBuilder
				.comment("Veins of Bloodstone Ore to attempt to generate every chunk in the Abyss. Set to 0 to disable entirely.")
				.translation("config.aoa3.bloodstoneVeinsPerChunk")
				.defineInRange("bloodstoneVeinsPerChunk", 4, 0, Integer.MAX_VALUE);

		bloodstoneMinOresPerVein = configBuilder
				.comment("Minimum vein size of Bloodstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.bloodstoneMinOresPerVein")
				.defineInRange("bloodstoneMinOresPerVein", 6, 1, 65536);

		bloodstoneMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Bloodstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.bloodstoneMaxOresPerVein")
				.defineInRange("bloodstoneMaxOresPerVein", 12, 1, 65536);

		varsiumVeinsPerChunk = configBuilder
				.comment("Veins of Varsium Ore to attempt to generate every chunk in Barathos. Set to 0 to disable entirely.")
				.translation("config.aoa3.varsiumVeinsPerChunk")
				.defineInRange("varsiumVeinsPerChunk", 2, 0, Integer.MAX_VALUE);

		varsiumMinOresPerVein = configBuilder
				.comment("Minimum vein size of Varsium Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.varsiumMinOresPerVein")
				.defineInRange("varsiumMinOresPerVein", 4, 1, 65536);

		varsiumMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Varsium Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.varsiumMaxOresPerVein")
				.defineInRange("varsiumMaxOresPerVein", 8, 1, 65536);

		baronyteVeinsPerChunk = configBuilder
				.comment("Veins of Baronyte Ore to attempt to generate every chunk in Barathos. Set to 0 to disable entirely.")
				.translation("config.aoa3.baronyteVeinsPerChunk")
				.defineInRange("baronyteVeinsPerChunk", 3, 0, Integer.MAX_VALUE);

		baronyteMinOresPerVein = configBuilder
				.comment("Minimum vein size of Baronyte Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.baronyteMinOresPerVein")
				.defineInRange("baronyteMinOresPerVein", 4, 1, 65536);

		baronyteMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Baronyte Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.baronyteMaxOresPerVein")
				.defineInRange("baronyteMaxOresPerVein", 8, 1, 65536);

		blaziumVeinsPerChunk = configBuilder
				.comment("Veins of Blazium Ore to attempt to generate every chunk in Barathos. Set to 0 to disable entirely.")
				.translation("config.aoa3.blaziumVeinsPerChunk")
				.defineInRange("blaziumVeinsPerChunk", 2, 0, Integer.MAX_VALUE);

		blaziumMinOresPerVein = configBuilder
				.comment("Minimum vein size of Blazium Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.blaziumMinOresPerVein")
				.defineInRange("blaziumMinOresPerVein", 4, 1, 65536);

		blaziumMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Blazium Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.blaziumMaxOresPerVein")
				.defineInRange("blaziumMaxOresPerVein", 8, 1, 65536);

		blueGemstoneVeinsPerChunk = configBuilder
				.comment("Veins of Blue Gemstone Ore to attempt to generate every chunk in Crystevia. Set to 0 to disable entirely.")
				.translation("config.aoa3.blueGemstoneVeinsPerChunk")
				.defineInRange("blueGemstoneVeinsPerChunk", 4, 0, Integer.MAX_VALUE);

		blueGemstoneMinOresPerVein = configBuilder
				.comment("Minimum vein size of Blue Gemstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.blueGemstoneMinOresPerVein")
				.defineInRange("blueGemstoneMinOresPerVein", 9, 1, 65536);

		blueGemstoneMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Blue Gemstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.blueGemstoneMaxOresPerVein")
				.defineInRange("blueGemstoneMaxOresPerVein", 15, 1, 65536);

		yellowGemstoneVeinsPerChunk = configBuilder
				.comment("Veins of Yellow Gemstone Ore to attempt to generate every chunk in Crystevia. Set to 0 to disable entirely.")
				.translation("config.aoa3.yellowGemstoneVeinsPerChunk")
				.defineInRange("yellowGemstoneVeinsPerChunk", 4, 0, Integer.MAX_VALUE);

		yellowGemstoneMinOresPerVein = configBuilder
				.comment("Minimum vein size of Yellow Gemstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.yellowGemstoneMinOresPerVein")
				.defineInRange("yellowGemstoneMinOresPerVein", 9, 1, 65536);

		yellowGemstoneMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Yellow Gemstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.yellowGemstoneMaxOresPerVein")
				.defineInRange("yellowGemstoneMaxOresPerVein", 15, 1, 65536);

		whiteGemstoneVeinsPerChunk = configBuilder
				.comment("Veins of White Gemstone Ore to attempt to generate every chunk in Crystevia. Set to 0 to disable entirely.")
				.translation("config.aoa3.whiteGemstoneVeinsPerChunk")
				.defineInRange("whiteGemstoneVeinsPerChunk", 4, 0, Integer.MAX_VALUE);

		whiteGemstoneMinOresPerVein = configBuilder
				.comment("Minimum vein size of White Gemstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.whiteGemstoneMinOresPerVein")
				.defineInRange("whiteGemstoneMinOresPerVein", 9, 1, 65536);

		whiteGemstoneMaxOresPerVein = configBuilder
				.comment("Maximum vein size of White Gemstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.whiteGemstoneMaxOresPerVein")
				.defineInRange("whiteGemstoneMaxOresPerVein", 15, 1, 65536);

		purpleGemstoneVeinsPerChunk = configBuilder
				.comment("Veins of Purple Gemstone Ore to attempt to generate every chunk in Crystevia. Set to 0 to disable entirely.")
				.translation("config.aoa3.purpleGemstoneVeinsPerChunk")
				.defineInRange("purpleGemstoneVeinsPerChunk", 4, 0, Integer.MAX_VALUE);

		purpleGemstoneMinOresPerVein = configBuilder
				.comment("Minimum vein size of Purple Gemstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.purpleGemstoneMinOresPerVein")
				.defineInRange("purpleGemstoneMinOresPerVein", 9, 1, 65536);

		purpleGemstoneMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Purple Gemstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.purpleGemstoneMaxOresPerVein")
				.defineInRange("purpleGemstoneMaxOresPerVein", 15, 1, 65536);

		redGemstoneVeinsPerChunk = configBuilder
				.comment("Veins of Red Gemstone Ore to attempt to generate every chunk in Crystevia. Set to 0 to disable entirely.")
				.translation("config.aoa3.redGemstoneVeinsPerChunk")
				.defineInRange("redGemstoneVeinsPerChunk", 4, 0, Integer.MAX_VALUE);

		redGemstoneMinOresPerVein = configBuilder
				.comment("Minimum vein size of Red Gemstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.redGemstoneMinOresPerVein")
				.defineInRange("redGemstoneMinOresPerVein", 9, 1, 65536);

		redGemstoneMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Red Gemstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.redGemstoneMaxOresPerVein")
				.defineInRange("redGemstoneMaxOresPerVein", 15, 1, 65536);

		greenGemstoneVeinsPerChunk = configBuilder
				.comment("Veins of Green Gemstone Ore to attempt to generate every chunk in Crystevia. Set to 0 to disable entirely.")
				.translation("config.aoa3.greenGemstoneVeinsPerChunk")
				.defineInRange("greenGemstoneVeinsPerChunk", 4, 0, Integer.MAX_VALUE);

		greenGemstoneMinOresPerVein = configBuilder
				.comment("Minimum vein size of Green Gemstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.greenGemstoneMinOresPerVein")
				.defineInRange("greenGemstoneMinOresPerVein", 9, 1, 65536);

		greenGemstoneMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Green Gemstone Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.greenGemstoneMaxOresPerVein")
				.defineInRange("greenGemstoneMaxOresPerVein", 15, 1, 65536);

		crystalliteVeinsPerChunk = configBuilder
				.comment("Veins of Crystallite Ore to attempt to generate every chunk in Haven. Set to 0 to disable entirely.")
				.translation("config.aoa3.crystalliteVeinsPerChunk")
				.defineInRange("crystalliteVeinsPerChunk", 8, 0, Integer.MAX_VALUE);

		crystalliteMinOresPerVein = configBuilder
				.comment("Minimum vein size of Crystallite Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.crystalliteMinOresPerVein")
				.defineInRange("crystalliteMinOresPerVein", 8, 1, 65536);

		crystalliteMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Crystallite Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.crystalliteMaxOresPerVein")
				.defineInRange("crystalliteMaxOresPerVein", 12, 1, 65536);

		chargedRuniumVeinsPerChunk = configBuilder
				.comment("Veins of Charged Runium Ore to attempt to generate every chunk in Deeplands. Set to 0 to disable entirely.")
				.translation("config.aoa3.chargedRuniumVeinsPerChunk")
				.defineInRange("chargedRuniumVeinsPerChunk", 5, 0, Integer.MAX_VALUE);

		chargedRuniumMinOresPerVein = configBuilder
				.comment("Minimum vein size of Charged Runium Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.chargedRuniumMinOresPerVein")
				.defineInRange("chargedRuniumMinOresPerVein", 12, 1, 65536);

		chargedRuniumMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Charged Runium Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.chargedRuniumMaxOresPerVein")
				.defineInRange("chargedRuniumMaxOresPerVein", 18, 1, 65536);

		ornamyteVeinsPerChunk = configBuilder
				.comment("Veins of Ornamyte Ore to attempt to generate every chunk in Creeponia. Set to 0 to disable entirely.")
				.translation("config.aoa3.ornamyteVeinsPerChunk")
				.defineInRange("ornamyteVeinsPerChunk", 4, 0, Integer.MAX_VALUE);

		ornamyteMinOresPerVein = configBuilder
				.comment("Minimum vein size of Ornamyte Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.ornamyteMinOresPerVein")
				.defineInRange("ornamyteMinOresPerVein", 2, 1, 65536);

		ornamyteMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Ornamyte Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.ornamyteMaxOresPerVein")
				.defineInRange("ornamyteMaxOresPerVein", 6, 1, 65536);

		jewelyteVeinsPerChunk = configBuilder
				.comment("Veins of Jewelyte Ore to attempt to generate every chunk in Creeponia. Set to 0 to disable entirely.")
				.translation("config.aoa3.jewelyteVeinsPerChunk")
				.defineInRange("jewelyteVeinsPerChunk", 4, 0, Integer.MAX_VALUE);

		jewelyteMinOresPerVein = configBuilder
				.comment("Minimum vein size of Jewelyte Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.jewelyteMinOresPerVein")
				.defineInRange("jewelyteMinOresPerVein", 2, 1, 65536);

		jewelyteMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Jewelyte Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.jewelyteMaxOresPerVein")
				.defineInRange("jewelyteMaxOresPerVein", 6, 1, 65536);

		gemenyteVeinsPerChunk = configBuilder
				.comment("Veins of Gemenyte Ore to attempt to generate every chunk in Creeponia. Set to 0 to disable entirely.")
				.translation("config.aoa3.gemenyteVeinsPerChunk")
				.defineInRange("gemenyteVeinsPerChunk", 4, 0, Integer.MAX_VALUE);

		gemenyteMinOresPerVein = configBuilder
				.comment("Minimum vein size of Gemenyte Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.gemenyteMinOresPerVein")
				.defineInRange("gemenyteMinOresPerVein", 2, 1, 65536);

		gemenyteMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Gemenyte Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.gemenyteMaxOresPerVein")
				.defineInRange("gemenyteMaxOresPerVein", 6, 1, 65536);

		mystiteVeinsPerChunk = configBuilder
				.comment("Veins of Mystite Ore to attempt to generate every chunk in Mysterium. Set to 0 to disable entirely.")
				.translation("config.aoa3.mystiteVeinsPerChunk")
				.defineInRange("mystiteVeinsPerChunk", 2, 0, Integer.MAX_VALUE);

		mystiteMinOresPerVein = configBuilder
				.comment("Minimum vein size of Mystite Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.mystiteMinOresPerVein")
				.defineInRange("mystiteMinOresPerVein", 5, 1, 65536);

		mystiteMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Mystite Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.mystiteMaxOresPerVein")
				.defineInRange("mystiteMaxOresPerVein", 9, 1, 65536);

		lyonVeinsPerChunk = configBuilder
				.comment("Veins of Lyon Ore to attempt to generate every chunk in Iromine. Set to 0 to disable entirely.")
				.translation("config.aoa3.lyonVeinsPerChunk")
				.defineInRange("lyonVeinsPerChunk", 2, 0, Integer.MAX_VALUE);

		lyonMinOresPerVein = configBuilder
				.comment("Minimum vein size of Lyon Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.lyonMinOresPerVein")
				.defineInRange("lyonMinOresPerVein", 3, 1, 65536);

		lyonMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Lyon Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.lyonMaxOresPerVein")
				.defineInRange("lyonMaxOresPerVein", 7, 1, 65536);

		elecaniumVeinsPerChunk = configBuilder
				.comment("Veins of Elecanium Ore to attempt to generate every chunk in Runandor. Set to 0 to disable entirely.")
				.translation("config.aoa3.elecaniumVeinsPerChunk")
				.defineInRange("elecaniumVeinsPerChunk", 3, 0, Integer.MAX_VALUE);

		elecaniumMinOresPerVein = configBuilder
				.comment("Minimum vein size of Elecanium Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.elecaniumMinOresPerVein")
				.defineInRange("elecaniumMinOresPerVein", 2, 1, 65536);

		elecaniumMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Elecanium Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.elecaniumMaxOresPerVein")
				.defineInRange("elecaniumMaxOresPerVein", 5, 1, 65536);

		ghoulishVeinsPerChunk = configBuilder
				.comment("Veins of ghoulish Ore to attempt to generate every chunk in Greckon. Set to 0 to disable entirely.")
				.translation("config.aoa3.ghoulishVeinsPerChunk")
				.defineInRange("ghoulishVeinsPerChunk", 2, 0, Integer.MAX_VALUE);

		ghoulishMinOresPerVein = configBuilder
				.comment("Minimum vein size of ghoulish Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.ghoulishMinOresPerVein")
				.defineInRange("ghoulishMinOresPerVein", 3, 1, 65536);

		ghoulishMaxOresPerVein = configBuilder
				.comment("Maximum vein size of ghoulish Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.ghoulishMaxOresPerVein")
				.defineInRange("ghoulishMaxOresPerVein", 7, 1, 65536);

		ghastlyVeinsPerChunk = configBuilder
				.comment("Veins of ghastly Ore to attempt to generate every chunk in Greckon. Set to 0 to disable entirely.")
				.translation("config.aoa3.ghastlyVeinsPerChunk")
				.defineInRange("ghastlyVeinsPerChunk", 2, 0, Integer.MAX_VALUE);

		ghastlyMinOresPerVein = configBuilder
				.comment("Minimum vein size of ghastly Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.ghastlyMinOresPerVein")
				.defineInRange("ghastlyMinOresPerVein", 3, 1, 65536);

		ghastlyMaxOresPerVein = configBuilder
				.comment("Maximum vein size of ghastly Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.ghastlyMaxOresPerVein")
				.defineInRange("ghastlyMaxOresPerVein", 7, 1, 65536);

		skullboneFragmentVeinsPerChunk = configBuilder
				.comment("Veins of Skullbone Fragments Ore to attempt to generate every chunk in Precasia. Set to 0 to disable entirely.")
				.translation("config.aoa3.skullboneFragmentVeinsPerChunk")
				.defineInRange("skullboneFragmentVeinsPerChunk", 2, 0, Integer.MAX_VALUE);

		skullboneFragmentMinOresPerVein = configBuilder
				.comment("Minimum vein size of Skullbone Fragments Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.skullboneFragmentMinOresPerVein")
				.defineInRange("skullboneFragmentMinOresPerVein", 2, 1, 65536);

		skullboneFragmentMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Skullbone Fragments Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.skullboneFragmentMaxOresPerVein")
				.defineInRange("skullboneFragmentMaxOresPerVein", 6, 1, 65536);

		footboneFragmentVeinsPerChunk = configBuilder
				.comment("Veins of Footbone Fragments Ore to attempt to generate every chunk in Precasia. Set to 0 to disable entirely.")
				.translation("config.aoa3.footboneFragmentVeinsPerChunk")
				.defineInRange("footboneFragmentVeinsPerChunk", 2, 0, Integer.MAX_VALUE);

		footboneFragmentMinOresPerVein = configBuilder
				.comment("Minimum vein size of Footbone Fragments Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.footboneFragmentMinOresPerVein")
				.defineInRange("footboneFragmentMinOresPerVein", 2, 1, 65536);

		footboneFragmentMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Footbone Fragments Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.footboneFragmentMaxOresPerVein")
				.defineInRange("footboneFragmentMaxOresPerVein", 6, 1, 65536);

		legboneFragmentVeinsPerChunk = configBuilder
				.comment("Veins of Legbone Fragments Ore to attempt to generate every chunk in Precasia. Set to 0 to disable entirely.")
				.translation("config.aoa3.legboneFragmentVeinsPerChunk")
				.defineInRange("legboneFragmentVeinsPerChunk", 2, 0, Integer.MAX_VALUE);

		legboneFragmentMinOresPerVein = configBuilder
				.comment("Minimum vein size of Legbone Fragments Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.legboneFragmentMinOresPerVein")
				.defineInRange("legboneFragmentMinOresPerVein", 2, 1, 65536);

		legboneFragmentMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Legbone Fragments Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.legboneFragmentMaxOresPerVein")
				.defineInRange("legboneFragmentMaxOresPerVein", 6, 1, 65536);

		chestboneFragmentVeinsPerChunk = configBuilder
				.comment("Veins of Chestbone Fragments Ore to attempt to generate every chunk in Precasia. Set to 0 to disable entirely.")
				.translation("config.aoa3.chestboneFragmentVeinsPerChunk")
				.defineInRange("chestboneFragmentVeinsPerChunk", 2, 0, Integer.MAX_VALUE);

		chestboneFragmentMinOresPerVein = configBuilder
				.comment("Minimum vein size of Chestbone Fragments Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.chestboneFragmentMinOresPerVein")
				.defineInRange("chestboneFragmentMinOresPerVein", 2, 1, 65536);

		chestboneFragmentMaxOresPerVein = configBuilder
				.comment("Maximum vein size of Chestbone Fragments Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.chestboneFragmentMaxOresPerVein")
				.defineInRange("chestboneFragmentMaxOresPerVein", 6, 1, 65536);

		shyregemMinOresPerChunk = configBuilder
				.comment("Minimum number of Shyregem ores to attempt to spawn per chunk in Shyrelands. A random number of ores will attempt to generate between the minimum and maximum values")
				.translation("config.aoa3.shyregemMinOresPerChunk")
				.defineInRange("shyregemMinOresPerChunk", 2, 0, 65536);

		shyregemMaxOresPerChunk = configBuilder
				.comment("Minimum vein size of Gemenyte Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.shyregemMaxOresPerChunk")
				.defineInRange("shyregemMaxOresPerChunk", 3, 0, 65536);

		shyrestoneMinOresPerChunk = configBuilder
				.comment("Maximum number of Shyregem ores to attempt to spawn per chunk in Shyrelands. A random number of ores will attempt to generate between the minimum and maximum values")
				.translation("config.aoa3.shyrestoneMinOresPerChunk")
				.defineInRange("shyrestoneMinOresPerChunk", 8, 0, 65536);

		shyrestoneMaxOresPerChunk = configBuilder
				.comment("Maximum vein size of Gemenyte Ore when generated. Veins will generate a random size between the minimum and maximum ores per vein values.")
				.translation("config.aoa3.shyrestoneMaxOresPerChunk")
				.defineInRange("shyrestoneMaxOresPerChunk", 16, 0, 65536);

		configBuilder.pop();
	}
}
