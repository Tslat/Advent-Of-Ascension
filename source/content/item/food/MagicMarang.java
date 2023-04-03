package net.tslat.aoa3.content.item.food;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

import javax.annotation.Nullable;
import java.util.List;

public class MagicMarang extends ItemNameBlockItem {
	public MagicMarang() {
		super(AoABlocks.MAGIC_MARANG_CROP.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.7f).effect(new EffectBuilder(MobEffects.NIGHT_VISION, 200).build(), 1).effect(new EffectBuilder(MobEffects.GLOWING, 200).build(), 1).build()));
	}

	@Override
	protected boolean canPlace(BlockPlaceContext context, BlockState state) {
		Block block = context.getLevel().getBlockState(BlockPos.containing(context.getClickLocation())).getBlock();

		return (block == AoABlocks.HAUNTED_LEAVES.get() || block == AoABlocks.HAUNTED_EYES_LEAVES.get()) && super.canPlace(context, state);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
