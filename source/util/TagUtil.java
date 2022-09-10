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
import net.minecraftforge.registries.tags.IReverseTag;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.Optional;
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
		return ForgeRegistries.ENTITY_TYPES.tags();
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

	public static <T> Stream<TagKey<T>> getAllTagsFor(ITagManager<T> tagManager, T object) {
		Optional<IReverseTag<T>> reverseTag = tagManager.getReverseTag(object);

		if (reverseTag.isEmpty())
			return Stream.empty();

		return reverseTag.get().getTagKeys();
	}

	public static <T> Stream<T> getTagContents(ITagManager<T> tagManager, TagKey<T> tag) {
		return tagManager.getTag(tag).stream();
	}

	public static <T> boolean isTaggedAsAny(ITagManager<T> tagManager, T object, TagKey<T>... tags) {
		for (TagKey<T> tag : tags) {
			if (tagManager.getTag(tag).contains(object))
				return true;
		}

		return false;
	}
}
