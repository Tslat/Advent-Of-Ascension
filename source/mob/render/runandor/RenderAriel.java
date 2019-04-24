package net.nevermine.mob.render.runandor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.runandor.modelAriel;

@SideOnly(Side.CLIENT)
public class RenderAriel extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelAriel model;

	public RenderAriel(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelAriel)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderAriel.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/ariel.png");
	}
}
