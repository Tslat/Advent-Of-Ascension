package net.tslat.aoa3.content.item.misc;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.data.client.MiscellaneousReloadListener;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.integration.patchouli.PatchouliIntegration;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class WornBook extends WrittenBookItem {
	private static final CompoundTag contents = new CompoundTag();

	public WornBook() {
		super(new Item.Properties().stacksTo(1));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack bookStack = player.getItemInHand(hand);

		if (!world.isClientSide) {
			if (!ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), false, 1, false)) {
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()));
				player.sendSystemMessage(LocaleUtil.getLocaleMessage(LocaleUtil.createFeedbackLocaleKey("wornBook.droppedRealmstone")));
				PlayerUtil.getAdventPlayer((ServerPlayer)player).addPatchouliBook(AdventOfAscension.id("worn_book"));
			}
		}
		else {
			if (IntegrationManager.isPatchouliActive()) {
				PatchouliIntegration.openBook(AdventOfAscension.id("worn_book"));
			}
			else {
				ClientOperations.displayWornBookGui();
			}
		}

		return InteractionResultHolder.success(bookStack);
	}

	public static ItemStack getBook(ItemStack stack) {
		stack.setTag(getBookContents());

		return stack;
	}

	public static CompoundTag getBookContents() {
		contents.putString("author", LocaleUtil.getLocaleString("entity.aoa3.corrupted_traveller"));
		contents.putString("title", LocaleUtil.getLocaleString("item.aoa3.worn_book"));

		String pageContents = MiscellaneousReloadListener.DATA.get(AoAItems.WORN_BOOK.get());

		if (pageContents == null)
			return contents;

		String[] lines = pageContents.split("\n");
		ListTag pages = new ListTag();

		for (String line : lines) {
			pages.add(StringTag.valueOf(line.replaceAll("<br>", "\n")));
		}

		contents.put("pages", pages);

		return contents;
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}
}
