package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

public class PedalCraftingStation extends Block {
	private static Block.SoundType rck;

	public PedalCraftingStation(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.TablesTab);
		setHardness(5.0f);
		setResistance(0.5f);
		setSoundType(PedalCraftingStation.rck);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!p.isSneaking() && p.inventory.hasItem(Itemizer.SmallPedalBlue) && p.inventory.hasItem(Itemizer.SmallPedalRed) && p.inventory.hasItem(Itemizer.SmallPedalGreen) && p.inventory.hasItem(Itemizer.SmallPedalOrange) && p.inventory.hasItem(Itemizer.SmallPedalPurple) && p.inventory.consumeInventoryItem(Itemizer.SmallPedalBlue) && p.inventory.consumeInventoryItem(Itemizer.SmallPedalRed) && p.inventory.consumeInventoryItem(Itemizer.SmallPedalGreen) && p.inventory.consumeInventoryItem(Itemizer.SmallPedalOrange) && p.inventory.consumeInventoryItem(Itemizer.SmallPedalPurple)) {
			w.playSoundAtEntity(p, "nevermine:PedalCrafting", 1.85f, 1.0f);
			if (!w.isRemote) {
				p.dropItem(Itemizer.PedalPile, 1);
			}
		}
		return true;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}

	static {
		PedalCraftingStation.rck = Block.soundTypeStone;
	}
}
