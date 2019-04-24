package net.nevermine.mob.render.gardencia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.gardencia.modelPodPlant;

@SideOnly(Side.CLIENT)
public class RenderPodPlant extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelPodPlant model;

	public RenderPodPlant(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelPodPlant)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderPodPlant.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/podplant.png");
	}
}
