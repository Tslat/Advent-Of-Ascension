package net.tslat.aoa3.client.render.entities.boss.horon;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.horon.ModelHoron;
import net.tslat.aoa3.entity.boss.horon.EntityHoron;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class HoronRenderer extends RenderLiving<EntityHoron> {
	private final ResourceLocation texture;

	public HoronRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelHoron(), EntityHoron.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	protected void preRenderCallback(EntityHoron entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(1.2f, 1.2f, 1.2f);
	}

	@Override
	public void doRender(EntityHoron entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		BossBarRenderer.boss = entity;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityHoron Horon) {
		return texture;
	}
}