package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

public class StrangeBlock extends Block {
	private static SoundType rck = Block.soundTypeStone;

	public StrangeBlock(Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(-1.0F);
		setResistance(999999999F);
		setSoundType(rck);
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int var6, float var7, float var8, float var9) {
		if ((!p.isSneaking()) && (p.getHeldItem() != null)) {
			if (p.getHeldItem().getItem() == Itemizer.StrangeStone1) {
				if ((p.inventory.consumeInventoryItem(Itemizer.StrangeStone1)) && (!w.isRemote)) {
					p.dropItem(Weaponizer.ShyreBlaster, 1);
				}
			}
			else if (p.getHeldItem().getItem() == Itemizer.StrangeStone2) {
				if ((p.inventory.consumeInventoryItem(Itemizer.StrangeStone2)) && (!w.isRemote)) {
					p.dropItem(Weaponizer.Amplifier, 1);
				}
			}
			else if ((p.getHeldItem().getItem() == Itemizer.StrangeStone3) && (p.inventory.consumeInventoryItem(Itemizer.StrangeStone3)) && (!w.isRemote)) {
				p.dropItem(Weaponizer.Sublimus, 1);
			}
		}

		return true;
	}

	public Block setSoundType(SoundType name) {
		return setStepSound(name);
	}
}
