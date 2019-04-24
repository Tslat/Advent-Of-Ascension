package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.overworld.EntityRammerhead;
import net.nevermine.mob.model.overworld.modelRammerhead;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderRammerhead extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	private float scale;
	protected modelRammerhead model;

	public RenderRammerhead(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelRammerhead)mainModel;
		scale = 2.0f;
	}

	protected void preRenderCallback(final EntityRammerhead par1EntityRammerhead, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderRammerhead.EntityTexture;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityRammerhead)par1EntityLivingBase, par2);
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/rammerhead.png");
	}
}
