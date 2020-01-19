package net.tslat.aoa3.client.render.entities.boss.kror;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.kror.ModelKror;
import net.tslat.aoa3.entity.boss.kror.EntityKror;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class KrorRenderer extends RenderLiving<EntityKror> {
	private final ResourceLocation texture;

	public KrorRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelKror(), EntityKror.entityWidth / 3);
		texture = resource;
	}

	@Override
	protected void preRenderCallback(EntityKror entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(2, 2, 2);
	}

	@Override
	public void doRender(EntityKror entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		if (x + y + z != 0)
			BossBarRenderer.boss = entity;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityKror entity) {
		return texture;
	}
}