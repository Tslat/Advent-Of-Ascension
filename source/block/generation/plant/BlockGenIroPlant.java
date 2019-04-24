package net.nevermine.block.generation.plant;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;

import java.util.ArrayList;
import java.util.Random;

public class BlockGenIroPlant extends Block implements IShearable {
	private String name;

	public BlockGenIroPlant(final Material Rck) {
		super(Rck);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(0.4f);
		setResistance(0.5f);
		setStepSound(BlockGenIroPlant.soundTypeGrass);
	}

	public int getRenderType() {
		return 1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public int quantityDropped(final Random p_149745_1_) {
		return 0;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int posX, int posY, int posZ) {
		Block bl = world.getBlock(posX, posY - 1, posZ);

		return bl.isOpaqueCube();

	}

	@Override
	public void onNeighborBlockChange(World world, int posX, int posY, int posZ, Block block) {
		if (world.getBlock(posX, posY - 1, posZ) == Blocks.air)
			world.setBlock(posX, posY, posZ, Blocks.air);
	}

	public void onEntityCollidedWithBlock(final World w, final int x, final int y, final int z, final Entity player) {
		if (player instanceof EntityPlayer) {
			if (w.getBlock(x, y, z) == Blockizer.IroGrass) {
				((EntityLivingBase)player).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 2));
			}
			if (w.getBlock(x, y, z) == Blockizer.Irotops) {
				((EntityLivingBase)player).addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 60, 1));
			}
			if (w.getBlock(x, y, z) == Blockizer.Lurchians) {
				player.addVelocity(player.motionX * 1.0, 1.2, player.motionZ * 1.0);
				player.fallDistance = 0;
			}
		}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World par1World, final int par2, final int par3, final int par4) {
		final float f = 0.5f;
		return AxisAlignedBB.getBoundingBox((double)par2, (double)par3, (double)par4, (double)(par2 + 1), (double)(par3 - f), (double)(par4 + 1));
	}

	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}

	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		breakBlock((World)world, x, y, z, this, world.getBlockMetadata(x, y, z));
		ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Item.getItemFromBlock(this), 1));

		return stack;
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
}
