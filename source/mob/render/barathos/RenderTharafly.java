package net.nevermine.mob.render.barathos;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.barathos.modelTharafly;

@SideOnly(Side.CLIENT)
public class RenderTharafly extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelTharafly model;

	public RenderTharafly(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelTharafly)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderTharafly.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/tharafly.png");
	}
}
