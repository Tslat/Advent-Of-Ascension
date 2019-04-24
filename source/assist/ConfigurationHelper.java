package net.nevermine.assist;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.HashMap;

public class ConfigurationHelper {
	public static Configuration cfg;
	private static HashMap<String, Integer> configMap;
	public static int abyss;
	public static int haven;
	public static int precasia;
	public static int mysterium;
	public static int iromine;
	public static int lunalus;
	public static int gardencia;
	public static int deeplands;
	public static int greckon;
	public static int lborean;
	public static int runandor;
	public static int barathos;
	public static int voxponds;
	public static int dustopia;
	public static int labricon;
	public static int lelyetia;
	public static int ancientcavern;
	public static int celeve;
	public static int crystevia;
	public static int candyland;
	public static int creeponia;
	public static int immortallis;
	public static int abyssB;
	public static int havenB;
	public static int precasiaB;
	public static int mysteriumB;
	public static int iromineB;
	public static int lunalusB;
	public static int gardenciaB;
	public static int deeplandsB;
	public static int greckonB;
	public static int ancientcavernB;
	public static int lboreanB;
	public static int runandorB;
	public static int barathosB;
	public static int voxpondsB;
	public static int dustopiaB;
	public static int labriconB;
	public static int lelyetiaB;
	public static int celeveB;
	public static int crysteviaB;
	public static int candylandB;
	public static int creeponiaB;
	public static int immortallisB;
	public static int shyrelands;
	public static int shyrelandsB;
	public static int creationBarX;
	public static int creationBarY;
	public static int soulBarX;
	public static int soulBarY;
	public static int ancientBarX;
	public static int ancientBarY;
	public static int rageBarX;
	public static int rageBarY;
	public static int tributeBarX;
	public static int tributeBarY;
	public static int revengeX;
	public static int revengeY;
	public static int dataW1;
	public static int dataW2;
	public static int dataW3;
	public static int dataW4;
	public static int econtrol;
	public static int eshell;
	public static int erecharge;
	public static int eoverpower;
	public static int ecrush;
	public static int esever;
	public static int earchmage;
	public static int ewindfury;
	public static int eslice;
	public static int eintervention;
	public static int foragingX;
	public static int foragingY;
	public static int infusionX;
	public static int infusionY;
	public static int auguryX;
	public static int auguryY;
	public static int hunterX;
	public static int hunterY;
	public static int runationX;
	public static int runationY;
	public static int robberyX;
	public static int robberyY;
	public static int creationskillX;
	public static int creationskillY;
	public static int innervationX;
	public static int innervationY;
	public static int animaX;
	public static int animaY;
	public static int extractionX;
	public static int extractionY;
	public static int expeditionX;
	public static int expeditionY;
	public static int vulcanismX;
	public static int vulcanismY;
	public static int butcheryX;
	public static int butcheryY;
	public static int hermetismX;
	public static int hermetismY;
	public static int loggingX;
	public static int loggingY;
	public static int haulingX;
	public static int haulingY;
	public static int limitation;

	public static int precasiaTall, precasiaDesert, precasiaField;
	public static int abyssEye, abyssShadow;
	public static int iroSilver, iroTech;
	public static int deepFungal, deepShine;
	public static int gardenFungal, gardenMarsh;
	public static int greckonForest, greckonSkull;
	public static int boreanForest, boreanBubble, boreanRed;
	public static int baronForest, baronMaze;
	public static int dustMarsh, dustPlains;
	public static int candyChocolate, candyMallow, candyRock;

	public static void init(final File dir) {
		(cfg = new Configuration(new File(dir, "AoA.cfg"))).load();
		dimensionInit();
		miscInit();
		cfg.save();
	}

