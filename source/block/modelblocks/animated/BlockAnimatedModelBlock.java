package net.nevermine.block.modelblocks.animated;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.nevermine.block.modelblocks.BlockMod;
import net.nevermine.block.modelblocks.ModelEternalBlock;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.SpecialBlockizer;

import java.util.Random;

public class BlockAnimatedModelBlock extends BlockMod {
	public ModelEternalBlock model;
	public ResourceLocation[] texture;
	private Random rand;

	public BlockAnimatedModelBlock(final ResourceLocation[] campfire, final ModelEternalBlock model) {
		super(Material.rock);
		rand = new Random();
		texture = campfire;
		this.model = model;
		setHardness(1.0f);
		setResistance(0.5f);
		setLightLevel(0.8f);
		setCreativeTab(Itemizer.FurnitureTab);
	}

	public boolean hasTileEntity(final int metadata) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final World p_149734_1_, final int p_149734_2_, final int p_149734_3_, final int p_149734_4_, final Random p_149734_5_) {
		final int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
		final double d0 = p_149734_2_ + 0.5f;
		final double d2 = p_149734_3_ + 0.7f;
		final double d3 = p_149734_4_ + 0.5f;
		final double d4 = 0.0099999988079071;
		final double d5 = 1.072883606E-8;
		if (l == 1) {
			p_149734_1_.spawnParticle("smoke", d0 - d5, d2 + d4, d3, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("flame", d0 - d5, d2 + d4, d3, 0.0, 0.0, 0.0);
		}
		else if (l == 2) {
			p_149734_1_.spawnParticle("smoke", d0 + d5, d2 + d4, d3, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("flame", d0 + d5, d2 + d4, d3, 0.0, 0.0, 0.0);
		}
		else if (l == 3) {
			p_149734_1_.spawnParticle("smoke", d0, d2 + d4, d3 - d5, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("flame", d0, d2 + d4, d3 - d5, 0.0, 0.0, 0.0);
		}
		else if (l == 4) {
			p_149734_1_.spawnParticle("smoke", d0, d2 + d4, d3 + d5, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("flame", d0, d2 + d4, d3 + d5, 0.0, 0.0, 0.0);
		}
		else {
			p_149734_1_.spawnParticle("smoke", d0, d2, d3, 0.0, 0.0, 0.0);
			p_149734_1_.spawnParticle("flame", d0, d2, d3, 0.0, 0.0, 0.0);
		}
	}

	public Item getItemDropped(final int par1, final Random par2, final int par3) {
		if (this == SpecialBlockizer.Campfire) {
			return Item.getItemFromBlock(Blocks.cobblestone);
		}
		return Item.getItemFromBlock(this);
	}

	public int quantityDropped(final Random par1) {
		if (this == SpecialBlockizer.Campfire) {
			return 3;
		}
		return 1;
	}

	public TileEntity createTileEntity(final World world, final int metadata) {
		return new TileEntityAnimatedModelBlock(texture, model);
	}

	public int getRenderType() {
		return -1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public void onBlockPlacedBy(final World w, final int x, final int y, final int z, final EntityLivingBase entity, final ItemStack item) {
		int l = MathHelper.floor_double(entity.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
		final int i1 = w.getBlockMetadata(x, y, z) >> 2;
		l = ++l % 4;
		if (l == 0) {
			w.setBlockMetadataWithNotify(x, y, z, 0x2 | i1 << 2, 2);
		}
		if (l == 1) {
			w.setBlockMetadataWithNotify(x, y, z, 0x3 | i1 << 2, 2);
		}
		if (l == 2) {
			w.setBlockMetadataWithNotify(x, y, z, 0x0 | i1 << 2, 2);
		}
		if (l == 3) {
			w.setBlockMetadataWithNotify(x, y, z, 0x1 | i1 << 2, 2);
		}
	}

	@Override
	public BlockAnimatedModelBlock setName(final String name) {
		setBlockTextureName("cobblestone");
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}
}
