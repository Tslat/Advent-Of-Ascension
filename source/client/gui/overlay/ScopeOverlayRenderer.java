package net.tslat.aoa3.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.FOVModifierEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.tslat.aoa3.content.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.util.RenderUtil;

public final class ScopeOverlayRenderer {
	public static boolean isScoped = false;

	public static void init() {
		final IEventBus bus = MinecraftForge.EVENT_BUS;

		bus.addListener(EventPriority.NORMAL, false, FOVModifierEvent.class, ScopeOverlayRenderer::onFOVUpdate);
		bus.addListener(EventPriority.NORMAL, false, RenderHandEvent.class, ScopeOverlayRenderer::onHandRender);
		bus.addListener(EventPriority.NORMAL, false, RenderGameOverlayEvent.PreLayer.class, ScopeOverlayRenderer::beforeOverlayRender);
		bus.addListener(EventPriority.NORMAL, false, RenderGameOverlayEvent.PostLayer.class, ScopeOverlayRenderer::afterOverlayRender);
	}

	private static void onFOVUpdate(final FOVModifierEvent event) {
		if (isScoped && Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON)
			event.setNewFov(0.2f);
	}

	private static void onHandRender(final RenderHandEvent event) {
		if (isScoped && Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON)
			event.setCanceled(true);
	}

	private static void beforeOverlayRender(final RenderGameOverlayEvent.PreLayer event) {
		if (isScoped && event.getOverlay() == ForgeIngameGui.CROSSHAIR_ELEMENT && Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON)
			event.setCanceled(true);
	}

	private static void afterOverlayRender(final RenderGameOverlayEvent.PostLayer event) {
		if (event.getOverlay() != ForgeIngameGui.HELMET_ELEMENT || Minecraft.getInstance().options.getCameraType() != CameraType.FIRST_PERSON)
			return;

		Minecraft mc = Minecraft.getInstance();
		ResourceLocation texture = null;

		if (mc.player.isShiftKeyDown() && mc.player.isOnGround()) {
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

		RenderSystem.setShaderTexture(0, texture);
		RenderUtil.renderFullscreenTexture();
	}
}
