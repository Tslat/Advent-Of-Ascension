package net.tslat.aoa3.client.render.entities.projectiles.bullets;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.entity.projectiles.gun.EntityDischargeSlug;

import javax.annotation.Nullable;

public class DischargeSlugRenderer extends Render<EntityDischargeSlug> {
	private final ResourceLocation texture;

	public DischargeSlugRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityDischargeSlug entity, double x, double y, double z, float entityYaw, float partialTicks) {}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityDischargeSlug entity) {
		return texture;
	}
}
