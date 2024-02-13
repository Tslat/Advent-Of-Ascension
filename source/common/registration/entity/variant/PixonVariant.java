package net.tslat.aoa3.common.registration.entity.variant;

import com.google.common.base.Suppliers;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.entity.misc.PixonEntity;

import java.util.Comparator;
import java.util.function.Supplier;

public record PixonVariant(String name, boolean isPriorityVariant, int primaryColour, int secondaryColour, ResourceLocation lootTable, VariantSpawnPredicate spawnPredicate) {
    public PixonVariant(String name, int primaryColour, int secondaryColour, ResourceLocation lootTable, VariantSpawnPredicate spawnPredicate) {
        this(name, false, primaryColour, secondaryColour, lootTable, spawnPredicate);
    }

    public static final DeferredHolder<PixonVariant, PixonVariant> AMBIENT = register("ambient", () -> new PixonVariant("ambient", 0x5A5A5A, 0x313131, AdventOfAscension.id("entities/ambient_pixon"), VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 0.41)));
    public static final DeferredHolder<PixonVariant, PixonVariant> GLISTENING = register("glistening", () -> new PixonVariant("glistening", 0x06D02B, 0x007506, AdventOfAscension.id("entities/glistening_pixon"), VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 0.41)));
    public static final DeferredHolder<PixonVariant, PixonVariant> BLOOMING = register("blooming", () -> new PixonVariant("blooming", 0x7800DF, 0x21003E, AdventOfAscension.id("entities/blooming_pixon"), VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 0.41)));
    public static final DeferredHolder<PixonVariant, PixonVariant> SHINING = register("shining", () -> new PixonVariant("shining", 0xD51073, 0x3A0E29, AdventOfAscension.id("entities/shining_pixon"), VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 0.41)));
    public static final DeferredHolder<PixonVariant, PixonVariant> GLEAMING = register("gleaming", () -> new PixonVariant("gleaming", 0x00C7D6, 0x006F78, AdventOfAscension.id("entities/gleaming_pixon"), VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 0.41)));
    public static final DeferredHolder<PixonVariant, PixonVariant> GLOWING = register("glowing", () -> new PixonVariant("glowing", 0xB26D22, 0x623C13, AdventOfAscension.id("entities/glowing_pixon"), VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 0.41)));
    public static final DeferredHolder<PixonVariant, PixonVariant> GLARING = register("glaring", () -> new PixonVariant("glaring", 0xD03206, 0x2D0400, AdventOfAscension.id("entities/glaring_pixon"), VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 0.41)));
    public static final DeferredHolder<PixonVariant, PixonVariant> RADIANT = register("radiant", () -> new PixonVariant("radiant", 0xCBCBCB, 0x727272, AdventOfAscension.id("entities/radiant_pixon"), VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 0.41)));

    public static void init() {}

    private static DeferredHolder<PixonVariant, PixonVariant> register(String id, Supplier<PixonVariant> variant) {
        return AoARegistries.PIXON_VARIANTS.register(id, variant);
    }

    private static final Supplier<PixonVariant[]> SORTED_VARIANTS = Suppliers.memoize(() -> AoARegistries.PIXON_VARIANTS.getAllRegisteredObjects().sorted(Comparator.comparing(PixonVariant::isPriorityVariant).reversed()).toArray(PixonVariant[]::new));

    public static PixonVariant getVariantForSpawn(ServerLevel level, DifficultyInstance difficulty, PixonEntity pixon, Supplier<Holder<Biome>> biome) {
        PixonVariant variant = PixonVariant.AMBIENT.get();

        for (PixonVariant testVariant : SORTED_VARIANTS.get()) {
            if (testVariant.spawnPredicate().canSpawnVariant(level, difficulty, MobSpawnType.NATURAL, pixon, biome, null, null)) {
                variant = testVariant;

                break;
            }
        }

        return variant;
    }

    // Gray - 0x5A5A5A - 0x313131 - AMBIENT
    // Green - 0x06D02B - 0x007506 - GLISTENING
    // Purple - 0x7800DF - 0x21003E - BLOOMING
    // Pink - 0xD51073 - 0x3A0E29 - SHINING
    // Blue - 0x00C7D6 - 0x006F78 - GLEAMING
    // Orange - 0xB26D22 - 0x623C13 - GLOWING
    // Red - 0xD03206 - 0x2D0400 - GLARING
    // White - 0xCBCBCB - 0x727272 - RADIANT
}
