package net.tslat.aoa3.util;

import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.LootTable;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class LootUtil {
	public static LootTable getTable(ServerWorld world, ResourceLocation table) {
		return world.getServer().getLootTableManager().getLootTableFromLocation(table);
	}

	@Nonnull
	public static List<ItemStack> generateLoot(ServerWorld world, ResourceLocation table, LootContext context) {
		LootTable lootTable = getTable(world, table);

		if (lootTable == LootTable.EMPTY_LOOT_TABLE)
			return Lists.<ItemStack>newArrayList();

		return lootTable.generate(context);
	}

	public static LootContext getGiftContext(ServerWorld world, BlockPos position, Entity targetEntity) {
		return getGiftContext(world, position, 0, targetEntity);
	}

	public static LootContext getGiftContext(ServerWorld world, BlockPos position, float luck, Entity targetEntity) {
		return new LootContext.Builder(world).withRandom(world.getRandom()).withParameter(LootParameters.THIS_ENTITY, targetEntity).withParameter(LootParameters.POSITION, position).withLuck(luck).build(LootParameterSets.GIFT);
	}
}
