package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.container.PlayerContainer;
import net.nevermine.izer.Itemizer;

import static net.nevermine.container.PlayerContainer.Skills.Augury;

public class CrystalExtensionShrine extends Block {
	public CrystalExtensionShrine(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.TablesTab);
		setHardness(5.0f);
		setResistance(5.5f);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!w.isRemote && p.getHeldItem() != null && (p.getHeldItem().getItem() == Itemizer.CrystalsGreen || p.getHeldItem().getItem() == Itemizer.CrystalsPurple || p.getHeldItem().getItem() == Itemizer.CrystalsYellow || p.getHeldItem().getItem() == Itemizer.CrystalsRed || p.getHeldItem().getItem() == Itemizer.CrystalsBlue || p.getHeldItem().getItem() == Itemizer.CrystalsWhite) && !p.isSneaking()) {
			final int lvl = PlayerContainer.getProperties(p).getLevel(Augury);
			if (lvl >= 84) {
				p.dropItem(Itemizer.EssenceDivine, 8);
			}
			else if (lvl >= 66) {
				p.dropItem(Itemizer.EssenceDark, 8);
			}
			else if (lvl >= 36) {
				p.dropItem(Itemizer.EssenceEmpowered, 8);
			}

			p.inventory.consumeInventoryItem(p.getHeldItem().getItem());
			w.playSoundAtEntity(p, "nevermine:AuguryTransfer", 1.85f, 1.0f);
		}
		return true;
	}
}
