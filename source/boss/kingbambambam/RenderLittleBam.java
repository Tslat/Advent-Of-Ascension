package net.nevermine.boss.kingbambambam;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderLittleBam extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelLittleBam model;

	public RenderLittleBam(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelLittleBam)mainModel;
	}

	public void renderLittleBam(final EntityLiving par1EntityLiving, final double par2, final double par4, final double par6, final float par8, final float par9) {
		renderLittleBam(par1EntityLiving, par2, par4, par6, par8, par9);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderLittleBam.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/littlebam.png");
	}
}
