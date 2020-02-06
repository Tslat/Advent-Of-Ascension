package net.tslat.aoa3.dimension;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ITeleporter;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.library.misc.PortalCoordinatesContainer;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.HashMap;

public abstract class AoATeleporter implements ITeleporter {
	protected final WorldServer fromWorld;

	public AoATeleporter(WorldServer fromWorld) {
		this.fromWorld = fromWorld;
	}

	@Override
	public void placeEntity(World world, Entity entity, float yaw) {
		PlayerDataManager plData = null;

		if (entity.hasCapability(AdventPlayerProvider.ADVENT_PLAYER, null)) {
			entity.setNoGravity(false);
			PortalCoordinatesContainer loc = (plData = PlayerUtil.getAdventPlayer((EntityPlayer)entity)).getPortalReturnLocation(world.provider.getDimension());
			BlockPos locPos;

			if (loc != null) {
				Block locationBlock = world.getBlockState(locPos = loc.asBlockPos()).getBlock();

				if (locationBlock == getPortalBlock()) {
					placeInPortal(world, entity, locPos);

					return;
				}
				else if (!(locationBlock instanceof PortalBlock)) {
					plData.removePortalReturnLocation(world.provider.getDimension());
				}
			}
		}

		double movementFactor = world.provider.getMovementFactor() / fromWorld.provider.getMovementFactor();

		if (movementFactor != 1)
			entity.setPositionAndUpdate(entity.posX * movementFactor, entity.posY, entity.posZ * movementFactor);

		if (fromWorld.getBlockState(entity.getPosition()).getBlock() == getPortalBlock()) {
			ChunkPos chunkPos = fromWorld.getChunk(entity.getPosition()).getPos();

			getCachedPortalMap().put(ChunkPos.asLong(chunkPos.x, chunkPos.z), entity.getPosition());
		}

		BlockPos pos = findExistingPortal(world, entity);

		if (pos == null) {
			pos = findSuitablePortalLocation(world, entity);
			pos = makePortal(world, entity, pos);

		}

		placeInPortal(world, entity, pos);

		ChunkPos chunkPos = world.getChunk(pos).getPos();

		getCachedPortalMap().put(ChunkPos.asLong(chunkPos.x, chunkPos.z), pos);

		if (plData != null) {
			PortalCoordinatesContainer portalLoc = plData.getPortalReturnLocation(world.provider.getDimension());

			if (portalLoc != null) {
				PortalCoordinatesContainer returnPortalLoc = plData.getPortalReturnLocation(entity.world.provider.getDimension());

				if (returnPortalLoc != null && returnPortalLoc.fromDim == world.provider.getDimension())
					return;
			}

			if (portalLoc == null || entity.world.provider.getDimension() == portalLoc.fromDim || entity.getDistanceSq(portalLoc.asBlockPos()) > ConfigurationUtil.MainConfig.portalSearchRadius)
				plData.setPortalReturnLocation(world.provider.getDimension(), new PortalCoordinatesContainer(entity.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ()));
		}
	}

	public BlockPos findExistingPortal(World world, Entity entity) {
		int posX = (int)Math.floor(entity.posX);
		int posY = (int)Math.floor(entity.posY);
		int posZ = (int)Math.floor(entity.posZ);
		ChunkPos chunkPos = world.getChunk(new BlockPos(posX, posY, posZ)).getPos();
		Long chunkPosLong = ChunkPos.asLong(chunkPos.x, chunkPos.z);
		HashMap<Long, BlockPos> cachedPortalMap = getCachedPortalMap();

		if (cachedPortalMap.containsKey(chunkPosLong)) {
			BlockPos pos = cachedPortalMap.get(chunkPosLong);

			if (world.getBlockState(pos).getBlock() == getPortalBlock()) {
				return cachedPortalMap.get(chunkPosLong);
			}
			else {
				cachedPortalMap.remove(chunkPosLong);
			}
		}

		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
		int searchRadius = ConfigurationUtil.MainConfig.portalSearchRadius;

		for (int x = posX - searchRadius; x <= posX + searchRadius; ++x) {
			for (int z = posZ - searchRadius; z <= posZ + searchRadius; ++z) {
				for (int y = posY - searchRadius; y <= posY + searchRadius; ++y) {
					checkPos.setPos(x, y, z);

					if (world.getBlockState(checkPos).getBlock() == getPortalBlock()) {
						while (world.getBlockState(checkPos.move(EnumFacing.DOWN)).getBlock() == getPortalBlock()) {
							;
						}

						Chunk chunk = world.getChunk(checkPos.move(EnumFacing.UP));

						cachedPortalMap.put(ChunkPos.asLong(chunk.x, chunk.z), checkPos);

						return checkPos;
					}
				}
			}
		}

		return null;
	}

