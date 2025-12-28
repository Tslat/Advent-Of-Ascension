package net.tslat.aoa3.content.block.functional.portal;

import com.google.common.base.Suppliers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.world.teleporter.AoAPortal;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

public abstract class PortalBlock extends Block implements AoAPortal {
	private static final VoxelShape X_SHAPE = Shapes.create(new AABB(0.375, 0, 0, 0.625, 1, 1));
	private static final VoxelShape Z_SHAPE = Shapes.create(new AABB(0, 0, 0.375, 1, 1, 0.625));

	private final int particleColour;
	private final ResourceKey<Level> dimension;
	private final Supplier<SoundEvent> ambientSound;

	public PortalBlock(BlockBehaviour.Properties properties, ResourceKey<Level> dimension, int particleColour) {
		this(properties, dimension, particleColour, null);
	}

	public PortalBlock(BlockBehaviour.Properties properties, ResourceKey<Level> dimension, int particleColour, @Nullable Supplier<SoundEvent> ambientSound) {
		super(properties);

		registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X));

		this.particleColour = particleColour;
		this.dimension = dimension;
		this.ambientSound = ambientSound;
	}

	public ResourceKey<Level> getDimension() {
		return this.dimension;
	}

	public int getParticleColour(BlockState state) {
		return this.particleColour;
	}

	@Override
	public PortalBlock getPortalBlock() {
		return this;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
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

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (this.ambientSound != null && random.nextInt(100) == 0 && level.dimension() != this.dimension)
			level.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, this.ambientSound.get(), SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);

		final boolean offsetX = !level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this);
		final int particleColour = getParticleColour(state);

		for (int i = 0; i < 4; i++) {
			double posX = pos.getX() + random.nextDouble();
			double posY = pos.getY() + random.nextDouble();
			double posZ = pos.getZ() + random.nextDouble();
			double xVelocity = (random.nextDouble() - 0.5d) * 0.5d;
			double yVelocity = (random.nextDouble() - 0.5d) * 0.5d;
			double zVelocity = (random.nextDouble() - 0.5d) * 0.5d;
			double randomMod = random.nextInt(2) * 2 - 1;

			if (offsetX) {
				posX = pos.getX() + 0.5f + 0.25f * randomMod;
				xVelocity = random.nextDouble() * 2 * randomMod;
			}
			else {
				posZ = pos.getZ() + 0.5f + 0.25f * randomMod;
				zVelocity = random.nextDouble() * 2 * randomMod;
			}

			ParticleBuilder.forPosition(ParticleTypes.PORTAL, posX, posY, posZ)
					.power(new Vec3(xVelocity, yVelocity, zVelocity))
					.colourTint(particleColour)
					.spawnClientParticles(level);
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

	private static final Supplier<Set<Block>> USEABLE_PORTALS = Suppliers.memoize(() -> Set.of(
			AoABlocks.NETHER_PORTAL.get(),
			AoABlocks.NOWHERE_PORTAL.get(),
			AoABlocks.PRECASIA_PORTAL.get(),
			AoABlocks.BARATHOS_PORTAL.get(),
			AoABlocks.LELYETIA_PORTAL.get(),
			AoABlocks.DEEPLANDS_PORTAL.get(),
			AoABlocks.LBOREAN_PORTAL.get(),
			AoABlocks.CELEVE_PORTAL.get(),
			AoABlocks.ABYSS_PORTAL.get(),
			AoABlocks.DUSTOPIA_PORTAL.get(),
			AoABlocks.CRYSTEVIA_PORTAL.get()));

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (!USEABLE_PORTALS.get().contains(this))
			return;

		if (entity.canUsePortal(false))
			entity.setAsInsidePortal(this, pos);
	}

	@Nullable
	@Override
	public DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
		if (!AoAConfigs.SERVER.allowNonPlayerPortalTravel.get() && !(entity instanceof ServerPlayer))
			return null;

		final ResourceKey<Level> currentDimension = level.dimension();
		final ResourceKey<Level> portalTargetDimension = getDimension();
		final MinecraftServer server = level.getServer();
		final Optional<GlobalPos> existingLink = Optional.ofNullable(entity instanceof ServerPlayer pl ? PlayerUtil.getAdventPlayer(pl).storage.getPortalReturnFor(currentDimension) : null);
		ServerLevel targetLevel = existingLink
				.map(link -> server.getLevel(currentDimension != portalTargetDimension ? portalTargetDimension : link.dimension()))
				.orElseGet(() -> server.getLevel(currentDimension == portalTargetDimension ? Level.OVERWORLD : portalTargetDimension));

		if (targetLevel == null) {
			if (currentDimension == Level.OVERWORLD)
				return null;

			targetLevel = server.overworld();
		}

		return getTransitionForPortalLink(targetLevel, entity, Optional.of(pos), AoAPortal.makeSafeCoords(level, targetLevel, entity.position()), existingLink);
	}

	public DimensionTransition getTransitionForPortalLink(ServerLevel targetLevel, Entity entity, Optional<BlockPos> fromPortal, BlockPos safeCoords, Optional<GlobalPos> existingLink) {
		return AoAPortal.getTransitionForLevel(targetLevel, entity, fromPortal, safeCoords, this, existingLink);
	}
}
