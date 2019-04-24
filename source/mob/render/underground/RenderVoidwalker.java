package net.nevermine.mob.render.underground;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.underground.modelVoidwalker;

@SideOnly(Side.CLIENT)
public class RenderVoidwalker extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelVoidwalker model;

	public RenderVoidwalker(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelVoidwalker)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderVoidwalker.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/voidwalker.png");
	}
}
