package net.tslat.aoa3.client.render.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.block.tileentity.LunarCreationTableBlockEntity;

public class LunarCreationTableRenderer implements BlockEntityRenderer<LunarCreationTableBlockEntity> {
	public LunarCreationTableRenderer(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(LunarCreationTableBlockEntity blockEntity, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
		NonNullList<ItemStack> contents = blockEntity.getContents();
		ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
		Level world = blockEntity.getLevel();
		BlockPos abovePos = blockEntity.getBlockPos().above();

		if (world != null) {
			if (world.getBlockState(abovePos).isSolidRender(world, abovePos))
				return;

			combinedLight = LevelRenderer.getLightColor(world, abovePos);
		}
		else {
			combinedLight = LightTexture.FULL_BRIGHT;
		}

		matrix.pushPose();

		matrix.scale(0.25f, 0.25f, 0.25f);
		matrix.translate(1.25f, 4.25f, 1.25f);
		matrix.mulPose(Axis.YP.rotationDegrees(180f));

		for (int i = 0; i < 9; i++) {
			ItemStack stack = contents.get(i);

			if (stack.isEmpty())
				continue;

			matrix.pushPose();
			matrix.translate(-0.75f * (i % 3), 0, -0.75f * (i / 3));

			if (!(stack.getItem() instanceof BlockItem))
				matrix.scale(0.5f, 0.5f, 0.5f);

			renderer.renderStatic(stack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, matrix, buffer, blockEntity.getLevel(), 0);
			matrix.popPose();
		}

		matrix.popPose();
	}
}
