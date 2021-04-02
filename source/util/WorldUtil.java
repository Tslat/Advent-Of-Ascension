package net.tslat.aoa3.util;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class WorldUtil {
	public static boolean checkGameRule(World world, GameRules.RuleKey<GameRules.BooleanValue> gameRule) {
		return world.getGameRules().getBoolean(gameRule);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, BlockPos pos, float strength) {
		return createExplosion(exploder, world, pos.getX(), pos.getY(), pos.getZ(), strength, checkGameRule(world, AoAGameRules.DESTRUCTIVE_WEAPON_PHYSICS) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
	}

	public static Explosion createExplosion(@Nonnull Entity exploder, World world, float strength) {
		return createExplosion(exploder, world, exploder.getX(), exploder.getY(), exploder.getZ(), strength, checkGameRule(world, AoAGameRules.STRONGER_MOB_GRIEFING) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, @Nonnull Entity explodingEntity, float strength) {
		boolean doGriefing;

		if (exploder instanceof PlayerEntity) {
			doGriefing = checkGameRule(world, AoAGameRules.DESTRUCTIVE_WEAPON_PHYSICS);
		}
		else {
			if (exploder instanceof LivingEntity || explodingEntity instanceof LivingEntity) {
				doGriefing = checkGameRule(world, AoAGameRules.STRONGER_MOB_GRIEFING);
			}
			else {
				doGriefing = checkGameRule(world, AoAGameRules.DESTRUCTIVE_WEAPON_PHYSICS);
			}

			if (exploder == null)
				exploder = explodingEntity;
		}

		return createExplosion(exploder, world, explodingEntity.getX(), explodingEntity.getY(), explodingEntity.getZ(), strength, doGriefing ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, double posX, double posY, double posZ, float strength) {
		return createExplosion(exploder, world, posX, posY, posZ, strength, checkGameRule(world, AoAGameRules.DESTRUCTIVE_WEAPON_PHYSICS) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
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

	public static boolean harvestAdditionalBlock(World world, PlayerEntity pl, BlockPos breakPos, boolean damageTool) {
		BlockState blockState = world.getBlockState(breakPos);
		Block block = blockState.getBlock();

		if (block.isAir(blockState, world, breakPos))
			return false;

		if (!world.isClientSide) {
			ServerPlayerEntity player = (ServerPlayerEntity)pl;
			GameType gameType = player.gameMode.getGameModeForPlayer();
			int exp = ForgeHooks.onBlockBreakEvent(world, gameType, player, breakPos);

			if (exp == -1)
				return false;

			if ((block instanceof CommandBlockBlock || block instanceof StructureBlock || block instanceof JigsawBlock) && !pl.canUseGameMasterBlocks()) {
				world.sendBlockUpdated(breakPos, blockState, blockState, 3);

				return false;
			}
			else if (pl.getMainHandItem().onBlockStartBreak(breakPos, pl) || pl.blockActionRestricted(world, breakPos, gameType)) {
				return false;
			}
			else {
				if (pl.isCreative()) {
					if (blockState.removedByPlayer(world, breakPos, player, false, world.getFluidState(breakPos)))
						block.destroy(world, breakPos, blockState);
				}
				else {
					ItemStack heldStack = pl.getMainHandItem();
					ItemStack clonedStack = heldStack.copy();

					boolean canHarvest = blockState.canHarvestBlock(world, breakPos, pl);

					if (damageTool && blockState.getDestroySpeed(world, breakPos) != 0)
						heldStack.hurtAndBreak(1, pl, (harvester) -> harvester.broadcastBreakEvent(EquipmentSlotType.MAINHAND));

					if (heldStack.isEmpty() && !clonedStack.isEmpty())
						ForgeEventFactory.onPlayerDestroyItem(pl, clonedStack, Hand.MAIN_HAND);

					boolean removed = blockState.removedByPlayer(world, breakPos, player, canHarvest, world.getFluidState(breakPos));

					if (removed) {
						block.destroy(world, breakPos, blockState);

						if (canHarvest)
							block.playerDestroy(world, pl, breakPos, blockState, world.getBlockEntity(breakPos), clonedStack);

						if (exp > 0)
							blockState.getBlock().popExperience((ServerWorld)world, breakPos, exp);
					}
				}

				return true;
			}
		}

		return true;
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

	public static void toAir(World world, BlockPos... positions) {
		for (BlockPos pos : positions) {
			world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
		}
	}

	public static boolean canModifyBlock(IWorld world, BlockPos pos, @Nullable Entity entity) {
		if (!(world instanceof World))
			return true;

		PlayerEntity relevantPlayer = PlayerUtil.getPlayerOrOwnerIfApplicable(entity);

		if (WorldUtil.isWorld((World)world, AoADimensions.NOWHERE.key))
			return relevantPlayer != null && relevantPlayer.isCreative();

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
