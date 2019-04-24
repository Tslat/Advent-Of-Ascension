package net.nevermine.mob.render.voxponds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.voxponds.EntityDestructor;
import net.nevermine.mob.model.voxponds.modelDestructor;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderDestructor extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	private float scale;
	protected modelDestructor model;

	public RenderDestructor(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelDestructor)mainModel;
		scale = 3.5f;
	}

	protected void preRenderCallback(final EntityDestructor par1EntityDestructor, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderDestructor.EntityTexture;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityDestructor)par1EntityLivingBase, par2);
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/destructor.png");
	}
}
