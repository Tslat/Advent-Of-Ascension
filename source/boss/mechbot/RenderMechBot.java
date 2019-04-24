package net.nevermine.boss.mechbot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.nevermine.resource.boss.EternalBossStatus;

@SideOnly(value = Side.CLIENT)
public class RenderMechBot extends RenderLiving {
	private static final ResourceLocation EntityTexture = new ResourceLocation("nevermine:textures/mobs/mechBot.png");
	protected modelMechBot model;

	public RenderMechBot(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = (modelMechBot)mainModel;
	}

	public void renderMechBot(EntityMechBot var1, double var2, double var4, double var6, float var8, float var9) {
		EternalBossStatus.setBossStatus(var1, true, 44);
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
		renderMechBot((EntityMechBot)var1, var2, var4, var6, var8, var9);
	}

	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		renderMechBot((EntityMechBot)var1, var2, var4, var6, var8, var9);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		return EntityTexture;
	}
}

