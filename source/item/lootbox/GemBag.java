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

public class GemBag extends Item {
	public GemBag() {
		setUnlocalizedName("GemBag");
		setRegistryName("aoa3:gem_bag");
		setCreativeTab(CreativeTabsRegister.miscTab);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			for (int i = 0; i < 3; i++) {
				ItemStack gemStack = ItemStack.EMPTY;

				switch (itemRand.nextInt(22)) {
					case 0:
						gemStack = new ItemStack(ItemRegister.tokensAbyss);
						break;
					case 1:
						gemStack = new ItemStack(ItemRegister.tokensBaron);
						break;
					case 2:
						gemStack = new ItemStack(ItemRegister.tokensBorean);
						break;
					case 3:
						gemStack = new ItemStack(ItemRegister.tokensCandyland);
						break;
					case 4:
						gemStack = new ItemStack(ItemRegister.tokensCeleve);
						break;
					case 5:
						gemStack = new ItemStack(ItemRegister.tokensCreeponia);
						break;
					case 6:
						gemStack = new ItemStack(ItemRegister.tokensCrystevia);
						break;
					case 7:
						gemStack = new ItemStack(ItemRegister.tokensDeeplands);
						break;
					case 8:
						gemStack = new ItemStack(ItemRegister.tokensDustopia);
						break;
					case 9:
						gemStack = new ItemStack(ItemRegister.tokensGardencia);
						break;
					case 10:
						gemStack = new ItemStack(ItemRegister.tokensGreckon);
						break;
					case 11:
						gemStack = new ItemStack(ItemRegister.tokensHaven);
						break;
					case 12:
						gemStack = new ItemStack(ItemRegister.tokensIromine);
						break;
					case 13:
						gemStack = new ItemStack(ItemRegister.tokensLelyetia);
						break;
					case 14:
						gemStack = new ItemStack(ItemRegister.tokensLunar);
						break;
					case 15:
						gemStack = new ItemStack(ItemRegister.tokensMysterium);
						break;
					case 16:
						gemStack = new ItemStack(ItemRegister.tokensNether);
						break;
					case 17:
						gemStack = new ItemStack(ItemRegister.tokensPrecasian);
						break;
					case 18:
						gemStack = new ItemStack(ItemRegister.tokensRunandor);
						break;
					case 19:
						gemStack = new ItemStack(ItemRegister.tokensShyrelands);
						break;
					case 20:
						gemStack = new ItemStack(ItemRegister.tokensVoxPonds);
						break;
				}

				ItemUtil.givePlayerItemOrDrop(player, gemStack);
			}

			if (!player.capabilities.isCreativeMode)
				player.getHeldItem(hand).shrink(1);

			player.inventoryContainer.detectAndSendChanges();
		}

		return EnumActionResult.PASS;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.GemBag.desc.1", TextFormatting.GOLD));
	}
}
