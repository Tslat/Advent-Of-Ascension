package net.tslat.aoa3.client;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.model.DynamicFluidContainerModel;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.block.AoABlocks;

import java.util.function.Function;

public final class AoATintHandling {
	public static void init() {
		IEventBus modBus = AdventOfAscension.getModEventBus();

		modBus.addListener(AoATintHandling::registerBlockColours);
		modBus.addListener(AoATintHandling::registerItemColours);
	}

	private static void registerBlockColours(final RegisterColorHandlersEvent.Block ev) {
		final BlockColors blockColors = ev.getBlockColors();
		final Function<BlockState, BlockColor> vanillaColouring = referenceBlock -> (state, level, pos, tintIndex) -> blockColors.getColor(referenceBlock, level, pos, tintIndex);

		ev.register(vanillaColouring.apply(Blocks.GRASS_BLOCK.defaultBlockState()), AoABlocks.PRECASIAN_GRASS.get(), AoABlocks.CALAB_GRASS.get(), AoABlocks.CALAB_LONG_GRASS.get());
		ev.register(vanillaColouring.apply(Blocks.ACACIA_LEAVES.defaultBlockState()), AoABlocks.BAOBAB_LEAVES.get(), AoABlocks.LUCALUS_LEAVES.get(), AoABlocks.STRANGLEWOOD_LEAVES.get(), AoABlocks.STRANGLEWOOD_LOG.get(), AoABlocks.STRANGLEWOOD.get());
		ev.register((state, level, pos, tintIndex) -> {
            if (level == null || pos == null)
                return FoliageColor.getEvergreenColor();

            final float desaturation = level.getRawBrightness(pos, 0) * 0.04f;

            return FoliageColor.get(0.1f + desaturation, 1.3f - desaturation * 2f);
        }, AoABlocks.ANCIENT_VINES.get());
	}

	private static void registerItemColours(final RegisterColorHandlersEvent.Item ev) {
		final ItemColors itemColors = ev.getItemColors();
		final Function<ItemStack, ItemColor> vanillaColouring = referenceStack -> (stack, tintIndex) -> itemColors.getColor(referenceStack, tintIndex);

		ev.register(vanillaColouring.apply(Items.GRASS_BLOCK.getDefaultInstance()), AoABlocks.PRECASIAN_GRASS.get(), AoABlocks.CALAB_GRASS.get(), AoABlocks.CALAB_LONG_GRASS.get());
		ev.register(vanillaColouring.apply(Items.ACACIA_LEAVES.getDefaultInstance()), AoABlocks.BAOBAB_LEAVES.get(), AoABlocks.LUCALUS_LEAVES.get(), AoABlocks.STRANGLEWOOD_LEAVES.get(), AoABlocks.STRANGLEWOOD_LOG.get(), AoABlocks.STRANGLEWOOD.get());

		ev.register(new DynamicFluidContainerModel.Colors(), AoABlocks.CANDIED_WATER.getBucket());
		ev.register(new DynamicFluidContainerModel.Colors(), AoABlocks.TOXIC_WASTE.getBucket());
		ev.register(new DynamicFluidContainerModel.Colors(), AoABlocks.TAR.getBucket());
	}
}
