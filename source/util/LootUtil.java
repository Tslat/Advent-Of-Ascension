package net.tslat.aoa3.util;

import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import java.util.List;

public final class LootUtil {
	public static LootTable getTable(ServerWorld world, ResourceLocation table) {
		return world.getServer().getLootTables().get(table);
	}

	@Nonnull
	public static List<ItemStack> generateLoot(ServerWorld world, ResourceLocation table, LootContext context) {
		LootTable lootTable = getTable(world, table);

		if (lootTable == LootTable.EMPTY)
			return Lists.<ItemStack>newArrayList();

		return lootTable.getRandomItems(context);
	}

	public static LootContext getGiftContext(ServerWorld world, Vector3d position, Entity targetEntity) {
		return getGiftContext(world, position, 0, targetEntity);
	}

	public static LootContext getGiftContext(ServerWorld world, Vector3d position, float luck, Entity targetEntity) {
		return new LootContext.Builder(world).withRandom(world.getRandom()).withParameter(LootParameters.THIS_ENTITY, targetEntity).withParameter(LootParameters.ORIGIN, position).withLuck(luck).create(LootParameterSets.GIFT);
	}
}
