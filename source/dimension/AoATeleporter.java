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
		int worldHeight = world.getActualHeight();

		if (posY >= worldHeight)
			posY = 65;

		if (world.getBlockState(checkPos.setPos(posX, posY, posZ)).getBlock() == getPortalBlock()) {
			while (world.getBlockState(checkPos.move(EnumFacing.DOWN)).getBlock() == getPortalBlock()) {
				;
			}

			Chunk chunk = world.getChunk(checkPos.move(EnumFacing.UP));

			cachedPortalMap.put(ChunkPos.asLong(chunk.x, chunk.z), checkPos);

			return checkPos;
		}

		for (int i = 1; i <= searchRadius; i++) {
			for (int y = -i; y <= i; y += i * 2) {
				int y2 = posY + y;

				if (y2 < 0 || y2 >= worldHeight)
					continue;

				int xNeg = -1;

				for (int x = 0; x <= i; x++) {
					int x2 = posX + x * xNeg;

					if (xNeg == 1 && x != 0)
						x--;

					xNeg *= -1;
					int zNeg = -1;

					for (int z = 0; z <= i; z++) {
						int z2 = posZ + z * zNeg;

						if (zNeg == 1 && z != 0)
							z--;

						zNeg *= -1;

						checkPos.setPos(x2, y2, z2);

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

			int yNeg = -1;

			for (int y = 0; y <= i - 1; y++) {
				int y2 = posY + y * yNeg;

				if (y2 < 0 || y2 >= worldHeight)
					continue;

				if (yNeg == 1 && y != 0)
					y--;

				yNeg *= -1;
				int zNeg = -1;

				for (int z = 0; z <= i; z++) {
					int z2 = posZ + z * zNeg;

					if (zNeg == 1 && z != 0)
						z--;

					zNeg *= -1;

					for (int x = -i; x <= i; x += i * 2) {
						int x2 = posX + x;

						checkPos.setPos(x2, y2, z2);

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

				int xNeg = 1;

				for (int x = 1; x <= i - 1; x++) {
					int x2 = posX + x * xNeg;

					if (xNeg == 1 && x != 0)
						x--;

					xNeg *= -1;

					for (int z = -i; z <= i; z += i * 2) {
						int z2 = posZ + z;

						checkPos.setPos(x2, y2, z2);

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
		}

		for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
			for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
				checkPos.setPos(x, worldHeight, z);

				while (world.getBlockState(checkPos.move(EnumFacing.DOWN)).getBlock() != getPortalBlock() && checkPos.getY() >= 0) {
					;
				}

				if (world.getBlockState(checkPos).getBlock() == getPortalBlock()) {
					while (world.getBlockState(checkPos.move(EnumFacing.DOWN)).getBlock() == getPortalBlock()) {
						;
					}

					return checkPos.up(2).toImmutable();
				}
			}
		}

		for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
			for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
				checkPos.setPos(x, posY, z);

				while (world.getBlockState(checkPos.move(EnumFacing.UP)).getBlock() != getPortalBlock() && checkPos.getY() < worldHeight) {
					;
				}

				if (world.getBlockState(checkPos).getBlock() == getPortalBlock())
					return checkPos.up(1).toImmutable();
			}
		}

		return null;
	}

	public BlockPos findSuitablePortalLocation(World world, Entity entity) {
		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
		int posX = (int)Math.floor(entity.posX);
		int posY = (int)Math.floor(entity.posY);
		int posZ = (int)Math.floor(entity.posZ);
		BlockPos planBPos = null;
		int searchRadius = ConfigurationUtil.MainConfig.portalSearchRadius;
		int worldHeight = world.getActualHeight();

		if (posY >= worldHeight)
			posY = 65;

		boolean cleanSpawn = true;

		for (int x = posX - 2; x <= posX + 2 && cleanSpawn; x++) {
			for (int z = posZ - 2; z <= posZ + 2 && cleanSpawn; z++) {
				for (int y = posY + 1; y <= posY + 6 && cleanSpawn; y++) {
					if (!world.isAirBlock(checkPos.setPos(x, y, z)))
						cleanSpawn = false;
				}
			}
		}

		if (cleanSpawn) {
			if (!world.isAirBlock(checkPos.setPos(posX, posY, posZ))) {
				return checkPos.setPos(posX, posY + 2, posZ).toImmutable();
			}
			else {
				planBPos = checkPos.setPos(posX, posY + 2, posZ).toImmutable();
			}
		}

		for (int i = 1; i <= searchRadius; i++) {
			for (int y = -i; y <= i; y += i * 2) {
				int y2 = posY + y;

				if (y2 < 0 || y2 >= worldHeight)
					continue;

				int xNeg = -1;

				for (int x = 0; x <= i; x++) {
					int x2 = posX + x * xNeg;

					if (xNeg == 1 && x != 0)
						x--;

					xNeg *= -1;
					int zNeg = -1;

					for (int z = 0; z <= i; z++) {
						int z2 = posZ + z * zNeg;

						if (zNeg == 1 && z != 0)
							z--;

						zNeg *= -1;

						if (!world.isAirBlock(checkPos.setPos(x2, y2, z2))) {
							cleanSpawn = true;

							for (int x3 = x2 - 2; x3 <= x2 + 2 && cleanSpawn; x3++) {
								for (int z3 = z2 - 2; z3 <= z2 + 2 && cleanSpawn; z3++) {
									for (int y3 = y2 + 1; y3 <= y2 + 6 && cleanSpawn; y3++) {
										if (!world.isAirBlock(checkPos.setPos(x3, y3, z3)))
											cleanSpawn = false;
									}
								}
							}

							if (cleanSpawn)
								return checkPos.setPos(x2, y2 + 2, z2).toImmutable();
						}
						else if (planBPos == null) {
							cleanSpawn = true;

							for (int x3 = x2 - 2; x3 <= x2 + 2 && cleanSpawn; x3++) {
								for (int z3 = z2 - 2; z3 <= z2 + 2 && cleanSpawn; z3++) {
									for (int y3 = y2 + 1; y3 <= y2 + 6 && cleanSpawn; y3++) {
										if (!world.isAirBlock(checkPos.setPos(x3, y3, z3)))
											cleanSpawn = false;
									}
								}
							}

							if (cleanSpawn)
								planBPos = checkPos.setPos(x2, y2 + 2, z2).toImmutable();
						}
					}
				}
			}

			int yNeg = -1;

			for (int y = 0; y <= i - 1; y++) {
				int y2 = posY + y * yNeg;

				if (y2 < 0 || y2 >= worldHeight)
					continue;

				if (yNeg == 1 && y != 0)
					y--;

				yNeg *= -1;
				int zNeg = -1;

				for (int z = 0; z <= i; z++) {
					int z2 = posZ + z * zNeg;

					if (zNeg == 1 && z != 0)
						z--;

					zNeg *= -1;

					for (int x = -i; x <= i; x += i * 2) {
						int x2 = posX + x;

						if (!world.isAirBlock(checkPos.setPos(x2, y2, z2))) {
							cleanSpawn = true;

							for (int x3 = x2 - 2; x3 <= x2 + 2 && cleanSpawn; x3++) {
								for (int z3 = z2 - 2; z3 <= z2 + 2 && cleanSpawn; z3++) {
									for (int y3 = y2 + 1; y3 <= y2 + 6 && cleanSpawn; y3++) {
										if (!world.isAirBlock(checkPos.setPos(x3, y3, z3)))
											cleanSpawn = false;
									}
								}
							}

							if (cleanSpawn)
								return checkPos.setPos(x2, y2 + 2, z2).toImmutable();
						}
						else if (planBPos == null) {
							cleanSpawn = true;

							for (int x3 = x2 - 2; x3 <= x2 + 2 && cleanSpawn; x3++) {
								for (int z3 = z2 - 2; z3 <= z2 + 2 && cleanSpawn; z3++) {
									for (int y3 = y2 + 1; y3 <= y2 + 6 && cleanSpawn; y3++) {
										if (!world.isAirBlock(checkPos.setPos(x3, y3, z3)))
											cleanSpawn = false;
									}
								}
							}

							if (cleanSpawn)
								planBPos = checkPos.setPos(x2, y2 + 2, z2).toImmutable();
						}
					}
				}

				int xNeg = 1;

				for (int x = 1; x <= i - 1; x++) {
					int x2 = posX + x * xNeg;

					if (xNeg == 1 && x != 0)
						x--;

					xNeg *= -1;

					for (int z = -i; z <= i; z += i * 2) {
						int z2 = posZ + z;

						if (!world.isAirBlock(checkPos.setPos(x2, y2, z2))) {
							cleanSpawn = true;

							for (int x3 = x2 - 2; x3 <= x2 + 2 && cleanSpawn; x3++) {
								for (int z3 = z2 - 2; z3 <= z2 + 2 && cleanSpawn; z3++) {
									for (int y3 = y2 + 1; y3 <= y2 + 6 && cleanSpawn; y3++) {
										if (!world.isAirBlock(checkPos.setPos(x3, y3, z3)))
											cleanSpawn = false;
									}
								}
							}

							if (cleanSpawn)
								return checkPos.setPos(x2, y2 + 2, z2).toImmutable();
						}
						else if (planBPos == null) {
							cleanSpawn = true;

							for (int x3 = x2 - 2; x3 <= x2 + 2 && cleanSpawn; x3++) {
								for (int z3 = z2 - 2; z3 <= z2 + 2 && cleanSpawn; z3++) {
									for (int y3 = y2 + 1; y3 <= y2 + 6 && cleanSpawn; y3++) {
										if (!world.isAirBlock(checkPos.setPos(x3, y3, z3)))
											cleanSpawn = false;
									}
								}
							}

							if (cleanSpawn)
								planBPos = checkPos.setPos(x2, y2 + 2, z2).toImmutable();
						}
					}
				}
			}
		}

		for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
			for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
				checkPos.setPos(x, posY - 1, z);

				while (world.isAirBlock(checkPos.move(EnumFacing.DOWN)) && checkPos.getY() >= 0) {
					;
				}

				int y = checkPos.getY();
				cleanSpawn = true;

				for (int x2 = x - 2; x2 <= x + 2 && cleanSpawn; x2++) {
					for (int z2 = z - 2; z2 <= z + 2 && cleanSpawn; z2++) {
						for (int y2 = y + 1; y2 <= y + 6 && cleanSpawn; y2++) {
							if (!world.isAirBlock(checkPos.setPos(x2, y2, z2)))
								cleanSpawn = false;
						}
					}
				}

				if (cleanSpawn)
					return checkPos.setPos(x, y + 2, z).toImmutable();
			}
		}

		if (planBPos != null)
			return planBPos;

		return entity.getPosition().up(2).toImmutable();
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
