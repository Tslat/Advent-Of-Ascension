package net.tslat.aoa3.client.render.entities.boss.rockrider;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.rockrider.ModelRockRider;
import net.tslat.aoa3.entity.boss.rockrider.EntityRockRider;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RockRiderRenderer extends RenderLiving<EntityRockRider> {
	private final ResourceLocation texture;
	private final ResourceLocation altTexture;

	public RockRiderRenderer(RenderManager renderManager, final ResourceLocation texture, final ResourceLocation alternateTexture) {
		super(renderManager, new ModelRockRider(), EntityRockRider.entityWidth / 3);
		this.texture = texture;
		this.altTexture = alternateTexture;
	}

	@Override
	protected void preRenderCallback(EntityRockRider entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(1.5f, 1.5f, 1.5f);
	}

	@Override
	public void doRender(EntityRockRider entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (x + y + z != 0)
			BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityRockRider rockRider) {
		return rockRider.isAlternateForm() ? altTexture : texture;
	}
}