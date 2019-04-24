package net.nevermine.block.generation;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Armorizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.Random;

public class DeepCase extends Block {
	private static Block.SoundType rck;
	private Random rand;

	public DeepCase(final Material p_i45394_1_) {
		super(p_i45394_1_);
		rand = new Random();
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5.0f);
		setResistance(0.5f);
		setSoundType(DeepCase.rck);
	}

	public void onBlockClicked(final World w, final int x, final int y, final int z, final EntityPlayer p) {
		if (p.inventory.getCurrentItem() != null && p.inventory.getCurrentItem().getItem() instanceof ItemPickaxe && !w.isRemote) {
			final int item = rand.nextInt(20);
			if (item == 1) {
				p.dropItem(Itemizer.CoinsDeeplands, 50);
			}
			if (item == 2) {
				p.dropItem(Weaponizer.DeepBow, 1);
			}
			if (item == 3) {
				p.dropItem(Armorizer.SubterraneanBoots, 1);
			}
			if (item == 4) {
				p.dropItem(Armorizer.SubterraneanHelmet, 1);
			}
			if (item == 5) {
				p.dropItem(Armorizer.SubterraneanLeggings, 1);
			}
			if (item == 6) {
				p.dropItem(Armorizer.SubterraneanChestplate, 1);
			}
			if (item == 7) {
				p.dropItem(Itemizer.CoinsDeeplands, 10 + rand.nextInt(11));
			}
			if (item == 0) {
				p.dropItem(Itemizer.GoldCoin, 2);
			}
			if (item > 7) {
				p.dropItem(Itemizer.CopperCoin, 2 + rand.nextInt(25));
			}
			w.setBlock(x, y, z, Blocks.air);
		}
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}

	static {
		DeepCase.rck = Block.soundTypeStone;
	}
}
