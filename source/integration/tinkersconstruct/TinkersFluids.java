package net.tslat.aoa3.integration.tinkersconstruct;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.util.FluidUtil;

import java.util.function.Consumer;

public final class TinkersFluids {
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_BARONYTE = registerMoltenFluid("molten_baronyte", builder -> builder.colour(182, 32, 32, 250).temperature(880).luminosity(10).density(2000).viscosity(10000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_BLAZIUM = registerMoltenFluid("molten_blazium", builder -> builder.colour(166, 52, 0, 250).temperature(1080).luminosity(11).density(2000).viscosity(10000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_ELECANIUM = registerMoltenFluid("molten_elecanium", builder -> builder.colour(121, 247, 211, 250).temperature(970).luminosity(12).density(2000).viscosity(10000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_EMBERSTONE = registerMoltenFluid("molten_emberstone", builder -> builder.colour(146, 0, 0, 250).temperature(1100).luminosity(12).density(2000).viscosity(10000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_GHASTLY = registerMoltenFluid("molten_ghastly", builder -> builder.colour(185, 212, 169, 250).temperature(690).luminosity(10).density(2000).viscosity(10000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_GHOULISH = registerMoltenFluid("molten_ghoulish", builder -> builder.colour(185, 212, 169, 250).temperature(680).luminosity(10).density(2000).viscosity(10000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_LIMONITE = registerMoltenFluid("molten_limonite", builder -> builder.colour(219, 136, 42, 250).temperature(650).luminosity(8).density(2000).viscosity(10000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_LUNAR = registerMoltenFluid("molten_lunar", builder -> builder.colour(249, 165, 207, 250).temperature(805).luminosity(10).density(2000).viscosity(10000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_LYON = registerMoltenFluid("molten_lyon", builder -> builder.colour(158, 105, 4, 250).temperature(820).luminosity(10).density(2000).viscosity(10000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_MYSTITE = registerMoltenFluid("molten_mystite", builder -> builder.colour(196, 236, 212, 250).temperature(730).luminosity(10).density(2000).viscosity(10000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_ROSITE = registerMoltenFluid("molten_rosite", builder -> builder.colour(218, 58, 68, 250).temperature(700).luminosity(9).density(2000).viscosity(10000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_SHYRESTONE = registerMoltenFluid("molten_shyrestone", builder -> builder.colour(0, 155, 232, 250).temperature(660).luminosity(15).density(2000).viscosity(10000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_SKELETAL = registerMoltenFluid("molten_skeletal", builder -> builder.colour(189, 201, 160, 250).temperature(660).luminosity(7).density(1700).viscosity(9000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_VARSIUM = registerMoltenFluid("molten_varsium", builder -> builder.colour(192, 128, 45, 255).temperature(750).luminosity(10).density(2000).viscosity(10000));
	public static final FluidUtil.RegisteredFluidHolder MOLTEN_CHARGER = registerFluid("molten_charger", builder -> builder.colour(255, 229, 0, 200).temperature(450).luminosity(5).density(1400).viscosity(8000));

	public static void preInit() {}

	private static FluidUtil.RegisteredFluidHolder registerMoltenFluid(String id, Consumer<FluidUtil.Builder> fluidHandler) {
		FluidUtil.Builder fluidBuilder = new FluidUtil.Builder(id).isMolten();

		if (!IntegrationManager.isTinkersConstructActive())
			return registerDummyFluid(fluidHandler, fluidBuilder);

		fluidBuilder.stillTexture(new ResourceLocation("tconstruct", "block/fluid/molten/still")).flowingTexture(new ResourceLocation("tconstruct", "block/fluid/molten/flowing"));
		fluidHandler.accept(fluidBuilder);

		return fluidBuilder.registerAll(AoAItems.ITEMS, AoABlocks.BLOCKS, AoABlocks.FLUIDS);
	}

	private static FluidUtil.RegisteredFluidHolder registerFluid(String id, Consumer<FluidUtil.Builder> fluidHandler) {
		FluidUtil.Builder fluidBuilder = new FluidUtil.Builder(id);

		if (!IntegrationManager.isTinkersConstructActive())
			return registerDummyFluid(fluidHandler, fluidBuilder);

		fluidHandler.accept(fluidBuilder);

		return fluidBuilder.registerAll(AoAItems.ITEMS, AoABlocks.BLOCKS, AoABlocks.FLUIDS);
	}

	private static FluidUtil.RegisteredFluidHolder registerDummyFluid(Consumer<FluidUtil.Builder> fluidHandler, FluidUtil.Builder fluidBuilder) {
		fluidHandler.accept(fluidBuilder);

		return new FluidUtil.RegisteredFluidHolder(null, null, fluidBuilder.registerFluid(AoABlocks.FLUIDS));
	}
}
