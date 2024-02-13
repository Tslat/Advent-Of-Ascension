package net.tslat.aoa3.client.gui.hud.toasts;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.tslat.aoa3.client.render.AoAGuiElementRenderers;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RegistryUtil;

public class ResourceRequirementToast extends GenericToast {
	private final AoAResource resource;

	public ResourceRequirementToast(AoAResource relevantResource, float resourceRequirement) {
		super(LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "toast.resourceReq.title"), ChatFormatting.DARK_RED), LocaleUtil.getLocaleMessage(LocaleUtil.createGenericLocaleKey("gui", "toast.resourceReq.subtitle"), relevantResource.getName(), Component.literal(NumberUtil.roundToNthDecimalPlace(resourceRequirement, 2))));

		this.resource = relevantResource;
	}

	@Override
	protected boolean stillValid() {
		return RegistryUtil.getId(this.resource) != null;
	}

	@Override
	protected void drawIcon(GuiGraphics guiGraphics, ToastComponent toastComponent, long timeSinceLastVisible) {
		final PoseStack poseStack = guiGraphics.pose();

		poseStack.pushPose();
		poseStack.scale(0.9f, 0.9f, 1f);
		poseStack.translate(5.5f, 5, 0);
		AoAGuiElementRenderers.getResourceRenderer(this.resource).renderInHud(poseStack, ClientPlayerDataManager.get().getResource(resource), Minecraft.getInstance().getDeltaFrameTime(), "-1");
		poseStack.popPose();
	}
}
