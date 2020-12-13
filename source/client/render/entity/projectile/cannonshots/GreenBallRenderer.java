package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;

public class GreenBallRenderer extends TexturedProjectileRenderer<ThrowableEntity> {
	private final float scale;

	public GreenBallRenderer(final EntityRendererManager manager, final float scale, final ResourceLocation textureResource) {
		super(manager, textureResource);

		this.scale = scale;
	}

	@Override
	public void render(ThrowableEntity entity, float entityYaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		matrix.scale(scale, scale, scale);

		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);
	}
}
