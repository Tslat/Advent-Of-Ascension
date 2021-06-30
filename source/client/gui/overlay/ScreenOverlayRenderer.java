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
import net.tslat.aoa3.common.packet.packets.ScreenOverlayPacket;
import net.tslat.aoa3.common.registration.AoATags;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
public class ScreenOverlayRenderer {
	public static int overlayTicks = 0;
	public static ScreenOverlayPacket.Type screen;
	// TODO look at stacked layers?
	@SubscribeEvent
	public static void renderOverlay(final RenderGameOverlayEvent.Post event) {
		if (event.isCanceled() || Minecraft.getInstance().options.getCameraType() != PointOfView.FIRST_PERSON)
			return;

		Minecraft mc = Minecraft.getInstance();
		MainWindow window = mc.getWindow();

		if (mc.player == null)
			return;

		if (overlayTicks > 0 && event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			mc.getTextureManager().bind(new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/effect/" + screen.toString().toLowerCase() + ".png"));

			final Tessellator tess = Tessellator.getInstance();
			final BufferBuilder buff = tess.getBuilder();

			RenderSystem.pushTextureAttributes();
			RenderSystem.enableAlphaTest();
			RenderSystem.enableBlend();
			buff.begin(7, DefaultVertexFormats.POSITION_TEX);
			buff.vertex(0.0D, window.getGuiScaledHeight(), -90.0D).uv(0.0f, 1.0f).normal(0.0F, 1.0F, 0.0F).endVertex();
			buff.vertex(window.getGuiScaledWidth(), window.getGuiScaledHeight(), -90.0D).uv(1.0f, 1.0f).normal(0.0F, 1.0F, 0.0F).endVertex();
			buff.vertex(window.getGuiScaledWidth(), 0.0, -90.0D).uv(1.0f, 0.0f).normal(0.0F, 1.0F, 0.0F).endVertex();
			buff.vertex(0.0D, 0.0D, -90.0D).uv(0.0f, 0.0f).normal(0.0F, 1.0F, 0.0F).endVertex();
			tess.end();
			RenderSystem.depthMask(true);
			RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
			RenderSystem.disableAlphaTest();
			RenderSystem.disableBlend();
			RenderSystem.popAttributes();
		}

		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL && mc.player.isEyeInFluid(AoATags.Fluids.TOXIC_WASTE)) {
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
}
