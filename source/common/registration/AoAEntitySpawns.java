package net.tslat.aoa3.common.registration;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.base.AbstractLavaFishEntity;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;

import static net.minecraft.world.entity.SpawnPlacements.Type.*;
import static net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES;

public final class AoAEntitySpawns {
    public static void registerEntitySpawns() {
        Logging.logStatusMessage("Setting entity spawn placements");

        setOverworldSpawnPlacements();
    }

    public static void setOverworldSpawnPlacements() {
        placeAsMonster(AoAMobs.ICE_GIANT.get());
        placeAsMonster(AoAMobs.LEAFY_GIANT.get());
        placeAsMonster(AoAMobs.STONE_GIANT.get());

        setSpawnPlacement(AoAAnimals.SHINY_SQUID.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, GlowSquid::checkGlowSquideSpawnRules);

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
    }
    private static void placeAsNpc(EntityType<? extends Mob> entityType) {
        setSpawnPlacement(entityType, ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(false));
    }

    private static void placeAsMonster(EntityType<? extends Mob> entityType) {
        setSpawnPlacement(entityType, ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.MONSTER);
    }

    private static void placeAsWaterMonster(EntityType<? extends Mob> entityType) {
        setSpawnPlacement(entityType, IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.WATER_MONSTER);
    }

    private static void placeAsFish(EntityType<? extends Mob> entityType) {
        setSpawnPlacement(entityType, IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
    }

    private static <T extends Mob> void setSpawnPlacement(EntityType<T> entityType, SpawnPlacements.Type placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<? extends Mob> spawnPredicate) {
        try {
            if (SpawnPlacements.getPlacementType(entityType) == NO_RESTRICTIONS)
                SpawnPlacements.register(entityType, placementType, heightmap, (SpawnPlacements.SpawnPredicate<T>)spawnPredicate);
        }
        catch (IllegalStateException ex) {
            Logging.logMessage(Level.WARN, "Caught duplicate spawn placement registration from: " + ForgeRegistries.ENTITIES.getKey(entityType).toString());
        }
    }

    private static final class SpawnPredicates {
        private static final SpawnPlacements.SpawnPredicate<Mob> npcPredicate(boolean spawnsInDarkness) {
            return (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> {
                if (!Mob.checkMobSpawnRules(type, world, reason, pos, rand))
                    return false;

                return spawnsInDarkness || WorldUtil.getLightLevel(world, pos, false, false) >= 8;
            };
        };

        private static final SpawnPlacements.SpawnPredicate<Mob> MONSTER = (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> {
            if (world.getDifficulty() == Difficulty.PEACEFUL)
                return false;

            boolean isVanillaWorld = WorldUtil.isWorld(world, net.minecraft.world.level.Level.OVERWORLD, net.minecraft.world.level.Level.NETHER);

            if (EntityUtil.isNaturalSpawnReason(reason)) {
                if (AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && !world.dimensionType().hasFixedTime() && !world.getLevel().isDay() && isVanillaWorld)
                    return false;

                if (AoAEntityData.SpawnConditions.SPAWN_HEIGHTS.containsKey(type) && pos.getY() > AoAEntityData.SpawnConditions.SPAWN_HEIGHTS.get(type))
                    return false;

                if (!ForgeRegistries.BIOMES.getKey(world.getBiome(pos).value()).getNamespace().equals(AdventOfAscension.MOD_ID) && !isVanillaWorld)
                    return false;
            }

            if (!Mob.checkMobSpawnRules(type, world, reason, pos, rand))
                return false;

            if (!AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && isVanillaWorld)
                return Monster.isDarkEnoughToSpawn(world, pos, rand);

            return WorldUtil.getLightLevel(world, pos, true, false) <= RandomUtil.randomNumberUpTo(8);
        };

        private static final SpawnPlacements.SpawnPredicate<Mob> WATER_MONSTER = (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> {
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

                if (!ForgeRegistries.BIOMES.getKey(world.getBiome(pos).value()).getNamespace().equals(AdventOfAscension.MOD_ID) && !isVanillaWorld)
                    return false;
            }

            if (!world.getBlockState(pos).getFluidState().getType().is(FluidTags.WATER))
                return false;

            if (!AoAEntityData.SpawnConditions.DAYLIGHT_MOBS.contains(type) && isVanillaWorld)
                return Monster.isDarkEnoughToSpawn(world, pos, rand);

            return WorldUtil.getLightLevel(world, pos, true, false) <= RandomUtil.randomNumberUpTo(8);
        };

        private static <T extends Mob> SpawnPlacements.SpawnPredicate<T> animalPredicate(@Nullable TagKey<Block> blockTag, boolean spawnsInDarkness) {
            return (EntityType<T> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> {
                if (blockTag != null && !world.getBlockState(pos.below()).is(blockTag))
                    return false;

                return spawnsInDarkness || world.getRawBrightness(pos, 0) >= 8;
            };
        }

        private static final SpawnPlacements.SpawnPredicate<Mob> FISH = (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER);
    }
}
