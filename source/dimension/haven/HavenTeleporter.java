package net.tslat.aoa3.dimension.haven;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.dimension.AoATeleporter;

import java.util.HashMap;

public class HavenTeleporter extends AoATeleporter {
	private static final HashMap<Long, BlockPos> cachedPortalMap = new HashMap<Long, BlockPos>();

	public HavenTeleporter(WorldServer world) {
		super(world);
	}

	@Override
	public HashMap<Long, BlockPos> getCachedPortalMap() {
		return cachedPortalMap;
	}

	@Override
	public PortalBlock getPortalBlock() {
		return BlockRegister.HAVEN_PORTAL;
	}

	@Override
	public Block getBorderBlock() {
		return BlockRegister.TWINKLESTONE;
	}
}
