package net.nevermine.mob.render.candyland;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.candyland.modelGingerbird;

@SideOnly(Side.CLIENT)
public class RenderGingerbird extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelGingerbird model;

	public RenderGingerbird(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelGingerbird)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderGingerbird.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/gingerbird.png");
	}
}
