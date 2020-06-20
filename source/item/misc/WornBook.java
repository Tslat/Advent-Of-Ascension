package net.tslat.aoa3.item.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.FileUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

public class WornBook extends ItemWrittenBook {
	private static final NBTTagCompound contents = new NBTTagCompound();

	public WornBook() {
		super();

		this.setTranslationKey("WornBook");
		this.setRegistryName("aoa3:worn_book");
		setCreativeTab(CreativeTabsRegister.MISC);
	}

	@SideOnly(Side.CLIENT)
	public static ItemStack getBook() {
		ItemStack stack = new ItemStack(ItemRegister.WORN_BOOK, 1);
		stack.setTagCompound(getBookContents());
		return stack;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack bookStack = player.getHeldItem(hand);

		if (world.isRemote) {
			player.openGui(AdventOfAscension.instance(), Enums.ModGuis.WORN_BOOK.guiId, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		else if (ItemUtil.findItemInInventory(player, ItemRegister.BLANK_REALMSTONE) == -1) {
			ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.BLANK_REALMSTONE));
			player.sendMessage(StringUtil.getLocale("message.feedback.wornBook.droppedRealmstone"));
		}

		return ActionResult.newResult(EnumActionResult.SUCCESS, bookStack);
	}

	@SideOnly(Side.CLIENT)
	public static NBTTagCompound getBookContents() {
		contents.setString("author", StringUtil.getLocaleString("entity.aoa3.corrupted_traveller.name"));
		contents.setString("title", StringUtil.getLocaleString("item.WornBook.name"));

		String pageContents = FileUtil.getTextFromResourceFile("aoa3", "lang/aoa3/misc/" + FMLCommonHandler.instance().getCurrentLanguage() + "/worn_book.txt", "lang/aoa3/misc/en_us/worn_book.txt");

		if (pageContents == null)
			return contents;

		String[] lines = pageContents.split("\n");
		NBTTagList pages = new NBTTagList();

		for (String line : lines) {
			pages.appendTag(new NBTTagString(line.replaceAll("<br>", "\n")));
		}

		contents.setTag("pages", pages);

		return contents;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack) {
		return false;
	}
}
