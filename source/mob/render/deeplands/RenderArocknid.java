package net.nevermine.mob.render.deeplands;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.deeplands.EntityArocknid;
import net.nevermine.mob.model.deeplands.modelArocknid;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderArocknid extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelArocknid model;
	private float scale;

	public RenderArocknid(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelArocknid)mainModel;
		scale = 1.8f;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityArocknid)par1EntityLivingBase, par2);
	}

	protected void preRenderCallback(final EntityArocknid par1EntityVoliant, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderArocknid.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/arocknid.png");
	}
}
