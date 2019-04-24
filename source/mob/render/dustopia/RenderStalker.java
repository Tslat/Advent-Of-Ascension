package net.nevermine.mob.render.dustopia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.dustopia.modelStalker;

@SideOnly(Side.CLIENT)
public class RenderStalker extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelStalker model;

	public RenderStalker(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelStalker)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderStalker.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/stalker.png");
	}
}
