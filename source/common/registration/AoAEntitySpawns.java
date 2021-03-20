package net.tslat.aoa3.common.registration;

import net.minecraft.block.Block;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.*;

import static net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType.*;
import static net.minecraft.world.gen.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES;
import static net.minecraft.world.gen.Heightmap.Type.OCEAN_FLOOR;

@SuppressWarnings({"unchecked", "rawtypes"})
@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public final class AoAEntitySpawns {
	private static final ArrayList<SpawnEntry<? extends MobEntity>> SPAWN_ENTRIES = new ArrayList<SpawnEntry<? extends MobEntity>>();

    public static void registerEntitySpawns() {
        Logging.logStatusMessage("Registering entity spawns");

        if (!AoAConfig.SERVER.disableOverworldMobs.get())
            populateOverworldSpawnEntries();

        setAbyssSpawnPlacements();
        setBarathosSpawnPlacements();
        setCandylandSpawnPlacements();
        setCeleveSpawnPlacements();
        setCreeponiaSpawnPlacements();
        setCrysteviaSpawnPlacements();
        setDeeplandsSpawnPlacements();
        setDustopiaSpawnPlacements();
        setGardenciaSpawnPlacements();
        setGreckonSpawnPlacements();
        setHavenSpawnPlacements();
        setIromineSpawnPlacements();
        populateNetherSpawnEntries();
        setLboreanSpawnPlacements();
        setLelyetiaSpawnPlacements();
        populateLunalusSpawnEntries();
        setMysteriumSpawnPlacements();
        setPrecasiaSpawnPlacements();
        setRunandorSpawnPlacements();
        setShyrelandsSpawnPlacements();
        setVoxPondsSpawnPlacements();

        new SpawnEntry(AoAEntities.Animals.AMBIENT_PIXON.get(), 12).groupSize(1, 4).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "haven"), new ResourceLocation(AdventOfAscension.MOD_ID, "runandor"), new ResourceLocation(AdventOfAscension.MOD_ID, "candyland"), new ResourceLocation(AdventOfAscension.MOD_ID, "shyrelands")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
    }

    public static void populateOverworldSpawnEntries() {
        new SpawnEntry(AoAEntities.Mobs.BOMB_CARRIER.get(), 10).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.EVERBEAST.get(), 25).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.GHOST.get(), 85).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.MOTHER_VOID_WALKER.get(), 85).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SHADOW.get(), 85).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.TRICKSTER.get(), 60).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.VOID_CHARGER.get(), 85).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.VOID_WALKER.get(), 85).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.BUGEYE.get(), 90).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.NIGHT_REAPER.get(), 55).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.WOOD_GIANT.get(), 1).groupSize(-10, 1).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.GOALBY.get(), 80).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.MOUNTAIN).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.STONE_GIANT.get(), 1).groupSize(-10, 1).matchBiomes(BiomeDictionary.Type.MOUNTAIN).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.YETI.get(), 70).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SNOWY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SNOW_CHARGER.get(), 70).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SNOWY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.ICE_GIANT.get(), 1).groupSize(-10, 1).matchBiomes(BiomeDictionary.Type.SNOWY).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.DESERT_CHARGER.get(), 30).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.FURLION.get(), 30).groupSize(1, 3).matchBiomes(BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SAND_GOLEM.get(), 30).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SPHINX.get(), 30).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SAND_GIANT.get(), 1).groupSize(-10, 1).matchBiomes(BiomeDictionary.Type.SANDY).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.CHIMERA.get(), 75).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SAVANNA).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.BONEBACK.get(), 75).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SAVANNA).placeMonster();
        new SpawnEntry(AoAEntities.Animals.ELKANYNE.get(), 20).groupSize(2, 4).matchBiomes(BiomeDictionary.Type.SAVANNA).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));

        new SpawnEntry(AoAEntities.Mobs.BUSH_BABY.get(), 70).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.JUNGLE).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.CHOMPER.get(), 70).matchBiomes(BiomeDictionary.Type.SWAMP).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.FISHIX.get(), 70).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SWAMP).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SWAMP_CHARGER.get(), 70).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SWAMP).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.HAG.get(), 70).matchBiomes(BiomeDictionary.Type.SWAMP).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.HIDING_FUNGI.get(), 40).matchBiomes(BiomeDictionary.Type.CONIFEROUS).ignoreBiomes(BiomeDictionary.Type.SNOWY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.LEAFY_GIANT.get(), 1).groupSize(-10, 1).matchBiomes(BiomeDictionary.Type.CONIFEROUS).ignoreBiomes(BiomeDictionary.Type.SNOWY).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.HILL_CHARGER.get(), 50).groupSize(1, 3).matchBiomes(BiomeDictionary.Type.MESA).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.MUCKOPEDE.get(), 50).matchBiomes(BiomeDictionary.Type.MESA).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.PINCHER.get(), 1).matchBiomes(BiomeDictionary.Type.OCEAN).place(IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);

        new SpawnEntry(AoAEntities.Mobs.SEA_CHARGER.get(), 20).groupSize(1, 3).matchBiomes(BiomeDictionary.Type.BEACH).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SEA_TROLL.get(), 20).matchBiomes(BiomeDictionary.Type.BEACH).placeMonster();
        new SpawnEntry(AoAEntities.NPCs.TROLL_TRADER.get(), 1).matchBiomes(BiomeDictionary.Type.BEACH).placeNPC();

        new SpawnEntry(AoAEntities.Mobs.CYCLOPS.get(), 20).groupSize(1, 4).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.CHARGER.get(), 20).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SASQUATCH.get(), 20).groupSize(1, 3).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.KING_CHARGER.get(), 1).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.GOBLIN.get(), 20).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.NIGHTFLY.get(), 20).groupSize(1, 3).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.NPCs.ASSASSIN.get(), 1).groupSize(0, 1).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeNPC();
        new SpawnEntry(AoAEntities.NPCs.NATURALIST.get(), 1).groupSize(0, 1).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeNPC();
        new SpawnEntry(AoAEntities.NPCs.REALMSHIFTER.get(), 1).groupSize(0, 1).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeNPC();
        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).groupSize(0, 1).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeNPC();
        new SpawnEntry(AoAEntities.NPCs.LOTTOMAN.get(), 1).groupSize(0, 1).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeNPC();
        new SpawnEntry(AoAEntities.NPCs.HAULING_MASTER.get(), 1).groupSize(0, 1).matchBiomes(BiomeDictionary.Type.BEACH, BiomeDictionary.Type.RIVER, BiomeDictionary.Type.SWAMP).placeNPC();
        new SpawnEntry(AoAEntities.Animals.GLISTENING_PIXON.get(), 30).groupSize(0, 4).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        new SpawnEntry(AoAEntities.Mobs.HORNDRON.get(), 5).matchBiomes(BiomeDictionary.Type.PLAINS).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.ANCIENT_GOLEM.get(), 5).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.PLAINS).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();

        new SpawnEntry(AoAEntities.Mobs.DEAD_TREE.get(), 10).matchExactBiomes(new ResourceLocation("forest"), new ResourceLocation("wooded_hills")).placeMonster();
    }

    private static void setAbyssSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.SHINING_PIXON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAEntities.Mobs.ANEMIA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.APPARITION.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.BLOODSUCKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DISTORTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.FIEND.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.OCCULENT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.WEB_REAPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.BUTCHERY_MASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setBarathosSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.ARKBACK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CRYPTID.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.ECHODAR.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.EMPEROR_BEAST.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.KEELER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.NOSPIKE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.PARASECT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.RAMRADON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SQUIGGLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.THARAFLY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.CREATION_MASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setCandylandSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.PEPPERMINT_SNAIL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Animals.SPEARMINT_SNAIL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Mobs.AIRHEAD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CANDY_CORNY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CHERRY_BLASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.LOLLYPOPPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setCeleveSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.BOBO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CHOCKO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.JUMBO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.KOKO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.KRANKY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.HAPPY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SNAPPY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.STICKY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.STITCHES.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.TIPSY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.INNERVATION_MASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setCreeponiaSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.CREEP_COW.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAEntities.Mobs.BONE_CREEPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CAVE_CREEPOID.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CREEPERLOCK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CREEPIRD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CREEPUPLE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.HOST.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.KING_CREEPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.MAGICAL_CREEPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.WINGED_CREEPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setCrysteviaSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_FLIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_MIND.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_RANGE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_RESISTANCE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_SPEED.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_STRENGTH.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CONSTRUCT_OF_TERROR.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setDeeplandsSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.SHIK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(Tags.Blocks.STONE, true));
        setSpawnPlacement(AoAEntities.Mobs.CASE_CONSTRUCT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CAVE_CREEP.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DOUBLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DWELLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.ROCKBITER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.ROCK_CRAWLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.ROCK_CRITTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.EXTRACTION_MASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setDustopiaSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.RADIANT_PIXON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAEntities.Animals.URKA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAEntities.Mobs.BASILISK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DEVOURER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DUSTEIVA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DUSTON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.LOST_SOUL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.LURKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.STALKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setGardenciaSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.ARCHVINE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.BROCCOHEAD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CARROTOP.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CORNY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.FLOWERFACE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SQUASHER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SUNNY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setGreckonSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.GRILLFACE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.HUNTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SHIFTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SILENCER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SKULL_CREATURE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SUGARFACE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.VALKYRIE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.INFUSION_MASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setHavenSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.ANGELICA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Animals.DAWNLIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Animals.EEO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Animals.HALYCON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Animals.RAINICORN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Animals.VOLIANT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setIromineSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.MECHACHRON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.MECHAMATON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.POLYTOM.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.VOLTRON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.FORAGING_MASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
        setSpawnPlacement(AoAEntities.NPCs.METALLOID.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void populateNetherSpawnEntries() {
        new SpawnEntry(AoAEntities.Mobs.EMBRAKE.get(), 60).matchBiomes(BiomeDictionary.Type.NETHER).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.FLAMEWALKER.get(), 60).matchBiomes(BiomeDictionary.Type.NETHER).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.INFERNAL.get(), 10).matchBiomes(BiomeDictionary.Type.NETHER).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.LITTLE_BAM.get(), 20).matchBiomes(BiomeDictionary.Type.NETHER).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SKELETAL_COWMAN.get(), 45).matchBiomes(BiomeDictionary.Type.NETHER).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.SCRUBBY.get(), 30).groupSize(1, 3).matchBiomes(BiomeDictionary.Type.NETHER).placeMonster();

        new SpawnEntry(AoAEntities.NPCs.WITHERING_LOTTOMAN.get(), 1).groupSize(0, 1).matchBiomes(BiomeDictionary.Type.NETHER).placeNPC();
        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).groupSize(0, 1).matchBiomes(BiomeDictionary.Type.NETHER).placeNPC();
    }

    public static void setLboreanSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.CORATEE.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAEntities.Animals.GLOWING_PIXON.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAEntities.Mobs.ANGLER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CORALON.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.MUNCHER.get(), IN_WATER, OCEAN_FLOOR, SpawnPredicates.WATER_MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.NEPTUNO.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SEA_VIPER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);
    }

    public static void setLelyetiaSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.GLARING_PIXON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Animals.TROTTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Mobs.FLYE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.GROBBLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.LELYETIAN_CASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.LELYETIAN_WARRIOR.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.TRACKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.LOGGING_MASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void populateLunalusSpawnEntries() {
        new SpawnEntry(AoAEntities.Mobs.EXPLODOT.get(), 20).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "lunalus")).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.LUNARCHER.get(), 20).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "lunalus")).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.VISULAR.get(), 20).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "lunalus")).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.MODULO.get(), 15).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "lunalus")).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.TERRESTRIAL.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "lunalus")).placeMonster();
        new SpawnEntry(AoAEntities.Mobs.VISULON.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "lunalus")).placeMonster();

        new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1).groupSize(0, 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "lunalus")).placeNPC();
    }

    public static void setMysteriumSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.BLOOMING_PIXON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAEntities.Mobs.FUNGAT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.FUNGBACK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.FUNGIK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.FUNGUNG.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.RUNATION_MASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setPrecasiaSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.MEGANEUROPSIS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Mobs.DEINOTHERIUM.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DYREHORN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.GIANT_SNAIL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SABRETOOTH.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.TERRADON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.TORTIONE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setRunandorSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.GLEAMING_PIXON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAEntities.Mobs.ARIEL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.BOUNCER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.PALADIN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.RUNICORN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.RUNICORN_RIDER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.ANIMA_MASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setShyrelandsSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Mobs.ARCBEAST.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.ARC_FLOWER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.AXIOLIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.OMNILIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SHYRE_KNIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SOULVYRE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.STIMULO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.STIMULOSUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SYSKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.EXPEDITION_MASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    public static void setVoxPondsSpawnPlacements() {
        setSpawnPlacement(AoAEntities.Animals.NIGHT_WATCHER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAEntities.Mobs.ALARMO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.CENTINEL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.DESTRUCTOR.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.FISCHER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.GADGETOID.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.SLIMER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.Mobs.TOXXULOUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAEntities.NPCs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.NPC);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(BiomeLoadingEvent ev) {
        if (ev.getName() == null)
            return;

        RegistryKey<Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, ev.getName());

        for (SpawnEntry<?> entry : SPAWN_ENTRIES) {
            if (entry.shouldSpawnInBiome(biomeKey))
                ev.getSpawns().getSpawner(entry.entityType.getCategory()).add(new MobSpawnInfo.Spawners(entry.entityType, entry.weight, entry.minGroupSize, entry.maxGroupSize));
        }
    }

    public static class SpawnEntry<T extends MobEntity> {
        private final EntityType<T> entityType;
        private final int weight;
        private int minGroupSize = 1;
        private int maxGroupSize = 1;
        @Nullable
        private HashSet<BiomeDictionary.Type> matchingBiomeTypes = null;
        @Nullable
        private HashSet<BiomeDictionary.Type> blacklistedBiomeTypes = null;
        @Nullable
        private HashSet<RegistryKey<Biome>> matchingBiomes = null;

        public SpawnEntry(EntityType<T> entityType, int weight) {
            this.entityType = entityType;
            this.weight = weight;

            SPAWN_ENTRIES.add(this);
        }

        public EntityType<T> getEntityType() {
            return this.entityType;
        }

        public int getWeight() {
            return this.weight;
        }

        public int getMinGroupSize() {
            return this.minGroupSize;
        }

        public int getMaxGroupSize() {
            return this.maxGroupSize;
        }

        public SpawnEntry<T> groupSize(int minGroupSize, int maxGroupSize) {
            this.minGroupSize = minGroupSize;
            this.maxGroupSize = Math.max(minGroupSize, maxGroupSize);

            return this;
        }

        private SpawnEntry<T> matchBiomes(BiomeDictionary.Type... biomeTypes) {
            this.matchingBiomeTypes = new HashSet<BiomeDictionary.Type>(Arrays.asList(biomeTypes));

            return this;
        }

        private SpawnEntry<T> ignoreBiomes(BiomeDictionary.Type... biomeTypes) {
            this.blacklistedBiomeTypes = new HashSet<BiomeDictionary.Type>(Arrays.asList(biomeTypes));

            return this;
        }

        private SpawnEntry<T> matchExactBiomes(ResourceLocation... biomeIds) {
            matchingBiomes = new HashSet<RegistryKey<Biome>>();

            for (ResourceLocation id : biomeIds) {
                matchingBiomes.add(RegistryKey.create(Registry.BIOME_REGISTRY, id));
            }

            return this;
        }

        private boolean shouldSpawnInBiome(RegistryKey<Biome> biomeKey) {
    	    if (matchingBiomes != null)
    	        return matchingBiomes.contains(biomeKey);

    	    if (matchingBiomeTypes != null) {
    	        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);

    	        if (!types.contains(BiomeDictionary.Type.OVERWORLD) || (matchingBiomeTypes.contains(BiomeDictionary.Type.NETHER) && !types.contains(BiomeDictionary.Type.NETHER)))
    	            return false;

    	        if (blacklistedBiomeTypes != null) {
    	            for (BiomeDictionary.Type type : blacklistedBiomeTypes) {
    	                if (types.contains(type))
    	                    return false;
                    }
                }

    	        for (BiomeDictionary.Type type : matchingBiomeTypes) {
    	            if (types.contains(type))
    	                return true;
                }
            }

    	    return false;
        }

        private SpawnEntry<T> placeNPC() {
            return place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, (EntitySpawnPlacementRegistry.IPlacementPredicate<T>)SpawnPredicates.NPC);
        }

        private SpawnEntry<T> placeMonster() {
            return place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, (EntitySpawnPlacementRegistry.IPlacementPredicate<T>)SpawnPredicates.MONSTER);
        }

        private SpawnEntry<T> place(EntitySpawnPlacementRegistry.PlacementType type, Heightmap.Type heightmap, EntitySpawnPlacementRegistry.IPlacementPredicate<T> spawnPredicate) {
            setSpawnPlacement(entityType, type, heightmap, spawnPredicate);

    	    return this;
        }
	}

	private static <T extends MobEntity> void setSpawnPlacement(EntityType<T> entityType, EntitySpawnPlacementRegistry.PlacementType placementType, Heightmap.Type heightmap, EntitySpawnPlacementRegistry.IPlacementPredicate<? extends MobEntity> spawnPredicate) {
        try {
            if (EntitySpawnPlacementRegistry.getPlacementType(entityType) == NO_RESTRICTIONS)
                EntitySpawnPlacementRegistry.register(entityType, placementType, heightmap, (EntitySpawnPlacementRegistry.IPlacementPredicate<T>)spawnPredicate);
        }
        catch (IllegalStateException ex) {
            Logging.logMessage(Level.WARN, "Caught duplicate spawn placement registration from: " + entityType.getRegistryName().toString());
        }
    }

	private static final class SpawnPredicates {
        private static final EntitySpawnPlacementRegistry.IPlacementPredicate<MobEntity> NPC = (EntityType<MobEntity> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random rand) -> {
            if (!MobEntity.checkMobSpawnRules(type, world, reason, pos, rand))
                return false;

            return WorldUtil.getLightLevel(world, pos, false, false) >= 8;
        };

        private static final EntitySpawnPlacementRegistry.IPlacementPredicate<MobEntity> MONSTER = (EntityType<MobEntity> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random rand) -> {
              if (world.getDifficulty() == Difficulty.PEACEFUL)
                return false;

              boolean isVanillaWorld = WorldUtil.isWorld(world, World.OVERWORLD, World.NETHER);

            if (EntityUtil.isNaturalSpawnReason(reason)) {
                if (AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && !world.dimensionType().hasFixedTime() && !world.getLevel().isDay() && isVanillaWorld)
                    return false;

                if (AoAEntityData.SpawnConditions.SPAWN_HEIGHTS.containsKey(type) && pos.getY() > AoAEntityData.SpawnConditions.SPAWN_HEIGHTS.get(type))
                    return false;

                if (!world.getBiome(pos).getRegistryName().getNamespace().equals(AdventOfAscension.MOD_ID) && !isVanillaWorld)
                    return false;
            }

            if (!MobEntity.checkMobSpawnRules(type, world, reason, pos, rand))
                return false;

            if (!AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && isVanillaWorld)
                return MonsterEntity.isDarkEnoughToSpawn(world, pos, rand);

            return WorldUtil.getLightLevel(world, pos, true, false) <= RandomUtil.randomNumberUpTo(8);
        };

        private static final EntitySpawnPlacementRegistry.IPlacementPredicate<MobEntity> WATER_MONSTER = (EntityType<MobEntity> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random rand) -> {
                if (world.getDifficulty() == Difficulty.PEACEFUL)
                    return false;

            boolean isVanillaWorld = WorldUtil.isWorld(world, World.OVERWORLD, World.NETHER);

            if (EntityUtil.isNaturalSpawnReason(reason)) {
                if (world.getLevel().isDay()) {
                    if (!AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && !world.dimensionType().hasFixedTime() && isVanillaWorld)
                        return false;
                }
                else if (!world.dimensionType().hasFixedTime() && !world.getLevel().dimension().location().getNamespace().equals(AdventOfAscension.MOD_ID) && AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type)) {
                    return false;
                }

                if (AoAEntityData.SpawnConditions.SPAWN_HEIGHTS.containsKey(type) && pos.getY() > AoAEntityData.SpawnConditions.SPAWN_HEIGHTS.get(type))
                    return false;

                if (!world.getBiome(pos).getRegistryName().getNamespace().equals(AdventOfAscension.MOD_ID) && !isVanillaWorld)
                    return false;
            }

            if (!world.getBlockState(pos).getFluidState().getType().is(FluidTags.WATER))
                return false;

            if (!AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && isVanillaWorld)
                return MonsterEntity.isDarkEnoughToSpawn(world, pos, rand);

            return WorldUtil.getLightLevel(world, pos, true, false) <= RandomUtil.randomNumberUpTo(8);
        };

        private static <T extends MobEntity> EntitySpawnPlacementRegistry.IPlacementPredicate<T> animalPredicate(ITag<Block> blockTag, boolean spawnsInDarkness) {
            return (EntityType<T> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random rand) -> {
                if (!world.getBlockState(pos.below()).is(blockTag))
                    return false;

                return spawnsInDarkness || world.getRawBrightness(pos, 0) >= 8;
            };
        }
    }
}
