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
import net.tslat.aoa3.common.particletype.PortalFloaterParticleType;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.player.ServerPlayerDataManager;
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
		if (entity.getVehicle() == null && !entity.isVehicle() && entity instanceof ServerPlayer) {
			if (entity.portalTime > 0) {
				entity.portalTime = 30;

				return;
			}

			entity.portalTime = 100;
			Activity activity = state.getValue(ACTIVITY);
			BlockPos teleportPos = activity.teleportPos;

			entity.teleportTo(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ());
			activity.onUse((ServerPlayer)entity);
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
			int colour;

			switch (state.getValue(ACTIVITY)) {
				case PARKOUR:
					colour = 39103;
					break;
				case BOSSES:
					colour = 12189696;
					break;
				case DUNGEON:
					colour = 9502944;
					break;
				case UTILITY:
					colour = 29210;
					break;
				case RETURN:
				default:
					colour = 16777215;
					break;
			}


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
		PARKOUR(0, 202, 0, pl -> {
			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

			for (NonNullList<ItemStack> inv : pl.getInventory().compartments) {
				plData.storeItems(inv);
			}

			pl.getInventory().clearContent();
		}),
		BOSSES(0, 202, 0),
		DUNGEON(0, 202, 0),
		UTILITY(0, 202, 0),
		RETURN(0, 202, 0, pl -> {
			PlayerUtil.getAdventPlayer(pl).returnItemStorage();
		});

		private final BlockPos teleportPos;
		private final Consumer<ServerPlayer> useFunction;

		Activity(int x, int y, int z, @Nullable Consumer<ServerPlayer> useFunction) {
			this.teleportPos = new BlockPos(x, y, z);
			this.useFunction = useFunction;
		}

		Activity(int x, int y, int z) {
			this(x, y, z, null);
		}

		@Override
		public String getSerializedName() {
			return toString().toLowerCase(Locale.ROOT);
		}

		public void onUse(ServerPlayer pl) {
			if (useFunction != null)
				useFunction.accept(pl);
		}
	}
}
