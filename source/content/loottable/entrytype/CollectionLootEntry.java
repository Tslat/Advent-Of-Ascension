package net.tslat.aoa3.content.loottable.entrytype;

import com.google.gson.*;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.tslat.aoa3.common.registration.AoALootOperations;

import java.util.List;
import java.util.function.Consumer;

public class CollectionLootEntry extends LootPoolSingletonContainer {
	private final LootPoolEntryContainer[] entries;

	CollectionLootEntry(LootPoolEntryContainer[] entries, int weight, int quality, LootItemCondition[] conditions, LootItemFunction[] functions) {
		super(weight, quality, conditions, functions);

		this.entries = entries;
	}

	@Override
	public LootPoolEntryType getType() {
		return AoALootOperations.LootEntryTypes.COLLECTION.get();
	}

	@Override
	protected void createItemStack(Consumer<ItemStack> loot, LootContext context) {
		for (LootPoolEntryContainer entry : this.entries) {
			entry.expand(context, subEntry -> subEntry.createItemStack(loot, context));
		}
	}

	public static CollectionLootEntry.Builder builder() {
		return new Builder();
	}

	public static class Builder extends LootPoolEntryContainer.Builder<CollectionLootEntry.Builder> {
		private final List<LootPoolEntryContainer> entries = new ObjectArrayList<>();

		private Builder() {}

		@Override
		protected CollectionLootEntry.Builder getThis() {
			return this;
		}

		public CollectionLootEntry.Builder with(LootPoolEntryContainer.Builder<?>... childBuilders) {
			for (LootPoolEntryContainer.Builder<?> builder : childBuilders) {
				this.entries.add(builder.build());
			}

			return this;
		}

		@Override
		public LootPoolEntryContainer build() {
			return simpleBuilder((weight, quality, conditions, functions) -> new CollectionLootEntry(this.entries.toArray(new LootPoolEntryContainer[0]), weight, quality, conditions, functions)).build();
		}
	}

	public static class Serializer extends LootPoolSingletonContainer.Serializer<CollectionLootEntry> {
		@Override
		public void serializeCustom(JsonObject json, CollectionLootEntry entry, JsonSerializationContext context) {
			super.serializeCustom(json, entry, context);

			JsonArray children = new JsonArray();

			for (LootPoolEntryContainer subEntry : entry.entries) {
				JsonObject childObj = new JsonObject();

				childObj.addProperty("type", BuiltInRegistries.LOOT_POOL_ENTRY_TYPE.getKey(subEntry.getType()).toString());
				((LootPoolEntryContainer.Serializer)subEntry.getType().getSerializer()).serialize(childObj, subEntry, context);
				children.add(childObj);
			}

			json.add("children", children);
		}

		@Override
		protected CollectionLootEntry deserialize(JsonObject json, JsonDeserializationContext context, int weight, int quality, LootItemCondition[] conditions, LootItemFunction[] functions) {
			JsonArray children = json.getAsJsonArray("children");
			List<LootPoolEntryContainer> entries = new ObjectArrayList<>();

			for (JsonElement child : children) {
				JsonObject obj = child.getAsJsonObject();
				ResourceLocation type = new ResourceLocation(GsonHelper.getAsString(obj, "type"));

				entries.add(BuiltInRegistries.LOOT_POOL_ENTRY_TYPE.get(type).getSerializer().deserialize(obj, context));
			}

			return new CollectionLootEntry(entries.toArray(new LootPoolEntryContainer[0]), weight, quality, conditions, functions);
		}
	}
}
