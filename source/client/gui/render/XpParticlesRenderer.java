package net.tslat.aoa3.client.gui.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.RenderUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class XpParticlesRenderer {
	private static final ConcurrentHashMap<Enums.Skills, CopyOnWriteArrayList<XPParticle>> particlesMap = new ConcurrentHashMap<Enums.Skills, CopyOnWriteArrayList<XPParticle>>(15);
	private static final ResourceLocation skillsTextures = new ResourceLocation("aoa3", "textures/gui/maingui/skills.png");

	private static long lastPacketReceivedTime = 0;
	private static XPParticle lastParticleReceived = null;
	private static Enums.Skills lastParticleSkill = null;

	public static void addXpParticle(Enums.Skills skill, float xp, boolean isLevelUp) {
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
				GlStateManager.enableAlpha();

				long currentTime = System.currentTimeMillis();
				Iterator<Map.Entry<Enums.Skills, CopyOnWriteArrayList<XPParticle>>> mapIterator = particlesMap.entrySet().iterator();
				int skillCount = particlesMap.size();
				int scrollHeight = (int)(res.getScaledHeight() / 3f);
				int skillNum = 0;
				float rowBasedScale = (int)(1 + ((skillCount - 1) / 5f));
				float renderSize = 25 / (1 + (rowBasedScale - 1) * 0.5f);
				final double skillIconsX = (res.getScaledWidth_double() - (renderSize * (1 + ((Math.min(skillCount, 5) - 1) * 0.5f)))) / 2d;
				final double skillIconsY = 2;

				while (mapIterator.hasNext()) {
					Map.Entry<Enums.Skills, CopyOnWriteArrayList<XPParticle>> particleEntry = mapIterator.next();
					Enums.Skills skill = particleEntry.getKey();
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
							RenderUtil.drawCenteredScaledString(mc.fontRenderer, particle.xpString, (int)(res.getScaledWidth_double() / 2d), (int)(scrollHeight * particleLifespan), 0.5f, Enums.RGBIntegers.WHITE | (int)MathHelper.clamp(255 * particleLifespan, 1, 255) << 24, RenderUtil.StringRenderType.NORMAL);

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
					GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
					RenderUtil.drawScaledCustomSizeModalRect(newX, newY, skillUvX, skillUvY, 50, 50, renderSize, renderSize, 450, 240);
					GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

					if (isLevelUp) {
						String lvl = String.valueOf(AdventGuiTabPlayer.getSkillLevel(skill));
						float stringScale = Math.min(1.4f, 16.8f / (float)mc.fontRenderer.getStringWidth(lvl)) / rowBasedScale;

						RenderUtil.drawCenteredScaledString(mc.fontRenderer, lvl, (int)(newX + (renderSize + 2) / 2f), (int)((newY + renderSize / 2f) - mc.fontRenderer.FONT_HEIGHT / 3f * stringScale), stringScale, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
					}

					if (removalList != null)
						particleArray.removeAll(removalList);

					if (particleArray.isEmpty())
						mapIterator.remove();

					skillNum++;
				}

				GlStateManager.enableDepth();
				GlStateManager.disableAlpha();
			}
			else if (!particlesMap.isEmpty()) {
				Iterator<Map.Entry<Enums.Skills, CopyOnWriteArrayList<XPParticle>>> mapIterator = particlesMap.entrySet().iterator();
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
			this.xpString = AdventOfAscension.instance().holiday == AdventOfAscension.Holiday.APRIL_FOOLS ? getAprilFoolsXpString() : "+" + StringUtil.floorAndAppendSuffix(xp, false);
		}

		protected void modifyXp(float additionalXp, boolean isLevelUp) {
			this.levelUp = levelUp || isLevelUp;
			this.xp = xp + additionalXp;
			this.xpString = AdventOfAscension.instance().holiday == AdventOfAscension.Holiday.APRIL_FOOLS ? getAprilFoolsXpString() : "+" + StringUtil.floorAndAppendSuffix(xp, false);
		}
	}

	private static String getAprilFoolsXpString() {
		switch (AdventOfAscension.rand.nextInt(9)) {
			case 0:
				return "Nice!";
			case 1:
				return "69xp";
			case 2:
				return "420xp";
			case 3:
				return "-1xp";
			case 4:
				return "422180734982xp";
			case 5:
				return "Xp Get!";
			case 6:
				return "GAINSSS";
			case 7:
				return "5xp maybe?";
			case 8:
			default:
				return "⌈(⌈A1*50^1.3⌉/8+800)/46*37⌉+6xp";
		}
	}
}
