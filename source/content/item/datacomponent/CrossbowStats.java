package net.tslat.aoa3.content.item.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record CrossbowStats(float damage, float chargeSpeedModifier) {
    public static final Codec<CrossbowStats> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.FLOAT.fieldOf("damage").forGetter(CrossbowStats::damage),
            Codec.FLOAT.fieldOf("charge_speed_modifier").forGetter(CrossbowStats::chargeSpeedModifier)
    ).apply(builder, CrossbowStats::new));
    public static final StreamCodec<FriendlyByteBuf, CrossbowStats> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, CrossbowStats::damage,
            ByteBufCodecs.FLOAT, CrossbowStats::chargeSpeedModifier,
            CrossbowStats::new);
}
