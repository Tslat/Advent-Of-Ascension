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

public class SkillsRenderer {
	private final Minecraft mc;
	private static final ResourceLocation skills = new ResourceLocation("aoa3:textures/gui/maingui/skills.png");

	public SkillsRenderer() {
		mc = Minecraft.getMinecraft();
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public void onRenderTick(final TickEvent.RenderTickEvent ev) {
		if (mc.currentScreen == null && !mc.gameSettings.hideGUI && !mc.player.isSpectator() && ConfigurationUtil.MainConfig.skillsEnabled) {
			GlStateManager.pushMatrix();
			GlStateManager.disableDepth();
			GlStateManager.scale(0.5f, 0.5f, 0.5f);
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			GlStateManager.enableAlpha();

			final ScaledResolution res = new ScaledResolution(mc);
			int renderOffsetY = getPotionGuiRenderOffset();
			int renderOffsetX = (int)((res.getScaledWidth() - 200) / 0.5f);

			if (ConfigurationUtil.MainConfig.hudResourcesPosition == ResourcesRenderer.HudResourcesPosition.Top_Right) {
				if (!ConfigurationUtil.MainConfig.hudResourcesHorizontal) {
					if (KeyBinder.statusResourceGui)
						renderOffsetX -= 50;
				}
				else {
					if (KeyBinder.statusResourceGui) {
						renderOffsetY += 50;
					}
					else if (KeyBinder.statusResourceGuiMessage) {
						renderOffsetY += 15;
					}
				}
			}

			renderSkills(renderOffsetX, renderOffsetY);

			GlStateManager.disableAlpha();
			GlStateManager.popMatrix();
		}
	}

	private void renderSkills(int rootX, int rootY) {
		if (KeyBinder.statusSkillGui) {
			int x;
			int y;

			for (Enums.Skills skill : Enums.Skills.values()) {
				mc.getTextureManager().bindTexture(skills);
				GlStateManager.color(1f, 1f, 1f);

				y = rootY + (int)Math.floor(skill.id / 8f) * 50;
				x = rootX + 50 * (skill.id % 8);
				int progressBarPercent = AdventGuiTabPlayer.getPercentCompleteLevel(skill);
				int uvX = 0;
				int uvY = 0;
				int optionalUvX = -1;
				int optionalUvY = -1;
				int level = AdventGuiTabPlayer.getSkillLevel(skill);

				switch (skill) {
					case ALCHEMY:
						uvY = level >= 100 ? 50 : 0;
						break;
					case ANIMA:
						uvX = 50;
						uvY = level >= 100 ? 50 : 0;
						break;
					case AUGURY:
						uvX = 100;
						uvY = level >= 100 ? 50 : 0;
						break;
					case BUTCHERY:
						uvX = 150;
						uvY = level >= 100 ? 50 : 0;
						break;
					case CREATION:
						uvX = 200;
						uvY = level >= 100 ? 50 : 0;
						break;
					case ENGINEERING:
						uvX = 250;
						uvY = level >= 100 ? 50 : 0;
						break;
					case EXPEDITION:
						uvX = 300;
						uvY = level >= 100 ? 50 : 0;

						switch (AdventGuiTabPlayer.getOptionalSkillData(Enums.Skills.EXPEDITION) % 4) {
							case 1:
								optionalUvX = 300;
								optionalUvY = 100;
								break;
							case 2:
								optionalUvX = 324;
								optionalUvY = 100;
								break;
							case 3:
								optionalUvX = 348;
								optionalUvY = 100;
								break;
							default:
								break;
						}
						break;
					case EXTRACTION:
						uvX = 350;
						uvY = level >= 100 ? 50 : 0;
						break;
					case FORAGING:
						uvX = 400;
						uvY = level >= 100 ? 50 : 0;
						break;
					case HAULING:
						uvY = 100 + (level >= 100 ? 50 : 0);
						break;
					case HUNTER:
						uvX = 50;
						uvY = 100 + (level >= 100 ? 50 : 0);
						break;
					case INFUSION:
						uvX = 100;
						uvY = 100 + (level >= 100 ? 50 : 0);
						break;
					case INNERVATION:
						uvX = 150;
						uvY = 100 + (level >= 100 ? 50 : 0);
						break;
					case LOGGING:
						uvX = 200;
						uvY = 100 + (level >= 100 ? 50 : 0);
						break;
					case RUNATION:
						uvX = 250;
						uvY = 100 + (level >= 100 ? 50 : 0);
						break;
				}

				RenderUtil.drawScaledCustomSizeModalRect(x, y, uvX, uvY, 50, 50, 50, 50, 450, 240);
				RenderUtil.drawScaledCustomSizeModalRect(x, y + 37, 0, 200, 100, 20, 50, 13, 450, 240);
				RenderUtil.drawScaledCustomSizeModalRect(x, y + 37, 0, 220, progressBarPercent, 20, progressBarPercent / 100f * 50f, 13, 450, 240);

				if (optionalUvX >= 0)
					RenderUtil.drawScaledCustomSizeModalRect(x, y, optionalUvX, optionalUvY, 24, 24, 24, 24, 450, 240);

				RenderUtil.drawScaledString(mc.fontRenderer, String.valueOf(level), x + 45 - (int)(mc.fontRenderer.getStringWidth(String.valueOf(level)) * 1.5f), y, 1.5f, level < 100 ? Enums.RGBIntegers.WHITE : Enums.RGBIntegers.GOLD_YELLOW, RenderUtil.StringRenderType.OUTLINED);
			}
		}
		else if (KeyBinder.statusSkillGuiMessage && KeyBinder.keySkillGui.getKeyCode() != 0) {
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleStringWithArguments("gui.skills.showTip", KeyBinder.keySkillGui.getDisplayName()), rootX + 228, rootY + 2, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
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
}
