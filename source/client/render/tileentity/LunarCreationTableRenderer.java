package net.tslat.aoa3.client.render.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.tslat.aoa3.object.block.tileentity.LunarCreationTableTileEntity;

public class LunarCreationTableRenderer extends TileEntityRenderer<LunarCreationTableTileEntity> {
	public LunarCreationTableRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(LunarCreationTableTileEntity tileEntity, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		NonNullList<ItemStack> contents = tileEntity.getContents();
		ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
		World world = tileEntity.getLevel();
		BlockPos abovePos = tileEntity.getBlockPos().above();

		if (world != null) {
			if (world.getBlockState(abovePos).isSolidRender(world, abovePos))
				return;

			combinedLight = WorldRenderer.getLightColor(world, abovePos);
		}
		else {
			combinedLight = 15728880;
		}

		matrix.pushPose();

		matrix.scale(0.25f, 0.25f, 0.25f);
		matrix.translate(1.25f, 4.25f, 1.25f);
		matrix.mulPose(Vector3f.YP.rotationDegrees(180f));

		for (int i = 0; i < 9; i++) {
			ItemStack stack = contents.get(i);

			if (stack.isEmpty())
				continue;

			matrix.pushPose();
			matrix.translate(-0.75f * (i % 3), 0, -0.75f * (i / 3));

			if (!(stack.getItem() instanceof BlockItem))
				matrix.scale(0.5f, 0.5f, 0.5f);

			renderer.renderStatic(stack, ItemCameraTransforms.TransformType.FIXED, combinedLight, combinedOverlay, matrix, buffer);
			matrix.popPose();
		}

		matrix.popPose();
	}
}
