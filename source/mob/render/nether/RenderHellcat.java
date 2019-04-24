package net.nevermine.mob.render.nether;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.nether.EntityHellcat;
import net.nevermine.mob.model.nether.modelHellcat;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderHellcat extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	private float scale;
	protected modelHellcat model;

	public RenderHellcat(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelHellcat)mainModel;
		scale = 2.2f;
	}

	protected void preRenderCallback(final EntityHellcat par1EntityHellcat, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderHellcat.EntityTexture;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityHellcat)par1EntityLivingBase, par2);
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/hellcat.png");
	}
}
