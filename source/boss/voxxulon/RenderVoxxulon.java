package net.nevermine.boss.voxxulon;

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
public class RenderVoxxulon extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelVoxxulon model;
	private float scale;

	public RenderVoxxulon(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelVoxxulon)mainModel;
		scale = 2.0f;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityVoxxulon)par1EntityLivingBase, par2);
	}

	public void renderVoxxulon(final EntityVoxxulon var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		EternalBossStatus.setBossStatus(var1, true, 28);
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final EntityLiving var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderVoxxulon((EntityVoxxulon)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final Entity var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderVoxxulon((EntityVoxxulon)var1, var2, var4, var6, var8, var9);
	}

	protected void preRenderCallback(final EntityVoxxulon par1EntityVoliant, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderVoxxulon.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/voxxulon.png");
	}
}
