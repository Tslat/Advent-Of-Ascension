package net.tslat.aoa3.content.world.teleporter.specific;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.portal.PortalBlock;
import net.tslat.aoa3.content.world.teleporter.AoATeleporter;

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
	public BlockPos findExistingPortal(Level world, Entity entity) {
		return super.findExistingPortal(world, entity);
	}

	@Override
	public BlockPos findSuitablePortalLocation(Level world, Entity entity) {
		return super.findSuitablePortalLocation(world, entity);
	}
}
