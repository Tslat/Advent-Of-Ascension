package net.tslat.aoa3.content.item.tool.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.content.entity.misc.GoldFishingBobberEntity;
import net.tslat.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.ListIterator;

public class GoldenRod extends HaulingRod {
	public GoldenRod(Properties itemProperties) {
		super(itemProperties);
	}

	@Override
	public int getLuckMod(PlayerEntity player, ItemStack stack) {
		return super.getLuckMod(player, stack) + 3;
	}

	@Override
	protected List<ItemStack> getLootForHauledEntity(ServerPlayerEntity player, ItemStack stack, HaulingFishingBobberEntity bobber, Entity hookedEntity) {
		List<ItemStack> loot = super.getLootForHauledEntity(player, stack, bobber, hookedEntity);

		for (ListIterator<ItemStack> iterator = loot.listIterator(); iterator.hasNext();) {
			ItemStack itStack = iterator.next();

			ItemUtil.increaseStackSize(itStack, itStack.getCount()).forEach(iterator::add);
		}

		return loot;
	}

	@Override
	protected HaulingFishingBobberEntity getNewBobber(PlayerEntity player, ItemStack stack, int lureMod, int luckMod) {
		return new GoldFishingBobberEntity(player, player.level, stack, luckMod, lureMod);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
