package net.tslat.aoa3.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.stream.Stream;

public final class TagUtil {
	public static ITagManager<Block> blockTags() {
		return ForgeRegistries.BLOCKS.tags();
	}

	public static ITagManager<Item> itemTags() {
		return ForgeRegistries.ITEMS.tags();
	}

	public static ITagManager<Fluid> fluidTags() {
		return ForgeRegistries.FLUIDS.tags();
	}

	public static ITagManager<EntityType<?>> entityTags() {
		return ForgeRegistries.ENTITIES.tags();
	}

	public static ITagManager<Biome> biomeTags() {
		return ForgeRegistries.BIOMES.tags();
	}

	public static boolean isTaggedAs(Block block, TagKey<Block> tagKey) {
		return blockTags().getTag(tagKey).contains(block);
	}

	public static boolean isTaggedAs(BlockState blockState, TagKey<Block> tagKey) {
		return isTaggedAs(blockState.getBlock(), tagKey);
	}

	public static boolean isTaggedAs(Item item, TagKey<Item> tagKey) {
		return itemTags().getTag(tagKey).contains(item);
	}

	public static boolean isTaggedAs(ItemStack itemStack, TagKey<Item> tagKey) {
		return isTaggedAs(itemStack.getItem(), tagKey);
	}

	public static boolean isTaggedAs(Fluid fluid, TagKey<Fluid> tagKey) {
		return fluidTags().getTag(tagKey).contains(fluid);
	}

	public static boolean isTaggedAs(FluidState fluidState, TagKey<Fluid> tagKey) {
		return isTaggedAs(fluidState.getType(), tagKey);
	}

	public static boolean isTaggedAs(EntityType<?> entityType, TagKey<EntityType<?>> tagKey) {
		return entityTags().getTag(tagKey).contains(entityType);
	}

	public static boolean isTaggedAs(Entity entity, TagKey<EntityType<?>> tagKey) {
		return isTaggedAs(entity.getType(), tagKey);
	}

	public static boolean isTaggedAs(Biome biome, TagKey<Biome> tagKey) {
		return biomeTags().getTag(tagKey).contains(biome);
	}

	public static Stream<TagKey<Biome>> getAllTagsFor(Biome biome) {
		return biomeTags().getReverseTag(biome).get().getTagKeys();
	}
}
