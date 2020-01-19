package net.tslat.aoa3.client.render.entities.boss.hydrolisk;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.hydrolisk.ModelHydrolisk;
import net.tslat.aoa3.entity.boss.hydrolisk.EntityHydrolisk;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class HydroliskRenderer extends RenderLiving<EntityHydrolisk> {
	private final ResourceLocation texture;
	private final ResourceLocation shieldedTexture;

	public HydroliskRenderer(RenderManager renderManager, ResourceLocation texture, ResourceLocation shieldedTexture) {
		super(renderManager, new ModelHydrolisk(), EntityHydrolisk.entityWidth / 3);
		this.texture = texture;
		this.shieldedTexture = shieldedTexture;
	}

	@Override
	protected void preRenderCallback(EntityHydrolisk hydrolisk, float partialTickTime) {
		GlStateManager.scale(2f, 2f, 2f);
	}

	@Override
	public void doRender(EntityHydrolisk entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		if (x + y + z != 0)
			BossBarRenderer.boss = entity;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityHydrolisk hydrolisk) {
		return hydrolisk.isShielded() ? shieldedTexture : texture;
	}
}