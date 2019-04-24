package net.nevermine.mob.render.celeve;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.celeve.modelSticky;

@SideOnly(Side.CLIENT)
public class RenderSticky extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelSticky model;

	public RenderSticky(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelSticky)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderSticky.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/sticky.png");
	}
}
