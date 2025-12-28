package net.tslat.aoa3.library.object.extension;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class BlockToItemHolder implements Holder<Item> {
    private final Supplier<Holder<Item>> itemHolder;

    BlockToItemHolder(Holder<? extends ItemLike> holder) {
        this.itemHolder = Suppliers.memoize(() -> convert(holder));
    }

    public static BlockToItemHolder of(Holder<? extends ItemLike> holder) {
        return new BlockToItemHolder(holder);
    }

    public static BlockToItemHolder[] of(Holder<? extends ItemLike>... holders) {
        BlockToItemHolder[] holdersArray = new BlockToItemHolder[holders.length];

        for (int i = 0; i < holders.length; i++) {
            holdersArray[i] = of(holders[i]);
        }

        return holdersArray;
    }

    private static Holder<Item> convert(Holder<? extends ItemLike> holder) {
        final Registry<Item> itemRegistry = BuiltInRegistries.ITEM;

        return holder.unwrap().map(key -> {
            if (key.registryKey().equals(Registries.ITEM))
                return BuiltInRegistries.ITEM.getHolderOrThrow((ResourceKey<Item>)key);

            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, key.location());

            if (!itemRegistry.containsKey(itemKey))
                throw new IllegalStateException("Item for ItemLike " + key.location() + " does not exist in registry");

            Item item = itemRegistry.get(itemKey);

            if (item == BuiltInRegistries.BLOCK.get(key.location()).asItem())
                return itemRegistry.wrapAsHolder(item);

            throw new IllegalStateException("Item for ItemLike " + key.location() + " does not exist in registry");
        }, itemLike -> {
            Item item = itemLike.asItem();

            if ((item != Items.AIR || item == itemLike || itemLike == Blocks.AIR) && itemRegistry.containsValue(item))
                return itemRegistry.wrapAsHolder(item);

            throw new IllegalStateException("Item for ItemLike " + itemLike + " does not exist in registry");
        });
    }

    @Override
    public Item value() {
        return this.itemHolder.get().value();
    }

    @Override
    public boolean isBound() {
        return this.itemHolder.get().isBound();
    }

    @Override
    public boolean is(ResourceLocation resourceLocation) {
        return this.itemHolder.get().is(resourceLocation);
    }

    @Override
    public boolean is(ResourceKey<Item> resourceKey) {
        return this.itemHolder.get().is(resourceKey);
    }

    @Override
    public boolean is(Predicate<ResourceKey<Item>> predicate) {
        return this.itemHolder.get().is(predicate);
    }

    @Override
    public boolean is(TagKey<Item> tagKey) {
        return this.itemHolder.get().is(tagKey);
    }

    @Deprecated
    @Override
    public boolean is(Holder<Item> holder) {
        return this.itemHolder.get().is(holder);
    }

    @Override
    public Stream<TagKey<Item>> tags() {
        return this.itemHolder.get().tags();
    }

    @Override
    public Either<ResourceKey<Item>, Item> unwrap() {
        return this.itemHolder.get().unwrap();
    }

    @Override
    public Optional<ResourceKey<Item>> unwrapKey() {
        return this.itemHolder.get().unwrapKey();
    }

    @Override
    public Kind kind() {
        return this.itemHolder.get().kind();
    }

    @Override
    public boolean canSerializeIn(HolderOwner<Item> holderOwner) {
        return this.itemHolder.get().canSerializeIn(holderOwner);
    }
}
