package net.tslat.aoa3.common.registration;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.animals.*;
import net.tslat.aoa3.entity.boss.kingbambambam.EntityLittleBam;
import net.tslat.aoa3.entity.misc.pixon.*;
import net.tslat.aoa3.entity.mobs.abyss.*;
import net.tslat.aoa3.entity.mobs.barathos.*;
import net.tslat.aoa3.entity.mobs.candyland.*;
import net.tslat.aoa3.entity.mobs.celeve.*;
import net.tslat.aoa3.entity.mobs.creeponia.*;
import net.tslat.aoa3.entity.mobs.crystevia.*;
import net.tslat.aoa3.entity.mobs.deeplands.*;
import net.tslat.aoa3.entity.mobs.dustopia.*;
import net.tslat.aoa3.entity.mobs.gardencia.*;
import net.tslat.aoa3.entity.mobs.greckon.*;
import net.tslat.aoa3.entity.mobs.haven.*;
import net.tslat.aoa3.entity.mobs.iromine.*;
import net.tslat.aoa3.entity.mobs.lborean.*;
import net.tslat.aoa3.entity.mobs.lelyetia.*;
import net.tslat.aoa3.entity.mobs.lunalus.EntityExplodot;
import net.tslat.aoa3.entity.mobs.lunalus.EntityLunarcher;
import net.tslat.aoa3.entity.mobs.lunalus.EntityVisular;
import net.tslat.aoa3.entity.mobs.lunalus.EntityVisulon;
import net.tslat.aoa3.entity.mobs.mysterium.*;
import net.tslat.aoa3.entity.mobs.nether.*;
import net.tslat.aoa3.entity.mobs.overworld.*;
import net.tslat.aoa3.entity.mobs.overworld.bigday.*;
import net.tslat.aoa3.entity.mobs.overworld.bloodhunt.EntityAnemia;
import net.tslat.aoa3.entity.mobs.overworld.bloodhunt.EntityBloodmist;
import net.tslat.aoa3.entity.mobs.overworld.bloodhunt.EntityLinger;
import net.tslat.aoa3.entity.mobs.overworld.creepday.EntityHost;
import net.tslat.aoa3.entity.mobs.overworld.deathday.EntityDeathHunter;
import net.tslat.aoa3.entity.mobs.overworld.deathday.EntityHeadlessDestroyer;
import net.tslat.aoa3.entity.mobs.overworld.deathday.EntityReaperTwins;
import net.tslat.aoa3.entity.mobs.overworld.deathday.EntityTriclops;
import net.tslat.aoa3.entity.mobs.overworld.fullmoon.*;
import net.tslat.aoa3.entity.mobs.overworld.lunarinvasion.*;
import net.tslat.aoa3.entity.mobs.overworld.soulscurry.*;
import net.tslat.aoa3.entity.mobs.precasia.*;
import net.tslat.aoa3.entity.mobs.runandor.*;
import net.tslat.aoa3.entity.mobs.shyrelands.*;
import net.tslat.aoa3.entity.mobs.voxponds.*;
import net.tslat.aoa3.entity.npcs.lottoman.EntityLottoman;
import net.tslat.aoa3.entity.npcs.lottoman.EntityWitheringLottoman;
import net.tslat.aoa3.entity.npcs.skillmaster.*;
import net.tslat.aoa3.entity.npcs.trader.*;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.BiomeRegister.*;

public class EntitySpawnRegister {
	private static final ArrayList<SpawnEntry> bigDaySpawns = new ArrayList<SpawnEntry>(5);
	private static final ArrayList<SpawnEntry> bloodHuntSpawns = new ArrayList<SpawnEntry>(3);
	private static final ArrayList<SpawnEntry> creepDaySpawns = new ArrayList<SpawnEntry>(1);
	private static final ArrayList<SpawnEntry> deathDaySpawns = new ArrayList<SpawnEntry>(4);
	private static final ArrayList<SpawnEntry> fullMoonSpawns = new ArrayList<SpawnEntry>(5);
	private static final ArrayList<SpawnEntry> lunarInvasionSpawns = new ArrayList<SpawnEntry>(5);
	private static final ArrayList<SpawnEntry> soulScurrySpawns = new ArrayList<SpawnEntry>(6);