	public BlockPos findSuitablePortalLocation(World world, Entity entity) {
		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
		BlockPos pos = entity.getPosition();
		BlockPos.MutableBlockPos planBPos = new BlockPos.MutableBlockPos(entity.getPosition());
		double planBDistance = 10000;
		int searchRadius = ConfigurationUtil.MainConfig.portalSearchRadius;
		int worldHeight = world.getActualHeight();


		for (int x = pos.getX() - searchRadius; x <= pos.getX() + searchRadius; x++) {
			for (int z = pos.getZ() - searchRadius; z <= pos.getZ() + searchRadius; z++) {

				heightCheck:
				for (int y = worldHeight - 6; y > 0; y--) {
					if (world.isAirBlock(checkPos.setPos(x, y, z))) {
						while (y > 0 && world.isAirBlock(checkPos.move(EnumFacing.DOWN))) {
							y--;
						}

						int y2 = y;

						for (y2 += 7; y2 >= y + 1; y2--) {
							for (int j = x - 2; j <= x + 2; j++) {
								for (int k = z - 2; k <= z + 2; k++) {
									if (!world.isAirBlock(checkPos.setPos(j, y2, k)))
										continue heightCheck;
								}
							}
						}

						double distance = checkPos.setPos(x, y, z).distanceSq(pos);

						if (distance < planBDistance) {
							planBPos.setPos(checkPos);

							planBDistance = distance;
						}

						for (int j = x - 2; j <= x + 2; j++) {
							for (int k = z - 2; k <= z + 2; k++) {
								if (!world.getBlockState(checkPos.setPos(j, y2 - 1, k)).getMaterial().isSolid())
									continue heightCheck;
							}
						}

						return checkPos.up(2).toImmutable();
					}
				}
			}
		}

		if (planBPos.compareTo(pos) == 0) {
			if (worldHeight < 256) {

				heightCheck:
				for (int y = 0; y < worldHeight - 6; y++) {
					if (world.isAirBlock(checkPos.setPos(pos.getX(), y, pos.getZ()))) {
						for (int y2 = y + 7; y2 >= y + 1; y2--) {
							for (int j = pos.getX() - 2; j <= pos.getX() + 2; j++) {
								for (int k = pos.getZ() - 2; k <= pos.getZ() + 2; k++) {
									if (!world.isAirBlock(checkPos.setPos(j, y2, k))) {
										y += 7;

										continue heightCheck;
									}
								}
							}
						}

						return checkPos.up(2);
					}
				}
			}

			return world.getTopSolidOrLiquidBlock(pos).up(2);
		}
		else {
			return planBPos.up(2).toImmutable();
		}
	}

	public BlockPos makePortal(World world, Entity entity, BlockPos pos) {
		if (world.provider.getDimension() == 0)
			return pos;

		BlockPos returnPos = pos;
		pos = pos.down();
		IBlockState border = getBorderBlock().getDefaultState();
		IBlockState portal = getPortalBlock().getDefaultState();
		EnumFacing.Axis direction = EntityUtil.getDirectionFacing(entity, true).getAxis();

		if (direction == EnumFacing.Axis.X) {
			portal = portal.withProperty(BlockHorizontal.FACING, EnumFacing.EAST);

			for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++) {
				world.setBlockState(new BlockPos(pos.getX(), pos.getY(), z), border);
				world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 5, z), border);
			}

			for (int y = pos.getY() + 1; y <= pos.getY() + 4; y++) {
				world.setBlockState(new BlockPos(pos.getX(), y, pos.getZ() - 2), border);
				world.setBlockState(new BlockPos(pos.getX(), y, pos.getZ() - 1), portal);
				world.setBlockState(new BlockPos(pos.getX(), y, pos.getZ()), portal);
				world.setBlockState(new BlockPos(pos.getX(), y, pos.getZ() + 1), portal);
				world.setBlockState(new BlockPos(pos.getX(), y, pos.getZ() + 2), border);
			}
		}
		else {
			portal = portal.withProperty(BlockHorizontal.FACING, EnumFacing.NORTH);

			for (int x = pos.getX() - 2; x <= pos.getX() + 2; x++) {
				world.setBlockState(new BlockPos(x, pos.getY(), pos.getZ()), border);
				world.setBlockState(new BlockPos(x, pos.getY() + 5, pos.getZ()), border);
			}

			for (int y = pos.getY() + 1; y <= pos.getY() + 4; y++) {
				world.setBlockState(new BlockPos(pos.getX() - 2, y, pos.getZ()), border);
				world.setBlockState(new BlockPos(pos.getX() - 1, y, pos.getZ()), portal);
				world.setBlockState(new BlockPos(pos.getX(), y, pos.getZ()), portal);
				world.setBlockState(new BlockPos(pos.getX() + 1, y, pos.getZ()), portal);
				world.setBlockState(new BlockPos(pos.getX() + 2, y, pos.getZ()), border);
			}
		}

		if (!world.getBlockState(pos = pos.down()).isOpaqueCube())
			makePortalPlatformAndDecorate(world, pos, direction);

		return returnPos;
	}

	public void makePortalPlatformAndDecorate(World world, BlockPos pos, EnumFacing.Axis direction) {
		IBlockState border = getBorderBlock().getDefaultState();

		for (int x = pos.getX() - 2; x <= pos.getX() + 2; x++) {
			for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++) {
				world.setBlockState(new BlockPos(x, pos.getY(), z), border);
			}
		}
	}

	public void placeInPortal(World world, Entity entity, BlockPos pos) {
		entity.motionX = 0;
		entity.motionY = 0;
		entity.motionZ = 0;

		if (entity instanceof EntityPlayerMP) {
			((EntityPlayerMP)entity).connection.setPlayerLocation(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, entity.rotationYaw, entity.rotationPitch);
		}
		else {
			entity.setLocationAndAngles(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, entity.rotationYaw, entity.rotationPitch);
		}
	}

	public abstract HashMap<Long, BlockPos> getCachedPortalMap();

	public abstract PortalBlock getPortalBlock();

	public abstract Block getBorderBlock();

	@Override
	public boolean isVanilla() {
		return false;
	}
}
