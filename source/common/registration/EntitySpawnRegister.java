package net.tslat.aoa3.common.registration;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.tslat.aoa3.entity.animals.EntityCreepCow;
import net.tslat.aoa3.entity.animals.EntityHalycon;
import net.tslat.aoa3.entity.misc.EntitySpawnFluffer;
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
import net.tslat.aoa3.entity.npcs.*;
import net.tslat.aoa3.entity.npcs.lottoman.EntityLottoman;
import net.tslat.aoa3.entity.npcs.lottoman.EntityWitheringLottoman;
import net.tslat.aoa3.entity.npcs.skillmaster.*;
import net.tslat.aoa3.utils.ConfigurationUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.DimensionRegister.*;

public class EntitySpawnRegister {

    public static void registerEntitySpawns() {
        if (!ConfigurationUtil.disableOverworldMobs) {
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
            EntityRegistry.addSpawn(EntityIrkling.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityReaperTwins.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityRoloscope.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityWoodGiant.class, 50, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityTriclops.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityVertebron.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityWalker.class, 70, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityEverbeast.class, 2, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityFacelessRunner.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityGhost.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityMotherVoidWalker.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityShadow.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityTrickster.class, 40, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityVoidCharger.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityVoidWalker.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityHeadlessDestroyer.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityBloodmist.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityLinger.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityBugeye.class, 70, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityDicer.class, 35, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityGhostlyBugeye.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityGhostlyCharger.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityGhostlyCyclops.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityGhostlyNightReaper.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityGhostlyNightReaper.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityGhostlySasquatch.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityTerrestrial.class, 2, 0, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntitySkellox.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityScrubby.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityNightWatcher.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityNightReaper.class, 35, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityDemonReaper.class, 1, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityDarkBeast.class, 60, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityClown.class, 65, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityHost.class, 40, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityDeathHunter.class, 40, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityAnemia.class, 35, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);
            EntityRegistry.addSpawn(EntityModulo.class, 100, 1, 1, EnumCreatureType.MONSTER, overworldBiomes);

            EntityRegistry.addSpawn(EntityGoalby.class, 60, 1, 1, EnumCreatureType.MONSTER, mountainBiomes);
            EntityRegistry.addSpawn(EntityGrunt.class, 20, 1, 1, EnumCreatureType.MONSTER, mountainBiomes);
            EntityRegistry.addSpawn(EntityStoneGiant.class, 60, 1, 1, EnumCreatureType.MONSTER, mountainBiomes);
            EntityRegistry.addSpawn(EntityMagicke.class, 60, 1, 1, EnumCreatureType.MONSTER, mountainBiomes);

            EntityRegistry.addSpawn(EntityHunch.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes);
            EntityRegistry.addSpawn(EntityIceGiant.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes);
            EntityRegistry.addSpawn(EntityPolarUrsa.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes);
            EntityRegistry.addSpawn(EntityYeti.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes);
            EntityRegistry.addSpawn(EntitySnowCharger.class, 70, 1, 1, EnumCreatureType.MONSTER, snowyBiomes);

            EntityRegistry.addSpawn(EntityDesertCharger.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes);
            EntityRegistry.addSpawn(EntityFurlion.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes);
            EntityRegistry.addSpawn(EntitySandGiant.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes);
            EntityRegistry.addSpawn(EntitySandGolem.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes);
            EntityRegistry.addSpawn(EntitySphinx.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes);
            EntityRegistry.addSpawn(EntityWickett.class, 30, 1, 1, EnumCreatureType.MONSTER, sandyBiomes);

            EntityRegistry.addSpawn(EntityChimera.class, 75, 1, 1, EnumCreatureType.MONSTER, savannaBiomes);
            EntityRegistry.addSpawn(EntityBoneback.class, 75, 1, 1, EnumCreatureType.MONSTER, savannaBiomes);
            EntityRegistry.addSpawn(EntityElkanyne.class, 75, 1, 1, EnumCreatureType.MONSTER, savannaBiomes);
            EntityRegistry.addSpawn(EntityRammerhead.class, 75, 1, 1, EnumCreatureType.MONSTER, savannaBiomes);

            EntityRegistry.addSpawn(EntityBushBaby.class, 70, 1, 1, EnumCreatureType.MONSTER, jungleBiomes);
            EntityRegistry.addSpawn(EntityStinger.class, 70, 1, 1, EnumCreatureType.MONSTER, jungleBiomes);

            EntityRegistry.addSpawn(EntityChomper.class, 70, 1, 1, EnumCreatureType.MONSTER, swampBiomes);
            EntityRegistry.addSpawn(EntitySkipper.class, 30, 1, 1, EnumCreatureType.MONSTER, swampBiomes);
            EntityRegistry.addSpawn(EntityFishix.class, 70, 1, 1, EnumCreatureType.MONSTER, swampBiomes);
            EntityRegistry.addSpawn(EntitySwampCharger.class, 70, 1, 1, EnumCreatureType.MONSTER, swampBiomes);
            EntityRegistry.addSpawn(EntityHag.class, 70, 1, 1, EnumCreatureType.MONSTER, swampBiomes);

            EntityRegistry.addSpawn(EntityBlackUrsa.class, 70, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes);
            EntityRegistry.addSpawn(EntityHidingFungi.class, 40, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes);
            EntityRegistry.addSpawn(EntityLeafyGiant.class, 70, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes);
            EntityRegistry.addSpawn(EntityNatura.class, 70, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes);
            EntityRegistry.addSpawn(EntitySkolle.class, 15, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes);
            EntityRegistry.addSpawn(EntityUrka.class, 70, 1, 1, EnumCreatureType.MONSTER, warmConiferousBiomes);

            EntityRegistry.addSpawn(EntityHillCharger.class, 50, 1, 1, EnumCreatureType.MONSTER, mesaBiomes);
            EntityRegistry.addSpawn(EntityMuckopede.class, 50, 1, 1, EnumCreatureType.MONSTER, mesaBiomes);

            EntityRegistry.addSpawn(EntityPincher.class, 1, 0, 1, EnumCreatureType.WATER_CREATURE, oceanBiomes);

            EntityRegistry.addSpawn(EntitySeaCharger.class, 20, 1, 1, EnumCreatureType.MONSTER, beachBiomes);
            EntityRegistry.addSpawn(EntitySpinux.class, 20, 1, 1, EnumCreatureType.MONSTER, beachBiomes);
            EntityRegistry.addSpawn(EntitySeaTroll.class, 20, 1, 1, EnumCreatureType.MONSTER, beachBiomes);
            EntityRegistry.addSpawn(EntityTrollTrader.class, 1, 0, 1, EnumCreatureType.AMBIENT, beachBiomes);

            EntityRegistry.addSpawn(EntityCyclops.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityCharger.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityTreeSpirit.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntitySasquatch.class, 70, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityHeadlessHunter.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityGlisteningPixon.class, 1, 0, 1, EnumCreatureType.AMBIENT, genericBiomes);
            EntityRegistry.addSpawn(EntityBoneCreature.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityKingCharger.class, 1, 0, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityBrute.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityGhostineAncient.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityGoblin.class, 20, 0, 1, EnumCreatureType.MONSTER, genericBiomes);
            EntityRegistry.addSpawn(EntityAssassin.class, 1, 0, 1, EnumCreatureType.AMBIENT, genericBiomes);
            EntityRegistry.addSpawn(EntityMetalloid.class, 1, 0, 1, EnumCreatureType.AMBIENT, genericBiomes);
            EntityRegistry.addSpawn(EntityNaturalist.class, 1, 0, 1, EnumCreatureType.AMBIENT, genericBiomes);
            EntityRegistry.addSpawn(EntityRealmshifter.class, 1, 0, 1, EnumCreatureType.AMBIENT, genericBiomes);
            EntityRegistry.addSpawn(EntitySoulAgent.class, 1, 0, 1, EnumCreatureType.AMBIENT, genericBiomes);
            EntityRegistry.addSpawn(EntityLottoman.class, 1, 0, 1, EnumCreatureType.AMBIENT, genericBiomes);
            EntityRegistry.addSpawn(EntityNightfly.class, 20, 1, 1, EnumCreatureType.MONSTER, genericBiomes);

            EntityRegistry.addSpawn(EntityHorndron.class, 5, 0, 1, EnumCreatureType.MONSTER, genericPlainsBiomes);
            EntityRegistry.addSpawn(EntityWarclops.class, 5, 0, 1, EnumCreatureType.MONSTER, genericPlainsBiomes);
            EntityRegistry.addSpawn(EntityAncientGolem.class, 5, 0, 1, EnumCreatureType.MONSTER, genericPlainsBiomes);

            EntityRegistry.addSpawn(EntitySpawnFluffer.class, 20, 0, 0, EnumCreatureType.AMBIENT, overworldBiomes);
        }

        EntityRegistry.addSpawn(EntityEmbrake.class, 20, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityWitheringLottoman.class, 1, 0, 1, EnumCreatureType.AMBIENT, Biomes.HELL);
        EntityRegistry.addSpawn(EntityFakePigman.class, 20, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityFlamewalker.class, 20, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityHellspot.class, 20, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityInfernal.class, 2, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityWitherWizard.class, 20, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntitySkeletalCowman.class, 20, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);
        EntityRegistry.addSpawn(EntityHellcat.class, 20, 0, 1, EnumCreatureType.MONSTER, Biomes.HELL);

        EntityRegistry.addSpawn(EntityBloomingPixon.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeMysterium);
        EntityRegistry.addSpawn(EntityRunationMaster.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeMysterium);
        EntityRegistry.addSpawn(EntityFungat.class, 5, 0, 1, EnumCreatureType.MONSTER, biomeMysterium);
        EntityRegistry.addSpawn(EntityFungback.class, 5, 0, 1, EnumCreatureType.MONSTER, biomeMysterium);
        EntityRegistry.addSpawn(EntityFungik.class, 5, 0, 1, EnumCreatureType.MONSTER, biomeMysterium);
        EntityRegistry.addSpawn(EntityFungung.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeMysterium);
        EntityRegistry.addSpawn(EntityEeo.class, 8, 0, 1, EnumCreatureType.MONSTER, biomeMysterium);

        EntityRegistry.addSpawn(EntityGlaringPixon.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeLelyetia);
        EntityRegistry.addSpawn(EntityLoggingMaster.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeLelyetia);
        EntityRegistry.addSpawn(EntityFlye.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLelyetia);
        EntityRegistry.addSpawn(EntityGrobbler.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeLelyetia);
        EntityRegistry.addSpawn(EntityLelyetianCaster.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLelyetia);
        EntityRegistry.addSpawn(EntityLelyetianWarrior.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLelyetia);
        EntityRegistry.addSpawn(EntityTracker.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLelyetia);
        EntityRegistry.addSpawn(EntityTrotter.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLelyetia);

        EntityRegistry.addSpawn(EntityGleamingPixon.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeRunandor);
        EntityRegistry.addSpawn(EntityAnimaMaster.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeRunandor);
        EntityRegistry.addSpawn(EntityAriel.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeRunandor);
        EntityRegistry.addSpawn(EntityBouncer.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeRunandor);
        EntityRegistry.addSpawn(EntityEyeCreature.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeRunandor);
        EntityRegistry.addSpawn(EntityPaladin.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeRunandor);
        EntityRegistry.addSpawn(EntityRunicorn.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeRunandor);
        EntityRegistry.addSpawn(EntityRunicornRider.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeRunandor);

        EntityRegistry.addSpawn(EntityGlowingPixon.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeLBorean);
        EntityRegistry.addSpawn(EntityHaulingMaster.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeLBorean);
        EntityRegistry.addSpawn(EntityAngler.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLBorean);
        EntityRegistry.addSpawn(EntityCoralon.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLBorean);
        EntityRegistry.addSpawn(EntityCoratee.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLBorean);
        EntityRegistry.addSpawn(EntityMuncher.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLBorean);
        EntityRegistry.addSpawn(EntityNeptuno.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeLBorean);
        EntityRegistry.addSpawn(EntitySeaViper.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLBorean);

        EntityRegistry.addSpawn(EntityRadiantPixon.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeDustopia);
        EntityRegistry.addSpawn(EntityBasilisk.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityDevourer.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityDuston.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityLostSoul.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityDusteiva.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityStalker.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityStalkerPrime.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);
        EntityRegistry.addSpawn(EntityLurker.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDustopia);

        EntityRegistry.addSpawn(EntityShiningPixon.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeAbyss);
        EntityRegistry.addSpawn(EntityButcheryMaster.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeAbyss);
        EntityRegistry.addSpawn(EntityApparition.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);
        EntityRegistry.addSpawn(EntityBloodsucker.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);
        EntityRegistry.addSpawn(EntityDistorter.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);
        EntityRegistry.addSpawn(EntityFiend.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);
        EntityRegistry.addSpawn(EntityOcculent.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);
        EntityRegistry.addSpawn(EntityWebReaper.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);
        EntityRegistry.addSpawn(EntitySlimer.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeAbyss);

        EntityRegistry.addSpawn(EntityCreationMaster.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeBarathos);
        EntityRegistry.addSpawn(EntityCryptid.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityArkback.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityEchodar.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityEilosapien.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityNospike.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityEmperorBeast.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityParasect.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityKeeler.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntitySquiggler.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityTharafly.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);
        EntityRegistry.addSpawn(EntityRamradon.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeBarathos);

        EntityRegistry.addSpawn(EntityAirhead.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCandyland);
        EntityRegistry.addSpawn(EntityCandyCorny.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCandyland);
        EntityRegistry.addSpawn(EntityCherryBarrager.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeCandyland);
        EntityRegistry.addSpawn(EntityLollypopper.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCandyland);
        EntityRegistry.addSpawn(EntityPeppermintSlug.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCandyland);
        EntityRegistry.addSpawn(EntitySpearmintSlug.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCandyland);
        EntityRegistry.addSpawn(EntityCherryBlaster.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCandyland);

        EntityRegistry.addSpawn(EntityConstructStrength.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);
        EntityRegistry.addSpawn(EntityConstructRange.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);
        EntityRegistry.addSpawn(EntityConstructTerror.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);
        EntityRegistry.addSpawn(EntityConstructSpeed.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);
        EntityRegistry.addSpawn(EntityConstructResistance.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);
        EntityRegistry.addSpawn(EntityConstructFlight.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);
        EntityRegistry.addSpawn(EntityConstructMind.class, 2, 0, 1, EnumCreatureType.MONSTER, biomeCrystevia);

        EntityRegistry.addSpawn(EntityExtractionMaster.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeDeeplands);
        EntityRegistry.addSpawn(EntityCaseConstruct.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);
        EntityRegistry.addSpawn(EntityDoubler.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);
        EntityRegistry.addSpawn(EntityDweller.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);
        EntityRegistry.addSpawn(EntityCaveCreep.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);
        EntityRegistry.addSpawn(EntityRockbiter.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);
        EntityRegistry.addSpawn(EntityRockCritter.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);
        EntityRegistry.addSpawn(EntityRockCrawler.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeDeeplands);

        EntityRegistry.addSpawn(EntityForagingMaster.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeIromine);
        EntityRegistry.addSpawn(EntityMechachron.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeIromine);
        EntityRegistry.addSpawn(EntityMechamaton.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeIromine);
        EntityRegistry.addSpawn(EntityPolytom.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeIromine);
        EntityRegistry.addSpawn(EntityQuickpocket.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeIromine);
        EntityRegistry.addSpawn(EntityVoltron.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeIromine);

        EntityRegistry.addSpawn(EntityAuguryMaster.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeHaven);
        EntityRegistry.addSpawn(EntityHalycon.class, 5, 0, 1, EnumCreatureType.AMBIENT, biomeHaven);
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

        EntityRegistry.addSpawn(EntityInfusionMaster.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeGreckon);
        EntityRegistry.addSpawn(EntityGrillface.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);
        EntityRegistry.addSpawn(EntityShifter.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);
        EntityRegistry.addSpawn(EntitySilencer.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);
        EntityRegistry.addSpawn(EntitySkullCreature.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);
        EntityRegistry.addSpawn(EntitySugarface.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);
        EntityRegistry.addSpawn(EntityValkyrie.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);
        EntityRegistry.addSpawn(EntityHunter.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGreckon);

        EntityRegistry.addSpawn(EntityInnervationMaster.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeCeleve);
        EntityRegistry.addSpawn(EntityBobo.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntityChocko.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntityJumbo.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntityKoko.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntityKranky.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntitySnappy.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntitySticky.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntityStitches.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);
        EntityRegistry.addSpawn(EntityTipsy.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeCeleve);

        EntityRegistry.addSpawn(EntityExplodot.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLunalus);
        EntityRegistry.addSpawn(EntityLunarcher.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLunalus);
        EntityRegistry.addSpawn(EntityVisular.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeLunalus);
        EntityRegistry.addSpawn(EntityVisulon.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeLunalus);

        EntityRegistry.addSpawn(EntityCreepCow.class, 5, 0, 1, EnumCreatureType.AMBIENT, biomeCreeponia);
        EntityRegistry.addSpawn(EntityBoneCreeper.class, 40, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityCaveCreepoid.class, 40, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityCreeperlock.class, 40, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityCreepird.class, 40, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityCreepuple.class, 40, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityKingCreeper.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityMagicalCreeper.class, 40, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);
        EntityRegistry.addSpawn(EntityWingedCreeper.class, 40, 0, 1, EnumCreatureType.MONSTER, biomeCreeponia);

        EntityRegistry.addSpawn(EntityAmbientPixon.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeHaven, biomeRunandor, biomeCandyland, biomeShyrelands);

        EntityRegistry.addSpawn(EntityDyrehorn.class, 20, 0, 1, EnumCreatureType.MONSTER, biomePrecasia);
        EntityRegistry.addSpawn(EntityGiantSlug.class, 20, 0, 1, EnumCreatureType.MONSTER, biomePrecasia);
        EntityRegistry.addSpawn(EntityMegatherium.class, 20, 0, 1, EnumCreatureType.MONSTER, biomePrecasia);
        EntityRegistry.addSpawn(EntitySabretooth.class, 20, 0, 1, EnumCreatureType.MONSTER, biomePrecasia);
        EntityRegistry.addSpawn(EntityTerradon.class, 1, 0, 1, EnumCreatureType.MONSTER, biomePrecasia);
        EntityRegistry.addSpawn(EntityTortione.class, 3, 0, 1, EnumCreatureType.MONSTER, biomePrecasia);

        EntityRegistry.addSpawn(EntityToxxulous.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);
        EntityRegistry.addSpawn(EntityGrocculate.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);
        EntityRegistry.addSpawn(EntityGadgetoid.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);
        EntityRegistry.addSpawn(EntityAlarmo.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);
        EntityRegistry.addSpawn(EntityFischer.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);
        EntityRegistry.addSpawn(EntityDestructor.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);
        EntityRegistry.addSpawn(EntityCentinel.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeVoxPonds);

        EntityRegistry.addSpawn(EntityArchvine.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);
        EntityRegistry.addSpawn(EntityBroccohead.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);
        EntityRegistry.addSpawn(EntityCarrotop.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);
        EntityRegistry.addSpawn(EntityFlowerface.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);
        EntityRegistry.addSpawn(EntitySquasher.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);
        EntityRegistry.addSpawn(EntitySunny.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);
        EntityRegistry.addSpawn(EntityCorny.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeGardencia);

        EntityRegistry.addSpawn(EntitySysker.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityShyreKnight.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityAxiolight.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityArcFlower.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityArcbeast.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityStimulo.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityStimulosus.class, 1, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntitySoulvyre.class, 5, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityOmnilight.class, 20, 0, 1, EnumCreatureType.MONSTER, biomeShyrelands);
        EntityRegistry.addSpawn(EntityExpeditionMaster.class, 1, 0, 1, EnumCreatureType.AMBIENT, biomeShyrelands);

        EntityRegistry.addSpawn(EntitySpawnFluffer.class, 1000, 0, 0, EnumCreatureType.AMBIENT, Biomes.HELL, biomeHaven, biomeRunandor, biomeCandyland, biomeShyrelands, biomeMysterium, biomeLelyetia, biomeLBorean, biomeDustopia, biomeAbyss, biomeCreeponia, biomeDeeplands);
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
}
