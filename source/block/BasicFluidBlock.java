package net.tslat.aoa3.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BasicFluidBlock extends BlockFluidClassic {
	public BasicFluidBlock(String name, Fluid fluid, Material material, MapColor mapColor) {
		super(fluid, material, mapColor);

		setRegistryName("aoa3", fluid.getName());
		setTranslationKey(name);
		setLightOpacity(3);
	}

	public FluidStateMapper getFluidStateMap() {
		return new FluidStateMapper(getFluid());
	}

	public static class FluidStateMapper extends StateMapperBase implements ItemMeshDefinition {
		private final ModelResourceLocation resource;

		public FluidStateMapper(Fluid fluid) {
			this.resource = new ModelResourceLocation(new ResourceLocation("aoa3", "basic_fluid_block"), fluid.getName());
		}

		@Override
		public ModelResourceLocation getModelLocation(ItemStack stack) {
			return resource;
		}

		@Override
		protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
			return resource;
		}
	}
}
