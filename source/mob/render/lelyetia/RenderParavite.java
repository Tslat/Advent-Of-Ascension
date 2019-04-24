package net.nevermine.mob.render.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.lelyetia.modelParavite;

@SideOnly(Side.CLIENT)
public class RenderParavite extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelParavite model;

	public RenderParavite(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelParavite)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderParavite.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/paravite.png");
	}
}
