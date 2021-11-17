package net.tslat.aoa3.client.render.custom;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RenderUtil;

public interface AoAResourceRenderer {
	void renderInHud(MatrixStack matrix, AoAResource.Instance resource, float partialTicks);
	void renderInGui(MatrixStack matrix, AoAResource.Instance resource, float partialTicks, int mouseX, int mouseY);

	default int hudRenderWidth(AoAResource.Instance resource) {
		return 25;
	}

	default int hudRenderHeight(AoAResource.Instance resource) {
		return 25;
	}

	default int guiRenderWidth(AoAResource.Instance resource) {
		return 25;
	}

	default int guiRenderHeight(AoAResource.Instance resource) {
		return 25;
	}

	AoAResourceRenderer DEFAULT = new AoAResourceRenderer() {
		@Override
		public void renderInHud(MatrixStack matrix, AoAResource.Instance resource, float partialTicks) {
			matrix.pushPose();
			matrix.scale(0.5f, 0.5f, 0);
			RenderSystem.color4f(1, 1, 1, 1);
			Minecraft.getInstance().getTextureManager().bind(new ResourceLocation(resource.type().getRegistryName().getNamespace(), "textures/gui/aoaresource/" + resource.type().getRegistryName().getPath() + ".png"));

			int renderWidth = hudRenderWidth(resource) * 2;
			int renderHeight = hudRenderHeight(resource) * 2;
			float percentOfMax = (float)Math.floor((resource.getCurrentValue() / resource.getMaxValue()) * (float)renderWidth);

			RenderUtil.renderScaledCustomSizedTexture(matrix, 0, 0, 0, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth, renderHeight * 3);
			RenderUtil.renderScaledCustomSizedTexture(matrix, 0, 0, 0, percentOfMax == renderWidth ? renderHeight * 2 : renderHeight, percentOfMax, renderHeight, percentOfMax, renderHeight, renderWidth, renderHeight * 3);
			RenderUtil.drawCenteredScaledString(matrix, Minecraft.getInstance().font, String.valueOf((int)resource.getCurrentValue()), 26, 29, 1.5f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
			matrix.popPose();
		}

		@Override
		public void renderInGui(MatrixStack matrix, AoAResource.Instance resource, float partialTicks, int mouseX, int mouseY) {

		}
	};

	enum HudResourcesPosition {
		Top_Right,
		Top_Left,
		Bottom_Right,
		Bottom_Left
	}
}
