package net.nevermine.mob.render.crystevia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.crystevia.modelConstructFlight;

@SideOnly(Side.CLIENT)
public class RenderConstructFlight extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelConstructFlight model;

	public RenderConstructFlight(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelConstructFlight)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderConstructFlight.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/constructFlight.png");
	}
}
