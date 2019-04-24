package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderSeaSpider extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected ModelSpider model;

	public RenderSeaSpider(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (ModelSpider)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderSeaSpider.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/seaSpider.png");
	}
}
