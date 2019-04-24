package net.nevermine.block.generation;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;
import net.nevermine.mob.entity.deeplands.EntityNipper;

import java.util.Random;

public class BlockDeeplandsTrap extends Block {
	private String name;

	public BlockDeeplandsTrap(final Material Mtr) {
		super(Mtr);
		setCreativeTab(Itemizer.GenerationTab);
		if (Mtr == Material.rock) {
			setHardness(1.3f);
		}
		else {
			setHarvestLevel("shovel", 1);
			setHardness(0.5f);
		}
		setResistance(0.1f);
	}

	public void onBlockClicked(final World w, final int x, final int y, final int z, final EntityPlayer p) {
		if (p.inventory.getCurrentItem() != null && p.inventory.getCurrentItem().getItem() instanceof ItemPickaxe) {
			if (name == "DeeplandsExplosionTrap") {
				w.createExplosion(p, (double)x, (double)y, (double)z, 1.0f, true);
				p.addPotionEffect(new PotionEffect(Potion.poison.id, 80, 2));
				w.setBlock(x, y, z, Blocks.air);
			}
			if (name == "DeeplandsLavaTrap" && !w.isRemote) {
				w.setBlock(x, y, z, Blocks.lava);
			}
			if (name == "DeeplandsNipperTrap" && !w.isRemote) {
				final EntityNipper var2 = new EntityNipper(w);
				var2.setLocationAndAngles(p.posX + 0.5, p.posY, p.posZ + 0.5, 0.0f, 0.0f);
				w.spawnEntityInWorld(var2);
			}
		}
	}

	public int quantityDropped(final Random p_149745_1_) {
		return 0;
	}

	public Block setName(final String name) {
		setBlockName(this.name = name);
		GameRegistry.registerBlock(this, name);
		return this;
	}
}
