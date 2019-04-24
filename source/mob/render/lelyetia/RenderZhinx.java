package net.nevermine.mob.render.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.lelyetia.modelZhinx;

@SideOnly(Side.CLIENT)
public class RenderZhinx extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelZhinx model;

	public RenderZhinx(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelZhinx)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderZhinx.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/zhinx.png");
	}
}
