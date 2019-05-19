package net.tslat.aoa3.client.render.entities.boss.graw;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.graw.ModelGraw;
import net.tslat.aoa3.entity.boss.graw.EntityGraw;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class GrawRenderer extends RenderLiving<EntityGraw> {
	private final ResourceLocation texture;

	public GrawRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelGraw(), EntityGraw.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	protected void preRenderCallback(EntityGraw graw, float partialTickTime) {
		GlStateManager.scale(3, 3, 3);
	}

	@Override
	public void doRender(EntityGraw entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (x + y + z != 0)
			BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityGraw graw) {
		return texture;
	}
}