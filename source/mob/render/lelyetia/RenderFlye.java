package net.nevermine.mob.render.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.lelyetia.modelFlye;

@SideOnly(Side.CLIENT)
public class RenderFlye extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelFlye model;

	public RenderFlye(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelFlye)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderFlye.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/flye.png");
	}
}
