package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.object.entity.projectile.staff.DestructionShotEntity;

import javax.annotation.Nullable;

public class DestructionShotRenderer extends EntityRenderer<DestructionShotEntity> {
	private final ResourceLocation texture;

	public DestructionShotRenderer(final EntityRendererManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(DestructionShotEntity entity) {
		return texture;
	}
}
