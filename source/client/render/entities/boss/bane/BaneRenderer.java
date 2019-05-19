package net.tslat.aoa3.client.render.entities.boss.bane;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.bane.ModelBane;
import net.tslat.aoa3.entity.boss.bane.EntityBane;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class BaneRenderer extends RenderLiving<EntityBane> {
	private final ResourceLocation texture;

	public BaneRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelBane(), EntityBane.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	public void doRender(EntityBane entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (x + y + z != 0)
			BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityBane corallus) {
		return texture;
	}
}