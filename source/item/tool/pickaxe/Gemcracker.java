package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Gemcracker extends BasePickaxe implements SpecialHarvestTool {
	public Gemcracker() {
		super(ItemUtil.customItemTier(2100, 10.0f, 6.0f, 6, 10, null));
	}

	@Override
	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		Block block = e.getState().getBlock();

		if (!e.getDrops().isEmpty() && block.isIn(Tags.Blocks.ORES)) {
			Item blockDrop = block.asItem();
			ItemStack primaryDrop = e.getDrops().get(0);

			if (blockDrop != primaryDrop.getItem())
				primaryDrop.setCount(primaryDrop.getCount() + (int)Math.ceil((primaryDrop.getCount() * random.nextFloat())));
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
