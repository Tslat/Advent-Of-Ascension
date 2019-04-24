package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.overworld.modelCyclops;

@SideOnly(Side.CLIENT)
public class RenderCyclops extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelCyclops model;

	public RenderCyclops(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelCyclops)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderCyclops.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/cyclops.png");
	}
}
