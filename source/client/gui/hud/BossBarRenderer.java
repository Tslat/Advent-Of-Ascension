package net.tslat.aoa3.client.gui.hud;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.RegistryUtil;
import net.tslat.aoa3.util.RenderUtil;

import java.util.Map;

public final class BossBarRenderer {
	public static final int STANDARD_BAR_WIDTH = 196;
	private static final Map<ResourceLocation, ResourceLocation> BAR_ID_CACHE = new Object2ObjectOpenHashMap<>();

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, CustomizeGuiOverlayEvent.BossEventProgress.class, BossBarRenderer::onBossInfoRender);
	}

	private static void onBossInfoRender(final CustomizeGuiOverlayEvent.BossEventProgress ev) {
		if (ev.isCanceled() || Minecraft.getInstance().level == null)
			return;

		LerpingBossEvent bossStatusInfo = ev.getBossEvent();

		if (bossStatusInfo.getColor() != BossEvent.BossBarColor.GREEN || bossStatusInfo.getOverlay() != BossEvent.BossBarOverlay.NOTCHED_20)
			return;

		Minecraft mc = Minecraft.getInstance();
		Entity entity = mc.level.getEntities().get(bossStatusInfo.getId());

		if (entity == null)
			return;

		RenderUtil.resetShaderColour();
		RenderUtil.prepRenderTexture(BAR_ID_CACHE.computeIfAbsent(RegistryUtil.getId(entity.getType()), key -> new ResourceLocation(key.getNamespace(), "textures/gui/bossbars/" + key.getPath() + ".png")));

		Window window = mc.getWindow();
		PoseStack poseStack = ev.getPoseStack();
		int xPos = window.getGuiScaledWidth() / 2 - 100;
		int yPos = ev.getY();
		float progressWidth = bossStatusInfo.getProgress() * STANDARD_BAR_WIDTH;
		Component displayName = bossStatusInfo.getName();

		if (progressWidth < STANDARD_BAR_WIDTH)
			RenderUtil.renderCustomSizedTexture(poseStack, xPos, yPos, 0, 12, 200, 12, 200, 36);

		if (progressWidth > 0)
			RenderUtil.renderCustomSizedTexture(poseStack, xPos + 2, yPos, 2, 0, progressWidth, 12, 200, 36);

		RenderUtil.renderCustomSizedTexture(poseStack, xPos, yPos, 0, 24, 200, 12, 200, 36);
		mc.font.drawShadow(poseStack, displayName, window.getGuiScaledWidth() / 2 - mc.font.width(displayName) / 2, yPos - 9, ColourUtil.WHITE);

		ev.setIncrement(ev.getIncrement() + 5);
		ev.setCanceled(true);
	}
}
