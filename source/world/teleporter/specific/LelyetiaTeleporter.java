package net.tslat.aoa3.world.teleporter.specific;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.object.block.functional.portal.PortalBlock;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.world.teleporter.AoATeleporter;

import java.util.HashMap;

public class LelyetiaTeleporter extends AoATeleporter {
	private static final HashMap<Long, BlockPos> cachedPortalMap = new HashMap<Long, BlockPos>();

	@Override
	public HashMap<Long, BlockPos> getCachedPortalMap() {
		return cachedPortalMap;
	}

	@Override
	public PortalBlock getPortalBlock() {
		return (PortalBlock)AoABlocks.LELYETIA_PORTAL.get();
	}

	@Override
	public Block getBorderBlock() {
		return AoABlocks.LELYETIAN_BRICKS.get();
	}

	@Override
	public BlockPos findExistingPortal(World world, Entity entity) {
		return super.findExistingPortal(world, entity);
	}

	@Override
	public BlockPos findSuitablePortalLocation(World world, Entity entity) {
		return super.findSuitablePortalLocation(world, entity);
	}
}
