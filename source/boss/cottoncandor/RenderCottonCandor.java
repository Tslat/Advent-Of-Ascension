package net.nevermine.boss.cottoncandor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.nevermine.resource.boss.EternalBossStatus;

@SideOnly(Side.CLIENT)
public class RenderCottonCandor extends RenderLiving {
	private static final ResourceLocation EntityTexture1;
	private static final ResourceLocation EntityTexture2;
	private static final ResourceLocation EntityTexture3;
	private static final ResourceLocation EntityTexture4;
	private static final ResourceLocation EntityTexture5;
	private static final ResourceLocation EntityTexture6;
	protected modelCottonCandor model;

	public RenderCottonCandor(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelCottonCandor)mainModel;
	}

	public void renderCottonCandor(final EntityCottonCandor var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		EternalBossStatus.setBossStatus(var1, true, 39);
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final EntityLiving var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderCottonCandor((EntityCottonCandor)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final Entity var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderCottonCandor((EntityCottonCandor)var1, var2, var4, var6, var8, var9);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		final EntityCottonCandor thisentity = (EntityCottonCandor)entity;
		final int state = thisentity.getState();
		if (state == 1) {
			return RenderCottonCandor.EntityTexture1;
		}
		if (state == 2) {
			return RenderCottonCandor.EntityTexture2;
		}
		if (state == 3) {
			return RenderCottonCandor.EntityTexture3;
		}
		if (state == 4) {
			return RenderCottonCandor.EntityTexture4;
		}
		if (state == 5) {
			return RenderCottonCandor.EntityTexture5;
		}
		return RenderCottonCandor.EntityTexture6;
	}

	static {
		EntityTexture1 = new ResourceLocation("nevermine:textures/mobs/cottonCandorWind.png");
		EntityTexture2 = new ResourceLocation("nevermine:textures/mobs/cottonCandorWater.png");
		EntityTexture3 = new ResourceLocation("nevermine:textures/mobs/cottonCandorFire.png");
		EntityTexture4 = new ResourceLocation("nevermine:textures/mobs/cottonCandorPoison.png");
		EntityTexture5 = new ResourceLocation("nevermine:textures/mobs/cottonCandorWither.png");
		EntityTexture6 = new ResourceLocation("nevermine:textures/mobs/cottonCandorLunar.png");
	}
}
