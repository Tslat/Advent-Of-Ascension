package net.nevermine.mob.render.haven;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.haven.EntityOrbiter;
import net.nevermine.mob.model.haven.modelOrbiter;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderOrbiter extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelOrbiter model;
	private float scale;

	public RenderOrbiter(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelOrbiter)mainModel;
		scale = 2.0f;
	}

	protected void preRenderCallback(final EntityOrbiter par1EntitySurveyor, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderOrbiter.EntityTexture;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityOrbiter)par1EntityLivingBase, par2);
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/orbiter.png");
	}
}
