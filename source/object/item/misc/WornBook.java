package net.tslat.aoa3.object.item.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.data.client.MiscellaneousReloadListener;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.util.PlayerUtil;

public class WornBook extends WrittenBookItem {
	private static final CompoundNBT contents = new CompoundNBT();

	public WornBook() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).stacksTo(1));
	}

	@OnlyIn(Dist.CLIENT)
	public static ItemStack getBook(ItemStack stack) {
		stack.setTag(getBookContents());
		return stack;
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack bookStack = player.getItemInHand(hand);

		if (!world.isClientSide) {
			if (!ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), false, 1)) {
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()));
				player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.wornBook.droppedRealmstone"), Util.NIL_UUID);
				PlayerUtil.getAdventPlayer((ServerPlayerEntity)player).addPatchouliBook(AdventOfAscension.id("worn_book"));
			}
		}
		else {
			DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientOperations::displayWornBookGui);

		}

		return ActionResult.success(bookStack);
	}

	@OnlyIn(Dist.CLIENT)
	public static CompoundNBT getBookContents() {
		contents.putString("author", LocaleUtil.getLocaleString("entity.aoa3.corrupted_traveller"));
		contents.putString("title", LocaleUtil.getLocaleString("item.aoa3.worn_book"));

		String pageContents = MiscellaneousReloadListener.DATA.get(AoAItems.WORN_BOOK.get());

		if (pageContents == null)
			return contents;

		String[] lines = pageContents.split("\n");
		ListNBT pages = new ListNBT();

		for (String line : lines) {
			pages.add(StringNBT.valueOf(line.replaceAll("<br>", "\n")));
		}

		contents.put("pages", pages);

		return contents;
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}
}
