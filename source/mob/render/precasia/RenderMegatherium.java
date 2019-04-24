package net.nevermine.mob.render.precasia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.precasia.EntityMegatherium;
import net.nevermine.mob.model.precasia.modelMegatherium;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderMegatherium extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	private float scale;
	protected modelMegatherium model;

	public RenderMegatherium(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelMegatherium)mainModel;
		scale = 2.0f;
	}

	protected void preRenderCallback(final EntityMegatherium par1EntityMegatherium, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderMegatherium.EntityTexture;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityMegatherium)par1EntityLivingBase, par2);
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/megatherium.png");
	}
}
