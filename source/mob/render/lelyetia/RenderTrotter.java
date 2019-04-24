package net.nevermine.mob.render.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.lelyetia.modelTrotter;

@SideOnly(Side.CLIENT)
public class RenderTrotter extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelTrotter model;

	public RenderTrotter(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelTrotter)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderTrotter.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/trotter.png");
	}
}
