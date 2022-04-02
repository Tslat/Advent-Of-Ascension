/*
package net.tslat.aoa3.content.world.gen.biomeprovider;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.SimplexNoiseGenerator;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FloatingIslandsBiomeProvider extends BiomeProvider {
	public static final Codec<FloatingIslandsBiomeProvider> CODEC = RecordCodecBuilder.create((builder) -> {
		return builder.group(RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((provider) -> {
			return provider.lookupRegistry;
		}), Codec.LONG.fieldOf("seed").stable().forGetter((provider) -> {
			return provider.seed;
		}), RecordCodecBuilder.<Pair<Biome.Attributes, Supplier<Biome>>>create((biomeAttributes) -> biomeAttributes.group(
				Biome.Attributes.CODEC.fieldOf("parameters")
						.forGetter(Pair::getFirst),
				Biome.CODEC.fieldOf("biome")
						.forGetter(Pair::getSecond))
				.apply(biomeAttributes, Pair::of))
				.listOf().fieldOf("biomes")
				.forGetter((provider) -> provider.biomeAttributes)
		).apply(builder, builder.stable(FloatingIslandsBiomeProvider::new));
	});

	private final SimplexNoiseGenerator generator;
	private final Registry<Biome> lookupRegistry;
	private final long seed;
	private final List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes;

	protected FloatingIslandsBiomeProvider(Registry<Biome> biomeRegistry, long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes) {
		super(biomeAttributes.stream().flatMap((pair) -> Stream.of(pair.getSecond())));

		this.lookupRegistry = biomeRegistry;
		this.seed = seed;
		this.biomeAttributes = biomeAttributes;
		this.generator = new SimplexNoiseGenerator(new SharedSeedRandom(seed));
	}

	@Override
	protected Codec<? extends BiomeProvider> codec() {
		return CODEC;
	}

	@Override
	public BiomeProvider withSeed(long seed) {
		return new FloatingIslandsBiomeProvider(this.lookupRegistry, seed, this.biomeAttributes);
	}

	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		return possibleBiomes.get(0);
	}
}
*/
