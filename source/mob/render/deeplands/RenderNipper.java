package net.nevermine.mob.render.deeplands;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.deeplands.EntityNipper;
import net.nevermine.mob.model.deeplands.modelNipper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderNipper extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelNipper model;
	private float scale;

	public RenderNipper(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelNipper)mainModel;
		scale = 0.5f;
	}

	protected void preRenderCallback(final EntityNipper par1EntityYeti, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityNipper)par1EntityLivingBase, par2);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderNipper.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/nipper.png");
	}
}
