package net.nevermine.boss.craexxeus;

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
public class RenderXxeus extends RenderLiving {
	private static final ResourceLocation EntityTexture = new ResourceLocation("nevermine:textures/mobs/craexxeus.png");
	protected modelXxeus model;
	private float scale;

	public RenderXxeus(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		this.model = ((modelXxeus)this.mainModel);
		this.scale = 1.5F;
	}

	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
		preRenderCallback((EntityXxeus)par1EntityLivingBase, par2);
	}

	public void renderXxeus(EntityXxeus var1, double var2, double var4, double var6, float var8, float var9) {
		EternalBossStatus.setBossStatus(var1, true, 42);
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
		renderXxeus((EntityXxeus)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		renderXxeus((EntityXxeus)var1, var2, var4, var6, var8, var9);
	}

	protected void preRenderCallback(EntityXxeus par1EntityVoliant, float par2) {
		GL11.glScalef(this.scale, this.scale, this.scale);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		return EntityTexture;
	}
}
