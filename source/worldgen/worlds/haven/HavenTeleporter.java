package net.tslat.aoa3.worldgen.worlds.haven;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.library.misc.AoATeleporter;

import java.util.HashMap;

public class HavenTeleporter extends AoATeleporter {
	private static final HashMap<Long, BlockPos> cachedPortalMap = new HashMap<Long, BlockPos>();

	@Override
	public HashMap<Long, BlockPos> getCachedPortalMap() {
		return cachedPortalMap;
	}

	@Override
	public PortalBlock getPortalBlock() {
		return (PortalBlock)AoABlocks.HAVEN_PORTAL.get();
	}

	@Override
	public Block getBorderBlock() {
		return AoABlocks.TWINKLESTONE.get();
	}
}
