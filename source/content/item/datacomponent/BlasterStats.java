package net.tslat.aoa3.content.item.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record BlasterStats(float damage, int ticksBetweenShots, float spiritCost, int chargeUpTicks, float beamDistance) {
    public static final Codec<BlasterStats> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.FLOAT.fieldOf("damage").forGetter(BlasterStats::damage),
            Codec.INT.fieldOf("ticks_between_shots").forGetter(BlasterStats::ticksBetweenShots),
            Codec.FLOAT.fieldOf("spirit_cost").forGetter(BlasterStats::spiritCost),
            Codec.INT.fieldOf("charge_up_ticks").forGetter(BlasterStats::chargeUpTicks),
            Codec.FLOAT.optionalFieldOf("beam_distance", 0f).forGetter(BlasterStats::beamDistance)
    ).apply(builder, BlasterStats::new));
    public static final StreamCodec<FriendlyByteBuf, BlasterStats> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, BlasterStats::damage,
            ByteBufCodecs.VAR_INT, BlasterStats::ticksBetweenShots,
            ByteBufCodecs.FLOAT, BlasterStats::spiritCost,
            ByteBufCodecs.VAR_INT, BlasterStats::chargeUpTicks,
            ByteBufCodecs.FLOAT, BlasterStats::beamDistance,
            BlasterStats::new);
}
