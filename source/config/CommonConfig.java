package net.tslat.aoa3.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class CommonConfig {
	public final ForgeConfigSpec.BooleanValue doVerboseDebugging;

	protected CommonConfig(ForgeConfigSpec.Builder specBuilder) {
		specBuilder.comment("These are common to both server and client, but you may edit it on either the client or server without it affecting the other").push("Unsynced Config Options");

		doVerboseDebugging = specBuilder
				.comment("Set this to true to enable more detailed debugging.",
						"If you don't mind your logs having a bit more info, or you are trying to figure out a bug or crash, this can be very helpful to have")
				.translation("config.aoa3.common.doVerboseDebugging")
				.define("doVerboseDebugging", false);

		specBuilder.pop();
	}
}
