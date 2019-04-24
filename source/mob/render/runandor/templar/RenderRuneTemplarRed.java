package net.nevermine.mob.render.runandor.templar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.nevermine.mob.entity.runandor.templar.EntityRuneTemplarRed;
import net.nevermine.mob.model.runandor.modelRuneTemplar;
import net.nevermine.resource.boss.EternalBossStatus;

@SideOnly(Side.CLIENT)
public class RenderRuneTemplarRed extends RenderLiving {
	private static final ResourceLocation EntityTexture;
	protected modelRuneTemplar model;

	public RenderRuneTemplarRed(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelRuneTemplar)mainModel;
	}

	public void renderBane(final EntityRuneTemplarRed var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		EternalBossStatus.setBossStatus(var1, true, 29);
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final EntityLiving var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderBane((EntityRuneTemplarRed)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final Entity var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderBane((EntityRuneTemplarRed)var1, var2, var4, var6, var8, var9);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		return RenderRuneTemplarRed.EntityTexture;
	}

	static {
		EntityTexture = new ResourceLocation("nevermine:textures/mobs/runeTemplarRed.png");
	}
}
