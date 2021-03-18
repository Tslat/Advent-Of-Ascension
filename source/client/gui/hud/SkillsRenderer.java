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
import net.tslat.aoa3.util.constant.Skills;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class SkillsRenderer {
	private static final ResourceLocation skills = new ResourceLocation("aoa3:textures/gui/maingui/skills.png");

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onRenderTick(final TickEvent.RenderTickEvent ev) {
		Minecraft mc = Minecraft.getInstance();
		
		if (mc.currentScreen == null && !mc.gameSettings.hideGUI && mc.player != null && !mc.player.isSpectator() && AoAConfig.COMMON.skillsEnabled.get()) {
			RenderSystem.pushMatrix();
			RenderSystem.disableDepthTest();
			RenderSystem.scalef(0.5f, 0.5f, 0.5f);
			RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
			RenderSystem.enableAlphaTest();

			int scaledWidth = mc.getMainWindow().getScaledWidth();
			int renderOffsetY = 1 + getPotionGuiRenderOffset(mc);
			int renderOffsetX = (int)((scaledWidth - 200) / 0.5f);

			if (AoAConfig.CLIENT.hudResourcesPosition.get() == ResourcesRenderer.HudResourcesPosition.Top_Right) {
				if (!AoAConfig.CLIENT.hudResourcesHorizontal.get()) {
					if (Keybinds.statusResourceGui)
						renderOffsetX -= 50;
				}
				else {
					if (Keybinds.statusResourceGui) {
						renderOffsetY += 50;
					}
					else if (Keybinds.statusResourceGuiMessage) {
						renderOffsetY += 15;
					}
				}
			}

			renderSkills(mc, renderOffsetX, renderOffsetY);

			RenderSystem.disableAlphaTest();
			RenderSystem.popMatrix();
		}
	}

	private static void renderSkills(Minecraft mc, int rootX, int rootY) {
		if (Keybinds.statusSkillGui) {
			int x;
			int y;

			for (Skills skill : Skills.values()) {
				mc.getTextureManager().bindTexture(skills);
				RenderSystem.color4f(1f, 1f, 1f, 1f);

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

						switch (AdventGuiTabPlayer.getOptionalSkillData(Skills.EXPEDITION) % 4) {
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

				RenderUtil.renderScaledCustomSizedTexture(x, y, uvX, uvY, 50, 50, 50, 50, 450, 240);
				RenderUtil.renderScaledCustomSizedTexture(x, y + 37, 0, 200, 100, 20, 50, 13, 450, 240);
				RenderUtil.renderScaledCustomSizedTexture(x, y + 37, 0, 220, progressBarPercent, 20, progressBarPercent / 100f * 50f, 13, 450, 240);

				if (optionalUvX >= 0)
					RenderUtil.renderScaledCustomSizedTexture(x, y, optionalUvX, optionalUvY, 24, 24, 24, 24, 450, 240);

				RenderUtil.drawScaledString(mc.fontRenderer, String.valueOf(level), x + 45 - (int)(mc.fontRenderer.getStringWidth(String.valueOf(level)) * 1.5f), y, 1.5f, level < 100 ? NumberUtil.RGB(255, 255, 255) : NumberUtil.RGB(255, 223, 0), RenderUtil.StringRenderType.OUTLINED);
			}
		}
		else if (Keybinds.statusSkillGuiMessage && Keybinds.keySkillGui.getKey().getKeyCode() != -1) {
			RenderUtil.drawCenteredScaledString(mc.fontRenderer, LocaleUtil.getLocaleString("gui.aoa3.skills.showtip", Keybinds.keySkillGui.getLocalizedName()), rootX + 228, rootY + 2, 1.5f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
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
}
