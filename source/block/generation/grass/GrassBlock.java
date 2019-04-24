package net.tslat.aoa3.block.generation.grass;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

import java.util.Random;

public class GrassBlock extends BlockGrass {
	protected final Block dirtBlock;

	public GrassBlock(String name, String registryName, Block dirtBlock) {
		super();
		setUnlocalizedName(name);
		setRegistryName(registryName);
		setHardness(0.6f);
		setSoundType(SoundType.PLANT);
		setCreativeTab(CreativeTabsRegister.generationBlocksTab);
		setHarvestLevel("shovel", 0);
		this.dirtBlock = dirtBlock;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote) {
			if (!world.isAreaLoaded(pos, 3))
				return;

			if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockState(pos.up()).getBlock().getLightOpacity(world.getBlockState(pos.up()), world, pos.up()) > 2) {
				world.setBlockState(pos, dirtBlock.getDefaultState());
			}
			else {
				for (int i = 0; i < 4; i++) {
					BlockPos newPos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

					if (newPos.getY() <= 0 || newPos.getY() >= 256 || !world.isBlockLoaded(newPos))
						return;

					IBlockState targetBlockState = world.getBlockState(newPos);

					if (targetBlockState.getBlock() == dirtBlock && world.getLightFromNeighbors(newPos.up()) >= 4 && world.getBlockState(newPos.up()).getLightOpacity(world, pos.up()) <= 2)
						world.setBlockState(newPos, getDefaultState());
				}
			}
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(dirtBlock);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}
}