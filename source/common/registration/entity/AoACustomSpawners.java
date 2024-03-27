package net.tslat.aoa3.common.registration.entity;

import com.mojang.serialization.Codec;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.spawner.AoACustomSpawner;
import net.tslat.aoa3.content.world.spawner.PixonSpawner;
import net.tslat.aoa3.content.world.spawner.RoamingTraderSpawner;

public final class AoACustomSpawners {
    public static final Codec<AoACustomSpawner> CODEC = ExtraCodecs.lazyInitializedCodec(() -> AoARegistries.CUSTOM_SPAWNER_TYPES.registry().get().byNameCodec().dispatch("spawner", AoACustomSpawner::getType, AoACustomSpawner.Type::codec));

    public static void init() {}

    public static final DeferredHolder<AoACustomSpawner.Type, AoACustomSpawner.Type> ROAMING_TRADER = register("roaming_trader", RoamingTraderSpawner.CODEC);
    public static final DeferredHolder<AoACustomSpawner.Type, AoACustomSpawner.Type> PIXONS = register("pixons", PixonSpawner.CODEC);

    private static DeferredHolder<AoACustomSpawner.Type, AoACustomSpawner.Type> register(String id, Codec<? extends AoACustomSpawner> codec) {
        return AoARegistries.CUSTOM_SPAWNER_TYPES.register(id, () -> new AoACustomSpawner.Type(codec));
    }
}
