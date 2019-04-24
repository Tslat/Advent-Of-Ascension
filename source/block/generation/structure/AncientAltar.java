package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

import java.util.Random;

public class AncientAltar extends Block {
	private static SoundType rck = Block.soundTypeStone;
	private Random rand = new Random();

	public AncientAltar(net.minecraft.block.material.Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(-1.0F);
		setResistance(-1.0F);
		setSoundType(rck);
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int var6, float var7, float var8, float var9) {
		if ((!p.isSneaking()) && (p.getHeldItem() != null)) {
			if (p.getHeldItem().getItem() == Itemizer.IngotShyrestone) {
				if ((p.inventory.consumeInventoryItem(Itemizer.IngotShyrestone)) && (!w.isRemote) && (this.rand.nextInt(8) == 2)) {
					p.dropItem(Itemizer.AncientRing, 1);
				}

			}
			else if ((p.getHeldItem().getItem() == Itemizer.IngotShyregem) && (p.inventory.consumeInventoryItem(Itemizer.IngotShyregem)) && (!w.isRemote) && (this.rand.nextInt(4) == 2)) {
				p.dropItem(Itemizer.AncientRing, 1);
			}
		}

		return true;
	}

	public Block setSoundType(SoundType name) {
		return setStepSound(name);
	}
}
