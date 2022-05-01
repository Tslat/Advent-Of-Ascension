package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.levelgen.DebugLevelSource;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;

public class EtherealMinerTrait extends Modifier {
	public EtherealMinerTrait() {
		super();
	}

	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xCFF99F))));
	}

	@Override
	public void afterBlockBreak(IToolStackView tool, int level, ToolHarvestContext context) {
		BlockPos pos = context.getPos();
		ServerLevel world = context.getWorld();

		if (context.getWorld().isOutsideBuildHeight(pos) || (world.getChunkSource().getGenerator() instanceof DebugLevelSource))
			return;

		if (!(world.getBlockState(pos.above()).getBlock() instanceof FallingBlock))
			return;

		world.setBlock(pos, AoABlocks.AIR_GAP.get().defaultBlockState(), Block.UPDATE_NEIGHBORS);
	}
}
