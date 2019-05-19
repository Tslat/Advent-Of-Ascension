package net.tslat.aoa3.client.gui.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.client.gui.KeyBinder;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.RenderUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.skills.AuguryUtil;

public class ResourcesRenderer {
	private final Minecraft mc;

	public static boolean revengeActive = false;

	private static final ResourceLocation resources = new ResourceLocation("aoa3:textures/gui/resource/hud_resources.png");

	public ResourcesRenderer() {
		mc = Minecraft.getMinecraft();
		revengeActive = false;
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public void onRenderTick(final TickEvent.RenderTickEvent ev) {
		if (mc.currentScreen == null && !mc.gameSettings.hideGUI && !mc.player.isSpectator()) {
			int effectRenderYOffset = 0;

			if (!mc.player.getActivePotionEffects().isEmpty()) {
				for (PotionEffect effect : mc.player.getActivePotionEffects()) {
					if (effect.getDuration() > 0) {
						if (effect.getPotion().isBadEffect()) {
							effectRenderYOffset = 100;
							break;
						}
						else {
							effectRenderYOffset = 50;
						}
					}
				}
			}

			GlStateManager.pushMatrix();
			GlStateManager.disableDepth();
			GlStateManager.scale(0.5f, 0.5f, 0.5f);

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
						renderHorizontalResources((int)((res.getScaledWidth() - 150) / 0.5f), effectRenderYOffset);
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
						renderVerticalResources((int)((res.getScaledWidth() - 25) / 0.5f), effectRenderYOffset);
						break;
				}
			}

			GlStateManager.popMatrix();
		}
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
				RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY, 0, 500, 50, 50, 50, 50, 100, 550);

			float percentComplete = AdventGuiTabPlayer.resourceRage / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 50, rootY, 0, 0, 50, 50, 50, 50, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 50, rootY, AdventGuiTabPlayer.resourceRage >= 150 ? 50 : 0, 50, percentComplete * 50, 50, percentComplete * 50, 50, 100, 550);

			percentComplete = AdventGuiTabPlayer.tributeSelyan / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY, 0, 400, 50, 16, 50, 16, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY, 0, 416, percentComplete * 50, 16, percentComplete * 50, 16, 100, 550);

			percentComplete = AdventGuiTabPlayer.tributeLuxon / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY + 16, 0, 432, 50, 10, 50, 10, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY + 16, 0, 442, percentComplete * 50, 10, percentComplete * 50, 10, 100, 550);

			percentComplete = AdventGuiTabPlayer.tributeErebon / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY + 26, 0, 452, 50, 10, 50, 10, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY + 26, 0, 462, percentComplete * 50, 10, percentComplete * 50, 10, 100, 550);

			percentComplete = AdventGuiTabPlayer.tributePluton / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY + 36, 0, 472, 50, 14, 50, 14, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 100, rootY + 36, 0, 486, percentComplete * 50, 14, percentComplete * 50, 14, 100, 550);

			percentComplete = AdventGuiTabPlayer.resourceEnergy / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 150, rootY, 0, 100, 50, 50, 50, 50, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 150, rootY, 0, 150, percentComplete * 50, 50, percentComplete * 50, 50, 100, 550);

			float currentMax = AuguryUtil.getMaxCreation(AdventGuiTabPlayer.getSkillLevel(Enums.Skills.AUGURY));
			percentComplete = AdventGuiTabPlayer.resourceCreation / currentMax;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 200, rootY, 0, 200, 50, 50, 50, 50, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 200, rootY, 0, 250, percentComplete * 50, 50, percentComplete * 50, 50, 100, 550);

			currentMax = AuguryUtil.getMaxSoul(AdventGuiTabPlayer.getSkillLevel(Enums.Skills.AUGURY));
			percentComplete = AdventGuiTabPlayer.resourceCreation / currentMax;

			RenderUtil.drawScaledCustomSizeModalRect(rootX + 250, rootY, 0, 300, 50, 50, 50, 50, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX + 250, rootY, 0, 350, percentComplete * 50, 50, percentComplete * 50, 50, 100, 550);

			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceRage), rootX + 76, rootY + 28, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceEnergy), rootX + 176, rootY + 28, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceCreation), rootX + 226, rootY + 28, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceSoul), rootX + 276, rootY + 28, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
		}
		else if (KeyBinder.statusResourceGuiMessage) {
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleStringWithArguments("gui.resources.showTip", KeyBinder.keyResourceGui.getDisplayName()), rootX + 150, rootY + 2, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
		}
	}

	private void renderVerticalResources(int rootX, int rootY) {
		if (KeyBinder.statusResourceGui) {
			mc.getTextureManager().bindTexture(resources);

			if (revengeActive)
				RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY, 0, 500, 50, 50, 50, 50, 100, 550);

			float percentComplete = AdventGuiTabPlayer.resourceRage / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 50, 0, 0, 50, 50, 50, 50, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 50, AdventGuiTabPlayer.resourceRage >= 150 ? 50 : 0, 50, percentComplete * 50, 50, percentComplete * 50, 50, 100, 550);

			percentComplete = AdventGuiTabPlayer.tributeSelyan / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 100, 0, 400, 50, 16, 50, 16, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 100, 0, 416, percentComplete * 50, 16, percentComplete * 50, 16, 100, 550);

			percentComplete = AdventGuiTabPlayer.tributeLuxon / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 116, 0, 432, 50, 10, 50, 10, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 116, 0, 442, percentComplete * 50, 10, percentComplete * 50, 10, 100, 550);

			percentComplete = AdventGuiTabPlayer.tributeErebon / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 126, 0, 452, 50, 10, 50, 10, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 126, 0, 462, percentComplete * 50, 10, percentComplete * 50, 10, 100, 550);

			percentComplete = AdventGuiTabPlayer.tributePluton / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 136, 0, 472, 50, 14, 50, 14, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 136, 0, 486, percentComplete * 50, 14, percentComplete * 50, 14, 100, 550);

			percentComplete = AdventGuiTabPlayer.resourceEnergy / 200f;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 150, 0, 100, 50, 50, 50, 50, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 150, 0, 150, percentComplete * 50, 50, percentComplete * 50, 50, 100, 550);

			float currentMax = AuguryUtil.getMaxCreation(AdventGuiTabPlayer.getSkillLevel(Enums.Skills.AUGURY));
			percentComplete = AdventGuiTabPlayer.resourceCreation / currentMax;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 200, 0, 200, 50, 50, 50, 50, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 200, 0, 250, percentComplete * 50, 50, percentComplete * 50, 50, 100, 550);

			currentMax = AuguryUtil.getMaxSoul(AdventGuiTabPlayer.getSkillLevel(Enums.Skills.AUGURY));
			percentComplete = AdventGuiTabPlayer.resourceCreation / currentMax;

			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 250, 0, 300, 50, 50, 50, 50, 100, 550);
			RenderUtil.drawScaledCustomSizeModalRect(rootX, rootY + 250, 0, 350, percentComplete * 50, 50, percentComplete * 50, 50, 100, 550);

			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceRage), rootX + 26, rootY + 76, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceEnergy), rootX + 26, rootY + 176, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceCreation), rootX + 26, rootY + 226, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, String.valueOf((int)AdventGuiTabPlayer.resourceSoul), rootX + 26, rootY + 276, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
		}
		else if (KeyBinder.statusResourceGuiMessage) {
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleStringWithArguments("gui.resources.showTip", KeyBinder.keyResourceGui.getDisplayName()), rootX + 150, rootY + 2, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
		}
	}
}
