package net.nevermine.boss.immortal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderProshield extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelProshield model;

	public RenderProshield(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelProshield)mainModel;
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderProshield.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/immortalDefender.png");
	}
}