    public static void registerEntitySpawns() {
        AdventOfAscension.logOptionalMessage("Registering entity spawns");

        if (!ConfigurationUtil.MainConfig.disableOverworldMobs) {
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

            EntityRegistry.addSpawn(EntityBombCarrier.class, 1, 0, 1, EnumCreatureType.MONSTER, overworldBiomes);
            fullMoonSpawns.add(new SpawnEntry(EntityIrkling.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            deathDaySpawns.add(new SpawnEntry(EntityReaperTwins.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            lunarInvasionSpawns.add(new SpawnEntry(EntityRoloscope.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            bigDaySpawns.add(new SpawnEntry(EntityWoodGiant.class, 50, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            deathDaySpawns.add(new SpawnEntry(EntityTriclops.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            lunarInvasionSpawns.add(new SpawnEntry(EntityVertebron.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            lunarInvasionSpawns.add(new SpawnEntry(EntityWalker.class, 70, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            EntityRegistry.addSpawn(EntityEverbeast.class, 2, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityFacelessRunner.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityGhost.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityMotherVoidWalker.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityShadow.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityTrickster.class, 40, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityVoidCharger.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityVoidWalker.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            deathDaySpawns.add(new SpawnEntry(EntityHeadlessDestroyer.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            bloodHuntSpawns.add(new SpawnEntry(EntityBloodmist.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            bloodHuntSpawns.add(new SpawnEntry(EntityLinger.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            EntityRegistry.addSpawn(EntityBugeye.class, 70, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityDicer.class, 35, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            soulScurrySpawns.add(new SpawnEntry(EntityGhostlyBugeye.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            soulScurrySpawns.add(new SpawnEntry(EntityGhostlyCharger.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            soulScurrySpawns.add(new SpawnEntry(EntityGhostlyCyclops.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            soulScurrySpawns.add(new SpawnEntry(EntityGhostlyNightReaper.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            soulScurrySpawns.add(new SpawnEntry(EntityGhostlyGoblin.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            soulScurrySpawns.add(new SpawnEntry(EntityGhostlySasquatch.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            lunarInvasionSpawns.add(new SpawnEntry(EntityTerrestrial.class, 2, 0, 1, EnumCreatureType.MONSTER, overworldBiomes));
            fullMoonSpawns.add(new SpawnEntry(EntitySkellox.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            fullMoonSpawns.add(new SpawnEntry(EntityScrubby.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            fullMoonSpawns.add(new SpawnEntry(EntityNightWatcher.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            EntityRegistry.addSpawn(EntityNightReaper.class, 35, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityDemonReaper.class, 1, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            fullMoonSpawns.add(new SpawnEntry(EntityDarkBeast.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            EntityRegistry.addSpawn(EntityClown.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            creepDaySpawns.add(new SpawnEntry(EntityHost.class, 40, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            deathDaySpawns.add(new SpawnEntry(EntityDeathHunter.class, 40, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            bloodHuntSpawns.add(new SpawnEntry(EntityAnemia.class, 35, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
            lunarInvasionSpawns.add(new SpawnEntry(EntityModulo.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));

            EntityRegistry.addSpawn(EntityGoalby.class, 60, 1, 1, EnumCreatureType.MONSTER, mountainBiomes);
            EntityRegistry.addSpawn(EntityGrunt.class, 20, 1, 1, EnumCreatureType.MONSTER, mountainBiomes);
            bigDaySpawns.add(new SpawnEntry(EntityStoneGiant.class, 60, 1, 1, EnumCreatureType.MONSTER, mountainBiomes));
            EntityRegistry.addSpawn(EntityMagicke.class, 60, 1, 1, EnumCreatureType.MONSTER, mountainBiomes);

            EntityRegistry.addSpawn(EntityHunch.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes);
            bigDaySpawns.add(new SpawnEntry(EntityIceGiant.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes));
            EntityRegistry.addSpawn(EntityPolarUrsa.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes);
            EntityRegistry.addSpawn(EntityYeti.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes);
            EntityRegistry.addSpawn(EntitySnowCharger.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes);

            EntityRegistry.addSpawn(EntityDesertCharger.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes);
            EntityRegistry.addSpawn(EntityFurlion.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes);
            bigDaySpawns.add(new SpawnEntry(EntitySandGiant.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes));
            EntityRegistry.addSpawn(EntitySandGolem.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes);
            EntityRegistry.addSpawn(EntitySphinx.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes);
            EntityRegistry.addSpawn(EntityWickett.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes);

            EntityRegistry.addSpawn(EntityChimera.class, 75, 1, 1, EnumCreatureType.MONSTER, savannaBiomes);
            EntityRegistry.addSpawn(EntityBoneback.class, 75, 1, 1, EnumCreatureType.MONSTER, savannaBiomes);
            EntityRegistry.addSpawn(EntityRammerhead.class, 75, 1, 1, EnumCreatureType.MONSTER, savannaBiomes);
            EntityRegistry.addSpawn(EntityElkanyne.class, 20, 1, 1, EnumCreatureType.CREATURE, savannaBiomes);

            EntityRegistry.addSpawn(EntityBushBaby.class, 70, 1, 1, EnumCreatureType.MONSTER, jungleBiomes);
            EntityRegistry.addSpawn(EntityStinger.class, 70, 1, 1, EnumCreatureType.MONSTER, jungleBiomes);

            EntityRegistry.addSpawn(EntityChomper.class, 70, 1, 1, EnumCreatureType.MONSTER, swampBiomes);
            EntityRegistry.addSpawn(EntitySkipper.class, 30, 1, 1, EnumCreatureType.MONSTER, swampBiomes);
            EntityRegistry.addSpawn(EntityFishix.class, 70, 1, 1, EnumCreatureType.MONSTER, swampBiomes);
            EntityRegistry.addSpawn(EntitySwampCharger.class, 70, 1, 1, EnumCreatureType.MONSTER, swampBiomes);
            EntityRegistry.addSpawn(EntityHag.class, 70, 1, 1, EnumCreatureType.MONSTER, swampBiomes);

            EntityRegistry.addSpawn(EntityBlackUrsa.class, 70, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes);
            EntityRegistry.addSpawn(EntityHidingFungi.class, 40, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes);
            bigDaySpawns.add(new SpawnEntry(EntityLeafyGiant.class, 70, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes));
            EntityRegistry.addSpawn(EntityNatura.class, 70, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes);
            EntityRegistry.addSpawn(EntitySkolle.class, 15, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes);
            EntityRegistry.addSpawn(EntityUrka.class, 70, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes);

            EntityRegistry.addSpawn(EntityHillCharger.class, 50, 1, 1, EnumCreatureType.MONSTER, mesaBiomes);
            EntityRegistry.addSpawn(EntityMuckopede.class, 50, 1, 1, EnumCreatureType.MONSTER, mesaBiomes);

            EntityRegistry.addSpawn(EntityPincher.class, 1, 0, 1, EnumCreatureType.WATER_CREATURE, oceanBiomes);

            EntityRegistry.addSpawn(EntitySeaCharger.class, 20, 1, 1, EnumCreatureType.MONSTER, beachBiomes);
            EntityRegistry.addSpawn(EntitySpinux.class, 20, 1, 1, EnumCreatureType.MONSTER, beachBiomes);
            EntityRegistry.addSpawn(EntitySeaTroll.class, 20, 1, 1, EnumCreatureType.MONSTER, beachBiomes);
            EntityRegistry.addSpawn(EntityTrollTrader.class, 1, 0, 1, EnumCreatureType.CREATURE, beachBiomes);

            EntityRegistry.addSpawn(EntityCyclops.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityCharger.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntitySasquatch.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityHeadlessHunter.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityGlisteningPixon.class, 30, 1, 3, EnumCreatureType.CREATURE, genericBiomes);
            EntityRegistry.addSpawn(EntityBoneCreature.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityKingCharger.class, 1, 0, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityBrute.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityGhostineAncient.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityGoblin.class, 20, 0, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityAssassin.class, 1, 0, 1, EnumCreatureType.CREATURE, genericBiomes);
            EntityRegistry.addSpawn(EntityNaturalist.class, 1, 0, 1, EnumCreatureType.CREATURE, genericBiomes);
            EntityRegistry.addSpawn(EntityRealmshifter.class, 1, 0, 1, EnumCreatureType.CREATURE, genericBiomes);
            EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, genericBiomes);
            EntityRegistry.addSpawn(EntityLottoman.class, 1, 0, 1, EnumCreatureType.CREATURE, genericBiomes);
            EntityRegistry.addSpawn(EntityNightfly.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);

            if (ConfigurationUtil.StructureConfig.overworld.ruinedTeleporterFrameSpawnChance == 0)
                EntityRegistry.addSpawn(EntityCorruptedTraveller.class, 1, 0, 1, EnumCreatureType.CREATURE, genericBiomes);

            EntityRegistry.addSpawn(EntityHorndron.class, 5, 0, 1, EnumCreatureType.MONSTER, genericPlainsBiomes);
            EntityRegistry.addSpawn(EntityWarclops.class, 5, 0, 1, EnumCreatureType.MONSTER, genericPlainsBiomes);
            EntityRegistry.addSpawn(EntityAncientGolem.class, 5, 0, 1, EnumCreatureType.MONSTER, genericPlainsBiomes);

            EntityRegistry.addSpawn(EntityDeadTree.class, 10, 0, 1, EnumCreatureType.MONSTER, Biomes.FOREST, Biomes.FOREST_HILLS);
        }

        EntityRegistry.addSpawn(EntityWitheringLottoman.class, 1, 0, 1, EnumCreatureType.CREATURE, Biomes.HELL);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, Biomes.HELL);
        EntityRegistry.addSpawn(EntityEmbrake.class, 60, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityFakePigman.class, 80, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityFlamewalker.class, 60, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityHellspot.class, 60, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityInfernal.class, 10, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityWitherWizard.class, 55, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntitySkeletalCowman.class, 45, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityHellcat.class, 50, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityLittleBam.class, 35, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);

        EntityRegistry.addSpawn(EntityBloomingPixon.class, 12, 1, 3, EnumCreatureType.CREATURE, biomeMysterium);
        EntityRegistry.addSpawn(EntityRunationMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeMysterium);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeMysterium);
        EntityRegistry.addSpawn(EntityFungat.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeMysterium);
        EntityRegistry.addSpawn(EntityFungback.class, 15, 0, 1, EnumCreatureType.MONSTER, biomeMysterium);
        EntityRegistry.addSpawn(EntityFungik.class, 15, 0, 1, EnumCreatureType.MONSTER, biomeMysterium);
        EntityRegistry.addSpawn(EntityFungung.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeMysterium);
        EntityRegistry.addSpawn(EntityEeo.class, 15, 0, 1, EnumCreatureType.MONSTER, biomeMysterium);

        EntityRegistry.addSpawn(EntityGlaringPixon.class, 12, 1, 3, EnumCreatureType.CREATURE, biomeLelyetia);
        EntityRegistry.addSpawn(EntityLoggingMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeLelyetia);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeLelyetia);
        EntityRegistry.addSpawn(EntityFlye.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLelyetia);
        EntityRegistry.addSpawn(EntityGrobbler.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeLelyetia);
        EntityRegistry.addSpawn(EntityLelyetianCaster.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLelyetia);
        EntityRegistry.addSpawn(EntityLelyetianWarrior.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLelyetia);
        EntityRegistry.addSpawn(EntityTracker.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLelyetia);
        EntityRegistry.addSpawn(EntityTrotter.class, 20, 0, 1, EnumCreatureType.CREATURE, biomeLelyetia);

        EntityRegistry.addSpawn(EntityGleamingPixon.class, 12, 1, 3, EnumCreatureType.CREATURE, biomeRunandor);
        EntityRegistry.addSpawn(EntityAnimaMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeRunandor);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeRunandor);
        EntityRegistry.addSpawn(EntityAriel.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeRunandor);
        EntityRegistry.addSpawn(EntityBouncer.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeRunandor);
        EntityRegistry.addSpawn(EntityEyeCreature.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeRunandor);
        EntityRegistry.addSpawn(EntityPaladin.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeRunandor);
        EntityRegistry.addSpawn(EntityRunicorn.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeRunandor);
        EntityRegistry.addSpawn(EntityRunicornRider.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeRunandor);

        EntityRegistry.addSpawn(EntityGlowingPixon.class, 12, 1, 3, EnumCreatureType.CREATURE, biomeLBorean);
        EntityRegistry.addSpawn(EntityHaulingMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeLBorean);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeLBorean);
        EntityRegistry.addSpawn(EntityAngler.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLBorean);
        EntityRegistry.addSpawn(EntityCoralon.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLBorean);
        EntityRegistry.addSpawn(EntityMuncher.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLBorean);
        EntityRegistry.addSpawn(EntityNeptuno.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeLBorean);
        EntityRegistry.addSpawn(EntitySeaViper.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLBorean);
        EntityRegistry.addSpawn(EntityCoratee.class, 20, 0, 1, EnumCreatureType.CREATURE, biomeLBorean);

        EntityRegistry.addSpawn(EntityRadiantPixon.class, 12, 1, 3, EnumCreatureType.CREATURE, biomeDustopia);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeDustopia);
        EntityRegistry.addSpawn(EntityBasilisk.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityDevourer.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityDuston.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityLostSoul.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityDusteiva.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityStalker.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityStalkerPrime.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityLurker.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);

        EntityRegistry.addSpawn(EntityShiningPixon.class, 12, 1, 1, EnumCreatureType.CREATURE, biomeAbyss);
        EntityRegistry.addSpawn(EntityButcheryMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeAbyss);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeAbyss);
        EntityRegistry.addSpawn(EntityApparition.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);
        EntityRegistry.addSpawn(EntityBloodsucker.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);
        EntityRegistry.addSpawn(EntityDistorter.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);
        EntityRegistry.addSpawn(EntityFiend.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);
        EntityRegistry.addSpawn(EntityOcculent.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);
        EntityRegistry.addSpawn(EntityWebReaper.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);
        EntityRegistry.addSpawn(EntitySlimer.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);

        EntityRegistry.addSpawn(EntityCreationMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeBarathos);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeBarathos);
        EntityRegistry.addSpawn(EntityCryptid.class, 30, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityArkback.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityEchodar.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityEilosapien.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityNospike.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityEmperorBeast.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityParasect.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityKeeler.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntitySquiggler.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityTharafly.class, 15, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityRamradon.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);

        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeCandyland);
        EntityRegistry.addSpawn(EntityAirhead.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCandyland);
        EntityRegistry.addSpawn(EntityCandyCorny.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCandyland);
        EntityRegistry.addSpawn(EntityCherryBarrager.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeCandyland);
        EntityRegistry.addSpawn(EntityLollypopper.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCandyland);
        EntityRegistry.addSpawn(EntityCherryBlaster.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCandyland);
        EntityRegistry.addSpawn(EntityPeppermintSnail.class, 20, 0, 1, EnumCreatureType.CREATURE, biomeCandyland);
        EntityRegistry.addSpawn(EntitySpearmintSnail.class, 20, 0, 1, EnumCreatureType.CREATURE, biomeCandyland);

        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeCrystevia);
        EntityRegistry.addSpawn(EntityConstructStrength.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);
        EntityRegistry.addSpawn(EntityConstructRange.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);
        EntityRegistry.addSpawn(EntityConstructTerror.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);
        EntityRegistry.addSpawn(EntityConstructSpeed.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);
        EntityRegistry.addSpawn(EntityConstructResistance.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);
        EntityRegistry.addSpawn(EntityConstructFlight.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);
        EntityRegistry.addSpawn(EntityConstructMind.class, 2, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);

        EntityRegistry.addSpawn(EntityExtractionMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeDeeplands);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeDeeplands);
        EntityRegistry.addSpawn(EntityCaseConstruct.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);
        EntityRegistry.addSpawn(EntityDoubler.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);
        EntityRegistry.addSpawn(EntityDweller.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);
        EntityRegistry.addSpawn(EntityCaveCreep.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);
        EntityRegistry.addSpawn(EntityRockbiter.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);
        EntityRegistry.addSpawn(EntityRockCritter.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);
        EntityRegistry.addSpawn(EntityRockCrawler.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);

        EntityRegistry.addSpawn(EntityMetalloid.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeIromine);
        EntityRegistry.addSpawn(EntityForagingMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeIromine);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeIromine);
        EntityRegistry.addSpawn(EntityMechachron.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeIromine);
        EntityRegistry.addSpawn(EntityMechamaton.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeIromine);
        EntityRegistry.addSpawn(EntityPolytom.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeIromine);
        EntityRegistry.addSpawn(EntityQuickpocket.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeIromine);
        EntityRegistry.addSpawn(EntityVoltron.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeIromine);

        //EntityRegistry.addSpawn(EntityAuguryMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeHaven);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeHaven);
        EntityRegistry.addSpawn(EntityAngelica.class, 15, 0, 1, EnumCreatureType.MONSTER, biomeHaven);
        EntityRegistry.addSpawn(EntityBlueAutomaton.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeHaven);
        EntityRegistry.addSpawn(EntityYellowAutomaton.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeHaven);
        EntityRegistry.addSpawn(EntityRedAutomaton.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeHaven);
        EntityRegistry.addSpawn(EntityPurpleAutomaton.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeHaven);
        EntityRegistry.addSpawn(EntityGreenAutomaton.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeHaven);
        EntityRegistry.addSpawn(EntityOrbiter.class, 15, 0, 1, EnumCreatureType.MONSTER, biomeHaven);
        EntityRegistry.addSpawn(EntitySeeker.class, 15, 0, 1, EnumCreatureType.MONSTER, biomeHaven);
        EntityRegistry.addSpawn(EntityRainicorn.class, 2, 0, 1, EnumCreatureType.MONSTER, biomeHaven);
        EntityRegistry.addSpawn(EntitySurveyor.class, 5, 0, 1, EnumCreatureType.MONSTER, biomeHaven);
        EntityRegistry.addSpawn(EntityVolar.class, 3, 0, 1, EnumCreatureType.MONSTER, biomeHaven);
        EntityRegistry.addSpawn(EntityVoliant.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeHaven);
        EntityRegistry.addSpawn(EntityHalycon.class, 5, 0, 1, EnumCreatureType.CREATURE, biomeHaven);

        EntityRegistry.addSpawn(EntityInfusionMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeGreckon);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeGreckon);
        EntityRegistry.addSpawn(EntityGrillface.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);
        EntityRegistry.addSpawn(EntityShifter.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);
        EntityRegistry.addSpawn(EntitySilencer.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);
        EntityRegistry.addSpawn(EntitySkullCreature.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);
        EntityRegistry.addSpawn(EntitySugarface.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);
        EntityRegistry.addSpawn(EntityValkyrie.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);
        EntityRegistry.addSpawn(EntityHunter.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);

        EntityRegistry.addSpawn(EntityInnervationMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeCeleve);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeCeleve);
        EntityRegistry.addSpawn(EntityBobo.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntityChocko.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntityJumbo.class, 2, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntityKoko.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntityKranky.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntitySnappy.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntitySticky.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntityStitches.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntityTipsy.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);

        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeLunalus);
        EntityRegistry.addSpawn(EntityExplodot.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLunalus);
        EntityRegistry.addSpawn(EntityLunarcher.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLunalus);
        EntityRegistry.addSpawn(EntityVisular.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLunalus);
        EntityRegistry.addSpawn(EntityVisulon.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeLunalus);

        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeCreeponia);
        EntityRegistry.addSpawn(EntityBoneCreeper.class, 40, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityCaveCreepoid.class, 25, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityCreeper.class, 40, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityCreeperlock.class, 25, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityCreepird.class, 15, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityCreepuple.class, 40, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityKingCreeper.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityMagicalCreeper.class, 25, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityWingedCreeper.class, 30, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityCreepCow.class, 5, 0, 1, EnumCreatureType.CREATURE, biomeCreeponia);

        EntityRegistry.addSpawn(EntityAmbientPixon.class, 12, 1, 3, EnumCreatureType.CREATURE, biomeHaven, biomeRunandor, biomeCandyland, biomeShyrelands);

        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomePrecasia);
        EntityRegistry.addSpawn(EntityDyrehorn.class, 20, 0, 1, EnumCreatureType.MONSTER, biomePrecasia);
        EntityRegistry.addSpawn(EntityGiantSnail.class, 20, 0, 1, EnumCreatureType.MONSTER, biomePrecasia);
        EntityRegistry.addSpawn(EntityDeinotherium.class, 20, 0, 1, EnumCreatureType.MONSTER, biomePrecasia);
        EntityRegistry.addSpawn(EntitySabretooth.class, 20, 0, 1, EnumCreatureType.MONSTER, biomePrecasia);
        EntityRegistry.addSpawn(EntityTerradon.class, 1, 0, 1, EnumCreatureType.MONSTER, biomePrecasia);
        EntityRegistry.addSpawn(EntityTortione.class, 3, 0, 1, EnumCreatureType.MONSTER, biomePrecasia);

        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeVoxPonds);
        EntityRegistry.addSpawn(EntityToxxulous.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);
        EntityRegistry.addSpawn(EntityGrocculate.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);
        EntityRegistry.addSpawn(EntityGadgetoid.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);
        EntityRegistry.addSpawn(EntityAlarmo.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);
        EntityRegistry.addSpawn(EntityFischer.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);
        EntityRegistry.addSpawn(EntityDestructor.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);
        EntityRegistry.addSpawn(EntityCentinel.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);

        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeGardencia);
        EntityRegistry.addSpawn(EntityArchvine.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);
        EntityRegistry.addSpawn(EntityBroccohead.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);
        EntityRegistry.addSpawn(EntityCarrotop.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);
        EntityRegistry.addSpawn(EntityFlowerface.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);
        EntityRegistry.addSpawn(EntitySquasher.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);
        EntityRegistry.addSpawn(EntitySunny.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);
        EntityRegistry.addSpawn(EntityCorny.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);

        EntityRegistry.addSpawn(EntityExpeditionMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeShyrelands);
        EntityRegistry.addSpawn(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, biomeShyrelands);
        EntityRegistry.addSpawn(EntitySysker.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityShyreKnight.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityAxiolight.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityArcFlower.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityArcbeast.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityStimulo.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityStimulosus.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntitySoulvyre.class, 5, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityOmnilight.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
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

        blacklist.add(biomeAbyss);
        blacklist.add(biomeAncientCavern);
        blacklist.add(biomeBarathos);
        blacklist.add(biomeCeleve);
        blacklist.add(biomeCandyland);
        blacklist.add(biomeCreeponia);
        blacklist.add(biomeCrystevia);
        blacklist.add(biomeDeeplands);
        blacklist.add(biomeDustopia);
        blacklist.add(biomeGardencia);
        blacklist.add(biomeGreckon);
        blacklist.add(biomeHaven);
        blacklist.add(biomeIromine);
        blacklist.add(biomeImmortallis);
        blacklist.add(biomeLBorean);
        blacklist.add(biomeLelyetia);
        blacklist.add(biomeLunalus);
        blacklist.add(biomeMysterium);
        blacklist.add(biomePrecasia);
        blacklist.add(biomeRunandor);
        blacklist.add(biomeShyrelands);
        blacklist.add(biomeVoxPonds);
        blacklist.add(Biomes.HELL);
        blacklist.add(Biomes.VOID);
        blacklist.add(Biomes.SKY);

        for (Biome biome : ForgeRegistries.BIOMES.getValuesCollection()) {
            if (!blacklist.contains(biome))
                suspectedOverworldBiomes.add(biome);
        }

        return suspectedOverworldBiomes.toArray(new Biome[0]);
    }

    public static void addEventSpawns(Enums.CreatureEvents event) {
        ArrayList<SpawnEntry> spawnList = null;

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

        for (SpawnEntry entry : spawnList) {
            EntityRegistry.addSpawn(entry.entityClass, entry.weight, entry.minGroupSize, entry.maxGroupSize, entry.creatureType, entry.biomes);
        }
    }

    public static void removeEventSpawns(Enums.CreatureEvents event) {
        ArrayList<SpawnEntry> spawnList = null;

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

        for (SpawnEntry entry : spawnList) {
            EntityRegistry.removeSpawn(entry.entityClass, entry.creatureType, entry.biomes);
        }
    }

    private static class SpawnEntry {
    	private final Class<? extends EntityLiving> entityClass;
    	private final int weight;
    	private final int minGroupSize;
    	private final int maxGroupSize;
    	private final EnumCreatureType creatureType;
    	private final Biome[] biomes;

    	private SpawnEntry(Class<? extends EntityLiving> entityClass, int weight, int minGroupSize, int maxGroupSize, EnumCreatureType creatureType, Biome... biomes) {
    	    this.entityClass = entityClass;
    	    this.weight = weight;
    	    this.minGroupSize = minGroupSize;
    	    this.maxGroupSize = maxGroupSize;
    	    this.creatureType = creatureType;
    	    this.biomes = biomes;
        }
	}
}
