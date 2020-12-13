package net.tslat.aoa3.client.gui.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabPlayer;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.HolidayUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.RenderUtil;
import net.tslat.aoa3.util.constant.Skills;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
public class XpParticlesRenderer {
	private static final ConcurrentHashMap<Skills, CopyOnWriteArrayList<XPParticle>> particlesMap = new ConcurrentHashMap<Skills, CopyOnWriteArrayList<XPParticle>>(15);
	private static final ResourceLocation skillsTextures = new ResourceLocation("aoa3", "textures/gui/maingui/skills.png");

	private static long lastPacketReceivedTime = 0;
	private static XPParticle lastParticleReceived = null;
	private static Skills lastParticleSkill = null;

	public static void addXpParticle(Skills skill, float xp, boolean isLevelUp) {
		if (!particlesMap.containsKey(skill))
			particlesMap.put(skill, new CopyOnWriteArrayList<XPParticle>());

		if (lastParticleSkill == skill && System.currentTimeMillis() <= lastPacketReceivedTime + 10) {
			if (lastParticleReceived != null) {
				lastParticleReceived.modifyXp(xp, isLevelUp);

				if (lastParticleReceived.levelUp) {
					CopyOnWriteArrayList<XPParticle> array = particlesMap.get(skill);

					if (array.size() > 0)
						array.remove(array.size() - 1);

					array.add(0, lastParticleReceived);
				}

				return;
			}
		}

		XPParticle particle;

		if (isLevelUp) {
			particlesMap.get(skill).add(0, particle = new XPParticle(xp, true));
		}
		else {
			particlesMap.get(skill).add(particle = new XPParticle(xp, false));
		}

		lastParticleReceived = particle;
		lastPacketReceivedTime = System.currentTimeMillis();
		lastParticleSkill = skill;
	}

	@SubscribeEvent
	public static void onRenderTick(final TickEvent.RenderTickEvent ev) {
		if (ev.phase != TickEvent.Phase.END || particlesMap.isEmpty())
			return;

		Minecraft mc = Minecraft.getInstance();
		MainWindow window = mc.getMainWindow();

		if (AoAConfig.CLIENT.showXpParticles.get()) {
			if (mc.currentScreen == null && !mc.gameSettings.hideGUI) {
				RenderSystem.disableDepthTest();
				RenderSystem.enableAlphaTest();

				long currentTime = System.currentTimeMillis();
				Iterator<Map.Entry<Skills, CopyOnWriteArrayList<XPParticle>>> mapIterator = particlesMap.entrySet().iterator();
				int skillCount = particlesMap.size();
				int scrollHeight = (int)(window.getScaledHeight() / 3f);
				int skillNum = 0;
				float rowBasedScale = (int)(1 + ((skillCount - 1) / 5f));
				float renderSize = 25 / (1 + (rowBasedScale - 1) * 0.5f);
				final double skillIconsX = (window.getScaledWidth() - (renderSize * (1 + ((Math.min(skillCount, 5) - 1) * 0.5f)))) / 2d;
				final double skillIconsY = 2;

				while (mapIterator.hasNext()) {
					Map.Entry<Skills, CopyOnWriteArrayList<XPParticle>> particleEntry = mapIterator.next();
					Skills skill = particleEntry.getKey();
					CopyOnWriteArrayList<XPParticle> particleArray = particleEntry.getValue();

					if (particleArray.isEmpty()) {
						mapIterator.remove();

						continue;
					}

					boolean isLevelUp = particleArray.get(0).levelUp;
					ArrayList<XPParticle> removalList = null;

					for (XPParticle particle : particleArray) {
						float particleLifespan = 1 - ((currentTime - particle.creationTime) / 1500f);

						if (particleLifespan >= 0.1)
							RenderUtil.drawCenteredScaledString(mc.fontRenderer, particle.xpString, (int)(window.getScaledWidth() / 2d), (int)(scrollHeight * particleLifespan), 0.5f, NumberUtil.RGB(255, 255, 255) | (int)MathHelper.clamp(255 * particleLifespan, 1, 255) << 24, RenderUtil.StringRenderType.NORMAL);

						if (particle.creationTime <= currentTime - 1800) {
							if (removalList == null)
								removalList = new ArrayList<XPParticle>();

							removalList.add(particle);
						}
					}

					int skillUvX = 0;
					int skillUvY = isLevelUp ? 50 : 0;

					switch (skill) {
						case ALCHEMY:
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
					RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
					RenderUtil.renderScaledCustomSizedTexture(newX, newY, skillUvX, skillUvY, 50, 50, renderSize, renderSize, 450, 240);
					RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);

					if (isLevelUp) {
						String lvl = String.valueOf(AdventGuiTabPlayer.getSkillLevel(skill));
						float stringScale = Math.min(1.4f, 16.8f / (float)mc.fontRenderer.getStringWidth(lvl)) / rowBasedScale;

						RenderUtil.drawCenteredScaledString(mc.fontRenderer, lvl, (int)(newX + (renderSize + 2) / 2f), (int)((newY + renderSize / 2f) - mc.fontRenderer.FONT_HEIGHT / 3f * stringScale), stringScale, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
					}

					if (removalList != null)
						particleArray.removeAll(removalList);

					if (particleArray.isEmpty())
						mapIterator.remove();

					skillNum++;
				}

				RenderSystem.enableDepthTest();
				RenderSystem.disableAlphaTest();
			}
			else if (!particlesMap.isEmpty()) {
				Iterator<Map.Entry<Skills, CopyOnWriteArrayList<XPParticle>>> mapIterator = particlesMap.entrySet().iterator();
				long cutoffTime = System.currentTimeMillis() - 1800;

				while (mapIterator.hasNext()) {
					CopyOnWriteArrayList<XPParticle> particleArray = mapIterator.next().getValue();

					particleArray.removeIf(particle -> particle.creationTime <= cutoffTime);

					if (particleArray.isEmpty())
						mapIterator.remove();
				}
			}
		}
		else if (!particlesMap.isEmpty())  {
			particlesMap.clear();
		}
	}

	static class XPParticle {
		boolean levelUp;
		float xp;
		String xpString;

		protected long creationTime = System.currentTimeMillis();

		XPParticle(float xp, boolean isLevelUp) {
			this.levelUp = isLevelUp;
			this.xp = xp;
			this.xpString = HolidayUtil.getCurrentHoliday() == HolidayUtil.Holiday.APRIL_FOOLS ? getAprilFoolsXpString() : "+" + NumberUtil.floorAndAppendSuffix(xp, false);
		}

		protected void modifyXp(float additionalXp, boolean isLevelUp) {
			this.levelUp = levelUp || isLevelUp;
			this.xp = xp + additionalXp;
			this.xpString = HolidayUtil.getCurrentHoliday() == HolidayUtil.Holiday.APRIL_FOOLS ? getAprilFoolsXpString() : "+" + NumberUtil.floorAndAppendSuffix(xp, false);
		}
	}

	private static String getAprilFoolsXpString() {
		return RandomUtil.getRandomSelection(
				"Nice!",
				"69xp",
				"420xp",
				"-1xp",
				"422180734982xp",
				"Xp Get!",
				"GAINSSS",
				"5xp maybe?",
				"⌈(⌈1*50^1.3⌉/8+800)/46*37⌉+6xp",
				"?",
				"Where am I?"
		);
	}
}
