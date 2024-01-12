package net.tslat.aoa3.content.world.teleporter.specific;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.portal.PortalBlock;
import net.tslat.aoa3.content.world.teleporter.AoATeleporter;

import java.util.HashMap;

public class LBoreanTeleporter extends AoATeleporter {
	private static final HashMap<Long, BlockPos> cachedPortalMap = new HashMap<Long, BlockPos>();

	@Override
	public HashMap<Long, BlockPos> getCachedPortalMap() {
		return cachedPortalMap;
	}

	@Override
	public PortalBlock getPortalBlock() {
		return (PortalBlock)AoABlocks.LBOREAN_PORTAL.get();
	}

	@Override
	public void makePortalPlatformAndDecorate(Level level, BlockPos pos, Direction.Axis direction) {
		BlockState border = getBorderBlock().defaultBlockState();
		BlockState air = Blocks.AIR.defaultBlockState();
		Vec3 centerPos = Vec3.atBottomCenterOf(pos.above(4));

		for (int x = -4; x <= 4; x++) {
			for (int y = 7; y >= 0; y--) {
				for (int z = -4; z <= 4; z++) {
					BlockPos placePos = pos.offset(x, y, z);
					double dist = Vec3.atCenterOf(placePos).distanceToSqr(centerPos);

					if (dist <= 4 * 4 && level.isInWorldBounds(placePos)) {
						BlockState state = level.getBlockState(placePos);

						if (!state.is(BlockTags.PORTALS) && !state.is(border.getBlock()))
							level.setBlockAndUpdate(placePos, dist <= 3 * 3 ? air : border);
					}
				}
			}
		}
	}

	@Override
	public Block getBorderBlock() {
		return Blocks.BUBBLE_CORAL_BLOCK;
	}
}
