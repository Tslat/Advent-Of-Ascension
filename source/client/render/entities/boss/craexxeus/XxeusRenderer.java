package net.tslat.aoa3.client.render.entities.boss.craexxeus;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.craexxeus.ModelXxeus;
import net.tslat.aoa3.entity.boss.craexxeus.EntityXxeus;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class XxeusRenderer extends RenderLiving<EntityXxeus> {
	private final ResourceLocation texture;

	public XxeusRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelXxeus(), EntityXxeus.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	protected void preRenderCallback(EntityXxeus entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(1.5f, 1.5f, 1.5f);
	}

	@Override
	public void doRender(EntityXxeus entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (x + y + z != 0)
			BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityXxeus xxeus) {
		return texture;
	}
}