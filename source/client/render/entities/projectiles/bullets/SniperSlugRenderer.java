package net.tslat.aoa3.client.render.entities.projectiles.bullets;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.entity.projectiles.gun.EntitySniperSlug;

import javax.annotation.Nullable;

public class SniperSlugRenderer extends Render<EntitySniperSlug> {
	private final ResourceLocation texture;

	public SniperSlugRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntitySniperSlug entity, double x, double y, double z, float entityYaw, float partialTicks) {}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntitySniperSlug entity) {
		return texture;
	}
}
