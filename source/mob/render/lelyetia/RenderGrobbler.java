package net.nevermine.mob.render.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.lelyetia.EntityGrobbler;
import net.nevermine.mob.model.lelyetia.modelGrobbler;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderGrobbler extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelGrobbler model;
	private float scale;

	public RenderGrobbler(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelGrobbler)mainModel;
		scale = 2.0f;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityGrobbler)par1EntityLivingBase, par2);
	}

	protected void preRenderCallback(final EntityGrobbler par1EntityVoliant, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderGrobbler.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/grobbler.png");
	}
}
