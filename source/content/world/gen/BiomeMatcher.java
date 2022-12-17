package net.tslat.aoa3.content.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public record BiomeMatcher(Optional<List<HolderSet<Biome>>> ifAll, Optional<List<HolderSet<Biome>>> ifAny, Optional<List<HolderSet<Biome>>> excluding) implements Predicate<Holder<Biome>> {
	public static final Codec<BiomeMatcher> CODEC = RecordCodecBuilder.create(codec -> codec.group(
			Biome.LIST_CODEC.listOf().optionalFieldOf("if_all").forGetter(BiomeMatcher::ifAll),
			Biome.LIST_CODEC.listOf().optionalFieldOf("if_any").forGetter(BiomeMatcher::ifAny),
			Biome.LIST_CODEC.listOf().optionalFieldOf("excluding").forGetter(BiomeMatcher::excluding)
	).apply(codec, BiomeMatcher::new));

	@Override
	public boolean test(Holder<Biome> biome) {
		if (ifAll.isPresent()) {
			for (HolderSet<Biome> biomeSet : ifAll.get()) {
				if (!biomeSet.contains(biome))
					return false;
			}
		}

		if (ifAny.isPresent()) {
			boolean found = false;

			for (HolderSet<Biome> biomeSet : ifAny.get()) {
				if (biomeSet.contains(biome)) {
					found = true;

					break;
				}
			}

			if (!found)
				return false;
		}

		if (excluding.isPresent()) {
			for (HolderSet<Biome> biomeSet : excluding.get()) {
				if (biomeSet.contains(biome))
					return false;
			}
		}

		return true;
	}

	public static class Builder {
		final HolderLookup.RegistryLookup<Biome> holderLookup;

		List<HolderSet<Biome>> ifAll = new ObjectArrayList<>(1);
		List<HolderSet<Biome>> ifAny = new ObjectArrayList<>(1);
		List<HolderSet<Biome>> excluding = new ObjectArrayList<>(1);

		public Builder(ServerLevel level) {
			this(level.registryAccess().lookupOrThrow(Registries.BIOME));
		}

		public Builder(HolderLookup.RegistryLookup<Biome> registryLookup) {
			this.holderLookup = registryLookup;
		}

		public Builder mustBe(TagKey<Biome>... tags) {
			for (TagKey<Biome> tag : tags) {
				ifAll.add(HolderSet.emptyNamed(this.holderLookup, tag));
			}

			return this;
		}

		public Builder mustBe(ResourceKey<Biome>... biomes) {
			for (ResourceKey<Biome> biome : biomes) {
				ifAll.add(HolderSet.direct(Holder.Reference.createStandAlone(this.holderLookup, biome)));
			}

			return this;
		}

		public Builder mustBe(Holder<Biome>... biomes) {
			for (Holder<Biome> biome : biomes) {
				ifAll.add(HolderSet.direct(biome));
			}

			return this;
		}

		public Builder atLeastOneOf(TagKey<Biome>... tags) {
			for (TagKey<Biome> tag : tags) {
				ifAny.add(HolderSet.emptyNamed(this.holderLookup, tag));
			}

			return this;
		}

		public Builder atLeastOneOf(ResourceKey<Biome>... biomes) {
			for (ResourceKey<Biome> biome : biomes) {
				ifAny.add(HolderSet.direct(Holder.Reference.createStandAlone(this.holderLookup, biome)));
			}

			return this;
		}

		public Builder atLeastOneOf(Holder<Biome>... biomes) {
			for (Holder<Biome> biome : biomes) {
				ifAny.add(HolderSet.direct(biome));
			}

			return this;
		}

		public Builder cannotBe(TagKey<Biome>... tags) {
			for (TagKey<Biome> tag : tags) {
				excluding.add(HolderSet.emptyNamed(this.holderLookup, tag));
			}

			return this;
		}

		public Builder cannotBe(ResourceKey<Biome>... biomes) {
			for (ResourceKey<Biome> biome : biomes) {
				excluding.add(HolderSet.direct(Holder.Reference.createStandAlone(this.holderLookup, biome)));
			}

			return this;
		}

		public Builder cannotBe(Holder<Biome>... biomes) {
			for (Holder<Biome> biome : biomes) {
				excluding.add(HolderSet.direct(biome));
			}

			return this;
		}

		public BiomeMatcher build() {
			Optional<List<HolderSet<Biome>>> ifAll = this.ifAll.isEmpty() ? Optional.empty() : Optional.of(this.ifAll);
			Optional<List<HolderSet<Biome>>> ifAny = this.ifAny.isEmpty() ? Optional.empty() : Optional.of(this.ifAny);
			Optional<List<HolderSet<Biome>>> excluding = this.excluding.isEmpty() ? Optional.empty() : Optional.of(this.excluding);

			return new BiomeMatcher(ifAll, ifAny, excluding);
		}
	}
}
