package net.tslat.aoa3.common.registration;

import com.mojang.serialization.Codec;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.player.ServerPlayerDataManager;

import java.util.UUID;
import java.util.function.Supplier;

public final class AoADataAttachments {
    public static void init() {}

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<ServerPlayerDataManager>> ADVENT_PLAYER = register("advent_player", () -> AttachmentType.serializable(pl -> new ServerPlayerDataManager((ServerPlayer)pl)).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Float>> MELEE_SWING_STRENGTH = register("melee_swing_strength", () -> AttachmentType.builder(() -> 1f).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Float>> DAMAGE_SCALING = register("damage_scaling", () -> AttachmentType.builder(() -> 0f).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<UUID>> LAST_TARGET = register("last_target", () -> AttachmentType.builder(() -> (UUID)null).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Float>> CHARGE = register("charge", () -> AttachmentType.builder(() -> 0f).serialize(Codec.FLOAT).build());
    /*public static final DeferredHolder<AttachmentType<?>, AttachmentType<ServerPlayerDataManager>> ADVENT_PLAYER = register("advent_player", () -> AttachmentType.serializable(entity -> {
        if (entity instanceof ServerPlayer pl)
            return new ServerPlayerDataManager(pl);

        throw new IllegalArgumentException("Attempting to retrieve ADVENT_PLAYER data for non-server player!");
    }).build());*/

    private static <T> DeferredHolder<AttachmentType<?>, AttachmentType<T>> register(String id, Supplier<AttachmentType<T>> attachment) {
        return AoARegistries.DATA_ATTACHMENTS.register(id, attachment);
    }
}
