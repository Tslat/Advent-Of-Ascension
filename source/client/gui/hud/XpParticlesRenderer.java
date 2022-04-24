package net.tslat.aoa3.client.gui.hud;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.tslat.aoa3.client.render.AoAGuiElementRenderers;
import net.tslat.aoa3.client.render.custom.AoASkillRenderer;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.*;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public final class XpParticlesRenderer {
	private static final ConcurrentHashMap<AoASkill, CopyOnWriteArrayList<XPParticle>> particlesMap = new ConcurrentHashMap<>(15);

	private static long lastPacketReceivedTime = 0;
	private static XPParticle lastParticleReceived = null;
	private static AoASkill lastParticleSkill = null;

	public static void init() {
		OverlayRegistry.registerOverlayTop("AoA Xp Particles", XpParticlesRenderer::renderParticles);
	}

	public static void addXpParticle(AoASkill skill, float xp, boolean isLevelUp) {
		if (!particlesMap.containsKey(skill))
			particlesMap.put(skill, new CopyOnWriteArrayList<>());

		if (lastParticleSkill == skill && lastParticleReceived != null && System.currentTimeMillis() <= lastPacketReceivedTime + 10) {
			lastParticleReceived.modifyXp(xp, isLevelUp);

			if (lastParticleReceived.levelUp) {
				CopyOnWriteArrayList<XPParticle> array = particlesMap.get(skill);

				if (array.size() > 0)
					array.remove(array.size() - 1);

				array.add(0, lastParticleReceived);
			}

			return;
		}

		XPParticle particle;

		if (isLevelUp) {
			particlesMap.get(skill).add(0, particle = new XPParticle(xp, true));
		}
		else {
			particlesMap.get(skill).add(particle = new XPParticle(xp, false));
		}

		lastParticleReceived = particle;
		lastParticleSkill = skill;
		lastPacketReceivedTime = System.currentTimeMillis();
	}

	private static void purgeExpiredEntries() {
		if (!AoAConfig.CLIENT.showXpParticles.get()) {
			particlesMap.clear();

			return;
		}

		Iterator<Map.Entry<AoASkill, CopyOnWriteArrayList<XPParticle>>> particleEntries = particlesMap.entrySet().iterator();
		long currentTime = System.currentTimeMillis();
		long expiryTime = currentTime - 1800;

		while (particleEntries.hasNext()) {
			Map.Entry<AoASkill, CopyOnWriteArrayList<XPParticle>> entry = particleEntries.next();

			entry.getValue().removeIf(particle -> particle.creationTime <= expiryTime);

			if (entry.getValue().isEmpty())
				particleEntries.remove();
		}
	}

	private static void renderParticles(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
		if (particlesMap.isEmpty())
			return;

		purgeExpiredEntries();

		if (particlesMap.isEmpty())
			return;

		long currentTime = System.currentTimeMillis();
		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();
		float scrollHeight = window.getGuiScaledHeight() / 3f;
		int windowWidth = window.getGuiScaledWidth();
		int maxHeight = 0;
		int cumulativeXOffset = 0;
		int x = 0;
		int y = 0;

		poseStack.pushPose();
		poseStack.translate(windowWidth / 2f, 1, 0);

		if (particlesMap.size() > 2)
			poseStack.translate(Math.min(particlesMap.size(), 5) / 2f * 12f + 12, 0, 0);

		RenderSystem.disableDepthTest();

		for (Map.Entry<AoASkill, CopyOnWriteArrayList<XPParticle>> entry : particlesMap.entrySet()) {
			AoASkill.Instance skill = ClientPlayerDataManager.get().getSkill(entry.getKey());
			CopyOnWriteArrayList<XPParticle> particles = entry.getValue();
			AoASkillRenderer skillRenderer = AoAGuiElementRenderers.getSkillRenderer(skill.type());
			int renderWidth = skillRenderer.hudRenderWidth(skill);
			int renderHeight = skillRenderer.hudRenderHeight(skill);

			poseStack.pushPose();
			poseStack.translate(x, y, 0);
			poseStack.pushPose();
			poseStack.translate(renderWidth / 2d, 0, 0);

			for (XPParticle particle : particles) {
				float lifespan = 1 - (currentTime - particle.creationTime) / 1500f;

				if (lifespan >= 0.1f) {
					RenderUtil.drawCenteredScaledString(poseStack, mc.font, particle.xpString, 0, scrollHeight * lifespan, 0.5f, ColourUtil.RGBA(255, 255, 255, (int)Mth.clamp(255 * lifespan, 1, 255)), RenderUtil.StringRenderType.NORMAL);
				}
			}

			poseStack.popPose();
			skillRenderer.renderInHud(poseStack, skill, partialTick, AoASkillRenderer.ProgressRenderType.Ring, false);

			if (particles.get(0).levelUp) {
				String level = String.valueOf(skill.getLevel(true));
				float stringWidth = mc.font.width(level);
				float scale = Math.min(1 / (stringWidth / (float)(renderWidth - 7)), 1 / (mc.font.lineHeight / (float)(renderHeight - 7)));

				poseStack.translate(renderWidth / 2d, renderHeight / 2d, 0);
				RenderUtil.drawCenteredScaledString(poseStack, mc.font, level, scale * 0.5f, -mc.font.lineHeight * scale / 2.5f, scale, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
			}

			cumulativeXOffset += renderWidth;
			x -= renderWidth;
			maxHeight = Math.max(maxHeight, renderHeight);

			if (cumulativeXOffset >= 100) {
				y += maxHeight;
				x = 0;
				maxHeight = 0;
				cumulativeXOffset = 0;
			}

			poseStack.popPose();
		}

		RenderSystem.enableDepthTest();
		poseStack.popPose();
	}

	static class XPParticle {
		boolean levelUp;
		float xp;
		String xpString;

		protected long creationTime = System.currentTimeMillis();

		XPParticle(float xp, boolean isLevelUp) {
			this.levelUp = isLevelUp;
			this.xp = xp;
			this.xpString = HolidayUtil.isAprilFools() ? getAprilFoolsXpString() : "+" + NumberUtil.floorAndAppendSuffix(xp, false);
		}

		protected void modifyXp(float additionalXp, boolean isLevelUp) {
			this.levelUp = levelUp || isLevelUp;
			this.xp = xp + additionalXp;
			this.xpString = HolidayUtil.isAprilFools() ? getAprilFoolsXpString() : "+" + NumberUtil.floorAndAppendSuffix(xp, false);
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
				"Where am I?",
				"Weeeeeee!",
				"Going up!",
				"I'm sad",
				"Goodbye!"
		);
	}
}
