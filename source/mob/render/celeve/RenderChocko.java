package net.nevermine.mob.render.celeve;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.celeve.modelChocko;

@SideOnly(Side.CLIENT)
public class RenderChocko extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelChocko model;

	public RenderChocko(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelChocko)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderChocko.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/chocko.png");
	}
}