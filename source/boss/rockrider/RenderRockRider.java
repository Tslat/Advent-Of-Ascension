package net.nevermine.boss.rockrider;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.nevermine.resource.boss.EternalBossStatus;

@SideOnly(Side.CLIENT)
public class RenderRockRider extends RenderLiving {
	private static final ResourceLocation EntityTexture1;
	private static final ResourceLocation EntityTexture2;
	protected modelRockRider model;

	public RenderRockRider(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelRockRider)mainModel;
	}

	public void renderRockRider(final EntityRockRider var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		EternalBossStatus.setBossStatus(var1, true, 4);
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final EntityLiving var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderRockRider((EntityRockRider)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final Entity var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderRockRider((EntityRockRider)var1, var2, var4, var6, var8, var9);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		if (EntityRockRider.protType == 1) {
			return RenderRockRider.EntityTexture2;
		}
		return RenderRockRider.EntityTexture1;
	}

	static {
		EntityTexture1 = new ResourceLocation("nevermine:textures/mobs/rockrider.png");
		EntityTexture2 = new ResourceLocation("nevermine:textures/mobs/rockriderAlternate.png");
	}
}
