package net.nevermine.boss.nethengeicwither;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.nevermine.resource.boss.EternalBossStatus;

@SideOnly(Side.CLIENT)
public class RenderNethengeicWither extends RenderLiving {
	private static final ResourceLocation EntityTexture1;
	private static final ResourceLocation EntityTexture2;
	private static final ResourceLocation EntityTexture3;
	protected modelNethengeicWither model;

	public RenderNethengeicWither(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelNethengeicWither)mainModel;
	}

	public void renderNethengeicWither(final EntityNethengeicWither var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		EternalBossStatus.setBossStatus(var1, true, 3);
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final EntityLiving var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderNethengeicWither((EntityNethengeicWither)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final Entity var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderNethengeicWither((EntityNethengeicWither)var1, var2, var4, var6, var8, var9);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		final EntityNethengeicWither thisentity = (EntityNethengeicWither)entity;
		final float curhp = thisentity.getform();
		if (curhp == 1.0f) {
			return RenderNethengeicWither.EntityTexture1;
		}
		if (curhp == 2.0f) {
			return RenderNethengeicWither.EntityTexture2;
		}
		return RenderNethengeicWither.EntityTexture3;
	}

	static {
		EntityTexture1 = new ResourceLocation("nevermine:textures/mobs/nethengeicwither.png");
		EntityTexture2 = new ResourceLocation("nevermine:textures/mobs/nethengeicwitherEnraged.png");
		EntityTexture3 = new ResourceLocation("nevermine:textures/mobs/nethengeicwitherCataclysmic.png");
	}
}
