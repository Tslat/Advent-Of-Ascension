package net.nevermine.mob.render.creeponia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderCreepCow extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected ModelCow model;

	public RenderCreepCow(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (ModelCow)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderCreepCow.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/creepCow.png");
	}
}
