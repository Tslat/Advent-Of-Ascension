package net.tslat.aoa3.block.functional.portal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.particletype.PortalFloaterParticleType;
import net.tslat.aoa3.common.registration.AoADimensions;

import java.util.Locale;
import java.util.Random;

public class NowhereActivityPortal extends PortalBlock {
	private static final EnumProperty<Activity> ACTIVITY = EnumProperty.create("activity", Activity.class);

	public NowhereActivityPortal() {
		super(AoADimensions.NOWHERE.key, MaterialColor.GOLD, 0);

		registerDefaultState(getStateDefinition().any().setValue(ACTIVITY, Activity.UTILITY));
	}

	@Override
	public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!world.isClientSide() && entity.getVehicle() == null && !entity.isVehicle() && entity instanceof PlayerEntity) {
			if (entity.portalTime > 0) {
				entity.portalTime = 30;

				return;
			}

			entity.portalTime = 100;
			BlockPos teleportPos = state.getValue(ACTIVITY).teleportPos;

			entity.teleportTo(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ());
		}
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);

		builder.add(ACTIVITY);
	}

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

	public enum Activity implements IStringSerializable {
		PARKOUR(0, 202, 0),
		BOSSES(0, 202, 0),
		DUNGEON(0, 202, 0),
		UTILITY(0, 202, 0),
		RETURN(0, 202, 0);

		private final BlockPos teleportPos;

		Activity(int x, int y, int z) {
			this.teleportPos = new BlockPos(x, y, z);
		}

		@Override
		public String getSerializedName() {
			return toString().toLowerCase(Locale.ROOT);
		}
	}
}
