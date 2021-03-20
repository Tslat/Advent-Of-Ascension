package net.tslat.aoa3.item.lootbox;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.LootUtil;

import javax.annotation.Nullable;
import java.util.List;

public class GemBag extends Item {
	public GemBag() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS));
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (player instanceof ServerPlayerEntity) {
			ServerPlayerEntity pl = (ServerPlayerEntity)player;

			ItemUtil.givePlayerMultipleItems(pl, LootUtil.generateLoot((ServerWorld)pl.level, new ResourceLocation(AdventOfAscension.MOD_ID, "items/gem_bag"), LootUtil.getGiftContext((ServerWorld)pl.level, pl.position(), pl)));

			if (!pl.isCreative())
				heldStack.shrink(1);

			pl.inventoryMenu.broadcastChanges();

			return ActionResult.success(heldStack);
		}

		return ActionResult.pass(heldStack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
