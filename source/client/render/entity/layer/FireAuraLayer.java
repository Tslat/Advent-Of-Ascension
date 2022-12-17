package net.tslat.aoa3.client.render.entity.layer;

import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.mob.nether.NethengeicBeastEntity;
import net.tslat.aoa3.util.RenderUtil;
import org.joml.Matrix4f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import javax.annotation.Nullable;

public class FireAuraLayer extends GeoRenderLayer<NethengeicBeastEntity> {
	protected static final ResourceLocation AURA_TEXTURE = AdventOfAscension.id("textures/entity/misc/fire_aura.png");

	public FireAuraLayer(GeoRenderer<NethengeicBeastEntity> baseRenderer) {
		super(baseRenderer);
	}

	@Override
	public void render(PoseStack poseStack, NethengeicBeastEntity entity, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
		ResourceLocation texture = getLayerTexture(entity, partialTick);

		if (texture == null)
			return;

		float lerpedTicks = (float)entity.tickCount + partialTick;
		RenderType layerRenderType = RenderType.energySwirl(texture, adjustU(lerpedTicks) % 1, lerpedTicks * 0.005F % 1);

		float centerX = (float)entity.getX(0.5f);
		float centerY = (float)entity.getY(0.5f);
		float centerZ = (float)entity.getZ(0.5f);
		float radius = Math.max(entity.getBbWidth(), entity.getBbHeight()) * 1.25f;
		BufferBuilder buff = Tesselator.getInstance().getBuilder();
		poseStack.pushPose();
		poseStack.translate(entity.getX(), entity.getY(), entity.getZ());
		Matrix4f matrix = poseStack.last().pose();

		RenderUtil.prepRenderTexture(texture);
		buff.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

		for (Direction direction : Direction.values()) {
			Vec3i normal = direction.getNormal();
			int xOffset = normal.getX() * 1;
			int yOffset = normal.getY() * 1;
			int zOffset = normal.getZ() * 1;

			buff.vertex(matrix, centerX - xOffset, centerY + yOffset, centerZ - zOffset).uv(0, 32).color(1, 1, 1, 1).endVertex();
			buff.vertex(matrix, centerX + xOffset, centerY + yOffset, centerZ + zOffset).uv(32, 32).color(1, 1, 1, 1).endVertex();
			buff.vertex(matrix, centerX + xOffset, centerY - yOffset, centerZ + zOffset).uv(32, 0).color(1, 1, 1, 1).endVertex();
			buff.vertex(matrix, centerX - xOffset, centerY - yOffset, centerZ - zOffset).uv(0, 0).color(1, 1, 1, 1).endVertex();
		}

		BufferUploader.drawWithShader(buff.end());
		poseStack.popPose();
	}

	@Nullable
	protected ResourceLocation getLayerTexture(NethengeicBeastEntity entity, float partialTicks) {
		return entity.hasAura() || true ? AURA_TEXTURE : null;
	}

	protected float adjustU(float tick) {
		return tick * 0.005f;
	}
}
