package net.tslat.aoa3.content.loottable.condition;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.TagCollectionManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.registration.AoALootOperations;

public class BlockHasTag implements ILootCondition {
	private final ITag<Block>[] tags;
	private final boolean requireAll;

	public BlockHasTag(ITag<Block>... tags) {
		this(false, tags);
	}

	public BlockHasTag(boolean requireAll, ITag<Block>... tags) {
		this.tags = tags;
		this.requireAll = requireAll;
	}

	@Override
	public boolean test(LootContext lootContext) {
		BlockState state = lootContext.getParamOrNull(LootParameters.BLOCK_STATE);

		if (state != null) {
			if (requireAll) {
				for (ITag<Block> tag : tags) {
					if (!state.is(tag))
						return false;
				}

				return true;
			}
			else {
				for (ITag<Block> tag : tags) {
					if (state.is(tag))
						return true;
				}
			}
		}

		return false;
	}

	@Override
	public LootConditionType getType() {
		return AoALootOperations.LootConditions.HAS_BLOCK_TAG;
	}

	public ITag<Block>[] getTags() {
		return this.tags;
	}

	public boolean requiresAllTags() {
		return this.requireAll;
	}

	public static class Serializer implements ILootSerializer<BlockHasTag> {
		@Override
		public void serialize(JsonObject json, BlockHasTag instance, JsonSerializationContext jsonSerializationContext) {
			ITagCollection<Block> blockTags = TagCollectionManager.getInstance().getBlocks();
			JsonArray tagsArray = new JsonArray();

			for (ITag<Block> tag : instance.tags) {
				tagsArray.add(blockTags.getId(tag).toString());
			}

			json.addProperty("require_all", instance.requireAll);
			json.add("tags", tagsArray);
		}

		@Override
		public BlockHasTag deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			JsonArray tagsArray = JSONUtils.getAsJsonArray(json, "tags");
			ITag<Block>[] tags = new ITag[tagsArray.size()];

			for (int i = 0; i < tagsArray.size(); i++) {
				tags[i] = TagCollectionManager.getInstance().getBlocks().getTag(new ResourceLocation(tagsArray.get(i).getAsString()));
			}

			return new BlockHasTag(JSONUtils.getAsBoolean(json, "require_all", false), tags);
		}
	}
}
