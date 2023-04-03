package net.tslat.aoa3.util;

import com.google.common.collect.Streams;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.tags.IReverseTag;

import java.util.Optional;
import java.util.stream.Stream;

public final class TagUtil {
	public static <T> boolean isTaggedAs(T object, TagKey<T> tagKey, Level level) {
		return level.registryAccess().registry(tagKey.registry()).map(registry -> registry.wrapAsHolder(object).is(tagKey)).orElse(false);
	}

	public static <T> Stream<TagKey<T>> getAllTagsFor(ResourceKey<? extends Registry<T>> registryKey, T object, Level level) {
		Optional<Registry<T>> vanillaRegistry = level.registryAccess().registry(registryKey);

		return vanillaRegistry
				.map(registry -> registry.getResourceKey(object).map(key -> registry.getHolderOrThrow(key).tags()).orElse(Stream.empty()))
				.orElseGet(() -> RegistryManager.ACTIVE.getRegistry(registryKey).tags().getReverseTag(object).map(IReverseTag::getTagKeys).orElseGet(Stream::empty));
	}

	public static <T> Stream<T> getTagContents(TagKey<T> tag, Level level) {
		Optional<Registry<T>> vanillaRegistry = level.registryAccess().registry(tag.registry());

		return vanillaRegistry
				.map(registry -> Streams.stream(registry.getTagOrEmpty(tag)).map(Holder::get))
				.orElseGet(() -> RegistryManager.ACTIVE.getRegistry(tag.registry()).tags().getTag(tag).stream());
	}

	@SafeVarargs
	public static <T extends Holder<T>> boolean isTaggedAsAny(T object, TagKey<T>... tags) {
		for (TagKey<T> tag : tags) {
			if (object.is(tag))
				return true;
		}

		return false;
	}
}
