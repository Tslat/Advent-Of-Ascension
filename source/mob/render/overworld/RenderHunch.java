package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.overworld.modelHunch;

@SideOnly(Side.CLIENT)
public class RenderHunch extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelHunch model;

	public RenderHunch(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelHunch)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderHunch.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/hunch.png");
	}
}
