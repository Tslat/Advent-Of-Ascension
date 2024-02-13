package net.tslat.aoa3.content.world.teleporter.specific;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.block.functional.portal.PortalBlock;
import net.tslat.aoa3.content.world.teleporter.AoATeleporter;
import net.tslat.aoa3.content.world.teleporter.PortalCoordinatesContainer;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class NowhereTeleporter extends AoATeleporter {
	private static final HashMap<Long, BlockPos> cachedPortalMap = new HashMap<Long, BlockPos>();

	@Override
	public HashMap<Long, BlockPos> getCachedPortalMap() {
		return cachedPortalMap;
	}

	@Nullable
	@Override
	protected BlockPos findReturnPos(ServerPlayer player, ServerLevel currentWorld, ServerLevel destWorld) {
		if (WorldUtil.isWorld((ServerLevelAccessor)currentWorld, AoADimensions.NOWHERE)) {
			if (player.distanceToSqr(17, 453, 1) < 100) {
				PortalCoordinatesContainer returnLoc = PlayerUtil.getAdventPlayer(player).getPortalReturnLocation(AoADimensions.NOWHERE);

				if (returnLoc != null)
					return returnLoc.asBlockPos();
			}
		}

		return super.findReturnPos(player, currentWorld, destWorld);
	}

	@Override
	public BlockPos findExistingPortal(Level world, Entity entity) {
		if (WorldUtil.isWorld(world, AoADimensions.NOWHERE)) {
			if (entity instanceof ServerPlayer pl)
				PlayerUtil.getAdventPlayer(pl).setPortalReturnLocation(world.dimension(), new PortalCoordinatesContainer(entity.level().dimension(), entity.getX(), entity.getY(), entity.getZ()));

			return new BlockPos(25, 1501, 15);
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
