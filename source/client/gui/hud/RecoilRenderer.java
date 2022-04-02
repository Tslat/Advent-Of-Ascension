package net.tslat.aoa3.client.gui.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.client.gui.overlay.ScopeOverlayRenderer;

public final class RecoilRenderer {
	private static float lastRecoil;
	private static float totalRecoil;

	public static void init() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, TickEvent.RenderTickEvent.class, RecoilRenderer::onRender);
	}

	public static void onRender(TickEvent.RenderTickEvent ev) {
		if (ev.phase != TickEvent.Phase.END || Minecraft.getInstance().isPaused())
			return;

		LocalPlayer pl = Minecraft.getInstance().player;

		if (pl == null)
			return;

		if (lastRecoil > 0.01f) {
			float adjustAmount = lastRecoil / 7f;
			lastRecoil -= adjustAmount;

			pl.setXRot(pl.getXRot() - adjustAmount);
		}

		if ((pl.getXRot() - lastRecoil * 0.25f >= 0 && !ScopeOverlayRenderer.isScoped) || totalRecoil <= 0.01f) {
			totalRecoil = 0;
		}
		else {
			float adjustAmount = Math.min(0.25f, totalRecoil * 0.01f);

			if (lastRecoil <= 0.01f)
				adjustAmount *= 2;
			totalRecoil -= adjustAmount;

			pl.setXRot(pl.getXRot() + adjustAmount);
		}
	}

	public static void addRecoil(float recoil) {
		LocalPlayer pl = Minecraft.getInstance().player;

		if (pl.getXRot() < -40)
			recoil /= 1.5 * (pl.getXRot() / -20);

		totalRecoil += recoil;
		lastRecoil += recoil;

		if (totalRecoil > 75)
			totalRecoil = 75;
	}
}
