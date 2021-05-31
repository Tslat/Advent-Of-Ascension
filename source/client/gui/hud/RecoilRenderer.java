package net.tslat.aoa3.client.gui.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.overlay.ScopeOverlayRenderer;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, value = Dist.CLIENT)
public class RecoilRenderer {
	public static int recoilTicks;
	public static int recoilTicksRemaining;
	public static float recoilAngle;

	@SubscribeEvent
	public static void renderEvent(TickEvent.RenderTickEvent ev) {
		if (ev.phase != TickEvent.Phase.END || Minecraft.getInstance().isPaused())
			return;

		if (recoilTicksRemaining > 0) {
			Minecraft mc = Minecraft.getInstance();

			final ClientPlayerEntity player = mc.player;

			if (player == null)
				return;

			float partialTicks = mc.getFrameTime();
			float mod = 20 / (float)Minecraft.fps;

			if (recoilTicksRemaining >= recoilTicks - 5) {
				player.xRot -= mod * partialTicks * (recoilAngle / (4.0f * (ScopeOverlayRenderer.isScoped ? 4 : 1)));
			}
			else {
				player.xRot += mod * partialTicks * (recoilAngle / (35.0f  * (ScopeOverlayRenderer.isScoped ? 2 : 1)));
			}
		}
	}
}
