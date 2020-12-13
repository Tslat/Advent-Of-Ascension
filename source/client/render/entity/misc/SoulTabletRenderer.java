package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.entity.tablet.SoulTabletEntity;

import javax.annotation.Nullable;
import java.util.Random;

public class SoulTabletRenderer extends EntityRenderer<Entity> {
	private static final Random particleRand = new Random();

	private final ResourceLocation texture;
	private final EntityModel<Entity> model;
	@Nullable
	private final IParticleData particleData;

	public SoulTabletRenderer(EntityRendererManager renderManager, EntityModel<Entity> model, ResourceLocation texture, @Nullable IParticleData particleData) {
		super(renderManager);
		this.texture = texture;
		this.model = model;
		this.particleData = particleData;
	}

	@Override
	public void render(Entity entity, float entityYaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		matrix.push();
		matrix.translate(0, 1.5d, 0);
		matrix.rotate(Vector3f.XP.rotationDegrees(180));
		matrix.scale(-1, 1, -1);

		IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.getEntityCutoutNoCull(texture));

		model.setRotationAngles(entity, 0, 0, entity.ticksExisted, entityYaw, 0);
		matrix.rotate(Vector3f.YP.rotationDegrees(entityYaw));
		model.render(matrix, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

		matrix.pop();

		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		if (particleData != null && !Minecraft.getInstance().isGamePaused()) {
			SoulTabletEntity tablet = (SoulTabletEntity)entity;

			if (tablet.isActive()) {
				int radius = tablet.getRelevantItem().getEffectRadius();

				tablet.world.addParticle(particleData, entity.getPosX() + (particleRand.nextFloat() * radius * 2) - radius, entity.getPosY() + (particleRand.nextFloat() * radius * 2) - radius, entity.getPosZ() + (particleRand.nextFloat() * radius * 2) - radius, 0, 0, 0);
			}
		}
	}

	@Nullable
	@Override
	public ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
