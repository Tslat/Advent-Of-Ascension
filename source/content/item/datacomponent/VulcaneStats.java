package net.tslat.aoa3.content.item.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record VulcaneStats(float damage, float rageCost) {
    public static final Codec<VulcaneStats> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.FLOAT.fieldOf("damage").forGetter(VulcaneStats::damage),
            Codec.FLOAT.fieldOf("rage_cost").forGetter(VulcaneStats::rageCost)
    ).apply(builder, VulcaneStats::new));
    public static final StreamCodec<FriendlyByteBuf, VulcaneStats> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, VulcaneStats::damage,
            ByteBufCodecs.FLOAT, VulcaneStats::rageCost,
            VulcaneStats::new);
}
