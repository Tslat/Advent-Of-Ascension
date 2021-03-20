package net.tslat.aoa3.library.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootFunction;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.registration.AoALootOperations;

public class RandomSelectionFromTag extends LootFunction {
	private final String tagType;
	private final String tag;

	protected RandomSelectionFromTag(String tagType, String tag, ILootCondition[] lootConditions) {
		super(lootConditions);

		this.tagType = tagType;
		this.tag = tag;
	}

	@Override
	public LootFunctionType getType() {
		return AoALootOperations.LootFunctions.RANDOM_SELECTION_FROM_TAG;
	}

	@Override
	protected ItemStack run(ItemStack stack, LootContext context) {
		ITag.INamedTag<? extends IItemProvider> tag;

		switch (tagType.toLowerCase()) {
			case "block":
				tag = BlockTags.createOptional(new ResourceLocation(this.tag));
				break;
			case "item":
			default:
				tag = ItemTags.createOptional(new ResourceLocation(this.tag));
				break;
		}

		if (tag.getValues().isEmpty())
			return stack;

		return new ItemStack(tag.getRandomElement(context.getRandom()), stack.getCount(), stack.getTag());
	}

	public static LootFunction.Builder<?> builder(String tagType, String tag) {
		return simpleBuilder((conditions) -> new RandomSelectionFromTag(tagType, tag, conditions));
	}

	public static class Serializer extends LootFunction.Serializer<RandomSelectionFromTag> {
		@Override
		public void serialize(JsonObject object, RandomSelectionFromTag function, JsonSerializationContext context) {
			super.serialize(object, function, context);

			object.add("tag_type", context.serialize(function.tagType));
			object.add("tag", context.serialize(function.tag));
		}

		@Override
		public RandomSelectionFromTag deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditions) {
			return new RandomSelectionFromTag(JSONUtils.getAsString(object, "tag_type"), JSONUtils.getAsString(object, "tag"), conditions);
		}
	}
}
