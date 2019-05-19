package net.tslat.aoa3.client.render.entities.boss.voxxulon;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.voxxulon.ModelVoxxulon;
import net.tslat.aoa3.entity.boss.voxxulon.EntityVoxxulon;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class VoxxulonRenderer extends RenderLiving<EntityVoxxulon> {
	private final ResourceLocation texture;

	public VoxxulonRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelVoxxulon(), EntityVoxxulon.entityWidth / 3);
		texture = resource;
	}

	@Override
	protected void preRenderCallback(EntityVoxxulon entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(2, 2, 2);
	}

	@Override
	public void doRender(EntityVoxxulon entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (x + y + z != 0)
			BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityVoxxulon entity) {
		return texture;
	}
}