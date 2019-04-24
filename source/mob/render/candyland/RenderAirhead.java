package net.nevermine.mob.render.candyland;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.candyland.modelAirhead;

@SideOnly(Side.CLIENT)
public class RenderAirhead extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelAirhead model;

	public RenderAirhead(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelAirhead)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderAirhead.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/airhead.png");
	}
}
