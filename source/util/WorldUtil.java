package net.tslat.aoa3.util;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAGameRules;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class WorldUtil {
	public static boolean checkGameRule(World world, GameRules.RuleKey<GameRules.BooleanValue> gameRule) {
		return world.getGameRules().getBoolean(gameRule);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, BlockPos pos, float strength) {
		return createExplosion(exploder, world, pos.getX(), pos.getY(), pos.getZ(), strength, AoAGameRules.checkDestructiveWeaponPhysics(world) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
	}

	public static Explosion createExplosion(@Nonnull Entity exploder, World world, float strength) {
		return createExplosion(exploder, world, exploder.getX(), exploder.getY(), exploder.getZ(), strength, AoAGameRules.checkStrongerMobGriefing(world, exploder) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, @Nonnull Entity explodingEntity, float strength) {
		boolean doGriefing;

		if (exploder instanceof PlayerEntity) {
			doGriefing = AoAGameRules.checkDestructiveWeaponPhysics(world);
		}
		else {
			if (exploder == null)
				exploder = explodingEntity;

			if (exploder instanceof LivingEntity || explodingEntity instanceof LivingEntity) {
				doGriefing = AoAGameRules.checkStrongerMobGriefing(world, exploder);
			}
			else {
				doGriefing = AoAGameRules.checkDestructiveWeaponPhysics(world);
			}
		}

		return createExplosion(exploder, world, explodingEntity.getX(), explodingEntity.getY(), explodingEntity.getZ(), strength, doGriefing ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, double posX, double posY, double posZ, float strength) {
		return createExplosion(exploder, world, posX, posY, posZ, strength, AoAGameRules.checkDestructiveWeaponPhysics(world) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, double posX, double posY, double posZ, float strength, Explosion.Mode explosionType) {
		return createExplosion(exploder, world, posX, posY, posZ, strength, explosionType, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, double posX, double posY, double posZ, float strength, Explosion.Mode explosionType, boolean fieryExplosion) {
		return world.explode(exploder, posX, posY, posZ, strength, fieryExplosion, explosionType);
	}

	public static int getLightLevel(IServerWorld world, BlockPos position, boolean ignoreSkyLight, boolean ignoreBlockLight) {
		if (ignoreBlockLight && ignoreSkyLight) {
			ignoreBlockLight = false;
			ignoreSkyLight = false;
		}

		if (position.getY() > 255) {
			position = new BlockPos(position.getX(), 255, position.getZ());
		}
		else if (position.getY() < 0) {
			position = new BlockPos(position.getX(), 0, position.getZ());
		}

		if (ignoreSkyLight)
			return world.getBrightness(LightType.BLOCK, position);

		if (ignoreBlockLight)
			return world.getBrightness(LightType.SKY, position) - world.getSkyDarken();

		return world.getRawBrightness(position, 0);
	}

	public static void spawnLightning(ServerWorld world, @Nullable ServerPlayerEntity caster, double x, double y, double z, boolean destructive) {
		LightningBoltEntity lightning = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, world);

		lightning.setVisualOnly(!destructive);
		lightning.moveTo(x, y, z);

		if (caster != null)
			lightning.setCause(caster);

		world.addFreshEntity(lightning);
	}

	public static boolean harvestAdditionalBlock(World world, PlayerEntity pl, BlockPos breakPos) {
		BlockState blockState = world.getBlockState(breakPos);
		Block block = blockState.getBlock();

		if (block.isAir(blockState, world, breakPos) || !world.mayInteract(pl, breakPos))
			return false;

		if (!world.isClientSide()) {
			ServerPlayerEntity player = (ServerPlayerEntity)pl;
			GameType gameMode = player.gameMode.getGameModeForPlayer();
			int blockXp = net.minecraftforge.common.ForgeHooks.onBlockBreakEvent(world, gameMode, player, breakPos);

			if (blockXp == -1)
				return false;

			TileEntity tileEntity = world.getBlockEntity(breakPos);

			if (!pl.canUseGameMasterBlocks() && (block instanceof CommandBlockBlock || block instanceof StructureBlock || block instanceof JigsawBlock)) {
				world.sendBlockUpdated(breakPos, blockState, blockState, Constants.BlockFlags.DEFAULT);

				return false;
			}

			if (pl.getMainHandItem().onBlockStartBreak(breakPos, pl) || pl.blockActionRestricted(world, breakPos, gameMode))
				return false;

			if (pl.isCreative()) {
				boolean removed = blockState.removedByPlayer(world, breakPos, player, false, world.getFluidState(breakPos));

				if (removed)
					blockState.getBlock().destroy(world, breakPos, blockState);
			}
			else {
				ItemStack toolStack = pl.getMainHandItem();
				ItemStack toolStackCopy = toolStack.copy();
				boolean canHarvest = blockState.canHarvestBlock(world, breakPos, pl);

				if (toolStack.isEmpty() && !toolStackCopy.isEmpty())
					net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(pl, toolStackCopy, Hand.MAIN_HAND);

				boolean removedBlock = blockState.removedByPlayer(world, breakPos, player, false, world.getFluidState(breakPos));

				if (removedBlock) {
					blockState.getBlock().destroy(world, breakPos, blockState);

					if (canHarvest)
						block.playerDestroy(world, pl, breakPos, blockState, tileEntity, toolStackCopy);

					if (blockXp > 0)
						blockState.getBlock().popExperience((ServerWorld)world, breakPos, blockXp);
				}
			}

			return true;
		}
		else {
			return ClientOperations.harvestAdditionalBlock(breakPos);
		}
	}

	public static float getAmbientTemperature(World world, BlockPos pos) {
		Biome biome = world.getBiome(pos);
		float temp = biome.getTemperature(pos);

		if (world.canSeeSky(pos)) {
			if (world.isDay()) {
				temp *= 1.35f;
			}
			else {
				temp /= 1.35f;
			}

			if (world.isRaining()) {
				if (biome.getPrecipitation() == Biome.RainType.SNOW) {
					temp /= 1.5f;
				}
				else if (biome.getPrecipitation() == Biome.RainType.RAIN) {
					temp /= 1.25f;
				}
			}
		}

		return temp;
	}

	@Nonnull
	public static ArrayList<PlayerEntity> getAllPlayersInRegion(World world, AxisAlignedBB region) {
		ArrayList<PlayerEntity> players = new ArrayList<PlayerEntity>();

		for (PlayerEntity player : world.players()) {
			if (region.contains(player.position()))
				players.add(player);
		}

		return players;
	}

	public static int getTrueWorldHeight(World world, int x, int z) {
		boolean headBlock = false;
		boolean feetBlock = false;

		try {
			int height = Math.max(world.getHeight(Heightmap.Type.MOTION_BLOCKING, x, z), world.dimensionType().logicalHeight());

			if (Math.abs(x) > 30000000 || Math.abs(z) > 30000000)
				return 0;

			for (int i = height; i > 0; i--) {
				if (world.getBlockState(new BlockPos(x, i, z)).getBlock() == Blocks.AIR) {
					if (headBlock) {
						if (!feetBlock)
							feetBlock = true;
					}
					else {
						headBlock = true;
					}
				}
				else {
					if (headBlock && feetBlock)
						return i;

					headBlock = false;
					feetBlock = false;
				}
			}
		}
		catch (Exception e) {
			return 0;
		}

		return 0;
	}

	public static boolean canPlaceBlock(IWorld world, BlockPos pos, @Nullable Entity entity, @Nullable ItemStack stack) {
		if (!(world instanceof World))
			return true;

		World activeWorld = (World)world;
		PlayerEntity relevantPlayer = PlayerUtil.getPlayerOrOwnerIfApplicable(entity);

		if (WorldUtil.isWorld(activeWorld, AoADimensions.NOWHERE.key))
			return relevantPlayer != null && relevantPlayer.isCreative();

		if (relevantPlayer != null) {
			if (!relevantPlayer.mayBuild())
				return false;

			GameType gameMode = PlayerUtil.getGameMode(relevantPlayer);

			if (gameMode == GameType.SPECTATOR)
				return false;

			if (gameMode == GameType.ADVENTURE && stack != null) {
				if (stack.isEmpty())
					return false;

				return stack.hasAdventureModePlaceTagForBlock((activeWorld).getTagManager(), new CachedBlockInfo(activeWorld, pos, false));
			}
		}

		return true;
	}

	public static boolean canModifyBlock(IWorld world, BlockPos pos, @Nullable Entity entity, @Nullable ItemStack stack) {
		if (!(world instanceof World))
			return true;

		World activeWorld = (World)world;
		PlayerEntity relevantPlayer = PlayerUtil.getPlayerOrOwnerIfApplicable(entity);

		if (WorldUtil.isWorld(activeWorld, AoADimensions.NOWHERE.key))
			return relevantPlayer != null && relevantPlayer.isCreative();

		if (relevantPlayer != null) {
			if (!relevantPlayer.mayBuild())
				return false;

			GameType gameMode = PlayerUtil.getGameMode(relevantPlayer);

			if (gameMode == GameType.SPECTATOR)
				return false;

			if (gameMode == GameType.ADVENTURE && stack != null) {
				if (stack.isEmpty())
					return false;

				return stack.hasAdventureModeBreakTagForBlock((activeWorld).getTagManager(), new CachedBlockInfo(activeWorld, pos, false));
			}
		}

		return true;
	}

	public static void operateOnMultipleBlocksInRange(World world, BlockPos center, int radius, Predicate<BlockState> test, Consumer<BlockPos> operation) {
		for (int x = center.getX() - radius; x < center.getX() + radius; x++) {
			for (int y = center.getY() - radius; y < center.getY() + radius; y++) {
				for (int z = center.getZ() - radius; z < center.getZ() + radius; z++) {
					BlockPos pos = new BlockPos(x, y, z);

					if (test.test(world.getBlockState(pos)))
						operation.accept(pos);
				}
			}
		}
	}

	public static ArrayList<BlockPos> getBlocksWithinAABB(World world, AxisAlignedBB aabb, @Nullable BiPredicate<BlockState, BlockPos.Mutable> predicate) {
		BlockPos.Mutable checkPos = new BlockPos.Mutable();
		ArrayList<BlockPos> matches = new ArrayList<BlockPos>();

		for (int x = (int)Math.floor(aabb.minX); x <= Math.ceil(aabb.maxX); x++) {
			for (int y = (int)Math.floor(aabb.minY); y <= Math.ceil(aabb.maxY); y++) {
				for (int z = (int)Math.floor(aabb.minZ); z <= Math.ceil(aabb.maxZ); z++) {
					checkPos.set(x, y, z);

					if (predicate == null || predicate.test(world.getBlockState(checkPos), checkPos))
						matches.add(checkPos.immutable());
				}
			}
		}

		return matches;
	}

	public static boolean isWorld(IServerWorld world, RegistryKey<World>... keys) {
		for (RegistryKey<World> key : keys) {
			if (world.getLevel().dimension() == key)
				return true;
		}

		return false;
	}

	public static boolean isWorld(World world, RegistryKey<World>... keys) {
		for (RegistryKey<World> key : keys) {
			if (world.dimension() == key)
				return true;
		}

		return false;
	}
}
