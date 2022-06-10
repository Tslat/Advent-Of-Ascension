package net.tslat.aoa3.common.registration;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.common.registration.entity.AoANpcs;
import net.tslat.aoa3.content.entity.base.AbstractLavaFishEntity;
import net.tslat.aoa3.content.world.spawner.PixonSpawner;
import net.tslat.aoa3.content.world.spawner.TraderSpawner;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.BiConsumer;

import static net.minecraft.world.entity.SpawnPlacements.Type.*;
import static net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES;
import static net.minecraft.world.level.levelgen.Heightmap.Types.OCEAN_FLOOR;

public final class AoAEntitySpawns {
	private static final ArrayList<SpawnEntry> SPAWN_ENTRIES = new ArrayList<SpawnEntry>();

    public static void preInit() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, false, BiomeLoadingEvent.class, AoAEntitySpawns::onBiomeLoad);
    }

    public static void registerEntitySpawns() {
        Logging.logStatusMessage("Registering entity spawns");

        populateOverworldSpawnEntries();

        /*setAbyssSpawnPlacements();
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
        setVoxPondsSpawnPlacements();*/

        //new SpawnEntry(AoAAnimals.AMBIENT_PIXON.get(), 12).groupSize(1, 4).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "haven"), new ResourceLocation(AdventOfAscension.MOD_ID, "runic_cliffs"), new ResourceLocation(AdventOfAscension.MOD_ID, "candy_hills"), new ResourceLocation(AdventOfAscension.MOD_ID, "shyre_remnants")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true)).specialSpawner(PixonSpawner::addSpawn);
    }

    public static void populateOverworldSpawnEntries() {
        new SpawnEntry(AoAMobs.ICE_GIANT.get(), 1).groupSize(-50, 1).matchBiomes(BiomeDictionary.Type.SNOWY).placeMonster();
        new SpawnEntry(AoAMobs.LEAFY_GIANT.get(), 1).groupSize(-50, 1).matchBiomes(BiomeDictionary.Type.CONIFEROUS).ignoreBiomes(BiomeDictionary.Type.SNOWY).placeMonster();
        new SpawnEntry(AoAMobs.STONE_GIANT.get(), 1).groupSize(-50, 1).matchBiomes(BiomeDictionary.Type.MOUNTAIN).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.LUSH).placeMonster();

        new SpawnEntry(AoAAnimals.SHINY_SQUID.get(), 1).groupSize(-1000, 1).matchBiomes(BiomeDictionary.Type.OCEAN).place(IN_WATER, MOTION_BLOCKING_NO_LEAVES, GlowSquid::checkGlowSquideSpawnRules);

        setSpawnPlacement(AoAAnimals.BLUE_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.CANDLEFISH.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, AbstractLavaFishEntity::checkFishSpawnRules);
        setSpawnPlacement(AoAAnimals.CHARRED_CHAR.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, AbstractLavaFishEntity::checkFishSpawnRules);
        setSpawnPlacement(AoAAnimals.CHOCAW.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.CRIMSON_SKIPPER.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, AbstractLavaFishEntity::checkFishSpawnRules);
        setSpawnPlacement(AoAAnimals.CRIMSON_STRIPEFISH.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, AbstractLavaFishEntity::checkFishSpawnRules);
        setSpawnPlacement(AoAAnimals.DARK_HATCHETFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.GREEN_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.HYDRONE.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.IRONBACK.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.JAMFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.PARAPIRANHA.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.PEARL_STRIPEFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.PURPLE_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.RAINBOWFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.RAZORFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.RED_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.REEFTOOTH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.ROCKETFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.SAILBACK.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.SAPPHIRE_STRIDER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.SKELECANTH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.WHITE_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.YELLOW_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.TURQUOISE_STRIPEFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.VIOLET_SKIPPER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);

        /*new SpawnEntry(AoAMobs.BOMB_CARRIER.get(), 10).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAMobs.EVERBEAST.get(), 25).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAMobs.GHOST.get(), 85).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAMobs.MOTHER_VOID_WALKER.get(), 85).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAMobs.SHADOW.get(), 85).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAMobs.TRICKSTER.get(), 60).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAMobs.VOID_CHARGER.get(), 85).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAMobs.VOID_WALKER.get(), 85).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAMobs.BUGEYE.get(), 90).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();
        new SpawnEntry(AoAMobs.NIGHT_REAPER.get(), 55).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.OVERWORLD).placeMonster();

        new SpawnEntry(AoAMobs.WOOD_GIANT.get(), 1).groupSize(-50, 1).matchBiomes(BiomeDictionary.Type.FOREST).placeMonster();

        new SpawnEntry(AoAMobs.GOALBY.get(), 80).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.MOUNTAIN).placeMonster();
        new SpawnEntry(AoAMobs.STONE_GIANT.get(), 1).groupSize(-50, 1).matchBiomes(BiomeDictionary.Type.MOUNTAIN).placeMonster();

        new SpawnEntry(AoAMobs.YETI.get(), 70).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SNOWY).placeMonster();
        new SpawnEntry(AoAMobs.SNOW_CHARGER.get(), 70).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SNOWY).placeMonster();

        new SpawnEntry(AoAMobs.DESERT_CHARGER.get(), 30).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAMobs.FURLION.get(), 30).groupSize(1, 3).matchBiomes(BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAMobs.SAND_GOLEM.get(), 30).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAMobs.SPHINX.get(), 30).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAMobs.SAND_GIANT.get(), 1).groupSize(-50, 1).matchBiomes(BiomeDictionary.Type.SANDY).placeMonster();

        new SpawnEntry(AoAMobs.CHIMERA.get(), 75).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SAVANNA).placeMonster();
        new SpawnEntry(AoAMobs.BONEBACK.get(), 75).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SAVANNA).placeMonster();
        new SpawnEntry(AoAAnimals.ELKANYNE.get(), 20).groupSize(2, 4).matchBiomes(BiomeDictionary.Type.SAVANNA).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));

        new SpawnEntry(AoAMobs.BUSH_BABY.get(), 70).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.JUNGLE).placeMonster();

        new SpawnEntry(AoAMobs.CHOMPER.get(), 70).matchBiomes(BiomeDictionary.Type.SWAMP).placeMonster();
        new SpawnEntry(AoAMobs.FISHIX.get(), 70).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SWAMP).placeMonster();
        new SpawnEntry(AoAMobs.SWAMP_CHARGER.get(), 70).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.SWAMP).placeMonster();
        new SpawnEntry(AoAMobs.HAG.get(), 70).matchBiomes(BiomeDictionary.Type.SWAMP).placeMonster();

        new SpawnEntry(AoAMobs.HIDING_FUNGI.get(), 40).matchBiomes(BiomeDictionary.Type.CONIFEROUS).ignoreBiomes(BiomeDictionary.Type.SNOWY).placeMonster();
        new SpawnEntry(AoAMobs.LEAFY_GIANT.get(), 1).groupSize(-50, 1).matchBiomes(BiomeDictionary.Type.CONIFEROUS).ignoreBiomes(BiomeDictionary.Type.SNOWY).placeMonster();

        new SpawnEntry(AoAMobs.HILL_CHARGER.get(), 50).groupSize(1, 3).matchBiomes(BiomeDictionary.Type.MESA).placeMonster();
        new SpawnEntry(AoAMobs.MUCKOPEDE.get(), 50).matchBiomes(BiomeDictionary.Type.MESA).placeMonster();

        new SpawnEntry(AoAMobs.PINCHER.get(), 1).matchBiomes(BiomeDictionary.Type.OCEAN).place(IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);

        new SpawnEntry(AoAMobs.SEA_CHARGER.get(), 20).groupSize(1, 3).matchBiomes(BiomeDictionary.Type.BEACH).placeMonster();
        new SpawnEntry(AoAMobs.SEA_TROLL.get(), 20).matchBiomes(BiomeDictionary.Type.BEACH).placeMonster();
        new SpawnEntry(AoANpcs.TROLL_TRADER.get(), 1).matchBiomes(BiomeDictionary.Type.BEACH).placeNPC().specialSpawner(TraderSpawner::addSpawn);

        new SpawnEntry(AoAMobs.CYCLOPS.get(), 20).groupSize(1, 4).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAMobs.CHARGER.get(), 20).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAMobs.SASQUATCH.get(), 20).groupSize(1, 3).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAMobs.KING_CHARGER.get(), 1).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAMobs.GOBLIN.get(), 20).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAMobs.NIGHTFLY.get(), 20).groupSize(1, 3).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAMobs.HORNDRON.get(), 5).matchBiomes(BiomeDictionary.Type.PLAINS).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();
        new SpawnEntry(AoAMobs.ANCIENT_GOLEM.get(), 5).groupSize(1, 2).matchBiomes(BiomeDictionary.Type.PLAINS).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeMonster();

        new SpawnEntry(AoAMobs.DEAD_TREE.get(), 10).matchExactBiomes(new ResourceLocation("forest"), new ResourceLocation("wooded_hills")).placeMonster();

        new SpawnEntry(AoAAnimals.SHINY_SQUID.get(), 1).groupSize(0, 1).matchBiomes(BiomeDictionary.Type.OCEAN).place(IN_WATER, MOTION_BLOCKING_NO_LEAVES, ShinySquidEntity::checkShinySquidSpawnRules);
        new SpawnEntry(AoAAnimals.GLISTENING_PIXON.get(), 30).groupSize(0, 4).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true)).specialSpawner(PixonSpawner::addSpawn);
        new SpawnEntry(AoANpcs.ASSASSIN.get(), 1).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeNPC().specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoANpcs.NATURALIST.get(), 1).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeNPC().specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoANpcs.REALMSHIFTER.get(), 1).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeNPC().specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeNPC().specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoANpcs.LOTTOMAN.get(), 1).matchBiomes(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST).ignoreBiomes(BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY).placeNPC().specialSpawner(TraderSpawner::addSpawn);

        */
    }

    private static void setAbyssSpawnPlacements() {
        setSpawnPlacement(AoAMobs.ANEMIA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.APPARITION.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.BLOODSUCKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.DISTORTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.FIEND.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.OCCULENT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.WEB_REAPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "abyssal_plains")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoAAnimals.SHINING_PIXON.get(), 12).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "abyssal_plains")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true)).specialSpawner(PixonSpawner::addSpawn);
    }

    public static void setBarathosSpawnPlacements() {
        setSpawnPlacement(AoAMobs.ARKBACK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CRYPTID.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.ECHODAR.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.EMPEROR_BEAST.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.KEELER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.NOSPIKE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.PARASECT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.RAMRADON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SQUIGGLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.THARAFLY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "barren_grounds")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setCandylandSpawnPlacements() {
        setSpawnPlacement(AoAAnimals.PEPPERMINT_SNAIL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAAnimals.SPEARMINT_SNAIL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAMobs.AIRHEAD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CANDY_CORNY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CHERRY_BLASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.LOLLYPOPPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "candy_hills")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setCeleveSpawnPlacements() {
        setSpawnPlacement(AoAMobs.BOBO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CHOCKO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.JUMBO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.KOKO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.KRANKY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.HAPPY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SNAPPY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.STICKY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.STITCHES.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.TIPSY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "celevian_highlands")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setCreeponiaSpawnPlacements() {
        setSpawnPlacement(AoAAnimals.CREEP_COW.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAMobs.BONE_CREEPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CAVE_CREEPOID.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CREEPERLOCK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CREEPIRD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CREEPUPLE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.HOST.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.KING_CREEPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.MAGICAL_CREEPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.WINGED_CREEPER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "creepoid_forest")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setCrysteviaSpawnPlacements() {
        setSpawnPlacement(AoAMobs.CONSTRUCT_OF_FLIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CONSTRUCT_OF_MIND.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CONSTRUCT_OF_RANGE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CONSTRUCT_OF_RESISTANCE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CONSTRUCT_OF_SPEED.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CONSTRUCT_OF_STRENGTH.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CONSTRUCT_OF_TERROR.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "crystevian_caverns")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setDeeplandsSpawnPlacements() {
        setSpawnPlacement(AoAAnimals.SHIK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(Tags.Blocks.STONE, true));
        setSpawnPlacement(AoAMobs.CASE_CONSTRUCT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CAVE_CREEP.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.DOUBLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.DWELLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.ROCKBITER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.ROCK_CRAWLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.ROCK_CRITTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "cavern_depths")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setDustopiaSpawnPlacements() {
        setSpawnPlacement(AoAAnimals.URKA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAMobs.BASILISK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.DEVOURER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.DUSTEIVA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.DUSTON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.LOST_SOUL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.LURKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.STALKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAAnimals.RADIANT_PIXON.get(), 12).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "dustopian_forest")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true)).specialSpawner(PixonSpawner::addSpawn);
        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "dustopian_forest")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setGardenciaSpawnPlacements() {
        setSpawnPlacement(AoAMobs.ARCHVINE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.BROCCOHEAD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CARROTOP.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CORNY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.FLOWERFACE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SQUASHER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SUNNY.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "floral_islands")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setGreckonSpawnPlacements() {
        setSpawnPlacement(AoAMobs.GRILLFACE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.HUNTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SHIFTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SILENCER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SKULL_CREATURE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SUGARFACE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.VALKYRIE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "greckon_mountains")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setHavenSpawnPlacements() {
        setSpawnPlacement(AoAAnimals.ANGELICA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAAnimals.DAWNLIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAAnimals.EEO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAAnimals.HALYCON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAAnimals.RAINICORN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAAnimals.VOLIANT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));

        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "haven")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setIromineSpawnPlacements() {
        setSpawnPlacement(AoAMobs.MECHACHRON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.MECHAMATON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.POLYTOM.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.VOLTRON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoANpcs.METALLOID.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "iromine")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "iromine")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void populateNetherSpawnEntries() {
        new SpawnEntry(AoAMobs.EMBRAKE.get(), 60).matchBiomes(BiomeDictionary.Type.NETHER).ignoreExactBiomes(new ResourceLocation("basalt_deltas"), new ResourceLocation("warped_forest"), new ResourceLocation("soul_sand_valley")).placeMonster();
        new SpawnEntry(AoAMobs.FLAMEWALKER.get(), 60).matchBiomes(BiomeDictionary.Type.NETHER).ignoreExactBiomes(new ResourceLocation("basalt_deltas"), new ResourceLocation("warped_forest"), new ResourceLocation("soul_sand_valley")).placeMonster();
        new SpawnEntry(AoAMobs.INFERNAL.get(), 10).matchBiomes(BiomeDictionary.Type.NETHER).ignoreExactBiomes(new ResourceLocation("basalt_deltas"), new ResourceLocation("warped_forest"), new ResourceLocation("soul_sand_valley")).placeMonster();
       // new SpawnEntry(AoAMobs.LITTLE_BAM.get(), 20).matchBiomes(BiomeDictionary.Type.NETHER).ignoreExactBiomes(new ResourceLocation("basalt_deltas"), new ResourceLocation("warped_forest"), new ResourceLocation("soul_sand_valley")).placeMonster();
        new SpawnEntry(AoAMobs.SKELETAL_COWMAN.get(), 45).matchBiomes(BiomeDictionary.Type.NETHER).ignoreExactBiomes(new ResourceLocation("basalt_deltas"), new ResourceLocation("warped_forest")).placeMonster();
        new SpawnEntry(AoAMobs.SCRUBBY.get(), 30).groupSize(1, 3).matchBiomes(BiomeDictionary.Type.NETHER).ignoreExactBiomes(new ResourceLocation("basalt_deltas"), new ResourceLocation("warped_forest"), new ResourceLocation("soul_sand_valley")).placeMonster();

        new SpawnEntry(AoANpcs.LOTTOMAN.get(), 1).matchBiomes(BiomeDictionary.Type.NETHER).placeNPC().specialSpawner(TraderSpawner::addSpawn);
        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchBiomes(BiomeDictionary.Type.NETHER).placeNPC().specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setLboreanSpawnPlacements() {
        setSpawnPlacement(AoAAnimals.CORATEE.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(null, true));
        setSpawnPlacement(AoAMobs.ANGLER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);
        setSpawnPlacement(AoAMobs.MUNCHER.get(), IN_WATER, OCEAN_FLOOR, SpawnPredicates.WATER_MONSTER);
        setSpawnPlacement(AoAMobs.NEPTUNO.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);
        setSpawnPlacement(AoAMobs.SEA_VIPER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);

        new SpawnEntry(AoAAnimals.GLOWING_PIXON.get(), 12).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "coral_fields")).place(IN_WATER, OCEAN_FLOOR, SpawnPredicates.animalPredicate(null, true)).specialSpawner(PixonSpawner::addSpawn);
        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "coral_fields")).place(IN_WATER, OCEAN_FLOOR, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setLelyetiaSpawnPlacements() {
        setSpawnPlacement(AoAAnimals.TROTTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAMobs.FLYE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.GROBBLER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.LELYETIAN_CASTER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.LELYETIAN_WARRIOR.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.TRACKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAAnimals.GLARING_PIXON.get(), 12).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "lelyetian_plains")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false)).specialSpawner(PixonSpawner::addSpawn);
        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "lelyetian_plains")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void populateLunalusSpawnEntries() {
        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "asteroid_belt")).placeNPC().specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setMysteriumSpawnPlacements() {
        setSpawnPlacement(AoAMobs.FUNGAT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.FUNGBACK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.FUNGIK.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.FUNGUNG.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAAnimals.BLOOMING_PIXON.get(), 12).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "fungal_caverns")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true)).specialSpawner(PixonSpawner::addSpawn);
        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "fungal_caverns")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setPrecasiaSpawnPlacements() {
        setSpawnPlacement(AoAAnimals.MEGANEUROPSIS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false));
        setSpawnPlacement(AoAMobs.DEINOTHERIUM.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.DYREHORN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.GIANT_SNAIL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SABRETOOTH.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.TERRADON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.TORTIONE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "precasian_tall_forest")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setRunandorSpawnPlacements() {
        setSpawnPlacement(AoAMobs.ARIEL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.BOUNCER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.PALADIN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.RUNICORN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.RUNICORN_RIDER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoAAnimals.GLEAMING_PIXON.get(), 12).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "runic_cliffs")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, false)).specialSpawner(PixonSpawner::addSpawn);
        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "runic_cliffs")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setShyrelandsSpawnPlacements() {
        setSpawnPlacement(AoAMobs.ARCBEAST.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.ARC_FLOWER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.AXIOLIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.OMNILIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SHYRE_KNIGHT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SOULVYRE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SOULSCORNE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.STIMULO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.STIMULOSUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SYSKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "shyre_remnants")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false)).specialSpawner(TraderSpawner::addSpawn);
    }

    public static void setVoxPondsSpawnPlacements() {
        setSpawnPlacement(AoAAnimals.NIGHT_WATCHER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.animalPredicate(AoATags.Blocks.GRASS, true));
        setSpawnPlacement(AoAMobs.ALARMO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.CENTINEL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.DESTRUCTOR.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.FISCHER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);
        setSpawnPlacement(AoAMobs.GADGETOID.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.SLIMER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        setSpawnPlacement(AoAMobs.TOXXULOUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);

        new SpawnEntry(AoANpcs.UNDEAD_HERALD.get(), 1).matchExactBiomes(new ResourceLocation(AdventOfAscension.MOD_ID, "vox_wastes")).place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true)).specialSpawner(TraderSpawner::addSpawn);
    }

    private static void onBiomeLoad(BiomeLoadingEvent ev) {
        if (ev.getName() == null)
            return;

        ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, ev.getName());

        for (SpawnEntry entry : SPAWN_ENTRIES) {
            if (entry.shouldSpawnInBiome(biomeKey)) {
                MobSpawnSettings.SpawnerData spawnData = new MobSpawnSettings.SpawnerData(entry.entityType, entry.weight, entry.minGroupSize, entry.maxGroupSize);

                if (entry.specialSpawnerConsumer != null) {
                    entry.specialSpawnerConsumer.accept(biomeKey, spawnData);
                }
                else {
                    ev.getSpawns().getSpawner(entry.entityType.getCategory()).add(spawnData);
                }
            }
        }
    }

    public static class SpawnEntry {
        private final EntityType<? extends Mob> entityType;
        private final int weight;
        private int minGroupSize = 1;
        private int maxGroupSize = 1;
        @Nullable
        private HashSet<BiomeDictionary.Type> matchingBiomeTypes = null;
        @Nullable
        private HashSet<BiomeDictionary.Type> blacklistedBiomeTypes = null;
        @Nullable
        private HashSet<ResourceKey<Biome>> matchingBiomes = null;
        @Nullable
        private HashSet<ResourceKey<Biome>> ignoringBiomes = null;
        @Nullable
        private BiConsumer<ResourceKey<Biome>, MobSpawnSettings.SpawnerData> specialSpawnerConsumer = null;

        public SpawnEntry(EntityType<? extends Mob> entityType, int weight) {
            this.entityType = entityType;
            this.weight = weight;

            SPAWN_ENTRIES.add(this);
        }

        public EntityType<? extends Mob> getEntityType() {
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

        public HashSet<BiomeDictionary.Type> getMatchingBiomeTypes() {
            return this.matchingBiomeTypes;
        }

        public HashSet<BiomeDictionary.Type> getBlacklistedBiomeTypes() {
            return this.blacklistedBiomeTypes;
        }

        public HashSet<ResourceKey<Biome>> getMatchingBiomes() {
            return this.matchingBiomes;
        }

        public HashSet<ResourceKey<Biome>> getIgnoredBiomes() {
            return this.ignoringBiomes;
        }

        public SpawnEntry groupSize(int minGroupSize, int maxGroupSize) {
            this.minGroupSize = minGroupSize;
            this.maxGroupSize = Math.max(minGroupSize, maxGroupSize);

            return this;
        }

        private SpawnEntry matchBiomes(BiomeDictionary.Type... biomeTypes) {
            this.matchingBiomeTypes = new HashSet<BiomeDictionary.Type>(Arrays.asList(biomeTypes));

            return this;
        }

        private SpawnEntry ignoreBiomes(BiomeDictionary.Type... biomeTypes) {
            this.blacklistedBiomeTypes = new HashSet<BiomeDictionary.Type>(Arrays.asList(biomeTypes));

            return this;
        }

        private SpawnEntry matchExactBiomes(ResourceLocation... biomeIds) {
            matchingBiomes = new HashSet<ResourceKey<Biome>>();

            for (ResourceLocation id : biomeIds) {
                matchingBiomes.add(ResourceKey.create(Registry.BIOME_REGISTRY, id));
            }

            return this;
        }

        private SpawnEntry ignoreExactBiomes(ResourceLocation... biomeIds) {
            ignoringBiomes = new HashSet<ResourceKey<Biome>>();

            for (ResourceLocation id : biomeIds) {
                ignoringBiomes.add(ResourceKey.create(Registry.BIOME_REGISTRY, id));
            }

            return this;
        }

        private SpawnEntry specialSpawner(BiConsumer<ResourceKey<Biome>, MobSpawnSettings.SpawnerData> specialSpawnerConsumer) {
            this.specialSpawnerConsumer = specialSpawnerConsumer;

            return this;
        }

        private boolean shouldSpawnInBiome(ResourceKey<Biome> biomeKey) {
    	    if (matchingBiomes != null)
    	        return matchingBiomes.contains(biomeKey);

    	    if (ignoringBiomes != null && ignoringBiomes.contains(biomeKey))
    	        return false;

    	    if (biomeKey == Biomes.MUSHROOM_FIELDS)
    	        return false;

    	    if (matchingBiomeTypes != null) {
    	        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);

    	        if (matchingBiomeTypes.contains(BiomeDictionary.Type.NETHER)) {
    	            if (!types.contains(BiomeDictionary.Type.NETHER))
    	                return false;
                }
    	        else if (!types.contains(BiomeDictionary.Type.OVERWORLD)) {
    	            return false;
                }

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

        private SpawnEntry placeNPC() {
            return place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false));
        }

        private SpawnEntry placeMonster() {
            return place(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
        }

        private SpawnEntry place(SpawnPlacements.Type type, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<? extends Mob> spawnPredicate) {
            setSpawnPlacement(entityType, type, heightmap, spawnPredicate);

    	    return this;
        }
	}

	private static <T extends Mob> void setSpawnPlacement(EntityType<T> entityType, SpawnPlacements.Type placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<? extends Mob> spawnPredicate) {
        try {
            if (SpawnPlacements.getPlacementType(entityType) == NO_RESTRICTIONS)
                SpawnPlacements.register(entityType, placementType, heightmap, (SpawnPlacements.SpawnPredicate<T>)spawnPredicate);
        }
        catch (IllegalStateException ex) {
            Logging.logMessage(Level.WARN, "Caught duplicate spawn placement registration from: " + entityType.getRegistryName().toString());
        }
    }

	private static final class SpawnPredicates {
        private static final SpawnPlacements.SpawnPredicate<Mob> npcPredicate(boolean spawnsInDarkness) {
            return (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, Random rand) -> {
                if (!Mob.checkMobSpawnRules(type, world, reason, pos, rand))
                    return false;

                return spawnsInDarkness || WorldUtil.getLightLevel(world, pos, false, false) >= 8;
            };
        };

        private static final SpawnPlacements.SpawnPredicate<Mob> MONSTER = (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, Random rand) -> {
              if (world.getDifficulty() == Difficulty.PEACEFUL)
                return false;

              boolean isVanillaWorld = WorldUtil.isWorld(world, net.minecraft.world.level.Level.OVERWORLD, net.minecraft.world.level.Level.NETHER);

            if (EntityUtil.isNaturalSpawnReason(reason)) {
                if (AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && !world.dimensionType().hasFixedTime() && !world.getLevel().isDay() && isVanillaWorld)
                    return false;

                if (AoAEntityData.SpawnConditions.SPAWN_HEIGHTS.containsKey(type) && pos.getY() > AoAEntityData.SpawnConditions.SPAWN_HEIGHTS.get(type))
                    return false;

                if (!world.getBiome(pos).value().getRegistryName().getNamespace().equals(AdventOfAscension.MOD_ID) && !isVanillaWorld)
                    return false;
            }

            if (!Mob.checkMobSpawnRules(type, world, reason, pos, rand))
                return false;

            if (!AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && isVanillaWorld)
                return Monster.isDarkEnoughToSpawn(world, pos, rand);

            return WorldUtil.getLightLevel(world, pos, true, false) <= RandomUtil.randomNumberUpTo(8);
        };

        private static final SpawnPlacements.SpawnPredicate<Mob> WATER_MONSTER = (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, Random rand) -> {
                if (world.getDifficulty() == Difficulty.PEACEFUL)
                    return false;

            boolean isVanillaWorld = WorldUtil.isWorld(world, net.minecraft.world.level.Level.OVERWORLD, net.minecraft.world.level.Level.NETHER);

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

                if (!world.getBiome(pos).value().getRegistryName().getNamespace().equals(AdventOfAscension.MOD_ID) && !isVanillaWorld)
                    return false;
            }

            if (!world.getBlockState(pos).getFluidState().getType().is(FluidTags.WATER))
                return false;

            if (!AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && isVanillaWorld)
                return Monster.isDarkEnoughToSpawn(world, pos, rand);

            return WorldUtil.getLightLevel(world, pos, true, false) <= RandomUtil.randomNumberUpTo(8);
        };

        private static <T extends Mob> SpawnPlacements.SpawnPredicate<T> animalPredicate(@Nullable TagKey<Block> blockTag, boolean spawnsInDarkness) {
            return (EntityType<T> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, Random rand) -> {
                if (blockTag != null && !world.getBlockState(pos.below()).is(blockTag))
                    return false;

                return spawnsInDarkness || world.getRawBrightness(pos, 0) >= 8;
            };
        }

        private static final SpawnPlacements.SpawnPredicate<Mob> FISH = (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, Random rand) -> world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER);
    }
}
