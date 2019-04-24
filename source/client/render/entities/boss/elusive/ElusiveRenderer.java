package net.tslat.aoa3.client.render.entities.boss.elusive;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.elusive.ModelElusive;
import net.tslat.aoa3.entity.boss.elusive.EntityElusive;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ElusiveRenderer extends RenderLiving<EntityElusive> {
	private final ResourceLocation texture;

	public ElusiveRenderer(RenderManager renderManager, ResourceLocation texture) {
		super(renderManager, new ModelElusive(), EntityElusive.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	public void doRender(EntityElusive entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityElusive elusive) {
		return texture;
	}
}