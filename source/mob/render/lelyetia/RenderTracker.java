package net.nevermine.mob.render.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.lelyetia.modelTracker;

@SideOnly(Side.CLIENT)
public class RenderTracker extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelTracker model;

	public RenderTracker(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelTracker)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderTracker.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/tracker.png");
	}
}
