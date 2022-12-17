package net.tslat.aoa3.client.render.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.tslat.aoa3.content.block.tileentity.BossAltarTileEntity;

public class BossAltarTileEntityRenderer implements BlockEntityRenderer<BossAltarTileEntity> {
	public BossAltarTileEntityRenderer(BlockEntityRendererProvider.Context context) {}

	@Override
	public void render(BossAltarTileEntity blockEntity, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
		Entity entity = blockEntity.getCachedEntity();

		if (entity != null) {
			matrix.pushPose();
			matrix.translate(0.5f, 0, 0.5f);

			float scale = 0.53125F;
			float maxScale = Math.max(entity.getBbWidth(), entity.getBbHeight());

			if (maxScale > 1.0D)
				scale /= maxScale;

			matrix.translate(0, -0.1, 0);
			matrix.scale(scale, scale, scale);
			matrix.translate(0, (1 / scale), 0);
			matrix.mulPose(Axis.YP.rotationDegrees(entity.level.getGameTime() % 360 - 90));

			Minecraft.getInstance().getEntityRenderDispatcher().render(entity, 0, 0, 0, 0, 0, matrix, buffer, combinedLight);
			matrix.popPose();

			if (partialTicks > 0.5f) {
				BlockPos pos = blockEntity.getBlockPos();
				RandomSource rand = blockEntity.getLevel().getRandom();

				blockEntity.getLevel().addParticle(ParticleTypes.GLOW, pos.getX() - 3 + rand.nextFloat() * 7, pos.getY() + 0.2, pos.getZ() - 3 + rand.nextFloat() * 7, 0, 0, 0);
			}
		}
	}
}