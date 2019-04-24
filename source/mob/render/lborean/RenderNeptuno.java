package net.nevermine.mob.render.lborean;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.lborean.modelNeptuno;

@SideOnly(Side.CLIENT)
public class RenderNeptuno extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelNeptuno model;

	public RenderNeptuno(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelNeptuno)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderNeptuno.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/neptuno.png");
	}
}
