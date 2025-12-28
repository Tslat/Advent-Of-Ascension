package net.tslat.aoa3.client.render.entity.projectile.bullets;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class SniperSlugRenderer<T extends Entity> extends EntityRenderer<T> {
	public SniperSlugRenderer(final EntityRendererProvider.Context renderManager) {
		super(renderManager);
	}

	@Override
	public final ResourceLocation getTextureLocation(T entity) {
		return null;
	}
}