package net.tslat.aoa3.client.gui.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.client.event.KeyBinder;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.RenderUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.skills.AuguryUtil;

public class ResourcesRenderer {
	private final Minecraft mc;

	public static boolean revengeActive = false;

	private static final ResourceLocation resources = new ResourceLocation("aoa3:textures/gui/maingui/resources.png");

	public ResourcesRenderer() {
		mc = Minecraft.getMinecraft();
		revengeActive = false;
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public void onRenderTick(final TickEvent.RenderTickEvent ev) {
		if (mc.currentScreen == null && !mc.gameSettings.hideGUI && !mc.player.isSpectator() && ConfigurationUtil.MainConfig.resourcesEnabled) {
			GlStateManager.pushMatrix();
			GlStateManager.disableDepth();
			GlStateManager.scale(0.5f, 0.5f, 0.5f);
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			GlStateManager.enableAlpha();

			final ScaledResolution res = new ScaledResolution(mc);

			if (ConfigurationUtil.MainConfig.hudResourcesHorizontal) {
				switch (ConfigurationUtil.MainConfig.hudResourcesPosition) {
					case Bottom_Right:
						renderHorizontalResources((int)((res.getScaledWidth() - 150) / 0.5f), (int)((res.getScaledHeight() - 25) / 0.5f));
						break;
					case Bottom_Left:
						renderHorizontalResources(0, (int)((res.getScaledHeight() - 25) / 0.5f));
						break;
					case Top_Left:
						renderHorizontalResources(0, 0);
						break;
					case Top_Right:
					default:
						renderHorizontalResources((int)((res.getScaledWidth() - 150) / 0.5f), getPotionGuiRenderOffset());
						break;
				}
			}
			else {
				switch (ConfigurationUtil.MainConfig.hudResourcesPosition) {
					case Bottom_Right:
						renderVerticalResources((int)((res.getScaledWidth() - 25) / 0.5f), (int)((res.getScaledHeight() - 150) / 0.5f));
						break;
					case Bottom_Left:
						renderVerticalResources(0, (int)((res.getScaledHeight() - 150) / 0.5f));
						break;
					case Top_Left:
						renderVerticalResources(0, 0);
						break;
					case Top_Right:
					default:
						renderVerticalResources((int)((res.getScaledWidth() - 25) / 0.5f), getPotionGuiRenderOffset());
						break;
				}
			}

			GlStateManager.disableAlpha();
			GlStateManager.popMatrix();
		}
	}

	private int getPotionGuiRenderOffset() {
		if (mc.player.getActivePotionEffects().isEmpty())
			return 0;

		int effectRenderYOffset = 0;

		for (PotionEffect effect : mc.player.getActivePotionEffects()) {
			if (effect.getDuration() > 0 && effect.getPotion().shouldRenderHUD(effect) && effect.doesShowParticles()) {
				if (effect.getPotion().isBadEffect()) {
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

	private void renderHorizontalResources(int rootX, int rootY) {
		if (KeyBinder.statusResourceGui) {
			mc.getTextureManager().bindTexture(resources);

			if (revengeActive)
				RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY, 100, 90, 50, 50, 50, 50, 400, 590);

			float percentComplete = AdventGuiTabPlayer.resourceRage / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 50, rootY, 0, 190, 50, 50, 50, 50, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 50, rootY, AdventGuiTabPlayer.resourceRage >= 150 ? 50 : 0, 240, percentComplete * 50, 50, percentComplete * 50, 50, 400, 590);

			percentComplete = AdventGuiTabPlayer.tributeSelyan / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY, 0, 490, 50, 15, 50, 15, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY, 0, 540, percentComplete * 50, 15, percentComplete * 50, 15, 400, 590);

			percentComplete = AdventGuiTabPlayer.tributeLuxon / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY + 15, 0, 505, 50, 10, 50, 10, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY + 15, 0, 555, percentComplete * 50, 10, percentComplete * 50, 10, 400, 590);

			percentComplete = AdventGuiTabPlayer.tributeErebon / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY + 25, 0, 515, 50, 10, 50, 10, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY + 25, 0, 565, percentComplete * 50, 10, percentComplete * 50, 10, 400, 590);

			percentComplete = AdventGuiTabPlayer.tributePluton / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY + 35, 0, 525, 50, 15, 50, 15, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY + 35, 0, 575, percentComplete * 50, 15, percentComplete * 50, 15, 400, 590);

			percentComplete = AdventGuiTabPlayer.resourceEnergy / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 150, rootY, 0, 90, 50, 50, 50, 50, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 150, rootY, 0, 140, percentComplete * 50, 50, percentComplete * 50, 50, 400, 590);

