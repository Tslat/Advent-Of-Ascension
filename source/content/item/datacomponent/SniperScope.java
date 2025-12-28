package net.tslat.aoa3.content.item.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;

public record SniperScope(ResourceLocation texture, float strength) {
    public static final Codec<SniperScope> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.STRING.optionalFieldOf("scope", "scope1").xmap(SniperScope::makeTexture, SniperScope::getName).forGetter(SniperScope::texture),
            Codec.FLOAT.optionalFieldOf("zoomStrength", 1f).forGetter(SniperScope::strength)
    ).apply(builder, SniperScope::new));
    public static final StreamCodec<FriendlyByteBuf, SniperScope> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8.map(SniperScope::makeTexture, SniperScope::getName), SniperScope::texture,
            ByteBufCodecs.FLOAT, SniperScope::strength,
            SniperScope::new);

    public static SniperScope of(String name) {
        return of(name, 1f);
    }

    public static SniperScope of(String name, float zoomStrength) {
        return new SniperScope(makeTexture(name), zoomStrength);
    }

    private static ResourceLocation makeTexture(String name) {
        return AdventOfAscension.id("textures/gui/overlay/scope/" + name + ".png");
    }

    private static String getName(ResourceLocation texture) {
        return texture.getPath().substring(texture.getPath().lastIndexOf('/') + 1, texture.getPath().lastIndexOf('.'));
    }
}
