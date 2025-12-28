package net.tslat.aoa3.content.item.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.datacomponent.CompressedItemData;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.tme.api.object.extension.Text;

import java.util.List;

public class CompressedItem extends Item {
    public CompressedItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public Component getName(ItemStack stack) {
        if (!stack.has(AoADataComponents.COMPRESSED_ITEM_DATA))
            return super.getName(stack);

        return Text.of("item.aoa3.compressed_item.name", stack.get(AoADataComponents.COMPRESSED_ITEM_DATA.get()).compressedStack());
    }

    public static long getCompressedCount(int maxStackSize, int compressions) {
        return maxStackSize * Math.round(Math.pow(9, compressions));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        CompressedItemData data = stack.has(AoADataComponents.COMPRESSED_ITEM_DATA) ? stack.get(AoADataComponents.COMPRESSED_ITEM_DATA) : null;
        int compressions = data != null ? data.compressions() : 0;

        if (compressions > 0)
            tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.SPECIAL, 1, Component.literal(NumberUtil.floorAndAppendSuffix(getCompressedCount(data.compressedStack().getMaxStackSize(), compressions), true))));
    }
}
