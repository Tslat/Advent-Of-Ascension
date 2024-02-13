package net.tslat.aoa3.common.registration.entity.variant;

import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
public interface VariantSpawnPredicate {
    VariantSpawnPredicate ALWAYS = (level, difficulty, spawnReason, mob, biome, spawnData, dataTag) -> true;
    VariantSpawnPredicate NEVER = (level, difficulty, spawnReason, mob, biome, spawnData, dataTag) -> false;

    boolean canSpawnVariant(ServerLevel level, DifficultyInstance difficulty, MobSpawnType spawnReason, Entity entity, Supplier<Holder<Biome>> biome, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag);

    static VariantSpawnPredicate checkingBiome(Predicate<Holder<Biome>> predicate) {
        return (level, difficulty, spawnReason, mob, biome, spawnData, dataTag) -> predicate.test(biome.get());
    }

    static VariantSpawnPredicate randomChance(Function<RandomSource, Boolean> predicate) {
        return (level, difficulty, spawnReason, mob, biome, spawnData, dataTag) -> predicate.apply(level.random);
    }

    static VariantSpawnPredicate checkBasic(BiPredicate<ServerLevel, Entity> predicate) {
        return (level, difficulty, spawnReason, mob, biome, spawnData, dataTag) -> predicate.test(level, mob);
    }
}
