package net.tslat.aoa3.library.object.container;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Reference2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntMaps;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tslat.tme.api.util.CodecUtil;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Predicate;

public record AmmoVoidPouchComponent(Reference2IntMap<Item> contents) {
    public static final StreamCodec<RegistryFriendlyByteBuf, AmmoVoidPouchComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.<RegistryFriendlyByteBuf, Item, Integer, Reference2IntMap<Item>>map(Reference2IntArrayMap::new, ByteBufCodecs.registry(Registries.ITEM), ByteBufCodecs.INT)
                    .map(Reference2IntMaps::unmodifiable, Function.identity()), AmmoVoidPouchComponent::contents,
            AmmoVoidPouchComponent::new);
    public static final Codec<AmmoVoidPouchComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            CodecUtil.map(BuiltInRegistries.ITEM.byNameCodec(), Codec.INT, map -> Reference2IntMaps.unmodifiable(new Reference2IntOpenHashMap<>(map)))
                    .fieldOf("contents")
                    .forGetter(instance -> instance.contents)
    ).apply(builder, AmmoVoidPouchComponent::new));
    public static final AmmoVoidPouchComponent EMPTY = new AmmoVoidPouchComponent(Reference2IntMaps.unmodifiable(new Reference2IntArrayMap<>()));

    public AmmoVoidPouchComponent() {
        this(Reference2IntMaps.unmodifiable(new Reference2IntArrayMap<>()));
    }

    public boolean has(Predicate<ItemStack> predicate) {
        for (Item entry : this.contents.keySet()) {
            if (predicate.test(entry.getDefaultInstance()))
                return true;
        }

        return false;
    }

    public int getAmount(Item item) {
        return this.contents.getOrDefault(item, 0);
    }

    @Nullable
    public Pair<AmmoVoidPouchComponent, ItemStack> retrieveAmmo(Predicate<ItemStack> predicate, int amount) {
        for (Item entry : this.contents.keySet()) {
            if (predicate.test(entry.getDefaultInstance())) {
                AmmoVoidPouchComponent newComponent = consume(entry, amount);

                if (newComponent != null)
                    return Pair.of(newComponent, new ItemStack(entry, amount));
            }
        }

        return null;
    }

    @Nullable
    public AmmoVoidPouchComponent consume(Item item, int amount) {
        int current = this.contents.getOrDefault(item, 0);

        if (current <= 0 || current < amount)
            return null;

        Reference2IntMap<Item> newContents = new Reference2IntArrayMap<>(this.contents);

        if (current == amount) {
            newContents.removeInt(item);
        }
        else {
            newContents.put(item, current - amount);
        }

        return new AmmoVoidPouchComponent(Reference2IntMaps.unmodifiable(newContents));
    }

    @Nullable
    public AmmoVoidPouchComponent add(Item item, int amount) {
        return add(new ItemStack(item, amount));
    }

    @Nullable
    public AmmoVoidPouchComponent add(ItemStack stack) {
        if (!stack.getComponentsPatch().isEmpty())
            return null;

        int current = this.contents.getOrDefault(stack.getItem(), 0);
        int toAdd = Math.min(Integer.MAX_VALUE - current, stack.getCount());

        if (toAdd <= 0)
            return null;

        Reference2IntMap<Item> newContents = new Reference2IntArrayMap<>(this.contents);

        newContents.put(stack.getItem(), current + toAdd);
        stack.shrink(toAdd);

        return new AmmoVoidPouchComponent(Reference2IntMaps.unmodifiable(newContents));
    }
}
