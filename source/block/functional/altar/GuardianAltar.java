package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.BasicBlock;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.fourguardians.EntityBlueGuardian;
import net.tslat.aoa3.entity.boss.fourguardians.EntityGreenGuardian;
import net.tslat.aoa3.entity.boss.fourguardians.EntityRedGuardian;
import net.tslat.aoa3.entity.boss.fourguardians.EntityYellowGuardian;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.utils.StringUtil;

public class GuardianAltar extends BasicBlock {
	public GuardianAltar() {
		super("GuardianAltar", "guardian_altar", Material.ROCK, -1f, 999999999f);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == ItemRegister.voliantHeart) {
			if (!world.isRemote) {
				for (EnumFacing direction : EnumFacing.HORIZONTALS) {
					if (world.getRedstonePower(pos.offset(direction), direction) == 0)
						return true;
				}

				if (!world.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.getX() - 15, pos.getY() - 15, pos.getZ() - 15, pos.getX() + 15, pos.getY() + 15, pos.getZ() + 15), entity -> entity instanceof BossEntity).isEmpty())
					return true;

				if (!player.capabilities.isCreativeMode)
					heldStack.shrink(1);

				for (EnumFacing direction : EnumFacing.HORIZONTALS) {
					BlockPos checkPos = pos.offset(direction);

					if (world.getBlockState(checkPos).getBlock() == Blocks.REDSTONE_WIRE)
						breakWire(world, checkPos, 0);
				}

				EntityBlueGuardian blueGuardian = new EntityBlueGuardian(world);
				EntityYellowGuardian yellowGuardian = new EntityYellowGuardian(world);
				EntityGreenGuardian greenGuardian = new EntityGreenGuardian(world);
				EntityRedGuardian redGuardian = new EntityRedGuardian(world);

				blueGuardian.setLocationAndAngles(pos.getX() + 8, pos.getY(), pos.getZ() + 8, 0, 0);
				redGuardian.setLocationAndAngles(pos.getX() - 8, pos.getY(), pos.getZ() + 8, 0, 0);
				yellowGuardian.setLocationAndAngles(pos.getX() - 8, pos.getY(), pos.getZ() - 8, 0, 0);
				greenGuardian.setLocationAndAngles(pos.getX() + 8, pos.getY(), pos.getZ() - 8, 0, 0);
				world.spawnEntity(blueGuardian);
				world.spawnEntity(greenGuardian);
				world.spawnEntity(yellowGuardian);
				world.spawnEntity(redGuardian);
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

				for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.getX() - 25, pos.getY() - 25, pos.getZ() - 25, pos.getX() + 26, pos.getY() + 26, pos.getZ() + 26))) {
					pl.sendMessage(StringUtil.getLocale("message.mob.fourGuardians.spawn"));
				}
			}

			return true;
		}

		return false;
	}

	private int breakWire(World world, BlockPos curPos, int currentCount) {
		world.spawnEntity(new EntityItem(world, curPos.getX(), curPos.getY(), curPos.getZ(), new ItemStack(Blocks.REDSTONE_WIRE)));
		world.setBlockToAir(curPos);
		currentCount++;

		for (EnumFacing direction : EnumFacing.HORIZONTALS) {
			BlockPos newPos = curPos.offset(direction);

			if (world.getBlockState(newPos).getBlock() == Blocks.REDSTONE_WIRE)
				currentCount = breakWire(world, newPos, currentCount);

			if (currentCount >= 20)
				return currentCount;
		}

		return currentCount;
	}
}
