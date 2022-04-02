package net.tslat.aoa3.util;

import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.tslat.aoa3.client.ClientOperations;

import java.util.Optional;

public final class TagUtil {
	public static Registry<Block> getBlockRegistry() {
		MinecraftServer server = ServerLifecycleHooks.getCurrentServer();

		if (server != null) {
			return server.registryAccess().registry(Registry.BLOCK_REGISTRY).get();
		}
		else {
			return ClientOperations.getWorld().registryAccess().registry(Registry.BLOCK_REGISTRY).get();
		}
	}
	public static Registry<Item> getItemRegistry() {
		MinecraftServer server = ServerLifecycleHooks.getCurrentServer();

		if (server != null) {
			return server.registryAccess().registry(Registry.ITEM_REGISTRY).get();
		}
		else {
			return ClientOperations.getWorld().registryAccess().registry(Registry.ITEM_REGISTRY).get();
		}
	}

	public static Optional<HolderSet.Named<Block>> getBlockTagContents(TagKey<Block> tagKey) {
		return getBlockRegistry().getTag(tagKey);
	}

	public static Optional<HolderSet.Named<Item>> getItemTagContents(TagKey<Item> tagKey) {
		return getItemRegistry().getTag(tagKey);
	}

	public static boolean isTaggedAs(Block block, TagKey<Block> tagKey) {
		return block.builtInRegistryHolder().is(tagKey);
	}

	public static boolean isTaggedAs(BlockState block, TagKey<Block> tagKey) {
		return block.is(tagKey);
	}

	public static boolean isTaggedAs(Item item, TagKey<Item> tagKey) {
		return item.builtInRegistryHolder().is(tagKey);
	}

	public static boolean isTaggedAs(ItemStack block, TagKey<Item> tagKey) {
		return block.is(tagKey);
	}
}
