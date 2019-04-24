package net.nevermine.block.modelblocks.utility;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class TileEntityUtilityBlock extends TileEntity {
	public ResourceLocation texture;
	public ModelEternalBlock model;

	public TileEntityUtilityBlock() {
	}

	public boolean canUpdate() {
		return false;
	}

	public TileEntityUtilityBlock(final ResourceLocation texture, final ModelEternalBlock model) {
		this.model = model;
		this.texture = texture;
	}
}
