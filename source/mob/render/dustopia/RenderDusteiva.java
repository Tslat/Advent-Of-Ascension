package net.nevermine.mob.render.dustopia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.dustopia.modelDusteiva;

@SideOnly(Side.CLIENT)
public class RenderDusteiva extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelDusteiva model;

	public RenderDusteiva(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelDusteiva)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderDusteiva.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/dusteiva.png");
	}
}
