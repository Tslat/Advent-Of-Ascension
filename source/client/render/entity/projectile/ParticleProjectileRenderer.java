package net.tslat.aoa3.client.render.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public abstract class ParticleProjectileRenderer<T extends Entity> extends EntityRenderer<T> {
	public ParticleProjectileRenderer(final EntityRendererProvider.Context renderManager) {
		super(renderManager);
	}

	@Override
	public void render(T entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
		addParticles(entity, partialTicks);

		super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
	}

	protected abstract void addParticles(T entity, float partialTicks);

	@Override
	public final ResourceLocation getTextureLocation(T entity) {
		return null;
	}
}
