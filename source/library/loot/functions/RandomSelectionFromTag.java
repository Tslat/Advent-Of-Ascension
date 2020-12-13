package net.tslat.aoa3.library.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.tslat.aoa3.advent.AdventOfAscension;

public class RandomSelectionFromTag extends LootFunction {
	private final String tagType;
	private final String tag;

	protected RandomSelectionFromTag(String tagType, String tag, ILootCondition[] lootConditions) {
		super(lootConditions);

		this.tagType = tagType;
		this.tag = tag;
	}

	@Override
	protected ItemStack doApply(ItemStack stack, LootContext context) {
		Tag<? extends IItemProvider> tag;

		switch (tagType.toLowerCase()) {
			case "block":
				tag = new BlockTags.Wrapper(new ResourceLocation(this.tag));
				break;
			case "item":
			default:
				tag = new ItemTags.Wrapper(new ResourceLocation(this.tag));
				break;
		}

		if (tag.getAllElements().isEmpty())
			return stack;

		return new ItemStack(tag.getRandomElement(context.getRandom()), stack.getCount(), stack.getTag());
	}

	public static LootFunction.Builder<?> builder(String tagType, String tag) {
		return builder((conditions) -> new RandomSelectionFromTag(tagType, tag, conditions));
	}

	public static class Serializer extends LootFunction.Serializer<RandomSelectionFromTag> {
		public Serializer() {
			super(new ResourceLocation(AdventOfAscension.MOD_ID, "random_selection_from_tag"), RandomSelectionFromTag.class);
		}

		@Override
		public void serialize(JsonObject object, RandomSelectionFromTag function, JsonSerializationContext context) {
			super.serialize(object, function, context);

			object.add("tag_type", context.serialize(function.tagType));
			object.add("tag", context.serialize(function.tag));
		}

		@Override
		public RandomSelectionFromTag deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditions) {
			return new RandomSelectionFromTag(JSONUtils.getString(object, "tag_type"), JSONUtils.getString(object, "tag"), conditions);
		}
	}
}
