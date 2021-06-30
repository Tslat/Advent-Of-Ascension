package net.tslat.aoa3.block.functional.portal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.ITeleporter;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.particletype.PortalFloaterParticleType;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.world.teleporter.PortalCoordinatesContainer;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.world.teleporter.specific.AbyssTeleporter;
import net.tslat.aoa3.world.teleporter.specific.BarathosTeleporter;
import net.tslat.aoa3.world.teleporter.specific.CandylandTeleporter;
import net.tslat.aoa3.world.teleporter.specific.CeleveTeleporter;
import net.tslat.aoa3.world.teleporter.specific.CreeponiaTeleporter;
import net.tslat.aoa3.world.teleporter.specific.CrysteviaTeleporter;
import net.tslat.aoa3.world.teleporter.specific.DeeplandsTeleporter;
import net.tslat.aoa3.world.teleporter.specific.DustopiaTeleporter;
import net.tslat.aoa3.world.teleporter.specific.GardenciaTeleporter;
import net.tslat.aoa3.world.teleporter.specific.GreckonTeleporter;
import net.tslat.aoa3.world.teleporter.specific.HavenTeleporter;
import net.tslat.aoa3.world.teleporter.specific.IromineTeleporter;
import net.tslat.aoa3.world.teleporter.specific.LBoreanTeleporter;
import net.tslat.aoa3.world.teleporter.specific.LelyetiaTeleporter;
import net.tslat.aoa3.world.teleporter.specific.LunalusTeleporter;
import net.tslat.aoa3.world.teleporter.specific.MysteriumTeleporter;
import net.tslat.aoa3.world.teleporter.specific.NetherTeleporter;
import net.tslat.aoa3.world.teleporter.specific.NowhereTeleporter;
import net.tslat.aoa3.world.teleporter.specific.PrecasiaTeleporter;
import net.tslat.aoa3.world.teleporter.specific.RunandorTeleporter;
import net.tslat.aoa3.world.teleporter.specific.ShyrelandsTeleporter;
import net.tslat.aoa3.world.teleporter.specific.VoxPondsTeleporter;

import javax.annotation.Nullable;
import java.util.Random;

