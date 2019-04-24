package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.overworld.EntityYeti;
import net.nevermine.mob.model.overworld.modelYeti;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderYeti extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	private float scale;
	protected modelYeti model;

	public RenderYeti(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelYeti)mainModel;
		scale = 1.5f;
	}

	protected void preRenderCallback(final EntityYeti par1EntityYeti, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderYeti.EntityTexture;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityYeti)par1EntityLivingBase, par2);
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/yeti.png");
	}
}
