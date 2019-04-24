package net.nevermine.mob.render.candyland;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.candyland.modelCandyCorny;

@SideOnly(Side.CLIENT)
public class RenderCandyCorny extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelCandyCorny model;

	public RenderCandyCorny(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelCandyCorny)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderCandyCorny.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/candyCorny.png");
	}
}
