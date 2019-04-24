package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.overworld.ghostly.modelGhostlyNightReaper;

@SideOnly(Side.CLIENT)
public class RenderGhostlyNightReaper extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelGhostlyNightReaper model;

	public RenderGhostlyNightReaper(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelGhostlyNightReaper)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderGhostlyNightReaper.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/nightreaperGhostly.png");
	}
}
