package net.tslat.aoa3.common.registration.entity.variant;

import com.google.common.base.Suppliers;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.entity.mob.overworld.ChargerEntity;
import net.tslat.aoa3.util.HolidayUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Supplier;

public record ChargerVariant(String name, boolean isPriorityVariant, Optional<ResourceLocation> lootTable, VariantSpawnPredicate spawnPredicate) {
    public ChargerVariant(String name, VariantSpawnPredicate spawnPredicate) {
        this(name, false, Optional.empty(), spawnPredicate);
    }

    public static final DeferredHolder<ChargerVariant, ChargerVariant> PLAINS = register("plains", () -> new ChargerVariant("plains", true, Optional.empty(), VariantSpawnPredicate.ALWAYS));
    public static final DeferredHolder<ChargerVariant, ChargerVariant> RAVEN = register("raven", () -> new ChargerVariant("raven", true, Optional.empty(), VariantSpawnPredicate.randomChance(random -> HolidayUtil.isHalloween() && random.nextInt(3) == 0)));
    public static final DeferredHolder<ChargerVariant, ChargerVariant> ZOMBIE = register("zombie", () -> new ChargerVariant("zombie", true, Optional.empty(), VariantSpawnPredicate.randomChance(random -> HolidayUtil.isHalloween() && random.nextInt(3) == 1)));
    public static final DeferredHolder<ChargerVariant, ChargerVariant> SKELETON = register("skeleton", () -> new ChargerVariant("skeleton", true, Optional.empty(), VariantSpawnPredicate.randomChance(random -> HolidayUtil.isHalloween() && random.nextInt(3) == 2)));
    public static final DeferredHolder<ChargerVariant, ChargerVariant> VOID = register("void", () -> new ChargerVariant("void", true, Optional.of(AdventOfAscension.id("entities/void_charger")), VariantSpawnPredicate.checkBasic((level, mob) -> level.getRawBrightness(mob.blockPosition(), 0) == 0)));
    public static final DeferredHolder<ChargerVariant, ChargerVariant> DESERT = register("desert", () -> new ChargerVariant("desert", VariantSpawnPredicate.checkingBiome(biome -> biome.is(Tags.Biomes.IS_HOT) && biome.is(Tags.Biomes.IS_SANDY))));
    public static final DeferredHolder<ChargerVariant, ChargerVariant> HILL = register("hill", () -> new ChargerVariant("hill", VariantSpawnPredicate.checkingBiome(biome -> biome.is(Tags.Biomes.IS_MOUNTAIN) && !biome.is(Tags.Biomes.IS_SNOWY))));
    public static final DeferredHolder<ChargerVariant, ChargerVariant> SEA = register("sea", () -> new ChargerVariant("sea", VariantSpawnPredicate.checkingBiome(biome -> (biome.is(BiomeTags.IS_OCEAN) || biome.is(BiomeTags.IS_RIVER) || biome.is(BiomeTags.IS_BEACH)) && !biome.is(Tags.Biomes.IS_SNOWY))));
    public static final DeferredHolder<ChargerVariant, ChargerVariant> SNOW = register("snow", () -> new ChargerVariant("snow", VariantSpawnPredicate.checkingBiome(biome -> biome.is(Tags.Biomes.IS_SNOWY))));
    public static final DeferredHolder<ChargerVariant, ChargerVariant> SWAMP = register("swamp", () -> new ChargerVariant("swamp", VariantSpawnPredicate.checkingBiome(biome -> biome.is(Tags.Biomes.IS_SWAMP) && !biome.is(Tags.Biomes.IS_SNOWY))));

    public static void init() {}

    private static DeferredHolder<ChargerVariant, ChargerVariant> register(String id, Supplier<ChargerVariant> chargerType) {
        return AoARegistries.CHARGER_VARIANTS.register(id, chargerType);
    }

    private static final Supplier<ChargerVariant[]> SORTED_VARIANTS = Suppliers.memoize(() -> AoARegistries.CHARGER_VARIANTS.getAllRegisteredObjects().filter(variant -> variant != PLAINS.get()).sorted(Comparator.comparing(ChargerVariant::isPriorityVariant).reversed()).toArray(ChargerVariant[]::new));

    public static ChargerVariant getVariantForSpawn(ServerLevel level, DifficultyInstance difficulty, MobSpawnType spawnReason, ChargerEntity charger, Supplier<Holder<Biome>> biome, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        ChargerVariant variant = ChargerVariant.PLAINS.get();

        for (ChargerVariant testVariant : SORTED_VARIANTS.get()) {
            if (testVariant.spawnPredicate().canSpawnVariant(level, difficulty, spawnReason, charger, biome, spawnData, dataTag)) {
                variant = testVariant;

                break;
            }
        }

        return variant;
    }
}
