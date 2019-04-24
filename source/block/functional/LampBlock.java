package net.nevermine.block.functional;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;

import java.util.Random;

public class LampBlock extends Block {
	private String name;
	private static Material Lmp;

	public LampBlock() {
		super(LampBlock.Lmp);
		setCreativeTab(Itemizer.LightingTab);
		setHardness(10.0f);
		setResistance(0.5f);
		setLightLevel(1.0f);
		setTickRandomly(true);
	}

	public Item getItem(final World par1World, final int par2, final int par3, final int par4) {
		return Item.getItemFromBlock(this);
	}

	public Item getBlock(final Block b) {
		return Item.getItemFromBlock(b);
	}

	public Item getItemDropped(final int par1, final Random par2, final int par3) {
		if (name == "dustopianLampOn") {
			return getBlock(Blockizer.DustopianLampOff);
		}
		if (name == "lampAquatic") {
			return getBlock(Blockizer.AquaticLampOff);
		}
		return getBlock(this);
	}

	public Block setTextureName(final String name) {
		return setBlockTextureName("nevermine:" + name);
	}

	public Block setName(final String name) {
		setBlockName(this.name = name);
		setTextureName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}

	static {
		LampBlock.Lmp = Material.rock;
	}
}
