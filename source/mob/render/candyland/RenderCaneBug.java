package net.nevermine.mob.render.candyland;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.candyland.modelCaneBug;

@SideOnly(Side.CLIENT)
public class RenderCaneBug extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelCaneBug model;

	public RenderCaneBug(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelCaneBug)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderCaneBug.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/caneBug.png");
	}
}
