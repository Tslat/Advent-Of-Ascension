package net.tslat.aoa3.client.render.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.resource.EnergyResource;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.RenderUtil;
import org.apache.commons.lang3.math.NumberUtils;

import javax.annotation.Nullable;

public class EnergyResourceRenderer implements AoAResourceRenderer {
	@Override
	public void renderInHud(PoseStack matrix, AoAResource.Instance resource, float partialTicks, @Nullable String valueOverride) {
		ResourceLocation resourceId = AoARegistries.AOA_RESOURCES.getId(resource.type());

		matrix.pushPose();
		matrix.scale(0.5f, 0.5f, 0);
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, new ResourceLocation(resourceId.getNamespace(), "textures/gui/aoaresource/" + resourceId.getPath() + ".png"));

		int renderWidth = hudRenderWidth(resource) * 2;
		int renderHeight = hudRenderHeight(resource) * 2;
		float currentValue = resource.getCurrentValue();
		String value = String.valueOf((int)currentValue);

		if (valueOverride != null) {
			if (valueOverride.equals("-1")) {
				currentValue = resource.getMaxValue();
				value = "";
			}
			else if (NumberUtils.isParsable(valueOverride)) {
				currentValue = Float.parseFloat(valueOverride);
				value = String.valueOf((int)currentValue);
			}
			else {
				currentValue = resource.getMaxValue();
				value = valueOverride;
			}
		}

		float percentOfMax = (float)Math.floor((Math.min(currentValue, resource.getMaxValue()) / resource.getMaxValue()) * (float)renderWidth);

		RenderUtil.renderScaledCustomSizedTexture(matrix, 0, 0, 0, 0, renderWidth, renderHeight, renderWidth, renderHeight, renderWidth, renderHeight * 3);
		RenderUtil.renderScaledCustomSizedTexture(matrix, 0, 0, 0, percentOfMax == renderWidth ? renderHeight * 2 : renderHeight, percentOfMax, renderHeight, percentOfMax, renderHeight, renderWidth, renderHeight * 3);

		if (((EnergyResource)resource).getCurrentDelay() > 0) {
			RenderUtil.renderCenteredScaledText(matrix, Component.literal(String.valueOf(Mth.ceil(((EnergyResource)resource).getCurrentDelay() / 20f))), 26, 29, 1.5f, ColourUtil.RED, RenderUtil.TextRenderType.OUTLINED);
		}
		else {
			RenderUtil.renderCenteredScaledText(matrix, Component.literal(value), 26, 29, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
		}

		matrix.popPose();
	}

	@Override
	public void renderInGui(PoseStack matrix, AoAResource.Instance resource, float partialTicks, int mouseX, int mouseY) {}
}
