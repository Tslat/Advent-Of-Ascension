package net.nevermine.npc.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.npc.auxil.EntityBloodlust;
import net.nevermine.npc.model.modelBloodlust;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBloodlust extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelBloodlust model;
	private float scale;

	public RenderBloodlust(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelBloodlust)mainModel;
		scale = 0.5f;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityBloodlust)par1EntityLivingBase, par2);
	}

	protected void preRenderCallback(final EntityBloodlust par1EntityVoliant, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderBloodlust.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/bloodlust.png");
	}
}
