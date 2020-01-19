package net.tslat.aoa3.client.render.entities.boss.cindaxas;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.boss.cindaxas.ModelCindaxas;
import net.tslat.aoa3.entity.boss.cindaxas.EntityCindaxas;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class CindaxasRenderer extends RenderLiving<EntityCindaxas> {
	private final ResourceLocation texture;

	public CindaxasRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelCindaxas(), EntityCindaxas.entityWidth / 3);
		texture = resource;
	}

	@Override
	public void doRender(EntityCindaxas entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		/*if (x + y + z != 0)
			BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();*/
	}

	@Override
	protected void preRenderCallback(EntityCindaxas entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(4f, 4f, 4f);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityCindaxas entity) {
		return texture;
	}
}