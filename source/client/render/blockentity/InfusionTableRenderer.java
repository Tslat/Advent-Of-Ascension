package net.tslat.aoa3.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.block.blockentity.InfusionTableBlockEntity;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.smartbrainlib.util.RandomUtil;

public class
InfusionTableRenderer implements BlockEntityRenderer<InfusionTableBlockEntity> {
	public InfusionTableRenderer(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(InfusionTableBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
		final NonNullList<ItemStack> contents = blockEntity.getContents();
		final ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
		final Level level = blockEntity.getLevel();
		final BlockPos pos = blockEntity.getBlockPos();
		final BlockPos abovePos = pos.above();

		if (level != null) {
			if (level.getBlockState(abovePos).isSolidRender(level, abovePos))
				return;

			combinedLight = LevelRenderer.getLightColor(level, abovePos);
		}
		else {
			combinedLight = LightTexture.FULL_BRIGHT;
		}

		poseStack.pushPose();

		poseStack.scale(0.25f, 0.25f, 0.25f);
		poseStack.translate(1.25f, 4.25f, 1.25f);
		poseStack.mulPose(Axis.YP.rotationDegrees(180f));

		ItemStack input = contents.get(0);
		ItemStack output = contents.get(10);

		if (!input.isEmpty()) {
			poseStack.pushPose();
			poseStack.translate(-0.75f, 1.25f, -0.75f);
			poseStack.mulPose(Axis.YP.rotationDegrees(level.getGameTime() + partialTick));
			poseStack.scale(1.25f, 1.25f, 1.25f);

			if (!(input.getItem() instanceof BlockItem))
				poseStack.scale(0.5f, 0.5f, 0.5f);

			renderer.renderStatic(input, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, buffer, blockEntity.getLevel(), 0);
			poseStack.popPose();

			if (!output.isEmpty() && RandomUtil.oneInNChance(10)) {
				Vec3 slotItemPos = Vec3.atCenterOf(blockEntity.getBlockPos())
						.add(level.random.nextGaussian() * 0.02f, 0.9, level.random.nextGaussian() * 0.02f);
				Vec3 velocity = slotItemPos.vectorTo(slotItemPos.add(0, 0.25f, 0)).scale(0.02);

				ParticleBuilder.forPositions(new ItemParticleOption(ParticleTypes.ITEM, input), slotItemPos)
						.scaleMod(0.125f)
						.gravityOverride(0)
						.velocity(0, 0, 0)
						.lifespan(30)
						.particleConsumer(particle -> ((Particle)particle).setParticleSpeed(velocity.x, velocity.y,	velocity.z))
						.cutoffDistance(20)
						.spawnParticles(level);
			}
		}

		if (!output.isEmpty()) {
			poseStack.pushPose();
			poseStack.translate(-0.75f, 2, -0.75f);
			poseStack.mulPose(Axis.YN.rotationDegrees(level.getGameTime() + partialTick));
			poseStack.scale(1.75f, 1.75f, 1.75f);

			if (!(output.getItem() instanceof BlockItem))
				poseStack.scale(0.5f, 0.5f, 0.5f);

			renderer.renderStatic(output, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, buffer, blockEntity.getLevel(), 0);
			poseStack.popPose();
		}

		for (int i = 1; i < 10; i++) {
			ItemStack stack = contents.get(i);

			if (stack.isEmpty())
				continue;

			final int row = (i - 1) % 3;
			final int column = (i - 1) / 3;

			poseStack.pushPose();
			poseStack.translate(-0.75f * row, 0, -0.75f * column);

			if (!(stack.getItem() instanceof BlockItem))
				poseStack.scale(0.5f, 0.5f, 0.5f);

			renderer.renderStatic(stack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, buffer, blockEntity.getLevel(), 0);
			poseStack.popPose();

			if (RandomUtil.oneInNChance(input.isEmpty() ? 50 : 10)) {
				Vec3 slotItemPos = Vec3.atLowerCornerOf(blockEntity.getBlockPos())
						.add(0.3125f, 1, 0.3125f)
						.add(row * 0.1875f, 0.0625f, column * 0.1875f)
						.add(level.random.nextGaussian() * 0.02f, level.random.nextGaussian() * 0.02f, level.random.nextGaussian() * 0.02f);
				Vec3 velocity;

				if (input.isEmpty()) {
					velocity = new Vec3(level.random.nextGaussian() * 0.001f, 0.01f, level.random.nextGaussian() * 0.001f);
				}
				else {
					Vec3 targetPos = Vec3.atCenterOf(blockEntity.getBlockPos())
							.add(0, 0.95, 0);
					velocity = slotItemPos.vectorTo(targetPos).normalize().scale(0.0125f);
				}

				ParticleBuilder.forPositions(new ItemParticleOption(ParticleTypes.ITEM, stack), slotItemPos)
						.scaleMod(0.125f)
						.gravityOverride(0)
						.velocity(0, 0, 0)
						.lifespan(30)
						.particleConsumer(particle -> ((Particle)particle).setParticleSpeed(velocity.x, velocity.y, velocity.z))
						.cutoffDistance(20)
						.spawnParticles(level);
			}
		}

		poseStack.popPose();
	}
}
