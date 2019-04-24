package net.nevermine.mob.render.iromine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.iromine.EntityMechachron;
import net.nevermine.mob.model.iromine.modelMechachron;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderMechachron extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelMechachron model;
	private float scale;

	public RenderMechachron(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelMechachron)mainModel;
		scale = 2.0f;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityMechachron)par1EntityLivingBase, par2);
	}

	protected void preRenderCallback(final EntityMechachron par1EntityVoliant, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderMechachron.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/mechachron.png");
	}
}
