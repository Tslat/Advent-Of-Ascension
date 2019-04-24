package net.tslat.aoa3.client.render.entities.boss.goldorth;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.goldorth.ModelGoldorth;
import net.tslat.aoa3.entity.boss.goldorth.EntityGoldorth;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class GoldorthRenderer extends RenderLiving<EntityGoldorth> {
	private final ResourceLocation texture;

	public GoldorthRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelGoldorth(), EntityGoldorth.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	protected void preRenderCallback(EntityGoldorth entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(1.2f, 1.2f, 1.2f);
	}

	@Override
	public void doRender(EntityGoldorth entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		BossBarRenderer.boss = entity;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityGoldorth goldorth) {
		return texture;
	}
}