package net.tslat.aoa3.block;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.utils.EntityUtil;

import java.util.Random;

public class DirectionalBlock extends BasicNonCubeBlock {
	public DirectionalBlock(String name, String registryName, Material material, float hardness, float resistance) {
		super(name, registryName, material, hardness, resistance);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockDirectional.FACING, EnumFacing.NORTH));
	}

	public DirectionalBlock(String name, String registryName, Material material) {
		super(name, registryName, material);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getDefaultState().withProperty(BlockDirectional.FACING, EntityUtil.getDirectionFacing(placer, false));
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this));
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockDirectional.FACING);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockDirectional.FACING).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.getFront(meta));
	}
}
