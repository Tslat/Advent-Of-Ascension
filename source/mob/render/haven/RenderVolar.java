package net.nevermine.mob.render.haven;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.haven.modelVolar;

@SideOnly(Side.CLIENT)
public class RenderVolar extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelVolar model;

	public RenderVolar(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelVolar)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderVolar.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/volar.png");
	}
}
