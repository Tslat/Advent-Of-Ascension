package net.tslat.aoa3.client.render.custom;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.resource.EnergyResource;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.RenderUtil;
import org.apache.commons.lang3.math.NumberUtils;

import javax.annotation.Nullable;

public class EnergyResourceRenderer implements AoAResourceRenderer {
	@Override
	public void renderInHud(MatrixStack matrix, AoAResource.Instance resource, float partialTicks, @Nullable String valueOverride) {
		matrix.pushPose();
		matrix.scale(0.5f, 0.5f, 0);
		RenderSystem.color4f(1, 1, 1, 1);
		Minecraft.getInstance().getTextureManager().bind(new ResourceLocation(resource.type().getRegistryName().getNamespace(), "textures/gui/aoaresource/" + resource.type().getRegistryName().getPath() + ".png"));

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
			RenderUtil.drawCenteredScaledString(matrix, Minecraft.getInstance().font, String.valueOf(MathHelper.ceil(((EnergyResource)resource).getCurrentDelay() / 20f)), 26, 29, 1.5f, ColourUtil.RED, RenderUtil.StringRenderType.OUTLINED);
		}
		else {
			RenderUtil.drawCenteredScaledString(matrix, Minecraft.getInstance().font, value, 26, 29, 1.5f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
		}

		matrix.popPose();
	}

	@Override
	public void renderInGui(MatrixStack matrix, AoAResource.Instance resource, float partialTicks, int mouseX, int mouseY) {}
}