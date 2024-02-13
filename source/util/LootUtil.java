package net.tslat.aoa3.util;

import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public final class LootUtil {
	public static LootTable getTable(ServerLevel world, ResourceLocation table) {
		return world.getServer().getLootData().getLootTable(table);
	}

	@NotNull
	public static List<ItemStack> generateLoot(ResourceLocation table, LootParams context) {
		LootTable lootTable = getTable(context.getLevel(), table);

		if (lootTable == LootTable.EMPTY)
			return Lists.newArrayList();

		return lootTable.getRandomItems(context);
	}

	public static LootContext createContext(ServerLevel level, Function<LootParams.Builder, LootParams> params) {
		return new LootContext.Builder(params.apply(new LootParams.Builder(level))).create(null);
	}

	public static LootParams getGiftParameters(ServerLevel world, Vec3 position, Entity targetEntity) {
		return getGiftParameters(world, position, 0, targetEntity);
	}

	public static LootParams getGiftParameters(ServerLevel level, Vec3 position, float luck, Entity targetEntity) {
		return new LootParams.Builder(level).withParameter(LootContextParams.THIS_ENTITY, targetEntity).withParameter(LootContextParams.ORIGIN, position).withLuck(luck).create(LootContextParamSets.GIFT);
	}
}
