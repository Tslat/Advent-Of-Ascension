package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.overworld.modelCharger;

@SideOnly(Side.CLIENT)
public class RenderSeaCharger extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelCharger model;

	public RenderSeaCharger(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelCharger)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderSeaCharger.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/chargerSea.png");
	}
}
