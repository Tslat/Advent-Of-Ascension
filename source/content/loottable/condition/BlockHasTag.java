package net.tslat.aoa3.content.loottable.condition;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.tslat.aoa3.common.registration.AoALootOperations;

public class BlockHasTag implements LootItemCondition {
	private final TagKey<Block>[] tags;
	private final boolean requireAll;

	public BlockHasTag(TagKey<Block>... tags) {
		this(false, tags);
	}

	public BlockHasTag(boolean requireAll, TagKey<Block>... tags) {
		this.tags = tags;
		this.requireAll = requireAll;
	}

	@Override
	public boolean test(LootContext lootContext) {
		BlockState state = lootContext.getParamOrNull(LootContextParams.BLOCK_STATE);

		if (state != null) {
			if (requireAll) {
				for (TagKey<Block> tag : tags) {
					if (!state.is(tag))
						return false;
				}

				return true;
			}
			else {
				for (TagKey<Block> tag : tags) {
					if (state.is(tag))
						return true;
				}
			}
		}

		return false;
	}

	@Override
	public LootItemConditionType getType() {
		return AoALootOperations.LootConditions.HAS_BLOCK_TAG;
	}

	public TagKey<Block>[] getTags() {
		return this.tags;
	}

	public boolean requiresAllTags() {
		return this.requireAll;
	}

	public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<BlockHasTag> {
		@Override
		public void serialize(JsonObject json, BlockHasTag instance, JsonSerializationContext jsonSerializationContext) {
			JsonArray tagsArray = new JsonArray();

			for (TagKey<Block> tag : instance.tags) {
				tagsArray.add(tag.location().toString());
			}

			json.addProperty("require_all", instance.requireAll);
			json.add("tags", tagsArray);
		}

		@Override
		public BlockHasTag deserialize(JsonObject json, JsonDeserializationContext jsonDeserializationContext) {
			JsonArray tagsArray = GsonHelper.getAsJsonArray(json, "tags");
			TagKey<Block>[] tags = new TagKey[tagsArray.size()];

			for (int i = 0; i < tagsArray.size(); i++) {
				tags[i] = BlockTags.create(new ResourceLocation(tagsArray.get(i).getAsString()));
			}

			return new BlockHasTag(GsonHelper.getAsBoolean(json, "require_all", false), tags);
		}
	}
}
