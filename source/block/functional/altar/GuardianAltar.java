package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.BlueGuardianEntity;
import net.tslat.aoa3.entity.boss.GreenGuardianEntity;
import net.tslat.aoa3.entity.boss.RedGuardianEntity;
import net.tslat.aoa3.entity.boss.YellowGuardianEntity;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.LocaleUtil;

public class GuardianAltar extends Block {
	public GuardianAltar() {
		super(BlockUtil.generateBlockProperties(Material.ROCK, MaterialColor.LIME, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == AoAItems.VOLIANT_HEART.get()) {
			if (!world.isRemote) {
				for (Direction direction : Direction.Plane.HORIZONTAL) {
					if (world.getRedstonePower(pos.offset(direction), direction) == 0)
						return ActionResultType.FAIL;
				}

				if (!world.getEntitiesWithinAABB(MonsterEntity.class, new AxisAlignedBB(pos.getX() - 15, pos.getY() - 15, pos.getZ() - 15, pos.getX() + 15, pos.getY() + 15, pos.getZ() + 15), entity -> !entity.isNonBoss()).isEmpty())
					return ActionResultType.FAIL;

				if (!player.isCreative())
					heldStack.shrink(1);

				for (Direction direction : Direction.Plane.HORIZONTAL) {
					BlockPos checkPos = pos.offset(direction);

					if (world.getBlockState(checkPos).getBlock() == Blocks.REDSTONE_WIRE)
						breakWire(world, checkPos, 0);
				}

				BlueGuardianEntity blueGuardian = new BlueGuardianEntity(AoAEntities.Mobs.BLUE_GUARDIAN.get(), world);
				YellowGuardianEntity yellowGuardian = new YellowGuardianEntity(AoAEntities.Mobs.YELLOW_GUARDIAN.get(), world);
				GreenGuardianEntity greenGuardian = new GreenGuardianEntity(AoAEntities.Mobs.GREEN_GUARDIAN.get(), world);
				RedGuardianEntity redGuardian = new RedGuardianEntity(AoAEntities.Mobs.RED_GUARDIAN.get(), world);

				blueGuardian.setLocationAndAngles(pos.getX() + 8, pos.getY(), pos.getZ() + 8, 0, 0);
				redGuardian.setLocationAndAngles(pos.getX() - 8, pos.getY(), pos.getZ() + 8, 0, 0);
				yellowGuardian.setLocationAndAngles(pos.getX() - 8, pos.getY(), pos.getZ() - 8, 0, 0);
				greenGuardian.setLocationAndAngles(pos.getX() + 8, pos.getY(), pos.getZ() - 8, 0, 0);
				world.addEntity(blueGuardian);
				world.addEntity(greenGuardian);
				world.addEntity(yellowGuardian);
				world.addEntity(redGuardian);
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
				yellowGuardian.setRedGuardian(redGuardian);

				for (PlayerEntity pl : world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(pos.getX() - 25, pos.getY() - 25, pos.getZ() - 25, pos.getX() + 26, pos.getY() + 26, pos.getZ() + 26))) {
					pl.sendMessage(LocaleUtil.getLocaleMessage("message.mob.fourGuardians.spawn"));
				}
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	private int breakWire(World world, BlockPos curPos, int currentCount) {
		world.addEntity(new ItemEntity(world, curPos.getX(), curPos.getY(), curPos.getZ(), new ItemStack(Blocks.REDSTONE_WIRE)));
		world.setBlockState(curPos, Blocks.AIR.getDefaultState());
		currentCount++;

		for (Direction direction : Direction.Plane.HORIZONTAL) {
			BlockPos newPos = curPos.offset(direction);

			if (world.getBlockState(newPos).getBlock() == Blocks.REDSTONE_WIRE)
				currentCount = breakWire(world, newPos, currentCount);

			if (currentCount >= 20)
				return currentCount;
		}

		return currentCount;
	}
}
