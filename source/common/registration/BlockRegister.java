package net.tslat.aoa3.common.registration;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.block.*;
import net.tslat.aoa3.block.decoration.BasicDecorationBlock;
import net.tslat.aoa3.block.decoration.banner.BannerBlock;
import net.tslat.aoa3.block.decoration.carpets.CarpetBlock;
import net.tslat.aoa3.block.decoration.fences.FenceBlock;
import net.tslat.aoa3.block.decoration.fences.TwinklestoneFence;
import net.tslat.aoa3.block.decoration.gates.GateBlock;
import net.tslat.aoa3.block.decoration.glass.GlassBlock;
import net.tslat.aoa3.block.decoration.glass.UnbreakableGlassBlock;
import net.tslat.aoa3.block.decoration.misc.CompressedBlock;
import net.tslat.aoa3.block.decoration.slabs.SlabBlock;
import net.tslat.aoa3.block.decoration.stairs.StairsBlock;
import net.tslat.aoa3.block.decoration.statue.StatueBlock;
import net.tslat.aoa3.block.functional.altar.*;
import net.tslat.aoa3.block.functional.misc.Crystallanium;
import net.tslat.aoa3.block.functional.misc.Emberium;
import net.tslat.aoa3.block.functional.misc.Shadonantium;
import net.tslat.aoa3.block.functional.misc.Skeletanium;
import net.tslat.aoa3.block.functional.crops.CropBlock;
import net.tslat.aoa3.block.functional.lamps.LifeLampBlock;
import net.tslat.aoa3.block.functional.lamps.LampBlock;
import net.tslat.aoa3.block.functional.lights.LightBlock;
import net.tslat.aoa3.block.functional.lights.UnbreakableLightBlock;
import net.tslat.aoa3.block.functional.misc.*;
import net.tslat.aoa3.block.functional.portal.GreckonPortalBlock;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.block.functional.portal.RunandorPortalBlock;
import net.tslat.aoa3.block.functional.spawners.SpawnerBlock;
import net.tslat.aoa3.block.functional.utility.*;
import net.tslat.aoa3.block.generation.dirt.DirtBlock;
import net.tslat.aoa3.block.generation.grass.GrassBlock;
import net.tslat.aoa3.block.generation.grass.UpsideDownGrassBlock;
import net.tslat.aoa3.block.generation.leaves.LeavesBlock;
import net.tslat.aoa3.block.generation.leaves.TranslucentLeavesBlock;
import net.tslat.aoa3.block.generation.misc.*;
import net.tslat.aoa3.block.generation.ores.OreBlock;
import net.tslat.aoa3.block.generation.plants.*;
import net.tslat.aoa3.block.generation.sand.SandBlock;
import net.tslat.aoa3.block.generation.special.*;
import net.tslat.aoa3.block.generation.stone.StoneBlock;
import net.tslat.aoa3.block.generation.wood.LogBlock;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import static net.tslat.aoa3.library.Enums.Deities.*;

@Mod.EventBusSubscriber
public class BlockRegister {
	public static final BasicBlock stoneAbyss = new StoneBlock("AbyssalStone", "abyss_stone");
	public static final BasicBlock stoneBarathos = new StoneBlock("BarathosHellstone", "barathos_hellstone");
	public static final BasicBlock stoneBaron = new StoneBlock("BaronStone", "baron_stone");
	public static final BasicBlock stoneBorean = new StoneBlock("BoreanStone", "borean_stone");
	public static final BasicBlock stoneCreep = new StoneBlock("CreepStone", "creep_stone");
	public static final BasicBlock stoneCrystevia = new StoneBlock("CrystevianStone", "crystevia_stone");
	public static final BasicBlock stoneDeeplands = new StoneBlock("DeeplandsStone", "deeplands_stone");
	public static final BasicBlock stoneDustopia = new StoneBlock("DustopianStone", "dustopia_stone");
	public static final BasicBlock stoneGardencia = new StoneBlock("GardencianStone", "gardencia_stone");
	public static final BasicBlock stoneGreckon = new StoneBlock("GreckonStone", "greckon_stone");
	public static final BasicBlock stoneHaven = new StoneBlock("HavenStone", "haven_stone");
	public static final BasicBlock stoneIromine = new StoneBlock("IroStone", "iromine_stone");
	public static final BasicBlock stoneLelyetia = new StoneBlock("LelyetianStone", "lelyetia_stone");
	public static final BasicBlock stoneMysterium = new StoneBlock("MysteriumStone", "mysterium_stone");
	public static final BasicBlock stonePrecasiaHigh = new StoneBlock("HighPrecasianStone", "high_precasia_stone");
	public static final BasicBlock stonePrecasiaLow = new StoneBlock("LowPrecasiaStone", "low_precasia_stone");
	public static final BasicBlock stonePrimed = new StoneBlock("PrimedStone", "primed_stone");
	public static final BasicBlock stoneRunic = new StoneBlock("RunicStone", "runic_stone");
	public static final BasicBlock stoneShyrelands = new StoneBlock("ShyrelandsStone", "shyrelands_stone");
	public static final BasicBlock stoneToxic = new StoneBlock("ToxicStone", "toxic_stone");
	public static final BasicBlock stoneUnstable = new StoneBlock("UnstableStone", "unstable_stone");

	public static final DirtBlock dirtBorean = new DirtBlock("BoreanDirt", "borean_dirt");
	public static final DirtBlock dirtCandyland = new DirtBlock("CandylandDirt", "candyland_dirt");
	public static final DirtBlock dirtCeleve = new DirtBlock("CeleveDirt", "celeve_dirt");
	public static final DirtBlock dirtCreeponia = new DirtBlock("CreeponiaDirt", "creeponia_dirt");
	public static final DirtBlock dirtDustopia = new DirtBlock("DustopiaDirt", "dustopia_dirt");
	public static final DirtBlock dirtGardencia = new DirtBlock("GardenciaDirt", "gardencia_dirt");
	public static final DirtBlock dirtGreckon = new DirtBlock("GreckonDirt", "greckon_dirt");
	public static final DirtBlock dirtHaven = new DirtBlock("HavenDirt", "haven_dirt");
	public static final DirtBlock dirtLunalyte = new DirtBlock("LunalyteDirt", "lunalyte_dirt");
	public static final DirtBlock dirtLunasole = new DirtBlock("LunasoleDirt", "lunasole_dirt");
	public static final DirtBlock dirtMysterium = new DirtBlock("MysteriumDirt", "mysterium_dirt");
	public static final DirtBlock dirtToxic = new DirtBlock("ToxicDirt", "toxic_dirt");

	public static final GrassBlock grassAbyss = new GrassBlock("AbyssGrass", "abyss_grass", stoneAbyss);
	public static final GrassBlock grassBorean = new GrassBlock("BoreanGrass", "borean_grass", dirtBorean);
	public static final GrassBlock grassCandyland = new GrassBlock("CandylandGrass", "candyland_grass", dirtCandyland);
	public static final GrassBlock grassCeleve = new GrassBlock("CeleveGrass", "celeve_grass", dirtCeleve);
	public static final GrassBlock grassChocolate = new GrassBlock("ChocolateGrass", "chocolate_grass", dirtCandyland);
	public static final GrassBlock grassCreeponia = new GrassBlock("CreeponiaGrass", "creeponia_grass", dirtCreeponia);
	public static final GrassBlock grassCrystal = new GrassBlock("CrystalGrass", "crystal_grass", dirtCandyland);
	public static final GrassBlock grassDustopia = new GrassBlock("DustopiaGrass", "dustopia_grass", dirtDustopia);
	public static final GrassBlock grassGardencia = new GrassBlock("GardenciaGrass", "gardencia_grass", dirtGardencia);
	public static final GrassBlock grassGreckon = new GrassBlock("GreckonGrass", "greckon_grass", dirtGreckon);
	public static final GrassBlock grassHaven = new GrassBlock("HavenGrass", "haven_grass", dirtHaven);
	public static final GrassBlock grassIromine = new GrassBlock("IromineGrass", "iromine_grass", stoneIromine);
	public static final GrassBlock grassLelyetia = new GrassBlock("LelyetiaGrass", "lelyetia_grass", stoneLelyetia);
	public static final GrassBlock grassLelyetiaDown = new UpsideDownGrassBlock("LelyetiaDownGrass", "lelyetia_down_grass", stoneLelyetia);
	public static final GrassBlock grassLunalyte = new GrassBlock("LunalyteGrass", "lunalyte_grass", dirtLunalyte);
	public static final GrassBlock grassLunasole = new GrassBlock("LunasoleGrass", "lunasole_grass", dirtLunasole);
	public static final GrassBlock grassMarshmallow = new GrassBlock("MarshmallowGrass", "marshmallow_grass", dirtCandyland);
	public static final GrassBlock grassMysterium = new GrassBlock("MysteriumGrass", "mysterium_grass", dirtMysterium);
	public static final GrassBlock grassPrecasia = new GrassBlock("PrecasiaGrass", "precasia_grass", stonePrecasiaHigh);
	public static final GrassBlock grassRunic = new GrassBlock("RunicGrass", "runic_grass", stoneRunic);
	public static final GrassBlock grassShyrelands = new GrassBlock("ShyrelandsGrass", "shyrelands_grass", stoneShyrelands);
	public static final GrassBlock grassSilvro = new GrassBlock("SilvroGrass", "silvro_grass", stoneIromine);
	public static final GrassBlock grassToxic = new GrassBlock("ToxicGrass", "toxic_grass", dirtToxic);

	public static final OreBlock oreAmethyst = new OreBlock("AmethystOre", "amethyst_ore", 2);
	public static final OreBlock oreBaronyte = new OreBlock("BaronyteOre", "baronyte_ore", 3);
	public static final OreBlock oreBlazium = new OreBlock("BlaziumOre", "blazium_ore", 3);
	public static final OreBlock oreBloodstone = new OreBlock("BloodstoneOre", "bloodstone_ore", 3);
	public static final OreBlock oreBlueGemstone = new OreBlock("BlueGemstoneOre", "blue_crystal_ore", 3);
	public static final OreBlock oreChargedRunium = new OreBlock("ChargedRuniumOre", "charged_runium_ore", 3);
	public static final OreBlock oreChestboneFragments = new OreBlock("ChestboneFragmentsOre", "chestbone_fragments_ore", 3);
	public static final OreBlock oreCrystallite = new OreBlock("CrystalliteOre", "crystallite_ore", 3);
	public static final OreBlock oreElecanium = new OreBlock("ElecaniumOre", "elecanium_ore", 3);
	public static final OreBlock oreEmberstone = new OreBlock("EmberstoneOre", "emberstone_ore", 3);
	public static final OreBlock oreFootboneFragments = new OreBlock("FootboneFragmentsOre", "footbone_fragments_ore", 3);
	public static final OreBlock oreGemenyte = new OreBlock("GemenyteOre", "gemenyte_ore", 3);
	public static final OreBlock oreGhastly = new OreBlock("GhastlyOre", "ghastly_ore", 3);
	public static final OreBlock oreGhoulish = new OreBlock("GhoulishOre", "ghoulish_ore", 3);
	public static final OreBlock oreGreenGemstone = new OreBlock("GreenGemstoneOre", "green_crystal_ore", 3);
	public static final OreBlock oreJade = new OreBlock("JadeOre", "jade_ore", 3);
	public static final OreBlock oreJewelyte = new OreBlock("JewelyteOre", "jewelyte_ore", 3);
	public static final OreBlock oreLegboneFragments = new OreBlock("LegboneFragmentsOre", "legbone_fragments_ore", 3);
	public static final OreBlock oreLimonite = new OreBlock("LimoniteOre", "limonite_ore", 1);
	public static final OreBlock oreLyon = new OreBlock("LyonOre", "lyon_ore", 3);
	public static final OreBlock oreMystite = new OreBlock("MystiteOre", "mystite_ore", 3);
	public static final OreBlock oreOrnamyte = new OreBlock("OrnamyteOre", "ornamyte_ore", 3);
	public static final OreBlock orePurpleGemstone = new OreBlock("PurpleGemstoneOre", "purple_crystal_ore", 3);
	public static final OreBlock oreRedGemstone = new OreBlock("RedGemstoneOre", "red_crystal_ore", 3);
	public static final OreBlock oreRosite = new OreBlock("RositeOre", "rosite_ore", 3);
	public static final OreBlock oreRunium = new OreBlock("RuniumOre", "runium_ore", 2);
	public static final OreBlock oreSapphire = new OreBlock("SapphireOre", "sapphire_ore", 3);
	public static final OreBlock oreShyregem = new OreBlock("ShyregemOre", "shyregem_ore", 3);
	public static final OreBlock oreShyrestone = new OreBlock("ShyrestoneOre", "shyrestone_ore", 3);
	public static final OreBlock oreSkullboneFragments = new OreBlock("SkullboneFragmentsOre", "skullbone_fragments_ore", 3);
	public static final OreBlock oreVarsium = new OreBlock("VarsiumOre", "varsium_ore", 3);
	public static final OreBlock oreWhiteGemstone = new OreBlock("WhiteGemstoneOre", "white_crystal_ore", 3);
	public static final OreBlock oreYellowGemstone = new OreBlock("YellowGemstoneOre", "yellow_crystal_ore", 3);

	public static final BasicBlock bricksBaron = new BasicDecorationBlock("BaronBricks", "baron_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksBlack = new BasicDecorationBlock("BlackBricks", "black_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksBloodstone = new BasicDecorationBlock("BloodstoneBricks", "bloodstone_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksBlue = new BasicDecorationBlock("BlueBricks", "blue_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksBrown = new BasicDecorationBlock("BrownBricks", "brown_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksCoral = new BasicDecorationBlock("CoralBricks", "coral_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksCreeponia = new BasicDecorationBlock("CreeponiaBricks", "creeponia_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksCrystallite = new BasicDecorationBlock("CrystalliteBricks", "crystallite_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksCrystevia = new BasicDecorationBlock("CrysteviaBricks", "crystevia_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksCyan = new BasicDecorationBlock("CyanBricks", "cyan_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksDark = new BasicDecorationBlock("DarkBricks", "dark_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksDarkBlue = new BasicDecorationBlock("DarkBlueBricks", "dark_blue_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksDarkGrey = new BasicDecorationBlock("DarkGreyBricks", "dark_grey_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksDarkwash = new BasicDecorationBlock("DarkwashBricks", "darkwash_bricks", Material.ROCK, 4f, 15f);
	public static final BasicBlock bricksGardencia = new BasicDecorationBlock("GardenciaBricks", "gardencia_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksGreckon = new BasicDecorationBlock("GreckonBricks", "greckon_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksGreen = new BasicDecorationBlock("GreenBricks", "green_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksGrey = new BasicDecorationBlock("GreyBricks", "grey_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksHaunted = new BasicDecorationBlock("HauntedBricks", "haunted_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksIroDotted = new BasicDecorationBlock("IroDottedBricks", "iro_dotted_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksIroStriped = new BasicDecorationBlock("IroStripedBricks", "iro_striped_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksLelyetia = new BasicDecorationBlock("LelyetiaBricks", "lelyetia_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksLime = new BasicDecorationBlock("LimeBricks", "lime_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksLunar = new BasicDecorationBlock("LunarBricks", "lunar_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksMagenta = new BasicDecorationBlock("MagentaBricks", "magenta_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksMysteriumBlack = new BasicDecorationBlock("BlackMysteriumBricks", "black_mysterium_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksMysteriumGreen = new BasicDecorationBlock("GreenMysteriumBricks", "green_mysterium_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksOrange = new BasicDecorationBlock("OrangeBricks", "orange_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksPink = new BasicDecorationBlock("PinkBricks", "pink_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksPurple = new BasicDecorationBlock("PurpleBricks", "purple_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksRed = new BasicDecorationBlock("RedBricks", "red_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksRosidian = new BasicDecorationBlock("RosidianBricks", "rosidian_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksRunicConstruct = new BasicDecorationBlock("RunicConstructBricks", "runic_construct_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksShyreWhite = new BasicDecorationBlock("WhiteShyreBricks", "white_shyre_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksShyreYellow = new BasicDecorationBlock("YellowShyreBricks", "yellow_shyre_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksSkeletal = new BasicDecorationBlock("SkeletalBricks", "skeletal_bricks", Material.ROCK, 10f, 15f);
	public static final BasicBlock bricksWhite = new BasicDecorationBlock("WhiteBricks", "white_bricks", Material.ROCK, 2.0f, 10.0f);
	public static final BasicBlock bricksWhitewash = new BasicDecorationBlock("WhitewashBricks", "whitewash_bricks", Material.ROCK, 4f, 15f);
	public static final BasicBlock bricksYellow = new BasicDecorationBlock("YellowBricks", "yellow_bricks", Material.ROCK, 2.0f, 10.0f);

	public static final BasicBlock ivoryAmethystIntricate = new BasicDecorationBlock("IntricateAmethystIvory", "intricate_amethyst_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryAmethystNatural = new BasicDecorationBlock("NaturalAmethystIvory", "natural_amethyst_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryAmethystOrnate = new BasicDecorationBlock("OrnateAmethystIvory", "ornate_amethyst_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryAmethystPatterned = new BasicDecorationBlock("PatternedAmethystIvory", "patterned_amethyst_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryIntricate = new BasicDecorationBlock("IntricateIvory", "intricate_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryJadeIntricate = new BasicDecorationBlock("IntricateJadeIvory", "intricate_jade_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryJadeNatural = new BasicDecorationBlock("NaturalJadeIvory", "natural_jade_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryJadeOrnate = new BasicDecorationBlock("OrnateJadeIvory", "ornate_jade_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryJadePatterned = new BasicDecorationBlock("PatternedJadeIvory", "patterned_jade_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryLimoniteIntricate = new BasicDecorationBlock("IntricateLimoniteIvory", "intricate_limonite_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryLimoniteNatural = new BasicDecorationBlock("NaturalLimoniteIvory", "natural_limonite_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryLimoniteOrnate = new BasicDecorationBlock("OrnateLimoniteIvory", "ornate_limonite_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryLimonitePatterned = new BasicDecorationBlock("PatternedLimoniteIvory", "patterned_limonite_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryNatural = new BasicDecorationBlock("NaturalIvory", "natural_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryOrnate = new BasicDecorationBlock("OrnateIvory", "ornate_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryPatterned = new BasicDecorationBlock("PatternedIvory", "patterned_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryRositeIntricate = new BasicDecorationBlock("IntricateRositeIvory", "intricate_rosite_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryRositeNatural = new BasicDecorationBlock("NaturalRositeIvory", "natural_rosite_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryRositeOrnate = new BasicDecorationBlock("OrnateRositeIvory", "ornate_rosite_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivoryRositePatterned = new BasicDecorationBlock("PatternedRositeIvory", "patterned_rosite_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivorySapphireIntricate = new BasicDecorationBlock("IntricateSapphireIvory", "intricate_sapphire_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivorySapphireNatural = new BasicDecorationBlock("NaturalSapphireIvory", "natural_sapphire_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivorySapphireOrnate = new BasicDecorationBlock("OrnateSapphireIvory", "ornate_sapphire_ivory", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock ivorySapphirePatterned = new BasicDecorationBlock("PatternedSapphireIvory", "patterned_sapphire_ivory", Material.ROCK, 5.0f, 5.0f);

	public static final LeavesBlock leavesAchony = new LeavesBlock("AchonyLeaves", "achony_leaves");
	public static final LeavesBlock leavesBlood = new LeavesBlock("BloodLeaves", "blood_leaves");
	public static final LeavesBlock leavesBubble = new TranslucentLeavesBlock("BubbleLeaves", "bubble_leaves");
	public static final LeavesBlock leavesCelevusBlue = new LeavesBlock("BlueCelevusLeaves", "blue_celevus_leaves");
	public static final LeavesBlock leavesCelevusGreen = new LeavesBlock("GreenCelevusLeaves", "green_celevus_leaves");
	public static final LeavesBlock leavesCelevusPurple = new LeavesBlock("PurpleCelevusLeaves", "purple_celevus_leaves");
	public static final LeavesBlock leavesCelevusRed = new LeavesBlock("RedCelevusLeaves", "red_celevus_leaves");
	public static final LeavesBlock leavesCelevusWhite = new LeavesBlock("WhiteCelevusLeaves", "white_celevus_leaves");
	public static final LeavesBlock leavesCelevusYellow = new LeavesBlock("YellowCelevusLeaves", "yellow_celevus_leaves");
	public static final LeavesBlock leavesChurry = new LeavesBlock("ChurryLeaves", "churry_leaves");
	public static final LeavesBlock leavesCreep = new LeavesBlock("CreepLeaves", "creep_leaves");
	public static final LeavesBlock leavesCycade = new LeavesBlock("CycadeLeaves", "cycade_leaves");
	public static final LeavesBlock leavesDawn = new LeavesBlock("DawnLeaves", "dawn_leaves");
	public static final LeavesBlock leavesDomiguous = new LeavesBlock("DomiguousLeaves", "domiguous_leaves");
	public static final LeavesBlock leavesEternal = new LeavesBlock("EternalLeaves", "eternal_leaves");
	public static final LeavesBlock leavesEucadon = new LeavesBlock("EucadonLeaves", "eucadon_leaves");
	public static final LeavesBlock leavesHaunted = new LeavesBlock("HauntedLeaves", "haunted_leaves");
	public static final LeavesBlock leavesHauntedEyes = new LeavesBlock("HauntedEyesLeaves", "haunted_eyes_leaves");
	public static final LeavesBlock leavesHavenBlue = new LeavesBlock("BlueHavenLeaves", "blue_haven_leaves");
	public static final LeavesBlock leavesHavenPink = new LeavesBlock("PinkHavenLeaves", "pink_haven_leaves");
	public static final LeavesBlock leavesHavenPurple = new LeavesBlock("PurpleHavenLeaves", "purple_haven_leaves");
	public static final LeavesBlock leavesHavenRed = new LeavesBlock("RedHavenLeaves", "red_haven_leaves");
	public static final LeavesBlock leavesHavenTurquoise = new LeavesBlock("TurquoiseHavenLeaves", "turquoise_haven_leaves");
	public static final LeavesBlock leavesHavenYellow = new LeavesBlock("YellowHavenLeaves", "yellow_haven_leaves");
	public static final LeavesBlock leavesIrodust = new LeavesBlock("IrodustLeaves", "irodust_leaves");
	public static final LeavesBlock leavesIrogold = new LeavesBlock("IrogoldLeaves", "irogold_leaves");
	public static final LeavesBlock leavesLelyetian = new LeavesBlock("LelyetianLeaves", "lelyetian_leaves");
	public static final LeavesBlock leavesLucalus = new LeavesBlock("LucalusLeaves", "lucalus_leaves");
	public static final LeavesBlock leavesLunicia = new LeavesBlock("LuniciaLeaves", "lunicia_leaves");
	public static final LeavesBlock leavesLunosso = new LeavesBlock("LunossoLeaves", "lunosso_leaves");
	public static final LeavesBlock leavesMelumia = new LeavesBlock("MelumiaLeaves", "melumia_leaves");
	public static final LeavesBlock leavesOpollo = new LeavesBlock("OpolloLeaves", "opollo_leaves");
	public static final LeavesBlock leavesRunic = new TranslucentLeavesBlock("RunicLeaves", "runic_leaves");
	public static final LeavesBlock leavesShadow = new LeavesBlock("ShadowLeaves", "shadow_leaves");
	public static final LeavesBlock leavesShadowblood = new LeavesBlock("ShadowbloodLeaves", "shadowblood_leaves");
	public static final LeavesBlock leavesShyre = new TranslucentLeavesBlock("ShyreLeaves", "shyre_leaves");
	public static final LeavesBlock leavesShyreBright = new TranslucentLeavesBlock("BrightShyreLeaves", "bright_shyre_leaves");
	public static final LeavesBlock leavesSilvro = new LeavesBlock("SilvroLeaves", "silvro_leaves");
	public static final LeavesBlock leavesStranglewood = new LeavesBlock("StranglewoodLeaves", "stranglewood_leaves");
	public static final LeavesBlock leavesVein = new LeavesBlock("VeinLeaves", "vein_leaves");

	public static final LogBlock logAchony = new LogBlock("AchonyLog", "achony_log");
	public static final LogBlock logBlood = new LogBlock("BloodLog", "blood_log");
	public static final LogBlock logChurry = new LogBlock("ChurryLog", "churry_log");
	public static final LogBlock logCreep = new LogBlock("CreepLog", "creep_log");
	public static final LogBlock logCycade = new LogBlock("CycadeLog", "cycade_log");
	public static final LogBlock logDawn = new LogBlock("DawnLog", "dawn_log");
	public static final LogBlock logDomiguous = new LogBlock("DomiguousLog", "domiguous_log");
	public static final LogBlock logEternal = new LogBlock("EternalLog", "eternal_log");
	public static final LogBlock logEucadon = new LogBlock("EucadonLog", "eucadon_log");
	public static final LogBlock logEyeball = new LogBlock("EyeballLog", "eyeball_log");
	public static final LogBlock logHaunted = new LogBlock("HauntedLog", "haunted_log");
	public static final LogBlock logHauntedEye = new LogBlock("HauntedEyeLog", "haunted_eye_log");
	public static final LogBlock logHauntedEyes = new LogBlock("HauntedEyesLog", "haunted_eyes_log");
	public static final LogBlock logHauntedFlashing = new LogBlock("HauntedFlashingLog", "haunted_flashing_log");
	public static final LogBlock logHauntedPurpling = new LogBlock("HauntedPurplingLog", "haunted_purpling_log");
	public static final LogBlock logIro = new LogBlock("IroLog", "iro_log");
	public static final LogBlock logLucalus = new LogBlock("LucalusLog", "lucalus_log");
	public static final LogBlock logLunide = new LogBlock("LunideLog", "lunide_log");
	public static final LogBlock logMelumia = new LogBlock("MelumiaLog", "melumia_log");
	public static final LogBlock logOpollo = new LogBlock("OpolloLog", "opollo_log");
	public static final LogBlock logRunic = new LogBlock("RunicLog", "runic_log");
	public static final LogBlock logShadow = new LogBlock("ShadowLog", "shadow_log");
	public static final LogBlock logShyre = new LogBlock("ShyreLog", "shyre_log");
	public static final LogBlock logStranglewood = new LogBlock("StranglewoodLog", "stranglewood_log");
	public static final LogBlock logToxic = new LogBlock("ToxicLog", "toxic_log");

	public static final BasicBlock planksAchony = new BasicDecorationBlock("AchonyPlanks", "achony_planks", Material.WOOD);
	public static final BasicBlock planksBloodwood = new BasicDecorationBlock("BloodwoodPlanks", "bloodwood_planks", Material.WOOD);
	public static final BasicBlock planksChurry = new BasicDecorationBlock("ChurryPlanks", "churry_planks", Material.WOOD);
	public static final BasicBlock planksCreep = new BasicDecorationBlock("CreepPlanks", "creep_planks", Material.WOOD);
	public static final BasicBlock planksCycade = new BasicDecorationBlock("CycadePlanks", "cycade_planks", Material.WOOD);
	public static final BasicBlock planksDawnwood = new BasicDecorationBlock("DawnwoodPlanks", "dawnwood_planks", Material.WOOD);
	public static final BasicBlock planksDomiguous = new BasicDecorationBlock("DomiguousPlanks", "domiguous_planks", Material.WOOD);
	public static final BasicBlock planksEucadon = new BasicDecorationBlock("EucadonPlanks", "eucadon_planks", Material.WOOD);
	public static final BasicBlock planksHauntedwood = new BasicDecorationBlock("HauntedwoodPlanks", "hauntedwood_planks", Material.WOOD);
	public static final BasicBlock planksIrowood = new BasicDecorationBlock("IrowoodPlanks", "irowood_planks", Material.WOOD);
	public static final BasicBlock planksLucalus = new BasicDecorationBlock("LucalusPlanks", "lucalus_planks", Material.WOOD);
	public static final BasicBlock planksLunide = new BasicDecorationBlock("LunidePlanks", "lunide_planks", Material.WOOD);
	public static final BasicBlock planksMelumia = new BasicDecorationBlock("MelumiaPlanks", "melumia_planks", Material.WOOD);
	public static final BasicBlock planksOpollo = new BasicDecorationBlock("OpolloPlanks", "opollo_planks", Material.WOOD);
	public static final BasicBlock planksRunic = new BasicDecorationBlock("RunicPlanks", "runic_planks", Material.WOOD);
	public static final BasicBlock planksShadow = new BasicDecorationBlock("ShadowPlanks", "shadow_planks", Material.WOOD);
	public static final BasicBlock planksShyre = new BasicDecorationBlock("ShyrePlanks", "shyre_planks", Material.WOOD);
	public static final BasicBlock planksStranglewood = new BasicDecorationBlock("StranglewoodPlanks", "stranglewood_planks", Material.WOOD);
	public static final BasicBlock planksToxicwood = new BasicDecorationBlock("ToxicwoodPlanks", "toxicwood_planks", Material.WOOD);

	public static final SlabBlock.DoubleSlabBlock slabAchony = new SlabBlock.DoubleSlabBlock("AchonySlab", "achony_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabBaronBricks = new SlabBlock.DoubleSlabBlock("BaronBricksSlab", "baron_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabBlackBricks = new SlabBlock.DoubleSlabBlock("BlackBricksSlab", "black_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabBloodstoneBricks = new SlabBlock.DoubleSlabBlock("BloodstoneBricksSlab", "bloodstone_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabBloodwood = new SlabBlock.DoubleSlabBlock("BloodwoodSlab", "bloodwood_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabBlueBricks = new SlabBlock.DoubleSlabBlock("BlueBricksSlab", "blue_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabBrownBricks = new SlabBlock.DoubleSlabBlock("BrownBricksSlab", "brown_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabChurry = new SlabBlock.DoubleSlabBlock("ChurrySlab", "churry_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabCoralBricks = new SlabBlock.DoubleSlabBlock("CoralBricksSlab", "coral_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabCreep = new SlabBlock.DoubleSlabBlock("CreepSlab", "creep_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabCreeponiaBricks = new SlabBlock.DoubleSlabBlock("CreeponiaBricksSlab", "creeponia_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabCrystalliteBricks = new SlabBlock.DoubleSlabBlock("CrystalliteBricksSlab", "crystallite_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabCrysteviaBricks = new SlabBlock.DoubleSlabBlock("CrysteviaBricksSlab", "crystevia_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabCyanBricks = new SlabBlock.DoubleSlabBlock("CyanBricksSlab", "cyan_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabCycade = new SlabBlock.DoubleSlabBlock("CycadeSlab", "cycade_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabDarkBlueBricks = new SlabBlock.DoubleSlabBlock("DarkBlueBricksSlab", "dark_blue_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabDarkBricks = new SlabBlock.DoubleSlabBlock("DarkBricksSlab", "dark_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabDarkGreyBricks = new SlabBlock.DoubleSlabBlock("DarkGreyBricksSlab", "dark_grey_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabDarkwashBricks = new SlabBlock.DoubleSlabBlock("DarkwashBricksSlab", "darkwash_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabDawnwood = new SlabBlock.DoubleSlabBlock("DawnwoodSlab", "dawnwood_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabDomiguous = new SlabBlock.DoubleSlabBlock("DomiguousSlab", "domiguous_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabEucadon = new SlabBlock.DoubleSlabBlock("EucadonSlab", "eucadon_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabGardenciaBricks = new SlabBlock.DoubleSlabBlock("GardenciaBricksSlab", "gardencia_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabGreckonBricks = new SlabBlock.DoubleSlabBlock("GreckonBricksSlab", "greckon_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabGreenBricks = new SlabBlock.DoubleSlabBlock("GreenBricksSlab", "green_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabGreyBricks = new SlabBlock.DoubleSlabBlock("GreyBricksSlab", "grey_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabHauntedBricks = new SlabBlock.DoubleSlabBlock("HauntedBricksSlab", "haunted_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabHauntedwood = new SlabBlock.DoubleSlabBlock("HauntedwoodSlab", "hauntedwood_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabIroDottedBricks = new SlabBlock.DoubleSlabBlock("IroDottedBricksSlab", "iro_dotted_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIroStripedBricks = new SlabBlock.DoubleSlabBlock("IroStripedBricksSlab", "iro_striped_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIrowood = new SlabBlock.DoubleSlabBlock("IrowoodSlab", "irowood_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabIvoryAmethystIntricate = new SlabBlock.DoubleSlabBlock("IntricateAmethystIvorySlab", "intricate_amethyst_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryAmethystNatural = new SlabBlock.DoubleSlabBlock("NaturalAmethystIvorySlab", "natural_amethyst_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryAmethystOrnate = new SlabBlock.DoubleSlabBlock("OrnateAmethystIvorySlab", "ornate_amethyst_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryAmethystPatterned = new SlabBlock.DoubleSlabBlock("PatternedAmethystIvorySlab", "patterned_amethyst_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryIntricate = new SlabBlock.DoubleSlabBlock("IntricateIvorySlab", "intricate_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryJadeIntricate = new SlabBlock.DoubleSlabBlock("IntricateJadeIvorySlab", "intricate_jade_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryJadeNatural = new SlabBlock.DoubleSlabBlock("NaturalJadeIvorySlab", "natural_jade_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryJadeOrnate = new SlabBlock.DoubleSlabBlock("OrnateJadeIvorySlab", "ornate_jade_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryJadePatterned = new SlabBlock.DoubleSlabBlock("PatternedJadeIvorySlab", "patterned_jade_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryLimoniteIntricate = new SlabBlock.DoubleSlabBlock("IntricateLimoniteIvorySlab", "intricate_limonite_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryLimoniteNatural = new SlabBlock.DoubleSlabBlock("NaturalLimoniteIvorySlab", "natural_limonite_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryLimoniteOrnate = new SlabBlock.DoubleSlabBlock("OrnateLimoniteIvorySlab", "ornate_limonite_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryLimonitePatterned = new SlabBlock.DoubleSlabBlock("PatternedLimoniteIvorySlab", "patterned_limonite_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryNatural = new SlabBlock.DoubleSlabBlock("NaturalIvorySlab", "natural_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryOrnate = new SlabBlock.DoubleSlabBlock("OrnateIvorySlab", "ornate_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryPatterned = new SlabBlock.DoubleSlabBlock("PatternedIvorySlab", "patterned_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryRositeIntricate = new SlabBlock.DoubleSlabBlock("IntricateRositeIvorySlab", "intricate_rosite_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryRositeNatural = new SlabBlock.DoubleSlabBlock("NaturalRositeIvorySlab", "natural_rosite_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryRositeOrnate = new SlabBlock.DoubleSlabBlock("OrnateRositeIvorySlab", "ornate_rosite_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvoryRositePatterned = new SlabBlock.DoubleSlabBlock("PatternedRositeIvorySlab", "patterned_rosite_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvorySapphireIntricate = new SlabBlock.DoubleSlabBlock("IntricateSapphireIvorySlab", "intricate_sapphire_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvorySapphireNatural = new SlabBlock.DoubleSlabBlock("NaturalSapphireIvorySlab", "natural_sapphire_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvorySapphireOrnate = new SlabBlock.DoubleSlabBlock("OrnateSapphireIvorySlab", "ornate_sapphire_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabIvorySapphirePatterned = new SlabBlock.DoubleSlabBlock("PatternedSapphireIvorySlab", "patterned_sapphire_ivory_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabLelyetiaBricks = new SlabBlock.DoubleSlabBlock("LelyetiaBricksSlab", "lelyetia_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabLimeBricks = new SlabBlock.DoubleSlabBlock("LimeBricksSlab", "lime_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabLucalus = new SlabBlock.DoubleSlabBlock("LucalusSlab", "lucalus_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabLunarBricks = new SlabBlock.DoubleSlabBlock("LunarBricksSlab", "lunar_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabLunide = new SlabBlock.DoubleSlabBlock("LunideSlab", "lunide_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabMagentaBricks = new SlabBlock.DoubleSlabBlock("MagentaBricksSlab", "magenta_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabMelumia = new SlabBlock.DoubleSlabBlock("MelumiaSlab", "melumia_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabMysteriumBlackBricks = new SlabBlock.DoubleSlabBlock("MysteriumBlackBricksSlab", "black_mysterium_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabMysteriumGreenBricks = new SlabBlock.DoubleSlabBlock("MysteriumGreenBricksSlab", "green_mysterium_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabOpollo = new SlabBlock.DoubleSlabBlock("OpolloSlab", "opollo_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabOrangeBricks = new SlabBlock.DoubleSlabBlock("OrangeBricksSlab", "orange_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabPinkBricks = new SlabBlock.DoubleSlabBlock("PinkBricksSlab", "pink_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabPurpleBricks = new SlabBlock.DoubleSlabBlock("PurpleBricksSlab", "purple_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabRedBricks = new SlabBlock.DoubleSlabBlock("RedBricksSlab", "red_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabRosidianBricks = new SlabBlock.DoubleSlabBlock("RosidianBricksSlab", "rosidian_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabRunic = new SlabBlock.DoubleSlabBlock("RunicSlab", "runic_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabRunicConstructBricks = new SlabBlock.DoubleSlabBlock("RunicConstructBricksSlab", "runic_construct_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabShadow = new SlabBlock.DoubleSlabBlock("ShadowSlab", "shadow_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabShyre = new SlabBlock.DoubleSlabBlock("ShyreSlab", "shyre_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabShyreWhiteBricks = new SlabBlock.DoubleSlabBlock("ShyreWhiteBricksSlab", "white_shyre_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabShyreYellowBricks = new SlabBlock.DoubleSlabBlock("ShyreYellowBricksSlab", "yellow_shyre_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabSkeletalBricks = new SlabBlock.DoubleSlabBlock("SkeletalBricksSlab", "skeletal_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabStranglewood = new SlabBlock.DoubleSlabBlock("StranglewoodSlab", "stranglewood_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabToxicwood = new SlabBlock.DoubleSlabBlock("ToxicwoodSlab", "toxicwood_slab", Material.WOOD);
	public static final SlabBlock.DoubleSlabBlock slabWhitewashBricks = new SlabBlock.DoubleSlabBlock("WhitewashBricksSlab", "whitewash_bricks_slab", Material.ROCK);
	public static final SlabBlock.DoubleSlabBlock slabYellowBricks = new SlabBlock.DoubleSlabBlock("YellowBricksSlab", "yellow_bricks_slab", Material.ROCK);

	public static final StairsBlock stairsAchony = new StairsBlock("AchonyStairs", "achony_stairs", planksAchony);
	public static final StairsBlock stairsBaronBricks = new StairsBlock("BaronBricksStairs", "baron_bricks_stairs", bricksBaron);
	public static final StairsBlock stairsBlackBricks = new StairsBlock("BlackBricksStairs", "black_bricks_stairs", bricksBlack);
	public static final StairsBlock stairsBloodstoneBricks = new StairsBlock("BloodstoneBricksStairs", "bloodstone_bricks_stairs", bricksBloodstone);
	public static final StairsBlock stairsBloodwood = new StairsBlock("BloodwoodStairs", "bloodwood_stairs", planksBloodwood);
	public static final StairsBlock stairsBlueBricks = new StairsBlock("BlueBricksStairs", "blue_bricks_stairs", bricksBlue);
	public static final StairsBlock stairsBrownBricks = new StairsBlock("BrownBricksStairs", "brown_bricks_stairs", bricksBrown);
	public static final StairsBlock stairsChurry = new StairsBlock("ChurryStairs", "churry_stairs", planksChurry);
	public static final StairsBlock stairsCoralBricks = new StairsBlock("CoralBricksStairs", "coral_bricks_stairs", bricksCoral);
	public static final StairsBlock stairsCreep = new StairsBlock("CreepStairs", "creep_stairs", planksCreep);
	public static final StairsBlock stairsCreeponiaBricks = new StairsBlock("CreeponiaBricksStairs", "creeponia_bricks_stairs", bricksCreeponia);
	public static final StairsBlock stairsCrystalliteBricks = new StairsBlock("CrystalliteBricksStairs", "crystallite_bricks_stairs", bricksCrystallite);
	public static final StairsBlock stairsCrysteviaBricks = new StairsBlock("CrysteviaBricksStairs", "crystevia_bricks_stairs", bricksCrystevia);
	public static final StairsBlock stairsCyanBricks = new StairsBlock("CyanBricksStairs", "cyan_bricks_stairs", bricksCyan);
	public static final StairsBlock stairsCycade = new StairsBlock("CycadeStairs", "cycade_stairs", planksCycade);
	public static final StairsBlock stairsDarkBlueBricks = new StairsBlock("DarkBlueBricksStairs", "dark_blue_bricks_stairs", bricksDarkBlue);
	public static final StairsBlock stairsDarkBricks = new StairsBlock("DarkBricksStairs", "dark_bricks_stairs", bricksDark);
	public static final StairsBlock stairsDarkGreyBricks = new StairsBlock("DarkGreyBricksStairs", "dark_grey_bricks_stairs", bricksDarkGrey);
	public static final StairsBlock stairsDarkwashBricks = new StairsBlock("DarkwashBricksStairs", "darkwash_bricks_stairs", bricksDarkwash);
	public static final StairsBlock stairsDawnwood = new StairsBlock("DawnwoodStairs", "dawnwood_stairs", planksDawnwood);
	public static final StairsBlock stairsDomiguous = new StairsBlock("DomiguousStairs", "domiguous_stairs", planksDomiguous);
	public static final StairsBlock stairsEucadon = new StairsBlock("EucadonStairs", "eucadon_stairs", planksEucadon);
	public static final StairsBlock stairsGardenciaBricks = new StairsBlock("GardenciaBricksStairs", "gardencia_bricks_stairs", bricksGardencia);
	public static final StairsBlock stairsGreckonBricks = new StairsBlock("GreckonBricksStairs", "greckon_bricks_stairs", bricksGreckon);
	public static final StairsBlock stairsGreenBricks = new StairsBlock("GreenBricksStairs", "green_bricks_stairs", bricksGreen);
	public static final StairsBlock stairsGreyBricks = new StairsBlock("GreyBricksStairs", "grey_bricks_stairs", bricksGrey);
	public static final StairsBlock stairsHauntedBricks = new StairsBlock("HauntedBricksStairs", "haunted_bricks_stairs", bricksHaunted);
	public static final StairsBlock stairsHauntedwood = new StairsBlock("HauntedwoodStairs", "hauntedwood_stairs", planksHauntedwood);
	public static final StairsBlock stairsIroDottedBricks = new StairsBlock("IroDottedBricksStairs", "iro_dotted_bricks_stairs", bricksIroDotted);
	public static final StairsBlock stairsIroStripedBricks = new StairsBlock("IroStripedBricksStairs", "iro_striped_bricks_stairs", bricksIroStriped);
	public static final StairsBlock stairsIrowood = new StairsBlock("IrowoodStairs", "irowood_stairs", planksIrowood);
	public static final StairsBlock stairsIvoryAmethystIntricate = new StairsBlock("IntricateAmethystIvoryStairs", "intricate_amethyst_ivory_stairs", ivoryAmethystIntricate);
	public static final StairsBlock stairsIvoryAmethystNatural = new StairsBlock("NaturalAmethystIvoryStairs", "natural_amethyst_ivory_stairs", ivoryAmethystNatural);
	public static final StairsBlock stairsIvoryAmethystOrnate = new StairsBlock("OrnateAmethystIvoryStairs", "ornate_amethyst_ivory_stairs", ivoryAmethystOrnate);
	public static final StairsBlock stairsIvoryAmethystPatterned = new StairsBlock("PatternedAmethystIvoryStairs", "patterned_amethyst_ivory_stairs", ivoryAmethystPatterned);
	public static final StairsBlock stairsIvoryIntricate = new StairsBlock("IntricateIvoryStairs", "intricate_ivory_stairs", ivoryIntricate);
	public static final StairsBlock stairsIvoryJadeIntricate = new StairsBlock("IntricateJadeIvoryStairs", "intricate_jade_ivory_stairs", ivoryJadeIntricate);
	public static final StairsBlock stairsIvoryJadeNatural = new StairsBlock("NaturalJadeIvoryStairs", "natural_jade_ivory_stairs", ivoryJadeNatural);
	public static final StairsBlock stairsIvoryJadeOrnate = new StairsBlock("OrnateJadeIvoryStairs", "ornate_jade_ivory_stairs", ivoryJadeOrnate);
	public static final StairsBlock stairsIvoryJadePatterned = new StairsBlock("PatternedJadeIvoryStairs", "patterned_jade_ivory_stairs", ivoryJadePatterned);
	public static final StairsBlock stairsIvoryLimoniteIntricate = new StairsBlock("IntricateLimoniteIvoryStairs", "intricate_limonite_ivory_stairs", ivoryLimoniteIntricate);
	public static final StairsBlock stairsIvoryLimoniteNatural = new StairsBlock("NaturalLimoniteIvoryStairs", "natural_limonite_ivory_stairs", ivoryLimoniteNatural);
	public static final StairsBlock stairsIvoryLimoniteOrnate = new StairsBlock("OrnateLimoniteIvoryStairs", "ornate_limonite_ivory_stairs", ivoryLimoniteOrnate);
	public static final StairsBlock stairsIvoryLimonitePatterned = new StairsBlock("PatternedLimoniteIvoryStairs", "patterned_limonite_ivory_stairs", ivoryLimonitePatterned);
	public static final StairsBlock stairsIvoryNatural = new StairsBlock("NaturalIvoryStairs", "natural_ivory_stairs", ivoryNatural);
	public static final StairsBlock stairsIvoryOrnate = new StairsBlock("OrnateIvoryStairs", "ornate_ivory_stairs", ivoryOrnate);
	public static final StairsBlock stairsIvoryPatterned = new StairsBlock("PatternedIvoryStairs", "patterned_ivory_stairs", ivoryPatterned);
	public static final StairsBlock stairsIvoryRositeIntricate = new StairsBlock("IntricateRositeIvoryStairs", "intricate_rosite_ivory_stairs", ivoryRositeIntricate);
	public static final StairsBlock stairsIvoryRositeNatural = new StairsBlock("NaturalRositeIvoryStairs", "natural_rosite_ivory_stairs", ivoryRositeNatural);
	public static final StairsBlock stairsIvoryRositeOrnate = new StairsBlock("OrnateRositeIvoryStairs", "ornate_rosite_ivory_stairs", ivoryRositeOrnate);
	public static final StairsBlock stairsIvoryRositePatterned = new StairsBlock("PatternedRositeIvoryStairs", "patterned_rosite_ivory_stairs", ivoryRositePatterned);
	public static final StairsBlock stairsIvorySapphireIntricate = new StairsBlock("IntricateSapphireIvoryStairs", "intricate_sapphire_ivory_stairs", ivorySapphireIntricate);
	public static final StairsBlock stairsIvorySapphireNatural = new StairsBlock("NaturalSapphireIvoryStairs", "natural_sapphire_ivory_stairs", ivorySapphireNatural);
	public static final StairsBlock stairsIvorySapphireOrnate = new StairsBlock("OrnateSapphireIvoryStairs", "ornate_sapphire_ivory_stairs", ivorySapphireOrnate);
	public static final StairsBlock stairsIvorySapphirePatterned = new StairsBlock("PatternedSapphireIvoryStairs", "patterned_sapphire_ivory_stairs", ivorySapphirePatterned);
	public static final StairsBlock stairsLelyetiaBricks = new StairsBlock("LelyetiaBricksStairs", "lelyetia_bricks_stairs", bricksLelyetia);
	public static final StairsBlock stairsLimeBricks = new StairsBlock("LimeBricksStairs", "lime_bricks_stairs", bricksLime);
	public static final StairsBlock stairsLucalus = new StairsBlock("LucalusStairs", "lucalus_stairs", planksLucalus);
	public static final StairsBlock stairsLunarBricks = new StairsBlock("LunarBricksStairs", "lunar_bricks_stairs", bricksLunar);
	public static final StairsBlock stairsLunide = new StairsBlock("LunideStairs", "lunide_stairs", planksLunide);
	public static final StairsBlock stairsMagentaBricks = new StairsBlock("MagentaBricksStairs", "magenta_bricks_stairs", bricksMagenta);
	public static final StairsBlock stairsMelumia = new StairsBlock("MelumiaStairs", "melumia_stairs", planksMelumia);
	public static final StairsBlock stairsMysteriumBlackBricks = new StairsBlock("MysteriumBlackBricksStairs", "black_mysterium_bricks_stairs", bricksMysteriumBlack);
	public static final StairsBlock stairsMysteriumGreenBricks = new StairsBlock("MysteriumGreenBricksStairs", "green_mysterium_bricks_stairs", bricksMysteriumGreen);
	public static final StairsBlock stairsOpollo = new StairsBlock("OpolloStairs", "opollo_stairs", planksOpollo);
	public static final StairsBlock stairsOrangeBricks = new StairsBlock("OrangeBricksStairs", "orange_bricks_stairs", bricksOrange);
	public static final StairsBlock stairsPinkBricks = new StairsBlock("PinkBricksStairs", "pink_bricks_stairs", bricksPink);
	public static final StairsBlock stairsPurpleBricks = new StairsBlock("PurpleBricksStairs", "purple_bricks_stairs", bricksPurple);
	public static final StairsBlock stairsRedBricks = new StairsBlock("RedBricksStairs", "red_bricks_stairs", bricksRed);
	public static final StairsBlock stairsRosidianBricks = new StairsBlock("RosidianBricksStairs", "rosidian_bricks_stairs", bricksRosidian);
	public static final StairsBlock stairsRunic = new StairsBlock("RunicStairs", "runic_stairs", planksRunic);
	public static final StairsBlock stairsRunicConstructBricks = new StairsBlock("RunicConstructBricksStairs", "runic_construct_bricks_stairs", bricksRunicConstruct);
	public static final StairsBlock stairsShadow = new StairsBlock("ShadowStairs", "shadow_stairs", planksShadow);
	public static final StairsBlock stairsShyre = new StairsBlock("ShyreStairs", "shyre_stairs", planksShyre);
	public static final StairsBlock stairsShyreWhiteBricks = new StairsBlock("ShyreWhiteBricksStairs", "white_shyre_bricks_stairs", bricksShyreWhite);
	public static final StairsBlock stairsShyreYellowBricks = new StairsBlock("ShyreYellowBricksStairs", "yellow_shyre_bricks_stairs", bricksShyreYellow);
	public static final StairsBlock stairsSkeletalBricks = new StairsBlock("SkeletalBricksStairs", "skeletal_bricks_stairs", bricksSkeletal);
	public static final StairsBlock stairsStranglewood = new StairsBlock("StranglewoodStairs", "stranglewood_stairs", planksStranglewood);
	public static final StairsBlock stairsToxicwood = new StairsBlock("ToxicwoodStairs", "toxicwood_stairs", planksToxicwood);
	public static final StairsBlock stairsWhitewashBricks = new StairsBlock("WhitewashBricksStairs", "whitewash_bricks_stairs", bricksWhitewash);
	public static final StairsBlock stairsYellowBricks = new StairsBlock("YellowBricksStairs", "yellow_bricks_stairs", bricksYellow);

	public static final FenceBlock fenceAchony = new FenceBlock("AchonyFence", "achony_fence");
	public static final FenceBlock fenceBloodwood = new FenceBlock("BloodwoodFence", "bloodwood_fence");
	public static final FenceBlock fenceChurry = new FenceBlock("ChurryFence", "churry_fence");
	public static final FenceBlock fenceCreep = new FenceBlock("CreepFence", "creep_fence");
	public static final FenceBlock fenceCycade = new FenceBlock("CycadeFence", "cycade_fence");
	public static final FenceBlock fenceDawnwood = new FenceBlock("DawnwoodFence", "dawnwood_fence");
	public static final FenceBlock fenceDomiguous = new FenceBlock("DomiguousFence", "domiguous_fence");
	public static final FenceBlock fenceEucadon = new FenceBlock("EucadonFence", "eucadon_fence");
	public static final FenceBlock fenceHauntedwood = new FenceBlock("HauntedwoodFence", "hauntedwood_fence");
	public static final FenceBlock fenceIrowood = new FenceBlock("IrowoodFence", "irowood_fence");
	public static final FenceBlock fenceLucalus = new FenceBlock("LucalusFence", "lucalus_fence");
	public static final FenceBlock fenceLunide = new FenceBlock("LunideFence", "lunide_fence");
	public static final FenceBlock fenceMelumia = new FenceBlock("MelumiaFence", "melumia_fence");
	public static final FenceBlock fenceOpollo = new FenceBlock("OpolloFence", "opollo_fence");
	public static final FenceBlock fenceRunic = new FenceBlock("RunicFence", "runic_fence");
	public static final FenceBlock fenceShadow = new FenceBlock("ShadowFence", "shadow_fence");
	public static final FenceBlock fenceShyre = new FenceBlock("ShyreFence", "shyre_fence");
	public static final FenceBlock fenceStranglewood = new FenceBlock("StranglewoodFence", "stranglewood_fence");
	public static final FenceBlock fenceToxicwood = new FenceBlock("ToxicwoodFence", "toxicwood_fence");
	public static final FenceBlock fenceTwinklestone = new TwinklestoneFence();

	public static final GateBlock gateAchony = new GateBlock("AchonyGate", "achony_gate");
	public static final GateBlock gateBloodwood = new GateBlock("BloodwoodGate", "bloodwood_gate");
	public static final GateBlock gateChurry = new GateBlock("ChurryGate", "churry_gate");
	public static final GateBlock gateCreep = new GateBlock("CreepGate", "creep_gate");
	public static final GateBlock gateCycade = new GateBlock("CycadeGate", "cycade_gate");
	public static final GateBlock gateDawnwood = new GateBlock("DawnwoodGate", "dawnwood_gate");
	public static final GateBlock gateDomiguous = new GateBlock("DomiguousGate", "domiguous_gate");
	public static final GateBlock gateEucadon = new GateBlock("EucadonGate", "eucadon_gate");
	public static final GateBlock gateHauntedwood = new GateBlock("HauntedwoodGate", "hauntedwood_gate");
	public static final GateBlock gateIrowood = new GateBlock("IrowoodGate", "irowood_gate");
	public static final GateBlock gateLucalus = new GateBlock("LucalusGate", "lucalus_gate");
	public static final GateBlock gateLunide = new GateBlock("LunideGate", "lunide_gate");
	public static final GateBlock gateMelumia = new GateBlock("MelumiaGate", "melumia_gate");
	public static final GateBlock gateOpollo = new GateBlock("OpolloGate", "opollo_gate");
	public static final GateBlock gateRunic = new GateBlock("RunicGate", "runic_gate");
	public static final GateBlock gateShadow = new GateBlock("ShadowGate", "shadow_gate");
	public static final GateBlock gateShyre = new GateBlock("ShyreGate", "shyre_gate");
	public static final GateBlock gateStranglewood = new GateBlock("StranglewoodGate", "stranglewood_gate");
	public static final GateBlock gateToxicwood = new GateBlock("ToxicwoodGate", "toxicwood_gate");

	public static final BasicBlock flowerCore = new BasicBlock("FlowerCore", "flower_core", Material.GOURD);
	public static final BasicBlock mushroomAquaInside = new BasicBlock("AquaMushroomInside", "aqua_mushroom_inside", Material.GOURD);
	public static final BasicBlock mushroomAquaOutside = new BasicBlock("AquaMushroomOutside", "aqua_mushroom_outside", Material.GOURD);
	public static final BasicBlock mushroomBlack = new BasicBlock("BlackMushroom", "black_mushroom", Material.GOURD);
	public static final BasicBlock mushroomBlueInside = new BasicBlock("BlueMushroomInside", "blue_mushroom_inside", Material.GOURD);
	public static final BasicBlock mushroomBlueOutside = new BasicBlock("BlueMushroomOutside", "blue_mushroom_outside", Material.GOURD);
	public static final BasicBlock mushroomGreenInside = new BasicBlock("GreenMushroomInside", "green_mushroom_inside", Material.GOURD);
	public static final BasicBlock mushroomGreenOutside = new BasicBlock("GreenMushroomOutside", "green_mushroom_outside", Material.GOURD);
	public static final BasicBlock mushroomOrangeInside = new BasicBlock("OrangeMushroomInside", "orange_mushroom_inside", Material.GOURD);
	public static final BasicBlock mushroomOrangeOutside = new BasicBlock("OrangeMushroomOutside", "orange_mushroom_outside", Material.GOURD);
	public static final BasicBlock mushroomPeachInside = new BasicBlock("PeachMushroomInside", "peach_mushroom_inside", Material.GOURD);
	public static final BasicBlock mushroomPeachOutside = new BasicBlock("PeachMushroomOutside", "peach_mushroom_outside", Material.GOURD);
	public static final BasicBlock mushroomPurpleInside = new BasicBlock("PurpleMushroomInside", "purple_mushroom_inside", Material.GOURD);
	public static final BasicBlock mushroomPurpleOutside = new BasicBlock("PurpleMushroomOutside", "purple_mushroom_outside", Material.GOURD);
	public static final BasicBlock mushroomStemBlack = new BasicBlock("BlackMushroomStem", "black_mushroom_stem", Material.GOURD);
	public static final BasicBlock mushroomStemBlue = new BasicBlock("BlueMushroomStem", "blue_mushroom_stem", Material.GOURD);
	public static final BasicBlock mushroomStemGreen = new BasicBlock("GreenMushroomStem", "green_mushroom_stem", Material.GOURD);
	public static final BasicBlock mushroomStemOrange = new BasicBlock("OrangeMushroomStem", "orange_mushroom_stem", Material.GOURD);
	public static final BasicBlock mushroomStemPurple = new BasicBlock("PurpleMushroomStem", "purple_mushroom_stem", Material.GOURD);
	public static final BasicBlock mushroomStemYellow = new BasicBlock("YellowMushroomStem", "yellow_mushroom_stem", Material.GOURD);
	public static final BasicBlock mushroomYellowInside = new BasicBlock("YellowMushroomInside", "yellow_mushroom_inside", Material.GOURD);
	public static final BasicBlock mushroomYellowOutside = new BasicBlock("YellowMushroomOutside", "yellow_mushroom_outside", Material.GOURD);
	public static final BasicBlock plantStem = new BasicBlock("PlantStem", "plant_stem", Material.GOURD);

	public static final LightBlock lightAncient = new UnbreakableLightBlock("AncientLight", "ancient_light", Material.GLASS, 1.0f);
	public static final LightBlock lightArchaic = new UnbreakableLightBlock("ArchaicLight", "archaic_light", Material.GLASS, 0.8f);
	public static final LightBlock lightCreepCrystal = new LightBlock("CreepCrystal", "creep_crystal", Material.GLASS, 0.8f, 1.2f, 0.5f);
	public static final LightBlock lightDarkstone = new LightBlock("Darkstone", "darkstone", Material.GLASS, 0.8f, 1.2f, 0.5f);
	public static final LightBlock lightDeepCrystal = new LightBlock("DeepCrystal", "deep_crystal", Material.GLASS, 0.8f, 1.2f, 0.5f);
	public static final LightBlock lightHiveLight = new LightBlock("HiveLight", "hive_light", Material.GLASS, 0.8f, 1.2f, 0.5f);
	public static final LightBlock lightLabDonut = new UnbreakableLightBlock("LabDonutLight", "lab_donut_light", Material.GLASS, 0.8f);
	public static final LightBlock lightSteel = new LightBlock("SteelLight", "steel_light", Material.IRON, 0.1f, 5.0f, 7.5f);
	public static final LightBlock lightTwinklestone = new LightBlock("Twinklestone", "twinklestone", Material.GLASS, 0.8f, 1.2f, 0.5f);
	public static final LightBlock lightVox = new LightBlock("VoxLight", "vox_light", Material.GLASS, 0.6f, 1.2f, 0.5f);

	public static final LampBlock lampAmethyst = new LampBlock("AmethystLamp", "amethyst_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampAquatic = new LampBlock("AquaticLamp", "aquatic_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampBaronyte = new LampBlock("BaronyteLamp", "baronyte_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampBlazium = new LampBlock("BlaziumLamp", "blazium_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampBloodstone = new LampBlock("BloodstoneLamp", "bloodstone_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampCrystallite = new LampBlock("CrystalliteLamp", "crystallite_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampElecanium = new LampBlock("ElecaniumLamp", "elecanium_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampEmberstone = new LampBlock("EmberstoneLamp", "emberstone_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampFire = new LampBlock("FireLamp", "fire_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampGhastly = new LampBlock("GhastlyLamp", "ghastly_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampGhoulish = new LampBlock("GhoulishLamp", "ghoulish_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampHeatDarkGrey = new LifeLampBlock("DarkGreyLifeLamp", "dark_grey_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampIro = new LampBlock("IroLamp", "iro_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampIvory = new LampBlock("IvoryLamp", "ivory_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampIvoryAmethyst = new LampBlock("IvoryAmethystLamp", "ivory_amethyst_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampIvoryJade = new LampBlock("IvoryJadeLamp", "ivory_jade_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampIvoryLimonite = new LampBlock("IvoryLimoniteLamp", "ivory_limonite_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampIvoryRosite = new LampBlock("IvoryRositeLamp", "ivory_rosite_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampIvorySapphire = new LampBlock("IvorySapphireLamp", "ivory_sapphire_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampJade = new LampBlock("JadeLamp", "jade_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifeAqua = new LifeLampBlock("AquaLifeLamp", "aqua_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifeBlack = new LifeLampBlock("BlackLifeLamp", "black_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifeBlue = new LifeLampBlock("BlueLifeLamp", "blue_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifeBrown = new LifeLampBlock("BrownLifeLamp", "brown_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifeCyan = new LifeLampBlock("CyanLifeLamp", "cyan_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifeGreen = new LifeLampBlock("GreenLifeLamp", "green_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifeGrey = new LifeLampBlock("GreyLifeLamp", "grey_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifeLime = new LifeLampBlock("LimeLifeLamp", "lime_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifeMagenta = new LifeLampBlock("MagentaLifeLamp", "magenta_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifeOrange = new LifeLampBlock("OrangeLifeLamp", "orange_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifePink = new LifeLampBlock("PinkLifeLamp", "pink_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifePurple = new LifeLampBlock("PurpleLifeLamp", "purple_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifeRed = new LifeLampBlock("RedLifeLamp", "red_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifeWhite = new LifeLampBlock("WhiteLifeLamp", "white_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLifeYellow = new LifeLampBlock("YellowLifeLamp", "yellow_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLimonite = new LampBlock("LimoniteLamp", "limonite_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLunar = new LampBlock("LunarLamp", "lunar_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampLyon = new LampBlock("LyonLamp", "lyon_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampMystic = new LampBlock("MysticLamp", "mystic_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampNeon = new LampBlock("NeonLamp", "neon_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampNeonCircling = new LampBlock("NeonCirclingLamp", "neon_circling_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampNeonLapis = new LampBlock("NeonLapisLamp", "neon_lapis_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampNeonLapisCircling = new LampBlock("NeonLapisCirclingLamp", "neon_lapis_circling_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampNeonLapisTriangles = new LampBlock("NeonLapisTrianglesLamp", "neon_lapis_triangles_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampNeonRunic = new LampBlock("NeonRunicLamp", "neon_runic_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampNeonTriangles = new LampBlock("NeonTrianglesLamp", "neon_triangles_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampRosite = new LampBlock("RositeLamp", "rosite_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampSapphire = new LampBlock("SapphireLamp", "sapphire_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);
	public static final LampBlock lampSkeletal = new LampBlock("SkeletalLamp", "skeletal_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f);

	public static final GlassBlock glassAbyssal = new GlassBlock("AbyssalGlass", "abyssal_glass");
	public static final GlassBlock glassAncient = new UnbreakableGlassBlock("AncientGlass", "ancient_glass");
	public static final GlassBlock glassAquatic = new GlassBlock("AquaticGlass", "aquatic_glass");
	public static final GlassBlock glassArchaic = new UnbreakableGlassBlock("ArchaicGlass", "archaic_glass");
	public static final GlassBlock glassBaron = new GlassBlock("BaronGlass", "baron_glass");
	public static final GlassBlock glassDecayed = new UnbreakableGlassBlock("DecayedGlass", "decayed_glass");
	public static final GlassBlock glassGardencian = new GlassBlock("GardencianGlass", "gardencian_glass");
	public static final GlassBlock glassHaven = new GlassBlock("HavenGlass", "haven_glass");
	public static final GlassBlock glassIro = new GlassBlock("IroGlass", "iro_glass");
	public static final GlassBlock glassLabBasic = new UnbreakableGlassBlock("BasicLabGlass", "basic_lab_glass");
	public static final GlassBlock glassLabSquares = new UnbreakableGlassBlock("SquaresLabGlass", "squares_lab_glass");
	public static final GlassBlock glassLelyetian = new GlassBlock("LelyetianGlass", "lelyetian_glass");
	public static final GlassBlock glassLunar = new UnbreakableGlassBlock("LunarGlass", "lunar_glass");
	public static final GlassBlock glassRunic = new GlassBlock("RunicGlass", "runic_glass");
	public static final GlassBlock glassRunicUnbreakable = new UnbreakableGlassBlock("UnbreakableRunicGlass", "unbreakable_runic_glass");
	public static final GlassBlock glassShyre = new GlassBlock("ShyreGlass", "shyre_glass");
	public static final GlassBlock glassVox = new GlassBlock("VoxGlass", "vox_glass");
	public static final GlassBlock glassZhinx = new GlassBlock("ZhinxGlass", "zhinx_glass");

	public static final SandBlock sandPrecasian = new SandBlock("PrecasianSand", "precasian_sand");

	public static final CompressedBlock amethystBlock = new CompressedBlock("AmethystBlock", "amethyst_block");
	public static final CompressedBlock baronyteBlock = new CompressedBlock("BaronyteBlock", "baronyte_block");
	public static final CompressedBlock blaziumBlock = new CompressedBlock("BlaziumBlock", "blazium_block");
	public static final CompressedBlock bloodstoneBlock = new CompressedBlock("BloodstoneBlock", "bloodstone_block");
	public static final CompressedBlock crystalliteBlock = new CompressedBlock("CrystalliteBlock", "crystallite_block");
	public static final CompressedBlock elecaniumBlock = new CompressedBlock("ElecaniumBlock", "elecanium_block");
	public static final CompressedBlock emberstoneBlock = new CompressedBlock("EmberstoneBlock", "emberstone_block");
	public static final CompressedBlock gemenyteBlock = new CompressedBlock("GemenyteBlock", "gemenyte_block");
	public static final CompressedBlock ghastlyBlock = new CompressedBlock("GhastlyBlock", "ghastly_block");
	public static final CompressedBlock ghoulishBlock = new CompressedBlock("GhoulishBlock", "ghoulish_block");
	public static final CompressedBlock jadeBlock = new CompressedBlock("JadeBlock", "jade_block");
	public static final CompressedBlock jewelyteBlock = new CompressedBlock("JewelyteBlock", "jewelyte_block");
	public static final CompressedBlock limoniteBlock = new CompressedBlock("LimoniteBlock", "limonite_block", true);
	public static final CompressedBlock lunarBlock = new CompressedBlock("LunarBlock", "lunar_block");
	public static final CompressedBlock lyonBlock = new CompressedBlock("LyonBlock", "lyon_block");
	public static final CompressedBlock mystiteBlock = new CompressedBlock("MystiteBlock", "mystite_block");
	public static final CompressedBlock ornamyteBlock = new CompressedBlock("OrnamyteBlock", "ornamyte_block");
	public static final CompressedBlock rositeBlock = new CompressedBlock("RositeBlock", "rosite_block");
	public static final CompressedBlock sapphireBlock = new CompressedBlock("SapphireBlock", "sapphire_block");
	public static final CompressedBlock shyregemBlock = new CompressedBlock("ShyregemBlock", "shyregem_block");
	public static final CompressedBlock shyrestoneBlock = new CompressedBlock("ShyrestoneBlock", "shyrestone_block");
	public static final CompressedBlock skeletalIngotBlock = new CompressedBlock("SkeletalIngotBlock", "skeletal_ingot_block");
	public static final CompressedBlock varsiumBlock = new CompressedBlock("VarsiumBlock", "varsium_block");

	public static final CarpetBlock carpetBaron = new CarpetBlock("BaronCarpet", "baron_carpet");
	public static final CarpetBlock carpetBorean = new CarpetBlock("BoreanCarpet", "borean_carpet");
	public static final CarpetBlock carpetGardencian = new CarpetBlock("GardencianCarpet", "gardencian_carpet");
	public static final CarpetBlock carpetIro = new CarpetBlock("IroCarpet", "iro_carpet");
	public static final CarpetBlock carpetLunar = new CarpetBlock("LunarCarpet", "lunar_carpet");

	public static final BasicBlock crystallanium = new Crystallanium();
	public static final BasicBlock emberium = new Emberium();
	public static final BasicBlock shadonantium = new Shadonantium();
	public static final BasicBlock skeletanium = new Skeletanium();

	public static final BasicBlock ancientRock = new BasicBlock("AncientRock", "ancient_rock", Material.ROCK);
	public static final BasicBlock ancientTileBlack = new UnbreakableBlock("BlackAncientTile", "black_ancient_tile", Material.ROCK);
	public static final BasicBlock ancientTileCore = new UnbreakableBlock("AncientTileCore", "ancient_tile_core", Material.ROCK);
	public static final BasicBlock ancientTileGreen = new UnbreakableBlock("GreenAncientTile", "green_ancient_tile", Material.ROCK);
	public static final BasicBlock ancientTileShrine = new UnbreakableBlock("AncientTileShrine", "ancient_tile_shrine", Material.ROCK);
	public static final BasicBlock ancientTileWhite = new UnbreakableBlock("WhiteAncientTile", "white_ancient_tile", Material.ROCK);
	public static final BasicBlock archaicDirt = new UnbreakableBlock("ArchaicDirt", "archaic_dirt", Material.GROUND);
	public static final BasicBlock archaicHorizontalStream = new UnbreakableBlock("ArchaicHorizontalStream", "archaic_stream_horizontal", Material.ROCK);
	public static final BasicBlock archaicRectangles = new UnbreakableBlock("ArchaicRectangles", "archaic_rectangles", Material.ROCK);
	public static final BasicBlock archaicSquares = new UnbreakableBlock("ArchaicSquares", "archaic_squares", Material.ROCK);
	public static final BasicBlock archaicTiles = new UnbreakableBlock("ArchaicTiles", "archaic_tiles", Material.ROCK);
	public static final BasicBlock archaicVerticalStream = new UnbreakableBlock("ArchaicVerticalStream", "archaic_stream_vertical", Material.ROCK);
	public static final BasicBlock baronCastleWall = new BasicBlock("BaronCastleWall", "baron_castle_wall", Material.ROCK);
	public static final BasicBlock baronCube = new BasicBlock("BaronCube", "baron_cube", Material.ROCK);
	public static final BasicBlock baronGround = new BasicBlock("BaronGround", "baron_ground", Material.CLAY);
	public static final BasicBlock bloodstoneBarBricks = new BasicBlock("BloodstoneBarBricks", "bloodstone_bar_bricks", Material.IRON, 7.5f, 7f);
	public static final BasicBlock bloodstoneBars = new BasicBlock("BloodstoneBars", "bloodstone_bars", Material.IRON, 7.5f, 7f);
	public static final BasicBlock candyGreen = new BasicBlock("GreenCandy", "green_candy", Material.ROCK);
	public static final BasicBlock candyRed = new BasicBlock("RedCandy", "red_candy", Material.ROCK);
	public static final BasicBlock candyWhite = new BasicBlock("WhiteCandy", "white_candy", Material.ROCK);
	public static final BasicBlock chocolateBlock = new BasicBlock("ChocolateBlock", "chocolate_block", Material.ROCK);
	public static final BasicBlock chocolateBlockDark = new BasicBlock("DarkChocolateBlock", "dark_chocolate_block", Material.ROCK);
	public static final BasicBlock chocolateBlockWhite = new BasicBlock("WhiteChocolateBlock", "white_chocolate_block", Material.ROCK);
	public static final BasicBlock cogBlock = new BasicBlock("CogBlock", "cog_block", Material.ROCK);
	public static final BasicBlock coralBlue = new BasicBlock("BlueCoral", "blue_coral", Material.ROCK);
	public static final BasicBlock coralGreen = new BasicBlock("GreenCoral", "green_coral", Material.ROCK);
	public static final BasicBlock coralHard = new BasicBlock("HardCoral", "hard_coral", Material.ROCK);
	public static final BasicBlock coralOrange = new BasicBlock("OrangeCoral", "orange_coral", Material.ROCK);
	public static final BasicBlock coralPink = new BasicBlock("PinkCoral", "pink_coral", Material.ROCK);
	public static final BasicBlock coralWhite = new BasicBlock("WhiteCoral", "white_coral", Material.ROCK);
	public static final BasicBlock coralYellow = new BasicBlock("YellowCoral", "yellow_coral", Material.ROCK);
	public static final BasicBlock cottonCandyAqua = new BasicBlock("AquaCottonCandy", "aqua_cotton_candy", Material.WEB);
	public static final BasicBlock cottonCandyPink = new BasicBlock("PinkCottonCandy", "pink_cotton_candy", Material.WEB);
	public static final BasicBlock crate = new BasicDecorationBlock("Crate", "crate", Material.WOOD);
	public static final BasicBlock crystalBlue = new TranslucentBlock("BlueCrystal", "blue_crystal_block", Material.GLASS, 1.0f, 0f, 0);
	public static final BasicBlock crystalGreen = new TranslucentBlock("GreenCrystal", "green_crystal_block", Material.GLASS, 1.0f, 0f, 0);
	public static final BasicBlock crystalPurple = new TranslucentBlock("PurpleCrystal", "purple_crystal_block", Material.GLASS, 1.0f, 0f, 0);
	public static final BasicBlock crystalRed = new TranslucentBlock("RedCrystal", "red_crystal_block", Material.GLASS, 1.0f, 0f, 0);
	public static final BasicBlock crystalWhite = new TranslucentBlock("WhiteCrystal", "white_crystal_block", Material.GLASS, 1.0f, 0f, 0);
	public static final BasicBlock crystalYellow = new TranslucentBlock("YellowCrystal", "yellow_crystal_block", Material.GLASS, 1.0f, 0f, 0);
	public static final BasicBlock darkFaceBrick = new BasicBlock("DarkFaceBrick", "dark_face_brick", Material.ROCK);
	public static final BasicBlock deeplandsTrapExplosion = new DeeplandsTrapExplosion();
	public static final BasicBlock deeplandsTrapLava = new DeeplandsTrapLava();
	public static final BasicBlock deeplandsTrapNipper = new DeeplandsTrapNipper();
	public static final BasicBlock deepshine = new BasicBlock("Deepshine", "deepshine", Material.ROCK);
	public static final BasicBlock degradedSteel = new BasicBlock("DegradedSteel", "degraded_steel", Material.IRON, 5.0f, 10.0f);
	public static final BasicBlock eyeBlock = new BasicBlock("EyeBlock", "eye_block", Material.GOURD, 4.0f, 1.5f);
	public static final BasicBlock giantSnailAcid = new GiantSnailAcid();
	public static final BasicBlock gingerbread = new BasicBlock("Gingerbread", "gingerbread", Material.ROCK);
	public static final BasicBlock hauntedOrb = new BasicBlock("HauntedOrb", "haunted_orb", Material.ROCK);
	public static final BasicBlock hiveWall = new BasicBlock("HiveWall", "hive_wall", Material.ROCK);
	public static final BasicBlock iroBox = new BasicDecorationBlock("IroBox", "iro_box", Material.IRON);
	public static final BasicBlock iroBrickTrap = new IroBrickTrap();
	public static final BasicBlock iropole = new BasicNonCubeBlock("Iropole", "iropole", Material.ROCK);
	public static final BasicBlock kaiyuTempleBlockFace = new UnbreakableBlock("KaiyuTempleBlockFace", "kaiyu_temple_block_face", Material.ROCK);
	public static final BasicBlock kaiyuTempleBlockFlow = new UnbreakableBlock("KaiyuTempleBlockFlow", "kaiyu_temple_block_flow", Material.ROCK);
	public static final BasicBlock kaiyuTempleBlockMaze = new UnbreakableBlock("KaiyuTempleBlockMaze", "kaiyu_temple_block_maze", Material.ROCK);
	public static final BasicBlock kaiyuTempleBlockPass = new UnbreakableBlock("KaiyuTempleBlockPass", "kaiyu_temple_block_pass", Material.ROCK);
	public static final BasicBlock kaiyuTempleBlockPlain = new UnbreakableBlock("KaiyuTempleBlockPlain", "kaiyu_temple_block_plain", Material.ROCK);
	public static final BasicBlock kaiyuTempleBlockSquares = new UnbreakableBlock("KaiyuTempleBlockSquares", "kaiyu_temple_block_squares", Material.ROCK);
	public static final BasicBlock kaiyuTempleBlockTrack = new UnbreakableBlock("KaiyuTempleBlockTrack", "kaiyu_temple_block_track", Material.ROCK);
	public static final BasicBlock kaiyuTempleTrapFlow = new KaiyuTempleTrapWither();
	public static final BasicBlock kaiyuTempleTrapMaze = new KaiyuTempleTrapDamage();
	public static final BasicBlock kaiyuTempleTrapPass = new KaiyuTempleTrapPoison();
	public static final BasicBlock kaiyuTempleTrapSquares = new KaiyuTempleTrapFire();
	public static final BasicBlock licorice = new BasicBlock("Licorice", "licorice", Material.ROCK);
	public static final BasicBlock lunarOrbDarklight = new LunarOrbBlock("DarklightOrb", "darklight_orb");
	public static final BasicBlock lunarOrbDusk = new LunarOrbBlock("DuskOrb", "dusk_orb");
	public static final BasicBlock lunarOrbLunar = new LunarOrbBlock("LunarOrb", "lunar_orb");
	public static final BasicBlock lunarOrbMoonlight = new LunarOrbBlock("MoonlightOrb", "moonlight_orb");
	public static final BasicBlock lunarOrbSunfire = new LunarOrbBlock("SunfireOrb", "sunfire_orb");
	public static final BasicBlock lunarPad = new BasicBlock("LunarPad", "lunar_pad", Material.ROCK);
	public static final BasicBlock marshmallow = new BasicBlock("Marshmallow", "marshmallow", Material.ROCK);
	public static final BasicBlock orangeAcid = new AcidBlock("OrangeAcid", "orange_acid");
	public static final BasicBlock paraviteHive = new BasicBlock("ParaviteHive", "paravite_hive", Material.ROCK);
	public static final BasicBlock petalsBlack = new UnbreakableBlock("BlackPetals", "black_petals", Material.GOURD);
	public static final BasicBlock petalsBlue = new BasicBlock("BluePetals", "blue_petals", Material.GOURD);
	public static final BasicBlock petalsLightBlue = new BasicBlock("LightBluePetals", "light_blue_petals", Material.GOURD);
	public static final BasicBlock petalsMagenta = new BasicBlock("MagentaPetals", "magenta_petals", Material.GOURD);
	public static final BasicBlock petalsOrange = new BasicBlock("OrangePetals", "orange_petals", Material.GOURD);
	public static final BasicBlock petalsPurple = new BasicBlock("PurplePetals", "purple_petals", Material.GOURD);
	public static final BasicBlock petalsRed = new BasicBlock("RedPetals", "red_petals", Material.GOURD);
	public static final BasicBlock petalsRose = new BasicBlock("RosePetals", "rose_petals", Material.GOURD);
	public static final BasicBlock petalsYellow = new BasicBlock("YellowPetals", "yellow_petals", Material.GOURD);
	public static final BasicBlock plastic = new BasicBlock("Plastic", "plastic", Material.ROCK);
	public static final BasicBlock rockCandyBlue = new BasicBlock("BlueRockCandy", "blue_rock_candy", Material.ROCK);
	public static final BasicBlock rockCandyGreen = new BasicBlock("GreenRockCandy", "green_rock_candy", Material.ROCK);
	public static final BasicBlock rockCandyPink = new BasicBlock("PinkRockCandy", "pink_rock_candy", Material.ROCK);
	public static final BasicBlock rockCandyPurple = new BasicBlock("PurpleRockCandy", "purple_rock_candy", Material.ROCK);
	public static final RunePostBlock runePostCompass = new RunePostBlock("RunePostCompass", "rune_post_compass",63, 45);
	public static final RunePostBlock runePostDistortion = new RunePostBlock("RunePostDistortion", "rune_post_distortion", 65, 50);
	public static final RunePostBlock runePostEnergy = new RunePostBlock("RunePostEnergy", "rune_post_energy", 36, 22);
	public static final RunePostBlock runePostFire = new RunePostBlock("RunePostFire", "rune_post_fire", 8, 7);
	public static final RunePostBlock runePostKinetic = new RunePostBlock("RunePostKinetic", "rune_post_kinetic", 54, 32);
	public static final RunePostBlock runePostLife = new RunePostBlock("RunePostLife", "rune_post_life", 72, 56);
	public static final RunePostBlock runePostLunar = new RunePostBlock("RunePostLunar", "rune_post_lunar", 32, 20);
	public static final RunePostBlock runePostPoison = new RunePostBlock("RunePostPoison", "rune_post_poison", 22, 14);
	public static final RunePostBlock runePostPower = new RunePostBlock("RunePostPower", "rune_post_power", 59, 40);
	public static final RunePostBlock runePostStorm = new RunePostBlock("RunePostStorm", "rune_post_storm", 49, 30);
	public static final RunePostBlock runePostStrike = new RunePostBlock("RunePostStrike", "rune_post_strike", 42, 24);
	public static final RunePostBlock runePostWater = new RunePostBlock("RunePostWater", "rune_post_water", 15, 10);
	public static final RunePostBlock runePostWind = new RunePostBlock("RunePostWind", "rune_post_wind", 0, 4);
	public static final RunePostBlock runePostWither = new RunePostBlock("RunePostWither", "rune_post_wither", 30, 15);
	public static final BasicBlock silvroBox = new BasicDecorationBlock("SilvroBox", "silvro_box", Material.IRON);
	public static final BasicBlock skeletalBlock = new BasicBlock("SkeletalBlock", "skeletal_block", Material.ROCK, 5.0f, 5.0f);
	public static final BasicBlock skullBlock = new BasicBlock("SkullBlock", "skull_block", Material.ROCK);
	public static final BasicBlock skullBlockDark = new BasicBlock("DarkSkullBlock", "dark_skull_block", Material.ROCK);
	public static final BasicBlock tentacles = new BasicBlock("Tentacles", "tentacles", Material.GOURD, 3.0f, 1.0f);
	public static final BasicBlock tentaclesDotsLeft = new BasicBlock("TentaclesDotsLeft", "tentacles_dots_left", Material.GOURD, 3.0f, 1.0f);
	public static final BasicBlock tentaclesDotsRight = new BasicBlock("TentaclesDotsRight", "tentacles_dots_right", Material.GOURD, 3.0f, 1.0f);
	public static final BasicBlock tentaclesEyeOrange = new BasicBlock("TentaclesEyeOrange", "tentacles_eye_orange", Material.GOURD, 3.0f, 1.0f);
	public static final BasicBlock tentaclesEyeRed = new BasicBlock("TentaclesEyeRed", "tentacles_eye_red", Material.GOURD, 3.0f, 1.0f);
	public static final BasicBlock tentaclesGreen = new BasicBlock("TentaclesGreen", "tentacles_green", Material.GOURD, 3.0f, 1.0f);
	public static final BasicBlock toxicStem = new BasicBlock("ToxicStem", "toxic_stem", Material.GOURD);
	public static final BasicBlock unbreakableIroBricks = new UnbreakableBlock("UnbreakableIroBricks", "unbreakable_iro_bricks", Material.ROCK);
	public static final BasicBlock unbreakablePlantStem = new UnbreakableBlock("UnbreakablePlantStem", "unbreakable_plant_stem", Material.GOURD);
	public static final BasicBlock unbreakableRunicBricks = new UnbreakableBlock("UnbreakableRunicBricks", "unbreakable_runic_bricks", Material.ROCK);
	public static final BasicNonCubeBlock shroomBlue = new BasicNonCubeBlock("BlueShroom", "blue_shroom", Material.GOURD, 2.0f, 0.5f);
	public static final BasicNonCubeBlock shroomGreen = new BasicNonCubeBlock("GreenShroom", "green_shroom", Material.GOURD, 2.0f, 0.5f);
	public static final BasicNonCubeBlock shroomOrange = new BasicNonCubeBlock("OrangeShroom", "orange_shroom", Material.GOURD, 2.0f, 0.5f);
	public static final BasicNonCubeBlock shroomPurple = new BasicNonCubeBlock("PurpleShroom", "purple_shroom", Material.GOURD, 2.0f, 0.5f);
	public static final BasicNonCubeBlock shroomStem = new BasicNonCubeBlock("ShroomStem", "shroom_stem", Material.GOURD, 2.0f, 0.5f).setBoundingBox(BasicNonCubeBlock.shroomStemAABB);
	public static final BasicNonCubeBlock shroomVox = new BasicNonCubeBlock("VoxShroom", "vox_shroom", Material.GOURD, 2.0f, 0.5f);
	public static final BasicNonCubeBlock shroomYellow = new BasicNonCubeBlock("YellowShroom", "yellow_shroom", Material.GOURD, 2.0f, 0.5f);
	public static final BasicNonCubeBlock voxLog1 = new DirectionalBlock("VoxLog", "vox_log", Material.WOOD, 1.2f, 0.5f);
	public static final BasicNonCubeBlock voxLog2 = new DirectionalBlock("VoxLog2", "vox_log2", Material.WOOD, 1.2f, 0.5f);
	public static final BoneyBlock boneyBlock = new BoneyBlock();
	public static final CactusBlock ancientCactus = new CactusBlock("AncientCactus", "ancient_cactus");
	public static final CarvedRunicBlock carvedRuneDirection = new CarvedRunicPortalBlock("CarvedRuneOfDirection", "carved_rune_direction");
	public static final CarvedRunicBlock carvedRuneEmpowering = new CarvedRunicBlock("CarvedRuneOfEmpowering", "carved_rune_empowering");
	public static final CarvedRunicBlock carvedRuneFocus = new CarvedRunicBlock("CarvedRuneOfFocus", "carved_rune_focus");
	public static final CarvedRunicBlock carvedRunePower = new CarvedRunicPortalBlock("CarvedRuneOfPower", "carved_rune_power");
	public static final CarvedRunicBlock carvedRuneReality = new CarvedRunicPortalBlock("CarvedRuneOfReality", "carved_rune_reality");
	public static final CarvedRunicBlock carvedRuneSpace = new CarvedRunicPortalBlock("CarvedRuneOfSpace", "carved_rune_space");
	public static final CarvedRunicBlock carvedRuneTravel = new CarvedRunicPortalBlock("CarvedRuneOfTravel", "carved_rune_travel");
	public static final ChargingTable chargingTable = new ChargingTable();
	public static final CloudBlock shyreCloud = new CloudBlock("ShyreCloud", "shyre_cloud", Material.AIR);
	public static final DimensionalFabric dimensionalFabric = new DimensionalFabric();
	public static final DustopianLamp dustopianLamp = new DustopianLamp();
	public static final DustopianLampOff dustopianLampOff = new DustopianLampOff();
	public static final EnhancerBlock enhancerDamage = new EnhancerBlock("DamageEnhancer", "damage_enhancer");
	public static final EnhancerBlock enhancerDurability = new EnhancerBlock("DurabilityEnhancer", "durability_enhancer");
	public static final EnhancerBlock enhancerMagical = new EnhancerBlock("MagicalEnhancer", "magical_enhancer");
	public static final EnhancerBlock enhancerResistance = new EnhancerBlock("ResistanceEnhancer", "resistance_enhancer");
	public static final EnhancerBlock enhancerSpeed = new EnhancerBlock("SpeedEnhancer", "speed_enhancer");
	public static final EnhancerBlock enhancerWeight = new EnhancerBlock("WeightEnhancer", "weight_enhancer");
	public static final LadderBlock archaicLadder = new LadderBlock("ArchaicLadder", "archaic_ladder", -1f, 999999999f);
	public static final LogBlock celeveStem = new LogBlock("CeleveStem", "celeve_stem");
	public static final LunarPillar lunarPillar = new LunarPillar();
	public static final SpikeyPillar spikeyPillar = new SpikeyPillar();
	public static final ToxicBlock toxicBlock = new ToxicBlock();
	public static final ToxicWaste toxicWaste = new ToxicWaste();

	public static final SpawnerBlock spawnerAmphibior = new SpawnerBlock("AmphibiorSpawner", "amphibior_spawner", "amphibior");
	public static final SpawnerBlock spawnerAmphibiyte = new SpawnerBlock("AmphibiyteSpawner", "amphibiyte_spawner", "amphibiyte");
	public static final SpawnerBlock spawnerAngelica = new SpawnerBlock("AngelicaSpawner", "angelica_spawner", "angelica");
	public static final SpawnerBlock spawnerArcWizard = new SpawnerBlock("ArcWizardSpawner", "arc_wizard_spawner", "arc_wizard");
	public static final SpawnerBlock spawnerArkzyne = new SpawnerBlock("ArkzyneSpawner", "arkzyne_spawner", "arkzyne");
	public static final SpawnerBlock spawnerArocknid = new SpawnerBlock("ArocknidSpawner", "arocknid_spawner", "arocknid");
	public static final SpawnerBlock spawnerBanshee = new SpawnerBlock("BansheeSpawner", "banshee_spawner", "banshee");
	public static final SpawnerBlock spawnerBaumba = new SpawnerBlock("BaumbaSpawner", "baumba_spawner", "baumba");
	public static final SpawnerBlock spawnerBloodsucker = new SpawnerBlock("BloodsuckerSpawner", "bloodsucker_spawner", "bloodsucker");
	public static final SpawnerBlock spawnerCaneBug = new SpawnerBlock("CaneBugSpawner", "cane_bug_spawner", "cane_bug");
	public static final SpawnerBlock spawnerCrusilisk = new SpawnerBlock("CrusiliskSpawner", "crusilisk_spawner", "crusilisk");
	public static final SpawnerBlock spawnerDawnlight = new SpawnerBlock("DawnlightSpawner", "dawnlight_spawner", "dawnlight");
	public static final SpawnerBlock spawnerDaysee = new SpawnerBlock("DayseeSpawner", "daysee_spawner", "daysee");
	public static final SpawnerBlock spawnerDiocus = new SpawnerBlock("DiocusSpawner", "diocus_spawner", "diocus");
	public static final SpawnerBlock spawnerEnforcer = new SpawnerBlock("EnforcerSpawner", "enforcer_spawner", "enforcer");
	public static final SpawnerBlock spawnerExohead = new SpawnerBlock("ExoheadSpawner", "exohead_spawner", "exohead");
	public static final SpawnerBlock spawnerFacelessFloater = new SpawnerBlock("FacelessFloaterSpawner", "faceless_floater_spawner", "faceless_floater");
	public static final SpawnerBlock spawnerFenix = new SpawnerBlock("FenixSpawner", "fenix_spawner", "fenix", true);
	public static final SpawnerBlock spawnerFleshEater = new SpawnerBlock("FleshEaterSpawner", "flesh_eater_spawner", "flesh_eater");
	public static final SpawnerBlock spawnerFlowerface = new SpawnerBlock("FlowerfaceSpawner", "flowerface_spawner", "flowerface");
	public static final SpawnerBlock spawnerFungock = new SpawnerBlock("FungockSpawner", "fungock_spawner", "fungock");
	public static final SpawnerBlock spawnerGhastus = new SpawnerBlock("GhastusSpawner", "ghastus_spawner", "ghastus", true);
	public static final SpawnerBlock spawnerGingerbird = new SpawnerBlock("GingerbirdSpawner", "gingerbird_spawner", "gingerbird");
	public static final SpawnerBlock spawnerGingerbreadMan = new SpawnerBlock("GingerbreadManSpawner", "gingerbread_man_spawner", "gingerbread_man");
	public static final SpawnerBlock spawnerGoldum = new SpawnerBlock("GoldumSpawner", "goldum_spawner", "goldum", true);
	public static final SpawnerBlock spawnerGoldus = new SpawnerBlock("GoldusSpawner", "goldus_spawner", "goldus", true);
	public static final SpawnerBlock spawnerInmateX = new SpawnerBlock("InmateXSpawner", "inmate_x_spawner", "inmate_x");
	public static final SpawnerBlock spawnerInmateY = new SpawnerBlock("InmateYSpawner", "inmate_y_spawner", "inmate_y");
	public static final SpawnerBlock spawnerIosaur = new SpawnerBlock("IosaurSpawner", "iosaur_spawner", "iosaur");
	public static final SpawnerBlock spawnerJawe = new SpawnerBlock("JaweSpawner", "jawe_spawner", "jawe");
	public static final SpawnerBlock spawnerKaiyu = new SpawnerBlock("KaiyuSpawner", "kaiyu_spawner", "kaiyu");
	public static final SpawnerBlock spawnerLightwalker = new SpawnerBlock("LightwalkerSpawner", "lightwalker_spawner", "lightwalker");
	public static final SpawnerBlock spawnerLuxocron = new SpawnerBlock("LuxocronSpawner", "luxocron_spawner", "luxocron");
	public static final SpawnerBlock spawnerMechyon = new SpawnerBlock("MechyonSpawner", "mechyon_spawner", "mechyon");
	public static final SpawnerBlock spawnerMerkyre = new SpawnerBlock("MerkyreSpawner", "merkyre_spawner", "merkyre");
	public static final SpawnerBlock spawnerMermage = new SpawnerBlock("MermageSpawner", "mermage_spawner", "mermage");
	public static final SpawnerBlock spawnerMushroomSpider = new SpawnerBlock("MushroomSpiderSpawner", "mushroom_spider_spawner", "mushroom_spider");
	public static final SpawnerBlock spawnerNethengeicBeast = new SpawnerBlock("NethengeicBeastSpawner", "nethengeic_beast_spawner", "nethengeic_beast");
	public static final SpawnerBlock spawnerNightmareSpider = new SpawnerBlock("NightmareSpiderSpawner", "nightmare_spider_spawner", "nightmare_spider");
	public static final SpawnerBlock spawnerNightwing = new SpawnerBlock("NightwingSpawner", "nightwing_spawner", "nightwing");
	public static final SpawnerBlock spawnerOpteryx = new SpawnerBlock("OpteryxSpawner", "opteryx_spawner", "opteryx");
	public static final SpawnerBlock spawnerParavite = new SpawnerBlock("ParaviteSpawner", "paravite_spawner", "paravite");
	public static final SpawnerBlock spawnerPhantom = new SpawnerBlock("PhantomSpawner", "phantom_spawner", "phantom");
	public static final SpawnerBlock spawnerPodPlant = new SpawnerBlock("PodPlantSpawner", "pod_plant_spawner", "pod_plant");
	public static final SpawnerBlock spawnerRawbone = new SpawnerBlock("RawboneSpawner", "rawbone_spawner", "rawbone");
	public static final SpawnerBlock spawnerReaver = new SpawnerBlock("ReaverSpawner", "reaver_spawner", "reaver", true);
	public static final SpawnerBlock spawnerRefluct = new SpawnerBlock("RefluctSpawner", "refluct_spawner", "refluct");
	public static final SpawnerBlock spawnerRockCritter = new SpawnerBlock("RockCritterSpawner", "rock_critter_spawner", "rock_critter");
	public static final SpawnerBlock spawnerRunicGolem = new SpawnerBlock("RunicGolemSpawner", "runic_golem_spawner", "runic_golem");
	public static final SpawnerBlock spawnerRunicGuardian = new SpawnerBlock("RunicGuardianSpawner", "runic_guardian_spawner", "runic_guardian");
	public static final SpawnerBlock spawnerSeeker = new SpawnerBlock("SeekerSpawner", "seeker_spawner", "seeker");
	public static final SpawnerBlock spawnerShavo = new SpawnerBlock("ShavoSpawner", "shavo_spawner", "shavo", true);
	public static final SpawnerBlock spawnerShyreTroll = new SpawnerBlock("ShyreTrollSpawner", "shyre_troll_spawner", "shyre_troll");
	public static final SpawnerBlock spawnerSkeledon = new SpawnerBlock("SkeledonSpawner", "skeledon_spawner", "skeledon", true);
	public static final SpawnerBlock spawnerSkelekyte = new SpawnerBlock("SkelekyteSpawner", "skelekyte_spawner", "skelekyte", true);
	public static final SpawnerBlock spawnerSoulscorne = new SpawnerBlock("SoulscorneSpawner", "soulscorne_spawner", "soulscorne");
	public static final SpawnerBlock spawnerSpectralWizard = new SpawnerBlock("SpectralWizardSpawner", "spectral_wizard_spawner", "spectral_wizard");
	public static final SpawnerBlock spawnerSpinoledon = new SpawnerBlock("SpinoledonSpawner", "spinoledon_spawner", "spinoledon");
	public static final SpawnerBlock spawnerSurveyor = new SpawnerBlock("SurveyorSpawner", "surveyor_spawner", "surveyor");
	public static final SpawnerBlock spawnerTharafly = new SpawnerBlock("TharaflySpawner", "tharafly_spawner", "tharafly");
	public static final SpawnerBlock spawnerUndeadTroll = new SpawnerBlock("UndeadTrollSpawner", "undead_troll_spawner", "undead_troll");
	public static final SpawnerBlock spawnerUrioh = new SpawnerBlock("UriohSpawner", "urioh_spawner", "urioh", true);
	public static final SpawnerBlock spawnerUrv = new SpawnerBlock("UrvSpawner", "urv_spawner", "urv", true);
	public static final SpawnerBlock spawnerVineWizard = new SpawnerBlock("VineWizardSpawner", "vine_wizard_spawner", "vine_wizard");
	public static final SpawnerBlock spawnerVisage = new SpawnerBlock("VisageSpawner", "visage_spawner", "visage", true);
	public static final SpawnerBlock spawnerVolar = new SpawnerBlock("VolarSpawner", "volar_spawner", "volar");
	public static final SpawnerBlock spawnerZarg = new SpawnerBlock("ZargSpawner", "zarg_spawner", "zarg");
	public static final SpawnerBlock spawnerZhinx = new SpawnerBlock("ZhinxSpawner", "zhinx_spawner", "zhinx");
	public static final SpawnerBlock spawnerZorp = new SpawnerBlock("ZorpSpawner", "zorp_spawner", "zorp");

	public static final BossAltarBlock armyBlock = new ArmyBlock();
	public static final BossAltarBlock baronessAltar = new BaronessAltar();
	public static final BossAltarBlock candyBlock = new CandyBlock();
	public static final BossAltarBlock clunkheadAltar = new ClunkheadAltar();
	public static final BossAltarBlock craexxeusAltar = new CraexxeusAltar();
	public static final BossAltarBlock creepAltar = new CreepAltar();
	public static final BossAltarBlock dracyonAltar = new DracyonAltar();
	public static final BossAltarBlock grawAltar = new GrawAltar();
	public static final BossAltarBlock guardianAltar = new GuardianAltar();
	public static final BossAltarBlock hiveSpawner = new HiveSpawner();
	public static final BossAltarBlock hydroTable = new HydroTable();
	public static final BossAltarBlock illusionAltar = new IllusionAltar();
	public static final BossAltarBlock krorAltar = new KrorAltar();
	public static final BossAltarBlock mechbotAltar = new MechBotAltar();
	public static final BossAltarBlock powerStation = new PowerStation();
	public static final BossAltarBlock primordialShrine = new PrimordialShrine();
	public static final BossAltarBlock rockriderShrine = new RockriderShrine();
	public static final BossAltarBlock shadowAltar = new ShadowAltar();
	public static final BossAltarBlock silverfootAltar = new SilverfootAltar();
	public static final BossAltarBlock toyBox = new ToyBox();
	public static final BossAltarBlock tyrosaurAltar = new TyrosaurAltar();
	public static final BossAltarBlock vinocorneShrine = new VinocorneShrine();
	public static final BossAltarBlock visualentAltar = new VisualentAltar();
	public static final BossAltarBlock voxxulonAltar = new VoxxulonAltar();

	public static final PortalBlock portalAbyss = new PortalBlock("AbyssPortal", "abyss_portal", ConfigurationUtil.MainConfig.dimensionIds.abyss, Enums.RGBIntegers.RED_2);
	public static final PortalBlock portalAncientCavern = new PortalBlock("AncientCavernPortal", "ancient_cavern_portal", ConfigurationUtil.MainConfig.dimensionIds.ancientCavern, Enums.RGBIntegers.PIGMENT_GREEN);
	public static final PortalBlock portalBarathos = new PortalBlock("BarathosPortal", "barathos_portal", ConfigurationUtil.MainConfig.dimensionIds.barathos, Enums.RGBIntegers.LIGHT_CORAL);
	public static final PortalBlock portalBorean = new PortalBlock("BoreanPortal", "borean_portal", ConfigurationUtil.MainConfig.dimensionIds.lborean, Enums.RGBIntegers.IRIS_BLUE);
	public static final PortalBlock portalCandyland = new PortalBlock("CandylandPortal", "candyland_portal", ConfigurationUtil.MainConfig.dimensionIds.candyland, Enums.RGBIntegers.MISTY_ROSE);
	public static final PortalBlock portalCeleve = new PortalBlock("CelevePortal", "celeve_portal", ConfigurationUtil.MainConfig.dimensionIds.celeve, Enums.RGBIntegers.YELLOW_2);
	public static final PortalBlock portalCreeponia = new PortalBlock("CreeponiaPortal", "creeponia_portal", ConfigurationUtil.MainConfig.dimensionIds.creeponia, Enums.RGBIntegers.DE_YORK);
	public static final PortalBlock portalCrystevia = new PortalBlock("CrysteviaPortal", "crystevia_portal", ConfigurationUtil.MainConfig.dimensionIds.crystevia, Enums.RGBIntegers.HELIOTROPE);
	public static final PortalBlock portalDeeplands = new PortalBlock("DeeplandsPortal", "deeplands_portal", ConfigurationUtil.MainConfig.dimensionIds.deeplands, Enums.RGBIntegers.SILVER);
	public static final PortalBlock portalDustopia = new PortalBlock("DustopiaPortal", "dustopia_portal", ConfigurationUtil.MainConfig.dimensionIds.dustopia, Enums.RGBIntegers.BLACK);
	public static final PortalBlock portalGardencia = new PortalBlock("GardenciaPortal", "gardencia_portal", ConfigurationUtil.MainConfig.dimensionIds.gardencia, Enums.RGBIntegers.DEEP_PINK);
	public static final PortalBlock portalGreckon = new GreckonPortalBlock();
	public static final PortalBlock portalHaven = new PortalBlock("HavenPortal", "haven_portal", ConfigurationUtil.MainConfig.dimensionIds.haven, Enums.RGBIntegers.BRIGHT_TURQUOISE);
	public static final PortalBlock portalImmortallis = new PortalBlock("ImmortallisPortal", "immortallis_portal", ConfigurationUtil.MainConfig.dimensionIds.immortallis, Enums.RGBIntegers.GOLDEN_POPPY);
	public static final PortalBlock portalIromine = new PortalBlock("IrominePortal", "iromine_portal", ConfigurationUtil.MainConfig.dimensionIds.iromine, Enums.RGBIntegers.TANGERINE_YELLOW);
	public static final PortalBlock portalLelyetia = new PortalBlock("LelyetiaPortal", "lelyetia_portal", ConfigurationUtil.MainConfig.dimensionIds.lelyetia, Enums.RGBIntegers.MANGO_TANGO);
	public static final PortalBlock portalLunalus = new PortalBlock("LunalusPortal", "lunalus_portal", ConfigurationUtil.MainConfig.dimensionIds.lunalus, Enums.RGBIntegers.LAVENDER_BLUSH);
	public static final PortalBlock portalMysterium = new PortalBlock("MysteriumPortal", "mysterium_portal", ConfigurationUtil.MainConfig.dimensionIds.mysterium, Enums.RGBIntegers.TYRIAN_PURPLE);
	public static final PortalBlock portalPrecasia = new PortalBlock("PrecasiaPortal", "precasia_portal", ConfigurationUtil.MainConfig.dimensionIds.precasia, Enums.RGBIntegers.ELECTRIC_LIME);
	public static final PortalBlock portalRunandor = new RunandorPortalBlock();
	public static final PortalBlock portalShyrelands = new PortalBlock("ShyrelandsPortal", "shyrelands_portal", ConfigurationUtil.MainConfig.dimensionIds.shyrelands, Enums.RGBIntegers.YELLOW);
	public static final PortalBlock portalVoxPonds = new PortalBlock("VoxPondsPortal", "vox_ponds_portal", ConfigurationUtil.MainConfig.dimensionIds.voxPonds, Enums.RGBIntegers.OLIVE);

	public static final AncientAltar ancientAltar = new AncientAltar();
	public static final AncientCavernShrine shrineErebon = new AncientCavernShrine("ErebonShrine", "erebon_shrine", EREBON);
	public static final AncientCavernShrine shrineLuxon = new AncientCavernShrine("LuxonShrine", "luxon_shrine", LUXON);
	public static final AncientCavernShrine shrinePluton = new AncientCavernShrine("PlutonShrine", "pluton_shrine", PLUTON);
	public static final AncientCavernShrine shrineSelyan = new AncientCavernShrine("SelyanShrine", "selyan_shrine", SELYAN);
	public static final AscensionShrine ascensionShrine = new AscensionShrine();
	public static final BasicBlock voxCrate = new VoxCrate();
	public static final CreationForge creationForge = new CreationForge();
	public static final CrystalCreator crystalCreatorBlue = new CrystalCreator("BlueCrystalCreator", "blue_crystal_creator");
	public static final CrystalCreator crystalCreatorGreen = new CrystalCreator("GreenCrystalCreator", "green_crystal_creator");
	public static final CrystalCreator crystalCreatorPurple = new CrystalCreator("PurpleCrystalCreator", "purple_crystal_creator");
	public static final CrystalCreator crystalCreatorRed = new CrystalCreator("RedCrystalCreator", "red_crystal_creator");
	public static final CrystalCreator crystalCreatorWhite = new CrystalCreator("WhiteCrystalCreator", "white_crystal_creator");
	public static final CrystalCreator crystalCreatorYellow = new CrystalCreator("YellowCrystalCreator", "yellow_crystal_creator");
	public static final CrystalExtensionShrine crystalExtensionShrine = new CrystalExtensionShrine();
	public static final DecloggingTable decloggingTable = new DecloggingTable();
	public static final DeepCase deepCase = new DeepCase();
	public static final DivineStation divineStation = new DivineStation();
	public static final EnigmaTable enigmaTable = new EnigmaTable();
	public static final ExoidStation exoidStation = new ExoidStation();
	public static final ExtractionDevice extractionDevice = new ExtractionDevice(false);
	public static final ExtractionDevice extractionDeviceOn = new ExtractionDevice(true);
	public static final FiltrationSystem filtrationSystem = new FiltrationSystem();
	public static final GoldAccumulator goldAccumulator = new GoldAccumulator();
	public static final HauntingTable hauntingTable = new HauntingTable();
	public static final ImmortallisProgressor immortallisProgressor1 = new ImmortallisProgressor("ImmortallisProgressor1", "immortallis_progressor_1", 1);
	public static final ImmortallisProgressor immortallisProgressor2 = new ImmortallisProgressor("ImmortallisProgressor2", "immortallis_progressor_2", 2);
	public static final ImmortallisProgressor immortallisProgressor3 = new ImmortallisProgressor("ImmortallisProgressor3", "immortallis_progressor_3", 3);
	public static final ImmortallisProgressor immortallisProgressor4 = new ImmortallisProgressor("ImmortallisProgressor4", "immortallis_progressor_4", 4);
	public static final ImmortallisProgressor immortallisProgressor5 = new ImmortallisProgressor("ImmortallisProgressor5", "immortallis_progressor_5", 5);
	public static final ImmortallisProgressor immortallisProgressor6 = new ImmortallisProgressor("ImmortallisProgressor6", "immortallis_progressor_6", 6);
	public static final ImmortallisProgressor immortallisProgressor7 = new ImmortallisProgressor("ImmortallisProgressor7", "immortallis_progressor_7", 7);
	public static final ImmortallisProgressor immortallisProgressor8 = new ImmortallisProgressor("ImmortallisProgressor8", "immortallis_progressor_8", 8);
	public static final ImmortallisProgressor immortallisProgressor9 = new ImmortallisProgressor("ImmortallisProgressor9", "immortallis_progressor_9", 9);
	public static final InfusionTable infusionTable = new InfusionTable();
	public static final IroCrate iroCrate = new IroCrate();
	public static final LunarCreationTable lunarCreationTable = new LunarCreationTable();
	public static final LunarEnrichmentTable lunarEnrichmentTable = new LunarEnrichmentTable();
	public static final MendingTable mendingTable = new MendingTable();
	public static final MineralizationStation mineralizationStation = new MineralizationStation();
	public static final PetalCraftingStation petalCraftingStation = new PetalCraftingStation();
	public static final PureGoldAccumulator pureGoldAccumulator = new PureGoldAccumulator();
	public static final RecreationStation recreationStation = new RecreationStation();
	public static final RuneRandomizer runeRandomizer = new RuneRandomizer();
	public static final RuneShrine runeShrine = new RuneShrine();
	public static final RunicBlock runicBlock = new RunicBlock();
	public static final StrangeBlock strangeBlock = new StrangeBlock();
	public static final TeaSink teaSink = new TeaSink();
	public static final VoxStoreCrate voxStoreCrate = new VoxStoreCrate();
	public static final WhitewashingTable whitewashingTable = new WhitewashingTable();

	public static final GenericPlantBlock plantAquaFungiBlue = new GenericPlantBlock("BlueAquaFungi", "blue_aqua_fungi", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantAquaFungiYellow = new GenericPlantBlock("YellowAquaFungi", "yellow_aqua_fungi", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantArcbulb = new FlowerBlock("Arcbulb", "arcbulb", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantArcflower = new FlowerBlock("Arcflower", "arcflower", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantBurealStocks = new GenericPlantBlock("BurealStocks", "bureal_stocks", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantCandycane = new GenericPlantBlock("Candycane", "candycane", Material.ROCK, 0.0f, Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantCandyGrass = new GenericPlantBlock("CandyGrass", "candy_grass", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantCandyGrassBlue = new GenericPlantBlock("BlueCandyGrass", "blue_candy_grass", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantCandyGrassWhite = new GenericPlantBlock("WhiteCandyGrass", "white_candy_grass", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantCeleviansBlue = new FlowerBlock("CeleviansBlue", "celevians_blue", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantCeleviansPurple = new FlowerBlock("CeleviansPurple", "celevians_purple", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantCeleviansRed = new FlowerBlock("CeleviansRed", "celevians_red", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantCeleviansWhite = new FlowerBlock("CeleviansWhite", "celevians_white", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantChocolateGrass = new GenericPlantBlock("ChocolateGrassPlant", "chocolate_grass_plant", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantChocolateStocks = new FlowerBlock("ChocolateStocks", "chocolate_stocks", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantCreepFlowers = new FlowerBlock("CreepFlowers", "creep_flowers", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantCreepGrass = new GenericPlantBlock("CreepGrass", "creep_grass", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantCrystalBlue = new GenericPlantBlock("BlueCrystalPlant", "blue_crystal_plant", Material.GLASS, 0.0f, Material.ROCK);
	public static final GenericPlantBlock plantCrystalGreen = new GenericPlantBlock("GreenCrystalPlant", "green_crystal_plant", Material.GLASS, 0.0f, Material.ROCK);
	public static final GenericPlantBlock plantCrystalPurple = new GenericPlantBlock("PurpleCrystalPlant", "purple_crystal_plant", Material.GLASS, 0.0f, Material.ROCK);
	public static final GenericPlantBlock plantCrystalRed = new GenericPlantBlock("RedCrystalPlant", "red_crystal_plant", Material.GLASS, 0.0f, Material.ROCK);
	public static final GenericPlantBlock plantCrystalWhite = new GenericPlantBlock("WhiteCrystalPlant", "white_crystal_plant", Material.GLASS, 0.0f, Material.ROCK);
	public static final GenericPlantBlock plantCrystalYellow = new GenericPlantBlock("YellowCrystalPlant", "yellow_crystal_plant", Material.GLASS, 0.0f, Material.ROCK);
	public static final GenericPlantBlock plantDaileers = new FlowerBlock("Daileers", "daileers", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantDawnBulb = new GenericPlantBlock("DawnBulb", "dawn_bulb", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantDawnBush = new FlowerBlock("DawnBush", "dawn_bush", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantDawnFlower = new FlowerBlock("DawnFlower", "dawn_flower", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantDawnGrass = new GenericPlantBlock("DawnGrass", "dawn_grass", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantDayloomsBlue = new FlowerBlock("DayloomsBlue", "daylooms_blue", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantDayloomsPink = new FlowerBlock("DayloomsPink", "daylooms_pink", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantDayloomsYellow = new FlowerBlock("DayloomsYellow", "daylooms_yellow", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantDeadGrass = new GenericPlantBlock("DeadGrass", "dead_grass", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantDeepBlooms = new GenericPlantBlock("DeepBlooms", "deep_blooms", Material.ROCK);
	public static final GenericPlantBlock plantDeepGrass = new GenericPlantBlock("DeepGrass", "deep_grass", Material.ROCK);
	public static final GenericPlantBlock plantHauntedFlower = new FlowerBlock("HauntedFlower", "haunted_flower", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantHavenGrass = new GenericPlantBlock("HavenGrassPlant", "haven_grass_plant", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantHorizonDaisies = new FlowerBlock("HorizonDaisies", "horizon_daisies", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantIroGrass = new IroGrass();
	public static final GenericPlantBlock plantIrotops = new Irotops();
	public static final GenericPlantBlock plantLelyetianGrass = new GenericPlantBlock("LelyetianGrass", "lelyetian_grass", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantLelyetianGrassDown = new UpsideDownGenericPlant("LelyetianGrassDown", "lelyetian_grass_down", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantLuconGrass = new GenericPlantBlock("LuconGrass", "lucon_grass", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantLunalip = new Lunalip();
	public static final GenericPlantBlock plantLuntar = new Luntar();
	public static final GenericPlantBlock plantLurchians = new Lurchians();
	public static final GenericPlantBlock plantLylips = new FlowerBlock("Lylips", "lylips", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantMagias = new FlowerBlock("Magias", "magias", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantMallowPile = new GenericPlantBlock("MallowPile", "mallow_pile", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantMarshTube = new GenericPlantBlock("MarshTube", "marsh_tube", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantMellians = new FlowerBlock("Mellians", "mellians", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantMysticBush = new MysticBush();
	public static final GenericPlantBlock plantMysticFerns = new MysticFerns();
	public static final GenericPlantBlock plantOcealitesBlue = new FlowerBlock("OcealitesBlue", "ocealites_blue", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantOcealitesRed = new FlowerBlock("OcealitesRed", "ocealites_red", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantPertonias = new FlowerBlock("Pertonias", "pertonias", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantRainbowGrass = new GenericPlantBlock("RainbowGrass", "rainbow_grass", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantRainbowGrass2 = new GenericPlantBlock("RainbowGrass2", "rainbow_grass2", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantRainbowGrass3 = new GenericPlantBlock("RainbowGrass3", "rainbow_grass3", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantRuneBulbs = new GenericPlantBlock("RuneBulbs", "rune_bulbs", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantRunicBush = new FlowerBlock("RunicBush", "runic_bush", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantShadowShrub = new GenericPlantBlock("ShadowShrub", "shadow_shrub", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantShyreWeed = new FlowerBlock("ShyreWeed", "shyre_weed", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantSilverGrass = new GenericPlantBlock("SilverGrass", "silver_grass", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantTangleThorns = new GenericPlantBlock("TangleThorns", "tangle_thorns", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantTubeicles = new GenericPlantBlock("Tubeicles", "tubeicles", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantWaterweedsGreen = new GenericPlantBlock("GreenWaterweeds", "green_waterweeds", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantWaterweedsWhite = new GenericPlantBlock("WhiteWaterweeds", "white_waterweeds", Material.GRASS, Material.GROUND);
	public static final GenericPlantBlock plantWaterweedsYellow = new GenericPlantBlock("YellowWaterweeds", "yellow_waterweeds", Material.GRASS, Material.GROUND);
	public static final PlantMultiStackable plantLollypopBlue = new PlantMultiStackable("BlueLollypop", "blue_lollypop", Material.GLASS, 0.0f, Material.GRASS, Material.GROUND);
	public static final PlantMultiStackable plantLollypopRed = new PlantMultiStackable("RedLollypop", "red_lollypop", Material.GLASS, 0.0f, Material.GRASS, Material.GROUND).addStemBlock(plantLollypopBlue);
	public static final PlantMultiStackable plantLollypopYellow = new PlantMultiStackable("YellowLollypop", "yellow_lollypop", Material.GLASS, 0.0f, Material.GRASS, Material.GROUND).addStemBlock(plantLollypopBlue, plantLollypopRed);
	public static final PlantStackable plantAncientVines = new UpsideDownPlantStackable("AncientVines", "ancient_vines", Material.ROCK);
	public static final PlantStackable plantAncientVinesCap = new UpsideDownPlantStackable("AncientVinesCap", "ancient_vines_cap", Material.ROCK).setStemBlock(plantAncientVines);
	public static final PlantStackable plantBloodPineStem = new PlantStackable("BloodPineStem", "blood_pine_stem", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantBloodPine = new PlantStackable("BloodPine", "blood_pine", Material.GRASS, Material.GROUND).setStemBlock(BlockRegister.plantBloodPineStem);
	public static final PlantStackable plantBloodSpikes = new BloodSpikes();
	public static final PlantStackable plantBloodStrands = new BloodStrands();
	public static final PlantStackable plantBulbStock = new PlantStackable("EyeBulb", "bulb_stock", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantBulbStockCap = new PlantStackable("EyeBulbCap", "bulb_stock_cap", Material.GRASS, Material.GROUND).setStemBlock(plantBulbStock);
	public static final PlantStackable plantCelebulbsStem = new PlantStackable("CelebulbsStem", "celebulbs_stem", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantCelebulbsGreen = new PlantStackable("CelebulbsGreen", "celebulbs_green", Material.GRASS, Material.GROUND).setStemBlock(plantCelebulbsStem);
	public static final PlantStackable plantCelebulbsYellow = new PlantStackable("CelebulbsYellow", "celebulbs_yellow", Material.GRASS, Material.GROUND).setStemBlock(plantCelebulbsStem);
	public static final PlantStackable plantCoralCage = new PlantStackable("CoralCage", "coral_cage", Material.GRASS, Material.GROUND, Material.ROCK);
	public static final PlantStackable plantDawnStocks = new PlantStackable("DawnStocks", "dawn_stocks", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantDawnStocksTop = new PlantStackable("DawnStocksTop", "dawn_stocks_top", Material.GRASS, Material.GROUND).setStemBlock(plantDawnStocks);
	public static final PlantStackable plantDawnwoodBars = new DawnwoodBars();
	public static final PlantStackable plantDeepBulb = new PlantStackable("DeepBulb", "deep_bulb", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantDeepVines = new PlantStackable("DeepVines", "deep_vines", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantEyeShrubStem = new PlantStackable("EyeShrubStem", "eye_shrub_stem", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantEyeShrub = new PlantStackable("EyeShrub", "eye_shrub", Material.GRASS, Material.GROUND).setStemBlock(plantEyeShrubStem);
	public static final PlantStackable plantFlakeVine = new PlantStackable("FlakeVine", "flake_vine", Material.ROCK);
	public static final PlantStackable plantFlakeVineTop = new PlantStackable("FlakeVineTop", "flake_vine_top", Material.ROCK).setStemBlock(plantFlakeVine);
	public static final PlantStackable plantGardenGrass = new PlantStackable("GardenGrass", "garden_grass", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantHavendalesBlueStem = new PlantStackable("HavendalesBlueStem", "havendales_blue_stem", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantHavendalesBlue = new PlantStackable("HavendalesBlue", "havendales_blue", Material.GRASS, Material.GROUND).setStemBlock(plantHavendalesBlueStem);
	public static final PlantStackable plantHavendalesPinkStem = new PlantStackable("HavendalesPinkStem", "havendales_pink_stem", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantHavendalesPink = new PlantStackable("HavendalesPink", "havendales_pink", Material.GRASS, Material.GROUND).setStemBlock(plantHavendalesPinkStem);
	public static final PlantStackable plantHavendalesYellowStem = new PlantStackable("HavendalesYellowStem", "havendales_yellow_stem", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantHavendalesYellow = new PlantStackable("HavendalesYellow", "havendales_yellow", Material.GRASS, Material.GROUND).setStemBlock(plantHavendalesYellowStem);
	public static final PlantStackable plantLelyetianStem = new BidirectionalPlantStackable("LelyetianStem", "lelyetian_stem", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantLelyetianStemCap = new PlantStackable("LelyetianStemCap", "lelyetian_stem_cap", Material.GRASS, Material.GROUND).setStemBlock(plantLelyetianStem);
	public static final PlantStackable plantLelyetianStemCapDown = new UpsideDownPlantStackable("LelyetianStemCapDown", "lelyetian_stem_cap_down", Material.GRASS, Material.GROUND).setStemBlock(plantLelyetianStem);
	public static final PlantStackable plantLelyetianWiggler = new BidirectionalPlantStackable("LelyetianWiggler", "lelyetian_wiggler", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantLelyetianWigglerBottom = new UpsideDownPlantStackable("LelyetianWigglerBottom", "lelyetian_wiggler_bottom", Material.GRASS, Material.GROUND).setStemBlock(plantLelyetianWiggler);
	public static final PlantStackable plantLelyetianWigglerTop = new PlantStackable("LelyetianWigglerTop", "lelyetian_wiggler_top", Material.GRASS, Material.GROUND).setStemBlock(plantLelyetianWiggler);
	public static final PlantStackable plantPeppermintGreen = new PlantStackable("GreenPeppermint", "green_peppermint", Material.GLASS, 0.0f, Material.GRASS, Material.GROUND);
	public static final PlantStackable plantPeppermintRed = new PlantStackable("RedPeppermint", "red_peppermint", Material.GLASS, 0.0f, Material.GRASS, Material.GROUND);
	public static final PlantStackable plantPlasticStick = new PlantStackable("PlasticStick", "plastic_stick", Material.ROCK, 0.0f, Material.GRASS, Material.GROUND);
	public static final PlantStackable plantCandyTube = new PlantStackable("CandyTube", "candy_tube", Material.GLASS, 0.0f, Material.GRASS, Material.GROUND).setStemBlock(plantPlasticStick);
	public static final PlantStackable plantShadicles = new PlantStackable("Shadicles", "shadicles", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantShadiclesTop = new PlantStackable("ShadiclesTop", "shadicles_top", Material.GRASS, Material.GROUND).setStemBlock(plantShadicles);
	public static final PlantStackable plantShyreStock = new BidirectionalPlantStackable("ShyreStock", "shyre_stock", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantShyreCap = new PlantStackable("ShyreCap", "shyre_cap", Material.GRASS, Material.GROUND).setStemBlock(plantShyreStock);
	public static final PlantStackable plantShyreCapDown = new UpsideDownPlantStackable("ShyreCapDown", "shyre_cap_down", Material.GRASS, Material.GROUND).setStemBlock(plantShyreStock);
	public static final PlantStackable plantVoxFungiStem = new PlantStackable("VoxFungiStem", "vox_fungi_stem", Material.GRASS, Material.GROUND);
	public static final PlantStackable plantVoxFungi = new PlantStackable("VoxFungi", "vox_fungi", Material.GRASS, Material.GROUND).setStemBlock(plantVoxFungiStem);
	public static final PlantStackable plantVoxTentaclesStem = new PlantStackable("VoxTentaclesStem", "vox_tentacles_stem", Material.GRASS, Material.GROUND, Material.SPONGE);
	public static final PlantStackable plantVoxTentacles = new PlantStackable("VoxTentacles", "vox_tentacles", Material.GRASS, Material.GROUND, Material.SPONGE).setStemBlock(plantVoxTentaclesStem);
	public static final PlantStackable plantWaterweedsRed = new PlantStackable("WaterweedsRed", "red_waterweeds", Material.GRASS, Material.GROUND);
	public static final VinesBlock plantCreepVines = new VinesBlock("CreepVines", "creep_vines");

	public static final CropBlock cropBubbleBerries = new CropBlock("BubbleBerryCrop", "bubble_berry_crop");
	public static final CropBlock cropChilli = new CropBlock("ChilliCrop", "chilli_crop");
	public static final CropBlock cropEyeBulbs = new CropBlock("EyeBulbCrop", "eye_bulb_crop");
	public static final CropBlock cropFloracles = new CropBlock("FloraclesCrop", "floracles_crop");
	public static final CropBlock cropGoldicaps = new CropBlock("GoldicapsCrop", "goldicaps_crop");
	public static final CropBlock cropHeartFruit = new CropBlock("HeartFruitCrop", "heart_fruit_crop");
	public static final CropBlock cropHollyTops = new CropBlock("HollyTopsCrop", "holly_tops_crop");
	public static final CropBlock cropLunacrike = new CropBlock("LunacrikeCrop", "lunacrike_crop");
	public static final CropBlock cropLunaGlobes = new CropBlock("LunaGlobeCrop", "luna_globe_crop");
	public static final CropBlock cropLunalons = new CropBlock("LunalonsCrop", "lunalon_crop");
	public static final CropBlock cropMagicMarang = new CropBlock("MagicMarangCrop", "magic_marang_crop");
	public static final CropBlock cropMysticShrooms = new CropBlock("MysticShroomCrop", "mystic_shroom_crop");
	public static final CropBlock cropRosidons = new CropBlock("RosidonCrop", "rosidon_crop");
	public static final CropBlock cropTea = new CropBlock("TeaCrop", "tea_crop");
	public static final CropBlock cropThornyPlant = new CropBlock("ThornyPlantCrop", "thorny_plant_crop");
	public static final CropBlock cropTrilliads = new CropBlock("TrilliadCrop", "trilliad_crop");

	public static final StatueBlock statueBane = new StatueBlock("BaneStatue", "bane_statue", SoundsRegister.mobBaneLiving);
	public static final StatueBlock statueBaneGold = new StatueBlock("GoldBaneStatue", "gold_bane_statue", SoundsRegister.mobBaneLiving);
	public static final StatueBlock statueBaneOrnate = new StatueBlock("OrnateBaneStatue", "ornate_bane_statue", SoundsRegister.mobBaneLiving);
	public static final StatueBlock statueBaroness = new StatueBlock("BaronessStatue", "baroness_statue", SoundsRegister.mobArielLiving);
	public static final StatueBlock statueBaronessGold = new StatueBlock("GoldBaronessStatue", "gold_baroness_statue", SoundsRegister.mobArielLiving);
	public static final StatueBlock statueBaronessOrnate = new StatueBlock("OrnateBaronessStatue", "ornate_baroness_statue", SoundsRegister.mobArielLiving);
	public static final StatueBlock statueClunkhead = new StatueBlock("ClunkheadStatue", "clunkhead_statue", SoundsRegister.mobClunkheadDeath);
	public static final StatueBlock statueClunkheadGold = new StatueBlock("GoldClunkheadStatue", "gold_clunkhead_statue", SoundsRegister.mobClunkheadDeath);
	public static final StatueBlock statueClunkheadOrnate = new StatueBlock("OrnateClunkheadStatue", "ornate_clunkhead_statue", SoundsRegister.mobClunkheadDeath);
	public static final StatueBlock statueConiferon = new StatueBlock("ConiferonStatue", "coniferon_statue", SoundsRegister.mobConiferonLiving);
	public static final StatueBlock statueConiferonGold = new StatueBlock("GoldConiferonStatue", "gold_coniferon_statue", SoundsRegister.mobConiferonLiving);
	public static final StatueBlock statueConiferonOrnate = new StatueBlock("OrnateConiferonStatue", "ornate_coniferon_statue", SoundsRegister.mobConiferonLiving);
	public static final StatueBlock statueCorallus = new StatueBlock("CorallusStatue", "corallus_statue", SoundsRegister.mobCorallusLiving);
	public static final StatueBlock statueCorallusGold = new StatueBlock("GoldCorallusStatue", "gold_corallus_statue", SoundsRegister.mobCorallusLiving);
	public static final StatueBlock statueCorallusOrnate = new StatueBlock("OrnateCorallusStatue", "ornate_corallus_statue", SoundsRegister.mobCorallusLiving);
	public static final StatueBlock statueCottonCandor = new StatueBlock("CottonCandorStatue", "cotton_candor_statue", SoundsRegister.mobCottonCandorLiving);
	public static final StatueBlock statueCottonCandorGold = new StatueBlock("GoldCottonCandorStatue", "gold_cotton_candor_statue", SoundsRegister.mobCottonCandorLiving);
	public static final StatueBlock statueCottonCandorOrnate = new StatueBlock("OrnateCottonCandorStatue", "ornate_cotton_candor_statue", SoundsRegister.mobCottonCandorLiving);
	public static final StatueBlock statueCraexxeus = new StatueBlock("CraexxeusStatue", "craexxeus_statue", SoundsRegister.mobCraexxeusLiving);
	public static final StatueBlock statueCraexxeusGold = new StatueBlock("GoldCraexxeusStatue", "gold_craexxeus_statue", SoundsRegister.mobCraexxeusLiving);
	public static final StatueBlock statueCraexxeusOrnate = new StatueBlock("OrnateCraexxeusStatue", "ornate_craexxeus_statue", SoundsRegister.mobCraexxeusLiving);
	public static final StatueBlock statueCreep = new StatueBlock("CreepStatue", "creep_statue", SoundsRegister.mobCreepoidLiving);
	public static final StatueBlock statueCreepGold = new StatueBlock("GoldCreepStatue", "gold_creep_statue", SoundsRegister.mobCreepoidLiving);
	public static final StatueBlock statueCreepOrnate = new StatueBlock("OrnateCreepStatue", "ornate_creep_statue", SoundsRegister.mobCreepoidLiving);
	public static final StatueBlock statueCrystocore = new StatueBlock("CrystocoreStatue", "crystocore_statue", SoundsRegister.mobCrystalConstructLiving);
	public static final StatueBlock statueCrystocoreGold = new StatueBlock("GoldCrystocoreStatue", "gold_crystocore_statue", SoundsRegister.mobCrystalConstructLiving);
	public static final StatueBlock statueCrystocoreOrnate = new StatueBlock("OrnateCrystocoreStatue", "ornate_crystocore_statue", SoundsRegister.mobCrystalConstructLiving);
	public static final StatueBlock statueDracyon = new StatueBlock("DracyonStatue", "dracyon_statue", SoundsRegister.mobDracyonLiving);
	public static final StatueBlock statueDracyonGold = new StatueBlock("GoldDracyonStatue", "gold_dracyon_statue", SoundsRegister.mobDracyonLiving);
	public static final StatueBlock statueDracyonOrnate = new StatueBlock("OrnateDracyonStatue", "ornate_dracyon_statue", SoundsRegister.mobDracyonLiving);
	public static final StatueBlock statueElusive = new StatueBlock("ElusiveStatue", "elusive_statue", SoundsRegister.mobElusiveLiving);
	public static final StatueBlock statueElusiveGold = new StatueBlock("GoldElusiveStatue", "gold_elusive_statue", SoundsRegister.mobElusiveLiving);
	public static final StatueBlock statueElusiveOrnate = new StatueBlock("OrnateElusiveStatue", "ornate_elusive_statue", SoundsRegister.mobElusiveLiving);
	public static final StatueBlock statueFlash = new StatueBlock("FlashStatue", "flash_statue", SoundsRegister.mobImmortalLiving);
	public static final StatueBlock statueFlashGold = new StatueBlock("GoldFlashStatue", "gold_flash_statue", SoundsRegister.mobImmortalLiving);
	public static final StatueBlock statueFlashOrnate = new StatueBlock("OrnateFlashStatue", "ornate_flash_statue", SoundsRegister.mobImmortalLiving);
	public static final StatueBlock statueGoldorth = new StatueBlock("GoldorthStatue", "goldorth_statue", SoundsRegister.mobGoldorthLiving);
	public static final StatueBlock statueGoldorthGold = new StatueBlock("GoldGoldorthStatue", "gold_goldorth_statue", SoundsRegister.mobGoldorthLiving);
	public static final StatueBlock statueGoldorthOrnate = new StatueBlock("OrnateGoldorthStatue", "ornate_goldorth_statue", SoundsRegister.mobGoldorthLiving);
	public static final StatueBlock statueGraw = new StatueBlock("GrawStatue", "graw_statue", SoundsRegister.mobGrawLiving);
	public static final StatueBlock statueGrawGold = new StatueBlock("GoldGrawStatue", "gold_graw_statue", SoundsRegister.mobGrawLiving);
	public static final StatueBlock statueGrawOrnate = new StatueBlock("OrnateGrawStatue", "ornate_graw_statue", SoundsRegister.mobGrawLiving);
	public static final StatueBlock statueGuardian = new StatueBlock("GuardianStatue", "guardian_statue", SoundsRegister.mobGuardianDeath);
	public static final StatueBlock statueGuardianGold = new StatueBlock("GoldGuardianStatue", "gold_guardian_statue", SoundsRegister.mobGuardianDeath);
	public static final StatueBlock statueGuardianOrnate = new StatueBlock("OrnateGuardianStatue", "ornate_guardian_statue", SoundsRegister.mobGuardianDeath);
	public static final StatueBlock statueGyro = new StatueBlock("GyroStatue", "gyro_statue", SoundsRegister.mobGyroLiving);
	public static final StatueBlock statueGyroGold = new StatueBlock("GoldGyroStatue", "gold_gyro_statue", SoundsRegister.mobGyroLiving);
	public static final StatueBlock statueGyroOrnate = new StatueBlock("OrnateGyroStatue", "ornate_gyro_statue", SoundsRegister.mobGyroLiving);
	public static final StatueBlock statueHarkos = new StatueBlock("HarkosStatue", "harkos_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueHarkosGold = new StatueBlock("GoldHarkosStatue", "gold_harkos_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueHarkosOrnate = new StatueBlock("OrnateHarkosStatue", "ornate_harkos_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueHiveKing = new StatueBlock("HiveKingStatue", "hive_king_statue", SoundsRegister.mobHiveKingLiving);
	public static final StatueBlock statueHiveKingGold = new StatueBlock("GoldHiveKingStatue", "gold_hive_king_statue", SoundsRegister.mobHiveKingLiving);
	public static final StatueBlock statueHiveKingOrnate = new StatueBlock("OrnateHiveKingStatue", "ornate_hive_king_statue", SoundsRegister.mobHiveKingLiving);
	public static final StatueBlock statueHoron = new StatueBlock("HoronStatue", "horon_statue", SoundsRegister.mobHoronLiving);
	public static final StatueBlock statueHoronGold = new StatueBlock("GoldHoronStatue", "gold_horon_statue", SoundsRegister.mobHoronLiving);
	public static final StatueBlock statueHoronOrnate = new StatueBlock("OrnateHoronStatue", "ornate_horon_statue", SoundsRegister.mobHoronLiving);
	public static final StatueBlock statueHydrolisk = new StatueBlock("HydroliskStatue", "hydrolisk_statue", SoundsRegister.mobHydroliskLiving);
	public static final StatueBlock statueHydroliskGold = new StatueBlock("GoldHydroliskStatue", "gold_hydrolisk_statue", SoundsRegister.mobHydroliskLiving);
	public static final StatueBlock statueHydroliskOrnate = new StatueBlock("OrnateHydroliskStatue", "ornate_hydrolisk_statue", SoundsRegister.mobHydroliskLiving);
	public static final StatueBlock statueKajaros = new StatueBlock("KajarosStatue", "kajaros_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueKajarosGold = new StatueBlock("GoldKajarosStatue", "gold_kajaros_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueKajarosOrnate = new StatueBlock("OrnateKajarosStatue", "ornate_kajaros_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueKingBamBamBam = new StatueBlock("KingBamBamBamStatue", "king_bambambam_statue", SoundsRegister.mobKingBamBamBamLiving);
	public static final StatueBlock statueKingBamBamBamGold = new StatueBlock("GoldKingBamBamBamStatue", "gold_king_bambambam_statue", SoundsRegister.mobKingBamBamBamLiving);
	public static final StatueBlock statueKingBamBamBamOrnate = new StatueBlock("OrnateKingBamBamBamStatue", "ornate_king_bambambam_statue", SoundsRegister.mobKingBamBamBamLiving);
	public static final StatueBlock statueKingShroomus = new StatueBlock("KingShroomusStatue", "king_shroomus_statue", SoundsRegister.mobFungiLiving);
	public static final StatueBlock statueKingShroomusGold = new StatueBlock("GoldKingShroomusStatue", "gold_king_shroomus_statue", SoundsRegister.mobFungiLiving);
	public static final StatueBlock statueKingShroomusOrnate = new StatueBlock("OrnateKingShroomusStatue", "ornate_king_shroomus_statue", SoundsRegister.mobFungiLiving);
	public static final StatueBlock statueKlobber = new StatueBlock("KlobberStatue", "klobber_statue", SoundsRegister.mobImmortalLiving);
	public static final StatueBlock statueKlobberGold = new StatueBlock("GoldKlobberStatue", "gold_klobber_statue", SoundsRegister.mobImmortalLiving);
	public static final StatueBlock statueKlobberOrnate = new StatueBlock("OrnateKlobberStatue", "ornate_klobber_statue", SoundsRegister.mobImmortalLiving);
	public static final StatueBlock statueKror = new StatueBlock("KrorStatue", "kror_statue", SoundsRegister.mobKrorLiving);
	public static final StatueBlock statueKrorGold = new StatueBlock("GoldKrorStatue", "gold_kror_statue", SoundsRegister.mobKrorLiving);
	public static final StatueBlock statueKrorOrnate = new StatueBlock("OrnateKrorStatue", "ornate_kror_statue", SoundsRegister.mobKrorLiving);
	public static final StatueBlock statueMechbot = new StatueBlock("MechbotStatue", "mechbot_statue", SoundsRegister.mobMechyonLiving);
	public static final StatueBlock statueMechbotGold = new StatueBlock("GoldMechbotStatue", "gold_mechbot_statue", SoundsRegister.mobMechyonLiving);
	public static final StatueBlock statueMechbotOrnate = new StatueBlock("OrnateMechbotStatue", "ornate_mechbot_statue", SoundsRegister.mobMechyonLiving);
	public static final StatueBlock statueMirage = new StatueBlock("MirageStatue", "mirage_statue", SoundsRegister.mobImmortalLiving);
	public static final StatueBlock statueMirageGold = new StatueBlock("GoldMirageStatue", "gold_mirage_statue", SoundsRegister.mobImmortalLiving);
	public static final StatueBlock statueMirageOrnate = new StatueBlock("OrnateMirageStatue", "ornate_mirage_statue", SoundsRegister.mobImmortalLiving);
	public static final StatueBlock statueMiskel = new StatueBlock("MiskelStatue", "miskel_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueMiskelGold = new StatueBlock("GoldMiskelStatue", "gold_miskel_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueMiskelOrnate = new StatueBlock("OrnateMiskelStatue", "ornate_miskel_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueNethengeicWither = new StatueBlock("NethengeicWitherStatue", "nethengeic_wither_statue", SoundsRegister.mobNethengeicWitherLiving);
	public static final StatueBlock statueNethengeicWitherGold = new StatueBlock("GoldNethengeicWitherStatue", "gold_nethengeic_wither_statue", SoundsRegister.mobNethengeicWitherLiving);
	public static final StatueBlock statueNethengeicWitherOrnate = new StatueBlock("OrnateNethengeicWitherStatue", "ornate_nethengeic_wither_statue", SoundsRegister.mobNethengeicWitherLiving);
	public static final StatueBlock statueOkazor = new StatueBlock("OkazorStatue", "okazor_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueOkazorGold = new StatueBlock("GoldOkazorStatue", "gold_okazor_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueOkazorOrnate = new StatueBlock("OrnateOkazorStatue", "ornate_okazor_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statuePenumbra = new StatueBlock("PenumbraStatue", "penumbra_statue", SoundsRegister.mobPenumbraLiving);
	public static final StatueBlock statuePenumbraGold = new StatueBlock("GoldPenumbraStatue", "gold_penumbra_statue", SoundsRegister.mobPenumbraLiving);
	public static final StatueBlock statuePenumbraOrnate = new StatueBlock("OrnatePenumbraStatue", "ornate_penumbra_statue", SoundsRegister.mobPenumbraLiving);
	public static final StatueBlock statueProshield = new StatueBlock("ProshieldStatue", "proshield_statue", SoundsRegister.mobImmortalLiving);
	public static final StatueBlock statueProshieldGold = new StatueBlock("GoldProshieldStatue", "gold_proshield_statue", SoundsRegister.mobImmortalLiving);
	public static final StatueBlock statueProshieldOrnate = new StatueBlock("OrnateProshieldStatue", "ornate_proshield_statue", SoundsRegister.mobImmortalLiving);
	public static final StatueBlock statueRaxxan = new StatueBlock("RaxxanStatue", "raxxan_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueRaxxanGold = new StatueBlock("GoldRaxxanStatue", "gold_raxxan_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueRaxxanOrnate = new StatueBlock("OrnateRaxxanStatue", "ornate_raxxan_statue", SoundsRegister.mobPrimordialLiving);
	public static final StatueBlock statueRockrider = new StatueBlock("RockriderStatue", "rockrider_statue", SoundsRegister.mobRockRiderSwitch);
	public static final StatueBlock statueRockriderGold = new StatueBlock("GoldRockriderStatue", "gold_rockrider_statue", SoundsRegister.mobRockRiderSwitch);
	public static final StatueBlock statueRockriderOrnate = new StatueBlock("OrnateRockriderStatue", "ornate_rockrider_statue", SoundsRegister.mobRockRiderSwitch);
	public static final StatueBlock statueShadowlord = new StatueBlock("ShadowlordStatue", "shadowlord_statue", SoundsRegister.mobShadowlordLiving);
	public static final StatueBlock statueShadowlordGold = new StatueBlock("GoldShadowlordStatue", "gold_shadowlord_statue", SoundsRegister.mobShadowlordLiving);
	public static final StatueBlock statueShadowlordOrnate = new StatueBlock("OrnateShadowlordStatue", "ornate_shadowlord_statue", SoundsRegister.mobShadowlordLiving);
	public static final StatueBlock statueSilverfoot = new StatueBlock("SilverfootStatue", "silverfoot_statue", SoundEvents.BLOCK_ANVIL_FALL);
	public static final StatueBlock statueSilverfootGold = new StatueBlock("GoldSilverfootStatue", "gold_silverfoot_statue", SoundEvents.BLOCK_ANVIL_FALL);
	public static final StatueBlock statueSilverfootOrnate = new StatueBlock("OrnateSilverfootStatue", "ornate_silverfoot_statue", SoundEvents.BLOCK_ANVIL_FALL);
	public static final StatueBlock statueSkeletron = new StatueBlock("SkeletronStatue", "skeletron_statue", SoundEvents.ENTITY_SKELETON_AMBIENT);
	public static final StatueBlock statueSkeletronGold = new StatueBlock("GoldSkeletronStatue", "gold_skeletron_statue", SoundEvents.ENTITY_SKELETON_AMBIENT);
	public static final StatueBlock statueSkeletronOrnate = new StatueBlock("OrnateSkeletronStatue", "ornate_skeletron_statue", SoundEvents.ENTITY_SKELETON_AMBIENT);
	public static final StatueBlock statueSmash = new StatueBlock("SmashStatue", "smash_statue", SoundsRegister.mobSmashLiving);
	public static final StatueBlock statueSmashGold = new StatueBlock("GoldSmashStatue", "gold_smash_statue", SoundsRegister.mobSmashLiving);
	public static final StatueBlock statueSmashOrnate = new StatueBlock("OrnateSmashStatue", "ornate_smash_statue", SoundsRegister.mobSmashLiving);
	public static final StatueBlock statueTyrosaur = new StatueBlock("TyrosaurStatue", "tyrosaur_statue", SoundsRegister.mobTyrosaurLiving);
	public static final StatueBlock statueTyrosaurGold = new StatueBlock("GoldTyrosaurStatue", "gold_tyrosaur_statue", SoundsRegister.mobTyrosaurLiving);
	public static final StatueBlock statueTyrosaurOrnate = new StatueBlock("OrnateTyrosaurStatue", "ornate_tyrosaur_statue", SoundsRegister.mobTyrosaurLiving);
	public static final StatueBlock statueVinocorne = new StatueBlock("VinocorneStatue", "vinocorne_statue", SoundsRegister.mobTreeSpiritLiving);
	public static final StatueBlock statueVinocorneGold = new StatueBlock("GoldVinocorneStatue", "gold_vinocorne_statue", SoundsRegister.mobTreeSpiritLiving);
	public static final StatueBlock statueVinocorneOrnate = new StatueBlock("OrnateVinocorneStatue", "ornate_vinocorne_statue", SoundsRegister.mobTreeSpiritLiving);
	public static final StatueBlock statueVisualent = new StatueBlock("VisualentStatue", "visualent_statue", SoundsRegister.mobVisularLiving);
	public static final StatueBlock statueVisualentGold = new StatueBlock("GoldVisualentStatue", "gold_visualent_statue", SoundsRegister.mobVisularLiving);
	public static final StatueBlock statueVisualentOrnate = new StatueBlock("OrnateVisualentStatue", "ornate_visualent_statue", SoundsRegister.mobVisularLiving);
	public static final StatueBlock statueVoxxulon = new StatueBlock("VoxxulonStatue", "voxxulon_statue", SoundsRegister.mobVoxxulonLiving);
	public static final StatueBlock statueVoxxulonGold = new StatueBlock("GoldVoxxulonStatue", "gold_voxxulon_statue", SoundsRegister.mobVoxxulonLiving);
	public static final StatueBlock statueVoxxulonOrnate = new StatueBlock("OrnateVoxxulonStatue", "ornate_voxxulon_statue", SoundsRegister.mobVoxxulonLiving);
	public static final StatueBlock statueXxeus = new StatueBlock("XxeusStatue", "xxeus_statue", SoundsRegister.mobXxeusLiving);
	public static final StatueBlock statueXxeusGold = new StatueBlock("GoldXxeusStatue", "gold_xxeus_statue", SoundsRegister.mobXxeusLiving);
	public static final StatueBlock statueXxeusOrnate = new StatueBlock("OrnateXxeusStatue", "ornate_xxeus_statue", SoundsRegister.mobXxeusLiving);

	public static final BannerBlock bannerAncient = new BannerBlock("AncientBanner", "ancient_banner");
	public static final BannerBlock bannerAncientBejewelled = new BannerBlock("BejewelledAncientBanner", "bejewelled_ancient_banner");
	public static final BannerBlock bannerAncientEncrusted = new BannerBlock("EncrustedAncientBanner", "encrusted_ancient_banner");
	public static final BannerBlock bannerAncientGilded = new BannerBlock("GildedAncientBanner", "gilded_ancient_banner");
	public static final BannerBlock bannerBaron = new BannerBlock("BaronBanner", "baron_banner");
	public static final BannerBlock bannerBaronBejewelled = new BannerBlock("BejewelledBaronBanner", "bejewelled_baron_banner");
	public static final BannerBlock bannerBaronEncrusted = new BannerBlock("EncrustedBaronBanner", "encrusted_baron_banner");
	public static final BannerBlock bannerBaronGilded = new BannerBlock("GildedBaronBanner", "gilded_baron_banner");
	public static final BannerBlock bannerBlood = new BannerBlock("BloodBanner", "blood_banner");
	public static final BannerBlock bannerBloodBejewelled = new BannerBlock("BejewelledBloodBanner", "bejewelled_blood_banner");
	public static final BannerBlock bannerBloodEncrusted = new BannerBlock("EncrustedBloodBanner", "encrusted_blood_banner");
	public static final BannerBlock bannerBloodGilded = new BannerBlock("GildedBloodBanner", "gilded_blood_banner");
	public static final BannerBlock bannerBoreic = new BannerBlock("BoreicBanner", "boreic_banner");
	public static final BannerBlock bannerBoreicBejewelled = new BannerBlock("BejewelledBoreicBanner", "bejewelled_boreic_banner");
	public static final BannerBlock bannerBoreicEncrusted = new BannerBlock("EncrustedBoreicBanner", "encrusted_boreic_banner");
	public static final BannerBlock bannerBoreicGilded = new BannerBlock("GildedBoreicBanner", "gilded_boreic_banner");
	public static final BannerBlock bannerCandy = new BannerBlock("CandyBanner", "candy_banner");
	public static final BannerBlock bannerCandyBejewelled = new BannerBlock("BejewelledCandyBanner", "bejewelled_candy_banner");
	public static final BannerBlock bannerCandyEncrusted = new BannerBlock("EncrustedCandyBanner", "encrusted_candy_banner");
	public static final BannerBlock bannerCandyGilded = new BannerBlock("GildedCandyBanner", "gilded_candy_banner");
	public static final BannerBlock bannerClown = new BannerBlock("ClownBanner", "clown_banner");
	public static final BannerBlock bannerClownBejewelled = new BannerBlock("BejewelledClownBanner", "bejewelled_clown_banner");
	public static final BannerBlock bannerClownEncrusted = new BannerBlock("EncrustedClownBanner", "encrusted_clown_banner");
	public static final BannerBlock bannerClownGilded = new BannerBlock("GildedClownBanner", "gilded_clown_banner");
	public static final BannerBlock bannerCreation = new BannerBlock("CreationBanner", "creation_banner");
	public static final BannerBlock bannerCreationBejewelled = new BannerBlock("BejewelledCreationBanner", "bejewelled_creation_banner");
	public static final BannerBlock bannerCreationEncrusted = new BannerBlock("EncrustedCreationBanner", "encrusted_creation_banner");
	public static final BannerBlock bannerCreationGilded = new BannerBlock("GildedCreationBanner", "gilded_creation_banner");
	public static final BannerBlock bannerCreepoid = new BannerBlock("CreepoidBanner", "creepoid_banner");
	public static final BannerBlock bannerCreepoidBejewelled = new BannerBlock("BejewelledCreepoidBanner", "bejewelled_creepoid_banner");
	public static final BannerBlock bannerCreepoidEncrusted = new BannerBlock("EncrustedCreepoidBanner", "encrusted_creepoid_banner");
	public static final BannerBlock bannerCreepoidGilded = new BannerBlock("GildedCreepoidBanner", "gilded_creepoid_banner");
	public static final BannerBlock bannerCreepy = new BannerBlock("CreepyBanner", "creepy_banner");
	public static final BannerBlock bannerCreepyBejewelled = new BannerBlock("BejewelledCreepyBanner", "bejewelled_creepy_banner");
	public static final BannerBlock bannerCreepyEncrusted = new BannerBlock("EncrustedCreepyBanner", "encrusted_creepy_banner");
	public static final BannerBlock bannerCreepyGilded = new BannerBlock("GildedCreepyBanner", "gilded_creepy_banner");
	public static final BannerBlock bannerCrystal = new BannerBlock("CrystalBanner", "crystal_banner");
	public static final BannerBlock bannerCrystalBejewelled = new BannerBlock("BejewelledCrystalBanner", "bejewelled_crystal_banner");
	public static final BannerBlock bannerCrystalEncrusted = new BannerBlock("EncrustedCrystalBanner", "encrusted_crystal_banner");
	public static final BannerBlock bannerCrystalGilded = new BannerBlock("GildedCrystalBanner", "gilded_crystal_banner");
	public static final BannerBlock bannerDeep = new BannerBlock("DeepBanner", "deep_banner");
	public static final BannerBlock bannerDeepBejewelled = new BannerBlock("BejewelledDeepBanner", "bejewelled_deep_banner");
	public static final BannerBlock bannerDeepEncrusted = new BannerBlock("EncrustedDeepBanner", "encrusted_deep_banner");
	public static final BannerBlock bannerDeepGilded = new BannerBlock("GildedDeepBanner", "gilded_deep_banner");
	public static final BannerBlock bannerDustopian = new BannerBlock("DustopianBanner", "dustopian_banner");
	public static final BannerBlock bannerDustopianBejewelled = new BannerBlock("BejewelledDustopianBanner", "bejewelled_dustopian_banner");
	public static final BannerBlock bannerDustopianEncrusted = new BannerBlock("EncrustedDustopianBanner", "encrusted_dustopian_banner");
	public static final BannerBlock bannerDustopianGilded = new BannerBlock("GildedDustopianBanner", "gilded_dustopian_banner");
	public static final BannerBlock bannerEnergy = new BannerBlock("EnergyBanner", "energy_banner");
	public static final BannerBlock bannerEnergyBejewelled = new BannerBlock("BejewelledEnergyBanner", "bejewelled_energy_banner");
	public static final BannerBlock bannerEnergyEncrusted = new BannerBlock("EncrustedEnergyBanner", "encrusted_energy_banner");
	public static final BannerBlock bannerEnergyGilded = new BannerBlock("GildedEnergyBanner", "gilded_energy_banner");
	public static final BannerBlock bannerErebon = new BannerBlock("ErebonBanner", "erebon_banner");
	public static final BannerBlock bannerFragment = new BannerBlock("FragmentBanner", "fragment_banner");
	public static final BannerBlock bannerFragmentBejewelled = new BannerBlock("BejewelledFragmentBanner", "bejewelled_fragment_banner");
	public static final BannerBlock bannerFragmentEncrusted = new BannerBlock("EncrustedFragmentBanner", "encrusted_fragment_banner");
	public static final BannerBlock bannerFragmentGilded = new BannerBlock("GildedFragmentBanner", "gilded_fragment_banner");
	public static final BannerBlock bannerFungal = new BannerBlock("FungalBanner", "fungal_banner");
	public static final BannerBlock bannerFungalBejewelled = new BannerBlock("BejewelledFungalBanner", "bejewelled_fungal_banner");
	public static final BannerBlock bannerFungalEncrusted = new BannerBlock("EncrustedFungalBanner", "encrusted_fungal_banner");
	public static final BannerBlock bannerFungalGilded = new BannerBlock("GildedFungalBanner", "gilded_fungal_banner");
	public static final BannerBlock bannerGhostly = new BannerBlock("GhostlyBanner", "ghostly_banner");
	public static final BannerBlock bannerGhostlyBejewelled = new BannerBlock("BejewelledGhostlyBanner", "bejewelled_ghostly_banner");
	public static final BannerBlock bannerGhostlyEncrusted = new BannerBlock("EncrustedGhostlyBanner", "encrusted_ghostly_banner");
	public static final BannerBlock bannerGhostlyGilded = new BannerBlock("GildedGhostlyBanner", "gilded_ghostly_banner");
	public static final BannerBlock bannerGhoul = new BannerBlock("GhoulBanner", "ghoul_banner");
	public static final BannerBlock bannerGhoulBejewelled = new BannerBlock("BejewelledGhoulBanner", "bejewelled_ghoul_banner");
	public static final BannerBlock bannerGhoulEncrusted = new BannerBlock("EncrustedGhoulBanner", "encrusted_ghoul_banner");
	public static final BannerBlock bannerGhoulGilded = new BannerBlock("GildedGhoulBanner", "gilded_ghoul_banner");
	public static final BannerBlock bannerGingerbread = new BannerBlock("GingerbreadBanner", "gingerbread_banner");
	public static final BannerBlock bannerGingerbreadBejewelled = new BannerBlock("BejewelledGingerbreadBanner", "bejewelled_gingerbread_banner");
	public static final BannerBlock bannerGingerbreadEncrusted = new BannerBlock("EncrustedGingerbreadBanner", "encrusted_gingerbread_banner");
	public static final BannerBlock bannerGingerbreadGilded = new BannerBlock("GildedGingerbreadBanner", "gilded_gingerbread_banner");
	public static final BannerBlock bannerHaunted = new BannerBlock("HauntedBanner", "haunted_banner");
	public static final BannerBlock bannerHauntedBejewelled = new BannerBlock("BejewelledHauntedBanner", "bejewelled_haunted_banner");
	public static final BannerBlock bannerHauntedEncrusted = new BannerBlock("EncrustedHauntedBanner", "encrusted_haunted_banner");
	public static final BannerBlock bannerHauntedGilded = new BannerBlock("GildedHauntedBanner", "gilded_haunted_banner");
	public static final BannerBlock bannerIllusion = new BannerBlock("IllusionBanner", "illusion_banner");
	public static final BannerBlock bannerIllusionBejewelled = new BannerBlock("BejewelledIllusionBanner", "bejewelled_illusion_banner");
	public static final BannerBlock bannerIllusionEncrusted = new BannerBlock("EncrustedIllusionBanner", "encrusted_illusion_banner");
	public static final BannerBlock bannerIllusionGilded = new BannerBlock("GildedIllusionBanner", "gilded_illusion_banner");
	public static final BannerBlock bannerImmortal = new BannerBlock("ImmortalBanner", "immortal_banner");
	public static final BannerBlock bannerImmortalBejewelled = new BannerBlock("BejewelledImmortalBanner", "bejewelled_immortal_banner");
	public static final BannerBlock bannerImmortalEncrusted = new BannerBlock("EncrustedImmortalBanner", "encrusted_immortal_banner");
	public static final BannerBlock bannerImmortalGilded = new BannerBlock("GildedImmortalBanner", "gilded_immortal_banner");
	public static final BannerBlock bannerLelyetian = new BannerBlock("LelyetianBanner", "lelyetian_banner");
	public static final BannerBlock bannerLelyetianBejewelled = new BannerBlock("BejewelledLelyetianBanner", "bejewelled_lelyetian_banner");
	public static final BannerBlock bannerLelyetianEncrusted = new BannerBlock("EncrustedLelyetianBanner", "encrusted_lelyetian_banner");
	public static final BannerBlock bannerLelyetianGilded = new BannerBlock("GildedLelyetianBanner", "gilded_lelyetian_banner");
	public static final BannerBlock bannerLight = new BannerBlock("LightBanner", "light_banner");
	public static final BannerBlock bannerLightBejewelled = new BannerBlock("BejewelledLightBanner", "bejewelled_light_banner");
	public static final BannerBlock bannerLightEncrusted = new BannerBlock("EncrustedLightBanner", "encrusted_light_banner");
	public static final BannerBlock bannerLightGilded = new BannerBlock("GildedLightBanner", "gilded_light_banner");
	public static final BannerBlock bannerLotto = new BannerBlock("LottoBanner", "lotto_banner");
	public static final BannerBlock bannerLottoBejewelled = new BannerBlock("BejewelledLottoBanner", "bejewelled_lotto_banner");
	public static final BannerBlock bannerLottoEncrusted = new BannerBlock("EncrustedLottoBanner", "encrusted_lotto_banner");
	public static final BannerBlock bannerLottoGilded = new BannerBlock("GildedLottoBanner", "gilded_lotto_banner");
	public static final BannerBlock bannerLunar = new BannerBlock("LunarBanner", "lunar_banner");
	public static final BannerBlock bannerLunarBejewelled = new BannerBlock("BejewelledLunarBanner", "bejewelled_lunar_banner");
	public static final BannerBlock bannerLunarEncrusted = new BannerBlock("EncrustedLunarBanner", "encrusted_lunar_banner");
	public static final BannerBlock bannerLunarGilded = new BannerBlock("GildedLunarBanner", "gilded_lunar_banner");
	public static final BannerBlock bannerLuxon = new BannerBlock("LuxonBanner", "luxon_banner");
	public static final BannerBlock bannerMecha = new BannerBlock("MechaBanner", "mecha_banner");
	public static final BannerBlock bannerMechaBejewelled = new BannerBlock("BejewelledMechaBanner", "bejewelled_mecha_banner");
	public static final BannerBlock bannerMechaEncrusted = new BannerBlock("EncrustedMechaBanner", "encrusted_mecha_banner");
	public static final BannerBlock bannerMechaGilded = new BannerBlock("GildedMechaBanner", "gilded_mecha_banner");
	public static final BannerBlock bannerNethengeic = new BannerBlock("NethengeicBanner", "nethengeic_banner");
	public static final BannerBlock bannerNethengeicBejewelled = new BannerBlock("BejewelledNethengeicBanner", "bejewelled_nethengeic_banner");
	public static final BannerBlock bannerNethengeicEncrusted = new BannerBlock("EncrustedNethengeicBanner", "encrusted_nethengeic_banner");
	public static final BannerBlock bannerNethengeicGilded = new BannerBlock("GildedNethengeicBanner", "gilded_nethengeic_banner");
	public static final BannerBlock bannerNether = new BannerBlock("NetherBanner", "nether_banner");
	public static final BannerBlock bannerNetherBejewelled = new BannerBlock("BejewelledNetherBanner", "bejewelled_nether_banner");
	public static final BannerBlock bannerNetherEncrusted = new BannerBlock("EncrustedNetherBanner", "encrusted_nether_banner");
	public static final BannerBlock bannerNetherGilded = new BannerBlock("GildedNetherBanner", "gilded_nether_banner");
	public static final BannerBlock bannerPluton = new BannerBlock("PlutonBanner", "pluton_banner");
	public static final BannerBlock bannerRosidian = new BannerBlock("RosidianBanner", "rosidian_banner");
	public static final BannerBlock bannerRosidianBejewelled = new BannerBlock("BejewelledRosidianBanner", "bejewelled_rosidian_banner");
	public static final BannerBlock bannerRosidianEncrusted = new BannerBlock("EncrustedRosidianBanner", "encrusted_rosidian_banner");
	public static final BannerBlock bannerRosidianGilded = new BannerBlock("GildedRosidianBanner", "gilded_rosidian_banner");
	public static final BannerBlock bannerRunic = new BannerBlock("RunicBanner", "runic_banner");
	public static final BannerBlock bannerRunicBejewelled = new BannerBlock("BejewelledRunicBanner", "bejewelled_runic_banner");
	public static final BannerBlock bannerRunicEncrusted = new BannerBlock("EncrustedRunicBanner", "encrusted_runic_banner");
	public static final BannerBlock bannerRunicGilded = new BannerBlock("GildedRunicBanner", "gilded_runic_banner");
	public static final BannerBlock bannerSea = new BannerBlock("SeaBanner", "sea_banner");
	public static final BannerBlock bannerSeaBejewelled = new BannerBlock("BejewelledSeaBanner", "bejewelled_sea_banner");
	public static final BannerBlock bannerSeaEncrusted = new BannerBlock("EncrustedSeaBanner", "encrusted_sea_banner");
	public static final BannerBlock bannerSeaGilded = new BannerBlock("GildedSeaBanner", "gilded_sea_banner");
	public static final BannerBlock bannerSelyan = new BannerBlock("SelyanBanner", "selyan_banner");
	public static final BannerBlock bannerShadow = new BannerBlock("ShadowBanner", "shadow_banner");
	public static final BannerBlock bannerShadowBejewelled = new BannerBlock("BejewelledShadowBanner", "bejewelled_shadow_banner");
	public static final BannerBlock bannerShadowEncrusted = new BannerBlock("EncrustedShadowBanner", "encrusted_shadow_banner");
	public static final BannerBlock bannerShadowGilded = new BannerBlock("GildedShadowBanner", "gilded_shadow_banner");
	public static final BannerBlock bannerShiny = new BannerBlock("ShinyBanner", "shiny_banner");
	public static final BannerBlock bannerShinyBejewelled = new BannerBlock("BejewelledShinyBanner", "bejewelled_shiny_banner");
	public static final BannerBlock bannerShinyEncrusted = new BannerBlock("EncrustedShinyBanner", "encrusted_shiny_banner");
	public static final BannerBlock bannerShinyGilded = new BannerBlock("GildedShinyBanner", "gilded_shiny_banner");
	public static final BannerBlock bannerShyre = new BannerBlock("ShyreBanner", "shyre_banner");
	public static final BannerBlock bannerShyreBejewelled = new BannerBlock("BejewelledShyreBanner", "bejewelled_shyre_banner");
	public static final BannerBlock bannerShyreEncrusted = new BannerBlock("EncrustedShyreBanner", "encrusted_shyre_banner");
	public static final BannerBlock bannerShyreGilded = new BannerBlock("GildedShyreBanner", "gilded_shyre_banner");
	public static final BannerBlock bannerSkeletal = new BannerBlock("SkeletalBanner", "skeletal_banner");
	public static final BannerBlock bannerSkeletalBejewelled = new BannerBlock("BejewelledSkeletalBanner", "bejewelled_skeletal_banner");
	public static final BannerBlock bannerSkeletalEncrusted = new BannerBlock("EncrustedSkeletalBanner", "encrusted_skeletal_banner");
	public static final BannerBlock bannerSkeletalGilded = new BannerBlock("GildedSkeletalBanner", "gilded_skeletal_banner");
	public static final BannerBlock bannerSoul = new BannerBlock("SoulBanner", "soul_banner");
	public static final BannerBlock bannerSoulBejewelled = new BannerBlock("BejewelledSoulBanner", "bejewelled_soul_banner");
	public static final BannerBlock bannerSoulEncrusted = new BannerBlock("EncrustedSoulBanner", "encrusted_soul_banner");
	public static final BannerBlock bannerSoulGilded = new BannerBlock("GildedSoulBanner", "gilded_soul_banner");
	public static final BannerBlock bannerUtopian = new BannerBlock("UtopianBanner", "utopian_banner");
	public static final BannerBlock bannerUtopianBejewelled = new BannerBlock("BejewelledUtopianBanner", "bejewelled_utopian_banner");
	public static final BannerBlock bannerUtopianEncrusted = new BannerBlock("EncrustedUtopianBanner", "encrusted_utopian_banner");
	public static final BannerBlock bannerUtopianGilded = new BannerBlock("GildedUtopianBanner", "gilded_utopian_banner");
	public static final BannerBlock bannerVoid = new BannerBlock("VoidBanner", "void_banner");
	public static final BannerBlock bannerVoidBejewelled = new BannerBlock("BejewelledVoidBanner", "bejewelled_void_banner");
	public static final BannerBlock bannerVoidEncrusted = new BannerBlock("EncrustedVoidBanner", "encrusted_void_banner");
	public static final BannerBlock bannerVoidGilded = new BannerBlock("GildedVoidBanner", "gilded_void_banner");
	public static final BannerBlock bannerVox = new BannerBlock("VoxBanner", "vox_banner");
	public static final BannerBlock bannerVoxBejewelled = new BannerBlock("BejewelledVoxBanner", "bejewelled_vox_banner");
	public static final BannerBlock bannerVoxEncrusted = new BannerBlock("EncrustedVoxBanner", "encrusted_vox_banner");
	public static final BannerBlock bannerVoxGilded = new BannerBlock("GildedVoxBanner", "gilded_vox_banner");

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> ev) {
		ev.getRegistry().registerAll(
				stoneAbyss,
				stoneBarathos,
				stoneBaron,
				stoneBorean,
				stoneCreep,
				stoneCrystevia,
				stoneDeeplands,
				stoneDustopia,
				stoneGardencia,
				stoneGreckon,
				stoneHaven,
				stoneIromine,
				stoneLelyetia,
				stoneMysterium,
				stonePrecasiaHigh,
				stonePrecasiaLow,
				stonePrimed,
				stoneRunic,
				stoneShyrelands,
				stoneToxic,
				stoneUnstable,
				dirtBorean,
				dirtCandyland,
				dirtCeleve,
				dirtCreeponia,
				dirtDustopia,
				dirtGardencia,
				dirtGreckon,
				dirtHaven,
				dirtLunalyte,
				dirtLunasole,
				dirtMysterium,
				dirtToxic,
				grassAbyss,
				grassBorean,
				grassCandyland,
				grassCeleve,
				grassChocolate,
				grassCreeponia,
				grassCrystal,
				grassDustopia,
				grassGardencia,
				grassGreckon,
				grassHaven,
				grassIromine,
				grassLelyetia,
				grassLelyetiaDown,
				grassLunalyte,
				grassLunasole,
				grassMarshmallow,
				grassMysterium,
				grassPrecasia,
				grassRunic,
				grassShyrelands,
				grassSilvro,
				grassToxic,
				oreAmethyst,
				oreBaronyte,
				oreBlazium,
				oreBloodstone,
				oreBlueGemstone,
				oreChargedRunium,
				oreChestboneFragments,
				oreCrystallite,
				oreElecanium,
				oreEmberstone,
				oreFootboneFragments,
				oreGemenyte,
				oreGhastly,
				oreGhoulish,
				oreGreenGemstone,
				oreJade,
				oreJewelyte,
				oreLegboneFragments,
				oreLimonite,
				oreLyon,
				oreMystite,
				oreOrnamyte,
				orePurpleGemstone,
				oreRedGemstone,
				oreRosite,
				oreRunium,
				oreSapphire,
				oreShyregem,
				oreShyrestone,
				oreSkullboneFragments,
				oreVarsium,
				oreWhiteGemstone,
				oreYellowGemstone,
				bricksBaron,
				bricksBlack,
				bricksBloodstone,
				bricksBlue,
				bricksBrown,
				bricksCoral,
				bricksCreeponia,
				bricksCrystallite,
				bricksCrystevia,
				bricksCyan,
				bricksDark,
				bricksDarkBlue,
				bricksDarkGrey,
				bricksDarkwash,
				bricksGardencia,
				bricksGreckon,
				bricksGreen,
				bricksGrey,
				bricksHaunted,
				bricksIroDotted,
				bricksIroStriped,
				bricksLelyetia,
				bricksLime,
				bricksLunar,
				bricksMagenta,
				bricksMysteriumBlack,
				bricksMysteriumGreen,
				bricksOrange,
				bricksPink,
				bricksPurple,
				bricksRed,
				bricksRosidian,
				bricksRunicConstruct,
				bricksShyreWhite,
				bricksShyreYellow,
				bricksSkeletal,
				bricksWhite,
				bricksWhitewash,
				bricksYellow,
				ivoryAmethystIntricate,
				ivoryAmethystNatural,
				ivoryAmethystOrnate,
				ivoryAmethystPatterned,
				ivoryIntricate,
				ivoryJadeIntricate,
				ivoryJadeNatural,
				ivoryJadeOrnate,
				ivoryJadePatterned,
				ivoryLimoniteIntricate,
				ivoryLimoniteNatural,
				ivoryLimoniteOrnate,
				ivoryLimonitePatterned,
				ivoryNatural,
				ivoryOrnate,
				ivoryPatterned,
				ivoryRositeIntricate,
				ivoryRositeNatural,
				ivoryRositeOrnate,
				ivoryRositePatterned,
				ivorySapphireIntricate,
				ivorySapphireNatural,
				ivorySapphireOrnate,
				ivorySapphirePatterned,
				leavesAchony,
				leavesBlood,
				leavesBubble,
				leavesCelevusBlue,
				leavesCelevusGreen,
				leavesCelevusPurple,
				leavesCelevusRed,
				leavesCelevusWhite,
				leavesCelevusYellow,
				leavesChurry,
				leavesCreep,
				leavesCycade,
				leavesDawn,
				leavesDomiguous,
				leavesEternal,
				leavesEucadon,
				leavesHaunted,
				leavesHauntedEyes,
				leavesHavenBlue,
				leavesHavenPink,
				leavesHavenPurple,
				leavesHavenRed,
				leavesHavenTurquoise,
				leavesHavenYellow,
				leavesIrodust,
				leavesIrogold,
				leavesLelyetian,
				leavesLucalus,
				leavesLunicia,
				leavesLunosso,
				leavesMelumia,
				leavesOpollo,
				leavesRunic,
				leavesShadow,
				leavesShadowblood,
				leavesShyre,
				leavesShyreBright,
				leavesSilvro,
				leavesStranglewood,
				leavesVein,
				logAchony,
				logBlood,
				logChurry,
				logCreep,
				logCycade,
				logDawn,
				logDomiguous,
				logEternal,
				logEucadon,
				logEyeball,
				logHaunted,
				logHauntedEye,
				logHauntedEyes,
				logHauntedFlashing,
				logHauntedPurpling,
				logIro,
				logLucalus,
				logLunide,
				logMelumia,
				logOpollo,
				logRunic,
				logShadow,
				logShyre,
				logStranglewood,
				logToxic,
				planksAchony,
				planksBloodwood,
				planksChurry,
				planksCreep,
				planksCycade,
				planksDawnwood,
				planksDomiguous,
				planksEucadon,
				planksHauntedwood,
				planksIrowood,
				planksLucalus,
				planksLunide,
				planksMelumia,
				planksOpollo,
				planksRunic,
				planksShadow,
				planksShyre,
				planksStranglewood,
				planksToxicwood,
				slabAchony.registerHalfSlab(ev.getRegistry()),
				slabBloodwood.registerHalfSlab(ev.getRegistry()),
				slabChurry.registerHalfSlab(ev.getRegistry()),
				slabCreep.registerHalfSlab(ev.getRegistry()),
				slabCycade.registerHalfSlab(ev.getRegistry()),
				slabDawnwood.registerHalfSlab(ev.getRegistry()),
				slabDomiguous.registerHalfSlab(ev.getRegistry()),
				slabEucadon.registerHalfSlab(ev.getRegistry()),
				slabHauntedwood.registerHalfSlab(ev.getRegistry()),
				slabIrowood.registerHalfSlab(ev.getRegistry()),
				slabLucalus.registerHalfSlab(ev.getRegistry()),
				slabLunide.registerHalfSlab(ev.getRegistry()),
				slabMelumia.registerHalfSlab(ev.getRegistry()),
				slabOpollo.registerHalfSlab(ev.getRegistry()),
				slabRunic.registerHalfSlab(ev.getRegistry()),
				slabShadow.registerHalfSlab(ev.getRegistry()),
				slabShyre.registerHalfSlab(ev.getRegistry()),
				slabStranglewood.registerHalfSlab(ev.getRegistry()),
				slabToxicwood.registerHalfSlab(ev.getRegistry()),
				slabBaronBricks.registerHalfSlab(ev.getRegistry()),
				slabBlackBricks.registerHalfSlab(ev.getRegistry()),
				slabBloodstoneBricks.registerHalfSlab(ev.getRegistry()),
				slabBlueBricks.registerHalfSlab(ev.getRegistry()),
				slabBrownBricks.registerHalfSlab(ev.getRegistry()),
				slabCoralBricks.registerHalfSlab(ev.getRegistry()),
				slabCreeponiaBricks.registerHalfSlab(ev.getRegistry()),
				slabCrystalliteBricks.registerHalfSlab(ev.getRegistry()),
				slabCrysteviaBricks.registerHalfSlab(ev.getRegistry()),
				slabCyanBricks.registerHalfSlab(ev.getRegistry()),
				slabDarkBlueBricks.registerHalfSlab(ev.getRegistry()),
				slabDarkBricks.registerHalfSlab(ev.getRegistry()),
				slabDarkGreyBricks.registerHalfSlab(ev.getRegistry()),
				slabDarkwashBricks.registerHalfSlab(ev.getRegistry()),
				slabGardenciaBricks.registerHalfSlab(ev.getRegistry()),
				slabGreckonBricks.registerHalfSlab(ev.getRegistry()),
				slabGreenBricks.registerHalfSlab(ev.getRegistry()),
				slabGreyBricks.registerHalfSlab(ev.getRegistry()),
				slabHauntedBricks.registerHalfSlab(ev.getRegistry()),
				slabIroDottedBricks.registerHalfSlab(ev.getRegistry()),
				slabIroStripedBricks.registerHalfSlab(ev.getRegistry()),
				slabIvoryAmethystIntricate.registerHalfSlab(ev.getRegistry()),
				slabIvoryAmethystNatural.registerHalfSlab(ev.getRegistry()),
				slabIvoryAmethystOrnate.registerHalfSlab(ev.getRegistry()),
				slabIvoryAmethystPatterned.registerHalfSlab(ev.getRegistry()),
				slabIvoryIntricate.registerHalfSlab(ev.getRegistry()),
				slabIvoryJadeIntricate.registerHalfSlab(ev.getRegistry()),
				slabIvoryJadeNatural.registerHalfSlab(ev.getRegistry()),
				slabIvoryJadeOrnate.registerHalfSlab(ev.getRegistry()),
				slabIvoryJadePatterned.registerHalfSlab(ev.getRegistry()),
				slabIvoryLimoniteIntricate.registerHalfSlab(ev.getRegistry()),
				slabIvoryLimoniteNatural.registerHalfSlab(ev.getRegistry()),
				slabIvoryLimoniteOrnate.registerHalfSlab(ev.getRegistry()),
				slabIvoryLimonitePatterned.registerHalfSlab(ev.getRegistry()),
				slabIvoryNatural.registerHalfSlab(ev.getRegistry()),
				slabIvoryOrnate.registerHalfSlab(ev.getRegistry()),
				slabIvoryPatterned.registerHalfSlab(ev.getRegistry()),
				slabIvoryRositeIntricate.registerHalfSlab(ev.getRegistry()),
				slabIvoryRositeNatural.registerHalfSlab(ev.getRegistry()),
				slabIvoryRositeOrnate.registerHalfSlab(ev.getRegistry()),
				slabIvoryRositePatterned.registerHalfSlab(ev.getRegistry()),
				slabIvorySapphireIntricate.registerHalfSlab(ev.getRegistry()),
				slabIvorySapphireNatural.registerHalfSlab(ev.getRegistry()),
				slabIvorySapphireOrnate.registerHalfSlab(ev.getRegistry()),
				slabIvorySapphirePatterned.registerHalfSlab(ev.getRegistry()),
				slabLelyetiaBricks.registerHalfSlab(ev.getRegistry()),
				slabLimeBricks.registerHalfSlab(ev.getRegistry()),
				slabLunarBricks.registerHalfSlab(ev.getRegistry()),
				slabMagentaBricks.registerHalfSlab(ev.getRegistry()),
				slabMysteriumBlackBricks.registerHalfSlab(ev.getRegistry()),
				slabMysteriumGreenBricks.registerHalfSlab(ev.getRegistry()),
				slabOrangeBricks.registerHalfSlab(ev.getRegistry()),
				slabPinkBricks.registerHalfSlab(ev.getRegistry()),
				slabPurpleBricks.registerHalfSlab(ev.getRegistry()),
				slabRedBricks.registerHalfSlab(ev.getRegistry()),
				slabRosidianBricks.registerHalfSlab(ev.getRegistry()),
				slabRunicConstructBricks.registerHalfSlab(ev.getRegistry()),
				slabShyreWhiteBricks.registerHalfSlab(ev.getRegistry()),
				slabShyreYellowBricks.registerHalfSlab(ev.getRegistry()),
				slabSkeletalBricks.registerHalfSlab(ev.getRegistry()),
				slabWhitewashBricks.registerHalfSlab(ev.getRegistry()),
				slabYellowBricks.registerHalfSlab(ev.getRegistry()),
				stairsAchony,
				stairsBloodwood,
				stairsChurry,
				stairsCreep,
				stairsCycade,
				stairsDawnwood,
				stairsDomiguous,
				stairsEucadon,
				stairsHauntedwood,
				stairsIrowood,
				stairsLucalus,
				stairsLunide,
				stairsMelumia,
				stairsOpollo,
				stairsRunic,
				stairsShadow,
				stairsShyre,
				stairsStranglewood,
				stairsToxicwood,
				stairsBaronBricks,
				stairsBlackBricks,
				stairsBloodstoneBricks,
				stairsBlueBricks,
				stairsBrownBricks,
				stairsCoralBricks,
				stairsCreeponiaBricks,
				stairsCrystalliteBricks,
				stairsCrysteviaBricks,
				stairsCyanBricks,
				stairsDarkBlueBricks,
				stairsDarkBricks,
				stairsDarkGreyBricks,
				stairsDarkwashBricks,
				stairsGardenciaBricks,
				stairsGreckonBricks,
				stairsGreenBricks,
				stairsGreyBricks,
				stairsHauntedBricks,
				stairsIroDottedBricks,
				stairsIroStripedBricks,
				stairsIvoryAmethystIntricate,
				stairsIvoryAmethystNatural,
				stairsIvoryAmethystOrnate,
				stairsIvoryAmethystPatterned,
				stairsIvoryIntricate,
				stairsIvoryJadeIntricate,
				stairsIvoryJadeNatural,
				stairsIvoryJadeOrnate,
				stairsIvoryJadePatterned,
				stairsIvoryLimoniteIntricate,
				stairsIvoryLimoniteNatural,
				stairsIvoryLimoniteOrnate,
				stairsIvoryLimonitePatterned,
				stairsIvoryNatural,
				stairsIvoryOrnate,
				stairsIvoryPatterned,
				stairsIvoryRositeIntricate,
				stairsIvoryRositeNatural,
				stairsIvoryRositeOrnate,
				stairsIvoryRositePatterned,
				stairsIvorySapphireIntricate,
				stairsIvorySapphireNatural,
				stairsIvorySapphireOrnate,
				stairsIvorySapphirePatterned,
				stairsLelyetiaBricks,
				stairsLimeBricks,
				stairsLunarBricks,
				stairsMagentaBricks,
				stairsMysteriumBlackBricks,
				stairsMysteriumGreenBricks,
				stairsOrangeBricks,
				stairsPinkBricks,
				stairsPurpleBricks,
				stairsRedBricks,
				stairsRosidianBricks,
				stairsRunicConstructBricks,
				stairsShyreWhiteBricks,
				stairsShyreYellowBricks,
				stairsSkeletalBricks,
				stairsWhitewashBricks,
				stairsYellowBricks,
				fenceAchony,
				fenceBloodwood,
				fenceChurry,
				fenceCreep,
				fenceCycade,
				fenceDawnwood,
				fenceDomiguous,
				fenceEucadon,
				fenceHauntedwood,
				fenceIrowood,
				fenceLucalus,
				fenceLunide,
				fenceMelumia,
				fenceOpollo,
				fenceRunic,
				fenceShadow,
				fenceShyre,
				fenceStranglewood,
				fenceToxicwood,
				fenceTwinklestone,
				gateAchony,
				gateBloodwood,
				gateChurry,
				gateCreep,
				gateCycade,
				gateDawnwood,
				gateDomiguous,
				gateEucadon,
				gateHauntedwood,
				gateIrowood,
				gateLucalus,
				gateLunide,
				gateMelumia,
				gateOpollo,
				gateRunic,
				gateShadow,
				gateShyre,
				gateStranglewood,
				gateToxicwood,
				flowerCore,
				mushroomAquaInside,
				mushroomAquaOutside,
				mushroomBlack,
				mushroomBlueInside,
				mushroomBlueOutside,
				mushroomGreenInside,
				mushroomGreenOutside,
				mushroomOrangeInside,
				mushroomOrangeOutside,
				mushroomPeachInside,
				mushroomPeachOutside,
				mushroomPurpleInside,
				mushroomPurpleOutside,
				mushroomStemBlack,
				mushroomStemBlue,
				mushroomStemGreen,
				mushroomStemOrange,
				mushroomStemPurple,
				mushroomStemYellow,
				mushroomYellowInside,
				mushroomYellowOutside,
				plantStem,
				lightAncient,
				lightArchaic,
				lightCreepCrystal,
				lightDarkstone,
				lightDeepCrystal,
				lightHiveLight,
				lightLabDonut,
				lightSteel,
				lightTwinklestone,
				lightVox,
				lampAmethyst.registerOffLamp(ev.getRegistry()),
				lampAquatic.registerOffLamp(ev.getRegistry()),
				lampBaronyte.registerOffLamp(ev.getRegistry()),
				lampBlazium.registerOffLamp(ev.getRegistry()),
				lampBloodstone.registerOffLamp(ev.getRegistry()),
				lampCrystallite.registerOffLamp(ev.getRegistry()),
				lampElecanium.registerOffLamp(ev.getRegistry()),
				lampEmberstone.registerOffLamp(ev.getRegistry()),
				lampFire.registerOffLamp(ev.getRegistry()),
				lampGhastly.registerOffLamp(ev.getRegistry()),
				lampGhoulish.registerOffLamp(ev.getRegistry()),
				lampLifeAqua.registerOffLamp(ev.getRegistry()),
				lampLifeBlack.registerOffLamp(ev.getRegistry()),
				lampLifeBrown.registerOffLamp(ev.getRegistry()),
				lampLifeCyan.registerOffLamp(ev.getRegistry()),
				lampLifeBlue.registerOffLamp(ev.getRegistry()),
				lampLifeGreen.registerOffLamp(ev.getRegistry()),
				lampHeatDarkGrey.registerOffLamp(ev.getRegistry()),
				lampLifeRed.registerOffLamp(ev.getRegistry()),
				lampLifeGrey.registerOffLamp(ev.getRegistry()),
				lampLifeLime.registerOffLamp(ev.getRegistry()),
				lampLifeMagenta.registerOffLamp(ev.getRegistry()),
				lampLifeOrange.registerOffLamp(ev.getRegistry()),
				lampLifePink.registerOffLamp(ev.getRegistry()),
				lampLifePurple.registerOffLamp(ev.getRegistry()),
				lampLifeWhite.registerOffLamp(ev.getRegistry()),
				lampLifeYellow.registerOffLamp(ev.getRegistry()),
				lampIro.registerOffLamp(ev.getRegistry()),
				lampIvory.registerOffLamp(ev.getRegistry()),
				lampIvoryAmethyst.registerOffLamp(ev.getRegistry()),
				lampIvoryJade.registerOffLamp(ev.getRegistry()),
				lampIvoryLimonite.registerOffLamp(ev.getRegistry()),
				lampIvoryRosite.registerOffLamp(ev.getRegistry()),
				lampIvorySapphire.registerOffLamp(ev.getRegistry()),
				lampJade.registerOffLamp(ev.getRegistry()),
				lampLimonite.registerOffLamp(ev.getRegistry()),
				lampLunar.registerOffLamp(ev.getRegistry()),
				lampLyon.registerOffLamp(ev.getRegistry()),
				lampMystic.registerOffLamp(ev.getRegistry()),
				lampNeon.registerOffLamp(ev.getRegistry()),
				lampNeonCircling.registerOffLamp(ev.getRegistry()),
				lampNeonLapis.registerOffLamp(ev.getRegistry()),
				lampNeonLapisCircling.registerOffLamp(ev.getRegistry()),
				lampNeonLapisTriangles.registerOffLamp(ev.getRegistry()),
				lampNeonTriangles.registerOffLamp(ev.getRegistry()),
				lampNeonRunic.registerOffLamp(ev.getRegistry()),
				lampRosite.registerOffLamp(ev.getRegistry()),
				lampSapphire.registerOffLamp(ev.getRegistry()),
				lampSkeletal.registerOffLamp(ev.getRegistry()),
				glassAbyssal,
				glassAncient,
				glassAquatic,
				glassArchaic,
				glassBaron,
				glassDecayed,
				glassGardencian,
				glassHaven,
				glassIro,
				glassLabBasic,
				glassLabSquares,
				glassLelyetian,
				glassLunar,
				glassRunic,
				glassRunicUnbreakable,
				glassShyre,
				glassVox,
				glassZhinx,
				sandPrecasian,
				amethystBlock,
				baronyteBlock,
				blaziumBlock,
				bloodstoneBlock,
				crystalliteBlock,
				elecaniumBlock,
				emberstoneBlock,
				gemenyteBlock,
				ghastlyBlock,
				ghoulishBlock,
				jadeBlock,
				jewelyteBlock,
				limoniteBlock,
				lunarBlock,
				lyonBlock,
				mystiteBlock,
				ornamyteBlock,
				rositeBlock,
				sapphireBlock,
				shyregemBlock,
				shyrestoneBlock,
				skeletalIngotBlock,
				varsiumBlock,
				carpetBaron,
				carpetBorean,
				carpetGardencian,
				carpetLunar,
				carpetIro,
				crystallanium,
				emberium,
				shadonantium,
				skeletanium,
				silvroBox,
				crate,
				ancientCactus,
				ancientRock,
				ancientTileBlack,
				ancientTileCore,
				ancientTileGreen,
				ancientTileShrine,
				ancientTileWhite,
				archaicDirt,
				archaicHorizontalStream,
				archaicLadder,
				archaicRectangles,
				archaicSquares,
				archaicTiles,
				archaicVerticalStream,
				baronCastleWall,
				baronCube,
				baronGround,
				bloodstoneBarBricks,
				bloodstoneBars,
				boneyBlock,
				candyGreen,
				candyRed,
				candyWhite,
				celeveStem,
				chargingTable,
				carvedRuneDirection,
				carvedRuneEmpowering,
				carvedRuneFocus,
				carvedRunePower,
				carvedRuneReality,
				carvedRuneSpace,
				carvedRuneTravel,
				chocolateBlock,
				chocolateBlockDark,
				chocolateBlockWhite,
				cogBlock,
				coralBlue,
				coralGreen,
				coralHard,
				coralOrange,
				coralPink,
				coralWhite,
				coralYellow,
				cottonCandyAqua,
				cottonCandyPink,
				crystalBlue,
				crystalGreen,
				crystalPurple,
				crystalRed,
				crystalWhite,
				crystalYellow,
				darkFaceBrick,
				deeplandsTrapExplosion,
				deeplandsTrapLava,
				deeplandsTrapNipper,
				deepshine,
				degradedSteel,
				dimensionalFabric,
				dustopianLamp,
				dustopianLampOff,
				enhancerDamage,
				enhancerDurability,
				enhancerMagical,
				enhancerResistance,
				enhancerSpeed,
				enhancerWeight,
				eyeBlock,
				giantSnailAcid,
				gingerbread,
				hauntedOrb,
				hiveWall,
				iroBrickTrap,
				iroBox,
				iropole,
				kaiyuTempleBlockFace,
				kaiyuTempleBlockFlow,
				kaiyuTempleBlockMaze,
				kaiyuTempleBlockPass,
				kaiyuTempleBlockPlain,
				kaiyuTempleBlockSquares,
				kaiyuTempleBlockTrack,
				kaiyuTempleTrapFlow,
				kaiyuTempleTrapMaze,
				kaiyuTempleTrapPass,
				kaiyuTempleTrapSquares,
				licorice,
				lunarOrbDarklight,
				lunarOrbDusk,
				lunarOrbLunar,
				lunarOrbMoonlight,
				lunarOrbSunfire,
				lunarPad,
				lunarPillar,
				marshmallow,
				orangeAcid,
				paraviteHive,
				petalsBlack,
				petalsBlue,
				petalsLightBlue,
				petalsMagenta,
				petalsOrange,
				petalsPurple,
				petalsRed,
				petalsRose,
				petalsYellow,
				plastic,
				rockCandyBlue,
				rockCandyGreen,
				rockCandyPink,
				rockCandyPurple,
				runePostCompass,
				runePostDistortion,
				runePostEnergy,
				runePostFire,
				runePostKinetic,
				runePostLife,
				runePostLunar,
				runePostPoison,
				runePostPower,
				runePostStorm,
				runePostStrike,
				runePostWater,
				runePostWind,
				runePostWither,
				shyreCloud,
				skeletalBlock,
				skullBlock,
				skullBlockDark,
				spikeyPillar,
				tentacles,
				tentaclesDotsLeft,
				tentaclesDotsRight,
				tentaclesEyeOrange,
				tentaclesEyeRed,
				tentaclesGreen,
				toxicBlock,
				toxicStem,
				toxicWaste,
				unbreakableIroBricks,
				unbreakablePlantStem,
				unbreakableRunicBricks,
				shroomBlue,
				shroomGreen,
				shroomOrange,
				shroomPurple,
				shroomVox,
				shroomYellow,
				voxLog1,
				voxLog2,
				shroomStem,
				spawnerAmphibior,
				spawnerAmphibiyte,
				spawnerAngelica,
				spawnerArcWizard,
				spawnerArkzyne,
				spawnerArocknid,
				spawnerBanshee,
				spawnerBaumba,
				spawnerBloodsucker,
				spawnerCaneBug,
				spawnerCrusilisk,
				spawnerDawnlight,
				spawnerDaysee,
				spawnerDiocus,
				spawnerEnforcer,
				spawnerExohead,
				spawnerFacelessFloater,
				spawnerFenix,
				spawnerFleshEater,
				spawnerFlowerface,
				spawnerFungock,
				spawnerGhastus,
				spawnerGingerbird,
				spawnerGingerbreadMan,
				spawnerGoldum,
				spawnerGoldus,
				spawnerInmateX,
				spawnerInmateY,
				spawnerIosaur,
				spawnerJawe,
				spawnerKaiyu,
				spawnerLightwalker,
				spawnerLuxocron,
				spawnerMechyon,
				spawnerMerkyre,
				spawnerMermage,
				spawnerMushroomSpider,
				spawnerNethengeicBeast,
				spawnerNightmareSpider,
				spawnerNightwing,
				spawnerOpteryx,
				spawnerParavite,
				spawnerPhantom,
				spawnerPodPlant,
				spawnerRawbone,
				spawnerReaver,
				spawnerRefluct,
				spawnerRockCritter,
				spawnerRunicGolem,
				spawnerRunicGuardian,
				spawnerSeeker,
				spawnerShavo,
				spawnerShyreTroll,
				spawnerSkeledon,
				spawnerSkelekyte,
				spawnerSoulscorne,
				spawnerSpectralWizard,
				spawnerSpinoledon,
				spawnerSurveyor,
				spawnerTharafly,
				spawnerUndeadTroll,
				spawnerUrioh,
				spawnerUrv,
				spawnerVineWizard,
				spawnerVisage,
				spawnerVolar,
				spawnerZarg,
				spawnerZhinx,
				spawnerZorp,
				armyBlock,
				baronessAltar,
				candyBlock,
				clunkheadAltar,
				craexxeusAltar,
				creepAltar,
				dracyonAltar,
				grawAltar,
				guardianAltar,
				hiveSpawner,
				hydroTable,
				illusionAltar,
				krorAltar,
				mechbotAltar,
				powerStation,
				primordialShrine,
				rockriderShrine,
				shadowAltar,
				silverfootAltar,
				toyBox,
				tyrosaurAltar,
				vinocorneShrine,
				visualentAltar,
				voxxulonAltar,
				portalAbyss,
				portalAncientCavern,
				portalBarathos,
				portalBorean,
				portalCandyland,
				portalCeleve,
				portalCreeponia,
				portalCrystevia,
				portalDeeplands,
				portalDustopia,
				portalGardencia,
				portalGreckon,
				portalHaven,
				portalImmortallis,
				portalIromine,
				portalLelyetia,
				portalLunalus,
				portalMysterium,
				portalPrecasia,
				portalRunandor,
				portalShyrelands,
				portalVoxPonds,
				ancientAltar,
				shrineErebon,
				shrineLuxon,
				shrinePluton,
				shrineSelyan,
				ascensionShrine,
				creationForge,
				crystalCreatorBlue,
				crystalCreatorGreen,
				crystalCreatorPurple,
				crystalCreatorRed,
				crystalCreatorWhite,
				crystalCreatorYellow,
				crystalExtensionShrine,
				decloggingTable,
				deepCase,
				divineStation,
				enigmaTable,
				exoidStation,
				extractionDevice,
				extractionDeviceOn,
				filtrationSystem.registerOnBlock(ev.getRegistry()),
				goldAccumulator,
				hauntingTable,
				immortallisProgressor1,
				immortallisProgressor2,
				immortallisProgressor3,
				immortallisProgressor4,
				immortallisProgressor5,
				immortallisProgressor6,
				immortallisProgressor7,
				immortallisProgressor8,
				immortallisProgressor9,
				infusionTable,
				iroCrate,
				lunarCreationTable,
				lunarEnrichmentTable,
				mendingTable,
				mineralizationStation,
				petalCraftingStation,
				pureGoldAccumulator,
				recreationStation,
				runeRandomizer,
				runeShrine,
				runicBlock,
				strangeBlock,
				teaSink.registerFullSink(ev.getRegistry()),
				voxCrate,
				voxStoreCrate,
				whitewashingTable,
				plantAncientVines,
				plantAncientVinesCap,
				plantAquaFungiBlue,
				plantAquaFungiYellow,
				plantArcbulb,
				plantArcflower,
				plantBloodPine,
				plantBloodPineStem,
				plantBloodSpikes,
				plantBloodStrands,
				plantBulbStock,
				plantBulbStockCap,
				plantBurealStocks,
				plantCandycane,
				plantCandyGrass,
				plantCandyGrassBlue,
				plantCandyGrassWhite,
				plantCandyTube,
				plantCelebulbsGreen,
				plantCelebulbsStem,
				plantCelebulbsYellow,
				plantCeleviansBlue,
				plantCeleviansPurple,
				plantCeleviansRed,
				plantCeleviansWhite,
				plantChocolateGrass,
				plantChocolateStocks,
				plantCoralCage,
				plantCreepFlowers,
				plantCreepGrass,
				plantCreepVines,
				plantCrystalBlue,
				plantCrystalGreen,
				plantCrystalPurple,
				plantCrystalRed,
				plantCrystalWhite,
				plantCrystalYellow,
				plantDaileers,
				plantDawnBulb,
				plantDawnBush,
				plantDawnFlower,
				plantDawnGrass,
				plantDawnStocks,
				plantDawnStocksTop,
				plantDawnwoodBars,
				plantDayloomsBlue,
				plantDayloomsPink,
				plantDayloomsYellow,
				plantDeadGrass,
				plantDeepBlooms,
				plantDeepBulb,
				plantDeepGrass,
				plantDeepVines,
				plantEyeShrub,
				plantEyeShrubStem,
				plantFlakeVine,
				plantFlakeVineTop,
				plantGardenGrass,
				plantHauntedFlower,
				plantHavendalesBlue,
				plantHavendalesBlueStem,
				plantHavendalesPink,
				plantHavendalesPinkStem,
				plantHavendalesYellow,
				plantHavendalesYellowStem,
				plantHavenGrass,
				plantHorizonDaisies,
				plantIroGrass,
				plantIrotops,
				plantLelyetianGrass,
				plantLelyetianGrassDown,
				plantLelyetianStem,
				plantLelyetianStemCap,
				plantLelyetianStemCapDown,
				plantLelyetianWiggler,
				plantLelyetianWigglerBottom,
				plantLelyetianWigglerTop,
				plantLollypopBlue,
				plantLollypopRed,
				plantLollypopYellow,
				plantLuconGrass,
				plantLunalip,
				plantLuntar,
				plantLurchians,
				plantLylips,
				plantMagias,
				plantMallowPile,
				plantMarshTube,
				plantMellians,
				plantMysticBush,
				plantMysticFerns,
				plantOcealitesBlue,
				plantOcealitesRed,
				plantPeppermintGreen,
				plantPeppermintRed,
				plantPertonias,
				plantPlasticStick,
				plantRainbowGrass,
				plantRainbowGrass2,
				plantRainbowGrass3,
				plantRuneBulbs,
				plantRunicBush,
				plantShadicles,
				plantShadiclesTop,
				plantShadowShrub,
				plantShyreCap,
				plantShyreCapDown,
				plantShyreStock,
				plantShyreWeed,
				plantSilverGrass,
				plantTangleThorns,
				plantTubeicles,
				plantVoxFungi,
				plantVoxFungiStem,
				plantVoxTentacles,
				plantVoxTentaclesStem,
				plantWaterweedsGreen,
				plantWaterweedsRed,
				plantWaterweedsWhite,
				plantWaterweedsYellow,
				cropBubbleBerries,
				cropChilli,
				cropEyeBulbs,
				cropFloracles,
				cropGoldicaps, cropMysticShrooms,
				cropHeartFruit,
				cropHollyTops,
				cropLunacrike,
				cropLunaGlobes,
				cropLunalons,
				cropMagicMarang,
				cropRosidons,
				cropTea,
				cropThornyPlant,
				cropTrilliads,
				statueBane,
				statueBaneGold,
				statueBaneOrnate,
				statueBaroness,
				statueBaronessGold,
				statueBaronessOrnate,
				statueClunkhead,
				statueClunkheadGold,
				statueClunkheadOrnate,
				statueConiferon,
				statueConiferonGold,
				statueConiferonOrnate,
				statueCorallus,
				statueCorallusGold,
				statueCorallusOrnate,
				statueCottonCandor,
				statueCottonCandorGold,
				statueCottonCandorOrnate,
				statueCraexxeus,
				statueCraexxeusGold,
				statueCraexxeusOrnate,
				statueCreep,
				statueCreepGold,
				statueCreepOrnate,
				statueCrystocore,
				statueCrystocoreGold,
				statueCrystocoreOrnate,
				statueDracyon,
				statueDracyonGold,
				statueDracyonOrnate,
				statueElusive,
				statueElusiveGold,
				statueElusiveOrnate,
				statueFlash,
				statueFlashGold,
				statueFlashOrnate,
				statueGoldorth,
				statueGoldorthGold,
				statueGoldorthOrnate,
				statueGraw,
				statueGrawGold,
				statueGrawOrnate,
				statueGuardian,
				statueGuardianGold,
				statueGuardianOrnate,
				statueGyro,
				statueGyroGold,
				statueGyroOrnate,
				statueHarkos,
				statueHarkosGold,
				statueHarkosOrnate,
				statueHiveKing,
				statueHiveKingGold,
				statueHiveKingOrnate,
				statueHoron,
				statueHoronGold,
				statueHoronOrnate,
				statueHydrolisk,
				statueHydroliskGold,
				statueHydroliskOrnate,
				statueKajaros,
				statueKajarosGold,
				statueKajarosOrnate,
				statueKingBamBamBam,
				statueKingBamBamBamGold,
				statueKingBamBamBamOrnate,
				statueKingShroomus,
				statueKingShroomusGold,
				statueKingShroomusOrnate,
				statueKlobber,
				statueKlobberGold,
				statueKlobberOrnate,
				statueKror,
				statueKrorGold,
				statueKrorOrnate,
				statueMechbot,
				statueMechbotGold,
				statueMechbotOrnate,
				statueMirage,
				statueMirageGold,
				statueMirageOrnate,
				statueMiskel,
				statueMiskelGold,
				statueMiskelOrnate,
				statueNethengeicWither,
				statueNethengeicWitherGold,
				statueNethengeicWitherOrnate,
				statueOkazor,
				statueOkazorGold,
				statueOkazorOrnate,
				statuePenumbra,
				statuePenumbraGold,
				statuePenumbraOrnate,
				statueProshield,
				statueProshieldGold,
				statueProshieldOrnate,
				statueRaxxan,
				statueRaxxanGold,
				statueRaxxanOrnate,
				statueRockrider,
				statueRockriderGold,
				statueRockriderOrnate,
				statueShadowlord,
				statueShadowlordGold,
				statueShadowlordOrnate,
				statueSilverfoot,
				statueSilverfootGold,
				statueSilverfootOrnate,
				statueSkeletron,
				statueSkeletronGold,
				statueSkeletronOrnate,
				statueSmash,
				statueSmashGold,
				statueSmashOrnate,
				statueTyrosaur,
				statueTyrosaurGold,
				statueTyrosaurOrnate,
				statueVinocorne,
				statueVinocorneGold,
				statueVinocorneOrnate,
				statueVisualent,
				statueVisualentGold,
				statueVisualentOrnate,
				statueVoxxulon,
				statueVoxxulonGold,
				statueVoxxulonOrnate,
				statueXxeus,
				statueXxeusGold,
				statueXxeusOrnate,
				bannerAncient,
				bannerAncientBejewelled,
				bannerAncientEncrusted,
				bannerAncientGilded,
				bannerBaron,
				bannerBaronBejewelled,
				bannerBaronEncrusted,
				bannerBaronGilded,
				bannerBlood,
				bannerBloodBejewelled,
				bannerBloodEncrusted,
				bannerBloodGilded,
				bannerBoreic,
				bannerBoreicBejewelled,
				bannerBoreicEncrusted,
				bannerBoreicGilded,
				bannerCandy,
				bannerCandyBejewelled,
				bannerCandyEncrusted,
				bannerCandyGilded,
				bannerClown,
				bannerClownBejewelled,
				bannerClownEncrusted,
				bannerClownGilded,
				bannerCreation,
				bannerCreationBejewelled,
				bannerCreationEncrusted,
				bannerCreationGilded,
				bannerCreepoid,
				bannerCreepoidBejewelled,
				bannerCreepoidEncrusted,
				bannerCreepoidGilded,
				bannerCreepy,
				bannerCreepyBejewelled,
				bannerCreepyEncrusted,
				bannerCreepyGilded,
				bannerCrystal,
				bannerCrystalBejewelled,
				bannerCrystalEncrusted,
				bannerCrystalGilded,
				bannerDeep,
				bannerDeepBejewelled,
				bannerDeepEncrusted,
				bannerDeepGilded,
				bannerDustopian,
				bannerDustopianBejewelled,
				bannerDustopianEncrusted,
				bannerDustopianGilded,
				bannerEnergy,
				bannerEnergyBejewelled,
				bannerEnergyEncrusted,
				bannerEnergyGilded,
				bannerErebon,
				bannerFragment,
				bannerFragmentBejewelled,
				bannerFragmentEncrusted,
				bannerFragmentGilded,
				bannerFungal,
				bannerFungalBejewelled,
				bannerFungalEncrusted,
				bannerFungalGilded,
				bannerGhostly,
				bannerGhostlyBejewelled,
				bannerGhostlyEncrusted,
				bannerGhostlyGilded,
				bannerGhoul,
				bannerGhoulBejewelled,
				bannerGhoulEncrusted,
				bannerGhoulGilded,
				bannerGingerbread,
				bannerGingerbreadBejewelled,
				bannerGingerbreadEncrusted,
				bannerGingerbreadGilded,
				bannerHaunted,
				bannerHauntedBejewelled,
				bannerHauntedEncrusted,
				bannerHauntedGilded,
				bannerIllusion,
				bannerIllusionBejewelled,
				bannerIllusionEncrusted,
				bannerIllusionGilded,
				bannerImmortal,
				bannerImmortalBejewelled,
				bannerImmortalEncrusted,
				bannerImmortalGilded,
				bannerLelyetian,
				bannerLelyetianBejewelled,
				bannerLelyetianEncrusted,
				bannerLelyetianGilded,
				bannerLight,
				bannerLightBejewelled,
				bannerLightEncrusted,
				bannerLightGilded,
				bannerLotto,
				bannerLottoBejewelled,
				bannerLottoEncrusted,
				bannerLottoGilded,
				bannerLunar,
				bannerLunarBejewelled,
				bannerLunarEncrusted,
				bannerLunarGilded,
				bannerLuxon,
				bannerMecha,
				bannerMechaBejewelled,
				bannerMechaEncrusted,
				bannerMechaGilded,
				bannerNethengeic,
				bannerNethengeicBejewelled,
				bannerNethengeicEncrusted,
				bannerNethengeicGilded,
				bannerNether,
				bannerNetherBejewelled,
				bannerNetherEncrusted,
				bannerNetherGilded,
				bannerPluton,
				bannerRosidian,
				bannerRosidianBejewelled,
				bannerRosidianEncrusted,
				bannerRosidianGilded,
				bannerRunic,
				bannerRunicBejewelled,
				bannerRunicEncrusted,
				bannerRunicGilded,
				bannerSea,
				bannerSeaBejewelled,
				bannerSeaEncrusted,
				bannerSeaGilded,
				bannerSelyan,
				bannerShadow,
				bannerShadowBejewelled,
				bannerShadowEncrusted,
				bannerShadowGilded,
				bannerShiny,
				bannerShinyBejewelled,
				bannerShinyEncrusted,
				bannerShinyGilded,
				bannerShyre,
				bannerShyreBejewelled,
				bannerShyreEncrusted,
				bannerShyreGilded,
				bannerSkeletal,
				bannerSkeletalBejewelled,
				bannerSkeletalEncrusted,
				bannerSkeletalGilded,
				bannerSoul,
				bannerSoulBejewelled,
				bannerSoulEncrusted,
				bannerSoulGilded,
				bannerUtopian,
				bannerUtopianBejewelled,
				bannerUtopianEncrusted,
				bannerUtopianGilded,
				bannerVoid,
				bannerVoidBejewelled,
				bannerVoidEncrusted,
				bannerVoidGilded,
				bannerVox,
				bannerVoxBejewelled,
				bannerVoxEncrusted,
				bannerVoxGilded
		);
	}

	@SubscribeEvent
	public static void registerItemBlocks(final RegistryEvent.Register<Item> ev) {
		ev.getRegistry().registerAll(
				new ItemBlock(stoneAbyss).setRegistryName(stoneAbyss.getRegistryName()),
				new ItemBlock(stoneBarathos).setRegistryName(stoneBarathos.getRegistryName()),
				new ItemBlock(stoneBaron).setRegistryName(stoneBaron.getRegistryName()),
				new ItemBlock(stoneBorean).setRegistryName(stoneBorean.getRegistryName()),
				new ItemBlock(stoneCreep).setRegistryName(stoneCreep.getRegistryName()),
				new ItemBlock(stoneCrystevia).setRegistryName(stoneCrystevia.getRegistryName()),
				new ItemBlock(stoneDeeplands).setRegistryName(stoneDeeplands.getRegistryName()),
				new ItemBlock(stoneDustopia).setRegistryName(stoneDustopia.getRegistryName()),
				new ItemBlock(stoneGardencia).setRegistryName(stoneGardencia.getRegistryName()),
				new ItemBlock(stoneGreckon).setRegistryName(stoneGreckon.getRegistryName()),
				new ItemBlock(stoneHaven).setRegistryName(stoneHaven.getRegistryName()),
				new ItemBlock(stoneIromine).setRegistryName(stoneIromine.getRegistryName()),
				new ItemBlock(stoneLelyetia).setRegistryName(stoneLelyetia.getRegistryName()),
				new ItemBlock(stoneMysterium).setRegistryName(stoneMysterium.getRegistryName()),
				new ItemBlock(stonePrecasiaHigh).setRegistryName(stonePrecasiaHigh.getRegistryName()),
				new ItemBlock(stonePrecasiaLow).setRegistryName(stonePrecasiaLow.getRegistryName()),
				new ItemBlock(stonePrimed).setRegistryName(stonePrimed.getRegistryName()),
				new ItemBlock(stoneRunic).setRegistryName(stoneRunic.getRegistryName()),
				new ItemBlock(stoneShyrelands).setRegistryName(stoneShyrelands.getRegistryName()),
				new ItemBlock(stoneToxic).setRegistryName(stoneToxic.getRegistryName()),
				new ItemBlock(stoneUnstable).setRegistryName(stoneUnstable.getRegistryName()),
				new ItemBlock(dirtBorean).setRegistryName(dirtBorean.getRegistryName()),
				new ItemBlock(dirtCandyland).setRegistryName(dirtCandyland.getRegistryName()),
				new ItemBlock(dirtCeleve).setRegistryName(dirtCeleve.getRegistryName()),
				new ItemBlock(dirtCreeponia).setRegistryName(dirtCreeponia.getRegistryName()),
				new ItemBlock(dirtDustopia).setRegistryName(dirtDustopia.getRegistryName()),
				new ItemBlock(dirtGardencia).setRegistryName(dirtGardencia.getRegistryName()),
				new ItemBlock(dirtGreckon).setRegistryName(dirtGreckon.getRegistryName()),
				new ItemBlock(dirtHaven).setRegistryName(dirtHaven.getRegistryName()),
				new ItemBlock(dirtLunalyte).setRegistryName(dirtLunalyte.getRegistryName()),
				new ItemBlock(dirtLunasole).setRegistryName(dirtLunasole.getRegistryName()),
				new ItemBlock(dirtMysterium).setRegistryName(dirtMysterium.getRegistryName()),
				new ItemBlock(dirtToxic).setRegistryName(dirtToxic.getRegistryName()),
				new ItemBlock(grassAbyss).setRegistryName(grassAbyss.getRegistryName()),
				new ItemBlock(grassBorean).setRegistryName(grassBorean.getRegistryName()),
				new ItemBlock(grassCandyland).setRegistryName(grassCandyland.getRegistryName()),
				new ItemBlock(grassCeleve).setRegistryName(grassCeleve.getRegistryName()),
				new ItemBlock(grassChocolate).setRegistryName(grassChocolate.getRegistryName()),
				new ItemBlock(grassCreeponia).setRegistryName(grassCreeponia.getRegistryName()),
				new ItemBlock(grassCrystal).setRegistryName(grassCrystal.getRegistryName()),
				new ItemBlock(grassDustopia).setRegistryName(grassDustopia.getRegistryName()),
				new ItemBlock(grassGardencia).setRegistryName(grassGardencia.getRegistryName()),
				new ItemBlock(grassGreckon).setRegistryName(grassGreckon.getRegistryName()),
				new ItemBlock(grassHaven).setRegistryName(grassHaven.getRegistryName()),
				new ItemBlock(grassIromine).setRegistryName(grassIromine.getRegistryName()),
				new ItemBlock(grassLelyetia).setRegistryName(grassLelyetia.getRegistryName()),
				new ItemBlock(grassLelyetiaDown).setRegistryName(grassLelyetiaDown.getRegistryName()),
				new ItemBlock(grassLunalyte).setRegistryName(grassLunalyte.getRegistryName()),
				new ItemBlock(grassLunasole).setRegistryName(grassLunasole.getRegistryName()),
				new ItemBlock(grassMarshmallow).setRegistryName(grassMarshmallow.getRegistryName()),
				new ItemBlock(grassMysterium).setRegistryName(grassMysterium.getRegistryName()),
				new ItemBlock(grassPrecasia).setRegistryName(grassPrecasia.getRegistryName()),
				new ItemBlock(grassRunic).setRegistryName(grassRunic.getRegistryName()),
				new ItemBlock(grassShyrelands).setRegistryName(grassShyrelands.getRegistryName()),
				new ItemBlock(grassSilvro).setRegistryName(grassSilvro.getRegistryName()),
				new ItemBlock(grassToxic).setRegistryName(grassToxic.getRegistryName()),
				new ItemBlock(oreAmethyst).setRegistryName(oreAmethyst.getRegistryName()),
				new ItemBlock(oreBaronyte).setRegistryName(oreBaronyte.getRegistryName()),
				new ItemBlock(oreBlazium).setRegistryName(oreBlazium.getRegistryName()),
				new ItemBlock(oreBloodstone).setRegistryName(oreBloodstone.getRegistryName()),
				new ItemBlock(oreBlueGemstone).setRegistryName(oreBlueGemstone.getRegistryName()),
				new ItemBlock(oreChargedRunium).setRegistryName(oreChargedRunium.getRegistryName()),
				new ItemBlock(oreChestboneFragments).setRegistryName(oreChestboneFragments.getRegistryName()),
				new ItemBlock(oreCrystallite).setRegistryName(oreCrystallite.getRegistryName()),
				new ItemBlock(oreElecanium).setRegistryName(oreElecanium.getRegistryName()),
				new ItemBlock(oreEmberstone).setRegistryName(oreEmberstone.getRegistryName()),
				new ItemBlock(oreFootboneFragments).setRegistryName(oreFootboneFragments.getRegistryName()),
				new ItemBlock(oreGemenyte).setRegistryName(oreGemenyte.getRegistryName()),
				new ItemBlock(oreGhastly).setRegistryName(oreGhastly.getRegistryName()),
				new ItemBlock(oreGhoulish).setRegistryName(oreGhoulish.getRegistryName()),
				new ItemBlock(oreGreenGemstone).setRegistryName(oreGreenGemstone.getRegistryName()),
				new ItemBlock(oreJade).setRegistryName(oreJade.getRegistryName()),
				new ItemBlock(oreJewelyte).setRegistryName(oreJewelyte.getRegistryName()),
				new ItemBlock(oreLegboneFragments).setRegistryName(oreLegboneFragments.getRegistryName()),
				new ItemBlock(oreLimonite).setRegistryName(oreLimonite.getRegistryName()),
				new ItemBlock(oreLyon).setRegistryName(oreLyon.getRegistryName()),
				new ItemBlock(oreMystite).setRegistryName(oreMystite.getRegistryName()),
				new ItemBlock(oreOrnamyte).setRegistryName(oreOrnamyte.getRegistryName()),
				new ItemBlock(orePurpleGemstone).setRegistryName(orePurpleGemstone.getRegistryName()),
				new ItemBlock(oreRedGemstone).setRegistryName(oreRedGemstone.getRegistryName()),
				new ItemBlock(oreRosite).setRegistryName(oreRosite.getRegistryName()),
				new ItemBlock(oreRunium).setRegistryName(oreRunium.getRegistryName()),
				new ItemBlock(oreSapphire).setRegistryName(oreSapphire.getRegistryName()),
				new ItemBlock(oreShyregem).setRegistryName(oreShyregem.getRegistryName()),
				new ItemBlock(oreShyrestone).setRegistryName(oreShyrestone.getRegistryName()),
				new ItemBlock(oreSkullboneFragments).setRegistryName(oreSkullboneFragments.getRegistryName()),
				new ItemBlock(oreVarsium).setRegistryName(oreVarsium.getRegistryName()),
				new ItemBlock(oreWhiteGemstone).setRegistryName(oreWhiteGemstone.getRegistryName()),
				new ItemBlock(oreYellowGemstone).setRegistryName(oreYellowGemstone.getRegistryName()),
				new ItemBlock(bricksBaron).setRegistryName(bricksBaron.getRegistryName()),
				new ItemBlock(bricksBlack).setRegistryName(bricksBlack.getRegistryName()),
				new ItemBlock(bricksBloodstone).setRegistryName(bricksBloodstone.getRegistryName()),
				new ItemBlock(bricksBlue).setRegistryName(bricksBlue.getRegistryName()),
				new ItemBlock(bricksBrown).setRegistryName(bricksBrown.getRegistryName()),
				new ItemBlock(bricksCoral).setRegistryName(bricksCoral.getRegistryName()),
				new ItemBlock(bricksCreeponia).setRegistryName(bricksCreeponia.getRegistryName()),
				new ItemBlock(bricksCrystallite).setRegistryName(bricksCrystallite.getRegistryName()),
				new ItemBlock(bricksCrystevia).setRegistryName(bricksCrystevia.getRegistryName()),
				new ItemBlock(bricksCyan).setRegistryName(bricksCyan.getRegistryName()),
				new ItemBlock(bricksDark).setRegistryName(bricksDark.getRegistryName()),
				new ItemBlock(bricksDarkBlue).setRegistryName(bricksDarkBlue.getRegistryName()),
				new ItemBlock(bricksDarkGrey).setRegistryName(bricksDarkGrey.getRegistryName()),
				new ItemBlock(bricksDarkwash).setRegistryName(bricksDarkwash.getRegistryName()),
				new ItemBlock(bricksGardencia).setRegistryName(bricksGardencia.getRegistryName()),
				new ItemBlock(bricksGreckon).setRegistryName(bricksGreckon.getRegistryName()),
				new ItemBlock(bricksGreen).setRegistryName(bricksGreen.getRegistryName()),
				new ItemBlock(bricksGrey).setRegistryName(bricksGrey.getRegistryName()),
				new ItemBlock(bricksHaunted).setRegistryName(bricksHaunted.getRegistryName()),
				new ItemBlock(bricksIroDotted).setRegistryName(bricksIroDotted.getRegistryName()),
				new ItemBlock(bricksIroStriped).setRegistryName(bricksIroStriped.getRegistryName()),
				new ItemBlock(bricksLelyetia).setRegistryName(bricksLelyetia.getRegistryName()),
				new ItemBlock(bricksLime).setRegistryName(bricksLime.getRegistryName()),
				new ItemBlock(bricksLunar).setRegistryName(bricksLunar.getRegistryName()),
				new ItemBlock(bricksMagenta).setRegistryName(bricksMagenta.getRegistryName()),
				new ItemBlock(bricksMysteriumBlack).setRegistryName(bricksMysteriumBlack.getRegistryName()),
				new ItemBlock(bricksMysteriumGreen).setRegistryName(bricksMysteriumGreen.getRegistryName()),
				new ItemBlock(bricksOrange).setRegistryName(bricksOrange.getRegistryName()),
				new ItemBlock(bricksPink).setRegistryName(bricksPink.getRegistryName()),
				new ItemBlock(bricksPurple).setRegistryName(bricksPurple.getRegistryName()),
				new ItemBlock(bricksRed).setRegistryName(bricksRed.getRegistryName()),
				new ItemBlock(bricksRosidian).setRegistryName(bricksRosidian.getRegistryName()),
				new ItemBlock(bricksRunicConstruct).setRegistryName(bricksRunicConstruct.getRegistryName()),
				new ItemBlock(bricksShyreWhite).setRegistryName(bricksShyreWhite.getRegistryName()),
				new ItemBlock(bricksShyreYellow).setRegistryName(bricksShyreYellow.getRegistryName()),
				new ItemBlock(bricksSkeletal).setRegistryName(bricksSkeletal.getRegistryName()),
				new ItemBlock(bricksWhite).setRegistryName(bricksWhite.getRegistryName()),
				new ItemBlock(bricksWhitewash).setRegistryName(bricksWhitewash.getRegistryName()),
				new ItemBlock(bricksYellow).setRegistryName(bricksYellow.getRegistryName()),
				new ItemBlock(ivoryAmethystIntricate).setRegistryName(ivoryAmethystIntricate.getRegistryName()),
				new ItemBlock(ivoryAmethystNatural).setRegistryName(ivoryAmethystNatural.getRegistryName()),
				new ItemBlock(ivoryAmethystOrnate).setRegistryName(ivoryAmethystOrnate.getRegistryName()),
				new ItemBlock(ivoryAmethystPatterned).setRegistryName(ivoryAmethystPatterned.getRegistryName()),
				new ItemBlock(ivoryIntricate).setRegistryName(ivoryIntricate.getRegistryName()),
				new ItemBlock(ivoryJadeIntricate).setRegistryName(ivoryJadeIntricate.getRegistryName()),
				new ItemBlock(ivoryJadeNatural).setRegistryName(ivoryJadeNatural.getRegistryName()),
				new ItemBlock(ivoryJadeOrnate).setRegistryName(ivoryJadeOrnate.getRegistryName()),
				new ItemBlock(ivoryJadePatterned).setRegistryName(ivoryJadePatterned.getRegistryName()),
				new ItemBlock(ivoryLimoniteIntricate).setRegistryName(ivoryLimoniteIntricate.getRegistryName()),
				new ItemBlock(ivoryLimoniteNatural).setRegistryName(ivoryLimoniteNatural.getRegistryName()),
				new ItemBlock(ivoryLimoniteOrnate).setRegistryName(ivoryLimoniteOrnate.getRegistryName()),
				new ItemBlock(ivoryLimonitePatterned).setRegistryName(ivoryLimonitePatterned.getRegistryName()),
				new ItemBlock(ivoryNatural).setRegistryName(ivoryNatural.getRegistryName()),
				new ItemBlock(ivoryOrnate).setRegistryName(ivoryOrnate.getRegistryName()),
				new ItemBlock(ivoryPatterned).setRegistryName(ivoryPatterned.getRegistryName()),
				new ItemBlock(ivoryRositeIntricate).setRegistryName(ivoryRositeIntricate.getRegistryName()),
				new ItemBlock(ivoryRositeNatural).setRegistryName(ivoryRositeNatural.getRegistryName()),
				new ItemBlock(ivoryRositeOrnate).setRegistryName(ivoryRositeOrnate.getRegistryName()),
				new ItemBlock(ivoryRositePatterned).setRegistryName(ivoryRositePatterned.getRegistryName()),
				new ItemBlock(ivorySapphireIntricate).setRegistryName(ivorySapphireIntricate.getRegistryName()),
				new ItemBlock(ivorySapphireNatural).setRegistryName(ivorySapphireNatural.getRegistryName()),
				new ItemBlock(ivorySapphireOrnate).setRegistryName(ivorySapphireOrnate.getRegistryName()),
				new ItemBlock(ivorySapphirePatterned).setRegistryName(ivorySapphirePatterned.getRegistryName()),
				new ItemBlock(leavesAchony).setRegistryName(leavesAchony.getRegistryName()),
				new ItemBlock(leavesBlood).setRegistryName(leavesBlood.getRegistryName()),
				new ItemBlock(leavesBubble).setRegistryName(leavesBubble.getRegistryName()),
				new ItemBlock(leavesCelevusBlue).setRegistryName(leavesCelevusBlue.getRegistryName()),
				new ItemBlock(leavesCelevusGreen).setRegistryName(leavesCelevusGreen.getRegistryName()),
				new ItemBlock(leavesCelevusPurple).setRegistryName(leavesCelevusPurple.getRegistryName()),
				new ItemBlock(leavesCelevusRed).setRegistryName(leavesCelevusRed.getRegistryName()),
				new ItemBlock(leavesCelevusWhite).setRegistryName(leavesCelevusWhite.getRegistryName()),
				new ItemBlock(leavesCelevusYellow).setRegistryName(leavesCelevusYellow.getRegistryName()),
				new ItemBlock(leavesChurry).setRegistryName(leavesChurry.getRegistryName()),
				new ItemBlock(leavesCreep).setRegistryName(leavesCreep.getRegistryName()),
				new ItemBlock(leavesCycade).setRegistryName(leavesCycade.getRegistryName()),
				new ItemBlock(leavesDawn).setRegistryName(leavesDawn.getRegistryName()),
				new ItemBlock(leavesDomiguous).setRegistryName(leavesDomiguous.getRegistryName()),
				new ItemBlock(leavesEternal).setRegistryName(leavesEternal.getRegistryName()),
				new ItemBlock(leavesEucadon).setRegistryName(leavesEucadon.getRegistryName()),
				new ItemBlock(leavesHaunted).setRegistryName(leavesHaunted.getRegistryName()),
				new ItemBlock(leavesHauntedEyes).setRegistryName(leavesHauntedEyes.getRegistryName()),
				new ItemBlock(leavesHavenBlue).setRegistryName(leavesHavenBlue.getRegistryName()),
				new ItemBlock(leavesHavenPink).setRegistryName(leavesHavenPink.getRegistryName()),
				new ItemBlock(leavesHavenPurple).setRegistryName(leavesHavenPurple.getRegistryName()),
				new ItemBlock(leavesHavenRed).setRegistryName(leavesHavenRed.getRegistryName()),
				new ItemBlock(leavesHavenTurquoise).setRegistryName(leavesHavenTurquoise.getRegistryName()),
				new ItemBlock(leavesHavenYellow).setRegistryName(leavesHavenYellow.getRegistryName()),
				new ItemBlock(leavesIrodust).setRegistryName(leavesIrodust.getRegistryName()),
				new ItemBlock(leavesIrogold).setRegistryName(leavesIrogold.getRegistryName()),
				new ItemBlock(leavesLelyetian).setRegistryName(leavesLelyetian.getRegistryName()),
				new ItemBlock(leavesLucalus).setRegistryName(leavesLucalus.getRegistryName()),
				new ItemBlock(leavesLunicia).setRegistryName(leavesLunicia.getRegistryName()),
				new ItemBlock(leavesLunosso).setRegistryName(leavesLunosso.getRegistryName()),
				new ItemBlock(leavesMelumia).setRegistryName(leavesMelumia.getRegistryName()),
				new ItemBlock(leavesOpollo).setRegistryName(leavesOpollo.getRegistryName()),
				new ItemBlock(leavesRunic).setRegistryName(leavesRunic.getRegistryName()),
				new ItemBlock(leavesShadow).setRegistryName(leavesShadow.getRegistryName()),
				new ItemBlock(leavesShadowblood).setRegistryName(leavesShadowblood.getRegistryName()),
				new ItemBlock(leavesShyre).setRegistryName(leavesShyre.getRegistryName()),
				new ItemBlock(leavesShyreBright).setRegistryName(leavesShyreBright.getRegistryName()),
				new ItemBlock(leavesSilvro).setRegistryName(leavesSilvro.getRegistryName()),
				new ItemBlock(leavesStranglewood).setRegistryName(leavesStranglewood.getRegistryName()),
				new ItemBlock(leavesVein).setRegistryName(leavesVein.getRegistryName()),
				new ItemBlock(logAchony).setRegistryName(logAchony.getRegistryName()),
				new ItemBlock(logBlood).setRegistryName(logBlood.getRegistryName()),
				new ItemBlock(logChurry).setRegistryName(logChurry.getRegistryName()),
				new ItemBlock(logCreep).setRegistryName(logCreep.getRegistryName()),
				new ItemBlock(logCycade).setRegistryName(logCycade.getRegistryName()),
				new ItemBlock(logDawn).setRegistryName(logDawn.getRegistryName()),
				new ItemBlock(logDomiguous).setRegistryName(logDomiguous.getRegistryName()),
				new ItemBlock(logEternal).setRegistryName(logEternal.getRegistryName()),
				new ItemBlock(logEucadon).setRegistryName(logEucadon.getRegistryName()),
				new ItemBlock(logEyeball).setRegistryName(logEyeball.getRegistryName()),
				new ItemBlock(logHaunted).setRegistryName(logHaunted.getRegistryName()),
				new ItemBlock(logHauntedEye).setRegistryName(logHauntedEye.getRegistryName()),
				new ItemBlock(logHauntedEyes).setRegistryName(logHauntedEyes.getRegistryName()),
				new ItemBlock(logHauntedFlashing).setRegistryName(logHauntedFlashing.getRegistryName()),
				new ItemBlock(logHauntedPurpling).setRegistryName(logHauntedPurpling.getRegistryName()),
				new ItemBlock(logIro).setRegistryName(logIro.getRegistryName()),
				new ItemBlock(logLucalus).setRegistryName(logLucalus.getRegistryName()),
				new ItemBlock(logLunide).setRegistryName(logLunide.getRegistryName()),
				new ItemBlock(logMelumia).setRegistryName(logMelumia.getRegistryName()),
				new ItemBlock(logOpollo).setRegistryName(logOpollo.getRegistryName()),
				new ItemBlock(logRunic).setRegistryName(logRunic.getRegistryName()),
				new ItemBlock(logShadow).setRegistryName(logShadow.getRegistryName()),
				new ItemBlock(logShyre).setRegistryName(logShyre.getRegistryName()),
				new ItemBlock(logStranglewood).setRegistryName(logStranglewood.getRegistryName()),
				new ItemBlock(logToxic).setRegistryName(logToxic.getRegistryName()),
				new ItemBlock(planksAchony).setRegistryName(planksAchony.getRegistryName()),
				new ItemBlock(planksBloodwood).setRegistryName(planksBloodwood.getRegistryName()),
				new ItemBlock(planksChurry).setRegistryName(planksChurry.getRegistryName()),
				new ItemBlock(planksCreep).setRegistryName(planksCreep.getRegistryName()),
				new ItemBlock(planksCycade).setRegistryName(planksCycade.getRegistryName()),
				new ItemBlock(planksDawnwood).setRegistryName(planksDawnwood.getRegistryName()),
				new ItemBlock(planksDomiguous).setRegistryName(planksDomiguous.getRegistryName()),
				new ItemBlock(planksEucadon).setRegistryName(planksEucadon.getRegistryName()),
				new ItemBlock(planksHauntedwood).setRegistryName(planksHauntedwood.getRegistryName()),
				new ItemBlock(planksIrowood).setRegistryName(planksIrowood.getRegistryName()),
				new ItemBlock(planksLucalus).setRegistryName(planksLucalus.getRegistryName()),
				new ItemBlock(planksLunide).setRegistryName(planksLunide.getRegistryName()),
				new ItemBlock(planksMelumia).setRegistryName(planksMelumia.getRegistryName()),
				new ItemBlock(planksOpollo).setRegistryName(planksOpollo.getRegistryName()),
				new ItemBlock(planksRunic).setRegistryName(planksRunic.getRegistryName()),
				new ItemBlock(planksShadow).setRegistryName(planksShadow.getRegistryName()),
				new ItemBlock(planksShyre).setRegistryName(planksShyre.getRegistryName()),
				new ItemBlock(planksStranglewood).setRegistryName(planksStranglewood.getRegistryName()),
				new ItemBlock(planksToxicwood).setRegistryName(planksToxicwood.getRegistryName()),
				new ItemSlab(slabAchony.getHalfBlock(), slabAchony.getHalfBlock(), slabAchony).setRegistryName(slabAchony.getHalfBlock().getRegistryName()),
				new ItemSlab(slabBloodwood.getHalfBlock(), slabBloodwood.getHalfBlock(), slabBloodwood).setRegistryName(slabBloodwood.getHalfBlock().getRegistryName()),
				new ItemSlab(slabChurry.getHalfBlock(), slabChurry.getHalfBlock(), slabChurry).setRegistryName(slabChurry.getHalfBlock().getRegistryName()),
				new ItemSlab(slabCreep.getHalfBlock(), slabCreep.getHalfBlock(), slabCreep).setRegistryName(slabCreep.getHalfBlock().getRegistryName()),
				new ItemSlab(slabCycade.getHalfBlock(), slabCycade.getHalfBlock(), slabCycade).setRegistryName(slabCycade.getHalfBlock().getRegistryName()),
				new ItemSlab(slabDawnwood.getHalfBlock(), slabDawnwood.getHalfBlock(), slabDawnwood).setRegistryName(slabDawnwood.getHalfBlock().getRegistryName()),
				new ItemSlab(slabDomiguous.getHalfBlock(), slabDomiguous.getHalfBlock(), slabDomiguous).setRegistryName(slabDomiguous.getHalfBlock().getRegistryName()),
				new ItemSlab(slabEucadon.getHalfBlock(), slabEucadon.getHalfBlock(), slabEucadon).setRegistryName(slabEucadon.getHalfBlock().getRegistryName()),
				new ItemSlab(slabHauntedwood.getHalfBlock(), slabHauntedwood.getHalfBlock(), slabHauntedwood).setRegistryName(slabHauntedwood.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIrowood.getHalfBlock(), slabIrowood.getHalfBlock(), slabIrowood).setRegistryName(slabIrowood.getHalfBlock().getRegistryName()),
				new ItemSlab(slabLucalus.getHalfBlock(), slabLucalus.getHalfBlock(), slabLucalus).setRegistryName(slabLucalus.getHalfBlock().getRegistryName()),
				new ItemSlab(slabLunide.getHalfBlock(), slabLunide.getHalfBlock(), slabLunide).setRegistryName(slabLunide.getHalfBlock().getRegistryName()),
				new ItemSlab(slabMelumia.getHalfBlock(), slabMelumia.getHalfBlock(), slabMelumia).setRegistryName(slabMelumia.getHalfBlock().getRegistryName()),
				new ItemSlab(slabOpollo.getHalfBlock(), slabOpollo.getHalfBlock(), slabOpollo).setRegistryName(slabOpollo.getHalfBlock().getRegistryName()),
				new ItemSlab(slabRunic.getHalfBlock(), slabRunic.getHalfBlock(), slabRunic).setRegistryName(slabRunic.getHalfBlock().getRegistryName()),
				new ItemSlab(slabShadow.getHalfBlock(), slabShadow.getHalfBlock(), slabShadow).setRegistryName(slabShadow.getHalfBlock().getRegistryName()),
				new ItemSlab(slabShyre.getHalfBlock(), slabShyre.getHalfBlock(), slabShyre).setRegistryName(slabShyre.getHalfBlock().getRegistryName()),
				new ItemSlab(slabStranglewood.getHalfBlock(), slabStranglewood.getHalfBlock(), slabStranglewood).setRegistryName(slabStranglewood.getHalfBlock().getRegistryName()),
				new ItemSlab(slabToxicwood.getHalfBlock(), slabToxicwood.getHalfBlock(), slabToxicwood).setRegistryName(slabToxicwood.getHalfBlock().getRegistryName()),
				new ItemSlab(slabBaronBricks.getHalfBlock(), slabBaronBricks.getHalfBlock(), slabBaronBricks).setRegistryName(slabBaronBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabBlackBricks.getHalfBlock(), slabBlackBricks.getHalfBlock(), slabBlackBricks).setRegistryName(slabBlackBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabBloodstoneBricks.getHalfBlock(), slabBloodstoneBricks.getHalfBlock(), slabBloodstoneBricks).setRegistryName(slabBloodstoneBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabBlueBricks.getHalfBlock(), slabBlueBricks.getHalfBlock(), slabBlueBricks).setRegistryName(slabBlueBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabBrownBricks.getHalfBlock(), slabBrownBricks.getHalfBlock(), slabBrownBricks).setRegistryName(slabBrownBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabCoralBricks.getHalfBlock(), slabCoralBricks.getHalfBlock(), slabCoralBricks).setRegistryName(slabCoralBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabCreeponiaBricks.getHalfBlock(), slabCreeponiaBricks.getHalfBlock(), slabCreeponiaBricks).setRegistryName(slabCreeponiaBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabCrystalliteBricks.getHalfBlock(), slabCrystalliteBricks.getHalfBlock(), slabCrystalliteBricks).setRegistryName(slabCrystalliteBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabCrysteviaBricks.getHalfBlock(), slabCrysteviaBricks.getHalfBlock(), slabCrysteviaBricks).setRegistryName(slabCrysteviaBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabCyanBricks.getHalfBlock(), slabCyanBricks.getHalfBlock(), slabCyanBricks).setRegistryName(slabCyanBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabDarkBlueBricks.getHalfBlock(), slabDarkBlueBricks.getHalfBlock(), slabDarkBlueBricks).setRegistryName(slabDarkBlueBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabDarkBricks.getHalfBlock(), slabDarkBricks.getHalfBlock(), slabDarkBricks).setRegistryName(slabDarkBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabDarkGreyBricks.getHalfBlock(), slabDarkGreyBricks.getHalfBlock(), slabDarkGreyBricks).setRegistryName(slabDarkGreyBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabDarkwashBricks.getHalfBlock(), slabDarkwashBricks.getHalfBlock(), slabDarkwashBricks).setRegistryName(slabDarkwashBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabGardenciaBricks.getHalfBlock(), slabGardenciaBricks.getHalfBlock(), slabGardenciaBricks).setRegistryName(slabGardenciaBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabGreckonBricks.getHalfBlock(), slabGreckonBricks.getHalfBlock(), slabGreckonBricks).setRegistryName(slabGreckonBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabGreenBricks.getHalfBlock(), slabGreenBricks.getHalfBlock(), slabGreenBricks).setRegistryName(slabGreenBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabGreyBricks.getHalfBlock(), slabGreyBricks.getHalfBlock(), slabGreyBricks).setRegistryName(slabGreyBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabHauntedBricks.getHalfBlock(), slabHauntedBricks.getHalfBlock(), slabHauntedBricks).setRegistryName(slabHauntedBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIroDottedBricks.getHalfBlock(), slabIroDottedBricks.getHalfBlock(), slabIroDottedBricks).setRegistryName(slabIroDottedBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIroStripedBricks.getHalfBlock(), slabIroStripedBricks.getHalfBlock(), slabIroStripedBricks).setRegistryName(slabIroStripedBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryAmethystIntricate.getHalfBlock(), slabIvoryAmethystIntricate.getHalfBlock(), slabIvoryAmethystIntricate).setRegistryName(slabIvoryAmethystIntricate.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryAmethystNatural.getHalfBlock(), slabIvoryAmethystNatural.getHalfBlock(), slabIvoryAmethystNatural).setRegistryName(slabIvoryAmethystNatural.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryAmethystOrnate.getHalfBlock(), slabIvoryAmethystOrnate.getHalfBlock(), slabIvoryAmethystOrnate).setRegistryName(slabIvoryAmethystOrnate.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryAmethystPatterned.getHalfBlock(), slabIvoryAmethystPatterned.getHalfBlock(), slabIvoryAmethystPatterned).setRegistryName(slabIvoryAmethystPatterned.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryIntricate.getHalfBlock(), slabIvoryIntricate.getHalfBlock(), slabIvoryIntricate).setRegistryName(slabIvoryIntricate.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryJadeIntricate.getHalfBlock(), slabIvoryJadeIntricate.getHalfBlock(), slabIvoryJadeIntricate).setRegistryName(slabIvoryJadeIntricate.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryJadeNatural.getHalfBlock(), slabIvoryJadeNatural.getHalfBlock(), slabIvoryJadeNatural).setRegistryName(slabIvoryJadeNatural.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryJadeOrnate.getHalfBlock(), slabIvoryJadeOrnate.getHalfBlock(), slabIvoryJadeOrnate).setRegistryName(slabIvoryJadeOrnate.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryJadePatterned.getHalfBlock(), slabIvoryJadePatterned.getHalfBlock(), slabIvoryJadePatterned).setRegistryName(slabIvoryJadePatterned.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryLimoniteIntricate.getHalfBlock(), slabIvoryLimoniteIntricate.getHalfBlock(), slabIvoryLimoniteIntricate).setRegistryName(slabIvoryLimoniteIntricate.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryLimoniteNatural.getHalfBlock(), slabIvoryLimoniteNatural.getHalfBlock(), slabIvoryLimoniteNatural).setRegistryName(slabIvoryLimoniteNatural.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryLimoniteOrnate.getHalfBlock(), slabIvoryLimoniteOrnate.getHalfBlock(), slabIvoryLimoniteOrnate).setRegistryName(slabIvoryLimoniteOrnate.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryLimonitePatterned.getHalfBlock(), slabIvoryLimonitePatterned.getHalfBlock(), slabIvoryLimonitePatterned).setRegistryName(slabIvoryLimonitePatterned.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryNatural.getHalfBlock(), slabIvoryNatural.getHalfBlock(), slabIvoryNatural).setRegistryName(slabIvoryNatural.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryOrnate.getHalfBlock(), slabIvoryOrnate.getHalfBlock(), slabIvoryOrnate).setRegistryName(slabIvoryOrnate.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryPatterned.getHalfBlock(), slabIvoryPatterned.getHalfBlock(), slabIvoryPatterned).setRegistryName(slabIvoryPatterned.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryRositeIntricate.getHalfBlock(), slabIvoryRositeIntricate.getHalfBlock(), slabIvoryRositeIntricate).setRegistryName(slabIvoryRositeIntricate.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryRositeNatural.getHalfBlock(), slabIvoryRositeNatural.getHalfBlock(), slabIvoryRositeNatural).setRegistryName(slabIvoryRositeNatural.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryRositeOrnate.getHalfBlock(), slabIvoryRositeOrnate.getHalfBlock(), slabIvoryRositeOrnate).setRegistryName(slabIvoryRositeOrnate.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvoryRositePatterned.getHalfBlock(), slabIvoryRositePatterned.getHalfBlock(), slabIvoryRositePatterned).setRegistryName(slabIvoryRositePatterned.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvorySapphireIntricate.getHalfBlock(), slabIvorySapphireIntricate.getHalfBlock(), slabIvorySapphireIntricate).setRegistryName(slabIvorySapphireIntricate.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvorySapphireNatural.getHalfBlock(), slabIvorySapphireNatural.getHalfBlock(), slabIvorySapphireNatural).setRegistryName(slabIvorySapphireNatural.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvorySapphireOrnate.getHalfBlock(), slabIvorySapphireOrnate.getHalfBlock(), slabIvorySapphireOrnate).setRegistryName(slabIvorySapphireOrnate.getHalfBlock().getRegistryName()),
				new ItemSlab(slabIvorySapphirePatterned.getHalfBlock(), slabIvorySapphirePatterned.getHalfBlock(), slabIvorySapphirePatterned).setRegistryName(slabIvorySapphirePatterned.getHalfBlock().getRegistryName()),
				new ItemSlab(slabLelyetiaBricks.getHalfBlock(), slabLelyetiaBricks.getHalfBlock(), slabLelyetiaBricks).setRegistryName(slabLelyetiaBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabLimeBricks.getHalfBlock(), slabLimeBricks.getHalfBlock(), slabLimeBricks).setRegistryName(slabLimeBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabLunarBricks.getHalfBlock(), slabLunarBricks.getHalfBlock(), slabLunarBricks).setRegistryName(slabLunarBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabMagentaBricks.getHalfBlock(), slabMagentaBricks.getHalfBlock(), slabMagentaBricks).setRegistryName(slabMagentaBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabMysteriumBlackBricks.getHalfBlock(), slabMysteriumBlackBricks.getHalfBlock(), slabMysteriumBlackBricks).setRegistryName(slabMysteriumBlackBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabMysteriumGreenBricks.getHalfBlock(), slabMysteriumGreenBricks.getHalfBlock(), slabMysteriumGreenBricks).setRegistryName(slabMysteriumGreenBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabOrangeBricks.getHalfBlock(), slabOrangeBricks.getHalfBlock(), slabOrangeBricks).setRegistryName(slabOrangeBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabPinkBricks.getHalfBlock(), slabPinkBricks.getHalfBlock(), slabPinkBricks).setRegistryName(slabPinkBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabPurpleBricks.getHalfBlock(), slabPurpleBricks.getHalfBlock(), slabPurpleBricks).setRegistryName(slabPurpleBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabRedBricks.getHalfBlock(), slabRedBricks.getHalfBlock(), slabRedBricks).setRegistryName(slabRedBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabRosidianBricks.getHalfBlock(), slabRosidianBricks.getHalfBlock(), slabRosidianBricks).setRegistryName(slabRosidianBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabRunicConstructBricks.getHalfBlock(), slabRunicConstructBricks.getHalfBlock(), slabRunicConstructBricks).setRegistryName(slabRunicConstructBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabShyreWhiteBricks.getHalfBlock(), slabShyreWhiteBricks.getHalfBlock(), slabShyreWhiteBricks).setRegistryName(slabShyreWhiteBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabShyreYellowBricks.getHalfBlock(), slabShyreYellowBricks.getHalfBlock(), slabShyreYellowBricks).setRegistryName(slabShyreYellowBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabSkeletalBricks.getHalfBlock(), slabSkeletalBricks.getHalfBlock(), slabSkeletalBricks).setRegistryName(slabSkeletalBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabWhitewashBricks.getHalfBlock(), slabWhitewashBricks.getHalfBlock(), slabWhitewashBricks).setRegistryName(slabWhitewashBricks.getHalfBlock().getRegistryName()),
				new ItemSlab(slabYellowBricks.getHalfBlock(), slabYellowBricks.getHalfBlock(), slabYellowBricks).setRegistryName(slabYellowBricks.getHalfBlock().getRegistryName()),
				new ItemBlock(stairsAchony).setRegistryName(stairsAchony.getRegistryName()),
				new ItemBlock(stairsBloodwood).setRegistryName(stairsBloodwood.getRegistryName()),
				new ItemBlock(stairsChurry).setRegistryName(stairsChurry.getRegistryName()),
				new ItemBlock(stairsCreep).setRegistryName(stairsCreep.getRegistryName()),
				new ItemBlock(stairsCycade).setRegistryName(stairsCycade.getRegistryName()),
				new ItemBlock(stairsDawnwood).setRegistryName(stairsDawnwood.getRegistryName()),
				new ItemBlock(stairsDomiguous).setRegistryName(stairsDomiguous.getRegistryName()),
				new ItemBlock(stairsEucadon).setRegistryName(stairsEucadon.getRegistryName()),
				new ItemBlock(stairsHauntedwood).setRegistryName(stairsHauntedwood.getRegistryName()),
				new ItemBlock(stairsIrowood).setRegistryName(stairsIrowood.getRegistryName()),
				new ItemBlock(stairsLucalus).setRegistryName(stairsLucalus.getRegistryName()),
				new ItemBlock(stairsLunide).setRegistryName(stairsLunide.getRegistryName()),
				new ItemBlock(stairsMelumia).setRegistryName(stairsMelumia.getRegistryName()),
				new ItemBlock(stairsOpollo).setRegistryName(stairsOpollo.getRegistryName()),
				new ItemBlock(stairsRunic).setRegistryName(stairsRunic.getRegistryName()),
				new ItemBlock(stairsShadow).setRegistryName(stairsShadow.getRegistryName()),
				new ItemBlock(stairsShyre).setRegistryName(stairsShyre.getRegistryName()),
				new ItemBlock(stairsStranglewood).setRegistryName(stairsStranglewood.getRegistryName()),
				new ItemBlock(stairsToxicwood).setRegistryName(stairsToxicwood.getRegistryName()),
				new ItemBlock(stairsBaronBricks).setRegistryName(stairsBaronBricks.getRegistryName()),
				new ItemBlock(stairsBlackBricks).setRegistryName(stairsBlackBricks.getRegistryName()),
				new ItemBlock(stairsBloodstoneBricks).setRegistryName(stairsBloodstoneBricks.getRegistryName()),
				new ItemBlock(stairsBlueBricks).setRegistryName(stairsBlueBricks.getRegistryName()),
				new ItemBlock(stairsBrownBricks).setRegistryName(stairsBrownBricks.getRegistryName()),
				new ItemBlock(stairsCoralBricks).setRegistryName(stairsCoralBricks.getRegistryName()),
				new ItemBlock(stairsCreeponiaBricks).setRegistryName(stairsCreeponiaBricks.getRegistryName()),
				new ItemBlock(stairsCrystalliteBricks).setRegistryName(stairsCrystalliteBricks.getRegistryName()),
				new ItemBlock(stairsCrysteviaBricks).setRegistryName(stairsCrysteviaBricks.getRegistryName()),
				new ItemBlock(stairsCyanBricks).setRegistryName(stairsCyanBricks.getRegistryName()),
				new ItemBlock(stairsDarkBlueBricks).setRegistryName(stairsDarkBlueBricks.getRegistryName()),
				new ItemBlock(stairsDarkBricks).setRegistryName(stairsDarkBricks.getRegistryName()),
				new ItemBlock(stairsDarkGreyBricks).setRegistryName(stairsDarkGreyBricks.getRegistryName()),
				new ItemBlock(stairsDarkwashBricks).setRegistryName(stairsDarkwashBricks.getRegistryName()),
				new ItemBlock(stairsGardenciaBricks).setRegistryName(stairsGardenciaBricks.getRegistryName()),
				new ItemBlock(stairsGreckonBricks).setRegistryName(stairsGreckonBricks.getRegistryName()),
				new ItemBlock(stairsGreenBricks).setRegistryName(stairsGreenBricks.getRegistryName()),
				new ItemBlock(stairsGreyBricks).setRegistryName(stairsGreyBricks.getRegistryName()),
				new ItemBlock(stairsHauntedBricks).setRegistryName(stairsHauntedBricks.getRegistryName()),
				new ItemBlock(stairsIroDottedBricks).setRegistryName(stairsIroDottedBricks.getRegistryName()),
				new ItemBlock(stairsIroStripedBricks).setRegistryName(stairsIroStripedBricks.getRegistryName()),
				new ItemBlock(stairsIvoryAmethystIntricate).setRegistryName(stairsIvoryAmethystIntricate.getRegistryName()),
				new ItemBlock(stairsIvoryAmethystNatural).setRegistryName(stairsIvoryAmethystNatural.getRegistryName()),
				new ItemBlock(stairsIvoryAmethystOrnate).setRegistryName(stairsIvoryAmethystOrnate.getRegistryName()),
				new ItemBlock(stairsIvoryAmethystPatterned).setRegistryName(stairsIvoryAmethystPatterned.getRegistryName()),
				new ItemBlock(stairsIvoryIntricate).setRegistryName(stairsIvoryIntricate.getRegistryName()),
				new ItemBlock(stairsIvoryJadeIntricate).setRegistryName(stairsIvoryJadeIntricate.getRegistryName()),
				new ItemBlock(stairsIvoryJadeNatural).setRegistryName(stairsIvoryJadeNatural.getRegistryName()),
				new ItemBlock(stairsIvoryJadeOrnate).setRegistryName(stairsIvoryJadeOrnate.getRegistryName()),
				new ItemBlock(stairsIvoryJadePatterned).setRegistryName(stairsIvoryJadePatterned.getRegistryName()),
				new ItemBlock(stairsIvoryLimoniteIntricate).setRegistryName(stairsIvoryLimoniteIntricate.getRegistryName()),
				new ItemBlock(stairsIvoryLimoniteNatural).setRegistryName(stairsIvoryLimoniteNatural.getRegistryName()),
				new ItemBlock(stairsIvoryLimoniteOrnate).setRegistryName(stairsIvoryLimoniteOrnate.getRegistryName()),
				new ItemBlock(stairsIvoryLimonitePatterned).setRegistryName(stairsIvoryLimonitePatterned.getRegistryName()),
				new ItemBlock(stairsIvoryNatural).setRegistryName(stairsIvoryNatural.getRegistryName()),
				new ItemBlock(stairsIvoryOrnate).setRegistryName(stairsIvoryOrnate.getRegistryName()),
				new ItemBlock(stairsIvoryPatterned).setRegistryName(stairsIvoryPatterned.getRegistryName()),
				new ItemBlock(stairsIvoryRositeIntricate).setRegistryName(stairsIvoryRositeIntricate.getRegistryName()),
				new ItemBlock(stairsIvoryRositeNatural).setRegistryName(stairsIvoryRositeNatural.getRegistryName()),
				new ItemBlock(stairsIvoryRositeOrnate).setRegistryName(stairsIvoryRositeOrnate.getRegistryName()),
				new ItemBlock(stairsIvoryRositePatterned).setRegistryName(stairsIvoryRositePatterned.getRegistryName()),
				new ItemBlock(stairsIvorySapphireIntricate).setRegistryName(stairsIvorySapphireIntricate.getRegistryName()),
				new ItemBlock(stairsIvorySapphireNatural).setRegistryName(stairsIvorySapphireNatural.getRegistryName()),
				new ItemBlock(stairsIvorySapphireOrnate).setRegistryName(stairsIvorySapphireOrnate.getRegistryName()),
				new ItemBlock(stairsIvorySapphirePatterned).setRegistryName(stairsIvorySapphirePatterned.getRegistryName()),
				new ItemBlock(stairsLelyetiaBricks).setRegistryName(stairsLelyetiaBricks.getRegistryName()),
				new ItemBlock(stairsLimeBricks).setRegistryName(stairsLimeBricks.getRegistryName()),
				new ItemBlock(stairsLunarBricks).setRegistryName(stairsLunarBricks.getRegistryName()),
				new ItemBlock(stairsMagentaBricks).setRegistryName(stairsMagentaBricks.getRegistryName()),
				new ItemBlock(stairsMysteriumBlackBricks).setRegistryName(stairsMysteriumBlackBricks.getRegistryName()),
				new ItemBlock(stairsMysteriumGreenBricks).setRegistryName(stairsMysteriumGreenBricks.getRegistryName()),
				new ItemBlock(stairsOrangeBricks).setRegistryName(stairsOrangeBricks.getRegistryName()),
				new ItemBlock(stairsPinkBricks).setRegistryName(stairsPinkBricks.getRegistryName()),
				new ItemBlock(stairsPurpleBricks).setRegistryName(stairsPurpleBricks.getRegistryName()),
				new ItemBlock(stairsRedBricks).setRegistryName(stairsRedBricks.getRegistryName()),
				new ItemBlock(stairsRosidianBricks).setRegistryName(stairsRosidianBricks.getRegistryName()),
				new ItemBlock(stairsRunicConstructBricks).setRegistryName(stairsRunicConstructBricks.getRegistryName()),
				new ItemBlock(stairsShyreWhiteBricks).setRegistryName(stairsShyreWhiteBricks.getRegistryName()),
				new ItemBlock(stairsShyreYellowBricks).setRegistryName(stairsShyreYellowBricks.getRegistryName()),
				new ItemBlock(stairsSkeletalBricks).setRegistryName(stairsSkeletalBricks.getRegistryName()),
				new ItemBlock(stairsWhitewashBricks).setRegistryName(stairsWhitewashBricks.getRegistryName()),
				new ItemBlock(stairsYellowBricks).setRegistryName(stairsYellowBricks.getRegistryName()),
				new ItemBlock(fenceAchony).setRegistryName(fenceAchony.getRegistryName()),
				new ItemBlock(fenceBloodwood).setRegistryName(fenceBloodwood.getRegistryName()),
				new ItemBlock(fenceChurry).setRegistryName(fenceChurry.getRegistryName()),
				new ItemBlock(fenceCreep).setRegistryName(fenceCreep.getRegistryName()),
				new ItemBlock(fenceCycade).setRegistryName(fenceCycade.getRegistryName()),
				new ItemBlock(fenceDawnwood).setRegistryName(fenceDawnwood.getRegistryName()),
				new ItemBlock(fenceDomiguous).setRegistryName(fenceDomiguous.getRegistryName()),
				new ItemBlock(fenceEucadon).setRegistryName(fenceEucadon.getRegistryName()),
				new ItemBlock(fenceHauntedwood).setRegistryName(fenceHauntedwood.getRegistryName()),
				new ItemBlock(fenceIrowood).setRegistryName(fenceIrowood.getRegistryName()),
				new ItemBlock(fenceLucalus).setRegistryName(fenceLucalus.getRegistryName()),
				new ItemBlock(fenceLunide).setRegistryName(fenceLunide.getRegistryName()),
				new ItemBlock(fenceMelumia).setRegistryName(fenceMelumia.getRegistryName()),
				new ItemBlock(fenceOpollo).setRegistryName(fenceOpollo.getRegistryName()),
				new ItemBlock(fenceRunic).setRegistryName(fenceRunic.getRegistryName()),
				new ItemBlock(fenceShadow).setRegistryName(fenceShadow.getRegistryName()),
				new ItemBlock(fenceShyre).setRegistryName(fenceShyre.getRegistryName()),
				new ItemBlock(fenceStranglewood).setRegistryName(fenceStranglewood.getRegistryName()),
				new ItemBlock(fenceToxicwood).setRegistryName(fenceToxicwood.getRegistryName()),
				new ItemBlock(fenceTwinklestone).setRegistryName(fenceTwinklestone.getRegistryName()),
				new ItemBlock(gateAchony).setRegistryName(gateAchony.getRegistryName()),
				new ItemBlock(gateBloodwood).setRegistryName(gateBloodwood.getRegistryName()),
				new ItemBlock(gateChurry).setRegistryName(gateChurry.getRegistryName()),
				new ItemBlock(gateCreep).setRegistryName(gateCreep.getRegistryName()),
				new ItemBlock(gateCycade).setRegistryName(gateCycade.getRegistryName()),
				new ItemBlock(gateDawnwood).setRegistryName(gateDawnwood.getRegistryName()),
				new ItemBlock(gateDomiguous).setRegistryName(gateDomiguous.getRegistryName()),
				new ItemBlock(gateEucadon).setRegistryName(gateEucadon.getRegistryName()),
				new ItemBlock(gateHauntedwood).setRegistryName(gateHauntedwood.getRegistryName()),
				new ItemBlock(gateIrowood).setRegistryName(gateIrowood.getRegistryName()),
				new ItemBlock(gateLucalus).setRegistryName(gateLucalus.getRegistryName()),
				new ItemBlock(gateLunide).setRegistryName(gateLunide.getRegistryName()),
				new ItemBlock(gateMelumia).setRegistryName(gateMelumia.getRegistryName()),
				new ItemBlock(gateOpollo).setRegistryName(gateOpollo.getRegistryName()),
				new ItemBlock(gateRunic).setRegistryName(gateRunic.getRegistryName()),
				new ItemBlock(gateShadow).setRegistryName(gateShadow.getRegistryName()),
				new ItemBlock(gateShyre).setRegistryName(gateShyre.getRegistryName()),
				new ItemBlock(gateStranglewood).setRegistryName(gateStranglewood.getRegistryName()),
				new ItemBlock(gateToxicwood).setRegistryName(gateToxicwood.getRegistryName()),
				new ItemBlock(flowerCore).setRegistryName(flowerCore.getRegistryName()),
				new ItemBlock(mushroomAquaInside).setRegistryName(mushroomAquaInside.getRegistryName()),
				new ItemBlock(mushroomAquaOutside).setRegistryName(mushroomAquaOutside.getRegistryName()),
				new ItemBlock(mushroomBlack).setRegistryName(mushroomBlack.getRegistryName()),
				new ItemBlock(mushroomBlueInside).setRegistryName(mushroomBlueInside.getRegistryName()),
				new ItemBlock(mushroomBlueOutside).setRegistryName(mushroomBlueOutside.getRegistryName()),
				new ItemBlock(mushroomGreenInside).setRegistryName(mushroomGreenInside.getRegistryName()),
				new ItemBlock(mushroomGreenOutside).setRegistryName(mushroomGreenOutside.getRegistryName()),
				new ItemBlock(mushroomOrangeInside).setRegistryName(mushroomOrangeInside.getRegistryName()),
				new ItemBlock(mushroomOrangeOutside).setRegistryName(mushroomOrangeOutside.getRegistryName()),
				new ItemBlock(mushroomPeachInside).setRegistryName(mushroomPeachInside.getRegistryName()),
				new ItemBlock(mushroomPeachOutside).setRegistryName(mushroomPeachOutside.getRegistryName()),
				new ItemBlock(mushroomPurpleInside).setRegistryName(mushroomPurpleInside.getRegistryName()),
				new ItemBlock(mushroomPurpleOutside).setRegistryName(mushroomPurpleOutside.getRegistryName()),
				new ItemBlock(mushroomStemBlack).setRegistryName(mushroomStemBlack.getRegistryName()),
				new ItemBlock(mushroomStemBlue).setRegistryName(mushroomStemBlue.getRegistryName()),
				new ItemBlock(mushroomStemGreen).setRegistryName(mushroomStemGreen.getRegistryName()),
				new ItemBlock(mushroomStemOrange).setRegistryName(mushroomStemOrange.getRegistryName()),
				new ItemBlock(mushroomStemPurple).setRegistryName(mushroomStemPurple.getRegistryName()),
				new ItemBlock(mushroomStemYellow).setRegistryName(mushroomStemYellow.getRegistryName()),
				new ItemBlock(mushroomYellowInside).setRegistryName(mushroomYellowInside.getRegistryName()),
				new ItemBlock(mushroomYellowOutside).setRegistryName(mushroomYellowOutside.getRegistryName()),
				new ItemBlock(plantStem).setRegistryName(plantStem.getRegistryName()),
				new ItemBlock(lightAncient).setRegistryName(lightAncient.getRegistryName()),
				new ItemBlock(lightArchaic).setRegistryName(lightArchaic.getRegistryName()),
				new ItemBlock(lightCreepCrystal).setRegistryName(lightCreepCrystal.getRegistryName()),
				new ItemBlock(lightDarkstone).setRegistryName(lightDarkstone.getRegistryName()),
				new ItemBlock(lightDeepCrystal).setRegistryName(lightDeepCrystal.getRegistryName()),
				new ItemBlock(lightHiveLight).setRegistryName(lightHiveLight.getRegistryName()),
				new ItemBlock(lightLabDonut).setRegistryName(lightLabDonut.getRegistryName()),
				new ItemBlock(lightSteel).setRegistryName(lightSteel.getRegistryName()),
				new ItemBlock(lightTwinklestone).setRegistryName(lightTwinklestone.getRegistryName()),
				new ItemBlock(lightVox).setRegistryName(lightVox.getRegistryName()),
				new ItemBlock(lampAmethyst.registerOffLampItem(ev.getRegistry())).setRegistryName(lampAmethyst.getRegistryName()),
				new ItemBlock(lampAquatic.registerOffLampItem(ev.getRegistry())).setRegistryName(lampAquatic.getRegistryName()),
				new ItemBlock(lampBaronyte.registerOffLampItem(ev.getRegistry())).setRegistryName(lampBaronyte.getRegistryName()),
				new ItemBlock(lampBlazium.registerOffLampItem(ev.getRegistry())).setRegistryName(lampBlazium.getRegistryName()),
				new ItemBlock(lampBloodstone.registerOffLampItem(ev.getRegistry())).setRegistryName(lampBloodstone.getRegistryName()),
				new ItemBlock(lampCrystallite.registerOffLampItem(ev.getRegistry())).setRegistryName(lampCrystallite.getRegistryName()),
				new ItemBlock(lampElecanium.registerOffLampItem(ev.getRegistry())).setRegistryName(lampElecanium.getRegistryName()),
				new ItemBlock(lampEmberstone.registerOffLampItem(ev.getRegistry())).setRegistryName(lampEmberstone.getRegistryName()),
				new ItemBlock(lampFire.registerOffLampItem(ev.getRegistry())).setRegistryName(lampFire.getRegistryName()),
				new ItemBlock(lampGhastly.registerOffLampItem(ev.getRegistry())).setRegistryName(lampGhastly.getRegistryName()),
				new ItemBlock(lampGhoulish.registerOffLampItem(ev.getRegistry())).setRegistryName(lampGhoulish.getRegistryName()),
				new ItemBlock(lampLifeAqua.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifeAqua.getRegistryName()),
				new ItemBlock(lampLifeBlack.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifeBlack.getRegistryName()),
				new ItemBlock(lampLifeBrown.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifeBrown.getRegistryName()),
				new ItemBlock(lampLifeCyan.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifeCyan.getRegistryName()),
				new ItemBlock(lampLifeBlue.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifeBlue.getRegistryName()),
				new ItemBlock(lampLifeGreen.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifeGreen.getRegistryName()),
				new ItemBlock(lampHeatDarkGrey.registerOffLampItem(ev.getRegistry())).setRegistryName(lampHeatDarkGrey.getRegistryName()),
				new ItemBlock(lampLifeRed.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifeRed.getRegistryName()),
				new ItemBlock(lampLifeGrey.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifeGrey.getRegistryName()),
				new ItemBlock(lampLifeLime.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifeLime.getRegistryName()),
				new ItemBlock(lampLifeMagenta.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifeMagenta.getRegistryName()),
				new ItemBlock(lampLifeOrange.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifeOrange.getRegistryName()),
				new ItemBlock(lampLifePink.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifePink.getRegistryName()),
				new ItemBlock(lampLifePurple.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifePurple.getRegistryName()),
				new ItemBlock(lampLifeWhite.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifeWhite.getRegistryName()),
				new ItemBlock(lampLifeYellow.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLifeYellow.getRegistryName()),
				new ItemBlock(lampIro.registerOffLampItem(ev.getRegistry())).setRegistryName(lampIro.getRegistryName()),
				new ItemBlock(lampIvory.registerOffLampItem(ev.getRegistry())).setRegistryName(lampIvory.getRegistryName()),
				new ItemBlock(lampIvoryAmethyst.registerOffLampItem(ev.getRegistry())).setRegistryName(lampIvoryAmethyst.getRegistryName()),
				new ItemBlock(lampIvoryJade.registerOffLampItem(ev.getRegistry())).setRegistryName(lampIvoryJade.getRegistryName()),
				new ItemBlock(lampIvoryLimonite.registerOffLampItem(ev.getRegistry())).setRegistryName(lampIvoryLimonite.getRegistryName()),
				new ItemBlock(lampIvoryRosite.registerOffLampItem(ev.getRegistry())).setRegistryName(lampIvoryRosite.getRegistryName()),
				new ItemBlock(lampIvorySapphire.registerOffLampItem(ev.getRegistry())).setRegistryName(lampIvorySapphire.getRegistryName()),
				new ItemBlock(lampJade.registerOffLampItem(ev.getRegistry())).setRegistryName(lampJade.getRegistryName()),
				new ItemBlock(lampLimonite.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLimonite.getRegistryName()),
				new ItemBlock(lampLunar.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLunar.getRegistryName()),
				new ItemBlock(lampLyon.registerOffLampItem(ev.getRegistry())).setRegistryName(lampLyon.getRegistryName()),
				new ItemBlock(lampMystic.registerOffLampItem(ev.getRegistry())).setRegistryName(lampMystic.getRegistryName()),
				new ItemBlock(lampNeon.registerOffLampItem(ev.getRegistry())).setRegistryName(lampNeon.getRegistryName()),
				new ItemBlock(lampNeonCircling.registerOffLampItem(ev.getRegistry())).setRegistryName(lampNeonCircling.getRegistryName()),
				new ItemBlock(lampNeonLapis.registerOffLampItem(ev.getRegistry())).setRegistryName(lampNeonLapis.getRegistryName()),
				new ItemBlock(lampNeonLapisCircling.registerOffLampItem(ev.getRegistry())).setRegistryName(lampNeonLapisCircling.getRegistryName()),
				new ItemBlock(lampNeonLapisTriangles.registerOffLampItem(ev.getRegistry())).setRegistryName(lampNeonLapisTriangles.getRegistryName()),
				new ItemBlock(lampNeonTriangles.registerOffLampItem(ev.getRegistry())).setRegistryName(lampNeonTriangles.getRegistryName()),
				new ItemBlock(lampNeonRunic.registerOffLampItem(ev.getRegistry())).setRegistryName(lampNeonRunic.getRegistryName()),
				new ItemBlock(lampRosite.registerOffLampItem(ev.getRegistry())).setRegistryName(lampRosite.getRegistryName()),
				new ItemBlock(lampSapphire.registerOffLampItem(ev.getRegistry())).setRegistryName(lampSapphire.getRegistryName()),
				new ItemBlock(lampSkeletal.registerOffLampItem(ev.getRegistry())).setRegistryName(lampSkeletal.getRegistryName()),
				new ItemBlock(glassAbyssal).setRegistryName(glassAbyssal.getRegistryName()),
				new ItemBlock(glassAncient).setRegistryName(glassAncient.getRegistryName()),
				new ItemBlock(glassAquatic).setRegistryName(glassAquatic.getRegistryName()),
				new ItemBlock(glassArchaic).setRegistryName(glassArchaic.getRegistryName()),
				new ItemBlock(glassBaron).setRegistryName(glassBaron.getRegistryName()),
				new ItemBlock(glassDecayed).setRegistryName(glassDecayed.getRegistryName()),
				new ItemBlock(glassGardencian).setRegistryName(glassGardencian.getRegistryName()),
				new ItemBlock(glassHaven).setRegistryName(glassHaven.getRegistryName()),
				new ItemBlock(glassIro).setRegistryName(glassIro.getRegistryName()),
				new ItemBlock(glassLabBasic).setRegistryName(glassLabBasic.getRegistryName()),
				new ItemBlock(glassLabSquares).setRegistryName(glassLabSquares.getRegistryName()),
				new ItemBlock(glassLelyetian).setRegistryName(glassLelyetian.getRegistryName()),
				new ItemBlock(glassLunar).setRegistryName(glassLunar.getRegistryName()),
				new ItemBlock(glassRunic).setRegistryName(glassRunic.getRegistryName()),
				new ItemBlock(glassRunicUnbreakable).setRegistryName(glassRunicUnbreakable.getRegistryName()),
				new ItemBlock(glassShyre).setRegistryName(glassShyre.getRegistryName()),
				new ItemBlock(glassVox).setRegistryName(glassVox.getRegistryName()),
				new ItemBlock(glassZhinx).setRegistryName(glassZhinx.getRegistryName()),
				new ItemBlock(sandPrecasian).setRegistryName(sandPrecasian.getRegistryName()),
				new ItemBlock(amethystBlock).setRegistryName(amethystBlock.getRegistryName()),
				new ItemBlock(baronyteBlock).setRegistryName(baronyteBlock.getRegistryName()),
				new ItemBlock(blaziumBlock).setRegistryName(blaziumBlock.getRegistryName()),
				new ItemBlock(bloodstoneBlock).setRegistryName(bloodstoneBlock.getRegistryName()),
				new ItemBlock(crystalliteBlock).setRegistryName(crystalliteBlock.getRegistryName()),
				new ItemBlock(elecaniumBlock).setRegistryName(elecaniumBlock.getRegistryName()),
				new ItemBlock(emberstoneBlock).setRegistryName(emberstoneBlock.getRegistryName()),
				new ItemBlock(gemenyteBlock).setRegistryName(gemenyteBlock.getRegistryName()),
				new ItemBlock(ghastlyBlock).setRegistryName(ghastlyBlock.getRegistryName()),
				new ItemBlock(ghoulishBlock).setRegistryName(ghoulishBlock.getRegistryName()),
				new ItemBlock(jadeBlock).setRegistryName(jadeBlock.getRegistryName()),
				new ItemBlock(jewelyteBlock).setRegistryName(jewelyteBlock.getRegistryName()),
				new ItemBlock(limoniteBlock).setRegistryName(limoniteBlock.getRegistryName()),
				new ItemBlock(lunarBlock).setRegistryName(lunarBlock.getRegistryName()),
				new ItemBlock(lyonBlock).setRegistryName(lyonBlock.getRegistryName()),
				new ItemBlock(mystiteBlock).setRegistryName(mystiteBlock.getRegistryName()),
				new ItemBlock(ornamyteBlock).setRegistryName(ornamyteBlock.getRegistryName()),
				new ItemBlock(rositeBlock).setRegistryName(rositeBlock.getRegistryName()),
				new ItemBlock(sapphireBlock).setRegistryName(sapphireBlock.getRegistryName()),
				new ItemBlock(shyregemBlock).setRegistryName(shyregemBlock.getRegistryName()),
				new ItemBlock(shyrestoneBlock).setRegistryName(shyrestoneBlock.getRegistryName()),
				new ItemBlock(skeletalIngotBlock).setRegistryName(skeletalIngotBlock.getRegistryName()),
				new ItemBlock(varsiumBlock).setRegistryName(varsiumBlock.getRegistryName()),
				new ItemBlock(carpetBaron).setRegistryName(carpetBaron.getRegistryName()),
				new ItemBlock(carpetBorean).setRegistryName(carpetBorean.getRegistryName()),
				new ItemBlock(carpetGardencian).setRegistryName(carpetGardencian.getRegistryName()),
				new ItemBlock(carpetIro).setRegistryName(carpetIro.getRegistryName()),
				new ItemBlock(carpetLunar).setRegistryName(carpetLunar.getRegistryName()),
				new ItemBlock(crystallanium).setRegistryName(crystallanium.getRegistryName()),
				new ItemBlock(emberium).setRegistryName(emberium.getRegistryName()),
				new ItemBlock(shadonantium).setRegistryName(shadonantium.getRegistryName()),
				new ItemBlock(skeletanium).setRegistryName(skeletanium.getRegistryName()),
				new ItemBlock(silvroBox).setRegistryName(silvroBox.getRegistryName()),
				new ItemBlock(crate).setRegistryName(crate.getRegistryName()),
				new ItemBlock(ancientCactus).setRegistryName(ancientCactus.getRegistryName()),
				new ItemBlock(ancientRock).setRegistryName(ancientRock.getRegistryName()),
				new ItemBlock(ancientTileBlack).setRegistryName(ancientTileBlack.getRegistryName()),
				new ItemBlock(ancientTileCore).setRegistryName(ancientTileCore.getRegistryName()),
				new ItemBlock(ancientTileGreen).setRegistryName(ancientTileGreen.getRegistryName()),
				new ItemBlock(ancientTileShrine).setRegistryName(ancientTileShrine.getRegistryName()),
				new ItemBlock(ancientTileWhite).setRegistryName(ancientTileWhite.getRegistryName()),
				new ItemBlock(archaicDirt).setRegistryName(archaicDirt.getRegistryName()),
				new ItemBlock(archaicHorizontalStream).setRegistryName(archaicHorizontalStream.getRegistryName()),
				new ItemBlock(archaicLadder).setRegistryName(archaicLadder.getRegistryName()),
				new ItemBlock(archaicRectangles).setRegistryName(archaicRectangles.getRegistryName()),
				new ItemBlock(archaicSquares).setRegistryName(archaicSquares.getRegistryName()),
				new ItemBlock(archaicTiles).setRegistryName(archaicTiles.getRegistryName()),
				new ItemBlock(archaicVerticalStream).setRegistryName(archaicVerticalStream.getRegistryName()),
				new ItemBlock(baronCastleWall).setRegistryName(baronCastleWall.getRegistryName()),
				new ItemBlock(baronCube).setRegistryName(baronCube.getRegistryName()),
				new ItemBlock(baronGround).setRegistryName(baronGround.getRegistryName()),
				new ItemBlock(bloodstoneBarBricks).setRegistryName(bloodstoneBarBricks.getRegistryName()),
				new ItemBlock(bloodstoneBars).setRegistryName(bloodstoneBars.getRegistryName()),
				new ItemBlock(boneyBlock).setRegistryName(boneyBlock.getRegistryName()),
				new ItemBlock(candyGreen).setRegistryName(candyGreen.getRegistryName()),
				new ItemBlock(candyRed).setRegistryName(candyRed.getRegistryName()),
				new ItemBlock(candyWhite).setRegistryName(candyWhite.getRegistryName()),
				new ItemBlock(celeveStem).setRegistryName(celeveStem.getRegistryName()),
				new ItemBlock(chargingTable).setRegistryName(chargingTable.getRegistryName()),
				new ItemBlock(carvedRuneDirection).setRegistryName(carvedRuneDirection.getRegistryName()),
				new ItemBlock(carvedRuneEmpowering).setRegistryName(carvedRuneEmpowering.getRegistryName()),
				new ItemBlock(carvedRuneFocus).setRegistryName(carvedRuneFocus.getRegistryName()),
				new ItemBlock(carvedRunePower).setRegistryName(carvedRunePower.getRegistryName()),
				new ItemBlock(carvedRuneReality).setRegistryName(carvedRuneReality.getRegistryName()),
				new ItemBlock(carvedRuneSpace).setRegistryName(carvedRuneSpace.getRegistryName()),
				new ItemBlock(carvedRuneTravel).setRegistryName(carvedRuneTravel.getRegistryName()),
				new ItemBlock(chocolateBlock).setRegistryName(chocolateBlock.getRegistryName()),
				new ItemBlock(chocolateBlockDark).setRegistryName(chocolateBlockDark.getRegistryName()),
				new ItemBlock(chocolateBlockWhite).setRegistryName(chocolateBlockWhite.getRegistryName()),
				new ItemBlock(cogBlock).setRegistryName(cogBlock.getRegistryName()),
				new ItemBlock(coralBlue).setRegistryName(coralBlue.getRegistryName()),
				new ItemBlock(coralGreen).setRegistryName(coralGreen.getRegistryName()),
				new ItemBlock(coralHard).setRegistryName(coralHard.getRegistryName()),
				new ItemBlock(coralOrange).setRegistryName(coralOrange.getRegistryName()),
				new ItemBlock(coralPink).setRegistryName(coralPink.getRegistryName()),
				new ItemBlock(coralWhite).setRegistryName(coralWhite.getRegistryName()),
				new ItemBlock(coralYellow).setRegistryName(coralYellow.getRegistryName()),
				new ItemBlock(cottonCandyAqua).setRegistryName(cottonCandyAqua.getRegistryName()),
				new ItemBlock(cottonCandyPink).setRegistryName(cottonCandyPink.getRegistryName()),
				new ItemBlock(crystalBlue).setRegistryName(crystalBlue.getRegistryName()),
				new ItemBlock(crystalGreen).setRegistryName(crystalGreen.getRegistryName()),
				new ItemBlock(crystalPurple).setRegistryName(crystalPurple.getRegistryName()),
				new ItemBlock(crystalRed).setRegistryName(crystalRed.getRegistryName()),
				new ItemBlock(crystalWhite).setRegistryName(crystalWhite.getRegistryName()),
				new ItemBlock(crystalYellow).setRegistryName(crystalYellow.getRegistryName()),
				new ItemBlock(darkFaceBrick).setRegistryName(darkFaceBrick.getRegistryName()),
				new ItemBlock(deeplandsTrapExplosion).setRegistryName(deeplandsTrapExplosion.getRegistryName()),
				new ItemBlock(deeplandsTrapLava).setRegistryName(deeplandsTrapLava.getRegistryName()),
				new ItemBlock(deeplandsTrapNipper).setRegistryName(deeplandsTrapNipper.getRegistryName()),
				new ItemBlock(deepshine).setRegistryName(deepshine.getRegistryName()),
				new ItemBlock(degradedSteel).setRegistryName(degradedSteel.getRegistryName()),
				new ItemBlock(dustopianLamp).setRegistryName(dustopianLamp.getRegistryName()),
				new ItemBlock(dustopianLampOff).setRegistryName(dustopianLampOff.getRegistryName()),
				new ItemBlock(enhancerDamage).setRegistryName(enhancerDamage.getRegistryName()),
				new ItemBlock(enhancerDurability).setRegistryName(enhancerDurability.getRegistryName()),
				new ItemBlock(enhancerMagical).setRegistryName(enhancerMagical.getRegistryName()),
				new ItemBlock(enhancerResistance).setRegistryName(enhancerResistance.getRegistryName()),
				new ItemBlock(enhancerSpeed).setRegistryName(enhancerSpeed.getRegistryName()),
				new ItemBlock(enhancerWeight).setRegistryName(enhancerWeight.getRegistryName()),
				new ItemBlock(eyeBlock).setRegistryName(eyeBlock.getRegistryName()),
				new ItemBlock(giantSnailAcid).setRegistryName(giantSnailAcid.getRegistryName()),
				new ItemBlock(gingerbread).setRegistryName(gingerbread.getRegistryName()),
				new ItemBlock(hauntedOrb).setRegistryName(hauntedOrb.getRegistryName()),
				new ItemBlock(hiveWall).setRegistryName(hiveWall.getRegistryName()),
				new ItemBlock(iroBrickTrap).setRegistryName(iroBrickTrap.getRegistryName()),
				new ItemBlock(iroBox).setRegistryName(iroBox.getRegistryName()),
				new ItemBlock(iropole).setRegistryName(iropole.getRegistryName()),
				new ItemBlock(kaiyuTempleBlockFace).setRegistryName(kaiyuTempleBlockFace.getRegistryName()),
				new ItemBlock(kaiyuTempleBlockFlow).setRegistryName(kaiyuTempleBlockFlow.getRegistryName()),
				new ItemBlock(kaiyuTempleBlockMaze).setRegistryName(kaiyuTempleBlockMaze.getRegistryName()),
				new ItemBlock(kaiyuTempleBlockPass).setRegistryName(kaiyuTempleBlockPass.getRegistryName()),
				new ItemBlock(kaiyuTempleBlockPlain).setRegistryName(kaiyuTempleBlockPlain.getRegistryName()),
				new ItemBlock(kaiyuTempleBlockSquares).setRegistryName(kaiyuTempleBlockSquares.getRegistryName()),
				new ItemBlock(kaiyuTempleBlockTrack).setRegistryName(kaiyuTempleBlockTrack.getRegistryName()),
				new ItemBlock(kaiyuTempleTrapFlow).setRegistryName(kaiyuTempleTrapFlow.getRegistryName()),
				new ItemBlock(kaiyuTempleTrapMaze).setRegistryName(kaiyuTempleTrapMaze.getRegistryName()),
				new ItemBlock(kaiyuTempleTrapPass).setRegistryName(kaiyuTempleTrapPass.getRegistryName()),
				new ItemBlock(kaiyuTempleTrapSquares).setRegistryName(kaiyuTempleTrapSquares.getRegistryName()),
				new ItemBlock(licorice).setRegistryName(licorice.getRegistryName()),
				new ItemBlock(lunarOrbDarklight).setRegistryName(lunarOrbDarklight.getRegistryName()),
				new ItemBlock(lunarOrbDusk).setRegistryName(lunarOrbDusk.getRegistryName()),
				new ItemBlock(lunarOrbLunar).setRegistryName(lunarOrbLunar.getRegistryName()),
				new ItemBlock(lunarOrbMoonlight).setRegistryName(lunarOrbMoonlight.getRegistryName()),
				new ItemBlock(lunarOrbSunfire).setRegistryName(lunarOrbSunfire.getRegistryName()),
				new ItemBlock(lunarPad).setRegistryName(lunarPad.getRegistryName()),
				new ItemBlock(lunarPillar).setRegistryName(lunarPillar.getRegistryName()),
				new ItemBlock(marshmallow).setRegistryName(marshmallow.getRegistryName()),
				new ItemBlock(orangeAcid).setRegistryName(orangeAcid.getRegistryName()),
				new ItemBlock(paraviteHive).setRegistryName(paraviteHive.getRegistryName()),
				new ItemBlock(petalsBlack).setRegistryName(petalsBlack.getRegistryName()),
				new ItemBlock(petalsBlue).setRegistryName(petalsBlue.getRegistryName()),
				new ItemBlock(petalsLightBlue).setRegistryName(petalsLightBlue.getRegistryName()),
				new ItemBlock(petalsMagenta).setRegistryName(petalsMagenta.getRegistryName()),
				new ItemBlock(petalsOrange).setRegistryName(petalsOrange.getRegistryName()),
				new ItemBlock(petalsPurple).setRegistryName(petalsPurple.getRegistryName()),
				new ItemBlock(petalsRed).setRegistryName(petalsRed.getRegistryName()),
				new ItemBlock(petalsRose).setRegistryName(petalsRose.getRegistryName()),
				new ItemBlock(petalsYellow).setRegistryName(petalsYellow.getRegistryName()),
				new ItemBlock(plastic).setRegistryName(plastic.getRegistryName()),
				new ItemBlock(rockCandyBlue).setRegistryName(rockCandyBlue.getRegistryName()),
				new ItemBlock(rockCandyGreen).setRegistryName(rockCandyGreen.getRegistryName()),
				new ItemBlock(rockCandyPink).setRegistryName(rockCandyPink.getRegistryName()),
				new ItemBlock(rockCandyPurple).setRegistryName(rockCandyPurple.getRegistryName()),
				new ItemBlock(runePostCompass).setRegistryName(runePostCompass.getRegistryName()),
				new ItemBlock(runePostDistortion).setRegistryName(runePostDistortion.getRegistryName()),
				new ItemBlock(runePostEnergy).setRegistryName(runePostEnergy.getRegistryName()),
				new ItemBlock(runePostFire).setRegistryName(runePostFire.getRegistryName()),
				new ItemBlock(runePostKinetic).setRegistryName(runePostKinetic.getRegistryName()),
				new ItemBlock(runePostLife).setRegistryName(runePostLife.getRegistryName()),
				new ItemBlock(runePostLunar).setRegistryName(runePostLunar.getRegistryName()),
				new ItemBlock(runePostPoison).setRegistryName(runePostPoison.getRegistryName()),
				new ItemBlock(runePostPower).setRegistryName(runePostPower.getRegistryName()),
				new ItemBlock(runePostStorm).setRegistryName(runePostStorm.getRegistryName()),
				new ItemBlock(runePostStrike).setRegistryName(runePostStrike.getRegistryName()),
				new ItemBlock(runePostWater).setRegistryName(runePostWater.getRegistryName()),
				new ItemBlock(runePostWind).setRegistryName(runePostWind.getRegistryName()),
				new ItemBlock(runePostWither).setRegistryName(runePostWither.getRegistryName()),
				new ItemBlock(shyreCloud).setRegistryName(shyreCloud.getRegistryName()),
				new ItemBlock(skeletalBlock).setRegistryName(skeletalBlock.getRegistryName()),
				new ItemBlock(skullBlock).setRegistryName(skullBlock.getRegistryName()),
				new ItemBlock(skullBlockDark).setRegistryName(skullBlockDark.getRegistryName()),
				new ItemBlock(spikeyPillar).setRegistryName(spikeyPillar.getRegistryName()),
				new ItemBlock(tentacles).setRegistryName(tentacles.getRegistryName()),
				new ItemBlock(tentaclesDotsLeft).setRegistryName(tentaclesDotsLeft.getRegistryName()),
				new ItemBlock(tentaclesDotsRight).setRegistryName(tentaclesDotsRight.getRegistryName()),
				new ItemBlock(tentaclesEyeOrange).setRegistryName(tentaclesEyeOrange.getRegistryName()),
				new ItemBlock(tentaclesEyeRed).setRegistryName(tentaclesEyeRed.getRegistryName()),
				new ItemBlock(tentaclesGreen).setRegistryName(tentaclesGreen.getRegistryName()),
				new ItemBlock(toxicBlock).setRegistryName(toxicBlock.getRegistryName()),
				new ItemBlock(toxicStem).setRegistryName(toxicStem.getRegistryName()),
				new ItemBlock(toxicWaste).setRegistryName(toxicWaste.getRegistryName()),
				new ItemBlock(unbreakableIroBricks).setRegistryName(unbreakableIroBricks.getRegistryName()),
				new ItemBlock(unbreakablePlantStem).setRegistryName(unbreakablePlantStem.getRegistryName()),
				new ItemBlock(unbreakableRunicBricks).setRegistryName(unbreakableRunicBricks.getRegistryName()),
				new ItemBlock(shroomBlue).setRegistryName(shroomBlue.getRegistryName()),
				new ItemBlock(shroomGreen).setRegistryName(shroomGreen.getRegistryName()),
				new ItemBlock(shroomOrange).setRegistryName(shroomOrange.getRegistryName()),
				new ItemBlock(shroomPurple).setRegistryName(shroomPurple.getRegistryName()),
				new ItemBlock(shroomVox).setRegistryName(shroomVox.getRegistryName()),
				new ItemBlock(shroomYellow).setRegistryName(shroomYellow.getRegistryName()),
				new ItemBlock(voxLog1).setRegistryName(voxLog1.getRegistryName()),
				new ItemBlock(voxLog2).setRegistryName(voxLog2.getRegistryName()),
				new ItemBlock(shroomStem).setRegistryName(shroomStem.getRegistryName()),
				new ItemBlock(spawnerAmphibior).setRegistryName(spawnerAmphibior.getRegistryName()),
				new ItemBlock(spawnerAmphibiyte).setRegistryName(spawnerAmphibiyte.getRegistryName()),
				new ItemBlock(spawnerArcWizard).setRegistryName(spawnerArcWizard.getRegistryName()),
				new ItemBlock(spawnerArkzyne).setRegistryName(spawnerArkzyne.getRegistryName()),
				new ItemBlock(spawnerArocknid).setRegistryName(spawnerArocknid.getRegistryName()),
				new ItemBlock(spawnerAngelica).setRegistryName(spawnerAngelica.getRegistryName()),
				new ItemBlock(spawnerBanshee).setRegistryName(spawnerBanshee.getRegistryName()),
				new ItemBlock(spawnerBaumba).setRegistryName(spawnerBaumba.getRegistryName()),
				new ItemBlock(spawnerBloodsucker).setRegistryName(spawnerBloodsucker.getRegistryName()),
				new ItemBlock(spawnerCaneBug).setRegistryName(spawnerCaneBug.getRegistryName()),
				new ItemBlock(spawnerRockCritter).setRegistryName(spawnerRockCritter.getRegistryName()),
				new ItemBlock(spawnerCrusilisk).setRegistryName(spawnerCrusilisk.getRegistryName()),
				new ItemBlock(spawnerDawnlight).setRegistryName(spawnerDawnlight.getRegistryName()),
				new ItemBlock(spawnerDaysee).setRegistryName(spawnerDaysee.getRegistryName()),
				new ItemBlock(spawnerDiocus).setRegistryName(spawnerDiocus.getRegistryName()),
				new ItemBlock(spawnerEnforcer).setRegistryName(spawnerEnforcer.getRegistryName()),
				new ItemBlock(spawnerExohead).setRegistryName(spawnerExohead.getRegistryName()),
				new ItemBlock(spawnerFacelessFloater).setRegistryName(spawnerFacelessFloater.getRegistryName()),
				new ItemBlock(spawnerFenix).setRegistryName(spawnerFenix.getRegistryName()),
				new ItemBlock(spawnerFleshEater).setRegistryName(spawnerFleshEater.getRegistryName()),
				new ItemBlock(spawnerFlowerface).setRegistryName(spawnerFlowerface.getRegistryName()),
				new ItemBlock(spawnerFungock).setRegistryName(spawnerFungock.getRegistryName()),
				new ItemBlock(spawnerGhastus).setRegistryName(spawnerGhastus.getRegistryName()),
				new ItemBlock(spawnerGingerbird).setRegistryName(spawnerGingerbird.getRegistryName()),
				new ItemBlock(spawnerGingerbreadMan).setRegistryName(spawnerGingerbreadMan.getRegistryName()),
				new ItemBlock(spawnerGoldum).setRegistryName(spawnerGoldum.getRegistryName()),
				new ItemBlock(spawnerGoldus).setRegistryName(spawnerGoldus.getRegistryName()),
				new ItemBlock(spawnerInmateX).setRegistryName(spawnerInmateX.getRegistryName()),
				new ItemBlock(spawnerInmateY).setRegistryName(spawnerInmateY.getRegistryName()),
				new ItemBlock(spawnerIosaur).setRegistryName(spawnerIosaur.getRegistryName()),
				new ItemBlock(spawnerJawe).setRegistryName(spawnerJawe.getRegistryName()),
				new ItemBlock(spawnerKaiyu).setRegistryName(spawnerKaiyu.getRegistryName()),
				new ItemBlock(spawnerLightwalker).setRegistryName(spawnerLightwalker.getRegistryName()),
				new ItemBlock(spawnerLuxocron).setRegistryName(spawnerLuxocron.getRegistryName()),
				new ItemBlock(spawnerMechyon).setRegistryName(spawnerMechyon.getRegistryName()),
				new ItemBlock(spawnerMerkyre).setRegistryName(spawnerMerkyre.getRegistryName()),
				new ItemBlock(spawnerMermage).setRegistryName(spawnerMermage.getRegistryName()),
				new ItemBlock(spawnerMushroomSpider).setRegistryName(spawnerMushroomSpider.getRegistryName()),
				new ItemBlock(spawnerNethengeicBeast).setRegistryName(spawnerNethengeicBeast.getRegistryName()),
				new ItemBlock(spawnerNightmareSpider).setRegistryName(spawnerNightmareSpider.getRegistryName()),
				new ItemBlock(spawnerNightwing).setRegistryName(spawnerNightwing.getRegistryName()),
				new ItemBlock(spawnerOpteryx).setRegistryName(spawnerOpteryx.getRegistryName()),
				new ItemBlock(spawnerParavite).setRegistryName(spawnerParavite.getRegistryName()),
				new ItemBlock(spawnerPhantom).setRegistryName(spawnerPhantom.getRegistryName()),
				new ItemBlock(spawnerPodPlant).setRegistryName(spawnerPodPlant.getRegistryName()),
				new ItemBlock(spawnerRawbone).setRegistryName(spawnerRawbone.getRegistryName()),
				new ItemBlock(spawnerReaver).setRegistryName(spawnerReaver.getRegistryName()),
				new ItemBlock(spawnerRefluct).setRegistryName(spawnerRefluct.getRegistryName()),
				new ItemBlock(spawnerRunicGolem).setRegistryName(spawnerRunicGolem.getRegistryName()),
				new ItemBlock(spawnerRunicGuardian).setRegistryName(spawnerRunicGuardian.getRegistryName()),
				new ItemBlock(spawnerSeeker).setRegistryName(spawnerSeeker.getRegistryName()),
				new ItemBlock(spawnerShavo).setRegistryName(spawnerShavo.getRegistryName()),
				new ItemBlock(spawnerShyreTroll).setRegistryName(spawnerShyreTroll.getRegistryName()),
				new ItemBlock(spawnerSkeledon).setRegistryName(spawnerSkeledon.getRegistryName()),
				new ItemBlock(spawnerSkelekyte).setRegistryName(spawnerSkelekyte.getRegistryName()),
				new ItemBlock(spawnerSoulscorne).setRegistryName(spawnerSoulscorne.getRegistryName()),
				new ItemBlock(spawnerSpectralWizard).setRegistryName(spawnerSpectralWizard.getRegistryName()),
				new ItemBlock(spawnerSpinoledon).setRegistryName(spawnerSpinoledon.getRegistryName()),
				new ItemBlock(spawnerSurveyor).setRegistryName(spawnerSurveyor.getRegistryName()),
				new ItemBlock(spawnerTharafly).setRegistryName(spawnerTharafly.getRegistryName()),
				new ItemBlock(spawnerUndeadTroll).setRegistryName(spawnerUndeadTroll.getRegistryName()),
				new ItemBlock(spawnerUrioh).setRegistryName(spawnerUrioh.getRegistryName()),
				new ItemBlock(spawnerUrv).setRegistryName(spawnerUrv.getRegistryName()),
				new ItemBlock(spawnerVineWizard).setRegistryName(spawnerVineWizard.getRegistryName()),
				new ItemBlock(spawnerVisage).setRegistryName(spawnerVisage.getRegistryName()),
				new ItemBlock(spawnerVolar).setRegistryName(spawnerVolar.getRegistryName()),
				new ItemBlock(spawnerZarg).setRegistryName(spawnerZarg.getRegistryName()),
				new ItemBlock(spawnerZhinx).setRegistryName(spawnerZhinx.getRegistryName()),
				new ItemBlock(spawnerZorp).setRegistryName(spawnerZorp.getRegistryName()),
				new ItemBlock(armyBlock).setRegistryName(armyBlock.getRegistryName()),
				new ItemBlock(baronessAltar).setRegistryName(baronessAltar.getRegistryName()),
				new ItemBlock(candyBlock).setRegistryName(candyBlock.getRegistryName()),
				new ItemBlock(clunkheadAltar).setRegistryName(clunkheadAltar.getRegistryName()),
				new ItemBlock(craexxeusAltar).setRegistryName(craexxeusAltar.getRegistryName()),
				new ItemBlock(creepAltar).setRegistryName(creepAltar.getRegistryName()),
				new ItemBlock(dracyonAltar).setRegistryName(dracyonAltar.getRegistryName()),
				new ItemBlock(grawAltar).setRegistryName(grawAltar.getRegistryName()),
				new ItemBlock(guardianAltar).setRegistryName(guardianAltar.getRegistryName()),
				new ItemBlock(hiveSpawner).setRegistryName(hiveSpawner.getRegistryName()),
				new ItemBlock(hydroTable).setRegistryName(hydroTable.getRegistryName()),
				new ItemBlock(illusionAltar).setRegistryName(illusionAltar.getRegistryName()),
				new ItemBlock(krorAltar).setRegistryName(krorAltar.getRegistryName()),
				new ItemBlock(mechbotAltar).setRegistryName(mechbotAltar.getRegistryName()),
				new ItemBlock(powerStation).setRegistryName(powerStation.getRegistryName()),
				new ItemBlock(primordialShrine).setRegistryName(primordialShrine.getRegistryName()),
				new ItemBlock(rockriderShrine).setRegistryName(rockriderShrine.getRegistryName()),
				new ItemBlock(shadowAltar).setRegistryName(shadowAltar.getRegistryName()),
				new ItemBlock(silverfootAltar).setRegistryName(silverfootAltar.getRegistryName()),
				new ItemBlock(toyBox).setRegistryName(toyBox.getRegistryName()),
				new ItemBlock(tyrosaurAltar).setRegistryName(tyrosaurAltar.getRegistryName()),
				new ItemBlock(vinocorneShrine).setRegistryName(vinocorneShrine.getRegistryName()),
				new ItemBlock(visualentAltar).setRegistryName(visualentAltar.getRegistryName()),
				new ItemBlock(voxxulonAltar).setRegistryName(voxxulonAltar.getRegistryName()),
				new ItemBlock(portalAbyss).setRegistryName(portalAbyss.getRegistryName()),
				new ItemBlock(portalAncientCavern).setRegistryName(portalAncientCavern.getRegistryName()),
				new ItemBlock(portalBarathos).setRegistryName(portalBarathos.getRegistryName()),
				new ItemBlock(portalBorean).setRegistryName(portalBorean.getRegistryName()),
				new ItemBlock(portalCandyland).setRegistryName(portalCandyland.getRegistryName()),
				new ItemBlock(portalCeleve).setRegistryName(portalCeleve.getRegistryName()),
				new ItemBlock(portalCreeponia).setRegistryName(portalCreeponia.getRegistryName()),
				new ItemBlock(portalCrystevia).setRegistryName(portalCrystevia.getRegistryName()),
				new ItemBlock(portalDeeplands).setRegistryName(portalDeeplands.getRegistryName()),
				new ItemBlock(portalDustopia).setRegistryName(portalDustopia.getRegistryName()),
				new ItemBlock(portalGardencia).setRegistryName(portalGardencia.getRegistryName()),
				new ItemBlock(portalGreckon).setRegistryName(portalGreckon.getRegistryName()),
				new ItemBlock(portalHaven).setRegistryName(portalHaven.getRegistryName()),
				new ItemBlock(portalImmortallis).setRegistryName(portalImmortallis.getRegistryName()),
				new ItemBlock(portalIromine).setRegistryName(portalIromine.getRegistryName()),
				new ItemBlock(portalLelyetia).setRegistryName(portalLelyetia.getRegistryName()),
				new ItemBlock(portalLunalus).setRegistryName(portalLunalus.getRegistryName()),
				new ItemBlock(portalMysterium).setRegistryName(portalMysterium.getRegistryName()),
				new ItemBlock(portalPrecasia).setRegistryName(portalPrecasia.getRegistryName()),
				new ItemBlock(portalRunandor).setRegistryName(portalRunandor.getRegistryName()),
				new ItemBlock(portalShyrelands).setRegistryName(portalShyrelands.getRegistryName()),
				new ItemBlock(portalVoxPonds).setRegistryName(portalVoxPonds.getRegistryName()),
				new ItemBlock(ancientAltar).setRegistryName(ancientAltar.getRegistryName()),
				new ItemBlock(shrineErebon).setRegistryName(shrineErebon.getRegistryName()),
				new ItemBlock(shrineLuxon).setRegistryName(shrineLuxon.getRegistryName()),
				new ItemBlock(shrinePluton).setRegistryName(shrinePluton.getRegistryName()),
				new ItemBlock(shrineSelyan).setRegistryName(shrineSelyan.getRegistryName()),
				new ItemBlock(ascensionShrine).setRegistryName(ascensionShrine.getRegistryName()),
				new ItemBlock(creationForge).setRegistryName(creationForge.getRegistryName()),
				new ItemBlock(crystalCreatorBlue).setRegistryName(crystalCreatorBlue.getRegistryName()),
				new ItemBlock(crystalCreatorGreen).setRegistryName(crystalCreatorGreen.getRegistryName()),
				new ItemBlock(crystalCreatorPurple).setRegistryName(crystalCreatorPurple.getRegistryName()),
				new ItemBlock(crystalCreatorRed).setRegistryName(crystalCreatorRed.getRegistryName()),
				new ItemBlock(crystalCreatorWhite).setRegistryName(crystalCreatorWhite.getRegistryName()),
				new ItemBlock(crystalCreatorYellow).setRegistryName(crystalCreatorYellow.getRegistryName()),
				new ItemBlock(crystalExtensionShrine).setRegistryName(crystalExtensionShrine.getRegistryName()),
				new ItemBlock(decloggingTable).setRegistryName(decloggingTable.getRegistryName()),
				new ItemBlock(deepCase).setRegistryName(deepCase.getRegistryName()),
				new ItemBlock(divineStation).setRegistryName(divineStation.getRegistryName()),
				new ItemBlock(enigmaTable).setRegistryName(enigmaTable.getRegistryName()),
				new ItemBlock(exoidStation).setRegistryName(exoidStation.getRegistryName()),
				new ItemBlock(extractionDevice).setRegistryName(extractionDevice.getRegistryName()),
				new ItemBlock(filtrationSystem).setRegistryName(filtrationSystem.getRegistryName()),
				new ItemBlock(goldAccumulator).setRegistryName(goldAccumulator.getRegistryName()),
				new ItemBlock(hauntingTable).setRegistryName(hauntingTable.getRegistryName()),
				new ItemBlock(immortallisProgressor1).setRegistryName(immortallisProgressor1.getRegistryName()),
				new ItemBlock(immortallisProgressor2).setRegistryName(immortallisProgressor2.getRegistryName()),
				new ItemBlock(immortallisProgressor3).setRegistryName(immortallisProgressor3.getRegistryName()),
				new ItemBlock(immortallisProgressor4).setRegistryName(immortallisProgressor4.getRegistryName()),
				new ItemBlock(immortallisProgressor5).setRegistryName(immortallisProgressor5.getRegistryName()),
				new ItemBlock(immortallisProgressor6).setRegistryName(immortallisProgressor6.getRegistryName()),
				new ItemBlock(immortallisProgressor7).setRegistryName(immortallisProgressor7.getRegistryName()),
				new ItemBlock(immortallisProgressor8).setRegistryName(immortallisProgressor8.getRegistryName()),
				new ItemBlock(immortallisProgressor9).setRegistryName(immortallisProgressor9.getRegistryName()),
				new ItemBlock(infusionTable).setRegistryName(infusionTable.getRegistryName()),
				new ItemBlock(iroCrate).setRegistryName(iroCrate.getRegistryName()),
				new ItemBlock(lunarCreationTable).setRegistryName(lunarCreationTable.getRegistryName()),
				new ItemBlock(lunarEnrichmentTable).setRegistryName(lunarEnrichmentTable.getRegistryName()),
				new ItemBlock(mendingTable).setRegistryName(mendingTable.getRegistryName()),
				new ItemBlock(mineralizationStation).setRegistryName(mineralizationStation.getRegistryName()),
				new ItemBlock(petalCraftingStation).setRegistryName(petalCraftingStation.getRegistryName()),
				new ItemBlock(pureGoldAccumulator).setRegistryName(pureGoldAccumulator.getRegistryName()),
				new ItemBlock(recreationStation).setRegistryName(recreationStation.getRegistryName()),
				new ItemBlock(runeRandomizer).setRegistryName(runeRandomizer.getRegistryName()),
				new ItemBlock(runeShrine).setRegistryName(runeShrine.getRegistryName()),
				new ItemBlock(runicBlock).setRegistryName(runicBlock.getRegistryName()),
				new ItemBlock(strangeBlock).setRegistryName(strangeBlock.getRegistryName()),
				new ItemBlock(teaSink).setRegistryName(teaSink.getRegistryName()),
				new ItemBlock(voxCrate).setRegistryName(voxCrate.getRegistryName()),
				new ItemBlock(voxStoreCrate).setRegistryName(voxStoreCrate.getRegistryName()),
				new ItemBlock(whitewashingTable).setRegistryName(whitewashingTable.getRegistryName()),
				new ItemBlock(plantAncientVines).setRegistryName(plantAncientVines.getRegistryName()),
				new ItemBlock(plantAncientVinesCap).setRegistryName(plantAncientVinesCap.getRegistryName()),
				new ItemBlock(plantAquaFungiBlue).setRegistryName(plantAquaFungiBlue.getRegistryName()),
				new ItemBlock(plantAquaFungiYellow).setRegistryName(plantAquaFungiYellow.getRegistryName()),
				new ItemBlock(plantArcbulb).setRegistryName(plantArcbulb.getRegistryName()),
				new ItemBlock(plantArcflower).setRegistryName(plantArcflower.getRegistryName()),
				new ItemBlock(plantBloodPine).setRegistryName(plantBloodPine.getRegistryName()),
				new ItemBlock(plantBloodPineStem).setRegistryName(plantBloodPineStem.getRegistryName()),
				new ItemBlock(plantBloodSpikes).setRegistryName(plantBloodSpikes.getRegistryName()),
				new ItemBlock(plantBloodStrands).setRegistryName(plantBloodStrands.getRegistryName()),
				new ItemBlock(plantBulbStock).setRegistryName(plantBulbStock.getRegistryName()),
				new ItemBlock(plantBulbStockCap).setRegistryName(plantBulbStockCap.getRegistryName()),
				new ItemBlock(plantBurealStocks).setRegistryName(plantBurealStocks.getRegistryName()),
				new ItemBlock(plantCandycane).setRegistryName(plantCandycane.getRegistryName()),
				new ItemBlock(plantCandyGrass).setRegistryName(plantCandyGrass.getRegistryName()),
				new ItemBlock(plantCandyGrassBlue).setRegistryName(plantCandyGrassBlue.getRegistryName()),
				new ItemBlock(plantCandyGrassWhite).setRegistryName(plantCandyGrassWhite.getRegistryName()),
				new ItemBlock(plantCandyTube).setRegistryName(plantCandyTube.getRegistryName()),
				new ItemBlock(plantCelebulbsGreen).setRegistryName(plantCelebulbsGreen.getRegistryName()),
				new ItemBlock(plantCelebulbsStem).setRegistryName(plantCelebulbsStem.getRegistryName()),
				new ItemBlock(plantCelebulbsYellow).setRegistryName(plantCelebulbsYellow.getRegistryName()),
				new ItemBlock(plantCeleviansBlue).setRegistryName(plantCeleviansBlue.getRegistryName()),
				new ItemBlock(plantCeleviansPurple).setRegistryName(plantCeleviansPurple.getRegistryName()),
				new ItemBlock(plantCeleviansRed).setRegistryName(plantCeleviansRed.getRegistryName()),
				new ItemBlock(plantCeleviansWhite).setRegistryName(plantCeleviansWhite.getRegistryName()),
				new ItemBlock(plantChocolateGrass).setRegistryName(plantChocolateGrass.getRegistryName()),
				new ItemBlock(plantChocolateStocks).setRegistryName(plantChocolateStocks.getRegistryName()),
				new ItemBlock(plantCoralCage).setRegistryName(plantCoralCage.getRegistryName()),
				new ItemBlock(plantCreepFlowers).setRegistryName(plantCreepFlowers.getRegistryName()),
				new ItemBlock(plantCreepGrass).setRegistryName(plantCreepGrass.getRegistryName()),
				new ItemBlock(plantCreepVines).setRegistryName(plantCreepVines.getRegistryName()),
				new ItemBlock(plantCrystalBlue).setRegistryName(plantCrystalBlue.getRegistryName()),
				new ItemBlock(plantCrystalGreen).setRegistryName(plantCrystalGreen.getRegistryName()),
				new ItemBlock(plantCrystalPurple).setRegistryName(plantCrystalPurple.getRegistryName()),
				new ItemBlock(plantCrystalRed).setRegistryName(plantCrystalRed.getRegistryName()),
				new ItemBlock(plantCrystalWhite).setRegistryName(plantCrystalWhite.getRegistryName()),
				new ItemBlock(plantCrystalYellow).setRegistryName(plantCrystalYellow.getRegistryName()),
				new ItemBlock(plantDaileers).setRegistryName(plantDaileers.getRegistryName()),
				new ItemBlock(plantDawnBulb).setRegistryName(plantDawnBulb.getRegistryName()),
				new ItemBlock(plantDawnBush).setRegistryName(plantDawnBush.getRegistryName()),
				new ItemBlock(plantDawnFlower).setRegistryName(plantDawnFlower.getRegistryName()),
				new ItemBlock(plantDawnGrass).setRegistryName(plantDawnGrass.getRegistryName()),
				new ItemBlock(plantDawnStocks).setRegistryName(plantDawnStocks.getRegistryName()),
				new ItemBlock(plantDawnStocksTop).setRegistryName(plantDawnStocksTop.getRegistryName()),
				new ItemBlock(plantDawnwoodBars).setRegistryName(plantDawnwoodBars.getRegistryName()),
				new ItemBlock(plantDayloomsBlue).setRegistryName(plantDayloomsBlue.getRegistryName()),
				new ItemBlock(plantDayloomsPink).setRegistryName(plantDayloomsPink.getRegistryName()),
				new ItemBlock(plantDayloomsYellow).setRegistryName(plantDayloomsYellow.getRegistryName()),
				new ItemBlock(plantDeadGrass).setRegistryName(plantDeadGrass.getRegistryName()),
				new ItemBlock(plantDeepBlooms).setRegistryName(plantDeepBlooms.getRegistryName()),
				new ItemBlock(plantDeepBulb).setRegistryName(plantDeepBulb.getRegistryName()),
				new ItemBlock(plantDeepGrass).setRegistryName(plantDeepGrass.getRegistryName()),
				new ItemBlock(plantDeepVines).setRegistryName(plantDeepVines.getRegistryName()),
				new ItemBlock(plantEyeShrub).setRegistryName(plantEyeShrub.getRegistryName()),
				new ItemBlock(plantEyeShrubStem).setRegistryName(plantEyeShrubStem.getRegistryName()),
				new ItemBlock(plantFlakeVine).setRegistryName(plantFlakeVine.getRegistryName()),
				new ItemBlock(plantFlakeVineTop).setRegistryName(plantFlakeVineTop.getRegistryName()),
				new ItemBlock(plantGardenGrass).setRegistryName(plantGardenGrass.getRegistryName()),
				new ItemBlock(plantHauntedFlower).setRegistryName(plantHauntedFlower.getRegistryName()),
				new ItemBlock(plantHavendalesBlue).setRegistryName(plantHavendalesBlue.getRegistryName()),
				new ItemBlock(plantHavendalesBlueStem).setRegistryName(plantHavendalesBlueStem.getRegistryName()),
				new ItemBlock(plantHavendalesPink).setRegistryName(plantHavendalesPink.getRegistryName()),
				new ItemBlock(plantHavendalesPinkStem).setRegistryName(plantHavendalesPinkStem.getRegistryName()),
				new ItemBlock(plantHavendalesYellow).setRegistryName(plantHavendalesYellow.getRegistryName()),
				new ItemBlock(plantHavendalesYellowStem).setRegistryName(plantHavendalesYellowStem.getRegistryName()),
				new ItemBlock(plantHavenGrass).setRegistryName(plantHavenGrass.getRegistryName()),
				new ItemBlock(plantHorizonDaisies).setRegistryName(plantHorizonDaisies.getRegistryName()),
				new ItemBlock(plantIroGrass).setRegistryName(plantIroGrass.getRegistryName()),
				new ItemBlock(plantIrotops).setRegistryName(plantIrotops.getRegistryName()),
				new ItemBlock(plantLelyetianGrass).setRegistryName(plantLelyetianGrass.getRegistryName()),
				new ItemBlock(plantLelyetianGrassDown).setRegistryName(plantLelyetianGrassDown.getRegistryName()),
				new ItemBlock(plantLelyetianStem).setRegistryName(plantLelyetianStem.getRegistryName()),
				new ItemBlock(plantLelyetianStemCap).setRegistryName(plantLelyetianStemCap.getRegistryName()),
				new ItemBlock(plantLelyetianStemCapDown).setRegistryName(plantLelyetianStemCapDown.getRegistryName()),
				new ItemBlock(plantLelyetianWiggler).setRegistryName(plantLelyetianWiggler.getRegistryName()),
				new ItemBlock(plantLelyetianWigglerBottom).setRegistryName(plantLelyetianWigglerBottom.getRegistryName()),
				new ItemBlock(plantLelyetianWigglerTop).setRegistryName(plantLelyetianWigglerTop.getRegistryName()),
				new ItemBlock(plantLollypopBlue).setRegistryName(plantLollypopBlue.getRegistryName()),
				new ItemBlock(plantLollypopRed).setRegistryName(plantLollypopRed.getRegistryName()),
				new ItemBlock(plantLollypopYellow).setRegistryName(plantLollypopYellow.getRegistryName()),
				new ItemBlock(plantLuconGrass).setRegistryName(plantLuconGrass.getRegistryName()),
				new ItemBlock(plantLunalip).setRegistryName(plantLunalip.getRegistryName()),
				new ItemBlock(plantLuntar).setRegistryName(plantLuntar.getRegistryName()),
				new ItemBlock(plantLurchians).setRegistryName(plantLurchians.getRegistryName()),
				new ItemBlock(plantLylips).setRegistryName(plantLylips.getRegistryName()),
				new ItemBlock(plantMagias).setRegistryName(plantMagias.getRegistryName()),
				new ItemBlock(plantMallowPile).setRegistryName(plantMallowPile.getRegistryName()),
				new ItemBlock(plantMarshTube).setRegistryName(plantMarshTube.getRegistryName()),
				new ItemBlock(plantMellians).setRegistryName(plantMellians.getRegistryName()),
				new ItemBlock(plantMysticBush).setRegistryName(plantMysticBush.getRegistryName()),
				new ItemBlock(plantMysticFerns).setRegistryName(plantMysticFerns.getRegistryName()),
				new ItemBlock(plantOcealitesBlue).setRegistryName(plantOcealitesBlue.getRegistryName()),
				new ItemBlock(plantOcealitesRed).setRegistryName(plantOcealitesRed.getRegistryName()),
				new ItemBlock(plantPeppermintGreen).setRegistryName(plantPeppermintGreen.getRegistryName()),
				new ItemBlock(plantPeppermintRed).setRegistryName(plantPeppermintRed.getRegistryName()),
				new ItemBlock(plantPertonias).setRegistryName(plantPertonias.getRegistryName()),
				new ItemBlock(plantPlasticStick).setRegistryName(plantPlasticStick.getRegistryName()),
				new ItemBlock(plantRainbowGrass).setRegistryName(plantRainbowGrass.getRegistryName()),
				new ItemBlock(plantRainbowGrass2).setRegistryName(plantRainbowGrass2.getRegistryName()),
				new ItemBlock(plantRainbowGrass3).setRegistryName(plantRainbowGrass3.getRegistryName()),
				new ItemBlock(plantRuneBulbs).setRegistryName(plantRuneBulbs.getRegistryName()),
				new ItemBlock(plantRunicBush).setRegistryName(plantRunicBush.getRegistryName()),
				new ItemBlock(plantShadicles).setRegistryName(plantShadicles.getRegistryName()),
				new ItemBlock(plantShadiclesTop).setRegistryName(plantShadiclesTop.getRegistryName()),
				new ItemBlock(plantShadowShrub).setRegistryName(plantShadowShrub.getRegistryName()),
				new ItemBlock(plantShyreCap).setRegistryName(plantShyreCap.getRegistryName()),
				new ItemBlock(plantShyreCapDown).setRegistryName(plantShyreCapDown.getRegistryName()),
				new ItemBlock(plantShyreStock).setRegistryName(plantShyreStock.getRegistryName()),
				new ItemBlock(plantShyreWeed).setRegistryName(plantShyreWeed.getRegistryName()),
				new ItemBlock(plantSilverGrass).setRegistryName(plantSilverGrass.getRegistryName()),
				new ItemBlock(plantTangleThorns).setRegistryName(plantTangleThorns.getRegistryName()),
				new ItemBlock(plantTubeicles).setRegistryName(plantTubeicles.getRegistryName()),
				new ItemBlock(plantVoxFungi).setRegistryName(plantVoxFungi.getRegistryName()),
				new ItemBlock(plantVoxFungiStem).setRegistryName(plantVoxFungiStem.getRegistryName()),
				new ItemBlock(plantVoxTentacles).setRegistryName(plantVoxTentacles.getRegistryName()),
				new ItemBlock(plantVoxTentaclesStem).setRegistryName(plantVoxTentaclesStem.getRegistryName()),
				new ItemBlock(plantWaterweedsGreen).setRegistryName(plantWaterweedsGreen.getRegistryName()),
				new ItemBlock(plantWaterweedsRed).setRegistryName(plantWaterweedsRed.getRegistryName()),
				new ItemBlock(plantWaterweedsWhite).setRegistryName(plantWaterweedsWhite.getRegistryName()),
				new ItemBlock(plantWaterweedsYellow).setRegistryName(plantWaterweedsYellow.getRegistryName()),
				new ItemBlock(statueBane).setRegistryName(statueBane.getRegistryName()),
				new ItemBlock(statueBaneGold).setRegistryName(statueBaneGold.getRegistryName()),
				new ItemBlock(statueBaneOrnate).setRegistryName(statueBaneOrnate.getRegistryName()),
				new ItemBlock(statueBaroness).setRegistryName(statueBaroness.getRegistryName()),
				new ItemBlock(statueBaronessGold).setRegistryName(statueBaronessGold.getRegistryName()),
				new ItemBlock(statueBaronessOrnate).setRegistryName(statueBaronessOrnate.getRegistryName()),
				new ItemBlock(statueClunkhead).setRegistryName(statueClunkhead.getRegistryName()),
				new ItemBlock(statueClunkheadGold).setRegistryName(statueClunkheadGold.getRegistryName()),
				new ItemBlock(statueClunkheadOrnate).setRegistryName(statueClunkheadOrnate.getRegistryName()),
				new ItemBlock(statueConiferon).setRegistryName(statueConiferon.getRegistryName()),
				new ItemBlock(statueConiferonGold).setRegistryName(statueConiferonGold.getRegistryName()),
				new ItemBlock(statueConiferonOrnate).setRegistryName(statueConiferonOrnate.getRegistryName()),
				new ItemBlock(statueCorallus).setRegistryName(statueCorallus.getRegistryName()),
				new ItemBlock(statueCorallusGold).setRegistryName(statueCorallusGold.getRegistryName()),
				new ItemBlock(statueCorallusOrnate).setRegistryName(statueCorallusOrnate.getRegistryName()),
				new ItemBlock(statueCottonCandor).setRegistryName(statueCottonCandor.getRegistryName()),
				new ItemBlock(statueCottonCandorGold).setRegistryName(statueCottonCandorGold.getRegistryName()),
				new ItemBlock(statueCottonCandorOrnate).setRegistryName(statueCottonCandorOrnate.getRegistryName()),
				new ItemBlock(statueCraexxeus).setRegistryName(statueCraexxeus.getRegistryName()),
				new ItemBlock(statueCraexxeusGold).setRegistryName(statueCraexxeusGold.getRegistryName()),
				new ItemBlock(statueCraexxeusOrnate).setRegistryName(statueCraexxeusOrnate.getRegistryName()),
				new ItemBlock(statueCreep).setRegistryName(statueCreep.getRegistryName()),
				new ItemBlock(statueCreepGold).setRegistryName(statueCreepGold.getRegistryName()),
				new ItemBlock(statueCreepOrnate).setRegistryName(statueCreepOrnate.getRegistryName()),
				new ItemBlock(statueCrystocore).setRegistryName(statueCrystocore.getRegistryName()),
				new ItemBlock(statueCrystocoreGold).setRegistryName(statueCrystocoreGold.getRegistryName()),
				new ItemBlock(statueCrystocoreOrnate).setRegistryName(statueCrystocoreOrnate.getRegistryName()),
				new ItemBlock(statueDracyon).setRegistryName(statueDracyon.getRegistryName()),
				new ItemBlock(statueDracyonGold).setRegistryName(statueDracyonGold.getRegistryName()),
				new ItemBlock(statueDracyonOrnate).setRegistryName(statueDracyonOrnate.getRegistryName()),
				new ItemBlock(statueElusive).setRegistryName(statueElusive.getRegistryName()),
				new ItemBlock(statueElusiveGold).setRegistryName(statueElusiveGold.getRegistryName()),
				new ItemBlock(statueElusiveOrnate).setRegistryName(statueElusiveOrnate.getRegistryName()),
				new ItemBlock(statueFlash).setRegistryName(statueFlash.getRegistryName()),
				new ItemBlock(statueFlashGold).setRegistryName(statueFlashGold.getRegistryName()),
				new ItemBlock(statueFlashOrnate).setRegistryName(statueFlashOrnate.getRegistryName()),
				new ItemBlock(statueGoldorth).setRegistryName(statueGoldorth.getRegistryName()),
				new ItemBlock(statueGoldorthGold).setRegistryName(statueGoldorthGold.getRegistryName()),
				new ItemBlock(statueGoldorthOrnate).setRegistryName(statueGoldorthOrnate.getRegistryName()),
				new ItemBlock(statueGraw).setRegistryName(statueGraw.getRegistryName()),
				new ItemBlock(statueGrawGold).setRegistryName(statueGrawGold.getRegistryName()),
				new ItemBlock(statueGrawOrnate).setRegistryName(statueGrawOrnate.getRegistryName()),
				new ItemBlock(statueGuardian).setRegistryName(statueGuardian.getRegistryName()),
				new ItemBlock(statueGuardianGold).setRegistryName(statueGuardianGold.getRegistryName()),
				new ItemBlock(statueGuardianOrnate).setRegistryName(statueGuardianOrnate.getRegistryName()),
				new ItemBlock(statueGyro).setRegistryName(statueGyro.getRegistryName()),
				new ItemBlock(statueGyroGold).setRegistryName(statueGyroGold.getRegistryName()),
				new ItemBlock(statueGyroOrnate).setRegistryName(statueGyroOrnate.getRegistryName()),
				new ItemBlock(statueHarkos).setRegistryName(statueHarkos.getRegistryName()),
				new ItemBlock(statueHarkosGold).setRegistryName(statueHarkosGold.getRegistryName()),
				new ItemBlock(statueHarkosOrnate).setRegistryName(statueHarkosOrnate.getRegistryName()),
				new ItemBlock(statueHiveKing).setRegistryName(statueHiveKing.getRegistryName()),
				new ItemBlock(statueHiveKingGold).setRegistryName(statueHiveKingGold.getRegistryName()),
				new ItemBlock(statueHiveKingOrnate).setRegistryName(statueHiveKingOrnate.getRegistryName()),
				new ItemBlock(statueHoron).setRegistryName(statueHoron.getRegistryName()),
				new ItemBlock(statueHoronGold).setRegistryName(statueHoronGold.getRegistryName()),
				new ItemBlock(statueHoronOrnate).setRegistryName(statueHoronOrnate.getRegistryName()),
				new ItemBlock(statueHydrolisk).setRegistryName(statueHydrolisk.getRegistryName()),
				new ItemBlock(statueHydroliskGold).setRegistryName(statueHydroliskGold.getRegistryName()),
				new ItemBlock(statueHydroliskOrnate).setRegistryName(statueHydroliskOrnate.getRegistryName()),
				new ItemBlock(statueKajaros).setRegistryName(statueKajaros.getRegistryName()),
				new ItemBlock(statueKajarosGold).setRegistryName(statueKajarosGold.getRegistryName()),
				new ItemBlock(statueKajarosOrnate).setRegistryName(statueKajarosOrnate.getRegistryName()),
				new ItemBlock(statueKingBamBamBam).setRegistryName(statueKingBamBamBam.getRegistryName()),
				new ItemBlock(statueKingBamBamBamGold).setRegistryName(statueKingBamBamBamGold.getRegistryName()),
				new ItemBlock(statueKingBamBamBamOrnate).setRegistryName(statueKingBamBamBamOrnate.getRegistryName()),
				new ItemBlock(statueKingShroomus).setRegistryName(statueKingShroomus.getRegistryName()),
				new ItemBlock(statueKingShroomusGold).setRegistryName(statueKingShroomusGold.getRegistryName()),
				new ItemBlock(statueKingShroomusOrnate).setRegistryName(statueKingShroomusOrnate.getRegistryName()),
				new ItemBlock(statueKlobber).setRegistryName(statueKlobber.getRegistryName()),
				new ItemBlock(statueKlobberGold).setRegistryName(statueKlobberGold.getRegistryName()),
				new ItemBlock(statueKlobberOrnate).setRegistryName(statueKlobberOrnate.getRegistryName()),
				new ItemBlock(statueKror).setRegistryName(statueKror.getRegistryName()),
				new ItemBlock(statueKrorGold).setRegistryName(statueKrorGold.getRegistryName()),
				new ItemBlock(statueKrorOrnate).setRegistryName(statueKrorOrnate.getRegistryName()),
				new ItemBlock(statueMechbot).setRegistryName(statueMechbot.getRegistryName()),
				new ItemBlock(statueMechbotGold).setRegistryName(statueMechbotGold.getRegistryName()),
				new ItemBlock(statueMechbotOrnate).setRegistryName(statueMechbotOrnate.getRegistryName()),
				new ItemBlock(statueMirage).setRegistryName(statueMirage.getRegistryName()),
				new ItemBlock(statueMirageGold).setRegistryName(statueMirageGold.getRegistryName()),
				new ItemBlock(statueMirageOrnate).setRegistryName(statueMirageOrnate.getRegistryName()),
				new ItemBlock(statueMiskel).setRegistryName(statueMiskel.getRegistryName()),
				new ItemBlock(statueMiskelGold).setRegistryName(statueMiskelGold.getRegistryName()),
				new ItemBlock(statueMiskelOrnate).setRegistryName(statueMiskelOrnate.getRegistryName()),
				new ItemBlock(statueNethengeicWither).setRegistryName(statueNethengeicWither.getRegistryName()),
				new ItemBlock(statueNethengeicWitherGold).setRegistryName(statueNethengeicWitherGold.getRegistryName()),
				new ItemBlock(statueNethengeicWitherOrnate).setRegistryName(statueNethengeicWitherOrnate.getRegistryName()),
				new ItemBlock(statueOkazor).setRegistryName(statueOkazor.getRegistryName()),
				new ItemBlock(statueOkazorGold).setRegistryName(statueOkazorGold.getRegistryName()),
				new ItemBlock(statueOkazorOrnate).setRegistryName(statueOkazorOrnate.getRegistryName()),
				new ItemBlock(statuePenumbra).setRegistryName(statuePenumbra.getRegistryName()),
				new ItemBlock(statuePenumbraGold).setRegistryName(statuePenumbraGold.getRegistryName()),
				new ItemBlock(statuePenumbraOrnate).setRegistryName(statuePenumbraOrnate.getRegistryName()),
				new ItemBlock(statueProshield).setRegistryName(statueProshield.getRegistryName()),
				new ItemBlock(statueProshieldGold).setRegistryName(statueProshieldGold.getRegistryName()),
				new ItemBlock(statueProshieldOrnate).setRegistryName(statueProshieldOrnate.getRegistryName()),
				new ItemBlock(statueRaxxan).setRegistryName(statueRaxxan.getRegistryName()),
				new ItemBlock(statueRaxxanGold).setRegistryName(statueRaxxanGold.getRegistryName()),
				new ItemBlock(statueRaxxanOrnate).setRegistryName(statueRaxxanOrnate.getRegistryName()),
				new ItemBlock(statueRockrider).setRegistryName(statueRockrider.getRegistryName()),
				new ItemBlock(statueRockriderGold).setRegistryName(statueRockriderGold.getRegistryName()),
				new ItemBlock(statueRockriderOrnate).setRegistryName(statueRockriderOrnate.getRegistryName()),
				new ItemBlock(statueShadowlord).setRegistryName(statueShadowlord.getRegistryName()),
				new ItemBlock(statueShadowlordGold).setRegistryName(statueShadowlordGold.getRegistryName()),
				new ItemBlock(statueShadowlordOrnate).setRegistryName(statueShadowlordOrnate.getRegistryName()),
				new ItemBlock(statueSilverfoot).setRegistryName(statueSilverfoot.getRegistryName()),
				new ItemBlock(statueSilverfootGold).setRegistryName(statueSilverfootGold.getRegistryName()),
				new ItemBlock(statueSilverfootOrnate).setRegistryName(statueSilverfootOrnate.getRegistryName()),
				new ItemBlock(statueSkeletron).setRegistryName(statueSkeletron.getRegistryName()),
				new ItemBlock(statueSkeletronGold).setRegistryName(statueSkeletronGold.getRegistryName()),
				new ItemBlock(statueSkeletronOrnate).setRegistryName(statueSkeletronOrnate.getRegistryName()),
				new ItemBlock(statueSmash).setRegistryName(statueSmash.getRegistryName()),
				new ItemBlock(statueSmashGold).setRegistryName(statueSmashGold.getRegistryName()),
				new ItemBlock(statueSmashOrnate).setRegistryName(statueSmashOrnate.getRegistryName()),
				new ItemBlock(statueTyrosaur).setRegistryName(statueTyrosaur.getRegistryName()),
				new ItemBlock(statueTyrosaurGold).setRegistryName(statueTyrosaurGold.getRegistryName()),
				new ItemBlock(statueTyrosaurOrnate).setRegistryName(statueTyrosaurOrnate.getRegistryName()),
				new ItemBlock(statueVinocorne).setRegistryName(statueVinocorne.getRegistryName()),
				new ItemBlock(statueVinocorneGold).setRegistryName(statueVinocorneGold.getRegistryName()),
				new ItemBlock(statueVinocorneOrnate).setRegistryName(statueVinocorneOrnate.getRegistryName()),
				new ItemBlock(statueVisualent).setRegistryName(statueVisualent.getRegistryName()),
				new ItemBlock(statueVisualentGold).setRegistryName(statueVisualentGold.getRegistryName()),
				new ItemBlock(statueVisualentOrnate).setRegistryName(statueVisualentOrnate.getRegistryName()),
				new ItemBlock(statueVoxxulon).setRegistryName(statueVoxxulon.getRegistryName()),
				new ItemBlock(statueVoxxulonGold).setRegistryName(statueVoxxulonGold.getRegistryName()),
				new ItemBlock(statueVoxxulonOrnate).setRegistryName(statueVoxxulonOrnate.getRegistryName()),
				new ItemBlock(statueXxeus).setRegistryName(statueXxeus.getRegistryName()),
				new ItemBlock(statueXxeusGold).setRegistryName(statueXxeusGold.getRegistryName()),
				new ItemBlock(statueXxeusOrnate).setRegistryName(statueXxeusOrnate.getRegistryName()),
				new ItemBlock(bannerAncient).setRegistryName(bannerAncient.getRegistryName()),
				new ItemBlock(bannerAncientBejewelled).setRegistryName(bannerAncientBejewelled.getRegistryName()),
				new ItemBlock(bannerAncientEncrusted).setRegistryName(bannerAncientEncrusted.getRegistryName()),
				new ItemBlock(bannerAncientGilded).setRegistryName(bannerAncientGilded.getRegistryName()),
				new ItemBlock(bannerBaron).setRegistryName(bannerBaron.getRegistryName()),
				new ItemBlock(bannerBaronBejewelled).setRegistryName(bannerBaronBejewelled.getRegistryName()),
				new ItemBlock(bannerBaronEncrusted).setRegistryName(bannerBaronEncrusted.getRegistryName()),
				new ItemBlock(bannerBaronGilded).setRegistryName(bannerBaronGilded.getRegistryName()),
				new ItemBlock(bannerBlood).setRegistryName(bannerBlood.getRegistryName()),
				new ItemBlock(bannerBloodBejewelled).setRegistryName(bannerBloodBejewelled.getRegistryName()),
				new ItemBlock(bannerBloodEncrusted).setRegistryName(bannerBloodEncrusted.getRegistryName()),
				new ItemBlock(bannerBloodGilded).setRegistryName(bannerBloodGilded.getRegistryName()),
				new ItemBlock(bannerBoreic).setRegistryName(bannerBoreic.getRegistryName()),
				new ItemBlock(bannerBoreicBejewelled).setRegistryName(bannerBoreicBejewelled.getRegistryName()),
				new ItemBlock(bannerBoreicEncrusted).setRegistryName(bannerBoreicEncrusted.getRegistryName()),
				new ItemBlock(bannerBoreicGilded).setRegistryName(bannerBoreicGilded.getRegistryName()),
				new ItemBlock(bannerCandy).setRegistryName(bannerCandy.getRegistryName()),
				new ItemBlock(bannerCandyBejewelled).setRegistryName(bannerCandyBejewelled.getRegistryName()),
				new ItemBlock(bannerCandyEncrusted).setRegistryName(bannerCandyEncrusted.getRegistryName()),
				new ItemBlock(bannerCandyGilded).setRegistryName(bannerCandyGilded.getRegistryName()),
				new ItemBlock(bannerClown).setRegistryName(bannerClown.getRegistryName()),
				new ItemBlock(bannerClownBejewelled).setRegistryName(bannerClownBejewelled.getRegistryName()),
				new ItemBlock(bannerClownEncrusted).setRegistryName(bannerClownEncrusted.getRegistryName()),
				new ItemBlock(bannerClownGilded).setRegistryName(bannerClownGilded.getRegistryName()),
				new ItemBlock(bannerCreation).setRegistryName(bannerCreation.getRegistryName()),
				new ItemBlock(bannerCreationBejewelled).setRegistryName(bannerCreationBejewelled.getRegistryName()),
				new ItemBlock(bannerCreationEncrusted).setRegistryName(bannerCreationEncrusted.getRegistryName()),
				new ItemBlock(bannerCreationGilded).setRegistryName(bannerCreationGilded.getRegistryName()),
				new ItemBlock(bannerCreepoid).setRegistryName(bannerCreepoid.getRegistryName()),
				new ItemBlock(bannerCreepoidBejewelled).setRegistryName(bannerCreepoidBejewelled.getRegistryName()),
				new ItemBlock(bannerCreepoidEncrusted).setRegistryName(bannerCreepoidEncrusted.getRegistryName()),
				new ItemBlock(bannerCreepoidGilded).setRegistryName(bannerCreepoidGilded.getRegistryName()),
				new ItemBlock(bannerCreepy).setRegistryName(bannerCreepy.getRegistryName()),
				new ItemBlock(bannerCreepyBejewelled).setRegistryName(bannerCreepyBejewelled.getRegistryName()),
				new ItemBlock(bannerCreepyEncrusted).setRegistryName(bannerCreepyEncrusted.getRegistryName()),
				new ItemBlock(bannerCreepyGilded).setRegistryName(bannerCreepyGilded.getRegistryName()),
				new ItemBlock(bannerCrystal).setRegistryName(bannerCrystal.getRegistryName()),
				new ItemBlock(bannerCrystalBejewelled).setRegistryName(bannerCrystalBejewelled.getRegistryName()),
				new ItemBlock(bannerCrystalEncrusted).setRegistryName(bannerCrystalEncrusted.getRegistryName()),
				new ItemBlock(bannerCrystalGilded).setRegistryName(bannerCrystalGilded.getRegistryName()),
				new ItemBlock(bannerDeep).setRegistryName(bannerDeep.getRegistryName()),
				new ItemBlock(bannerDeepBejewelled).setRegistryName(bannerDeepBejewelled.getRegistryName()),
				new ItemBlock(bannerDeepEncrusted).setRegistryName(bannerDeepEncrusted.getRegistryName()),
				new ItemBlock(bannerDeepGilded).setRegistryName(bannerDeepGilded.getRegistryName()),
				new ItemBlock(bannerDustopian).setRegistryName(bannerDustopian.getRegistryName()),
				new ItemBlock(bannerDustopianBejewelled).setRegistryName(bannerDustopianBejewelled.getRegistryName()),
				new ItemBlock(bannerDustopianEncrusted).setRegistryName(bannerDustopianEncrusted.getRegistryName()),
				new ItemBlock(bannerDustopianGilded).setRegistryName(bannerDustopianGilded.getRegistryName()),
				new ItemBlock(bannerEnergy).setRegistryName(bannerEnergy.getRegistryName()),
				new ItemBlock(bannerEnergyBejewelled).setRegistryName(bannerEnergyBejewelled.getRegistryName()),
				new ItemBlock(bannerEnergyEncrusted).setRegistryName(bannerEnergyEncrusted.getRegistryName()),
				new ItemBlock(bannerEnergyGilded).setRegistryName(bannerEnergyGilded.getRegistryName()),
				new ItemBlock(bannerErebon).setRegistryName(bannerErebon.getRegistryName()),
				new ItemBlock(bannerFragment).setRegistryName(bannerFragment.getRegistryName()),
				new ItemBlock(bannerFragmentBejewelled).setRegistryName(bannerFragmentBejewelled.getRegistryName()),
				new ItemBlock(bannerFragmentEncrusted).setRegistryName(bannerFragmentEncrusted.getRegistryName()),
				new ItemBlock(bannerFragmentGilded).setRegistryName(bannerFragmentGilded.getRegistryName()),
				new ItemBlock(bannerFungal).setRegistryName(bannerFungal.getRegistryName()),
				new ItemBlock(bannerFungalBejewelled).setRegistryName(bannerFungalBejewelled.getRegistryName()),
				new ItemBlock(bannerFungalEncrusted).setRegistryName(bannerFungalEncrusted.getRegistryName()),
				new ItemBlock(bannerFungalGilded).setRegistryName(bannerFungalGilded.getRegistryName()),
				new ItemBlock(bannerGhostly).setRegistryName(bannerGhostly.getRegistryName()),
				new ItemBlock(bannerGhostlyBejewelled).setRegistryName(bannerGhostlyBejewelled.getRegistryName()),
				new ItemBlock(bannerGhostlyEncrusted).setRegistryName(bannerGhostlyEncrusted.getRegistryName()),
				new ItemBlock(bannerGhostlyGilded).setRegistryName(bannerGhostlyGilded.getRegistryName()),
				new ItemBlock(bannerGhoul).setRegistryName(bannerGhoul.getRegistryName()),
				new ItemBlock(bannerGhoulBejewelled).setRegistryName(bannerGhoulBejewelled.getRegistryName()),
				new ItemBlock(bannerGhoulEncrusted).setRegistryName(bannerGhoulEncrusted.getRegistryName()),
				new ItemBlock(bannerGhoulGilded).setRegistryName(bannerGhoulGilded.getRegistryName()),
				new ItemBlock(bannerGingerbread).setRegistryName(bannerGingerbread.getRegistryName()),
				new ItemBlock(bannerGingerbreadBejewelled).setRegistryName(bannerGingerbreadBejewelled.getRegistryName()),
				new ItemBlock(bannerGingerbreadEncrusted).setRegistryName(bannerGingerbreadEncrusted.getRegistryName()),
				new ItemBlock(bannerGingerbreadGilded).setRegistryName(bannerGingerbreadGilded.getRegistryName()),
				new ItemBlock(bannerHaunted).setRegistryName(bannerHaunted.getRegistryName()),
				new ItemBlock(bannerHauntedBejewelled).setRegistryName(bannerHauntedBejewelled.getRegistryName()),
				new ItemBlock(bannerHauntedEncrusted).setRegistryName(bannerHauntedEncrusted.getRegistryName()),
				new ItemBlock(bannerHauntedGilded).setRegistryName(bannerHauntedGilded.getRegistryName()),
				new ItemBlock(bannerIllusion).setRegistryName(bannerIllusion.getRegistryName()),
				new ItemBlock(bannerIllusionBejewelled).setRegistryName(bannerIllusionBejewelled.getRegistryName()),
				new ItemBlock(bannerIllusionEncrusted).setRegistryName(bannerIllusionEncrusted.getRegistryName()),
				new ItemBlock(bannerIllusionGilded).setRegistryName(bannerIllusionGilded.getRegistryName()),
				new ItemBlock(bannerImmortal).setRegistryName(bannerImmortal.getRegistryName()),
				new ItemBlock(bannerImmortalBejewelled).setRegistryName(bannerImmortalBejewelled.getRegistryName()),
				new ItemBlock(bannerImmortalEncrusted).setRegistryName(bannerImmortalEncrusted.getRegistryName()),
				new ItemBlock(bannerImmortalGilded).setRegistryName(bannerImmortalGilded.getRegistryName()),
				new ItemBlock(bannerLelyetian).setRegistryName(bannerLelyetian.getRegistryName()),
				new ItemBlock(bannerLelyetianBejewelled).setRegistryName(bannerLelyetianBejewelled.getRegistryName()),
				new ItemBlock(bannerLelyetianEncrusted).setRegistryName(bannerLelyetianEncrusted.getRegistryName()),
				new ItemBlock(bannerLelyetianGilded).setRegistryName(bannerLelyetianGilded.getRegistryName()),
				new ItemBlock(bannerLight).setRegistryName(bannerLight.getRegistryName()),
				new ItemBlock(bannerLightBejewelled).setRegistryName(bannerLightBejewelled.getRegistryName()),
				new ItemBlock(bannerLightEncrusted).setRegistryName(bannerLightEncrusted.getRegistryName()),
				new ItemBlock(bannerLightGilded).setRegistryName(bannerLightGilded.getRegistryName()),
				new ItemBlock(bannerLotto).setRegistryName(bannerLotto.getRegistryName()),
				new ItemBlock(bannerLottoBejewelled).setRegistryName(bannerLottoBejewelled.getRegistryName()),
				new ItemBlock(bannerLottoEncrusted).setRegistryName(bannerLottoEncrusted.getRegistryName()),
				new ItemBlock(bannerLottoGilded).setRegistryName(bannerLottoGilded.getRegistryName()),
				new ItemBlock(bannerLunar).setRegistryName(bannerLunar.getRegistryName()),
				new ItemBlock(bannerLunarBejewelled).setRegistryName(bannerLunarBejewelled.getRegistryName()),
				new ItemBlock(bannerLunarEncrusted).setRegistryName(bannerLunarEncrusted.getRegistryName()),
				new ItemBlock(bannerLunarGilded).setRegistryName(bannerLunarGilded.getRegistryName()),
				new ItemBlock(bannerLuxon).setRegistryName(bannerLuxon.getRegistryName()),
				new ItemBlock(bannerMecha).setRegistryName(bannerMecha.getRegistryName()),
				new ItemBlock(bannerMechaBejewelled).setRegistryName(bannerMechaBejewelled.getRegistryName()),
				new ItemBlock(bannerMechaEncrusted).setRegistryName(bannerMechaEncrusted.getRegistryName()),
				new ItemBlock(bannerMechaGilded).setRegistryName(bannerMechaGilded.getRegistryName()),
				new ItemBlock(bannerNethengeic).setRegistryName(bannerNethengeic.getRegistryName()),
				new ItemBlock(bannerNethengeicBejewelled).setRegistryName(bannerNethengeicBejewelled.getRegistryName()),
				new ItemBlock(bannerNethengeicEncrusted).setRegistryName(bannerNethengeicEncrusted.getRegistryName()),
				new ItemBlock(bannerNethengeicGilded).setRegistryName(bannerNethengeicGilded.getRegistryName()),
				new ItemBlock(bannerNether).setRegistryName(bannerNether.getRegistryName()),
				new ItemBlock(bannerNetherBejewelled).setRegistryName(bannerNetherBejewelled.getRegistryName()),
				new ItemBlock(bannerNetherEncrusted).setRegistryName(bannerNetherEncrusted.getRegistryName()),
				new ItemBlock(bannerNetherGilded).setRegistryName(bannerNetherGilded.getRegistryName()),
				new ItemBlock(bannerPluton).setRegistryName(bannerPluton.getRegistryName()),
				new ItemBlock(bannerRosidian).setRegistryName(bannerRosidian.getRegistryName()),
				new ItemBlock(bannerRosidianBejewelled).setRegistryName(bannerRosidianBejewelled.getRegistryName()),
				new ItemBlock(bannerRosidianEncrusted).setRegistryName(bannerRosidianEncrusted.getRegistryName()),
				new ItemBlock(bannerRosidianGilded).setRegistryName(bannerRosidianGilded.getRegistryName()),
				new ItemBlock(bannerRunic).setRegistryName(bannerRunic.getRegistryName()),
				new ItemBlock(bannerRunicBejewelled).setRegistryName(bannerRunicBejewelled.getRegistryName()),
				new ItemBlock(bannerRunicEncrusted).setRegistryName(bannerRunicEncrusted.getRegistryName()),
				new ItemBlock(bannerRunicGilded).setRegistryName(bannerRunicGilded.getRegistryName()),
				new ItemBlock(bannerSea).setRegistryName(bannerSea.getRegistryName()),
				new ItemBlock(bannerSeaBejewelled).setRegistryName(bannerSeaBejewelled.getRegistryName()),
				new ItemBlock(bannerSeaEncrusted).setRegistryName(bannerSeaEncrusted.getRegistryName()),
				new ItemBlock(bannerSeaGilded).setRegistryName(bannerSeaGilded.getRegistryName()),
				new ItemBlock(bannerSelyan).setRegistryName(bannerSelyan.getRegistryName()),
				new ItemBlock(bannerShadow).setRegistryName(bannerShadow.getRegistryName()),
				new ItemBlock(bannerShadowBejewelled).setRegistryName(bannerShadowBejewelled.getRegistryName()),
				new ItemBlock(bannerShadowEncrusted).setRegistryName(bannerShadowEncrusted.getRegistryName()),
				new ItemBlock(bannerShadowGilded).setRegistryName(bannerShadowGilded.getRegistryName()),
				new ItemBlock(bannerShiny).setRegistryName(bannerShiny.getRegistryName()),
				new ItemBlock(bannerShinyBejewelled).setRegistryName(bannerShinyBejewelled.getRegistryName()),
				new ItemBlock(bannerShinyEncrusted).setRegistryName(bannerShinyEncrusted.getRegistryName()),
				new ItemBlock(bannerShinyGilded).setRegistryName(bannerShinyGilded.getRegistryName()),
				new ItemBlock(bannerShyre).setRegistryName(bannerShyre.getRegistryName()),
				new ItemBlock(bannerShyreBejewelled).setRegistryName(bannerShyreBejewelled.getRegistryName()),
				new ItemBlock(bannerShyreEncrusted).setRegistryName(bannerShyreEncrusted.getRegistryName()),
				new ItemBlock(bannerShyreGilded).setRegistryName(bannerShyreGilded.getRegistryName()),
				new ItemBlock(bannerSkeletal).setRegistryName(bannerSkeletal.getRegistryName()),
				new ItemBlock(bannerSkeletalBejewelled).setRegistryName(bannerSkeletalBejewelled.getRegistryName()),
				new ItemBlock(bannerSkeletalEncrusted).setRegistryName(bannerSkeletalEncrusted.getRegistryName()),
				new ItemBlock(bannerSkeletalGilded).setRegistryName(bannerSkeletalGilded.getRegistryName()),
				new ItemBlock(bannerSoul).setRegistryName(bannerSoul.getRegistryName()),
				new ItemBlock(bannerSoulBejewelled).setRegistryName(bannerSoulBejewelled.getRegistryName()),
				new ItemBlock(bannerSoulEncrusted).setRegistryName(bannerSoulEncrusted.getRegistryName()),
				new ItemBlock(bannerSoulGilded).setRegistryName(bannerSoulGilded.getRegistryName()),
				new ItemBlock(bannerUtopian).setRegistryName(bannerUtopian.getRegistryName()),
				new ItemBlock(bannerUtopianBejewelled).setRegistryName(bannerUtopianBejewelled.getRegistryName()),
				new ItemBlock(bannerUtopianEncrusted).setRegistryName(bannerUtopianEncrusted.getRegistryName()),
				new ItemBlock(bannerUtopianGilded).setRegistryName(bannerUtopianGilded.getRegistryName()),
				new ItemBlock(bannerVoid).setRegistryName(bannerVoid.getRegistryName()),
				new ItemBlock(bannerVoidBejewelled).setRegistryName(bannerVoidBejewelled.getRegistryName()),
				new ItemBlock(bannerVoidEncrusted).setRegistryName(bannerVoidEncrusted.getRegistryName()),
				new ItemBlock(bannerVoidGilded).setRegistryName(bannerVoidGilded.getRegistryName()),
				new ItemBlock(bannerVox).setRegistryName(bannerVox.getRegistryName()),
				new ItemBlock(bannerVoxBejewelled).setRegistryName(bannerVoxBejewelled.getRegistryName()),
				new ItemBlock(bannerVoxEncrusted).setRegistryName(bannerVoxEncrusted.getRegistryName()),
				new ItemBlock(bannerVoxGilded).setRegistryName(bannerVoxGilded.getRegistryName())
		);
	}

	@SubscribeEvent
	public static void remapMissing(final RegistryEvent.MissingMappings<Block> ev) {
		for (RegistryEvent.MissingMappings.Mapping<Block> map : ev.getAllMappings()) {
			if (map.key.equals(new ResourceLocation("aoa3:blue_crystal_ore"))) {
				map.remap(oreBlueGemstone);
			}
			else if (map.key.equals(new ResourceLocation("aoa3:green_crystal_ore"))) {
				map.remap(oreGreenGemstone);
			}
			else if (map.key.equals(new ResourceLocation("aoa3:red_crystal_ore"))) {
				map.remap(oreRedGemstone);
			}
			else if (map.key.equals(new ResourceLocation("aoa3:purple_crystal_ore"))) {
				map.remap(orePurpleGemstone);
			}
			else if (map.key.equals(new ResourceLocation("aoa3:white_crystal_ore"))) {
				map.remap(oreWhiteGemstone);
			}
			else if (map.key.equals(new ResourceLocation("aoa3:yellow_crystal_ore"))) {
				map.remap(oreWhiteGemstone);
			}
		}
	}

	@SubscribeEvent
	public static void registerItemBlockRenders(final ModelRegistryEvent ev) {
		registerRender(Item.getItemFromBlock(stoneAbyss), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneBarathos), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneBaron), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneBorean), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneCreep), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneCrystevia), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneDeeplands), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneDustopia), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneGardencia), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneGreckon), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneHaven), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneIromine), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneLelyetia), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneMysterium), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stonePrecasiaHigh), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stonePrecasiaLow), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stonePrimed), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneRunic), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneShyrelands), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneToxic), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(stoneUnstable), "blocks/generation/stone/");
		registerRender(Item.getItemFromBlock(dirtBorean), "blocks/generation/dirt/");
		registerRender(Item.getItemFromBlock(dirtCandyland), "blocks/generation/dirt/");
		registerRender(Item.getItemFromBlock(dirtCeleve), "blocks/generation/dirt/");
		registerRender(Item.getItemFromBlock(dirtCreeponia), "blocks/generation/dirt/");
		registerRender(Item.getItemFromBlock(dirtDustopia), "blocks/generation/dirt/");
		registerRender(Item.getItemFromBlock(dirtGardencia), "blocks/generation/dirt/");
		registerRender(Item.getItemFromBlock(dirtGreckon), "blocks/generation/dirt/");
		registerRender(Item.getItemFromBlock(dirtHaven), "blocks/generation/dirt/");
		registerRender(Item.getItemFromBlock(dirtLunalyte), "blocks/generation/dirt/");
		registerRender(Item.getItemFromBlock(dirtLunasole), "blocks/generation/dirt/");
		registerRender(Item.getItemFromBlock(dirtMysterium), "blocks/generation/dirt/");
		registerRender(Item.getItemFromBlock(dirtToxic), "blocks/generation/dirt/");
		registerRender(Item.getItemFromBlock(grassAbyss), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassBorean), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassCandyland), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassCeleve), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassChocolate), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassCreeponia), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassCrystal), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassDustopia), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassGardencia), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassGreckon), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassHaven), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassIromine), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassLelyetia), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassLelyetiaDown), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassLunalyte), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassLunasole), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassMarshmallow), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassMysterium), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassPrecasia), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassRunic), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassShyrelands), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassSilvro), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(grassToxic), "blocks/generation/grass/");
		registerRender(Item.getItemFromBlock(oreAmethyst), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreBaronyte), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreBlazium), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreBloodstone), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreBlueGemstone), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreChargedRunium), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreChestboneFragments), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreCrystallite), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreElecanium), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreEmberstone), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreFootboneFragments), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreGemenyte), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreGhastly), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreGhoulish), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreGreenGemstone), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreJade), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreJewelyte), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreLegboneFragments), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreLimonite), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreLyon), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreMystite), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreOrnamyte), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(orePurpleGemstone), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreRedGemstone), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreRosite), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreRunium), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreSapphire), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreShyregem), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreShyrestone), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreSkullboneFragments), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreVarsium), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreWhiteGemstone), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(oreYellowGemstone), "blocks/generation/ores/");
		registerRender(Item.getItemFromBlock(bricksBaron), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksBlack), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksBloodstone), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksBlue), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksBrown), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksCoral), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksCreeponia), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksCrystallite), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksCrystevia), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksCyan), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksDark), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksDarkBlue), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksDarkGrey), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksDarkwash), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksGardencia), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksGreckon), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksGreen), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksGrey), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksHaunted), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksIroDotted), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksIroStriped), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksLelyetia), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksLime), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksLunar), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksMagenta), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksMysteriumBlack), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksMysteriumGreen), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksOrange), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksPink), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksPurple), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksRed), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksRosidian), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksRunicConstruct), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksShyreWhite), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksShyreYellow), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksSkeletal), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksWhite), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksWhitewash), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(bricksYellow), "blocks/decoration/bricks/");
		registerRender(Item.getItemFromBlock(ivoryAmethystIntricate), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryAmethystNatural), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryAmethystOrnate), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryAmethystPatterned), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryIntricate), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryJadeIntricate), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryJadeNatural), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryJadeOrnate), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryJadePatterned), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryLimoniteIntricate), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryLimoniteNatural), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryLimoniteOrnate), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryLimonitePatterned), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryNatural), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryOrnate), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryPatterned), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryRositeIntricate), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryRositeNatural), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryRositeOrnate), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivoryRositePatterned), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivorySapphireIntricate), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivorySapphireNatural), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivorySapphireOrnate), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(ivorySapphirePatterned), "blocks/decoration/ivory/");
		registerRender(Item.getItemFromBlock(leavesAchony), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesBlood), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesBubble), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesCelevusBlue), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesCelevusGreen), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesCelevusPurple), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesCelevusRed), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesCelevusWhite), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesCelevusYellow), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesChurry), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesCreep), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesCycade), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesDawn), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesDomiguous), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesEternal), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesEucadon), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesHaunted), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesHauntedEyes), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesHavenBlue), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesHavenPink), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesHavenPurple), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesHavenRed), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesHavenTurquoise), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesHavenYellow), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesIrodust), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesIrogold), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesLelyetian), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesLucalus), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesLunicia), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesLunosso), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesMelumia), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesOpollo), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesRunic), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesShadow), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesShadowblood), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesShyre), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesShyreBright), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesSilvro), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesStranglewood), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(leavesVein), "blocks/generation/leaves/");
		registerRender(Item.getItemFromBlock(logAchony), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logBlood), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logChurry), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logCreep), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logCycade), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logDawn), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logDomiguous), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logEternal), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logEucadon), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logEyeball), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logHaunted), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logHauntedEye), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logHauntedEyes), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logHauntedFlashing), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logHauntedPurpling), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logIro), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logLucalus), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logLunide), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logMelumia), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logOpollo), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logRunic), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logShadow), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logShyre), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logStranglewood), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(logToxic), "blocks/generation/wood/");
		registerRender(Item.getItemFromBlock(planksAchony), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksBloodwood), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksChurry), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksCreep), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksCycade), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksDawnwood), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksDomiguous), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksEucadon), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksHauntedwood), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksIrowood), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksLucalus), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksLunide), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksMelumia), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksOpollo), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksRunic), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksShadow), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksShyre), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksStranglewood), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(planksToxicwood), "blocks/decoration/planks/");
		registerRender(Item.getItemFromBlock(slabAchony.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabBloodwood.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabChurry.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabCreep.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabCycade.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabDawnwood.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabDomiguous.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabEucadon.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabHauntedwood.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIrowood.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabLucalus.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabLunide.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabMelumia.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabOpollo.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabRunic.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabShadow.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabShyre.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabStranglewood.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabToxicwood.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabBaronBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabBlackBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabBloodstoneBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabBlueBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabBrownBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabCoralBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabCreeponiaBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabCrystalliteBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabCrysteviaBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabCyanBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabDarkBlueBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabDarkBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabDarkGreyBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabDarkwashBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabGardenciaBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabGreckonBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabGreenBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabGreyBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabHauntedBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIroDottedBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIroStripedBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryAmethystIntricate.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryAmethystNatural.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryAmethystOrnate.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryAmethystPatterned.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryIntricate.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryJadeIntricate.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryJadeNatural.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryJadeOrnate.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryJadePatterned.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryLimoniteIntricate.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryLimoniteNatural.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryLimoniteOrnate.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryLimonitePatterned.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryNatural.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryOrnate.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryPatterned.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryRositeIntricate.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryRositeNatural.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryRositeOrnate.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvoryRositePatterned.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvorySapphireIntricate.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvorySapphireNatural.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvorySapphireOrnate.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabIvorySapphirePatterned.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabLelyetiaBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabLimeBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabLunarBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabMagentaBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabMysteriumBlackBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabMysteriumGreenBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabOrangeBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabPinkBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabPurpleBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabRedBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabRosidianBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabRunicConstructBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabShyreWhiteBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabShyreYellowBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabSkeletalBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabWhitewashBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(slabYellowBricks.getHalfBlock()), "blocks/decoration/slabs/");
		registerRender(Item.getItemFromBlock(stairsAchony), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsBloodwood), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsChurry), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsCreep), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsCycade), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsDawnwood), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsDomiguous), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsEucadon), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsHauntedwood), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIrowood), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsLucalus), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsLunide), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsMelumia), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsOpollo), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsRunic), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsShadow), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsShyre), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsStranglewood), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsToxicwood), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsBaronBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsBlackBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsBloodstoneBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsBlueBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsBrownBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsCoralBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsCreeponiaBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsCrystalliteBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsCrysteviaBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsCyanBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsDarkBlueBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsDarkBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsDarkGreyBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsDarkwashBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsGardenciaBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsGreckonBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsGreenBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsGreyBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsHauntedBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIroDottedBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIroStripedBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryAmethystIntricate), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryAmethystNatural), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryAmethystOrnate), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryAmethystPatterned), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryIntricate), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryJadeIntricate), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryJadeNatural), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryJadeOrnate), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryJadePatterned), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryLimoniteIntricate), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryLimoniteNatural), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryLimoniteOrnate), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryLimonitePatterned), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryNatural), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryOrnate), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryPatterned), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryRositeIntricate), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryRositeNatural), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryRositeOrnate), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvoryRositePatterned), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvorySapphireIntricate), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvorySapphireNatural), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvorySapphireOrnate), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsIvorySapphirePatterned), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsLelyetiaBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsLimeBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsLunarBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsMagentaBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsMysteriumBlackBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsMysteriumGreenBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsOrangeBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsPinkBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsPurpleBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsRedBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsRosidianBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsRunicConstructBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsShyreWhiteBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsShyreYellowBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsSkeletalBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsWhitewashBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(stairsYellowBricks), "blocks/decoration/stairs/");
		registerRender(Item.getItemFromBlock(fenceAchony), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceBloodwood), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceChurry), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceCreep), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceCycade), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceDawnwood), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceDomiguous), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceEucadon), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceHauntedwood), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceIrowood), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceLucalus), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceLunide), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceMelumia), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceOpollo), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceRunic), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceShadow), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceShyre), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceStranglewood), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceToxicwood), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(fenceTwinklestone), "blocks/decoration/fences/");
		registerRender(Item.getItemFromBlock(gateAchony), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateBloodwood), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateChurry), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateCreep), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateCycade), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateDawnwood), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateDomiguous), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateEucadon), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateHauntedwood), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateIrowood), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateLucalus), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateLunide), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateMelumia), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateOpollo), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateRunic), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateShadow), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateShyre), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateStranglewood), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(gateToxicwood), "blocks/decoration/gates/");
		registerRender(Item.getItemFromBlock(flowerCore), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomAquaInside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomAquaOutside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomBlack), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomBlueInside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomBlueOutside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomGreenInside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomGreenOutside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomOrangeInside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomOrangeOutside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomPeachInside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomPeachOutside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomPurpleInside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomPurpleOutside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomStemBlack), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomStemBlue), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomStemGreen), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomStemOrange), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomStemPurple), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomStemYellow), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomYellowInside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(mushroomYellowOutside), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(plantStem), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(lightAncient), "blocks/functional/lights/");
		registerRender(Item.getItemFromBlock(lightArchaic), "blocks/functional/lights/");
		registerRender(Item.getItemFromBlock(lightCreepCrystal), "blocks/functional/lights/");
		registerRender(Item.getItemFromBlock(lightDarkstone), "blocks/functional/lights/");
		registerRender(Item.getItemFromBlock(lightDeepCrystal), "blocks/functional/lights/");
		registerRender(Item.getItemFromBlock(lightHiveLight), "blocks/functional/lights/");
		registerRender(Item.getItemFromBlock(lightLabDonut), "blocks/functional/lights/");
		registerRender(Item.getItemFromBlock(lightSteel), "blocks/functional/lights/");
		registerRender(Item.getItemFromBlock(lightTwinklestone), "blocks/functional/lights/");
		registerRender(Item.getItemFromBlock(lightVox), "blocks/functional/lights/");
		registerRender(Item.getItemFromBlock(lampAmethyst), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampAmethyst.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampAquatic), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampAquatic.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampBaronyte), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampBaronyte.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampBlazium), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampBlazium.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampBloodstone), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampBloodstone.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampCrystallite), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampCrystallite.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampElecanium), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampElecanium.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampEmberstone), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampEmberstone.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampFire), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampFire.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampGhastly), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampGhastly.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampGhoulish), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampGhoulish.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeAqua), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeAqua.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeBlack), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeBlack.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeBrown), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeBrown.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeCyan), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeCyan.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeBlue), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeBlue.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeGreen), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeGreen.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampHeatDarkGrey), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampHeatDarkGrey.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeRed), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeRed.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeGrey), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeGrey.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeLime), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeLime.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeMagenta), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeMagenta.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeOrange), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeOrange.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifePink), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifePink.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifePurple), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifePurple.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeWhite), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeWhite.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeYellow), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLifeYellow.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIro), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIro.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIvory), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIvory.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIvoryAmethyst), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIvoryAmethyst.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIvoryJade), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIvoryJade.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIvoryLimonite), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIvoryLimonite.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIvoryRosite), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIvoryRosite.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIvorySapphire), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampIvorySapphire.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampJade), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampJade.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLimonite), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLimonite.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLunar), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLunar.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLyon), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampLyon.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampMystic), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampMystic.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeon), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeon.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeonCircling), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeonCircling.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeonLapis), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeonLapis.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeonLapisCircling), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeonLapisCircling.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeonLapisTriangles), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeonLapisTriangles.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeonTriangles), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeonTriangles.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeonRunic), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampNeonRunic.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampRosite), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampRosite.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampSapphire), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampSapphire.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampSkeletal), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(lampSkeletal.getOffLamp()), "blocks/functional/lamps/");
		registerRender(Item.getItemFromBlock(glassAbyssal), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassAncient), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassAquatic), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassArchaic), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassBaron), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassDecayed), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassGardencian), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassHaven), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassIro), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassLabBasic), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassLabSquares), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassLelyetian), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassLunar), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassRunic), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassRunicUnbreakable), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassShyre), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassVox), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(glassZhinx), "blocks/decoration/glass/");
		registerRender(Item.getItemFromBlock(sandPrecasian), "blocks/generation/sand/");
		registerRender(Item.getItemFromBlock(amethystBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(amethystBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(baronyteBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(blaziumBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(bloodstoneBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(crystalliteBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(elecaniumBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(emberstoneBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(gemenyteBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(ghastlyBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(ghoulishBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(jadeBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(jewelyteBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(limoniteBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(lunarBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(lyonBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(mystiteBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(ornamyteBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(rositeBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(sapphireBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(shyregemBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(shyrestoneBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(skeletalIngotBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(varsiumBlock), "blocks/decoration/compressedblock/");
		registerRender(Item.getItemFromBlock(carpetBaron), "blocks/decoration/carpets/");
		registerRender(Item.getItemFromBlock(carpetBorean), "blocks/decoration/carpets/");
		registerRender(Item.getItemFromBlock(carpetGardencian), "blocks/decoration/carpets/");
		registerRender(Item.getItemFromBlock(carpetIro), "blocks/decoration/carpets/");
		registerRender(Item.getItemFromBlock(carpetLunar), "blocks/decoration/carpets/");
		registerRender(Item.getItemFromBlock(crystallanium), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(emberium), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(shadonantium), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(skeletanium), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(silvroBox), "blocks/decoration/misc/");
		registerRender(Item.getItemFromBlock(crate), "blocks/decoration/misc/");
		registerRender(Item.getItemFromBlock(ancientCactus), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(ancientRock), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(ancientTileBlack), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(ancientTileCore), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(ancientTileGreen), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(ancientTileShrine), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(ancientTileWhite), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(archaicDirt), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(archaicHorizontalStream), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(archaicLadder), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(archaicRectangles), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(archaicSquares), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(archaicTiles), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(archaicVerticalStream), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(baronCastleWall), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(baronCube), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(baronGround), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(bloodstoneBarBricks), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(bloodstoneBars), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(boneyBlock), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(candyGreen), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(candyRed), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(candyWhite), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(celeveStem), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(chargingTable), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(carvedRuneDirection), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(carvedRuneEmpowering), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(carvedRuneFocus), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(carvedRunePower), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(carvedRuneReality), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(carvedRuneSpace), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(carvedRuneTravel), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(chocolateBlock), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(chocolateBlockDark), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(chocolateBlockWhite), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(cogBlock), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(coralBlue), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(coralGreen), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(coralHard), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(coralOrange), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(coralPink), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(coralWhite), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(coralYellow), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(cottonCandyAqua), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(cottonCandyPink), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(crystalBlue), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(crystalGreen), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(crystalPurple), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(crystalRed), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(crystalWhite), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(crystalYellow), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(darkFaceBrick), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(deeplandsTrapExplosion), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(deeplandsTrapLava), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(deeplandsTrapNipper), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(deepshine), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(degradedSteel), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(dustopianLamp), "blocks/generation/special/");
		registerRender(Item.getItemFromBlock(dustopianLampOff), "blocks/generation/special/");
		registerRender(Item.getItemFromBlock(enhancerDamage), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(enhancerDurability), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(enhancerMagical), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(enhancerResistance), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(enhancerSpeed), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(enhancerWeight), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(eyeBlock), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(giantSnailAcid), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(gingerbread), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(hauntedOrb), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(hiveWall), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(iroBrickTrap), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(iroBox), "blocks/decoration/misc/");
		registerRender(Item.getItemFromBlock(iropole), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(kaiyuTempleBlockFace), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(kaiyuTempleBlockFlow), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(kaiyuTempleBlockMaze), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(kaiyuTempleBlockPass), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(kaiyuTempleBlockPlain), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(kaiyuTempleBlockSquares), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(kaiyuTempleBlockTrack), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(kaiyuTempleTrapFlow), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(kaiyuTempleTrapMaze), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(kaiyuTempleTrapPass), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(kaiyuTempleTrapSquares), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(licorice), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(lunarOrbDarklight), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(lunarOrbDusk), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(lunarOrbLunar), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(lunarOrbMoonlight), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(lunarOrbSunfire), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(lunarPad), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(lunarPillar), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(marshmallow), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(orangeAcid), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(paraviteHive), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(petalsBlack), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(petalsBlue), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(petalsLightBlue), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(petalsMagenta), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(petalsOrange), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(petalsPurple), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(petalsRed), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(petalsRose), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(petalsYellow), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(plastic), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(rockCandyBlue), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(rockCandyGreen), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(rockCandyPink), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(rockCandyPurple), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostCompass), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostDistortion), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostEnergy), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostFire), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostKinetic), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostLife), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostLunar), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostPoison), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostPower), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostStorm), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostStrike), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostWater), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostWind), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(runePostWither), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(shyreCloud), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(skeletalBlock), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(skullBlock), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(skullBlockDark), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(spikeyPillar), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(tentacles), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(tentaclesDotsLeft), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(tentaclesDotsRight), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(tentaclesEyeOrange), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(tentaclesEyeRed), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(tentaclesGreen), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(toxicBlock), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(toxicStem), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(toxicWaste), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(unbreakableIroBricks), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(unbreakablePlantStem), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(unbreakableRunicBricks), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(shroomBlue), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(shroomGreen), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(shroomOrange), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(shroomPurple), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(shroomVox), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(shroomYellow), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(voxLog1), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(voxLog2), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(shroomStem), "blocks/generation/misc/");
		registerRender(Item.getItemFromBlock(spawnerAmphibior), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerAmphibiyte), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerAngelica), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerArcWizard), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerArkzyne), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerArocknid), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerBanshee), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerBaumba), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerBloodsucker), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerCaneBug), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerCrusilisk), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerDawnlight), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerDaysee), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerDiocus), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerEnforcer), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerExohead), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerFacelessFloater), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerFenix), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerFleshEater), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerFlowerface), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerFungock), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerGhastus), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerGingerbird), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerGingerbreadMan), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerGoldum), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerGoldus), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerInmateX), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerInmateY), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerIosaur), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerJawe), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerKaiyu), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerLightwalker), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerLuxocron), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerMechyon), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerMerkyre), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerMermage), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerMushroomSpider), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerNethengeicBeast), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerNightmareSpider), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerNightwing), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerOpteryx), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerParavite), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerPhantom), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerPodPlant), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerRawbone), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerReaver), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerRefluct), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerRockCritter), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerRunicGolem), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerRunicGuardian), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerSeeker), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerShavo), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerShyreTroll), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerSkeledon), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerSkelekyte), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerSoulscorne), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerSpectralWizard), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerSpinoledon), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerSurveyor), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerTharafly), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerUndeadTroll), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerUrioh), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerUrv), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerVineWizard), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerVisage), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerVolar), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerZarg), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerZhinx), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(spawnerZorp), "blocks/functional/spawners/");
		registerRender(Item.getItemFromBlock(armyBlock), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(baronessAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(candyBlock), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(clunkheadAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(craexxeusAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(creepAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(dracyonAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(grawAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(guardianAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(hiveSpawner), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(hydroTable), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(illusionAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(krorAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(mechbotAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(powerStation), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(primordialShrine), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(rockriderShrine), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(shadowAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(silverfootAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(toyBox), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(tyrosaurAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(vinocorneShrine), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(visualentAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(voxxulonAltar), "blocks/functional/boss/");
		registerRender(Item.getItemFromBlock(portalAbyss), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalAncientCavern), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalBarathos), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalBorean), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalCandyland), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalCeleve), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalCreeponia), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalCrystevia), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalDeeplands), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalDustopia), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalGardencia), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalGreckon), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalHaven), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalImmortallis), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalIromine), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalLelyetia), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalLunalus), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalMysterium), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalPrecasia), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalRunandor), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalShyrelands), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(portalVoxPonds), "blocks/functional/portal/");
		registerRender(Item.getItemFromBlock(ancientAltar), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(shrineErebon), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(shrineLuxon), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(shrinePluton), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(shrineSelyan), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(ascensionShrine), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(creationForge), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(crystalCreatorBlue), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(crystalCreatorGreen), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(crystalCreatorPurple), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(crystalCreatorRed), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(crystalCreatorWhite), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(crystalCreatorYellow), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(crystalExtensionShrine), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(decloggingTable), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(deepCase), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(divineStation), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(enigmaTable), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(exoidStation), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(extractionDevice), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(filtrationSystem), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(goldAccumulator), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(hauntingTable), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(immortallisProgressor1), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(immortallisProgressor2), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(immortallisProgressor3), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(immortallisProgressor4), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(immortallisProgressor5), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(immortallisProgressor6), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(immortallisProgressor7), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(immortallisProgressor8), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(immortallisProgressor9), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(infusionTable), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(iroCrate), "blocks/functional/misc/");
		registerRender(Item.getItemFromBlock(lunarCreationTable), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(lunarEnrichmentTable), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(mendingTable), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(mineralizationStation), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(petalCraftingStation), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(pureGoldAccumulator), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(recreationStation), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(runeRandomizer), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(runeShrine), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(runicBlock), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(strangeBlock), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(teaSink), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(voxCrate), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(voxStoreCrate), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(whitewashingTable), "blocks/functional/utility/");
		registerRender(Item.getItemFromBlock(plantAncientVines), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantAncientVinesCap), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantAquaFungiBlue), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantAquaFungiYellow), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantArcbulb), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantArcflower), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantBloodPine), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantBloodPineStem), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantBloodSpikes), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantBloodStrands), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantBulbStock), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantBulbStockCap), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantBurealStocks), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCandycane), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCandyGrass), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCandyGrassBlue), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCandyGrassWhite), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCandyTube), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCelebulbsGreen), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCelebulbsStem), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCelebulbsYellow), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCeleviansBlue), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCeleviansPurple), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCeleviansRed), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCeleviansWhite), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantChocolateGrass), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantChocolateStocks), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCoralCage), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCreepFlowers), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCreepGrass), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCreepVines), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCrystalBlue), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCrystalGreen), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCrystalPurple), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCrystalRed), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCrystalWhite), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantCrystalYellow), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDaileers), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDawnBulb), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDawnBush), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDawnFlower), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDawnGrass), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDawnStocks), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDawnStocksTop), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDawnwoodBars), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDayloomsBlue), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDayloomsPink), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDayloomsYellow), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDeadGrass), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDeepBlooms), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDeepBulb), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDeepGrass), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantDeepVines), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantEyeShrub), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantEyeShrubStem), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantFlakeVine), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantFlakeVineTop), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantGardenGrass), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantHauntedFlower), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantHavendalesBlue), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantHavendalesBlueStem), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantHavendalesPink), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantHavendalesPinkStem), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantHavendalesYellow), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantHavendalesYellowStem), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantHavenGrass), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantHorizonDaisies), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantIroGrass), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantIrotops), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLelyetianGrass), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLelyetianGrassDown), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLelyetianStem), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLelyetianStemCap), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLelyetianStemCapDown), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLelyetianWiggler), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLelyetianWigglerBottom), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLelyetianWigglerTop), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLollypopBlue), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLollypopRed), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLollypopYellow), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLuconGrass), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLunalip), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLuntar), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLurchians), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantLylips), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantMagias), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantMallowPile), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantMarshTube), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantMellians), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantMysticBush), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantMysticFerns), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantOcealitesBlue), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantOcealitesRed), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantPeppermintGreen), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantPeppermintRed), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantPertonias), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantPlasticStick), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantRainbowGrass), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantRainbowGrass2), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantRainbowGrass3), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantRuneBulbs), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantRunicBush), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantShadicles), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantShadiclesTop), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantShadowShrub), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantShyreCap), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantShyreCapDown), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantShyreStock), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantShyreWeed), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantSilverGrass), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantTangleThorns), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantTubeicles), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantVoxFungi), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantVoxFungiStem), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantVoxTentacles), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantVoxTentaclesStem), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantWaterweedsGreen), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantWaterweedsRed), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantWaterweedsWhite), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(plantWaterweedsYellow), "blocks/generation/plants/");
		registerRender(Item.getItemFromBlock(statueBane), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueBaneGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueBaneOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueBaroness), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueBaronessGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueBaronessOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueClunkhead), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueClunkheadGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueClunkheadOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueConiferon), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueConiferonGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueConiferonOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCorallus), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCorallusGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCorallusOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCottonCandor), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCottonCandorGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCottonCandorOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCraexxeus), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCraexxeusGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCraexxeusOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCreep), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCreepGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCreepOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCrystocore), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCrystocoreGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueCrystocoreOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueDracyon), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueDracyonGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueDracyonOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueElusive), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueElusiveGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueElusiveOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueFlash), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueFlashGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueFlashOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueGoldorth), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueGoldorthGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueGoldorthOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueGraw), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueGrawGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueGrawOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueGuardian), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueGuardianGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueGuardianOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueGyro), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueGyroGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueGyroOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueHarkos), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueHarkosGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueHarkosOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueHiveKing), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueHiveKingGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueHiveKingOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueHoron), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueHoronGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueHoronOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueHydrolisk), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueHydroliskGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueHydroliskOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKajaros), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKajarosGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKajarosOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKingBamBamBam), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKingBamBamBamGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKingBamBamBamOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKingShroomus), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKingShroomusGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKingShroomusOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKlobber), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKlobberGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKlobberOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKror), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKrorGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueKrorOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueMechbot), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueMechbotGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueMechbotOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueMirage), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueMirageGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueMirageOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueMiskel), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueMiskelGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueMiskelOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueNethengeicWither), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueNethengeicWitherGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueNethengeicWitherOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueOkazor), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueOkazorGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueOkazorOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statuePenumbra), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statuePenumbraGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statuePenumbraOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueProshield), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueProshieldGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueProshieldOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueRaxxan), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueRaxxanGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueRaxxanOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueRockrider), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueRockriderGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueRockriderOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueShadowlord), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueShadowlordGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueShadowlordOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueSilverfoot), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueSilverfootGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueSilverfootOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueSkeletron), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueSkeletronGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueSkeletronOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueSmash), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueSmashGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueSmashOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueTyrosaur), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueTyrosaurGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueTyrosaurOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueVinocorne), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueVinocorneGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueVinocorneOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueVisualent), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueVisualentGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueVisualentOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueVoxxulon), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueVoxxulonGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueVoxxulonOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueXxeus), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueXxeusGold), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(statueXxeusOrnate), "blocks/decoration/statues/");
		registerRender(Item.getItemFromBlock(bannerAncient), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerAncientBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerAncientEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerAncientGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerBaron), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerBaronBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerBaronEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerBaronGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerBlood), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerBloodBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerBloodEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerBloodGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerBoreic), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerBoreicBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerBoreicEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerBoreicGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerCandy), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerCandyBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerCandyEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerCandyGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerClown), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerClownBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerClownEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerClownGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerCreation), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerCreationBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerCreationEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerCreationGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerCreepoid), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerCreepoidBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerCreepoidEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerCreepoidGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerCreepy), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerCreepyBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerCreepyEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerCreepyGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerCrystal), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerCrystalBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerCrystalEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerCrystalGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerDeep), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerDeepBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerDeepEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerDeepGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerDustopian), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerDustopianBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerDustopianEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerDustopianGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerEnergy), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerEnergyBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerEnergyEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerEnergyGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerErebon), "blocks/decoration/banners/special/");
		registerRender(Item.getItemFromBlock(bannerFragment), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerFragmentBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerFragmentEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerFragmentGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerFungal), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerFungalBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerFungalEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerFungalGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerGhostly), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerGhostlyBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerGhostlyEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerGhostlyGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerGhoul), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerGhoulBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerGhoulEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerGhoulGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerGingerbread), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerGingerbreadBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerGingerbreadEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerGingerbreadGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerHaunted), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerHauntedBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerHauntedEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerHauntedGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerIllusion), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerIllusionBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerIllusionEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerIllusionGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerImmortal), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerImmortalBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerImmortalEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerImmortalGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerLelyetian), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerLelyetianBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerLelyetianEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerLelyetianGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerLight), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerLightBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerLightEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerLightGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerLotto), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerLottoBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerLottoEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerLottoGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerLunar), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerLunarBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerLunarEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerLunarGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerLuxon), "blocks/decoration/banners/special/");
		registerRender(Item.getItemFromBlock(bannerMecha), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerMechaBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerMechaEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerMechaGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerNethengeic), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerNethengeicBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerNethengeicEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerNethengeicGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerNether), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerNetherBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerNetherEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerNetherGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerPluton), "blocks/decoration/banners/special/");
		registerRender(Item.getItemFromBlock(bannerRosidian), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerRosidianBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerRosidianEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerRosidianGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerRunic), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerRunicBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerRunicEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerRunicGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerSea), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerSeaBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerSeaEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerSeaGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerSelyan), "blocks/decoration/banners/special/");
		registerRender(Item.getItemFromBlock(bannerShadow), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerShadowBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerShadowEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerShadowGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerShiny), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerShinyBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerShinyEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerShinyGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerShyre), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerShyreBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerShyreEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerShyreGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerSkeletal), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerSkeletalBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerSkeletalEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerSkeletalGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerSoul), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerSoulBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerSoulEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerSoulGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerUtopian), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerUtopianBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerUtopianEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerUtopianGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerVoid), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerVoidBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerVoidEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerVoidGilded), "blocks/decoration/banners/gilded/");
		registerRender(Item.getItemFromBlock(bannerVox), "blocks/decoration/banners/normal/");
		registerRender(Item.getItemFromBlock(bannerVoxBejewelled), "blocks/decoration/banners/bejewelled/");
		registerRender(Item.getItemFromBlock(bannerVoxEncrusted), "blocks/decoration/banners/encrusted/");
		registerRender(Item.getItemFromBlock(bannerVoxGilded), "blocks/decoration/banners/gilded/");

		AdventOfAscension.proxy.registerStateMappers();
	}

	public static void doInitTasks() {
		String oreDictCobble = "cobblestone";
		String oreDictFenceGateWood = "fenceGateWood";
		String oreDictFenceWood = "fenceWood";
		String oreDictGlass = "blockGlass";
		String oreDictLeaves = "treeLeaves";
		String oreDictPlanks = "plankWood";
		String oreDictSand = "sand";
		String oreDictSlabWood = "slabWood";
		String oreDictStairsWood = "stairWood";
		String oreDictStone = "stone";
		String oreDictWood = "logWood";
		
		crystalCreatorBlue.setConversionItems(ItemRegister.gemstonesBlue, ItemRegister.crystalBlue);
		crystalCreatorGreen.setConversionItems(ItemRegister.gemstonesGreen, ItemRegister.crystalGreen);
		crystalCreatorPurple.setConversionItems(ItemRegister.gemstonesPurple, ItemRegister.crystalPurple);
		crystalCreatorRed.setConversionItems(ItemRegister.gemstonesRed, ItemRegister.crystalRed);
		crystalCreatorWhite.setConversionItems(ItemRegister.gemstonesWhite, ItemRegister.crystalWhite);
		crystalCreatorYellow.setConversionItems(ItemRegister.gemstonesYellow, ItemRegister.crystalYellow);
		oreAmethyst.setDrop(ItemRegister.amethyst);
		oreBloodstone.setDrop(ItemRegister.gemBloodstone);
		oreBlueGemstone.setDrop(ItemRegister.gemstonesBlue);
		oreChestboneFragments.setDrop(ItemRegister.boneFragmentChestbone);
		oreCrystallite.setDrop(ItemRegister.gemCrystallite);
		oreFootboneFragments.setDrop(ItemRegister.boneFragmentFootbone);
		oreGreenGemstone.setDrop(ItemRegister.gemstonesGreen);
		oreJade.setDrop(ItemRegister.jade);
		oreLegboneFragments.setDrop(ItemRegister.boneFragmentLegbone);
		orePurpleGemstone.setDrop(ItemRegister.gemstonesPurple);
		oreRedGemstone.setDrop(ItemRegister.gemstonesRed);
		oreSapphire.setDrop(ItemRegister.sapphire);
		oreShyregem.setDrop(ItemRegister.shyregem);
		oreSkullboneFragments.setDrop(ItemRegister.boneFragmentSkullbone);
		oreWhiteGemstone.setDrop(ItemRegister.gemstonesWhite);
		oreYellowGemstone.setDrop(ItemRegister.gemstonesYellow);

		OreDictionary.registerOre("oreAmethyst", oreAmethyst);
		OreDictionary.registerOre("oreBaronyte", oreBaronyte);
		OreDictionary.registerOre("oreBlazium", oreBlazium);
		OreDictionary.registerOre("oreBloodstone", oreBloodstone);
		OreDictionary.registerOre("oreBlueGemstone", oreBlueGemstone);
		OreDictionary.registerOre("oreChargedRunium", oreChargedRunium);
		OreDictionary.registerOre("oreChestboneFragments", oreChestboneFragments);
		OreDictionary.registerOre("oreCrystallite", oreCrystallite);
		OreDictionary.registerOre("oreElecanium", oreElecanium);
		OreDictionary.registerOre("oreEmberstone", oreEmberstone);
		OreDictionary.registerOre("oreFootboneFragments", oreFootboneFragments);
		OreDictionary.registerOre("oreGemenyte", oreGemenyte);
		OreDictionary.registerOre("oreGhastly", oreGhastly);
		OreDictionary.registerOre("oreGhoulish", oreGhoulish);
		OreDictionary.registerOre("oreGreenGemstone", oreGreenGemstone);
		OreDictionary.registerOre("oreJade", oreJade);
		OreDictionary.registerOre("oreJewelyte", oreJewelyte);
		OreDictionary.registerOre("oreLegboneFragments", oreLegboneFragments);
		OreDictionary.registerOre("oreLimonite", oreLimonite);
		OreDictionary.registerOre("oreLyon", oreLyon);
		OreDictionary.registerOre("oreMystite", oreMystite);
		OreDictionary.registerOre("oreOrnamyte", oreOrnamyte);
		OreDictionary.registerOre("orePurpleGemstone", orePurpleGemstone);
		OreDictionary.registerOre("oreRedGemstone", oreRedGemstone);
		OreDictionary.registerOre("oreRosite", oreRosite);
		OreDictionary.registerOre("oreRunium", oreRunium);
		OreDictionary.registerOre("oreSapphire", oreSapphire);
		OreDictionary.registerOre("oreShyregem", oreShyregem);
		OreDictionary.registerOre("oreShyrestone", oreShyrestone);
		OreDictionary.registerOre("oreSkullboneFragments", oreSkullboneFragments);
		OreDictionary.registerOre("oreVarsium", oreVarsium);
		OreDictionary.registerOre("oreWhiteGemstone", oreWhiteGemstone);
		OreDictionary.registerOre("oreYellowGemstone", oreYellowGemstone);

		OreDictionary.registerOre(oreDictStone, ancientRock);

		OreDictionary.registerOre(oreDictCobble, stoneAbyss);
		OreDictionary.registerOre(oreDictCobble, stoneBarathos);
		OreDictionary.registerOre(oreDictCobble, stoneBaron);
		OreDictionary.registerOre(oreDictCobble, stoneBorean);
		OreDictionary.registerOre(oreDictCobble, stoneCreep);
		OreDictionary.registerOre(oreDictCobble, stoneCrystevia);
		OreDictionary.registerOre(oreDictCobble, stoneDeeplands);
		OreDictionary.registerOre(oreDictCobble, stoneDustopia);
		OreDictionary.registerOre(oreDictCobble, stoneGardencia);
		OreDictionary.registerOre(oreDictCobble, stoneGreckon);
		OreDictionary.registerOre(oreDictCobble, stoneHaven);
		OreDictionary.registerOre(oreDictCobble, stoneIromine);
		OreDictionary.registerOre(oreDictCobble, stoneLelyetia);
		OreDictionary.registerOre(oreDictCobble, stoneMysterium);
		OreDictionary.registerOre(oreDictCobble, stonePrecasiaHigh);
		OreDictionary.registerOre(oreDictCobble, stonePrecasiaLow);
		OreDictionary.registerOre(oreDictCobble, stonePrimed);
		OreDictionary.registerOre(oreDictCobble, stoneRunic);
		OreDictionary.registerOre(oreDictCobble, stoneShyrelands);
		OreDictionary.registerOre(oreDictCobble, stoneToxic);
		OreDictionary.registerOre(oreDictCobble, stoneUnstable);

		OreDictionary.registerOre(oreDictLeaves, leavesAchony);
		OreDictionary.registerOre(oreDictLeaves, leavesBlood);
		OreDictionary.registerOre(oreDictLeaves, leavesBubble);
		OreDictionary.registerOre(oreDictLeaves, leavesCelevusBlue);
		OreDictionary.registerOre(oreDictLeaves, leavesCelevusGreen);
		OreDictionary.registerOre(oreDictLeaves, leavesCelevusPurple);
		OreDictionary.registerOre(oreDictLeaves, leavesCelevusRed);
		OreDictionary.registerOre(oreDictLeaves, leavesCelevusWhite);
		OreDictionary.registerOre(oreDictLeaves, leavesCelevusYellow);
		OreDictionary.registerOre(oreDictLeaves, leavesChurry);
		OreDictionary.registerOre(oreDictLeaves, leavesCreep);
		OreDictionary.registerOre(oreDictLeaves, leavesCycade);
		OreDictionary.registerOre(oreDictLeaves, leavesDawn);
		OreDictionary.registerOre(oreDictLeaves, leavesDomiguous);
		OreDictionary.registerOre(oreDictLeaves, leavesEternal);
		OreDictionary.registerOre(oreDictLeaves, leavesEucadon);
		OreDictionary.registerOre(oreDictLeaves, leavesHaunted);
		OreDictionary.registerOre(oreDictLeaves, leavesHauntedEyes);
		OreDictionary.registerOre(oreDictLeaves, leavesHavenBlue);
		OreDictionary.registerOre(oreDictLeaves, leavesHavenPink);
		OreDictionary.registerOre(oreDictLeaves, leavesHavenPurple);
		OreDictionary.registerOre(oreDictLeaves, leavesHavenRed);
		OreDictionary.registerOre(oreDictLeaves, leavesHavenTurquoise);
		OreDictionary.registerOre(oreDictLeaves, leavesHavenYellow);
		OreDictionary.registerOre(oreDictLeaves, leavesIrodust);
		OreDictionary.registerOre(oreDictLeaves, leavesIrogold);
		OreDictionary.registerOre(oreDictLeaves, leavesLelyetian);
		OreDictionary.registerOre(oreDictLeaves, leavesLucalus);
		OreDictionary.registerOre(oreDictLeaves, leavesLunicia);
		OreDictionary.registerOre(oreDictLeaves, leavesLunosso);
		OreDictionary.registerOre(oreDictLeaves, leavesMelumia);
		OreDictionary.registerOre(oreDictLeaves, leavesOpollo);
		OreDictionary.registerOre(oreDictLeaves, leavesRunic);
		OreDictionary.registerOre(oreDictLeaves, leavesShadow);
		OreDictionary.registerOre(oreDictLeaves, leavesShadowblood);
		OreDictionary.registerOre(oreDictLeaves, leavesShyre);
		OreDictionary.registerOre(oreDictLeaves, leavesShyreBright);
		OreDictionary.registerOre(oreDictLeaves, leavesSilvro);
		OreDictionary.registerOre(oreDictLeaves, leavesStranglewood);
		OreDictionary.registerOre(oreDictLeaves, leavesVein);

		OreDictionary.registerOre(oreDictWood, logAchony);
		OreDictionary.registerOre(oreDictWood, logBlood);
		OreDictionary.registerOre(oreDictWood, logChurry);
		OreDictionary.registerOre(oreDictWood, logCreep);
		OreDictionary.registerOre(oreDictWood, logCycade);
		OreDictionary.registerOre(oreDictWood, logDawn);
		OreDictionary.registerOre(oreDictWood, logDomiguous);
		OreDictionary.registerOre(oreDictWood, logEternal);
		OreDictionary.registerOre(oreDictWood, logEucadon);
		OreDictionary.registerOre(oreDictWood, logEyeball);
		OreDictionary.registerOre(oreDictWood, logHaunted);
		OreDictionary.registerOre(oreDictWood, logHauntedEye);
		OreDictionary.registerOre(oreDictWood, logHauntedEyes);
		OreDictionary.registerOre(oreDictWood, logHauntedFlashing);
		OreDictionary.registerOre(oreDictWood, logHauntedPurpling);
		OreDictionary.registerOre(oreDictWood, logIro);
		OreDictionary.registerOre(oreDictWood, logLucalus);
		OreDictionary.registerOre(oreDictWood, logLunide);
		OreDictionary.registerOre(oreDictWood, logMelumia);
		OreDictionary.registerOre(oreDictWood, logOpollo);
		OreDictionary.registerOre(oreDictWood, logRunic);
		OreDictionary.registerOre(oreDictWood, logShadow);
		OreDictionary.registerOre(oreDictWood, logShyre);
		OreDictionary.registerOre(oreDictWood, logStranglewood);
		OreDictionary.registerOre(oreDictWood, logToxic);

		OreDictionary.registerOre(oreDictPlanks, planksAchony);
		OreDictionary.registerOre(oreDictPlanks, planksBloodwood);
		OreDictionary.registerOre(oreDictPlanks, planksChurry);
		OreDictionary.registerOre(oreDictPlanks, planksCreep);
		OreDictionary.registerOre(oreDictPlanks, planksCycade);
		OreDictionary.registerOre(oreDictPlanks, planksDawnwood);
		OreDictionary.registerOre(oreDictPlanks, planksDomiguous);
		OreDictionary.registerOre(oreDictPlanks, planksEucadon);
		OreDictionary.registerOre(oreDictPlanks, planksHauntedwood);
		OreDictionary.registerOre(oreDictPlanks, planksIrowood);
		OreDictionary.registerOre(oreDictPlanks, planksLucalus);
		OreDictionary.registerOre(oreDictPlanks, planksLunide);
		OreDictionary.registerOre(oreDictPlanks, planksMelumia);
		OreDictionary.registerOre(oreDictPlanks, planksOpollo);
		OreDictionary.registerOre(oreDictPlanks, planksRunic);
		OreDictionary.registerOre(oreDictPlanks, planksShadow);
		OreDictionary.registerOre(oreDictPlanks, planksShyre);
		OreDictionary.registerOre(oreDictPlanks, planksStranglewood);
		OreDictionary.registerOre(oreDictPlanks, planksToxicwood);

		OreDictionary.registerOre(oreDictFenceWood, fenceAchony);
		OreDictionary.registerOre(oreDictFenceWood, fenceBloodwood);
		OreDictionary.registerOre(oreDictFenceWood, fenceChurry);
		OreDictionary.registerOre(oreDictFenceWood, fenceCreep);
		OreDictionary.registerOre(oreDictFenceWood, fenceCycade);
		OreDictionary.registerOre(oreDictFenceWood, fenceDawnwood);
		OreDictionary.registerOre(oreDictFenceWood, fenceDomiguous);
		OreDictionary.registerOre(oreDictFenceWood, fenceEucadon);
		OreDictionary.registerOre(oreDictFenceWood, fenceHauntedwood);
		OreDictionary.registerOre(oreDictFenceWood, fenceIrowood);
		OreDictionary.registerOre(oreDictFenceWood, fenceLucalus);
		OreDictionary.registerOre(oreDictFenceWood, fenceLunide);
		OreDictionary.registerOre(oreDictFenceWood, fenceMelumia);
		OreDictionary.registerOre(oreDictFenceWood, fenceOpollo);
		OreDictionary.registerOre(oreDictFenceWood, fenceRunic);
		OreDictionary.registerOre(oreDictFenceWood, fenceShadow);
		OreDictionary.registerOre(oreDictFenceWood, fenceShyre);
		OreDictionary.registerOre(oreDictFenceWood, fenceStranglewood);
		OreDictionary.registerOre(oreDictFenceWood, fenceToxicwood);

		OreDictionary.registerOre(oreDictFenceGateWood, gateAchony);
		OreDictionary.registerOre(oreDictFenceGateWood, gateBloodwood);
		OreDictionary.registerOre(oreDictFenceGateWood, gateChurry);
		OreDictionary.registerOre(oreDictFenceGateWood, gateCreep);
		OreDictionary.registerOre(oreDictFenceGateWood, gateCycade);
		OreDictionary.registerOre(oreDictFenceGateWood, gateDawnwood);
		OreDictionary.registerOre(oreDictFenceGateWood, gateDomiguous);
		OreDictionary.registerOre(oreDictFenceGateWood, gateEucadon);
		OreDictionary.registerOre(oreDictFenceGateWood, gateHauntedwood);
		OreDictionary.registerOre(oreDictFenceGateWood, gateIrowood);
		OreDictionary.registerOre(oreDictFenceGateWood, gateLucalus);
		OreDictionary.registerOre(oreDictFenceGateWood, gateLunide);
		OreDictionary.registerOre(oreDictFenceGateWood, gateMelumia);
		OreDictionary.registerOre(oreDictFenceGateWood, gateOpollo);
		OreDictionary.registerOre(oreDictFenceGateWood, gateRunic);
		OreDictionary.registerOre(oreDictFenceGateWood, gateShadow);
		OreDictionary.registerOre(oreDictFenceGateWood, gateShyre);
		OreDictionary.registerOre(oreDictFenceGateWood, gateStranglewood);
		OreDictionary.registerOre(oreDictFenceGateWood, gateToxicwood);

		OreDictionary.registerOre(oreDictSlabWood, slabAchony.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabBloodwood.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabChurry.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabCreep.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabCycade.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabDawnwood.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabDomiguous.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabEucadon.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabHauntedwood.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabIrowood.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabLucalus.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabLunide.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabMelumia.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabOpollo.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabRunic.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabShadow.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabShyre.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabStranglewood.getHalfBlock());
		OreDictionary.registerOre(oreDictSlabWood, slabToxicwood.getHalfBlock());

		OreDictionary.registerOre(oreDictStairsWood, stairsAchony);
		OreDictionary.registerOre(oreDictStairsWood, stairsBloodwood);
		OreDictionary.registerOre(oreDictStairsWood, stairsChurry);
		OreDictionary.registerOre(oreDictStairsWood, stairsCreep);
		OreDictionary.registerOre(oreDictStairsWood, stairsCycade);
		OreDictionary.registerOre(oreDictStairsWood, stairsDawnwood);
		OreDictionary.registerOre(oreDictStairsWood, stairsDomiguous);
		OreDictionary.registerOre(oreDictStairsWood, stairsEucadon);
		OreDictionary.registerOre(oreDictStairsWood, stairsHauntedwood);
		OreDictionary.registerOre(oreDictStairsWood, stairsIrowood);
		OreDictionary.registerOre(oreDictStairsWood, stairsLucalus);
		OreDictionary.registerOre(oreDictStairsWood, stairsLunide);
		OreDictionary.registerOre(oreDictStairsWood, stairsMelumia);
		OreDictionary.registerOre(oreDictStairsWood, stairsOpollo);
		OreDictionary.registerOre(oreDictStairsWood, stairsRunic);
		OreDictionary.registerOre(oreDictStairsWood, stairsShadow);
		OreDictionary.registerOre(oreDictStairsWood, stairsShyre);
		OreDictionary.registerOre(oreDictStairsWood, stairsStranglewood);
		OreDictionary.registerOre(oreDictStairsWood, stairsToxicwood);

		OreDictionary.registerOre(oreDictGlass, glassAbyssal);
		OreDictionary.registerOre(oreDictGlass, glassAquatic);
		OreDictionary.registerOre(oreDictGlass, glassBaron);
		OreDictionary.registerOre(oreDictGlass, glassGardencian);
		OreDictionary.registerOre(oreDictGlass, glassHaven);
		OreDictionary.registerOre(oreDictGlass, glassIro);
		OreDictionary.registerOre(oreDictGlass, glassLelyetian);
		OreDictionary.registerOre(oreDictGlass, glassLunar);
		OreDictionary.registerOre(oreDictGlass, glassRunic);
		OreDictionary.registerOre(oreDictGlass, glassShyre);
		OreDictionary.registerOre(oreDictGlass, glassVox);
		OreDictionary.registerOre(oreDictGlass, glassZhinx);

		OreDictionary.registerOre(oreDictSand, sandPrecasian);

		OreDictionary.registerOre("oreAmethyst", oreAmethyst);
		OreDictionary.registerOre("oreBaronyte", oreBaronyte);
		OreDictionary.registerOre("oreBlazium", oreBlazium);
		OreDictionary.registerOre("oreBloodstone", oreBloodstone);
		OreDictionary.registerOre("oreBone", oreChestboneFragments);
		OreDictionary.registerOre("oreBone", oreFootboneFragments);
		OreDictionary.registerOre("oreBone", oreLegboneFragments);
		OreDictionary.registerOre("oreBone", oreSkullboneFragments);
		OreDictionary.registerOre("oreCrystallite", oreCrystallite);
		OreDictionary.registerOre("oreElecanium", oreElecanium);
		OreDictionary.registerOre("oreEmberstone", oreEmberstone);
		OreDictionary.registerOre("oreGemenyte", oreGemenyte);
		OreDictionary.registerOre("oreGhastly", oreGhastly);
		OreDictionary.registerOre("oreGhoulish", oreGhoulish);
		OreDictionary.registerOre("oreJade", oreJade);
		OreDictionary.registerOre("oreJewelyte", oreJewelyte);
		OreDictionary.registerOre("oreLimonite", oreLimonite);
		OreDictionary.registerOre("oreLyon", oreLyon);
		OreDictionary.registerOre("oreMystite", oreMystite);
		OreDictionary.registerOre("oreOrnamyte", oreOrnamyte);
		OreDictionary.registerOre("oreRosite", oreRosite);
		OreDictionary.registerOre("oreRunium", oreChargedRunium);
		OreDictionary.registerOre("oreRunium", oreRunium);
		OreDictionary.registerOre("oreSapphire", oreSapphire);
		OreDictionary.registerOre("oreShryeGems", oreShyregem);
		OreDictionary.registerOre("oreShyreStones", oreShyrestone);
		OreDictionary.registerOre("oreVarsium", oreVarsium);

		OreDictionary.registerOre("blockCrystallanium", crystallanium);
		OreDictionary.registerOre("blockEmberium", emberium);
		OreDictionary.registerOre("blockShadonantium", shadonantium);
		OreDictionary.registerOre("blockSkeletanium", skeletanium);
	}

	@SideOnly(Side.CLIENT)
	public static void registerStateMappers() {
		ModelLoader.setCustomStateMapper(ancientCactus, ancientCactus.getStateMapper());
		ModelLoader.setCustomStateMapper(carpetBaron, carpetBaron.getStateMapper());
		ModelLoader.setCustomStateMapper(carpetBorean, carpetBorean.getStateMapper());
		ModelLoader.setCustomStateMapper(carpetGardencian, carpetGardencian.getStateMapper());
		ModelLoader.setCustomStateMapper(carpetIro, carpetIro.getStateMapper());
		ModelLoader.setCustomStateMapper(carpetLunar, carpetLunar.getStateMapper());
		ModelLoader.setCustomStateMapper(dirtBorean, dirtBorean.getStateMapper());
		ModelLoader.setCustomStateMapper(dirtCandyland, dirtCandyland.getStateMapper());
		ModelLoader.setCustomStateMapper(dirtCeleve, dirtCeleve.getStateMapper());
		ModelLoader.setCustomStateMapper(dirtCreeponia, dirtCreeponia.getStateMapper());
		ModelLoader.setCustomStateMapper(dirtDustopia, dirtDustopia.getStateMapper());
		ModelLoader.setCustomStateMapper(dirtGardencia, dirtGardencia.getStateMapper());
		ModelLoader.setCustomStateMapper(dirtGreckon, dirtGreckon.getStateMapper());
		ModelLoader.setCustomStateMapper(dirtHaven, dirtHaven.getStateMapper());
		ModelLoader.setCustomStateMapper(dirtLunalyte, dirtLunalyte.getStateMapper());
		ModelLoader.setCustomStateMapper(dirtLunasole, dirtLunasole.getStateMapper());
		ModelLoader.setCustomStateMapper(dirtMysterium, dirtMysterium.getStateMapper());
		ModelLoader.setCustomStateMapper(dirtToxic, dirtToxic.getStateMapper());
		ModelLoader.setCustomStateMapper(gateAchony, gateAchony.getStateMapper());
		ModelLoader.setCustomStateMapper(gateBloodwood, gateBloodwood.getStateMapper());
		ModelLoader.setCustomStateMapper(gateChurry, gateChurry.getStateMapper());
		ModelLoader.setCustomStateMapper(gateCreep, gateCreep.getStateMapper());
		ModelLoader.setCustomStateMapper(gateCycade, gateCycade.getStateMapper());
		ModelLoader.setCustomStateMapper(gateDawnwood, gateDawnwood.getStateMapper());
		ModelLoader.setCustomStateMapper(gateDomiguous, gateDomiguous.getStateMapper());
		ModelLoader.setCustomStateMapper(gateEucadon, gateEucadon.getStateMapper());
		ModelLoader.setCustomStateMapper(gateHauntedwood, gateHauntedwood.getStateMapper());
		ModelLoader.setCustomStateMapper(gateIrowood, gateIrowood.getStateMapper());
		ModelLoader.setCustomStateMapper(gateLucalus, gateLucalus.getStateMapper());
		ModelLoader.setCustomStateMapper(gateLunide, gateLunide.getStateMapper());
		ModelLoader.setCustomStateMapper(gateMelumia, gateMelumia.getStateMapper());
		ModelLoader.setCustomStateMapper(gateOpollo, gateOpollo.getStateMapper());
		ModelLoader.setCustomStateMapper(gateRunic, gateRunic.getStateMapper());
		ModelLoader.setCustomStateMapper(gateShadow, gateShadow.getStateMapper());
		ModelLoader.setCustomStateMapper(gateShyre, gateShyre.getStateMapper());
		ModelLoader.setCustomStateMapper(gateStranglewood, gateStranglewood.getStateMapper());
		ModelLoader.setCustomStateMapper(gateToxicwood, gateToxicwood.getStateMapper());
		ModelLoader.setCustomStateMapper(lampAmethyst, lampAmethyst.getStateMapper());
		ModelLoader.setCustomStateMapper(lampAmethyst.getOffLamp(), lampAmethyst.getStateMapper());
		ModelLoader.setCustomStateMapper(lampAquatic, lampAquatic.getStateMapper());
		ModelLoader.setCustomStateMapper(lampAquatic.getOffLamp(), lampAquatic.getStateMapper());
		ModelLoader.setCustomStateMapper(lampBaronyte, lampBaronyte.getStateMapper());
		ModelLoader.setCustomStateMapper(lampBaronyte.getOffLamp(), lampBaronyte.getStateMapper());
		ModelLoader.setCustomStateMapper(lampBlazium, lampBlazium.getStateMapper());
		ModelLoader.setCustomStateMapper(lampBlazium.getOffLamp(), lampBlazium.getStateMapper());
		ModelLoader.setCustomStateMapper(lampBloodstone, lampBloodstone.getStateMapper());
		ModelLoader.setCustomStateMapper(lampBloodstone.getOffLamp(), lampBloodstone.getStateMapper());
		ModelLoader.setCustomStateMapper(lampCrystallite, lampCrystallite.getStateMapper());
		ModelLoader.setCustomStateMapper(lampCrystallite.getOffLamp(), lampCrystallite.getStateMapper());
		ModelLoader.setCustomStateMapper(lampElecanium, lampElecanium.getStateMapper());
		ModelLoader.setCustomStateMapper(lampElecanium.getOffLamp(), lampElecanium.getStateMapper());
		ModelLoader.setCustomStateMapper(lampEmberstone, lampEmberstone.getStateMapper());
		ModelLoader.setCustomStateMapper(lampEmberstone.getOffLamp(), lampEmberstone.getStateMapper());
		ModelLoader.setCustomStateMapper(lampFire, lampFire.getStateMapper());
		ModelLoader.setCustomStateMapper(lampFire.getOffLamp(), lampFire.getStateMapper());
		ModelLoader.setCustomStateMapper(lampGhastly, lampGhastly.getStateMapper());
		ModelLoader.setCustomStateMapper(lampGhastly.getOffLamp(), lampGhastly.getStateMapper());
		ModelLoader.setCustomStateMapper(lampGhoulish, lampGhoulish.getStateMapper());
		ModelLoader.setCustomStateMapper(lampGhoulish.getOffLamp(), lampGhoulish.getStateMapper());
		ModelLoader.setCustomStateMapper(lampHeatDarkGrey, lampHeatDarkGrey.getStateMapper());
		ModelLoader.setCustomStateMapper(lampHeatDarkGrey.getOffLamp(), lampHeatDarkGrey.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIro, lampIro.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIro.getOffLamp(), lampIro.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIvory, lampIvory.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIvory.getOffLamp(), lampIvory.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIvoryAmethyst, lampIvoryAmethyst.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIvoryAmethyst.getOffLamp(), lampIvoryAmethyst.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIvoryJade, lampIvoryJade.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIvoryJade.getOffLamp(), lampIvoryJade.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIvoryLimonite, lampIvoryLimonite.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIvoryLimonite.getOffLamp(), lampIvoryLimonite.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIvoryRosite, lampIvoryRosite.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIvoryRosite.getOffLamp(), lampIvoryRosite.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIvorySapphire, lampIvorySapphire.getStateMapper());
		ModelLoader.setCustomStateMapper(lampIvorySapphire.getOffLamp(), lampIvorySapphire.getStateMapper());
		ModelLoader.setCustomStateMapper(lampJade, lampJade.getStateMapper());
		ModelLoader.setCustomStateMapper(lampJade.getOffLamp(), lampJade.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeAqua, lampLifeAqua.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeAqua.getOffLamp(), lampLifeAqua.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeBlack, lampLifeBlack.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeBlack.getOffLamp(), lampLifeBlack.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeBlue, lampLifeBlue.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeBlue.getOffLamp(), lampLifeBlue.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeBrown, lampLifeBrown.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeBrown.getOffLamp(), lampLifeBrown.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeCyan, lampLifeCyan.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeCyan.getOffLamp(), lampLifeCyan.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeGreen, lampLifeGreen.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeGreen.getOffLamp(), lampLifeGreen.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeGrey, lampLifeGrey.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeGrey.getOffLamp(), lampLifeGrey.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeLime, lampLifeLime.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeLime.getOffLamp(), lampLifeLime.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeMagenta, lampLifeMagenta.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeMagenta.getOffLamp(), lampLifeMagenta.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeOrange, lampLifeOrange.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeOrange.getOffLamp(), lampLifeOrange.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifePink, lampLifePink.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifePink.getOffLamp(), lampLifePink.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifePurple, lampLifePurple.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifePurple.getOffLamp(), lampLifePurple.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeRed, lampLifeRed.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeRed.getOffLamp(), lampLifeRed.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeWhite, lampLifeWhite.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeWhite.getOffLamp(), lampLifeWhite.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeYellow, lampLifeYellow.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLifeYellow.getOffLamp(), lampLifeYellow.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLimonite, lampLimonite.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLimonite.getOffLamp(), lampLimonite.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLunar, lampLunar.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLunar.getOffLamp(), lampLunar.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLyon, lampLyon.getStateMapper());
		ModelLoader.setCustomStateMapper(lampLyon.getOffLamp(), lampLyon.getStateMapper());
		ModelLoader.setCustomStateMapper(lampMystic, lampMystic.getStateMapper());
		ModelLoader.setCustomStateMapper(lampMystic.getOffLamp(), lampMystic.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeon, lampNeon.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeon.getOffLamp(), lampNeon.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeonCircling, lampNeonCircling.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeonCircling.getOffLamp(), lampNeonCircling.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeonLapis, lampNeonLapis.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeonLapis.getOffLamp(), lampNeonLapis.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeonLapisCircling, lampNeonLapisCircling.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeonLapisCircling.getOffLamp(), lampNeonLapisCircling.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeonLapisTriangles, lampNeonLapisTriangles.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeonLapisTriangles.getOffLamp(), lampNeonLapisTriangles.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeonRunic, lampNeonRunic.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeonRunic.getOffLamp(), lampNeonRunic.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeonTriangles, lampNeonTriangles.getStateMapper());
		ModelLoader.setCustomStateMapper(lampNeonTriangles.getOffLamp(), lampNeonTriangles.getStateMapper());
		ModelLoader.setCustomStateMapper(lampRosite, lampRosite.getStateMapper());
		ModelLoader.setCustomStateMapper(lampRosite.getOffLamp(), lampRosite.getStateMapper());
		ModelLoader.setCustomStateMapper(lampSapphire, lampSapphire.getStateMapper());
		ModelLoader.setCustomStateMapper(lampSapphire.getOffLamp(), lampSapphire.getStateMapper());
		ModelLoader.setCustomStateMapper(lampSkeletal, lampSkeletal.getStateMapper());
		ModelLoader.setCustomStateMapper(lampSkeletal.getOffLamp(), lampSkeletal.getStateMapper());
		ModelLoader.setCustomStateMapper(sandPrecasian, sandPrecasian.getStateMapper());
	}

	private static void registerRender(Item item, String location) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation("aoa3:" + location + item.getRegistryName().getResourcePath()), "inventory"));
	}
}
