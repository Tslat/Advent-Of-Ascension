package net.nevermine.mob.render.haven;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.haven.modelHalycon;

@SideOnly(Side.CLIENT)
public class RenderHalycon extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelHalycon model;

	public RenderHalycon(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelHalycon)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderHalycon.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/halycon.png");
	}
}
