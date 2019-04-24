package net.nevermine.mob.render.iromine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.iromine.modelEnforcer;

@SideOnly(Side.CLIENT)
public class RenderEnforcer extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelEnforcer model;

	public RenderEnforcer(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelEnforcer)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderEnforcer.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/enforcer.png");
	}
}
