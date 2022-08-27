package net.tslat.aoa3.content.block.functional.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.ITeleporter;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.particletype.PortalFloaterParticleType;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.world.teleporter.PortalCoordinatesContainer;
import net.tslat.aoa3.content.world.teleporter.specific.*;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class PortalBlock extends Block {
	private static final VoxelShape X_SHAPE = Shapes.create(new AABB(0.375, 0, 0, 0.625, 1, 1));
	private static final VoxelShape Z_SHAPE = Shapes.create(new AABB(0, 0, 0.375, 1, 1, 0.625));

	private final int particleColour;
	private final ResourceKey<Level> world;

	public PortalBlock(ResourceKey<Level> world, MaterialColor mapColour, int particleColour) {
		super(new BlockUtil.CompactProperties(Material.PORTAL, mapColour).unbreakable().light(11).sound(SoundType.GLASS).noClip().get());

		registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X));

		this.particleColour = particleColour;
		this.world = world;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		if (state.getValue(BlockStateProperties.HORIZONTAL_AXIS) == Direction.Axis.Z)
			return Z_SHAPE;

		return X_SHAPE;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_AXIS, EntityUtil.getDirectionFacing(context.getPlayer(), true).getAxis());
	}

	private boolean isCompatibleNeighbour(Level world, BlockPos pos) {
		BlockState block = world.getBlockState(pos);

		return block.getBlock() == this || !block.isAir();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean skipRendering(BlockState state, BlockState adjacent, Direction side) {
		if (adjacent.getBlock() == this) {
			Direction.Axis axis;

			switch (state.getValue(BlockStateProperties.HORIZONTAL_AXIS)) {
				case X -> {
					axis = adjacent.getValue(BlockStateProperties.HORIZONTAL_AXIS);
					if (axis == Direction.Axis.X)
						return true;
				}
				case Z -> {
					axis = adjacent.getValue(BlockStateProperties.HORIZONTAL_AXIS);
					if (axis == Direction.Axis.Z)
						return true;
				}
				default -> {
				}
			}
		}

		return false;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {
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
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (this != AoABlocks.NETHER_PORTAL.get() && this != AoABlocks.NOWHERE_PORTAL.get())
			return;

		if (!world.isClientSide() && !entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
			if (!AoAConfigs.SERVER.allowNonPlayerPortalTravel.get() & !(entity instanceof Player))
				return;

			if (entity.portalTime > 0) {
				entity.portalTime = 30;

				return;
			}

			if (this.world == null || !world.getServer().isNetherEnabled())
				return;

			ITeleporter teleporter = this.world == Level.NETHER ? new NetherTeleporter() : getTeleporterForWorld(world.getServer().getLevel(this.world));
			PortalCoordinatesContainer portalLoc = null;

			if (entity instanceof Player) {
				ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayer)entity);
				portalLoc = plData.getPortalReturnLocation(world.dimension());

				((ServerPlayer)entity).connection.teleport(pos.getX(), pos.getY(), pos.getZ(), entity.getYRot(), entity.getXRot());

				if (portalLoc != null && world.getServer().getLevel(portalLoc.fromDim()) == null)
					portalLoc = null;
			}
			else {
				entity.teleportTo(pos.getX(), pos.getY(), pos.getZ());
			}

			if (portalLoc == null) {
				if (world.dimension() == this.world) {
					entity = entity.changeDimension(world.getServer().getLevel(Level.OVERWORLD), teleporter);
				}
				else {
					entity = entity.changeDimension(world.getServer().getLevel(this.world), teleporter);
				}
			}
			else if (world.dimension() != this.world) {
				entity = entity.changeDimension(world.getServer().getLevel(this.world), teleporter);
			}
			else {
				entity = entity.changeDimension(world.getServer().getLevel(portalLoc.fromDim()), teleporter);
			}

			if (entity != null)
				entity.portalTime = 100;
		}
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return switch (rot) {
			case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (state.getValue(BlockStateProperties.HORIZONTAL_AXIS)) {
				case Z -> state.setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X);
				case X -> state.setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z);
				default -> state;
			};
			default -> state;
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_AXIS);
	}

	@Override
	public void attack(BlockState state, Level world, BlockPos pos, Player player) {
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
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
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

	public static ITeleporter getTeleporterForWorld(ServerLevel world) {
		if (!world.dimension().location().getNamespace().equals(AdventOfAscension.MOD_ID))
			return world.getPortalForcer();

		AoADimensions.AoADimension aoaDim = AoADimensions.getDim(world.dimension());

		if (aoaDim == null)
			return world.getPortalForcer();

		return switch (aoaDim) {
			case ABYSS -> new AbyssTeleporter();
			case BARATHOS -> new BarathosTeleporter();
			case CANDYLAND -> new CandylandTeleporter();
			case CELEVE -> new CeleveTeleporter();
			case CREEPONIA -> new CreeponiaTeleporter();
			case CRYSTEVIA -> new CrysteviaTeleporter();
			case DEEPLANDS -> new DeeplandsTeleporter();
			case DUSTOPIA -> new DustopiaTeleporter();
			case GARDENCIA -> new GardenciaTeleporter();
			case GRECKON -> new GreckonTeleporter();
			case HAVEN -> new HavenTeleporter();
			case IROMINE -> new IromineTeleporter();
			case LBOREAN -> new LBoreanTeleporter();
			case LELYETIA -> new LelyetiaTeleporter();
			case LUNALUS -> new LunalusTeleporter();
			case MYSTERIUM -> new MysteriumTeleporter();
			case NOWHERE -> new NowhereTeleporter();
			case PRECASIA -> new PrecasiaTeleporter();
			case RUNANDOR -> new RunandorTeleporter();
			case SHYRELANDS -> new ShyrelandsTeleporter();
			case VOX_PONDS -> new VoxPondsTeleporter();
			default -> world.getPortalForcer();
		};
	}
}
