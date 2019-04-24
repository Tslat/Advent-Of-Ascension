package net.nevermine.mob.render.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.lelyetia.modelExohead;

@SideOnly(Side.CLIENT)
public class RenderExohead extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelExohead model;

	public RenderExohead(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelExohead)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderExohead.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/exohead.png");
	}
}
