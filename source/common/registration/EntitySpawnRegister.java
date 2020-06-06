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
import net.tslat.aoa3.entity.passive.*;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import java.util.ArrayList;
import java.util.Collection;
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

        if (!ConfigurationUtil.MainConfig.disableOverworldMobs)
            addSpawns(getOverworldSpawns(false));

        addSpawns(getNetherSpawns());
        addSpawns(getDimensionSpawns());
    }

	public static HashSet<SpawnEntry> getOverworldSpawns(boolean includeEventSpawns) {
	    final HashSet<SpawnEntry> spawns = new HashSet<SpawnEntry>();
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
        
        spawns.add(new SpawnEntry(EntityBombCarrier.class, 1, 0, 1, EnumCreatureType.MONSTER, overworldBiomes));
        spawns.add(new SpawnEntry(EntityEverbeast.class, 5, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        spawns.add(new SpawnEntry(EntityFacelessRunner.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        spawns.add(new SpawnEntry(EntityGhost.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        spawns.add(new SpawnEntry(EntityMotherVoidWalker.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        spawns.add(new SpawnEntry(EntityShadow.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        spawns.add(new SpawnEntry(EntityTrickster.class, 40, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        spawns.add(new SpawnEntry(EntityVoidCharger.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        spawns.add(new SpawnEntry(EntityVoidWalker.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        spawns.add(new SpawnEntry(EntityBugeye.class, 70, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        spawns.add(new SpawnEntry(EntityDicer.class, 35, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        spawns.add(new SpawnEntry(EntityNightReaper.class, 35, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        spawns.add(new SpawnEntry(EntityDemonReaper.class, 1, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        spawns.add(new SpawnEntry(EntityClown.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        fullMoonSpawns.add(new SpawnEntry(EntityIrkling.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        deathDaySpawns.add(new SpawnEntry(EntityReaperTwins.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        lunarInvasionSpawns.add(new SpawnEntry(EntityRoloscope.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        bigDaySpawns.add(new SpawnEntry(EntityWoodGiant.class, 50, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        deathDaySpawns.add(new SpawnEntry(EntityTriclops.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        lunarInvasionSpawns.add(new SpawnEntry(EntityVertebron.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        lunarInvasionSpawns.add(new SpawnEntry(EntityWalker.class, 70, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        deathDaySpawns.add(new SpawnEntry(EntityHeadlessDestroyer.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        bloodHuntSpawns.add(new SpawnEntry(EntityBloodmist.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        bloodHuntSpawns.add(new SpawnEntry(EntityLinger.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
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
        fullMoonSpawns.add(new SpawnEntry(EntityDarkBeast.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        creepDaySpawns.add(new SpawnEntry(EntityHost.class, 40, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        deathDaySpawns.add(new SpawnEntry(EntityDeathHunter.class, 40, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        bloodHuntSpawns.add(new SpawnEntry(EntityAnemia.class, 35, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        lunarInvasionSpawns.add(new SpawnEntry(EntityModulo.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes));
        
        spawns.add(new SpawnEntry(EntityGoalby.class, 60, 1, 1, EnumCreatureType.MONSTER, mountainBiomes));
        spawns.add(new SpawnEntry(EntityGrunt.class, 20, 1, 1, EnumCreatureType.MONSTER, mountainBiomes));
        spawns.add(new SpawnEntry(EntityMagicke.class, 60, 1, 1, EnumCreatureType.MONSTER, mountainBiomes));
        bigDaySpawns.add(new SpawnEntry(EntityStoneGiant.class, 60, 1, 1, EnumCreatureType.MONSTER, mountainBiomes));

        spawns.add(new SpawnEntry(EntityHunch.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes));
        spawns.add(new SpawnEntry(EntityPolarUrsa.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes));
        spawns.add(new SpawnEntry(EntityYeti.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes));
        spawns.add(new SpawnEntry(EntitySnowCharger.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes));
        bigDaySpawns.add(new SpawnEntry(EntityIceGiant.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes));

        spawns.add(new SpawnEntry(EntityDesertCharger.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes));
        spawns.add(new SpawnEntry(EntityFurlion.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes));
        spawns.add(new SpawnEntry(EntitySandGolem.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes));
        spawns.add(new SpawnEntry(EntitySphinx.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes));
        spawns.add(new SpawnEntry(EntityWickett.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes));
        bigDaySpawns.add(new SpawnEntry(EntitySandGiant.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes));

        spawns.add(new SpawnEntry(EntityChimera.class, 75, 1, 1, EnumCreatureType.MONSTER, savannaBiomes));
        spawns.add(new SpawnEntry(EntityBoneback.class, 75, 1, 1, EnumCreatureType.MONSTER, savannaBiomes));
        spawns.add(new SpawnEntry(EntityRammerhead.class, 75, 1, 1, EnumCreatureType.MONSTER, savannaBiomes));
        spawns.add(new SpawnEntry(EntityElkanyne.class, 20, 1, 1, EnumCreatureType.CREATURE, savannaBiomes));

        spawns.add(new SpawnEntry(EntityBushBaby.class, 70, 1, 1, EnumCreatureType.MONSTER, jungleBiomes));
        spawns.add(new SpawnEntry(EntityStinger.class, 70, 1, 1, EnumCreatureType.MONSTER, jungleBiomes));

        spawns.add(new SpawnEntry(EntityChomper.class, 70, 1, 1, EnumCreatureType.MONSTER, swampBiomes));
        spawns.add(new SpawnEntry(EntitySkipper.class, 30, 1, 1, EnumCreatureType.MONSTER, swampBiomes));
        spawns.add(new SpawnEntry(EntityFishix.class, 70, 1, 1, EnumCreatureType.MONSTER, swampBiomes));
        spawns.add(new SpawnEntry(EntitySwampCharger.class, 70, 1, 1, EnumCreatureType.MONSTER, swampBiomes));
        spawns.add(new SpawnEntry(EntityHag.class, 70, 1, 1, EnumCreatureType.MONSTER, swampBiomes));

        spawns.add(new SpawnEntry(EntityBlackUrsa.class, 70, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes));
        spawns.add(new SpawnEntry(EntityHidingFungi.class, 40, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes));
        spawns.add(new SpawnEntry(EntityNatura.class, 70, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes));
        spawns.add(new SpawnEntry(EntitySkolle.class, 15, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes));
        spawns.add(new SpawnEntry(EntityUrka.class, 70, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes));
        bigDaySpawns.add(new SpawnEntry(EntityLeafyGiant.class, 70, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes));

        spawns.add(new SpawnEntry(EntityHillCharger.class, 50, 1, 1, EnumCreatureType.MONSTER, mesaBiomes));
        spawns.add(new SpawnEntry(EntityMuckopede.class, 50, 1, 1, EnumCreatureType.MONSTER, mesaBiomes));

        spawns.add(new SpawnEntry(EntityPincher.class, 1, 0, 1, EnumCreatureType.WATER_CREATURE, oceanBiomes));

        spawns.add(new SpawnEntry(EntitySeaCharger.class, 20, 1, 1, EnumCreatureType.MONSTER, beachBiomes));
        spawns.add(new SpawnEntry(EntitySpinux.class, 20, 1, 1, EnumCreatureType.MONSTER, beachBiomes));
        spawns.add(new SpawnEntry(EntitySeaTroll.class, 20, 1, 1, EnumCreatureType.MONSTER, beachBiomes));
        spawns.add(new SpawnEntry(EntityTrollTrader.class, 1, 0, 1, EnumCreatureType.CREATURE, beachBiomes));

        spawns.add(new SpawnEntry(EntityCyclops.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes));
        spawns.add(new SpawnEntry(EntityCharger.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes));
        spawns.add(new SpawnEntry(EntitySasquatch.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes));
        spawns.add(new SpawnEntry(EntityHeadlessHunter.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes));
        spawns.add(new SpawnEntry(EntityGlisteningPixon.class, 30, 1, 3, EnumCreatureType.CREATURE, genericBiomes));
        spawns.add(new SpawnEntry(EntityBoneCreature.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes));
        spawns.add(new SpawnEntry(EntityKingCharger.class, 1, 0, 1, EnumCreatureType.MONSTER, genericBiomes));
        spawns.add(new SpawnEntry(EntityBrute.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes));
        spawns.add(new SpawnEntry(EntityGhostineAncient.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes));
        spawns.add(new SpawnEntry(EntityGoblin.class, 20, 0, 1, EnumCreatureType.MONSTER, genericBiomes));
        spawns.add(new SpawnEntry(EntityAssassin.class, 1, 0, 1, EnumCreatureType.CREATURE, genericBiomes));
        spawns.add(new SpawnEntry(EntityNaturalist.class, 1, 0, 1, EnumCreatureType.CREATURE, genericBiomes));
        spawns.add(new SpawnEntry(EntityRealmshifter.class, 1, 0, 1, EnumCreatureType.CREATURE, genericBiomes));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, genericBiomes));
        spawns.add(new SpawnEntry(EntityLottoman.class, 1, 0, 1, EnumCreatureType.CREATURE, genericBiomes));
        spawns.add(new SpawnEntry(EntityNightfly.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes));

        if (ConfigurationUtil.StructureConfig.overworld.ruinedTeleporterFrameSpawnChance == 0)
            spawns.add(new SpawnEntry(EntityCorruptedTraveller.class, 1, 0, 1, EnumCreatureType.CREATURE, genericBiomes));

        spawns.add(new SpawnEntry(EntityHorndron.class, 5, 0, 1, EnumCreatureType.MONSTER, genericPlainsBiomes));
        spawns.add(new SpawnEntry(EntityWarclops.class, 5, 0, 1, EnumCreatureType.MONSTER, genericPlainsBiomes));
        spawns.add(new SpawnEntry(EntityAncientGolem.class, 5, 0, 1, EnumCreatureType.MONSTER, genericPlainsBiomes));

        spawns.add(new SpawnEntry(EntityDeadTree.class, 10, 0, 1, EnumCreatureType.MONSTER, Biomes.FOREST, Biomes.FOREST_HILLS));
        
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
    
    public static HashSet<SpawnEntry> getNetherSpawns() {
	    final HashSet<SpawnEntry> spawns = new HashSet<SpawnEntry>();
        Biome[] netherBiomes = biomes(BiomeDictionary.Type.NETHER);
        
        spawns.add(new SpawnEntry(EntityWitheringLottoman.class, 1, 0, 1, EnumCreatureType.CREATURE, netherBiomes));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, netherBiomes));
        spawns.add(new SpawnEntry(EntityEmbrake.class, 60, 0, 1, EnumCreatureType.MONSTER, netherBiomes));
        spawns.add(new SpawnEntry(EntityFakePigman.class, 80, 0, 1, EnumCreatureType.MONSTER, netherBiomes));
        spawns.add(new SpawnEntry(EntityFlamewalker.class, 60, 0, 1, EnumCreatureType.MONSTER, netherBiomes));
        spawns.add(new SpawnEntry(EntityHellspot.class, 60, 0, 1, EnumCreatureType.MONSTER, netherBiomes));
        spawns.add(new SpawnEntry(EntityInfernal.class, 10, 0, 1, EnumCreatureType.MONSTER, netherBiomes));
        spawns.add(new SpawnEntry(EntityWitherWizard.class, 55, 0, 1, EnumCreatureType.MONSTER, netherBiomes));
        spawns.add(new SpawnEntry(EntitySkeletalCowman.class, 45, 0, 1, EnumCreatureType.MONSTER, netherBiomes));
        spawns.add(new SpawnEntry(EntityHellcat.class, 50, 0, 1, EnumCreatureType.MONSTER, netherBiomes));
        spawns.add(new SpawnEntry(EntityLittleBam.class, 35, 0, 1, EnumCreatureType.MONSTER, netherBiomes));
        
        return spawns;
    }
    
    public static HashSet<SpawnEntry> getDimensionSpawns() {
	    final HashSet<SpawnEntry> spawns = new HashSet<SpawnEntry>();

        spawns.add(new SpawnEntry(EntityBloomingPixon.class, 12, 1, 3, EnumCreatureType.CREATURE, MYSTERIUM));
        spawns.add(new SpawnEntry(EntityRunationMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, MYSTERIUM));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, MYSTERIUM));
        spawns.add(new SpawnEntry(EntityFungat.class, 20, 0, 1, EnumCreatureType.MONSTER, MYSTERIUM));
        spawns.add(new SpawnEntry(EntityFungback.class, 15, 0, 1, EnumCreatureType.MONSTER, MYSTERIUM));
        spawns.add(new SpawnEntry(EntityFungik.class, 15, 0, 1, EnumCreatureType.MONSTER, MYSTERIUM));
        spawns.add(new SpawnEntry(EntityFungung.class, 1, 0, 1, EnumCreatureType.MONSTER, MYSTERIUM));
        spawns.add(new SpawnEntry(EntityEeo.class, 15, 0, 1, EnumCreatureType.MONSTER, MYSTERIUM));

        spawns.add(new SpawnEntry(EntityGlaringPixon.class, 12, 1, 3, EnumCreatureType.CREATURE, LELYETIA));
        spawns.add(new SpawnEntry(EntityLoggingMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, LELYETIA));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, LELYETIA));
        spawns.add(new SpawnEntry(EntityFlye.class, 20, 0, 1, EnumCreatureType.MONSTER, LELYETIA));
        spawns.add(new SpawnEntry(EntityGrobbler.class, 1, 0, 1, EnumCreatureType.MONSTER, LELYETIA));
        spawns.add(new SpawnEntry(EntityLelyetianCaster.class, 20, 0, 1, EnumCreatureType.MONSTER, LELYETIA));
        spawns.add(new SpawnEntry(EntityLelyetianWarrior.class, 20, 0, 1, EnumCreatureType.MONSTER, LELYETIA));
        spawns.add(new SpawnEntry(EntityTracker.class, 20, 0, 1, EnumCreatureType.MONSTER, LELYETIA));
        spawns.add(new SpawnEntry(EntityTrotter.class, 20, 0, 1, EnumCreatureType.CREATURE, LELYETIA));

        spawns.add(new SpawnEntry(EntityGleamingPixon.class, 12, 1, 3, EnumCreatureType.CREATURE, RUNANDOR));
        spawns.add(new SpawnEntry(EntityAnimaMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, RUNANDOR));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, RUNANDOR));
        spawns.add(new SpawnEntry(EntityAriel.class, 20, 0, 1, EnumCreatureType.MONSTER, RUNANDOR));
        spawns.add(new SpawnEntry(EntityBouncer.class, 20, 0, 1, EnumCreatureType.MONSTER, RUNANDOR));
        spawns.add(new SpawnEntry(EntityEyeCreature.class, 20, 0, 1, EnumCreatureType.MONSTER, RUNANDOR));
        spawns.add(new SpawnEntry(EntityPaladin.class, 20, 0, 1, EnumCreatureType.MONSTER, RUNANDOR));
        spawns.add(new SpawnEntry(EntityRunicorn.class, 20, 0, 1, EnumCreatureType.MONSTER, RUNANDOR));
        spawns.add(new SpawnEntry(EntityRunicornRider.class, 1, 0, 1, EnumCreatureType.MONSTER, RUNANDOR));

        spawns.add(new SpawnEntry(EntityGlowingPixon.class, 12, 1, 3, EnumCreatureType.CREATURE, LBOREAN));
        spawns.add(new SpawnEntry(EntityHaulingMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, LBOREAN));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, LBOREAN));
        spawns.add(new SpawnEntry(EntityAngler.class, 20, 0, 1, EnumCreatureType.MONSTER, LBOREAN));
        spawns.add(new SpawnEntry(EntityCoralon.class, 20, 0, 1, EnumCreatureType.MONSTER, LBOREAN));
        spawns.add(new SpawnEntry(EntityMuncher.class, 20, 0, 1, EnumCreatureType.MONSTER, LBOREAN));
        spawns.add(new SpawnEntry(EntityNeptuno.class, 1, 0, 1, EnumCreatureType.MONSTER, LBOREAN));
        spawns.add(new SpawnEntry(EntitySeaViper.class, 20, 0, 1, EnumCreatureType.MONSTER, LBOREAN));
        spawns.add(new SpawnEntry(EntityCoratee.class, 20, 0, 1, EnumCreatureType.CREATURE, LBOREAN));

        spawns.add(new SpawnEntry(EntityRadiantPixon.class, 12, 1, 3, EnumCreatureType.CREATURE, DUSTOPIA));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, DUSTOPIA));
        spawns.add(new SpawnEntry(EntityBasilisk.class, 20, 0, 1, EnumCreatureType.MONSTER, DUSTOPIA));
        spawns.add(new SpawnEntry(EntityDevourer.class, 20, 0, 1, EnumCreatureType.MONSTER, DUSTOPIA));
        spawns.add(new SpawnEntry(EntityDuston.class, 20, 0, 1, EnumCreatureType.MONSTER, DUSTOPIA));
        spawns.add(new SpawnEntry(EntityLostSoul.class, 20, 0, 1, EnumCreatureType.MONSTER, DUSTOPIA));
        spawns.add(new SpawnEntry(EntityDusteiva.class, 20, 0, 1, EnumCreatureType.MONSTER, DUSTOPIA));
        spawns.add(new SpawnEntry(EntityStalker.class, 20, 0, 1, EnumCreatureType.MONSTER, DUSTOPIA));
        spawns.add(new SpawnEntry(EntityStalkerPrime.class, 1, 0, 1, EnumCreatureType.MONSTER, DUSTOPIA));
        spawns.add(new SpawnEntry(EntityLurker.class, 20, 0, 1, EnumCreatureType.MONSTER, DUSTOPIA));

        spawns.add(new SpawnEntry(EntityShiningPixon.class, 12, 1, 1, EnumCreatureType.CREATURE, ABYSS));
        spawns.add(new SpawnEntry(EntityButcheryMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, ABYSS));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, ABYSS));
        spawns.add(new SpawnEntry(EntityApparition.class, 20, 0, 1, EnumCreatureType.MONSTER, ABYSS));
        spawns.add(new SpawnEntry(EntityBloodsucker.class, 20, 0, 1, EnumCreatureType.MONSTER, ABYSS));
        spawns.add(new SpawnEntry(EntityDistorter.class, 20, 0, 1, EnumCreatureType.MONSTER, ABYSS));
        spawns.add(new SpawnEntry(EntityFiend.class, 20, 0, 1, EnumCreatureType.MONSTER, ABYSS));
        spawns.add(new SpawnEntry(EntityOcculent.class, 20, 0, 1, EnumCreatureType.MONSTER, ABYSS));
        spawns.add(new SpawnEntry(EntityWebReaper.class, 20, 0, 1, EnumCreatureType.MONSTER, ABYSS));
        spawns.add(new SpawnEntry(EntitySlimer.class, 1, 0, 1, EnumCreatureType.MONSTER, ABYSS));

        spawns.add(new SpawnEntry(EntityCreationMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, BARATHOS));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, BARATHOS));
        spawns.add(new SpawnEntry(EntityCryptid.class, 30, 0, 1, EnumCreatureType.MONSTER, BARATHOS));
        spawns.add(new SpawnEntry(EntityArkback.class, 20, 0, 1, EnumCreatureType.MONSTER, BARATHOS));
        spawns.add(new SpawnEntry(EntityEchodar.class, 20, 0, 1, EnumCreatureType.MONSTER, BARATHOS));
        spawns.add(new SpawnEntry(EntityEilosapien.class, 1, 0, 1, EnumCreatureType.MONSTER, BARATHOS));
        spawns.add(new SpawnEntry(EntityNospike.class, 1, 0, 1, EnumCreatureType.MONSTER, BARATHOS));
        spawns.add(new SpawnEntry(EntityEmperorBeast.class, 20, 0, 1, EnumCreatureType.MONSTER, BARATHOS));
        spawns.add(new SpawnEntry(EntityParasect.class, 20, 0, 1, EnumCreatureType.MONSTER, BARATHOS));
        spawns.add(new SpawnEntry(EntityKeeler.class, 20, 0, 1, EnumCreatureType.MONSTER, BARATHOS));
        spawns.add(new SpawnEntry(EntitySquiggler.class, 1, 0, 1, EnumCreatureType.MONSTER, BARATHOS));
        spawns.add(new SpawnEntry(EntityTharafly.class, 15, 0, 1, EnumCreatureType.MONSTER, BARATHOS));
        spawns.add(new SpawnEntry(EntityRamradon.class, 20, 0, 1, EnumCreatureType.MONSTER, BARATHOS));

        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, CANDYLAND));
        spawns.add(new SpawnEntry(EntityAirhead.class, 20, 0, 1, EnumCreatureType.MONSTER, CANDYLAND));
        spawns.add(new SpawnEntry(EntityCandyCorny.class, 20, 0, 1, EnumCreatureType.MONSTER, CANDYLAND));
        spawns.add(new SpawnEntry(EntityCherryBarrager.class, 1, 0, 1, EnumCreatureType.MONSTER, CANDYLAND));
        spawns.add(new SpawnEntry(EntityLollypopper.class, 20, 0, 1, EnumCreatureType.MONSTER, CANDYLAND));
        spawns.add(new SpawnEntry(EntityCherryBlaster.class, 20, 0, 1, EnumCreatureType.MONSTER, CANDYLAND));
        spawns.add(new SpawnEntry(EntityPeppermintSnail.class, 20, 0, 1, EnumCreatureType.CREATURE, CANDYLAND));
        spawns.add(new SpawnEntry(EntitySpearmintSnail.class, 20, 0, 1, EnumCreatureType.CREATURE, CANDYLAND));

        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, CRYSTEVIA));
        spawns.add(new SpawnEntry(EntityConstructStrength.class, 20, 0, 1, EnumCreatureType.MONSTER, CRYSTEVIA));
        spawns.add(new SpawnEntry(EntityConstructRange.class, 20, 0, 1, EnumCreatureType.MONSTER, CRYSTEVIA));
        spawns.add(new SpawnEntry(EntityConstructTerror.class, 20, 0, 1, EnumCreatureType.MONSTER, CRYSTEVIA));
        spawns.add(new SpawnEntry(EntityConstructSpeed.class, 20, 0, 1, EnumCreatureType.MONSTER, CRYSTEVIA));
        spawns.add(new SpawnEntry(EntityConstructResistance.class, 20, 0, 1, EnumCreatureType.MONSTER, CRYSTEVIA));
        spawns.add(new SpawnEntry(EntityConstructFlight.class, 20, 0, 1, EnumCreatureType.MONSTER, CRYSTEVIA));
        spawns.add(new SpawnEntry(EntityConstructMind.class, 2, 0, 1, EnumCreatureType.MONSTER, CRYSTEVIA));

        spawns.add(new SpawnEntry(EntityExtractionMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, DEEPLANDS));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, DEEPLANDS));
        spawns.add(new SpawnEntry(EntityCaseConstruct.class, 1, 0, 1, EnumCreatureType.MONSTER, DEEPLANDS));
        spawns.add(new SpawnEntry(EntityDoubler.class, 1, 0, 1, EnumCreatureType.MONSTER, DEEPLANDS));
        spawns.add(new SpawnEntry(EntityDweller.class, 20, 0, 1, EnumCreatureType.MONSTER, DEEPLANDS));
        spawns.add(new SpawnEntry(EntityCaveCreep.class, 20, 0, 1, EnumCreatureType.MONSTER, DEEPLANDS));
        spawns.add(new SpawnEntry(EntityRockbiter.class, 20, 0, 1, EnumCreatureType.MONSTER, DEEPLANDS));
        spawns.add(new SpawnEntry(EntityRockCritter.class, 20, 0, 1, EnumCreatureType.MONSTER, DEEPLANDS));
        spawns.add(new SpawnEntry(EntityRockCrawler.class, 20, 0, 1, EnumCreatureType.MONSTER, DEEPLANDS));
        spawns.add(new SpawnEntry(EntityShik.class, 5, 0, 3, EnumCreatureType.AMBIENT, DEEPLANDS));
        spawns.add(new SpawnEntry(EntityMetalloid.class, 1, 0, 1, EnumCreatureType.CREATURE, IROMINE));
        spawns.add(new SpawnEntry(EntityForagingMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, IROMINE));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, IROMINE));
        spawns.add(new SpawnEntry(EntityMechachron.class, 1, 0, 1, EnumCreatureType.MONSTER, IROMINE));
        spawns.add(new SpawnEntry(EntityMechamaton.class, 20, 0, 1, EnumCreatureType.MONSTER, IROMINE));
        spawns.add(new SpawnEntry(EntityPolytom.class, 20, 0, 1, EnumCreatureType.MONSTER, IROMINE));
        spawns.add(new SpawnEntry(EntityQuickpocket.class, 20, 0, 1, EnumCreatureType.MONSTER, IROMINE));
        spawns.add(new SpawnEntry(EntityVoltron.class, 20, 0, 1, EnumCreatureType.MONSTER, IROMINE));

        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, HAVEN));
        spawns.add(new SpawnEntry(EntityAngelica.class, 15, 0, 1, EnumCreatureType.MONSTER, HAVEN));
        spawns.add(new SpawnEntry(EntityAutomaton.class, 20, 0, 1, EnumCreatureType.MONSTER, HAVEN));
        spawns.add(new SpawnEntry(EntityOrbiter.class, 15, 0, 1, EnumCreatureType.MONSTER, HAVEN));
        spawns.add(new SpawnEntry(EntitySeeker.class, 15, 0, 1, EnumCreatureType.MONSTER, HAVEN));
        spawns.add(new SpawnEntry(EntityRainicorn.class, 2, 0, 1, EnumCreatureType.MONSTER, HAVEN));
        spawns.add(new SpawnEntry(EntitySurveyor.class, 5, 0, 1, EnumCreatureType.MONSTER, HAVEN));
        spawns.add(new SpawnEntry(EntityVolar.class, 3, 0, 1, EnumCreatureType.MONSTER, HAVEN));
        spawns.add(new SpawnEntry(EntityVoliant.class, 1, 0, 1, EnumCreatureType.MONSTER, HAVEN));
        spawns.add(new SpawnEntry(EntityHalycon.class, 5, 1, 1, EnumCreatureType.CREATURE, HAVEN));

        spawns.add(new SpawnEntry(EntityInfusionMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, GRECKON));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, GRECKON));
        spawns.add(new SpawnEntry(EntityGrillface.class, 20, 0, 1, EnumCreatureType.MONSTER, GRECKON));
        spawns.add(new SpawnEntry(EntityShifter.class, 20, 0, 1, EnumCreatureType.MONSTER, GRECKON));
        spawns.add(new SpawnEntry(EntitySilencer.class, 1, 0, 1, EnumCreatureType.MONSTER, GRECKON));
        spawns.add(new SpawnEntry(EntitySkullCreature.class, 20, 0, 1, EnumCreatureType.MONSTER, GRECKON));
        spawns.add(new SpawnEntry(EntitySugarface.class, 20, 0, 1, EnumCreatureType.MONSTER, GRECKON));
        spawns.add(new SpawnEntry(EntityValkyrie.class, 20, 0, 1, EnumCreatureType.MONSTER, GRECKON));
        spawns.add(new SpawnEntry(EntityHunter.class, 20, 0, 1, EnumCreatureType.MONSTER, GRECKON));

        spawns.add(new SpawnEntry(EntityInnervationMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, CELEVE));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, CELEVE));
        spawns.add(new SpawnEntry(EntityBobo.class, 20, 0, 1, EnumCreatureType.MONSTER, CELEVE));
        spawns.add(new SpawnEntry(EntityChocko.class, 20, 0, 1, EnumCreatureType.MONSTER, CELEVE));
        spawns.add(new SpawnEntry(EntityJumbo.class, 2, 0, 1, EnumCreatureType.MONSTER, CELEVE));
        spawns.add(new SpawnEntry(EntityKoko.class, 20, 0, 1, EnumCreatureType.MONSTER, CELEVE));
        spawns.add(new SpawnEntry(EntityKranky.class, 20, 0, 1, EnumCreatureType.MONSTER, CELEVE));
        spawns.add(new SpawnEntry(EntitySnappy.class, 20, 0, 1, EnumCreatureType.MONSTER, CELEVE));
        spawns.add(new SpawnEntry(EntitySticky.class, 20, 0, 1, EnumCreatureType.MONSTER, CELEVE));
        spawns.add(new SpawnEntry(EntityStitches.class, 20, 0, 1, EnumCreatureType.MONSTER, CELEVE));
        spawns.add(new SpawnEntry(EntityTipsy.class, 20, 0, 1, EnumCreatureType.MONSTER, CELEVE));

        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, LUNALUS));
        spawns.add(new SpawnEntry(EntityExplodot.class, 20, 0, 1, EnumCreatureType.MONSTER, LUNALUS));
        spawns.add(new SpawnEntry(EntityLunarcher.class, 20, 0, 1, EnumCreatureType.MONSTER, LUNALUS));
        spawns.add(new SpawnEntry(EntityVisular.class, 20, 0, 1, EnumCreatureType.MONSTER, LUNALUS));
        spawns.add(new SpawnEntry(EntityVisulon.class, 1, 0, 1, EnumCreatureType.MONSTER, LUNALUS));

        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, CREEPONIA));
        spawns.add(new SpawnEntry(EntityBoneCreeper.class, 40, 0, 1, EnumCreatureType.MONSTER, CREEPONIA));
        spawns.add(new SpawnEntry(EntityCaveCreepoid.class, 25, 0, 1, EnumCreatureType.MONSTER, CREEPONIA));
        spawns.add(new SpawnEntry(EntityCreeper.class, 40, 0, 1, EnumCreatureType.MONSTER, CREEPONIA));
        spawns.add(new SpawnEntry(EntityCreeperlock.class, 25, 0, 1, EnumCreatureType.MONSTER, CREEPONIA));
        spawns.add(new SpawnEntry(EntityCreepird.class, 15, 0, 1, EnumCreatureType.MONSTER, CREEPONIA));
        spawns.add(new SpawnEntry(EntityCreepuple.class, 40, 0, 1, EnumCreatureType.MONSTER, CREEPONIA));
        spawns.add(new SpawnEntry(EntityKingCreeper.class, 1, 0, 1, EnumCreatureType.MONSTER, CREEPONIA));
        spawns.add(new SpawnEntry(EntityMagicalCreeper.class, 25, 0, 1, EnumCreatureType.MONSTER, CREEPONIA));
        spawns.add(new SpawnEntry(EntityWingedCreeper.class, 30, 0, 1, EnumCreatureType.MONSTER, CREEPONIA));
        spawns.add(new SpawnEntry(EntityCreepCow.class, 5, 0, 1, EnumCreatureType.CREATURE, CREEPONIA));

        spawns.add(new SpawnEntry(EntityAmbientPixon.class, 12, 1, 3, EnumCreatureType.CREATURE, HAVEN, RUNANDOR, CANDYLAND, SHYRELANDS));

        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, PRECASIA));
        spawns.add(new SpawnEntry(EntityDyrehorn.class, 20, 0, 1, EnumCreatureType.MONSTER, PRECASIA));
        spawns.add(new SpawnEntry(EntityGiantSnail.class, 20, 0, 1, EnumCreatureType.MONSTER, PRECASIA));
        spawns.add(new SpawnEntry(EntityDeinotherium.class, 20, 0, 1, EnumCreatureType.MONSTER, PRECASIA));
        spawns.add(new SpawnEntry(EntitySabretooth.class, 20, 0, 1, EnumCreatureType.MONSTER, PRECASIA));
        spawns.add(new SpawnEntry(EntityTerradon.class, 1, 0, 1, EnumCreatureType.MONSTER, PRECASIA));
        spawns.add(new SpawnEntry(EntityTortione.class, 3, 0, 1, EnumCreatureType.MONSTER, PRECASIA));
        spawns.add(new SpawnEntry(EntityMeganeuropsis.class, 5, 1, 2, EnumCreatureType.CREATURE, PRECASIA));

        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, VOX_PONDS));
        spawns.add(new SpawnEntry(EntityToxxulous.class, 20, 0, 1, EnumCreatureType.MONSTER, VOX_PONDS));
        spawns.add(new SpawnEntry(EntityGrocculate.class, 20, 0, 1, EnumCreatureType.MONSTER, VOX_PONDS));
        spawns.add(new SpawnEntry(EntityGadgetoid.class, 20, 0, 1, EnumCreatureType.MONSTER, VOX_PONDS));
        spawns.add(new SpawnEntry(EntityAlarmo.class, 20, 0, 1, EnumCreatureType.MONSTER, VOX_PONDS));
        spawns.add(new SpawnEntry(EntityFischer.class, 1, 0, 1, EnumCreatureType.MONSTER, VOX_PONDS));
        spawns.add(new SpawnEntry(EntityDestructor.class, 1, 0, 1, EnumCreatureType.MONSTER, VOX_PONDS));
        spawns.add(new SpawnEntry(EntityCentinel.class, 20, 0, 1, EnumCreatureType.MONSTER, VOX_PONDS));

        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, GARDENCIA));
        spawns.add(new SpawnEntry(EntityArchvine.class, 20, 0, 1, EnumCreatureType.MONSTER, GARDENCIA));
        spawns.add(new SpawnEntry(EntityBroccohead.class, 20, 0, 1, EnumCreatureType.MONSTER, GARDENCIA));
        spawns.add(new SpawnEntry(EntityCarrotop.class, 20, 0, 1, EnumCreatureType.MONSTER, GARDENCIA));
        spawns.add(new SpawnEntry(EntityFlowerface.class, 20, 0, 1, EnumCreatureType.MONSTER, GARDENCIA));
        spawns.add(new SpawnEntry(EntitySquasher.class, 20, 0, 1, EnumCreatureType.MONSTER, GARDENCIA));
        spawns.add(new SpawnEntry(EntitySunny.class, 1, 0, 1, EnumCreatureType.MONSTER, GARDENCIA));
        spawns.add(new SpawnEntry(EntityCorny.class, 20, 0, 1, EnumCreatureType.MONSTER, GARDENCIA));

        spawns.add(new SpawnEntry(EntityExpeditionMaster.class, 1, 0, 1, EnumCreatureType.CREATURE, SHYRELANDS));
        spawns.add(new SpawnEntry(EntityUndeadHerald.class, 1, 0, 1, EnumCreatureType.CREATURE, SHYRELANDS));
        spawns.add(new SpawnEntry(EntitySysker.class, 20, 0, 1, EnumCreatureType.MONSTER, SHYRELANDS));
        spawns.add(new SpawnEntry(EntityShyreKnight.class, 20, 0, 1, EnumCreatureType.MONSTER, SHYRELANDS));
        spawns.add(new SpawnEntry(EntityAxiolight.class, 20, 0, 1, EnumCreatureType.MONSTER, SHYRELANDS));
        spawns.add(new SpawnEntry(EntityArcFlower.class, 20, 0, 1, EnumCreatureType.MONSTER, SHYRELANDS));
        spawns.add(new SpawnEntry(EntityArcbeast.class, 20, 0, 1, EnumCreatureType.MONSTER, SHYRELANDS));
        spawns.add(new SpawnEntry(EntityStimulo.class, 20, 0, 1, EnumCreatureType.MONSTER, SHYRELANDS));
        spawns.add(new SpawnEntry(EntityStimulosus.class, 1, 0, 1, EnumCreatureType.MONSTER, SHYRELANDS));
        spawns.add(new SpawnEntry(EntitySoulvyre.class, 5, 0, 1, EnumCreatureType.MONSTER, SHYRELANDS));
        spawns.add(new SpawnEntry(EntityOmnilight.class, 20, 0, 1, EnumCreatureType.MONSTER, SHYRELANDS));

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

        blacklist.add(ABYSS);
        blacklist.add(ANCIENT_CAVERN);
        blacklist.add(BARATHOS);
        blacklist.add(CELEVE);
        blacklist.add(CANDYLAND);
        blacklist.add(CREEPONIA);
        blacklist.add(CRYSTEVIA);
        blacklist.add(DEEPLANDS);
        blacklist.add(DUSTOPIA);
        blacklist.add(GARDENCIA);
        blacklist.add(GRECKON);
        blacklist.add(HAVEN);
        blacklist.add(IROMINE);
        blacklist.add(IMMORTALLIS);
        blacklist.add(LBOREAN);
        blacklist.add(LELYETIA);
        blacklist.add(LUNALUS);
        blacklist.add(MYSTERIUM);
        blacklist.add(PRECASIA);
        blacklist.add(RUNANDOR);
        blacklist.add(SHYRELANDS);
        blacklist.add(VOX_PONDS);
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

        addSpawns(spawnList);
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

        removeSpawns(spawnList);
    }

    public static void addSpawns(Collection<SpawnEntry> spawns) {
        spawns.forEach(entry -> EntityRegistry.addSpawn(entry.entityClass, entry.weight, entry.minGroupSize, entry.maxGroupSize, entry.creatureType, entry.biomes));
    }

    public static void removeSpawns(Collection<SpawnEntry> spawns) {
        spawns.forEach(entry -> EntityRegistry.removeSpawn(entry.entityClass, entry.creatureType, entry.biomes));
    }

    public static class SpawnEntry {
        public final Class<? extends EntityLiving> entityClass;
        public final int weight;
        public final int minGroupSize;
        public final int maxGroupSize;
        public final EnumCreatureType creatureType;
        public final Biome[] biomes;

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
