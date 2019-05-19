package net.tslat.aoa3.client.render.entities.boss.penumbra;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.penumbra.ModelPenumbra;
import net.tslat.aoa3.entity.boss.penumbra.EntityPenumbra;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class PenumbraRenderer extends RenderLiving<EntityPenumbra> {
	private final ResourceLocation texture;

	public PenumbraRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelPenumbra(), EntityPenumbra.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	protected void preRenderCallback(EntityPenumbra entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(1.5f, 1.5f, 1.5f);
	}

	@Override
	public void doRender(EntityPenumbra entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (x + y + z != 0)
			BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityPenumbra Penumbra) {
		return texture;
	}
}