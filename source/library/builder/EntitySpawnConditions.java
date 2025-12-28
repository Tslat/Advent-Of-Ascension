package net.tslat.aoa3.library.builder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings({"unchecked", "NullableProblems"})
public final class EntitySpawnConditions<T extends Entity> implements SpawnPlacements.SpawnPredicate<T> {
    private SpawnPlacements.SpawnPredicate<T>[] bakedPredicate = null;
    private List<SpawnPlacements.SpawnPredicate<T>> predicates = new ObjectArrayList<>();

    EntitySpawnConditions() {}

    public static <T extends Entity> EntitySpawnConditions<T> create(EntityType<T> entityType) {
        return new EntitySpawnConditions<>();
    }

    public static <T extends Mob> EntitySpawnConditions<T> createMonster(EntityType<T> entityType) {
        return create(entityType).noPeacefulSpawn().defaultMonsterLightLevels().ifValidSpawnBlock();
    }

    public static <T extends Mob> EntitySpawnConditions<T> createDayNightMonster(EntityType<T> entityType) {
        return create(entityType).noPeacefulSpawn().defaultMonsterBlockLightLevels().ifValidSpawnBlock();
    }

    public static <T extends Mob> EntitySpawnConditions<T> createDayMonster(EntityType<T> entityType) {
        return create(entityType).noPeacefulSpawn().onlyDuringDay().defaultMonsterBlockLightLevels().ifValidSpawnBlock().difficultyBasedSpawnChance(0.12f);
    }

    public static <T extends Entity> EntitySpawnConditions<T> createAnimal(EntityType<T> entityType) {
        return create(entityType).animalSpawnRules();
    }

    @Override
    public boolean test(EntityType<T> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource rand) {
        if (this.bakedPredicate == null) {
            this.bakedPredicate = this.predicates.toArray(new SpawnPlacements.SpawnPredicate[0]);
            this.predicates = null;
        }

        for (SpawnPlacements.SpawnPredicate<T> predicate : this.bakedPredicate) {
            if (!predicate.test(entityType, level, spawnType, pos, rand))
                return false;
        }

        return true;
    }

    public EntitySpawnConditions<T> and(SpawnPlacements.SpawnPredicate<T> predicate) {
        this.predicates.add(predicate);

        return this;
    }

    public EntitySpawnConditions<T> animalSpawnRules() {
        return and((entityType, level, spawnType, pos, rand) ->
                           level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && (MobSpawnType.ignoresLightRequirements(spawnType) || level.getRawBrightness(pos, 0) > 8));
    }

    public EntitySpawnConditions<T> noPeacefulSpawn() {
        return and((entityType, level, spawnType, pos, rand) ->
                           level.getDifficulty() != Difficulty.PEACEFUL);
    }

    public EntitySpawnConditions<T> ifValidSpawnBlock() {
        return and((entityType, level, spawnType, pos, rand) ->
                Mob.checkMobSpawnRules((EntityType<? extends Mob>)entityType, level, spawnType, pos, rand));
    }

    public EntitySpawnConditions<T> noLowerThanY(@Nullable ResourceKey<Level> inLevel, int minY) {
        return and((entityType, level, spawnType, pos, rand) ->
                           !isNaturalSpawn(spawnType) || levelDependentCondition(inLevel, level, pos.getY() >= minY));
    }

    public EntitySpawnConditions<T> noHigherThanY(@Nullable ResourceKey<Level> inLevel, int maxY) {
        return and((entityType, level, spawnType, pos, rand) ->
                           !isNaturalSpawn(spawnType) || levelDependentCondition(inLevel, level, pos.getY() <= maxY));
    }

    public EntitySpawnConditions<T> betweenYLevels(@Nullable ResourceKey<Level> inLevel, int minY, int maxY) {
        return and((entityType, level, spawnType, pos, rand) ->
                           !isNaturalSpawn(spawnType) || levelDependentCondition(inLevel, level, pos.getY() >= minY && pos.getY() <= maxY));
    }

    public EntitySpawnConditions<T> spawnChance(float chance) {
        return and((entityType, level, spawnType, pos, rand) ->
                           !isNaturalSpawn(spawnType) || rand.nextFloat() < chance);
    }

