package net.nevermine.block.modelblocks.statue;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class TileEntityStatue extends TileEntity {
	public ResourceLocation texture;
	public ModelEternalBlock model;

	public TileEntityStatue() {
	}

	public TileEntityStatue(final ResourceLocation texture, final ModelEternalBlock model) {
		this.model = model;
		this.texture = texture;
	}
}
