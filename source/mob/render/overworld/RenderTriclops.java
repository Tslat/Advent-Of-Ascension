package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.overworld.modelTriclops;

@SideOnly(Side.CLIENT)
public class RenderTriclops extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelTriclops model;

	public RenderTriclops(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelTriclops)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderTriclops.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/triclops.png");
	}
}
