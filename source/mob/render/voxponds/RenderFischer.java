package net.nevermine.mob.render.voxponds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.voxponds.modelFischer;

@SideOnly(Side.CLIENT)
public class RenderFischer extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelFischer model;

	public RenderFischer(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelFischer)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderFischer.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/fischer.png");
	}
}
