package net.nevermine.boss.shadowlord;

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
public class RenderShadowlord extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelShadowlord model;
	private float scale;

	public RenderShadowlord(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelShadowlord)mainModel;
		scale = 6.0f;
	}

	protected void preRenderCallback(final EntityShadowlord par1EntityMegatherium, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	public void renderShadowlord(final EntityShadowlord var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		EternalBossStatus.setBossStatus(var1, true, 9);
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final EntityLiving var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderShadowlord((EntityShadowlord)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final Entity var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderShadowlord((EntityShadowlord)var1, var2, var4, var6, var8, var9);
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityShadowlord)par1EntityLivingBase, par2);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderShadowlord.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/shadowlord.png");
	}
}
