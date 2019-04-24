package net.nevermine.mob.render.underground;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.underground.modelTrickster;

@SideOnly(Side.CLIENT)
public class RenderTrickster extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelTrickster model;

	public RenderTrickster(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelTrickster)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderTrickster.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/trickster.png");
	}
}
