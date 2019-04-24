package net.nevermine.item.functional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.entity.overworld.*;
import net.nevermine.skill.hauling.haulingHelper;

import java.util.List;
import java.util.Random;

import static net.nevermine.container.PlayerContainer.Skills.Hauling;

public class TreasureBox extends Item {
	Random rand = new Random();

	public TreasureBox() {
		setCreativeTab(Itemizer.TotemsTab);
	}

	public boolean onItemUse(final ItemStack stack, final EntityPlayer player, final World world, final int par4, final int par5, final int par6, final int par7, final float par8, final float par9, final float par10) {
		if (world.isRemote)
			return true;

		PlayerContainer cont = PlayerContainer.getProperties(player);

		if (rand.nextInt(10) == 0) {
			Entity entity = null;
			switch (rand.nextInt(5)) {
				case 0:
					entity = new EntitySeaTroll(world);
					break;
				case 1:
					entity = new EntitySpinux(world);
					break;
				case 2:
					entity = new EntityAmphibiyte(world);
					break;
				case 3:
					entity = new EntitySeaSkeleton(world);
					break;
				case 4:
					entity = new EntitySeaSpider(world);
					break;
				default:
					break;
			}

			entity.setLocationAndAngles(player.posX, player.posY, player.posZ, rand.nextFloat() * 360.0f, 0.0f);
			world.spawnEntityInWorld(entity);
		}
		else {
			ItemStack loot = null;

			if (rand.nextInt(100) == 0) {
				loot = new ItemStack(Itemizer.OldBoot);
			}
			else {
				for (int i = 0; i < haulingHelper.getTreasureBoxRolls(cont.getLevel(Hauling)); i++) {
					switch (rand.nextInt(30)) {
						case 0:
						case 1:
						case 2:
						case 3:
						case 4:
							loot = new ItemStack(Itemizer.CopperCoin, 5);
							break;
						case 5:
						case 6:
						case 7:
							loot = new ItemStack(Itemizer.SilverCoin, 1);
							break;
						case 8:
						case 9:
						case 10:
							loot = new ItemStack(Itemizer.IngotLimonite, 4);
							break;
						case 11:
						case 12:
						case 13:
							loot = new ItemStack(Itemizer.Tea, 4);
							break;
						case 14:
						case 15:
						case 16:
						case 17:
							loot = new ItemStack(Itemizer.HollyTopPedals, 16);
							break;
						case 18:
						case 19:
						case 20:
						case 21:
							loot = new ItemStack(Items.experience_bottle, 3);
							break;
						case 22:
						case 23:
						case 24:
							loot = new ItemStack(Itemizer.RealmstoneBorean, 1);
							break;
						case 25:
							loot = new ItemStack(Itemizer.FragmentedAnimaStone, 4);
							break;
						case 26:
							loot = new ItemStack(Itemizer.MagicRepairDust, 1);
							break;
						case 27:
							loot = new ItemStack(Itemizer.IngotRosite, 1);
							break;
						case 28:
							loot = new ItemStack(Itemizer.GoldCoin, 1);
							break;
						case 29:
							loot = new ItemStack(Itemizer.iStoneAmbient, 3);
							break;
						default:
							break;
					}
				}
			}

			if (!player.inventory.addItemStackToInventory(loot))
				player.entityDropItem(loot, 0f);
		}

		cont.addExperience((cont.getExpRequired(Hauling) / haulingHelper.getExpDenominator(cont.getLevel(Hauling))) * 3, Hauling);
		--stack.stackSize;

		if (player instanceof EntityPlayerMP)
			((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);

		return true;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.TreasureBox.desc.1", EnumChatFormatting.GOLD));
	}
}
