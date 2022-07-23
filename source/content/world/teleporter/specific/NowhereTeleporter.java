package net.tslat.aoa3.content.world.teleporter.specific;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.functional.portal.PortalBlock;
import net.tslat.aoa3.content.world.teleporter.AoATeleporter;
import net.tslat.aoa3.content.world.teleporter.PortalCoordinatesContainer;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.HashMap;

public class NowhereTeleporter extends AoATeleporter {
	private static final HashMap<Long, BlockPos> cachedPortalMap = new HashMap<Long, BlockPos>();

	@Override
	public HashMap<Long, BlockPos> getCachedPortalMap() {
		return cachedPortalMap;
	}

	@Override
	public BlockPos findExistingPortal(Level world, Entity entity) {
		if (WorldUtil.isWorld(world, AoADimensions.NOWHERE.key)) {
			if (entity instanceof ServerPlayer) {
				ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayer)entity);

				PortalCoordinatesContainer portalLoc = new PortalCoordinatesContainer(entity.level.dimension(), entity.getX(), entity.getY(), entity.getZ());

				plData.setPortalReturnLocation(world.dimension(), portalLoc);
			}

			return new BlockPos(24, 1502, 16);
		}

		return super.findExistingPortal(world, entity);
	}

	@Override
	public BlockPos makePortal(Level world, Entity entity, BlockPos pos) {
		return pos;
	}

	@Override
	public PortalBlock getPortalBlock() {
		return (PortalBlock)AoABlocks.NOWHERE_PORTAL.get();
	}

	@Override
	public Block getBorderBlock() {
		return AoABlocks.ANCIENT_ROCK.get();
	}
}
