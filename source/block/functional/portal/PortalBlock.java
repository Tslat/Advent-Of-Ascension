package net.tslat.aoa3.block.functional.portal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.block.UnbreakableBlock;
import net.tslat.aoa3.block.functional.misc.CarvedRunicPortalBlock;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.dimension.AoAWorldProvider;
import net.tslat.aoa3.dimension.nether.NetherTeleporter;
import net.tslat.aoa3.library.misc.PortalCoordinatesContainer;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.Random;

public class PortalBlock extends UnbreakableBlock {
	private static final AxisAlignedBB latAABB = new AxisAlignedBB(0.375, 0, 0, 0.625, 1, 1);
	private static final AxisAlignedBB longAABB = new AxisAlignedBB(0, 0, 0.375, 1, 1, 0.625);
	private final int dimId;
	private final int particleColour;

	public PortalBlock(String name, String registryName, int dimId, int particleColour) {
		super(name, registryName, Material.PORTAL);
		this.dimId = dimId;
		this.particleColour = particleColour;
		setCreativeTab(null);
		setDefaultState(this.blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch(state.getValue(BlockHorizontal.FACING)) {
			case NORTH:
			case SOUTH:
				return longAABB;
			case EAST:
			case WEST:
			default:
				return latAABB;
		}
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return NULL_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing side) {
		IBlockState adjacent = access.getBlockState(pos.offset(side));

		if (adjacent.getBlock() == this) {
			EnumFacing direction;

			switch (state.getValue(BlockHorizontal.FACING)) {
				case NORTH:
				case SOUTH:
					direction = adjacent.getValue(BlockHorizontal.FACING);

					if (direction == EnumFacing.SOUTH || direction == EnumFacing.NORTH)
						return false;

					break;
				case EAST:
				case WEST:
					direction = adjacent.getValue(BlockHorizontal.FACING);

					if (direction == EnumFacing.EAST || direction == EnumFacing.WEST)
						return false;

					break;
				default:
					break;
			}
		}

		return true;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean getUseNeighborBrightness(IBlockState state) {
		return true;
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block changedBlock, BlockPos changedPos) {
		if (isCompatibleNeighbour(changedBlock.getDefaultState())) {
			EnumFacing facing = state.getValue(BlockHorizontal.FACING);

			switch (facing) {
				case NORTH:
				case SOUTH:
					if (!isCompatibleNeighbour(world.getBlockState(pos.up())) || !isCompatibleNeighbour(world.getBlockState(pos.down()))
							|| !isCompatibleNeighbour(world.getBlockState(pos.east())) || !isCompatibleNeighbour(world.getBlockState(pos.west()))) {
						world.setBlockToAir(pos);
					}
					break;
				case EAST:
				case WEST:
					if (!isCompatibleNeighbour(world.getBlockState(pos.up())) || !isCompatibleNeighbour(world.getBlockState(pos.down()))
							|| !isCompatibleNeighbour(world.getBlockState(pos.north())) || !isCompatibleNeighbour(world.getBlockState(pos.south()))) {
						world.setBlockToAir(pos);
					}
					break;
			}
		}
	}

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		if (world.isAirBlock(pos.up()) || world.isAirBlock(pos.down())) {
			world.setBlockToAir(pos);

			return;
		}

		switch (world.getBlockState(pos).getValue(BlockHorizontal.FACING)) {
			case NORTH:
			case SOUTH:
				if (world.isAirBlock(pos.east()) || world.isAirBlock(pos.west()))
					world.setBlockToAir(pos);
				break;
			case EAST:
			case WEST:
				if (world.isAirBlock(pos.north()) || world.isAirBlock(pos.south()))
					world.setBlockToAir(pos);
				break;
		}
	}

	private boolean isCompatibleNeighbour(IBlockState state) {
		Block bl = state.getBlock();

		return bl == BlockRegister.ANCIENT_ROCK || bl instanceof CarvedRunicPortalBlock || bl == this;
	}

	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		WorldProvider provider;
		ITeleporter teleporter;

		if (!world.isRemote && !entity.isRiding() && !entity.isBeingRidden()) {
			if (!ConfigurationUtil.MainConfig.allowNonPlayerPortalTravel & !(entity instanceof EntityPlayer))
				return;

			if (entity.timeUntilPortal > 0) {
				entity.timeUntilPortal = 30;

				return;
			}

			provider = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(dimId).provider;
			teleporter = provider instanceof AoAWorldProvider ? ((AoAWorldProvider)provider).getTeleporter((WorldServer)world) : dimId == -1 ? new NetherTeleporter((WorldServer)world) : ((WorldServer)world).getDefaultTeleporter();
			PortalCoordinatesContainer portalLoc = null;

			if (entity instanceof EntityPlayer) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((EntityPlayerMP)entity);
				portalLoc = plData.getPortalReturnLocation(world.provider.getDimension());

				((EntityPlayerMP)entity).connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), entity.rotationYaw, entity.rotationPitch);
			}
			else {
				entity.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
			}

			if (portalLoc == null) {
				if (world.provider.getDimension() == dimId) {
					entity = entity.changeDimension(0, teleporter);
				}
				else {
					entity = entity.changeDimension(dimId, teleporter);
				}
			}
			else if (world.provider.getDimension() != dimId) {
				entity = entity.changeDimension(dimId, teleporter);
			}
			else {
				entity = entity.changeDimension(portalLoc.fromDim, teleporter);
			}

			if (entity != null)
				entity.timeUntilPortal = 100;
		}
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getDefaultState().withProperty(BlockHorizontal.FACING, EntityUtil.getDirectionFacing(placer, true));
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return ItemStack.EMPTY;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockHorizontal.FACING);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.byHorizontalIndex(meta));
	}

	public int getParticleColour() {
		return particleColour;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState blockState, World world, BlockPos pos, Random rand) {
		for (int i = 0; i < 4; ++i) {
			double posXStart = (float)pos.getX() + rand.nextFloat();
			double posYStart = (float)pos.getY() + rand.nextFloat();
			double posZStart = (float)pos.getZ() + rand.nextFloat();
			double motionX = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double motionY = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			double motionZ = ((double)rand.nextFloat() - 0.5D) * 0.5D;
			int randomMod = rand.nextInt(2) * 2 - 1;

			if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
				posXStart = (double)pos.getX() + 0.5D + 0.25D * (double)randomMod;
				motionX = rand.nextFloat() * 2.0F * (float)randomMod;
			}
			else {
				posZStart = (double)pos.getZ() + 0.5D + 0.25D * (double)randomMod;
				motionZ = rand.nextFloat() * 2.0F * (float)randomMod;
			}

			world.spawnParticle(ParticleRegister.PORTAL_FLOATER, posXStart, posYStart, posZStart, motionX, motionY, motionZ, getParticleColour(), 100);
		}
	}
}
