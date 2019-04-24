package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

import java.util.Random;

public class CrystalCreatorGreen extends Block {
	private Random rand;

	public CrystalCreatorGreen(final Material p_i45394_1_) {
		super(p_i45394_1_);
		rand = new Random();
		setCreativeTab(Itemizer.TablesTab);
		setHardness(5.0f);
		setResistance(5.5f);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!w.isRemote && p.getHeldItem() != null && p.getHeldItem().getItem() == Itemizer.GemstonesGreen) {
			p.inventory.consumeInventoryItem(Itemizer.GemstonesGreen);
			if (!w.isRemote && rand.nextInt(8) == 2) {
				p.dropItem(Itemizer.CrystalsGreen, 1);
				w.playSoundAtEntity(p, "nevermine:CrystalMake", 1.85f, 1.0f);
			}
		}
		if (p instanceof EntityPlayerMP) {
			((EntityPlayerMP)p).sendContainerToPlayer(p.inventoryContainer);
		}
		return true;
	}
}
