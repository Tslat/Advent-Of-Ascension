package net.tslat.aoa3.utils;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;

import javax.annotation.Nonnull;
import java.util.List;

public class LootUtil {
	public static void generateAndProvideLootDirectly(EntityPlayerMP pl, ResourceLocation lootTable) {
		ItemUtil.givePlayerMultipleItems(pl, generateLootWithLuck(lootTable, pl));
	}

	@Nonnull
	public static List<ItemStack> generateLootWithLuck(ResourceLocation lootTable, EntityPlayerMP pl) {
		if (pl.world.isRemote)
			return Lists.<ItemStack>newArrayList();

		return pl.world.getLootTableManager().getLootTableFromLocation(lootTable).generateLootForPools(pl.world.rand, new LootContext.Builder(pl.getServerWorld()).withPlayer(pl).withLuck(pl.getLuck()).build());
	}

	@Nonnull
	public static List<ItemStack> generateLootWithCustomLuck(ResourceLocation lootTable, WorldServer world, float luck) {
		return world.getLootTableManager().getLootTableFromLocation(lootTable).generateLootForPools(world.rand, new LootContext.Builder(world).withLuck(luck).build());
	}

	@Nonnull
	public static List<ItemStack> generateLoot(ResourceLocation lootTable, WorldServer world) {
		return world.getLootTableManager().getLootTableFromLocation(lootTable).generateLootForPools(world.rand, new LootContext.Builder(world).build());
	}

	@Nonnull
	public static List<ItemStack> generateLootWithPlayer(ResourceLocation lootTable, EntityPlayerMP pl) {
		return pl.world.getLootTableManager().getLootTableFromLocation(lootTable).generateLootForPools(pl.world.rand, new LootContext.Builder(pl.getServerWorld()).withPlayer(pl).build());
	}
}
