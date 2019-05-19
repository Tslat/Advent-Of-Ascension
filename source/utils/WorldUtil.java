package net.tslat.aoa3.utils;

import net.minecraft.block.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldUtil {
	public static int getTrueWorldHeight(World world, int x, int z) {
		Block bl;
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

	public static boolean isBlockProtectedWorld(int id) {
		return id == ConfigurationUtil.MainConfig.dimensionIds.immortallis || id == ConfigurationUtil.MainConfig.dimensionIds.ancientCavern;
	}

	public static boolean isNaturalOverworldBlock(IBlockState blockState) {
		Block bl = blockState.getBlock();

		if (bl instanceof BlockStone) {
			BlockStone.EnumType stoneVariant = blockState.getValue(BlockStone.VARIANT);

			return stoneVariant != BlockStone.EnumType.ANDESITE_SMOOTH && stoneVariant != BlockStone.EnumType.DIORITE_SMOOTH && stoneVariant != BlockStone.EnumType.GRANITE_SMOOTH;

		}
		else {
			return bl instanceof BlockGrass || bl instanceof BlockDirt || bl instanceof BlockSand || bl instanceof BlockGravel || bl instanceof BlockSnowBlock || bl instanceof BlockSnow || bl instanceof BlockIce || bl instanceof BlockOre || bl instanceof BlockRedstoneOre;
		}
	}
}
