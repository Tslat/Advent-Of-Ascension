package net.nevermine.mob.render.lunalus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.lunalus.modelInmateY;

@SideOnly(Side.CLIENT)
public class RenderInmateY extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelInmateY model;

	public RenderInmateY(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelInmateY)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderInmateY.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/inmateY.png");
	}
}
