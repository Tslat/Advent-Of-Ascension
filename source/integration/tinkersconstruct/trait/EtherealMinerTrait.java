package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.block.FallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.DebugChunkGenerator;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;
import net.tslat.aoa3.common.registration.AoABlocks;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class EtherealMinerTrait extends Modifier {
	public EtherealMinerTrait() {
		super(0xCFF99F);
	}

	@Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
		BlockPos pos = context.getPos();
		ServerWorld world = context.getWorld();

		if (World.isOutsideBuildHeight(pos) || (world.getChunkSource().generator instanceof DebugChunkGenerator))
			return;

		if (!(world.getBlockState(pos.above()).getBlock() instanceof FallingBlock))
			return;

		world.setBlock(pos, AoABlocks.AIR_GAP.get().defaultBlockState(), Constants.BlockFlags.UPDATE_NEIGHBORS);
	}
}
