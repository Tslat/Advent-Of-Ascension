package net.nevermine.mob.render.precasia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.precasia.modelDyrehorn;

@SideOnly(Side.CLIENT)
public class RenderDyrehorn extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelDyrehorn model;

	public RenderDyrehorn(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelDyrehorn)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderDyrehorn.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/dyrehorn.png");
	}
}
