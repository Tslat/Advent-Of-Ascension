package net.tslat.aoa3.client.gui.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.client.event.RenderFrameEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.tslat.aoa3.client.gui.overlay.ScopeOverlayRenderer;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public final class AoACameraModifications {
	private static final RecoilContainer RECOIL = new RecoilContainer();
	private static final List<ShakeContainer> SHAKE = new CopyOnWriteArrayList<>();

	public static void init() {
		NeoForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, RenderFrameEvent.Post.class, AoACameraModifications::onRender);
	}

	public static void addScreenRecoil(final float vertical, final float lateral) {
		float adjustedVertical = getCamera(Minecraft.getInstance())
				.filter(camera -> camera.getXRot() < -40)
				.map(camera -> vertical / (1.5f * (camera.getXRot() / -20)))
				.orElse(vertical);

		RECOIL.addRecoil(adjustedVertical, lateral);
	}

	public static void addScreenShake(final double frequency, float strength, float dampening) {
		if (!AoAConfigs.CLIENT.screenShake.getAsBoolean())
			return;

		SHAKE.add(new ShakeContainer(frequency * (RandomUtil.fiftyFifty() ? 1 : -1), strength, Math.min(dampening, 0.995f)));
	}

	private static Optional<Entity> getCamera(Minecraft mc) {
		return Optional.ofNullable(mc.getCameraEntity() != null ? mc.getCameraEntity() : mc.player);
	}

	private static void onRender(RenderFrameEvent.Post ev) {
		final Minecraft mc = Minecraft.getInstance();

		if (mc.isPaused())
			return;

		getCamera(mc).ifPresent(camera -> {
			addRecoil(camera);
			addShake(camera);
		});
	}

	private static void addShake(Entity camera) {
		if (SHAKE.isEmpty())
			return;

		for (ShakeContainer shake : SHAKE) {
			float angle = ((long)(System.currentTimeMillis() * shake.frequency / 2.75f)) % 360;

			camera.setYRot(camera.getYRot() + Mth.sin(angle * Mth.DEG_TO_RAD) * shake.force);
			camera.setXRot(camera.getXRot() + Mth.sin(angle * Mth.DEG_TO_RAD) * shake.force * 0.1f * (float)camera.getRandom().nextGaussian());

			shake.dampen();
		}

		SHAKE.removeIf(shake -> shake.force < 0.04f);
	}

	private static void addRecoil(Entity camera) {
		if (RECOIL.lastVerticalAmount > 0.01f) {
			float adjustAmount = RECOIL.lastVerticalAmount / 7f;
			RECOIL.lastVerticalAmount -= adjustAmount;

			camera.setXRot(camera.getXRot() - adjustAmount);
		}

		if ((camera.getXRot() - RECOIL.lastVerticalAmount * 0.25f >= 0 && !ScopeOverlayRenderer.isScoped) || RECOIL.totalVerticalAmount <= 0.01f) {
			RECOIL.totalVerticalAmount = 0;
		}
		else {
			float adjustAmount = Math.min(0.25f, RECOIL.totalVerticalAmount * 0.01f);

			if (RECOIL.lastVerticalAmount <= 0.01f)
				adjustAmount *= 2;

			RECOIL.totalVerticalAmount -= adjustAmount;

			camera.setXRot(camera.getXRot() + adjustAmount);
		}

		if (RECOIL.lateralAmount != 0) {
			camera.setYRot(camera.getYRot() + RECOIL.lateralAmount * 0.25f);
			RECOIL.lateralAmount *= 0.75f;
		}
	}

	private static class ShakeContainer {
		final double frequency;
		final float dampening;
		float force;

		ShakeContainer(final double frequency, final float force, final float dampening) {
			this.frequency = frequency;
			this.dampening = dampening;
			this.force = force;
		}

		void dampen() {
			this.force *= this.dampening;
		}
	}

	private static class RecoilContainer {
		float totalVerticalAmount = 0;
		float lastVerticalAmount = 0;
		float lateralAmount = 0;

		void addRecoil(float vertical, float lateral) {
			totalVerticalAmount += vertical;
			lastVerticalAmount += vertical;
			lateralAmount += lateral;

			if (totalVerticalAmount > 75f)
				totalVerticalAmount = 75f;
		}
	}
}
