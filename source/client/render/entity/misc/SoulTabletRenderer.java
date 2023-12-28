/*
package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.tslat.aoa3.content.entity.tablet.SoulTabletEntity;

import java.util.Random;

public class SoulTabletRenderer extends EntityRenderer<Entity> {
	private static final Random particleRand = new Random();

	private final ResourceLocation texture;
	private final EntityModel<Entity> model;
	@Nullable
	private final ParticleOptions particleData;

	public SoulTabletRenderer(EntityRendererProvider.Context renderManager, EntityModel<Entity> model, ResourceLocation texture, @Nullable ParticleOptions particleData) {
		super(renderManager);
		this.texture = texture;
		this.model = model;
		this.particleData = particleData;
	}

	@Override
	public void render(Entity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		matrix.pushPose();
		matrix.translate(0, 1.5d, 0);
		matrix.mulPose(Vector3f.XP.rotationDegrees(180));
		matrix.scale(-1, 1, -1);

		IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));

		model.setupAnim(entity, 0, 0, entity.tickCount, entityYaw, 0);
		matrix.mulPose(Vector3f.YP.rotationDegrees(entityYaw));
		model.renderToBuffer(matrix, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

		matrix.popPose();

		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		if (particleData != null && !Minecraft.getInstance().isPaused()) {
			SoulTabletEntity tablet = (SoulTabletEntity)entity;

			if (tablet.isActive()) {
				int radius = tablet.getRelevantItem().getEffectRadius();

				tablet.level.addParticle(particleData, entity.getX() + (particleRand.nextFloat() * radius * 2) - radius, entity.getY() + (particleRand.nextFloat() * radius * 2) - radius, entity.getZ() + (particleRand.nextFloat() * radius * 2) - radius, 0, 0, 0);
			}
		}
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(Entity entity) {
		return texture;
	}
}
*/
