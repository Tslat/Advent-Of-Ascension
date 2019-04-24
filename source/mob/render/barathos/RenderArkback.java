package net.nevermine.mob.render.barathos;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.barathos.EntityArkback;
import net.nevermine.mob.model.barathos.modelArkback;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderArkback extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelArkback model;
	private float scale;

	public RenderArkback(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelArkback)mainModel;
		scale = 2.0f;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityArkback)par1EntityLivingBase, par2);
	}

	protected void preRenderCallback(final EntityArkback par1EntityVoliant, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderArkback.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/arkback.png");
	}
}
