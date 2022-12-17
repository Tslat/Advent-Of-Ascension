package net.tslat.aoa3.content.item.lootbox;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.LootUtil;

import javax.annotation.Nullable;
import java.util.List;

public class WeaponsCase extends Item {
	public WeaponsCase() {
		super(new Item.Properties());
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (player instanceof ServerPlayer pl) {
			ItemUtil.givePlayerMultipleItems(pl, LootUtil.generateLoot((ServerLevel)pl.level, new ResourceLocation(AdventOfAscension.MOD_ID, "items/weapons_case"), LootUtil.getGiftContext((ServerLevel)pl.level, pl.position(), pl)));

			if (!pl.isCreative())
				heldStack.shrink(1);

			pl.inventoryMenu.broadcastChanges();

			return InteractionResultHolder.success(heldStack);
		}

		return InteractionResultHolder.pass(heldStack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
