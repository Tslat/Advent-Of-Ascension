package net.tslat.aoa3.item.lootbox;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.mobs.misc.EntitySeaSkeleton;
import net.tslat.aoa3.entity.mobs.misc.EntitySeaSpider;
import net.tslat.aoa3.entity.mobs.overworld.EntityAmphibiyte;
import net.tslat.aoa3.entity.mobs.overworld.EntitySeaTroll;
import net.tslat.aoa3.entity.mobs.overworld.EntitySpinux;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class FishCase extends Item {
	public FishCase() {
		setUnlocalizedName("FishCase");
		setRegistryName("aoa3:fish_case");
		setCreativeTab(CreativeTabsRegister.miscTab);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(player);

			if (itemRand.nextInt(7 + (8 / (100 / cap.getLevel(Enums.Skills.HAULING)))) == 0) {
				switch (itemRand.nextInt(5)) {
					case 0:
						world.spawnEntity(new EntitySeaTroll(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5));
						break;
					case 1:
						world.spawnEntity(new EntitySpinux(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5));
						break;
					case 2:
						world.spawnEntity(new EntityAmphibiyte(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5));
						break;
					case 3:
						world.spawnEntity(new EntitySeaSkeleton(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5));
						break;
					case 4:
						world.spawnEntity(new EntitySeaSpider(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5));
						break;
				}
			}
			else {
				ItemStack fishStack = ItemStack.EMPTY;

				switch (itemRand.nextInt(17)) {
					case 0:
						fishStack = new ItemStack(Items.FISH);
						break;
					case 1:
						fishStack = new ItemStack(ItemRegister.fingerfish);
						break;
					case 2:
						fishStack = new ItemStack(ItemRegister.pearlStripefish);
						break;
					case 3:
						fishStack = new ItemStack(ItemRegister.limefish);
						break;
					case 4:
						fishStack = new ItemStack(ItemRegister.sailback);
						break;
					case 5:
						fishStack = new ItemStack(ItemRegister.goldenGullfish);
						break;
					case 6:
						fishStack = new ItemStack(ItemRegister.turquoiseStripefish);
						break;
					case 7:
						fishStack = new ItemStack(ItemRegister.violetSkipper);
						break;
					case 8:
						fishStack = new ItemStack(ItemRegister.rocketfish);
						break;
					case 9:
						fishStack = new ItemStack(ItemRegister.crimsonStripefish);
						break;
					case 10:
						fishStack = new ItemStack(ItemRegister.crimsonSkipper);
						break;
					case 11:
						fishStack = new ItemStack(ItemRegister.sapphireStrider);
						break;
					case 12:
						fishStack = new ItemStack(ItemRegister.candlefish);
						break;
					case 13:
						fishStack = new ItemStack(ItemRegister.darkHatchetfish);
						break;
					case 14:
						fishStack = new ItemStack(ItemRegister.ironback);
						break;
					case 15:
						fishStack = new ItemStack(ItemRegister.rainbowfish);
						break;
					case 16:
						fishStack = new ItemStack(ItemRegister.razorfish);
						break;
				}

				fishStack.setCount(1 + itemRand.nextInt(8));

				if (!player.capabilities.isCreativeMode)
					player.getHeldItem(hand).shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, fishStack);
			}
		}

		return EnumActionResult.PASS;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.FishCase.desc.1", TextFormatting.GOLD));
	}
}
