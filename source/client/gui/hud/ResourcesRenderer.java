package net.tslat.aoa3.client.gui.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.Keybinds;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabPlayer;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RenderUtil;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ResourcesRenderer {
	private static final ResourceLocation resources = new ResourceLocation("aoa3:textures/gui/maingui/resources.png");

	public static boolean revengeActive = false;

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onRenderTick(final TickEvent.RenderTickEvent ev) {
		Minecraft mc = Minecraft.getInstance();
		
		if (mc.currentScreen == null && !mc.gameSettings.hideGUI && mc.player != null && !mc.player.isSpectator() && AoAConfig.COMMON.resourcesEnabled.get()) {
			RenderSystem.pushMatrix();
			RenderSystem.disableDepthTest();
			RenderSystem.scalef(0.5f, 0.5f, 0.5f);
			RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
			RenderSystem.enableAlphaTest();

			int scaledWidth = mc.getMainWindow().getScaledWidth();
			int scaledHeight = mc.getMainWindow().getScaledHeight();
			
			if (AoAConfig.CLIENT.hudResourcesHorizontal.get()) {
				switch (AoAConfig.CLIENT.hudResourcesPosition.get()) {
					case Bottom_Right:
						renderHorizontalResources(mc, (int)((scaledWidth - 150) / 0.5f), (int)((scaledHeight - 25) / 0.5f));
						break;
					case Bottom_Left:
						renderHorizontalResources(mc, 0, (int)((scaledHeight - 25) / 0.5f));
						break;
					case Top_Left:
						renderHorizontalResources(mc, 0, 0);
						break;
					case Top_Right:
					default:
						renderHorizontalResources(mc, (int)((scaledWidth - 150) / 0.5f), getPotionGuiRenderOffset(mc));
						break;
				}
			}
			else {
				switch (AoAConfig.CLIENT.hudResourcesPosition.get()) {
					case Bottom_Right:
						renderVerticalResources(mc, (int)((scaledWidth - 25) / 0.5f), (int)((scaledHeight - 150) / 0.5f));
						break;
					case Bottom_Left:
						renderVerticalResources(mc, 0, (int)((scaledHeight - 150) / 0.5f));
						break;
					case Top_Left:
						renderVerticalResources(mc, 0, 0);
						break;
					case Top_Right:
					default:
						renderVerticalResources(mc, (int)((scaledWidth - 25) / 0.5f), getPotionGuiRenderOffset(mc));
						break;
				}
			}

			RenderSystem.disableAlphaTest();
			RenderSystem.popMatrix();
		}
	}

	private static int getPotionGuiRenderOffset(Minecraft mc) {
		if (mc.player.getActivePotionEffects().isEmpty() || AoAConfig.CLIENT.disableHudPotionOffset.get())
			return 0;

		int effectRenderYOffset = 0;

		for (EffectInstance effect : mc.player.getActivePotionEffects()) {
			if (effect.getDuration() > 0 && effect.getPotion().shouldRenderHUD(effect) && effect.doesShowParticles()) {
				if (!effect.getPotion().isBeneficial()) {
					effectRenderYOffset = 100;
					break;
				}
				else {
					effectRenderYOffset = 50;
				}
			}
		}

		return effectRenderYOffset;
	}

	public enum HudResourcesPosition {
		Top_Right,
		Top_Left,
		Bottom_Right,
		Bottom_Left
	}

	private static void renderHorizontalResources(Minecraft mc, int rootX, int rootY) {
		if (Keybinds.statusResourceGui) {
			mc.getTextureManager().bindTexture(resources);

			if (revengeActive)
				RenderUtil.renderScaledCustomSizedTexture(rootX, rootY, 100, 90, 50, 50, 50, 50, 400, 590);

			float percentComplete = (float)Math.floor(AdventGuiTabPlayer.resourceRage / 200f * 50);

			RenderUtil.renderScaledCustomSizedTexture(rootX + 50, rootY, 0, 190, 50, 50, 50, 50, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX + 50, rootY, AdventGuiTabPlayer.resourceRage >= 180 ? 50 : 0, 240, percentComplete, 50, percentComplete, 50, 400, 590);

			percentComplete = (float)Math.floor(AdventGuiTabPlayer.tributeSelyan / 200f * 50);

			RenderUtil.renderScaledCustomSizedTexture(rootX + 100, rootY, 0, 490, 50, 15, 50, 15, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX + 100, rootY, 0, 540, percentComplete, 15, percentComplete, 15, 400, 590);

			percentComplete = (float)Math.floor(AdventGuiTabPlayer.tributeLuxon / 200f * 50);

			RenderUtil.renderScaledCustomSizedTexture(rootX + 100, rootY + 15, 0, 505, 50, 10, 50, 10, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX + 100, rootY + 15, 0, 555, percentComplete, 10, percentComplete, 10, 400, 590);

			percentComplete = (float)Math.floor(AdventGuiTabPlayer.tributeErebon / 200f * 50);

			RenderUtil.renderScaledCustomSizedTexture(rootX + 100, rootY + 25, 0, 515, 50, 10, 50, 10, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX + 100, rootY + 25, 0, 565, percentComplete, 10, percentComplete, 10, 400, 590);

			percentComplete = (float)Math.floor(AdventGuiTabPlayer.tributePluton / 200f * 50);

			RenderUtil.renderScaledCustomSizedTexture(rootX + 100, rootY + 35, 0, 525, 50, 15, 50, 15, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX + 100, rootY + 35, 0, 575, percentComplete, 15, percentComplete, 15, 400, 590);

			percentComplete = (float)Math.floor(AdventGuiTabPlayer.resourceEnergy / 200f * 50);

			RenderUtil.renderScaledCustomSizedTexture(rootX + 150, rootY, 0, 90, 50, 50, 50, 50, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX + 150, rootY, 0, 140, percentComplete, 50, percentComplete, 50, 400, 590);

			percentComplete = (float)Math.floor(AdventGuiTabPlayer.resourceCreation / 200f * 50);

			RenderUtil.renderScaledCustomSizedTexture(rootX + 200, rootY, 0, 290, 50, 50, 50, 50, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX + 200, rootY, 0, 340, percentComplete, 50, percentComplete, 50, 400, 590);

			percentComplete = (float)Math.floor(AdventGuiTabPlayer.resourceSoul / 200f * 50);

			RenderUtil.renderScaledCustomSizedTexture(rootX + 250, rootY, 0, 390, 50, 50, 50, 50, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX + 250, rootY, 0, 440, percentComplete, 50, percentComplete, 50, 400, 590);

			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceRage), rootX + 76, rootY + 28, 2f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceEnergy), rootX + 176, rootY + 28, 2f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceCreation), rootX + 226, rootY + 28, 2f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceSoul), rootX + 276, rootY + 28, 2f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
		}
		else if (Keybinds.statusResourceGuiMessage && Keybinds.keyResourceGui.getKey().getKeyCode() != -1) {
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, LocaleUtil.getLocaleString("gui.aoa3.resources.showtip", Keybinds.keyResourceGui.getLocalizedName()), rootX + 150, rootY + 2, 1.5f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
		}
	}

	private static void renderVerticalResources(Minecraft mc, int rootX, int rootY) {
		if (Keybinds.statusResourceGui) {
			mc.getTextureManager().bindTexture(resources);

			if (revengeActive)
				RenderUtil.renderScaledCustomSizedTexture(rootX, rootY, 0, 500, 50, 50, 50, 50, 400, 590);

			float percentComplete = AdventGuiTabPlayer.resourceRage / 200f;

			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 50, 0, 190, 50, 50, 50, 50, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 50, AdventGuiTabPlayer.resourceRage >= 180 ? 50 : 0, 240, percentComplete * 50, 50, percentComplete * 50, 50, 400, 590);

			percentComplete = AdventGuiTabPlayer.tributeSelyan / 200f;

			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 100, 0, 490, 50, 15, 50, 15, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 100, 0, 540, percentComplete * 50, 15, percentComplete * 50, 15, 400, 590);

			percentComplete = AdventGuiTabPlayer.tributeLuxon / 200f;

			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 115, 0, 505, 50, 10, 50, 10, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 115, 0, 555, percentComplete * 50, 10, percentComplete * 50, 10, 400, 590);

			percentComplete = AdventGuiTabPlayer.tributeErebon / 200f;

			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 125, 0, 515, 50, 10, 50, 10, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 125, 0, 565, percentComplete * 50, 10, percentComplete * 50, 10, 400, 590);

			percentComplete = AdventGuiTabPlayer.tributePluton / 200f;

			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 135, 0, 525, 50, 15, 50, 15, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 135, 0, 575, percentComplete * 50, 15, percentComplete * 50, 15, 400, 590);

			percentComplete = AdventGuiTabPlayer.resourceEnergy / 200f;

			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 150, 0, 90, 50, 50, 50, 50, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 150, 0, 140, percentComplete * 50, 50, percentComplete * 50, 50, 400, 590);

			percentComplete = AdventGuiTabPlayer.resourceEnergy / 200f;

			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 200, 0, 290, 50, 50, 50, 50, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 200, 0, 340, percentComplete * 50, 50, percentComplete * 50, 50, 400, 590);

			percentComplete = AdventGuiTabPlayer.resourceEnergy / 200f;

			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 250, 0, 390, 50, 50, 50, 50, 400, 590);
			RenderUtil.renderScaledCustomSizedTexture(rootX, rootY + 250, 0, 440, percentComplete * 50, 50, percentComplete * 50, 50, 400, 590);

			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceRage), rootX + 26, rootY + 76, 2f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceEnergy), rootX + 26, rootY + 176, 2f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceCreation), rootX + 26, rootY + 226, 2f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceSoul), rootX + 26, rootY + 276, 2f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
		}
		else if (Keybinds.statusResourceGuiMessage && Keybinds.keyResourceGui.getKey().getKeyCode() != -1) {
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, LocaleUtil.getLocaleString("gui.aoa3.resources.showtip", Keybinds.keyResourceGui.getLocalizedName()), rootX + 150, rootY + 2, 1.5f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
		}
	}
}
