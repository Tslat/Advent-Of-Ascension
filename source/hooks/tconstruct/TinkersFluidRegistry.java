package net.tslat.aoa3.hooks.tconstruct;

import net.minecraftforge.fluids.FluidRegistry;
import slimeknights.tconstruct.library.fluid.FluidColored;
import slimeknights.tconstruct.library.fluid.FluidMolten;

public class TinkersFluidRegistry {
	public static final FluidMolten BARONYTE = moltenFluid("baronyte", 11935776, 880);
	public static final FluidMolten BLAZIUM = moltenFluid("blazium", 10892288, 1080);
	public static final FluidMolten ELECANIUM = moltenFluid("elecanium", 7993299, 970);
	public static final FluidMolten EMBERSTONE = moltenFluid("emberstone", 9568256, 1100);
	public static final FluidMolten GHASTLY = moltenFluid("ghastly", 12178601, 690);
	public static final FluidMolten GHOULISH = moltenFluid("ghoulish", 10385663, 680);
	public static final FluidMolten LIMONITE = moltenFluid("limonite", 14387242, 650);
	public static final FluidMolten LUNAR = moltenFluid("lunar", 16360911, 805);
	public static final FluidMolten LYON = moltenFluid("lyon", 10381572, 820);
	public static final FluidMolten MYSTITE = moltenFluid("mystite", 12905684, 730);
	public static final FluidMolten ROSITE = moltenFluid("rosite", 14301764, 700);
	public static final FluidMolten SHYRESTONE = moltenFluid("shyrestone", 39932, 660);
	public static final FluidMolten SKELETAL = moltenFluid("skeletal", 12437920, 660);
	public static final FluidMolten VARSIUM = moltenFluid("varsium", 12615725, 750);

	public static final FluidColored CHARGER = fluid("charger", 16770304, 450);

	private static FluidMolten moltenFluid(String name, int colour, int temperature) {
		FluidMolten fluid = new FluidMolten(name, colour);

		fluid.setUnlocalizedName("aoa3." + name);
		fluid.setTemperature(temperature);
		net.minecraftforge.fluids.FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);

		return fluid;
	}

	private static FluidColored fluid(String name, int colour, int temperature) {
		FluidColored fluid = new FluidColored(name, colour);

		fluid.setUnlocalizedName("aoa3." + name);
		fluid.setTemperature(temperature);
		net.minecraftforge.fluids.FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);

		return fluid;
	}
}
