package net.nevermine.block.modelblocks.banner;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.nevermine.block.modelblocks.BlockMod;
import net.nevermine.block.modelblocks.ModelEternalBlock;
import net.nevermine.block.modelblocks.statue.TileEntityStatue;
import net.nevermine.izer.Itemizer;

public class BlockBanner extends BlockMod {
	public ModelEternalBlock model;
	public ResourceLocation texture;

	public BlockBanner(final ResourceLocation texture, final ModelEternalBlock model) {
		super(Material.cloth);
		this.texture = texture;
		this.model = model;
		setHardness(1.0f);
		setResistance(0.5f);
		setCreativeTab(Itemizer.FurnitureTab);
	}

	public boolean hasTileEntity(final int metadata) {
		return true;
	}

	public TileEntity createTileEntity(final World world, final int metadata) {
		return new TileEntityStatue(texture, model);
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
			w.setBlockMetadataWithNotify(x, y, z, 0x1 | i1 << 2, 2);
		}
		if (l == 1) {
			w.setBlockMetadataWithNotify(x, y, z, 0x2 | i1 << 2, 2);
		}
		if (l == 2) {
			w.setBlockMetadataWithNotify(x, y, z, 0x3 | i1 << 2, 2);
		}
		if (l == 3) {
			w.setBlockMetadataWithNotify(x, y, z, 0x0 | i1 << 2, 2);
		}
	}

	@Override
	public BlockBanner setName(final String name) {
		setBlockTextureName("cobblestone");
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}
}
