package net.nevermine.mob.render.lborean;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.lborean.modelAngler;

@SideOnly(Side.CLIENT)
public class RenderAngler extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelAngler model;

	public RenderAngler(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelAngler)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderAngler.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/angler.png");
	}
}
