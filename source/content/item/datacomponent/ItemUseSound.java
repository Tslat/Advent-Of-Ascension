package net.tslat.aoa3.content.item.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvent;

import java.util.Optional;

public record ItemUseSound(Optional<Holder<SoundEvent>> sound, float pitch) {
    public static final Codec<ItemUseSound> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            BuiltInRegistries.SOUND_EVENT.holderByNameCodec().optionalFieldOf("sound").forGetter(ItemUseSound::sound),
            Codec.FLOAT.optionalFieldOf("pitch", 1f).forGetter(ItemUseSound::pitch)
    ).apply(builder, ItemUseSound::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemUseSound> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.holderRegistry(Registries.SOUND_EVENT).apply(ByteBufCodecs::optional), ItemUseSound::sound,
            ByteBufCodecs.FLOAT, ItemUseSound::pitch,
            ItemUseSound::new);

    public static ItemUseSound of(Holder<SoundEvent> sound, float pitch) {
        return new ItemUseSound(Optional.of(sound), pitch);
    }

    public static ItemUseSound of(SoundEvent sound, float pitch) {
        return of(Holder.direct(sound), pitch);
    }

    public static ItemUseSound of(Holder<SoundEvent> sound) {
        return of(sound, 1);
    }

    public static ItemUseSound of(SoundEvent sound) {
        return of(sound, 1);
    }

    public static ItemUseSound none() {
        return new ItemUseSound(Optional.empty(), 1f);
    }
}
