package net.tslat.aoa3.content.world.teleporter;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.block.functional.portal.PortalBlock;
import net.tslat.aoa3.content.world.teleporter.specific.NowhereTeleporter;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.function.Function;

public abstract class AoATeleporter implements ITeleporter {
	public abstract HashMap<Long, BlockPos> getCachedPortalMap();

	public abstract PortalBlock getPortalBlock();

	public abstract Block getBorderBlock();

	@Override
	public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> entityPlacementFunction) {
		ServerPlayerDataManager plData = null;
		boolean failedPortalReturn = false;
		BlockPos startPos = entity.blockPosition();

		if (entity instanceof ServerPlayer pl) {
			plData = PlayerUtil.getAdventPlayer(pl);
			BlockPos existingReturnPos = findReturnPos(pl, currentWorld, destWorld);
			pl.setNoGravity(false);

			if (existingReturnPos != null) {
				return positionPlayer(pl, new Vec3(existingReturnPos.getX() + 0.5f, existingReturnPos.getY() + 0.5f, existingReturnPos.getZ() + 0.5f), currentWorld, destWorld, false);
			}
			else {
				failedPortalReturn = true;
			}
		}

		if (currentWorld.getBlockState(entity.blockPosition()).getBlock() == getPortalBlock()) {
			ChunkPos chunkPos = currentWorld.getChunk(entity.blockPosition()).getPos();

			getCachedPortalMap().put(ChunkPos.asLong(chunkPos.x, chunkPos.z), entity.blockPosition());
		}

		BlockPos pos = null;

		if (failedPortalReturn) {
			if (WorldUtil.isWorld((Level)destWorld, AoADimensions.NOWHERE.key) && !(this instanceof NowhereTeleporter))
				pos = new BlockPos(16, 1003, 16);
		}

		if (pos == null)
			pos = findExistingPortal(destWorld, entity);

		if (pos == null) {
			pos = findSuitablePortalLocation(destWorld, entity);
			pos = makePortal(destWorld, entity, pos);
		}

		Vec3 teleportPos = new Vec3(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f);
		ChunkPos chunkPos = destWorld.getChunk(pos).getPos();

		getCachedPortalMap().put(ChunkPos.asLong(chunkPos.x, chunkPos.z), pos);

		if (plData != null) {
			PortalCoordinatesContainer portalLoc = plData.getPortalReturnLocation(destWorld.dimension());

			if (portalLoc != null) {
				PortalCoordinatesContainer returnPortalLoc = plData.getPortalReturnLocation(entity.level().dimension());

				if (returnPortalLoc != null && returnPortalLoc.fromDim() == destWorld.dimension())
					return positionPlayer((ServerPlayer)entity, teleportPos, currentWorld, destWorld, false);
			}

			if (portalLoc == null || entity.level().dimension() == portalLoc.fromDim() || entity.distanceToSqr(Vec3.atLowerCornerOf(portalLoc.asBlockPos())) > AoAConfigs.SERVER.portalSearchRadius.get())
				plData.setPortalReturnLocation(destWorld.dimension(), new PortalCoordinatesContainer(currentWorld.dimension(), startPos.getX(), startPos.getY(), startPos.getZ()));
		}

		return entity instanceof ServerPlayer ? positionPlayer((ServerPlayer)entity, teleportPos, currentWorld, destWorld, false) : positionNonPlayer(entity, teleportPos, destWorld, false);
	}

	private Entity positionPlayer(ServerPlayer player, Vec3 position, ServerLevel fromWorld, ServerLevel toWorld, boolean spawnPortal) {
		fromWorld.getProfiler().push("moving");

		if (fromWorld.dimension() == Level.OVERWORLD && toWorld.dimension() == Level.NETHER) {
			player.enteredNetherPosition = player.position();
		}
		else if (spawnPortal && toWorld.dimension() == Level.END) {
			BlockPos originPos = BlockPos.containing(position);
			BlockPos.MutableBlockPos mutablePos = originPos.mutable();

			for(int z = -2; z <= 2; ++z) {
				for(int x = -2; x <= 2; ++x) {
					for(int y = -1; y < 3; ++y) {
						BlockState state = y == -1 ? Blocks.OBSIDIAN.defaultBlockState() : Blocks.AIR.defaultBlockState();

						toWorld.setBlockAndUpdate(mutablePos.set(originPos).move(x, y, z), state);
					}
				}
			}
		}

		fromWorld.getProfiler().pop();
		fromWorld.getProfiler().push("placing");
		player.setServerLevel(toWorld);
		player.connection.teleport(position.x(), position.y(), position.z(), player.getYRot(), player.getXRot());
		player.connection.resetPosition();
		toWorld.addDuringPortalTeleport(player);
		fromWorld.getProfiler().pop();
		player.triggerDimensionChangeTriggers(fromWorld);

		return player;
	}

	private Entity positionNonPlayer(Entity entity, Vec3 position, ServerLevel toWorld, boolean spawnPortal) {
		entity.level().getProfiler().popPush("reloading");

		Entity newEntity = entity.getType().create(toWorld);

		if (newEntity != null) {
			newEntity.restoreFrom(entity);
			newEntity.moveTo(position.x(), position.y(), position.z(), entity.getYRot(), entity.getXRot());
			newEntity.setDeltaMovement(entity.getDeltaMovement());
			toWorld.addDuringTeleport(newEntity);

			if (spawnPortal && toWorld.dimension() == Level.END)
				ServerLevel.makeObsidianPlatform(toWorld);
		}

		return newEntity;
	}

	@Nullable
	protected BlockPos findReturnPos(ServerPlayer player, ServerLevel currentWorld, ServerLevel destWorld) {
		ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(player);
		PortalCoordinatesContainer loc = plData.getPortalReturnLocation(currentWorld.dimension());
		BlockPos locPos;

		if (loc != null) {
			Block locationBlock = destWorld.getBlockState(locPos = loc.asBlockPos()).getBlock();

			if (locationBlock == getPortalBlock())
				return locPos;

			if (!(locationBlock instanceof PortalBlock))
				plData.removePortalReturnLocation(currentWorld.dimension());
		}

		return null;
	}

	public BlockPos findExistingPortal(Level world, Entity entity) {
		int posX = (int)Math.floor(entity.getX());
		int posY = (int)Math.floor(entity.getY());
		int posZ = (int)Math.floor(entity.getZ());
		ChunkPos chunkPos = new ChunkPos(new BlockPos(posX, posY, posZ));
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
		int searchRadius = AoAConfigs.SERVER.portalSearchRadius.get();
		int worldHeight = world.dimensionType().logicalHeight();

		if (posY >= worldHeight)
			posY = 65;

		if (world.getBlockState(checkPos.set(posX, posY, posZ)).getBlock() == getPortalBlock()) {
			while (world.getBlockState(checkPos.move(Direction.DOWN)).getBlock() == getPortalBlock()) {
				;
			}

			ChunkAccess chunk = world.getChunk(checkPos.move(Direction.UP));

			cachedPortalMap.put(ChunkPos.asLong(chunk.getPos().getMinBlockZ(), chunk.getPos().getMinBlockZ()), checkPos);

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

						checkPos.set(x2, y2, z2);

						if (world.getBlockState(checkPos).getBlock() == getPortalBlock()) {
							while (world.getBlockState(checkPos.move(Direction.DOWN)).getBlock() == getPortalBlock()) {
								;
							}

							ChunkAccess chunk = world.getChunk(checkPos.move(Direction.UP));

							cachedPortalMap.put(ChunkPos.asLong(chunk.getPos().getMinBlockX(), chunk.getPos().getMinBlockZ()), checkPos);

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

						checkPos.set(x2, y2, z2);

						if (world.getBlockState(checkPos).getBlock() == getPortalBlock()) {
							while (world.getBlockState(checkPos.move(Direction.DOWN)).getBlock() == getPortalBlock()) {
								;
							}

							ChunkAccess chunk = world.getChunk(checkPos.move(Direction.UP));

							cachedPortalMap.put(ChunkPos.asLong(chunk.getPos().getMinBlockX(), chunk.getPos().getMinBlockZ()), checkPos);

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

						checkPos.set(x2, y2, z2);

						if (world.getBlockState(checkPos).getBlock() == getPortalBlock()) {
							while (world.getBlockState(checkPos.move(Direction.DOWN)).getBlock() == getPortalBlock()) {
								;
							}

							ChunkAccess chunk = world.getChunk(checkPos.move(Direction.UP));

							cachedPortalMap.put(ChunkPos.asLong(chunk.getPos().getMinBlockX(), chunk.getPos().getMinBlockZ()), checkPos);

							return checkPos;
						}
					}
				}
			}
		}

		for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
			for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
				checkPos.set(x, posY - searchRadius, z);

				while (world.getBlockState(checkPos.move(Direction.DOWN)).getBlock() != getPortalBlock() && checkPos.getY() >= 0) {
					;
				}

				if (world.getBlockState(checkPos).getBlock() == getPortalBlock()) {
					while (world.getBlockState(checkPos.move(Direction.DOWN)).getBlock() == getPortalBlock()) {
						;
					}

					return checkPos.above(2).immutable();
				}
			}
		}

		for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
			for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
				checkPos.set(x, posY + searchRadius, z);

				while (world.getBlockState(checkPos.move(Direction.UP)).getBlock() != getPortalBlock() && checkPos.getY() < worldHeight) {
					;
				}

				if (world.getBlockState(checkPos).getBlock() == getPortalBlock())
					return checkPos.above(1).immutable();
			}
		}

		return null;
	}
	
	public BlockPos findSuitablePortalLocation(Level world, Entity entity) {
		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
		int posX = (int)Math.floor(entity.getX());
		int posY = (int)Math.floor(entity.getY());
		int posZ = (int)Math.floor(entity.getZ());
		BlockPos planBPos = null;
		int searchRadius = AoAConfigs.SERVER.portalSearchRadius.get();
		int worldHeight = world.dimensionType().logicalHeight();

		if (posY >= worldHeight)
			posY = 65;

		boolean cleanSpawn = true;

		for (int x = posX - 2; x <= posX + 2 && cleanSpawn; x++) {
			for (int z = posZ - 2; z <= posZ + 2 && cleanSpawn; z++) {
				for (int y = posY + 1; y <= posY + 6 && cleanSpawn; y++) {
					if (y >= worldHeight || !world.isEmptyBlock(checkPos.set(x, y, z)))
						cleanSpawn = false;
				}
			}
		}

		if (cleanSpawn) {
			if (!world.isEmptyBlock(checkPos.set(posX, posY, posZ))) {
				return checkPos.set(posX, posY + 2, posZ).immutable();
			}
			else {
				planBPos = checkPos.set(posX, posY + 2, posZ).immutable();
			}
		}

		for (int i = 1; i <= searchRadius; i++) {
			for (int y = -i; y <= i; y += i * 2) {
				int y2 = posY + y;

				if (y2 < 0 || y2 >= worldHeight - 6)
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

						if (!world.isEmptyBlock(checkPos.set(x2, y2, z2))) {
							cleanSpawn = true;

							for (int x3 = x2 - 2; x3 <= x2 + 2 && cleanSpawn; x3++) {
								for (int z3 = z2 - 2; z3 <= z2 + 2 && cleanSpawn; z3++) {
									for (int y3 = y2 + 1; y3 <= y2 + 6 && cleanSpawn; y3++) {
										if (!world.isEmptyBlock(checkPos.set(x3, y3, z3)))
											cleanSpawn = false;
									}
								}
							}

							if (cleanSpawn)
								return checkPos.set(x2, y2 + 2, z2).immutable();
						}
						else if (planBPos == null) {
							cleanSpawn = true;

							for (int x3 = x2 - 2; x3 <= x2 + 2 && cleanSpawn; x3++) {
								for (int z3 = z2 - 2; z3 <= z2 + 2 && cleanSpawn; z3++) {
									for (int y3 = y2 + 1; y3 <= y2 + 6 && cleanSpawn; y3++) {
										if (!world.isEmptyBlock(checkPos.set(x3, y3, z3)))
											cleanSpawn = false;
									}
								}
							}

							if (cleanSpawn)
								planBPos = checkPos.set(x2, y2 + 2, z2).immutable();
						}
					}
				}
			}

			int yNeg = -1;

			for (int y = 0; y <= i - 1; y++) {
				int y2 = posY + y * yNeg;

				if (y2 < 0 || y2 >= worldHeight - 6)
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

						if (!world.isEmptyBlock(checkPos.set(x2, y2, z2))) {
							cleanSpawn = true;

							for (int x3 = x2 - 2; x3 <= x2 + 2 && cleanSpawn; x3++) {
								for (int z3 = z2 - 2; z3 <= z2 + 2 && cleanSpawn; z3++) {
									for (int y3 = y2 + 1; y3 <= y2 + 6 && cleanSpawn; y3++) {
										if (!world.isEmptyBlock(checkPos.set(x3, y3, z3)))
											cleanSpawn = false;
									}
								}
							}

							if (cleanSpawn)
								return checkPos.set(x2, y2 + 2, z2).immutable();
						}
						else if (planBPos == null) {
							cleanSpawn = true;

							for (int x3 = x2 - 2; x3 <= x2 + 2 && cleanSpawn; x3++) {
								for (int z3 = z2 - 2; z3 <= z2 + 2 && cleanSpawn; z3++) {
									for (int y3 = y2 + 1; y3 <= y2 + 6 && cleanSpawn; y3++) {
										if (!world.isEmptyBlock(checkPos.set(x3, y3, z3)))
											cleanSpawn = false;
									}
								}
							}

							if (cleanSpawn)
								planBPos = checkPos.set(x2, y2 + 2, z2).immutable();
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

						if (!world.isEmptyBlock(checkPos.set(x2, y2, z2))) {
							cleanSpawn = true;

							for (int x3 = x2 - 2; x3 <= x2 + 2 && cleanSpawn; x3++) {
								for (int z3 = z2 - 2; z3 <= z2 + 2 && cleanSpawn; z3++) {
									for (int y3 = y2 + 1; y3 <= y2 + 6 && cleanSpawn; y3++) {
										if (!world.isEmptyBlock(checkPos.set(x3, y3, z3)))
											cleanSpawn = false;
									}
								}
							}

							if (cleanSpawn)
								return checkPos.set(x2, y2 + 2, z2).immutable();
						}
						else if (planBPos == null) {
							cleanSpawn = true;

							for (int x3 = x2 - 2; x3 <= x2 + 2 && cleanSpawn; x3++) {
								for (int z3 = z2 - 2; z3 <= z2 + 2 && cleanSpawn; z3++) {
									for (int y3 = y2 + 1; y3 <= y2 + 6 && cleanSpawn; y3++) {
										if (!world.isEmptyBlock(checkPos.set(x3, y3, z3)))
											cleanSpawn = false;
									}
								}
							}

							if (cleanSpawn)
								planBPos = checkPos.set(x2, y2 + 2, z2).immutable();
						}
					}
				}
			}
		}

		for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
			for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
				checkPos.set(x, posY - searchRadius, z);

				while (world.isEmptyBlock(checkPos.move(Direction.DOWN)) && checkPos.getY() >= 0) {
					;
				}

				int y = checkPos.getY();
				cleanSpawn = true;

				for (int x2 = x - 2; x2 <= x + 2 && cleanSpawn; x2++) {
					for (int z2 = z - 2; z2 <= z + 2 && cleanSpawn; z2++) {
						for (int y2 = y + 1; y2 <= y + 6 && cleanSpawn; y2++) {
							if (!world.isEmptyBlock(checkPos.set(x2, y2, z2)) || y2 >= worldHeight - 6)
								cleanSpawn = false;
						}
					}
				}

				if (cleanSpawn && y >= 0)
					return checkPos.set(x, y + 2, z).immutable();
			}
		}

		for (int x = posX - searchRadius; x <= posX + searchRadius; x++) {
			for (int z = posZ - searchRadius; z <= posZ + searchRadius; z++) {
				checkPos.set(x, worldHeight - 7, z);

				while (world.isEmptyBlock(checkPos.move(Direction.DOWN)) && checkPos.getY() >= posY + searchRadius) {
					;
				}

				int y = checkPos.getY();
				cleanSpawn = true;

				for (int x2 = x - 2; x2 <= x + 2 && cleanSpawn; x2++) {
					for (int z2 = z - 2; z2 <= z + 2 && cleanSpawn; z2++) {
						for (int y2 = y + 1; y2 <= y + 6 && cleanSpawn; y2++) {
							if (!world.isEmptyBlock(checkPos.set(x2, y2, z2)))
								cleanSpawn = false;
						}
					}
				}

				if (cleanSpawn)
					return checkPos.set(x, y + 2, z).immutable();
			}
		}

		if (planBPos != null)
			return planBPos;

		BlockPos placementPos = entity.blockPosition().above(2).immutable();

		if (placementPos.getY() >= worldHeight - 10)
			return new BlockPos(placementPos.getX(), worldHeight - 10, placementPos.getZ());

		return placementPos;
	}
	
	public BlockPos makePortal(Level world, Entity entity, BlockPos pos) {
		if (WorldUtil.isWorld(world, AoADimensions.OVERWORLD.key))
			return pos;

		BlockPos returnPos = pos;
		pos = pos.below();
		BlockState border = getBorderBlock().defaultBlockState();
		BlockState portal = getPortalBlock().defaultBlockState();
		Direction.Axis direction = EntityUtil.getDirectionFacing(entity, true).getAxis();

		if (!world.isEmptyBlock(pos)) {
			for (int x = pos.getX() - 3; x <= pos.getX() + 3; x++) {
				for (int z = pos.getZ() - 3; z <= pos.getZ() + 3; z++) {
					for (int y = pos.getY() + 1; y <= pos.getY() + 4; y++) {
						world.setBlock(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState(), 2);
					}
				}
			}
		}

		if (direction == Direction.Axis.X) {
			portal = portal.setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X);

			for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++) {
				world.setBlock(new BlockPos(pos.getX(), pos.getY(), z), border, 2);
				world.setBlock(new BlockPos(pos.getX(), pos.getY() + 5, z), border, 2);
			}

			for (int y = pos.getY() + 1; y <= pos.getY() + 4; y++) {
				world.setBlock(new BlockPos(pos.getX(), y, pos.getZ() - 2), border, 2);
				world.setBlock(new BlockPos(pos.getX(), y, pos.getZ() - 1), portal, 2);
				world.setBlock(new BlockPos(pos.getX(), y, pos.getZ()), portal, 2);
				world.setBlock(new BlockPos(pos.getX(), y, pos.getZ() + 1), portal, 2);
				world.setBlock(new BlockPos(pos.getX(), y, pos.getZ() + 2), border, 2);
			}
		}
		else {
			portal = portal.setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z);

			for (int x = pos.getX() - 2; x <= pos.getX() + 2; x++) {
				world.setBlock(new BlockPos(x, pos.getY(), pos.getZ()), border, 2);
				world.setBlock(new BlockPos(x, pos.getY() + 5, pos.getZ()), border, 2);
			}

			for (int y = pos.getY() + 1; y <= pos.getY() + 4; y++) {
				world.setBlock(new BlockPos(pos.getX() - 2, y, pos.getZ()), border, 2);
				world.setBlock(new BlockPos(pos.getX() - 1, y, pos.getZ()), portal, 2);
				world.setBlock(new BlockPos(pos.getX(), y, pos.getZ()), portal, 2);
				world.setBlock(new BlockPos(pos.getX() + 1, y, pos.getZ()), portal, 2);
				world.setBlock(new BlockPos(pos.getX() + 2, y, pos.getZ()), border, 2);
			}
		}
		
		pos = pos.below();
		
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				BlockPos checkPos = pos.offset(x, 0, z);

				if (!world.getBlockState(checkPos).isFaceSturdy(world, checkPos, Direction.UP)) {
					makePortalPlatformAndDecorate(world, pos, direction);
					
					return returnPos;
				}
			}
		}

		return returnPos;
	}
	
	public void makePortalPlatformAndDecorate(Level world, BlockPos pos, Direction.Axis direction) {
		BlockState border = getBorderBlock().defaultBlockState();

		for (int x = pos.getX() - 2; x <= pos.getX() + 2; x++) {
			for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++) {
				world.setBlock(new BlockPos(x, pos.getY(), z), border, 2);
			}
		}
	}
	
	public void placeInPortal(Level world, Entity entity, BlockPos pos) {
		entity.setDeltaMovement(0, 0, 0);
		entity.moveTo(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f);
	}
}