			float currentMax = AuguryUtil.getMaxCreation(AdventGuiTabPlayer.getSkillLevel(Enums.Skills.AUGURY));
			percentComplete = AdventGuiTabPlayer.resourceCreation / currentMax;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 200, rootY, 0, 290, 50, 50, 50, 50, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 200, rootY, 0, 340, percentComplete * 50, 50, percentComplete * 50, 50, 400, 590);

			currentMax = AuguryUtil.getMaxSoul(AdventGuiTabPlayer.getSkillLevel(Enums.Skills.AUGURY));
			percentComplete = AdventGuiTabPlayer.resourceSoul / currentMax;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 250, rootY, 0, 390, 50, 50, 50, 50, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 250, rootY, 0, 440, percentComplete * 50, 50, percentComplete * 50, 50, 400, 590);

			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceRage), rootX + 76, rootY + 28, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceEnergy), rootX + 176, rootY + 28, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceCreation), rootX + 226, rootY + 28, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceSoul), rootX + 276, rootY + 28, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
		}
		else if (KeyBinder.statusResourceGuiMessage && KeyBinder.keyResourceGui.getKeyCode() != 0) {
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleStringWithArguments("gui.resources.showTip", KeyBinder.keyResourceGui.getDisplayName()), rootX + 150, rootY + 2, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
		}
	}

	private void renderVerticalResources(int rootX, int rootY) {
		if (KeyBinder.statusResourceGui) {
			mc.getTextureManager().bindTexture(resources);

			if (revengeActive)
				RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY, 0, 500, 50, 50, 50, 50, 400, 590);

			float percentComplete = AdventGuiTabPlayer.resourceRage / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 50, 0, 190, 50, 50, 50, 50, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 50, AdventGuiTabPlayer.resourceRage >= 150 ? 50 : 0, 240, percentComplete * 50, 50, percentComplete * 50, 50, 400, 590);

			percentComplete = AdventGuiTabPlayer.tributeSelyan / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 100, 0, 490, 50, 15, 50, 15, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 100, 0, 540, percentComplete * 50, 15, percentComplete * 50, 15, 400, 590);

			percentComplete = AdventGuiTabPlayer.tributeLuxon / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 115, 0, 505, 50, 10, 50, 10, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 115, 0, 555, percentComplete * 50, 10, percentComplete * 50, 10, 400, 590);

			percentComplete = AdventGuiTabPlayer.tributeErebon / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 125, 0, 515, 50, 10, 50, 10, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 125, 0, 565, percentComplete * 50, 10, percentComplete * 50, 10, 400, 590);

			percentComplete = AdventGuiTabPlayer.tributePluton / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 135, 0, 525, 50, 15, 50, 15, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 135, 0, 575, percentComplete * 50, 15, percentComplete * 50, 15, 400, 590);

			percentComplete = AdventGuiTabPlayer.resourceEnergy / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 150, 0, 90, 50, 50, 50, 50, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 150, 0, 140, percentComplete * 50, 50, percentComplete * 50, 50, 400, 590);

			float currentMax = AuguryUtil.getMaxCreation(AdventGuiTabPlayer.getSkillLevel(Enums.Skills.AUGURY));
			percentComplete = AdventGuiTabPlayer.resourceCreation / currentMax;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 200, 0, 290, 50, 50, 50, 50, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 200, 0, 340, percentComplete * 50, 50, percentComplete * 50, 50, 400, 590);

			currentMax = AuguryUtil.getMaxSoul(AdventGuiTabPlayer.getSkillLevel(Enums.Skills.AUGURY));
			percentComplete = AdventGuiTabPlayer.resourceCreation / currentMax;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 250, 0, 390, 50, 50, 50, 50, 400, 590);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 250, 0, 440, percentComplete * 50, 50, percentComplete * 50, 50, 400, 590);

			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceRage), rootX + 26, rootY + 76, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceEnergy), rootX + 26, rootY + 176, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceCreation), rootX + 26, rootY + 226, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceSoul), rootX + 26, rootY + 276, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
		}
		else if (KeyBinder.statusResourceGuiMessage && KeyBinder.keyResourceGui.getKeyCode() != 0) {
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleStringWithArguments("gui.resources.showTip", KeyBinder.keyResourceGui.getDisplayName()), rootX + 150, rootY + 2, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
		}
	}
}
