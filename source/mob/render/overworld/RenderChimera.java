package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.overworld.EntityChimera;
import net.nevermine.mob.model.overworld.modelChimera;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderChimera extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	private float scale;
	protected modelChimera model;

	public RenderChimera(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelChimera)mainModel;
		scale = 1.5f;
	}

	protected void preRenderCallback(final EntityChimera par1EntityChimera, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderChimera.EntityTexture;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityChimera)par1EntityLivingBase, par2);
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/chimera.png");
	}
}
