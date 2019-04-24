package net.nevermine.mob.render.immortallis;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.immortallis.modelGhastus;

@SideOnly(Side.CLIENT)
public class RenderGhastus extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelGhastus model;

	public RenderGhastus(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelGhastus)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderGhastus.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/ghastus.png");
	}
}