public class PortalBlock extends Block {
	private static final VoxelShape X_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.375, 0, 0, 0.625, 1, 1));
	private static final VoxelShape Z_SHAPE = VoxelShapes.create(new AxisAlignedBB(0, 0, 0.375, 1, 1, 0.625));

	private final int particleColour;
	private final RegistryKey<World> world;

	public PortalBlock(RegistryKey<World> world, MaterialColor mapColour, int particleColour) {
		super(new BlockUtil.CompactProperties(Material.PORTAL, mapColour).unbreakable().light(11).sound(SoundType.GLASS).noClip().get());

		registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X));

		this.particleColour = particleColour;
		this.world = world;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.getValue(BlockStateProperties.HORIZONTAL_AXIS)) {
			case Z:
				return Z_SHAPE;
			case X:
			default:
				return X_SHAPE;
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_AXIS, EntityUtil.getDirectionFacing(context.getPlayer(), true).getAxis());
	}

	private boolean isCompatibleNeighbour(World world, BlockPos pos) {
		BlockState block = world.getBlockState(pos);

		return block.getBlock() == this || !block.getBlock().isAir(block, world, pos);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean skipRendering(BlockState state, BlockState adjacent, Direction side) {
		if (adjacent.getBlock() == this) {
			Direction.Axis axis;

			switch (state.getValue(BlockStateProperties.HORIZONTAL_AXIS)) {
				case X:
					axis = adjacent.getValue(BlockStateProperties.HORIZONTAL_AXIS);

					if (axis == Direction.Axis.X)
						return true;

					break;
				case Z:
					axis = adjacent.getValue(BlockStateProperties.HORIZONTAL_AXIS);

					if (axis == Direction.Axis.Z)
						return true;

					break;
				default:
					break;
			}
		}

		return false;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
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

			world.addParticle(new PortalFloaterParticleType.Data(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), particleColour), posXStart, posYStart, posZStart, motionX, motionY, motionZ);
		}
	}

	@Override
	public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!world.isClientSide() && !entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
			if (!AoAConfig.SERVER.allowNonPlayerPortalTravel.get() & !(entity instanceof PlayerEntity))
				return;

			if (entity.portalTime > 0) {
				entity.portalTime = 30;

				return;
			}

			if (this.world == null || !world.getServer().isNetherEnabled())
				return;

			ITeleporter teleporter = this.world == World.NETHER ? new NetherTeleporter() : getTeleporterForWorld(world.getServer().getLevel(this.world));
			PortalCoordinatesContainer portalLoc = null;

			if (entity instanceof PlayerEntity) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity);
				portalLoc = plData.getPortalReturnLocation(world.dimension());

				((ServerPlayerEntity)entity).connection.teleport(pos.getX(), pos.getY(), pos.getZ(), entity.yRot, entity.xRot);

				if (portalLoc != null && world.getServer().getLevel(portalLoc.fromDim) == null)
					portalLoc = null;
			}
			else {
				entity.teleportTo(pos.getX(), pos.getY(), pos.getZ());
			}

			if (portalLoc == null) {
				if (world.dimension() == this.world) {
					entity = entity.changeDimension(world.getServer().getLevel(World.OVERWORLD), teleporter);
				}
				else {
					entity = entity.changeDimension(world.getServer().getLevel(this.world), teleporter);
				}
			}
			else if (world.dimension() != this.world) {
				entity = entity.changeDimension(world.getServer().getLevel(this.world), teleporter);
			}
			else {
				entity = entity.changeDimension(world.getServer().getLevel(portalLoc.fromDim), teleporter);
			}

			if (entity != null)
				entity.portalTime = 100;
		}
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		switch(rot) {
			case COUNTERCLOCKWISE_90:
			case CLOCKWISE_90:
				switch(state.getValue(BlockStateProperties.HORIZONTAL_AXIS)) {
					case Z:
						return state.setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X);
					case X:
						return state.setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z);
					default:
						return state;
				}
			default:
				return state;
		}
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_AXIS);
	}

	@Override
	public void attack(BlockState state, World world, BlockPos pos, PlayerEntity player) {
		if (world.isEmptyBlock(pos.above()) || world.isEmptyBlock(pos.below())) {
			world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());

			return;
		}

		switch (world.getBlockState(pos).getValue(BlockStateProperties.HORIZONTAL_AXIS)) {
			case Z:
				if (world.isEmptyBlock(pos.east()) || world.isEmptyBlock(pos.west()))
					world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
				break;
			case X:
				if (world.isEmptyBlock(pos.north()) || world.isEmptyBlock(pos.south()))
					world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
				break;
		}
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		Direction.Axis facing = state.getValue(BlockStateProperties.HORIZONTAL_AXIS);

		switch (facing) {
			case Z:
				if (!isCompatibleNeighbour(world, pos.above()) || !isCompatibleNeighbour(world, pos.below()) || !isCompatibleNeighbour(world, pos.east()) || !isCompatibleNeighbour(world, pos.west()))
					world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
				break;
			case X:
				if (!isCompatibleNeighbour(world, pos.above()) || !isCompatibleNeighbour(world, pos.below()) || !isCompatibleNeighbour(world, pos.north()) || !isCompatibleNeighbour(world, pos.south()))
					world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
				break;
		}
	}

	public static ITeleporter getTeleporterForWorld(ServerWorld world) {
		if (!world.dimension().location().getNamespace().equals(AdventOfAscension.MOD_ID))
			return world.getPortalForcer();

		AoADimensions.AoADimension aoaDim = AoADimensions.getDim(world.dimension());

		if (aoaDim == null)
			return world.getPortalForcer();

		switch (aoaDim) {
			case ABYSS:
				return new AbyssTeleporter();
			case BARATHOS:
				return new BarathosTeleporter();
			case CANDYLAND:
				return new CandylandTeleporter();
			case CELEVE:
				return new CeleveTeleporter();
			case CREEPONIA:
				return new CreeponiaTeleporter();
			case CRYSTEVIA:
				return new CrysteviaTeleporter();
			case DEEPLANDS:
				return new DeeplandsTeleporter();
			case DUSTOPIA:
				return new DustopiaTeleporter();
			case GARDENCIA:
				return new GardenciaTeleporter();
			case GRECKON:
				return new GreckonTeleporter();
			case HAVEN:
				return new HavenTeleporter();
			case IROMINE:
				return new IromineTeleporter();
			case LBOREAN:
				return new LBoreanTeleporter();
			case LELYETIA:
				return new LelyetiaTeleporter();
			case LUNALUS:
				return new LunalusTeleporter();
			case MYSTERIUM:
				return new MysteriumTeleporter();
			case NOWHERE:
				return new NowhereTeleporter();
			case PRECASIA:
				return new PrecasiaTeleporter();
			case RUNANDOR:
				return new RunandorTeleporter();
			case SHYRELANDS:
				return new ShyrelandsTeleporter();
			case VOX_PONDS:
				return new VoxPondsTeleporter();
			default:
				return world.getPortalForcer();
		}
	}
}
