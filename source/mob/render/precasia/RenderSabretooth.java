package net.nevermine.mob.render.precasia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.precasia.EntitySabretooth;
import net.nevermine.mob.model.precasia.modelSabretooth;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSabretooth extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	private float scale;
	protected modelSabretooth model;

	public RenderSabretooth(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelSabretooth)mainModel;
		scale = 1.5f;
	}

	protected void preRenderCallback(final EntitySabretooth par1EntitySabretooth, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderSabretooth.EntityTexture;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntitySabretooth)par1EntityLivingBase, par2);
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/sabretooth.png");
	}
}
