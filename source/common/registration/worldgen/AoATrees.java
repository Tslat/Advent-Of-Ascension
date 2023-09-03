package net.tslat.aoa3.common.registration.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.gen.feature.tree.foliageplacer.NoFoliagePlacer;
import net.tslat.aoa3.content.world.gen.feature.tree.trunkplacer.AoAGiantJungleTrunkPlacer;
import net.tslat.aoa3.content.world.gen.feature.tree.trunkplacer.AoAGiantTrunkPlacer;
import net.tslat.aoa3.content.world.gen.feature.tree.trunkplacer.BaobabTrunkPlacer;

public final class AoATrees {
	public static final RegistryObject<TrunkPlacerType<AoAGiantTrunkPlacer>> AOA_GIANT_TRUNK = registerTrunkPlacer("aoa_giant", AoAGiantTrunkPlacer.CODEC);
	public static final RegistryObject<TrunkPlacerType<BaobabTrunkPlacer>> BAOBAB_TRUNK = registerTrunkPlacer("baobab", BaobabTrunkPlacer.CODEC);
	public static final RegistryObject<TrunkPlacerType<AoAGiantJungleTrunkPlacer>> AOA_GIANT_JUNGLE_TRUNK = registerTrunkPlacer("aoa_giant_jungle", AoAGiantJungleTrunkPlacer.CODEC);

	public static final RegistryObject<FoliagePlacerType<NoFoliagePlacer>> NO_FOLIAGE_FOLIAGE = registerFoliagePlacer("no_foliage", NoFoliagePlacer.CODEC);

	private static <T extends TrunkPlacer> RegistryObject<TrunkPlacerType<T>> registerTrunkPlacer(String id, Codec<T> codec) {
		return AoARegistries.TRUNK_PLACERS.register(id, () -> new TrunkPlacerType<>(codec));
	}

	private static <T extends FoliagePlacer> RegistryObject<FoliagePlacerType<T>> registerFoliagePlacer(String id, Codec<T> codec) {
		return AoARegistries.FOLIAGE_PLACERS.register(id, () -> new FoliagePlacerType<>(codec));
	}

	public static void init() {}
}
