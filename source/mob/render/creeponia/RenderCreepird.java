package net.nevermine.mob.render.creeponia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.creeponia.modelCreepird;

@SideOnly(Side.CLIENT)
public class RenderCreepird extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelCreepird model;

	public RenderCreepird(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelCreepird)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderCreepird.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/creepird.png");
	}
}
