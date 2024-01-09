package net.tslat.aoa3.client.gui.overlay;

import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;
import net.neoforged.neoforge.client.event.RenderGuiOverlayEvent;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import net.neoforged.neoforge.client.gui.overlay.ExtendedGui;
import net.neoforged.neoforge.client.gui.overlay.VanillaGuiOverlay;
import net.neoforged.neoforge.common.NeoForge;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.util.RenderUtil;

public final class ScopeOverlayRenderer {
	public static boolean isScoped = false;

	public static void init() {
		final IEventBus bus = NeoForge.EVENT_BUS;

		bus.addListener(EventPriority.NORMAL, false, ComputeFovModifierEvent.class, ScopeOverlayRenderer::onFOVUpdate);
		bus.addListener(EventPriority.NORMAL, false, RenderHandEvent.class, ScopeOverlayRenderer::onHandRender);
		bus.addListener(EventPriority.NORMAL, false, RenderGuiOverlayEvent.Pre.class, ScopeOverlayRenderer::beforeOverlayRender);

		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, RegisterGuiOverlaysEvent.class, ev -> ev.registerAboveAll(AdventOfAscension.id("aoa_sniper_scopes"), ScopeOverlayRenderer::afterOverlayRender));
	}

	private static void onFOVUpdate(final ComputeFovModifierEvent event) {
		if (isScoped && Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON)
			event.setNewFovModifier(0.2f);
	}

	private static void onHandRender(final RenderHandEvent event) {
		if (isScoped && Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON)
			event.setCanceled(true);
	}

	private static void beforeOverlayRender(final RenderGuiOverlayEvent.Pre event) {
		if (isScoped && event.getOverlay().id().equals(VanillaGuiOverlay.CROSSHAIR.id()) && Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON)
			event.setCanceled(true);
	}

	private static void afterOverlayRender(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int width, int height) {
		if (Minecraft.getInstance().options.getCameraType() != CameraType.FIRST_PERSON)
			return;

		Minecraft mc = Minecraft.getInstance();
		ResourceLocation texture = null;

		if (mc.player.isShiftKeyDown() && mc.player.onGround()) {
			ItemStack sniper = mc.player.getItemInHand(InteractionHand.MAIN_HAND);

			if (sniper.getItem() instanceof BaseSniper) {
				isScoped = true;
				texture = ((BaseSniper)sniper.getItem()).getScopeTexture(sniper);
			}
			else {
				isScoped = false;
			}
		}
		else {
			isScoped = false;
		}

		if (!isScoped)
			return;

		RenderUtil.renderFullscreenTexture(guiGraphics, texture);
	}
}
