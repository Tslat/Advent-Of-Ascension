package net.nevermine.projectiles.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderShoeShot extends Render {
	private static ResourceLocation EntityTexture;
	private float scale;

	public RenderShoeShot() {
		scale = 1.0f;
	}

	public void doRender(final Entity entity, final double x, final double y, final double z, final float par8, final float par9) {
		GL11.glPushMatrix();
		bindEntityTexture(entity);
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glEnable(32826);
		GL11.glScalef(scale * 0.5f, scale * 0.5f, scale * 0.5f);
		final Tessellator tessellator = Tessellator.instance;
		final float minU = 0.0f;
		final float maxU = 1.0f;
		final float minV = 0.0f;
		final float maxV = 1.0f;
		final float f7 = 1.0f;
		final float f8 = 0.5f;
		final float f9 = 0.25f;
		GL11.glRotatef(180.0f - renderManager.playerViewY, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(((entity.ticksExisted + par9) / 0.3f) * (180f / (float)Math.PI), 1.0f, 1.0f, 1.0f);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0f, 1.0f, 0.0f);
		tessellator.addVertexWithUV((double)(0.0f - f8), (double)(0.0f - f9), 0.0, (double)minU, (double)maxV);
		tessellator.addVertexWithUV((double)(f7 - f8), (double)(0.0f - f9), 0.0, (double)maxU, (double)maxV);
		tessellator.addVertexWithUV((double)(f7 - f8), (double)(1.0f - f9), 0.0, (double)maxU, (double)minV);
		tessellator.addVertexWithUV((double)(0.0f - f8), (double)(1.0f - f9), 0.0, (double)minU, (double)minV);
		tessellator.draw();
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderShoeShot.EntityTexture;
	}

	static {
		RenderShoeShot.EntityTexture = new ResourceLocation("nevermine:textures/projectiles/blindCat.png");
	}
}
