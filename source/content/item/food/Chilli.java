package net.tslat.aoa3.content.item.food;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.item.AoAFood;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Chilli extends Item {
	public Chilli() {
		super(new Item.Properties().food(AoAFood.CHILLI));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (!world.isClientSide()) {
			entity.setSecondsOnFire(3);

			if (entity instanceof ServerPlayer && WorldUtil.isWorld(world, AoADimensions.NETHER.key) && entity.isInLava())
				AdvancementUtil.completeAdvancement((ServerPlayer)entity, new ResourceLocation(AdventOfAscension.MOD_ID, "nether/overheat"), "lava_chilli_consume");
		}

		return super.finishUsingItem(stack, world, entity);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
