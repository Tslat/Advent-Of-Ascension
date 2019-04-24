package net.tslat.aoa3.client.render.entities.boss.silverfoot;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.silverfoot.ModelSilverfoot;
import net.tslat.aoa3.entity.boss.silverfoot.EntitySilverfoot;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class SilverfootRenderer extends RenderLiving<EntitySilverfoot> {
	private final ResourceLocation texture;

	public SilverfootRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelSilverfoot(), EntitySilverfoot.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	public void doRender(EntitySilverfoot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntitySilverfoot Silverfoot) {
		return texture;
	}
}