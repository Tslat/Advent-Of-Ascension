package net.nevermine.minion.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.minion.model.modelDraggy;

@SideOnly(Side.CLIENT)
public class RenderDraggy extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelDraggy model;

	public RenderDraggy(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelDraggy)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderDraggy.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/draggy.png");
	}
}
