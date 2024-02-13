package net.tslat.aoa3.common.registration.entity.variant;

import com.google.common.base.Suppliers;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.entity.mob.precasia.VeloraptorEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Supplier;

public record VeloraptorVariant(String name, boolean isPriorityVariant, Optional<ResourceLocation> lootTable, VariantSpawnPredicate spawnPredicate) {
    public VeloraptorVariant(String name, VariantSpawnPredicate spawnPredicate) {
        this(name, false, Optional.empty(), spawnPredicate);
    }

    public static final DeferredHolder<VeloraptorVariant, VeloraptorVariant> GREEN = register("green", () -> new VeloraptorVariant("green", true, Optional.empty(), VariantSpawnPredicate.ALWAYS));
    public static final DeferredHolder<VeloraptorVariant, VeloraptorVariant> BROWN = register("brown", () -> new VeloraptorVariant("brown", VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 1f / AoARegistries.VELORAPTOR_VARIANTS.size())));

    public static void init() {}

    private static DeferredHolder<VeloraptorVariant, VeloraptorVariant> register(String id, Supplier<VeloraptorVariant> variant) {
        return AoARegistries.VELORAPTOR_VARIANTS.register(id, variant);
    }

    private static final Supplier<VeloraptorVariant[]> SORTED_VARIANTS = Suppliers.memoize(() -> AoARegistries.VELORAPTOR_VARIANTS.getAllRegisteredObjects().filter(variant -> variant != GREEN.get()).sorted(Comparator.comparing(VeloraptorVariant::isPriorityVariant).reversed()).toArray(VeloraptorVariant[]::new));

    public static VeloraptorVariant getVariantForSpawn(ServerLevel level, DifficultyInstance difficulty, MobSpawnType spawnReason, VeloraptorEntity veloraptor, Supplier<Holder<Biome>> biome, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        VeloraptorVariant variant = VeloraptorVariant.GREEN.get();

        for (VeloraptorVariant testVariant : SORTED_VARIANTS.get()) {
            if (testVariant.spawnPredicate().canSpawnVariant(level, difficulty, spawnReason, veloraptor, biome, spawnData, dataTag)) {
                variant = testVariant;

                break;
            }
        }

        return variant;
    }
}
