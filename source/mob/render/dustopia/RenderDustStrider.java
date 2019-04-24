package net.nevermine.mob.render.dustopia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.dustopia.modelDustStrider;

@SideOnly(Side.CLIENT)
public class RenderDustStrider extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelDustStrider model;

	public RenderDustStrider(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelDustStrider)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderDustStrider.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/duststrider.png");
	}
}
