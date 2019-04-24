package net.tslat.aoa3.client.gui.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.misc.XpGainParticle;
import net.tslat.aoa3.utils.ConfigurationUtil;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;

public class XpParticlesRenderer {
	public static final HashSet<XpGainParticle> particles = new HashSet<XpGainParticle>();
	public static final HashSet<XpGainParticle> particlesStore = new HashSet<XpGainParticle>();
	Minecraft mc;

	public XpParticlesRenderer() {
		mc = Minecraft.getMinecraft();
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderTick(final TickEvent.RenderTickEvent ev) {
		if (ConfigurationUtil.showXpParticles && mc.currentScreen == null && !mc.gameSettings.hideGUI) {
			if (particles.isEmpty() || particlesStore.isEmpty())
				return;

			GlStateManager.pushMatrix();
			GlStateManager.disableDepth();
			final ScaledResolution res = new ScaledResolution(mc);

			GlStateManager.scale(0.4f, 0.4f, 0.4f);
			GlStateManager.enableAlpha();
			GlStateManager.enableBlend();

			if (!particlesStore.isEmpty()) {
				try {
					particles.addAll(particlesStore);
					particlesStore.clear();
				}
				catch (ConcurrentModificationException ex) {}
			}

			Iterator<XpGainParticle> it = particles.iterator();

			while (it.hasNext()) {
				XpGainParticle particle = it.next();
				mc.fontRenderer.drawString(particle.getString(), (int)((res.getScaledWidth()) * 2.5) - mc.fontRenderer.getStringWidth(particle.getString()) - 1, (int)(particle.getY() * 2.5), 16777215 | (255 - (particle.getAge() * 2)) << 24);
				GlStateManager.color(255, 255, 255, 255);
				particle.tickParticle();

				if (particle.getAge() >= 120)
					it.remove();
			}

			GlStateManager.popMatrix();
		}
		else {
			try {
				particles.clear();
				particlesStore.clear();
			}
			catch (ConcurrentModificationException ex) {}
		}
	}
}
