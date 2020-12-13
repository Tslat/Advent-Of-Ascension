package net.tslat.aoa3.config;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.ForgeConfigSpec;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.CommonConfigSyncPacket;

public final class CommonConfig {
	public final ForgeConfigSpec.BooleanValue doVerboseDebugging;
	public final ForgeConfigSpec.BooleanValue skillsEnabled;
	public final ForgeConfigSpec.BooleanValue resourcesEnabled;
	public final ForgeConfigSpec.BooleanValue hardcoreMode;

	protected CommonConfig(ForgeConfigSpec.Builder specBuilder) {
		specBuilder.comment("AoA Synced config options. Do not edit on client-side, only edit on server-side.").push("General Settings");

		skillsEnabled = specBuilder
				.comment("Set this to false to disable AoA's skills functionality.",
						"WARNING: AoA makes no guarantees for completely stable or balanced gameplay with skills disabled.")
				.translation("config.aoa3.skillsEnabled")
				.define("skillsEnabled", true);

		resourcesEnabled = specBuilder
				.comment("Set this to false to disable AoA's resources functionality.",
						"WARNING: AoA makes no guarantees for completely stable or balanced gameplay with resources disabled.")
				.translation("config.aoa3.resourcesEnabled")
				.define("resourcesEnabled", true);

		specBuilder.pop();
		specBuilder.comment("These are common to both server and client, but you may edit it on either the client or server without it affecting the other").push("Unsynced Config Options");

		doVerboseDebugging = specBuilder
				.comment("Set this to true to enable more detailed debugging.",
						"If you don't mind your logs having a bit more info, or you are trying to figure out a bug or crash, this can be very helpful to have")
				.translation("config.aoa3.doVerboseDebugging")
				.define("doVerboseDebugging", false);

		specBuilder.pop();
		specBuilder.comment("Just for fun :)").push("Fun Options");

		hardcoreMode = specBuilder
				.comment("Set this to true to enable AoA hardcore mode.")
				.translation("config.aoa3.hardcoreMode")
				.worldRestart()
				.define("hardcoreMode", false);

		specBuilder.pop();
	}

	public void sync() {
		AoAPackets.messageAllPlayers(getPacket());
	}

	public void sync(ServerPlayerEntity player) {
		AoAPackets.messagePlayer(player, getPacket());
	}

	private CommonConfigSyncPacket getPacket() {
		return new CommonConfigSyncPacket(skillsEnabled.get(), resourcesEnabled.get(), hardcoreMode.get());
	}
}
