package net.nevermine.block.generation.structure;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.block.functional.AdventPortalBlock;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;

public class CarvedRunicBlock extends BlockDirectional {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	private String name;

	public CarvedRunicBlock(final Material p_i45394_1_, final String strname) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5.0f);
		setResistance(0.5f);
		setLightLevel(0.0f);
		name = strname;
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int side, final float offsetX, final float offsetY, final float offsetZ) {
		if (p.isSneaking() || p.getHeldItem() == null || w.getBlock(x, y, z) != Blockizer.CarvedRune4 || !p.getHeldItem().getItem().getUnlocalizedName().contains("Realmstone"))
			return false;

		if (!checkPortal(w, side, x, y, z)) {
			if (w.isRemote)
				p.addChatMessage(StringUtil.getColourLocale("message.feedback.teleporterFrame.fail", EnumChatFormatting.RED));

			return true;
		}

		Item key = null;
		IChatComponent msg = null;
		String sound = null;
		Block portal = null;

		switch (p.getHeldItem().getItem().getUnlocalizedName()) {
			case "item.RealmstoneAbyss":
				key = Itemizer.RealmstoneAbyss;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.abyss", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:AbyssPortal";
				portal = Blockizer.abyssPortal;
				break;
			case "item.RealmstoneHaven":
				key = Itemizer.RealmstoneHaven;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.haven", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:LightPortal";
				portal = Blockizer.havenPortal;
				break;
			case "item.RealmstoneMysterium":
				key = Itemizer.RealmstoneMysterium;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.mysterium", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:NaturePortal";
				portal = Blockizer.mysteriumPortal;
				break;
			case "item.RealmstonePrecasia":
				key = Itemizer.RealmstonePrecasia;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.precasia", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:NaturePortal";
				portal = Blockizer.precasiaPortal;
				break;
			case "item.RealmstoneDeeplands":
				key = Itemizer.RealmstoneDeeplands;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.deeplands", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:EmptyWorldPortal";
				portal = Blockizer.deeplandsPortal;
				break;
			case "item.RealmstoneIromine":
				key = Itemizer.RealmstoneIromine;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.iromine", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:IroPortal";
				portal = Blockizer.irominePortal;
				break;
			case "item.RealmstoneGardencia":
				key = Itemizer.RealmstoneGardencia;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.gardencia", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:NaturePortal";
				portal = Blockizer.gardenciaPortal;
				break;
			case "item.RealmstoneBorean":
				key = Itemizer.RealmstoneBorean;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.lborean", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:NaturePortal";
				portal = Blockizer.boreanPortal;
				break;
			case "item.RealmstoneVoxPonds":
				key = Itemizer.RealmstoneVoxPonds;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.voxPonds", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:DarknessPortal";
				portal = Blockizer.voxpondsPortal;
				break;
			case "item.RealmstoneDustopia":
				key = Itemizer.RealmstoneDustopia;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.dustopia", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:DarknessPortal";
				portal = Blockizer.dustopiaPortal;
				break;
			case "item.RealmstoneLelyetia":
				key = Itemizer.RealmstoneLelyetia;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.lelyetia", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:NaturePortal";
				portal = Blockizer.lelyetiaPortal;
				break;
			case "item.RealmstoneAncientCavern":
				key = Itemizer.RealmstoneAncientCavern;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.ancientCavern", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:AncientCavernPortal";
				portal = Blockizer.ancientcavernPortal;
				break;
			case "item.RealmstoneBarathos":
				key = Itemizer.RealmstoneBarathos;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.barathos", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:EmptyWorldPortal";
				portal = Blockizer.barathosPortal;
				break;
			case "item.RealmstoneCeleve":
				key = Itemizer.RealmstoneCeleve;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.celeve", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:CelevePortal";
				portal = Blockizer.celevePortal;
				break;
			case "item.RealmstoneCrystevia":
				key = Itemizer.RealmstoneCrystevia;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.crystevia", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:CrysteviaPortal";
				portal = Blockizer.crysteviaPortal;
				break;
			case "item.RealmstoneCandyland":
				key = Itemizer.RealmstoneCandyland;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.candyland", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:CandylandPortal";
				portal = Blockizer.candylandPortal;
				break;
			case "item.RealmstoneCreeponia":
				key = Itemizer.RealmstoneCreeponia;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.creeponia", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:CreeponiaPortal";
				portal = Blockizer.creeponiaPortal;
				break;
			case "item.RealmstoneImmortallis":
				key = Itemizer.RealmstoneImmortallis;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.immortallis", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:ImmortallisPortal";
				portal = Blockizer.immortallisPortal;
				break;
			case "item.RealmstoneShyrelands":
				key = Itemizer.RealmstoneShyrelands;
				msg = StringUtil.getColourLocale("message.feedback.teleporterFrame.shyrelands", EnumChatFormatting.LIGHT_PURPLE);
				sound = "nevermine:ShyrelandsPortal";
				portal = Blockizer.shyrelandsPortal;
				break;
			default:
				break;
		}

		if (key == null)
			return true;

		Block existing = w.getBlock(x, y + 1, z);

		if (existing == portal) {
			if (w.isRemote)
				p.addChatMessage(StringUtil.getColourLocale("message.feedback.teleporterFrame.shyrelands", EnumChatFormatting.LIGHT_PURPLE));
			return true;
		}

		if (p.capabilities.isCreativeMode || p.inventory.consumeInventoryItem(key)) {
			if (w.isRemote)
				p.addChatMessage(msg);

			w.playSoundAtEntity(p, sound, 1.85f, 1.0f);
			setPortal(portal, w, side, x, y, z);
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int par1, final int par2) {
		final int var3 = par2 & 0xC;
		return (var3 == 0 && (par1 == 1 || par1 == 0)) ? top : ((var3 == 4 && (par1 == 5 || par1 == 4)) ? top : ((var3 == 8 && (par1 == 2 || par1 == 3)) ? top : side));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:ancientRock");
		bottom = top;
		side = icon.registerIcon("nevermine:animated/" + name);
		blockIcon = icon.registerIcon("nevermine:animated/" + name);
	}

	private void setPortal(Block portalBlock, World w, int side, int x, int y, int z) {
		switch (side) {
			case 0:
			case 1:
				setPortal(portalBlock, w, getPortalDirection(w, x, y, z), x, y, z);
				break;
			case 2:
			case 3:
				for (int i = x - 1; i <= x + 1; i++) {
					for (int j = y + 1; j <= y + 4; j++) {
						w.setBlock(i, j, z, portalBlock);
					}
				}
				break;
			case 4:
			case 5:
				for (int i = z - 1; i <= z + 1; i++) {
					for (int j = y + 1; j <= y + 4; j++) {
						w.setBlock(x, j, i, portalBlock);
					}
				}
				break;
			default:
				break;
		}
	}

	protected static int getPortalDirection(World world, int x, int y, int z) {
		Block bl = world.getBlock(x + 2, y, z);
		Block bl2 = world.getBlock(x - 2, y, z);

		if ((bl instanceof CarvedRunicBlockBase || bl == Blockizer.AncientRock) && (bl2 instanceof CarvedRunicBlockBase || bl2 == Blockizer.AncientRock)) {
			return 2;
		}
		else {
			bl = world.getBlock(x, y, z + 2);
			bl2 = world.getBlock(x, y, z - 2);

			if ((bl instanceof CarvedRunicBlockBase || bl == Blockizer.AncientRock) && (bl2 instanceof CarvedRunicBlockBase || bl2 == Blockizer.AncientRock)) {
				return 4;
			}
		}

		return 10;
	}

	protected static boolean checkPortal(World world, int side, int x, int y, int z) {
		int stoneCount = 0;
		boolean stone1 = false;
		boolean stone2 = false;
		boolean stone3 = false;
		boolean stone4 = false;

		boolean cycle = true;
		Block bl;

		switch (side) {
			case 0:
			case 1:
				return checkPortal(world, getPortalDirection(world, x, y, z), x, y, z);
			case 2:
			case 3:
				for (int i = x - 2; i <= x + 2; i++) {
					bl = cycle ? world.getBlock(i, y, z) : world.getBlock(i, y + 5, z);

					if (bl == Blockizer.AncientRock) {
						stoneCount++;
					}
					else if (bl instanceof CarvedRunicBlockBase) {
						if (bl == Blockizer.CarvedRune1) {
							stone1 = true;
						}
						else if (bl == Blockizer.CarvedRune2) {
							stone2 = true;
						}
						else if (bl == Blockizer.CarvedRune3) {
							stone3 = true;
						}
						else if (bl == Blockizer.CarvedRune6) {
							stone4 = true;
						}
					}
					else if (bl == Blockizer.CarvedRune4) {
						continue;
					}
					else {
						return false;
					}

					if (i == x + 2 && cycle) {
						i = x - 3;
						cycle = false;
					}
				}

				cycle = true;

				for (int i = y + 1; i <= y + 4; i++) {
					bl = cycle ? world.getBlock(x - 2, i, z) : world.getBlock(x + 2, i, z);

					if (bl == Blockizer.AncientRock) {
						stoneCount++;
					}
					else if (bl instanceof CarvedRunicBlockBase) {
						if (bl == Blockizer.CarvedRune1) {
							stone1 = true;
						}
						else if (bl == Blockizer.CarvedRune2) {
							stone2 = true;
						}
						else if (bl == Blockizer.CarvedRune3) {
							stone3 = true;
						}
						else if (bl == Blockizer.CarvedRune6) {
							stone4 = true;
						}
					}
					else if (bl == Blockizer.CarvedRune4) {
						continue;
					}
					else {
						return false;
					}

					if (i == y + 4 && cycle) {
						i = y;
						cycle = false;
					}
				}
				break;
			case 4:
			case 5:
				for (int i = z - 2; i <= z + 2; i++) {
					bl = cycle ? world.getBlock(x, y, i) : world.getBlock(x, y + 5, i);

					if (bl == Blockizer.AncientRock) {
						stoneCount++;
					}
					else if (bl instanceof CarvedRunicBlockBase) {
						if (bl == Blockizer.CarvedRune1) {
							stone1 = true;
						}
						else if (bl == Blockizer.CarvedRune2) {
							stone2 = true;
						}
						else if (bl == Blockizer.CarvedRune3) {
							stone3 = true;
						}
						else if (bl == Blockizer.CarvedRune6) {
							stone4 = true;
						}
					}
					else if (bl == Blockizer.CarvedRune4) {
						continue;
					}
					else {
						return false;
					}

					if (i == z + 2 && cycle) {
						i = z - 3;
						cycle = false;
					}
				}

				cycle = true;

				for (int i = y + 1; i <= y + 4; i++) {
					bl = cycle ? world.getBlock(x, i, z - 2) : world.getBlock(x, i, z + 2);

					if (bl == Blockizer.AncientRock) {
						stoneCount++;
					}
					else if (bl instanceof CarvedRunicBlockBase) {
						if (bl == Blockizer.CarvedRune1) {
							stone1 = true;
						}
						else if (bl == Blockizer.CarvedRune2) {
							stone2 = true;
						}
						else if (bl == Blockizer.CarvedRune3) {
							stone3 = true;
						}
						else if (bl == Blockizer.CarvedRune6) {
							stone4 = true;
						}
					}
					else if (bl == Blockizer.CarvedRune4) {
						continue;
					}
					else {
						return false;
					}

					if (i == y + 4 && cycle) {
						i = y;
						cycle = false;
					}
				}
				break;
			default:
				return false;
		}

		if (stoneCount == 13 && stone1 && stone2 && stone3 && stone4)
			return true;

		return false;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		if (world.getBlock(x, y + 1, z) instanceof AdventPortalBlock) {
			breakPortal(world, x, y, z);
		}
	}

	public static void breakPortal(World world, int baseX, int baseY, int baseZ) {
		for (int i = baseX - 1; i < baseX + 2; i++) {
			for (int j = baseY + 1; j < baseY + 5; j++) {
				for (int k = baseZ - 1; k < baseZ + 2; k++) {
					if (world.getBlock(i, j, k).getMaterial() == Material.portal)
						world.setBlockToAir(i, j, k);
				}
			}
		}
	}
}
