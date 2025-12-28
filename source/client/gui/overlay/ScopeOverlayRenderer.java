package net.tslat.aoa3.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.CameraType;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.datacomponent.SniperScope;
import net.tslat.aoa3.content.item.weapon.sniper.AoASniper;
import net.tslat.aoa3.util.RenderUtil;

public final class ScopeOverlayRenderer {
	public static boolean isScoped = false;

	public static void init() {
		final IEventBus bus = NeoForge.EVENT_BUS;

		bus.addListener(EventPriority.NORMAL, false, ComputeFovModifierEvent.class, ScopeOverlayRenderer::onFOVUpdate);
		bus.addListener(EventPriority.NORMAL, false, RenderHandEvent.class, ScopeOverlayRenderer::onHandRender);
		bus.addListener(EventPriority.NORMAL, false, RenderGuiLayerEvent.Pre.class, ScopeOverlayRenderer::beforeOverlayRender);

		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, RegisterGuiLayersEvent.class, ev -> ev.registerAboveAll(AdventOfAscension.id("aoa_sniper_scopes"), ScopeOverlayRenderer::afterOverlayRender));
	}

	private static void onFOVUpdate(final ComputeFovModifierEvent event) {
		if (isScoped && Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON) {
			SniperScope scope = ClientOperations.getPlayer().getMainHandItem().get(AoADataComponents.SNIPER_SCOPE);

			event.setNewFovModifier(0.35f / (scope == null ? 1f : scope.strength()));
		}
	}

	private static void onHandRender(final RenderHandEvent event) {
		if (isScoped && Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON)
			event.setCanceled(true);
	}

	private static void beforeOverlayRender(final RenderGuiLayerEvent.Pre event) {
		if (isScoped && event.getName().equals(VanillaGuiLayers.CROSSHAIR) && Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON)
			event.setCanceled(true);
	}

	private static void afterOverlayRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
		if (Minecraft.getInstance().options.getCameraType() != CameraType.FIRST_PERSON)
			return;

		Minecraft mc = Minecraft.getInstance();
		ResourceLocation texture;

		if (AoASniper.isScoped(mc.player)) {
			isScoped = true;
			ItemStack sniper = mc.player.getMainHandItem();
			texture = ((AoASniper)sniper.getItem()).getScopeTexture(sniper);
		}
		else {
			isScoped = false;

			return;
		}

		RenderSystem.enableBlend();
		RenderUtil.renderFullscreenTexture(guiGraphics, texture);
	}
}
