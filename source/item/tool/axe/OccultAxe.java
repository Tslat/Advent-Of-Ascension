package net.tslat.aoa3.item.tool.axe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class OccultAxe extends BaseAxe {
	public OccultAxe() {
		super(ItemUtil.customItemTier(3000, 11.0f, 11.5f, 6, 10, null),
				new Properties().durability(3000).tab(AoAItemGroups.TOOLS).rarity(Rarity.RARE));
	}

	@Override
	public boolean mineBlock(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entity) {
		if (entity instanceof PlayerEntity && state.is(BlockTags.LOGS)) {
			BlockPos breakPos = pos;
			Block originBlock = state.getBlock();
			ItemStack toolStack = entity.getItemInHand(Hand.MAIN_HAND);

			while (world.getBlockState(breakPos = breakPos.above()).getBlock() == originBlock && !toolStack.isEmpty()) {
				WorldUtil.harvestAdditionalBlock(world, (PlayerEntity)entity, breakPos);
			}
		}

		return super.mineBlock(stack, world, state, pos, entity);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
