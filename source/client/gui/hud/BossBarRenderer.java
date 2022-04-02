package net.tslat.aoa3.client.gui.hud;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.advent.AdventOfAscension;

import java.util.HashMap;

public final class BossBarRenderer {
	private static final HashMap<String, ResourceLocation> textureCache = new HashMap<String, ResourceLocation>();

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, RenderGameOverlayEvent.BossInfo.class, BossBarRenderer::onBossInfoRender);
	}

	private static void onBossInfoRender(final RenderGameOverlayEvent.BossInfo ev) {
		if (!ev.isCanceled()) {
			Component nameComponent = ev.getBossEvent().getName();
			Component name;
			String id;
			PoseStack matrix = ev.getMatrixStack();

			if (nameComponent.getSiblings().isEmpty() || !(nameComponent instanceof TranslatableComponent))
				return;

			id = ((TranslatableComponent)nameComponent).getKey();

			if (!id.startsWith("entity.aoa3."))
				return;

			name = nameComponent.getSiblings().get(0);

			Minecraft mc = Minecraft.getInstance();
			Window mainWindow = mc.getWindow();
			ResourceLocation texture = getTexture(id.substring(12));
			int textureWidth = 196;
			int xPos = mainWindow.getGuiScaledWidth() / 2 - 100;
			int percentPixels = (int)Math.ceil(ev.getBossEvent().getProgress() * textureWidth);
			int stringWidth = mc.font.width(name);
			int x = mainWindow.getGuiScaledWidth() / 2 - stringWidth / 2;

			matrix.pushPose();
			/*RenderSystem.enableAlphaTest();
			RenderSystem.disableDepthTest();
			RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
			mc.getTextureManager().bind(texture);

			if (percentPixels < textureWidth)
				RenderUtil.renderCustomSizedTexture(matrix, xPos, ev.getY(), 0, 12, 200, 12, 200, 36);

			if (percentPixels > 0)
				RenderUtil.renderCustomSizedTexture(matrix, xPos + 2, ev.getY(), 2, 0, percentPixels, 12, 200, 36);

			RenderUtil.renderCustomSizedTexture(matrix, xPos, ev.getY(), 0, 24, 200, 12, 200, 36);
			mc.font.drawShadow(ev.getMatrixStack(), name, x, ev.getY() - 9, 16777215);
			RenderSystem.enableDepthTest();
			RenderSystem.disableAlphaTest();*/
			matrix.popPose();

			ev.setIncrement(ev.getIncrement() + 5);
			ev.setCanceled(true);
		}
	}

	private static ResourceLocation getTexture(String id) {
		if (textureCache.containsKey(id))
			return textureCache.get(id);

		ResourceLocation texture = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/bossbars/" + id + ".png");

		textureCache.put(id, texture);

		return texture;
	}
}
