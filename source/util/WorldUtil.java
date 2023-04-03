package net.tslat.aoa3.util;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CommandBlock;
import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.StructureBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.misc.CustomisableLightningBolt;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class WorldUtil {
	public static boolean checkGameRule(Level world, GameRules.Key<GameRules.BooleanValue> gameRule) {
		return world.getGameRules().getBoolean(gameRule);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, Level world, BlockPos pos, float strength) {
		return createExplosion(exploder, world, pos.getX(), pos.getY(), pos.getZ(), strength, AoAGameRules.checkDestructiveWeaponPhysics(world) ? Level.ExplosionInteraction.BLOCK : Level.ExplosionInteraction.NONE, false);
	}

	public static Explosion createExplosion(@Nonnull Entity exploder, Level world, float strength) {
		return createExplosion(exploder, world, exploder.getX(), exploder.getY(), exploder.getZ(), strength, ForgeEventFactory.getMobGriefingEvent(world, exploder) ? Level.ExplosionInteraction.MOB : Level.ExplosionInteraction.NONE, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, Level world, @Nonnull Entity explodingEntity, float strength) {
		boolean doGriefing;

		if (exploder instanceof Player) {
			doGriefing = AoAGameRules.checkDestructiveWeaponPhysics(world);
		}
		else {
			if (exploder == null)
				exploder = explodingEntity;

			if (exploder instanceof LivingEntity || explodingEntity instanceof LivingEntity) {
				doGriefing = ForgeEventFactory.getMobGriefingEvent(world, exploder);
			}
			else {
				doGriefing = AoAGameRules.checkDestructiveWeaponPhysics(world);
			}
		}

		return createExplosion(exploder, world, explodingEntity.getX(), explodingEntity.getY(), explodingEntity.getZ(), strength, doGriefing ? Level.ExplosionInteraction.MOB : Level.ExplosionInteraction.NONE, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, Level world, double posX, double posY, double posZ, float strength) {
		return createExplosion(exploder, world, posX, posY, posZ, strength, AoAGameRules.checkDestructiveWeaponPhysics(world) ? Level.ExplosionInteraction.BLOCK : Level.ExplosionInteraction.NONE, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, Level world, double posX, double posY, double posZ, float strength, Level.ExplosionInteraction explosionType) {
		return createExplosion(exploder, world, posX, posY, posZ, strength, explosionType, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, Level world, double posX, double posY, double posZ, float strength, Level.ExplosionInteraction explosionType, boolean fieryExplosion) {
		return world.explode(exploder, posX, posY, posZ, strength, fieryExplosion, explosionType);
	}

	public static int getLightLevel(ServerLevelAccessor world, BlockPos position, boolean ignoreSkyLight, boolean ignoreBlockLight) {
		if (position.getY() > world.getMaxBuildHeight()) {
			position = new BlockPos(position.getX(), world.getMaxBuildHeight(), position.getZ());
		}
		else if (position.getY() < world.getMinBuildHeight()) {
			position = new BlockPos(position.getX(), world.getMinBuildHeight(), position.getZ());
		}

		if (ignoreSkyLight)
			return world.getBrightness(LightLayer.BLOCK, position);

		if (ignoreBlockLight)
			return world.getBrightness(LightLayer.SKY, position) - world.getSkyDarken();

		return world.getRawBrightness(position, 0);
	}

	public static void spawnLightning(ServerLevel world, @Nullable ServerPlayer caster, double x, double y, double z, boolean destructive, boolean createFire) {
		CustomisableLightningBolt lightning = new CustomisableLightningBolt(world, x, y, z);

		lightning.setVisualOnly(!destructive);

		if (!createFire)
			lightning.noFire();

		if (caster != null)
			lightning.setCause(caster);

		world.addFreshEntity(lightning);
	}

	public static boolean harvestAdditionalBlock(Level world, Player pl, BlockPos breakPos, boolean forceDropsInCreative) {
		BlockState blockState = world.getBlockState(breakPos);
		Block block = blockState.getBlock();

		if (blockState.isAir() || !world.mayInteract(pl, breakPos))
			return false;

		if (!world.isClientSide()) {
			ServerPlayer player = (ServerPlayer)pl;
			GameType gameMode = player.gameMode.getGameModeForPlayer();
			int blockXp = net.minecraftforge.common.ForgeHooks.onBlockBreakEvent(world, gameMode, player, breakPos);

			if (blockXp == -1)
				return false;

			BlockEntity tileEntity = world.getBlockEntity(breakPos);

			if (!pl.canUseGameMasterBlocks() && (block instanceof CommandBlock || block instanceof StructureBlock || block instanceof JigsawBlock)) {
				world.sendBlockUpdated(breakPos, blockState, blockState, Block.UPDATE_ALL);

				return false;
			}

			if (pl.getMainHandItem().onBlockStartBreak(breakPos, pl) || pl.blockActionRestricted(world, breakPos, gameMode))
				return false;

			if (pl.isCreative()) {
				boolean canHarvest = forceDropsInCreative && blockState.canHarvestBlock(world, breakPos, pl);
				boolean removed = blockState.onDestroyedByPlayer(world, breakPos, player, false, world.getFluidState(breakPos));

				if (removed) {
					blockState.getBlock().destroy(world, breakPos, blockState);

					if (canHarvest)
						block.playerDestroy(world, pl, breakPos, blockState, tileEntity, pl.getMainHandItem().copy());

					if (forceDropsInCreative && blockXp > 0)
						blockState.getBlock().popExperience((ServerLevel)world, breakPos, blockXp);
				}
			}
			else {
				ItemStack toolStack = pl.getMainHandItem();
				ItemStack toolStackCopy = toolStack.copy();
				boolean canHarvest = blockState.canHarvestBlock(world, breakPos, pl);

				if (toolStack.isEmpty() && !toolStackCopy.isEmpty())
					net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(pl, toolStackCopy, InteractionHand.MAIN_HAND);

				boolean removedBlock = blockState.onDestroyedByPlayer(world, breakPos, player, false, world.getFluidState(breakPos));

				if (removedBlock) {
					blockState.getBlock().destroy(world, breakPos, blockState);

					if (canHarvest)
						block.playerDestroy(world, pl, breakPos, blockState, tileEntity, toolStackCopy);

					if (blockXp > 0)
						blockState.getBlock().popExperience((ServerLevel)world, breakPos, blockXp);
				}
			}

			return true;
		}
		else {
			return ClientOperations.harvestAdditionalBlock(breakPos);
		}
	}

	public static float getAmbientTemperature(Level world, BlockPos pos) {
		Holder<Biome> biome = world.getBiome(pos);
		float temp = biome.value().getTemperature(pos);

		if (world.canSeeSky(pos)) {
			if (world.isDay()) {
				temp *= 1.35f;
			}
			else {
				temp /= 1.35f;
			}

			if (world.isRaining()) {
				Biome.Precipitation rainType = biome.value().getPrecipitationAt(pos);

				if (rainType == Biome.Precipitation.SNOW) {
					temp /= 1.5f;
				}
				else if (rainType == Biome.Precipitation.RAIN) {
					temp /= 1.25f;
				}
			}
		}

		return temp;
	}

	public static int getHighestSafeLocation(Level world, int x, int z, boolean allowFluids, int minY) {
		if (Math.abs(x) > 30000000 || Math.abs(z) > 30000000)
			return world.getMinBuildHeight();

		BlockPos.MutableBlockPos testPos = new BlockPos.MutableBlockPos();
		boolean headBlock = false;
		boolean feetBlock = false;

		for (int i = Math.min(world.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z) + 2, world.dimensionType().logicalHeight()); i > minY; i--) {
			BlockState state;

			if ((state = world.getBlockState(testPos.set(x, i, z))).isAir() || (allowFluids && state.getMaterial().isLiquid())) {
				if (headBlock) {
					if (!feetBlock)
						feetBlock = true;
				}
				else {
					headBlock = true;
				}
			}
			else if (!headBlock || !feetBlock) {
				headBlock = false;
				feetBlock = false;
			}
			else {
				return i;
			}
		}

		return minY;
	}

	public static boolean canPlaceBlock(LevelAccessor world, BlockPos pos, @Nullable Entity entity, @Nullable ItemStack stack) {
		if (!(world instanceof Level activeWorld))
			return true;

		Player relevantPlayer = PlayerUtil.getPlayerOrOwnerIfApplicable(entity);

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

				return stack.hasAdventureModePlaceTagForBlock(RegistryUtil.getVanillaRegistry(activeWorld, ForgeRegistries.BLOCKS), new BlockInWorld(activeWorld, pos, false));
			}
		}

		return true;
	}

	public static boolean canModifyBlock(LevelAccessor world, BlockPos pos, @Nullable Entity entity, @Nullable ItemStack stack) {
		if (!(world instanceof Level activeWorld))
			return true;

		Player relevantPlayer = PlayerUtil.getPlayerOrOwnerIfApplicable(entity);

		if (isWorld(activeWorld, AoADimensions.NOWHERE.key))
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

				return stack.hasAdventureModeBreakTagForBlock(RegistryUtil.getVanillaRegistry(activeWorld, ForgeRegistries.BLOCKS), new BlockInWorld(activeWorld, pos, false));
			}
		}

		return true;
	}

	public static void operateOnMultipleBlocksInRange(Level world, BlockPos center, int radius, Predicate<BlockState> test, Consumer<BlockPos> operation) {
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

	public static List<BlockPos> getBlocksWithinAABB(Level world, AABB aabb, @Nullable BiPredicate<BlockState, BlockPos.MutableBlockPos> predicate) {
		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
		List<BlockPos> matches = new ObjectArrayList<>();

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

	public static boolean isWorld(ServerLevelAccessor world, ResourceKey<Level>... keys) {
		for (ResourceKey<Level> key : keys) {
			if (world.getLevel().dimension() == key)
				return true;
		}

		return false;
	}

	public static boolean isWorld(Level world, ResourceKey<Level>... keys) {
		for (ResourceKey<Level> key : keys) {
			if (world.dimension() == key)
				return true;
		}

		return false;
	}

	public static MinecraftServer getServer() {
		return ServerLifecycleHooks.getCurrentServer();
	}
}
