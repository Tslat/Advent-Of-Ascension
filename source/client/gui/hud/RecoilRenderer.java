package net.tslat.aoa3.client.gui.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.tslat.aoa3.client.gui.overlay.ScopeOverlayRenderer;

public final class RecoilRenderer {
	private static float lastRecoil;
	private static float totalRecoil;

	public static void renderEvent(TickEvent.RenderTickEvent ev) {
		if (ev.phase != TickEvent.Phase.END || Minecraft.getInstance().isPaused())
			return;

		ClientPlayerEntity pl = Minecraft.getInstance().player;

		if (pl == null)
			return;

		if (lastRecoil > 0.01f) {
			float adjustAmount = lastRecoil / 7f;
			pl.xRot -= adjustAmount;
			lastRecoil -= adjustAmount;
		}

		if ((pl.xRot - lastRecoil * 0.25f >= 0 && !ScopeOverlayRenderer.isScoped) || totalRecoil <= 0.01f) {
			totalRecoil = 0;
		}
		else {
			float adjustAmount = Math.min(0.25f, totalRecoil * 0.01f);

			if (lastRecoil <= 0.01f)
				adjustAmount *= 2;

			pl.xRot += adjustAmount;
			totalRecoil -= adjustAmount;
		}
	}

	public static void addRecoil(float recoil) {
		ClientPlayerEntity pl = Minecraft.getInstance().player;

		if (pl.xRot < -40)
			recoil /= 1.5 * (pl.xRot / -20);

		totalRecoil += recoil;
		lastRecoil += recoil;

		if (totalRecoil > 75)
			totalRecoil = 75;
	}
}
