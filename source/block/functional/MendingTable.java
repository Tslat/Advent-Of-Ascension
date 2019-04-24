package net.nevermine.block.functional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

public class MendingTable extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public MendingTable(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.FurnitureTab);
		setHardness(5.0f);
		setResistance(0.5f);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int p_149691_1_, final int p_149691_2_) {
		return (p_149691_1_ == 0) ? bottom : ((p_149691_1_ == 1) ? top : blockIcon);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!p.isSneaking() && p.getHeldItem() != null) {
			if (p.getHeldItem().getItem() == Itemizer.IngotRustedIron) {
				w.playSoundAtEntity(p, "nevermine:MendingRepair", 1.85f, 1.0f);

				p.inventory.consumeInventoryItem(Itemizer.IngotRustedIron);
				if (!w.isRemote && !p.inventory.addItemStackToInventory(new ItemStack(Items.iron_ingot))) {
					p.dropItem(Items.iron_ingot, 1);
				}
			}
			else if (p.getHeldItem().getItem() == Itemizer.BlindCat) {
				if (p.inventory.consumeInventoryItem(Itemizer.MagicMendingSolution) && p.inventory.consumeInventoryItem(Itemizer.BlindCat)) {
					w.playSoundAtEntity(p, "nevermine:MendingRepair", 1.85f, 1.0f);

					if (!w.isRemote && !p.inventory.addItemStackToInventory(new ItemStack(Weaponizer.BangBang))) {
						p.dropItem(Weaponizer.BangBang, 1);
					}
				}
			}
			else if (p.getHeldItem().getItemDamage() != 0 && p.getHeldItem().getItem().isDamageable()) {
				if (p.inventory.consumeInventoryItem(Itemizer.MagicMendingSolution)) {
					p.getHeldItem().setItemDamage(0);
					w.playSoundAtEntity(p, "nevermine:MendingRepair", 1.85f, 1.0f);

					if (!w.isRemote && !p.inventory.addItemStackToInventory(new ItemStack(Itemizer.MetalTub))) {
						p.dropItem(Itemizer.MetalTub, 1);
					}
				}
			}
		}

		if (p instanceof EntityPlayerMP) {
			((EntityPlayerMP)p).sendContainerToPlayer(p.inventoryContainer);
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:mendingTable_top");
		side = icon.registerIcon("nevermine:mendingTable_side");
		bottom = icon.registerIcon("nevermine:mendingTable_bottom");
		blockIcon = icon.registerIcon("nevermine:mendingTable_side");
	}
}
