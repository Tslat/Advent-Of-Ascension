package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Chilli extends Item {
	public Chilli() {
		super(new Item.Properties().tab(AoAItemGroups.FOOD).food(new Food.Builder().nutrition(0).saturationMod(0).alwaysEat().build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		if (!world.isClientSide()) {
			entity.setSecondsOnFire(3);

			if (entity instanceof ServerPlayerEntity && WorldUtil.isWorld(world, AoADimensions.NETHER.key) && entity.isInLava())
				AdvancementUtil.completeAdvancement((ServerPlayerEntity)entity, new ResourceLocation(AdventOfAscension.MOD_ID, "nether/overheat"), "lava_chilli_consume");
		}

		return super.finishUsingItem(stack, world, entity);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
