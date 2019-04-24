package net.nevermine.mob.render.gardencia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.gardencia.modelCarrotop;

@SideOnly(Side.CLIENT)
public class RenderCarrotop extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelCarrotop model;

	public RenderCarrotop(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelCarrotop)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderCarrotop.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/carrotop.png");
	}
}
