package net.tslat.aoa3.content.block.functional.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.particletype.PortalFloaterParticleType;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.data.server.AoANowhereParkourCourseListener;
import net.tslat.aoa3.event.dimension.NowhereEvents;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class NowhereActivityPortal extends PortalBlock {
	private static final EnumProperty<Activity> ACTIVITY = EnumProperty.create("activity", Activity.class);

	public NowhereActivityPortal(BlockBehaviour.Properties properties) {
		super(properties, AoADimensions.NOWHERE.key, 0);

		registerDefaultState(getStateDefinition().any().setValue(ACTIVITY, Activity.UTILITY));
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (entity.getVehicle() == null && !entity.isVehicle() && entity instanceof ServerPlayer pl && WorldUtil.isWorld(pl.level(), AoADimensions.NOWHERE.key)) {
			if (pl.portalTime > 0) {
				pl.portalTime = 30;

				return;
			}

			pl.portalTime = 100;

			state.getValue(ACTIVITY).activate(pl);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);

		builder.add(ACTIVITY);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		for (int i = 0; i < 4; ++i) {
			double posXStart = (float)pos.getX() + random.nextFloat();
			double posYStart = (float)pos.getY() + random.nextFloat();
			double posZStart = (float)pos.getZ() + random.nextFloat();
			double motionX = ((double)random.nextFloat() - 0.5D) * 0.5D;
			double motionY = ((double)random.nextFloat() - 0.5D) * 0.5D;
			double motionZ = ((double)random.nextFloat() - 0.5D) * 0.5D;
			int randomMod = random.nextInt(2) * 2 - 1;
			int colour = switch (state.getValue(ACTIVITY)) {
				case PARKOUR -> 39103;
				case PARKOUR_1 -> 9175295;
				case PARKOUR_2 -> 262388;
				case PARKOUR_3 -> 12449536;
				case PARKOUR_4 -> 16763904;
				case PARKOUR_5 -> 14711552;
				case PARKOUR_6 -> 13828096;
				case BOSSES -> 12189696;
				case DUNGEON -> 9502944;
				case UTILITY -> 29210;
				case RETURN -> 16777215;
			};

			if (level.getBlockState(pos.west()).getBlock() != this && level.getBlockState(pos.east()).getBlock() != this) {
				posXStart = (double)pos.getX() + 0.5D + 0.25D * (double)randomMod;
				motionX = random.nextFloat() * 2.0F * (float)randomMod;
			}
			else {
				posZStart = (double)pos.getZ() + 0.5D + 0.25D * (double)randomMod;
				motionZ = random.nextFloat() * 2.0F * (float)randomMod;
			}

			level.addParticle(new PortalFloaterParticleType.Data(new Vec3(pos.getX(), pos.getY(), pos.getZ()), colour), posXStart, posYStart, posZStart, motionX, motionY, motionZ);
		}
	}

	public enum Activity implements StringRepresentable {
		PARKOUR(21.5d, 10.5d, 22d, 90, pl -> {
			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

			plData.storeInventoryContents();
		}),
		PARKOUR_1(pl -> findParkourCourse(pl, 1), pl -> ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()))),
		PARKOUR_2(pl -> findParkourCourse(pl, 2), pl -> ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()))),
		PARKOUR_3(pl -> findParkourCourse(pl, 3), pl -> ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()))),
		PARKOUR_4(pl -> findParkourCourse(pl, 4), pl -> ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()))),
		PARKOUR_5(pl -> findParkourCourse(pl, 5), pl -> ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()))),
		PARKOUR_6(pl -> findParkourCourse(pl, 6), pl -> ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()))),
		BOSSES(17.5d, 502.5d, 3.5d, 0),
		DUNGEON(6.5d, 1501.5d, 16.5d, -90),
		UTILITY(25.5d, 1001.5d, 16, 90),
		RETURN(pl -> doReturnPortalTeleport(pl, 16.5d, 1501.5d, 16.5d, 180), pl -> {
			if (!NowhereEvents.isInParkourRegion(pl.blockPosition())) {
				ItemUtil.clearInventoryOfItems(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
				PlayerUtil.getAdventPlayer(pl).returnItemStorage();
				PlayerUtil.resetToDefaultStatus(pl);
			}
		});

		private final Predicate<ServerPlayer> teleportFunction;
		private final Consumer<ServerPlayer> afterTeleportFunction;

		Activity(Predicate<ServerPlayer> teleportFunction, Consumer<ServerPlayer> afterTeleportFunction) {
			this.teleportFunction = teleportFunction;
			this.afterTeleportFunction = afterTeleportFunction;
		}

		Activity(double x, double y, double z) {
			this(x, y, z, 0);
		}

		Activity(double x, double y, double z, float rot) {
			this(x, y, z, rot, null);
		}

		Activity(double x, double y, double z, float rot, @Nullable Consumer<ServerPlayer> afterTeleportFunction) {
			this(pl -> {
				pl.connection.teleport(x, y, z, rot, pl.getXRot());

				return true;
			},
					afterTeleportFunction == null ? pl -> {} : afterTeleportFunction);
		}

		@Override
		public String getSerializedName() {
			return toString().toLowerCase(Locale.ROOT);
		}

		public void teleport(ServerPlayer pl) {
			AoAScheduler.scheduleSyncronisedTask(() -> teleportFunction.test(pl), 1);
		}

		public void activate(ServerPlayer pl) {
			AoAScheduler.scheduleSyncronisedTask(() -> {
				if (teleportFunction.test(pl))
					afterTeleportFunction.accept(pl);
			}, 1);
		}

		private static boolean doReturnPortalTeleport(ServerPlayer pl, double x, double y, double z, float rot) {
			if (NowhereEvents.isInParkourRegion(pl.blockPosition())) {
				AoANowhereParkourCourseListener.NowhereParkourCourse course = AoANowhereParkourCourseListener.getCourseForPosition(pl.serverLevel(), pl.position());

				if (course != null) {
					course.grantRewards(pl);

					AoANowhereParkourCourseListener.NowhereParkourCourse nextCourse = AoANowhereParkourCourseListener.getNextCourse(course);

					if (nextCourse != null) {
						nextCourse.teleportPlayerToCourse(pl);

						return true;
					}
				}
			}

			pl.connection.teleport(x, y, z, rot, pl.getXRot());

			return true;
		}

		private static boolean findParkourCourse(ServerPlayer pl, int tier) {
			AoANowhereParkourCourseListener.NowhereParkourCourse course = AoANowhereParkourCourseListener.getFirstCourseForTier(tier);

			if (course == null)
				return false;

			course.teleportPlayerToCourse(pl);

			return true;
		}
	}
}
