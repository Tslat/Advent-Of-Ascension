package net.tslat.aoa3.common.registration;

import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.base.*;
import net.tslat.aoa3.entity.mob.creeponia.AoACreeponiaCreeper;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType.*;
import static net.minecraft.world.gen.Heightmap.Type.MOTION_BLOCKING;
import static net.minecraft.world.gen.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES;
import static net.tslat.aoa3.common.registration.AoABiomes.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public final class AoAEntitySpawns {
    private static boolean forceAllSpawns = false;

	private static final ArrayList<SpawnEntry<? extends MobEntity>> bigDaySpawns = new ArrayList<SpawnEntry<? extends MobEntity>>(5);
	private static final ArrayList<SpawnEntry<? extends MobEntity>> bloodHuntSpawns = new ArrayList<SpawnEntry<? extends MobEntity>>(3);
	private static final ArrayList<SpawnEntry<? extends MobEntity>> creepDaySpawns = new ArrayList<SpawnEntry<? extends MobEntity>>(1);
	private static final ArrayList<SpawnEntry<? extends MobEntity>> deathDaySpawns = new ArrayList<SpawnEntry<? extends MobEntity>>(4);
	private static final ArrayList<SpawnEntry<? extends MobEntity>> fullMoonSpawns = new ArrayList<SpawnEntry<? extends MobEntity>>(5);
	private static final ArrayList<SpawnEntry<? extends MobEntity>> lunarInvasionSpawns = new ArrayList<SpawnEntry<? extends MobEntity>>(5);
	private static final ArrayList<SpawnEntry<? extends MobEntity>> soulScurrySpawns = new ArrayList<SpawnEntry<? extends MobEntity>>(6);

    public static void registerEntitySpawns() {
        Logging.logStatusMessage("Registering entity spawns");

        if (!AoAConfig.SERVER.disableOverworldMobs.get()) {
            if (ModList.get().isLoaded("openterraingenerator"))
                forceAllSpawns = true;

            addSpawns(getOverworldSpawns(forceAllSpawns));
        }

        addSpawns(getNetherSpawns());
        addSpawns(getDimensionSpawns());
    }

	public static HashSet<SpawnEntry<? extends MobEntity>> getOverworldSpawns(boolean includeEventSpawns) {
	    final HashSet<SpawnEntry<? extends MobEntity>> spawns = new HashSet<SpawnEntry<? extends MobEntity>>();
        final BiomeDictionary.Type[] emptyTypeList = new BiomeDictionary.Type[0];

        Biome[] overworldBiomes = getOverworldBiomes();
        Biome[] mountainBiomes = extractAllBiomesMatching(overworldBiomes, new BiomeDictionary.Type[]{BiomeDictionary.Type.MOUNTAIN}, emptyTypeList);
        Biome[] snowyBiomes = extractAllBiomesMatching(overworldBiomes, new BiomeDictionary.Type[]{BiomeDictionary.Type.SNOWY}, emptyTypeList);
        Biome[] sandyBiomes = extractAllBiomesMatching(overworldBiomes, new BiomeDictionary.Type[]{BiomeDictionary.Type.SANDY}, emptyTypeList);
        Biome[] savannaBiomes = extractAllBiomesMatching(overworldBiomes, new BiomeDictionary.Type[]{BiomeDictionary.Type.SAVANNA}, emptyTypeList);
        Biome[] jungleBiomes = extractAllBiomesMatching(overworldBiomes, new BiomeDictionary.Type[]{BiomeDictionary.Type.JUNGLE}, emptyTypeList);
        Biome[] swampBiomes = extractAllBiomesMatching(overworldBiomes, new BiomeDictionary.Type[]{BiomeDictionary.Type.SWAMP}, emptyTypeList);
        Biome[] warmConiferousBiomes = extractAllBiomesMatching(overworldBiomes, new BiomeDictionary.Type[]{BiomeDictionary.Type.CONIFEROUS}, new BiomeDictionary.Type[]{BiomeDictionary.Type.SNOWY});
        Biome[] mesaBiomes = extractAllBiomesMatching(overworldBiomes, new BiomeDictionary.Type[]{BiomeDictionary.Type.MESA}, emptyTypeList);
        Biome[] oceanBiomes = extractAllBiomesMatching(overworldBiomes, new BiomeDictionary.Type[]{BiomeDictionary.Type.OCEAN}, emptyTypeList);
        Biome[] beachBiomes = extractAllBiomesMatching(overworldBiomes, new BiomeDictionary.Type[]{BiomeDictionary.Type.BEACH}, emptyTypeList);
        Biome[] genericBiomes = extractAllBiomesMatching(overworldBiomes, new BiomeDictionary.Type[]{BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.FOREST}, new BiomeDictionary.Type[]{BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.SANDY});
        Biome[] genericPlainsBiomes = extractAllBiomesMatching(genericBiomes, new BiomeDictionary.Type[]{BiomeDictionary.Type.PLAINS}, emptyTypeList);
        
        spawns.add(new SpawnEntry(AoAEntities.Mobs.BOMB_CARRIER.get(), 10, 1, 1, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.EVERBEAST.get(), 25, 1, 1, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.FACELESS_RUNNER.get(), 85, 1, 1, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.GHOST.get(), 85, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.MOTHER_VOID_WALKER.get(), 85, 1, 1, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SHADOW.get(), 85, 1, 1, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.TRICKSTER.get(), 60, 1, 1, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.VOID_CHARGER.get(), 85, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.VOID_WALKER.get(), 85, 1, 1, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.BUGEYE.get(), 90, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.DICER.get(), 55, 1, 1, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.NIGHT_REAPER.get(), 55, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.DEMON_REAPER.get(), 21, 1, 1, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CLOWN.get(), 85, 1, 1, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        fullMoonSpawns.add(new SpawnEntry(AoAEntities.Mobs.IRKLING.get(), 120, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        deathDaySpawns.add(new SpawnEntry(AoAEntities.Mobs.REAPER_TWINS.get(), 80, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        lunarInvasionSpawns.add(new SpawnEntry(AoAEntities.Mobs.ROLOSCOPE.get(), 120, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        bigDaySpawns.add(new SpawnEntry(AoAEntities.Mobs.WOOD_GIANT.get(), 70, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        deathDaySpawns.add(new SpawnEntry(AoAEntities.Mobs.TRICLOPS.get(), 80, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        lunarInvasionSpawns.add(new SpawnEntry(AoAEntities.Mobs.VERTEBRON.get(), 120, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        lunarInvasionSpawns.add(new SpawnEntry(AoAEntities.Mobs.WALKER.get(), 90, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        deathDaySpawns.add(new SpawnEntry(AoAEntities.Mobs.HEADLESS_DESTROYER.get(), 80, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        bloodHuntSpawns.add(new SpawnEntry(AoAEntities.Mobs.BLOODMIST.get(), 80, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        bloodHuntSpawns.add(new SpawnEntry(AoAEntities.Mobs.LINGER.get(), 80, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        soulScurrySpawns.add(new SpawnEntry(AoAEntities.Mobs.GHOSTLY_BUGEYE.get(), 120, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        soulScurrySpawns.add(new SpawnEntry(AoAEntities.Mobs.GHOSTLY_CHARGER.get(), 120, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        soulScurrySpawns.add(new SpawnEntry(AoAEntities.Mobs.GHOSTLY_CYCLOPS.get(), 120, 1, 3, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        soulScurrySpawns.add(new SpawnEntry(AoAEntities.Mobs.GHOSTLY_NIGHT_REAPER.get(), 120, 1, 3, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        soulScurrySpawns.add(new SpawnEntry(AoAEntities.Mobs.GHOSTLY_GOBLIN.get(), 120, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        soulScurrySpawns.add(new SpawnEntry(AoAEntities.Mobs.GHOSTLY_SASQUATCH.get(), 120, 1, 3, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        lunarInvasionSpawns.add(new SpawnEntry(AoAEntities.Mobs.TERRESTRIAL.get(), 22, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        fullMoonSpawns.add(new SpawnEntry(AoAEntities.Mobs.SKELLOX.get(), 80, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        fullMoonSpawns.add(new SpawnEntry(AoAEntities.Mobs.SCRUBBY.get(), 80, 1, 3, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        fullMoonSpawns.add(new SpawnEntry(AoAEntities.Mobs.NIGHT_WATCHER.get(), 80, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        fullMoonSpawns.add(new SpawnEntry(AoAEntities.Mobs.DARK_BEAST.get(), 80, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        creepDaySpawns.add(new SpawnEntry(AoAEntities.Mobs.HOST.get(), 60, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        deathDaySpawns.add(new SpawnEntry(AoAEntities.Mobs.DEATH_HUNTER.get(), 60, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        bloodHuntSpawns.add(new SpawnEntry(AoAEntities.Mobs.ANEMIA.get(), 55, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingRangedMob::meetsSpawnConditions));
        lunarInvasionSpawns.add(new SpawnEntry(AoAEntities.Mobs.MODULO.get(), 120, 1, 2, overworldBiomes).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingRangedMob::meetsSpawnConditions));
        
        spawns.add(new SpawnEntry(AoAEntities.Mobs.GOALBY.get(), 80, 1, 2, mountainBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.GRUNT.get(), 40, 1, 2, mountainBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.MAGICKE.get(), 80, 1, 2, mountainBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        bigDaySpawns.add(new SpawnEntry(AoAEntities.Mobs.STONE_GIANT.get(), 80, 1, 1, mountainBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Mobs.HUNCH.get(), 70, 1, 2, snowyBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.POLAR_URSA.get(), 70, 1, 1, snowyBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.YETI.get(), 70, 1, 2, snowyBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SNOW_CHARGER.get(), 70, 1, 2, snowyBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        bigDaySpawns.add(new SpawnEntry(AoAEntities.Mobs.ICE_GIANT.get(), 70, 1, 1, snowyBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Mobs.DESERT_CHARGER.get(), 30, 1, 2, sandyBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.FURLION.get(), 30, 1, 3, sandyBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SAND_GOLEM.get(), 30, 1, 2, sandyBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SPHINX.get(), 30, 1, 2, sandyBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.WICKETT.get(), 30, 1, 2, sandyBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        bigDaySpawns.add(new SpawnEntry(AoAEntities.Mobs.SAND_GIANT.get(), 30, 1, 1, sandyBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Mobs.CHIMERA.get(), 75, 1, 2, savannaBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.BONEBACK.get(), 75, 1, 2, savannaBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.RAMMERHEAD.get(), 75, 1, 1, savannaBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Animals.ELKANYNE.get(), 20, 2, 4, savannaBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Mobs.BUSH_BABY.get(), 70, 1, 2, jungleBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.STINGER.get(), 70, 1, 1, jungleBiomes).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Mobs.CHOMPER.get(), 70, 1, 1, swampBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SKIPPER.get(), 30, 1, 1, swampBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.FISHIX.get(), 70, 1, 2, swampBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SWAMP_CHARGER.get(), 70, 1, 2, swampBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.HAG.get(), 70, 1, 1, swampBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Mobs.BLACK_URSA.get(), 70, 1, 1, warmConiferousBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.HIDING_FUNGI.get(), 40, 1, 1, warmConiferousBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.NATURA.get(), 70, 1, 1, warmConiferousBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SKOLLE.get(), 15, 1, 3, warmConiferousBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.URKA.get(), 70, 1, 1, warmConiferousBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        bigDaySpawns.add(new SpawnEntry(AoAEntities.Mobs.LEAFY_GIANT.get(), 70, 1, 1, warmConiferousBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Mobs.HILL_CHARGER.get(), 50, 1, 3, mesaBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.MUCKOPEDE.get(), 50, 1, 1, mesaBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Mobs.PINCHER.get(), 1, 1, 1, oceanBiomes).placement(IN_WATER, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Mobs.SEA_CHARGER.get(), 20, 1, 3, beachBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SPINUX.get(), 20, 1, 1, beachBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SEA_TROLL.get(), 20, 1, 1, beachBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.TROLL_TRADER.get(), 1, 0, 1, beachBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Mobs.CYCLOPS.get(), 20, 1, 4, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CHARGER.get(), 20, 1, 2, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SASQUATCH.get(), 20, 1, 3, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.HEADLESS_HUNTER.get(), 20, 1, 1, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Animals.GLISTENING_PIXON.get(), 30, 1, 4, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.BONE_CREATURE.get(), 20, 1, 1, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.KING_CHARGER.get(), 1, 1, 1, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.BRUTE.get(), 20, 1, 3, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.GHOSTINE_ANCIENT.get(), 20, 1, 2, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.GOBLIN.get(), 20, 1, 1, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.ASSASSIN.get(), 1, 0, 1, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.NATURALIST.get(), 1, 0, 1, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.REALMSHIFTER.get(), 1, 0, 1, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.LOTTOMAN.get(), 1, 0, 1, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.NIGHTFLY.get(), 20, 1, 3, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));

        if (AoAConfig.SERVER.STRUCTURES.ruinedTeleporterFrameSpawnChance.get() == 0)
            spawns.add(new SpawnEntry(AoAEntities.NPCs.CORRUPTED_TRAVELLER.get(), 1, 0, 1, genericBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Mobs.HORNDRON.get(), 5, 1, 1, genericPlainsBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.WARCLOPS.get(), 5, 1, 2, genericPlainsBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ANCIENT_GOLEM.get(), 5, 1, 2, genericPlainsBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Mobs.DEAD_TREE.get(), 10, 1, 1, Biomes.FOREST, Biomes.WOODED_HILLS).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        
        if (includeEventSpawns) {
            spawns.addAll(bigDaySpawns);
            spawns.addAll(creepDaySpawns);
            spawns.addAll(deathDaySpawns);
            spawns.addAll(fullMoonSpawns);
            spawns.addAll(bloodHuntSpawns);
            spawns.addAll(soulScurrySpawns);
            spawns.addAll(lunarInvasionSpawns);
        }
        
        return spawns;
    }
    
    public static HashSet<SpawnEntry<? extends MobEntity>> getNetherSpawns() {
	    final HashSet<SpawnEntry<? extends MobEntity>> spawns = new HashSet<SpawnEntry<? extends MobEntity>>();
        Biome[] netherBiomes = biomes(BiomeDictionary.Type.NETHER);
        
        spawns.add(new SpawnEntry(AoAEntities.NPCs.WITHERING_LOTTOMAN.get(), 1, 0, 1, netherBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, netherBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.EMBRAKE.get(), 60, 1, 1, netherBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.FAKE_ZOMBIE_PIGMAN.get(), 80, 1, 2, netherBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.FLAMEWALKER.get(), 60, 1, 1, netherBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.HELLSPOT.get(), 60, 1, 1, netherBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.INFERNAL.get(), 10, 1, 1, netherBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.WITHER_WIZARD.get(), 55, 1, 1, netherBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SKELETAL_COWMAN.get(), 45, 1, 1, netherBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.HELLCAT.get(), 50, 1, 1, netherBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.LITTLE_BAM.get(), 20, 1, 1, netherBiomes).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        
        return spawns;
    }
    
    public static HashSet<SpawnEntry<? extends MobEntity>> getDimensionSpawns() {
	    final HashSet<SpawnEntry<? extends MobEntity>> spawns = new HashSet<SpawnEntry<? extends MobEntity>>();

        spawns.add(new SpawnEntry(AoAEntities.Animals.BLOOMING_PIXON.get(), 12, 1, 4, MYSTERIUM.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.RUNATION_MASTER.get(), 1, 0, 1, MYSTERIUM.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, MYSTERIUM.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.FUNGAT.get(), 20, 1, 1, MYSTERIUM.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.FUNGBACK.get(), 15, 1, 1, MYSTERIUM.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.FUNGIK.get(), 15, 1, 1, MYSTERIUM.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.FUNGUNG.get(), 1, 1, 1, MYSTERIUM.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.EEO.get(), 15, 1, 1, MYSTERIUM.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Animals.GLARING_PIXON.get(), 12, 1, 4, LELYETIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.LOGGING_MASTER.get(), 1, 0, 1, LELYETIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, LELYETIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.FLYE.get(), 20, 1, 1, LELYETIA.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.GROBBLER.get(), 1, 1, 1, LELYETIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.LELYETIAN_CASTER.get(), 20, 1, 1, LELYETIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.LELYETIAN_WARRIOR.get(), 20, 1, 1, LELYETIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.TRACKER.get(), 20, 0, 1, LELYETIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Animals.TROTTER.get(), 20, 2, 4, LELYETIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Animals.GLEAMING_PIXON.get(), 12, 1, 4, RUNANDOR.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.ANIMA_MASTER.get(), 1, 0, 1, RUNANDOR.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, RUNANDOR.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ARIEL.get(), 20, 1, 1, RUNANDOR.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.BOUNCER.get(), 20, 1, 1, RUNANDOR.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.EYE_CREATURE.get(), 20, 1, 1, RUNANDOR.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.PALADIN.get(), 20, 1, 1, RUNANDOR.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.RUNICORN.get(), 20, 1, 1, RUNANDOR.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.RUNICORN_RIDER.get(), 1, 1, 1, RUNANDOR.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Animals.GLOWING_PIXON.get(), 12, 1, 4, LBOREAN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.HAULING_MASTER.get(), 1, 0, 1, LBOREAN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, LBOREAN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ANGLER.get(), 20, 1, 1, LBOREAN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CORALON.get(), 20, 1, 1, LBOREAN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.MUNCHER.get(), 20, 1, 1, LBOREAN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.NEPTUNO.get(), 1, 1, 1, LBOREAN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SEA_VIPER.get(), 20, 1, 1, LBOREAN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Animals.CORATEE.get(), 20, 2, 4, LBOREAN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Animals.RADIANT_PIXON.get(), 12, 1, 4, DUSTOPIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, DUSTOPIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.BASILISK.get(), 20, 0, 1, DUSTOPIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.DEVOURER.get(), 20, 0, 1, DUSTOPIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.DUSTON.get(), 20, 0, 1, DUSTOPIA.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.LOST_SOUL.get(), 20, 0, 1, DUSTOPIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.DUSTEIVA.get(), 20, 0, 1, DUSTOPIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.STALKER.get(), 20, 0, 1, DUSTOPIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.STALKER_PRIME.get(), 1, 0, 1, DUSTOPIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.LURKER.get(), 20, 0, 1, DUSTOPIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Animals.SHINING_PIXON.get(), 12, 1, 4, ABYSS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.BUTCHERY_MASTER.get(), 1, 0, 1, ABYSS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, ABYSS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.APPARITION.get(), 20, 1, 1, ABYSS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.BLOODSUCKER.get(), 20, 1, 1, ABYSS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.DISTORTER.get(), 20, 1, 1, ABYSS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.FIEND.get(), 20, 1, 2, ABYSS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.OCCULENT.get(), 20, 1, 1, ABYSS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.WEB_REAPER.get(), 20, 1, 1, ABYSS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SLIMER.get(), 1, 1, 1, ABYSS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.NPCs.CREATION_MASTER.get(), 1, 0, 1, BARATHOS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, BARATHOS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CRYPTID.get(), 30, 1, 1, BARATHOS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ARKBACK.get(), 20, 1, 1, BARATHOS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ECHODAR.get(), 20, 1, 1, BARATHOS.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.EILOSAPIEN.get(), 1, 1, 1, BARATHOS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.NOSPIKE.get(), 1, 1, 1, BARATHOS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.EMPEROR_BEAST.get(), 20, 1, 1, BARATHOS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.PARASECT.get(), 20, 1, 1, BARATHOS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.KEELER.get(), 20, 1, 1, BARATHOS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SQUIGGLER.get(), 1, 1, 1, BARATHOS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.THARAFLY.get(), 15, 1, 2, BARATHOS.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.RAMRADON.get(), 20, 1, 1, BARATHOS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, CANDYLAND.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.AIRHEAD.get(), 20, 0, 1, CANDYLAND.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingRangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CANDY_CORNY.get(), 20, 1, 1, CANDYLAND.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CHERRY_BARRAGER.get(), 1, 1, 1, CANDYLAND.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.LOLLYPOPPER.get(), 20, 1, 1, CANDYLAND.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CHERRY_BLASTER.get(), 20, 1, 1, CANDYLAND.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Animals.PEPPERMINT_SNAIL.get(), 20, 1, 3, CANDYLAND.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Animals.SPEARMINT_SNAIL.get(), 20, 1, 3, CANDYLAND.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, CRYSTEVIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CONSTRUCT_OF_STRENGTH.get(), 20, 1, 1, CRYSTEVIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CONSTRUCT_OF_RANGE.get(), 20, 1, 1, CRYSTEVIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CONSTRUCT_OF_TERROR.get(), 20, 1, 1, CRYSTEVIA.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingRangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CONSTRUCT_OF_SPEED.get(), 20, 1, 1, CRYSTEVIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CONSTRUCT_OF_RESISTANCE.get(), 20, 1, 1, CRYSTEVIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CONSTRUCT_OF_FLIGHT.get(), 20, 1, 1, CRYSTEVIA.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CONSTRUCT_OF_MIND.get(), 2, 1, 1, CRYSTEVIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.NPCs.EXTRACTION_MASTER.get(), 1, 0, 1, DEEPLANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, DEEPLANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CASE_CONSTRUCT.get(), 1, 1, 1, DEEPLANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.DOUBLER.get(), 2, 1, 1, DEEPLANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.DWELLER.get(), 20, 1, 1, DEEPLANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CAVE_CREEP.get(), 20, 1, 1, DEEPLANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ROCKBITER.get(), 20, 1, 1, DEEPLANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ROCK_CRITTER.get(), 20, 1, 1, DEEPLANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ROCK_CRAWLER.get(), 20, 1, 1, DEEPLANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Animals.SHIK.get(), 5, 1, 3, DEEPLANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.METALLOID.get(), 1, 0, 1, IROMINE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.FORAGING_MASTER.get(), 1, 0, 1, IROMINE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, IROMINE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.MECHACHRON.get(), 1, 1, 1, IROMINE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.MECHAMATON.get(), 20, 1, 1, IROMINE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.POLYTOM.get(), 20, 1, 1, IROMINE.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingRangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.QUICKPOCKET.get(), 20, 1, 1, IROMINE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.VOLTRON.get(), 20, 1, 1, IROMINE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, HAVEN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ANGELICA.get(), 15, 1, 1, HAVEN.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.AUTOMATON.get(), 20, 1, 1, HAVEN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ORBITER.get(), 15, 1, 1, HAVEN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SEEKER.get(), 15, 1, 1, HAVEN.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.RAINICORN.get(), 2, 1, 1, HAVEN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SURVEYOR.get(), 5, 1, 1, HAVEN.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.VOLAR.get(), 3, 1, 1, HAVEN.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingRangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.VOLIANT.get(), 1, 1, 1, HAVEN.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingRangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Animals.HALYCON.get(), 5, 2, 4, HAVEN.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.NPCs.INFUSION_MASTER.get(), 1, 0, 1, GRECKON.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, GRECKON.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.GRILLFACE.get(), 20, 1, 1, GRECKON.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SHIFTER.get(), 20, 1, 1, GRECKON.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SILENCER.get(), 1, 1, 1, GRECKON.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SKULL_CREATURE.get(), 20, 1, 1, GRECKON.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SUGARFACE.get(), 20, 1, 1, GRECKON.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.VALKYRIE.get(), 20, 1, 1, GRECKON.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingRangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.HUNTER.get(), 20, 1, 1, GRECKON.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.NPCs.INNERVATION_MASTER.get(), 1, 0, 1, CELEVE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, CELEVE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.BOBO.get(), 20, 1, 1, CELEVE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CHOCKO.get(), 20, 1, 1, CELEVE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.JUMBO.get(), 2, 1, 1, CELEVE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.KOKO.get(), 20, 1, 1, CELEVE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.KRANKY.get(), 20, 1, 1, CELEVE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SNAPPY.get(), 20, 1, 1, CELEVE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.STICKY.get(), 20, 1, 1, CELEVE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.STITCHES.get(), 20, 1, 1, CELEVE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.TIPSY.get(), 20, 1, 1, CELEVE.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, LUNALUS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.EXPLODOT.get(), 20, 1, 1, LUNALUS.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.LUNARCHER.get(), 20, 1, 1, LUNALUS.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingRangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.VISULAR.get(), 20, 1, 1, LUNALUS.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.VISULON.get(), 1, 1, 1, LUNALUS.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, CREEPONIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.BONE_CREEPER.get(), 40, 1, 1, CREEPONIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoACreeponiaCreeper::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CAVE_CREEPOID.get(), 25, 1, 1, CREEPONIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoACreeponiaCreeper::meetsSpawnConditions));
        spawns.add(new SpawnEntry(EntityType.CREEPER, 40, 1, 1, CREEPONIA.get()));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CREEPERLOCK.get(), 25, 1, 1, CREEPONIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoACreeponiaCreeper::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CREEPIRD.get(), 15, 1, 1, CREEPONIA.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CREEPUPLE.get(), 40, 1, 1, CREEPONIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoACreeponiaCreeper::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.KING_CREEPER.get(), 1, 1, 1, CREEPONIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoACreeponiaCreeper::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.MAGICAL_CREEPER.get(), 25, 1, 1, CREEPONIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoACreeponiaCreeper::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.WINGED_CREEPER.get(), 30, 1, 1, CREEPONIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoACreeponiaCreeper::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Animals.CREEP_COW.get(), 5, 2, 4, CREEPONIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.Animals.AMBIENT_PIXON.get(), 12, 1, 4, HAVEN.get(), RUNANDOR.get(), CANDYLAND.get(), SHYRELANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, PRECASIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.DYREHORN.get(), 20, 1, 1, PRECASIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.GIANT_SNAIL.get(), 20, 1, 1, PRECASIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.DEINOTHERIUM.get(), 20, 1, 1, PRECASIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SABRETOOTH.get(), 20, 1, 1, PRECASIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.TERRADON.get(), 1, 1, 1, PRECASIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.TORTIONE.get(), 3, 1, 1, PRECASIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Animals.MEGANEUROPSIS.get(), 5, 1, 2, PRECASIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAAnimal::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, VOX_PONDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.TOXXULOUS.get(), 20, 1, 1, VOX_PONDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.GROCCULATE.get(), 20, 1, 1, VOX_PONDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.GADGETOID.get(), 20, 1, 1, VOX_PONDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ALARMO.get(), 20, 1, 1, VOX_PONDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.FISCHER.get(), 1, 1, 1, VOX_PONDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.DESTRUCTOR.get(), 1, 1, 1, VOX_PONDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CENTINEL.get(), 20, 1, 1, VOX_PONDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoARangedMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, GARDENCIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ARCHVINE.get(), 20, 1, 1, GARDENCIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.BROCCOHEAD.get(), 20, 1, 1, GARDENCIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CARROTOP.get(), 20, 1, 1, GARDENCIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.FLOWERFACE.get(), 20, 1, 1, GARDENCIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SQUASHER.get(), 20, 1, 1, GARDENCIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SUNNY.get(), 1, 1, 1, GARDENCIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.CORNY.get(), 20, 1, 1, GARDENCIA.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));

        spawns.add(new SpawnEntry(AoAEntities.NPCs.EXPEDITION_MASTER.get(), 1, 0, 1, SHYRELANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.NPCs.UNDEAD_HERALD.get(), 1, 0, 1, SHYRELANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SYSKER.get(), 20, 1, 1, SHYRELANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SHYRE_KNIGHT.get(), 20, 1, 1, SHYRELANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.AXIOLIGHT.get(), 20, 1, 1, SHYRELANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ARC_FLOWER.get(), 20, 1, 1, SHYRELANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.ARCBEAST.get(), 20, 1, 1, SHYRELANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.STIMULO.get(), 20, 1, 1, SHYRELANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.STIMULOSUS.get(), 1, 1, 1, SHYRELANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.SOULVYRE.get(), 5, 1, 1, SHYRELANDS.get()).placement(ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AoAMeleeMob::meetsSpawnConditions));
        spawns.add(new SpawnEntry(AoAEntities.Mobs.OMNILIGHT.get(), 20, 1, 1, SHYRELANDS.get()).placement(ON_GROUND, MOTION_BLOCKING, AoAFlyingRangedMob::meetsSpawnConditions));

        return spawns;
    }

    private static Biome[] biomes(BiomeDictionary.Type... biomeTypes) {
        List<Biome> matchedBiomes = new ArrayList<Biome>();

        for (BiomeDictionary.Type type : biomeTypes) {
            matchedBiomes.addAll(BiomeDictionary.getBiomes(type));
        }

        return matchedBiomes.toArray(new Biome[0]);
    }

    private static Biome[] extractAllBiomesMatching(Biome[] preFilledList, BiomeDictionary.Type[] matchingTypes, BiomeDictionary.Type[] blacklistTypes) {
        List<Biome> passedBiomes = new ArrayList<Biome>();

        biomeLoop:
        for (Biome biome : preFilledList) {
            for (BiomeDictionary.Type goodType : matchingTypes) {
                if (BiomeDictionary.hasType(biome, goodType)) {
                    for (BiomeDictionary.Type badType : blacklistTypes) {
                        if (BiomeDictionary.hasType(biome, badType))
                            continue biomeLoop;
                    }

                    passedBiomes.add(biome);
                }
            }
        }

        return passedBiomes.toArray(new Biome[0]);
    }

    private static Biome[] getOverworldBiomes() {
        List<Biome> suspectedOverworldBiomes = new ArrayList<Biome>();
        HashSet<Biome> blacklist = new HashSet<Biome>();

        blacklist.add(ABYSS.get());
        blacklist.add(ANCIENT_CAVERN.get());
        blacklist.add(BARATHOS.get());
        blacklist.add(CANDYLAND.get());
        blacklist.add(CELEVE.get());
        blacklist.add(CREEPONIA.get());
        blacklist.add(CRYSTEVIA.get());
        blacklist.add(DEEPLANDS.get());
        blacklist.add(DUSTOPIA.get());
        blacklist.add(GARDENCIA.get());
        blacklist.add(GRECKON.get());
        blacklist.add(HAVEN.get());
        blacklist.add(IMMORTALLIS.get());
        blacklist.add(IROMINE.get());
        blacklist.add(LBOREAN.get());
        blacklist.add(LELYETIA.get());
        blacklist.add(LUNALUS.get());
        blacklist.add(MYSTERIUM.get());
        blacklist.add(PRECASIA.get());
        blacklist.add(RUNANDOR.get());
        blacklist.add(SHYRELANDS.get());
        blacklist.add(VOX_PONDS.get());
        blacklist.add(Biomes.NETHER);
        blacklist.add(Biomes.THE_VOID);
        blacklist.add(Biomes.THE_END);

        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (!blacklist.contains(biome))
                suspectedOverworldBiomes.add(biome);
        }

        return suspectedOverworldBiomes.toArray(new Biome[0]);
    }

    public static void addEventSpawns(OverworldEvents.Event event) {
        if (forceAllSpawns)
            return;

        ArrayList<SpawnEntry<? extends MobEntity>> spawnList = null;

        switch (event) {
            case BIG_DAY:
                spawnList = bigDaySpawns;
                break;
            case CREEP_DAY:
                spawnList = creepDaySpawns;
                break;
            case DEATH_DAY:
                spawnList = deathDaySpawns;
                break;
            case FULL_MOON:
                spawnList = fullMoonSpawns;
                break;
            case BLOOD_HUNT:
                spawnList = bloodHuntSpawns;
                break;
            case SOUL_SCURRY:
                spawnList = soulScurrySpawns;
                break;
            case LUNAR_INVASION:
                spawnList = lunarInvasionSpawns;
                break;
        }

        addSpawns(spawnList);
    }

    public static void removeEventSpawns(OverworldEvents.Event event) {
        if (forceAllSpawns)
            return;

        ArrayList<SpawnEntry<? extends MobEntity>> spawnList = null;

        switch (event) {
            case BIG_DAY:
                spawnList = bigDaySpawns;
                break;
            case CREEP_DAY:
                spawnList = creepDaySpawns;
                break;
            case DEATH_DAY:
                spawnList = deathDaySpawns;
                break;
            case FULL_MOON:
                spawnList = fullMoonSpawns;
                break;
            case BLOOD_HUNT:
                spawnList = bloodHuntSpawns;
                break;
            case SOUL_SCURRY:
                spawnList = soulScurrySpawns;
                break;
            case LUNAR_INVASION:
                spawnList = lunarInvasionSpawns;
                break;
        }

        removeSpawns(spawnList);
    }

    public static void addSpawns(Collection<SpawnEntry<? extends MobEntity>> spawns) {
        spawns.forEach(entry -> addSpawn(entry.entityType, entry.weight, entry.minGroupSize, entry.maxGroupSize, entry.biomes));
    }

    public static void removeSpawns(Collection<SpawnEntry<? extends MobEntity>> spawns) {
        spawns.forEach(entry -> removeSpawn(entry.entityType, entry.biomes));
    }

    public static void addSpawn(EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize, Biome... biomes) {
        for (Biome biome : biomes) {
            if (biome != null)
                biome.getSpawns(entityType.getClassification()).add(new Biome.SpawnListEntry(entityType, weight, minGroupSize, maxGroupSize));
        }
    }

    public static void removeSpawn(EntityType<?> entityType, Biome... biomes) {
       for (Biome biome : biomes) {
           biome.getSpawns(entityType.getClassification()).removeIf(entry -> entry.entityType == entityType);
       }
    }

    public static class SpawnEntry<T extends MobEntity> {
        public final EntityType<T> entityType;
        public final int weight;
        public final int minGroupSize;
        public final int maxGroupSize;
        public final Biome[] biomes;

    	private SpawnEntry(EntityType<T> entityType, int weight, int minGroupSize, int maxGroupSize, Biome... biomes) {
    	    this.entityType = entityType;
    	    this.weight = weight;
    	    this.minGroupSize = minGroupSize;
    	    this.maxGroupSize = maxGroupSize;
    	    this.biomes = biomes;
        }

        private SpawnEntry<T> placement(EntitySpawnPlacementRegistry.PlacementType type, Heightmap.Type heightmap, EntitySpawnPlacementRegistry.IPlacementPredicate<T> spawnPredicate) {

    	    try {
                if (EntitySpawnPlacementRegistry.getPlacementType(entityType) == NO_RESTRICTIONS)
                    EntitySpawnPlacementRegistry.register(entityType, type, heightmap, spawnPredicate);
            }
    	    catch (IllegalStateException ex) {
    	        Logging.logMessage(Level.WARN, "Caught duplicate spawn placement registration from: " + entityType.getRegistryName().toString());
            }

    	    return this;
        }
	}
}
