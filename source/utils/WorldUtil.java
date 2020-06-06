package net.tslat.aoa3.utils;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.function.BiPredicate;

public final class WorldUtil {
	public static int getTrueWorldHeight(World world, int x, int z) {
		boolean match1 = false;
		boolean match2 = false;

		try {
			int height;

			if (world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.deeplands) {
				height = 121;
			}
			else if (world.provider.getDimension() == -1) {
				height = 128;
			}
			else if (world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.crystevia) {
				height = 127;
			}
			else {
				return world.getHeight(x, z);
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

	public static boolean isNaturalDimensionBlock(World world, BlockPos pos, IBlockState blockState) {
		Block bl = blockState.getBlock();

		if (bl instanceof BlockStone) {
			BlockStone.EnumType stoneVariant = blockState.getValue(BlockStone.VARIANT);

			return stoneVariant != BlockStone.EnumType.ANDESITE_SMOOTH && stoneVariant != BlockStone.EnumType.DIORITE_SMOOTH && stoneVariant != BlockStone.EnumType.GRANITE_SMOOTH;
		}
		else if (bl instanceof BlockGrass || bl instanceof BlockDirt || bl instanceof BlockSand || bl instanceof BlockGravel || bl instanceof BlockSnowBlock || bl instanceof BlockSnow || bl instanceof BlockIce || bl instanceof BlockOre || bl instanceof BlockRedstoneOre) {
			return true;
		}
		else {
			Biome biome = world.getBiome(pos);

			return bl == biome.topBlock.getBlock() || bl == biome.fillerBlock.getBlock();
		}
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, BlockPos pos, float strength) {
		return createExplosion(exploder, world, pos.getX(), pos.getY(), pos.getZ(), strength, checkGameRule(world, "destructiveWeaponPhysics"), false);
	}

	public static Explosion createExplosion(@Nonnull Entity exploder, World world, float strength) {
		return createExplosion(exploder, world, exploder.posX, exploder.posY, exploder.posZ, strength, checkGameRule(world, "doStrongerMobGriefing"), false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, @Nonnull Entity explodingEntity, float strength) {
		boolean doGriefing;

		if (exploder instanceof EntityPlayer) {
			doGriefing = checkGameRule(world, "destructiveWeaponPhysics");
		}
		else {
			if (exploder == null)
				exploder = explodingEntity;

			if (explodingEntity instanceof BaseMobProjectile || explodingEntity instanceof EntityLivingBase) {
				doGriefing = checkGameRule(world, "doStrongerMobGriefing");
			}
			else {
				doGriefing = checkGameRule(world, "destructiveWeaponPhysics");
			}
		}

		return createExplosion(exploder, world, explodingEntity.posX, explodingEntity.posY, explodingEntity.posZ, strength, doGriefing, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, double posX, double posY, double posZ, float strength) {
		return createExplosion(exploder, world, posX, posY, posZ, strength, checkGameRule(world, "destructiveWeaponPhysics"), false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, double posX, double posY, double posZ, float strength, boolean destructiveExplosion) {
		return createExplosion(exploder, world, posX, posY, posZ, strength, destructiveExplosion, false);
	}

	public static Explosion createExplosion(@Nullable Entity exploder, World world, double posX, double posY, double posZ, float strength, boolean destructiveExplosion, boolean fieryExplosion) {
		return world.newExplosion(exploder, posX, posY, posZ, strength, fieryExplosion, destructiveExplosion);
	}

	@Nullable
	public static Enums.Dimensions getDimensionFromId(int id) {
		if (id == 0) {
			return Enums.Dimensions.OVERWORLD;
		}
		else if (id == -1) {
			return Enums.Dimensions.NETHER;
		}
		else if (id == 1) {
			return Enums.Dimensions.THE_END;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.abyss) {
			return Enums.Dimensions.ABYSS;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.ancientCavern) {
			return Enums.Dimensions.ANCIENT_CAVERN;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.barathos) {
			return Enums.Dimensions.BARATHOS;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.candyland) {
			return Enums.Dimensions.CANDYLAND;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.celeve) {
			return Enums.Dimensions.CELEVE;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.creeponia) {
			return Enums.Dimensions.CREEPONIA;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.crystevia) {
			return Enums.Dimensions.CRYSTEVIA;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.deeplands) {
			return Enums.Dimensions.DEEPLANDS;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.dustopia) {
			return Enums.Dimensions.DUSTOPIA;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.gardencia) {
			return Enums.Dimensions.GARDENCIA;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.greckon) {
			return Enums.Dimensions.GRECKON;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.haven) {
			return Enums.Dimensions.HAVEN;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.immortallis) {
			return Enums.Dimensions.IMMORTALLIS;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.iromine) {
			return Enums.Dimensions.IROMINE;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.lborean) {
			return Enums.Dimensions.LBOREAN;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.lelyetia) {
			return Enums.Dimensions.LELYETIA;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.lunalus) {
			return Enums.Dimensions.LUNALUS;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.mysterium) {
			return Enums.Dimensions.MYSTERIUM;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.precasia) {
			return Enums.Dimensions.PRECASIA;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.runandor) {
			return Enums.Dimensions.RUNANDOR;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.shyrelands) {
			return Enums.Dimensions.SHYRELANDS;
		}
		else if (id == ConfigurationUtil.MainConfig.dimensionIds.voxPonds) {
			return Enums.Dimensions.VOX_PONDS;
		}
		else {
			return null;
		}
	}

	public int blockFlagsToInt(Enums.BlockUpdateFlags... flags) {
		int value = 0;

		for (Enums.BlockUpdateFlags flag : flags) {
			value |= flag.value;
		}

		return value;
	}

	public static boolean checkGameRule(World world, String gameRule) {
		return world.getGameRules().getBoolean(gameRule);
	}

	public static String getGameRuleString(World world, String gameRule) {
		return world.getGameRules().getString(gameRule);
	}

	public static int getGameRuleInt(World world, String gameRule) {
		return world.getGameRules().getInt(gameRule);
	}

	public static int getLightLevel(World world, BlockPos position, boolean ignoreSkyLight, boolean ignoreBlockLight) {
		if (ignoreBlockLight && ignoreSkyLight) {
			ignoreBlockLight = false;
			ignoreSkyLight = false;
		}

		Chunk chunk = world.getChunk(position);

		if (position.getY() > 255) {
			position = new BlockPos(position.getX(), 255, position.getZ());
		}
		else if (position.getY() < 0) {
			position = new BlockPos(position.getX(), 0, position.getZ());
		}

		if (ignoreSkyLight)
			return chunk.getLightFor(EnumSkyBlock.BLOCK, position);

		if (ignoreBlockLight)
			return chunk.getLightFor(EnumSkyBlock.SKY, position);

		return world.getLightFromNeighbors(position);
	}

	public static boolean harvestAdditionalBlock(World world, EntityPlayer pl, ItemStack toolStack, BlockPos originPos, BlockPos breakPos) {
		IBlockState blockState = world.getBlockState(breakPos);
		Block block = blockState.getBlock();

		if (block.isAir(blockState, world, breakPos))
			return false;

		if (ForgeHooks.blockStrength(world.getBlockState(originPos), pl, world, originPos) / ForgeHooks.blockStrength(blockState, pl, world, breakPos) > 10f)
			return false;

		if (!world.isRemote) {
			int blockXp = ForgeHooks.onBlockBreakEvent(world, ((EntityPlayerMP)pl).interactionManager.getGameType(), (EntityPlayerMP)pl, breakPos);

			if (blockXp < 0)
				return false;

			if (block.removedByPlayer(blockState, world, breakPos, pl, true)) {
				toolStack.onBlockDestroyed(world, blockState, breakPos, pl);
				block.onPlayerDestroy(world, breakPos, blockState);
				block.harvestBlock(world, pl, breakPos, blockState, world.getTileEntity(breakPos), toolStack);
				block.dropXpOnBlockBreak(world, breakPos, blockXp);
				((EntityPlayerMP)pl).connection.sendPacket(new SPacketBlockChange(world, breakPos));
			}
		}
		else {
			if (block.removedByPlayer(blockState, world, breakPos, pl, true)) {
				world.playBroadcastSound(2001, breakPos, Block.getStateId(blockState));
				block.onPlayerDestroy(world, breakPos, blockState);
				toolStack.onBlockDestroyed(world, blockState, breakPos, pl);

				if (toolStack.getCount() <= 0 && toolStack == pl.getHeldItemMainhand()) {
					ForgeEventFactory.onPlayerDestroyItem(pl, toolStack, EnumHand.MAIN_HAND);
					pl.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
				}
			}
		}

		return true;
	}

	public static ArrayList<BlockPos> getBlocksWithinAABB(World world, AxisAlignedBB aabb, @Nullable BiPredicate<IBlockState, BlockPos.MutableBlockPos> predicate) {
		BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
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

	public static boolean isOreBlock(Block block) {
		return block instanceof BlockOre || block instanceof BlockRedstoneOre;
	}

	public static boolean isOreBlock(IBlockState block) {
		return isOreBlock(block.getBlock());
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

			if (world.isRaining() && world.getPrecipitationHeight(pos).getY() < pos.getY()) {
				if (biome.getEnableSnow()) {
					temp /= 1.5f;
				}
				else if (biome.canRain()) {
					temp /= 1.25f;
				}
			}
		}

		return temp;
	}
}