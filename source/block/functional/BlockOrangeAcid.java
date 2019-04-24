package net.nevermine.block.functional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.nevermine.izer.Itemizer;

import java.util.Random;

public class BlockOrangeAcid extends Block {
	public BlockOrangeAcid() {
		super(Material.sponge);
		setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
		setTickRandomly(true);
		setCreativeTab(Itemizer.GenerationTab);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World p_149668_1_, final int p_149668_2_, final int p_149668_3_, final int p_149668_4_) {
		final int l = p_149668_1_.getBlockMetadata(p_149668_2_, p_149668_3_, p_149668_4_) & 0x7;
		final float f = 0.125f;
		return AxisAlignedBB.getBoundingBox(p_149668_2_ + minX, p_149668_3_ + minY, p_149668_4_ + minZ, p_149668_2_ + maxX, (double)(p_149668_3_ + l * f), p_149668_4_ + maxZ);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public void setBlockBoundsBasedOnState(final IBlockAccess par1IBlockAccess, final int par2, final int par3, final int par4) {
		final int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 0x7;
		final float var6 = 2 * (1 + var5) / 16.0f;
		setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, var6, 1.0f);
	}

	public boolean canPlaceBlockAt(final World world, final int par2, final int par3, final int par4) {
		final Block block = world.getBlock(par2, par3 - 1, par4);
		return block != Blocks.ice && block != Blocks.packed_ice && (block.isLeaves(world, par2, par3 - 1, par4) || (block == this && (world.getBlockMetadata(par2, par2 - 1, par3) & 0x7) == 0x7) || (block.isOpaqueCube() && block.getMaterial().blocksMovement()));
	}

	public void onNeighborBlockChange(final World par1World, final int par2, final int par3, final int par4, final Block par5) {
		canSnowStay(par1World, par2, par3, par4);
	}

	private boolean canSnowStay(final World par1World, final int par2, final int par3, final int par4) {
		if (!canPlaceBlockAt(par1World, par2, par3, par4)) {
			par1World.setBlockToAir(par2, par3, par4);
			return false;
		}
		return true;
	}

	public void harvestBlock(final World par1World, final EntityPlayer par2EntityPlayer, final int par3, final int par4, final int par5, final int par6) {
		super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
		par1World.setBlockToAir(par3, par4, par5);
	}

	public int tickRate(final World par1World) {
		return 20;
	}

	public int quantityDropped(final Random par1Random) {
		return 0;
	}

	public void updateTick(final World par1World, final int par2, final int par3, final int par4, final Random par5Random) {
		par1World.setBlockToAir(par2, par3, par4);
	}

	public void onEntityCollidedWithBlock(final World var1, final int var2, final int var3, final int var4, final Entity var5) {
		if (var5 instanceof EntityMob) {
			var5.attackEntityFrom(DamageSource.cactus, 8.0f);
			((EntityMob)var5).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40, 3));
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(final IBlockAccess par1IBlockAccess, final int par2, final int par3, final int par4, final int par5) {
		return par5 == 1 || super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
	}
}
