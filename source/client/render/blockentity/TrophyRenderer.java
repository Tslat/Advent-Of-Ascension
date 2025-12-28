package net.tslat.aoa3.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.content.block.blockentity.TrophyBlockEntity;
import net.tslat.tme.api.particle.ParticleBuilder;

public class TrophyRenderer implements BlockEntityRenderer<TrophyBlockEntity> {
	public TrophyRenderer(BlockEntityRendererProvider.Context context) {}

	@Override
	public void render(TrophyBlockEntity blockEntity, float partialTick, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
		Entity entity = blockEntity.getCachedEntity();

		if (entity != null) {
			matrix.pushPose();
			matrix.translate(0.5f, 0, 0.5f);

			float scale = 0.53125F;
			float maxScale = Math.max(entity.getBbWidth(), entity.getBbHeight());
			double hover = (Mth.sin((float)blockEntity.hoverStep + partialTick) / 30d);
			blockEntity.hoverStep += hover * 0.01f;
			RandomSource random = entity.level().getRandom();

			if (maxScale > 1.0D)
				scale /= maxScale;

			matrix.translate(0, -0.1, 0);
			matrix.scale(scale, scale, scale);
			matrix.translate(0, (1 / scale), 0);

			if (blockEntity.getTrophyData().isOriginalTrophy() && !Minecraft.getInstance().isPaused() && random.nextInt(5) == 0) {
				float colourMod = random.nextFloat() * 0.7f + 0.3f;
				Vec3 pos = Vec3.atBottomCenterOf(blockEntity.getBlockPos()).add(random.nextGaussian() * (entity.getBbWidth() / 2f) * scale * 0.75f, 0.9f + random.nextFloat() * entity.getBbHeight() * scale, random.nextGaussian() * (entity.getBbWidth() / 2f) * scale * 0.75f);

				ParticleBuilder.forPositions(ParticleTypes.GLOW, pos)
						.scaleMod(0.05f)
						.lifespan(Mth.ceil(10 * (random.nextFloat() * 0.8f + 0.2f)))
						.colourTint(colourMod, colourMod * 200 / 255f, 0, 1f)
						.power(Vec3.ZERO)
						.spawnClientParticles(entity.level());
			}

			if (AoAConfigs.CLIENT.rotatingTrophies.get())
				matrix.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, blockEntity.getPrevMobRotation(), blockEntity.getMobRotation()) * 30.0F));

			Minecraft.getInstance().getEntityRenderDispatcher().render(entity, 0, 0, 0, 0, 0, matrix, buffer, combinedLight);
			matrix.popPose();
		}
	}
}