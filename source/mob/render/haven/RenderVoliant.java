package net.nevermine.mob.render.haven;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.haven.EntityVoliant;
import net.nevermine.mob.model.haven.modelVoliant;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderVoliant extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	private float scale;
	protected modelVoliant model;

	public RenderVoliant(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelVoliant)mainModel;
		scale = 4.0f;
	}

	protected void preRenderCallback(final EntityVoliant par1EntityVoliant, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderVoliant.EntityTexture;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityVoliant)par1EntityLivingBase, par2);
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/voliant.png");
	}
}
