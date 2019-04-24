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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

public class WornBook extends ItemWrittenBook {
	static NBTTagCompound contents = new NBTTagCompound();

	public WornBook() {
		super();

		this.setRegistryName("aoa3:worn_book");
		this.setUnlocalizedName("WornBook");
		setCreativeTab(CreativeTabsRegister.miscTab);
	}

	@SideOnly(Side.CLIENT)
	public static ItemStack getBook() {
		ItemStack stack = new ItemStack(ItemRegister.wornBook, 1);
		stack.setTagCompound(getBookContents());
		return stack;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack bookStack = player.getHeldItem(hand);

		if (world.isRemote)
			player.openGui(AdventOfAscension.instance, Enums.ModGuis.WORN_BOOK.guiId, world, (int)player.posX, (int)player.posY, (int)player.posZ);

		return ActionResult.newResult(EnumActionResult.SUCCESS, bookStack);
	}

	@SideOnly(Side.CLIENT)
	public static NBTTagCompound getBookContents() {
		contents.setString("author", StringUtil.getLocaleString("entity.aoa3.corrupted_traveller.name"));
		contents.setString("title", StringUtil.getLocaleString("item.WornBook.name"));
		NBTTagList pages = new NBTTagList();

		int i = 1;

		do {
			String st = StringUtil.getLocaleString("message.book.worn." + i);

			if (st.startsWith("message.book.worn.")) {
				i = 0;
			}
			else {
				pages.appendTag(new NBTTagString(st.replaceAll("<br>", "\n")));
				i++;
			}
		} while (i != 0);

		contents.setTag("pages", pages);

		return contents;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack) {
		return false;
	}
}
