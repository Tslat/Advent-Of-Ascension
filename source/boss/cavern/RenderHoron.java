package net.nevermine.boss.cavern;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.resource.boss.EternalBossStatus;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderHoron extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelHoron model;
	private float scale;

	public RenderHoron(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelHoron)mainModel;
		scale = 1.2f;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityHoron)par1EntityLivingBase, par2);
	}

	public void renderHoron(final EntityHoron var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		EternalBossStatus.setBossStatus(var1, true, 33);
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final EntityLiving var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderHoron((EntityHoron)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final Entity var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderHoron((EntityHoron)var1, var2, var4, var6, var8, var9);
	}

	protected void preRenderCallback(final EntityHoron par1EntityVoliant, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderHoron.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/horon.png");
	}
}
