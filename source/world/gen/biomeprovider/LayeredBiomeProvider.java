package net.tslat.aoa3.world.gen.biomeprovider;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.LayerUtil;

import java.util.List;
import java.util.function.Supplier;

public class LayeredBiomeProvider extends BiomeProvider {
	public static final Codec<LayeredBiomeProvider> CODEC = RecordCodecBuilder.create((builder) -> builder.group(
			Codec.LONG.fieldOf("seed")
					.stable()
					.forGetter((provider) -> provider.seed),
			Codec.BOOL.fieldOf("large_biomes").orElse(false)
					.stable()
					.forGetter((provider) -> provider.largeBiomes),
			RecordCodecBuilder.<Pair<Biome.Attributes, Supplier<Biome>>>create((biomeAttributes) -> biomeAttributes.group(
					Biome.Attributes.CODEC.fieldOf("parameters")
							.forGetter(Pair::getFirst),
					Biome.CODEC.fieldOf("biome")
							.forGetter(Pair::getSecond))
					.apply(biomeAttributes, Pair::of))
					.listOf().fieldOf("biomes")
					.forGetter((provider) -> provider.biomeAttributes),
			RegistryLookupCodec.create(Registry.BIOME_REGISTRY)
					.forGetter((provider) -> provider.lookupRegistry))
			.apply(builder, builder.stable(LayeredBiomeProvider::new)));

	private final Layer biomeLayer;
	private final List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes;
	private final long seed;
	private final boolean largeBiomes;
	private final Registry<Biome> lookupRegistry;

	protected LayeredBiomeProvider(long seed, boolean largeBiomes, List<Pair<Biome.Attributes, Supplier<Biome>>> biomes, Registry<Biome> lookupRegistry) {
		super(biomes.stream().map((key) -> () -> lookupRegistry.getOrThrow(RegistryKey.create(Registry.BIOME_REGISTRY, key.getSecond().get().getRegistryName()))));

		this.seed = seed;
		this.largeBiomes = largeBiomes;
		this.lookupRegistry = lookupRegistry;
		this.biomeAttributes = biomes;
		this.biomeLayer = LayerUtil.getDefaultLayer(seed, false, largeBiomes ? 6 : 4, 4);
	}

	@Override
	protected Codec<? extends BiomeProvider> codec() {
		return CODEC;
	}

	@Override
	public BiomeProvider withSeed(long seed) {
		return new LayeredBiomeProvider(seed, largeBiomes, biomeAttributes, lookupRegistry);
	}

	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		return biomeLayer.get(lookupRegistry, x, z);
	}
}
