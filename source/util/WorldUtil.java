package net.tslat.aoa3.util;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
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
		return createExplosion(exploder, world, exploder.getPosX(), exploder.getPosY(), exploder.getPosZ(), strength, checkGameRule(world, AoAGameRules.STRONGER_MOB_GRIEFING) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
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

		return createExplosion(exploder, world, explodingEntity.getPosX(), explodingEntity.getPosY(), explodingEntity.getPosZ(), strength, doGriefing ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, double posX, double posY, double posZ, float strength) {
		return createExplosion(exploder, world, posX, posY, posZ, strength, checkGameRule(world, AoAGameRules.DESTRUCTIVE_WEAPON_PHYSICS) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, double posX, double posY, double posZ, float strength, Explosion.Mode explosionType) {
		return createExplosion(exploder, world, posX, posY, posZ, strength, explosionType, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, double posX, double posY, double posZ, float strength, Explosion.Mode explosionType, boolean fieryExplosion) {
		return world.createExplosion(exploder, posX, posY, posZ, strength, fieryExplosion, explosionType);
	}

	public static int getLightLevel(World world, BlockPos position, boolean ignoreSkyLight, boolean ignoreBlockLight) {
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
			return world.getLightFor(LightType.BLOCK, position);

		if (ignoreBlockLight)
			return world.getLightFor(LightType.SKY, position) - world.getSkylightSubtracted();

		return world.getLightSubtracted(position, 0);
	}

	public static void spawnLightning(ServerWorld world, @Nullable ServerPlayerEntity caster, double x, double y, double z, boolean destructive) {
		LightningBoltEntity lightning = new LightningBoltEntity(world, x, y, z, !destructive);

		if (caster != null)
			lightning.setCaster(caster);

		world.addLightningBolt(lightning);
	}

	public static boolean harvestAdditionalBlock(World world, PlayerEntity pl, BlockPos breakPos, boolean damageTool) {
		BlockState blockState = world.getBlockState(breakPos);
		Block block = blockState.getBlock();

		if (block.isAir(blockState, world, breakPos))
			return false;

		if (!world.isRemote) {
			ServerPlayerEntity player = (ServerPlayerEntity)pl;
			GameType gameType = player.interactionManager.getGameType();
			int exp = ForgeHooks.onBlockBreakEvent(world, gameType, player, breakPos);

			if (exp == -1)
				return false;

			if ((block instanceof CommandBlockBlock || block instanceof StructureBlock || block instanceof JigsawBlock) && !pl.canUseCommandBlock()) {
				world.notifyBlockUpdate(breakPos, blockState, blockState, 3);

				return false;
			}
			else if (pl.getHeldItemMainhand().onBlockStartBreak(breakPos, pl) || pl.blockActionRestricted(world, breakPos, gameType)) {
				return false;
			}
			else {
				if (pl.isCreative()) {
					if (blockState.removedByPlayer(world, breakPos, player, false, world.getFluidState(breakPos)))
						block.onPlayerDestroy(world, breakPos, blockState);
				}
				else {
					ItemStack heldStack = pl.getHeldItemMainhand();
					ItemStack clonedStack = heldStack.copy();

					boolean canHarvest = blockState.canHarvestBlock(world, breakPos, pl);

					if (damageTool && blockState.getBlockHardness(world, breakPos) != 0)
						heldStack.damageItem(1, pl, (harvester) -> harvester.sendBreakAnimation(EquipmentSlotType.MAINHAND));

					if (heldStack.isEmpty() && !clonedStack.isEmpty())
						ForgeEventFactory.onPlayerDestroyItem(pl, clonedStack, Hand.MAIN_HAND);

					boolean removed = blockState.removedByPlayer(world, breakPos, player, canHarvest, world.getFluidState(breakPos));

					if (removed) {
						block.onPlayerDestroy(world, breakPos, blockState);

						if (canHarvest)
							block.harvestBlock(world, pl, breakPos, blockState, world.getTileEntity(breakPos), clonedStack);

						if (exp > 0)
							blockState.getBlock().dropXpOnBlockBreak(world, breakPos, exp);
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
			if (world.isDaytime()) {
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

		for (PlayerEntity player : world.getPlayers()) {
			if (region.contains(player.getPositionVec()))
				players.add(player);
		}

		return players;
	}

	public static int getTrueWorldHeight(World world, int x, int z) {
		boolean match1 = false;
		boolean match2 = false;

		try {
			int height;

			if (world.getDimension().getType() == AoADimensions.DEEPLANDS.type()) {
				height = 121;
			}
			else if (world.getDimension().getType() == DimensionType.THE_NETHER) {
				height = 128;
			}
			else if (world.getDimension().getType() == AoADimensions.CRYSTEVIA.type()) {
				height = 127;
			}
			else {
				return world.getHeight(Heightmap.Type.MOTION_BLOCKING, x, z);
			}

			if (Math.abs(x) > 30000000 || Math.abs(z) > 30000000)
				return 0;

			for (int i = height; i > 0; i--) {
				if (world.getBlockState(new BlockPos(x, i, z)).getBlock() == Blocks.AIR) {
					if (match1) {
						if (!match2)
							match2 = true;
					}
					else {
						match1 = true;
					}
				}
				else {
					if (match1 && match2)
						return i;

					match1 = false;
					match2 = false;
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
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
	}

	public static boolean canModifyBlock(IWorld world, BlockPos pos, @Nullable Entity entity) {
		DimensionType dimType = world.getDimension().getType();
		PlayerEntity relevantPlayer = PlayerUtil.getPlayerOrOwnerIfApplicable(entity);

		if (dimType == AoADimensions.IMMORTALLIS.type() || dimType == AoADimensions.ANCIENT_CAVERN.type()) {
			if (relevantPlayer == null || !relevantPlayer.isCreative())
				return false;
		}

		return relevantPlayer == null || world.getWorld().canMineBlockBody(relevantPlayer, pos);
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
					checkPos.setPos(x, y, z);

					if (predicate == null || predicate.test(world.getBlockState(checkPos), checkPos))
						matches.add(checkPos.toImmutable());
				}
			}
		}

		return matches;
	}
}
