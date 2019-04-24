package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.night.EntityAnemia;
import net.nevermine.mob.model.overworld.modelAnemia;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderAnemia extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelAnemia model;
	private float scale;

	public RenderAnemia(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelAnemia)mainModel;
		scale = 3.0f;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityAnemia)par1EntityLivingBase, par2);
	}

	protected void preRenderCallback(final EntityAnemia par1EntityVoliant, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderAnemia.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/anemia.png");
	}
}
