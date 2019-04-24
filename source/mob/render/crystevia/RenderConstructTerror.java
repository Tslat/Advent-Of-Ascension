package net.nevermine.mob.render.crystevia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.crystevia.modelConstructTerror;

@SideOnly(Side.CLIENT)
public class RenderConstructTerror extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelConstructTerror model;

	public RenderConstructTerror(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelConstructTerror)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderConstructTerror.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/constructTerror.png");
	}
}
