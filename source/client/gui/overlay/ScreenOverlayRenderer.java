package net.tslat.aoa3.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoATags;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
public class ScreenOverlayRenderer {
	private static final ConcurrentHashMap<ResourceLocation, Integer> overlays = new ConcurrentHashMap<ResourceLocation, Integer>();

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
		if (event.isCanceled() || Minecraft.getInstance().options.getCameraType() != PointOfView.FIRST_PERSON || event.getType() != RenderGameOverlayEvent.ElementType.ALL)
			return;

		Minecraft mc = Minecraft.getInstance();
		MainWindow window = mc.getWindow();

		if (mc.player != null && mc.player.isEyeInFluid(AoATags.Fluids.TOXIC_WASTE)) {
			final BufferBuilder buff = Tessellator.getInstance().getBuilder();
			float yMod = mc.player.xRot / 512f + (mc.player.tickCount / 1800f);
			float xMod = mc.player.yRot / 1024f + (mc.player.tickCount / 3600f);

			RenderSystem.pushTextureAttributes();
			RenderSystem.enableAlphaTest();
			RenderSystem.enableBlend();
			mc.getTextureManager().bind(new ResourceLocation(AdventOfAscension.MOD_ID, "textures/block/toxic_waste_overlay.png"));
			RenderSystem.color4f(1.0f, 1.0f, 1.0f, 0.1001f);
			buff.begin(7, DefaultVertexFormats.POSITION_TEX);
			buff.vertex(0.0D, window.getGuiScaledHeight(), -90.0D).uv(xMod, 1f + yMod).normal(0.0F, 1.0F, 0.0F).endVertex();
			buff.vertex(window.getGuiScaledWidth(), window.getGuiScaledHeight(), -90.0D).uv(1f + xMod, 1f + yMod).normal(0.0F, 1.0F, 0.0F).endVertex();
			buff.vertex(window.getGuiScaledWidth(), 0.0, -90.0D).uv(1f + xMod, yMod).normal(0.0F, 1.0F, 0.0F).endVertex();
			buff.vertex(0.0D, 0.0D, -90.0D).uv(xMod, yMod).normal(0.0F, 1.0F, 0.0F).endVertex();
			Tessellator.getInstance().end();
			RenderSystem.depthMask(true);
			RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
			RenderSystem.disableAlphaTest();
			RenderSystem.disableBlend();
			RenderSystem.popAttributes();
		}
	}

	@SubscribeEvent
	public static void renderOverlay(final RenderGameOverlayEvent.Post event) {
		handleToxicWaste(event);

		if (event.isCanceled() || Minecraft.getInstance().options.getCameraType() != PointOfView.FIRST_PERSON || event.getType() != RenderGameOverlayEvent.ElementType.HELMET || overlays.isEmpty())
			return;

		Minecraft mc = Minecraft.getInstance();
		MainWindow window = mc.getWindow();
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuilder();

		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.defaultBlendFunc();
		RenderSystem.disableAlphaTest();

		for (Map.Entry<ResourceLocation, Integer> entry : overlays.entrySet()) {
			mc.getTextureManager().bind(entry.getKey());
			RenderSystem.color4f(1, 1, 1, entry.getValue() / 50f);
			buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
			buffer.vertex(0, window.getGuiScaledHeight(), -90).uv(0, 1).endVertex();
			buffer.vertex(window.getGuiScaledWidth(), window.getGuiScaledHeight(), -90).uv(1, 1).endVertex();
			buffer.vertex(window.getGuiScaledWidth(), 0, -90).uv(1, 0).endVertex();
			buffer.vertex(0, 0, -90).uv(0, 0).endVertex();
			tessellator.end();
		}

		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
		RenderSystem.enableAlphaTest();
		RenderSystem.color4f(1, 1, 1, 1);
	}
}
