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
import net.tslat.aoa3.common.registration.WeaponRegister;
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

public class WeaponsCase extends Item {
	public WeaponsCase() {
		setUnlocalizedName("WeaponsCase");
		setRegistryName("aoa3:weapons_case");
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
				ItemStack loot = ItemStack.EMPTY;

				switch (itemRand.nextInt(12)) {
					case 0:
						loot = new ItemStack(WeaponRegister.throwableGooBall, 5 + itemRand.nextInt(11));
						break;
					case 1:
						loot = new ItemStack(WeaponRegister.throwableVulkram, 5 + itemRand.nextInt(11));
						break;
					case 2:
						loot = new ItemStack(WeaponRegister.throwableHellfire, 5 + itemRand.nextInt(11));
						break;
					case 3:
						loot = new ItemStack(WeaponRegister.throwableChakram, 5 + itemRand.nextInt(11));
						break;
					case 4:
						loot = new ItemStack(WeaponRegister.throwableSliceStar, 5 + itemRand.nextInt(11));
						break;
					case 5:
						loot = new ItemStack(WeaponRegister.throwableGrenade, 5 + itemRand.nextInt(11));
						break;
					case 6:
						loot = new ItemStack(ItemRegister.cannonball, 8 + itemRand.nextInt(8) * 4);
						break;
					case 7:
						loot = new ItemStack(ItemRegister.bulletLimonite, 16 + itemRand.nextInt(10) * 3);
						break;
					case 8:
						loot = new ItemStack(ItemRegister.metalSlug, 8 + itemRand.nextInt(8) * 4);
						break;
					case 9:
						loot = new ItemStack(ItemRegister.spreadshot, 5 + itemRand.nextInt(11));
						break;
					case 10:
						loot = new ItemStack(ItemRegister.popShot, 8 + itemRand.nextInt(8) * 4);
						break;
					case 11:
						loot = new ItemStack(ItemRegister.hollyArrow, 8 + itemRand.nextInt(8) * 4);
						break;
				}

				if (!player.capabilities.isCreativeMode)
					player.getHeldItem(hand).shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, loot);
			}
		}

		return EnumActionResult.PASS;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.WeaponsCase.desc.1", TextFormatting.GOLD));
	}
}
