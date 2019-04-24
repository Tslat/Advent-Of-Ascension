package net.tslat.aoa3.block.decoration.banner;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class BannerBlock extends Block {
	private static final PropertyEnum<BannerType> TYPE = PropertyEnum.<BannerType>create("type", BannerType.class);

	private static final AxisAlignedBB MOUNTED_NORTH_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 0.15625);
	private static final AxisAlignedBB MOUNTED_SOUTH_AABB = new AxisAlignedBB(0, 0, 0.84375, 1, 1, 1);
	private static final AxisAlignedBB MOUNTED_EAST_AABB = new AxisAlignedBB(0.84375, 0, 0, 1, 1, 1);
	private static final AxisAlignedBB MOUNTED_WEST_AABB = new AxisAlignedBB(0, 0, 0, 0.15625, 1, 1);

	private static final AxisAlignedBB STANDING_NORTH_AABB = new AxisAlignedBB(0.1875, 0, 0.453125, 0.8125, 1, 0.609375);
	private static final AxisAlignedBB STANDING_SOUTH_AABB = new AxisAlignedBB(0.1875, 0, 0.390625, 0.8125, 1, 0.546875);
	private static final AxisAlignedBB STANDING_EAST_AABB = new AxisAlignedBB(0.390625, 0, 0.1875, 0.546875, 1, 0.8125);
	private static final AxisAlignedBB STANDING_WEST_AABB = new AxisAlignedBB(0.453125, 0, 0.1875, 0.609375, 1, 0.8125);

	public BannerBlock(String name, String registryName) {
		super(Material.CLOTH);
		setUnlocalizedName(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(1.5f);
		setResistance(1.0f);
		setSoundType(SoundType.CLOTH);
		setLightOpacity(0);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, BannerType.MOUNTED).withProperty(BlockHorizontal.FACING, EnumFacing.NORTH));
		setCreativeTab(CreativeTabsRegister.bannersTab);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean getUseNeighborBrightness(IBlockState state) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (state.getValue(TYPE) == BannerType.STANDING) {
			switch (state.getValue(BlockHorizontal.FACING)) {
				case NORTH:
					return STANDING_NORTH_AABB;
				case SOUTH:
					return STANDING_SOUTH_AABB;
				case EAST:
					return STANDING_EAST_AABB;
				case WEST:
					return STANDING_WEST_AABB;
			}
		}
		else {
			switch (state.getValue(BlockHorizontal.FACING)) {
				case NORTH:
					return MOUNTED_NORTH_AABB;
				case SOUTH:
					return MOUNTED_SOUTH_AABB;
				case EAST:
					return MOUNTED_EAST_AABB;
				case WEST:
					return MOUNTED_WEST_AABB;
			}
		}

		return new AxisAlignedBB(0, 0, 0, 1, 1, 1);
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (state.getValue(TYPE) == BannerType.MOUNTED) {
			if (!worldIn.getBlockState(pos.offset(state.getValue(BlockHorizontal.FACING))).getMaterial().isSolid()) {
				dropBlockAsItem(worldIn, pos, state, 0);
				worldIn.setBlockToAir(pos);
			}
		}
		else {
			if (!worldIn.getBlockState(pos.down()).getMaterial().isSolid()) {
				dropBlockAsItem(worldIn, pos, state, 0);
				worldIn.setBlockToAir(pos);
			}
		}

		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
		switch (side) {
			case NORTH:
			case SOUTH:
			case EAST:
			case WEST:
				if (!world.getBlockState(pos.offset(side.getOpposite())).isFullCube())
					return false;

				if (!world.getBlockState(pos.down()).getMaterial().isReplaceable())
					return false;
				break;
			case DOWN:
				return false;
			case UP:
				if (!world.getBlockState(pos.up()).getMaterial().isReplaceable())
					return false;
		}

		return true;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		switch (facing) {
			case SOUTH:
			case NORTH:
			case WEST:
			case EAST:
				return getDefaultState().withProperty(TYPE, BannerType.MOUNTED).withProperty(BlockHorizontal.FACING, facing.getOpposite());
			case UP:
			case DOWN:
			default:
				return getDefaultState().withProperty(TYPE, BannerType.STANDING).withProperty(BlockHorizontal.FACING, EntityUtil.getDirectionFacing(placer, true));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BannerBlock.TYPE, BlockHorizontal.FACING);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = state.getValue(TYPE).getMetaMuliplier();

		switch (state.getValue(BlockHorizontal.FACING)) {
			case NORTH:
				return meta;
			case SOUTH:
				return meta * 3;
			case EAST:
				return meta * 5;
			case WEST:
				return meta * 7;
		}

		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState();

		if (meta % 2 == 0) {
			state = state.withProperty(TYPE, BannerType.STANDING);

			switch (meta / 2) {
				case 0:
				case 1:
					return state.withProperty(BlockHorizontal.FACING, EnumFacing.NORTH);
				case 3:
					return state.withProperty(BlockHorizontal.FACING, EnumFacing.SOUTH);
				case 5:
					return state.withProperty(BlockHorizontal.FACING, EnumFacing.EAST);
				case 7:
					return state.withProperty(BlockHorizontal.FACING, EnumFacing.WEST);
			}
		}
		else {
			state = state.withProperty(TYPE, BannerType.MOUNTED);

			switch (meta) {
				case 1:
					return state.withProperty(BlockHorizontal.FACING, EnumFacing.NORTH);
				case 3:
					return state.withProperty(BlockHorizontal.FACING, EnumFacing.SOUTH);
				case 5:
					return state.withProperty(BlockHorizontal.FACING, EnumFacing.EAST);
				case 7:
					return state.withProperty(BlockHorizontal.FACING, EnumFacing.WEST);
			}
		}

		return state.withProperty(TYPE, BannerType.STANDING).withProperty(TYPE, BannerType.MOUNTED);
	}

	private enum BannerType implements IStringSerializable {
		MOUNTED("mounted"),
		STANDING("standing");

		private final String name;

		private BannerType(String name) {
			this.name = name;
		}

		public String toString() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}

		public int getMetaMuliplier() {
			return this == MOUNTED ? 1 : 2;
		}

		public static BannerType getByMeta(int meta) {
			return meta == 1 ? MOUNTED : STANDING;
		}
	}
}
