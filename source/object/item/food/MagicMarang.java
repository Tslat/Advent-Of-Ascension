package net.tslat.aoa3.object.item.food;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.List;

public class MagicMarang extends BlockNamedItem {
	public MagicMarang() {
		super(AoABlocks.MAGIC_MARANG_CROP.get(), new Item.Properties().tab(AoAItemGroups.FOOD).food(new Food.Builder().nutrition(5).saturationMod(0.7f).effect(new PotionUtil.EffectBuilder(Effects.NIGHT_VISION, 200).build(), 1).effect(new PotionUtil.EffectBuilder(Effects.GLOWING, 200).build(), 1).build()));
	}

	@Override
	protected boolean canPlace(BlockItemUseContext context, BlockState state) {
		return context.getLevel().getBlockState(new BlockPos(context.getClickLocation())).getBlock() == AoABlocks.HAUNTED_LEAVES.get() && super.canPlace(context, state);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
