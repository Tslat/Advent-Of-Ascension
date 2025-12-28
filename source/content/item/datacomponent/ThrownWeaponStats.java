package net.tslat.aoa3.content.item.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record ThrownWeaponStats(float impactDamage, int ticksBetweenThrows) {
    public static final Codec<ThrownWeaponStats> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.FLOAT.optionalFieldOf("impact_damage", 0f).forGetter(ThrownWeaponStats::impactDamage),
            Codec.INT.fieldOf("ticks_between_throws").forGetter(ThrownWeaponStats::ticksBetweenThrows)
    ).apply(builder, ThrownWeaponStats::new));
    public static final StreamCodec<FriendlyByteBuf, ThrownWeaponStats> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, ThrownWeaponStats::impactDamage,
            ByteBufCodecs.INT, ThrownWeaponStats::ticksBetweenThrows,
            ThrownWeaponStats::new);
}
