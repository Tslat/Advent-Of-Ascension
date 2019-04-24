package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.overworld.modelSkolle;

@SideOnly(Side.CLIENT)
public class RenderSkolle extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelSkolle model;

	public RenderSkolle(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelSkolle)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderSkolle.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/skolle.png");
	}
}
