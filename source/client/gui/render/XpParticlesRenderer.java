package net.tslat.aoa3.client.gui.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.RenderUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class XpParticlesRenderer {
	private static final ConcurrentHashMap<Enums.Skills, ArrayList<XPParticle>> particlesMap = new ConcurrentHashMap<Enums.Skills, ArrayList<XPParticle>>(15);
	private static final ResourceLocation skillsTextures = new ResourceLocation("aoa3", "textures/gui/maingui/skills.png");

	public static void addXpParticle(Enums.Skills skill, float xp, boolean isLevelUp) {
		if (!particlesMap.containsKey(skill))
			particlesMap.put(skill, new ArrayList<XPParticle>());

		if (isLevelUp) {
			particlesMap.get(skill).add(0, new XPParticle(xp, true));
		}
		else {
			particlesMap.get(skill).add(new XPParticle(xp, false));
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderTick(final TickEvent.RenderTickEvent ev) {
		if (ev.phase != TickEvent.Phase.END || particlesMap.isEmpty())
			return;

		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution res = new ScaledResolution(mc);

		if (ConfigurationUtil.MainConfig.showXpParticles) {
			if (mc.currentScreen == null && !mc.gameSettings.hideGUI) {
				GlStateManager.disableDepth();

				Iterator<Map.Entry<Enums.Skills, ArrayList<XPParticle>>> mapIterator = particlesMap.entrySet().iterator();
				int skillCount = particlesMap.size();
				int scrollHeight = (int)(res.getScaledHeight() / 3f);
				int skillNum = 0;
				float rowBasedScale = (int)(1 + ((skillCount - 1) / 5f));
				float renderSize = 25 / (1 + (rowBasedScale - 1) * 0.5f);
				final double skillIconsX = (res.getScaledWidth_double() - ((renderSize * (1 + (((skillCount <= 5 ? skillCount : 5) - 1) * 0.5f))))) / 2d;
				final double skillIconsY = 2;

				while (mapIterator.hasNext()) {
					Map.Entry<Enums.Skills, ArrayList<XPParticle>> particleEntry = mapIterator.next();
					Enums.Skills skill = particleEntry.getKey();
					ArrayList<XPParticle> particleArray = particleEntry.getValue();

					if (particleArray.isEmpty()) {
						mapIterator.remove();

						continue;
					}

					boolean isLevelUp = particleArray.get(0).levelUp;
					Iterator<XPParticle> particleIterator = particleArray.iterator();

					while (particleIterator.hasNext()) {
						XPParticle particle = particleIterator.next();

						RenderUtil.drawCenteredScaledString(mc.fontRenderer, particle.xpString, (int)(res.getScaledWidth_double() / 2d), scrollHeight - particle.age, 0.5f, Enums.RGBIntegers.WHITE | (int)(255 * (1 - particle.age / (float)scrollHeight)) << 24, RenderUtil.StringRenderType.NORMAL);

						if (++particle.age >= scrollHeight - 2)
							particleIterator.remove();
					}

					int skillUvX = 0;
					int skillUvY = isLevelUp ? 50 : 0;

					switch (skill) {
						case ALCHEMY:
							skillUvX = 0;
							break;
						case ANIMA:
							skillUvX = 50;
							break;
						case AUGURY:
							skillUvX = 100;
							break;
						case BUTCHERY:
							skillUvX = 150;
							break;
						case CREATION:
							skillUvX = 200;
							break;
						case ENGINEERING:
							skillUvX = 250;
							break;
						case EXPEDITION:
							skillUvX = 300;
							break;
						case EXTRACTION:
							skillUvX = 350;
							break;
						case FORAGING:
							skillUvX = 400;
							break;
						case HAULING:
							skillUvX = 0;
							skillUvY += 100;
							break;
						case HUNTER:
							skillUvX = 50;
							skillUvY += 100;
							break;
						case INFUSION:
							skillUvX = 100;
							skillUvY += 100;
							break;
						case INNERVATION:
							skillUvX = 150;
							skillUvY += 100;
							break;
						case LOGGING:
							skillUvX = 200;
							skillUvY += 100;
							break;
						case RUNATION:
							skillUvX = 250;
							skillUvY += 100;
							break;
					}

					double newX = skillIconsX + (skillNum % 5 * renderSize * 0.5f);
					double newY = skillIconsY + (skillNum / 5 * renderSize * 0.5f);

					mc.getTextureManager().bindTexture(skillsTextures);
					RenderUtil.drawScaledCustomSizeModalRect(newX, newY, skillUvX, skillUvY, 50, 50, renderSize, renderSize, 450, 240);

					if (isLevelUp) {
						String lvl = String.valueOf(AdventGuiTabPlayer.getSkillLevel(skill));
						float stringScale = Math.min(1.4f, 16.8f / (float)mc.fontRenderer.getStringWidth(lvl)) / rowBasedScale;

						RenderUtil.drawCenteredScaledString(mc.fontRenderer, lvl, (int)(newX + (renderSize + 2) / 2f), (int)((newY + renderSize / 2f) - mc.fontRenderer.FONT_HEIGHT / 3f * stringScale), stringScale, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
					}
					if (particleArray.isEmpty())
						mapIterator.remove();

					skillNum++;
				}

				GlStateManager.enableDepth();
			}
			else {
				Iterator<Map.Entry<Enums.Skills, ArrayList<XPParticle>>> mapIterator = particlesMap.entrySet().iterator();

				while (mapIterator.hasNext()) {
					ArrayList<XPParticle> particleArray = mapIterator.next().getValue();

					particleArray.removeIf(particle -> particle.age++ >= 200);

					if (particleArray.isEmpty())
						mapIterator.remove();
				}
			}
		}
		else  {
			particlesMap.clear();
		}
	}

	static class XPParticle {
		boolean levelUp;
		String xpString;

		protected int age = 0;

		XPParticle(float xp, boolean isLevelUp) {
			this.levelUp = isLevelUp;
			this.xpString = "+" + StringUtil.floorAndAppendSuffix(xp, false);
		}
	}
}
