package net.tslat.aoa3.world.teleporter.specific;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.object.block.functional.portal.PortalBlock;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.world.teleporter.AoATeleporter;

import java.util.HashMap;

public class ShyrelandsTeleporter extends AoATeleporter {
	private static final HashMap<Long, BlockPos> cachedPortalMap = new HashMap<Long, BlockPos>();
	@Override
	public HashMap<Long, BlockPos> getCachedPortalMap() {
		return cachedPortalMap;
	}

	@Override
	public PortalBlock getPortalBlock() {
		return (PortalBlock)AoABlocks.SHYRELANDS_PORTAL.get();
	}

	@Override
	public Block getBorderBlock() {
		return AoABlocks.YELLOW_SHYRE_BRICKS.get();
	}
}
