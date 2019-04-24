package net.nevermine.mob.render.abyss;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.model.abyss.modelBloodsucker;

@SideOnly(Side.CLIENT)
public class RenderBloodsucker extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelBloodsucker model;

	public RenderBloodsucker(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelBloodsucker)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderBloodsucker.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/bloodsucker.png");
	}
}
