package net.nevermine.minion.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.minion.entity.EntityShadowStalker;
import net.nevermine.minion.model.modelShadowStalker;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderShadowStalker extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	private float scale;
	protected modelShadowStalker model;

	public RenderShadowStalker(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelShadowStalker)mainModel;
		scale = 1.5f;
	}

	protected void preRenderCallback(final EntityShadowStalker par1EntityChimera, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderShadowStalker.EntityTexture;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityShadowStalker)par1EntityLivingBase, par2);
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/shadowstalker.png");
	}
}
