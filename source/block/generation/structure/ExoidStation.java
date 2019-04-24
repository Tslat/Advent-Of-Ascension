package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.entity.voxponds.EntityExoid;

public class ExoidStation extends Block {
	private static Block.SoundType rck;

	public ExoidStation(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(-1.0f);
		setResistance(999999999f);
		setSoundType(ExoidStation.rck);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!p.isSneaking() && p.inventory.consumeInventoryItem(Itemizer.ApocoStone) && !w.isRemote) {
			final EntityExoid var10 = new EntityExoid(w, 1);
			var10.setLocationAndAngles((double)x, (double)(y + 3), (double)z, 0.0f, 0.0f);
			w.spawnEntityInWorld(var10);
		}
		return true;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}

	static {
		ExoidStation.rck = Block.soundTypeStone;
	}
}
