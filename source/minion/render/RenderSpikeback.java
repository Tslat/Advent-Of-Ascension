package net.nevermine.minion.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.minion.model.modelSpikeback;

@SideOnly(Side.CLIENT)
public class RenderSpikeback extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelSpikeback model;

	public RenderSpikeback(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelSpikeback)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderSpikeback.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/spikeback.png");
	}
}