	public static void dimensionInit() {
		abyss = cfg.get("Dimension", "Abyss ID", 9).getInt();
		haven = cfg.get("Dimension", "Haven ID", 10).getInt();
		precasia = cfg.get("Dimension", "Precasia ID", 11).getInt();
		mysterium = cfg.get("Dimension", "Mysterium ID", 12).getInt();
		iromine = cfg.get("Dimension", "Iromine ID", 13).getInt();
		lunalus = cfg.get("Dimension", "Lunalus ID", 14).getInt();
		gardencia = cfg.get("Dimension", "Gardencia ID", 16).getInt();
		deeplands = cfg.get("Dimension", "Deeplands ID", 15).getInt();
		greckon = cfg.get("Dimension", "Greckon ID", 17).getInt();
		lborean = cfg.get("Dimension", "L'borean ID", 19).getInt();
		runandor = cfg.get("Dimension", "Runandor ID", 21).getInt();
		barathos = cfg.get("Dimension", "Barathos ID", 22).getInt();
		dustopia = cfg.get("Dimension", "Dustopia ID", 18).getInt();
		voxponds = cfg.get("Dimension", "Voxponds ID", 20).getInt();
		labricon = cfg.get("Dimension", "Labricon ID", 23).getInt();
		lelyetia = cfg.get("Dimension", "Lelyetia ID", 24).getInt();
		ancientcavern = cfg.get("Dimension", "Ancient Cavern ID", 25).getInt();
		celeve = cfg.get("Dimension", "Celeve ID", 26).getInt();
		crystevia = cfg.get("Dimension", "Crystevia ID", 27).getInt();
		candyland = cfg.get("Dimension", "Candyland ID", 28).getInt();
		creeponia = cfg.get("Dimension", "Creeponia ID", 29).getInt();
		immortallis = cfg.get("Dimension", "Immortallis ID", 30).getInt();
		shyrelands = cfg.get("Dimension", "Shyrelands ID", 31).getInt();
		abyssB = cfg.get("Biome", "Abyss Biome ID", 74).getInt();
		havenB = cfg.get("Biome", "Haven Biome ID", 76).getInt();
		mysteriumB = cfg.get("Biome", "Mysterium Biome ID", 77).getInt();
		iromineB = cfg.get("Biome", "Iromine Biome ID", 78).getInt();
		lunalusB = cfg.get("Biome", "Lunalus Biome ID", 79).getInt();
		gardenciaB = cfg.get("Biome", "Gardencia Biome ID", 81).getInt();
		deeplandsB = cfg.get("Biome", "Deeplands Biome ID", 80).getInt();
		greckonB = cfg.get("Biome", "Greckon Biome ID", 82).getInt();
		lboreanB = cfg.get("Biome", "L'borean Biome ID", 84).getInt();
		runandorB = cfg.get("Biome", "Runandor Biome ID", 86).getInt();
		barathosB = cfg.get("Biome", "Barathos Biome ID", 87).getInt();
		dustopiaB = cfg.get("Biome", "Dustopia Biome ID", 83).getInt();
		voxpondsB = cfg.get("Biome", "Voxponds Biome ID", 85).getInt();
		labriconB = cfg.get("Biome", "Labricon Biome ID", 88).getInt();
		lelyetiaB = cfg.get("Biome", "Lelyetia Biome ID", 89).getInt();
		ancientcavernB = cfg.get("Biome", "Ancient Cavern Biome ID", 90).getInt();
		celeveB = cfg.get("Biome", "Celeve Biome ID", 91).getInt();
		crysteviaB = cfg.get("Biome", "Crystevia Biome ID", 92).getInt();
		candylandB = cfg.get("Biome", "Candyland Biome ID", 93).getInt();
		creeponiaB = cfg.get("Biome", "Creeponia Biome ID", 94).getInt();
		immortallisB = cfg.get("Biome", "Immortallis Biome ID", 95).getInt();

		precasiaB = cfg.get("Biome", "Precasia Biome ID", 75).getInt();
		precasiaDesert = cfg.get("Biome", "Precasian Desert Biome ID", 96).getInt();
		precasiaField = cfg.get("Biome", "Precasian Field Biome ID", 97).getInt();
		precasiaTall = cfg.get("Biome", "Precasian Tall Forest Biome ID", 99).getInt();

		abyssShadow = cfg.get("Biome", "Abyss Shadow Biome ID", 100).getInt();
		abyssEye = cfg.get("Biome", "Abyss Eye Biome ID", 101).getInt();

		iroSilver = cfg.get("Biome", "Silvro Biome ID", 102).getInt();
		iroTech = cfg.get("Biome", "Iro-Tech Biome ID", 103).getInt();

		deepFungal = cfg.get("Biome", "Deeplands Fungal Biome ID", 104).getInt();
		deepShine = cfg.get("Biome", "Deeplands Shine Biome ID", 105).getInt();

		gardenFungal = cfg.get("Biome", "Gardencia Fungal Biome ID", 106).getInt();
		gardenMarsh = cfg.get("Biome", "Gardencian Marsh Biome ID", 107).getInt();

		greckonForest = cfg.get("Biome", "Haunted Forest Biome ID", 108).getInt();
		greckonSkull = cfg.get("Biome", "Haunted Skulls Biome ID", 109).getInt();

		boreanForest = cfg.get("Biome", "L'Borean Forest Biome ID", 110).getInt();
		boreanBubble = cfg.get("Biome", "L'Borean Bubble Forest Biome ID", 111).getInt();
		boreanRed = cfg.get("Biome", "L'Borean Reds Biome ID", 112).getInt();

		baronForest = cfg.get("Biome", "Baron Forest Biome ID", 113).getInt();
		baronMaze = cfg.get("Biome", "Rocky Maze Biome ID", 114).getInt();

		dustMarsh = cfg.get("Biome", "Dustopian Marsh Biome ID", 115).getInt();
		dustPlains = cfg.get("Biome", "Dustopian Plains Biome ID", 116).getInt();

		candyChocolate = cfg.get("Biome", "Chocolate Forest Biome ID", 117).getInt();
		candyMallow = cfg.get("Biome", "Marshmallow Hills Biome ID", 118).getInt();
		candyRock = cfg.get("Biome", "Rock Candy Hills Biome ID", 119).getInt();

		int shyreTemp = cfg.get("Biome", "Shyrelands Biome ID", 120).getInt();
		if (shyreTemp == 96 && precasiaDesert == 96) {
			shyrelandsB = 120;
			System.out.println("Looks like you haven't fixed your AoA config yet! Set shyrelands biome to 120!");
		}
		else {
			shyrelandsB = shyreTemp;
		}
	}

