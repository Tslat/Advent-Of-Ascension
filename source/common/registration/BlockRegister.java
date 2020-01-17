package net.tslat.aoa3.common.registration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
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
import net.tslat.aoa3.block.functional.crops.CropBlock;
import net.tslat.aoa3.block.functional.lamps.LampBlock;
import net.tslat.aoa3.block.functional.lamps.LifeLampBlock;
import net.tslat.aoa3.block.functional.lights.LightBlock;
import net.tslat.aoa3.block.functional.lights.UnbreakableLightBlock;
import net.tslat.aoa3.block.functional.lights.VoxLight;
import net.tslat.aoa3.block.functional.liquids.CandiedWaterBlock;
import net.tslat.aoa3.block.functional.misc.*;
import net.tslat.aoa3.block.functional.portal.AncientCavernPortalBlock;
import net.tslat.aoa3.block.functional.portal.ImmortallisPortalBlock;
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
import net.tslat.aoa3.block.generation.special.DimensionalFabric;
import net.tslat.aoa3.block.generation.special.DustopianLamp;
import net.tslat.aoa3.block.generation.special.DustopianLampOff;
import net.tslat.aoa3.block.functional.altar.HydroTable;
import net.tslat.aoa3.block.generation.stone.StoneBlock;
import net.tslat.aoa3.block.generation.wood.LogBlock;
import net.tslat.aoa3.block.generation.wood.TentaclesEyeRed;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;

import static net.tslat.aoa3.library.Enums.Deities.*;

@SuppressWarnings({"ConstantConditions", "unused"})
@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder("aoa3")
public final class BlockRegister {
	private static ArrayList<BlockRegistryWrapper> blockRegistryList = new ArrayList<BlockRegistryWrapper>(1500);

	private static final String oreDictCobble = "cobblestone";
	private static final String oreDictFenceGateWood = "fenceGateWood";
	private static final String oreDictFenceWood = "fenceWood";
	private static final String oreDictGlass = "blockGlass";
	private static final String oreDictLeaves = "treeLeaves";
	private static final String oreDictPlanks = "plankWood";
	private static final String oreDictSand = "sand";
	private static final String oreDictSlabWood = "slabWood";
	private static final String oreDictStairsWood = "stairWood";
	private static final String oreDictStone = "stone";
	private static final String oreDictWood = "logWood";

	@GameRegistry.ObjectHolder("abyss_stone")
	public static final BasicBlock stoneAbyss = null;
	@GameRegistry.ObjectHolder("barathos_hellstone")
	public static final BasicBlock stoneBarathos = null;
	@GameRegistry.ObjectHolder("baron_stone")
	public static final BasicBlock stoneBaron = null;
	@GameRegistry.ObjectHolder("borean_stone")
	public static final BasicBlock stoneBorean = null;
	@GameRegistry.ObjectHolder("creep_stone")
	public static final BasicBlock stoneCreep = null;
	@GameRegistry.ObjectHolder("crystevia_stone")
	public static final BasicBlock stoneCrystevia = null;
	@GameRegistry.ObjectHolder("deeplands_stone")
	public static final BasicBlock stoneDeeplands = null;
	@GameRegistry.ObjectHolder("dustopia_stone")
	public static final BasicBlock stoneDustopia = null;
	@GameRegistry.ObjectHolder("gardencia_stone")
	public static final BasicBlock stoneGardencia = null;
	@GameRegistry.ObjectHolder("greckon_stone")
	public static final BasicBlock stoneGreckon = null;
	@GameRegistry.ObjectHolder("haven_stone")
	public static final BasicBlock stoneHaven = null;
	@GameRegistry.ObjectHolder("iromine_stone")
	public static final BasicBlock stoneIromine = null;
	@GameRegistry.ObjectHolder("lelyetia_stone")
	public static final BasicBlock stoneLelyetia = null;
	@GameRegistry.ObjectHolder("mysterium_stone")
	public static final BasicBlock stoneMysterium = null;
	@GameRegistry.ObjectHolder("high_precasia_stone")
	public static final BasicBlock stonePrecasiaHigh = null;
	@GameRegistry.ObjectHolder("low_precasia_stone")
	public static final BasicBlock stonePrecasiaLow = null;
	@GameRegistry.ObjectHolder("primed_stone")
	public static final BasicBlock stonePrimed = null;
	@GameRegistry.ObjectHolder("runic_stone")
	public static final BasicBlock stoneRunic = null;
	@GameRegistry.ObjectHolder("shyrelands_stone")
	public static final BasicBlock stoneShyrelands = null;
	@GameRegistry.ObjectHolder("toxic_stone")
	public static final BasicBlock stoneToxic = null;
	@GameRegistry.ObjectHolder("unstable_stone")
	public static final BasicBlock stoneUnstable = null;

	@GameRegistry.ObjectHolder("borean_dirt")
	public static final DirtBlock dirtBorean = null;
	@GameRegistry.ObjectHolder("candyland_dirt")
	public static final DirtBlock dirtCandyland = null;
	@GameRegistry.ObjectHolder("celeve_dirt")
	public static final DirtBlock dirtCeleve = null;
	@GameRegistry.ObjectHolder("creeponia_dirt")
	public static final DirtBlock dirtCreeponia = null;
	@GameRegistry.ObjectHolder("dustopia_dirt")
	public static final DirtBlock dirtDustopia = null;
	@GameRegistry.ObjectHolder("gardencia_dirt")
	public static final DirtBlock dirtGardencia = null;
	@GameRegistry.ObjectHolder("greckon_dirt")
	public static final DirtBlock dirtGreckon = null;
	@GameRegistry.ObjectHolder("haven_dirt")
	public static final DirtBlock dirtHaven = null;
	@GameRegistry.ObjectHolder("lunalyte_dirt")
	public static final DirtBlock dirtLunalyte = null;
	@GameRegistry.ObjectHolder("lunasole_dirt")
	public static final DirtBlock dirtLunasole = null;
	@GameRegistry.ObjectHolder("mysterium_dirt")
	public static final DirtBlock dirtMysterium = null;
	@GameRegistry.ObjectHolder("toxic_dirt")
	public static final DirtBlock dirtToxic = null;

	@GameRegistry.ObjectHolder("abyss_grass")
	public static final GrassBlock grassAbyss = null;
	@GameRegistry.ObjectHolder("borean_grass")
	public static final GrassBlock grassBorean = null;
	@GameRegistry.ObjectHolder("candyland_grass")
	public static final GrassBlock grassCandyland = null;
	@GameRegistry.ObjectHolder("celeve_grass")
	public static final GrassBlock grassCeleve = null;
	@GameRegistry.ObjectHolder("chocolate_grass")
	public static final GrassBlock grassChocolate = null;
	@GameRegistry.ObjectHolder("creeponia_grass")
	public static final GrassBlock grassCreeponia = null;
	@GameRegistry.ObjectHolder("crystal_grass")
	public static final GrassBlock grassCrystal = null;
	@GameRegistry.ObjectHolder("dustopia_grass")
	public static final GrassBlock grassDustopia = null;
	@GameRegistry.ObjectHolder("gardencia_grass")
	public static final GrassBlock grassGardencia = null;
	@GameRegistry.ObjectHolder("dirtGreckon")
	public static final GrassBlock grassGreckon = null;
	@GameRegistry.ObjectHolder("dirtHaven")
	public static final GrassBlock grassHaven = null;
	@GameRegistry.ObjectHolder("stoneIromine")
	public static final GrassBlock grassIromine = null;
	@GameRegistry.ObjectHolder("lelyetia_grass")
	public static final GrassBlock grassLelyetia = null;
	@GameRegistry.ObjectHolder("lelyetia_down_grass")
	public static final GrassBlock grassLelyetiaDown = null;
	@GameRegistry.ObjectHolder("lunalyte_grass")
	public static final GrassBlock grassLunalyte = null;
	@GameRegistry.ObjectHolder("lunasole_grass")
	public static final GrassBlock grassLunasole = null;
	@GameRegistry.ObjectHolder("marshmallow_grass")
	public static final GrassBlock grassMarshmallow = null;
	@GameRegistry.ObjectHolder("mysterium_grass")
	public static final GrassBlock grassMysterium = null;
	@GameRegistry.ObjectHolder("precasia_grass")
	public static final GrassBlock grassPrecasia = null;
	@GameRegistry.ObjectHolder("runic_grass")
	public static final GrassBlock grassRunic = null;
	@GameRegistry.ObjectHolder("shyrelands_grass")
	public static final GrassBlock grassShyrelands = null;
	@GameRegistry.ObjectHolder("silvro_grass")
	public static final GrassBlock grassSilvro = null;
	@GameRegistry.ObjectHolder("toxic_grass")
	public static final GrassBlock grassToxic = null;

	@GameRegistry.ObjectHolder("amethyst_ore")
	public static final OreBlock oreAmethyst = null;
	@GameRegistry.ObjectHolder("baronyte_ore")
	public static final OreBlock oreBaronyte = null;
	@GameRegistry.ObjectHolder("blazium_ore")
	public static final OreBlock oreBlazium = null;
	@GameRegistry.ObjectHolder("bloodstone_ore")
	public static final OreBlock oreBloodstone = null;
	@GameRegistry.ObjectHolder("blue_crystal_ore")
	public static final OreBlock oreBlueGemstone = null;
	@GameRegistry.ObjectHolder("charged_runium_ore")
	public static final OreBlock oreChargedRunium = null;
	@GameRegistry.ObjectHolder("chestbone_fragments_ore")
	public static final OreBlock oreChestboneFragments = null;
	@GameRegistry.ObjectHolder("crystallite_ore")
	public static final OreBlock oreCrystallite = null;
	@GameRegistry.ObjectHolder("elecanium_ore")
	public static final OreBlock oreElecanium = null;
	@GameRegistry.ObjectHolder("emberstone_ore")
	public static final OreBlock oreEmberstone = null;
	@GameRegistry.ObjectHolder("footbone_fragments_ore")
	public static final OreBlock oreFootboneFragments = null;
	@GameRegistry.ObjectHolder("gemenyte_ore")
	public static final OreBlock oreGemenyte = null;
	@GameRegistry.ObjectHolder("ghastly_ore")
	public static final OreBlock oreGhastly = null;
	@GameRegistry.ObjectHolder("ghoulish_ore")
	public static final OreBlock oreGhoulish = null;
	@GameRegistry.ObjectHolder("green_crystal_ore")
	public static final OreBlock oreGreenGemstone = null;
	@GameRegistry.ObjectHolder("jade_ore")
	public static final OreBlock oreJade = null;
	@GameRegistry.ObjectHolder("jewelyte_ore")
	public static final OreBlock oreJewelyte = null;
	@GameRegistry.ObjectHolder("legbone_fragments_ore")
	public static final OreBlock oreLegboneFragments = null;
	@GameRegistry.ObjectHolder("limonite_ore")
	public static final OreBlock oreLimonite = null;
	@GameRegistry.ObjectHolder("lyon_ore")
	public static final OreBlock oreLyon = null;
	@GameRegistry.ObjectHolder("mystite_ore")
	public static final OreBlock oreMystite = null;
	@GameRegistry.ObjectHolder("ornamyte_ore")
	public static final OreBlock oreOrnamyte = null;
	@GameRegistry.ObjectHolder("purple_crystal_ore")
	public static final OreBlock orePurpleGemstone = null;
	@GameRegistry.ObjectHolder("red_crystal_ore")
	public static final OreBlock oreRedGemstone = null;
	@GameRegistry.ObjectHolder("rosite_ore")
	public static final OreBlock oreRosite = null;
	@GameRegistry.ObjectHolder("runium_ore")
	public static final OreBlock oreRunium = null;
	@GameRegistry.ObjectHolder("sapphire_ore")
	public static final OreBlock oreSapphire = null;
	@GameRegistry.ObjectHolder("shyregem_ore")
	public static final OreBlock oreShyregem = null;
	@GameRegistry.ObjectHolder("shyrestone_ore")
	public static final OreBlock oreShyrestone = null;
	@GameRegistry.ObjectHolder("skullbone_fragments_ore")
	public static final OreBlock oreSkullboneFragments = null;
	@GameRegistry.ObjectHolder("varsium_ore")
	public static final OreBlock oreVarsium = null;
	@GameRegistry.ObjectHolder("white_crystal_ore")
	public static final OreBlock oreWhiteGemstone = null;
	@GameRegistry.ObjectHolder("yellow_crystal_ore")
	public static final OreBlock oreYellowGemstone = null;

	@GameRegistry.ObjectHolder("baron_bricks")
	public static final BasicBlock bricksBaron = null;
	@GameRegistry.ObjectHolder("black_bricks")
	public static final BasicBlock bricksBlack = null;
	@GameRegistry.ObjectHolder("bloodstone_bricks")
	public static final BasicBlock bricksBloodstone = null;
	@GameRegistry.ObjectHolder("blue_bricks")
	public static final BasicBlock bricksBlue = null;
	@GameRegistry.ObjectHolder("brown_bricks")
	public static final BasicBlock bricksBrown = null;
	@GameRegistry.ObjectHolder("coral_bricks")
	public static final BasicBlock bricksCoral = null;
	@GameRegistry.ObjectHolder("creeponia_bricks")
	public static final BasicBlock bricksCreeponia = null;
	@GameRegistry.ObjectHolder("crystallite_bricks")
	public static final BasicBlock bricksCrystallite = null;
	@GameRegistry.ObjectHolder("crystevia_bricks")
	public static final BasicBlock bricksCrystevia = null;
	@GameRegistry.ObjectHolder("cyan_bricks")
	public static final BasicBlock bricksCyan = null;
	@GameRegistry.ObjectHolder("dark_bricks")
	public static final BasicBlock bricksDark = null;
	@GameRegistry.ObjectHolder("dark_blue_bricks")
	public static final BasicBlock bricksDarkBlue = null;
	@GameRegistry.ObjectHolder("dark_grey_bricks")
	public static final BasicBlock bricksDarkGrey = null;
	@GameRegistry.ObjectHolder("darkwash_bricks")
	public static final BasicBlock bricksDarkwash = null;
	@GameRegistry.ObjectHolder("gardencia_bricks")
	public static final BasicBlock bricksGardencia = null;
	@GameRegistry.ObjectHolder("greckon_bricks")
	public static final BasicBlock bricksGreckon = null;
	@GameRegistry.ObjectHolder("green_bricks")
	public static final BasicBlock bricksGreen = null;
	@GameRegistry.ObjectHolder("grey_bricks")
	public static final BasicBlock bricksGrey = null;
	@GameRegistry.ObjectHolder("haunted_bricks")
	public static final BasicBlock bricksHaunted = null;
	@GameRegistry.ObjectHolder("iro_dotted_bricks")
	public static final BasicBlock bricksIroDotted = null;
	@GameRegistry.ObjectHolder("iro_striped_bricks")
	public static final BasicBlock bricksIroStriped = null;
	@GameRegistry.ObjectHolder("lelyetia_bricks")
	public static final BasicBlock bricksLelyetia = null;
	@GameRegistry.ObjectHolder("lime_bricks")
	public static final BasicBlock bricksLime = null;
	@GameRegistry.ObjectHolder("lunar_bricks")
	public static final BasicBlock bricksLunar = null;
	@GameRegistry.ObjectHolder("magenta_bricks")
	public static final BasicBlock bricksMagenta = null;
	@GameRegistry.ObjectHolder("black_mysterium_bricks")
	public static final BasicBlock bricksMysteriumBlack = null;
	@GameRegistry.ObjectHolder("green_mysterium_bricks")
	public static final BasicBlock bricksMysteriumGreen = null;
	@GameRegistry.ObjectHolder("orange_bricks")
	public static final BasicBlock bricksOrange = null;
	@GameRegistry.ObjectHolder("pink_bricks")
	public static final BasicBlock bricksPink = null;
	@GameRegistry.ObjectHolder("purple_bricks")
	public static final BasicBlock bricksPurple = null;
	@GameRegistry.ObjectHolder("red_bricks")
	public static final BasicBlock bricksRed = null;
	@GameRegistry.ObjectHolder("rosidian_bricks")
	public static final BasicBlock bricksRosidian = null;
	@GameRegistry.ObjectHolder("runic_construct_bricks")
	public static final BasicBlock bricksRunicConstruct = null;
	@GameRegistry.ObjectHolder("white_shyre_bricks")
	public static final BasicBlock bricksShyreWhite = null;
	@GameRegistry.ObjectHolder("yellow_shyre_bricks")
	public static final BasicBlock bricksShyreYellow = null;
	@GameRegistry.ObjectHolder("skeletal_bricks")
	public static final BasicBlock bricksSkeletal = null;
	@GameRegistry.ObjectHolder("white_bricks")
	public static final BasicBlock bricksWhite = null;
	@GameRegistry.ObjectHolder("whitewash_bricks")
	public static final BasicBlock bricksWhitewash = null;
	@GameRegistry.ObjectHolder("yellow_bricks")
	public static final BasicBlock bricksYellow = null;

	@GameRegistry.ObjectHolder("intricate_amethyst_ivory")
	public static final BasicBlock ivoryAmethystIntricate = null;
	@GameRegistry.ObjectHolder("natural_amethyst_ivory")
	public static final BasicBlock ivoryAmethystNatural = null;
	@GameRegistry.ObjectHolder("ornate_amethyst_ivory")
	public static final BasicBlock ivoryAmethystOrnate = null;
	@GameRegistry.ObjectHolder("patterned_amethyst_ivory")
	public static final BasicBlock ivoryAmethystPatterned = null;
	@GameRegistry.ObjectHolder("intricate_ivory")
	public static final BasicBlock ivoryIntricate = null;
	@GameRegistry.ObjectHolder("intricate_jade_ivory")
	public static final BasicBlock ivoryJadeIntricate = null;
	@GameRegistry.ObjectHolder("natural_jade_ivory")
	public static final BasicBlock ivoryJadeNatural = null;
	@GameRegistry.ObjectHolder("ornate_jade_ivory")
	public static final BasicBlock ivoryJadeOrnate = null;
	@GameRegistry.ObjectHolder("patterned_jade_ivory")
	public static final BasicBlock ivoryJadePatterned = null;
	@GameRegistry.ObjectHolder("intricate_limonite_ivory")
	public static final BasicBlock ivoryLimoniteIntricate = null;
	@GameRegistry.ObjectHolder("natural_limonite_ivory")
	public static final BasicBlock ivoryLimoniteNatural = null;
	@GameRegistry.ObjectHolder("ornate_limonite_ivory")
	public static final BasicBlock ivoryLimoniteOrnate = null;
	@GameRegistry.ObjectHolder("patterned_limonite_ivory")
	public static final BasicBlock ivoryLimonitePatterned = null;
	@GameRegistry.ObjectHolder("natural_ivory")
	public static final BasicBlock ivoryNatural = null;
	@GameRegistry.ObjectHolder("ornate_ivory")
	public static final BasicBlock ivoryOrnate = null;
	@GameRegistry.ObjectHolder("patterned_ivory")
	public static final BasicBlock ivoryPatterned = null;
	@GameRegistry.ObjectHolder("intricate_rosite_ivory")
	public static final BasicBlock ivoryRositeIntricate = null;
	@GameRegistry.ObjectHolder("natural_rosite_ivory")
	public static final BasicBlock ivoryRositeNatural = null;
	@GameRegistry.ObjectHolder("ornate_rosite_ivory")
	public static final BasicBlock ivoryRositeOrnate = null;
	@GameRegistry.ObjectHolder("patterned_rosite_ivory")
	public static final BasicBlock ivoryRositePatterned = null;
	@GameRegistry.ObjectHolder("intricate_sapphire_ivory")
	public static final BasicBlock ivorySapphireIntricate = null;
	@GameRegistry.ObjectHolder("natural_sapphire_ivory")
	public static final BasicBlock ivorySapphireNatural = null;
	@GameRegistry.ObjectHolder("ornate_sapphire_ivory")
	public static final BasicBlock ivorySapphireOrnate = null;
	@GameRegistry.ObjectHolder("patterned_sapphire_ivory")
	public static final BasicBlock ivorySapphirePatterned = null;

	@GameRegistry.ObjectHolder("achony_leaves")
	public static final LeavesBlock leavesAchony = null;
	@GameRegistry.ObjectHolder("blood_leaves")
	public static final LeavesBlock leavesBlood = null;
	@GameRegistry.ObjectHolder("bubble_leaves")
	public static final LeavesBlock leavesBubble = null;
	@GameRegistry.ObjectHolder("blue_celevus_leaves")
	public static final LeavesBlock leavesCelevusBlue = null;
	@GameRegistry.ObjectHolder("green_celevus_leaves")
	public static final LeavesBlock leavesCelevusGreen = null;
	@GameRegistry.ObjectHolder("purple_celevus_leaves")
	public static final LeavesBlock leavesCelevusPurple = null;
	@GameRegistry.ObjectHolder("red_celevus_leaves")
	public static final LeavesBlock leavesCelevusRed = null;
	@GameRegistry.ObjectHolder("white_celevus_leaves")
	public static final LeavesBlock leavesCelevusWhite = null;
	@GameRegistry.ObjectHolder("yellow_celevus_leaves")
	public static final LeavesBlock leavesCelevusYellow = null;
	@GameRegistry.ObjectHolder("churry_leaves")
	public static final LeavesBlock leavesChurry = null;
	@GameRegistry.ObjectHolder("creep_leaves")
	public static final LeavesBlock leavesCreep = null;
	@GameRegistry.ObjectHolder("cycade_leaves")
	public static final LeavesBlock leavesCycade = null;
	@GameRegistry.ObjectHolder("dawn_leaves")
	public static final LeavesBlock leavesDawn = null;
	@GameRegistry.ObjectHolder("domiguous_leaves")
	public static final LeavesBlock leavesDomiguous = null;
	@GameRegistry.ObjectHolder("eternal_leaves")
	public static final LeavesBlock leavesEternal = null;
	@GameRegistry.ObjectHolder("eucadon_leaves")
	public static final LeavesBlock leavesEucadon = null;
	@GameRegistry.ObjectHolder("haunted_leaves")
	public static final LeavesBlock leavesHaunted = null;
	@GameRegistry.ObjectHolder("haunted_eyes_leaves")
	public static final LeavesBlock leavesHauntedEyes = null;
	@GameRegistry.ObjectHolder("blue_haven_leaves")
	public static final LeavesBlock leavesHavenBlue = null;
	@GameRegistry.ObjectHolder("pink_haven_leaves")
	public static final LeavesBlock leavesHavenPink = null;
	@GameRegistry.ObjectHolder("purple_haven_leaves")
	public static final LeavesBlock leavesHavenPurple = null;
	@GameRegistry.ObjectHolder("red_haven_leaves")
	public static final LeavesBlock leavesHavenRed = null;
	@GameRegistry.ObjectHolder("turquoise_haven_leaves")
	public static final LeavesBlock leavesHavenTurquoise = null;
	@GameRegistry.ObjectHolder("yellow_haven_leaves")
	public static final LeavesBlock leavesHavenYellow = null;
	@GameRegistry.ObjectHolder("irodust_leaves")
	public static final LeavesBlock leavesIrodust = null;
	@GameRegistry.ObjectHolder("irogold_leaves")
	public static final LeavesBlock leavesIrogold = null;
	@GameRegistry.ObjectHolder("lelyetian_leaves")
	public static final LeavesBlock leavesLelyetian = null;
	@GameRegistry.ObjectHolder("lucalus_leaves")
	public static final LeavesBlock leavesLucalus = null;
	@GameRegistry.ObjectHolder("lunicia_leaves")
	public static final LeavesBlock leavesLunicia = null;
	@GameRegistry.ObjectHolder("lunosso_leaves")
	public static final LeavesBlock leavesLunosso = null;
	@GameRegistry.ObjectHolder("melumia_leaves")
	public static final LeavesBlock leavesMelumia = null;
	@GameRegistry.ObjectHolder("opollo_leaves")
	public static final LeavesBlock leavesOpollo = null;
	@GameRegistry.ObjectHolder("runic_leaves")
	public static final LeavesBlock leavesRunic = null;
	@GameRegistry.ObjectHolder("shadow_leaves")
	public static final LeavesBlock leavesShadow = null;
	@GameRegistry.ObjectHolder("shadowblood_leaves")
	public static final LeavesBlock leavesShadowblood = null;
	@GameRegistry.ObjectHolder("shyre_leaves")
	public static final LeavesBlock leavesShyre = null;
	@GameRegistry.ObjectHolder("bright_shyre_leaves")
	public static final LeavesBlock leavesShyreBright = null;
	@GameRegistry.ObjectHolder("silvro_leaves")
	public static final LeavesBlock leavesSilvro = null;
	@GameRegistry.ObjectHolder("stranglewood_leaves")
	public static final LeavesBlock leavesStranglewood = null;
	@GameRegistry.ObjectHolder("vein_leaves")
	public static final LeavesBlock leavesVein = null;

	@GameRegistry.ObjectHolder("achony_log")
	public static final LogBlock logAchony = null;
	@GameRegistry.ObjectHolder("blood_log")
	public static final LogBlock logBlood = null;
	@GameRegistry.ObjectHolder("churry_log")
	public static final LogBlock logChurry = null;
	@GameRegistry.ObjectHolder("creep_log")
	public static final LogBlock logCreep = null;
	@GameRegistry.ObjectHolder("cycade_log")
	public static final LogBlock logCycade = null;
	@GameRegistry.ObjectHolder("dawn_log")
	public static final LogBlock logDawn = null;
	@GameRegistry.ObjectHolder("domiguous_log")
	public static final LogBlock logDomiguous = null;
	@GameRegistry.ObjectHolder("eternal_log")
	public static final LogBlock logEternal = null;
	@GameRegistry.ObjectHolder("eucadon_log")
	public static final LogBlock logEucadon = null;
	@GameRegistry.ObjectHolder("eyeball_log")
	public static final LogBlock logEyeball = null;
	@GameRegistry.ObjectHolder("haunted_log")
	public static final LogBlock logHaunted = null;
	@GameRegistry.ObjectHolder("haunted_eye_log")
	public static final LogBlock logHauntedEye = null;
	@GameRegistry.ObjectHolder("haunted_eyes_log")
	public static final LogBlock logHauntedEyes = null;
	@GameRegistry.ObjectHolder("haunted_flashing_log")
	public static final LogBlock logHauntedFlashing = null;
	@GameRegistry.ObjectHolder("haunted_purpling_log")
	public static final LogBlock logHauntedPurpling = null;
	@GameRegistry.ObjectHolder("iro_log")
	public static final LogBlock logIro = null;
	@GameRegistry.ObjectHolder("lucalus_log")
	public static final LogBlock logLucalus = null;
	@GameRegistry.ObjectHolder("lunide_log")
	public static final LogBlock logLunide = null;
	@GameRegistry.ObjectHolder("melumia_log")
	public static final LogBlock logMelumia = null;
	@GameRegistry.ObjectHolder("opollo_log")
	public static final LogBlock logOpollo = null;
	@GameRegistry.ObjectHolder("runic_log")
	public static final LogBlock logRunic = null;
	@GameRegistry.ObjectHolder("shadow_log")
	public static final LogBlock logShadow = null;
	@GameRegistry.ObjectHolder("shyre_log")
	public static final LogBlock logShyre = null;
	@GameRegistry.ObjectHolder("stranglewood_log")
	public static final LogBlock logStranglewood = null;
	@GameRegistry.ObjectHolder("toxic_log")
	public static final LogBlock logToxic = null;

	@GameRegistry.ObjectHolder("achony_planks")
	public static final BasicBlock planksAchony = null;
	@GameRegistry.ObjectHolder("bloodwood_planks")
	public static final BasicBlock planksBloodwood = null;
	@GameRegistry.ObjectHolder("churry_planks")
	public static final BasicBlock planksChurry = null;
	@GameRegistry.ObjectHolder("creep_planks")
	public static final BasicBlock planksCreep = null;
	@GameRegistry.ObjectHolder("cycade_planks")
	public static final BasicBlock planksCycade = null;
	@GameRegistry.ObjectHolder("dawnwood_planks")
	public static final BasicBlock planksDawnwood = null;
	@GameRegistry.ObjectHolder("domiguous_planks")
	public static final BasicBlock planksDomiguous = null;
	@GameRegistry.ObjectHolder("eucadon_planks")
	public static final BasicBlock planksEucadon = null;
	@GameRegistry.ObjectHolder("hauntedwood_planks")
	public static final BasicBlock planksHauntedwood = null;
	@GameRegistry.ObjectHolder("irowood_planks")
	public static final BasicBlock planksIrowood = null;
	@GameRegistry.ObjectHolder("lucalus_planks")
	public static final BasicBlock planksLucalus = null;
	@GameRegistry.ObjectHolder("lunide_planks")
	public static final BasicBlock planksLunide = null;
	@GameRegistry.ObjectHolder("melumia_planks")
	public static final BasicBlock planksMelumia = null;
	@GameRegistry.ObjectHolder("opollo_planks")
	public static final BasicBlock planksOpollo = null;
	@GameRegistry.ObjectHolder("runic_planks")
	public static final BasicBlock planksRunic = null;
	@GameRegistry.ObjectHolder("shadow_planks")
	public static final BasicBlock planksShadow = null;
	@GameRegistry.ObjectHolder("shyre_planks")
	public static final BasicBlock planksShyre = null;
	@GameRegistry.ObjectHolder("stranglewood_planks")
	public static final BasicBlock planksStranglewood = null;
	@GameRegistry.ObjectHolder("toxicwood_planks")
	public static final BasicBlock planksToxicwood = null;

