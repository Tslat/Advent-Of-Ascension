package net.tslat.aoa3.content.item.weapon.sniper;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.List;

public class CamoRifle extends AoASniper {
	public CamoRifle(Item.Properties properties) {
		super(properties);
	}

	@Override
	public ResourceLocation getScopeTexture(ItemStack stack) {
		return CLASSIC;
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity holder, int itemSlot, boolean isSelected) {
		if (!world.isClientSide && isSelected && holder.isCrouching())
			EntityUtil.applyPotions(holder, holder, new EffectBuilder(MobEffects.INVISIBILITY, 5));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