	public static void miscInit() {
		creationBarY = cfg.get("Bar", "CreationBarY", 2).getInt();
		creationBarX = cfg.get("Bar", "CreationBarX", 50).getInt();
		ancientBarY = cfg.get("Bar", "EnergyBarY", 2).getInt();
		ancientBarX = cfg.get("Bar", "EnergyBarX", 75).getInt();
		soulBarY = cfg.get("Bar", "SoulBarY", 2).getInt();
		soulBarX = cfg.get("Bar", "SoulBarX", 25).getInt();
		rageBarY = cfg.get("Bar", "RageBarY", 2).getInt();
		rageBarX = cfg.get("Bar", "RageBarX", 125).getInt();
		tributeBarY = cfg.get("Bar", "TributeBarY", 2).getInt();
		tributeBarX = cfg.get("Bar", "TributeBarX", 100).getInt();
		revengeY = cfg.get("Vulcanism", "VulcanismIconY", 2).getInt();
		revengeX = cfg.get("Vulcanism", "VulcanismIconX", 150).getInt();
		dataW1 = cfg.get("DataWatcher", "EnergyValue", 27).getInt();
		dataW2 = cfg.get("DataWatcher", "Thermal", 28).getInt();
		dataW3 = cfg.get("DataWatcher", "SoulValue", 29).getInt();
		dataW4 = cfg.get("DataWatcher", "CreationValue", 30).getInt();
		econtrol = cfg.get("Enchantment", "Control", 100).getInt();
		eshell = cfg.get("Enchantment", "Shell", 101).getInt();
		erecharge = cfg.get("Enchantment", "Recharge", 102).getInt();
		eoverpower = cfg.get("Enchantment", "Overpower", 103).getInt();
		ecrush = cfg.get("Enchantment", "Crush", 104).getInt();
		esever = cfg.get("Enchantment", "Sever", 105).getInt();
		earchmage = cfg.get("Enchantment", "Archmage", 106).getInt();
		ewindfury = cfg.get("Enchantment", "Windfury", 107).getInt();
		eslice = cfg.get("Enchantment", "Slice", 108).getInt();
		eintervention = cfg.get("Enchantment", "Intervention", 109).getInt();
		hunterY = cfg.get("Skill", "HunterY", 30).getInt();
		hunterX = cfg.get("Skill", "HunterX", 28).getInt();
		auguryY = cfg.get("Skill", "AuguryY", 30).getInt();
		auguryX = cfg.get("Skill", "AuguryX", 80).getInt();
		foragingY = cfg.get("Skill", "ForagingY", 30).getInt();
		foragingX = cfg.get("Skill", "ForagingX", 106).getInt();
		infusionY = cfg.get("Skill", "InfusionY", 30).getInt();
		infusionX = cfg.get("Skill", "InfusionX", 132).getInt();
		creationskillY = cfg.get("Skill", "CreationSkillY", 30).getInt();
		creationskillX = cfg.get("Skill", "CreationSkillX", 54).getInt();
		runationY = cfg.get("Skill", "RunationY", 30).getInt();
		runationX = cfg.get("Skill", "RunationX", 158).getInt();
		robberyY = cfg.get("Skill", "RobberyY", 30).getInt();
		robberyX = cfg.get("Skill", "RobberyX", 184).getInt();
		innervationY = cfg.get("Skill", "InnervationY", 30).getInt();
		innervationX = cfg.get("Skill", "InnervationX", 210).getInt();
		animaY = cfg.get("Skill", "AnimaY", 65).getInt();
		animaX = cfg.get("Skill", "AnimaX", 28).getInt();
		extractionY = cfg.get("Skill", "ExtractionY", 65).getInt();
		extractionX = cfg.get("Skill", "ExtractionX", 54).getInt();
		expeditionY = cfg.get("Skill", "ExpeditionY", 65).getInt();
		expeditionX = cfg.get("Skill", "ExpeditionX", 80).getInt();
		vulcanismY = cfg.get("Skill", "VulcanismY", 65).getInt();
		vulcanismX = cfg.get("Skill", "VulcanismX", 106).getInt();
		loggingY = cfg.get("Skill", "LoggingY", 65).getInt();
		loggingX = cfg.get("Skill", "LoggingX", 132).getInt();
		hermetismY = cfg.get("Skill", "HermetismY", 65).getInt();
		hermetismX = cfg.get("Skill", "HermetismX", 158).getInt();
		butcheryY = cfg.get("Skill", "ButcheryY", 65).getInt();
		butcheryX = cfg.get("Skill", "ButcheryX", 184).getInt();
		haulingY = cfg.get("Skill", "HaulingY", 65).getInt();
		haulingX = cfg.get("Skill", "HaulingX", 210).getInt();
		limitation = cfg.get("Limitation", "DMG Limit", 1).getInt();
	}

	public static int getConfig(final String name) {
		return configMap.get(name);
	}

	static {
		configMap = new HashMap<String, Integer>();
	}
}
