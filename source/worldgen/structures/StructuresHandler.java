package net.tslat.aoa3.worldgen.structures;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.worldgen.structures.abyss.*;
import net.tslat.aoa3.worldgen.structures.barathos.*;
import net.tslat.aoa3.worldgen.structures.candyland.*;
import net.tslat.aoa3.worldgen.structures.celeve.*;
import net.tslat.aoa3.worldgen.structures.creeponia.*;
import net.tslat.aoa3.worldgen.structures.crystevia.*;
import net.tslat.aoa3.worldgen.structures.deeplands.*;
import net.tslat.aoa3.worldgen.structures.dustopia.*;
import net.tslat.aoa3.worldgen.structures.gardencia.*;
import net.tslat.aoa3.worldgen.structures.greckon.*;
import net.tslat.aoa3.worldgen.structures.haven.*;
import net.tslat.aoa3.worldgen.structures.iromine.*;
import net.tslat.aoa3.worldgen.structures.lborean.*;
import net.tslat.aoa3.worldgen.structures.lelyetia.*;
import net.tslat.aoa3.worldgen.structures.lunalus.*;
import net.tslat.aoa3.worldgen.structures.mysterium.*;
import net.tslat.aoa3.worldgen.structures.nether.FireRuneShrine;
import net.tslat.aoa3.worldgen.structures.nether.NethengeicPit;
import net.tslat.aoa3.worldgen.structures.overworld.AmphibiyteCove;
import net.tslat.aoa3.worldgen.structures.overworld.RuinedTeleporterFrame;
import net.tslat.aoa3.worldgen.structures.overworld.WindRuneShrine;
import net.tslat.aoa3.worldgen.structures.precasia.*;
import net.tslat.aoa3.worldgen.structures.runandor.*;
import net.tslat.aoa3.worldgen.structures.shyrelands.*;
import net.tslat.aoa3.worldgen.structures.voxponds.*;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class StructuresHandler {
	private static final TreeMap<String, AoAStructure> structures = new TreeMap<String, AoAStructure>();
	public static final AoAStructure EMPTY_STRUCTURE = new AoAStructure.EmptyStructure();

	protected static void registerStructure(AoAStructure structure) {
		String name = structure.getName();

		if (name != null && name.length() > 0)
			structures.put(name, structure);
	}

	public static boolean generateStructure(final String name, final IWorld world, final Random rand, final BlockPos basePos) {
		return generateStructure(getStructure(name), world, rand, basePos);
	}

	public static boolean generateStructure(final AoAStructure structure, final IWorld world, @Nullable Random rand, final BlockPos basePos) {
		if (structure == EMPTY_STRUCTURE)
			return false;

		structure.generate(world, rand, basePos);
		return true;
	}

	public static AoAStructure getStructure(String name) {
		AoAStructure structure = structures.get(name);

		if (structure == null) {
			Logging.logMessage(Level.WARN, "Unable to find registered structure with name: " + name);

			return EMPTY_STRUCTURE;
		}
		else {
			return structure;
		}
	}

	public static int getStructureListPageCount() {
		return (int)Math.ceil(structures.size() / 50d);
	}

	public static String getStructuresList(int pageIndex) {
		StringBuilder builder = new StringBuilder(", ");
		int i = 0;

		for (String name : structures.keySet()) {
			if (i >= (pageIndex - 1) * 50) {
				if (i >= pageIndex * 50)
					return builder.substring(2);

				builder.append(name);
				builder.append(", ");
			}

			i++;
		}

		return builder.substring(2);
	}

	public static List<String> autoCompleteStructureName(String st) {
		List<String> names = new ArrayList<String>();

		for (String name : structures.keySet()) {
			if (name.startsWith(st))
				names.add(name);
		}

		return names;
	}

	public static void registerStructures() {
		Logging.logStatusMessage("Registering structures...");

		new AbyssalArch1();
		new AbyssalArch2();
		new AbyssalLottoHut();
		new AbyssalTree1();
		new AbyssalTree2();
		new AbyssalTree3();
		new AbyssalTree4();
		new AchonyTree1();
		new AchonyTree2();
		new AmphibiyteCove();
		new AquaCottonCandyTree1();
		new AquaCottonCandyTree2();
		new AquaticCastle();
		new ArcWizardCheckpoint();
		new ArkzyneOutpost();
		new ArocknidCave();
		new BaronArch1();
		new BaronArch2();
		new BaronArch3();
		new BaronArch4();
		new BaronArch5();
		new BaronArch6();
		new BaronCastle();
		new BaronessArena();
		new BaronessHouse();
		new BaronRock1();
		new BaronRock2();
		new BaronRock3();
		new BaronRock4();
		new BaronRock5();
		new BaronRock6();
		new BaronRock7();
		new BaronRock8();
		new BloodPine();
		new BloodSpikes();
		new BlueCelevusTree();
		new BlueCrystalChunk1();
		new BlueCrystalChunk2();
		new BlueCrystalChunk3();
		new BlueCrystalChunk4();
		new BlueCrystalChunk5();
		new BlueCrystalChunk6();
		new BlueCrystalTransferHut();
		new BlueHavendales();
		new BlueHavenTree();
		new BlueMushroomTree();
		new BlueStarflower1();
		new BlueStarflower2();
		new BlueTulip();
		new BoneCircle();
		new BoneyDungeon();
		new BrightShyreTree1();
		new BrightShyreTree2();
		new BulbStock();
		new CandyCane1();
		new CandyCane2();
		new CandyCane3();
		new CandyCane4();
		new CandyLottoPlatform();
		new CandyStack();
		new CandyTube();
		new Celebulb();
		new CelevePole();
		new CelevianLottoBalloon();
		new CellTower();
		new ChargingPads();
		new ChargingStation();
		new ChocolateBar1();
		new ChocolateBar2();
		new ChocolateTree1();
		new ChocolateTree2();
		new ChurryTree1();
		new ChurryTree2();
		new ClunkheadArena();
		new CompassRuneShrine();
		new ControlTower();
		new CottonCandyTower();
		new CottonCandyTree1();
		new CottonCandyTree2();
		new CraexxeusTower();
		new CreeperHQ();
		new CreeponiaBank();
		new CreeponianLottoStand();
		new CreepTree1();
		new CreepTree2();
		new CreepTree3();
		new CreepTree4();
		new CreepTree5();
		new CreepTree6();
		new CrusiliskCave();
		new CrystalExtensionStation();
		new CrystalLottoOverlook();
		new CrystalTradingPost();
		new DarkChocolateBar1();
		new DarkChocolateBar2();
		new DawnCage1();
		new DawnCage2();
		new DawnCage3();
		new DawnlightDungeon();
		new DawnTree1();
		new DawnTree2();
		new DawnTree3();
		new DayseeFlower();
		new DeepLottoShelter();
		new Deepshroom1();
		new Deepshroom2();
		new DeepSpire1();
		new DeepSpire2();
		new DegradedLampPost();
		new DegradedSupportBeam1();
		new DegradedSupportBeam2();
		new DestroyedStore1();
		new DestroyedStore2();
		new DiocusDen();
		new DistortionRuneShrine();
		new DivinePlatform();
		new DracyonFountain();
		new DrownedLottoStand();
		new DustopianVillage();
		new EnergyRuneShrine();
		new EnforcerTower();
		new EnigmaStation();
		new ExoidPlatform();
		new ExplosivesTower();
		new EyeBulbGrotto();
		new EyeCane1();
		new EyeCane2();
		new EyeCane3();
		new EyeCane4();
		new EyeHanger1();
		new EyeHanger2();
		new EyeHanger3();
		new EyeShrub();
		new FacelessTree();
		new FireRuneShrine();
		new FleshTemple();
		new FloatingLottoFountain();
		new FloroCastle();
		new FossilisedRibs1();
		new FossilisedRibs2();
		new Fungshroom();
		new GardenCastle();
		new GardenGrass();
		new GiantDeepshroom1();
		new GiantDeepshroom2();
		new GiantDeepshroom3();
		new GingerbirdAviary();
		new GingerbreadHouse();
		new GorbVillage();
		new GrawPillar();
		new GreenCelevusTree();
		new GreenCoral1();
		new GreenCoral2();
		new GreenCrystalChunk1();
		new GreenCrystalChunk2();
		new GreenCrystalChunk3();
		new GreenCrystalChunk4();
		new GreenCrystalChunk5();
		new GreenCrystalChunk6();
		new GreenCrystalTransferHut();
		new GreenMushroomTree();
		new GreenPeppermintStack();
		new GuardianTower();
		new GyroPlatform();
		new HangingDegradedLampPost();
		new HauntedCastle();
		new HauntedLottoRock();
		new HauntedMaze();
		new HauntedTree1();
		new HauntedTree2();
		new HauntedTree3();
		new HauntedTree4();
		new HiveNest();
		new HydroPlatform();
		new IllusionTree();
		new InfestedCandyCane();
		new InvertedAchonyTree1();
		new InvertedAchonyTree2();
		new InvertedChurryTree1();
		new InvertedChurryTree2();
		new InvertedLelyetianStem();
		new InvertedLelyetianWiggler();
		new InvertedShyreStock();
		new IosaurDen();
		new IroDoubler();
		new IrodustTree1();
		new IrodustTree2();
		new IroFloater();
		new IrogoldTree1();
		new IrogoldTree2();
		new IroMaze();
		new IroPassage1();
		new IroPassage2();
		new IroPillar();
		new JaweHut();
		new JungleLottoHut();
		new KaiyuTemple();
		new KineticRuneShrine();
		new KrorPillars();
		new LelyetianStem();
		new LelyetianTower();
		new LelyetianWiggler();
		new LifeRuneShrine();
		new LightwalkerDungeon();
		new Lollypop1();
		new Lollypop2();
		new LottoCage();
		new LottoSkyFlower();
		new LucalusTree();
		new LunaradeStand();
		new LunarAtom();
		new LunarBank();
		new LunarCreationPlatform();
		new LunarFoodMarket();
		new LunarFountain();
		new LunarGarden();
		new LunarHerbalHouse();
		new LunarIsland1();
		new LunarIsland2();
		new LunarLottoPlatform();
		new LunarMaze();
		new LunarPrison();
		new LunarRuneShrine();
		new LuniciaTree1();
		new LuniciaTree2();
		new LunossoTree1();
		new LunossoTree2();
		new LuxocronDungeon();
		new MagentaTulip();
		new MarshLilly1();
		new MarshLilly2();
		new MarshLilly3();
		new MechyonTemple();
		new MerkyreTower();
		new MiniBlueMushroomTree();
		new MiniTentacles();
		new MushroomSpiderCave();
		new MysticLottoShroom();
		new MysticPortalPlatform();
		new NethengeicPit();
		new NightwingIsland();
		new ObservationTower();
		new ObserversEye();
		new OpteryxNest();
		new OrangeCoral();
		new OrangeMushroomTree();
		new ParaviteHive();
		new PinkCoral1();
		new PinkCoral2();
		new PinkCoral3();
		new PinkHavendales();
		new PinkHavenTree();
		new PoisonRuneShrine();
		new PowerRuneShrine();
		new PowerStation();
		new PrimordialShrine();
		new ProfessorsLab();
		new PurpleCelevusTree();
		new PurpleCrystalChunk1();
		new PurpleCrystalChunk2();
		new PurpleCrystalChunk3();
		new PurpleCrystalChunk4();
		new PurpleCrystalChunk5();
		new PurpleCrystalChunk6();
		new PurpleCrystalTransferHut();
		new PurpleHavenTree();
		new PurpleMushroomTree();
		new PurpleTulip();
		new RedCrystalChunk1();
		new RedCrystalChunk2();
		new RedCrystalChunk3();
		new RedCrystalChunk4();
		new RedCrystalChunk5();
		new RedCrystalChunk6();
		new RedCrystalTransferHut();
		new RedHavenTree();
		new RedPeppermintStack();
		new RoseStarflower1();
		new RoseStarflower2();
		new RoseTree();
		new RuinedTeleporterFrame();
		new RuneRandomisationStation();
		new RuneTemplarBunker();
		new RunicArena();
		new RunicTower();
		new RunicTree1();
		new RunicTree2();
		new RunicTree3();
		new RunicTree4();
		new RunicTree5();
		new RunicTree6();
		new ShadeBush1();
		new ShadeBush2();
		new ShadeBush3();
		new ShadeBush4();
		new ShadeTree1();
		new ShadeTree2();
		new ShadeTree3();
		new ShadeTree4();
		new ShadeTree5();
		new ShadowlordPlatform();
		new ShyreDecoration1();
		new ShyreDecoration2();
		new ShyreDecoration3();
		new ShyreStock();
		new ShyreTree1();
		new ShyreTree2();
		new ShyreTrollDungeon();
		new SkeletalArmyArena();
		new SmallBaronRock1();
		new SmallBaronRock2();
		new SmallBaronRock3();
		new SmallBaronRock4();
		new SoulscorneAmbush();
		new SpaceArena();
		new SpectralCage();
		new SpellbinderHouse();
		new SpinoledonDen();
		new StormRuneShrine();
		new StrangeShrine();
		new StranglewoodTree1();
		new StranglewoodTree2();
		new StrikeRuneShrine();
		new Sunflower1();
		new Sunflower2();
		new SwirlPop1();
		new SwirlPop2();
		new TechTree1();
		new TechTree2();
		new TechTree3();
		new TechTree4();
		new TentacleTree1();
		new TentacleTree2();
		new TentacleTree3();
		new TentacleTree4();
		new TentacleTree5();
		new TentacleTree6();
		new TentacleTree7();
		new TinyBlueMushroom();
		new TinyGreenMushroom();
		new TinyOrangeShroom();
		new TinyYellowShroom();
		new ToxicStem1();
		new ToxicStem2();
		new ToxicStem3();
		new ToxicStem4();
		new ToxicTentacle1();
		new ToxicTentacle2();
		new ToxicTentacle3();
		new ToxicTentacle4();
		new ToxicTentacle5();
		new ToxicTree1();
		new ToxicTree2();
		new ToxicTree3();
		new ToyTower();
		new TurquoiseHavenTree();
		new VoxBranch1();
		new VoxBranch2();
		new VoxFungi();
		new VoxLottoOutpost();
		new VoxxulonBeacon();
		new WaterRuneShrine();
		new WhiteChocolateBar1();
		new WhiteChocolateBar2();
		new WhiteCoral();
		new WhiteCrystalChunk1();
		new WhiteCrystalChunk2();
		new WhiteCrystalChunk3();
		new WhiteCrystalChunk4();
		new WhiteCrystalChunk5();
		new WhiteCrystalChunk6();
		new WhiteCrystalTransferHut();
		new WhitewashingStation();
		new WindRuneShrine();
		new WitherRuneShrine();
		new WizardFlower();
		new YellowCelevusTree();
		new YellowCoral();
		new YellowCrystalChunk1();
		new YellowCrystalChunk2();
		new YellowCrystalChunk3();
		new YellowCrystalChunk4();
		new YellowCrystalChunk5();
		new YellowCrystalChunk6();
		new YellowCrystalTransferHut();
		new YellowHavendales();
		new YellowHavenTree();
		new YellowMushroomTree();
		new ZargPlanetoid();
		new ZhinxEnclave();
	}
}
