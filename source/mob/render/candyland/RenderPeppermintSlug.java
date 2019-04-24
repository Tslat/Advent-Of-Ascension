package net.nevermine.mob.render.candyland;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.candyland.modelGummySlug;

@SideOnly(Side.CLIENT)
public class RenderPeppermintSlug extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelGummySlug model;

	public RenderPeppermintSlug(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelGummySlug)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderPeppermintSlug.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/peppermintSlug.png");
	}
}
