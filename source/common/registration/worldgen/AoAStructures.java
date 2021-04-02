package net.tslat.aoa3.common.registration.worldgen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.worldgen.feature.features.config.IntRangeConfig;
import net.tslat.aoa3.worldgen.structure.structures.*;
import net.tslat.aoa3.worldgen.structure.structures.special.KrorCaveStructure;

import java.util.*;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public class AoAStructures {
	public static final ArrayList<StructureDataPackage> STRUCTURE_DATA = new ArrayList<StructureDataPackage>();
	public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, AdventOfAscension.MOD_ID);

	public static final RegistryObject<YPosStructure> RUINED_TELEPORTER_FRAME = register("ruined_teleporter_frame", () -> new YPosStructure(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, "overworld/ruined_teleporter_frame/main_pool"), 4, 10);

	public static final RegistryObject<YPosStructure> NETHENGEIC_PIT = register("nethengeic_pit", () -> new YPosStructure(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, "nether/nethengeic_pit/main_pool"), 8, 32);

	public static final RegistryObject<GenericAoAStructure> FLESH_TEMPLE = register("flesh_temple", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "abyss/flesh_temple/main_pool"), 8, 32);
	public static final RegistryObject<GenericAoAStructure> JAWE_HUT = register("jawe_hut", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "abyss/jawe_hut/main_pool"), 8, 32);
	public static final RegistryObject<GenericAoAStructure> ABYSSAL_LOTTO_HUT = register("abyssal_lotto_hut", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "abyss/abyssal_lotto_hut/main_pool"), 6, 20);
	public static final RegistryObject<GenericAoAStructure> SHADOWLORD_PLATFORM = register("shadowlord_platform", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "abyss/shadowlord_platform/main_pool"), 10, 40);
	public static final RegistryObject<GenericAoAStructure> ILLUSION_TREE = register("illusion_tree", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "abyss/illusion_tree/main_pool"), 20, 40);

	public static final RegistryObject<GenericAoAStructure> BARON_CASTLE = register("baron_castle", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "barathos/baron_castle/main_pool"), 11, 32);
	public static final RegistryObject<GenericAoAStructure> BARONESS_ARENA = register("baroness_arena", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "barathos/baroness_arena/main_pool"), 12, 40);
	public static final RegistryObject<GenericAoAStructure> BARONESS_HOUSE = register("baroness_house", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "barathos/baroness_house/main_pool"), 8, 32);
	public static final RegistryObject<GenericAoAStructure> HIVE_NEST = register("hive_nest", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "barathos/hive_nest/main_pool"), 4, 20);

	public static final RegistryObject<GenericAoAStructure> CANDY_LOTTO_PLATFORM = register("candy_lotto_platform", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "candyland/candy_lotto_platform/main_pool"), 4, 16);
	public static final RegistryObject<GenericAoAStructure> COTTON_CANDY_TOWER = register("cotton_candy_tower", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "candyland/cotton_candy_tower/main_pool"), 10, 32);
	public static final RegistryObject<GenericAoAStructure> GINGERBIRD_AVIARY = register("gingerbird_aviary", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "candyland/gingerbird_aviary/main_pool"), 8, 32);
	public static final RegistryObject<GenericAoAStructure> GINGERBREAD_HOUSE = register("gingerbread_house", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "candyland/gingerbread_house/main_pool"), 8, 32);
	public static final RegistryObject<GenericAoAStructure> INFESTED_CANDY_CANE = register("infested_candy_cane", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "candyland/infested_candy_cane/main_pool"), 8, 32);

	public static final RegistryObject<YPosStructure> CELEVIAN_LOTTO_BALLOON = register("celevian_lotto_balloon", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "celeve/celevian_lotto_balloon/main_pool"), 6, 26);

	public static final RegistryObject<GenericAoAStructure> CREEPER_HQ = register("creeper_hq", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "creeponia/creeper_hq/main_pool"), 12, 37);
	public static final RegistryObject<GenericAoAStructure> CREEPONIA_BANK = register("creeponia_bank", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "creeponia/creeponia_bank/main_pool"), 8, 32);
	public static final RegistryObject<GenericAoAStructure> CREEPONIAN_LOTTO_STAND = register("creeponian_lotto_stand", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "creeponia/creeponian_lotto_stand/main_pool"), 6, 20);
	public static final RegistryObject<GenericAoAStructure> EXPLOSIVES_TOWER = register("explosives_tower", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "creeponia/explosives_tower/main_pool"), 6, 20);

	public static final RegistryObject<YPosStructure> CRYSTAL_EXTENSION_STATION = register("crystal_extension_station", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "crystevia/crystal_extension_station/main_pool"), 8, 32);
	public static final RegistryObject<YPosStructure> CRYSTAL_LOTTO_OVERLOOK = register("crystal_lotto_overlook", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "crystevia/crystal_lotto_overlook/main_pool"), 6, 24);
	public static final RegistryObject<YPosStructure> POWER_STATION = register("power_station", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "crystevia/power_station/main_pool"), 8, 32);
	public static final RegistryObject<YPosStructure> CRYSTAL_TRADING_POST = register("crystal_trading_post", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "crystevia/crystal_trading_post/main_pool"), 6, 24);
	public static final RegistryObject<YPosStructure> BLUE_CRYSTAL_TRANSFER_HUT = register("blue_crystal_transfer_hut", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "crystevia/crystal_transfer_hut/blue"), 12, 40);
	public static final RegistryObject<YPosStructure> GREEN_CRYSTAL_TRANSFER_HUT = register("green_crystal_transfer_hut", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "crystevia/crystal_transfer_hut/green"), 12, 40);
	public static final RegistryObject<YPosStructure> RED_CRYSTAL_TRANSFER_HUT = register("red_crystal_transfer_hut", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "crystevia/crystal_transfer_hut/red"), 12, 40);
	public static final RegistryObject<YPosStructure> PURPLE_CRYSTAL_TRANSFER_HUT = register("purple_crystal_transfer_hut", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "crystevia/crystal_transfer_hut/purple"), 12, 40);
	public static final RegistryObject<YPosStructure> WHITE_CRYSTAL_TRANSFER_HUT = register("white_crystal_transfer_hut", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "crystevia/crystal_transfer_hut/white"), 12, 40);
	public static final RegistryObject<YPosStructure> YELLOW_CRYSTAL_TRANSFER_HUT = register("yellow_crystal_transfer_hut", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "crystevia/crystal_transfer_hut/yellow"), 12, 40);

	public static final RegistryObject<UndergroundSurfaceStructure> AROCKNID_CAVE = register("arocknid_cave", () -> new UndergroundSurfaceStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "deeplands/arocknid_cave/main_pool"), 6, 16);
	public static final RegistryObject<UndergroundSurfaceStructure> CHARGING_STATION = register("charging_station", () -> new UndergroundSurfaceStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "deeplands/charging_station/main_pool"), 8, 18);
	public static final RegistryObject<UndergroundSurfaceStructure> DEEP_LOTTO_SHELTER = register("deep_lotto_shelter", () -> new UndergroundSurfaceStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "deeplands/deep_lotto_shelter/main_pool"), 6, 18);
	public static final RegistryObject<KrorCaveStructure> KROR_CAVE = register("kror_cave", () -> new KrorCaveStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "deeplands/kror_cave/main_pool"), 10, 26, 934827456);

	public static final RegistryObject<FixedRotationStructure> PRIMORDIAL_SHRINE = register("primordial_shrine", () -> new FixedRotationStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "dustopia/primordial_shrine/main_pool"), 8, 32);
	public static final RegistryObject<GenericAoAStructure> CRUSILISK_CAGE = register("crusilisk_cage", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "dustopia/crusilisk_cage/main_pool"), 6, 20);
	public static final RegistryObject<GenericAoAStructure> MERKYRE_TOWER = register("merkyre_tower", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "dustopia/merkyre_tower/main_pool"), 8, 28);
	public static final RegistryObject<GenericAoAStructure> ARKZYNE_OUTPOST = register("arkzyne_outpost", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "dustopia/arkzyne_outpost/main_pool"), 8, 28);
	public static final RegistryObject<GenericAoAStructure> LOTTO_CAGE = register("lotto_cage", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "dustopia/lotto_cage/main_pool"), 8, 28);
	public static final RegistryObject<GenericAoAStructure> DUSTOPIAN_VILLAGE = register("dustopian_village", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "dustopia/dustopian_village/main_pool"), 10, 26);

	public static final RegistryObject<GenericAoAStructure> DAYSEE_FLOWER = register("daysee_flower", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "gardencia/daysee_flower/main_pool"), 10, 26);
	public static final RegistryObject<GenericAoAStructure> FLORO_CASTLE = register("floro_castle", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "gardencia/floro_castle/main_pool"), 16, 40);
	public static final RegistryObject<GenericAoAStructure> GARDEN_CASTLE = register("garden_castle", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "gardencia/garden_castle/main_pool"), 12, 32);
	public static final RegistryObject<GenericAoAStructure> LOTTO_SKY_FLOWER = register("lotto_sky_flower", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "gardencia/lotto_sky_flower/main_pool"), 12, 32);
	public static final RegistryObject<GenericAoAStructure> WIZARD_FLOWER = register("wizard_flower", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "gardencia/wizard_flower/main_pool"), 10, 26);

	public static final RegistryObject<GenericAoAStructure> HAUNTED_MAZE = register("haunted_maze", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "greckon/haunted_maze/main_pool"), 10, 32);
	public static final RegistryObject<GenericAoAStructure> HAUNTED_LOTTO_ROCK = register("haunted_lotto_rock", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "greckon/haunted_lotto_rock/main_pool"), 8, 24);
	public static final RegistryObject<GenericAoAStructure> FACELESS_TREE = register("faceless_tree", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "greckon/faceless_tree/main_pool"), 8, 20);

	public static final RegistryObject<YPosStructure> FLOATING_LOTTO_FOUNTAIN = register("floating_lotto_fountain", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "haven/floating_lotto_fountain/main_pool"), 10, 26);
	public static final RegistryObject<GenericAoAStructure> GUARDIAN_TOWER = register("guardian_tower", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "haven/guardian_tower/main_pool"), 12, 40);

	public static final RegistryObject<GenericAoAStructure> CHARGING_PADS = register("charging_pads", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "iromine/charging_pads/main_pool"), 10, 36);
	public static final RegistryObject<GenericAoAStructure> ENFORCER_TOWER = register("enforcer_tower", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "iromine/enforcer_tower/main_pool"), 6, 26);
	public static final RegistryObject<GenericAoAStructure> IRO_PASSAGE = register("iro_passage", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "iromine/iro_passage/main_pool"), 6, 20);
	public static final RegistryObject<GenericAoAStructure> IRO_PILLAR = register("iro_pillar", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "iromine/iro_pillar/main_pool"), 6, 20);
	public static final RegistryObject<GenericAoAStructure> MECHYON_TEMPLE = register("mechyon_temple", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "iromine/mechyon_temple/main_pool"), 6, 20);
	public static final RegistryObject<GenericAoAStructure> PROFESSORS_LAB = register("professors_lab", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "iromine/professors_lab/main_pool"), 8, 24);

	public static final RegistryObject<OceanFloorStructure> AMPHIBIYTE_COVE = register("amphibiyte_cove", () -> new OceanFloorStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lborean/amphibiyte_cove/main_pool"), 8, 32);
	public static final RegistryObject<OceanFloorStructure> AQUATIC_CASTLE = register("aquatic_castle", () -> new OceanFloorStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lborean/aquatic_castle/main_pool"), 12, 40);
	public static final RegistryObject<OceanFloorStructure> DRACYON_FOUNTAIN = register("dracyon_fountain", () -> new OceanFloorStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lborean/dracyon_fountain/main_pool"), 10, 36);
	public static final RegistryObject<OceanFloorStructure> DROWNED_LOTTO_STAND = register("drowned_lotto_stand", () -> new OceanFloorStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lborean/drowned_lotto_stand/main_pool"), 8, 24);

	public static final RegistryObject<YPosStructure> BONEY_DUNGEON = register("boney_dungeon", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lelyetia/boney_dungeon/main_pool"), 8, 24);
	public static final RegistryObject<GenericAoAStructure> GRAW_PILLAR = register("graw_pillar", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lelyetia/graw_pillar/main_pool"), 10, 30);
	public static final RegistryObject<GenericAoAStructure> LELYETIAN_TOWER = register("lelyetian_tower", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lelyetia/lelyetian_tower/main_pool"), 10, 36);
	public static final RegistryObject<HangingStructure> PARAVITE_HIVE = register("paravite_hive", () -> new HangingStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lelyetia/paravite_hive/main_pool"), 8, 24);
	public static final RegistryObject<HangingStructure> ZHINX_ENCLAVE = register("zhinx_enclave", () -> new HangingStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lelyetia/zhinx_enclave/main_pool"), 8, 24);

	public static final RegistryObject<YPosStructure> DIOCUS_DEN = register("diocus_den", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "precasia/diocus_den/main_pool"), 8, 24);
	public static final RegistryObject<YPosStructure> IOSAUR_DEN = register("iosaur_den", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "precasia/iosaur_den/main_pool"), 8, 24);
	public static final RegistryObject<GenericAoAStructure> JUNGLE_LOTTO_HUT = register("jungle_lotto_hut", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "precasia/jungle_lotto_hut/main_pool"), 8, 24);
	public static final RegistryObject<YPosStructure> KAIYU_TEMPLE = register("kaiyu_temple", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "precasia/kaiyu_temple/main_pool"), 12, 40);
	public static final RegistryObject<GenericAoAStructure> OPTERYX_NEST = register("opteryx_nest", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "precasia/opteryx_nest/main_pool"), 8, 24);
	public static final RegistryObject<GenericAoAStructure> SKELETAL_ARMY_ARENA = register("skeletal_army_arena", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "precasia/skeletal_army_arena/main_pool"), 10, 36);
	public static final RegistryObject<YPosStructure> SPINOLEDON_DEN = register("spinoledon_den", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "precasia/spinoledon_den/main_pool"), 8, 24);

	public static final RegistryObject<YPosStructure> LUNAR_BANK = register("lunar_bank", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lunalus/lunar_bank/main_pool"), 12, 40);
	public static final RegistryObject<YPosStructure> LUNAR_CREATION_PLATFORM = register("lunar_creation_platform", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lunalus/lunar_creation_platform/main_pool"), 12, 40);
	public static final RegistryObject<YPosStructure> LUNAR_FOOD_MARKET = register("lunar_food_market", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lunalus/lunar_food_market/main_pool"), 12, 40);
	public static final RegistryObject<YPosStructure> LUNAR_GARDEN = register("lunar_garden", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lunalus/lunar_garden/main_pool"), 12, 40);
	public static final RegistryObject<YPosStructure> LUNAR_HERBAL_HOUSE = register("lunar_herbal_house", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lunalus/lunar_herbal_house/main_pool"), 12, 40);
	public static final RegistryObject<YPosStructure> LUNAR_LOTTO_PLATFORM = register("lunar_lotto_platform", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lunalus/lunar_lotto_platform/main_pool"), 12, 40);
	public static final RegistryObject<YPosStructure> LUNAR_MAZE = register("lunar_maze", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lunalus/lunar_maze/main_pool"), 14, 40);
	public static final RegistryObject<YPosStructure> LUNAR_PRISON = register("lunar_prison", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lunalus/lunar_prison/main_pool"), 16, 40);
	public static final RegistryObject<YPosStructure> LUNARADE_STAND = register("lunarade_stand", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lunalus/lunarade_stand/main_pool"), 12, 40);
	public static final RegistryObject<YPosStructure> OBSERVERS_EYE = register("observers_eye", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lunalus/observers_eye/main_pool"), 12, 40);
	public static final RegistryObject<YPosStructure> SPACE_ARENA = register("space_arena", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lunalus/space_arena/main_pool"), 12, 40);
	public static final RegistryObject<YPosStructure> SPELLBINDER_HOUSE = register("spellbinder_house", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lunalus/spellbinder_house/main_pool"), 12, 40);
	public static final RegistryObject<YPosStructure> ZARG_PLANETOID = register("zarg_planetoid", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "lunalus/zarg_planetoid/main_pool"), 12, 40);

	public static final RegistryObject<GenericAoAStructure> CLUNKHEAD_ARENA = register("clunkhead_arena", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "runandor/clunkhead_arena/main_pool"), 10, 36);
	public static final RegistryObject<GenericAoAStructure> RUNE_RANDOMISATION_STATION = register("rune_randomisation_station", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "runandor/rune_randomisation_station/main_pool"), 8, 26);
	public static final RegistryObject<YPosStructure> BLUE_RUNE_TEMPLAR_BUNKER = register("blue_rune_templar_bunker", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "runandor/rune_templar_bunker/blue"), 10, 32);
	public static final RegistryObject<YPosStructure> GREEN_RUNE_TEMPLAR_BUNKER = register("green_rune_templar_bunker", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "runandor/rune_templar_bunker/green"), 10, 32);
	public static final RegistryObject<YPosStructure> RED_RUNE_TEMPLAR_BUNKER = register("red_rune_templar_bunker", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "runandor/rune_templar_bunker/red"), 10, 32);
	public static final RegistryObject<YPosStructure> YELLOW_RUNE_TEMPLAR_BUNKER = register("yellow_rune_templar_bunker", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "runandor/rune_templar_bunker/yellow"), 10, 32);
	public static final RegistryObject<GenericAoAStructure> RUNIC_TOWER = register("runic_tower", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "runandor/runic_tower/main_pool"), 8, 30);
	public static final RegistryObject<GenericAoAStructure> SPECTRAL_CAGE = register("spectral_cage", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "runandor/spectral_cage/main_pool"), 8, 24);

	public static final RegistryObject<GenericAoAStructure> CELL_TOWER = register("cell_tower", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "vox_ponds/cell_tower/main_pool"), 12, 40);
	public static final RegistryObject<GenericAoAStructure> CONTROL_TOWER = register("control_tower", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "vox_ponds/control_tower/main_pool"), 8, 28);
	public static final RegistryObject<GenericAoAStructure> DESTROYED_STORE = register("destroyed_store", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "vox_ponds/destroyed_store/main_pool"), 8, 24);
	public static final RegistryObject<YPosStructure> NIGHTWING_ISLAND = register("nightwing_island", () -> new YPosStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "vox_ponds/nightwing_island/main_pool"), 8, 24);
	public static final RegistryObject<GenericAoAStructure> OBSERVATION_TOWER = register("observation_tower", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "vox_ponds/observation_tower/main_pool"), 8, 26);
	public static final RegistryObject<GenericAoAStructure> VOX_LOTTO_OUTPOST = register("vox_lotto_outpost", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "vox_ponds/vox_lotto_outpost/main_pool"), 8, 24);
	public static final RegistryObject<GenericAoAStructure> VOXXULON_BEACON = register("voxxulon_beacon", () -> new GenericAoAStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "vox_ponds/voxxulon_beacon/main_pool"), 10, 32);

	public static final RegistryObject<OneTimeStructure> NOWHERE_HUB = register("nowhere_hub", () -> new OneTimeStructure(GenerationStage.Decoration.SURFACE_STRUCTURES, "nowhere/hub/main_pool"), 0, 1);

	private static <T extends net.minecraft.world.gen.feature.structure.Structure<?>> RegistryObject<T> register(String id, Supplier<T> structure, int minSeparation, int averageSpacing) {
		return register(id, structure, minSeparation, averageSpacing, new Random(id.hashCode()).nextInt(Integer.MAX_VALUE), false, false);
	}

	private static <T extends net.minecraft.world.gen.feature.structure.Structure<?>> RegistryObject<T> register(String id, Supplier<T> structure, int minSeparation, int averageSpacing, int salt) {
		return register(id, structure, minSeparation, averageSpacing, salt, false, false);
	}

	private static <T extends net.minecraft.world.gen.feature.structure.Structure<?>> RegistryObject<T> register(String id, Supplier<T> structure, int minSeparation, int averageSpacing, int salt, boolean blendLandToStructure, boolean forVanillaBiomes) {
		RegistryObject<T> registryObject =  STRUCTURES.register(id, structure);

		STRUCTURE_DATA.add(new StructureDataPackage(registryObject, new StructureSeparationSettings(averageSpacing, minSeparation, salt), blendLandToStructure, forVanillaBiomes));

		return registryObject;
	}

	public static void postInit() {
		prePopulateStructureSpacings();
		Configured.postInit();
	}

	public static class StructureDataPackage {
		public final RegistryObject<? extends Structure<?>> structure;
		public final StructureSeparationSettings spreadSettings;
		private final boolean blendsLand;
		private final boolean isVanillaBiomeStructure;

		private StructureDataPackage(RegistryObject<? extends Structure<?>> structure, StructureSeparationSettings settings, boolean blendsLand, boolean forVanillaBiomes) {
			this.structure = structure;
			this.spreadSettings = settings;
			this.blendsLand = blendsLand;
			this.isVanillaBiomeStructure = forVanillaBiomes;
		}
	}

	public static class Configured {
		private static final StructureFeature<?, ? extends Structure<?>> NETHENGEIC_PIT = register("nethengeic_pit", AoAStructures.NETHENGEIC_PIT.get().configured(new IntRangeConfig(25, 35)));
		private static final StructureFeature<?, ? extends Structure<?>> RUINED_TELEPORTER_FRAME = register("ruined_teleporter_frame", AoAStructures.RUINED_TELEPORTER_FRAME.get().configured(new IntRangeConfig(10, 20, true)));

		public static void postInit() {}

		private static <C extends IFeatureConfig, S extends Structure<C>, SF extends StructureFeature<C,? extends S>> SF register(String id, SF configuredStructure) {
			return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, new ResourceLocation(AdventOfAscension.MOD_ID, id), configuredStructure);
		}
	}

	private static void prePopulateStructureSpacings() {
		for (StructureDataPackage structureData : STRUCTURE_DATA) {
			Structure<?> structure = structureData.structure.get();

			Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

			if (structureData.blendsLand)
				Structure.NOISE_AFFECTING_FEATURES = ImmutableList.<Structure<?>>builder().addAll(Structure.NOISE_AFFECTING_FEATURES).add(structure).build();

			DimensionStructuresSettings.DEFAULTS = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.DEFAULTS).put(structure, structureData.spreadSettings).build();

			for (Map.Entry<RegistryKey<DimensionSettings>, DimensionSettings> settingsEntry : WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet()) {
				Map<Structure<?>, StructureSeparationSettings> separationSettings = settingsEntry.getValue().structureSettings().structureConfig();

				if (separationSettings instanceof ImmutableMap) {
					Map<Structure<?>, StructureSeparationSettings> newMap = new HashMap<Structure<?>, StructureSeparationSettings>(separationSettings);

					newMap.put(structure, structureData.spreadSettings);
					settingsEntry.getValue().structureSettings().structureConfig = newMap;
				}
				else {
					separationSettings.put(structure, structureData.spreadSettings);
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onBiomeLoad(final BiomeLoadingEvent ev) {
		if (ev.getName() == null)
			return;

		RegistryKey<Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, ev.getName());
		Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(biomeKey);
		List<Supplier<StructureFeature<?, ?>>> structures = ev.getGeneration().getStructures();

		if (biomeTypes.contains(BiomeDictionary.Type.OVERWORLD)) {
			structures.add(() -> Configured.RUINED_TELEPORTER_FRAME);
		}
		else if (biomeTypes.contains(BiomeDictionary.Type.NETHER)) {
			structures.add(() -> Configured.NETHENGEIC_PIT);
		}
	}
}
