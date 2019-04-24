package net.nevermine.projectiles.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderElementalArrow extends Render {
	private static ResourceLocation EntityTexture;

	public void doRender(final EntityArrow par1EntityArrow, final double par2, final double par4, final double par6, final float par8, final float par9) {
		bindEntityTexture(par1EntityArrow);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2, (float)par4, (float)par6);
		GL11.glRotatef(par1EntityArrow.prevRotationYaw + (par1EntityArrow.rotationYaw - par1EntityArrow.prevRotationYaw) * par9 - 90.0f, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(par1EntityArrow.prevRotationPitch + (par1EntityArrow.rotationPitch - par1EntityArrow.prevRotationPitch) * par9, 0.0f, 0.0f, 1.0f);
		final Tessellator tessellator = Tessellator.instance;
		final byte b0 = 0;
		final float f2 = 0.0f;
		final float f3 = 0.5f;
		final float f4 = (0 + b0 * 10) / 32.0f;
		final float f5 = (5 + b0 * 10) / 32.0f;
		final float f6 = 0.0f;
		final float f7 = 0.15625f;
		final float f8 = (5 + b0 * 10) / 32.0f;
		final float f9 = (10 + b0 * 10) / 32.0f;
		final float f10 = 0.05625f;
		GL11.glEnable(32826);
		final float f11 = par1EntityArrow.arrowShake - par9;
		if (f11 > 0.0f) {
			final float f12 = -MathHelper.sin(f11 * 3.0f) * f11;
			GL11.glRotatef(f12, 0.0f, 0.0f, 1.0f);
		}
		GL11.glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
		GL11.glScalef(f10, f10, f10);
		GL11.glTranslatef(-4.0f, 0.0f, 0.0f);
		GL11.glNormal3f(f10, 0.0f, 0.0f);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(-7.0, -2.0, -2.0, (double)f6, (double)f8);
		tessellator.addVertexWithUV(-7.0, -2.0, 2.0, (double)f7, (double)f8);
		tessellator.addVertexWithUV(-7.0, 2.0, 2.0, (double)f7, (double)f9);
		tessellator.addVertexWithUV(-7.0, 2.0, -2.0, (double)f6, (double)f9);
		tessellator.draw();
		GL11.glNormal3f(-f10, 0.0f, 0.0f);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(-7.0, 2.0, -2.0, (double)f6, (double)f8);
		tessellator.addVertexWithUV(-7.0, 2.0, 2.0, (double)f7, (double)f8);
		tessellator.addVertexWithUV(-7.0, -2.0, 2.0, (double)f7, (double)f9);
		tessellator.addVertexWithUV(-7.0, -2.0, -2.0, (double)f6, (double)f9);
		tessellator.draw();
		for (int i = 0; i < 4; ++i) {
			GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
			GL11.glNormal3f(0.0f, 0.0f, f10);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-8.0, -2.0, 0.0, (double)f2, (double)f4);
			tessellator.addVertexWithUV(8.0, -2.0, 0.0, (double)f3, (double)f4);
			tessellator.addVertexWithUV(8.0, 2.0, 0.0, (double)f3, (double)f5);
			tessellator.addVertexWithUV(-8.0, 2.0, 0.0, (double)f2, (double)f5);
			tessellator.draw();
		}
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}

	protected ResourceLocation getEntityTexture(final Entity par1Entity) {
		return RenderElementalArrow.EntityTexture;
	}

	public void doRender(final Entity par1Entity, final double par2, final double par4, final double par6, final float par8, final float par9) {
		doRender((EntityArrow)par1Entity, par2, par4, par6, par8, par9);
	}

	static {
		RenderElementalArrow.EntityTexture = new ResourceLocation("nevermine:textures/projectiles/elementalArrow.png");
	}
}
