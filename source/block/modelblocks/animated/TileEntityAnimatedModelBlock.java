package net.nevermine.block.modelblocks.animated;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class TileEntityAnimatedModelBlock extends TileEntity {
	public ResourceLocation[] texture;
	public ModelEternalBlock model;

	public TileEntityAnimatedModelBlock() {
	}

	public boolean canUpdate() {
		return false;
	}

	public TileEntityAnimatedModelBlock(final ResourceLocation[] texture2, final ModelEternalBlock model) {
		this.model = model;
		texture = texture2;
	}
}
