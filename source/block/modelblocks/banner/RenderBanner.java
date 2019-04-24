package net.nevermine.block.modelblocks.banner;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RenderBanner extends TileEntitySpecialRenderer {
	public void renderTileEntityAt(final TileEntity te, final double x, final double y, final double z, final float tick) {
		if (te instanceof TileEntityBanner) {
			final TileEntityBanner tes = (TileEntityBanner)te;
			int rotation = 0;
			if (tes.getWorldObj() != null) {
				rotation = tes.getBlockMetadata();
			}
			Minecraft.getMinecraft().getTextureManager().bindTexture(tes.texture);
			GL11.glPushMatrix();
			GL11.glDisable(2896);
			GL11.glTranslatef((float)x + 0.5f, (float)y + 0.7f, (float)z + 0.5f);
			GL11.glScaled(0.5, 0.5, 0.5);
			GL11.glRotatef((float)(rotation * 90), 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
			tes.model.render(0.0625f);
			GL11.glEnable(2896);
			GL11.glPopMatrix();
		}
	}
}
