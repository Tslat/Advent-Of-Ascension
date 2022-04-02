package net.tslat.aoa3.client.gui.overlay;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;

import java.util.concurrent.ConcurrentHashMap;

public final class ScreenOverlayRenderer {
	private static final ConcurrentHashMap<ResourceLocation, Integer> overlays = new ConcurrentHashMap<ResourceLocation, Integer>();

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, RenderGameOverlayEvent.Post.class, ScreenOverlayRenderer::onOverlayRender);
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

	private static void handleToxicWaste(RenderGameOverlayEvent.Post event) {
		/*if (event.getType() != RenderGameOverlayEvent.ElementType.ALL)
			return;

		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();

		if (mc.player != null && mc.player.isEyeInFluid(AoATags.Fluids.TOXIC_WASTE)) {
			final BufferBuilder buff = Tesselator.getInstance().getBuilder();
			float yMod = mc.player.getXRot() / 512f + (mc.player.tickCount / 1800f);
			float xMod = mc.player.getYRot() / 1024f + (mc.player.tickCount / 3600f);

			RenderSystem.pushTextureAttributes();
			RenderSystem.enableAlphaTest();
			RenderSystem.enableBlend();
			mc.getTextureManager().bind(new ResourceLocation(AdventOfAscension.MOD_ID, "textures/block/toxic_waste_overlay.png"));
			RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 0.1001f);
			buff.begin(7, DefaultVertexFormat.POSITION_TEX);
			buff.vertex(0.0D, window.getGuiScaledHeight(), -90.0D).uv(xMod, 1f + yMod).normal(0.0F, 1.0F, 0.0F).endVertex();
			buff.vertex(window.getGuiScaledWidth(), window.getGuiScaledHeight(), -90.0D).uv(1f + xMod, 1f + yMod).normal(0.0F, 1.0F, 0.0F).endVertex();
			buff.vertex(window.getGuiScaledWidth(), 0.0, -90.0D).uv(1f + xMod, yMod).normal(0.0F, 1.0F, 0.0F).endVertex();
			buff.vertex(0.0D, 0.0D, -90.0D).uv(xMod, yMod).normal(0.0F, 1.0F, 0.0F).endVertex();
			Tesselator.getInstance().end();
			RenderSystem.depthMask(true);
			RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
			RenderSystem.disableAlphaTest();
			RenderSystem.disableBlend();
			RenderSystem.popAttributes();
		}*/
	}

	private static void onOverlayRender(final RenderGameOverlayEvent.Post event) {
		/*if (Minecraft.getInstance().options.getCameraType() != CameraType.FIRST_PERSON)
			return;

		handleToxicWaste(event);

		if (event.getType() != RenderGameOverlayEvent.ElementType.HELMET || overlays.isEmpty())
			return;

		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder buffer = Tesselator.getBuilder();

		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.defaultBlendFunc();
		RenderSystem.disableAlphaTest();

		for (Map.Entry<ResourceLocation, Integer> entry : overlays.entrySet()) {
			mc.getTextureManager().bind(entry.getKey());
			RenderSystem.setShaderColor(1, 1, 1, entry.getValue() / 50f);
			buffer.begin(7, DefaultVertexFormat.POSITION_TEX);
			buffer.vertex(0, window.getGuiScaledHeight(), -90).uv(0, 1).endVertex();
			buffer.vertex(window.getGuiScaledWidth(), window.getGuiScaledHeight(), -90).uv(1, 1).endVertex();
			buffer.vertex(window.getGuiScaledWidth(), 0, -90).uv(1, 0).endVertex();
			buffer.vertex(0, 0, -90).uv(0, 0).endVertex();
			Tesselator.end();
		}

		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
		RenderSystem.enableAlphaTest();
		RenderSystem.setShaderColor(1, 1, 1, 1);*/
	}
}
