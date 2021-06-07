package net.tslat.aoa3.common.registration.worldgen;

import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.world.gen.surfacebuilder.MirroredSurfaceBuilder;
import net.tslat.aoa3.world.gen.surfacebuilder.OceanlessSurfaceBuilder;
import net.tslat.aoa3.world.gen.surfacebuilder.TieredSurfaceBuilder;

import java.util.function.Supplier;

public class AoASurfaceBuilders {
	public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, AdventOfAscension.MOD_ID);

	public static final RegistryObject<OceanlessSurfaceBuilder> OCEANLESS = register("oceanless", () -> new OceanlessSurfaceBuilder(OceanlessSurfaceBuilder.Config.CODEC));
	public static final RegistryObject<TieredSurfaceBuilder> TIERED = register("tiered", () -> new TieredSurfaceBuilder(TieredSurfaceBuilder.Config.CODEC));
	public static final RegistryObject<MirroredSurfaceBuilder> MIRRORED = register("mirrored", () -> new MirroredSurfaceBuilder(MirroredSurfaceBuilder.Config.CODEC));

	private static <C extends ISurfaceBuilderConfig, B extends SurfaceBuilder<C>> RegistryObject<B> register(String id, Supplier<B> surfaceBuilder) {
		return SURFACE_BUILDERS.register(id, surfaceBuilder);
	}
}
