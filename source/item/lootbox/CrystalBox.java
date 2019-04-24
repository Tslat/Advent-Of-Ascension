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

public class CrystalBox extends Item {
	public CrystalBox() {
		setUnlocalizedName("CrystalBox");
		setRegistryName("aoa3:crystal_box");
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
				ItemStack crystalStack;
				int choice = itemRand.nextInt(41);

				if (choice == 0) {
					crystalStack = new ItemStack(ItemRegister.skillCrystalGiant);
				}
				else if (choice < 4) {
					crystalStack = new ItemStack(ItemRegister.skillCrystalLarge);
				}
				else if (choice < 13) {
					crystalStack = new ItemStack(ItemRegister.skillCrystalMedium);
				}
				else {
					crystalStack = new ItemStack(ItemRegister.skillCrystalSmall);
				}

				if (!player.capabilities.isCreativeMode)
					player.getHeldItem(hand).shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, crystalStack);
			}
		}

		return EnumActionResult.PASS;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.CrystalBox.desc.1", TextFormatting.GOLD));
	}
}
