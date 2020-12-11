package net.tslat.aoa3.library.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import net.tslat.aoa3.block.functional.portal.PortalBlock;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.worldgen.worlds.ancientcavern.AncientCavernTeleporter;
import net.tslat.aoa3.worldgen.worlds.immortallis.ImmortallisTeleporter;

import java.util.HashMap;
import java.util.function.Function;

public abstract class AoATeleporter implements ITeleporter {
	public abstract HashMap<Long, BlockPos> getCachedPortalMap();

	public abstract PortalBlock getPortalBlock();

	public abstract Block getBorderBlock();

	@Override
	public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> entityPlacementFunction) {
		PlayerDataManager plData = null;
		boolean failedPortalReturn = false;

		if (entity instanceof ServerPlayerEntity) {
			entity.setNoGravity(false);
			PortalCoordinatesContainer loc = (plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity)).getPortalReturnLocation(destWorld.getDimension().getType());
			BlockPos locPos;

			if (loc != null) {
				Block locationBlock = destWorld.getBlockState(locPos = loc.asBlockPos()).getBlock();

				if (locationBlock == getPortalBlock()) {
					placeInPortal(destWorld, entity, locPos);

					return customPlayerPlacementFunction((ServerPlayerEntity)entity, currentWorld, destWorld, false);
				}
				else if (!(locationBlock instanceof PortalBlock)) {
					plData.removePortalReturnLocation(destWorld.getDimension().getType());

					failedPortalReturn = true;
				}
			}
		}

		if (currentWorld.getBlockState(entity.getPosition()).getBlock() == getPortalBlock()) {
			ChunkPos chunkPos = currentWorld.getChunk(entity.getPosition()).getPos();

			getCachedPortalMap().put(ChunkPos.asLong(chunkPos.x, chunkPos.z), entity.getPosition());
		}

		BlockPos pos = null;

		if (failedPortalReturn) {
			if (destWorld.getDimension().getType() == AoADimensions.ANCIENT_CAVERN.type() && !(this instanceof AncientCavernTeleporter)) {
				pos = new BlockPos(0.5, 18.5, 0.5);
			}
			else if (destWorld.getDimension().getType() == AoADimensions.IMMORTALLIS.type() && !(this instanceof ImmortallisTeleporter)) {
				pos = new BlockPos(-5, 20, 3);
			}
		}

		if (pos == null)
			pos = findExistingPortal(destWorld, entity);

		if (pos == null) {
			pos = findSuitablePortalLocation(destWorld, entity);
			pos = makePortal(destWorld, entity, pos);
		}

		placeInPortal(destWorld, entity, pos);

		ChunkPos chunkPos = destWorld.getChunk(pos).getPos();

		getCachedPortalMap().put(ChunkPos.asLong(chunkPos.x, chunkPos.z), pos);

		if (plData != null) {
			PortalCoordinatesContainer portalLoc = plData.getPortalReturnLocation(destWorld.getDimension().getType());

			if (portalLoc != null) {
				PortalCoordinatesContainer returnPortalLoc = plData.getPortalReturnLocation(entity.world.getDimension().getType());

				if (returnPortalLoc != null && returnPortalLoc.fromDim == destWorld.getDimension().getType())
					return entity instanceof ServerPlayerEntity ? customPlayerPlacementFunction((ServerPlayerEntity)entity, currentWorld, destWorld, false) : customEntityPlacementFunction(entity, currentWorld, destWorld, false);
			}

			if (portalLoc == null || entity.world.getDimension().getType() == portalLoc.fromDim || entity.getDistanceSq(new Vec3d(portalLoc.asBlockPos())) > AoAConfig.SERVER.portalSearchRadius.get())
				plData.setPortalReturnLocation(destWorld.getDimension().getType(), new PortalCoordinatesContainer(currentWorld.getDimension().getType(), pos.getX(), pos.getY(), pos.getZ()));
		}

		return entity instanceof ServerPlayerEntity ? customPlayerPlacementFunction((ServerPlayerEntity)entity, currentWorld, destWorld, false) : customEntityPlacementFunction(entity, currentWorld, destWorld, false);
	}

	private Entity customPlayerPlacementFunction(ServerPlayerEntity entity, ServerWorld fromWorld, ServerWorld toWorld, boolean spawnPortal) {
		double newPosX = entity.getPosX();
		double newPosY = entity.getPosY();
		double newPosZ = entity.getPosZ();
		float pitch = entity.rotationPitch;
		float yaw = entity.rotationYaw;
		float f2 = yaw;

		entity.world.getProfiler().startSection("moving");

		if (fromWorld.getDimension().getType() == DimensionType.OVERWORLD && toWorld.getDimension().getType() == DimensionType.THE_NETHER) {
			entity.enteredNetherPosition = entity.getPositionVec();
		}
		else if (fromWorld.getDimension().getType() == DimensionType.OVERWORLD && toWorld.getDimension().getType() == DimensionType.THE_END) {
			BlockPos spawnLocation = toWorld.getSpawnCoordinate();
			newPosX = spawnLocation.getX();
			newPosY = spawnLocation.getY();
			newPosZ = spawnLocation.getZ();
			yaw = 90.0F;
			pitch = 0.0F;
		}

		entity.setLocationAndAngles(newPosX, newPosY, newPosZ, yaw, pitch);
		fromWorld.getProfiler().endSection();
		fromWorld.getProfiler().startSection("placing");

		double minX = Math.min(-2.9999872E7D, toWorld.getWorldBorder().minX() + 16.0D);
		double minZ = Math.min(-2.9999872E7D, toWorld.getWorldBorder().minZ() + 16.0D);
		double maxX = Math.min(2.9999872E7D, toWorld.getWorldBorder().maxX() - 16.0D);
		double maxZ = Math.min(2.9999872E7D, toWorld.getWorldBorder().maxZ() - 16.0D);
		newPosX = MathHelper.clamp(newPosX, minX, maxX);
		newPosZ = MathHelper.clamp(newPosZ, minZ, maxZ);

		entity.setLocationAndAngles(newPosX, newPosY, newPosZ, yaw, pitch);

		if (toWorld.getDimension().getType() == DimensionType.THE_END) {
			int baseX = MathHelper.floor(entity.getPosX());
			int baseY = MathHelper.floor(entity.getPosY()) - 1;
			int baseZ = MathHelper.floor(entity.getPosZ());

			for(int j1 = -2; j1 <= 2; ++j1) {
				for(int k1 = -2; k1 <= 2; ++k1) {
					for(int l1 = -1; l1 < 3; ++l1) {
						int i2 = baseX + k1;
						int j2 = baseY + l1;
						int k2 = baseZ + j1;
						boolean flag = l1 < 0;

						toWorld.setBlockState(new BlockPos(i2, j2, k2), flag ? Blocks.OBSIDIAN.getDefaultState() : Blocks.AIR.getDefaultState());
					}
				}
			}

			entity.setLocationAndAngles(baseX, baseY, baseZ, yaw, 0.0F);
			entity.setMotion(Vec3d.ZERO);
		}
		else if (spawnPortal && !toWorld.getDefaultTeleporter().placeInPortal(entity, f2)) {
			toWorld.getDefaultTeleporter().makePortal(entity);
			toWorld.getDefaultTeleporter().placeInPortal(entity, f2);
		}

		fromWorld.getProfiler().endSection();
		entity.setWorld(toWorld);
		toWorld.addDuringPortalTeleport(entity);
		entity.func_213846_b(fromWorld);
		entity.connection.setPlayerLocation(entity.getPosX(), entity.getPosY(), entity.getPosZ(), yaw, pitch);

		return entity;
	}

	private Entity customEntityPlacementFunction(Entity entity, ServerWorld fromWorld, ServerWorld toWorld, boolean spawnPortal) {
		Vec3d motion = entity.getMotion();
		float portalRotation = 0.0F;
		BlockPos pos;

		if (fromWorld.getDimension().getType() == DimensionType.THE_END && toWorld.getDimension().getType() == DimensionType.OVERWORLD) {
			pos = toWorld.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, toWorld.getSpawnPoint());
		}
		else if (toWorld.getDimension().getType() == DimensionType.THE_END) {
			pos = toWorld.getSpawnCoordinate();
		}
		else {
			double posX = entity.getPosX();
			double posZ = entity.getPosZ();
			double minX = Math.min(-2.9999872E7D, toWorld.getWorldBorder().minX() + 16.0D);
			double minZ = Math.min(-2.9999872E7D, toWorld.getWorldBorder().minZ() + 16.0D);
			double maxX = Math.min(2.9999872E7D, toWorld.getWorldBorder().maxX() - 16.0D);
			double maxZ = Math.min(2.9999872E7D, toWorld.getWorldBorder().maxZ() - 16.0D);
			posX = MathHelper.clamp(posX, minX, maxX);
			posZ = MathHelper.clamp(posZ, minZ, maxZ);
			Vec3d portalLocation = entity.getLastPortalVec();
			pos = new BlockPos(posX, entity.getPosY(), posZ);

			if (spawnPortal) {
				BlockPattern.PortalInfo portalInfo = toWorld.getDefaultTeleporter().placeInExistingPortal(pos, motion, entity.getTeleportDirection(), portalLocation.x, portalLocation.y, entity instanceof PlayerEntity);

				if (portalInfo == null)
					return null;

				pos = new BlockPos(portalInfo.pos);
				motion = portalInfo.motion;
				portalRotation = (float)portalInfo.rotation;
			}
		}

		entity.world.getProfiler().endStartSection("reloading");

		Entity newEntity = entity.getType().create(toWorld);

		newEntity.copyDataFromOld(entity);
		newEntity.moveToBlockPosAndAngles(pos, entity.rotationYaw + portalRotation, entity.rotationPitch);
		newEntity.setMotion(motion);
		toWorld.addFromAnotherDimension(newEntity);

		return newEntity;
	}

	public BlockPos findExistingPortal(World world, Entity entity) {
		int posX = (int)Math.floor(entity.getPosX());
		int posY = (int)Math.floor(entity.getPosY());
		int posZ = (int)Math.floor(entity.getPosZ());
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

		BlockPos.Mutable checkPos = new BlockPos.Mutable();
		int searchRadius = AoAConfig.SERVER.portalSearchRadius.get();
		int worldHeight = world.getActualHeight();

		if (posY >= worldHeight)
			posY = 65;

		if (world.getBlockState(checkPos.setPos(posX, posY, posZ)).getBlock() == getPortalBlock()) {
			while (world.getBlockState(checkPos.move(Direction.DOWN)).getBlock() == getPortalBlock()) {
				;
			}

			IChunk chunk = world.getChunk(checkPos.move(Direction.UP));

			cachedPortalMap.put(ChunkPos.asLong(chunk.getPos().getZStart(), chunk.getPos().getZStart()), checkPos);

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
							while (world.getBlockState(checkPos.move(Direction.DOWN)).getBlock() == getPortalBlock()) {
								;
							}

							IChunk chunk = world.getChunk(checkPos.move(Direction.UP));

							cachedPortalMap.put(ChunkPos.asLong(chunk.getPos().getXStart(), chunk.getPos().getZStart()), checkPos);

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
							while (world.getBlockState(checkPos.move(Direction.DOWN)).getBlock() == getPortalBlock()) {
								;
							}

							IChunk chunk = world.getChunk(checkPos.move(Direction.UP));

							cachedPortalMap.put(ChunkPos.asLong(chunk.getPos().getXStart(), chunk.getPos().getZStart()), checkPos);

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
							while (world.getBlockState(checkPos.move(Direction.DOWN)).getBlock() == getPortalBlock()) {
								;
							}

							IChunk chunk = world.getChunk(checkPos.move(Direction.UP));

							cachedPortalMap.put(ChunkPos.asLong(chunk.getPos().getXStart(), chunk.getPos().getZStart()), checkPos);

							return checkPos;
						}
					}
				}
			}
		}

		for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
			for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
				checkPos.setPos(x, worldHeight, z);

				while (world.getBlockState(checkPos.move(Direction.DOWN)).getBlock() != getPortalBlock() && checkPos.getY() >= 0) {
					;
				}

				if (world.getBlockState(checkPos).getBlock() == getPortalBlock()) {
					while (world.getBlockState(checkPos.move(Direction.DOWN)).getBlock() == getPortalBlock()) {
						;
					}

					return checkPos.up(2).toImmutable();
				}
			}
		}

		for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
			for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
				checkPos.setPos(x, posY, z);

				while (world.getBlockState(checkPos.move(Direction.UP)).getBlock() != getPortalBlock() && checkPos.getY() < worldHeight) {
					;
				}

				if (world.getBlockState(checkPos).getBlock() == getPortalBlock())
					return checkPos.up(1).toImmutable();
			}
		}

		return null;
	}
	
	public BlockPos findSuitablePortalLocation(World world, Entity entity) {
		BlockPos.Mutable checkPos = new BlockPos.Mutable();
		int posX = (int)Math.floor(entity.getPosX());
		int posY = (int)Math.floor(entity.getPosY());
		int posZ = (int)Math.floor(entity.getPosZ());
		BlockPos planBPos = null;
		int searchRadius = AoAConfig.SERVER.portalSearchRadius.get();
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

				while (world.isAirBlock(checkPos.move(Direction.DOWN)) && checkPos.getY() >= 0) {
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
		if (world.getDimension().getType() == DimensionType.OVERWORLD)
			return pos;

		BlockPos returnPos = pos;
		pos = pos.down();
		BlockState border = getBorderBlock().getDefaultState();
		BlockState portal = getPortalBlock().getDefaultState();
		Direction.Axis direction = EntityUtil.getDirectionFacing(entity, true).getAxis();

		if (direction == Direction.Axis.X) {
			portal = portal.with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X);

			for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++) {
				world.setBlockState(new BlockPos(pos.getX(), pos.getY(), z), border, 0);
				world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 5, z), border, 0);
			}

			for (int y = pos.getY() + 1; y <= pos.getY() + 4; y++) {
				world.setBlockState(new BlockPos(pos.getX(), y, pos.getZ() - 2), border, 0);
				world.setBlockState(new BlockPos(pos.getX(), y, pos.getZ() - 1), portal, 0);
				world.setBlockState(new BlockPos(pos.getX(), y, pos.getZ()), portal, 0);
				world.setBlockState(new BlockPos(pos.getX(), y, pos.getZ() + 1), portal, 0);
				world.setBlockState(new BlockPos(pos.getX(), y, pos.getZ() + 2), border, 0);
			}
		}
		else {
			portal = portal.with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z);

			for (int x = pos.getX() - 2; x <= pos.getX() + 2; x++) {
				world.setBlockState(new BlockPos(x, pos.getY(), pos.getZ()), border, 0);
				world.setBlockState(new BlockPos(x, pos.getY() + 5, pos.getZ()), border, 0);
			}

			for (int y = pos.getY() + 1; y <= pos.getY() + 4; y++) {
				world.setBlockState(new BlockPos(pos.getX() - 2, y, pos.getZ()), border, 0);
				world.setBlockState(new BlockPos(pos.getX() - 1, y, pos.getZ()), portal, 0);
				world.setBlockState(new BlockPos(pos.getX(), y, pos.getZ()), portal, 0);
				world.setBlockState(new BlockPos(pos.getX() + 1, y, pos.getZ()), portal, 0);
				world.setBlockState(new BlockPos(pos.getX() + 2, y, pos.getZ()), border, 0);
			}
		}
		
		pos = pos.down();
		
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				BlockPos checkPos = pos.add(x, 0, z);

				if (!world.getBlockState(checkPos).isSolidSide(world, checkPos, Direction.UP)) {
					makePortalPlatformAndDecorate(world, pos, direction);
					
					return returnPos;
				}
			}
		}

		return returnPos;
	}
	
	public void makePortalPlatformAndDecorate(World world, BlockPos pos, Direction.Axis direction) {
		BlockState border = getBorderBlock().getDefaultState();

		for (int x = pos.getX() - 2; x <= pos.getX() + 2; x++) {
			for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++) {
				world.setBlockState(new BlockPos(x, pos.getY(), z), border);
			}
		}
	}
	
	public void placeInPortal(World world, Entity entity, BlockPos pos) {
		entity.setMotion(0, 0, 0);

		if (entity instanceof ServerPlayerEntity) {
			((ServerPlayerEntity)entity).connection.setPlayerLocation(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, entity.rotationYaw, entity.rotationPitch);
		}
		else {
			entity.setLocationAndAngles(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, entity.rotationYaw, entity.rotationPitch);
		}
	}
}
