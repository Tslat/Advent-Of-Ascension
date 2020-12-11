package net.tslat.aoa3.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
public class ScopeOverlayRenderer {
	public static Type scope;
	public static boolean isScoped = false;

	@SubscribeEvent
	public static void fovEvent(final FOVUpdateEvent event) {
		if (isScoped && Minecraft.getInstance().gameSettings.thirdPersonView == 0)
			event.setNewfov(0.2f);
	}

	@SubscribeEvent
	public static void renderHand(final RenderHandEvent event) {
		if (isScoped && Minecraft.getInstance().gameSettings.thirdPersonView == 0)
			event.setCanceled(true);
	}

	@SubscribeEvent
	public static void renderScopeScreenPre(final RenderGameOverlayEvent.Pre event) {
		if (isScoped && event.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS && Minecraft.getInstance().gameSettings.thirdPersonView == 0)
			event.setCanceled(true);
	}

	@SubscribeEvent
	public static void renderOverlay(final RenderGameOverlayEvent.Post event) {
		if (!isScoped || event.isCanceled() || event.getType() != RenderGameOverlayEvent.ElementType.HELMET || Minecraft.getInstance().gameSettings.thirdPersonView != 0)
			return;

		Minecraft mc = Minecraft.getInstance();
		MainWindow window = mc.getMainWindow();

		mc.getTextureManager().bindTexture(new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/scope/" + scope.toString().toLowerCase() + ".png"));

		final Tessellator tess = Tessellator.getInstance();
		final BufferBuilder buff = tess.getBuffer();

		RenderSystem.pushTextureAttributes();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableBlend();
		buff.begin(7, DefaultVertexFormats.POSITION_TEX);
		buff.pos(0.0D, window.getScaledHeight(), -90.0D).tex(0.0f, 1.0f).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.pos(window.getScaledWidth(), window.getScaledHeight(), -90.0D).tex(1.0f, 1.0f).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.pos(window.getScaledWidth(), 0.0, -90.0D).tex(1.0f, 0.0f).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.pos(0.0D, 0.0D, -90.0D).tex(0.0f, 0.0f).normal(0.0F, 1.0F, 0.0F).endVertex();
		tess.draw();
		RenderSystem.depthMask(true);
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.disableAlphaTest();
		RenderSystem.disableBlend();
		RenderSystem.popAttributes();
	}

	public enum Type {
		BASIC,
		BOULDER,
		CANDY,
		CRYSTAL,
		DIAMOND,
		DISCHARGE,
		DOTTED,
		FLORO,
		MARKER,
		MONSTER,
		MOON,
		REDLIGHT,
		SCRATCHES
	}
}
