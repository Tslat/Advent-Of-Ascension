package net.tslat.aoa3.content.item.tool.pickaxe;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.FakePlayer;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Pickmax extends BasePickaxe {
	public Pickmax() {
		super(ItemUtil.customItemTier(3000, 8.0f, 6.0f, 6, 10, null, BlockTags.MINEABLE_WITH_PICKAXE));
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entity) {
		super.mineBlock(stack, world, state, pos, entity);

		if (!world.isClientSide && entity instanceof Player && !(entity instanceof FakePlayer) && (state.is(Tags.Blocks.STONE) || state.is(Tags.Blocks.COBBLESTONE))) {
			for (int i = pos.getX() - 1; i < pos.getX() + 2; i++) {
				for (int j = pos.getY() - 1; j < pos.getY() + 2; j++) {
					for (int k = pos.getZ() - 1; k < pos.getZ() + 2; k++) {
						if (pos.getX() == i && pos.getY() == j && pos.getZ() == k)
							continue;

						Player pl = (Player)entity;
						BlockPos breakPos = new BlockPos(i, j, k);
						BlockState extraBlock = world.getBlockState(breakPos);

						if ((extraBlock.is(Tags.Blocks.STONE) || extraBlock.is(Tags.Blocks.COBBLESTONE)) && world.getBlockState(pos).getDestroyProgress(pl, world, pos) / extraBlock.getDestroyProgress(pl, world, breakPos) < 10f)
							WorldUtil.harvestAdditionalBlock(world, (Player)entity, breakPos, false);
					}
				}
			}
		}

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
