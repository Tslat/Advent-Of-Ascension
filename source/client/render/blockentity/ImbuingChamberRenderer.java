package net.tslat.aoa3.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.block.blockentity.ImbuingChamberBlockEntity;
import net.tslat.aoa3.content.item.misc.AspectFocusItem;
import net.tslat.aoa3.content.item.misc.PowerStone;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;

import java.util.List;

public class ImbuingChamberRenderer implements BlockEntityRenderer<ImbuingChamberBlockEntity> {
	public ImbuingChamberRenderer(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(ImbuingChamberBlockEntity imbuingChamber, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
		final List<ItemStack> contents = imbuingChamber.getContents();
		final Direction direction = imbuingChamber.getBlockState().getValue(HorizontalDirectionalBlock.FACING).getCounterClockWise();

		poseStack.pushPose();
		poseStack.translate(0.5f, 0.5625f, 0.5f);
		poseStack.mulPose(Axis.YN.rotationDegrees(direction.toYRot()));

		renderContents(poseStack, contents, partialTick, packedLight, packedOverlay, buffer);

		int colour = renderBeam(poseStack, contents, partialTick, packedLight, packedOverlay, buffer);

		if (colour != 0) {
			Vec3 pos = new Vec3(imbuingChamber.getBlockPos().getX() + 0.5f, imbuingChamber.getBlockPos().getY() + 0.7f, imbuingChamber.getBlockPos().getZ() + 0.5f)
					.add(direction.getStepX() * 0.375f, 0, direction.getStepZ() * 0.375f);

			if (!Minecraft.getInstance().isPaused()) {
				ParticleBuilder.forPositions(ParticleTypes.WARPED_SPORE, pos)
						.colourOverride(ColourUtil.getRed(colour), ColourUtil.getGreen(colour), ColourUtil.getBlue(colour), 255)
						.cutoffDistance(16)
						.scaleMod(0.25f)
						.isAmbient()
						.lifespan(3)
						.spawnParticles(Minecraft.getInstance().level);
			}
		}

		poseStack.popPose();
	}

	private static int renderBeam(PoseStack poseStack, List<ItemStack> contents, float partialTick, int packedLight, int packedOverlay, MultiBufferSource buffer) {
		if (contents.get(0).getItem() instanceof PowerStone powerStone) {
			int colour = powerStone.getColour();
			int currentIndex = 1;
			int lastIndex = 0;
			ItemStack slotStack;

			poseStack.mulPose(Axis.XP.rotationDegrees(90));
			poseStack.translate(-0.5f, -0.375f, -0.5f);

			do {
				if (!(slotStack = contents.get(currentIndex)).isEmpty() || currentIndex == 6) {
					int delta = (currentIndex - lastIndex);

					poseStack.pushPose();
					poseStack.translate(0, (lastIndex) * 0.137f, 0);
					poseStack.scale(1, 0.137f * delta, 1);
					BeaconRenderer.renderBeaconBeam(poseStack, buffer, BeaconRenderer.BEAM_LOCATION, partialTick, 0.125f, Minecraft.getInstance().level.getGameTime() * 12, 0, 1, new float[] {ColourUtil.getRed(colour) / 255f, ColourUtil.getGreen(colour) / 255f, ColourUtil.getBlue(colour) / 255f}, 0.1f, 0.12f);
					poseStack.popPose();

					if (slotStack.getItem() instanceof AspectFocusItem focus)
						colour = ColourUtil.lerpColour(colour, focus.getFocus().colour(), 0.75f);

					lastIndex = currentIndex;
				}

				currentIndex++;
			} while (currentIndex < contents.size());

			return colour;
		}

		return 0;
	}

	private static void renderContents(PoseStack poseStack, List<ItemStack> contents, float partialTick, int packedLight, int packedOverlay, MultiBufferSource buffer) {
		final ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();

		for (int i = 0; i < contents.size(); i++) {
			ItemStack focus = contents.get(i);

			if (focus == ItemStack.EMPTY)
				continue;

			poseStack.pushPose();
			poseStack.translate(0, 0, -0.34375f + 0.125 * i);

			if (i == 0 || i == 6) {
				poseStack.scale(0.5f, 0.5f, 0.5f);

				if (i == 0) {
					poseStack.translate(0, 0, 0.0313f);
				}
				else {
					poseStack.translate(0, 0, -0.03125f);
				}
			}

			renderer.renderStatic(focus, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, buffer, Minecraft.getInstance().level, 0);
			poseStack.popPose();
		}
	}
}
