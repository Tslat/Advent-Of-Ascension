package net.nevermine.block.generation;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

import java.util.Random;

public class OreBlock extends Block {
	private String name;
	private static Material OreBlk;

	public OreBlock() {
		super(OreBlock.OreBlk);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5.0f);
		setHarvestLevel("pickaxe", 3);
	}

	public Item getItemDropped(final int par1, final Random par2, final int par3) {
		if (name == "oreCrystallite") {
			return Itemizer.CrystalliteStone;
		}
		if (name == "oreFootFragments") {
			return Itemizer.FootboneFragment;
		}
		if (name == "oreBloodstone") {
			return Itemizer.Bloodstone;
		}
		if (name == "oreChestFragments") {
			return Itemizer.ChestboneFragment;
		}
		if (name == "oreSkullFragments") {
			return Itemizer.SkullboneFragment;
		}
		if (name == "oreLegFragments") {
			return Itemizer.LegboneFragment;
		}
		if (name == "oreCrystalPurple") {
			return Itemizer.GemstonesPurple;
		}
		if (name == "oreCrystalGreen") {
			return Itemizer.GemstonesGreen;
		}
		if (name == "oreCrystalYellow") {
			return Itemizer.GemstonesYellow;
		}
		if (name == "oreCrystalRed") {
			return Itemizer.GemstonesRed;
		}
		if (name == "oreCrystalBlue") {
			return Itemizer.GemstonesBlue;
		}
		if (name == "oreCrystalWhite") {
			return Itemizer.GemstonesWhite;
		}
		return getBlock(this);
	}

	public Item getBlock(final Block b) {
		return Item.getItemFromBlock(b);
	}

	public Item getItem(final World par1World, final int par2, final int par3, final int par4) {
		return Item.getItemFromBlock(this);
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
		OreBlock.OreBlk = Material.rock;
	}
}
