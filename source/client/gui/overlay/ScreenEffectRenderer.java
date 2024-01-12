package net.tslat.aoa3.client.gui.overlay;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;
import net.neoforged.neoforge.client.gui.overlay.ExtendedGui;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.constant.ScreenImageEffect;
import net.tslat.aoa3.util.RenderUtil;
import org.joml.Matrix4f;

import java.util.concurrent.CopyOnWriteArrayList;

public final class ScreenEffectRenderer {
	private static final CopyOnWriteArrayList<ScreenImageEffect> effects = new CopyOnWriteArrayList<>();

	public static void init() {
		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, RegisterGuiOverlaysEvent.class, ev -> ev.registerAboveAll(AdventOfAscension.id("screen_effects"), ScreenEffectRenderer::onEffectRender));
	}

	public static void addScreenEffect(ScreenImageEffect effect) {
		Minecraft mc = Minecraft.getInstance();

		if (mc.level == null)
			return;

		effect.init(mc.getWindow(), mc.level.getGameTime());
		effects.add(effect);
	}

	public static void clearOverlays() {
		effects.clear();
	}

	private static void onEffectRender(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int width, int height) {
		if (Minecraft.getInstance().options.getCameraType() != CameraType.FIRST_PERSON || effects.isEmpty() || Minecraft.getInstance().level == null)
			return;

		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder buffer = tesselator.getBuilder();

		long gameTime = mc.level.getGameTime();
		boolean hasExpiredEffects = false;
		PoseStack poseStack = guiGraphics.pose();

		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);

		for (ScreenImageEffect effect : effects) {
			if (effect.isExpired(gameTime)) {
				hasExpiredEffects = true;

				continue;
			}

			float scale = effect.getScale();
			float fadeTime = (effect.getExpiry() - gameTime) / (float)effect.getDuration();

			poseStack.pushPose();
			RenderSystem.setShaderColor(effect.getRed(), effect.getGreen(), effect.getBlue(), effect.getAlpha() * fadeTime);
			RenderUtil.setRenderingTexture(effect.getTexture());
			buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

			if (effect.isFullscreen()) {
				buffer.vertex(0, window.getGuiScaledHeight(), -90).uv(0, 1).endVertex();
				buffer.vertex(window.getGuiScaledWidth(), window.getGuiScaledHeight(), -90).uv(1, 1).endVertex();
				buffer.vertex(window.getGuiScaledWidth(), 0, -90).uv(1, 0).endVertex();
				buffer.vertex(0, 0, -90).uv(0, 0).endVertex();
			}
			else {
				poseStack.translate(-256, -256, 0);
				poseStack.scale(scale, scale, scale);
				poseStack.translate(effect.x(), effect.y(), 0);

				Matrix4f pose = poseStack.last().pose();

				buffer.vertex(pose, 0, 256, -90).uv(0, 1).endVertex();
				buffer.vertex(pose, 256, 256, -90).uv(1, 1).endVertex();
				buffer.vertex(pose, 256, 0, -90).uv(1, 0).endVertex();
				buffer.vertex(pose, 0, 0, -90).uv(0, 0).endVertex();
			}

			tesselator.end();

			poseStack.popPose();

			RenderSystem.setShaderColor(1, 1, 1, 1);
		}

		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();

		if (hasExpiredEffects)
			effects.removeIf(effect -> effect.isExpired(gameTime));
	}
}
