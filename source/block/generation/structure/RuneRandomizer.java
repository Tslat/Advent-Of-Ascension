package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.assist.ItemUtil;
import net.nevermine.izer.Itemizer;

import java.util.Random;

public class RuneRandomizer extends Block {
	private static Block.SoundType rck = Block.soundTypeStone;
	private Random rand;

	public RuneRandomizer(final Material p_i45394_1_) {
		super(p_i45394_1_);
		rand = new Random();
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5.0f);
		setResistance(0.5f);
		setSoundType(RuneRandomizer.rck);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!w.isRemote && !p.isSneaking() && p.inventory.consumeInventoryItem(Itemizer.Rune)) {
			w.playSoundAtEntity(p, "nevermine:RuneRandomizer", 1.85f, 1.0f);
			p.dropItem(ItemUtil.getRandomRune(), 2);
		}
		return true;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}
}
