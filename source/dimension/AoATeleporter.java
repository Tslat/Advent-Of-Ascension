package net.tslat.aoa3.dimension;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ITeleporter;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.library.PortalCoordinatesContainer;
import net.tslat.aoa3.utils.EntityUtil;

import java.util.HashMap;

public abstract class AoATeleporter implements ITeleporter {
	protected final WorldServer fromWorld;

	protected static final int searchRadius = 64;

	public AoATeleporter(WorldServer fromWorld) {
		this.fromWorld = fromWorld;
	}

	@Override
	public void placeEntity(World world, Entity entity, float yaw) {
		AdventPlayerCapability cap = null;

		if (entity.hasCapability(AdventPlayerProvider.ADVENT_PLAYER, null)) {
			PortalCoordinatesContainer loc = (cap = (AdventPlayerCapability)entity.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null)).getPortalReturnLocation(world.provider.getDimension());
			BlockPos locPos;

			if (loc != null && world.getBlockState(locPos = loc.asBlockPos()).getBlock() == getPortalBlock()) {
				placeInPortal(world, entity, locPos);

				PortalCoordinatesContainer newLoc = new PortalCoordinatesContainer(entity.world.provider.getDimension(), locPos.getX(), locPos.getY(), locPos.getZ());

				cap.setPortalReturnLocation(world.provider.getDimension(), newLoc);

				return;
			}
		}

		BlockPos pos = findExistingPortal(world, entity);

		if (pos != null) {
			placeInPortal(world, entity, pos);
		}
		else {
			pos = findSuitablePortalLocation(world, entity);
			pos = makePortal(world, entity, pos);
			placeInPortal(world, entity, pos);

			ChunkPos chunkPos = world.getChunkFromBlockCoords(pos).getPos();

			getCachedPortalMap().put(ChunkPos.asLong(chunkPos.x, chunkPos.z), pos);

			if (cap != null) {
				PortalCoordinatesContainer portalLoc = new PortalCoordinatesContainer(entity.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ());
				cap.setPortalReturnLocation(world.provider.getDimension(), portalLoc);
			}
		}
	}

	public BlockPos findExistingPortal(World world, Entity entity) {
		int posX = (int)Math.floor(entity.posX);
		int posY = (int)Math.floor(entity.posY);
		int posZ = (int)Math.floor(entity.posZ);
		ChunkPos chunkPos = world.getChunkFromBlockCoords(new BlockPos(posX, posY, posZ)).getPos();
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

		for (int x = posX - searchRadius; x <= posX + searchRadius; ++x) {
			for (int z = posZ - searchRadius; z <= posZ + searchRadius; ++z) {
				for (int y = posY - searchRadius; y <= posY + searchRadius; ++y) {
					checkPos.setPos(x, y, z);

					if (world.getBlockState(checkPos).getBlock() == getPortalBlock()) {
						while (world.getBlockState(checkPos.move(EnumFacing.DOWN)).getBlock() == getPortalBlock()) {
							;
						}

						Chunk chunk = world.getChunkFromBlockCoords(checkPos.move(EnumFacing.UP));

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

		for (int x = pos.getX() - (searchRadius / 4); x <= pos.getX() + (searchRadius / 4); x++) {
			for (int z = pos.getZ() - (searchRadius / 4); z <= pos.getZ() + (searchRadius / 4); z++) {

				heightCheck:
				for (int y = world.getActualHeight() - 6; y > 0; y--) {
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

			//returnPos = returnPos.south();
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

			//returnPos = returnPos.east();
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
