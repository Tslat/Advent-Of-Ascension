package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.overworld.modelMegaGuardian;

@SideOnly(Side.CLIENT)
public class RenderMegaGuardian extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelMegaGuardian model;

	public RenderMegaGuardian(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelMegaGuardian)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderMegaGuardian.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/megaGuardian.png");
	}
}
