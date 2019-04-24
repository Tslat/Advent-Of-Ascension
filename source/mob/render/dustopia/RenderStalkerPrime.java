package net.nevermine.mob.render.dustopia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.dustopia.modelStalkerPrime;

@SideOnly(Side.CLIENT)
public class RenderStalkerPrime extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelStalkerPrime model;

	public RenderStalkerPrime(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelStalkerPrime)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderStalkerPrime.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/stalker.png");
	}
}
