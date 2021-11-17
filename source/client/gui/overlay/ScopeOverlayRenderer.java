package net.tslat.aoa3.client.gui.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.util.RenderUtil;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
public class ScopeOverlayRenderer {
	public static boolean isScoped = false;

	@SubscribeEvent
	public static void fovEvent(final FOVUpdateEvent event) {
		if (isScoped && Minecraft.getInstance().options.getCameraType() == PointOfView.FIRST_PERSON)
			event.setNewfov(0.2f);
	}

	@SubscribeEvent
	public static void renderHand(final RenderHandEvent event) {
		if (isScoped && Minecraft.getInstance().options.getCameraType() == PointOfView.FIRST_PERSON)
			event.setCanceled(true);
	}

	@SubscribeEvent
	public static void renderScopeScreenPre(final RenderGameOverlayEvent.Pre event) {
		if (isScoped && event.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS && Minecraft.getInstance().options.getCameraType() == PointOfView.FIRST_PERSON)
			event.setCanceled(true);
	}

	@SubscribeEvent
	public static void renderOverlay(final RenderGameOverlayEvent.Post event) {
		if (event.isCanceled() || event.getType() != RenderGameOverlayEvent.ElementType.HELMET || Minecraft.getInstance().options.getCameraType() != PointOfView.FIRST_PERSON)
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
