package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.overworld.EntityStoneGiant;
import net.nevermine.mob.model.overworld.modelGiant;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderStoneGiant extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	private float scale;
	protected modelGiant model;

	public RenderStoneGiant(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelGiant)mainModel;
		scale = 1.8f;
	}

	protected void preRenderCallback(final EntityStoneGiant par1EntityStoneGiant, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderStoneGiant.EntityTexture;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityStoneGiant)par1EntityLivingBase, par2);
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/stoneGiant.png");
	}
}
