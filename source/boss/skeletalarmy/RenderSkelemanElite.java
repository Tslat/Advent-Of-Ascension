package net.nevermine.boss.skeletalarmy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderSkelemanElite extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelSkelemanElite model;

	public RenderSkelemanElite(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelSkelemanElite)mainModel;
	}

	public void renderSkelemanElite(final EntitySkelemanElite var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final EntityLiving var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderSkelemanElite((EntitySkelemanElite)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final Entity var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderSkelemanElite((EntitySkelemanElite)var1, var2, var4, var6, var8, var9);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderSkelemanElite.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/skeleman.png");
	}
}
