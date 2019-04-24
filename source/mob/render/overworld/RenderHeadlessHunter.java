package net.nevermine.mob.render.overworld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.overworld.modelHeadlessHunter;

@SideOnly(Side.CLIENT)
public class RenderHeadlessHunter extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelHeadlessHunter model;

	public RenderHeadlessHunter(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelHeadlessHunter)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderHeadlessHunter.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/headlessHunter.png");
	}
}
