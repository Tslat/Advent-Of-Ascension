package net.tslat.aoa3.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class BasicFluid extends Fluid {
	private static final ResourceLocation DEFAULT_STILL = new ResourceLocation("aoa3", "blocks/functional/liquids/liquid_water_still");
	private static final ResourceLocation DEFAULT_FLOWING = new ResourceLocation("aoa3", "blocks/functional/liquids/liquid_water_flowing");
	private static final ResourceLocation DEFAULT_OVERLAY = new ResourceLocation("minecraft", "blocks/water_overlay");

	public BasicFluid(String name, int liquidColour, int viscosity, int density) {
		this(name, liquidColour, 1, viscosity, density);
	}

	public BasicFluid(String name, int liquidColour, float opacity, int viscosity, int density) {
		super(name, DEFAULT_STILL, DEFAULT_FLOWING, DEFAULT_OVERLAY, liquidColour);

		setUnlocalizedName("aoa3." + name);
		setViscosity(viscosity);
		setDensity(density);

		if(((color >> 24) & 0xFF) == 0)
			color |= ((int)(255 * opacity) & 0xFF) << 24;
	}

	@Override
	public String getLocalizedName(FluidStack stack) {
		String unlocalisedName = getUnlocalizedName();

		return unlocalisedName == null ? "" : I18n.translateToLocal(unlocalisedName + ".name");
	}
}
