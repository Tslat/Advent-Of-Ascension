package net.nevermine.mob.render.celeve;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.celeve.modelBobo;

@SideOnly(Side.CLIENT)
public class RenderBobo extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelBobo model;

	public RenderBobo(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelBobo)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderBobo.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/bobo.png");
	}
}
