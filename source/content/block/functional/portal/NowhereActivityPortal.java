package net.tslat.aoa3.content.block.functional.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.particletype.PortalFloaterParticleType;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.function.Consumer;

public class NowhereActivityPortal extends PortalBlock {
	private static final EnumProperty<Activity> ACTIVITY = EnumProperty.create("activity", Activity.class);

	public NowhereActivityPortal() {
		super(AoADimensions.NOWHERE.key, MaterialColor.GOLD, 0);

		registerDefaultState(getStateDefinition().any().setValue(ACTIVITY, Activity.UTILITY));
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (entity.getVehicle() == null && !entity.isVehicle() && entity instanceof ServerPlayer pl) {
			if (pl.portalTime > 0) {
				pl.portalTime = 30;

				return;
			}

			pl.portalTime = 100;

			state.getValue(ACTIVITY).teleport(pl);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);

		builder.add(ACTIVITY);
	}

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

			if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
				posXStart = (double)pos.getX() + 0.5D + 0.25D * (double)randomMod;
				motionX = rand.nextFloat() * 2.0F * (float)randomMod;
			}
			else {
				posZStart = (double)pos.getZ() + 0.5D + 0.25D * (double)randomMod;
				motionZ = rand.nextFloat() * 2.0F * (float)randomMod;
			}

			world.addParticle(new PortalFloaterParticleType.Data(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), colour), posXStart, posYStart, posZStart, motionX, motionY, motionZ);
		}
	}

	public enum Activity implements StringRepresentable {
		PARKOUR(21.5d, 10.5d, 22d, 90, pl -> {
			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

			for (NonNullList<ItemStack> inv : pl.getInventory().compartments) {
				plData.storeItems(inv);
			}

			pl.getInventory().clearContent();
		}),
		PARKOUR_1(0, 0, 0, -90, pl -> ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()))),
		PARKOUR_2(0, 0, 0, -90, pl -> ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()))),
		PARKOUR_3(0, 0, 0, -90, pl -> ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()))),
		PARKOUR_4(0, 0, 0, -90, pl -> ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()))),
		PARKOUR_5(0, 0, 0, -90, pl -> ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()))),
		PARKOUR_6(0, 0, 0, -90, pl -> ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()))),
		BOSSES(17.5d, 502.5d, 3.5d, 0),
		DUNGEON(6.5d, 1501.5d, 16.5d, -90),
		UTILITY(25.5d, 1001.5d, 16, 90),
		RETURN(6.5d, 1501.5d, 16.5d, -90, pl -> {
			ItemUtil.clearInventoryOfItems(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
			PlayerUtil.getAdventPlayer(pl).returnItemStorage();
		});

		private final Vec3 teleportPos;
		private final float teleportRot;
		private final Consumer<ServerPlayer> useFunction;

		Activity(double x, double y, double z, float rot, @Nullable Consumer<ServerPlayer> useFunction) {
			this.teleportPos = new Vec3(x, y, z);
			this.teleportRot = rot;
			this.useFunction = useFunction;
		}

		Activity(double x, double y, double z, float rot) {
			this(x, y, z, rot, null);
		}

		@Override
		public String getSerializedName() {
			return toString().toLowerCase(Locale.ROOT);
		}

		public Vec3 getPos() {
			return this.teleportPos;
		}

		public void onUse(ServerPlayer pl) {
			if (useFunction != null)
				useFunction.accept(pl);
		}

		public void teleport(ServerPlayer pl) {
			if (teleportPos.lengthSqr() == 0)
				return;

			AoAScheduler.scheduleSyncronisedTask(() -> {
				pl.connection.teleport(teleportPos.x, teleportPos.y, teleportPos.z, teleportRot, pl.getXRot());
				onUse(pl);
			}, 1);
		}
	}
}
