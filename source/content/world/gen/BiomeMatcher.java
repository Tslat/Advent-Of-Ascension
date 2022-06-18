package net.tslat.aoa3.content.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
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
		if (ifAll.isPresent() && !ifAll.get().stream().allMatch(set -> set.contains(biome)))
			return false;

		if (ifAny.isPresent() && ifAny.get().stream().noneMatch(set -> set.contains(biome)))
			return false;

		return excluding.isEmpty() || excluding.get().stream().noneMatch(set -> set.contains(biome));
	}
}
