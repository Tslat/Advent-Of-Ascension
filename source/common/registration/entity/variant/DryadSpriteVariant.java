package net.tslat.aoa3.common.registration.entity.variant;

import com.google.common.base.Suppliers;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.entity.npc.ambient.DryadSpriteEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.function.Supplier;

public record DryadSpriteVariant(String name, boolean isPriorityVariant, Ingredient offering, VariantSpawnPredicate spawnPredicate) {
    public DryadSpriteVariant(String name, Ingredient offering, VariantSpawnPredicate spawnPredicate) {
        this(name, false, offering, spawnPredicate);
    }

    public boolean isCorrectOffering(ItemStack stack) {
        return this.offering.test(stack);
    }

    public static final DeferredHolder<DryadSpriteVariant, DryadSpriteVariant> WOOD = register("wood", () -> new DryadSpriteVariant("wood", true, Ingredient.of(Items.WOODEN_HOE), VariantSpawnPredicate.ALWAYS));
    public static final DeferredHolder<DryadSpriteVariant, DryadSpriteVariant> STONE = register("stone", () -> new DryadSpriteVariant("stone", true, Ingredient.of(Items.STONE_HOE), VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 1f / AoARegistries.DRYAD_SPRITE_VARIANTS.size())));
    public static final DeferredHolder<DryadSpriteVariant, DryadSpriteVariant> IRON = register("iron", () -> new DryadSpriteVariant("iron", true, Ingredient.of(Items.IRON_HOE), VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 1f / AoARegistries.DRYAD_SPRITE_VARIANTS.size())));
    public static final DeferredHolder<DryadSpriteVariant, DryadSpriteVariant> GOLD = register("gold", () -> new DryadSpriteVariant("gold", true, Ingredient.of(Items.GOLDEN_HOE), VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 1f / AoARegistries.DRYAD_SPRITE_VARIANTS.size())));
    public static final DeferredHolder<DryadSpriteVariant, DryadSpriteVariant> DIAMOND = register("diamond", () -> new DryadSpriteVariant("diamond", true, Ingredient.of(Items.DIAMOND_HOE), VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 1f / AoARegistries.DRYAD_SPRITE_VARIANTS.size())));
    public static final DeferredHolder<DryadSpriteVariant, DryadSpriteVariant> NETHERITE = register("netherite", () -> new DryadSpriteVariant("netherite", true, Ingredient.of(Items.NETHERITE_HOE), VariantSpawnPredicate.randomChance(random -> random.nextFloat() < 1f / AoARegistries.DRYAD_SPRITE_VARIANTS.size())));

    public static void init() {}

    private static DeferredHolder<DryadSpriteVariant, DryadSpriteVariant> register(String id, Supplier<DryadSpriteVariant> variant) {
        return AoARegistries.DRYAD_SPRITE_VARIANTS.register(id, variant);
    }

    private static final Supplier<DryadSpriteVariant[]> SORTED_VARIANTS = Suppliers.memoize(() -> AoARegistries.DRYAD_SPRITE_VARIANTS.getAllRegisteredObjects().filter(variant -> variant != WOOD.get()).sorted(Comparator.comparing(DryadSpriteVariant::isPriorityVariant).reversed()).toArray(DryadSpriteVariant[]::new));

    public static DryadSpriteVariant getVariantForSpawn(ServerLevel level, DifficultyInstance difficulty, MobSpawnType spawnReason, DryadSpriteEntity dryadSprite, Supplier<Holder<Biome>> biome, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        DryadSpriteVariant variant = DryadSpriteVariant.WOOD.get();

        for (DryadSpriteVariant testVariant : SORTED_VARIANTS.get()) {
            if (testVariant.spawnPredicate().canSpawnVariant(level, difficulty, spawnReason, dryadSprite, biome, spawnData, dataTag)) {
                variant = testVariant;

                break;
            }
        }

        return variant;
    }
}
