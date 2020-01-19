package net.tslat.aoa3.common.registration;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.fluid.CandiedWater;
import org.apache.logging.log4j.Level;

public final class FluidsRegister {
	public static final CandiedWater candiedWater = new CandiedWater();

	public static void registerFluids() {
		AdventOfAscension.logMessage(Level.INFO, "Beginning fluid registration");

		registerFluids(
				candiedWater
		);
	}

	private static void registerFluids(Fluid... fluids) {
		for (Fluid fluid : fluids) {
			registerFluid(fluid);
		}
	}

	private static void registerFluid(Fluid fluid) {
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);
	}
}
