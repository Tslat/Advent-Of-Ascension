package net.tslat.aoa3.item.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.library.resourcemanager.MiscTextFileManager;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.ClientOperations;

public class WornBook extends WrittenBookItem {
	private static final CompoundNBT contents = new CompoundNBT();

	public WornBook() {
		super(new Item.Properties().group(AoAItemGroups.MISC_ITEMS).maxStackSize(1));
	}

	@OnlyIn(Dist.CLIENT)
	public static ItemStack getBook(ItemStack stack) {
		stack.setTag(getBookContents());
		return stack;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack bookStack = player.getHeldItem(hand);

		if (!world.isRemote) {
			if (!ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), false, 1)) {
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()));
				player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.wornBook.droppedRealmstone"));
			}
		}
		else {
			DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientOperations::displayWornBookGui);
		}

		return ActionResult.resultSuccess(bookStack);
	}

	@OnlyIn(Dist.CLIENT)
	public static CompoundNBT getBookContents() {
		contents.putString("author", LocaleUtil.getLocaleString("entity.aoa3.corrupted_traveller"));
		contents.putString("title", LocaleUtil.getLocaleString("item.aoa3.worn_book"));

		String pageContents = MiscTextFileManager.DATA.get(AoAItems.WORN_BOOK.get());

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
	public boolean hasEffect(ItemStack stack) {
		return false;
	}
}
