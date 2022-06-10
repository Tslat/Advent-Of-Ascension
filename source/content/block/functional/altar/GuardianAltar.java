package net.tslat.aoa3.content.block.functional.altar;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

public class GuardianAltar extends Block {
	public GuardianAltar() {
		super(new BlockUtil.CompactProperties(Material.STONE, MaterialColor.COLOR_LIGHT_GREEN).stats(35f, 1000f).get());
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (!WorldUtil.isWorld(world, AoADimensions.HAVEN.key))
			return InteractionResult.FAIL;

		if (heldStack.getItem() == AoAItems.VOLIANT_HEART.get()) {
			if (!world.isClientSide) {
				for (Direction direction : Direction.Plane.HORIZONTAL) {
					if (world.getSignal(pos.relative(direction), direction) == 0)
						return InteractionResult.FAIL;
				}

				if (!world.getEntitiesOfClass(Monster.class, new AABB(pos.getX() - 15, pos.getY() - 15, pos.getZ() - 15, pos.getX() + 15, pos.getY() + 15, pos.getZ() + 15), entity -> !entity.canChangeDimensions()).isEmpty())
					return InteractionResult.FAIL;

				if (!player.isCreative())
					heldStack.shrink(1);

				for (Direction direction : Direction.Plane.HORIZONTAL) {
					BlockPos checkPos = pos.relative(direction);

					if (world.getBlockState(checkPos).getBlock() == Blocks.REDSTONE_WIRE)
						breakWire(world, checkPos, 0);
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

				for (Player pl : world.getEntitiesOfClass(Player.class, new AABB(pos.getX() - 25, pos.getY() - 25, pos.getZ() - 25, pos.getX() + 26, pos.getY() + 26, pos.getZ() + 26))) {
					pl.sendSystemMessage(LocaleUtil.getLocaleMessage("message.mob.four_guardians.spawn"));
				}
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
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
