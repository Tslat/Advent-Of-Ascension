package net.nevermine.mob.render.iromine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.iromine.modelMechyon;

@SideOnly(Side.CLIENT)
public class RenderMechyon extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelMechyon model;

	public RenderMechyon(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelMechyon)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderMechyon.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/mechyon.png");
	}
}
