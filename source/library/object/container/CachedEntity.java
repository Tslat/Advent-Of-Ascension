package net.tslat.aoa3.library.object.container;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.Logging;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record CachedEntity<T extends Entity>(@Nullable EntityType<T> entityType, CompoundTag nbt, Optional<Component> name) {
    public static final Codec<CachedEntity<?>> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity_type").forGetter(CachedEntity::entityType),
            CompoundTag.CODEC.fieldOf("data").forGetter(CachedEntity::nbt),
            ComponentSerialization.CODEC.optionalFieldOf("name").forGetter(CachedEntity::name)
    ).apply(builder, CachedEntity::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, CachedEntity<?>> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.registry(Registries.ENTITY_TYPE), CachedEntity::entityType,
            ByteBufCodecs.COMPOUND_TAG, CachedEntity::nbt,
            ComponentSerialization.OPTIONAL_STREAM_CODEC, CachedEntity::name,
            CachedEntity::new);
    public static final CachedEntity<?> EMPTY = new CachedEntity<Entity>(null, null, Optional.empty());

    public boolean isEmpty() {
        return this.entityType == null;
    }

    @Nullable
    public T createEntity(Level level) {
        return (T)EntityType.create(this.nbt, level).orElse(null);
    }

    public static <T extends Entity> CachedEntity<T> store(T entity) {
        CompoundTag nbt = new CompoundTag();

        entity.save(nbt);

        return new CachedEntity<>((EntityType<T>)entity.getType(), nbt, Optional.of(entity.getName()));
    }

    public Component getName(@Nullable HolderLookup.Provider registryAccess) {
        return name()
                .orElseGet(() -> {
                    if (registryAccess != null && this.nbt.contains("CustomName", Tag.TAG_STRING)) {
                        String customName = this.nbt.getString("CustomName");

                        try {
                            return Component.Serializer.fromJson(customName, registryAccess);
                        }
                        catch (Exception exception) {
                            Logging.error("Failed to parse entity custom name " + customName, exception);
                        }
                    }

                    return this.entityType.getDescription();
                });
    }
}
