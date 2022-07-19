package net.tslat.aoa3.client.gui.overlay;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.util.RenderUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ScreenOverlayRenderer {
	private static final ConcurrentHashMap<ResourceLocation, Integer> overlays = new ConcurrentHashMap<ResourceLocation, Integer>();

	public static void init() {
		AdventOfAscension.modEventBus.addListener(EventPriority.NORMAL, false, RegisterGuiOverlaysEvent.class, ev -> ev.registerAboveAll("aoa_mob_overlays", ScreenOverlayRenderer::onOverlayRender));
	}

	public static void addOverlay(ResourceLocation overlay, int duration) {
		overlays.put(overlay, duration);
	}

	public static void tickOverlays() {
		overlays.entrySet().removeIf(entry -> entry.getValue() <= 0);
		overlays.keySet().spliterator().forEachRemaining(overlay -> overlays.compute(overlay, (overlay2, duration) -> duration - 1));
	}

	public static void clearOverlays() {
		overlays.clear();
	}

	private static void onOverlayRender(ForgeGui gui, PoseStack poseStack, float partialTick, int width, int height) {
		if (Minecraft.getInstance().options.getCameraType() != CameraType.FIRST_PERSON || overlays.isEmpty())
			return;

		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder buffer = tesselator.getBuilder();

		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);

		for (Map.Entry<ResourceLocation, Integer> entry : overlays.entrySet()) {
			RenderUtil.setRenderingTexture(entry.getKey());
			RenderSystem.setShaderColor(1, 1, 1, entry.getValue() / 50f);
			buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			buffer.vertex(0, window.getGuiScaledHeight(), -90).uv(0, 1).endVertex();
			buffer.vertex(window.getGuiScaledWidth(), window.getGuiScaledHeight(), -90).uv(1, 1).endVertex();
			buffer.vertex(window.getGuiScaledWidth(), 0, -90).uv(1, 0).endVertex();
			buffer.vertex(0, 0, -90).uv(0, 0).endVertex();
			tesselator.end();
		}

		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
}
