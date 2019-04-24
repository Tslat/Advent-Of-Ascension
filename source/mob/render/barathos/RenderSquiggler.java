package net.nevermine.mob.render.barathos;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.barathos.modelSquiggler;

@SideOnly(Side.CLIENT)
public class RenderSquiggler extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelSquiggler model;

	public RenderSquiggler(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelSquiggler)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderSquiggler.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/squiggler.png");
	}
}
