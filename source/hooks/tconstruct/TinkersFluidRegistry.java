package net.tslat.aoa3.hooks.tconstruct;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.tslat.aoa3.utils.ModUtil;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.fluid.FluidMolten;

public class TinkersFluidRegistry {
	public static final FluidMolten baronyte = moltenFluid("baronyte", 11935776, 880);
	public static final FluidMolten blazium = moltenFluid("blazium", 10892288, 1080);
	public static final FluidMolten elecanium = moltenFluid("elecanium", 7993299, 970);
	public static final FluidMolten emberstone = moltenFluid("emberstone", 9568256, 1100);
	public static final FluidMolten ghastly = moltenFluid("ghastly", 12178601, 690);
	public static final FluidMolten ghoulish = moltenFluid("ghoulish", 10385663, 680);
	public static final FluidMolten limonite = moltenFluid("limonite", 14387242, 650);
	public static final FluidMolten lunar = moltenFluid("lunar", 16360911, 805);
	public static final FluidMolten lyon = moltenFluid("lyon", 10381572, 820);
	public static final FluidMolten mystite = moltenFluid("mystite", 12905684, 730);
	public static final FluidMolten rosite = moltenFluid("rosite", 14301764, 700);
	public static final FluidMolten skeletal = moltenFluid("skeletal", 12437920, 660);
	public static final FluidMolten varsium = moltenFluid("varsium", 12615725, 750);

	public static void preInit() {
		registerFluids();

		if (ModUtil.isClient())
			MinecraftForge.EVENT_BUS.register(new TinkerCompatEventHandler());
	}

	private static void registerFluids() {
		TinkerRegistry.integrate(integration(baronyte, "Baronyte", true));
		TinkerRegistry.integrate(integration(blazium, "Blazium", true));
		TinkerRegistry.integrate(integration(elecanium, "Elecanium", true));
		TinkerRegistry.integrate(integration(emberstone, "Emberstone", true));
		TinkerRegistry.integrate(integration(ghastly, "Ghastly", true));
		TinkerRegistry.integrate(integration(ghoulish, "Ghoulish", true));
		TinkerRegistry.integrate(integration(limonite, "Limonite", true));
		TinkerRegistry.integrate(integration(lunar, "Lunar", true));
		TinkerRegistry.integrate(integration(lyon, "Lyon", true));
		TinkerRegistry.integrate(integration(mystite, "Mystite", true));
		TinkerRegistry.integrate(integration(rosite, "Rosite", true));
		TinkerRegistry.integrate(integration(skeletal, "Skeletal", true));
		TinkerRegistry.integrate(integration(varsium, "Varsium", true));
	}

	private static MaterialIntegration integration(Fluid fluid, String oreSuffix, boolean toolForgeBlock) {
		MaterialIntegration integration = new MaterialIntegration(null, fluid, oreSuffix);

		if (toolForgeBlock)
			integration.toolforge();

		return integration;
	}

	private static FluidMolten moltenFluid(String name, int colour, int temperature) {
		FluidMolten fluid = new FluidMolten(name, colour);

		fluid.setUnlocalizedName("aoa3." + name);
		fluid.setTemperature(temperature);
		net.minecraftforge.fluids.FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);

		return fluid;
	}
}