	@GameRegistry.ObjectHolder("double_achony_slab")
	public static final SlabBlock.DoubleSlabBlock slabAchony = null;
	@GameRegistry.ObjectHolder("double_baron_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabBaronBricks = null;
	@GameRegistry.ObjectHolder("double_black_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabBlackBricks = null;
	@GameRegistry.ObjectHolder("double_bloodstone_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabBloodstoneBricks = null;
	@GameRegistry.ObjectHolder("double_bloodwood_slab")
	public static final SlabBlock.DoubleSlabBlock slabBloodwood = null;
	@GameRegistry.ObjectHolder("double_blue_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabBlueBricks = null;
	@GameRegistry.ObjectHolder("double_brown_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabBrownBricks = null;
	@GameRegistry.ObjectHolder("double_churry_slab")
	public static final SlabBlock.DoubleSlabBlock slabChurry = null;
	@GameRegistry.ObjectHolder("double_coral_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabCoralBricks = null;
	@GameRegistry.ObjectHolder("double_creep_slab")
	public static final SlabBlock.DoubleSlabBlock slabCreep = null;
	@GameRegistry.ObjectHolder("double_creeponia_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabCreeponiaBricks = null;
	@GameRegistry.ObjectHolder("double_crystallite_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabCrystalliteBricks = null;
	@GameRegistry.ObjectHolder("double_crystevia_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabCrysteviaBricks = null;
	@GameRegistry.ObjectHolder("double_cyan_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabCyanBricks = null;
	@GameRegistry.ObjectHolder("double_cycade_slab")
	public static final SlabBlock.DoubleSlabBlock slabCycade = null;
	@GameRegistry.ObjectHolder("double_dark_blue_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabDarkBlueBricks = null;
	@GameRegistry.ObjectHolder("double_dark_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabDarkBricks = null;
	@GameRegistry.ObjectHolder("double_dark_grey_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabDarkGreyBricks = null;
	@GameRegistry.ObjectHolder("double_darkwash_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabDarkwashBricks = null;
	@GameRegistry.ObjectHolder("double_dawnwood_slab")
	public static final SlabBlock.DoubleSlabBlock slabDawnwood = null;
	@GameRegistry.ObjectHolder("double_domiguous_slab")
	public static final SlabBlock.DoubleSlabBlock slabDomiguous = null;
	@GameRegistry.ObjectHolder("double_eucadon_slab")
	public static final SlabBlock.DoubleSlabBlock slabEucadon = null;
	@GameRegistry.ObjectHolder("double_gardencia_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabGardenciaBricks = null;
	@GameRegistry.ObjectHolder("double_greckon_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabGreckonBricks = null;
	@GameRegistry.ObjectHolder("double_green_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabGreenBricks = null;
	@GameRegistry.ObjectHolder("double_grey_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabGreyBricks = null;
	@GameRegistry.ObjectHolder("double_haunted_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabHauntedBricks = null;
	@GameRegistry.ObjectHolder("double_hauntedwood_slab")
	public static final SlabBlock.DoubleSlabBlock slabHauntedwood = null;
	@GameRegistry.ObjectHolder("double_iro_dotted_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabIroDottedBricks = null;
	@GameRegistry.ObjectHolder("double_iro_striped_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabIroStripedBricks = null;
	@GameRegistry.ObjectHolder("double_irowood_slab")
	public static final SlabBlock.DoubleSlabBlock slabIrowood = null;
	@GameRegistry.ObjectHolder("double_intricate_amethyst_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryAmethystIntricate = null;
	@GameRegistry.ObjectHolder("double_natural_amethyst_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryAmethystNatural = null;
	@GameRegistry.ObjectHolder("double_ornate_amethyst_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryAmethystOrnate = null;
	@GameRegistry.ObjectHolder("double_patterned_amethyst_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryAmethystPatterned  = null;
	@GameRegistry.ObjectHolder("double_intricate_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryIntricate = null;
	@GameRegistry.ObjectHolder("double_intricate_jade_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryJadeIntricate = null;
	@GameRegistry.ObjectHolder("double_natural_jade_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryJadeNatural = null;
	@GameRegistry.ObjectHolder("double_ornate_jade_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryJadeOrnate = null;
	@GameRegistry.ObjectHolder("double_patterned_jade_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryJadePatterned = null;
	@GameRegistry.ObjectHolder("double_intricate_limonite_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryLimoniteIntricate = null;
	@GameRegistry.ObjectHolder("double_natural_limonite_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryLimoniteNatural = null;
	@GameRegistry.ObjectHolder("double_ornate_limonite_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryLimoniteOrnate = null;
	@GameRegistry.ObjectHolder("double_patterned_limonite_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryLimonitePatterned = null;
	@GameRegistry.ObjectHolder("double_natural_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryNatural = null;
	@GameRegistry.ObjectHolder("double_ornate_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryOrnate = null;
	@GameRegistry.ObjectHolder("double_patterned_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryPatterned = null;
	@GameRegistry.ObjectHolder("double_intricate_rosite_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryRositeIntricate = null;
	@GameRegistry.ObjectHolder("double_natural_rosite_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryRositeNatural = null;
	@GameRegistry.ObjectHolder("double_ornate_rosite_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryRositeOrnate = null;
	@GameRegistry.ObjectHolder("double_patterned_rosite_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvoryRositePatterned = null;
	@GameRegistry.ObjectHolder("double_intricate_sapphire_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvorySapphireIntricate = null;
	@GameRegistry.ObjectHolder("double_natural_sapphire_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvorySapphireNatural = null;
	@GameRegistry.ObjectHolder("double_ornate_sapphire_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvorySapphireOrnate = null;
	@GameRegistry.ObjectHolder("double_patterned_sapphire_ivory_slab")
	public static final SlabBlock.DoubleSlabBlock slabIvorySapphirePatterned = null;
	@GameRegistry.ObjectHolder("double_lelyetia_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabLelyetiaBricks = null;
	@GameRegistry.ObjectHolder("double_lime_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabLimeBricks = null;
	@GameRegistry.ObjectHolder("double_lucalus_slab")
	public static final SlabBlock.DoubleSlabBlock slabLucalus = null;
	@GameRegistry.ObjectHolder("double_lunar_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabLunarBricks = null;
	@GameRegistry.ObjectHolder("double_lunide_slab")
	public static final SlabBlock.DoubleSlabBlock slabLunide = null;
	@GameRegistry.ObjectHolder("double_magenta_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabMagentaBricks = null;
	@GameRegistry.ObjectHolder("double_melumia_slab")
	public static final SlabBlock.DoubleSlabBlock slabMelumia = null;
	@GameRegistry.ObjectHolder("double_black_mysterium_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabMysteriumBlackBricks = null;
	@GameRegistry.ObjectHolder("double_green_mysterium_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabMysteriumGreenBricks = null;
	@GameRegistry.ObjectHolder("double_opollo_slab")
	public static final SlabBlock.DoubleSlabBlock slabOpollo = null;
	@GameRegistry.ObjectHolder("double_orange_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabOrangeBricks = null;
	@GameRegistry.ObjectHolder("double_pink_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabPinkBricks = null;
	@GameRegistry.ObjectHolder("double_purple_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabPurpleBricks = null;
	@GameRegistry.ObjectHolder("double_red_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabRedBricks = null;
	@GameRegistry.ObjectHolder("double_rosidian_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabRosidianBricks = null;
	@GameRegistry.ObjectHolder("double_runic_slab")
	public static final SlabBlock.DoubleSlabBlock slabRunic = null;
	@GameRegistry.ObjectHolder("double_runic_construct_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabRunicConstructBricks = null;
	@GameRegistry.ObjectHolder("double_shadow_slab")
	public static final SlabBlock.DoubleSlabBlock slabShadow = null;
	@GameRegistry.ObjectHolder("double_shyre_slab")
	public static final SlabBlock.DoubleSlabBlock slabShyre = null;
	@GameRegistry.ObjectHolder("double_white_shyre_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabShyreWhiteBricks = null;
	@GameRegistry.ObjectHolder("double_yellow_shyre_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabShyreYellowBricks = null;
	@GameRegistry.ObjectHolder("double_skeletal_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabSkeletalBricks = null;
	@GameRegistry.ObjectHolder("double_stranglewood_slab")
	public static final SlabBlock.DoubleSlabBlock slabStranglewood = null;
	@GameRegistry.ObjectHolder("double_toxicwood_slab")
	public static final SlabBlock.DoubleSlabBlock slabToxicwood = null;
	@GameRegistry.ObjectHolder("double_whitewash_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabWhitewashBricks = null;
	@GameRegistry.ObjectHolder("double_yellow_bricks_slab")
	public static final SlabBlock.DoubleSlabBlock slabYellowBricks = null;

	@GameRegistry.ObjectHolder("achony_stairs")
	public static final StairsBlock stairsAchony = null;
	@GameRegistry.ObjectHolder("baron_bricks_stairs")
	public static final StairsBlock stairsBaronBricks = null;
	@GameRegistry.ObjectHolder("black_bricks_stairs")
	public static final StairsBlock stairsBlackBricks = null;
	@GameRegistry.ObjectHolder("bloodstone_bricks_stairs")
	public static final StairsBlock stairsBloodstoneBricks = null;
	@GameRegistry.ObjectHolder("bloodwood_stairs")
	public static final StairsBlock stairsBloodwood = null;
	@GameRegistry.ObjectHolder("blue_bricks_stairs")
	public static final StairsBlock stairsBlueBricks = null;
	@GameRegistry.ObjectHolder("brown_bricks_stairs")
	public static final StairsBlock stairsBrownBricks = null;
	@GameRegistry.ObjectHolder("churry_stairs")
	public static final StairsBlock stairsChurry = null;
	@GameRegistry.ObjectHolder("coral_bricks_stairs")
	public static final StairsBlock stairsCoralBricks = null;
	@GameRegistry.ObjectHolder("creep_stairs")
	public static final StairsBlock stairsCreep = null;
	@GameRegistry.ObjectHolder("creeponia_bricks_stairs")
	public static final StairsBlock stairsCreeponiaBricks = null;
	@GameRegistry.ObjectHolder("crystallite_bricks_stairs")
	public static final StairsBlock stairsCrystalliteBricks = null;
	@GameRegistry.ObjectHolder("crystevia_bricks_stairs")
	public static final StairsBlock stairsCrysteviaBricks = null;
	@GameRegistry.ObjectHolder("cyan_bricks_stairs")
	public static final StairsBlock stairsCyanBricks = null;
	@GameRegistry.ObjectHolder("cycade_stairs")
	public static final StairsBlock stairsCycade = null;
	@GameRegistry.ObjectHolder("dark_blue_bricks_stairs")
	public static final StairsBlock stairsDarkBlueBricks = null;
	@GameRegistry.ObjectHolder("dark_bricks_stairs")
	public static final StairsBlock stairsDarkBricks = null;
	@GameRegistry.ObjectHolder("dark_grey_bricks_stairs")
	public static final StairsBlock stairsDarkGreyBricks = null;
	@GameRegistry.ObjectHolder("darkwash_bricks_stairs")
	public static final StairsBlock stairsDarkwashBricks = null;
	@GameRegistry.ObjectHolder("dawnwood_stairs")
	public static final StairsBlock stairsDawnwood = null;
	@GameRegistry.ObjectHolder("domiguous_stairs")
	public static final StairsBlock stairsDomiguous = null;
	@GameRegistry.ObjectHolder("eucadon_stairs")
	public static final StairsBlock stairsEucadon = null;
	@GameRegistry.ObjectHolder("gardencia_bricks_stairs")
	public static final StairsBlock stairsGardenciaBricks = null;
	@GameRegistry.ObjectHolder("greckon_bricks_stairs")
	public static final StairsBlock stairsGreckonBricks = null;
	@GameRegistry.ObjectHolder("green_bricks_stairs")
	public static final StairsBlock stairsGreenBricks = null;
	@GameRegistry.ObjectHolder("grey_bricks_stairs")
	public static final StairsBlock stairsGreyBricks = null;
	@GameRegistry.ObjectHolder("haunted_bricks_stairs")
	public static final StairsBlock stairsHauntedBricks = null;
	@GameRegistry.ObjectHolder("hauntedwood_stairs")
	public static final StairsBlock stairsHauntedwood = null;
	@GameRegistry.ObjectHolder("iro_dotted_bricks_stairs")
	public static final StairsBlock stairsIroDottedBricks = null;
	@GameRegistry.ObjectHolder("iro_striped_bricks_stairs")
	public static final StairsBlock stairsIroStripedBricks = null;
	@GameRegistry.ObjectHolder("irowood_stairs")
	public static final StairsBlock stairsIrowood = null;
	@GameRegistry.ObjectHolder("intricate_amethyst_ivory_stairs")
	public static final StairsBlock stairsIvoryAmethystIntricate = null;
	@GameRegistry.ObjectHolder("natural_amethyst_ivory_stairs")
	public static final StairsBlock stairsIvoryAmethystNatural = null;
	@GameRegistry.ObjectHolder("ornate_amethyst_ivory_stairs")
	public static final StairsBlock stairsIvoryAmethystOrnate = null;
	@GameRegistry.ObjectHolder("patterned_amethyst_ivory_stairs")
	public static final StairsBlock stairsIvoryAmethystPatterned = null;
	@GameRegistry.ObjectHolder("intricate_ivory_stairs")
	public static final StairsBlock stairsIvoryIntricate = null;
	@GameRegistry.ObjectHolder("intricate_jade_ivory_stairs")
	public static final StairsBlock stairsIvoryJadeIntricate = null;
	@GameRegistry.ObjectHolder("natural_jade_ivory_stairs")
	public static final StairsBlock stairsIvoryJadeNatural = null;
	@GameRegistry.ObjectHolder("ornate_jade_ivory_stairs")
	public static final StairsBlock stairsIvoryJadeOrnate = null;
	@GameRegistry.ObjectHolder("patterned_jade_ivory_stairs")
	public static final StairsBlock stairsIvoryJadePatterned = null;
	@GameRegistry.ObjectHolder("intricate_limonite_ivory_stairs")
	public static final StairsBlock stairsIvoryLimoniteIntricate = null;
	@GameRegistry.ObjectHolder("natural_limonite_ivory_stairs")
	public static final StairsBlock stairsIvoryLimoniteNatural = null;
	@GameRegistry.ObjectHolder("ornate_limonite_ivory_stairs")
	public static final StairsBlock stairsIvoryLimoniteOrnate = null;
	@GameRegistry.ObjectHolder("patterned_limonite_ivory_stairs")
	public static final StairsBlock stairsIvoryLimonitePatterned = null;
	@GameRegistry.ObjectHolder("natural_ivory_stairs")
	public static final StairsBlock stairsIvoryNatural = null;
	@GameRegistry.ObjectHolder("ornate_ivory_stairs")
	public static final StairsBlock stairsIvoryOrnate = null;
	@GameRegistry.ObjectHolder("patterned_ivory_stairs")
	public static final StairsBlock stairsIvoryPatterned = null;
	@GameRegistry.ObjectHolder("intricate_rosite_ivory_stairs")
	public static final StairsBlock stairsIvoryRositeIntricate = null;
	@GameRegistry.ObjectHolder("natural_rosite_ivory_stairs")
	public static final StairsBlock stairsIvoryRositeNatural = null;
	@GameRegistry.ObjectHolder("ornate_rosite_ivory_stairs")
	public static final StairsBlock stairsIvoryRositeOrnate = null;
	@GameRegistry.ObjectHolder("patterned_rosite_ivory_stairs")
	public static final StairsBlock stairsIvoryRositePatterned = null;
	@GameRegistry.ObjectHolder("intricate_sapphire_ivory_stairs")
	public static final StairsBlock stairsIvorySapphireIntricate = null;
	@GameRegistry.ObjectHolder("natural_sapphire_ivory_stairs")
	public static final StairsBlock stairsIvorySapphireNatural = null;
	@GameRegistry.ObjectHolder("ornate_sapphire_ivory_stairs")
	public static final StairsBlock stairsIvorySapphireOrnate = null;
	@GameRegistry.ObjectHolder("patterned_sapphire_ivory_stairs")
	public static final StairsBlock stairsIvorySapphirePatterned = null;
	@GameRegistry.ObjectHolder("lelyetia_bricks_stairs")
	public static final StairsBlock stairsLelyetiaBricks = null;
	@GameRegistry.ObjectHolder("lime_bricks_stairs")
	public static final StairsBlock stairsLimeBricks = null;
	@GameRegistry.ObjectHolder("lucalus_stairs")
	public static final StairsBlock stairsLucalus = null;
	@GameRegistry.ObjectHolder("lunar_bricks_stairs")
	public static final StairsBlock stairsLunarBricks = null;
	@GameRegistry.ObjectHolder("lunide_stairs")
	public static final StairsBlock stairsLunide = null;
	@GameRegistry.ObjectHolder("magenta_bricks_stairs")
	public static final StairsBlock stairsMagentaBricks = null;
	@GameRegistry.ObjectHolder("melumia_stairs")
	public static final StairsBlock stairsMelumia = null;
	@GameRegistry.ObjectHolder("black_mysterium_bricks_stairs")
	public static final StairsBlock stairsMysteriumBlackBricks = null;
	@GameRegistry.ObjectHolder("green_mysterium_bricks_stairs")
	public static final StairsBlock stairsMysteriumGreenBricks = null;
	@GameRegistry.ObjectHolder("opollo_stairs")
	public static final StairsBlock stairsOpollo = null;
	@GameRegistry.ObjectHolder("orange_bricks_stairs")
	public static final StairsBlock stairsOrangeBricks = null;
	@GameRegistry.ObjectHolder("pink_bricks_stairs")
	public static final StairsBlock stairsPinkBricks = null;
	@GameRegistry.ObjectHolder("purple_bricks_stairs")
	public static final StairsBlock stairsPurpleBricks = null;
	@GameRegistry.ObjectHolder("red_bricks_stairs")
	public static final StairsBlock stairsRedBricks = null;
	@GameRegistry.ObjectHolder("rosidian_bricks_stairs")
	public static final StairsBlock stairsRosidianBricks = null;
	@GameRegistry.ObjectHolder("runic_stairs")
	public static final StairsBlock stairsRunic = null;
	@GameRegistry.ObjectHolder("runic_construct_bricks_stairs")
	public static final StairsBlock stairsRunicConstructBricks = null;
	@GameRegistry.ObjectHolder("shadow_stairs")
	public static final StairsBlock stairsShadow = null;
	@GameRegistry.ObjectHolder("shyre_stairs")
	public static final StairsBlock stairsShyre = null;
	@GameRegistry.ObjectHolder("white_shyre_bricks_stairs")
	public static final StairsBlock stairsShyreWhiteBricks = null;
	@GameRegistry.ObjectHolder("yellow_shyre_bricks_stairs")
	public static final StairsBlock stairsShyreYellowBricks = null;
	@GameRegistry.ObjectHolder("skeletal_bricks_stairs")
	public static final StairsBlock stairsSkeletalBricks = null;
	@GameRegistry.ObjectHolder("stranglewood_stairs")
	public static final StairsBlock stairsStranglewood = null;
	@GameRegistry.ObjectHolder("toxicwood_stairs")
	public static final StairsBlock stairsToxicwood = null;
	@GameRegistry.ObjectHolder("whitewash_bricks_stairs")
	public static final StairsBlock stairsWhitewashBricks = null;
	@GameRegistry.ObjectHolder("yellow_bricks_stairs")
	public static final StairsBlock stairsYellowBricks = null;

	@GameRegistry.ObjectHolder("achony_fence")
	public static final FenceBlock fenceAchony = null;
	@GameRegistry.ObjectHolder("achony_fence")
	public static final FenceBlock fenceBloodwood = null;
	@GameRegistry.ObjectHolder("achony_fence")
	public static final FenceBlock fenceChurry = null;
	@GameRegistry.ObjectHolder("achony_fence")
	public static final FenceBlock fenceCreep = null;
	@GameRegistry.ObjectHolder("achony_fence")
	public static final FenceBlock fenceCycade = null;
	@GameRegistry.ObjectHolder("achony_fence")
	public static final FenceBlock fenceDawnwood = null;
	@GameRegistry.ObjectHolder("achony_fence")
	public static final FenceBlock fenceDomiguous = null;
	@GameRegistry.ObjectHolder("eucadon_fence")
	public static final FenceBlock fenceEucadon = null;
	@GameRegistry.ObjectHolder("hauntedwood_fence")
	public static final FenceBlock fenceHauntedwood = null;
	@GameRegistry.ObjectHolder("irowood_fence")
	public static final FenceBlock fenceIrowood = null;
	@GameRegistry.ObjectHolder("lucalus_fence")
	public static final FenceBlock fenceLucalus = null;
	@GameRegistry.ObjectHolder("lunide_fence")
	public static final FenceBlock fenceLunide = null;
	@GameRegistry.ObjectHolder("melumia_fence")
	public static final FenceBlock fenceMelumia = null;
	@GameRegistry.ObjectHolder("opollo_fence")
	public static final FenceBlock fenceOpollo = null;
	@GameRegistry.ObjectHolder("runic_fence")
	public static final FenceBlock fenceRunic = null;
	@GameRegistry.ObjectHolder("shadow_fence")
	public static final FenceBlock fenceShadow = null;
	@GameRegistry.ObjectHolder("shyre_fence")
	public static final FenceBlock fenceShyre = null;
	@GameRegistry.ObjectHolder("stranglewood_fence")
	public static final FenceBlock fenceStranglewood = null;
	@GameRegistry.ObjectHolder("toxicwood_fence")
	public static final FenceBlock fenceToxicwood = null;
	@GameRegistry.ObjectHolder("twinklestone_fence")
	public static final FenceBlock fenceTwinklestone = null;

	@GameRegistry.ObjectHolder("achony_gate")
	public static final GateBlock gateAchony = null;
	@GameRegistry.ObjectHolder("bloodwood_gate")
	public static final GateBlock gateBloodwood = null;
	@GameRegistry.ObjectHolder("churry_gate")
	public static final GateBlock gateChurry = null;
	@GameRegistry.ObjectHolder("creep_gate")
	public static final GateBlock gateCreep = null;
	@GameRegistry.ObjectHolder("cycade_gate")
	public static final GateBlock gateCycade = null;
	@GameRegistry.ObjectHolder("dawnwood_gate")
	public static final GateBlock gateDawnwood = null;
	@GameRegistry.ObjectHolder("domiguous_gate")
	public static final GateBlock gateDomiguous = null;
	@GameRegistry.ObjectHolder("eucadon_gate")
	public static final GateBlock gateEucadon = null;
	@GameRegistry.ObjectHolder("hauntedwood_gate")
	public static final GateBlock gateHauntedwood = null;
	@GameRegistry.ObjectHolder("irowood_gate")
	public static final GateBlock gateIrowood = null;
	@GameRegistry.ObjectHolder("lucalus_gate")
	public static final GateBlock gateLucalus = null;
	@GameRegistry.ObjectHolder("lunide_gate")
	public static final GateBlock gateLunide = null;
	@GameRegistry.ObjectHolder("melumia_gate")
	public static final GateBlock gateMelumia = null;
	@GameRegistry.ObjectHolder("opollo_gate")
	public static final GateBlock gateOpollo = null;
	@GameRegistry.ObjectHolder("runic_gate")
	public static final GateBlock gateRunic = null;
	@GameRegistry.ObjectHolder("shadow_gate")
	public static final GateBlock gateShadow = null;
	@GameRegistry.ObjectHolder("shyre_gate")
	public static final GateBlock gateShyre = null;
	@GameRegistry.ObjectHolder("stranglewood_gate")
	public static final GateBlock gateStranglewood = null;
	@GameRegistry.ObjectHolder("toxicwood_gate")
	public static final GateBlock gateToxicwood = null;

	@GameRegistry.ObjectHolder("flower_core")
	public static final BasicBlock flowerCore = null;
	@GameRegistry.ObjectHolder("aqua_mushroom_inside")
	public static final BasicBlock mushroomAquaInside = null;
	@GameRegistry.ObjectHolder("aqua_mushroom_outside")
	public static final BasicBlock mushroomAquaOutside = null;
	@GameRegistry.ObjectHolder("black_mushroom")
	public static final BasicBlock mushroomBlack = null;
	@GameRegistry.ObjectHolder("blue_mushroom_inside")
	public static final BasicBlock mushroomBlueInside = null;
	@GameRegistry.ObjectHolder("blue_mushroom_outside")
	public static final BasicBlock mushroomBlueOutside = null;
	@GameRegistry.ObjectHolder("green_mushroom_inside")
	public static final BasicBlock mushroomGreenInside = null;
	@GameRegistry.ObjectHolder("green_mushroom_outside")
	public static final BasicBlock mushroomGreenOutside = null;
	@GameRegistry.ObjectHolder("orange_mushroom_inside")
	public static final BasicBlock mushroomOrangeInside = null;
	@GameRegistry.ObjectHolder("orange_mushroom_outside")
	public static final BasicBlock mushroomOrangeOutside = null;
	@GameRegistry.ObjectHolder("peach_mushroom_inside")
	public static final BasicBlock mushroomPeachInside = null;
	@GameRegistry.ObjectHolder("peach_mushroom_outside")
	public static final BasicBlock mushroomPeachOutside = null;
	@GameRegistry.ObjectHolder("purple_mushroom_inside")
	public static final BasicBlock mushroomPurpleInside = null;
	@GameRegistry.ObjectHolder("purple_mushroom_outside")
	public static final BasicBlock mushroomPurpleOutside = null;
	@GameRegistry.ObjectHolder("black_mushroom_stem")
	public static final BasicBlock mushroomStemBlack = null;
	@GameRegistry.ObjectHolder("blue_mushroom_stem")
	public static final BasicBlock mushroomStemBlue = null;
	@GameRegistry.ObjectHolder("green_mushroom_stem")
	public static final BasicBlock mushroomStemGreen = null;
	@GameRegistry.ObjectHolder("orange_mushroom_stem")
	public static final BasicBlock mushroomStemOrange = null;
	@GameRegistry.ObjectHolder("purple_mushroom_stem")
	public static final BasicBlock mushroomStemPurple = null;
	@GameRegistry.ObjectHolder("yellow_mushroom_stem")
	public static final BasicBlock mushroomStemYellow = null;
	@GameRegistry.ObjectHolder("yellow_mushroom_inside")
	public static final BasicBlock mushroomYellowInside = null;
	@GameRegistry.ObjectHolder("yellow_mushroom_outside")
	public static final BasicBlock mushroomYellowOutside = null;
	@GameRegistry.ObjectHolder("plant_stem")
	public static final BasicBlock plantStem = null;

	@GameRegistry.ObjectHolder("candied_water")
	public static final BasicFluidBlock candiedWater = null;

	@GameRegistry.ObjectHolder("ancient_light")
	public static final LightBlock lightAncient = null;
	@GameRegistry.ObjectHolder("archaic_light")
	public static final LightBlock lightArchaic = null;
	@GameRegistry.ObjectHolder("archaic_light_breakable")
	public static final LightBlock lightArchaicBreakable = null;
	@GameRegistry.ObjectHolder("creep_crystal")
	public static final LightBlock lightCreepCrystal = null;
	@GameRegistry.ObjectHolder("darkstone")
	public static final LightBlock lightDarkstone = null;
	@GameRegistry.ObjectHolder("deep_crystal")
	public static final LightBlock lightDeepCrystal = null;
	@GameRegistry.ObjectHolder("hive_light")
	public static final LightBlock lightHiveLight = null;
	@GameRegistry.ObjectHolder("steel_light")
	public static final LightBlock lightSteel = null;
	@GameRegistry.ObjectHolder("twinklestone")
	public static final LightBlock lightTwinklestone = null;
	@GameRegistry.ObjectHolder("vox_light")
	public static final LightBlock lightVox = null;

	@GameRegistry.ObjectHolder("amethyst_lamp")
	public static final LampBlock lampAmethyst = null;
	@GameRegistry.ObjectHolder("aquatic_lamp")
	public static final LampBlock lampAquatic = null;
	@GameRegistry.ObjectHolder("baronyte_lamp")
	public static final LampBlock lampBaronyte = null;
	@GameRegistry.ObjectHolder("blazium_lamp")
	public static final LampBlock lampBlazium = null;
	@GameRegistry.ObjectHolder("bloodstone_lamp")
	public static final LampBlock lampBloodstone = null;
	@GameRegistry.ObjectHolder("crystallite_lamp")
	public static final LampBlock lampCrystallite = null;
	@GameRegistry.ObjectHolder("elecanium_lamp")
	public static final LampBlock lampElecanium = null;
	@GameRegistry.ObjectHolder("emberstone_lamp")
	public static final LampBlock lampEmberstone = null;
	@GameRegistry.ObjectHolder("fire_lamp")
	public static final LampBlock lampFire = null;
	@GameRegistry.ObjectHolder("ghastly_lamp")
	public static final LampBlock lampGhastly = null;
	@GameRegistry.ObjectHolder("ghoulish_lamp")
	public static final LampBlock lampGhoulish = null;
	@GameRegistry.ObjectHolder("iro_lamp")
	public static final LampBlock lampIro = null;
	@GameRegistry.ObjectHolder("ivory_lamp")
	public static final LampBlock lampIvory = null;
	@GameRegistry.ObjectHolder("ivory_amethyst_lamp")
	public static final LampBlock lampIvoryAmethyst = null;
	@GameRegistry.ObjectHolder("ivory_jade_lamp")
	public static final LampBlock lampIvoryJade = null;
	@GameRegistry.ObjectHolder("ivory_limonite_lamp")
	public static final LampBlock lampIvoryLimonite = null;
	@GameRegistry.ObjectHolder("ivory_rosite_lamp")
	public static final LampBlock lampIvoryRosite = null;
	@GameRegistry.ObjectHolder("ivory_sapphire_lamp")
	public static final LampBlock lampIvorySapphire = null;
	@GameRegistry.ObjectHolder("jade_lamp")
	public static final LampBlock lampJade = null;
	@GameRegistry.ObjectHolder("aqua_life_lamp")
	public static final LampBlock lampLifeAqua = null;
	@GameRegistry.ObjectHolder("black_life_lamp")
	public static final LampBlock lampLifeBlack = null;
	@GameRegistry.ObjectHolder("blue_life_lamp")
	public static final LampBlock lampLifeBlue = null;
	@GameRegistry.ObjectHolder("brown_life_lamp")
	public static final LampBlock lampLifeBrown = null;
	@GameRegistry.ObjectHolder("cyan_life_lamp")
	public static final LampBlock lampLifeCyan = null;
	@GameRegistry.ObjectHolder("dark_grey_life_lamp")
	public static final LampBlock lampLifeDarkGrey = null;
	@GameRegistry.ObjectHolder("green_life_lamp")
	public static final LampBlock lampLifeGreen = null;
	@GameRegistry.ObjectHolder("grey_life_lamp")
	public static final LampBlock lampLifeGrey = null;
	@GameRegistry.ObjectHolder("lime_life_lamp")
	public static final LampBlock lampLifeLime = null;
	@GameRegistry.ObjectHolder("magenta_life_lamp")
	public static final LampBlock lampLifeMagenta = null;
	@GameRegistry.ObjectHolder("orange_life_lamp")
	public static final LampBlock lampLifeOrange = null;
	@GameRegistry.ObjectHolder("pink_life_lamp")
	public static final LampBlock lampLifePink = null;
	@GameRegistry.ObjectHolder("purple_life_lamp")
	public static final LampBlock lampLifePurple = null;
	@GameRegistry.ObjectHolder("red_life_lamp")
	public static final LampBlock lampLifeRed  = null;
	@GameRegistry.ObjectHolder("white_life_lamp")
	public static final LampBlock lampLifeWhite = null;
	@GameRegistry.ObjectHolder("yellow_life_lamp")
	public static final LampBlock lampLifeYellow = null;
	@GameRegistry.ObjectHolder("limonite_lamp")
	public static final LampBlock lampLimonite = null;
	@GameRegistry.ObjectHolder("lunar_lamp")
	public static final LampBlock lampLunar = null;
	@GameRegistry.ObjectHolder("lyon_lamp")
	public static final LampBlock lampLyon = null;
	@GameRegistry.ObjectHolder("mystic_lamp")
	public static final LampBlock lampMystic = null;
	@GameRegistry.ObjectHolder("neon_lamp")
	public static final LampBlock lampNeon = null;
	@GameRegistry.ObjectHolder("neon_circling_lamp")
	public static final LampBlock lampNeonCircling = null;
	@GameRegistry.ObjectHolder("neon_lapis_lamp")
	public static final LampBlock lampNeonLapis = null;
	@GameRegistry.ObjectHolder("neon_lapis_circling_lamp")
	public static final LampBlock lampNeonLapisCircling = null;
	@GameRegistry.ObjectHolder("neon_lapis_triangles_lamp")
	public static final LampBlock lampNeonLapisTriangles = null;
	@GameRegistry.ObjectHolder("neon_runic_lamp")
	public static final LampBlock lampNeonRunic = null;
	@GameRegistry.ObjectHolder("neon_triangles_lamp")
	public static final LampBlock lampNeonTriangles = null;
	@GameRegistry.ObjectHolder("rosite_lamp")
	public static final LampBlock lampRosite = null;
	@GameRegistry.ObjectHolder("sapphire_lamp")
	public static final LampBlock lampSapphire = null;
	@GameRegistry.ObjectHolder("skeletal_lamp")
	public static final LampBlock lampSkeletal = null;

	@GameRegistry.ObjectHolder("abyssal_glass")
	public static final GlassBlock glassAbyssal = null;
	@GameRegistry.ObjectHolder("ancient_glass")
	public static final GlassBlock glassAncient = null;
	@GameRegistry.ObjectHolder("aquatic_glass")
	public static final GlassBlock glassAquatic = null;
	@GameRegistry.ObjectHolder("archaic_glass")
	public static final GlassBlock glassArchaic = null;
	@GameRegistry.ObjectHolder("archaic_glass_breakable")
	public static final GlassBlock glassArchaicBreakable = null;
	@GameRegistry.ObjectHolder("baron_glass")
	public static final GlassBlock glassBaron = null;
	@GameRegistry.ObjectHolder("decayed_glass")
	public static final GlassBlock glassDecayed = null;
	@GameRegistry.ObjectHolder("gardencian_glass")
	public static final GlassBlock glassGardencian = null;
	@GameRegistry.ObjectHolder("haven_glass")
	public static final GlassBlock glassHaven = null;
	@GameRegistry.ObjectHolder("iro_glass")
	public static final GlassBlock glassIro = null;
	@GameRegistry.ObjectHolder("lelyetian_glass")
	public static final GlassBlock glassLelyetian = null;
	@GameRegistry.ObjectHolder("lunar_glass")
	public static final GlassBlock glassLunar = null;
	@GameRegistry.ObjectHolder("runic_glass")
	public static final GlassBlock glassRunic = null;
	@GameRegistry.ObjectHolder("unbreakable_runic_glass")
	public static final GlassBlock glassRunicUnbreakable = null;
	@GameRegistry.ObjectHolder("shyre_glass")
	public static final GlassBlock glassShyre = null;
	@GameRegistry.ObjectHolder("vox_glass")
	public static final GlassBlock glassVox = null;
	@GameRegistry.ObjectHolder("zhinx_glass")
	public static final GlassBlock glassZhinx = null;

	@GameRegistry.ObjectHolder("precasian_sand")
	public static final SandBlock sandPrecasian = null;

	@GameRegistry.ObjectHolder("amethyst_block")
	public static final CompressedBlock amethystBlock = null;
	@GameRegistry.ObjectHolder("baronyte_block")
	public static final CompressedBlock baronyteBlock = null;
	@GameRegistry.ObjectHolder("blazium_block")
	public static final CompressedBlock blaziumBlock = null;
	@GameRegistry.ObjectHolder("bloodstone_block")
	public static final CompressedBlock bloodstoneBlock = null;
	@GameRegistry.ObjectHolder("crystallite_block")
	public static final CompressedBlock crystalliteBlock = null;
	@GameRegistry.ObjectHolder("elecanium_block")
	public static final CompressedBlock elecaniumBlock = null;
	@GameRegistry.ObjectHolder("emberstone_block")
	public static final CompressedBlock emberstoneBlock = null;
	@GameRegistry.ObjectHolder("gemenyte_block")
	public static final CompressedBlock gemenyteBlock = null;
	@GameRegistry.ObjectHolder("ghastly_block")
	public static final CompressedBlock ghastlyBlock = null;
	@GameRegistry.ObjectHolder("ghoulish_block")
	public static final CompressedBlock ghoulishBlock = null;
	@GameRegistry.ObjectHolder("jade_block")
	public static final CompressedBlock jadeBlock = null;
	@GameRegistry.ObjectHolder("jewelyte_block")
	public static final CompressedBlock jewelyteBlock = null;
	@GameRegistry.ObjectHolder("limonite_block")
	public static final CompressedBlock limoniteBlock = null;
	@GameRegistry.ObjectHolder("lunar_block")
	public static final CompressedBlock lunarBlock = null;
	@GameRegistry.ObjectHolder("lyon_block")
	public static final CompressedBlock lyonBlock = null;
	@GameRegistry.ObjectHolder("mystite_block")
	public static final CompressedBlock mystiteBlock = null;
	@GameRegistry.ObjectHolder("ornamyte_block")
	public static final CompressedBlock ornamyteBlock = null;
	@GameRegistry.ObjectHolder("rosite_block")
	public static final CompressedBlock rositeBlock = null;
	@GameRegistry.ObjectHolder("sapphire_block")
	public static final CompressedBlock sapphireBlock = null;
	@GameRegistry.ObjectHolder("shyregem_block")
	public static final CompressedBlock shyregemBlock = null;
	@GameRegistry.ObjectHolder("shyrestone_block")
	public static final CompressedBlock shyrestoneBlock = null;
	@GameRegistry.ObjectHolder("skeletal_ingot_block")
	public static final CompressedBlock skeletalIngotBlock = null;
	@GameRegistry.ObjectHolder("varsium_block")
	public static final CompressedBlock varsiumBlock = null;

	@GameRegistry.ObjectHolder("baron_carpet")
	public static final CarpetBlock carpetBaron = null;
	@GameRegistry.ObjectHolder("borean_carpet")
	public static final CarpetBlock carpetBorean = null;
	@GameRegistry.ObjectHolder("gardencian_carpet")
	public static final CarpetBlock carpetGardencian = null;
	@GameRegistry.ObjectHolder("iro_carpet")
	public static final CarpetBlock carpetIro = null;
	@GameRegistry.ObjectHolder("lunar_carpet")
	public static final CarpetBlock carpetLunar = null;

	@GameRegistry.ObjectHolder("crystallanium")
	public static final BasicBlock crystallanium = null;
	@GameRegistry.ObjectHolder("emberium")
	public static final BasicBlock emberium = null;
	@GameRegistry.ObjectHolder("shadonantium")
	public static final BasicBlock shadonantium = null;
	@GameRegistry.ObjectHolder("skeletanium")
	public static final BasicBlock skeletanium = null;

	@GameRegistry.ObjectHolder("ancient_rock")
	public static final BasicBlock ancientRock = null;
	@GameRegistry.ObjectHolder("black_ancient_tile")
	public static final BasicBlock ancientTileBlack = null;
	@GameRegistry.ObjectHolder("ancient_tile_core")
	public static final BasicBlock ancientTileCore = null;
	@GameRegistry.ObjectHolder("green_ancient_tile")
	public static final BasicBlock ancientTileGreen = null;
	@GameRegistry.ObjectHolder("ancient_tile_shrine")
	public static final BasicBlock ancientTileShrine = null;
	@GameRegistry.ObjectHolder("white_ancient_tile")
	public static final BasicBlock ancientTileWhite = null;
	@GameRegistry.ObjectHolder("archaic_dirt")
	public static final BasicBlock archaicDirt = null;
	@GameRegistry.ObjectHolder("archaic_dirt_breakable")
	public static final BasicBlock archaicDirtBreakable = null;
	@GameRegistry.ObjectHolder("archaic_stream_horizontal")
	public static final BasicBlock archaicHorizontalStream = null;
	@GameRegistry.ObjectHolder("archaic_stream_horizontal_breakable")
	public static final BasicBlock archaicHorizontalStreamBreakaable = null;
	@GameRegistry.ObjectHolder("archaic_rectangles")
	public static final BasicBlock archaicRectangles = null;
	@GameRegistry.ObjectHolder("archaic_rectangles_breakable")
	public static final BasicBlock archaicRectanglesBreakable = null;
	@GameRegistry.ObjectHolder("archaic_squares")
	public static final BasicBlock archaicSquares = null;
	@GameRegistry.ObjectHolder("archaic_squares_breakable")
	public static final BasicBlock archaicSquaresBreakable = null;
	@GameRegistry.ObjectHolder("archaic_tiles")
	public static final BasicBlock archaicTiles = null;
	@GameRegistry.ObjectHolder("archaic_tiles_breakable")
	public static final BasicBlock archaicTilesBreakable = null;
	@GameRegistry.ObjectHolder("archaic_stream_vertical")
	public static final BasicBlock archaicVerticalStream = null;
	@GameRegistry.ObjectHolder("archaic_stream_vertical_breakable")
	public static final BasicBlock archaicVerticalStreamBreakable = null;
	@GameRegistry.ObjectHolder("baron_castle_wall")
	public static final BasicBlock baronCastleWall = null;
	@GameRegistry.ObjectHolder("baron_cube")
	public static final BasicBlock baronCube = null;
	@GameRegistry.ObjectHolder("baron_ground")
	public static final BasicBlock baronGround = null;
	@GameRegistry.ObjectHolder("bloodstone_bar_bricks")
	public static final BasicBlock bloodstoneBarBricks = null;
	@GameRegistry.ObjectHolder("bloodstone_bars")
	public static final BasicBlock bloodstoneBars = null;
	@GameRegistry.ObjectHolder("green_candy")
	public static final BasicBlock candyGreen = null;
	@GameRegistry.ObjectHolder("red_candy")
	public static final BasicBlock candyRed = null;
	@GameRegistry.ObjectHolder("white_candy")
	public static final BasicBlock candyWhite = null;
	@GameRegistry.ObjectHolder("chocolate_block")
	public static final BasicBlock chocolateBlock = null;
	@GameRegistry.ObjectHolder("dark_chocolate_block")
	public static final BasicBlock chocolateBlockDark = null;
	@GameRegistry.ObjectHolder("white_chocolate_block")
	public static final BasicBlock chocolateBlockWhite = null;
	@GameRegistry.ObjectHolder("cog_block")
	public static final BasicBlock cogBlock = null;
	@GameRegistry.ObjectHolder("blue_coral")
	public static final BasicBlock coralBlue = null;
	@GameRegistry.ObjectHolder("green_coral")
	public static final BasicBlock coralGreen = null;
	@GameRegistry.ObjectHolder("hard_coral")
	public static final BasicBlock coralHard = null;
	@GameRegistry.ObjectHolder("orange_coral")
	public static final BasicBlock coralOrange = null;
	@GameRegistry.ObjectHolder("pink_coral")
	public static final BasicBlock coralPink = null;
	@GameRegistry.ObjectHolder("white_coral")
	public static final BasicBlock coralWhite = null;
	@GameRegistry.ObjectHolder("yellow_coral")
	public static final BasicBlock coralYellow = null;
	@GameRegistry.ObjectHolder("aqua_cotton_candy")
	public static final BasicBlock cottonCandyAqua = null;
	@GameRegistry.ObjectHolder("pink_cotton_candy")
	public static final BasicBlock cottonCandyPink = null;
	@GameRegistry.ObjectHolder("crate")
	public static final BasicBlock crate = null;
	@GameRegistry.ObjectHolder("blue_crystal_block")
	public static final BasicBlock crystalBlue = null;
	@GameRegistry.ObjectHolder("green_crystal_block")
	public static final BasicBlock crystalGreen = null;
	@GameRegistry.ObjectHolder("purple_crystal_block")
	public static final BasicBlock crystalPurple = null;
	@GameRegistry.ObjectHolder("red_crystal_block")
	public static final BasicBlock crystalRed = null;
	@GameRegistry.ObjectHolder("white_crystal_block")
	public static final BasicBlock crystalWhite = null;
	@GameRegistry.ObjectHolder("yellow_crystal_block")
	public static final BasicBlock crystalYellow = null;
	@GameRegistry.ObjectHolder("dark_face_brick")
	public static final BasicBlock darkFaceBrick = null;
	@GameRegistry.ObjectHolder("deeplands_trap_explosion")
	public static final BasicBlock deeplandsTrapExplosion = null;
	@GameRegistry.ObjectHolder("deeplands_trap_lava")
	public static final BasicBlock deeplandsTrapLava = null;
	@GameRegistry.ObjectHolder("deeplands_trap_nipper")
	public static final BasicBlock deeplandsTrapNipper = null;
	@GameRegistry.ObjectHolder("deepshine")
	public static final BasicBlock deepshine = null;
	@GameRegistry.ObjectHolder("degraded_steel")
	public static final BasicBlock degradedSteel = null;
	@GameRegistry.ObjectHolder("eye_block")
	public static final BasicBlock eyeBlock = null;
	@GameRegistry.ObjectHolder("giant_snail_acid")
	public static final BasicBlock giantSnailAcid = null;
	@GameRegistry.ObjectHolder("gingerbread")
	public static final BasicBlock gingerbread = null;
	@GameRegistry.ObjectHolder("haunted_orb")
	public static final BasicBlock hauntedOrb = null;
	@GameRegistry.ObjectHolder("hive_wall")
	public static final BasicBlock hiveWall = null;
	@GameRegistry.ObjectHolder("iro_box")
	public static final BasicBlock iroBox = null;
	@GameRegistry.ObjectHolder("iro_brick_trap")
	public static final BasicBlock iroBrickTrap = null;
	@GameRegistry.ObjectHolder("iropole")
	public static final BasicBlock iropole = null;
	@GameRegistry.ObjectHolder("kaiyu_temple_block_face")
	public static final BasicBlock kaiyuTempleBlockFace = null;
	@GameRegistry.ObjectHolder("kaiyu_temple_block_flow")
	public static final BasicBlock kaiyuTempleBlockFlow = null;
	@GameRegistry.ObjectHolder("kaiyu_temple_block_maze")
	public static final BasicBlock kaiyuTempleBlockMaze = null;
	@GameRegistry.ObjectHolder("kaiyu_temple_block_pass")
	public static final BasicBlock kaiyuTempleBlockPass = null;
	@GameRegistry.ObjectHolder("kaiyu_temple_block_plain")
	public static final BasicBlock kaiyuTempleBlockPlain = null;
	@GameRegistry.ObjectHolder("kaiyu_temple_block_squares")
	public static final BasicBlock kaiyuTempleBlockSquares = null;
	@GameRegistry.ObjectHolder("kaiyu_temple_block_track")
	public static final BasicBlock kaiyuTempleBlockTrack = null;
	@GameRegistry.ObjectHolder("kaiyu_temple_trap_flow")
	public static final BasicBlock kaiyuTempleTrapFlow = null;
	@GameRegistry.ObjectHolder("kaiyu_temple_trap_maze")
	public static final BasicBlock kaiyuTempleTrapMaze = null;
	@GameRegistry.ObjectHolder("kaiyu_temple_trap_pass")
	public static final BasicBlock kaiyuTempleTrapPass = null;
	@GameRegistry.ObjectHolder("kaiyu_temple_trap_squares")
	public static final BasicBlock kaiyuTempleTrapSquares = null;
	@GameRegistry.ObjectHolder("licorice")
	public static final BasicBlock licorice = null;
	@GameRegistry.ObjectHolder("darklight_orb")
	public static final BasicBlock lunarOrbDarklight = null;
	@GameRegistry.ObjectHolder("dusk_orb")
	public static final BasicBlock lunarOrbDusk = null;
	@GameRegistry.ObjectHolder("lunar_orb")
	public static final BasicBlock lunarOrbLunar = null;
	@GameRegistry.ObjectHolder("moonlight_orb")
	public static final BasicBlock lunarOrbMoonlight = null;
	@GameRegistry.ObjectHolder("sunfire_orb")
	public static final BasicBlock lunarOrbSunfire = null;
	@GameRegistry.ObjectHolder("lunar_pad")
	public static final BasicBlock lunarPad = null;
	@GameRegistry.ObjectHolder("marshmallow")
	public static final BasicBlock marshmallow = null;
	@GameRegistry.ObjectHolder("orange_acid")
	public static final BasicBlock orangeAcid = null;
	@GameRegistry.ObjectHolder("paravite_hive")
	public static final BasicBlock paraviteHive = null;
	@GameRegistry.ObjectHolder("black_petals")
	public static final BasicBlock petalsBlack = null;
	@GameRegistry.ObjectHolder("blue_petals")
	public static final BasicBlock petalsBlue = null;
	@GameRegistry.ObjectHolder("light_blue_petals")
	public static final BasicBlock petalsLightBlue = null;
	@GameRegistry.ObjectHolder("magenta_petals")
	public static final BasicBlock petalsMagenta = null;
	@GameRegistry.ObjectHolder("orange_petals")
	public static final BasicBlock petalsOrange = null;
	@GameRegistry.ObjectHolder("purple_petals")
	public static final BasicBlock petalsPurple = null;
	@GameRegistry.ObjectHolder("red_petals")
	public static final BasicBlock petalsRed = null;
	@GameRegistry.ObjectHolder("rose_petals")
	public static final BasicBlock petalsRose = null;
	@GameRegistry.ObjectHolder("yellow_petals")
	public static final BasicBlock petalsYellow = null;
	@GameRegistry.ObjectHolder("plastic")
	public static final BasicBlock plastic = null;
	@GameRegistry.ObjectHolder("blue_rock_candy")
	public static final BasicBlock rockCandyBlue = null;
	@GameRegistry.ObjectHolder("green_rock_candy")
	public static final BasicBlock rockCandyGreen = null;
	@GameRegistry.ObjectHolder("pink_rock_candy")
	public static final BasicBlock rockCandyPink = null;
	@GameRegistry.ObjectHolder("purple_rock_candy")
	public static final BasicBlock rockCandyPurple = null;
	@GameRegistry.ObjectHolder("rune_post_compass")
	public static final RunePostBlock runePostCompass = null;
	@GameRegistry.ObjectHolder("rune_post_distortion")
	public static final RunePostBlock runePostDistortion = null;
	@GameRegistry.ObjectHolder("rune_post_energy")
	public static final RunePostBlock runePostEnergy = null;
	@GameRegistry.ObjectHolder("rune_post_fire")
	public static final RunePostBlock runePostFire = null;
	@GameRegistry.ObjectHolder("rune_post_kinetic")
	public static final RunePostBlock runePostKinetic = null;
	@GameRegistry.ObjectHolder("rune_post_life")
	public static final RunePostBlock runePostLife = null;
	@GameRegistry.ObjectHolder("rune_post_lunar")
	public static final RunePostBlock runePostLunar = null;
	@GameRegistry.ObjectHolder("rune_post_poison")
	public static final RunePostBlock runePostPoison = null;
	@GameRegistry.ObjectHolder("rune_post_power")
	public static final RunePostBlock runePostPower = null;
	@GameRegistry.ObjectHolder("rune_post_storm")
	public static final RunePostBlock runePostStorm = null;
	@GameRegistry.ObjectHolder("rune_post_strike")
	public static final RunePostBlock runePostStrike = null;
	@GameRegistry.ObjectHolder("rune_post_water")
	public static final RunePostBlock runePostWater = null;
	@GameRegistry.ObjectHolder("rune_post_wind")
	public static final RunePostBlock runePostWind = null;
	@GameRegistry.ObjectHolder("rune_post_wither")
	public static final RunePostBlock runePostWither = null;
	@GameRegistry.ObjectHolder("silvro_box")
	public static final BasicBlock silvroBox = null;
	@GameRegistry.ObjectHolder("skeletal_block")
	public static final BasicBlock skeletalBlock = null;
	@GameRegistry.ObjectHolder("skull_block")
	public static final BasicBlock skullBlock = null;
	@GameRegistry.ObjectHolder("dark_skull_block")
	public static final BasicBlock skullBlockDark = null;
	@GameRegistry.ObjectHolder("tentacles")
	public static final BasicBlock tentacles = null;
	@GameRegistry.ObjectHolder("tentacles_dots_left")
	public static final BasicBlock tentaclesDotsLeft = null;
	@GameRegistry.ObjectHolder("tentacles_dots_right")
	public static final BasicBlock tentaclesDotsRight = null;
	@GameRegistry.ObjectHolder("tentacles_eye_orange")
	public static final BasicBlock tentaclesEyeOrange = null;
	@GameRegistry.ObjectHolder("tentacles_eye_red")
	public static final BasicBlock tentaclesEyeRed = null;
	@GameRegistry.ObjectHolder("tentacles_green")
	public static final BasicBlock tentaclesGreen = null;
	@GameRegistry.ObjectHolder("toxic_stem")
	public static final BasicBlock toxicStem = null;
	@GameRegistry.ObjectHolder("unbreakable_iro_bricks")
	public static final BasicBlock unbreakableIroBricks = null;
	@GameRegistry.ObjectHolder("unbreakable_plant_stem")
	public static final BasicBlock unbreakablePlantStem = null;
	@GameRegistry.ObjectHolder("unbreakable_runic_bricks")
	public static final BasicBlock unbreakableRunicBricks = null;
	@GameRegistry.ObjectHolder("blue_shroom")
	public static final BasicNonCubeBlock shroomBlue = null;
	@GameRegistry.ObjectHolder("green_shroom")
	public static final BasicNonCubeBlock shroomGreen = null;
	@GameRegistry.ObjectHolder("orange_shroom")
	public static final BasicNonCubeBlock shroomOrange = null;
	@GameRegistry.ObjectHolder("purple_shroom")
	public static final BasicNonCubeBlock shroomPurple = null;
	@GameRegistry.ObjectHolder("shroom_stem")
	public static final BasicNonCubeBlock shroomStem = null;
	@GameRegistry.ObjectHolder("vox_shroom")
	public static final BasicNonCubeBlock shroomVox = null;
	@GameRegistry.ObjectHolder("yellow_shroom")
	public static final BasicNonCubeBlock shroomYellow = null;
	@GameRegistry.ObjectHolder("vox_log")
	public static final BasicNonCubeBlock voxLog1 = null;
	@GameRegistry.ObjectHolder("vox_log2")
	public static final BasicNonCubeBlock voxLog2 = null;
	@GameRegistry.ObjectHolder("boney_block")
	public static final BoneyBlock boneyBlock = null;
	@GameRegistry.ObjectHolder("ancient_cactus")
	public static final CactusBlock ancientCactus = null;
	@GameRegistry.ObjectHolder("carved_rune_direction")
	public static final CarvedRunicBlock carvedRuneDirection = null;
	@GameRegistry.ObjectHolder("carved_rune_empowering")
	public static final CarvedRunicBlock carvedRuneEmpowering = null;
	@GameRegistry.ObjectHolder("carved_rune_focus")
	public static final CarvedRunicBlock carvedRuneFocus = null;
	@GameRegistry.ObjectHolder("carved_rune_power")
	public static final CarvedRunicBlock carvedRunePower = null;
	@GameRegistry.ObjectHolder("carved_rune_reality")
	public static final CarvedRunicBlock carvedRuneReality = null;
	@GameRegistry.ObjectHolder("carved_rune_space")
	public static final CarvedRunicBlock carvedRuneSpace = null;
	@GameRegistry.ObjectHolder("carved_rune_travel")
	public static final CarvedRunicBlock carvedRuneTravel = null;
	@GameRegistry.ObjectHolder("charging_table")
	public static final ChargingTable chargingTable = null;
	@GameRegistry.ObjectHolder("shyre_cloud")
	public static final CloudBlock shyreCloud = null;
	@GameRegistry.ObjectHolder("dimensional_fabric")
	public static final DimensionalFabric dimensionalFabric = null;
	@GameRegistry.ObjectHolder("dustopian_lamp")
	public static final DustopianLamp dustopianLamp = null;
	@GameRegistry.ObjectHolder("dustopian_lamp_off")
	public static final DustopianLampOff dustopianLampOff = null;
	@GameRegistry.ObjectHolder("damage_enhancer")
	public static final EnhancerBlock enhancerDamage = null;
	@GameRegistry.ObjectHolder("divine_enhancer")
	public static final EnhancerBlock enhancerDivine = null;
	@GameRegistry.ObjectHolder("durability_enhancer")
	public static final EnhancerBlock enhancerDurability = null;
	@GameRegistry.ObjectHolder("luck_enhancer")
	public static final EnhancerBlock enhancerLuck = null;
	@GameRegistry.ObjectHolder("magic_enhancer")
	public static final EnhancerBlock enhancerMagic = null;
	@GameRegistry.ObjectHolder("resistance_enhancer")
	public static final EnhancerBlock enhancerResistance = null;
	@GameRegistry.ObjectHolder("speed_enhancer")
	public static final EnhancerBlock enhancerSpeed = null;
	@GameRegistry.ObjectHolder("weight_enhancer")
	public static final EnhancerBlock enhancerWeight = null;
	@GameRegistry.ObjectHolder("archaic_ladder")
	public static final LadderBlock archaicLadder = null;
	@GameRegistry.ObjectHolder("archaic_ladder_breakable")
	public static final LadderBlock archaicLadderBreakable = null;
	@GameRegistry.ObjectHolder("living_growth")
	public static final LivingGrowth livingGrowth = null;
	@GameRegistry.ObjectHolder("celeve_stem")
	public static final LogBlock celeveStem = null;
	@GameRegistry.ObjectHolder("lunar_pillar")
	public static final LunarPillar lunarPillar = null;
	@GameRegistry.ObjectHolder("spikey_pillar")
	public static final SpikeyPillar spikeyPillar = null;
	@GameRegistry.ObjectHolder("toxic_block")
	public static final ToxicBlock toxicBlock = null;
	@GameRegistry.ObjectHolder("toxic_waste")
	public static final ToxicWaste toxicWaste = null;

	@GameRegistry.ObjectHolder("amphibior_spawner")
	public static final SpawnerBlock spawnerAmphibior = null;
	@GameRegistry.ObjectHolder("amphibiyte_spawner")
	public static final SpawnerBlock spawnerAmphibiyte = null;
	@GameRegistry.ObjectHolder("angelica_spawner")
	public static final SpawnerBlock spawnerAngelica = null;
	@GameRegistry.ObjectHolder("arc_wizard_spawner")
	public static final SpawnerBlock spawnerArcWizard = null;
	@GameRegistry.ObjectHolder("arkzyne_spawner")
	public static final SpawnerBlock spawnerArkzyne = null;
	@GameRegistry.ObjectHolder("arocknid_spawner")
	public static final SpawnerBlock spawnerArocknid = null;
	@GameRegistry.ObjectHolder("banshee_spawner")
	public static final SpawnerBlock spawnerBanshee = null;
	@GameRegistry.ObjectHolder("baumba_spawner")
	public static final SpawnerBlock spawnerBaumba = null;
	@GameRegistry.ObjectHolder("bloodsucker_spawner")
	public static final SpawnerBlock spawnerBloodsucker = null;
	@GameRegistry.ObjectHolder("cane_bug_spawner")
	public static final SpawnerBlock spawnerCaneBug = null;
	@GameRegistry.ObjectHolder("crusilisk_spawner")
	public static final SpawnerBlock spawnerCrusilisk = null;
	@GameRegistry.ObjectHolder("dawnlight_spawner")
	public static final SpawnerBlock spawnerDawnlight = null;
	@GameRegistry.ObjectHolder("daysee_spawner")
	public static final SpawnerBlock spawnerDaysee = null;
	@GameRegistry.ObjectHolder("diocus_spawner")
	public static final SpawnerBlock spawnerDiocus = null;
	@GameRegistry.ObjectHolder("enforcer_spawner")
	public static final SpawnerBlock spawnerEnforcer = null;
	@GameRegistry.ObjectHolder("exohead_spawner")
	public static final SpawnerBlock spawnerExohead = null;
	@GameRegistry.ObjectHolder("faceless_floater_spawner")
	public static final SpawnerBlock spawnerFacelessFloater = null;
	@GameRegistry.ObjectHolder("fenix_spawner")
	public static final SpawnerBlock spawnerFenix = null;
	@GameRegistry.ObjectHolder("flesh_eater_spawner")
	public static final SpawnerBlock spawnerFleshEater = null;
	@GameRegistry.ObjectHolder("flowerface_spawner")
	public static final SpawnerBlock spawnerFlowerface = null;
	@GameRegistry.ObjectHolder("fungock_spawner")
	public static final SpawnerBlock spawnerFungock = null;
	@GameRegistry.ObjectHolder("ghastus_spawner")
	public static final SpawnerBlock spawnerGhastus = null;
	@GameRegistry.ObjectHolder("gingerbird_spawner")
	public static final SpawnerBlock spawnerGingerbird = null;
	@GameRegistry.ObjectHolder("gingerbread_man_spawner")
	public static final SpawnerBlock spawnerGingerbreadMan = null;
	@GameRegistry.ObjectHolder("goldum_spawner")
	public static final SpawnerBlock spawnerGoldum = null;
	@GameRegistry.ObjectHolder("goldus_spawner")
	public static final SpawnerBlock spawnerGoldus = null;
	@GameRegistry.ObjectHolder("inmate_x_spawner")
	public static final SpawnerBlock spawnerInmateX = null;
	@GameRegistry.ObjectHolder("inmate_y_spawner")
	public static final SpawnerBlock spawnerInmateY = null;
	@GameRegistry.ObjectHolder("iosaur_spawner")
	public static final SpawnerBlock spawnerIosaur = null;
	@GameRegistry.ObjectHolder("jawe_spawner")
	public static final SpawnerBlock spawnerJawe = null;
	@GameRegistry.ObjectHolder("kaiyu_spawner")
	public static final SpawnerBlock spawnerKaiyu = null;
	@GameRegistry.ObjectHolder("lightwalker_spawner")
	public static final SpawnerBlock spawnerLightwalker = null;
	@GameRegistry.ObjectHolder("luxocron_spawner")
	public static final SpawnerBlock spawnerLuxocron = null;
	@GameRegistry.ObjectHolder("mechyon_spawner")
	public static final SpawnerBlock spawnerMechyon = null;
	@GameRegistry.ObjectHolder("merkyre_spawner")
	public static final SpawnerBlock spawnerMerkyre = null;
	@GameRegistry.ObjectHolder("mermage_spawner")
	public static final SpawnerBlock spawnerMermage = null;
	@GameRegistry.ObjectHolder("mushroom_spider_spawner")
	public static final SpawnerBlock spawnerMushroomSpider = null;
	@GameRegistry.ObjectHolder("nethengeic_beast_spawner")
	public static final SpawnerBlock spawnerNethengeicBeast = null;
	@GameRegistry.ObjectHolder("nightmare_spider_spawner")
	public static final SpawnerBlock spawnerNightmareSpider = null;
	@GameRegistry.ObjectHolder("nightwing_spawner")
	public static final SpawnerBlock spawnerNightwing = null;
	@GameRegistry.ObjectHolder("opteryx_spawner")
	public static final SpawnerBlock spawnerOpteryx = null;
	@GameRegistry.ObjectHolder("paravite_spawner")
	public static final SpawnerBlock spawnerParavite = null;
	@GameRegistry.ObjectHolder("phantom_spawner")
	public static final SpawnerBlock spawnerPhantom = null;
	@GameRegistry.ObjectHolder("pod_plant_spawner")
	public static final SpawnerBlock spawnerPodPlant = null;
	@GameRegistry.ObjectHolder("rawbone_spawner")
	public static final SpawnerBlock spawnerRawbone = null;
	@GameRegistry.ObjectHolder("reaver_spawner")
	public static final SpawnerBlock spawnerReaver = null;
	@GameRegistry.ObjectHolder("refluct_spawner")
	public static final SpawnerBlock spawnerRefluct = null;
	@GameRegistry.ObjectHolder("rock_critter_spawner")
	public static final SpawnerBlock spawnerRockCritter = null;
	@GameRegistry.ObjectHolder("runic_golem_spawner")
	public static final SpawnerBlock spawnerRunicGolem = null;
	@GameRegistry.ObjectHolder("runic_guardian_spawner")
	public static final SpawnerBlock spawnerRunicGuardian = null;
	@GameRegistry.ObjectHolder("seeker_spawner")
	public static final SpawnerBlock spawnerSeeker = null;
	@GameRegistry.ObjectHolder("shavo_spawner")
	public static final SpawnerBlock spawnerShavo = null;
	@GameRegistry.ObjectHolder("shyre_troll_spawner")
	public static final SpawnerBlock spawnerShyreTroll = null;
	@GameRegistry.ObjectHolder("skeledon_spawner")
	public static final SpawnerBlock spawnerSkeledon = null;
	@GameRegistry.ObjectHolder("skelekyte_spawner")
	public static final SpawnerBlock spawnerSkelekyte = null;
	@GameRegistry.ObjectHolder("soulscorne_spawner")
	public static final SpawnerBlock spawnerSoulscorne = null;
	@GameRegistry.ObjectHolder("spectral_wizard_spawner")
	public static final SpawnerBlock spawnerSpectralWizard = null;
	@GameRegistry.ObjectHolder("spinoledon_spawner")
	public static final SpawnerBlock spawnerSpinoledon = null;
	@GameRegistry.ObjectHolder("surveyor_spawner")
	public static final SpawnerBlock spawnerSurveyor = null;
	@GameRegistry.ObjectHolder("tharafly_spawner")
	public static final SpawnerBlock spawnerTharafly = null;
	@GameRegistry.ObjectHolder("undead_troll_spawner")
	public static final SpawnerBlock spawnerUndeadTroll = null;
	@GameRegistry.ObjectHolder("urioh_spawner")
	public static final SpawnerBlock spawnerUrioh = null;
	@GameRegistry.ObjectHolder("urv_spawner")
	public static final SpawnerBlock spawnerUrv = null;
	@GameRegistry.ObjectHolder("vine_wizard_spawner")
	public static final SpawnerBlock spawnerVineWizard = null;
	@GameRegistry.ObjectHolder("visage_spawner")
	public static final SpawnerBlock spawnerVisage = null;
	@GameRegistry.ObjectHolder("volar_spawner")
	public static final SpawnerBlock spawnerVolar = null;
	@GameRegistry.ObjectHolder("zarg_spawner")
	public static final SpawnerBlock spawnerZarg = null;
	@GameRegistry.ObjectHolder("zhinx_spawner")
	public static final SpawnerBlock spawnerZhinx = null;
	@GameRegistry.ObjectHolder("zorp_spawner")
	public static final SpawnerBlock spawnerZorp = null;

	@GameRegistry.ObjectHolder("army_block")
	public static final BossAltarBlock armyBlock = null;
	@GameRegistry.ObjectHolder("baroness_altar")
	public static final BossAltarBlock baronessAltar = null;
	@GameRegistry.ObjectHolder("candy_block")
	public static final BossAltarBlock candyBlock = null;
	@GameRegistry.ObjectHolder("clunkhead_altar")
	public static final BossAltarBlock clunkheadAltar = null;
	@GameRegistry.ObjectHolder("craexxeus_altar")
	public static final BossAltarBlock craexxeusAltar = null;
	@GameRegistry.ObjectHolder("creep_altar")
	public static final BossAltarBlock creepAltar = null;
	@GameRegistry.ObjectHolder("dracyon_altar")
	public static final BossAltarBlock dracyonAltar = null;
	@GameRegistry.ObjectHolder("graw_altar")
	public static final BossAltarBlock grawAltar = null;
	@GameRegistry.ObjectHolder("guardian_altar")
	public static final GuardianAltar guardianAltar = null;
	@GameRegistry.ObjectHolder("hive_spawner")
	public static final BossAltarBlock hiveSpawner = null;
	@GameRegistry.ObjectHolder("hydro_table")
	public static final BossAltarBlock hydroTable = null;
	@GameRegistry.ObjectHolder("illusion_altar")
	public static final BossAltarBlock illusionAltar = null;
	@GameRegistry.ObjectHolder("kror_altar")
	public static final BossAltarBlock krorAltar = null;
	@GameRegistry.ObjectHolder("mechbot_altar")
	public static final BossAltarBlock mechbotAltar = null;
	@GameRegistry.ObjectHolder("power_station")
	public static final BossAltarBlock powerStation = null;
	@GameRegistry.ObjectHolder("primordial_shrine")
	public static final BossAltarBlock primordialShrine = null;
	@GameRegistry.ObjectHolder("rockrider_shrine")
	public static final BasicBlock rockriderShrine = null;
	@GameRegistry.ObjectHolder("shadow_altar")
	public static final BossAltarBlock shadowAltar = null;
	@GameRegistry.ObjectHolder("silverfoot_altar")
	public static final BossAltarBlock silverfootAltar = null;
	@GameRegistry.ObjectHolder("toy_box")
	public static final BossAltarBlock toyBox = null;
	@GameRegistry.ObjectHolder("vinocorne_shrine")
	public static final BossAltarBlock vinocorneShrine = null;
	@GameRegistry.ObjectHolder("visualent_altar")
	public static final BossAltarBlock visualentAltar = null;
	@GameRegistry.ObjectHolder("voxxulon_altar")
	public static final BossAltarBlock voxxulonAltar = null;

	@GameRegistry.ObjectHolder("abyss_portal")
	public static final PortalBlock portalAbyss = null;
	@GameRegistry.ObjectHolder("ancient_cavern_portal")
	public static final PortalBlock portalAncientCavern = null;
	@GameRegistry.ObjectHolder("barathos_portal")
	public static final PortalBlock portalBarathos = null;
	@GameRegistry.ObjectHolder("borean_portal")
	public static final PortalBlock portalBorean = null;
	@GameRegistry.ObjectHolder("candyland_portal")
	public static final PortalBlock portalCandyland = null;
	@GameRegistry.ObjectHolder("celeve_portal")
	public static final PortalBlock portalCeleve = null;
	@GameRegistry.ObjectHolder("creeponia_portal")
	public static final PortalBlock portalCreeponia = null;
	@GameRegistry.ObjectHolder("crystevia_portal")
	public static final PortalBlock portalCrystevia = null;
	@GameRegistry.ObjectHolder("deeplands_portal")
	public static final PortalBlock portalDeeplands = null;
	@GameRegistry.ObjectHolder("dustopia_portal")
	public static final PortalBlock portalDustopia = null;
	@GameRegistry.ObjectHolder("gardencia_portal")
	public static final PortalBlock portalGardencia = null;
	@GameRegistry.ObjectHolder("greckon_portal")
	public static final PortalBlock portalGreckon = null;
	@GameRegistry.ObjectHolder("haven_portal")
	public static final PortalBlock portalHaven = null;
	@GameRegistry.ObjectHolder("immortallis_portal")
	public static final PortalBlock portalImmortallis = null;
	@GameRegistry.ObjectHolder("iromine_portal")
	public static final PortalBlock portalIromine = null;
	@GameRegistry.ObjectHolder("lelyetia_portal")
	public static final PortalBlock portalLelyetia = null;
	@GameRegistry.ObjectHolder("lunalus_portal")
	public static final PortalBlock portalLunalus = null;
	@GameRegistry.ObjectHolder("mysterium_portal")
	public static final PortalBlock portalMysterium = null;
	@GameRegistry.ObjectHolder("nether_portal")
	public static final PortalBlock portalNether = null;
	@GameRegistry.ObjectHolder("precasia_portal")
	public static final PortalBlock portalPrecasia = null;
	@GameRegistry.ObjectHolder("runandor_portal")
	public static final PortalBlock portalRunandor = null;
	@GameRegistry.ObjectHolder("shyrelands_portal")
	public static final PortalBlock portalShyrelands = null;
	@GameRegistry.ObjectHolder("vox_ponds_portal")
	public static final PortalBlock portalVoxPonds = null;

	@GameRegistry.ObjectHolder("ancient_altar")
	public static final AncientAltar ancientAltar = null;
	@GameRegistry.ObjectHolder("erebon_shrine")
	public static final AncientCavernShrine shrineErebon = null;
	@GameRegistry.ObjectHolder("luxon_shrine")
	public static final AncientCavernShrine shrineLuxon = null;
	@GameRegistry.ObjectHolder("pluton_shrine")
	public static final AncientCavernShrine shrinePluton = null;
	@GameRegistry.ObjectHolder("selyan_shrine")
	public static final AncientCavernShrine shrineSelyan = null;
	@GameRegistry.ObjectHolder("ascension_shrine")
	public static final AscensionShrine ascensionShrine = null;
	@GameRegistry.ObjectHolder("vox_crate")
	public static final BasicBlock voxCrate = null;
	@GameRegistry.ObjectHolder("creation_forge")
	public static final CreationForge creationForge = null;
	@GameRegistry.ObjectHolder("blue_crystal_creator")
	public static final CrystalCreator crystalCreatorBlue = null;
	@GameRegistry.ObjectHolder("green_crystal_creator")
	public static final CrystalCreator crystalCreatorGreen = null;
	@GameRegistry.ObjectHolder("purple_crystal_creator")
	public static final CrystalCreator crystalCreatorPurple = null;
	@GameRegistry.ObjectHolder("red_crystal_creator")
	public static final CrystalCreator crystalCreatorRed = null;
	@GameRegistry.ObjectHolder("white_crystal_creator")
	public static final CrystalCreator crystalCreatorWhite = null;
	@GameRegistry.ObjectHolder("yellow_crystal_creator")
	public static final CrystalCreator crystalCreatorYellow = null;
	@GameRegistry.ObjectHolder("crystal_extension_shrine")
	public static final CrystalExtensionShrine crystalExtensionShrine = null;
	@GameRegistry.ObjectHolder("declogging_table")
	public static final DecloggingTable decloggingTable = null;
	@GameRegistry.ObjectHolder("deep_case")
	public static final DeepCase deepCase = null;
	@GameRegistry.ObjectHolder("divine_station")
	public static final DivineStation divineStation = null;
	@GameRegistry.ObjectHolder("enigma_table")
	public static final EnigmaTable enigmaTable = null;
	@GameRegistry.ObjectHolder("exoid_station")
	public static final ExoidStation exoidStation = null;
	@GameRegistry.ObjectHolder("extraction_device")
	public static final ExtractionDevice extractionDevice = null;
	@GameRegistry.ObjectHolder("extraction_device_on")
	public static final ExtractionDevice extractionDeviceOn = null;
	@GameRegistry.ObjectHolder("filtration_system")
	public static final FiltrationSystem filtrationSystem = null;
	@GameRegistry.ObjectHolder("frame_bench")
	public static final FrameBench frameBench = null;
	@GameRegistry.ObjectHolder("gold_accumulator")
	public static final GoldAccumulator goldAccumulator = null;
	@GameRegistry.ObjectHolder("haunting_table")
	public static final HauntingTable hauntingTable = null;
	@GameRegistry.ObjectHolder("immortallis_progressor_1")
	public static final ImmortallisProgressor immortallisProgressor1 = null;
	@GameRegistry.ObjectHolder("immortallis_progressor_2")
	public static final ImmortallisProgressor immortallisProgressor2 = null;
	@GameRegistry.ObjectHolder("immortallis_progressor_3")
	public static final ImmortallisProgressor immortallisProgressor3 = null;
	@GameRegistry.ObjectHolder("immortallis_progressor_4")
	public static final ImmortallisProgressor immortallisProgressor4 = null;
	@GameRegistry.ObjectHolder("immortallis_progressor_5")
	public static final ImmortallisProgressor immortallisProgressor5 = null;
	@GameRegistry.ObjectHolder("immortallis_progressor_6")
	public static final ImmortallisProgressor immortallisProgressor6 = null;
	@GameRegistry.ObjectHolder("immortallis_progressor_7")
	public static final ImmortallisProgressor immortallisProgressor7 = null;
	@GameRegistry.ObjectHolder("immortallis_progressor_8")
	public static final ImmortallisProgressor immortallisProgressor8 = null;
	@GameRegistry.ObjectHolder("immortallis_progressor_9")
	public static final ImmortallisProgressor immortallisProgressor9 = null;
	@GameRegistry.ObjectHolder("infusion_table")
	public static final InfusionTable infusionTable = null;
	@GameRegistry.ObjectHolder("iro_crate")
	public static final IroCrate iroCrate = null;
	@GameRegistry.ObjectHolder("lunar_creation_table")
	public static final LunarCreationTable lunarCreationTable = null;
	@GameRegistry.ObjectHolder("lunar_enrichment_table")
	public static final LunarEnrichmentTable lunarEnrichmentTable = null;
	@GameRegistry.ObjectHolder("mending_table")
	public static final MendingTable mendingTable = null;
	@GameRegistry.ObjectHolder("mineralization_station")
	public static final MineralizationStation mineralizationStation = null;
	@GameRegistry.ObjectHolder("petal_crafting_station")
	public static final PetalCraftingStation petalCraftingStation = null;
	@GameRegistry.ObjectHolder("pure_gold_accumulator")
	public static final PureGoldAccumulator pureGoldAccumulator = null;
	@GameRegistry.ObjectHolder("recreation_station")
	public static final RecreationStation recreationStation = null;
	@GameRegistry.ObjectHolder("rune_randomizer")
	public static final RuneRandomizer runeRandomizer = null;
	@GameRegistry.ObjectHolder("rune_shrine")
	public static final RuneShrine runeShrine = null;
	@GameRegistry.ObjectHolder("runic_block")
	public static final RunicBlock runicBlock = null;
	@GameRegistry.ObjectHolder("strange_block")
	public static final StrangeBlock strangeBlock = null;
	@GameRegistry.ObjectHolder("tea_sink")
	public static final TeaSink teaSink = null;
	@GameRegistry.ObjectHolder("vox_store_crate")
	public static final VoxStoreCrate voxStoreCrate = null;
	@GameRegistry.ObjectHolder("whitewashing_table")
	public static final WhitewashingTable whitewashingTable = null;

	@GameRegistry.ObjectHolder("blue_aqua_fungi")
	public static final GenericPlantBlock plantAquaFungiBlue = null;
	@GameRegistry.ObjectHolder("yellow_aqua_fungi")
	public static final GenericPlantBlock plantAquaFungiYellow = null;
	@GameRegistry.ObjectHolder("arcbulb")
	public static final GenericPlantBlock plantArcbulb = null;
	@GameRegistry.ObjectHolder("arcflower")
	public static final GenericPlantBlock plantArcflower = null;
	@GameRegistry.ObjectHolder("bureal_stocks")
	public static final GenericPlantBlock plantBurealStocks = null;
	@GameRegistry.ObjectHolder("candycane")
	public static final GenericPlantBlock plantCandycane = null;
	@GameRegistry.ObjectHolder("candy_grass")
	public static final GenericPlantBlock plantCandyGrass = null;
	@GameRegistry.ObjectHolder("blue_candy_grass")
	public static final GenericPlantBlock plantCandyGrassBlue = null;
	@GameRegistry.ObjectHolder("white_candy_grass")
	public static final GenericPlantBlock plantCandyGrassWhite = null;
	@GameRegistry.ObjectHolder("celevians_blue")
	public static final GenericPlantBlock plantCeleviansBlue = null;
	@GameRegistry.ObjectHolder("celevians_purple")
	public static final GenericPlantBlock plantCeleviansPurple = null;
	@GameRegistry.ObjectHolder("celevians_red")
	public static final GenericPlantBlock plantCeleviansRed = null;
	@GameRegistry.ObjectHolder("celevians_white")
	public static final GenericPlantBlock plantCeleviansWhite = null;
	@GameRegistry.ObjectHolder("chocolate_grass_plant")
	public static final GenericPlantBlock plantChocolateGrass = null;
	@GameRegistry.ObjectHolder("chocolate_stocks")
	public static final GenericPlantBlock plantChocolateStocks = null;
	@GameRegistry.ObjectHolder("creep_flowers")
	public static final GenericPlantBlock plantCreepFlowers = null;
	@GameRegistry.ObjectHolder("creep_grass")
	public static final GenericPlantBlock plantCreepGrass = null;
	@GameRegistry.ObjectHolder("blue_crystal_plant")
	public static final GenericPlantBlock plantCrystalBlue = null;
	@GameRegistry.ObjectHolder("green_crystal_plant")
	public static final GenericPlantBlock plantCrystalGreen = null;
	@GameRegistry.ObjectHolder("purple_crystal_plant")
	public static final GenericPlantBlock plantCrystalPurple = null;
	@GameRegistry.ObjectHolder("red_crystal_plant")
	public static final GenericPlantBlock plantCrystalRed = null;
	@GameRegistry.ObjectHolder("white_crystal_plant")
	public static final GenericPlantBlock plantCrystalWhite = null;
	@GameRegistry.ObjectHolder("yellow_crystal_plant")
	public static final GenericPlantBlock plantCrystalYellow = null;
	@GameRegistry.ObjectHolder("daileers")
	public static final GenericPlantBlock plantDaileers = null;
	@GameRegistry.ObjectHolder("dawn_bulb")
	public static final GenericPlantBlock plantDawnBulb = null;
	@GameRegistry.ObjectHolder("dawn_bush")
	public static final GenericPlantBlock plantDawnBush = null;
	@GameRegistry.ObjectHolder("dawn_flower")
	public static final GenericPlantBlock plantDawnFlower = null;
	@GameRegistry.ObjectHolder("dawn_grass")
	public static final GenericPlantBlock plantDawnGrass = null;
	@GameRegistry.ObjectHolder("daylooms_blue")
	public static final GenericPlantBlock plantDayloomsBlue = null;
	@GameRegistry.ObjectHolder("daylooms_pink")
	public static final GenericPlantBlock plantDayloomsPink = null;
	@GameRegistry.ObjectHolder("daylooms_yellow")
	public static final GenericPlantBlock plantDayloomsYellow = null;
	@GameRegistry.ObjectHolder("dead_grass")
	public static final GenericPlantBlock plantDeadGrass = null;
	@GameRegistry.ObjectHolder("deep_blooms")
	public static final GenericPlantBlock plantDeepBlooms = null;
	@GameRegistry.ObjectHolder("deep_grass")
	public static final GenericPlantBlock plantDeepGrass = null;
	@GameRegistry.ObjectHolder("haunted_flower")
	public static final GenericPlantBlock plantHauntedFlower = null;
	@GameRegistry.ObjectHolder("haven_grass_plant")
	public static final GenericPlantBlock plantHavenGrass = null;
	@GameRegistry.ObjectHolder("horizon_daisies")
	public static final GenericPlantBlock plantHorizonDaisies = null;
	@GameRegistry.ObjectHolder("iro_grass")
	public static final GenericPlantBlock plantIroGrass = null;
	@GameRegistry.ObjectHolder("irotops")
	public static final GenericPlantBlock plantIrotops = null;
	@GameRegistry.ObjectHolder("lelyetian_grass")
	public static final GenericPlantBlock plantLelyetianGrass = null;
	@GameRegistry.ObjectHolder("lelyetian_grass_down")
	public static final GenericPlantBlock plantLelyetianGrassDown = null;
	@GameRegistry.ObjectHolder("lucon_grass")
	public static final GenericPlantBlock plantLuconGrass = null;
	@GameRegistry.ObjectHolder("lunalip")
	public static final GenericPlantBlock plantLunalip = null;
	@GameRegistry.ObjectHolder("luntar")
	public static final GenericPlantBlock plantLuntar = null;
	@GameRegistry.ObjectHolder("lurchians")
	public static final GenericPlantBlock plantLurchians = null;
	@GameRegistry.ObjectHolder("lylips")
	public static final GenericPlantBlock plantLylips = null;
	@GameRegistry.ObjectHolder("magias")
	public static final GenericPlantBlock plantMagias = null;
	@GameRegistry.ObjectHolder("mallow_pile")
	public static final GenericPlantBlock plantMallowPile = null;
	@GameRegistry.ObjectHolder("marsh_tube")
	public static final GenericPlantBlock plantMarshTube = null;
	@GameRegistry.ObjectHolder("mellians")
	public static final GenericPlantBlock plantMellians = null;
	@GameRegistry.ObjectHolder("mystic_bush")
	public static final GenericPlantBlock plantMysticBush = null;
	@GameRegistry.ObjectHolder("mystic_ferns")
	public static final GenericPlantBlock plantMysticFerns = null;
	@GameRegistry.ObjectHolder("ocealites_blue")
	public static final GenericPlantBlock plantOcealitesBlue = null;
	@GameRegistry.ObjectHolder("ocealites_red")
	public static final GenericPlantBlock plantOcealitesRed = null;
	@GameRegistry.ObjectHolder("pertonias")
	public static final GenericPlantBlock plantPertonias = null;
	@GameRegistry.ObjectHolder("rainbow_grass")
	public static final GenericPlantBlock plantRainbowGrass = null;
	@GameRegistry.ObjectHolder("rainbow_grass2")
	public static final GenericPlantBlock plantRainbowGrass2 = null;
	@GameRegistry.ObjectHolder("rainbow_grass3")
	public static final GenericPlantBlock plantRainbowGrass3 = null;
	@GameRegistry.ObjectHolder("rune_bulbs")
	public static final GenericPlantBlock plantRuneBulbs = null;
	@GameRegistry.ObjectHolder("runic_bush")
	public static final GenericPlantBlock plantRunicBush = null;
	@GameRegistry.ObjectHolder("shadow_shrub")
	public static final GenericPlantBlock plantShadowShrub = null;
	@GameRegistry.ObjectHolder("shyre_weed")
	public static final GenericPlantBlock plantShyreWeed = null;
	@GameRegistry.ObjectHolder("silver_grass")
	public static final GenericPlantBlock plantSilverGrass = null;
	@GameRegistry.ObjectHolder("tangle_thorns")
	public static final GenericPlantBlock plantTangleThorns = null;
	@GameRegistry.ObjectHolder("trilliad_bloom")
	public static final GenericPlantBlock plantTrilliadBloom = null;
	@GameRegistry.ObjectHolder("tubeicles")
	public static final GenericPlantBlock plantTubeicles = null;
	@GameRegistry.ObjectHolder("green_waterweeds")
	public static final GenericPlantBlock plantWaterweedsGreen = null;
	@GameRegistry.ObjectHolder("white_waterweeds")
	public static final GenericPlantBlock plantWaterweedsWhite = null;
	@GameRegistry.ObjectHolder("yellow_waterweeds")
	public static final GenericPlantBlock plantWaterweedsYellow = null;
	@GameRegistry.ObjectHolder("blue_lollypop")
	public static final PlantMultiStackable plantLollypopBlue = null;
	@GameRegistry.ObjectHolder("red_lollypop")
	public static final PlantMultiStackable plantLollypopRed = null;
	@GameRegistry.ObjectHolder("yellow_lollypop")
	public static final PlantMultiStackable plantLollypopYellow = null;
	@GameRegistry.ObjectHolder("ancient_vines")
	public static final PlantStackable plantAncientVines = null;
	@GameRegistry.ObjectHolder("ancient_vines_cap")
	public static final PlantStackable plantAncientVinesCap = null;
	@GameRegistry.ObjectHolder("blood_pine_stem")
	public static final PlantStackable plantBloodPineStem = null;
	@GameRegistry.ObjectHolder("blood_pine")
	public static final PlantStackable plantBloodPine = null;
	@GameRegistry.ObjectHolder("blood_spikes")
	public static final PlantStackable plantBloodSpikes = null;
	@GameRegistry.ObjectHolder("blood_strands")
	public static final PlantStackable plantBloodStrands = null;
	@GameRegistry.ObjectHolder("bulb_stock")
	public static final PlantStackable plantBulbStock = null;
	@GameRegistry.ObjectHolder("bulb_stock_cap")
	public static final PlantStackable plantBulbStockCap = null;
	@GameRegistry.ObjectHolder("celebulbs_stem")
	public static final PlantStackable plantCelebulbsStem = null;
	@GameRegistry.ObjectHolder("celebulbs_green")
	public static final PlantStackable plantCelebulbsGreen = null;
	@GameRegistry.ObjectHolder("celebulbs_yellow")
	public static final PlantStackable plantCelebulbsYellow = null;
	@GameRegistry.ObjectHolder("coral_cage")
	public static final PlantStackable plantCoralCage = null;
	@GameRegistry.ObjectHolder("dawn_stocks")
	public static final PlantStackable plantDawnStocks = null;
	@GameRegistry.ObjectHolder("dawn_stocks_top")
	public static final PlantStackable plantDawnStocksTop = null;
	@GameRegistry.ObjectHolder("dawnwood_bars")
	public static final PlantStackable plantDawnwoodBars = null;
	@GameRegistry.ObjectHolder("deep_bulb")
	public static final PlantStackable plantDeepBulb = null;
	@GameRegistry.ObjectHolder("deep_vines")
	public static final PlantStackable plantDeepVines = null;
	@GameRegistry.ObjectHolder("eye_shrub_stem")
	public static final PlantStackable plantEyeShrubStem = null;
	@GameRegistry.ObjectHolder("eye_shrub")
	public static final PlantStackable plantEyeShrub = null;
	@GameRegistry.ObjectHolder("flake_vine")
	public static final PlantStackable plantFlakeVine = null;
	@GameRegistry.ObjectHolder("flake_vine_top")
	public static final PlantStackable plantFlakeVineTop = null;
	@GameRegistry.ObjectHolder("garden_grass")
	public static final PlantStackable plantGardenGrass = null;
	@GameRegistry.ObjectHolder("havendales_blue_stem")
	public static final PlantStackable plantHavendalesBlueStem = null;
	@GameRegistry.ObjectHolder("havendales_blue")
	public static final PlantStackable plantHavendalesBlue = null;
	@GameRegistry.ObjectHolder("havendales_pink_stem")
	public static final PlantStackable plantHavendalesPinkStem = null;
	@GameRegistry.ObjectHolder("havendales_pink")
	public static final PlantStackable plantHavendalesPink = null;
	@GameRegistry.ObjectHolder("havendales_yellow_stem")
	public static final PlantStackable plantHavendalesYellowStem = null;
	@GameRegistry.ObjectHolder("havendales_yellow")
	public static final PlantStackable plantHavendalesYellow = null;
	@GameRegistry.ObjectHolder("lelyetian_stem")
	public static final PlantStackable plantLelyetianStem = null;
	@GameRegistry.ObjectHolder("lelyetian_stem_cap")
	public static final PlantStackable plantLelyetianStemCap = null;
	@GameRegistry.ObjectHolder("lelyetian_stem_cap_down")
	public static final PlantStackable plantLelyetianStemCapDown = null;
	@GameRegistry.ObjectHolder("lelyetian_wiggler")
	public static final PlantStackable plantLelyetianWiggler = null;
	@GameRegistry.ObjectHolder("lelyetian_wiggler_bottom")
	public static final PlantStackable plantLelyetianWigglerBottom = null;
	@GameRegistry.ObjectHolder("lelyetian_wiggler_top")
	public static final PlantStackable plantLelyetianWigglerTop = null;
	@GameRegistry.ObjectHolder("green_peppermint")
	public static final PlantStackable plantPeppermintGreen = null;
	@GameRegistry.ObjectHolder("red_peppermint")
	public static final PlantStackable plantPeppermintRed = null;
	@GameRegistry.ObjectHolder("plastic_stick")
	public static final PlantStackable plantPlasticStick = null;
	@GameRegistry.ObjectHolder("candy_tube")
	public static final PlantStackable plantCandyTube = null;
	@GameRegistry.ObjectHolder("shadicles")
	public static final PlantStackable plantShadicles = null;
	@GameRegistry.ObjectHolder("shadicles_top")
	public static final PlantStackable plantShadiclesTop = null;
	@GameRegistry.ObjectHolder("shyre_stock")
	public static final PlantStackable plantShyreStock = null;
	@GameRegistry.ObjectHolder("shyre_cap")
	public static final PlantStackable plantShyreCap = null;
	@GameRegistry.ObjectHolder("shyre_cap_down")
	public static final PlantStackable plantShyreCapDown = null;
	@GameRegistry.ObjectHolder("vox_fungi_stem")
	public static final PlantStackable plantVoxFungiStem = null;
	@GameRegistry.ObjectHolder("vox_fungi")
	public static final PlantStackable plantVoxFungi = null;
	@GameRegistry.ObjectHolder("vox_tentacles_stem")
	public static final PlantStackable plantVoxTentaclesStem = null;
	@GameRegistry.ObjectHolder("vox_tentacles")
	public static final PlantStackable plantVoxTentacles = null;
	@GameRegistry.ObjectHolder("red_waterweeds")
	public static final RedWaterweeds plantWaterweedsRed = null;
	@GameRegistry.ObjectHolder("creep_vines")
	public static final VinesBlock plantCreepVines = null;

   	@GameRegistry.ObjectHolder("bubble_berry_crop")
	public static final CropBlock cropBubbleBerries = null; // Seeds dropped in Lborean
	@GameRegistry.ObjectHolder("chilli_crop")
	public static final CropBlock cropChilli = null; // Seeds dropped in Lelyetia
	@GameRegistry.ObjectHolder("eye_bulb_crop")
	public static final CropBlock cropEyeBulbs = null; // Dropped by Bulb stocks in Abyss grottos
	@GameRegistry.ObjectHolder("floracles_crop")
	public static final CropBlock cropFloracles = null; // Lborean red waterweeds
	@GameRegistry.ObjectHolder("goldicaps_crop")
	public static final CropBlock cropGoldicaps = null; // Garden Castle
	@GameRegistry.ObjectHolder("heart_fruit_crop")
	public static final CropBlock cropHeartFruit = null; // Seeds dropped by tangle thorns in Precasia
	@GameRegistry.ObjectHolder("holly_tops_crop")
	public static final CropBlock cropHollyTops = null; // Dungeon Chests
	@GameRegistry.ObjectHolder("lunacrike_crop")
	public static final CropBlock cropLunacrike = null; // Zal herbalist & lunar garden
	@GameRegistry.ObjectHolder("luna_globe_crop")
	public static final CropBlock cropLunaGlobes = null; // Zal herbalist & lunar garden
	@GameRegistry.ObjectHolder("lunalon_crop")
	public static final CropBlock cropLunalons = null; // Zal herbalist & lunar garden
	@GameRegistry.ObjectHolder("magic_marang_crop")
	public static final CropBlock cropMagicMarang = null; // Haunted Leaves
	@GameRegistry.ObjectHolder("mystic_shroom_crop")
	public static final CropBlock cropMysticShrooms = null; // Mysterium & Haunted Castle
	@GameRegistry.ObjectHolder("rosidon_crop")
	public static final CropBlock cropRosidons = null; // Vinocorne
	@GameRegistry.ObjectHolder("tea_crop")
	public static final CropBlock cropTea = null; // Overworld drops
	@GameRegistry.ObjectHolder("thorny_plant_crop")
	public static final CropBlock cropThornyPlant = null; // TODO Obtain method
	@GameRegistry.ObjectHolder("trilliad_crop")
	public static final CropBlock cropTrilliads = null; // Seeds dropped by trilliad blooms

	@GameRegistry.ObjectHolder("bane_statue")
	public static final StatueBlock statueBane = null;
	@GameRegistry.ObjectHolder("gold_bane_statue")
	public static final StatueBlock statueBaneGold = null;
	@GameRegistry.ObjectHolder("ornate_bane_statue")
	public static final StatueBlock statueBaneOrnate = null;
	@GameRegistry.ObjectHolder("baroness_statue")
	public static final StatueBlock statueBaroness = null;
	@GameRegistry.ObjectHolder("gold_baroness_statue")
	public static final StatueBlock statueBaronessGold = null;
	@GameRegistry.ObjectHolder("ornate_baroness_statue")
	public static final StatueBlock statueBaronessOrnate = null;
	@GameRegistry.ObjectHolder("clunkhead_statue")
	public static final StatueBlock statueClunkhead = null;
	@GameRegistry.ObjectHolder("gold_clunkhead_statue")
	public static final StatueBlock statueClunkheadGold = null;
	@GameRegistry.ObjectHolder("ornate_clunkhead_statue")
	public static final StatueBlock statueClunkheadOrnate = null;
	@GameRegistry.ObjectHolder("coniferon_statue")
	public static final StatueBlock statueConiferon = null;
	@GameRegistry.ObjectHolder("gold_coniferon_statue")
	public static final StatueBlock statueConiferonGold = null;
	@GameRegistry.ObjectHolder("ornate_coniferon_statue")
	public static final StatueBlock statueConiferonOrnate = null;
	@GameRegistry.ObjectHolder("corallus_statue")
	public static final StatueBlock statueCorallus = null;
	@GameRegistry.ObjectHolder("gold_corallus_statue")
	public static final StatueBlock statueCorallusGold = null;
	@GameRegistry.ObjectHolder("ornate_corallus_statue")
	public static final StatueBlock statueCorallusOrnate = null;
	@GameRegistry.ObjectHolder("cotton_candor_statue")
	public static final StatueBlock statueCottonCandor = null;
	@GameRegistry.ObjectHolder("gold_cotton_candor_statue")
	public static final StatueBlock statueCottonCandorGold = null;
	@GameRegistry.ObjectHolder("ornate_cotton_candor_statue")
	public static final StatueBlock statueCottonCandorOrnate = null;
	@GameRegistry.ObjectHolder("craexxeus_statue")
	public static final StatueBlock statueCraexxeus = null;
	@GameRegistry.ObjectHolder("gold_craexxeus_statue")
	public static final StatueBlock statueCraexxeusGold = null;
	@GameRegistry.ObjectHolder("ornate_craexxeus_statue")
	public static final StatueBlock statueCraexxeusOrnate = null;
	@GameRegistry.ObjectHolder("creep_statue")
	public static final StatueBlock statueCreep = null;
	@GameRegistry.ObjectHolder("gold_creep_statue")
	public static final StatueBlock statueCreepGold = null;
	@GameRegistry.ObjectHolder("ornate_creep_statue")
	public static final StatueBlock statueCreepOrnate = null;
	@GameRegistry.ObjectHolder("crystocore_statue")
	public static final StatueBlock statueCrystocore = null;
	@GameRegistry.ObjectHolder("gold_crystocore_statue")
	public static final StatueBlock statueCrystocoreGold = null;
	@GameRegistry.ObjectHolder("ornate_crystocore_statue")
	public static final StatueBlock statueCrystocoreOrnate = null;
	@GameRegistry.ObjectHolder("dracyon_statue")
	public static final StatueBlock statueDracyon = null;
	@GameRegistry.ObjectHolder("gold_dracyon_statue")
	public static final StatueBlock statueDracyonGold = null;
	@GameRegistry.ObjectHolder("ornate_dracyon_statue")
	public static final StatueBlock statueDracyonOrnate = null;
	@GameRegistry.ObjectHolder("elusive_statue")
	public static final StatueBlock statueElusive = null;
	@GameRegistry.ObjectHolder("gold_elusive_statue")
	public static final StatueBlock statueElusiveGold = null;
	@GameRegistry.ObjectHolder("ornate_elusive_statue")
	public static final StatueBlock statueElusiveOrnate = null;
	@GameRegistry.ObjectHolder("flash_statue")
	public static final StatueBlock statueFlash = null;
	@GameRegistry.ObjectHolder("gold_flash_statue")
	public static final StatueBlock statueFlashGold = null;
	@GameRegistry.ObjectHolder("ornate_flash_statue")
	public static final StatueBlock statueFlashOrnate = null;
	@GameRegistry.ObjectHolder("goldorth_statue")
	public static final StatueBlock statueGoldorth = null;
	@GameRegistry.ObjectHolder("gold_goldorth_statue")
	public static final StatueBlock statueGoldorthGold = null;
	@GameRegistry.ObjectHolder("ornate_goldorth_statue")
	public static final StatueBlock statueGoldorthOrnate = null;
	@GameRegistry.ObjectHolder("graw_statue")
	public static final StatueBlock statueGraw = null;
	@GameRegistry.ObjectHolder("gold_graw_statue")
	public static final StatueBlock statueGrawGold = null;
	@GameRegistry.ObjectHolder("ornate_graw_statue")
	public static final StatueBlock statueGrawOrnate = null;
	@GameRegistry.ObjectHolder("guardian_statue")
	public static final StatueBlock statueGuardian = null;
	@GameRegistry.ObjectHolder("gold_guardian_statue")
	public static final StatueBlock statueGuardianGold = null;
	@GameRegistry.ObjectHolder("ornate_guardian_statue")
	public static final StatueBlock statueGuardianOrnate = null;
	@GameRegistry.ObjectHolder("gyro_statue")
	public static final StatueBlock statueGyro = null;
	@GameRegistry.ObjectHolder("gold_gyro_statue")
	public static final StatueBlock statueGyroGold = null;
	@GameRegistry.ObjectHolder("ornate_gyro_statue")
	public static final StatueBlock statueGyroOrnate = null;
	@GameRegistry.ObjectHolder("harkos_statue")
	public static final StatueBlock statueHarkos = null;
	@GameRegistry.ObjectHolder("gold_harkos_statue")
	public static final StatueBlock statueHarkosGold = null;
	@GameRegistry.ObjectHolder("ornate_harkos_statue")
	public static final StatueBlock statueHarkosOrnate = null;
	@GameRegistry.ObjectHolder("hive_king_statue")
	public static final StatueBlock statueHiveKing = null;
	@GameRegistry.ObjectHolder("gold_hive_king_statue")
	public static final StatueBlock statueHiveKingGold = null;
	@GameRegistry.ObjectHolder("ornate_hive_king_statue")
	public static final StatueBlock statueHiveKingOrnate = null;
	@GameRegistry.ObjectHolder("horon_statue")
	public static final StatueBlock statueHoron = null;
	@GameRegistry.ObjectHolder("gold_horon_statue")
	public static final StatueBlock statueHoronGold = null;
	@GameRegistry.ObjectHolder("ornate_horon_statue")
	public static final StatueBlock statueHoronOrnate = null;
	@GameRegistry.ObjectHolder("hydrolisk_statue")
	public static final StatueBlock statueHydrolisk = null;
	@GameRegistry.ObjectHolder("gold_hydrolisk_statue")
	public static final StatueBlock statueHydroliskGold = null;
	@GameRegistry.ObjectHolder("ornate_hydrolisk_statue")
	public static final StatueBlock statueHydroliskOrnate = null;
	@GameRegistry.ObjectHolder("kajaros_statue")
	public static final StatueBlock statueKajaros = null;
	@GameRegistry.ObjectHolder("gold_kajaros_statue")
	public static final StatueBlock statueKajarosGold = null;
	@GameRegistry.ObjectHolder("ornate_kajaros_statue")
	public static final StatueBlock statueKajarosOrnate = null;
	@GameRegistry.ObjectHolder("king_bambambam_statue")
	public static final StatueBlock statueKingBamBamBam = null;
	@GameRegistry.ObjectHolder("gold_king_bambambam_statue")
	public static final StatueBlock statueKingBamBamBamGold = null;
	@GameRegistry.ObjectHolder("ornate_king_bambambam_statue")
	public static final StatueBlock statueKingBamBamBamOrnate = null;
	@GameRegistry.ObjectHolder("king_shroomus_statue")
	public static final StatueBlock statueKingShroomus = null;
	@GameRegistry.ObjectHolder("gold_king_shroomus_statue")
	public static final StatueBlock statueKingShroomusGold = null;
	@GameRegistry.ObjectHolder("ornate_king_shroomus_statue")
	public static final StatueBlock statueKingShroomusOrnate = null;
	@GameRegistry.ObjectHolder("klobber_statue")
	public static final StatueBlock statueKlobber = null;
	@GameRegistry.ObjectHolder("gold_klobber_statue")
	public static final StatueBlock statueKlobberGold = null;
	@GameRegistry.ObjectHolder("ornate_klobber_statue")
	public static final StatueBlock statueKlobberOrnate = null;
	@GameRegistry.ObjectHolder("kror_statue")
	public static final StatueBlock statueKror = null;
	@GameRegistry.ObjectHolder("gold_kror_statue")
	public static final StatueBlock statueKrorGold = null;
	@GameRegistry.ObjectHolder("ornate_kror_statue")
	public static final StatueBlock statueKrorOrnate = null;
	@GameRegistry.ObjectHolder("mechbot_statue")
	public static final StatueBlock statueMechbot = null;
	@GameRegistry.ObjectHolder("gold_mechbot_statue")
	public static final StatueBlock statueMechbotGold = null;
	@GameRegistry.ObjectHolder("ornate_mechbot_statue")
	public static final StatueBlock statueMechbotOrnate = null;
	@GameRegistry.ObjectHolder("mirage_statue")
	public static final StatueBlock statueMirage = null;
	@GameRegistry.ObjectHolder("gold_mirage_statue")
	public static final StatueBlock statueMirageGold = null;
	@GameRegistry.ObjectHolder("ornate_mirage_statue")
	public static final StatueBlock statueMirageOrnate = null;
	@GameRegistry.ObjectHolder("miskel_statue")
	public static final StatueBlock statueMiskel = null;
	@GameRegistry.ObjectHolder("gold_miskel_statue")
	public static final StatueBlock statueMiskelGold = null;
	@GameRegistry.ObjectHolder("ornate_miskel_statue")
	public static final StatueBlock statueMiskelOrnate = null;
	@GameRegistry.ObjectHolder("nethengeic_wither_statue")
	public static final StatueBlock statueNethengeicWither = null;
	@GameRegistry.ObjectHolder("gold_nethengeic_wither_statue")
	public static final StatueBlock statueNethengeicWitherGold = null;
	@GameRegistry.ObjectHolder("ornate_nethengeic_wither_statue")
	public static final StatueBlock statueNethengeicWitherOrnate = null;
	@GameRegistry.ObjectHolder("okazor_statue")
	public static final StatueBlock statueOkazor = null;
	@GameRegistry.ObjectHolder("gold_okazor_statue")
	public static final StatueBlock statueOkazorGold = null;
	@GameRegistry.ObjectHolder("ornate_okazor_statue")
	public static final StatueBlock statueOkazorOrnate = null;
	@GameRegistry.ObjectHolder("penumbra_statue")
	public static final StatueBlock statuePenumbra = null;
	@GameRegistry.ObjectHolder("gold_penumbra_statue")
	public static final StatueBlock statuePenumbraGold = null;
	@GameRegistry.ObjectHolder("ornate_penumbra_statue")
	public static final StatueBlock statuePenumbraOrnate = null;
	@GameRegistry.ObjectHolder("proshield_statue")
	public static final StatueBlock statueProshield = null;
	@GameRegistry.ObjectHolder("gold_proshield_statue")
	public static final StatueBlock statueProshieldGold = null;
	@GameRegistry.ObjectHolder("ornate_proshield_statue")
	public static final StatueBlock statueProshieldOrnate = null;
	@GameRegistry.ObjectHolder("raxxan_statue")
	public static final StatueBlock statueRaxxan = null;
	@GameRegistry.ObjectHolder("gold_raxxan_statue")
	public static final StatueBlock statueRaxxanGold = null;
	@GameRegistry.ObjectHolder("ornate_raxxan_statue")
	public static final StatueBlock statueRaxxanOrnate = null;
	@GameRegistry.ObjectHolder("rockrider_statue")
	public static final StatueBlock statueRockrider = null;
	@GameRegistry.ObjectHolder("gold_rockrider_statue")
	public static final StatueBlock statueRockriderGold = null;
	@GameRegistry.ObjectHolder("ornate_rockrider_statue")
	public static final StatueBlock statueRockriderOrnate = null;
	@GameRegistry.ObjectHolder("shadowlord_statue")
	public static final StatueBlock statueShadowlord = null;
	@GameRegistry.ObjectHolder("gold_shadowlord_statue")
	public static final StatueBlock statueShadowlordGold = null;
	@GameRegistry.ObjectHolder("ornate_shadowlord_statue")
	public static final StatueBlock statueShadowlordOrnate = null;
	@GameRegistry.ObjectHolder("silverfoot_statue")
	public static final StatueBlock statueSilverfoot = null;
	@GameRegistry.ObjectHolder("gold_silverfoot_statue")
	public static final StatueBlock statueSilverfootGold = null;
	@GameRegistry.ObjectHolder("ornate_silverfoot_statue")
	public static final StatueBlock statueSilverfootOrnate = null;
	@GameRegistry.ObjectHolder("skeletron_statue")
	public static final StatueBlock statueSkeletron = null;
	@GameRegistry.ObjectHolder("gold_skeletron_statue")
	public static final StatueBlock statueSkeletronGold = null;
	@GameRegistry.ObjectHolder("ornate_skeletron_statue")
	public static final StatueBlock statueSkeletronOrnate = null;
	@GameRegistry.ObjectHolder("smash_statue")
	public static final StatueBlock statueSmash = null;
	@GameRegistry.ObjectHolder("gold_smash_statue")
	public static final StatueBlock statueSmashGold = null;
	@GameRegistry.ObjectHolder("ornate_smash_statue")
	public static final StatueBlock statueSmashOrnate = null;
	@GameRegistry.ObjectHolder("tyrosaur_statue")
	public static final StatueBlock statueTyrosaur = null;
	@GameRegistry.ObjectHolder("gold_tyrosaur_statue")
	public static final StatueBlock statueTyrosaurGold = null;
	@GameRegistry.ObjectHolder("ornate_tyrosaur_statue")
	public static final StatueBlock statueTyrosaurOrnate = null;
	@GameRegistry.ObjectHolder("vinocorne_statue")
	public static final StatueBlock statueVinocorne = null;
	@GameRegistry.ObjectHolder("gold_vinocorne_statue")
	public static final StatueBlock statueVinocorneGold = null;
	@GameRegistry.ObjectHolder("ornate_vinocorne_statue")
	public static final StatueBlock statueVinocorneOrnate = null;
	@GameRegistry.ObjectHolder("visualent_statue")
	public static final StatueBlock statueVisualent = null;
	@GameRegistry.ObjectHolder("gold_visualent_statue")
	public static final StatueBlock statueVisualentGold = null;
	@GameRegistry.ObjectHolder("ornate_visualent_statue")
	public static final StatueBlock statueVisualentOrnate = null;
	@GameRegistry.ObjectHolder("voxxulon_statue")
	public static final StatueBlock statueVoxxulon = null;
	@GameRegistry.ObjectHolder("gold_voxxulon_statue")
	public static final StatueBlock statueVoxxulonGold = null;
	@GameRegistry.ObjectHolder("ornate_voxxulon_statue")
	public static final StatueBlock statueVoxxulonOrnate = null;
	@GameRegistry.ObjectHolder("xxeus_statue")
	public static final StatueBlock statueXxeus = null;
	@GameRegistry.ObjectHolder("gold_xxeus_statue")
	public static final StatueBlock statueXxeusGold = null;
	@GameRegistry.ObjectHolder("ornate_xxeus_statue")
	public static final StatueBlock statueXxeusOrnate = null;

	@GameRegistry.ObjectHolder("ancient_banner")
	public static final BannerBlock bannerAncient = null;
	@GameRegistry.ObjectHolder("gilded_ancient_banner")
	public static final BannerBlock bannerAncientGilded = null;
	@GameRegistry.ObjectHolder("encrusted_ancient_banner")
	public static final BannerBlock bannerAncientEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_ancient_banner")
	public static final BannerBlock bannerAncientBejewelled = null;
	@GameRegistry.ObjectHolder("baron_banner")
	public static final BannerBlock bannerBaron = null;
	@GameRegistry.ObjectHolder("gilded_baron_banner")
	public static final BannerBlock bannerBaronGilded = null;
	@GameRegistry.ObjectHolder("encrusted_baron_banner")
	public static final BannerBlock bannerBaronEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_baron_banner")
	public static final BannerBlock bannerBaronBejewelled = null;
	@GameRegistry.ObjectHolder("blood_banner")
	public static final BannerBlock bannerBlood = null;
	@GameRegistry.ObjectHolder("gilded_blood_banner")
	public static final BannerBlock bannerBloodGilded = null;
	@GameRegistry.ObjectHolder("encrusted_blood_banner")
	public static final BannerBlock bannerBloodEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_blood_banner")
	public static final BannerBlock bannerBloodBejewelled = null;
	@GameRegistry.ObjectHolder("boreic_banner")
	public static final BannerBlock bannerBoreic = null;
	@GameRegistry.ObjectHolder("gilded_boreic_banner")
	public static final BannerBlock bannerBoreicGilded = null;
	@GameRegistry.ObjectHolder("encrusted_boreic_banner")
	public static final BannerBlock bannerBoreicEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_boreic_banner")
	public static final BannerBlock bannerBoreicBejewelled = null;
	@GameRegistry.ObjectHolder("candy_banner")
	public static final BannerBlock bannerCandy = null;
	@GameRegistry.ObjectHolder("gilded_candy_banner")
	public static final BannerBlock bannerCandyGilded = null;
	@GameRegistry.ObjectHolder("encrusted_candy_banner")
	public static final BannerBlock bannerCandyEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_candy_banner")
	public static final BannerBlock bannerCandyBejewelled = null;
	@GameRegistry.ObjectHolder("clown_banner")
	public static final BannerBlock bannerClown = null;
	@GameRegistry.ObjectHolder("gilded_clown_banner")
	public static final BannerBlock bannerClownGilded = null;
	@GameRegistry.ObjectHolder("encrusted_clown_banner")
	public static final BannerBlock bannerClownEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_clown_banner")
	public static final BannerBlock bannerClownBejewelled = null;
	@GameRegistry.ObjectHolder("creation_banner")
	public static final BannerBlock bannerCreation = null;
	@GameRegistry.ObjectHolder("gilded_creation_banner")
	public static final BannerBlock bannerCreationGilded = null;
	@GameRegistry.ObjectHolder("encrusted_creation_banner")
	public static final BannerBlock bannerCreationEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_creation_banner")
	public static final BannerBlock bannerCreationBejewelled = null;
	@GameRegistry.ObjectHolder("creepoid_banner")
	public static final BannerBlock bannerCreepoid = null;
	@GameRegistry.ObjectHolder("gilded_creepoid_banner")
	public static final BannerBlock bannerCreepoidGilded = null;
	@GameRegistry.ObjectHolder("encrusted_creepoid_banner")
	public static final BannerBlock bannerCreepoidEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_creepoid_banner")
	public static final BannerBlock bannerCreepoidBejewelled = null;
	@GameRegistry.ObjectHolder("creepy_banner")
	public static final BannerBlock bannerCreepy = null;
	@GameRegistry.ObjectHolder("gilded_creepy_banner")
	public static final BannerBlock bannerCreepyGilded = null;
	@GameRegistry.ObjectHolder("encrusted_creepy_banner")
	public static final BannerBlock bannerCreepyEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_creepy_banner")
	public static final BannerBlock bannerCreepyBejewelled = null;
	@GameRegistry.ObjectHolder("crystal_banner")
	public static final BannerBlock bannerCrystal = null;
	@GameRegistry.ObjectHolder("gilded_crystal_banner")
	public static final BannerBlock bannerCrystalGilded = null;
	@GameRegistry.ObjectHolder("encrusted_crystal_banner")
	public static final BannerBlock bannerCrystalEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_crystal_banner")
	public static final BannerBlock bannerCrystalBejewelled = null;
	@GameRegistry.ObjectHolder("deep_banner")
	public static final BannerBlock bannerDeep = null;
	@GameRegistry.ObjectHolder("gilded_deep_banner")
	public static final BannerBlock bannerDeepGilded = null;
	@GameRegistry.ObjectHolder("encrusted_deep_banner")
	public static final BannerBlock bannerDeepEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_deep_banner")
	public static final BannerBlock bannerDeepBejewelled = null;
	@GameRegistry.ObjectHolder("dustopian_banner")
	public static final BannerBlock bannerDustopian = null;
	@GameRegistry.ObjectHolder("gilded_dustopian_banner")
	public static final BannerBlock bannerDustopianGilded = null;
	@GameRegistry.ObjectHolder("encrusted_dustopian_banner")
	public static final BannerBlock bannerDustopianEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_dustopian_banner")
	public static final BannerBlock bannerDustopianBejewelled = null;
	@GameRegistry.ObjectHolder("energy_banner")
	public static final BannerBlock bannerEnergy = null;
	@GameRegistry.ObjectHolder("gilded_energy_banner")
	public static final BannerBlock bannerEnergyGilded = null;
	@GameRegistry.ObjectHolder("encrusted_energy_banner")
	public static final BannerBlock bannerEnergyEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_energy_banner")
	public static final BannerBlock bannerEnergyBejewelled = null;
	@GameRegistry.ObjectHolder("fungal_banner")
	public static final BannerBlock bannerFungal = null;
	@GameRegistry.ObjectHolder("gilded_fungal_banner")
	public static final BannerBlock bannerFungalGilded = null;
	@GameRegistry.ObjectHolder("encrusted_fungal_banner")
	public static final BannerBlock bannerFungalEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_fungal_banner")
	public static final BannerBlock bannerFungalBejewelled = null;
	@GameRegistry.ObjectHolder("ghostly_banner")
	public static final BannerBlock bannerGhostly = null;
	@GameRegistry.ObjectHolder("gilded_ghostly_banner")
	public static final BannerBlock bannerGhostlyGilded = null;
	@GameRegistry.ObjectHolder("encrusted_ghostly_banner")
	public static final BannerBlock bannerGhostlyEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_ghostly_banner")
	public static final BannerBlock bannerGhostlyBejewelled = null;
	@GameRegistry.ObjectHolder("ghoul_banner")
	public static final BannerBlock bannerGhoul = null;
	@GameRegistry.ObjectHolder("gilded_ghoul_banner")
	public static final BannerBlock bannerGhoulGilded = null;
	@GameRegistry.ObjectHolder("encrusted_ghoul_banner")
	public static final BannerBlock bannerGhoulEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_ghoul_banner")
	public static final BannerBlock bannerGhoulBejewelled = null;
	@GameRegistry.ObjectHolder("gingerbread_banner")
	public static final BannerBlock bannerGingerbread = null;
	@GameRegistry.ObjectHolder("gilded_gingerbread_banner")
	public static final BannerBlock bannerGingerbreadGilded = null;
	@GameRegistry.ObjectHolder("encrusted_gingerbread_banner")
	public static final BannerBlock bannerGingerbreadEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_gingerbread_banner")
	public static final BannerBlock bannerGingerbreadBejewelled = null;
	@GameRegistry.ObjectHolder("haunted_banner")
	public static final BannerBlock bannerHaunted = null;
	@GameRegistry.ObjectHolder("gilded_haunted_banner")
	public static final BannerBlock bannerHauntedGilded = null;
	@GameRegistry.ObjectHolder("encrusted_haunted_banner")
	public static final BannerBlock bannerHauntedEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_haunted_banner")
	public static final BannerBlock bannerHauntedBejewelled = null;
	@GameRegistry.ObjectHolder("illusion_banner")
	public static final BannerBlock bannerIllusion = null;
	@GameRegistry.ObjectHolder("gilded_illusion_banner")
	public static final BannerBlock bannerIllusionGilded = null;
	@GameRegistry.ObjectHolder("encrusted_illusion_banner")
	public static final BannerBlock bannerIllusionEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_illusion_banner")
	public static final BannerBlock bannerIllusionBejewelled = null;
	@GameRegistry.ObjectHolder("immortal_banner")
	public static final BannerBlock bannerImmortal = null;
	@GameRegistry.ObjectHolder("gilded_immortal_banner")
	public static final BannerBlock bannerImmortalGilded = null;
	@GameRegistry.ObjectHolder("encrusted_immortal_banner")
	public static final BannerBlock bannerImmortalEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_immortal_banner")
	public static final BannerBlock bannerImmortalBejewelled = null;
	@GameRegistry.ObjectHolder("lelyetian_banner")
	public static final BannerBlock bannerLelyetian = null;
	@GameRegistry.ObjectHolder("gilded_lelyetian_banner")
	public static final BannerBlock bannerLelyetianGilded = null;
	@GameRegistry.ObjectHolder("encrusted_lelyetian_banner")
	public static final BannerBlock bannerLelyetianEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_lelyetian_banner")
	public static final BannerBlock bannerLelyetianBejewelled = null;
	@GameRegistry.ObjectHolder("light_banner")
	public static final BannerBlock bannerLight = null;
	@GameRegistry.ObjectHolder("gilded_light_banner")
	public static final BannerBlock bannerLightGilded = null;
	@GameRegistry.ObjectHolder("encrusted_light_banner")
	public static final BannerBlock bannerLightEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_light_banner")
	public static final BannerBlock bannerLightBejewelled = null;
	@GameRegistry.ObjectHolder("lotto_banner")
	public static final BannerBlock bannerLotto = null;
	@GameRegistry.ObjectHolder("gilded_lotto_banner")
	public static final BannerBlock bannerLottoGilded = null;
	@GameRegistry.ObjectHolder("encrusted_lotto_banner")
	public static final BannerBlock bannerLottoEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_lotto_banner")
	public static final BannerBlock bannerLottoBejewelled = null;
	@GameRegistry.ObjectHolder("lunar_banner")
	public static final BannerBlock bannerLunar = null;
	@GameRegistry.ObjectHolder("gilded_lunar_banner")
	public static final BannerBlock bannerLunarGilded = null;
	@GameRegistry.ObjectHolder("encrusted_lunar_banner")
	public static final BannerBlock bannerLunarEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_lunar_banner")
	public static final BannerBlock bannerLunarBejewelled = null;
	@GameRegistry.ObjectHolder("mecha_banner")
	public static final BannerBlock bannerMecha = null;
	@GameRegistry.ObjectHolder("gilded_mecha_banner")
	public static final BannerBlock bannerMechaGilded = null;
	@GameRegistry.ObjectHolder("encrusted_mecha_banner")
	public static final BannerBlock bannerMechaEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_mecha_banner")
	public static final BannerBlock bannerMechaBejewelled = null;
	@GameRegistry.ObjectHolder("nethengeic_banner")
	public static final BannerBlock bannerNethengeic = null;
	@GameRegistry.ObjectHolder("gilded_nethengeic_banner")
	public static final BannerBlock bannerNethengeicGilded = null;
	@GameRegistry.ObjectHolder("encrusted_nethengeic_banner")
	public static final BannerBlock bannerNethengeicEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_nethengeic_banner")
	public static final BannerBlock bannerNethengeicBejewelled = null;
	@GameRegistry.ObjectHolder("nether_banner")
	public static final BannerBlock bannerNether = null;
	@GameRegistry.ObjectHolder("gilded_nether_banner")
	public static final BannerBlock bannerNetherGilded = null;
	@GameRegistry.ObjectHolder("encrusted_nether_banner")
	public static final BannerBlock bannerNetherEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_nether_banner")
	public static final BannerBlock bannerNetherBejewelled = null;
	@GameRegistry.ObjectHolder("rosidian_banner")
	public static final BannerBlock bannerRosidian = null;
	@GameRegistry.ObjectHolder("gilded_rosidian_banner")
	public static final BannerBlock bannerRosidianGilded = null;
	@GameRegistry.ObjectHolder("encrusted_rosidian_banner")
	public static final BannerBlock bannerRosidianEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_rosidian_banner")
	public static final BannerBlock bannerRosidianBejewelled = null;
	@GameRegistry.ObjectHolder("runic_banner")
	public static final BannerBlock bannerRunic = null;
	@GameRegistry.ObjectHolder("gilded_runic_banner")
	public static final BannerBlock bannerRunicGilded = null;
	@GameRegistry.ObjectHolder("encrusted_runic_banner")
	public static final BannerBlock bannerRunicEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_runic_banner")
	public static final BannerBlock bannerRunicBejewelled = null;
	@GameRegistry.ObjectHolder("sea_banner")
	public static final BannerBlock bannerSea = null;
	@GameRegistry.ObjectHolder("gilded_sea_banner")
	public static final BannerBlock bannerSeaGilded = null;
	@GameRegistry.ObjectHolder("encrusted_sea_banner")
	public static final BannerBlock bannerSeaEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_sea_banner")
	public static final BannerBlock bannerSeaBejewelled = null;
	@GameRegistry.ObjectHolder("shadow_banner")
	public static final BannerBlock bannerShadow = null;
	@GameRegistry.ObjectHolder("gilded_shadow_banner")
	public static final BannerBlock bannerShadowGilded = null;
	@GameRegistry.ObjectHolder("encrusted_shadow_banner")
	public static final BannerBlock bannerShadowEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_shadow_banner")
	public static final BannerBlock bannerShadowBejewelled = null;
	@GameRegistry.ObjectHolder("shiny_banner")
	public static final BannerBlock bannerShiny = null;
	@GameRegistry.ObjectHolder("gilded_shiny_banner")
	public static final BannerBlock bannerShinyGilded = null;
	@GameRegistry.ObjectHolder("encrusted_shiny_banner")
	public static final BannerBlock bannerShinyEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_shiny_banner")
	public static final BannerBlock bannerShinyBejewelled = null;
	@GameRegistry.ObjectHolder("shyre_banner")
	public static final BannerBlock bannerShyre = null;
	@GameRegistry.ObjectHolder("gilded_shyre_banner")
	public static final BannerBlock bannerShyreGilded = null;
	@GameRegistry.ObjectHolder("encrusted_shyre_banner")
	public static final BannerBlock bannerShyreEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_shyre_banner")
	public static final BannerBlock bannerShyreBejewelled = null;
	@GameRegistry.ObjectHolder("skeletal_banner")
	public static final BannerBlock bannerSkeletal = null;
	@GameRegistry.ObjectHolder("gilded_skeletal_banner")
	public static final BannerBlock bannerSkeletalGilded = null;
	@GameRegistry.ObjectHolder("encrusted_skeletal_banner")
	public static final BannerBlock bannerSkeletalEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_skeletal_banner")
	public static final BannerBlock bannerSkeletalBejewelled = null;
	@GameRegistry.ObjectHolder("soul_banner")
	public static final BannerBlock bannerSoul = null;
	@GameRegistry.ObjectHolder("gilded_soul_banner")
	public static final BannerBlock bannerSoulGilded = null;
	@GameRegistry.ObjectHolder("encrusted_soul_banner")
	public static final BannerBlock bannerSoulEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_soul_banner")
	public static final BannerBlock bannerSoulBejewelled = null;
	@GameRegistry.ObjectHolder("utopian_banner")
	public static final BannerBlock bannerUtopian = null;
	@GameRegistry.ObjectHolder("gilded_utopian_banner")
	public static final BannerBlock bannerUtopianGilded = null;
	@GameRegistry.ObjectHolder("encrusted_utopian_banner")
	public static final BannerBlock bannerUtopianEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_utopian_banner")
	public static final BannerBlock bannerUtopianBejewelled = null;
	@GameRegistry.ObjectHolder("void_banner")
	public static final BannerBlock bannerVoid = null;
	@GameRegistry.ObjectHolder("gilded_void_banner")
	public static final BannerBlock bannerVoidGilded = null;
	@GameRegistry.ObjectHolder("encrusted_void_banner")
	public static final BannerBlock bannerVoidEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_void_banner")
	public static final BannerBlock bannerVoidBejewelled = null;
	@GameRegistry.ObjectHolder("vox_banner")
	public static final BannerBlock bannerVox = null;
	@GameRegistry.ObjectHolder("gilded_vox_banner")
	public static final BannerBlock bannerVoxGilded = null;
	@GameRegistry.ObjectHolder("encrusted_vox_banner")
	public static final BannerBlock bannerVoxEncrusted = null;
	@GameRegistry.ObjectHolder("bejewelled_vox_banner")
	public static final BannerBlock bannerVoxBejewelled = null;
	@GameRegistry.ObjectHolder("pluton_banner")
	public static final BannerBlock bannerPluton = null;
	@GameRegistry.ObjectHolder("luxon_banner")
	public static final BannerBlock bannerLuxon = null;
	@GameRegistry.ObjectHolder("erebon_banner")
	public static final BannerBlock bannerErebon = null;
	@GameRegistry.ObjectHolder("selyan_banner")
	public static final BannerBlock bannerSelyan = null;

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> ev) {
		AdventOfAscension.logMessage(Level.INFO, "Beginning block registration");
		IForgeRegistry<Block> registry = ev.getRegistry();

		registerBlock(registry, new StoneBlock("AbyssalStone", "abyss_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("BarathosHellstone", "barathos_hellstone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("BaronStone", "baron_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("BoreanStone", "borean_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("CreepStone", "creep_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("CrystevianStone", "crystevia_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("DeeplandsStone", "deeplands_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("DustopianStone", "dustopia_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("GardencianStone", "gardencia_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("GreckonStone", "greckon_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("HavenStone", "haven_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("IroStone", "iromine_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("LelyetianStone", "lelyetia_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("MysteriumStone", "mysterium_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("HighPrecasianStone", "high_precasia_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("LowPrecasiaStone", "low_precasia_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("PrimedStone", "primed_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("RunicStone", "runic_stone"), "blocks/generation/stone/",oreDictCobble);
		registerBlock(registry, new StoneBlock("ShyrelandsStone", "shyrelands_stone"), "blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("ToxicStone", "toxic_stone"),"blocks/generation/stone/", oreDictCobble);
		registerBlock(registry, new StoneBlock("UnstableStone", "unstable_stone"), "blocks/generation/stone/", oreDictCobble);

		registerBlock(registry, new DirtBlock("BoreanDirt", "borean_dirt"), "blocks/generation/dirt/");
		registerBlock(registry, new DirtBlock("CandylandDirt", "candyland_dirt"), "blocks/generation/dirt/");
		registerBlock(registry, new DirtBlock("CeleveDirt", "celeve_dirt"), "blocks/generation/dirt/");
		registerBlock(registry, new DirtBlock("CreeponiaDirt", "creeponia_dirt"), "blocks/generation/dirt/");
		registerBlock(registry, new DirtBlock("DustopiaDirt", "dustopia_dirt"), "blocks/generation/dirt/");
		registerBlock(registry, new DirtBlock("GardenciaDirt", "gardencia_dirt"), "blocks/generation/dirt/");
		registerBlock(registry, new DirtBlock("GreckonDirt", "greckon_dirt"), "blocks/generation/dirt/");
		registerBlock(registry, new DirtBlock("HavenDirt", "haven_dirt"), "blocks/generation/dirt/");
		registerBlock(registry, new DirtBlock("LunalyteDirt", "lunalyte_dirt"), "blocks/generation/dirt/");
		registerBlock(registry, new DirtBlock("LunasoleDirt", "lunasole_dirt"), "blocks/generation/dirt/");
		registerBlock(registry, new DirtBlock("MysteriumDirt", "mysterium_dirt"), "blocks/generation/dirt/");
		registerBlock(registry, new DirtBlock("ToxicDirt", "toxic_dirt"), "blocks/generation/dirt/");

		registerBlock(registry, new GrassBlock("AbyssGrass", "abyss_grass", getUnmappedBlock("abyss_stone"), 1.3f, 8f), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("BoreanGrass", "borean_grass", getUnmappedBlock("borean_dirt")),"blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("CandylandGrass", "candyland_grass", getUnmappedBlock("candyland_dirt")),"blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("CeleveGrass", "celeve_grass", getUnmappedBlock("celeve_dirt")), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("ChocolateGrass", "chocolate_grass", getUnmappedBlock("candyland_grass")),"blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("CreeponiaGrass", "creeponia_grass", getUnmappedBlock("creeponia_dirt")),"blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("CrystalGrass", "crystal_grass", getUnmappedBlock("candyland_dirt")),"blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("DustopiaGrass", "dustopia_grass", getUnmappedBlock("dustopia_dirt")),"blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("GardenciaGrass", "gardencia_grass", getUnmappedBlock("gardencia_dirt")),"blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("GreckonGrass", "greckon_grass", getUnmappedBlock("greckon_dirt")), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("HavenGrass", "haven_grass", getUnmappedBlock("haven_dirt")), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("IromineGrass", "iromine_grass", getUnmappedBlock("iromine_stone"), 1.3f, 8f), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("LelyetiaGrass", "lelyetia_grass", getUnmappedBlock("lelyetia_stone"), 1.3f, 8f), "blocks/generation/grass/");
		registerBlock(registry, new UpsideDownGrassBlock("LelyetiaDownGrass", "lelyetia_down_grass", getUnmappedBlock("lelyetia_stone"), 1.3f, 8f), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("LunalyteGrass", "lunalyte_grass", getUnmappedBlock("lunalyte_dirt")), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("LunasoleGrass", "lunasole_grass", getUnmappedBlock("lunasole_dirt")), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("MarshmallowGrass", "marshmallow_grass", getUnmappedBlock("candyland_dirt")), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("MysteriumGrass", "mysterium_grass", getUnmappedBlock("mysterium_dirt")), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("PrecasiaGrass", "precasia_grass", getUnmappedBlock("high_precasia_stone"), 1.3f, 8f), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("RunicGrass", "runic_grass", getUnmappedBlock("runic_stone"), 1.3f, 8f), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("ShyrelandsGrass", "shyrelands_grass", getUnmappedBlock("shyrelands_stone"), 1.3f, 8f), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("SilvroGrass", "silvro_grass", getUnmappedBlock("iromine_stone"), 1.3f, 8f), "blocks/generation/grass/");
		registerBlock(registry, new GrassBlock("ToxicGrass", "toxic_grass", getUnmappedBlock("toxic_dirt")), "blocks/generation/grass/");

		registerBlock(registry, new OreBlock("AmethystOre", "amethyst_ore", 2, 2, 8), "blocks/generation/ores/", "oreAmethyst");
		registerBlock(registry, new OreBlock("BaronyteOre", "baronyte_ore", 3), "blocks/generation/ores/", "oreBaronyte");
		registerBlock(registry, new OreBlock("BlaziumOre", "blazium_ore", 3), "blocks/generation/ores/", "oreBlazium");
		registerBlock(registry, new OreBlock("BloodstoneOre", "bloodstone_ore", 3, 6, 11), "blocks/generation/ores/", "oreBloodstone");
		registerBlock(registry, new OreBlock("BlueGemstoneOre", "blue_crystal_ore", 3, 4, 7), "blocks/generation/ores/", "oreBlueGemstone");
		registerBlock(registry, new OreBlock("ChargedRuniumOre", "charged_runium_ore", 3), "blocks/generation/ores/", "oreChargedRunium");
		registerBlock(registry, new OreBlock("ChestboneFragmentsOre", "chestbone_fragments_ore", 3, 4, 8), "blocks/generation/ores/", "oreChestboneFragments");
		registerBlock(registry, new OreBlock("CrystalliteOre", "crystallite_ore", 3, 6, 11), "blocks/generation/ores/", "oreCrystallite");
		registerBlock(registry, new OreBlock("ElecaniumOre", "elecanium_ore", 3), "blocks/generation/ores/", "oreElecanium");
		registerBlock(registry, new OreBlock("EmberstoneOre", "emberstone_ore", 3), "blocks/generation/ores/", "oreEmberstone");
		registerBlock(registry, new OreBlock("FootboneFragmentsOre", "footbone_fragments_ore", 3, 4, 8), "blocks/generation/ores/", "oreFootboneFragments");
		registerBlock(registry, new OreBlock("GemenyteOre", "gemenyte_ore", 3, 4, 8), "blocks/generation/ores/", "oreGemenyte");
		registerBlock(registry, new OreBlock("GhastlyOre", "ghastly_ore", 3), "blocks/generation/ores/", "oreGhastly");
		registerBlock(registry, new OreBlock("GhoulishOre", "ghoulish_ore", 3), "blocks/generation/ores/", "oreGhoulish");
		registerBlock(registry, new OreBlock("GreenGemstoneOre", "green_crystal_ore", 3, 4, 7), "blocks/generation/ores/", "oreGreenGemstone");
		registerBlock(registry, new OreBlock("JadeOre", "jade_ore", 3, 3, 8), "blocks/generation/ores/", "oreJade");
		registerBlock(registry, new OreBlock("JewelyteOre", "jewelyte_ore", 3, 4, 8), "blocks/generation/ores/", "oreJewelyte");
		registerBlock(registry, new OreBlock("LegboneFragmentsOre", "legbone_fragments_ore", 3, 4, 8), "blocks/generation/ores/", "oreLegboneFragments");
		registerBlock(registry, new OreBlock("LimoniteOre", "limonite_ore", 1), "blocks/generation/ores/", "oreLimonite");
		registerBlock(registry, new OreBlock("LyonOre", "lyon_ore", 3), "blocks/generation/ores/", "oreLyon");
		registerBlock(registry, new OreBlock("MystiteOre", "mystite_ore", 3), "blocks/generation/ores/", "oreMystite");
		registerBlock(registry, new OreBlock("OrnamyteOre", "ornamyte_ore", 3, 4, 8), "blocks/generation/ores/", "oreOrnamyte");
		registerBlock(registry, new OreBlock("PurpleGemstoneOre", "purple_crystal_ore", 3, 4, 7), "blocks/generation/ores/", "orePurpleGemstone");
		registerBlock(registry, new OreBlock("RedGemstoneOre", "red_crystal_ore", 3, 4, 7), "blocks/generation/ores/", "oreRedGemstone");
		registerBlock(registry, new OreBlock("RositeOre", "rosite_ore", 3), "blocks/generation/ores/", "oreRosite");
		registerBlock(registry, new OreBlock("RuniumOre", "runium_ore", 2, 1, 4), "blocks/generation/ores/", "oreRunium");
		registerBlock(registry, new OreBlock("SapphireOre", "sapphire_ore", 3, 4, 9), "blocks/generation/ores/", "oreSapphire");
		registerBlock(registry, new OreBlock("ShyregemOre", "shyregem_ore", 3, 6, 13), "blocks/generation/ores/", "oreShyregem");
		registerBlock(registry, new OreBlock("ShyrestoneOre", "shyrestone_ore", 3), "blocks/generation/ores/", "oreShyrestone");
		registerBlock(registry, new OreBlock("SkullboneFragmentsOre", "skullbone_fragments_ore", 3, 4, 8), "blocks/generation/ores/", "oreSkullboneFragments");
		registerBlock(registry, new OreBlock("VarsiumOre", "varsium_ore", 3), "blocks/generation/ores/", "oreVarsium");
		registerBlock(registry, new OreBlock("WhiteGemstoneOre", "white_crystal_ore", 3, 4, 7), "blocks/generation/ores/", "oreWhiteGemstone");
		registerBlock(registry, new OreBlock("YellowGemstoneOre", "yellow_crystal_ore", 3, 4, 7), "blocks/generation/ores/", "oreYellowGemstone");

		registerBlock(registry, new BasicDecorationBlock("BaronBricks", "baron_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("BlackBricks", "black_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("BloodstoneBricks", "bloodstone_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("BlueBricks", "blue_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("BrownBricks", "brown_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("CoralBricks", "coral_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("CreeponiaBricks", "creeponia_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("CrystalliteBricks", "crystallite_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("CrysteviaBricks", "crystevia_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("CyanBricks", "cyan_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("DarkBricks", "dark_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("DarkBlueBricks", "dark_blue_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("DarkGreyBricks", "dark_grey_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("DarkwashBricks", "darkwash_bricks", Material.ROCK, 50f, 2000f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("GardenciaBricks", "gardencia_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("GreckonBricks", "greckon_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("GreenBricks", "green_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("GreyBricks", "grey_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("HauntedBricks", "haunted_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("IroDottedBricks", "iro_dotted_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("IroStripedBricks", "iro_striped_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("LelyetiaBricks", "lelyetia_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("LimeBricks", "lime_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("LunarBricks", "lunar_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("MagentaBricks", "magenta_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("BlackMysteriumBricks", "black_mysterium_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("GreenMysteriumBricks", "green_mysterium_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("OrangeBricks", "orange_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("PinkBricks", "pink_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("PurpleBricks", "purple_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("RedBricks", "red_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("RosidianBricks", "rosidian_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("RunicConstructBricks", "runic_construct_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("WhiteShyreBricks", "white_shyre_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("YellowShyreBricks", "yellow_shyre_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("SkeletalBricks", "skeletal_bricks", Material.ROCK, 10f, 15f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("WhiteBricks", "white_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("WhitewashBricks", "whitewash_bricks", Material.ROCK, 50f, 2000f), "blocks/decoration/bricks/");
		registerBlock(registry, new BasicDecorationBlock("YellowBricks", "yellow_bricks", Material.ROCK, 2.0f, 10.0f), "blocks/decoration/bricks/");

		registerBlock(registry, new BasicDecorationBlock("IntricateAmethystIvory", "intricate_amethyst_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("NaturalAmethystIvory", "natural_amethyst_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("OrnateAmethystIvory", "ornate_amethyst_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("PatternedAmethystIvory", "patterned_amethyst_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("IntricateIvory", "intricate_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("IntricateJadeIvory", "intricate_jade_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("NaturalJadeIvory", "natural_jade_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("OrnateJadeIvory", "ornate_jade_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("PatternedJadeIvory", "patterned_jade_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("IntricateLimoniteIvory", "intricate_limonite_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("NaturalLimoniteIvory", "natural_limonite_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("OrnateLimoniteIvory", "ornate_limonite_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("PatternedLimoniteIvory", "patterned_limonite_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("NaturalIvory", "natural_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("OrnateIvory", "ornate_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("PatternedIvory", "patterned_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("IntricateRositeIvory", "intricate_rosite_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("NaturalRositeIvory", "natural_rosite_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("OrnateRositeIvory", "ornate_rosite_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("PatternedRositeIvory", "patterned_rosite_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("IntricateSapphireIvory", "intricate_sapphire_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("NaturalSapphireIvory", "natural_sapphire_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("OrnateSapphireIvory", "ornate_sapphire_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");
		registerBlock(registry, new BasicDecorationBlock("PatternedSapphireIvory", "patterned_sapphire_ivory", Material.ROCK, 5.0f, 5.0f), "blocks/decoration/ivory/");

		registerBlock(registry, new LeavesBlock("AchonyLeaves", "achony_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("BloodLeaves", "blood_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new TranslucentLeavesBlock("BubbleLeaves", "bubble_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("BlueCelevusLeaves", "blue_celevus_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("GreenCelevusLeaves", "green_celevus_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("PurpleCelevusLeaves", "purple_celevus_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("RedCelevusLeaves", "red_celevus_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("WhiteCelevusLeaves", "white_celevus_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("YellowCelevusLeaves", "yellow_celevus_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("ChurryLeaves", "churry_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("CreepLeaves", "creep_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("CycadeLeaves", "cycade_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("DawnLeaves", "dawn_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("DomiguousLeaves", "domiguous_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("EternalLeaves", "eternal_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("EucadonLeaves", "eucadon_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("HauntedLeaves", "haunted_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("HauntedEyesLeaves", "haunted_eyes_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("BlueHavenLeaves", "blue_haven_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("PinkHavenLeaves", "pink_haven_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("PurpleHavenLeaves", "purple_haven_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("RedHavenLeaves", "red_haven_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("TurquoiseHavenLeaves", "turquoise_haven_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("YellowHavenLeaves", "yellow_haven_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("IrodustLeaves", "irodust_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("IrogoldLeaves", "irogold_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("LelyetianLeaves", "lelyetian_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("LucalusLeaves", "lucalus_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("LuniciaLeaves", "lunicia_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("LunossoLeaves", "lunosso_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("MelumiaLeaves", "melumia_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("OpolloLeaves", "opollo_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new TranslucentLeavesBlock("RunicLeaves", "runic_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("ShadowLeaves", "shadow_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("ShadowbloodLeaves", "shadowblood_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("ShyreLeaves", "shyre_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("BrightShyreLeaves", "bright_shyre_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("SilvroLeaves", "silvro_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("StranglewoodLeaves", "stranglewood_leaves"), "blocks/generation/leaves/", oreDictLeaves);
		registerBlock(registry, new LeavesBlock("VeinLeaves", "vein_leaves"), "blocks/generation/leaves/", oreDictLeaves);

		registerBlock(registry, new LogBlock("AchonyLog", "achony_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("BloodLog", "blood_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("ChurryLog", "churry_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("CreepLog", "creep_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("CycadeLog", "cycade_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("DawnLog", "dawn_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("DomiguousLog", "domiguous_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("EternalLog", "eternal_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("EucadonLog", "eucadon_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("EyeballLog", "eyeball_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("HauntedLog", "haunted_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("HauntedEyeLog", "haunted_eye_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("HauntedEyesLog", "haunted_eyes_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("HauntedFlashingLog", "haunted_flashing_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("HauntedPurplingLog", "haunted_purpling_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("IroLog", "iro_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("LucalusLog", "lucalus_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("LunideLog", "lunide_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("MelumiaLog", "melumia_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("OpolloLog", "opollo_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("RunicLog", "runic_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("ShadowLog", "shadow_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("ShyreLog", "shyre_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("StranglewoodLog", "stranglewood_log"), "blocks/generation/wood/", oreDictWood);
		registerBlock(registry, new LogBlock("ToxicLog", "toxic_log"), "blocks/generation/wood/", oreDictWood);

		registerBlock(registry, new BasicDecorationBlock("AchonyPlanks", "achony_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("BloodwoodPlanks", "bloodwood_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("ChurryPlanks", "churry_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("CreepPlanks", "creep_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("CycadePlanks", "cycade_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("DawnwoodPlanks", "dawnwood_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("DomiguousPlanks", "domiguous_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("EucadonPlanks", "eucadon_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("HauntedwoodPlanks", "hauntedwood_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("IrowoodPlanks", "irowood_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("LucalusPlanks", "lucalus_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("LunidePlanks", "lunide_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("MelumiaPlanks", "melumia_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("OpolloPlanks", "opollo_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("RunicPlanks", "runic_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("ShadowPlanks", "shadow_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("ShyrePlanks", "shyre_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("StranglewoodPlanks", "stranglewood_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);
		registerBlock(registry, new BasicDecorationBlock("ToxicwoodPlanks", "toxicwood_planks", Material.WOOD), "blocks/decoration/planks/", oreDictPlanks);

		registerBlock(registry, new SlabBlock.DoubleSlabBlock("AchonySlab", "achony_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("BaronBricksSlab", "baron_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("BlackBricksSlab", "black_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("BloodstoneBricksSlab", "bloodstone_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("BloodwoodSlab", "bloodwood_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("BlueBricksSlab", "blue_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("BrownBricksSlab", "brown_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("ChurrySlab", "churry_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("CoralBricksSlab", "coral_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("CreepSlab", "creep_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("CreeponiaBricksSlab", "creeponia_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("CrystalliteBricksSlab", "crystallite_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("CrysteviaBricksSlab", "crystevia_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("CyanBricksSlab", "cyan_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("CycadeSlab", "cycade_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("DarkBlueBricksSlab", "dark_blue_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("DarkBricksSlab", "dark_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("DarkGreyBricksSlab", "dark_grey_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("DarkwashBricksSlab", "darkwash_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("DawnwoodSlab", "dawnwood_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("DomiguousSlab", "domiguous_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("EucadonSlab", "eucadon_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("GardenciaBricksSlab", "gardencia_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("GreckonBricksSlab", "greckon_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("GreenBricksSlab", "green_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("GreyBricksSlab", "grey_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("HauntedBricksSlab", "haunted_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("HauntedwoodSlab", "hauntedwood_slab", Material.WOOD), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("IroDottedBricksSlab", "iro_dotted_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("IroStripedBricksSlab", "iro_striped_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("IrowoodSlab", "irowood_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("IntricateAmethystIvorySlab", "intricate_amethyst_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("NaturalAmethystIvorySlab", "natural_amethyst_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("OrnateAmethystIvorySlab", "ornate_amethyst_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("PatternedAmethystIvorySlab", "patterned_amethyst_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("IntricateIvorySlab", "intricate_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("IntricateJadeIvorySlab", "intricate_jade_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("NaturalJadeIvorySlab", "natural_jade_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("OrnateJadeIvorySlab", "ornate_jade_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("PatternedJadeIvorySlab", "patterned_jade_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("IntricateLimoniteIvorySlab", "intricate_limonite_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("NaturalLimoniteIvorySlab", "natural_limonite_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("OrnateLimoniteIvorySlab", "ornate_limonite_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("PatternedLimoniteIvorySlab", "patterned_limonite_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("NaturalIvorySlab", "natural_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("OrnateIvorySlab", "ornate_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("PatternedIvorySlab", "patterned_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("IntricateRositeIvorySlab", "intricate_rosite_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("NaturalRositeIvorySlab", "natural_rosite_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("OrnateRositeIvorySlab", "ornate_rosite_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("PatternedRositeIvorySlab", "patterned_rosite_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("IntricateSapphireIvorySlab", "intricate_sapphire_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("NaturalSapphireIvorySlab", "natural_sapphire_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("OrnateSapphireIvorySlab", "ornate_sapphire_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("PatternedSapphireIvorySlab", "patterned_sapphire_ivory_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("LelyetiaBricksSlab", "lelyetia_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("LimeBricksSlab", "lime_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("LucalusSlab", "lucalus_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("LunarBricksSlab", "lunar_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("LunideSlab", "lunide_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("MagentaBricksSlab", "magenta_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("MelumiaSlab", "melumia_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("MysteriumBlackBricksSlab", "black_mysterium_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("MysteriumGreenBricksSlab", "green_mysterium_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("OpolloSlab", "opollo_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("OrangeBricksSlab", "orange_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("PinkBricksSlab", "pink_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("PurpleBricksSlab", "purple_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("RedBricksSlab", "red_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("RosidianBricksSlab", "rosidian_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("RunicSlab", "runic_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("RunicConstructBricksSlab", "runic_construct_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("ShadowSlab", "shadow_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("ShyreSlab", "shyre_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("ShyreWhiteBricksSlab", "white_shyre_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("ShyreYellowBricksSlab", "yellow_shyre_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("SkeletalBricksSlab", "skeletal_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("StranglewoodSlab", "stranglewood_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("ToxicwoodSlab", "toxicwood_slab", Material.WOOD), "blocks/decoration/slabs/", oreDictSlabWood);
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("WhitewashBricksSlab", "whitewash_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");
		registerBlock(registry, new SlabBlock.DoubleSlabBlock("YellowBricksSlab", "yellow_bricks_slab", Material.ROCK), "blocks/decoration/slabs/");

		registerBlock(registry, new StairsBlock("AchonyStairs", "achony_stairs", getUnmappedBlock("achony_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("BaronBricksStairs", "baron_bricks_stairs", getUnmappedBlock("baron_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("BlackBricksStairs", "black_bricks_stairs", getUnmappedBlock("black_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("BloodstoneBricksStairs", "bloodstone_bricks_stairs", getUnmappedBlock("bloodstone_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("BloodwoodStairs", "bloodwood_stairs", getUnmappedBlock("bloodwood_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("BlueBricksStairs", "blue_bricks_stairs", getUnmappedBlock("blue_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("BrownBricksStairs", "brown_bricks_stairs", getUnmappedBlock("brown_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("ChurryStairs", "churry_stairs", getUnmappedBlock("churry_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("CoralBricksStairs", "coral_bricks_stairs", getUnmappedBlock("coral_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("CreepStairs", "creep_stairs", getUnmappedBlock("creep_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("CreeponiaBricksStairs", "creeponia_bricks_stairs", getUnmappedBlock("creeponia_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("CrystalliteBricksStairs", "crystallite_bricks_stairs", getUnmappedBlock("crystallite_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("CrysteviaBricksStairs", "crystevia_bricks_stairs", getUnmappedBlock("crystevia_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("CyanBricksStairs", "cyan_bricks_stairs", getUnmappedBlock("cyan_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("CycadeStairs", "cycade_stairs", getUnmappedBlock("cycade_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("DarkBlueBricksStairs", "dark_blue_bricks_stairs", getUnmappedBlock("dark_blue_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("DarkBricksStairs", "dark_bricks_stairs", getUnmappedBlock("dark_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("DarkGreyBricksStairs", "dark_grey_bricks_stairs", getUnmappedBlock("dark_grey_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("DarkwashBricksStairs", "darkwash_bricks_stairs", getUnmappedBlock("darkwash_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("DawnwoodStairs", "dawnwood_stairs", getUnmappedBlock("dawnwood_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("DomiguousStairs", "domiguous_stairs", getUnmappedBlock("domiguous_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("EucadonStairs", "eucadon_stairs", getUnmappedBlock("eucadon_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("GardenciaBricksStairs", "gardencia_bricks_stairs", getUnmappedBlock("gardencia_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("GreckonBricksStairs", "greckon_bricks_stairs", getUnmappedBlock("greckon_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("GreenBricksStairs", "green_bricks_stairs", getUnmappedBlock("green_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("GreyBricksStairs", "grey_bricks_stairs", getUnmappedBlock("grey_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("HauntedBricksStairs", "haunted_bricks_stairs", getUnmappedBlock("haunted_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("HauntedwoodStairs", "hauntedwood_stairs", getUnmappedBlock("hauntedwood_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("IroDottedBricksStairs", "iro_dotted_bricks_stairs", getUnmappedBlock("iro_dotted_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("IroStripedBricksStairs", "iro_striped_bricks_stairs", getUnmappedBlock("iro_striped_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("IrowoodStairs", "irowood_stairs", getUnmappedBlock("irowood_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("IntricateAmethystIvoryStairs", "intricate_amethyst_ivory_stairs", getUnmappedBlock("intricate_amethyst_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("NaturalAmethystIvoryStairs", "natural_amethyst_ivory_stairs", getUnmappedBlock("natural_amethyst_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("OrnateAmethystIvoryStairs", "ornate_amethyst_ivory_stairs", getUnmappedBlock("ornate_amethyst_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("PatternedAmethystIvoryStairs", "patterned_amethyst_ivory_stairs", getUnmappedBlock("patterned_amethyst_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("IntricateIvoryStairs", "intricate_ivory_stairs", getUnmappedBlock("intricate_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("IntricateJadeIvoryStairs", "intricate_jade_ivory_stairs", getUnmappedBlock("intricate_jade_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("NaturalJadeIvoryStairs", "natural_jade_ivory_stairs", getUnmappedBlock("natural_jade_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("OrnateJadeIvoryStairs", "ornate_jade_ivory_stairs", getUnmappedBlock("ornate_jade_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("PatternedJadeIvoryStairs", "patterned_jade_ivory_stairs", getUnmappedBlock("patterned_jade_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("IntricateLimoniteIvoryStairs", "intricate_limonite_ivory_stairs", getUnmappedBlock("intricate_limonite_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("NaturalLimoniteIvoryStairs", "natural_limonite_ivory_stairs", getUnmappedBlock("natural_limonite_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("OrnateLimoniteIvoryStairs", "ornate_limonite_ivory_stairs", getUnmappedBlock("ornate_limonite_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("PatternedLimoniteIvoryStairs", "patterned_limonite_ivory_stairs", getUnmappedBlock("patterned_limonite_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("NaturalIvoryStairs", "natural_ivory_stairs", getUnmappedBlock("natural_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("OrnateIvoryStairs", "ornate_ivory_stairs", getUnmappedBlock("ornate_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("PatternedIvoryStairs", "patterned_ivory_stairs", getUnmappedBlock("patterned_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("IntricateRositeIvoryStairs", "intricate_rosite_ivory_stairs", getUnmappedBlock("intricate_rosite_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("NaturalRositeIvoryStairs", "natural_rosite_ivory_stairs", getUnmappedBlock("natural_rosite_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("OrnateRositeIvoryStairs", "ornate_rosite_ivory_stairs", getUnmappedBlock("ornate_rosite_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("PatternedRositeIvoryStairs", "patterned_rosite_ivory_stairs", getUnmappedBlock("patterned_rosite_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("IntricateSapphireIvoryStairs", "intricate_sapphire_ivory_stairs", getUnmappedBlock("intricate_sapphire_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("NaturalSapphireIvoryStairs", "natural_sapphire_ivory_stairs", getUnmappedBlock("natural_sapphire_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("OrnateSapphireIvoryStairs", "ornate_sapphire_ivory_stairs", getUnmappedBlock("ornate_sapphire_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("PatternedSapphireIvoryStairs", "patterned_sapphire_ivory_stairs", getUnmappedBlock("patterned_sapphire_ivory")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("LelyetiaBricksStairs", "lelyetia_bricks_stairs", getUnmappedBlock("lelyetia_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("LimeBricksStairs", "lime_bricks_stairs", getUnmappedBlock("lime_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("LucalusStairs", "lucalus_stairs", getUnmappedBlock("lucalus_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("LunarBricksStairs", "lunar_bricks_stairs", getUnmappedBlock("lunar_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("LunideStairs", "lunide_stairs", getUnmappedBlock("lunide_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("MagentaBricksStairs", "magenta_bricks_stairs", getUnmappedBlock("magenta_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("MelumiaStairs", "melumia_stairs", getUnmappedBlock("melumia_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("MysteriumBlackBricksStairs", "black_mysterium_bricks_stairs", getUnmappedBlock("black_mysterium_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("MysteriumGreenBricksStairs", "green_mysterium_bricks_stairs", getUnmappedBlock("green_mysterium_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("OpolloStairs", "opollo_stairs", getUnmappedBlock("opollo_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("OrangeBricksStairs", "orange_bricks_stairs", getUnmappedBlock("orange_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("PinkBricksStairs", "pink_bricks_stairs", getUnmappedBlock("pink_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("PurpleBricksStairs", "purple_bricks_stairs", getUnmappedBlock("purple_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("RedBricksStairs", "red_bricks_stairs", getUnmappedBlock("red_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("RosidianBricksStairs", "rosidian_bricks_stairs", getUnmappedBlock("rosidian_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("RunicStairs", "runic_stairs", getUnmappedBlock("runic_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("RunicConstructBricksStairs", "runic_construct_bricks_stairs", getUnmappedBlock("runic_construct_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("ShadowStairs", "shadow_stairs", getUnmappedBlock("shadow_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("ShyreStairs", "shyre_stairs", getUnmappedBlock("shyre_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("ShyreWhiteBricksStairs", "white_shyre_bricks_stairs", getUnmappedBlock("white_shyre_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("ShyreYellowBricksStairs", "yellow_shyre_bricks_stairs", getUnmappedBlock("yellow_shyre_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("SkeletalBricksStairs", "skeletal_bricks_stairs", getUnmappedBlock("skeletal_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("StranglewoodStairs", "stranglewood_stairs", getUnmappedBlock("stranglewood_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("ToxicwoodStairs", "toxicwood_stairs", getUnmappedBlock("toxicwood_planks")), "blocks/decoration/stairs/", oreDictStairsWood);
		registerBlock(registry, new StairsBlock("WhitewashBricksStairs", "whitewash_bricks_stairs", getUnmappedBlock("whitewash_bricks")), "blocks/decoration/stairs/");
		registerBlock(registry, new StairsBlock("YellowBricksStairs", "yellow_bricks_stairs", getUnmappedBlock("yellow_bricks")), "blocks/decoration/stairs/");

		registerBlock(registry, new FenceBlock("AchonyFence", "achony_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("BloodwoodFence", "bloodwood_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("ChurryFence", "churry_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("CreepFence", "creep_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("CycadeFence", "cycade_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("DawnwoodFence", "dawnwood_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("DomiguousFence", "domiguous_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("EucadonFence", "eucadon_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("HauntedwoodFence", "hauntedwood_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("IrowoodFence", "irowood_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("LucalusFence", "lucalus_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("LunideFence", "lunide_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("MelumiaFence", "melumia_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("OpolloFence", "opollo_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("RunicFence", "runic_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("ShadowFence", "shadow_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("ShyreFence", "shyre_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("StranglewoodFence", "stranglewood_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new FenceBlock("ToxicwoodFence", "toxicwood_fence"), "blocks/decoration/fences/", oreDictFenceWood);
		registerBlock(registry, new TwinklestoneFence(), "blocks/decoration/fences/");

		registerBlock(registry, new GateBlock("AchonyGate", "achony_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("BloodwoodGate", "bloodwood_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("ChurryGate", "churry_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("CreepGate", "creep_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("CycadeGate", "cycade_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("DawnwoodGate", "dawnwood_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("DomiguousGate", "domiguous_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("EucadonGate", "eucadon_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("HauntedwoodGate", "hauntedwood_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("IrowoodGate", "irowood_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("LucalusGate", "lucalus_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("LunideGate", "lunide_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("MelumiaGate", "melumia_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("OpolloGate", "opollo_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("RunicGate", "runic_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("ShadowGate", "shadow_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("ShyreGate", "shyre_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("StranglewoodGate", "stranglewood_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);
		registerBlock(registry, new GateBlock("ToxicwoodGate", "toxicwood_gate"), "blocks/decoration/gates/", oreDictFenceGateWood);

		registerBlock(registry, new BasicBlock("FlowerCore", "flower_core", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("AquaMushroomInside", "aqua_mushroom_inside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("AquaMushroomOutside", "aqua_mushroom_outside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("BlackMushroom", "black_mushroom", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("BlueMushroomInside", "blue_mushroom_inside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("BlueMushroomOutside", "blue_mushroom_outside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("GreenMushroomInside", "green_mushroom_inside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("GreenMushroomOutside", "green_mushroom_outside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("OrangeMushroomInside", "orange_mushroom_inside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("OrangeMushroomOutside", "orange_mushroom_outside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("PeachMushroomInside", "peach_mushroom_inside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("PeachMushroomOutside", "peach_mushroom_outside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("PurpleMushroomInside", "purple_mushroom_inside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("PurpleMushroomOutside", "purple_mushroom_outside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("BlackMushroomStem", "black_mushroom_stem", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("BlueMushroomStem", "blue_mushroom_stem", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("GreenMushroomStem", "green_mushroom_stem", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("OrangeMushroomStem", "orange_mushroom_stem", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("PurpleMushroomStem", "purple_mushroom_stem", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("YellowMushroomStem", "yellow_mushroom_stem", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("YellowMushroomInside", "yellow_mushroom_inside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("YellowMushroomOutside", "yellow_mushroom_outside", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("PlantStem", "plant_stem", Material.GOURD), "blocks/generation/misc/");

		registerItemlessBlock(registry, new CandiedWaterBlock());

		registerBlock(registry, new UnbreakableLightBlock("AncientLight", "ancient_light", Material.GLASS, 1.0f), "blocks/functional/lights/");
		registerBlock(registry, new UnbreakableLightBlock("ArchaicLight", "archaic_light", Material.GLASS, 0.8f), "blocks/functional/lights/");
		registerBlock(registry, new LightBlock("ArchaicLightBreakable", "archaic_light_breakable", Material.GLASS, 0.8f, 1.2f, 0.5f), "blocks/functional/lights/");
		registerBlock(registry, new LightBlock("CreepCrystal", "creep_crystal", Material.GLASS, 0.8f, 1.2f, 0.5f), "blocks/functional/lights/");
		registerBlock(registry, new LightBlock("Darkstone", "darkstone", Material.GLASS, 0.8f, 1.2f, 0.5f), "blocks/functional/lights/");
		registerBlock(registry, new LightBlock("DeepCrystal", "deep_crystal", Material.GLASS, 0.8f, 1.2f, 0.5f), "blocks/functional/lights/");
		registerBlock(registry, new LightBlock("HiveLight", "hive_light", Material.GLASS, 0.8f, 1.2f, 0.5f), "blocks/functional/lights/");
		registerBlock(registry, new LightBlock("SteelLight", "steel_light", Material.IRON, 0.1f, 5.0f, 7.5f), "blocks/functional/lights/");
		registerBlock(registry, new LightBlock("Twinklestone", "twinklestone", Material.GLASS, 0.8f, 1.2f, 0.5f), "blocks/functional/lights/");
		registerBlock(registry, new VoxLight(), "blocks/functional/lights/");

		registerBlock(registry, new LampBlock("AmethystLamp", "amethyst_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("AquaticLamp", "aquatic_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("BaronyteLamp", "baronyte_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("BlaziumLamp", "blazium_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("BloodstoneLamp", "bloodstone_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("CrystalliteLamp", "crystallite_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("ElecaniumLamp", "elecanium_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("EmberstoneLamp", "emberstone_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("FireLamp", "fire_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("GhastlyLamp", "ghastly_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("GhoulishLamp", "ghoulish_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("IroLamp", "iro_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("IvoryLamp", "ivory_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("IvoryAmethystLamp", "ivory_amethyst_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("IvoryJadeLamp", "ivory_jade_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("IvoryLimoniteLamp", "ivory_limonite_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("IvoryRositeLamp", "ivory_rosite_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("IvorySapphireLamp", "ivory_sapphire_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("JadeLamp", "jade_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("AquaLifeLamp", "aqua_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("BlackLifeLamp", "black_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("BlueLifeLamp", "blue_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("BrownLifeLamp", "brown_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("CyanLifeLamp", "cyan_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("DarkGreyLifeLamp", "dark_grey_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("GreenLifeLamp", "green_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("GreyLifeLamp", "grey_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("LimeLifeLamp", "lime_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("MagentaLifeLamp", "magenta_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("OrangeLifeLamp", "orange_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("PinkLifeLamp", "pink_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("PurpleLifeLamp", "purple_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("RedLifeLamp", "red_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("WhiteLifeLamp", "white_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LifeLampBlock("YellowLifeLamp", "yellow_life_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("LimoniteLamp", "limonite_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("LunarLamp", "lunar_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("LyonLamp", "lyon_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("MysticLamp", "mystic_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("NeonLamp", "neon_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("NeonCirclingLamp", "neon_circling_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("NeonLapisLamp", "neon_lapis_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("NeonLapisCirclingLamp", "neon_lapis_circling_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("NeonLapisTrianglesLamp", "neon_lapis_triangles_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("NeonRunicLamp", "neon_runic_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("NeonTrianglesLamp", "neon_triangles_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("RositeLamp", "rosite_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("SapphireLamp", "sapphire_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");
		registerBlock(registry, new LampBlock("SkeletalLamp", "skeletal_lamp", Material.REDSTONE_LIGHT, 1.0f, 1.5f, 1.0f), "blocks/functional/lamps/");

		registerBlock(registry, new GlassBlock("AbyssalGlass", "abyssal_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new UnbreakableGlassBlock("AncientGlass", "ancient_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new GlassBlock("AquaticGlass", "aquatic_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new UnbreakableGlassBlock("ArchaicGlass", "archaic_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new GlassBlock("ArchaicGlassBreakable", "archaic_glass_breakable"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new GlassBlock("BaronGlass", "baron_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new UnbreakableGlassBlock("DecayedGlass", "decayed_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new GlassBlock("GardencianGlass", "gardencian_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new GlassBlock("HavenGlass", "haven_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new GlassBlock("IroGlass", "iro_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new GlassBlock("LelyetianGlass", "lelyetian_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new UnbreakableGlassBlock("LunarGlass", "lunar_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new GlassBlock("RunicGlass", "runic_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new UnbreakableGlassBlock("UnbreakableRunicGlass", "unbreakable_runic_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new GlassBlock("ShyreGlass", "shyre_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new GlassBlock("VoxGlass", "vox_glass"), "blocks/decoration/glass/", oreDictGlass);
		registerBlock(registry, new GlassBlock("ZhinxGlass", "zhinx_glass"), "blocks/decoration/glass/", oreDictGlass);

		registerBlock(registry, new SandBlock("PrecasianSand", "precasian_sand"), "blocks/generation/sand/", oreDictSand);

		registerBlock(registry, new CompressedBlock("AmethystBlock", "amethyst_block"), "blocks/decoration/compressedblock/", "blockAmethyst");
		registerBlock(registry, new CompressedBlock("BaronyteBlock", "baronyte_block"), "blocks/decoration/compressedblock/", "blockBaronyte");
		registerBlock(registry, new CompressedBlock("BlaziumBlock", "blazium_block"), "blocks/decoration/compressedblock/", "blockBlazium");
		registerBlock(registry, new CompressedBlock("BloodstoneBlock", "bloodstone_block"), "blocks/decoration/compressedblock/", "blockBloodstone");
		registerBlock(registry, new CompressedBlock("CrystalliteBlock", "crystallite_block"), "blocks/decoration/compressedblock/", "blockCrystallite");
		registerBlock(registry, new CompressedBlock("ElecaniumBlock", "elecanium_block"), "blocks/decoration/compressedblock/", "blockElecanium");
		registerBlock(registry, new CompressedBlock("EmberstoneBlock", "emberstone_block"), "blocks/decoration/compressedblock/", "blockEmberstone");
		registerBlock(registry, new CompressedBlock("GemenyteBlock", "gemenyte_block"), "blocks/decoration/compressedblock/", "blockGemenyte");
		registerBlock(registry, new CompressedBlock("GhastlyBlock", "ghastly_block"), "blocks/decoration/compressedblock/", "blockGhastly");
		registerBlock(registry, new CompressedBlock("GhoulishBlock", "ghoulish_block"), "blocks/decoration/compressedblock/", "blockGhoulish");
		registerBlock(registry, new CompressedBlock("JadeBlock", "jade_block"), "blocks/decoration/compressedblock/", "blockJade");
		registerBlock(registry, new CompressedBlock("JewelyteBlock", "jewelyte_block"), "blocks/decoration/compressedblock/", "blockJewelyte");
		registerBlock(registry, new CompressedBlock("LimoniteBlock", "limonite_block", true), "blocks/decoration/compressedblock/", "blockLimonite");
		registerBlock(registry, new CompressedBlock("LunarBlock", "lunar_block"), "blocks/decoration/compressedblock/", "blockLunar");
		registerBlock(registry, new CompressedBlock("LyonBlock", "lyon_block"), "blocks/decoration/compressedblock/", "blockLyon");
		registerBlock(registry, new CompressedBlock("MystiteBlock", "mystite_block"), "blocks/decoration/compressedblock/", "blockMystite");
		registerBlock(registry, new CompressedBlock("OrnamyteBlock", "ornamyte_block"), "blocks/decoration/compressedblock/", "blockOrnamyte");
		registerBlock(registry, new CompressedBlock("RositeBlock", "rosite_block"), "blocks/decoration/compressedblock/", "blockRosite");
		registerBlock(registry, new CompressedBlock("SapphireBlock", "sapphire_block"), "blocks/decoration/compressedblock/", "blocksaphire");
		registerBlock(registry, new CompressedBlock("ShyregemBlock", "shyregem_block"), "blocks/decoration/compressedblock/", "blockShyregem");
		registerBlock(registry, new CompressedBlock("ShyrestoneBlock", "shyrestone_block"), "blocks/decoration/compressedblock/", "blockShyrestone");
		registerBlock(registry, new CompressedBlock("SkeletalIngotBlock", "skeletal_ingot_block"), "blocks/decoration/compressedblock/", "blockSkeletal");
		registerBlock(registry, new CompressedBlock("VarsiumBlock", "varsium_block"), "blocks/decoration/compressedblock/", "blockVarsium");

		registerBlock(registry, new CarpetBlock("BaronCarpet", "baron_carpet"), "blocks/decoration/carpets/");
		registerBlock(registry, new CarpetBlock("BoreanCarpet", "borean_carpet"), "blocks/decoration/carpets/");
		registerBlock(registry, new CarpetBlock("GardencianCarpet", "gardencian_carpet"), "blocks/decoration/carpets/");
		registerBlock(registry, new CarpetBlock("IroCarpet", "iro_carpet"), "blocks/decoration/carpets/");
		registerBlock(registry, new CarpetBlock("LunarCarpet", "lunar_carpet"), "blocks/decoration/carpets/");

		registerBlock(registry, new Crystallanium(), "blocks/functional/misc/");
		registerBlock(registry, new Emberium(), "blocks/functional/misc/");
		registerBlock(registry, new Shadonantium(), "blocks/functional/misc/");
		registerBlock(registry, new Skeletanium(), "blocks/functional/misc/");

		registerBlock(registry, new BasicBlock("AncientRock", "ancient_rock", Material.ROCK), "blocks/generation/misc/", oreDictStone);
		registerBlock(registry, new UnbreakableBlock("BlackAncientTile", "black_ancient_tile", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("AncientTileCore", "ancient_tile_core", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("GreenAncientTile", "green_ancient_tile", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("AncientTileShrine", "ancient_tile_shrine", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("WhiteAncientTile", "white_ancient_tile", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("ArchaicDirt", "archaic_dirt", Material.GROUND), "blocks/generation/misc/");
		registerBlock(registry, new BasicDecorationBlock("ArchaicDirtBreakable", "archaic_dirt_breakable", Material.GROUND, 0.5f, 0.0f), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("ArchaicHorizontalStream", "archaic_stream_horizontal", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicDecorationBlock("ArchaicHorizontalStreamBreakable", "archaic_stream_horizontal_breakable", Material.ROCK, 2.0f, 10.0f), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("ArchaicRectangles", "archaic_rectangles", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicDecorationBlock("ArchaicRectanglesBreakable", "archaic_rectangles_breakable", Material.ROCK, 2.0f, 10.0f), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("ArchaicSquares", "archaic_squares", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicDecorationBlock("ArchaicSquaresBreakable", "archaic_squares_breakable", Material.ROCK, 2.0f, 10.0f), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("ArchaicTiles", "archaic_tiles", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicDecorationBlock("ArchaicTilesBreakable", "archaic_tiles_breakable", Material.ROCK, 2.0f, 10.0f), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("ArchaicVerticalStream", "archaic_stream_vertical", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicDecorationBlock("ArchaicVerticalStreamBreakable", "archaic_stream_vertical_breakable", Material.ROCK, 2.0f, 10.0f), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("BaronCastleWall", "baron_castle_wall", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("BaronCube", "baron_cube", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("BaronGround", "baron_ground", Material.CLAY), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("BloodstoneBarBricks", "bloodstone_bar_bricks", Material.IRON, 7.5f, 7f), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("BloodstoneBars", "bloodstone_bars", Material.IRON, 7.5f, 7f), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("GreenCandy", "green_candy", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("RedCandy", "red_candy", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("WhiteCandy", "white_candy", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("ChocolateBlock", "chocolate_block", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("DarkChocolateBlock", "dark_chocolate_block", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("WhiteChocolateBlock", "white_chocolate_block", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("CogBlock", "cog_block", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("BlueCoral", "blue_coral", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("GreenCoral", "green_coral", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("HardCoral", "hard_coral", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("OrangeCoral", "orange_coral", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("PinkCoral", "pink_coral", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("WhiteCoral", "white_coral", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("YellowCoral", "yellow_coral", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("AquaCottonCandy", "aqua_cotton_candy", Material.WEB), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("PinkCottonCandy", "pink_cotton_candy", Material.WEB), "blocks/generation/misc/");
		registerBlock(registry, new BasicDecorationBlock("Crate", "crate", Material.WOOD), "blocks/decoration/misc/");
		registerBlock(registry, new TranslucentBlock("BlueCrystal", "blue_crystal_block", Material.GLASS, 1.0f, 0f, 0), "blocks/generation/misc/");
		registerBlock(registry, new TranslucentBlock("GreenCrystal", "green_crystal_block", Material.GLASS, 1.0f, 0f, 0), "blocks/generation/misc/");
		registerBlock(registry, new TranslucentBlock("PurpleCrystal", "purple_crystal_block", Material.GLASS, 1.0f, 0f, 0), "blocks/generation/misc/");
		registerBlock(registry, new TranslucentBlock("RedCrystal", "red_crystal_block", Material.GLASS, 1.0f, 0f, 0), "blocks/generation/misc/");
		registerBlock(registry, new TranslucentBlock("WhiteCrystal", "white_crystal_block", Material.GLASS, 1.0f, 0f, 0), "blocks/generation/misc/");
		registerBlock(registry, new TranslucentBlock("YellowCrystal", "yellow_crystal_block", Material.GLASS, 1.0f, 0f, 0), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("DarkFaceBrick", "dark_face_brick", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new DeeplandsTrapExplosion(), "blocks/generation/misc/");
		registerBlock(registry, new DeeplandsTrapLava(), "blocks/generation/misc/");
		registerBlock(registry, new DeeplandsTrapNipper(), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("Deepshine", "deepshine", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("DegradedSteel", "degraded_steel", Material.IRON, 5.0f, 10.0f), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("EyeBlock", "eye_block", Material.GOURD, 4.0f, 1.5f), "blocks/generation/misc/");
		registerBlock(registry, new GiantSnailAcid(), "blocks/functional/misc/");
		registerBlock(registry, new BasicBlock("Gingerbread", "gingerbread", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("HauntedOrb", "haunted_orb", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("HiveWall", "hive_wall", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicDecorationBlock("IroBox", "iro_box", Material.IRON), "blocks/decoration/misc/");

		registerBlock(registry, new IroBrickTrap(), "blocks/generation/misc/");
		registerBlock(registry, new BasicNonCubeBlock("Iropole", "iropole", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("KaiyuTempleBlockFace", "kaiyu_temple_block_face", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("KaiyuTempleBlockFlow", "kaiyu_temple_block_flow", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("KaiyuTempleBlockMaze", "kaiyu_temple_block_maze", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("KaiyuTempleBlockPass", "kaiyu_temple_block_pass", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("KaiyuTempleBlockPlain", "kaiyu_temple_block_plain", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("KaiyuTempleBlockSquares", "kaiyu_temple_block_squares", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("KaiyuTempleBlockTrack", "kaiyu_temple_block_track", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new KaiyuTempleTrapWither(), "blocks/generation/misc/");
		registerBlock(registry, new KaiyuTempleTrapDamage(), "blocks/generation/misc/");
		registerBlock(registry, new KaiyuTempleTrapPoison(), "blocks/generation/misc/");
		registerBlock(registry,	new KaiyuTempleTrapFire(), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("Licorice", "licorice", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new LunarOrbBlock("DarklightOrb", "darklight_orb"), "blocks/generation/misc/");
		registerBlock(registry, new LunarOrbBlock("DuskOrb", "dusk_orb"), "blocks/generation/misc/");
		registerBlock(registry, new LunarOrbBlock("LunarOrb", "lunar_orb"), "blocks/generation/misc/");
		registerBlock(registry, new LunarOrbBlock("MoonlightOrb", "moonlight_orb"), "blocks/generation/misc/");
		registerBlock(registry, new LunarOrbBlock("SunfireOrb", "sunfire_orb"), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("LunarPad", "lunar_pad", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("Marshmallow", "marshmallow", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new AcidBlock("OrangeAcid", "orange_acid"), "blocks/functional/misc/");
		registerBlock(registry, new BasicBlock("ParaviteHive", "paravite_hive", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("BlackPetals", "black_petals", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("BluePetals", "blue_petals", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("LightBluePetals", "light_blue_petals", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("MagentaPetals", "magenta_petals", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("OrangePetals", "orange_petals", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("PurplePetals", "purple_petals", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("RedPetals", "red_petals", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("RosePetals", "rose_petals", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("YellowPetals", "yellow_petals", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("Plastic", "plastic", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("BlueRockCandy", "blue_rock_candy", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("GreenRockCandy", "green_rock_candy", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("PinkRockCandy", "pink_rock_candy", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("PurpleRockCandy", "purple_rock_candy", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostCompass", "rune_post_compass",63, 45), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostDistortion", "rune_post_distortion", 65, 50), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostEnergy", "rune_post_energy", 36, 22), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostFire", "rune_post_fire", 8, 7), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostKinetic", "rune_post_kinetic", 54, 32), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostLife", "rune_post_life", 72, 56), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostPoison", "rune_post_poison", 22, 14), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostLunar", "rune_post_lunar", 32, 20), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostPower", "rune_post_power", 59, 40), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostStorm", "rune_post_storm", 49, 30), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostStrike", "rune_post_strike", 42, 24), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostWater", "rune_post_water", 15, 10), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostWind", "rune_post_wind", 0, 4), "blocks/generation/misc/");
		registerBlock(registry, new RunePostBlock("RunePostWither", "rune_post_wither", 30, 15), "blocks/generation/misc/");
		registerBlock(registry, new BasicDecorationBlock("SilvroBox", "silvro_box", Material.IRON), "blocks/decoration/misc/");
		registerBlock(registry, new BasicBlock("SkeletalBlock", "skeletal_block", Material.ROCK, 5.0f, 5.0f), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("SkullBlock", "skull_block", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("DarkSkullBlock", "dark_skull_block", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("Tentacles", "tentacles", Material.GOURD, 3.0f, 1.0f), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("TentaclesDotsLeft", "tentacles_dots_left", Material.GOURD, 3.0f, 1.0f), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("TentaclesDotsRight", "tentacles_dots_right", Material.GOURD, 3.0f, 1.0f), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("TentaclesEyeOrange", "tentacles_eye_orange", Material.GOURD, 3.0f, 1.0f), "blocks/generation/misc/");
		registerBlock(registry, new TentaclesEyeRed(), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("TentaclesGreen", "tentacles_green", Material.GOURD, 3.0f, 1.0f), "blocks/generation/misc/");
		registerBlock(registry, new BasicBlock("ToxicStem", "toxic_stem", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("UnbreakableIroBricks", "unbreakable_iro_bricks", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("UnbreakablePlantStem", "unbreakable_plant_stem", Material.GOURD), "blocks/generation/misc/");
		registerBlock(registry, new UnbreakableBlock("UnbreakableRunicBricks", "unbreakable_runic_bricks", Material.ROCK), "blocks/generation/misc/");
		registerBlock(registry, new BasicNonCubeBlock("BlueShroom", "blue_shroom", Material.GOURD, 2.0f, 0.5f), "blocks/generation/misc/");
		registerBlock(registry, new BasicNonCubeBlock("GreenShroom", "green_shroom", Material.GOURD, 2.0f, 0.5f), "blocks/generation/misc/");
		registerBlock(registry, new BasicNonCubeBlock("OrangeShroom", "orange_shroom", Material.GOURD, 2.0f, 0.5f), "blocks/generation/misc/");
		registerBlock(registry, new BasicNonCubeBlock("PurpleShroom", "purple_shroom", Material.GOURD, 2.0f, 0.5f), "blocks/generation/misc/");
		registerBlock(registry, new BasicNonCubeBlock("ShroomStem", "shroom_stem", Material.GOURD, 2.0f, 0.5f).setBoundingBox(BasicNonCubeBlock.shroomStemAABB), "blocks/generation/misc/");
		registerBlock(registry, new BasicNonCubeBlock("VoxShroom", "vox_shroom", Material.GOURD, 2.0f, 0.5f), "blocks/generation/misc/");
		registerBlock(registry, new BasicNonCubeBlock("YellowShroom", "yellow_shroom", Material.GOURD, 2.0f, 0.5f), "blocks/generation/misc/");
		registerBlock(registry, new DirectionalBlock("VoxLog", "vox_log", Material.WOOD, 1.2f, 0.5f), "blocks/generation/misc/");
		registerBlock(registry, new DirectionalBlock("VoxLog2", "vox_log2", Material.WOOD, 1.2f, 0.5f), "blocks/generation/misc/");
		registerBlock(registry, new BoneyBlock(), "blocks/generation/misc/");
		registerBlock(registry, new CactusBlock("AncientCactus", "ancient_cactus"), "blocks/generation/plants/");
		registerBlock(registry, new CarvedRunicPortalBlock("CarvedRuneOfDirection", "carved_rune_direction"), "blocks/generation/misc/");
		registerBlock(registry, new CarvedRunicBlock("CarvedRuneOfEmpowering", "carved_rune_empowering"), "blocks/generation/misc/");
		registerBlock(registry, new CarvedRunicBlock("CarvedRuneOfFocus", "carved_rune_focus"), "blocks/generation/misc/");
		registerBlock(registry, new CarvedRunicPortalBlock("CarvedRuneOfPower", "carved_rune_power"), "blocks/generation/misc/");
		registerBlock(registry, new CarvedRunicPortalBlock("CarvedRuneOfReality", "carved_rune_reality"), "blocks/generation/misc/");
		registerBlock(registry, new CarvedRunicPortalBlock("CarvedRuneOfSpace", "carved_rune_space"), "blocks/generation/misc/");
		registerBlock(registry, new CarvedRunicPortalBlock("CarvedRuneOfTravel", "carved_rune_travel"), "blocks/generation/misc/");
		registerBlock(registry, new ChargingTable(), "blocks/functional/utility/");
		registerBlock(registry, new CloudBlock("ShyreCloud", "shyre_cloud", Material.AIR), "blocks/generation/misc/");
		registerItemlessBlock(registry, new DimensionalFabric());
		registerBlock(registry, new DustopianLamp(), "blocks/generation/special/");
		registerBlock(registry, new DustopianLampOff(), "blocks/generation/special/");
		registerBlock(registry, new EnhancerBlock("DamageEnhancer", "damage_enhancer"), "blocks/functional/misc/");
		registerBlock(registry, new EnhancerBlock("DivineEnhancer", "divine_enhancer"), "blocks/functional/misc/");
		registerBlock(registry, new EnhancerBlock("DurabilityEnhancer", "durability_enhancer"), "blocks/functional/misc/");
		registerBlock(registry, new EnhancerBlock("LuckEnhancer", "luck_enhancer"), "blocks/functional/misc/");
		registerBlock(registry, new EnhancerBlock("MagicEnhancer", "magic_enhancer"), "blocks/functional/misc/");
		registerBlock(registry, new EnhancerBlock("ResistanceEnhancer", "resistance_enhancer"), "blocks/functional/misc/");
		registerBlock(registry, new EnhancerBlock("SpeedEnhancer", "speed_enhancer"), "blocks/functional/misc/");
		registerBlock(registry, new EnhancerBlock("WeightEnhancer", "weight_enhancer"), "blocks/functional/misc/");
		registerBlock(registry, new LadderBlock("ArchaicLadder", "archaic_ladder", -1f, 999999999f), "blocks/functional/misc/");
		registerBlock(registry, new LadderBlock("ArchaicLadderBreakable", "archaic_ladder_breakable", 0.5f, 0.0f), "blocks/functional/misc/");
		registerItemlessBlock(registry, new LivingGrowth());
		registerBlock(registry, new LogBlock("CeleveStem", "celeve_stem"), "blocks/generation/misc/");
		registerBlock(registry, new LunarPillar(), "blocks/generation/misc/");
		registerBlock(registry, new SpikeyPillar(), "blocks/generation/misc/");
		registerBlock(registry, new ToxicBlock(), "blocks/generation/misc/");
		registerBlock(registry, new ToxicWaste(), "blocks/generation/misc/");

		registerBlock(registry, new SpawnerBlock("AmphibiorSpawner", "amphibior_spawner", "amphibior"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("AmphibiyteSpawner", "amphibiyte_spawner", "amphibiyte"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("AngelicaSpawner", "angelica_spawner", "angelica"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("ArcWizardSpawner", "arc_wizard_spawner", "arc_wizard"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("ArkzyneSpawner", "arkzyne_spawner", "arkzyne"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("ArocknidSpawner", "arocknid_spawner", "arocknid"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("BansheeSpawner", "banshee_spawner", "banshee"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("BaumbaSpawner", "baumba_spawner", "baumba"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("BloodsuckerSpawner", "bloodsucker_spawner", "bloodsucker"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("CaneBugSpawner", "cane_bug_spawner", "cane_bug"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("CrusiliskSpawner", "crusilisk_spawner", "crusilisk"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("DawnlightSpawner", "dawnlight_spawner", "dawnlight"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("DayseeSpawner", "daysee_spawner", "daysee"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("DiocusSpawner", "diocus_spawner", "diocus"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("EnforcerSpawner", "enforcer_spawner", "enforcer"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("ExoheadSpawner", "exohead_spawner", "exohead"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("FacelessFloaterSpawner", "faceless_floater_spawner", "faceless_floater"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("FenixSpawner", "fenix_spawner", "fenix", true), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("FleshEaterSpawner", "flesh_eater_spawner", "flesh_eater"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("FlowerfaceSpawner", "flowerface_spawner", "flowerface"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("FungockSpawner", "fungock_spawner", "fungock"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("GhastusSpawner", "ghastus_spawner", "ghastus", true), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("GingerbirdSpawner", "gingerbird_spawner", "gingerbird"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("GingerbreadManSpawner", "gingerbread_man_spawner", "gingerbread_man"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("GoldumSpawner", "goldum_spawner", "goldum", true), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("GoldusSpawner", "goldus_spawner", "goldus", true), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("InmateXSpawner", "inmate_x_spawner", "inmate_x"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("InmateYSpawner", "inmate_y_spawner", "inmate_y"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("IosaurSpawner", "iosaur_spawner", "iosaur"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("JaweSpawner", "jawe_spawner", "jawe"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("KaiyuSpawner", "kaiyu_spawner", "kaiyu"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("LightwalkerSpawner", "lightwalker_spawner", "lightwalker"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("LuxocronSpawner", "luxocron_spawner", "luxocron"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("MechyonSpawner", "mechyon_spawner", "mechyon"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("MerkyreSpawner", "merkyre_spawner", "merkyre"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("MermageSpawner", "mermage_spawner", "mermage"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("MushroomSpiderSpawner", "mushroom_spider_spawner", "mushroom_spider"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("NethengeicBeastSpawner", "nethengeic_beast_spawner", "nethengeic_beast"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("NightmareSpiderSpawner", "nightmare_spider_spawner", "nightmare_spider"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("NightwingSpawner", "nightwing_spawner", "nightwing"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("OpteryxSpawner", "opteryx_spawner", "opteryx"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("ParaviteSpawner", "paravite_spawner", "paravite"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("PhantomSpawner", "phantom_spawner", "phantom"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("PodPlantSpawner", "pod_plant_spawner", "pod_plant"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("RawboneSpawner", "rawbone_spawner", "rawbone"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("ReaverSpawner", "reaver_spawner", "reaver", true), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("RefluctSpawner", "refluct_spawner", "refluct"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("RockCritterSpawner", "rock_critter_spawner", "rock_critter"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("RunicGolemSpawner", "runic_golem_spawner", "runic_golem"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("RunicGuardianSpawner", "runic_guardian_spawner", "runic_guardian"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("SeekerSpawner", "seeker_spawner", "seeker"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("ShavoSpawner", "shavo_spawner", "shavo", true), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("ShyreTrollSpawner", "shyre_troll_spawner", "shyre_troll"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("SkeledonSpawner", "skeledon_spawner", "skeledon", true), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("SkelekyteSpawner", "skelekyte_spawner", "skelekyte", true), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("SoulscorneSpawner", "soulscorne_spawner", "soulscorne"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("SpectralWizardSpawner", "spectral_wizard_spawner", "spectral_wizard"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("SpinoledonSpawner", "spinoledon_spawner", "spinoledon"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("SurveyorSpawner", "surveyor_spawner", "surveyor"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("TharaflySpawner", "tharafly_spawner", "tharafly"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("UndeadTrollSpawner", "undead_troll_spawner", "undead_troll"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("UriohSpawner", "urioh_spawner", "urioh", true), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("UrvSpawner", "urv_spawner", "urv", true), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("VineWizardSpawner", "vine_wizard_spawner", "vine_wizard"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("VisageSpawner", "visage_spawner", "visage", true), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("VolarSpawner", "volar_spawner", "volar"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("ZargSpawner", "zarg_spawner", "zarg"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("ZhinxSpawner", "zhinx_spawner", "zhinx"), "blocks/functional/spawners/");
		registerBlock(registry, new SpawnerBlock("ZorpSpawner", "zorp_spawner", "zorp"), "blocks/functional/spawners/");

		registerBlock(registry, new ArmyBlock(), "blocks/functional/boss/");
		registerBlock(registry, new BaronessAltar(), "blocks/functional/boss/");
		registerBlock(registry, new CandyBlock(), "blocks/functional/boss/");
		registerBlock(registry, new ClunkheadAltar(), "blocks/functional/boss/");
		registerBlock(registry, new CraexxeusAltar(), "blocks/functional/boss/");
		registerBlock(registry, new CreepAltar(), "blocks/functional/boss/");
		registerBlock(registry, new DracyonAltar(), "blocks/functional/boss/");
		registerBlock(registry, new GrawAltar(), "blocks/functional/boss/");
		registerBlock(registry, new GuardianAltar(), "blocks/functional/boss/");
		registerBlock(registry, new HiveSpawner(), "blocks/functional/boss/");
		registerBlock(registry, new HydroTable(), "blocks/functional/boss/");
		registerBlock(registry, new IllusionAltar(), "blocks/functional/boss/");
		registerBlock(registry, new KrorAltar(), "blocks/functional/boss/");
		registerBlock(registry, new MechBotAltar(), "blocks/functional/boss/");
		registerBlock(registry, new PowerStation(), "blocks/functional/boss/");
		registerBlock(registry, new PrimordialShrine(), "blocks/functional/boss/");
		registerBlock(registry, new RockriderShrine(), "blocks/functional/boss/");
		registerBlock(registry, new ShadowAltar(), "blocks/functional/boss/");
		registerBlock(registry, new SilverfootAltar(), "blocks/functional/boss/");
		registerBlock(registry, new ToyBox(), "blocks/functional/boss/");
		registerBlock(registry, new VinocorneShrine(), "blocks/functional/boss/");
		registerBlock(registry, new VisualentAltar(), "blocks/functional/boss/");
		registerBlock(registry, new VoxxulonAltar(), "blocks/functional/boss/");

		registerBlock(registry, new PortalBlock("AbyssPortal", "abyss_portal", ConfigurationUtil.MainConfig.dimensionIds.abyss, Enums.RGBIntegers.RED_2), "blocks/functional/portal/");
		registerBlock(registry, new AncientCavernPortalBlock(), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("BarathosPortal", "barathos_portal", ConfigurationUtil.MainConfig.dimensionIds.barathos, Enums.RGBIntegers.LIGHT_CORAL), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("BoreanPortal", "borean_portal", ConfigurationUtil.MainConfig.dimensionIds.lborean, Enums.RGBIntegers.IRIS_BLUE), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("CandylandPortal", "candyland_portal", ConfigurationUtil.MainConfig.dimensionIds.candyland, Enums.RGBIntegers.MISTY_ROSE), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("CelevePortal", "celeve_portal", ConfigurationUtil.MainConfig.dimensionIds.celeve, Enums.RGBIntegers.YELLOW_2), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("CreeponiaPortal", "creeponia_portal", ConfigurationUtil.MainConfig.dimensionIds.creeponia, Enums.RGBIntegers.DE_YORK), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("CrysteviaPortal", "crystevia_portal", ConfigurationUtil.MainConfig.dimensionIds.crystevia, Enums.RGBIntegers.HELIOTROPE), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("DeeplandsPortal", "deeplands_portal", ConfigurationUtil.MainConfig.dimensionIds.deeplands, Enums.RGBIntegers.SILVER), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("DustopiaPortal", "dustopia_portal", ConfigurationUtil.MainConfig.dimensionIds.dustopia, Enums.RGBIntegers.BLACK), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("GardenciaPortal", "gardencia_portal", ConfigurationUtil.MainConfig.dimensionIds.gardencia, Enums.RGBIntegers.DEEP_PINK), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("GreckonPortal", "greckon_portal", ConfigurationUtil.MainConfig.dimensionIds.greckon, Enums.RGBIntegers.DARK_VIOLET), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("HavenPortal", "haven_portal", ConfigurationUtil.MainConfig.dimensionIds.haven, Enums.RGBIntegers.BRIGHT_TURQUOISE), "blocks/functional/portal/");
		registerBlock(registry, new ImmortallisPortalBlock(), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("IrominePortal", "iromine_portal", ConfigurationUtil.MainConfig.dimensionIds.iromine, Enums.RGBIntegers.TANGERINE_YELLOW), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("LelyetiaPortal", "lelyetia_portal", ConfigurationUtil.MainConfig.dimensionIds.lelyetia, Enums.RGBIntegers.MANGO_TANGO), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("LunalusPortal", "lunalus_portal", ConfigurationUtil.MainConfig.dimensionIds.lunalus, Enums.RGBIntegers.LAVENDER_BLUSH), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("MysteriumPortal", "mysterium_portal", ConfigurationUtil.MainConfig.dimensionIds.mysterium, Enums.RGBIntegers.TYRIAN_PURPLE), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("NetherPortal", "nether_portal", -1, Enums.RGBIntegers.PURPLE), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("PrecasiaPortal", "precasia_portal", ConfigurationUtil.MainConfig.dimensionIds.precasia, Enums.RGBIntegers.ELECTRIC_LIME), "blocks/functional/portal/");
		registerBlock(registry, new RunandorPortalBlock(), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("ShyrelandsPortal", "shyrelands_portal", ConfigurationUtil.MainConfig.dimensionIds.shyrelands, Enums.RGBIntegers.YELLOW), "blocks/functional/portal/");
		registerBlock(registry, new PortalBlock("VoxPondsPortal", "vox_ponds_portal", ConfigurationUtil.MainConfig.dimensionIds.voxPonds, Enums.RGBIntegers.OLIVE), "blocks/functional/portal/");

		registerBlock(registry, new AncientAltar(), "blocks/functional/utility/");
		registerBlock(registry, new AncientCavernShrine("ErebonShrine", "erebon_shrine", EREBON), "blocks/functional/utility/");
		registerBlock(registry, new AncientCavernShrine("LuxonShrine", "luxon_shrine", LUXON), "blocks/functional/utility/");
		registerBlock(registry, new AncientCavernShrine("PlutonShrine", "pluton_shrine", PLUTON), "blocks/functional/utility/");
		registerBlock(registry, new AncientCavernShrine("SelyanShrine", "selyan_shrine", SELYAN), "blocks/functional/utility/");
		registerBlock(registry, new AscensionShrine(), "blocks/functional/utility/");
		registerBlock(registry, new VoxCrate(), "blocks/functional/utility/");
		registerBlock(registry, new CreationForge(), "blocks/functional/utility/");
		registerBlock(registry, new CrystalCreator("BlueCrystalCreator", "blue_crystal_creator"), "blocks/functional/utility/");
		registerBlock(registry, new CrystalCreator("GreenCrystalCreator", "green_crystal_creator"), "blocks/functional/utility/");
		registerBlock(registry, new CrystalCreator("PurpleCrystalCreator", "purple_crystal_creator"), "blocks/functional/utility/");
		registerBlock(registry, new CrystalCreator("RedCrystalCreator", "red_crystal_creator"), "blocks/functional/utility/");
		registerBlock(registry, new CrystalCreator("WhiteCrystalCreator", "white_crystal_creator"), "blocks/functional/utility/");
		registerBlock(registry, new CrystalCreator("YellowCrystalCreator", "yellow_crystal_creator"), "blocks/functional/utility/");
		registerBlock(registry, new CrystalExtensionShrine(), "blocks/functional/utility/");
		registerBlock(registry, new DecloggingTable(), "blocks/functional/utility/");
		registerBlock(registry, new DeepCase(), "blocks/functional/misc/");
		registerBlock(registry, new DivineStation(), "blocks/functional/utility/");
		registerBlock(registry, new EnigmaTable(), "blocks/functional/utility/");
		registerBlock(registry, new ExoidStation(), "blocks/functional/utility/");
		registerBlock(registry, new ExtractionDevice(false), "blocks/functional/utility/");
		registerBlock(registry, new ExtractionDevice(true), "blocks/functional/utility/");
		registerBlock(registry, new FiltrationSystem(), "blocks/functional/utility/");
		registerItemlessBlock(registry, ((FiltrationSystem)getUnmappedBlock("filtration_system")).getBoilingState());
		registerBlock(registry, new FrameBench(), "blocks/functional/utility/");
		registerBlock(registry, new GoldAccumulator(), "blocks/functional/utility/");
		registerBlock(registry, new HauntingTable(), "blocks/functional/utility/");
		registerBlock(registry, new ImmortallisProgressor("ImmortallisProgressor1", "immortallis_progressor_1", 1), "blocks/functional/utility/");
		registerBlock(registry, new ImmortallisProgressor("ImmortallisProgressor2", "immortallis_progressor_2", 2), "blocks/functional/utility/");
		registerBlock(registry, new ImmortallisProgressor("ImmortallisProgressor3", "immortallis_progressor_3", 3), "blocks/functional/utility/");
		registerBlock(registry, new ImmortallisProgressor("ImmortallisProgressor4", "immortallis_progressor_4", 4), "blocks/functional/utility/");
		registerBlock(registry, new ImmortallisProgressor("ImmortallisProgressor5", "immortallis_progressor_5", 5), "blocks/functional/utility/");
		registerBlock(registry, new ImmortallisProgressor("ImmortallisProgressor6", "immortallis_progressor_6", 6), "blocks/functional/utility/");
		registerBlock(registry, new ImmortallisProgressor("ImmortallisProgressor7", "immortallis_progressor_7", 7), "blocks/functional/utility/");
		registerBlock(registry, new ImmortallisProgressor("ImmortallisProgressor8", "immortallis_progressor_8", 8), "blocks/functional/utility/");
		registerBlock(registry, new ImmortallisProgressor("ImmortallisProgressor9", "immortallis_progressor_9", 9), "blocks/functional/utility/");
		registerBlock(registry, new InfusionTable(), "blocks/functional/utility/");
		registerBlock(registry, new IroCrate(), "blocks/functional/misc/");
		registerBlock(registry, new LunarCreationTable(), "blocks/functional/utility/");
		registerBlock(registry, new LunarEnrichmentTable(), "blocks/functional/utility/");
		registerBlock(registry, new MendingTable(), "blocks/functional/utility/");
		registerBlock(registry, new MineralizationStation(), "blocks/functional/utility/");
		registerBlock(registry, new PetalCraftingStation(), "blocks/functional/utility/");
		registerBlock(registry, new PureGoldAccumulator(), "blocks/functional/utility/");
		registerBlock(registry, new RecreationStation(), "blocks/functional/utility/");
		registerBlock(registry, new RuneRandomizer(), "blocks/functional/utility/");
		registerBlock(registry, new RuneShrine(), "blocks/functional/utility/");
		registerBlock(registry, new RunicBlock(), "blocks/functional/utility/");
		registerBlock(registry, new StrangeBlock(), "blocks/functional/utility/");
		registerBlock(registry, new TeaSink(), "blocks/functional/utility/");
		registerItemlessBlock(registry, ((TeaSink)getUnmappedBlock("tea_sink")).getFullSink());
		registerBlock(registry, new VoxStoreCrate(), "blocks/functional/utility/");
		registerBlock(registry, new WhitewashingTable(), "blocks/functional/utility/");

		registerBlock(registry, new GenericPlantBlock("BlueAquaFungi", "blue_aqua_fungi", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("YellowAquaFungi", "yellow_aqua_fungi", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("Arcbulb", "arcbulb", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("Arcflower", "arcflower", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("BurealStocks", "bureal_stocks", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("Candycane", "candycane", Material.ROCK, 0.0f, Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("CandyGrass", "candy_grass", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("BlueCandyGrass", "blue_candy_grass", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("WhiteCandyGrass", "white_candy_grass", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("CeleviansBlue", "celevians_blue", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("CeleviansPurple", "celevians_purple", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("CeleviansRed", "celevians_red", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("CeleviansWhite", "celevians_white", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("ChocolateGrassPlant", "chocolate_grass_plant", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("ChocolateStocks", "chocolate_stocks", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("CreepFlowers", "creep_flowers", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("CreepGrass", "creep_grass", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("BlueCrystalPlant", "blue_crystal_plant", Material.GLASS, 0.0f, Material.ROCK), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("GreenCrystalPlant", "green_crystal_plant", Material.GLASS, 0.0f, Material.ROCK), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("PurpleCrystalPlant", "purple_crystal_plant", Material.GLASS, 0.0f, Material.ROCK), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("RedCrystalPlant", "red_crystal_plant", Material.GLASS, 0.0f, Material.ROCK), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("WhiteCrystalPlant", "white_crystal_plant", Material.GLASS, 0.0f, Material.ROCK), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("YellowCrystalPlant", "yellow_crystal_plant", Material.GLASS, 0.0f, Material.ROCK), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("Daileers", "daileers", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("DawnBulb", "dawn_bulb", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("DawnBush", "dawn_bush", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("DawnFlower", "dawn_flower", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("DawnGrass", "dawn_grass", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("DayloomsBlue", "daylooms_blue", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("DayloomsPink", "daylooms_pink", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("DayloomsYellow", "daylooms_yellow", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("DeadGrass", "dead_grass", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("DeepBlooms", "deep_blooms", Material.ROCK), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("DeepGrass", "deep_grass", Material.ROCK), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("HauntedFlower", "haunted_flower", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("HavenGrassPlant", "haven_grass_plant", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("HorizonDaisies", "horizon_daisies", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("IroGrass", "iro_grass", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("Irotops", "irotops", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("LelyetianGrass", "lelyetian_grass", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new UpsideDownGenericPlant("LelyetianGrassDown", "lelyetian_grass_down", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("LuconGrass", "lucon_grass", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("Lunalip", "lunalip", Material.GROUND, Material.GRASS), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("Luntar", "luntar", Material.GROUND, Material.GRASS), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("Lurchians", "lurchians", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("Lylips", "lylips", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("Magias", "magias", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("MallowPile", "mallow_pile", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("MarshTube", "marsh_tube", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("Mellians", "mellians", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new MysticBush(), "blocks/generation/plants/");
		registerBlock(registry, new MysticFerns(), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("OcealitesBlue", "ocealites_blue", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("OcealitesRed", "ocealites_red", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("Pertonias", "pertonias", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("RainbowGrass", "rainbow_grass", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("RainbowGrass2", "rainbow_grass2", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("RainbowGrass3", "rainbow_grass3", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("RuneBulbs", "rune_bulbs", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("RunicBush", "runic_bush", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("ShadowShrub", "shadow_shrub", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new FlowerBlock("ShyreWeed", "shyre_weed", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("SilverGrass", "silver_grass", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new TangleThorns(), "blocks/generation/plants/");
		registerBlock(registry, new TrilliadBloom(), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("Tubeicles", "tubeicles", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("GreenWaterweeds", "green_waterweeds", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("WhiteWaterweeds", "white_waterweeds", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new GenericPlantBlock("YellowWaterweeds", "yellow_waterweeds", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantMultiStackable("BlueLollypop", "blue_lollypop", Material.GLASS, 0.0f, Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantMultiStackable("RedLollypop", "red_lollypop", Material.GLASS, 0.0f, Material.GRASS, Material.GROUND).addStemBlock((PlantMultiStackable)getUnmappedBlock("blue_lollypop")), "blocks/generation/plants/");
		registerBlock(registry, new PlantMultiStackable("YellowLollypop", "yellow_lollypop", Material.GLASS, 0.0f, Material.GRASS, Material.GROUND).addStemBlock((PlantMultiStackable)getUnmappedBlock("blue_lollypop"), (PlantMultiStackable)getUnmappedBlock("red_lollypop")), "blocks/generation/plants/");
		registerBlock(registry, new UpsideDownPlantStackable("AncientVines", "ancient_vines", Material.ROCK), "blocks/generation/plants/");
		registerBlock(registry, new UpsideDownPlantStackable("AncientVinesCap", "ancient_vines_cap", Material.ROCK).setStemBlock((PlantStackable)getUnmappedBlock("ancient_vines")), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("BloodPineStem", "blood_pine_stem", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("BloodPine", "blood_pine", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("blood_pine_stem")), "blocks/generation/plants/");
		registerBlock(registry, new BloodSpikes(), "blocks/generation/plants/");
		registerBlock(registry, new BloodStrands(), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("EyeBulb", "bulb_stock", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new BulbStockCap(), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("CelebulbsStem", "celebulbs_stem", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("CelebulbsGreen", "celebulbs_green", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("celebulbs_stem")), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("CelebulbsYellow", "celebulbs_yellow", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("celebulbs_stem")), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("CoralCage", "coral_cage", Material.GRASS, Material.GROUND, Material.ROCK), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("DawnStocks", "dawn_stocks", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("DawnStocksTop", "dawn_stocks_top", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("dawn_stocks")), "blocks/generation/plants/");
		registerBlock(registry, new DawnwoodBars(), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("DeepBulb", "deep_bulb", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("DeepVines", "deep_vines", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("EyeShrubStem", "eye_shrub_stem", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("EyeShrub", "eye_shrub", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("eye_shrub_stem")), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("FlakeVine", "flake_vine", Material.ROCK), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("FlakeVineTop", "flake_vine_top", Material.ROCK).setStemBlock((PlantStackable)getUnmappedBlock("flake_vine")), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("GardenGrass", "garden_grass", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("HavendalesBlueStem", "havendales_blue_stem", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("HavendalesBlue", "havendales_blue", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("havendales_blue_stem")), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("HavendalesPinkStem", "havendales_pink_stem", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("HavendalesPink", "havendales_pink", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("havendales_pink_stem")), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("HavendalesYellowStem", "havendales_yellow_stem", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("HavendalesYellow", "havendales_yellow", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("havendales_yellow_stem")), "blocks/generation/plants/");
		registerBlock(registry, new BidirectionalPlantStackable("LelyetianStem", "lelyetian_stem", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("LelyetianStemCap", "lelyetian_stem_cap", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("lelyetian_stem")), "blocks/generation/plants/");
		registerBlock(registry, new UpsideDownPlantStackable("LelyetianStemCapDown", "lelyetian_stem_cap_down", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("lelyetian_stem")), "blocks/generation/plants/");
		registerBlock(registry, new BidirectionalPlantStackable("LelyetianWiggler", "lelyetian_wiggler", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new UpsideDownPlantStackable("LelyetianWigglerBottom", "lelyetian_wiggler_bottom", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("lelyetian_wiggler")), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("LelyetianWigglerTop", "lelyetian_wiggler_top", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("lelyetian_wiggler")), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("GreenPeppermint", "green_peppermint", Material.GLASS, 0.0f, Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("RedPeppermint", "red_peppermint", Material.GLASS, 0.0f, Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("PlasticStick", "plastic_stick", Material.ROCK, 0.0f, Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("CandyTube", "candy_tube", Material.GLASS, 0.0f, Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("plastic_stick")), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("Shadicles", "shadicles", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("ShadiclesTop", "shadicles_top", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("shadicles")), "blocks/generation/plants/");
		registerBlock(registry, new BidirectionalPlantStackable("ShyreStock", "shyre_stock", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("ShyreCap", "shyre_cap", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("shyre_stock")), "blocks/generation/plants/");
		registerBlock(registry, new UpsideDownPlantStackable("ShyreCapDown", "shyre_cap_down", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("shyre_stock")), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("VoxFungiStem", "vox_fungi_stem", Material.GRASS, Material.GROUND), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("VoxFungi", "vox_fungi", Material.GRASS, Material.GROUND).setStemBlock((PlantStackable)getUnmappedBlock("vox_fungi_stem")), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("VoxTentaclesStem", "vox_tentacles_stem", Material.GRASS, Material.GROUND, Material.SPONGE), "blocks/generation/plants/");
		registerBlock(registry, new PlantStackable("VoxTentacles", "vox_tentacles", Material.GRASS, Material.GROUND, Material.SPONGE).setStemBlock((PlantStackable)getUnmappedBlock("vox_tentacles_stem")), "blocks/generation/plants/");
		registerBlock(registry, new RedWaterweeds(), "blocks/generation/plants/");
		registerBlock(registry, new VinesBlock("CreepVines", "creep_vines"), "blocks/generation/plants/");

		registerItemlessBlock(registry, new CropBlock("BubbleBerryCrop", "bubble_berry_crop", true));
		registerItemlessBlock(registry, new CropBlock("ChilliCrop", "chilli_crop", true));
		registerItemlessBlock(registry, new CropBlock("EyeBulbCrop", "eye_bulb_crop", false));
		registerItemlessBlock(registry, new CropBlock("FloraclesCrop", "floracles_crop", true));
		registerItemlessBlock(registry, new CropBlock("GoldicapsCrop", "goldicaps_crop", true));
		registerItemlessBlock(registry, new CropBlock("HeartFruitCrop", "heart_fruit_crop", false));
		registerItemlessBlock(registry, new CropBlock("HollyTopsCrop", "holly_tops_crop", true));
		registerItemlessBlock(registry, new CropBlock("LunacrikeCrop", "lunacrike_crop", true));
		registerItemlessBlock(registry, new CropBlock("LunaGlobeCrop", "luna_globe_crop", true));
		registerItemlessBlock(registry, new CropBlock("LunalonsCrop", "lunalon_crop", true));
		registerItemlessBlock(registry, new CropBlock("MagicMarangCrop", "magic_marang_crop", false));
		registerItemlessBlock(registry, new CropBlock("MysticShroomCrop", "mystic_shroom_crop", false));
		registerItemlessBlock(registry, new CropBlock("RosidonCrop", "rosidon_crop", true));
		registerItemlessBlock(registry, new CropBlock("TeaCrop", "tea_crop", true));
		registerItemlessBlock(registry, new CropBlock("ThornyPlantCrop", "thorny_plant_crop", true));
		registerItemlessBlock(registry, new CropBlock("TrilliadCrop", "trilliad_crop", true));

		registerBlock(registry, new StatueBlock("BaneStatue", "bane_statue", SoundsRegister.mobBaneLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldBaneStatue", "gold_bane_statue", SoundsRegister.mobBaneLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateBaneStatue", "ornate_bane_statue", SoundsRegister.mobBaneLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("BaronessStatue", "baroness_statue", SoundsRegister.mobArielLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldBaronessStatue", "gold_baroness_statue", SoundsRegister.mobArielLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateBaronessStatue", "ornate_baroness_statue", SoundsRegister.mobArielLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("ClunkheadStatue", "clunkhead_statue", SoundsRegister.mobClunkheadDeath), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldClunkheadStatue", "gold_clunkhead_statue", SoundsRegister.mobClunkheadDeath), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateClunkheadStatue", "ornate_clunkhead_statue", SoundsRegister.mobClunkheadDeath), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("ConiferonStatue", "coniferon_statue", SoundsRegister.mobConiferonLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldConiferonStatue", "gold_coniferon_statue", SoundsRegister.mobConiferonLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateConiferonStatue", "ornate_coniferon_statue", SoundsRegister.mobConiferonLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("CorallusStatue", "corallus_statue", SoundsRegister.mobCorallusLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldCorallusStatue", "gold_corallus_statue", SoundsRegister.mobCorallusLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateCorallusStatue", "ornate_corallus_statue", SoundsRegister.mobCorallusLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("CottonCandorStatue", "cotton_candor_statue", SoundsRegister.mobCottonCandorLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldCottonCandorStatue", "gold_cotton_candor_statue", SoundsRegister.mobCottonCandorLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateCottonCandorStatue", "ornate_cotton_candor_statue", SoundsRegister.mobCottonCandorLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("CraexxeusStatue", "craexxeus_statue", SoundsRegister.mobCraexxeusLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldCraexxeusStatue", "gold_craexxeus_statue", SoundsRegister.mobCraexxeusLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateCraexxeusStatue", "ornate_craexxeus_statue", SoundsRegister.mobCraexxeusLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("CreepStatue", "creep_statue", SoundsRegister.mobCreepoidLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldCreepStatue", "gold_creep_statue", SoundsRegister.mobCreepoidLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateCreepStatue", "ornate_creep_statue", SoundsRegister.mobCreepoidLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("CrystocoreStatue", "crystocore_statue", SoundsRegister.mobCrystalConstructLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldCrystocoreStatue", "gold_crystocore_statue", SoundsRegister.mobCrystalConstructLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateCrystocoreStatue", "ornate_crystocore_statue", SoundsRegister.mobCrystalConstructLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("DracyonStatue", "dracyon_statue", SoundsRegister.mobDracyonLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldDracyonStatue", "gold_dracyon_statue", SoundsRegister.mobDracyonLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateDracyonStatue", "ornate_dracyon_statue", SoundsRegister.mobDracyonLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("ElusiveStatue", "elusive_statue", SoundsRegister.mobElusiveLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldElusiveStatue", "gold_elusive_statue", SoundsRegister.mobElusiveLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateElusiveStatue", "ornate_elusive_statue", SoundsRegister.mobElusiveLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("FlashStatue", "flash_statue", SoundsRegister.mobImmortalLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateFlashStatue", "ornate_flash_statue", SoundsRegister.mobImmortalLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldFlashStatue", "gold_flash_statue", SoundsRegister.mobImmortalLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldorthStatue", "goldorth_statue", SoundsRegister.mobGoldorthLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldGoldorthStatue", "gold_goldorth_statue", SoundsRegister.mobGoldorthLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateGoldorthStatue", "ornate_goldorth_statue", SoundsRegister.mobGoldorthLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GrawStatue", "graw_statue", SoundsRegister.mobGrawLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldGrawStatue", "gold_graw_statue", SoundsRegister.mobGrawLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateGrawStatue", "ornate_graw_statue", SoundsRegister.mobGrawLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GuardianStatue", "guardian_statue", SoundsRegister.mobGuardianDeath), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldGuardianStatue", "gold_guardian_statue", SoundsRegister.mobGuardianDeath), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateGuardianStatue", "ornate_guardian_statue", SoundsRegister.mobGuardianDeath), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GyroStatue", "gyro_statue", SoundsRegister.mobGyroLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldGyroStatue", "gold_gyro_statue", SoundsRegister.mobGyroLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateGyroStatue", "ornate_gyro_statue", SoundsRegister.mobGyroLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("HarkosStatue", "harkos_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldHarkosStatue", "gold_harkos_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateHarkosStatue", "ornate_harkos_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("HiveKingStatue", "hive_king_statue", SoundsRegister.mobHiveKingLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldHiveKingStatue", "gold_hive_king_statue", SoundsRegister.mobHiveKingLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateHiveKingStatue", "ornate_hive_king_statue", SoundsRegister.mobHiveKingLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("HoronStatue", "horon_statue", SoundsRegister.mobHoronLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldHoronStatue", "gold_horon_statue", SoundsRegister.mobHoronLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateHoronStatue", "ornate_horon_statue", SoundsRegister.mobHoronLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("HydroliskStatue", "hydrolisk_statue", SoundsRegister.mobHydroliskLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldHydroliskStatue", "gold_hydrolisk_statue", SoundsRegister.mobHydroliskLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateHydroliskStatue", "ornate_hydrolisk_statue", SoundsRegister.mobHydroliskLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("KajarosStatue", "kajaros_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldKajarosStatue", "gold_kajaros_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateKajarosStatue", "ornate_kajaros_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("KingBamBamBamStatue", "king_bambambam_statue", SoundsRegister.mobKingBamBamBamLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldKingBamBamBamStatue", "gold_king_bambambam_statue", SoundsRegister.mobKingBamBamBamLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateKingBamBamBamStatue", "ornate_king_bambambam_statue", SoundsRegister.mobKingBamBamBamLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("KingShroomusStatue", "king_shroomus_statue", SoundsRegister.mobFungiLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldKingShroomusStatue", "gold_king_shroomus_statue", SoundsRegister.mobFungiLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateKingShroomusStatue", "ornate_king_shroomus_statue", SoundsRegister.mobFungiLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("KlobberStatue", "klobber_statue", SoundsRegister.mobImmortalLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldKlobberStatue", "gold_klobber_statue", SoundsRegister.mobImmortalLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateKlobberStatue", "ornate_klobber_statue", SoundsRegister.mobImmortalLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("KrorStatue", "kror_statue", SoundsRegister.mobKrorLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldKrorStatue", "gold_kror_statue", SoundsRegister.mobKrorLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateKrorStatue", "ornate_kror_statue", SoundsRegister.mobKrorLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("MechbotStatue", "mechbot_statue", SoundsRegister.mobMechyonLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldMechbotStatue", "gold_mechbot_statue", SoundsRegister.mobMechyonLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateMechbotStatue", "ornate_mechbot_statue", SoundsRegister.mobMechyonLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("MirageStatue", "mirage_statue", SoundsRegister.mobImmortalLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldMirageStatue", "gold_mirage_statue", SoundsRegister.mobImmortalLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateMirageStatue", "ornate_mirage_statue", SoundsRegister.mobImmortalLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("MiskelStatue", "miskel_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldMiskelStatue", "gold_miskel_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateMiskelStatue", "ornate_miskel_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("NethengeicWitherStatue", "nethengeic_wither_statue", SoundsRegister.mobNethengeicWitherLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldNethengeicWitherStatue", "gold_nethengeic_wither_statue", SoundsRegister.mobNethengeicWitherLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateNethengeicWitherStatue", "ornate_nethengeic_wither_statue", SoundsRegister.mobNethengeicWitherLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OkazorStatue", "okazor_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldOkazorStatue", "gold_okazor_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateOkazorStatue", "ornate_okazor_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("PenumbraStatue", "penumbra_statue", SoundsRegister.mobPenumbraLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldPenumbraStatue", "gold_penumbra_statue", SoundsRegister.mobPenumbraLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnatePenumbraStatue", "ornate_penumbra_statue", SoundsRegister.mobPenumbraLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("ProshieldStatue", "proshield_statue", SoundsRegister.mobImmortalLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldProshieldStatue", "gold_proshield_statue", SoundsRegister.mobImmortalLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateProshieldStatue", "ornate_proshield_statue", SoundsRegister.mobImmortalLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("RaxxanStatue", "raxxan_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldRaxxanStatue", "gold_raxxan_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateRaxxanStatue", "ornate_raxxan_statue", SoundsRegister.mobPrimordialLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("RockriderStatue", "rockrider_statue", SoundsRegister.mobRockRiderSwitch), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldRockriderStatue", "gold_rockrider_statue", SoundsRegister.mobRockRiderSwitch), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateRockriderStatue", "ornate_rockrider_statue", SoundsRegister.mobRockRiderSwitch), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("ShadowlordStatue", "shadowlord_statue", SoundsRegister.mobShadowlordLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldShadowlordStatue", "gold_shadowlord_statue", SoundsRegister.mobShadowlordLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateShadowlordStatue", "ornate_shadowlord_statue", SoundsRegister.mobShadowlordLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("SilverfootStatue", "silverfoot_statue", SoundEvents.BLOCK_ANVIL_FALL), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldSilverfootStatue", "gold_silverfoot_statue", SoundEvents.BLOCK_ANVIL_FALL), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateSilverfootStatue", "ornate_silverfoot_statue", SoundEvents.BLOCK_ANVIL_FALL), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("SkeletronStatue", "skeletron_statue", SoundEvents.ENTITY_SKELETON_AMBIENT), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldSkeletronStatue", "gold_skeletron_statue", SoundEvents.ENTITY_SKELETON_AMBIENT), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateSkeletronStatue", "ornate_skeletron_statue", SoundEvents.ENTITY_SKELETON_AMBIENT), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("SmashStatue", "smash_statue", SoundsRegister.mobSmashLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateSmashStatue", "ornate_smash_statue", SoundsRegister.mobSmashLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldSmashStatue", "gold_smash_statue", SoundsRegister.mobSmashLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("TyrosaurStatue", "tyrosaur_statue", SoundsRegister.mobTyrosaurLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldTyrosaurStatue", "gold_tyrosaur_statue", SoundsRegister.mobTyrosaurLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateTyrosaurStatue", "ornate_tyrosaur_statue", SoundsRegister.mobTyrosaurLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("VinocorneStatue", "vinocorne_statue", SoundsRegister.mobTreeSpiritLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldVinocorneStatue", "gold_vinocorne_statue", SoundsRegister.mobTreeSpiritLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateVinocorneStatue", "ornate_vinocorne_statue", SoundsRegister.mobTreeSpiritLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("VisualentStatue", "visualent_statue", SoundsRegister.mobVisularLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldVisualentStatue", "gold_visualent_statue", SoundsRegister.mobVisularLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateVisualentStatue", "ornate_visualent_statue", SoundsRegister.mobVisularLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("VoxxulonStatue", "voxxulon_statue", SoundsRegister.mobVoxxulonLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldVoxxulonStatue", "gold_voxxulon_statue", SoundsRegister.mobVoxxulonLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateVoxxulonStatue", "ornate_voxxulon_statue", SoundsRegister.mobVoxxulonLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("XxeusStatue", "xxeus_statue", SoundsRegister.mobXxeusLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("GoldXxeusStatue", "gold_xxeus_statue", SoundsRegister.mobXxeusLiving), "blocks/decoration/statues/");
		registerBlock(registry, new StatueBlock("OrnateXxeusStatue", "ornate_xxeus_statue", SoundsRegister.mobXxeusLiving), "blocks/decoration/statues/");

		registerBlock(registry, new BannerBlock("AncientBanner", "ancient_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedAncientBanner", "gilded_ancient_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedAncientBanner", "encrusted_ancient_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledAncientBanner", "bejewelled_ancient_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("BaronBanner", "baron_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedBaronBanner", "gilded_baron_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedBaronBanner", "encrusted_baron_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledBaronBanner", "bejewelled_baron_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("BloodBanner", "blood_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedBloodBanner", "gilded_blood_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedBloodBanner", "encrusted_blood_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledBloodBanner", "bejewelled_blood_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("BoreicBanner", "boreic_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedBoreicBanner", "gilded_boreic_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedBoreicBanner", "encrusted_boreic_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledBoreicBanner", "bejewelled_boreic_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("CandyBanner", "candy_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedCandyBanner", "gilded_candy_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedCandyBanner", "encrusted_candy_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledCandyBanner", "bejewelled_candy_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("ClownBanner", "clown_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedClownBanner", "gilded_clown_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedClownBanner", "encrusted_clown_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledClownBanner", "bejewelled_clown_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("CreationBanner", "creation_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedCreationBanner", "gilded_creation_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedCreationBanner", "encrusted_creation_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledCreationBanner", "bejewelled_creation_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("CreepoidBanner", "creepoid_banner"), "blocks/decoration/banners/normal/"); // TODO Obtain method
		registerBlock(registry, new BannerBlock("GildedCreepoidBanner", "gilded_creepoid_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedCreepoidBanner", "encrusted_creepoid_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledCreepoidBanner", "bejewelled_creepoid_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("CreepyBanner", "creepy_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedCreepyBanner", "gilded_creepy_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedCreepyBanner", "encrusted_creepy_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledCreepyBanner", "bejewelled_creepy_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("CrystalBanner", "crystal_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedCrystalBanner", "gilded_crystal_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedCrystalBanner", "encrusted_crystal_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledCrystalBanner", "bejewelled_crystal_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("DeepBanner", "deep_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedDeepBanner", "gilded_deep_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedDeepBanner", "encrusted_deep_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledDeepBanner", "bejewelled_deep_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("DustopianBanner", "dustopian_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedDustopianBanner", "gilded_dustopian_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedDustopianBanner", "encrusted_dustopian_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledDustopianBanner", "bejewelled_dustopian_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("EnergyBanner", "energy_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedEnergyBanner", "gilded_energy_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedEnergyBanner", "encrusted_energy_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledEnergyBanner", "bejewelled_energy_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("FungalBanner", "fungal_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedFungalBanner", "gilded_fungal_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedFungalBanner", "encrusted_fungal_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledFungalBanner", "bejewelled_fungal_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("GhostlyBanner", "ghostly_banner"), "blocks/decoration/banners/normal/"); // TODO Obtain method
		registerBlock(registry, new BannerBlock("GildedGhostlyBanner", "gilded_ghostly_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedGhostlyBanner", "encrusted_ghostly_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledGhostlyBanner", "bejewelled_ghostly_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("GhoulBanner", "ghoul_banner"), "blocks/decoration/banners/normal/"); // TODO Obtain method
		registerBlock(registry, new BannerBlock("GildedGhoulBanner", "gilded_ghoul_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedGhoulBanner", "encrusted_ghoul_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledGhoulBanner", "bejewelled_ghoul_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("GingerbreadBanner", "gingerbread_banner"), "blocks/decoration/banners/normal/"); // TODO Obtain method
		registerBlock(registry, new BannerBlock("GildedGingerbreadBanner", "gilded_gingerbread_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedGingerbreadBanner", "encrusted_gingerbread_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledGingerbreadBanner", "bejewelled_gingerbread_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("HauntedBanner", "haunted_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedHauntedBanner", "gilded_haunted_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedHauntedBanner", "encrusted_haunted_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledHauntedBanner", "bejewelled_haunted_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("IllusionBanner", "illusion_banner"), "blocks/decoration/banners/normal/"); // TODO Obtain method
		registerBlock(registry, new BannerBlock("GildedIllusionBanner", "gilded_illusion_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedIllusionBanner", "encrusted_illusion_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledIllusionBanner", "bejewelled_illusion_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("ImmortalBanner", "immortal_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedImmortalBanner", "gilded_immortal_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedImmortalBanner", "encrusted_immortal_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledImmortalBanner", "bejewelled_immortal_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("LelyetianBanner", "lelyetian_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedLelyetianBanner", "gilded_lelyetian_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedLelyetianBanner", "encrusted_lelyetian_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledLelyetianBanner", "bejewelled_lelyetian_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("LightBanner", "light_banner"), "blocks/decoration/banners/normal/"); // TODO Obtain method
		registerBlock(registry, new BannerBlock("GildedLightBanner", "gilded_light_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedLightBanner", "encrusted_light_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledLightBanner", "bejewelled_light_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("LottoBanner", "lotto_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedLottoBanner", "gilded_lotto_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedLottoBanner", "encrusted_lotto_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledLottoBanner", "bejewelled_lotto_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("LunarBanner", "lunar_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedLunarBanner", "gilded_lunar_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedLunarBanner", "encrusted_lunar_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledLunarBanner", "bejewelled_lunar_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("MechaBanner", "mecha_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedMechaBanner", "gilded_mecha_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedMechaBanner", "encrusted_mecha_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledMechaBanner", "bejewelled_mecha_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("NethengeicBanner", "nethengeic_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedNethengeicBanner", "gilded_nethengeic_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedNethengeicBanner", "encrusted_nethengeic_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledNethengeicBanner", "bejewelled_nethengeic_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("NetherBanner", "nether_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedNetherBanner", "gilded_nether_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedNetherBanner", "encrusted_nether_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledNetherBanner", "bejewelled_nether_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("RosidianBanner", "rosidian_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedRosidianBanner", "gilded_rosidian_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedRosidianBanner", "encrusted_rosidian_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledRosidianBanner", "bejewelled_rosidian_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("RunicBanner", "runic_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedRunicBanner", "gilded_runic_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedRunicBanner", "encrusted_runic_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledRunicBanner", "bejewelled_runic_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("SeaBanner", "sea_banner"), "blocks/decoration/banners/normal/"); // TODO Obtain method
		registerBlock(registry, new BannerBlock("GildedSeaBanner", "gilded_sea_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedSeaBanner", "encrusted_sea_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledSeaBanner", "bejewelled_sea_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("ShadowBanner", "shadow_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedShadowBanner", "gilded_shadow_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedShadowBanner", "encrusted_shadow_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledShadowBanner", "bejewelled_shadow_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("ShinyBanner", "shiny_banner"), "blocks/decoration/banners/normal/"); // TODO Obtain method
		registerBlock(registry, new BannerBlock("GildedShinyBanner", "gilded_shiny_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedShinyBanner", "encrusted_shiny_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledShinyBanner", "bejewelled_shiny_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("ShyreBanner", "shyre_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedShyreBanner", "gilded_shyre_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedShyreBanner", "encrusted_shyre_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledShyreBanner", "bejewelled_shyre_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("SkeletalBanner", "skeletal_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedSkeletalBanner", "gilded_skeletal_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedSkeletalBanner", "encrusted_skeletal_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledSkeletalBanner", "bejewelled_skeletal_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("SoulBanner", "soul_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedSoulBanner", "gilded_soul_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedSoulBanner", "encrusted_soul_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledSoulBanner", "bejewelled_soul_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("UtopianBanner", "utopian_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedUtopianBanner", "gilded_utopian_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedUtopianBanner", "encrusted_utopian_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledUtopianBanner", "bejewelled_utopian_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("VoidBanner", "void_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedVoidBanner", "gilded_void_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedVoidBanner", "encrusted_void_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledVoidBanner", "bejewelled_void_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("VoxBanner", "vox_banner"), "blocks/decoration/banners/normal/");
		registerBlock(registry, new BannerBlock("GildedVoxBanner", "gilded_vox_banner"), "blocks/decoration/banners/gilded/");
		registerBlock(registry, new BannerBlock("EncrustedVoxBanner", "encrusted_vox_banner"), "blocks/decoration/banners/encrusted/");
		registerBlock(registry, new BannerBlock("BejewelledVoxBanner", "bejewelled_vox_banner"), "blocks/decoration/banners/bejewelled/");
		registerBlock(registry, new BannerBlock("PlutonBanner", "pluton_banner"), "blocks/decoration/banners/special/");
		registerBlock(registry, new BannerBlock("LuxonBanner", "luxon_banner"), "blocks/decoration/banners/special/");
		registerBlock(registry, new BannerBlock("ErebonBanner", "erebon_banner"), "blocks/decoration/banners/special/");
		registerBlock(registry, new BannerBlock("SelyanBanner", "selyan_banner"), "blocks/decoration/banners/special/");
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
			else if (map.key.equals(new ResourceLocation("aoa3:magical_enhancer"))) {
				map.remap(enhancerMagic);
			}
		}
	}

	@SubscribeEvent
	public static void registerItemBlockRenders(final ModelRegistryEvent ev) {
		blockRegistryList.forEach(wrapper -> {
			if (wrapper.itemBlock != null)
				registerRender(wrapper.itemBlock, wrapper.modelSubfolder);
		});

		AdventOfAscension.proxy.registerStateMappers();
	}

	public static void doInitTasks() {
		AdventOfAscension.logOptionalMessage("Performing miscellaneous post-init blocks tasks");

		blockRegistryList.forEach(wrapper -> {
			if (wrapper.oreDictEntries != null) {
				for (String entry : wrapper.oreDictEntries) {
					OreDictionary.registerOre(entry, wrapper.block);
				}
			}
		});

		crystalCreatorBlue.setConversionItems(ItemRegister.gemstonesBlue, ItemRegister.crystalBlue);
		crystalCreatorGreen.setConversionItems(ItemRegister.gemstonesGreen, ItemRegister.crystalGreen);
		crystalCreatorPurple.setConversionItems(ItemRegister.gemstonesPurple, ItemRegister.crystalPurple);
		crystalCreatorRed.setConversionItems(ItemRegister.gemstonesRed, ItemRegister.crystalRed);
		crystalCreatorWhite.setConversionItems(ItemRegister.gemstonesWhite, ItemRegister.crystalWhite);
		crystalCreatorYellow.setConversionItems(ItemRegister.gemstonesYellow, ItemRegister.crystalYellow);
		oreAmethyst.setDrop(ItemRegister.gemAmethyst);
		oreBloodstone.setDrop(ItemRegister.gemBloodstone);
		oreBlueGemstone.setDrop(ItemRegister.gemstonesBlue);
		oreChestboneFragments.setDrop(ItemRegister.boneFragmentChestbone);
		oreCrystallite.setDrop(ItemRegister.gemCrystallite);
		oreFootboneFragments.setDrop(ItemRegister.boneFragmentFootbone);
		oreGemenyte.setDrop(ItemRegister.gemGemenyte);
		oreGreenGemstone.setDrop(ItemRegister.gemstonesGreen);
		oreJade.setDrop(ItemRegister.gemJade);
		oreJewelyte.setDrop(ItemRegister.gemJewelyte);
		oreLegboneFragments.setDrop(ItemRegister.boneFragmentLegbone);
		oreOrnamyte.setDrop(ItemRegister.gemOrnamyte);
		orePurpleGemstone.setDrop(ItemRegister.gemstonesPurple);
		oreRedGemstone.setDrop(ItemRegister.gemstonesRed);
		oreSapphire.setDrop(ItemRegister.gemSapphire);
		oreShyregem.setDrop(ItemRegister.gemShyregem);
		oreSkullboneFragments.setDrop(ItemRegister.boneFragmentSkullbone);
		oreWhiteGemstone.setDrop(ItemRegister.gemstonesWhite);
		oreYellowGemstone.setDrop(ItemRegister.gemstonesYellow);

		blockRegistryList = null;
	}

	@SideOnly(Side.CLIENT)
	public static void registerStateMappers() {
		for (BlockRegistryWrapper wrapper : blockRegistryList) {
			if (wrapper.block instanceof CustomStateMapperBlock) {
				ModelLoader.setCustomStateMapper(wrapper.block, ((CustomStateMapperBlock)wrapper.block).getStateMapper());
			}
			else if (wrapper.block instanceof BasicFluidBlock) {
				BasicFluidBlock.FluidStateMapper fluidStateMapper = ((BasicFluidBlock)wrapper.block).getFluidStateMap();
				Item fluidItem = Item.getItemFromBlock(wrapper.block);

				if (fluidItem != Items.AIR)
					ModelLoader.setCustomMeshDefinition(fluidItem, fluidStateMapper);

				ModelLoader.setCustomStateMapper(wrapper.block, fluidStateMapper);
			}
		}

	}

	@SideOnly(Side.CLIENT)
	private static void setStateMapper(Block... blocks) {
		for (Block block : blocks) {
			if (block instanceof CustomStateMapperBlock)
				ModelLoader.setCustomStateMapper(block, ((CustomStateMapperBlock)block).getStateMapper());
		}
	}

	private static void registerBlock(IForgeRegistry<Block> registry, Block block, String modelSubfolder, String... oreDictKeys) {
		registry.register(block);

		ItemBlock itemBlock;

		if (block instanceof SlabBlock.DoubleSlabBlock) {
			BlockSlab halfBlock = ((SlabBlock)block).getHalfBlock();

			blockRegistryList.add(new BlockRegistryWrapper(halfBlock, (ItemSlab)new ItemSlab(halfBlock, halfBlock, (SlabBlock.DoubleSlabBlock)block).setRegistryName(halfBlock.getRegistryName()), "blocks/decoration/slabs/", oreDictKeys));
			blockRegistryList.add(new BlockRegistryWrapper(block, null, null));

			registry.register(halfBlock);

			return;
		}
		else if (block instanceof LampBlock) {
			LampBlock offLamp = ((LampBlock)block).getOffLamp();

			blockRegistryList.add(new BlockRegistryWrapper(offLamp, (ItemBlock)new ItemBlock(offLamp).setRegistryName(offLamp.getRegistryName()), modelSubfolder, oreDictKeys));
			blockRegistryList.add(new BlockRegistryWrapper(block, (ItemBlock)new ItemBlock(block).setRegistryName(block.getRegistryName()), modelSubfolder));

			registry.register(offLamp);

			return;
		}
		else {
			itemBlock = (ItemBlock)new ItemBlock(block).setRegistryName(block.getRegistryName());
		}

		blockRegistryList.add(new BlockRegistryWrapper(block, itemBlock, modelSubfolder, oreDictKeys));
	}

	private static void registerItemlessBlock(IForgeRegistry<Block> registry, Block block) {
		registry.register(block);
		blockRegistryList.add(new BlockRegistryWrapper(block, null, null));
	}

	@SubscribeEvent
	public static void registerItemBlocks(final RegistryEvent.Register<Item> ev) {
		blockRegistryList.forEach(wrapper -> {
			if (wrapper.itemBlock != null)
				ev.getRegistry().register(wrapper.itemBlock);
		});
	}

	private static void registerRender(Item item, String location) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation("aoa3:" + location + item.getRegistryName().getPath()), "inventory"));
	}

	@Nullable
	public static Block getUnmappedBlock(String blockRegistryName) {
		if (blockRegistryList != null) {
			for (BlockRegistryWrapper wrapper : blockRegistryList) {
				if (wrapper.block.getRegistryName().getPath().equals(blockRegistryName))
					return wrapper.block;
			}
		}

		return null;
	}

	private static class BlockRegistryWrapper {
		private final String[] oreDictEntries;
		private final String modelSubfolder;
		private final Block block;
		private ItemBlock itemBlock;

		private BlockRegistryWrapper(Block block, ItemBlock itemBlock, String modelSubfolder, String... oreDictEntries) {
			this.block = block;
			this.itemBlock = itemBlock;
			this.modelSubfolder = modelSubfolder;
			this.oreDictEntries = oreDictEntries == null || oreDictEntries.length == 0 ? null : oreDictEntries;
		}
	}
}
