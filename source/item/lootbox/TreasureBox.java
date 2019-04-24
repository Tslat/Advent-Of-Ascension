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
import net.tslat.aoa3.utils.skills.HaulingUtil;

import javax.annotation.Nullable;
import java.util.List;

public class TreasureBox extends Item {
	public TreasureBox() {
		setUnlocalizedName("TreasureBox");
		setRegistryName("aoa3:treasure_box");
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
				for (int i = 0; i < HaulingUtil.getTreasureBoxRolls(cap.getLevel(Enums.Skills.HAULING)); i++) {
					ItemStack loot = ItemStack.EMPTY;

					switch (itemRand.nextInt(30)) {
						case 0:
						case 1:
						case 2:
						case 3:
						case 4:
							loot = new ItemStack(ItemRegister.coinCopper, 5);
							break;
						case 5:
						case 6:
						case 7:
							loot = new ItemStack(ItemRegister.coinSilver, 1);
							break;
						case 8:
						case 9:
						case 10:
							loot = new ItemStack(ItemRegister.ingotLimonite, 4);
							break;
						case 11:
						case 12:
						case 13:
							loot = new ItemStack(ItemRegister.tea, 4);
							break;
						case 14:
						case 15:
						case 16:
						case 17:
							loot = new ItemStack(ItemRegister.hollyTopPetals, 16);
							break;
						case 18:
						case 19:
						case 20:
						case 21:
							loot = new ItemStack(Items.EXPERIENCE_BOTTLE, 3);
							break;
						case 22:
						case 23:
						case 24:
							loot = new ItemStack(ItemRegister.realmstoneBorean, 1);
							break;
						case 25:
							loot = new ItemStack(ItemRegister.fragmentedAnimaStone, 4);
							break;
						case 26:
							loot = new ItemStack(ItemRegister.magicRepairDust, 1);
							break;
						case 27:
							loot = new ItemStack(ItemRegister.ingotRosite, 1);
							break;
						case 28:
							loot = new ItemStack(ItemRegister.coinGold, 1);
							break;
						case 29:
							loot = new ItemStack(ItemRegister.infusionStoneAmbient, 3);
							break;
					}

					ItemUtil.givePlayerItemOrDrop(player, loot);
				}

				if (!player.capabilities.isCreativeMode)
					player.getHeldItem(hand).shrink(1);
				
				player.inventoryContainer.detectAndSendChanges();
			}
		}

		return EnumActionResult.PASS;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.TreasureBox.desc.1", TextFormatting.GOLD));
	}
}
