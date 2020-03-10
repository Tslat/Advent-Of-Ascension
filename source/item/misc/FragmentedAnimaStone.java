package net.tslat.aoa3.item.misc;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;
import net.tslat.aoa3.utils.skills.AnimaUtil;

public class FragmentedAnimaStone extends SimpleItem {
	public FragmentedAnimaStone() {
		super("FragmentedAnimaStone", "fragmented_anima_stone");
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);

		if (!player.canPlayerEdit(pos.offset(facing), facing, stack))
			return EnumActionResult.FAIL;

		IBlockState state = world.getBlockState(pos);
		int bonemealEvent = ForgeEventFactory.onApplyBonemeal(player, world, pos, state, stack, hand);

		if (bonemealEvent != 0)
			return bonemealEvent > 0 ? EnumActionResult.PASS : EnumActionResult.FAIL;

		if (state.getBlock() instanceof IGrowable) {
			IGrowable growable = (IGrowable)state.getBlock();

			if (growable.canGrow(world, pos, state, world.isRemote) && !world.isRemote) {
				PlayerDataManager.PlayerStats plStats = PlayerUtil.getAdventPlayer(player).stats();
				Block block = state.getBlock();

				if (block instanceof BlockSapling) {
					((BlockSapling)block).generateTree(world, pos, state, world.rand);
				}
				else if (block instanceof BlockCrops) {
					world.setBlockState(pos, ((BlockCrops)block).withAge(((BlockCrops)block).getMaxAge()), 2);
				}
				else if (block instanceof BlockCocoa) {
					world.setBlockState(pos, state.withProperty(BlockCocoa.AGE, 2), 2);
				}
				else {
					int backupCounter = block instanceof BlockGrass ? 1 : 10;

					while (world.getBlockState(pos).equals(state) && backupCounter > 0) {
						backupCounter--;
						growable.grow(world, world.rand, pos, state);
					}
				}

				plStats.addXp(Enums.Skills.ANIMA, PlayerUtil.getXpRequiredForNextLevel(plStats.getLevel(Enums.Skills.ANIMA)) / AnimaUtil.getExpDenominator(plStats.getLevel(Enums.Skills.ANIMA)), false, false);
				world.playEvent(2005, pos, 0);

				if (!player.capabilities.isCreativeMode)
					stack.shrink(1);
			}

			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.PASS;
	}
}
