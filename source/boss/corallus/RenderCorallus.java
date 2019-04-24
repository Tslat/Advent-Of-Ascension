package net.nevermine.boss.corallus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.nevermine.resource.boss.EternalBossStatus;

@SideOnly(Side.CLIENT)
public class RenderCorallus extends RenderLiving {
	private static final ResourceLocation EntityTexture1;
	private static final ResourceLocation EntityTexture2;
	protected modelCorallus model;

	public RenderCorallus(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelCorallus)mainModel;
	}

	public void renderCorallus(final EntityCorallus var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		EternalBossStatus.setBossStatus(var1, true, 12);
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final EntityLiving var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderCorallus((EntityCorallus)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final Entity var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderCorallus((EntityCorallus)var1, var2, var4, var6, var8, var9);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		final EntityCorallus thisentity = (EntityCorallus)entity;
		if (thisentity.isEnraged() == 1) {
			return RenderCorallus.EntityTexture2;
		}
		return RenderCorallus.EntityTexture1;
	}

	static {
		EntityTexture1 = new ResourceLocation("nevermine:textures/mobs/corallus.png");
		EntityTexture2 = new ResourceLocation("nevermine:textures/mobs/corallusAlternate.png");
	}
}
