package net.tslat.aoa3.content.loottable.entry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntries;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.tslat.aoa3.common.registration.loot.AoALootEntryTypes;

import java.util.List;
import java.util.function.Consumer;


public class MultiLootEntry extends LootPoolSingletonContainer  {
    public static final Codec<MultiLootEntry> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            ExtraCodecs.strictOptionalField(LootPoolEntries.CODEC.listOf(), "entries", List.of()).forGetter(instance -> instance.entries))
            .and(singletonFields(builder))
            .apply(builder, MultiLootEntry::new));
    private final List<LootPoolEntryContainer> entries;

    private MultiLootEntry(List<LootPoolEntryContainer> entries, int weight, int quality, List<LootItemCondition> conditions, List<LootItemFunction> functions) {
        super(weight, quality, conditions, functions);

        this.entries = entries;
    }

    @Override
    public LootPoolEntryType getType() {
        return AoALootEntryTypes.MULTI.get();
    }

    @Override
    protected void createItemStack(Consumer<ItemStack> loot, LootContext context) {
        for (LootPoolEntryContainer entry : this.entries) {
            entry.expand(context, subEntry -> subEntry.createItemStack(loot, context));
        }
    }

    public static MultiLootEntry.Builder builder() {
        return new Builder();
    }

    public static class Builder extends LootPoolEntryContainer.Builder<MultiLootEntry.Builder> {
        private final List<LootPoolEntryContainer> entries = new ObjectArrayList<>();

        private Builder() {}

        @Override
        protected MultiLootEntry.Builder getThis() {
            return this;
        }

        public MultiLootEntry.Builder with(LootPoolEntryContainer.Builder<?>... childBuilders) {
            for (LootPoolEntryContainer.Builder<?> builder : childBuilders) {
                this.entries.add(builder.build());
            }

            return this;
        }

        @Override
        public LootPoolEntryContainer build() {
            return LootPoolSingletonContainer.simpleBuilder((weight, quality, conditions, functions) -> new MultiLootEntry(this.entries, weight, quality, conditions, functions)).build();
        }
    }
}
