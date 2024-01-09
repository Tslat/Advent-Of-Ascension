package net.tslat.aoa3.common.registration.loot;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.loottable.function.EnchantSpecific;
import net.tslat.aoa3.content.loottable.function.GrantSkillXp;

public final class AoALootFunctions {
    public static void init() {}

    public static final DeferredHolder<LootItemFunctionType, LootItemFunctionType> ENCHANT_SPECIFIC = register("enchant_specific", EnchantSpecific.CODEC);
    public static final DeferredHolder<LootItemFunctionType, LootItemFunctionType> GRANT_SKILL_XP = register("grant_skill_xp", GrantSkillXp.CODEC);

    private static DeferredHolder<LootItemFunctionType, LootItemFunctionType> register(String id, Codec<? extends LootItemFunction> codec) {
        return AoARegistries.LOOT_FUNCTIONS.register(id, () -> new LootItemFunctionType(codec));
    }
}
