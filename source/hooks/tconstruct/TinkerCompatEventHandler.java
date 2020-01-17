package net.tslat.aoa3.hooks.tconstruct;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tslat.aoa3.block.BasicFluidBlock;

public class TinkerCompatEventHandler {
	@SubscribeEvent
	public void modelRegistration(final ModelRegistryEvent ev) {
		registerFluidModels(
				TinkersFluidRegistry.baronyte,
				TinkersFluidRegistry.blazium,
				TinkersFluidRegistry.elecanium,
				TinkersFluidRegistry.emberstone,
				TinkersFluidRegistry.ghastly,
				TinkersFluidRegistry.ghoulish,
				TinkersFluidRegistry.limonite,
				TinkersFluidRegistry.lunar,
				TinkersFluidRegistry.lyon,
				TinkersFluidRegistry.mystite,
				TinkersFluidRegistry.rosite,
				TinkersFluidRegistry.skeletal,
				TinkersFluidRegistry.varsium
		);
	}

	private static void registerFluidModels(Fluid... fluids) {
		for (Fluid fluid : fluids) {
			Block fluidBlock = fluid.getBlock();

			if (fluidBlock != null) {
				Item fluidItem = Item.getItemFromBlock(fluidBlock);
				BasicFluidBlock.FluidStateMapper mapper = new BasicFluidBlock.FluidStateMapper(fluid);

				if (fluidItem != Items.AIR)
					ModelLoader.setCustomMeshDefinition(fluidItem, mapper);

				ModelLoader.setCustomStateMapper(fluidBlock, mapper);
			}
		}
	}
}
