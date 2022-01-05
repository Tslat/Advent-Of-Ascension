package net.tslat.aoa3.client.gui.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.tslat.aoa3.object.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.util.RenderUtil;

public final class ScopeOverlayRenderer {
	public static boolean isScoped = false;

	public static void init() {
		final IEventBus bus = MinecraftForge.EVENT_BUS;

		bus.addListener(EventPriority.NORMAL, false, FOVUpdateEvent.class, ScopeOverlayRenderer::onFOVUpdate);
		bus.addListener(EventPriority.NORMAL, false, RenderHandEvent.class, ScopeOverlayRenderer::onHandRender);
		bus.addListener(EventPriority.NORMAL, false, RenderGameOverlayEvent.Pre.class, ScopeOverlayRenderer::beforeOverlayRender);
		bus.addListener(EventPriority.NORMAL, false, RenderGameOverlayEvent.Post.class, ScopeOverlayRenderer::afterOverlayRender);
	}

	private static void onFOVUpdate(final FOVUpdateEvent event) {
		if (isScoped && Minecraft.getInstance().options.getCameraType() == PointOfView.FIRST_PERSON)
			event.setNewfov(0.2f);
	}

	private static void onHandRender(final RenderHandEvent event) {
		if (isScoped && Minecraft.getInstance().options.getCameraType() == PointOfView.FIRST_PERSON)
			event.setCanceled(true);
	}

	private static void beforeOverlayRender(final RenderGameOverlayEvent.Pre event) {
		if (isScoped && event.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS && Minecraft.getInstance().options.getCameraType() == PointOfView.FIRST_PERSON)
			event.setCanceled(true);
	}

	private static void afterOverlayRender(final RenderGameOverlayEvent.Post event) {
		if (event.getType() != RenderGameOverlayEvent.ElementType.HELMET || Minecraft.getInstance().options.getCameraType() != PointOfView.FIRST_PERSON)
			return;

		Minecraft mc = Minecraft.getInstance();
		ResourceLocation texture = null;

		if (mc.player.isShiftKeyDown() && mc.player.onGround) {
			ItemStack sniper = mc.player.getItemInHand(Hand.MAIN_HAND);

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

		mc.getTextureManager().bind(texture);
		RenderUtil.renderFullscreenTexture();
	}
}
