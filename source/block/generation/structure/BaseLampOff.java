package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;

public class BaseLampOff extends Block {
	private static Block.SoundType rck;

	public BaseLampOff(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5.0f);
		setResistance(0.5f);
		setSoundType(BaseLampOff.rck);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!p.isSneaking()) {
			if (w.getBlock(x, y, z) == Blockizer.DustopianLampOff && p.inventory.consumeInventoryItem(Itemizer.PrimordialDust) && !w.isRemote) {
				w.setBlock(x, y, z, Blockizer.LampDustopian);
			}
			if (w.getBlock(x, y, z) == Blockizer.AquaticLampOff && p.inventory.consumeInventoryItem(Itemizer.IngotMystite) && !w.isRemote) {
				w.setBlock(x, y, z, Blockizer.LampAquatic);
			}
		}
		return true;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}

	static {
		BaseLampOff.rck = Block.soundTypeStone;
	}
}
