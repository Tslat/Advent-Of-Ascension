package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.overworld.EntityDeathHunter;
import net.nevermine.mob.model.overworld.modelDeathHunter;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderDeathHunter extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelDeathHunter model;
	private float scale;

	public RenderDeathHunter(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelDeathHunter)mainModel;
		scale = 2.0f;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityDeathHunter)par1EntityLivingBase, par2);
	}

	protected void preRenderCallback(final EntityDeathHunter par1EntityVoliant, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderDeathHunter.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/deathHunter.png");
	}
}
