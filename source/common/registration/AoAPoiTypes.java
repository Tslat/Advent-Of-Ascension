package net.tslat.aoa3.common.registration;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.block.AoABlocks;

import java.util.function.Supplier;

public final class AoAPoiTypes {
	public static void init() {}

	public static final RegistryObject<PoiType> ASSASSIN = register("assassin", () -> ImmutableSet.of(AoABlocks.FRAME_BENCH.get().defaultBlockState()), 3, 1);

	private static RegistryObject<PoiType> register(String name, Supplier<BlockState> poiBlock) {
		return register(name, () -> ImmutableSet.of(poiBlock.get()), 1, 1);
	}

	private static RegistryObject<PoiType> register(String name, Supplier<ImmutableSet<BlockState>> matchingBlockstates, int maxOccupancy, int poiRadius) {
		return AoARegistries.POI_TYPES.register(name, () -> new PoiType(matchingBlockstates.get(), maxOccupancy, poiRadius));
	}
}
