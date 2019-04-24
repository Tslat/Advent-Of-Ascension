package net.nevermine.block.functional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.nevermine.assist.ItemUtil;
import net.nevermine.assist.StringUtil;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.skill.extraction.extractionHelper;

import java.util.Random;

import static net.nevermine.container.PlayerContainer.Deities.Pluton;
import static net.nevermine.container.PlayerContainer.Skills.Extraction;

public class BlockExtractionDevice extends Block {
	private boolean active;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public BlockExtractionDevice(final boolean on) {
		super(Material.iron);
		setBlockName("extractionDevice");
		setCreativeTab(active ? null : Itemizer.TablesTab);
		setHardness(5.0f);
		setResistance(10.0f);
		active = on;
		GameRegistry.registerBlock(this, active ? "extractionDeviceOn" : "extractionDevice");
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = active ? icon.registerIcon("nevermine:animated/extractionDeviceON_top") : icon.registerIcon("nevermine:extractionDevice_top");
		side = active ? icon.registerIcon("nevermine:animated/extractionDeviceON_side") : icon.registerIcon("nevermine:extractionDevice_side");
		bottom = icon.registerIcon("nevermine:extractionDevice_bottom");
		blockIcon = active ? icon.registerIcon("nevermine:animated/extractionDeviceON_side") : icon.registerIcon("nevermine:extractionDevice_side");
	}

	public int getRenderBlockPass() {
		return 1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(final IBlockAccess blockAccess, final int x, final int y, final int z, final int side) {
		final Block i1 = blockAccess.getBlock(x, y, z);

		return i1 != this && super.shouldSideBeRendered(blockAccess, x, y, z, side);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int p_149691_1_, final int p_149691_2_) {
		return (p_149691_1_ == 0) ? bottom : ((p_149691_1_ == 1) ? top : blockIcon);
	}

	public void onNeighborBlockChange(final World w, final int x, final int y, final int z, final Block neighbor) {
		Block bl = w.getBlock(x, y + 1, z);

		if (!isLava(bl) || (isLava(bl) && w.getBlockMetadata(x, y + 1, z) != 0)) {
			w.setBlock(x, y, z, Blockizer.ExtractionDevice);
		}
		else if (isLava(bl) && w.getBlockMetadata(x, y + 1, z) == 0) {
			w.setBlock(x, y, z, Blockizer.ExtractionDeviceOn);
		}
	}

	private boolean isLava(final Block b) {
		return b == Blocks.lava || b == Blocks.flowing_lava;
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer player, final int side, final float subX, final float subY, final float subZ) {
		if (!active || w.isRemote)
			return false;

		if (player.getHeldItem() != null && (player.getHeldItem().getItem() == Itemizer.StoneBowl || player.getHeldItem().getItem() == Itemizer.DiamondBowl)) {
			Random r = new Random();

			for (int i = y - 1; i > 1; i--) {
				Block b = w.getBlock(x, i, z);

				if (b == Blocks.air) {
					PlayerContainer cont = PlayerContainer.getProperties(player);

					if (extractionHelper.isExtractionFail(cont.getLevel(Extraction))) {
						player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.none"));
						w.setBlock(x, i, z, Blocks.obsidian);
						w.setBlock(x, y + 1, z, Blocks.air);
						return true;
					}
					else {
						int loot = extractionHelper.getLootPick(cont.getLevel(Extraction));

						switch (loot) {
							case 0:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.fail"));
								break;
							case 1:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.coal"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Items.coal, 4)))
									player.dropItem(Items.coal, 4);
								break;
							case 2:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.flint"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Items.flint, 4)))
									player.dropItem(Items.flint, 4);
								break;
							case 3:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.arrows"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Itemizer.ElementalArrow, 8)))
									player.dropItem(Itemizer.ElementalArrow, 8);
								break;
							case 4:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.blazePowder"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Items.blaze_powder, 4)))
									player.dropItem(Items.blaze_powder, 4);
								break;
							case 5:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.limonite"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Itemizer.IngotLimonite, 4)))
									player.dropItem(Itemizer.IngotLimonite, 4);
								break;
							case 6:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.bones"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Items.bone, 8)))
									player.dropItem(Items.bone, 8);
								break;
							case 7:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.coinsSilver"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Itemizer.SilverCoin, 2)))
									player.dropItem(Itemizer.SilverCoin, 2);
								break;
							case 8:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.emerald"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Items.emerald, 1)))
									player.dropItem(Items.emerald, 1);
								break;
							case 9:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.experienceBottles"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Items.experience_bottle, 4)))
									player.dropItem(Items.experience_bottle, 4);
								break;
							case 10:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.rosite"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Itemizer.IngotRosite, 1)))
									player.dropItem(Itemizer.IngotRosite, 1);
								break;
							case 11:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.blazeRods"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Items.blaze_rod, 4)))
									player.dropItem(Items.blaze_rod, 4);
								break;
							case 12:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.bullets"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Itemizer.MetalPellet, 32)))
									player.dropItem(Itemizer.MetalPellet, 32);
								break;
							case 13:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.dye"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Items.dye, 32, r.nextInt(16))))
									player.entityDropItem(new ItemStack(Items.dye, 32, r.nextInt(16)), 1.0f);
								break;
							case 14:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.animaStones"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Itemizer.FragmentedAnimaStone, 4)))
									player.dropItem(Itemizer.FragmentedAnimaStone, 4);
								break;
							case 15:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.runes"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(ItemUtil.getRandomRune(), 64)))
									player.dropItem(ItemUtil.getRandomRune(), 64);
								break;
							case 16:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.grenades"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Weaponizer.Grenade, 16)))
									player.dropItem(Weaponizer.Grenade, 16);
								break;
							case 17:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.diamond"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Items.diamond, 1)))
									player.dropItem(Items.diamond, 1);
								break;
							case 18:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.gemBags"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Itemizer.GemBag, 2)))
									player.dropItem(Itemizer.GemBag, 2);
								break;
							case 19:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.shinyBox"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Itemizer.ShinyBox, 1)))
									player.dropItem(Itemizer.ShinyBox, 1);
								break;
							case 20:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.coinGold"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Itemizer.GoldCoin, 1)))
									player.dropItem(Itemizer.GoldCoin, 1);
								break;
							case 21:
								player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.repairDust"));

								if (!player.inventory.addItemStackToInventory(new ItemStack(Itemizer.MagicRepairDust, 1)))
									player.dropItem(Itemizer.MagicRepairDust, 1);
								break;
							default:
								break;
						}

						if (player.dimension == 0)
							cont.adjustTribute(Pluton, 2);

						cont.addExperience(cont.getExpRequired(Extraction) / extractionHelper.getExpDenominator(cont.getLevel(Extraction)), Extraction);
						w.setBlock(x, i, z, Blocks.obsidian);
						w.setBlock(x, y + 1, z, Blocks.air);
						w.playSoundAtEntity(player, "nevermine:FiltrationLava", 5.85f, 1.0f);
					}

					player.getHeldItem().damageItem(1, player);

					if (player instanceof EntityPlayerMP)
						((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);

					break;
				}
				else if (b == Blocks.obsidian) {
					continue;
				}
				else {
					player.addChatMessage(StringUtil.getLocale("message.feedback.extraction.noSpace"));
					break;
				}
			}

			return true;
		}

		return false;
	}
}
