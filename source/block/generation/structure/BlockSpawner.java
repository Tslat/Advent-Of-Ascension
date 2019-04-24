package net.nevermine.block.generation.structure;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

import java.util.Random;

public class BlockSpawner extends BlockContainer {
	private String name;
	public String mobName;
	private static Material spwnr;

	public BlockSpawner(final String spawnMob) {
		super(BlockSpawner.spwnr);
		switch ("nevermine." + spawnMob) {
			case "Visage":
			case "Urioh":
			case "Goldum":
			case "Skeledon":
			case "Skelekyte":
			case "Fenix":
			case "Goldus":
			case "Shavo":
			case "Urv":
			case "Ghastus":
				setHardness(-1.0f);
				setResistance(999999999f);
				break;
			default:
				setHardness(5.0f);
				setResistance(0.5f);
				break;
		}

		setBlockName(mobName = spawnMob);
		setCreativeTab(Itemizer.GenerationTab);
	}

	public TileEntity createNewTileEntity(final World world, final int var1) {
		final TileEntityMobSpawner spawner = new TileEntityMobSpawner();
		spawner.func_145881_a().setEntityName(mobName);
		return spawner;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public Block setTextureName(final String name) {
		return setBlockTextureName("nevermine:" + name);
	}

	public int quantityDropped(final Random par1Random) {
		return 0;
	}

	public Item getItem(final World par1World, final int par2, final int par3, final int par4) {
		return Item.getItemFromBlock(this);
	}

	public void dropBlockAsItemWithChance(final World par1World, final int par2, final int par3, final int par4, final int par5, final float par6, final int par7) {
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
		final int var8 = 15 + par1World.rand.nextInt(15) + par1World.rand.nextInt(15);
		dropXpOnBlockBreak(par1World, par2, par3, par4, var8);
	}

	public Block setName(final String name) {
		setBlockName(this.name = name);
		setTextureName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}

	static {
		BlockSpawner.spwnr = Material.iron;
	}
}
