package net.tslat.aoa3.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.packets.ScreenOverlayPacket;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ScreenOverlayRenderer {
	public static int overlayTicks = 0;
	public static ScreenOverlayPacket.Type screen;
	// TODO look at stacked layers?
	@SubscribeEvent
	public void renderOverlay(final RenderGameOverlayEvent.Post event) {
		if (overlayTicks == 0 || event.isCanceled() || event.getType() != RenderGameOverlayEvent.ElementType.HELMET || Minecraft.getInstance().gameSettings.thirdPersonView != 0)
			return;

		Minecraft mc = Minecraft.getInstance();
		MainWindow window = mc.getMainWindow();

		mc.getTextureManager().bindTexture(new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/effect/" + screen.toString().toLowerCase() + ".png"));

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
}
