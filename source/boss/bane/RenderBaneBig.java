package net.nevermine.boss.bane;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBaneBig extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelBane model;
	private float scale;

	public RenderBaneBig(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelBane)mainModel;
		scale = 2.0f;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityBaneBig)par1EntityLivingBase, par2);
	}

	protected void preRenderCallback(final EntityBaneBig par1EntityVoliant, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderBaneBig.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/bane.png");
	}
}
