package net.tslat.aoa3.content.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameter;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;

import javax.annotation.Nonnull;
import java.util.List;

public interface LootModifyingItem {
	void doLootModification(final List<ItemStack> existingLoot, final LootContext lootContext);

	default boolean isBlockHarvestLoot(LootContext context) {
		for (LootParameter<?> param : LootParameterSets.BLOCK.getRequired()) {
			if (!context.hasParam(param))
				return false;
		}

		return true;
	}

	default boolean isEntityKillLoot(LootContext context) {
		for (LootParameter<?> param : LootParameterSets.ENTITY.getRequired()) {
			if (!context.hasParam(param))
				return false;
		}

		return true;
	}

	default boolean isFishingLoot(LootContext context) {
		for (LootParameter<?> param : LootParameterSets.FISHING.getRequired()) {
			if (!context.hasParam(param))
				return false;
		}

		return true;
	}

	@Nonnull
	default BlockState getHarvestedBlock(LootContext lootContext) {
		if (!isBlockHarvestLoot(lootContext))
			return Blocks.AIR.defaultBlockState();

		return lootContext.getParamOrNull(LootParameters.BLOCK_STATE);
	}
}
