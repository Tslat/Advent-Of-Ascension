package net.tslat.aoa3.world.teleporter.specific;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.world.teleporter.AoATeleporter;
import net.tslat.aoa3.world.teleporter.PortalCoordinatesContainer;

import java.util.HashMap;

public class NowhereTeleporter extends AoATeleporter {
	private static final HashMap<Long, BlockPos> cachedPortalMap = new HashMap<Long, BlockPos>();

	@Override
	public HashMap<Long, BlockPos> getCachedPortalMap() {
		return cachedPortalMap;
	}

	@Override
	public BlockPos findExistingPortal(World world, Entity entity) {
		if (WorldUtil.isWorld(world, AoADimensions.NOWHERE.key)) {
			if (entity instanceof ServerPlayerEntity) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity);

				PortalCoordinatesContainer portalLoc = new PortalCoordinatesContainer(entity.level.dimension(), entity.getX(), entity.getY(), entity.getZ());

				plData.setPortalReturnLocation(world.dimension(), portalLoc);
			}

			return new BlockPos(0, 202, 0);
		}

		return super.findExistingPortal(world, entity);
	}

	@Override
	public BlockPos makePortal(World world, Entity entity, BlockPos pos) {
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
