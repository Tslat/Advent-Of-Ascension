package net.tslat.aoa3.content.item.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Reference2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.tslat.aoa3.common.registration.AoARegistries;

public record StaffStats(Reference2IntMap<Item> runeCosts, float damage) {
    public static final Codec<StaffStats> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.simpleMap(AoARegistries.ITEMS.lookupCodec(), Codec.INT, AoARegistries.ITEMS).<Reference2IntMap<Item>>xmap(Reference2IntArrayMap::new, map -> map).fieldOf("runes").forGetter(StaffStats::runeCosts),
            Codec.FLOAT.fieldOf("damage").forGetter(StaffStats::damage)
    ).apply(builder, StaffStats::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, StaffStats> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.map(Reference2IntArrayMap::new, ByteBufCodecs.registry(Registries.ITEM), ByteBufCodecs.VAR_INT), StaffStats::runeCosts,
            ByteBufCodecs.FLOAT, StaffStats::damage,
            StaffStats::new);
}
