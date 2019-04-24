package net.nevermine.mob.render.lunalus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.lunalus.modelZarg;

@SideOnly(Side.CLIENT)
public class RenderZarg extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelZarg model;

	public RenderZarg(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelZarg)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderZarg.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/zarg.png");
	}
}
