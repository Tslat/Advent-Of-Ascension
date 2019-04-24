package net.nevermine.mob.render.abyss;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.abyss.EntitySlimer;
import net.nevermine.mob.model.abyss.modelSlimer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSlimer extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	private float scale;
	protected modelSlimer model;

	public RenderSlimer(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelSlimer)mainModel;
		scale = 2.0f;
	}

	protected void preRenderCallback(final EntitySlimer par1EntitySlimer, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderSlimer.EntityTexture;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntitySlimer)par1EntityLivingBase, par2);
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/slimer.png");
	}
}
