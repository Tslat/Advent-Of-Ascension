package net.tslat.aoa3.content.block.functional.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.world.nowhere.NowhereParkourCourse;
import net.tslat.aoa3.event.dimension.NowhereEvents;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class NowhereActivityPortal extends PortalBlock {
	private static final EnumProperty<Activity> ACTIVITY = EnumProperty.create("activity", Activity.class);

	public NowhereActivityPortal(BlockBehaviour.Properties properties) {
		super(properties, AoADimensions.NOWHERE, 0);

		registerDefaultState(getStateDefinition().any().setValue(ACTIVITY, Activity.UTILITY));
	}

	@Override
	public Block getPortalFrame() {
		return AoABlocks.ANCIENT_TILE_SHRINE.get();
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (entity.getVehicle() == null && !entity.isVehicle() && entity instanceof ServerPlayer pl && WorldUtil.isWorld(pl.level(), AoADimensions.NOWHERE)) {
			if (pl.isOnPortalCooldown()) {
				pl.setPortalCooldown();

				return;
			}

			pl.setPortalCooldown();
			state.getValue(ACTIVITY).activate(pl);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);

		builder.add(ACTIVITY);
	}

	@Override
	public int getParticleColour(BlockState state) {
		return switch (state.getValue(ACTIVITY)) {
			case PARKOUR -> 0x0098BF;
			case PARKOUR_1 -> 0x8C00FF;
			case PARKOUR_2 -> 0x0400F4;
			case PARKOUR_3 -> 0xBDF700;
			case PARKOUR_4 -> 0xFFCC00;
			case PARKOUR_5 -> 0xE07B00;
			case PARKOUR_6 -> 0xD30000;
			case BOSSES -> 0xBA0000;
			case DUNGEON -> 0x9100E0;
			case UTILITY -> 0x00721A;
			case RETURN -> 0xFFFFFF;
		};
	}

	public enum Activity implements StringRepresentable {
		PARKOUR(21.5d, 10.5d, 22d, 90, pl -> {
			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

			plData.storeInventoryContents();
		}),
		PARKOUR_1(pl -> findParkourCourse(pl, 1), pl -> InventoryUtil.giveItemTo(pl, AoAItems.RETURN_CRYSTAL)),
		PARKOUR_2(pl -> findParkourCourse(pl, 2), pl -> InventoryUtil.giveItemTo(pl, AoAItems.RETURN_CRYSTAL)),
		PARKOUR_3(pl -> findParkourCourse(pl, 3), pl -> InventoryUtil.giveItemTo(pl, AoAItems.RETURN_CRYSTAL)),
		PARKOUR_4(pl -> findParkourCourse(pl, 4), pl -> InventoryUtil.giveItemTo(pl, AoAItems.RETURN_CRYSTAL)),
		PARKOUR_5(pl -> findParkourCourse(pl, 5), pl -> InventoryUtil.giveItemTo(pl, AoAItems.RETURN_CRYSTAL)),
		PARKOUR_6(pl -> findParkourCourse(pl, 6), pl -> InventoryUtil.giveItemTo(pl, AoAItems.RETURN_CRYSTAL)),
		BOSSES(17.5d, 502.5d, 3.5d, 0),
		DUNGEON(6.5d, 1501.5d, 16.5d, -90),
		UTILITY(25.5d, 1001.5d, 16, 90),
		RETURN(pl -> doReturnPortalTeleport(pl, 16.5d, 1501.5d, 16.5d, 180), pl -> {
			if (!NowhereEvents.isInParkourRegion(pl.blockPosition())) {
				InventoryUtil.clearItems(pl, AoAItems.RETURN_CRYSTAL);
				PlayerUtil.getAdventPlayer(pl).storage.returnStoredItems();
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
			}, afterTeleportFunction == null ? pl -> {} : afterTeleportFunction);
		}

		@Override
		public String getSerializedName() {
			return toString().toLowerCase(Locale.ROOT);
		}

		public void teleport(ServerPlayer pl) {
			AoAScheduler.schedule(1, tick -> teleportFunction.test(pl));
		}

		public void activate(ServerPlayer pl) {
			AoAScheduler.schedule(1, tick -> {
				if (teleportFunction.test(pl))
					afterTeleportFunction.accept(pl);
			});
		}

		private static boolean doReturnPortalTeleport(ServerPlayer pl, double x, double y, double z, float rot) {
			if (pl.getY() >= pl.level().getMinBuildHeight() && NowhereEvents.isInParkourRegion(pl.blockPosition()) && pl.isAlive() && BlockPos.betweenClosedStream(pl.getBoundingBox()).anyMatch(testPos -> pl.level().getBlockState(testPos).is(AoABlocks.NOWHERE_ACTIVITY_PORTAL))) {
				NowhereParkourCourse course = NowhereParkourCourse.getCourseForPosition(pl.serverLevel(), pl.position());

				if (course != null) {
					course.grantRewards(pl);

					NowhereParkourCourse nextCourse = NowhereParkourCourse.getNextCourse(course);

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
			NowhereParkourCourse course = NowhereParkourCourse.getFirstCourseForTier(tier);

			if (course == null)
				return false;

			course.teleportPlayerToCourse(pl);

			return true;
		}
	}
}
