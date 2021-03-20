package net.tslat.aoa3.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.item.armour.AdventArmour;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
public class HelmetScreenRenderer {
	public static boolean active = false;
	public static AdventArmour.Overlay type;

	@SubscribeEvent
	public static void renderHelmetScreen(final RenderGameOverlayEvent.Post ev) {
		if (!active || ev.isCanceled() || ev.getType() != RenderGameOverlayEvent.ElementType.HELMET || Minecraft.getInstance().options.getCameraType() != PointOfView.FIRST_PERSON)
			return;

		Minecraft mc = Minecraft.getInstance();
		MainWindow window = mc.getWindow();
		Matrix4f matrix = ev.getMatrixStack().last().pose();

		switch (type) {
			case NIGHT_VISION_GOGGLES:
				mc.getTextureManager().bind(new ResourceLocation("aoa3:textures/gui/overlay/helmet/night_vision_goggles.png"));
				break;
			default:
				return;
		}

		final Tessellator tess = Tessellator.getInstance();
		final BufferBuilder buff = tess.getBuilder();

		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		buff.begin(7, DefaultVertexFormats.POSITION_TEX);
		buff.vertex(matrix, 0f, window.getGuiScaledHeight(), -90f).uv(0.0f, 1.0f).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.vertex(matrix, window.getGuiScaledWidth(), window.getGuiScaledHeight(), -90f).uv(1.0f, 1.0f).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.vertex(matrix, window.getGuiScaledWidth(), 0f, -90f).uv(1.0f, 0.0f).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.vertex(matrix, 0f, 0f, -90f).uv(0.0f, 0.0f).normal(0.0F, 1.0F, 0.0F).endVertex();
		tess.end();
		RenderSystem.depthMask(true);
	}
}
