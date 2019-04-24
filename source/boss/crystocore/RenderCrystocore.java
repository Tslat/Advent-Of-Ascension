package net.nevermine.boss.crystocore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.nevermine.resource.boss.EternalBossStatus;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderCrystocore extends RenderLiving {
	private static final ResourceLocation EntityTexture1;
	private static final ResourceLocation EntityTexture2;
	private static final ResourceLocation EntityTexture3;
	private static final ResourceLocation EntityTexture4;
	private static final ResourceLocation EntityTexture5;
	private static final ResourceLocation EntityTexture6;
	protected modelCrystocore model;
	private float scale;

	public RenderCrystocore(final ModelBase par1ModelBase, final float par2) {
		super(par1ModelBase, par2);
		model = (modelCrystocore)mainModel;
		scale = 2.0f;
	}

	protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
		preRenderCallback((EntityCrystocore)par1EntityLivingBase, par2);
	}

	public void renderCrystocore(final EntityCrystocore var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		EternalBossStatus.setBossStatus(var1, true, 38);
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final EntityLiving var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderCrystocore((EntityCrystocore)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(final Entity var1, final double var2, final double var4, final double var6, final float var8, final float var9) {
		renderCrystocore((EntityCrystocore)var1, var2, var4, var6, var8, var9);
	}

	protected void preRenderCallback(final EntityCrystocore par1EntityVoliant, final float par2) {
		GL11.glScalef(scale, scale, scale);
	}

	protected ResourceLocation getEntityTexture(final Entity entity) {
		final EntityCrystocore thisentity = (EntityCrystocore)entity;
		final int state = thisentity.getState();
		if (state == 1) {
			return RenderCrystocore.EntityTexture1;
		}
		if (state == 2) {
			return RenderCrystocore.EntityTexture2;
		}
		if (state == 3) {
			return RenderCrystocore.EntityTexture3;
		}
		if (state == 4) {
			return RenderCrystocore.EntityTexture4;
		}
		if (state == 5) {
			return RenderCrystocore.EntityTexture5;
		}
		return RenderCrystocore.EntityTexture6;
	}

	static {
		EntityTexture1 = new ResourceLocation("nevermine:textures/mobs/crystocoreGreen.png");
		EntityTexture2 = new ResourceLocation("nevermine:textures/mobs/crystocorePurple.png");
		EntityTexture3 = new ResourceLocation("nevermine:textures/mobs/crystocoreYellow.png");
		EntityTexture4 = new ResourceLocation("nevermine:textures/mobs/crystocoreRed.png");
		EntityTexture5 = new ResourceLocation("nevermine:textures/mobs/crystocoreWhite.png");
		EntityTexture6 = new ResourceLocation("nevermine:textures/mobs/crystocoreBlue.png");
	}
}
