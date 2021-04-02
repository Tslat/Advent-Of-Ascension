package net.tslat.aoa3.item.misc;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.util.skill.AnimaUtil;

public class FragmentedAnimaStone extends Item {
	public FragmentedAnimaStone() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS));
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		PlayerEntity player = context.getPlayer();
		World world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		ItemStack stack = context.getItemInHand();

		if (player != null && !player.mayUseItemAt(pos.relative(context.getClickedFace()), context.getClickedFace(), stack))
			return ActionResultType.FAIL;

		BlockState state = world.getBlockState(pos);

		if (player != null) {
			int bonemealEvent = ForgeEventFactory.onApplyBonemeal(player, world, pos, state, stack);

			if (bonemealEvent != 0)
				return bonemealEvent > 0 ? ActionResultType.PASS : ActionResultType.FAIL;
		}

		if (state.getBlock() instanceof IGrowable) {
			IGrowable growable = (IGrowable)state.getBlock();

			if (growable.isValidBonemealTarget(world, pos, state, world.isClientSide) && world instanceof ServerWorld) {
				PlayerDataManager.PlayerStats plStats = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player).stats();
				Block block = state.getBlock();

				if (block instanceof SaplingBlock) {
					int backupCounter = 10;

					while (world.getBlockState(pos).getBlock() == state.getBlock() && backupCounter > 0) {
						backupCounter--;
						((SaplingBlock)block).advanceTree((ServerWorld)world, pos, world.getBlockState(pos), world.getRandom());
					}
				}
				else if (block instanceof CropsBlock) {
					world.setBlock(pos, ((CropsBlock)block).getStateForAge(((CropsBlock)block).getMaxAge()), 2);
				}
				else if (block instanceof CocoaBlock) {
					world.setBlock(pos, state.setValue(CocoaBlock.AGE, 2), 2);
				}
				else {
					int backupCounter = block instanceof GrassBlock ? 1 : 10;

					while (world.getBlockState(pos).equals(state) && backupCounter > 0) {
						backupCounter--;
						growable.performBonemeal((ServerWorld)world, world.getRandom(), pos, state);
					}
				}

				plStats.addXp(Skills.ANIMA, PlayerUtil.getXpRequiredForNextLevel(plStats.getLevel(Skills.ANIMA)) / AnimaUtil.getExpDenominator(plStats.getLevel(Skills.ANIMA)), false, false);
				world.levelEvent(2005, pos, 0);

				if (!player.isCreative())
					stack.shrink(1);
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}
}
