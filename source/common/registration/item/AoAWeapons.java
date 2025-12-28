package net.tslat.aoa3.common.registration.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.tslat.aoa3.content.item.weapon.blaster.*;
import net.tslat.aoa3.content.item.weapon.bow.*;
import net.tslat.aoa3.content.item.weapon.cannon.*;
import net.tslat.aoa3.content.item.weapon.crossbow.*;
import net.tslat.aoa3.content.item.weapon.greatblade.*;
import net.tslat.aoa3.content.item.weapon.gun.*;
import net.tslat.aoa3.content.item.weapon.maul.*;
import net.tslat.aoa3.content.item.weapon.shotgun.*;
import net.tslat.aoa3.content.item.weapon.sniper.*;
import net.tslat.aoa3.content.item.weapon.sniper.Terminator;
import net.tslat.aoa3.content.item.weapon.staff.*;
import net.tslat.aoa3.content.item.weapon.sword.*;
import net.tslat.aoa3.content.item.weapon.thrown.*;
import net.tslat.aoa3.content.item.weapon.vulcane.*;
import net.tslat.aoa3.library.constant.AttackSpeed;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public final class AoAWeapons {
	public static void init() {}

	public static final DeferredItem<Item> BARON_SWORD = registerSword("baron_sword", () -> new BaronSword(AoAItemStats.BARON, AoASword.baseProperties(AoAItemStats.BARON)));
	public static final DeferredItem<Item> BLOODFURY = registerSword("bloodfury", () -> new AoASword(AoAItemStats.BLOODFURY, AoASword.baseProperties(AoAItemStats.BLOODFURY)));
	public static final DeferredItem<Item> BLOODSTONE_SWORD = registerSword("bloodstone_sword", () -> new AoASword(AoAItemStats.BLOODSTONE, AoASword.baseProperties(AoAItemStats.BLOODSTONE, AttackSpeed.SWORD)));
	public static final DeferredItem<Item> CANDLEFIRE_SWORD = registerSword("candlefire_sword", () -> new CandlefireSword(AoAItemStats.CANDLEFIRE, AoASword.baseProperties(AoAItemStats.CANDLEFIRE)));
	public static final DeferredItem<Item> CARAMEL_CARVER = registerSword("caramel_carver", () -> new CaramelCarver(AoAItemStats.CARAMEL_CARVER, AoASword.baseProperties(AoAItemStats.CARAMEL_CARVER)));
	public static final DeferredItem<Item> CORALSTORM_SWORD = registerSword("coralstorm_sword", () -> new CoralstormSword(AoAItemStats.CORALSTORM, AoASword.baseProperties(AoAItemStats.CORALSTORM)));
	public static final DeferredItem<Item> CREEPIFIED_SWORD = registerSword("creepified_sword", () -> new CreepifiedSword(AoAItemStats.CREEPIFIED, AoASword.baseProperties(AoAItemStats.CREEPIFIED)));
	public static final DeferredItem<Item> CRYSTALLITE_SWORD = registerSword("crystallite_sword", () -> new AoASword(AoAItemStats.CRYSTALLITE, AoASword.baseProperties(AoAItemStats.CRYSTALLITE)));
	public static final DeferredItem<Item> EMBERSTONE_SWORD = registerSword("emberstone_sword", () -> new EmberstoneSword(AoAItemStats.EMBERSTONE, AoASword.baseProperties(AoAItemStats.EMBERSTONE)));
	public static final DeferredItem<Item> EXPLOCHRON_SWORD = registerSword("explochron_sword", () -> new ExplochronSword(AoAItemStats.EXPLOCHRON, AoASword.baseProperties(AoAItemStats.EXPLOCHRON)));
	public static final DeferredItem<Item> FIREBORNE_SWORD = registerSword("fireborne_sword", () -> new FireborneSword(AoAItemStats.FIREBORNE, AoASword.baseProperties(AoAItemStats.FIREBORNE)));
	public static final DeferredItem<Item> GUARDIANS_SWORD = registerSword("guardians_sword", () -> new GuardiansSword(AoAItemStats.GUARDIAN, AoASword.baseProperties(AoAItemStats.GUARDIAN).component(AoADataComponents.CHARGE, 0f)));
	public static final DeferredItem<Item> HARVESTER_SWORD = registerSword("harvester_sword", () -> new HarvesterSword(AoAItemStats.HARVESTER, AoASword.baseProperties(AoAItemStats.HARVESTER)));
	public static final DeferredItem<Item> HOLY_SWORD = registerSword("holy_sword", () -> new HolySword(AoAItemStats.HOLY, AoASword.baseProperties(AoAItemStats.HOLY).rarity(Rarity.EPIC)));
	public static final DeferredItem<Item> ILLUSION_SWORD = registerSword("illusion_sword", () -> new IllusionSword(AoAItemStats.ILLUSION, AoASword.baseProperties(AoAItemStats.ILLUSION)));
	public static final DeferredItem<Item> JADE_SWORD = registerSword("jade_sword", () -> new AoASword(AoAItemStats.JADE, AoASword.baseProperties(AoAItemStats.JADE, AttackSpeed.SWORD)));
	public static final DeferredItem<Item> LEGBONE_SWORD = registerSword("legbone_sword", () -> new LegboneSword(AoAItemStats.LEGBONE, AoASword.baseProperties(AoAItemStats.LEGBONE)));
	public static final DeferredItem<Item> LIGHTS_WAY = registerSword("lights_way", () -> new AoASword(AoAItemStats.LIGHTS_WAY, AoASword.baseProperties(AoAItemStats.LIGHTS_WAY)));
	public static final DeferredItem<Item> LIMONITE_SWORD = registerSword("limonite_sword", () -> new AoASword(AoAItemStats.LIMONITE, AoASword.baseProperties(AoAItemStats.LIMONITE, AttackSpeed.SWORD)));
	public static final DeferredItem<Item> NETHENGEIC_SWORD = registerSword("nethengeic_sword", () -> new NethengeicSword(AoAItemStats.NETHENGEIC, AoASword.baseProperties(AoAItemStats.NETHENGEIC)));
	public static final DeferredItem<Item> PRIMAL_SWORD = registerSword("primal_sword", () -> new PrimalSword(AoAItemStats.PRIMAL, AoASword.baseProperties(AoAItemStats.PRIMAL)));
	public static final DeferredItem<Item> ROCKBASHER_SWORD = registerSword("rockbasher_sword", () -> new RockbasherSword(AoAItemStats.ROCKBASHER, AoASword.baseProperties(AoAItemStats.ROCKBASHER)));
	public static final DeferredItem<Item> ROCK_PICK_SWORD = registerSword("rock_pick_sword", () -> new RockPickSword(AoAItemStats.ROCK_PICK, AoASword.baseProperties(AoAItemStats.ROCK_PICK)));
	public static final DeferredItem<Item> ROSIDIAN_SWORD = registerSword("rosidian_sword", () -> new RosidianSword(AoAItemStats.ROSIDIAN, AoASword.baseProperties(AoAItemStats.ROSIDIAN)));
	public static final DeferredItem<Item> RUNIC_SWORD = registerSword("runic_sword", () -> new RunicSword(AoAItemStats.RUNIC, AoASword.baseProperties(AoAItemStats.RUNIC)));
	public static final DeferredItem<Item> SHADOW_SWORD = registerSword("shadow_sword", () -> new AoASword(AoAItemStats.SHADOW, AoASword.baseProperties(AoAItemStats.SHADOW, AttackSpeed.SWORD)));
	public static final DeferredItem<Item> SHROOMUS_SWORD = registerSword("shroomus_sword", () -> new ShroomusSword(AoAItemStats.SHROOMUS, AoASword.baseProperties(AoAItemStats.SHROOMUS)));
	public static final DeferredItem<Item> SKELETAL_SWORD = registerSword("skeletal_sword", () -> new AoASword(AoAItemStats.SKELETAL, AoASword.baseProperties(AoAItemStats.SKELETAL, 1, AttackSpeed.forAttacksPerSecond(2))));
	public static final DeferredItem<Item> SUPREMACY_SWORD = registerSword("supremacy_sword", () -> new SupremacySword(AoAItemStats.SUPREMACY, AoASword.baseProperties(AoAItemStats.SUPREMACY)));
	public static final DeferredItem<Item> SWEET_SWORD = registerSword("sweet_sword", () -> new SweetSword(AoAItemStats.SWEET, AoASword.baseProperties(AoAItemStats.SWEET)));
	public static final DeferredItem<Item> TROLL_BASHER_AXE = registerSword("troll_basher_axe", () -> new TrollBasherAxe(AoAItemStats.TROLL_BASHER, AoASword.baseProperties(AoAItemStats.TROLL_BASHER)));
	public static final DeferredItem<Item> ULTRAFLAME = registerSword("ultraflame", () -> new Ultraflame(AoAItemStats.ULTRAFLAME, AoASword.baseProperties(AoAItemStats.ULTRAFLAME)));
	public static final DeferredItem<Item> VOID_SWORD = registerSword("void_sword", () -> new VoidSword(AoAItemStats.VOID, AoASword.baseProperties(AoAItemStats.VOID)));
	public static final DeferredItem<Item> FAUNAMANCERS_BLADE = registerSword("faunamancers_blade", () -> new FaunamancersBlade(AoAItemStats.FAUNAMANCERS_BLADE, AoASword.baseProperties(AoAItemStats.FAUNAMANCERS_BLADE)));

	public static final DeferredItem<Item> BARON_GREATBLADE = registerGreatblade("baron_greatblade", () -> new BaronGreatblade(AoAItemStats.BARON_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.BARON_GREATBLADE)));
	public static final DeferredItem<Item> BLOODLURKER = registerGreatblade("bloodlurker", () -> new AoAGreatblade(AoAItemStats.BLOODLURKER_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.BLOODLURKER_GREATBLADE)));
	public static final DeferredItem<Item> CANDY_BLADE = registerGreatblade("candy_blade", () -> new CandyBlade(AoAItemStats.CANDY_BLADE_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.CANDY_BLADE_GREATBLADE)));
	public static final DeferredItem<Item> CORAL_GREATBLADE = registerGreatblade("coral_greatblade", () -> new AoAGreatblade(AoAItemStats.CORAL_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.CORAL_GREATBLADE)));
	public static final DeferredItem<Item> COTTON_CRUSHER = registerGreatblade("cotton_crusher", () -> new CottonCrusher(AoAItemStats.COTTON_CRUSHER_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.COTTON_CRUSHER_GREATBLADE)));
	public static final DeferredItem<Item> CREEPOID_GREATAXE = registerGreatblade("creepoid_greataxe", () -> new CreepoidGreatblade(AoAItemStats.CREEPOID_GREATAXE, AoAGreatblade.baseProperties(AoAItemStats.CREEPOID_GREATAXE)));
	public static final DeferredItem<Item> CRYSTAL_GREATBLADE = registerGreatblade("crystal_greatblade", () -> new CrystalGreatblade(AoAItemStats.CRYSTAL_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.CRYSTAL_GREATBLADE)));
	public static final DeferredItem<Item> EREBON_SCYTHE = registerGreatblade("erebon_scythe", () -> new ErebonScythe(AoAItemStats.EREBON_SCYTHE, AoAGreatblade.baseProperties(AoAItemStats.EREBON_SCYTHE)));
	public static final DeferredItem<Item> GODS_GREATBLADE = registerGreatblade("gods_greatblade", () -> new AoAGreatblade(AoAItemStats.GODS_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.GODS_GREATBLADE)));
	public static final DeferredItem<Item> GOOFY_GREATBLADE = registerGreatblade("goofy_greatblade", () -> new GoofyGreatblade(AoAItemStats.GOOFY_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.GOOFY_GREATBLADE)));
	public static final DeferredItem<Item> HAUNTED_GREATBLADE = registerGreatblade("haunted_greatblade", () -> new HauntedGreatblade(AoAItemStats.HAUNTED_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.HAUNTED_GREATBLADE)));
	public static final DeferredItem<Item> KNIGHTS_GUARD = registerGreatblade("knights_guard", () -> new KnightsGuard(AoAItemStats.KNIGHTS_GUARD_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.KNIGHTS_GUARD_GREATBLADE)));
	public static final DeferredItem<Item> LELYETIAN_RANSEUR = registerGreatblade("lelyetian_ranseur", () -> new LelyetianGreatblade(AoAItemStats.LELYETIAN_RANSEUR, AoAGreatblade.baseProperties(AoAItemStats.LELYETIAN_RANSEUR)));
	public static final DeferredItem<Item> LUNAR_GREATBLADE = registerGreatblade("lunar_greatblade", () -> new LunarGreatblade(AoAItemStats.LUNAR_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.LUNAR_GREATBLADE)));
	public static final DeferredItem<Item> LUXON_SCYTHE = registerGreatblade("luxon_scythe", () -> new LuxonScythe(AoAItemStats.LUXON_SCYTHE, AoAGreatblade.baseProperties(AoAItemStats.LUXON_SCYTHE)));
	public static final DeferredItem<Item> LYONIC_BARDICHE = registerGreatblade("lyonic_bardiche", () -> new AoAGreatblade(AoAItemStats.LYONIC_BARDICHE, AoAGreatblade.baseProperties(AoAItemStats.LYONIC_BARDICHE)));
	public static final DeferredItem<Item> MILLENNIUM_GREATBLADE = registerGreatblade("millennium_greatblade", () -> new MillenniumGreatblade(AoAItemStats.MILLENNIUM_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.MILLENNIUM_GREATBLADE)));
	public static final DeferredItem<Item> NOXIOUS_PARTIZAN = registerGreatblade("noxious_partizan", () -> new NoxiousGreatblade(AoAItemStats.NOXIOUS_PARTIZAN, AoAGreatblade.baseProperties(AoAItemStats.NOXIOUS_PARTIZAN)));
	public static final DeferredItem<Item> PLUTON_SCYTHE = registerGreatblade("pluton_scythe", () -> new PlutonScythe(AoAItemStats.PLUTON_SCYTHE, PlutonScythe.baseProperties(AoAItemStats.PLUTON_SCYTHE)));
	public static final DeferredItem<Item> PRIMORDIAL_GLAIVE = registerGreatblade("primordial_glaive", () -> new PrimordialGreatblade(AoAItemStats.PRIMORDIAL_GLAIVE, AoAGreatblade.baseProperties(AoAItemStats.PRIMORDIAL_GLAIVE)));
	public static final DeferredItem<Item> ROSIDIAN_GREATBLADE = registerGreatblade("rosidian_greatblade", () -> new RosidianGreatblade(AoAItemStats.ROSIDIAN_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.ROSIDIAN_GREATBLADE)));
	public static final DeferredItem<Item> ROYAL_GREATBLADE = registerGreatblade("royal_greatblade", () -> new AoAGreatblade(AoAItemStats.ROYAL_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.ROYAL_GREATBLADE)));
	public static final DeferredItem<Item> RUNIC_GREATBLADE = registerGreatblade("runic_greatblade", () -> new RunicGreatblade(AoAItemStats.RUNIC_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.RUNIC_GREATBLADE)));
	public static final DeferredItem<Item> SELYAN_SCYTHE = registerGreatblade("selyan_scythe", () -> new SelyanScythe(AoAItemStats.SELYAN_SCYTHE, AoAGreatblade.baseProperties(AoAItemStats.SELYAN_SCYTHE)));
	public static final DeferredItem<Item> SHROOMIC_BARDICHE = registerGreatblade("shroomic_bardiche", () -> new ShroomicGreatblade(AoAItemStats.SHROOMIC_BARDICHE, AoAGreatblade.baseProperties(AoAItemStats.SHROOMIC_BARDICHE)));
	public static final DeferredItem<Item> SHYRE_SWORD = registerGreatblade("shyre_sword", () -> new ShyreSword(AoAItemStats.SHYRE_SWORD_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.SHYRE_SWORD_GREATBLADE)));
	public static final DeferredItem<Item> SUBTERRANEAN_GREATBLADE = registerGreatblade("subterranean_greatblade", () -> new SubterraneanGreatblade(AoAItemStats.SUBTERRANEAN_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.SUBTERRANEAN_GREATBLADE)));
	public static final DeferredItem<Item> TIDAL_GREATBLADE = registerGreatblade("tidal_greatblade", () -> new TidalGreatblade(AoAItemStats.TIDAL_GREATBLADE, AoAGreatblade.baseProperties(AoAItemStats.TIDAL_GREATBLADE).rarity(Rarity.RARE)));
	public static final DeferredItem<Item> UNDERWORLD_GLAIVE = registerGreatblade("underworld_glaive", () -> new UnderworldGreatblade(AoAItemStats.UNDERWORLD_GLAIVE, AoAGreatblade.baseProperties(AoAItemStats.UNDERWORLD_GLAIVE)));

	public static final DeferredItem<Item> CORALSTONE_MAUL = registerMaul("coralstone_maul", () -> new AoAMaul(AoAItemStats.CORALSTONE_MAUL, AoAMaul.baseProperties(AoAItemStats.CORALSTONE_MAUL, 8.5f)));
	public static final DeferredItem<Item> CRYSTAL_MAUL = registerMaul("crystal_maul", () -> new CrystalMaul(AoAItemStats.CRYSTAL_MAUL, AoAMaul.baseProperties(AoAItemStats.CRYSTAL_MAUL, 7.2f)));
	public static final DeferredItem<Item> ELECTRON_MAUL = registerMaul("electron_maul", () -> new ElectronMaul(AoAItemStats.ELECTRON_MAUL, AoAMaul.baseProperties(AoAItemStats.ELECTRON_MAUL, 2.5f)));
	public static final DeferredItem<Item> HORIZON_MAUL = registerMaul("horizon_maul", () -> new HorizonMaul(AoAItemStats.HORIZON_MAUL, AoAMaul.baseProperties(AoAItemStats.HORIZON_MAUL, 7f)));
	public static final DeferredItem<Item> VULCAMMER_MAUL = registerMaul("vulcammer_maul", () -> new VulcammerMaul(AoAItemStats.VULCAMMER_MAUL, AoAMaul.baseProperties(AoAItemStats.VULCAMMER_MAUL, 8.2f)));

	public static final DeferredItem<Item> ABOMINATOR = registerGun("abominator", () -> new AoAGun(AoAItemStats.ABOMINATOR.durability(4720)));
	public static final DeferredItem<Item> APOCO_ASSAULT_RIFLE = registerGun("apoco_assault_rifle", () -> new ApocoAssaultRifle(AoAItemStats.APOCO_ASSAULT_RIFLE.durability(5920)));
	public static final DeferredItem<Item> APOCO_RIFLE = registerGun("apoco_rifle", () -> new ApocoRifle(AoAItemStats.APOCO_RIFLE.durability(5960)));
	public static final DeferredItem<Item> AQUA_MAGNUM = registerGun("aqua_magnum", () -> new AoAGun(AoAItemStats.AQUA_MAGNUM.durability(6080)));
	public static final DeferredItem<Item> ARTIFACT = registerGun("artifact", () -> new Artifact(AoAItemStats.ARTIFACT.durability(6000)));
	public static final DeferredItem<Item> BARONATOR = registerGun("baronator", () -> new Baronator(AoAItemStats.BARONATOR.durability(2480)));
	public static final DeferredItem<Item> BAYONETTE_RIFLE = registerGun("bayonette_rifle", () -> new BayonetteRifle(AoAItemStats.BAYONETTE_RIFLE.durability(2520).attributes(BayonetteRifle.createAttributes(0.84250003f))));
	public static final DeferredItem<Item> BIG_TOP = registerGun("big_top", () -> new AoAGun(AoAItemStats.BIG_TOP.durability(3680)));
	public static final DeferredItem<Item> BLOOD_IRON = registerGun("blood_iron", () -> new BloodIron(AoAItemStats.BLOOD_IRON.durability(3480)));
	public static final DeferredItem<Item> CHAIN_WRECKER = registerGun("chain_wrecker", () -> new AoAGun(AoAItemStats.CHAIN_WRECKER.durability(3840)));
	public static final DeferredItem<Item> CHILLI_CHUGGER = registerGun("chilli_chugger", () -> new ChilliChugger(AoAItemStats.CHILLI_CHUGGER.durability(3560)));
	public static final DeferredItem<Item> CLOWNERSHOT = registerGun("clownershot", () -> new Clownershot(AoAItemStats.CLOWNERSHOT.durability(5880)));
	public static final DeferredItem<Item> CONSTRUCT = registerGun("construct", () -> new AoAGun(AoAItemStats.CONSTRUCT.durability(3640)));
	public static final DeferredItem<Item> CORAL_CLOGGER = registerGun("coral_clogger", () -> new CoralClogger(AoAItemStats.CORAL_CLOGGER.durability(2000)));
	public static final DeferredItem<Item> CORE_RIFLE = registerGun("core_rifle", () -> new AoAGun(AoAItemStats.CORE_RIFLE.durability(2520)));
	public static final DeferredItem<Item> CRYSTAL_CARVER = registerGun("crystal_carver", () -> new CrystalCarver(AoAItemStats.CRYSTAL_CARVER.durability(3480)));
	public static final DeferredItem<Item> CYCLONE = registerGun("cyclone", () -> new Cyclone(AoAItemStats.CYCLONE.durability(4400)));
	public static final DeferredItem<Item> DARKENER = registerGun("darkener", () -> new Darkener(AoAItemStats.DARKENER.durability(6040)));
	public static final DeferredItem<Item> DART_GUN = registerGun("dart_gun", () -> new DartGun(AoAItemStats.DART_GUN.durability(4520)));
	public static final DeferredItem<Item> DESTRUCTION_RIFLE = registerGun("destruction_rifle", () -> new DestructionRifle(AoAItemStats.DESTRUCTION_RIFLE.durability(2440)));
	public static final DeferredItem<Item> DISCHARGE_RIFLE = registerGun("discharge_rifle", () -> new DischargeRifle(AoAItemStats.DISCHARGE_RIFLE.durability(2280)));
	public static final DeferredItem<Item> DRACO = registerGun("draco", () -> new Draco(AoAItemStats.DRACO.durability(5560)));
	public static final DeferredItem<Item> DRAGILATOR = registerGun("dragilator", () -> new Dragilator(AoAItemStats.DRAGILATOR.durability(4480)));
	public static final DeferredItem<Item> DUSTOMETER = registerGun("dustometer", () -> new Dustometer(AoAItemStats.DUSTOMETER.durability(4720)));
	public static final DeferredItem<Item> ECHO_GULL = registerGun("echo_gull", () -> new EchoGull(AoAItemStats.ECHO_GULL.durability(2320)));
	public static final DeferredItem<Item> ELECTINATOR = registerGun("electinator", () -> new Electinator(AoAItemStats.ELECTINATOR.durability(4480)));
	public static final DeferredItem<Item> FLAME_WRECKER = registerGun("flame_wrecker", () -> new FlameWrecker(AoAItemStats.FLAME_WRECKER.durability(6120)));
	public static final DeferredItem<Item> FLAMING_FURY = registerGun("flaming_fury", () -> new FlamingFury(AoAItemStats.FLAMING_FURY.durability(3440)));
	public static final DeferredItem<Item> FLORO_RIFLE = registerGun("floro_rifle", () -> new AoAGun(AoAItemStats.FLORO_RIFLE.durability(6000)));
	public static final DeferredItem<Item> FLOWERS_FURY = registerGun("flowers_fury", () -> new FlowersFury(AoAItemStats.FLOWERS_FURY.durability(4400)));
	public static final DeferredItem<Item> FROSTICATOR = registerGun("frosticator", () -> new Frosticator(AoAItemStats.FROSTICATOR.durability(4480)));
	public static final DeferredItem<Item> GARDENER = registerGun("gardener", () -> new Gardener(AoAItemStats.GARDENER.durability(2520).rarity(Rarity.RARE)));
	public static final DeferredItem<Item> GAUGE_RIFLE = registerGun("gauge_rifle", () -> new GaugeRifle(AoAItemStats.GAUGE_RIFLE.durability(2360)));
	public static final DeferredItem<Item> GERMINATOR = registerGun("germinator", () -> new Germinator(AoAItemStats.GERMINATOR.durability(4480)));
	public static final DeferredItem<Item> GOLDEN_FURY = registerGun("golden_fury", () -> new GoldenFury(AoAItemStats.GOLDEN_FURY.durability(6080)));
	public static final DeferredItem<Item> HAPPY_HAUNTER = registerGun("happy_haunter", () -> new AoAGun(AoAItemStats.HAPPY_HAUNTER.durability(6040)));
	public static final DeferredItem<Item> HAUNTER_RIFLE = registerGun("haunter_rifle", () -> new AoAGun(AoAItemStats.HAUNTER_RIFLE.durability(5680)));
	public static final DeferredItem<Item> HEAT_WAVE = registerGun("heat_wave", () -> new HeatWave(AoAItemStats.HEAT_WAVE.durability(5880)));
	public static final DeferredItem<Item> HIVER = registerGun("hiver", () -> new AoAGun(AoAItemStats.HIVER.durability(5960)));
	public static final DeferredItem<Item> HOT_SHOT = registerGun("hot_shot", () -> new HotShot(AoAItemStats.HOT_SHOT.durability(2320)));
	public static final DeferredItem<Item> HUNTERS_RIFLE = registerGun("hunters_rifle", () -> new AoAGun(AoAItemStats.HUNTERS_RIFLE.durability(3600)));
	public static final DeferredItem<Item> IOMINATOR = registerGun("iominator", () -> new Iominator(AoAItemStats.IOMINATOR.durability(6000)));
	public static final DeferredItem<Item> ION_REVOLVER = registerGun("ion_revolver", () -> new IonRevolver(AoAItemStats.ION_REVOLVER.durability(3720)));
	public static final DeferredItem<Item> IRO_RIFLE = registerGun("iro_rifle", () -> new AoAGun(AoAItemStats.IRO_RIFLE.durability(3640)));
	public static final DeferredItem<Item> KRILINATOR = registerGun("krilinator", () -> new Krilinator(AoAItemStats.KRILINATOR.durability(3600)));
	public static final DeferredItem<Item> LIGHT_IRON = registerGun("light_iron", () -> new LightIron(AoAItemStats.LIGHT_IRON.durability(5880)));
	public static final DeferredItem<Item> LUNAR_ASSAULT_RIFLE = registerGun("lunar_assault_rifle", () -> new LunarAssaultRifle(AoAItemStats.LUNAR_ASSAULT_RIFLE.durability(5720)));
	public static final DeferredItem<Item> MECHANICAL_ASSAULT_RIFLE = registerGun("mechanical_assault_rifle", () -> new AoAGun(AoAItemStats.MECHANICAL_ASSAULT_RIFLE.durability(3760)));
	public static final DeferredItem<Item> MEGAGUN = registerGun("megagun", () -> new Megagun(AoAItemStats.MEGAGUN.durability(6120)));
	public static final DeferredItem<Item> MIASMA = registerGun("miasma", () -> new Miasma(AoAItemStats.MIASMA.durability(3760)));
	public static final DeferredItem<Item> MINIGUN = registerGun("minigun", () -> new Minigun(AoAItemStats.MINIGUN.durability(2680)));
	public static final DeferredItem<Item> MINT_MAGNUM = registerGun("mint_magnum", () -> new AoAGun(AoAItemStats.MINT_MAGNUM.durability(3600)));
	public static final DeferredItem<Item> MK = registerGun("mk", () -> new AoAGun(AoAItemStats.MK.durability(4960)));
	public static final DeferredItem<Item> MK_FUNG = registerGun("mk_fung", () -> new MKFung(AoAItemStats.MK_FUNG.durability(5960)));
	public static final DeferredItem<Item> NETHENETTE_RIFLE = registerGun("nethenette_rifle", () -> new NethenetteRifle(AoAItemStats.NETHENETTE_RIFLE.durability(6000).attributes(NethenetteRifle.createAttributes(0.8629f))));
	public static final DeferredItem<Item> NETHENGEIC_SLUGGER = registerGun("nethengeic_slugger", () -> new NethengeicSlugger(AoAItemStats.NETHENGEIC_SLUGGER.durability(3120)));
	public static final DeferredItem<Item> OVERSHOT = registerGun("overshot", () -> new Overshot(AoAItemStats.OVERSHOT.durability(3960)));
	public static final DeferredItem<Item> PRECASIAN_SLUGGER = registerGun("precasian_slugger", () -> new PrecasianSlugger(AoAItemStats.PRECASIAN_SLUGGER.durability(5960)));
	public static final DeferredItem<Item> PREDATOR = registerGun("predator", () -> new Predator(AoAItemStats.PREDATOR.durability(5920)));
	public static final DeferredItem<Item> PREDIGUN = registerGun("predigun", () -> new Predigun(AoAItemStats.PREDIGUN.durability(6120)));
	public static final DeferredItem<Item> PULSATOR = registerGun("pulsator", () -> new Pulsator(AoAItemStats.PULSATOR.durability(4760)));
	public static final DeferredItem<Item> PURITY_RIFLE = registerGun("purity_rifle", () -> new PurityRifle(AoAItemStats.PURITY_RIFLE.durability(4800)));
	public static final DeferredItem<Item> ROCKER_RIFLE = registerGun("rocker_rifle", () -> new RockerRifle(AoAItemStats.ROCKER_RIFLE.durability(2520)));
	public static final DeferredItem<Item> ROULETTE = registerGun("roulette", () -> new AoAGun(AoAItemStats.ROULETTE.durability(5880)));
	public static final DeferredItem<Item> SHOE_FLINGER = registerGun("shoe_flinger", () -> new ShoeFlinger(AoAItemStats.SHOE_FLINGER.durability(1600)));
	public static final DeferredItem<Item> SKULLETTE = registerGun("skullette", () -> new AoAGun(AoAItemStats.SKULLETTE.durability(6320)));
	public static final DeferredItem<Item> SKULLIFACT = registerGun("skullifact", () -> new Skullifact(AoAItemStats.SKULLIFACT.durability(6280)));
	public static final DeferredItem<Item> SPECTACLE = registerGun("spectacle", () -> new Spectacle(AoAItemStats.SPECTACLE.durability(3520)));
	public static final DeferredItem<Item> SPINE_GUN = registerGun("spine_gun", () -> new AoAGun(AoAItemStats.SPINE_GUN.durability(6040)));
	public static final DeferredItem<Item> SQUAD_GUN = registerGun("squad_gun", () -> new AoAGun(AoAItemStats.SQUAD_GUN.durability(4840)));
	public static final DeferredItem<Item> STAMPEDE = registerGun("stampede", () -> new AoAGun(AoAItemStats.STAMPEDE.durability(1200)));
	public static final DeferredItem<Item> STORMER = registerGun("stormer", () -> new Stormer(AoAItemStats.STORMER.durability(3600)));
	public static final DeferredItem<Item> SUBLIMUS = registerGun("sublimus", () -> new Sublimus(AoAItemStats.SUBLIMUS.durability(6120)));
	public static final DeferredItem<Item> TIGER_TOMMY = registerGun("tiger_tommy", () -> new AoAGun(AoAItemStats.TIGER_TOMMY.durability(5960)));
	public static final DeferredItem<Item> TOMMY = registerGun("tommy", () -> new AoAGun(AoAItemStats.TOMMY.durability(1320)));
	public static final DeferredItem<Item> VILE_VANQUISHER = registerGun("vile_vanquisher", () -> new VileVanquisher(AoAItemStats.VILE_VANQUISHER.durability(3320)));
	public static final DeferredItem<Item> WART_GUN = registerGun("wart_gun", () -> new WartGun(AoAItemStats.WART_GUN.durability(2320)));
	public static final DeferredItem<Item> WRECKER = registerGun("wrecker", () -> new Wrecker(AoAItemStats.WRECKER.durability(3680)));

	public static final DeferredItem<Item> ABYSSRO = registerShotgun("abyssro", () -> new AoAShotgun(AoAItemStats.ABYSSRO.durability(1100)));
	public static final DeferredItem<Item> AMPLIFIER = registerShotgun("amplifier", () -> new Amplifier(AoAItemStats.AMPLIFIER.durability(1100)));
	public static final DeferredItem<Item> BLAST_BARREL = registerShotgun("blast_barrel", () -> new BlastBarrel(AoAItemStats.BLAST_BARREL.durability(740)));
	public static final DeferredItem<Item> BLUE_BARREL = registerShotgun("blue_barrel", () -> new AoAShotgun(AoAItemStats.BLUE_BARREL.durability(1120)));
	public static final DeferredItem<Item> BOULDER = registerShotgun("boulder", () -> new AoAShotgun(AoAItemStats.BOULDER.durability(1080)));
	public static final DeferredItem<Item> BROWN_BLASTER = registerShotgun("brown_blaster", () -> new AoAShotgun(AoAItemStats.BROWN_BLASTER.durability(300)));
	public static final DeferredItem<Item> DEMOLISHER = registerShotgun("demolisher", () -> new AoAShotgun(AoAItemStats.DEMOLISHER.durability(730)));
	public static final DeferredItem<Item> DESTRUCTION_SHOTGUN = registerShotgun("destruction_shotgun", () -> new DestructionShotgun(AoAItemStats.DESTRUCTION_SHOTGUN.durability(690)));
	public static final DeferredItem<Item> DISCHARGE_SHOTGUN = registerShotgun("discharge_shotgun", () -> new DischargeShotgun(AoAItemStats.DISCHARGE_SHOTGUN.durability(160)));
	public static final DeferredItem<Item> GIMMICK = registerShotgun("gimmick", () -> new AoAShotgun(AoAItemStats.GIMMICK.durability(800)));
	public static final DeferredItem<Item> GINGER_BLASTER = registerShotgun("ginger_blaster", () -> new AoAShotgun(AoAItemStats.GINGER_BLASTER.durability(720)));
	public static final DeferredItem<Item> LONG_SHOT = registerShotgun("long_shot", () -> new LongShot(AoAItemStats.LONG_SHOT.durability(730)));
	public static final DeferredItem<Item> MECHYRO = registerShotgun("mechyro", () -> new Mechyro(AoAItemStats.MECHYRO.durability(720)));
	public static final DeferredItem<Item> PURITY_SHOTGUN = registerShotgun("purity_shotgun", () -> new PurityShotgun(AoAItemStats.PURITY_SHOTGUN.durability(1090)));
	public static final DeferredItem<Item> PURPLE_PUNISHER = registerShotgun("purple_punisher", () -> new AoAShotgun(AoAItemStats.PURPLE_PUNISHER.durability(1120)));
	public static final DeferredItem<Item> RED_ROCKET = registerShotgun("red_rocket", () -> new AoAShotgun(AoAItemStats.RED_ROCKET.durability(500)));
	public static final DeferredItem<Item> VIVO = registerShotgun("vivo", () -> new Vivo(AoAItemStats.VIVO.durability(710)));

	public static final DeferredItem<Item> BARON_SSR = registerSniper("baron_ssr", () -> new BaronSSR(AoAItemStats.BARON_SSR.durability(240)));
	public static final DeferredItem<Item> BAYONETTE_SR = registerSniper("bayonette_sr", () -> new BayonetteSR(AoAItemStats.BAYONETTE_SR.durability(300).attributes(BayonetteSR.createAttributes(0.82372093f))));
	public static final DeferredItem<Item> BOLT_RIFLE = registerSniper("bolt_rifle", () -> new AoASniper(AoAItemStats.BOLT_RIFLE.durability(100)));
	public static final DeferredItem<Item> CAMO_RIFLE = registerSniper("camo_rifle", () -> new CamoRifle(AoAItemStats.CAMO_RIFLE.durability(150)));
	public static final DeferredItem<Item> CLOWN_CRACKER = registerSniper("clown_cracker", () -> new ClownCracker(AoAItemStats.CLOWN_CRACKER.durability(305)));
	public static final DeferredItem<Item> CLOWNIMATOR = registerSniper("clownimator", () -> new Clownimator(AoAItemStats.CLOWNIMATOR.durability(545)));
	public static final DeferredItem<Item> CRYSTANEER = registerSniper("crystaneer", () -> new Crystaneer(AoAItemStats.CRYSTANEER.durability(345)));
	public static final DeferredItem<Item> DARK_BEAST = registerSniper("dark_beast", () -> new DarkBeast(AoAItemStats.DARK_BEAST.durability(560)));
	public static final DeferredItem<Item> DEADLOCK = registerSniper("deadlock", () -> new Deadlock(AoAItemStats.DEADLOCK.durability(120)));
	public static final DeferredItem<Item> DECIMATOR = registerSniper("decimator", () -> new Decimator(AoAItemStats.DECIMATOR.durability(180)));
	public static final DeferredItem<Item> DISCHARGE_SNIPER = registerSniper("discharge_sniper", () -> new DischargeSniper(AoAItemStats.DISCHARGE_SNIPER.durability(540)));
	public static final DeferredItem<Item> DUAL_SIGHT = registerSniper("dual_sight", () -> new DualSight(AoAItemStats.DUAL_SIGHT.durability(350)));
	public static final DeferredItem<Item> DUSTER = registerSniper("duster", () -> new AoASniper(AoAItemStats.DUSTER.durability(170)));
	public static final DeferredItem<Item> FLORO500 = registerSniper("floro500", () -> new Floro500(AoAItemStats.FLORO500.durability(550)));
	public static final DeferredItem<Item> HEAD_HUNTER = registerSniper("head_hunter", () -> new HeadHunter(AoAItemStats.HEAD_HUNTER.durability(380)));
	public static final DeferredItem<Item> HIVE_CRACKER = registerSniper("hive_cracker", () -> new HiveCracker(AoAItemStats.HIVE_CRACKER.durability(555)));
	public static final DeferredItem<Item> KA_500 = registerSniper("ka_500", () -> new Ka500(AoAItemStats.KA_500.durability(360)));
	public static final DeferredItem<Item> MARK_MAKER = registerSniper("mark_maker", () -> new MarkMaker(AoAItemStats.MARK_MAKER.durability(530)));
	public static final DeferredItem<Item> MINERAL = registerSniper("mineral", () -> new AoASniper(AoAItemStats.MINERAL.durability(560)));
	public static final DeferredItem<Item> MONSTER = registerSniper("monster", () -> new Monster(AoAItemStats.MONSTER.durability(250)));
	public static final DeferredItem<Item> MOON_MAKER = registerSniper("moon_maker", () -> new MoonMaker(AoAItemStats.MOON_MAKER.durability(570)));
	public static final DeferredItem<Item> ROSID_RIFLE = registerSniper("rosid_rifle", () -> new RosidRifle(AoAItemStats.ROSID_RIFLE.durability(405)));
	public static final DeferredItem<Item> SABBATH = registerSniper("sabbath", () -> new Sabbath(AoAItemStats.SABBATH.durability(450)));
	public static final DeferredItem<Item> SLUDGE_SNIPER = registerSniper("sludge_sniper", () -> new SludgeSniper(AoAItemStats.SLUDGE_SNIPER.durability(350)));
	public static final DeferredItem<Item> SWEET_TOOTH = registerSniper("sweet_tooth", () -> new SweetTooth(AoAItemStats.SWEET_TOOTH.durability(460)));
	public static final DeferredItem<Item> TERMINATOR = registerSniper("terminator", () -> new Terminator(AoAItemStats.TERMINATOR.durability(470)));
	public static final DeferredItem<Item> VIPER_1 = registerSniper("viper_1", () -> new Viper1(AoAItemStats.VIPER_1.durability(185)));

	public static final DeferredItem<Item> ANCIENT_BOMBER = registerCannon("ancient_bomber", () -> new AncientBomber(AoAItemStats.ANCIENT_BOMBER.durability(855)));
	public static final DeferredItem<Item> ANCIENT_DISCHARGER = registerCannon("ancient_discharger", () -> new AncientDischarger(AoAItemStats.ANCIENT_DISCHARGER.durability(850)));
	public static final DeferredItem<Item> AQUA_CANNON = registerCannon("aqua_cannon", () -> new AquaCannon(AoAItemStats.AQUA_CANNON.durability(300)));
	public static final DeferredItem<Item> BALLOON_BOMBER = registerCannon("balloon_bomber", () -> new BalloonBomber(AoAItemStats.BALLOON_BOMBER.durability(505)));
	public static final DeferredItem<Item> BIG_BLAST = registerCannon("big_blast", () -> new BigBlast(AoAItemStats.BIG_BLAST.durability(550)));
	public static final DeferredItem<Item> BLAST_CANNON = registerCannon("blast_cannon", () -> new BlastCannon(AoAItemStats.BLAST_CANNON.durability(510)));
	public static final DeferredItem<Item> BLISSFUL_BLAST = registerCannon("blissful_blast", () -> new BlissfulBlast(AoAItemStats.BLISSFUL_BLAST.durability(835)));
	public static final DeferredItem<Item> BOMB_LAUNCHER = registerCannon("bomb_launcher", () -> new BombLauncher(AoAItemStats.BOMB_LAUNCHER.durability(840)));
	public static final DeferredItem<Item> BOOM_BOOM = registerCannon("boom_boom", () -> new BoomBoom(AoAItemStats.BOOM_BOOM.durability(390)));
	public static final DeferredItem<Item> BOOM_CANNON = registerCannon("boom_cannon", () -> new BoomCannon(AoAItemStats.BOOM_CANNON.durability(510)));
	public static final DeferredItem<Item> BOULDER_BOMBER = registerCannon("boulder_bomber", () -> new BoulderBomber(AoAItemStats.BOULDER_BOMBER.durability(475)));
	public static final DeferredItem<Item> BOZO_BLASTER = registerCannon("bozo_blaster", () -> new BozoBlaster(AoAItemStats.BOZO_BLASTER.durability(505)));
	public static final DeferredItem<Item> BULB_CANNON = registerCannon("bulb_cannon", () -> new BulbCannon(AoAItemStats.BULB_CANNON.durability(860)));
	public static final DeferredItem<Item> CARROT_CANNON = registerCannon("carrot_cannon", () -> new CarrotCannon(AoAItemStats.CARROT_CANNON.durability(520)));
	public static final DeferredItem<Item> CLOWN_CANNON = registerCannon("clown_cannon", () -> new ClownCannon(AoAItemStats.CLOWN_CANNON.durability(570)));
	public static final DeferredItem<Item> CLOWNO_PULSE = registerCannon("clowno_pulse", () -> new ClownoPulse(AoAItemStats.CLOWNO_PULSE.durability(845)));
	public static final DeferredItem<Item> CORAL_CANNON = registerCannon("coral_cannon", () -> new CoralCannon(AoAItemStats.CORAL_CANNON.durability(300)));
	public static final DeferredItem<Item> DISCHARGE_CANNON = registerCannon("discharge_cannon", () -> new DischargeCannon(AoAItemStats.DISCHARGE_CANNON.durability(400)));
	public static final DeferredItem<Item> ENERGY_CANNON = registerCannon("energy_cannon", () -> new EnergyCannon(AoAItemStats.ENERGY_CANNON.durability(610)));
	public static final DeferredItem<Item> EREBON_STICKLER = registerCannon("erebon_stickler", () -> new ErebonStickler(AoAItemStats.EREBON_STICKLER.durability(750)));
	public static final DeferredItem<Item> FLORO_RPG = registerCannon("floro_rpg", () -> new FloroRPG(AoAItemStats.FLORO_RPG.durability(830)));
	public static final DeferredItem<Item> FLOWER_CANNON = registerCannon("flower_cannon", () -> new FlowerCannon(AoAItemStats.FLOWER_CANNON.durability(510)));
	public static final DeferredItem<Item> FUNGAL_CANNON = registerCannon("fungal_cannon", () -> new FungalCannon(AoAItemStats.FUNGAL_CANNON.durability(850)));
	public static final DeferredItem<Item> GHAST_BLASTER = registerCannon("ghast_blaster", () -> new GhastBlaster(AoAItemStats.GHAST_BLASTER.durability(600)));
	public static final DeferredItem<Item> GHOUL_CANNON = registerCannon("ghoul_cannon", () -> new GhoulCannon(AoAItemStats.GHOUL_CANNON.durability(590)));
	public static final DeferredItem<Item> GIGA_CANNON = registerCannon("giga_cannon", () -> new GigaCannon(AoAItemStats.GIGA_CANNON.durability(700)));
	public static final DeferredItem<Item> GOLDER_BOMBER = registerCannon("golder_bomber", () -> new GolderBomber(AoAItemStats.GOLDER_BOMBER.durability(840)));
	public static final DeferredItem<Item> HIVE_BLASTER = registerCannon("hive_blaster", () -> new HiveBlaster(AoAItemStats.HIVE_BLASTER.durability(850)));
	public static final DeferredItem<Item> HIVE_HOWITZER = registerCannon("hive_howitzer", () -> new HiveHowitzer(AoAItemStats.HIVE_HOWITZER.durability(375)));
	public static final DeferredItem<Item> IRO_CANNON = registerCannon("iro_cannon", () -> new IroCannon(AoAItemStats.IRO_CANNON.durability(580)));
	public static final DeferredItem<Item> JACK_FUNGER = registerCannon("jack_funger", () -> new JackFunger(AoAItemStats.JACK_FUNGER.durability(860)));
	public static final DeferredItem<Item> JACK_ROCKER = registerCannon("jack_rocker", () -> new JackRocker(AoAItemStats.JACK_ROCKER.durability(400)));
	public static final DeferredItem<Item> LUXON_STICKLER = registerCannon("luxon_stickler", () -> new LuxonStickler(AoAItemStats.LUXON_STICKLER.durability(750)));
	public static final DeferredItem<Item> MECHA_CANNON = registerCannon("mecha_cannon", () -> new MechaCannon(AoAItemStats.MECHA_CANNON.durability(525)));
	public static final DeferredItem<Item> MINI_CANNON = registerCannon("mini_cannon", () -> new MiniCannon(AoAItemStats.MINI_CANNON.durability(415)));
	public static final DeferredItem<Item> MISSILE_MAKER = registerCannon("missile_maker", () -> new MissileMaker(AoAItemStats.MISSILE_MAKER.durability(495)));
	public static final DeferredItem<Item> MOON_CANNON = registerCannon("moon_cannon", () -> new MoonCannon(AoAItemStats.MOON_CANNON.durability(855)));
	public static final DeferredItem<Item> PLUTON_STICKLER = registerCannon("pluton_stickler", () -> new PlutonStickler(AoAItemStats.PLUTON_STICKLER.durability(750)));
	public static final DeferredItem<Item> PREDATORIAN_BLASTER = registerCannon("predatorian_blaster", () -> new PredatorianBlaster(AoAItemStats.PREDATORIAN_BLASTER.durability(845)));
	public static final DeferredItem<Item> PULSE_CANNON = registerCannon("pulse_cannon", () -> new PulseCannon(AoAItemStats.PULSE_CANNON.durability(510)));
	public static final DeferredItem<Item> RPG = registerCannon("rpg", () -> new RPG(AoAItemStats.RPG.durability(320)));
	public static final DeferredItem<Item> SELYAN_STICKLER = registerCannon("selyan_stickler", () -> new SelyanStickler(AoAItemStats.SELYAN_STICKLER.durability(750)));
	public static final DeferredItem<Item> SHADOW_BLASTER = registerCannon("shadow_blaster", () -> new ShadowBlaster(AoAItemStats.SHADOW_BLASTER.durability(515)));
	public static final DeferredItem<Item> SHYRE_BLASTER = registerCannon("shyre_blaster", () -> new AoAGun(AoAItemStats.SHYRE_BLASTER.durability(850)));
	public static final DeferredItem<Item> SMILE_BLASTER = registerCannon("smile_blaster", () -> new SmileBlaster(AoAItemStats.SMILE_BLASTER.durability(840)));
	public static final DeferredItem<Item> SUPER_CANNON = registerCannon("super_cannon", () -> new SuperCannon(AoAItemStats.SUPER_CANNON.durability(510)));
	public static final DeferredItem<Item> ULTRA_CANNON = registerCannon("ultra_cannon", () -> new UltraCannon(AoAItemStats.ULTRA_CANNON.durability(605)));
	public static final DeferredItem<Item> VOX_CANNON = registerCannon("vox_cannon", () -> new VoxCannon(AoAItemStats.VOX_CANNON.durability(440)));
	public static final DeferredItem<Item> WATER_BALLOON_BOMBER = registerCannon("water_balloon_bomber", () -> new WaterBalloonBomber(AoAItemStats.WATER_BALLOON_BOMBER.durability(855)));
	public static final DeferredItem<Item> WITHER_CANNON = registerCannon("wither_cannon", () -> new WitherCannon(AoAItemStats.WITHER_CANNON.durability(460)));

	public static final DeferredItem<Grenade> GRENADE = registerThrownWeapon("grenade", () -> new Grenade(AoAItemStats.GRENADE.stacksTo(64)));
	public static final DeferredItem<SliceStar> SLICE_STAR = registerThrownWeapon("slice_star", () -> new SliceStar(AoAItemStats.SLICE_STAR.stacksTo(64)));
	public static final DeferredItem<Chakram> CHAKRAM = registerThrownWeapon("chakram", () -> new Chakram(AoAItemStats.CHAKRAM.stacksTo(64)));
	public static final DeferredItem<GooBall> GOO_BALL = registerThrownWeapon("goo_ball", () -> new GooBall(AoAItemStats.GOO_BALL.stacksTo(64)));
	public static final DeferredItem<Vulkram> VULKRAM = registerThrownWeapon("vulkram", () -> new Vulkram(AoAItemStats.VULKRAM.stacksTo(64)));
	public static final DeferredItem<Hellfire> HELLFIRE = registerThrownWeapon("hellfire", () -> new Hellfire(AoAItemStats.HELLFIRE.stacksTo(64)));
	public static final DeferredItem<RunicBomb> RUNIC_BOMB = registerThrownWeapon("runic_bomb", () -> new RunicBomb(AoAItemStats.RUNIC_BOMB.stacksTo(64)));
	public static final DeferredItem<HardenedParapiranha> HARDENED_PARAPIRANHA = registerThrownWeapon("hardened_parapiranha", () -> new HardenedParapiranha(AoAItemStats.HARDENED_PARAPIRANHA.stacksTo(64)));

	public static final DeferredItem<Item> VULCANE = registerVulcane("vulcane", () -> new Vulcane(AoAItemStats.VULCANE.durability(50)));
	public static final DeferredItem<Item> BATTLE_VULCANE = registerVulcane("battle_vulcane", () -> new BattleVulcane(AoAItemStats.BATTLE_VULCANE.durability(75)));
	public static final DeferredItem<Item> EQUALITY_VULCANE = registerVulcane("equality_vulcane", () -> new EqualityVulcane(AoAItemStats.EQUALITY_VULCANE.durability(75)));
	public static final DeferredItem<Item> FIRE_VULCANE = registerVulcane("fire_vulcane", () -> new FireVulcane(AoAItemStats.FIRE_VULCANE.durability(75)));
	public static final DeferredItem<Item> IMPAIRMENT_VULCANE = registerVulcane("impairment_vulcane", () -> new ImpairmentVulcane(AoAItemStats.IMPAIRMENT_VULCANE.durability(75)));
	public static final DeferredItem<Item> POISON_VULCANE = registerVulcane("poison_vulcane", () -> new PoisonVulcane(AoAItemStats.POISON_VULCANE.durability(75)));
	public static final DeferredItem<Item> POWER_VULCANE = registerVulcane("power_vulcane", () -> new PowerVulcane(AoAItemStats.POWER_VULCANE.durability(75)));
	public static final DeferredItem<Item> WITHER_VULCANE = registerVulcane("wither_vulcane", () -> new WitherVulcane(AoAItemStats.WITHER_VULCANE.durability(75)));
	
	public static final DeferredItem<Item> ALACRITY_BOW = registerBow("alacrity_bow", () -> new AoABow(AoAItemStats.ALACRITY_BOW.durability(600)));
	public static final DeferredItem<Item> ANCIENT_BOW = registerBow("ancient_bow", () -> new AoABow(AoAItemStats.ANCIENT_BOW.durability(1510)));
	public static final DeferredItem<Item> ATLANTIC_BOW = registerBow("atlantic_bow", () -> new AoABow(AoAItemStats.ATLANTIC_BOW.durability(1480)));
	public static final DeferredItem<Item> BARON_BOW = registerBow("baron_bow", () -> new BaronBow(AoAItemStats.BARON_BOW.durability(3000)));
	public static final DeferredItem<Item> BOREIC_BOW = registerBow("boreic_bow", () -> new BoreicBow(AoAItemStats.BOREIC_BOW.durability(1190)));
	public static final DeferredItem<Item> DAYBREAKER_BOW = registerBow("daybreaker_bow", () -> new DaybreakerBow(AoAItemStats.DAYBREAKER_BOW.durability(1180)));
	public static final DeferredItem<Item> DEEP_BOW = registerBow("deep_bow", () -> new DeepBow(AoAItemStats.DEEP_BOW.durability(700)));
	public static final DeferredItem<Item> EXPLOSIVE_BOW = registerBow("explosive_bow", () -> new ExplosiveBow(AoAItemStats.EXPLOSIVE_BOW.durability(900)));
	public static final DeferredItem<Item> HAUNTED_BOW = registerBow("haunted_bow", () -> new HauntedBow(AoAItemStats.HAUNTED_BOW.durability(920)));
	public static final DeferredItem<Item> ICE_BOW = registerBow("ice_bow", () -> new IceBow(AoAItemStats.ICE_BOW.durability(580)));
	public static final DeferredItem<Item> INFERNAL_BOW = registerBow("infernal_bow", () -> new InfernalBow(AoAItemStats.INFERNAL_BOW.durability(710)));
	public static final DeferredItem<Item> JUSTICE_BOW = registerBow("justice_bow", () -> new JusticeBow(AoAItemStats.JUSTICE_BOW.durability(920)));
	public static final DeferredItem<Item> LUNAR_BOW = registerBow("lunar_bow", () -> new LunarBow(AoAItemStats.LUNAR_BOW.durability(900)));
	public static final DeferredItem<Item> MECHA_BOW = registerBow("mecha_bow", () -> new AoABow(AoAItemStats.MECHA_BOW.durability(930)));
	public static final DeferredItem<Item> NIGHTMARE_BOW = registerBow("nightmare_bow", () -> new NightmareBow(AoAItemStats.NIGHTMARE_BOW.durability(890)));
	public static final DeferredItem<Item> POISON_BOW = registerBow("poison_bow", () -> new PoisonBow(AoAItemStats.POISON_BOW.durability(950)));
	public static final DeferredItem<Item> PREDATIOUS_BOW = registerBow("predatious_bow", () -> new AoABow(AoAItemStats.PREDATIOUS_BOW.durability(690)));
	public static final DeferredItem<Item> PRIMORDIAL_BOW = registerBow("primordial_bow", () -> new PrimordialBow(AoAItemStats.PRIMORDIAL_BOW.durability(1350)));
	public static final DeferredItem<Item> ROSIDIAN_BOW = registerBow("rosidian_bow", () -> new RosidianBow(AoAItemStats.ROSIDIAN_BOW.durability(900)));
	public static final DeferredItem<Item> RUNIC_BOW = registerBow("runic_bow", () -> new RunicBow(AoAItemStats.RUNIC_BOW.durability(1320)));
	public static final DeferredItem<Item> SCREAMER_BOW = registerBow("screamer_bow", () -> new ScreamerBow(AoAItemStats.SCREAMER_BOW.durability(665)));
	public static final DeferredItem<Item> SHYREGEM_BOW = registerBow("shyregem_bow", () -> new ShyregemBow(AoAItemStats.SHYREGEM_BOW.durability(1500)));
	public static final DeferredItem<Item> SKELETAL_BOW = registerBow("skeletal_bow", () -> new AoABow(AoAItemStats.SKELETAL_BOW.durability(720)));
	public static final DeferredItem<Item> SKYDRIVER_BOW = registerBow("skydriver_bow", () -> new SkydriverBow(AoAItemStats.SKYDRIVER_BOW.durability(850)));
	public static final DeferredItem<Item> SLINGSHOT = registerBow("slingshot", () -> new Slingshot(AoAItemStats.SLINGSHOT.durability(1200)));
	public static final DeferredItem<Item> SOULFIRE_BOW = registerBow("soulfire_bow", () -> new SoulfireBow(AoAItemStats.SOULFIRE_BOW.durability(1100)));
	public static final DeferredItem<Item> SPECTRAL_BOW = registerBow("spectral_bow", () -> new SpectralBow(AoAItemStats.SPECTRAL_BOW.durability(900)));
	public static final DeferredItem<Item> SPEED_BOW = registerBow("speed_bow", () -> new AoABow(AoAItemStats.SPEED_BOW.durability(930)));
	public static final DeferredItem<Item> SUNSHINE_BOW = registerBow("sunshine_bow", () -> new SunshineBow(AoAItemStats.SUNSHINE_BOW.durability(1530)));
	public static final DeferredItem<Item> TOXIN_BOW = registerBow("toxin_bow", () -> new ToxinBow(AoAItemStats.TOXIN_BOW.durability(900)));
	public static final DeferredItem<Item> VOID_BOW = registerBow("void_bow", () -> new AoABow(AoAItemStats.VOID_BOW.durability(600)));
	public static final DeferredItem<Item> WEAKEN_BOW = registerBow("weaken_bow", () -> new WeakenBow(AoAItemStats.WEAKEN_BOW.durability(700)));
	public static final DeferredItem<Item> WITHER_BOW = registerBow("wither_bow", () -> new WitherBow(AoAItemStats.WITHER_BOW.durability(835)));
	
	public static final DeferredItem<Item> CORAL_CROSSBOW = registerCrossbow("coral_crossbow", () -> new CoralCrossbow(AoAItemStats.CORAL_CROSSBOW.durability(1080)));
	public static final DeferredItem<Item> LUNAR_CROSSBOW = registerCrossbow("lunar_crossbow", () -> new LunarCrossbow(AoAItemStats.LUNAR_CROSSBOW.durability(1100)));
	public static final DeferredItem<Item> MECHA_CROSSBOW = registerCrossbow("mecha_crossbow", () -> new AoACrossbow(AoAItemStats.MECHA_CROSSBOW.durability(930)));
	public static final DeferredItem<Item> PYRO_CROSSBOW = registerCrossbow("pyro_crossbow", () -> new PyroCrossbow(AoAItemStats.PYRO_CROSSBOW.durability(800)));
	public static final DeferredItem<Item> ROSIDIAN_CROSSBOW = registerCrossbow("rosidian_crossbow", () -> new RosidianCrossbow(AoAItemStats.ROSIDIAN_CROSSBOW.durability(900)));
	public static final DeferredItem<Item> SKELETAL_CROSSBOW = registerCrossbow("skeletal_crossbow", () -> new AoACrossbow(AoAItemStats.SKELETAL_CROSSBOW.durability(860)));
	public static final DeferredItem<Item> SPECTRAL_CROSSBOW = registerCrossbow("spectral_crossbow", () -> new SpectralCrossbow(AoAItemStats.SPECTRAL_CROSSBOW.durability(1030)));
	public static final DeferredItem<Item> TROLLS_CROSSBOW = registerCrossbow("trolls_crossbow", () -> new AoACrossbow(AoAItemStats.TROLLS_CROSSBOW.durability(700)));
	public static final DeferredItem<Item> VIRAL_CROSSBOW = registerCrossbow("viral_crossbow", () -> new ViralCrossbow(AoAItemStats.VIRAL_CROSSBOW.durability(780)));

	public static final DeferredItem<Item> AMBER_STAFF = registerStaff("amber_staff", () -> new AmberStaff(AoAItemStats.AMBER_STAFF.durability(900)));
	public static final DeferredItem<Item> AQUATIC_STAFF = registerStaff("aquatic_staff", () -> new AquaticStaff(AoAItemStats.AQUATIC_STAFF.durability(1220)));
	public static final DeferredItem<Item> ATLANTIC_STAFF = registerStaff("atlantic_staff", () -> new AtlanticStaff(AoAItemStats.ATLANTIC_STAFF.durability(1250)));
	public static final DeferredItem<Item> BARON_STAFF = registerStaff("baron_staff", () -> new BaronStaff(AoAItemStats.BARON_STAFF.durability(1200)));
	public static final DeferredItem<Item> CANDY_STAFF = registerStaff("candy_staff", () -> new CandyStaff(AoAItemStats.CANDY_STAFF.durability(950)));
	public static final DeferredItem<Item> CELESTIAL_STAFF = registerStaff("celestial_staff", () -> new CelestialStaff(AoAItemStats.CELESTIAL_STAFF.durability(1150)));
	public static final DeferredItem<Item> CONCUSSION_STAFF = registerStaff("concussion_staff", () -> new ConcussionStaff(AoAItemStats.CONCUSSION_STAFF.durability(1150)));
	public static final DeferredItem<Item> CORAL_STAFF = registerStaff("coral_staff", () -> new CoralStaff(AoAItemStats.CORAL_STAFF.durability(950)));
	public static final DeferredItem<Item> CRYSTAL_STAFF = registerStaff("crystal_staff", () -> new CrystalStaff(AoAItemStats.CRYSTAL_STAFF.durability(1230)));
	public static final DeferredItem<Item> CRYSTIK_STAFF = registerStaff("crystik_staff", () -> new CrystikStaff(AoAItemStats.CRYSTIK_STAFF.durability(1140)));
	public static final DeferredItem<Item> CRYSTON_STAFF = registerStaff("cryston_staff", () -> new CrystonStaff(AoAItemStats.CRYSTON_STAFF.durability(1240)));
	public static final DeferredItem<Item> DESTRUCTION_STAFF = registerStaff("destruction_staff", () -> new DestructionStaff(AoAItemStats.DESTRUCTION_STAFF.durability(1170)));
	public static final DeferredItem<Item> EMBER_STAFF = registerStaff("ember_staff", () -> new EmberStaff(AoAItemStats.EMBER_STAFF.durability(1400)));
	public static final DeferredItem<Item> EVERFIGHT_STAFF = registerStaff("everfight_staff", () -> new EverfightStaff(AoAItemStats.EVERFIGHT_STAFF.durability(1050)));
	public static final DeferredItem<Item> EVERMIGHT_STAFF = registerStaff("evermight_staff", () -> new EvermightStaff(AoAItemStats.EVERMIGHT_STAFF.durability(1050)));
	public static final DeferredItem<Item> FIRE_STAFF = registerStaff("fire_staff", () -> new FireStaff(AoAItemStats.FIRE_STAFF.durability(850)));
	public static final DeferredItem<Item> FIREFLY_STAFF = registerStaff("firefly_staff", () -> new FireflyStaff(AoAItemStats.FIREFLY_STAFF.durability(1230)));
	public static final DeferredItem<Item> FIRESTORM_STAFF = registerStaff("firestorm_staff", () -> new FirestormStaff(AoAItemStats.FIRESTORM_STAFF.durability(990)));
	public static final DeferredItem<Item> FUNGAL_STAFF = registerStaff("fungal_staff", () -> new FungalStaff(AoAItemStats.FUNGAL_STAFF.durability(1130)));
	public static final DeferredItem<Item> GHOUL_STAFF = registerStaff("ghoul_staff", () -> new GhoulStaff(AoAItemStats.GHOUL_STAFF.durability(400)));
	public static final DeferredItem<Item> HAUNTERS_STAFF = registerStaff("haunters_staff", () -> new HauntersStaff(AoAItemStats.HAUNTERS_STAFF.durability(1225)));
	public static final DeferredItem<Item> HIVE_STAFF = registerStaff("hive_staff", () -> new HiveStaff(AoAItemStats.HIVE_STAFF.durability(1130)));
	public static final DeferredItem<Item> JOKER_STAFF = registerStaff("joker_staff", () -> new JokerStaff(AoAItemStats.JOKER_STAFF.durability(300)));
	public static final DeferredItem<Item> LIGHTNING_STAFF = registerStaff("lightning_staff", () -> new LightningStaff(AoAItemStats.LIGHTNING_STAFF.durability(1070)));
	public static final DeferredItem<Item> LIGHTSHINE = registerStaff("lightshine", () -> new Lightshine(AoAItemStats.LIGHTSHINE.durability(700)));
	public static final DeferredItem<Item> LUNAR_STAFF = registerStaff("lunar_staff", () -> new LunarStaff(AoAItemStats.LUNAR_STAFF.durability(1250)));
	public static final DeferredItem<Item> LYONIC_STAFF = registerStaff("lyonic_staff", () -> new LyonicStaff(AoAItemStats.LYONIC_STAFF.durability(1100)));
	public static final DeferredItem<Item> MECHA_STAFF = registerStaff("mecha_staff", () -> new MechaStaff(AoAItemStats.MECHA_STAFF.durability(1110)));
	public static final DeferredItem<Item> METEOR_STAFF = registerStaff("meteor_staff", () -> new MeteorStaff(AoAItemStats.METEOR_STAFF.durability(1190)));
	public static final DeferredItem<Item> MOONLIGHT_STAFF = registerStaff("moonlight_staff", () -> new MoonlightStaff(AoAItemStats.MOONLIGHT_STAFF.durability(1130)));
	public static final DeferredItem<Item> NATURE_STAFF = registerStaff("nature_staff", () -> new NatureStaff(AoAItemStats.NATURE_STAFF.durability(1450)));
	public static final DeferredItem<Item> NIGHTMARE_STAFF = registerStaff("nightmare_staff", () -> new NightmareStaff(AoAItemStats.NIGHTMARE_STAFF.durability(1200)));
	public static final DeferredItem<Item> NOXIOUS_STAFF = registerStaff("noxious_staff", () -> new NoxiousStaff(AoAItemStats.NOXIOUS_STAFF.durability(1210)));
	public static final DeferredItem<Item> PHANTOM_STAFF = registerStaff("phantom_staff", () -> new PhantomStaff(AoAItemStats.PHANTOM_STAFF.durability(1060))); // TODO move to phantom?
	public static final DeferredItem<Item> POISON_STAFF = registerStaff("poison_staff", () -> new PoisonStaff(AoAItemStats.POISON_STAFF.durability(850)));
	public static final DeferredItem<Item> POWER_STAFF = registerStaff("power_staff", () -> new PowerStaff(AoAItemStats.POWER_STAFF.durability(1010)));
	public static final DeferredItem<Item> PRIMORDIAL_STAFF = registerStaff("primordial_staff", () -> new PrimordialStaff(AoAItemStats.PRIMORDIAL_STAFF.durability(1230)));
	public static final DeferredItem<Item> REEF_STAFF = registerStaff("reef_staff", () -> new ReefStaff(AoAItemStats.REEF_STAFF.durability(1230)));
	public static final DeferredItem<Item> REJUVENATION_STAFF = registerStaff("rejuvenation_staff", () -> new RejuvenationStaff(AoAItemStats.REJUVENATION_STAFF.durability(870)));
	public static final DeferredItem<Item> ROSIDIAN_STAFF = registerStaff("rosidian_staff", () -> new RosidianStaff(AoAItemStats.ROSIDIAN_STAFF.durability(1180)));
	public static final DeferredItem<Item> RUNIC_STAFF = registerStaff("runic_staff", () -> new RunicStaff(AoAItemStats.RUNIC_STAFF.durability(1000)));
	public static final DeferredItem<Item> SHADOWLORD_STAFF = registerStaff("shadowlord_staff", () -> new ShadowlordStaff(AoAItemStats.SHADOWLORD_STAFF.durability(1200)));
	public static final DeferredItem<Item> SHOW_STAFF = registerStaff("show_staff", () -> new ShowStaff(AoAItemStats.SHOW_STAFF.durability(900)));
	public static final DeferredItem<Item> SHYRE_STAFF = registerStaff("shyre_staff", () -> new ShyreStaff(AoAItemStats.SHYRE_STAFF.durability(1380)));
	public static final DeferredItem<Item> SKY_STAFF = registerStaff("sky_staff", () -> new SkyStaff(AoAItemStats.SKY_STAFF.durability(1480)));
	public static final DeferredItem<Item> STRIKER_STAFF = registerStaff("striker_staff", () -> new StrikerStaff(AoAItemStats.STRIKER_STAFF.durability(850)));
	public static final DeferredItem<Item> SUN_STAFF = registerStaff("sun_staff", () -> new SunStaff(AoAItemStats.SUN_STAFF.durability(960)));
	public static final DeferredItem<Item> SURGE_STAFF = registerStaff("surge_staff", () -> new SurgeStaff(AoAItemStats.SURGE_STAFF.durability(1270)));
	public static final DeferredItem<Item> TANGLE_STAFF = registerStaff("tangle_staff", () -> new TangleStaff(AoAItemStats.TANGLE_STAFF.durability(1140)));
	public static final DeferredItem<Item> ULTIMATUM_STAFF = registerStaff("ultimatum_staff", () -> new UltimatumStaff(AoAItemStats.ULTIMATUM_STAFF.durability(750)));
	public static final DeferredItem<Item> UNDERWORLD_STAFF = registerStaff("underworld_staff", () -> new UnderworldStaff(AoAItemStats.UNDERWORLD_STAFF.durability(1140)));
	public static final DeferredItem<Item> WARLOCK_STAFF = registerStaff("warlock_staff", () -> new WarlockStaff(AoAItemStats.WARLOCK_STAFF.durability(1050)));
	public static final DeferredItem<Item> WATER_STAFF = registerStaff("water_staff", () -> new WaterStaff(AoAItemStats.WATER_STAFF.durability(850)));
	public static final DeferredItem<Item> WEB_STAFF = registerStaff("web_staff", () -> new WebStaff(AoAItemStats.WEB_STAFF.durability(1420)));
	public static final DeferredItem<Item> WIND_STAFF = registerStaff("wind_staff", () -> new WindStaff(AoAItemStats.WIND_STAFF.durability(850)));
	public static final DeferredItem<Item> WITHER_STAFF = registerStaff("wither_staff", () -> new WitherStaff(AoAItemStats.WITHER_STAFF.durability(850)));
	public static final DeferredItem<Item> WIZARDS_STAFF = registerStaff("wizards_staff", () -> new WizardsStaff(AoAItemStats.WIZARDS_STAFF.durability(800)));

	public static final DeferredItem<Item> APOCO_SHOWER = registerBlaster("apoco_shower", () -> new ApocoShower(AoAItemStats.APOCO_SHOWER.durability(3760)));// TODO
	public static final DeferredItem<Item> ATOMIZER = registerBlaster("atomizer", () -> new Atomizer(AoAItemStats.ATOMIZER.durability(3150)));// TODO
	public static final DeferredItem<Item> BEAMER = registerBlaster("beamer", () -> new Beamer(AoAItemStats.BEAMER.durability(3240)));
	public static final DeferredItem<Item> BLAST_CHILLER = registerBlaster("blast_chiller", () -> new BlastChiller(AoAItemStats.BLAST_CHILLER.durability(1750)));
	public static final DeferredItem<Item> BLOOD_DRAINER = registerBlaster("blood_drainer", () -> new BloodDrainer(AoAItemStats.BLOOD_DRAINER.durability(2750)));
	public static final DeferredItem<Item> BONE_BLASTER = registerBlaster("bone_blaster", () -> new BoneBlaster(AoAItemStats.BONE_BLASTER.durability(2430)));
	public static final DeferredItem<Item> BUBBLE_HORN = registerBlaster("bubble_horn", () -> new BubbleHorn(AoAItemStats.BUBBLE_HORN.durability(3200)));
	public static final DeferredItem<Item> COLOUR_CANNON = registerBlaster("colour_cannon", () -> new ColourCannon(AoAItemStats.COLOUR_CANNON.durability(1000).rarity(Rarity.EPIC)));
	public static final DeferredItem<Item> CONFETTI_CANNON = registerBlaster("confetti_cannon", () -> new ConfettiCannon(AoAItemStats.CONFETTI_CANNON.durability(1000)));
	public static final DeferredItem<Item> CONFETTI_CLUSTER = registerBlaster("confetti_cluster", () -> new ConfettiCluster(AoAItemStats.CONFETTI_CLUSTER.durability(1500)));
	public static final DeferredItem<Item> DARK_DESTROYER = registerBlaster("dark_destroyer", () -> new DarkDestroyer(AoAItemStats.DARK_DESTROYER.durability(3180)));
	public static final DeferredItem<Item> DARKLY_GUSTER = registerBlaster("darkly_guster", () -> new DarklyGuster(AoAItemStats.DARKLY_GUSTER.durability(3790)));
	public static final DeferredItem<Item> DEATH_RAY = registerBlaster("death_ray", () -> new DeathRay(AoAItemStats.DEATH_RAY.durability(3840)));
	public static final DeferredItem<Item> DOOM_BRINGER = registerBlaster("doom_bringer", () -> new DoomBringer(AoAItemStats.DOOM_BRINGER.durability(2820)));
	public static final DeferredItem<Item> ERADICATOR = registerBlaster("eradicator", () -> new Eradicator(AoAItemStats.ERADICATOR.durability(2790)));
	public static final DeferredItem<Item> EXPERIMENT_W_801 = registerWeapon("experiment_w_801", () -> new ExperimentW801(AoAItemStats.EXPERIMENT_W_801.durability(5000).rarity(Rarity.EPIC)), (ResourceKey<CreativeModeTab>[])null);
	public static final DeferredItem<Item> FLOWERCORNE = registerBlaster("flowercorne", () -> new Flowercorne(AoAItemStats.FLOWERCORNE.durability(2910)));
	public static final DeferredItem<Item> FRAGMENT = registerBlaster("fragment", () -> new Fragment(AoAItemStats.FRAGMENT.durability(3830)));
	public static final DeferredItem<Item> FROSTER = registerBlaster("froster", () -> new Froster(AoAItemStats.FROSTER.durability(2800)));
	public static final DeferredItem<Item> GAS_BLASTER = registerBlaster("gas_blaster", () -> new GasBlaster(AoAItemStats.GAS_BLASTER.durability(2860)));
	public static final DeferredItem<Item> GHOUL_GASSER = registerBlaster("ghoul_gasser", () -> new GhoulGasser(AoAItemStats.GHOUL_GASSER.durability(3210)));
	public static final DeferredItem<Item> GOLD_BRINGER = registerBlaster("gold_bringer", () -> new GoldBringer(AoAItemStats.GOLD_BRINGER.durability(3830)));
	public static final DeferredItem<Item> GRAVITY_BLASTER = registerBlaster("gravity_blaster", () -> new GravityBlaster(AoAItemStats.GRAVITY_BLASTER.durability(600)));
	public static final DeferredItem<Item> HELL_HORN = registerBlaster("hell_horn", () -> new HellHorn(AoAItemStats.HELL_HORN.durability(3800)));
	public static final DeferredItem<Item> ILLUSION_REVOLVER = registerBlaster("illusion_revolver", () -> new IllusionRevolver(AoAItemStats.ILLUSION_REVOLVER.durability(3200)));
	public static final DeferredItem<Item> ILLUSION_SMG = registerBlaster("illusion_smg", () -> new IllusionSMG(AoAItemStats.ILLUSION_SMG.durability(3240)));
	public static final DeferredItem<Item> ION_BLASTER = registerBlaster("ion_blaster", () -> new IonBlaster(AoAItemStats.ION_BLASTER.durability(2810)));
	public static final DeferredItem<Item> IRO_MINER = registerBlaster("iro_miner", () -> new IroMiner(AoAItemStats.IRO_MINER.durability(3060).component(AoADataComponents.LAST_TARGET.get(), Optional.empty()).component(AoADataComponents.DAMAGE_SCALING.get(), 1f)));
	public static final DeferredItem<Item> LASER_BLASTER = registerBlaster("laser_blaster", () -> new LaserBlaster(AoAItemStats.LASER_BLASTER.durability(2840)));
	public static final DeferredItem<Item> LIGHT_BLASTER = registerBlaster("light_blaster", () -> new LightBlaster(AoAItemStats.LIGHT_BLASTER.durability(3810)));
	public static final DeferredItem<Item> LIGHT_SPARK = registerBlaster("light_spark", () -> new LightSpark(AoAItemStats.LIGHT_SPARK.durability(7)));
	public static final DeferredItem<Item> LUNA_BLASTER = registerBlaster("luna_blaster", () -> new LunaBlaster(AoAItemStats.LUNA_BLASTER.durability(3200)));
	public static final DeferredItem<Item> MECHA_BLASTER = registerBlaster("mecha_blaster", () -> new MechaBlaster(AoAItemStats.MECHA_BLASTER.durability(2770)));
	public static final DeferredItem<Item> MIND_BLASTER = registerBlaster("mind_blaster", () -> new MindBlaster(AoAItemStats.MIND_BLASTER.durability(3180)));
	public static final DeferredItem<Item> MOON_DESTROYER = registerBlaster("moon_destroyer", () -> new MoonDestroyer(AoAItemStats.MOON_DESTROYER.durability(3750)));
	public static final DeferredItem<Item> MOON_SHINER = registerBlaster("moon_shiner", () -> new MoonShiner(AoAItemStats.MOON_SHINER.durability(1810)));
	public static final DeferredItem<Item> ODIOUS = registerBlaster("odious", () -> new Odious(AoAItemStats.ODIOUS.durability(3190)));
	public static final DeferredItem<Item> ORBOCRON = registerBlaster("orbocron", () -> new Orbocron(AoAItemStats.ORBOCRON.durability( 3230)));
	public static final DeferredItem<Item> PARALYZER = registerBlaster("paralyzer", () -> new Paralyzer(AoAItemStats.PARALYZER.durability(3900)));
	public static final DeferredItem<Item> PARTY_POPPER = registerBlaster("party_popper", () -> new PartyPopper(AoAItemStats.PARTY_POPPER.durability(2810)));
	public static final DeferredItem<Item> POISON_PLUNGER = registerBlaster("poison_plunger", () -> new PoisonPlunger(AoAItemStats.POISON_PLUNGER.durability(2600)));
	public static final DeferredItem<Item> POWER_RAY = registerBlaster("power_ray", () -> new PowerRay(AoAItemStats.POWER_RAY.durability(2490)));
	public static final DeferredItem<Item> PROTON = registerBlaster("proton", () -> new Proton(AoAItemStats.PROTON.durability(3000)));
	public static final DeferredItem<Item> REEFER = registerBlaster("reefer", () -> new Reefer(AoAItemStats.REEFER.durability(3600)));
	public static final DeferredItem<Item> REVOLUTION = registerBlaster("revolution", () -> new Revolution(AoAItemStats.REVOLUTION.durability(2800)));
	public static final DeferredItem<Item> SEAOCRON = registerBlaster("seaocron", () -> new Seaocron(AoAItemStats.SEAOCRON.durability(3800)));
	public static final DeferredItem<Item> SKULLO_BLASTER = registerBlaster("skullo_blaster", () -> new SkulloBlaster(AoAItemStats.SKULLO_BLASTER.durability(3780)));
	public static final DeferredItem<Item> SOUL_DRAINER = registerBlaster("soul_drainer", () -> new SoulDrainer(AoAItemStats.SOUL_DRAINER.durability(3770)));
	public static final DeferredItem<Item> SOUL_SPARK = registerBlaster("soul_spark", () -> new SoulSpark(AoAItemStats.SOUL_SPARK.durability(5)));
	public static final DeferredItem<Item> SOUL_STORM = registerBlaster("soul_storm", () -> new SoulStorm(AoAItemStats.SOUL_STORM.durability(3190)));
	public static final DeferredItem<Item> SPIRIT_SHOWER = registerBlaster("spirit_shower", () -> new SpiritShower(AoAItemStats.SPIRIT_SHOWER.durability(2870)));
	public static final DeferredItem<Item> SWARMOTRON = registerBlaster("swarmotron", () -> new Swarmotron(AoAItemStats.SWARMOTRON.durability(2400)));
	public static final DeferredItem<Item> TOXIC_TERRORIZER = registerBlaster("toxic_terrorizer", () -> new ToxicTerrorizer(AoAItemStats.TOXIC_TERRORIZER.durability(2760)));
	public static final DeferredItem<Item> VORTEX_BLASTER = registerBlaster("vortex_blaster", () -> new VortexBlaster(AoAItemStats.VORTEX_BLASTER.durability(2870)));
	public static final DeferredItem<Item> WHIMSY_WINDER = registerBlaster("whimsy_winder", () -> new WhimsyWinder(AoAItemStats.WHIMSY_WINDER.durability(2830)));
	public static final DeferredItem<Item> WITHERS_WRATH = registerBlaster("withers_wrath", () -> new WithersWrath(AoAItemStats.WITHERS_WRATH.durability(800)));

    private static <T extends Item> DeferredItem<T> registerWeapon(String registryName, Supplier<T> item, @Nullable ResourceKey<CreativeModeTab>... creativeTabs) {
		return AoAItems.registerItem(registryName, item, creativeTabs);
	}

	private static <T extends Item> DeferredItem<T> registerSword(String registryName, Supplier<T> item) {
		return registerWeapon(registryName, item, AoACreativeModeTabs.SWORDS.getKey());
	}

	private static <T extends Item> DeferredItem<T> registerGreatblade(String registryName, Supplier<T> item) {
		return registerWeapon(registryName, item, AoACreativeModeTabs.GREATBLADES.getKey());
	}

	private static <T extends Item> DeferredItem<T> registerMaul(String registryName, Supplier<T> item) {
		return registerWeapon(registryName, item, AoACreativeModeTabs.MAULS.getKey());
	}

	private static <T extends Item> DeferredItem<T> registerGun(String registryName, Supplier<T> item) {
		return registerWeapon(registryName, item, AoACreativeModeTabs.GUNS.getKey());
	}

	private static <T extends Item> DeferredItem<T> registerShotgun(String registryName, Supplier<T> item) {
		return registerWeapon(registryName, item, AoACreativeModeTabs.SHOTGUNS.getKey());
	}

	private static <T extends Item> DeferredItem<T> registerSniper(String registryName, Supplier<T> item) {
		return registerWeapon(registryName, item, AoACreativeModeTabs.SNIPERS.getKey());
	}

	private static <T extends Item> DeferredItem<T> registerCannon(String registryName, Supplier<T> item) {
		return registerWeapon(registryName, item, AoACreativeModeTabs.CANNONS.getKey());
	}

	private static <T extends Item> DeferredItem<T> registerThrownWeapon(String registryName, Supplier<T> item) {
		return registerWeapon(registryName, item, AoACreativeModeTabs.THROWN_WEAPONS.getKey());
	}

	private static <T extends Item> DeferredItem<T> registerVulcane(String registryName, Supplier<T> item) {
		return registerWeapon(registryName, item, AoACreativeModeTabs.VULCANES.getKey());
	}

	private static <T extends Item> DeferredItem<T> registerBow(String registryName, Supplier<T> item) {
		return registerWeapon(registryName, item, AoACreativeModeTabs.BOWS.getKey());
	}

	private static <T extends Item> DeferredItem<T> registerCrossbow(String registryName, Supplier<T> item) {
		return registerWeapon(registryName, item, AoACreativeModeTabs.CROSSBOWS.getKey());
	}

	private static <T extends Item> DeferredItem<T> registerStaff(String registryName, Supplier<T> item) {
		return registerWeapon(registryName, item, AoACreativeModeTabs.STAVES.getKey());
	}

	private static <T extends Item> DeferredItem<T> registerBlaster(String registryName, Supplier<T> item) {
		return registerWeapon(registryName, item, AoACreativeModeTabs.BLASTERS.getKey());
	}
}
