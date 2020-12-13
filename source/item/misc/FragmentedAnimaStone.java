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
		super(new Item.Properties().group(AoAItemGroups.MISC_ITEMS));
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity player = context.getPlayer();
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		ItemStack stack = context.getItem();

		if (player != null && !player.canPlayerEdit(pos.offset(context.getFace()), context.getFace(), stack))
			return ActionResultType.FAIL;

		BlockState state = world.getBlockState(pos);

		if (player != null) {
			int bonemealEvent = ForgeEventFactory.onApplyBonemeal(player, world, pos, state, stack);

			if (bonemealEvent != 0)
				return bonemealEvent > 0 ? ActionResultType.PASS : ActionResultType.FAIL;
		}

		if (state.getBlock() instanceof IGrowable) {
			IGrowable growable = (IGrowable)state.getBlock();

			if (growable.canGrow(world, pos, state, world.isRemote) && world instanceof ServerWorld) {
				PlayerDataManager.PlayerStats plStats = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player).stats();
				Block block = state.getBlock();

				if (block instanceof SaplingBlock) {
					((SaplingBlock)block).placeTree((ServerWorld)world, pos, state, world.rand);
				}
				else if (block instanceof CropsBlock) {
					world.setBlockState(pos, ((CropsBlock)block).withAge(((CropsBlock)block).getMaxAge()), 2);
				}
				else if (block instanceof CocoaBlock) {
					world.setBlockState(pos, state.with(CocoaBlock.AGE, 2), 2);
				}
				else {
					int backupCounter = block instanceof GrassBlock ? 1 : 10;

					while (world.getBlockState(pos).equals(state) && backupCounter > 0) {
						backupCounter--;
						growable.grow((ServerWorld)world, world.rand, pos, state);
					}
				}

				plStats.addXp(Skills.ANIMA, PlayerUtil.getXpRequiredForNextLevel(plStats.getLevel(Skills.ANIMA)) / AnimaUtil.getExpDenominator(plStats.getLevel(Skills.ANIMA)), false, false);
				world.playEvent(2005, pos, 0);

				if (!player.isCreative())
					stack.shrink(1);
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}
}
