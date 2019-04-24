package net.tslat.aoa3.client.gui.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;

public class SniperGuiRenderer {
	public static Enums.ScopeScreens screen;
	public static boolean isSniping = false;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void fovEvent(final FOVUpdateEvent event) {
		if (SniperGuiRenderer.isSniping)
			event.setNewfov(0.2f);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderScopeScreenPre(final RenderGameOverlayEvent.Pre event) {
		if (isSniping && event.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
			event.setCanceled(true);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderScopeScreen(final RenderGameOverlayEvent.Post event) {
		if (!isSniping || event.isCanceled() || event.getType() != RenderGameOverlayEvent.ElementType.HELMET || Minecraft.getMinecraft().gameSettings.thirdPersonView != 0)
			return;

		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution res = new ScaledResolution(mc);

		switch(screen) {
			case BASIC:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/snipers/scope_basic.png"));
				break;
			case BOULDER:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/snipers/scope_boulder.png"));
				break;
			case CANDY:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/snipers/scope_candy.png"));
				break;
			case CRYSTAL:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/snipers/scope_crystal.png"));
				break;
			case DIAMOND:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/snipers/scope_diamond.png"));
				break;
			case DISCHARGE:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/snipers/scope_discharge.png"));
				break;
			case DOTTED:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/snipers/scope_dotted.png"));
				break;
			case FLORO:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/snipers/scope_floro.png"));
				break;
			case MARKER:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/snipers/scope_marker.png"));
				break;
			case MONSTER:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/snipers/scope_monster.png"));
				break;
			case MOON:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/snipers/scope_moon.png"));
				break;
			case REDLIGHT:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/snipers/scope_redlight.png"));
				break;
			case SCRATCHES:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/snipers/scope_scratches.png"));
				break;
			default:
				return;
		}

		final Tessellator tess = Tessellator.getInstance();
		final BufferBuilder buff = tess.getBuffer();

		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		buff.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
		buff.pos(0.0D, res.getScaledHeight_double(), -90.0D).tex(0.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.pos(res.getScaledWidth_double(), res.getScaledHeight_double(), -90.0D).tex(1.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.pos(res.getScaledWidth_double(), 0.0, -90.0D).tex(1.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		tess.draw();
		GlStateManager.depthMask(true);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderHand(RenderSpecificHandEvent event) {
		if (isSniping)
			event.setCanceled(true);
	}
}