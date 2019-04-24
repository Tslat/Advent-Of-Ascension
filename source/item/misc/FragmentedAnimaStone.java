package net.tslat.aoa3.item.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;
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

		if (ItemDye.applyBonemeal(stack, world, pos, player, hand)) {
			if (!world.isRemote) {
				AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(player);

				cap.addXp(Enums.Skills.ANIMA, cap.getXpReqForLevel(cap.getLevel(Enums.Skills.ANIMA)) / AnimaUtil.getExpDenominator(cap.getLevel(Enums.Skills.ANIMA)), false);
				world.playEvent(2005, pos, 0);
			}

			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.PASS;
	}
}
