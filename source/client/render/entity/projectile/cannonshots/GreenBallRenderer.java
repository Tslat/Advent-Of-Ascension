package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.tslat.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;

public class GreenBallRenderer extends TexturedProjectileRenderer<ThrowableProjectile> {
	private final float scale;

	public GreenBallRenderer(final EntityRendererProvider.Context manager, final float scale, final ResourceLocation textureResource) {
		super(manager, textureResource);

		this.scale = scale;
	}

	@Override
	public void render(ThrowableProjectile entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		matrix.scale(scale, scale, scale);

		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);
	}
}