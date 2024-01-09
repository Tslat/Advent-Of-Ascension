package net.tslat.aoa3.content.loottable.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.tslat.aoa3.common.registration.loot.AoALootConditions;

import java.util.List;
import java.util.Set;

public record BlockHasTag(List<TagKey<Block>> tags, boolean requireAll) implements LootItemCondition {
	public static final Codec<BlockHasTag> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			TagKey.codec(Registries.BLOCK).listOf().fieldOf("tags").forGetter(BlockHasTag::tags),
			Codec.BOOL.optionalFieldOf("require_all", false).forGetter(BlockHasTag::requireAll))
			.apply(builder, BlockHasTag::new));

	@Override
	public LootItemConditionType getType() {
		return AoALootConditions.HAS_BLOCK_TAG.get();
	}

	public static LootItemCondition.Builder forAny(TagKey<Block>... tags) {
		return () -> new BlockHasTag(List.of(tags), false);
	}

	public static LootItemCondition.Builder forAll(TagKey<Block>... tags) {
		return () -> new BlockHasTag(List.of(tags), true);
	}

	@Override
	public Set<LootContextParam<?>> getReferencedContextParams() {
		return Set.of(LootContextParams.BLOCK_STATE);
	}

	@Override
	public boolean test(LootContext lootContext) {
		final BlockState state = lootContext.getParamOrNull(LootContextParams.BLOCK_STATE);

		if (state == null)
			return false;

		if (this.requireAll) {
			for (TagKey<Block> tag : this.tags) {
				if (!state.is(tag))
					return false;
			}

			return true;
		}

		for (TagKey<Block> tag : this.tags) {
			if (state.is(tag))
				return true;
		}

		return false;
	}
}
