package net.tslat.aoa3.client.render.entities.boss.coniferon;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.coniferon.ModelConiferon;
import net.tslat.aoa3.entity.boss.coniferon.EntityConiferon;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ConiferonRenderer extends RenderLiving<EntityConiferon> {
	private final ResourceLocation texture;

	public ConiferonRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelConiferon(), EntityConiferon.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	protected void preRenderCallback(EntityConiferon entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(1.5f, 1.5f, 1.5f);
	}

	@Override
	public void doRender(EntityConiferon entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (x + y + z != 0)
			BossBarRenderer.boss = entity;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityConiferon coniferon) {
		return texture;
	}
}