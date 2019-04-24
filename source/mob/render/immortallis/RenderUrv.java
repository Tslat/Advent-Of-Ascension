package net.nevermine.mob.render.immortallis;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.immortallis.modelUrv;

@SideOnly(Side.CLIENT)
public class RenderUrv extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelUrv model;

	public RenderUrv(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelUrv)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderUrv.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/urv.png");
	}
}
