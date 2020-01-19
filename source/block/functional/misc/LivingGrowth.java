package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.entity.boss.vinocorne.EntityVinocorne;

import java.util.Random;

public class LivingGrowth extends Block {
	private static final PropertyInteger GROWTH_STAGE = PropertyInteger.create("growth", 0, 5);
	private static final AxisAlignedBB[] AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375),
			new AxisAlignedBB(0.1875, 0, 0.1875, 0.8125, 0.5, 0.8125),
			new AxisAlignedBB(0.0625, 0, 0.0625, 0.9375, 0.875, 0.9375),
			new AxisAlignedBB(0.0625, 0, 0.0625, 0.9375, 1.4375, 0.9375),
			new AxisAlignedBB(0, 0, 0, 1, 1.625, 1),
			new AxisAlignedBB(0, 0, 0, 1, 1.625, 1)};

	public LivingGrowth() {
		super(Material.PLANTS);
		setTranslationKey("LivingGrowth");
		setRegistryName("aoa3:living_growth");
		setHardness(1f);
		setResistance(0f);
		setCreativeTab(null);
		setSoundType(SoundType.PLANT);
		setDefaultState(getDefaultState().withProperty(GROWTH_STAGE, 0));
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return ItemStack.EMPTY;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB[state.getValue(GROWTH_STAGE)];
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);

		int currentAge = state.getValue(GROWTH_STAGE);

		if (currentAge < 6) {
			if (currentAge == 5) {
				EntityVinocorne vinocorne = new EntityVinocorne(world);

				vinocorne.setPosition(pos.getX(), pos.getY(), pos.getZ());
				world.spawnEntity(vinocorne);
				world.setBlockToAir(pos);
			}
			else {
				world.setBlockState(pos, getDefaultState().withProperty(GROWTH_STAGE, currentAge + 1));
				world.scheduleUpdate(pos, state.getBlock(), 40);
			}
		}
	}

	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		if (world.getBlockState(pos.down()).getBlock() != BlockRegister.vinocorneShrine)
			((World)world).setBlockToAir(pos);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, GROWTH_STAGE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(GROWTH_STAGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(GROWTH_STAGE);
	}
}
