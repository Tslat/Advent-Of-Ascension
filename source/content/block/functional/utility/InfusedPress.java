package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.piston.PistonHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.menu.InfusedPressMenu;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.block.blockentity.InfusedPressBlockEntity;
import net.tslat.aoa3.library.object.extension.GenericItemStackHandler;
import net.tslat.aoa3.util.InteractionResults;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import org.jetbrains.annotations.Nullable;

public class InfusedPress extends Block implements EntityBlock {
	public InfusedPress(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new InfusedPressBlockEntity(pos, state);
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			if (level.getBlockEntity(pos) instanceof InfusedPressBlockEntity blockEntity)
				blockEntity.dropContents();

			super.onRemove(state, level, pos, newState, isMoving);
		}
	}

	@Override
	protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
		if (level instanceof ServerLevel serverLevel && pos.above().equals(neighborPos) && neighborBlock instanceof PistonHeadBlock && level.getBlockEntity(pos) instanceof InfusedPressBlockEntity press) {
			BlockState pistonState = level.getBlockState(neighborPos.above());
			boolean compressing = pistonState.getBlock() instanceof PistonBaseBlock;

			if (!compressing && !(pistonState.getBlock() instanceof MovingPistonBlock))
				return;

			if (pistonState.getValue(DirectionalBlock.FACING) != Direction.DOWN)
				return;

			if (compressing) {
				if (press.compress()) {
					RandomSource rand = level.random;
					TMEParticlePacket packet = new TMEParticlePacket();

					level.playSound(null, pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f, SoundEvents.ANVIL_LAND, SoundSource.BLOCKS, 1, rand.nextFloat() * 0.5f + 0.75f);

					for (int i = 0; i < 50; i++) {
						packet.particle(ParticleBuilder.forRandomPosInCircleRadius(new ItemParticleOption(ParticleTypes.ITEM, press.getItemHandler().getStackInSlot(9)), pos.above(1).getBottomCenter(), 0.5f)
								.scaleMod(0.5f)
								.velocity(rand.nextGaussian() * 0.05f, rand.nextFloat() * 0.1f, rand.nextGaussian() * 0.05f));
						packet.particle(ParticleBuilder.forRandomPosInCircleRadius(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.above(1).getBottomCenter(), 0.5f)
								.scaleMod(0.25f)
								.lifespan(rand.nextInt(10, 20))
								.colourTint(rand.nextInt(125, 175), 0, 0, 255));
						packet.particle(ParticleBuilder.forRandomPosInCircleRadius(ParticleTypes.CRIT, pos.above(1).getBottomCenter(), 0.5f)
								.scaleMod(0.25f)
								.lifespan(rand.nextInt(3, 15))
								.velocity(rand.nextGaussian() * 0.05f, rand.nextFloat() * 0.1f, rand.nextGaussian() * 0.05f)
								.colourTint(rand.nextInt(125, 175), 0, 0, 255));
					}

					packet.sendToAllPlayersTrackingBlock(serverLevel, pos);
				}
			}
			else {
				if (press.decompress())
					level.playSound(null, pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f, SoundEvents.IRON_TRAPDOOR_OPEN, SoundSource.BLOCKS, 1, 1);
			}
		}
	}

	@Override
	protected boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
		if (level.getBlockEntity(pos) instanceof InfusedPressBlockEntity press) {
			GenericItemStackHandler itemHandler = press.getItemHandler();

			if (!itemHandler.getStackInSlot(9).isEmpty())
				return 15;

			int count = 0;

			for (int i = 0; i < 9; i++) {
				ItemStack stack = itemHandler.getStackInSlot(i);

				if (stack.is(AoAItems.COMPRESSED_ITEM) || (stack.isStackable() && stack.getCount() >= stack.getMaxStackSize()))
					count++;
			}

			return count;
		}

		return 0;
	}

	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (player instanceof ServerPlayer pl)
			InfusedPressMenu.openContainer(pl, pos);

		return InteractionResults.BlockUseWithoutItem.succeedAndSwingArmBothSides(level.isClientSide);
	}

	@Nullable
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		if (level.getBlockEntity(pos) instanceof InfusedPressBlockEntity blockEntity)
			return blockEntity;

		return null;
	}
}
