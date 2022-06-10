package net.tslat.aoa3.content.item.food;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Rosidons extends Item {
	public Rosidons() {
		super(new Item.Properties().tab(AoAItemGroups.FOOD).food(new FoodProperties.Builder().nutrition(0).saturationMod(0).alwaysEat().build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (!world.isClientSide) {
			if (WorldUtil.isWorld(world, AoADimensions.NOWHERE.key)) {
				PlayerUtil.notifyPlayer((ServerPlayer)entity, Component.translatable("message.feedback.item.rosidons.dimFail"));

				return super.finishUsingItem(stack, world, entity);
			}

			int calculatedY = WorldUtil.getTrueWorldHeight(world, (int)entity.getX(), (int)entity.getZ());

			if (calculatedY == 0) {
				PlayerUtil.notifyPlayer((ServerPlayer)entity, Component.translatable("message.feedback.item.rosidons.noHeightFail"));

				return super.finishUsingItem(stack, world, entity);
			}

			entity.teleportTo(entity.getX(), calculatedY + 2, entity.getZ());
		}

		return super.finishUsingItem(stack, world, entity);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
