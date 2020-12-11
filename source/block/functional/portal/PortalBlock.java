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
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.ITeleporter;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.library.misc.CustomisableParticleType;
import net.tslat.aoa3.library.misc.PortalCoordinatesContainer;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.worldgen.worlds.abyss.AbyssTeleporter;
import net.tslat.aoa3.worldgen.worlds.ancientcavern.AncientCavernTeleporter;
import net.tslat.aoa3.worldgen.worlds.barathos.BarathosTeleporter;
import net.tslat.aoa3.worldgen.worlds.candyland.CandylandTeleporter;
import net.tslat.aoa3.worldgen.worlds.celeve.CeleveTeleporter;
import net.tslat.aoa3.worldgen.worlds.creeponia.CreeponiaTeleporter;
import net.tslat.aoa3.worldgen.worlds.crystevia.CrysteviaTeleporter;
import net.tslat.aoa3.worldgen.worlds.deeplands.DeeplandsTeleporter;
import net.tslat.aoa3.worldgen.worlds.dustopia.DustopiaTeleporter;
import net.tslat.aoa3.worldgen.worlds.gardencia.GardenciaTeleporter;
import net.tslat.aoa3.worldgen.worlds.greckon.GreckonTeleporter;
import net.tslat.aoa3.worldgen.worlds.haven.HavenTeleporter;
import net.tslat.aoa3.worldgen.worlds.immortallis.ImmortallisTeleporter;
import net.tslat.aoa3.worldgen.worlds.iromine.IromineTeleporter;
import net.tslat.aoa3.worldgen.worlds.lborean.LBoreanTeleporter;
import net.tslat.aoa3.worldgen.worlds.lelyetia.LelyetiaTeleporter;
import net.tslat.aoa3.worldgen.worlds.lunalus.LunalusTeleporter;
import net.tslat.aoa3.worldgen.worlds.mysterium.MysteriumTeleporter;
import net.tslat.aoa3.worldgen.worlds.nether.NetherTeleporter;
import net.tslat.aoa3.worldgen.worlds.precasia.PrecasiaTeleporter;
import net.tslat.aoa3.worldgen.worlds.runandor.RunandorTeleporter;
import net.tslat.aoa3.worldgen.worlds.shyrelands.ShyrelandsTeleporter;
import net.tslat.aoa3.worldgen.worlds.voxponds.VoxPondsTeleporter;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class PortalBlock extends Block {
	private static final VoxelShape X_SHAPE = VoxelShapes.create(new AxisAlignedBB(0.375, 0, 0, 0.625, 1, 1));
	private static final VoxelShape Z_SHAPE = VoxelShapes.create(new AxisAlignedBB(0, 0, 0.375, 1, 1, 0.625));

	private final int particleColour;
	private final Supplier<DimensionType> dimension;

	public PortalBlock(Supplier<DimensionType> dimType, MaterialColor mapColour, int particleColour) {
		super(BlockUtil.generateBlockProperties(Material.PORTAL, mapColour, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE, 11).sound(SoundType.GLASS).doesNotBlockMovement());

		setDefaultState(getStateContainer().getBaseState().with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X));

		this.particleColour = particleColour;
		this.dimension = dimType;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.get(BlockStateProperties.HORIZONTAL_AXIS)) {
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
		return getDefaultState().with(BlockStateProperties.HORIZONTAL_AXIS, EntityUtil.getDirectionFacing(context.getPlayer(), true).getAxis());
	}

	private boolean isCompatibleNeighbour(World world, BlockPos pos) {
		BlockState block = world.getBlockState(pos);

		return block.getBlock() == this || block.isOpaqueCube(world, pos);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isSideInvisible(BlockState state, BlockState adjacent, Direction side) {
		if (adjacent.getBlock() == this) {
			Direction.Axis axis;

			switch (state.get(BlockStateProperties.HORIZONTAL_AXIS)) {
				case X:
					axis = adjacent.get(BlockStateProperties.HORIZONTAL_AXIS);

					if (axis == Direction.Axis.X)
						return true;

					break;
				case Z:
					axis = adjacent.get(BlockStateProperties.HORIZONTAL_AXIS);

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

			world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.PORTAL_FLOATER.get(), particleColour), posXStart, posYStart, posZStart, motionX, motionY, motionZ);
		}
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!world.isRemote() && entity.getRidingEntity() == null && !entity.isBeingRidden()) {
			if (!AoAConfig.SERVER.allowNonPlayerPortalTravel.get() & !(entity instanceof PlayerEntity))
				return;

			if (entity.timeUntilPortal > 0) {
				entity.timeUntilPortal = 30;

				return;
			}

			if (dimension.get() == null)
				return;

			ITeleporter teleporter = dimension.get() == DimensionType.THE_NETHER ? new NetherTeleporter() : getTeleporterForWorld(world.getServer().getWorld(dimension.get()));
			PortalCoordinatesContainer portalLoc = null;

			if (entity instanceof PlayerEntity) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity);
				portalLoc = plData.getPortalReturnLocation(world.getDimension().getType());

				((ServerPlayerEntity)entity).connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), entity.rotationYaw, entity.rotationPitch);
			}
			else {
				entity.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
			}

			if (portalLoc == null) {
				if (world.getDimension().getType() == dimension.get()) {
					entity = entity.changeDimension(DimensionType.OVERWORLD, teleporter);
				}
				else {
					entity = entity.changeDimension(dimension.get(), teleporter);
				}
			}
			else if (world.getDimension().getType() != dimension.get()) {
				entity = entity.changeDimension(dimension.get(), teleporter);
			}
			else {
				entity = entity.changeDimension(portalLoc.fromDim, teleporter);
			}

			if (entity != null)
				entity.timeUntilPortal = 100;
		}
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		switch(rot) {
			case COUNTERCLOCKWISE_90:
			case CLOCKWISE_90:
				switch(state.get(BlockStateProperties.HORIZONTAL_AXIS)) {
					case Z:
						return state.with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X);
					case X:
						return state.with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z);
					default:
						return state;
				}
			default:
				return state;
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_AXIS);
	}

	@Override
	public void onBlockClicked(BlockState state, World world, BlockPos pos, PlayerEntity player) {
		if (world.isAirBlock(pos.up()) || world.isAirBlock(pos.down())) {
			world.setBlockState(pos, Blocks.AIR.getDefaultState());

			return;
		}

		switch (world.getBlockState(pos).get(BlockStateProperties.HORIZONTAL_AXIS)) {
			case Z:
				if (world.isAirBlock(pos.east()) || world.isAirBlock(pos.west()))
					world.setBlockState(pos, Blocks.AIR.getDefaultState());
				break;
			case X:
				if (world.isAirBlock(pos.north()) || world.isAirBlock(pos.south()))
					world.setBlockState(pos, Blocks.AIR.getDefaultState());
				break;
		}
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		Direction.Axis facing = state.get(BlockStateProperties.HORIZONTAL_AXIS);

		switch (facing) {
			case Z:
				if (!isCompatibleNeighbour(world, pos.up()) || !isCompatibleNeighbour(world, pos.down()) || !isCompatibleNeighbour(world, pos.east()) || !isCompatibleNeighbour(world, pos.west()))
					world.setBlockState(pos, Blocks.AIR.getDefaultState());
				break;
			case X:
				if (!isCompatibleNeighbour(world, pos.up()) || !isCompatibleNeighbour(world, pos.down()) || !isCompatibleNeighbour(world, pos.north()) || !isCompatibleNeighbour(world, pos.south()))
					world.setBlockState(pos, Blocks.AIR.getDefaultState());
				break;
		}
	}

	public static ITeleporter getTeleporterForWorld(ServerWorld world) {
		DimensionType dimType = world.dimension.getType();
		AoADimensions.AoADimension aoaDim = AoADimensions.AoADimension.fromDimType(dimType);

		if (dimType.isVanilla() || aoaDim == AoADimensions.AoADimension.NONE || aoaDim == null)
			return world.getDefaultTeleporter();

		switch (aoaDim) {
			case ABYSS:
				return new AbyssTeleporter();
			case ANCIENT_CAVERN:
				return new AncientCavernTeleporter();
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
			case IMMORTALLIS:
				return new ImmortallisTeleporter();
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
			case PRECASIA:
				return new PrecasiaTeleporter();
			case RUNANDOR:
				return new RunandorTeleporter();
			case SHYRELANDS:
				return new ShyrelandsTeleporter();
			case VOX_PONDS:
				return new VoxPondsTeleporter();
			default:
				return world.getDefaultTeleporter();
		}
	}
}
