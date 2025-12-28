package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;

public class GuardianAltar extends Block {
	public GuardianAltar(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (!WorldUtil.isWorld(level, AoADimensions.HAVEN))
			return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;

		if (stack.getItem() == AoAItems.VOLIANT_HEART.get()) {
			for (Direction direction : Direction.Plane.HORIZONTAL) {
				if (level.getSignal(pos.relative(direction), direction) == 0)
					return ItemInteractionResult.FAIL;
			}

			if (!EntityRetrievalUtil.getEntities(level, AABB.ofSize(Vec3.atCenterOf(pos), 30, 30, 30), entity -> entity.getType().is(Tags.EntityTypes.BOSSES)).isEmpty())
				return ItemInteractionResult.FAIL;

			if (!level.isClientSide) {
				if (!player.getAbilities().instabuild)
					stack.shrink(1);

				for (Direction direction : Direction.Plane.HORIZONTAL) {
					BlockPos checkPos = pos.relative(direction);

					if (level.getBlockState(checkPos).getBlock() == Blocks.REDSTONE_WIRE)
						breakWire(level, checkPos, 0);
				}

				/*BlueGuardianEntity blueGuardian = new BlueGuardianEntity(AoAMobs.BLUE_GUARDIAN.get(), world);
				YellowGuardianEntity yellowGuardian = new YellowGuardianEntity(AoAMobs.YELLOW_GUARDIAN.get(), world);
				GreenGuardianEntity greenGuardian = new GreenGuardianEntity(AoAMobs.GREEN_GUARDIAN.get(), world);
				RedGuardianEntity redGuardian = new RedGuardianEntity(AoAMobs.RED_GUARDIAN.get(), world);

				blueGuardian.moveTo(pos.getX() + 8, pos.getY(), pos.getZ() + 8, 0, 0);
				redGuardian.moveTo(pos.getX() - 8, pos.getY(), pos.getZ() + 8, 0, 0);
				yellowGuardian.moveTo(pos.getX() - 8, pos.getY(), pos.getZ() - 8, 0, 0);
				greenGuardian.moveTo(pos.getX() + 8, pos.getY(), pos.getZ() - 8, 0, 0);
				world.addFreshEntity(blueGuardian);
				world.addFreshEntity(greenGuardian);
				world.addFreshEntity(yellowGuardian);
				world.addFreshEntity(redGuardian);
				blueGuardian.setGreenGuardian(greenGuardian);
				blueGuardian.setRedGuardian(redGuardian);
				blueGuardian.setYellowGuardian(yellowGuardian);
				greenGuardian.setBlueGuardian(blueGuardian);
				greenGuardian.setRedGuardian(redGuardian);
				greenGuardian.setYellowGuardian(yellowGuardian);
				redGuardian.setBlueGuardian(blueGuardian);
				redGuardian.setGreenGuardian(greenGuardian);
				redGuardian.setYellowGuardian(yellowGuardian);
				yellowGuardian.setBlueGuardian(blueGuardian);
				yellowGuardian.setGreenGuardian(greenGuardian);
				yellowGuardian.setRedGuardian(redGuardian);*/

				for (Player pl : EntityRetrievalUtil.getPlayers(level, AABB.ofSize(Vec3.atCenterOf(pos), 50, 50, 50))) {
					pl.sendSystemMessage(LocaleUtil.getLocaleMessage("message.mob.four_guardians.spawn"));
				}
			}

			return ItemInteractionResult.sidedSuccess(level.isClientSide);
		}

		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}

	private int breakWire(Level world, BlockPos curPos, int currentCount) {
		world.addFreshEntity(new ItemEntity(world, curPos.getX(), curPos.getY(), curPos.getZ(), new ItemStack(Blocks.REDSTONE_WIRE)));
		world.setBlockAndUpdate(curPos, Blocks.AIR.defaultBlockState());
		currentCount++;

		for (Direction direction : Direction.Plane.HORIZONTAL) {
			BlockPos newPos = curPos.relative(direction);

			if (world.getBlockState(newPos).getBlock() == Blocks.REDSTONE_WIRE)
				currentCount = breakWire(world, newPos, currentCount);

			if (currentCount >= 20)
				return currentCount;
		}

		return currentCount;
	}
}
