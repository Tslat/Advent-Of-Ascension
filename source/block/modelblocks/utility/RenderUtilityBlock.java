package net.nevermine.block.modelblocks.utility;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RenderUtilityBlock extends TileEntitySpecialRenderer {
	private float Scale;

	public RenderUtilityBlock() {
		Scale = 0.98f;
	}

	public void renderTileEntityAt(final TileEntity te, final double x, final double y, final double z, final float tick) {
		if (te instanceof TileEntityUtilityBlock) {
			final TileEntityUtilityBlock tes = (TileEntityUtilityBlock)te;
			int rotation = 0;
			if (tes.getWorldObj() != null) {
				rotation = tes.getBlockMetadata();
			}
			Minecraft.getMinecraft().getTextureManager().bindTexture(tes.texture);
			GL11.glPushMatrix();
			GL11.glScalef(Scale, Scale, Scale);
			GL11.glDisable(2896);
			GL11.glTranslatef((float)x + 0.5f, (float)y + 1.5f, (float)z + 0.5f);
			GL11.glRotatef((float)(rotation * 90), 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
			tes.model.render(0.0625f);
			GL11.glEnable(2896);
			GL11.glPopMatrix();
		}
	}
}
