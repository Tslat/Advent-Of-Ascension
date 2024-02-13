package net.tslat.aoa3.content.block.functional.portal;

import com.google.common.base.Suppliers;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.util.ITeleporter;
import net.tslat.aoa3.common.particletype.PortalFloaterParticleType;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.world.teleporter.PortalCoordinatesContainer;
import net.tslat.aoa3.content.world.teleporter.specific.*;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;
import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.Set;
import java.util.function.Supplier;

public class PortalBlock extends Block {
	private static final Supplier<Set<Block>> USEABLE_PORTALS = Suppliers.memoize(() -> Set.of(AoABlocks.NETHER_PORTAL.get(), AoABlocks.NOWHERE_PORTAL.get(), AoABlocks.PRECASIA_PORTAL.get(), AoABlocks.BARATHOS_PORTAL.get(), AoABlocks.LELYETIA_PORTAL.get(), AoABlocks.DEEPLANDS_PORTAL.get(), AoABlocks.LBOREAN_PORTAL.get(), AoABlocks.DUSTOPIA_PORTAL.get()));
	private static final IdentityHashMap<ResourceKey<Level>, ITeleporter> TELEPORTERS = Util.make(new IdentityHashMap<>(21), map -> {
		map.put(AoADimensions.ABYSS, new AbyssTeleporter());
		map.put(AoADimensions.BARATHOS, new BarathosTeleporter());
		map.put(AoADimensions.CANDYLAND, new CandylandTeleporter());
		map.put(AoADimensions.CELEVE, new CeleveTeleporter());
		map.put(AoADimensions.CREEPONIA, new CreeponiaTeleporter());
		map.put(AoADimensions.CRYSTEVIA, new CrysteviaTeleporter());
		map.put(AoADimensions.DEEPLANDS, new DeeplandsTeleporter());
		map.put(AoADimensions.DUSTOPIA, new DustopiaTeleporter());
		map.put(AoADimensions.GARDENCIA, new GardenciaTeleporter());
		map.put(AoADimensions.GRECKON, new GreckonTeleporter());
		map.put(AoADimensions.HAVEN, new HavenTeleporter());
		map.put(AoADimensions.IROMINE, new IromineTeleporter());
		map.put(AoADimensions.LBOREAN, new LBoreanTeleporter());
		map.put(AoADimensions.LELYETIA, new LelyetiaTeleporter());
		map.put(AoADimensions.LUNALUS, new LunalusTeleporter());
		map.put(AoADimensions.MYSTERIUM, new MysteriumTeleporter());
		map.put(AoADimensions.NOWHERE, new NowhereTeleporter());
		map.put(AoADimensions.PRECASIA, new PrecasiaTeleporter());
		map.put(AoADimensions.RUNANDOR, new RunandorTeleporter());
		map.put(AoADimensions.SHYRELANDS, new ShyrelandsTeleporter());
		map.put(AoADimensions.VOX_PONDS, new VoxPondsTeleporter());
	});

	private static final VoxelShape X_SHAPE = Shapes.create(new AABB(0.375, 0, 0, 0.625, 1, 1));
	private static final VoxelShape Z_SHAPE = Shapes.create(new AABB(0, 0, 0.375, 1, 1, 0.625));

	private final int particleColour;
	private final ResourceKey<Level> world;
	private final Supplier<SoundEvent> ambientSound;

	public PortalBlock(BlockBehaviour.Properties properties, ResourceKey<Level> world, int particleColour) {
		this(properties, world, particleColour, null);
	}

	public PortalBlock(BlockBehaviour.Properties properties, ResourceKey<Level> world, int particleColour, @Nullable Supplier<SoundEvent> ambientSound) {
		super(properties);

		registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X));

		this.particleColour = particleColour;
		this.world = world;
		this.ambientSound = ambientSound;
	}

	public static void addTeleporter(ResourceKey<Level> dimKey, ITeleporter teleporter) {
		TELEPORTERS.put(dimKey, teleporter);
	}

	public static ITeleporter getTeleporterForLevel(ServerLevel level) {
		return TELEPORTERS.getOrDefault(level.dimension(), level.getPortalForcer());
	}

	public int getParticleColour() {
		return this.particleColour;
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
		if (this.ambientSound != null && random.nextInt(100) == 0 && level.dimension() != this.world)
			level.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, this.ambientSound.get(), SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);

		for (int i = 0; i < 4; ++i) {
			double posXStart = (float)pos.getX() + random.nextFloat();
			double posYStart = (float)pos.getY() + random.nextFloat();
			double posZStart = (float)pos.getZ() + random.nextFloat();
			double motionX = ((double)random.nextFloat() - 0.5D) * 0.5D;
			double motionY = ((double)random.nextFloat() - 0.5D) * 0.5D;
			double motionZ = ((double)random.nextFloat() - 0.5D) * 0.5D;
			int randomMod = random.nextInt(2) * 2 - 1;

			if (level.getBlockState(pos.west()).getBlock() != this && level.getBlockState(pos.east()).getBlock() != this) {
				posXStart = (double)pos.getX() + 0.5D + 0.25D * (double)randomMod;
				motionX = random.nextFloat() * 2.0F * (float)randomMod;
			}
			else {
				posZStart = (double)pos.getZ() + 0.5D + 0.25D * (double)randomMod;
				motionZ = random.nextFloat() * 2.0F * (float)randomMod;
			}

			level.addParticle(new PortalFloaterParticleType.Data(new Vec3(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f), particleColour), posXStart, posYStart, posZStart, motionX, motionY, motionZ);
		}
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (!USEABLE_PORTALS.get().contains(this))
			return;

		if (!world.isClientSide() && !entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
			if (!AoAConfigs.SERVER.allowNonPlayerPortalTravel.get() & !(entity instanceof Player))
				return;

			if (entity.isOnPortalCooldown()) {
				entity.setPortalCooldown();

				return;
			}

			if (this.world == null || !world.getServer().isNetherEnabled())
				return;

			ServerLevel targetLevel = world.getServer().getLevel(this.world);

			if (targetLevel == null)
				return;

			ITeleporter teleporter = this.world == Level.NETHER ? new NetherTeleporter() : getTeleporterForLevel(targetLevel);
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
				entity.setPortalCooldown();
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

	@Nullable
	public static Block getInsidePortalBlock(Entity entity) {
		final AABB entityBounds = entity.getBoundingBox();
		final BlockPos minPos = BlockPos.containing(entityBounds.minX + 1.0E-7D, entityBounds.minY + 1.0E-7D, entityBounds.minZ + 1.0E-7D);
		final BlockPos maxPos = BlockPos.containing(entityBounds.maxX - 1.0E-7D, entityBounds.maxY - 1.0E-7D, entityBounds.maxZ - 1.0E-7D);

		if (entity.level().hasChunksAt(minPos, maxPos)) {
			final BlockPos.MutableBlockPos testPos = new BlockPos.MutableBlockPos();

			for(int x = minPos.getX(); x <= maxPos.getX(); ++x) {
				for(int y = minPos.getY(); y <= maxPos.getY(); ++y) {
					for(int z = minPos.getZ(); z <= maxPos.getZ(); ++z) {
						testPos.set(x, y, z);
						final Block block = entity.level().getBlockState(testPos).getBlock();

						if (block == Blocks.NETHER_PORTAL)
							return null;

						if (block instanceof PortalBlock portal)
							return portal;
					}
				}
			}
		}

		return null;
	}
}
