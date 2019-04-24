package net.nevermine.event.recoil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

public class RecoilClientTick {
	public static int tickNumber;
	public static float recoil;

	public static void doRotation() {
		final EntityClientPlayerMP thePlayer = Minecraft.getMinecraft().thePlayer;

		if (thePlayer == null)
			return;

		if (RecoilClientTick.tickNumber > 18) {
			thePlayer.rotationPitch -= RecoilClientTick.recoil / 7.0f;
		}
		else if (RecoilClientTick.tickNumber > 0) {
			thePlayer.rotationPitch += RecoilClientTick.recoil / 45.0f;
		}
	}

	static {
		RecoilClientTick.tickNumber = 0;
		RecoilClientTick.recoil = 0.0f;
	}
}
