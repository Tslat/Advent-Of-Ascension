package net.tslat.aoa3.client.gui.hud.toasts;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.RenderUtil;

public class GenericToast implements Toast {
    protected static final ResourceLocation ADVANCEMENT_TOAST_TEXTURE = new ResourceLocation("toast/advancement");
    protected final Component title;
    protected final Component message;

    public GenericToast(Component title, Component message) {
        this.title = title;
        this.message = message;
    }

    protected ResourceLocation getBackgroundTexture() {
        return ADVANCEMENT_TOAST_TEXTURE;
    }

    protected boolean stillValid() {
        return true;
    }

    protected int defaultLifeSpan() {
        return 5000;
    }

    @Override
    public Visibility render(GuiGraphics guiGraphics, ToastComponent toastComponent, long timeSinceLastVisible) {
        if (!stillValid())
            return Visibility.HIDE;

        drawBackground(guiGraphics, toastComponent, timeSinceLastVisible);
        drawIcon(guiGraphics, toastComponent, timeSinceLastVisible);
        drawTexts(guiGraphics, toastComponent, timeSinceLastVisible);

        return timeSinceLastVisible >= defaultLifeSpan() * toastComponent.getNotificationDisplayTimeMultiplier() ? Toast.Visibility.HIDE : Toast.Visibility.SHOW;
    }

    protected void drawBackground(GuiGraphics guiGraphics, ToastComponent toastComponent, long timeSinceLastVisible) {
        RenderUtil.resetShaderColour();
        guiGraphics.blitSprite(getBackgroundTexture(), 0, 0, width(), height());
    }

    protected void drawIcon(GuiGraphics guiGraphics, ToastComponent toastComponent, long timeSinceLastVisible) {}

    protected void drawTexts(GuiGraphics guiGraphics, ToastComponent toastComponent, long timeSinceLastVisible) {
        final PoseStack poseStack = guiGraphics.pose();
        final int messageWidth = Minecraft.getInstance().font.width(this.message);

        RenderUtil.renderText(poseStack, this.title, 30, 7, -11534256, RenderUtil.TextRenderType.NORMAL);

        if (messageWidth <= 125) {
            RenderUtil.renderText(poseStack, this.message, 30, 18, ColourUtil.WHITE, RenderUtil.TextRenderType.NORMAL);
        }
        else {
            float scale = 125f / messageWidth;

            poseStack.pushPose();
            poseStack.scale(scale, scale, 1);
            RenderUtil.renderText(poseStack, this.message, 30 / scale, 18 / scale, ColourUtil.WHITE, RenderUtil.TextRenderType.NORMAL);
            poseStack.popPose();
        }
    }
}
