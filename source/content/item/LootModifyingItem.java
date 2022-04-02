package net.tslat.aoa3.content.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import javax.annotation.Nonnull;
import java.util.List;

public interface LootModifyingItem {
	void doLootModification(final List<ItemStack> existingLoot, final LootContext lootContext);

	default boolean isBlockHarvestLoot(LootContext context) {
		for (LootContextParam<?> param : LootContextParamSets.BLOCK.getRequired()) {
			if (!context.hasParam(param))
				return false;
		}

		return true;
	}

	default boolean isEntityKillLoot(LootContext context) {
		for (LootContextParam<?> param : LootContextParamSets.ENTITY.getRequired()) {
			if (!context.hasParam(param))
				return false;
		}

		return true;
	}

	default boolean isFishingLoot(LootContext context) {
		for (LootContextParam<?> param : LootContextParamSets.FISHING.getRequired()) {
			if (!context.hasParam(param))
				return false;
		}

		return true;
	}

	@Nonnull
	default BlockState getHarvestedBlock(LootContext lootContext) {
		if (!isBlockHarvestLoot(lootContext))
			return Blocks.AIR.defaultBlockState();

		return lootContext.getParamOrNull(LootContextParams.BLOCK_STATE);
	}

	default ItemStack getToolStack(LootContext lootContext) {
		return lootContext.getParamOrNull(LootContextParams.TOOL);
	}
}
