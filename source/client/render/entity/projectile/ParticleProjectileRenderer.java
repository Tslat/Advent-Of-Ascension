package net.tslat.aoa3.client.render.entity.projectile;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public abstract class ParticleProjectileRenderer<T extends Entity> extends EntityRenderer<T> {
	public ParticleProjectileRenderer(final EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public void render(T entity, float yaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		addParticles(entity, partialTicks);

		super.render(entity, yaw, partialTicks, matrix, buffer, packedLight);
	}

	protected abstract void addParticles(T entity, float partialTicks);

	@Override
	public final ResourceLocation getTextureLocation(T entity) {
		return null;
	}
}
