package net.tslat.aoa3.content.item.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record ShotgunStats(int pellets, float pelletSpread, float knockbackModifier) {
    public static final Codec<ShotgunStats> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.INT.fieldOf("pellets").forGetter(ShotgunStats::pellets),
            Codec.FLOAT.optionalFieldOf("pellet_spread", 0.3f).forGetter(ShotgunStats::pelletSpread),
            Codec.FLOAT.fieldOf("knockback_modifier").forGetter(ShotgunStats::knockbackModifier)
    ).apply(builder, ShotgunStats::new));
    public static final StreamCodec<FriendlyByteBuf, ShotgunStats> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, ShotgunStats::pellets,
            ByteBufCodecs.FLOAT, ShotgunStats::pelletSpread,
            ByteBufCodecs.FLOAT, ShotgunStats::knockbackModifier,
            ShotgunStats::new);
}
