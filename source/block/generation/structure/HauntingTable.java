package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class HauntingTable extends Block {
	public HauntingTable(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.TablesTab);
		setHardness(5.0f);
		setResistance(5.5f);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!p.isSneaking() && p.inventory.consumeInventoryItem(Itemizer.Ghoulasm)) {
			w.playSoundAtEntity(p, "nevermine:HauntTable", 1.85f, 1.0f);
			if (!w.isRemote) {
				p.dropItem(Itemizer.GhoulasmPrimed, 1);
			}
		}
		return true;
	}
}
