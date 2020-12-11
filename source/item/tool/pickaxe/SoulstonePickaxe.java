package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SoulstonePickaxe extends BasePickaxe implements SpecialHarvestTool {
	public SoulstonePickaxe() {
		super(ItemUtil.customItemTier(2000, 11.0f, 6.0f, 6, 10, null));
	}

	@Override
	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (e.getHarvester() != null && !e.getWorld().isRemote()) {
			Block block = e.getState().getBlock();

			if ((block.isIn(Tags.Blocks.STONE) || block.isIn(Tags.Blocks.COBBLESTONE)) && PlayerUtil.consumeResource((ServerPlayerEntity)e.getHarvester(), Resources.SOUL, 1, false)) {
				ItemStack primaryStack = e.getDrops().get(0);

				primaryStack.setCount(primaryStack.getCount() * 2);
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