    public EntitySpawnConditions<T> difficultyBasedSpawnChance(float chance) {
        return and((entityType, level, spawnType, pos, rand) ->
                           !isNaturalSpawn(spawnType) || rand.nextFloat() < chance * level.getCurrentDifficultyAt(pos).getEffectiveDifficulty());
    }

    public EntitySpawnConditions<T> noSpawnOn(TagKey<Block> blockTag) {
        return and((entityType, level, spawnType, pos, rand) ->
                           !level.getBlockState(pos.below()).is(blockTag));
    }

    public EntitySpawnConditions<T> noSpawnOn(Block block) {
        return and((entityType, level, spawnType, pos, rand) ->
                           !level.getBlockState(pos.below()).is(block));
    }

    public EntitySpawnConditions<T> onlySpawnOn(TagKey<Block> blockTag) {
        return and((entityType, level, spawnType, pos, rand) ->
                           level.getBlockState(pos.below()).is(blockTag));
    }

    public EntitySpawnConditions<T> onlySpawnOn(Block block) {
        return and((entityType, level, spawnType, pos, rand) ->
                           level.getBlockState(pos.below()).is(block));
    }

    public EntitySpawnConditions<T> onlySpawnIn(TagKey<Block> blockTag) {
        return and((entityType, level, spawnType, pos, rand) ->
                           level.getBlockState(pos).is(blockTag));
    }

    public EntitySpawnConditions<T> onlySpawnIn(Block block) {
        return and((entityType, level, spawnType, pos, rand) ->
                           level.getBlockState(pos).is(block));
    }

    public EntitySpawnConditions<T> onlySpawnUnder(TagKey<Block> blockTag) {
        return and((entityType, level, spawnType, pos, rand) ->
                           level.getBlockState(pos.above()).is(blockTag));
    }

    public EntitySpawnConditions<T> onlySpawnUnder(Block block) {
        return and((entityType, level, spawnType, pos, rand) ->
                           level.getBlockState(pos.above()).is(block));
    }

    public EntitySpawnConditions<T> minLightLevel(int lightLevel) {
        return and((entityType, level, spawnType, pos, rand) ->
                           WorldUtil.getLightLevel(level, pos, false, false) >= lightLevel);
    }

    public EntitySpawnConditions<T> maxLightLevel(int lightLevel) {
        return and((entityType, level, spawnType, pos, rand) ->
                           WorldUtil.getLightLevel(level, pos, false, false) <= lightLevel);
    }

    public EntitySpawnConditions<T> defaultMonsterLightLevels() {
        return and((entityType, level, spawnType, pos, rand) ->
                           Monster.isDarkEnoughToSpawn(level, pos, rand));
    }

    public EntitySpawnConditions<T> defaultMonsterBlockLightLevels() {
        return and((entityType, level, spawnType, pos, rand) ->
                           WorldUtil.getLightLevel(level, pos, true, false) <= level.dimensionType().monsterSpawnBlockLightLimit());
    }

    public EntitySpawnConditions<T> onlyDuringDay() {
        return and((entityType, level, spawnType, pos, rand) -> {
            if (!isNaturalSpawn(spawnType))
                return true;

            final float dayFrac = level.getTimeOfDay(1);

            return dayFrac < 0.25f || dayFrac > 0.75f;
        });
    }

    public EntitySpawnConditions<T> notNearEntities(Class<? extends Entity> entityClass, double radius) {
        return notNearEntities(entityClass, radius, radius, radius);
    }

    public EntitySpawnConditions<T> notNearEntities(Class<? extends Entity> entityClass, double xRadius, double yRadius, double zRadius) {
        return and((entityType, level, spawnType, pos, rand) -> {
            if (!isNaturalSpawn(spawnType))
                return true;

            return EntityRetrievalUtil.streamEntities(level.getLevel(), Vec3.atCenterOf(pos), xRadius, yRadius, zRadius, entityClass).findAny().isEmpty();
        });
    }

    public static boolean isNaturalSpawn(MobSpawnType mobSpawnType) {
        return switch (mobSpawnType) {
            case NATURAL, CHUNK_GENERATION, REINFORCEMENT, STRUCTURE, PATROL -> true;
            default -> false;
        };
    }

    public static boolean levelDependentCondition(@Nullable ResourceKey<Level> level, ServerLevelAccessor levelAccessor, boolean condition) {
        if (level != null && !level.equals(levelAccessor.getLevel().dimension()))
            return true;

        return condition;
    }
}