package net.nevermine.boss.corallus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderCorallusShot extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelCorallusShot model;

	public RenderCorallusShot(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelCorallusShot)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderCorallusShot.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/corallusshot.png");
	}
}
