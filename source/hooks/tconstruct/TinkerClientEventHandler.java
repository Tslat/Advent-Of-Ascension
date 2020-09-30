package net.tslat.aoa3.hooks.tconstruct;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tslat.aoa3.block.BasicFluidBlock;
import net.tslat.aoa3.hooks.tconstruct.traits.Traits;
import net.tslat.aoa3.utils.ConfigurationUtil;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.modifiers.IModifier;

public class TinkerClientEventHandler {
	@SubscribeEvent
	public void modelRegistration(final ModelRegistryEvent ev) {
		registerFluidModels(
				TinkersFluidRegistry.BARONYTE,
				TinkersFluidRegistry.BLAZIUM,
				TinkersFluidRegistry.CHARGER,
				TinkersFluidRegistry.ELECANIUM,
				TinkersFluidRegistry.EMBERSTONE,
				TinkersFluidRegistry.GHASTLY,
				TinkersFluidRegistry.GHOULISH,
				TinkersFluidRegistry.LIMONITE,
				TinkersFluidRegistry.LUNAR,
				TinkersFluidRegistry.LYON,
				TinkersFluidRegistry.MYSTITE,
				TinkersFluidRegistry.ROSITE,
				TinkersFluidRegistry.SHYRESTONE,
				TinkersFluidRegistry.SKELETAL,
				TinkersFluidRegistry.VARSIUM
		);

		if (ConfigurationUtil.IntegrationsConfig.tinkersConstruct.modifiers) {
			registerModifierModels(Traits.AIR_BLADE,
					Traits.BUTCHERER,
					Traits.CREEPIFIED,
					Traits.MONEYBAGS,
					Traits.REFREEZING,
					Traits.SURPRISE_ME);
		}
	}

	private static void registerModifierModels(IModifier... modifiers) {
		for (IModifier modifier : modifiers) {
			ModelRegisterUtil.registerModifierModel(modifier, new ResourceLocation("aoa3", "models/item/tconstruct/modifiers/" + modifier.getIdentifier()));
		}
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
