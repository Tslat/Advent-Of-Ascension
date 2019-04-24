package net.tslat.aoa3.item.lootbox;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ShinyBox extends Item {
	public ShinyBox() {
		setUnlocalizedName("ShinyBox");
		setRegistryName("aoa3:shiny_box");
		setCreativeTab(CreativeTabsRegister.miscTab);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			for (int i = 0; i < itemRand.nextInt(3) + 1; i++) {
				ItemStack ingotStack = ItemStack.EMPTY;

				if (itemRand.nextInt(50) == 0) {
					switch (itemRand.nextInt(13)) {
						case 0:
							ingotStack = new ItemStack(ItemRegister.ingotShyrestone);
							break;
						case 1:
							ingotStack = new ItemStack(ItemRegister.shyregem);
							break;
						case 2:
							ingotStack = new ItemStack(ItemRegister.ingotVarsium);
							break;
						case 3:
							ingotStack = new ItemStack(ItemRegister.ingotMystite);
							break;
						case 4:
							ingotStack = new ItemStack(ItemRegister.ingotLyon);
							break;
						case 5:
							ingotStack = new ItemStack(ItemRegister.ingotGhoulish);
							break;
						case 6:
							ingotStack = new ItemStack(ItemRegister.ingotGhastly);
							break;
						case 7:
							ingotStack = new ItemStack(ItemRegister.ingotEmberstone);
							break;
						case 8:
							ingotStack = new ItemStack(ItemRegister.ingotElecanium);
							break;
						case 9:
							ingotStack = new ItemStack(ItemRegister.ingotBlazium);
							break;
						case 10:
							ingotStack = new ItemStack(ItemRegister.ingotBaronyte);
							break;
						case 11:
							ingotStack = new ItemStack(ItemRegister.ingotSkeletal);
							break;
						case 12:
							ingotStack = new ItemStack(ItemRegister.ingotLunar);
							break;
					}
				}
				else {
					switch (itemRand.nextInt(6)) {
						case 0:
							ingotStack = new ItemStack(ItemRegister.amethyst);
							break;
						case 1:
							ingotStack = new ItemStack(ItemRegister.jade);
							break;
						case 2:
							ingotStack = new ItemStack(ItemRegister.ingotLimonite);
							break;
						case 3:
							ingotStack = new ItemStack(ItemRegister.sapphire);
							break;
						case 4:
							ingotStack = new ItemStack(ItemRegister.ingotRosite);
							break;
						case 5:
							ingotStack = new ItemStack(ItemRegister.ingotRustedIron);
							break;
					}
				}

				ItemUtil.givePlayerItemOrDrop(player, ingotStack);
			}

			if (!player.capabilities.isCreativeMode)
				player.getHeldItem(hand).shrink(1);

			player.inventoryContainer.detectAndSendChanges();
		}

		return EnumActionResult.PASS;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.ShinyBox.desc.1", TextFormatting.GOLD));
	}
}
