package net.nevermine.mob.render.celeve;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.celeve.modelKoko;

@SideOnly(Side.CLIENT)
public class RenderKoko extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelKoko model;

	public RenderKoko(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelKoko)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderKoko.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/koko.png");
	}
}
