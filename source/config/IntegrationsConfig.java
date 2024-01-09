package net.tslat.aoa3.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class IntegrationsConfig {
	public final ModConfigSpec.BooleanValue jeiIntegrationEnabled;
	public final ModConfigSpec.BooleanValue patchouliEnabled;
	public final ModConfigSpec.BooleanValue tesEnabled;

	public IntegrationsConfig(ModConfigSpec.Builder specBuilder) {
		specBuilder.comment("Integration options for JEI (Just Enough Items)").push("JEI Settings");

		jeiIntegrationEnabled = specBuilder
				.comment("Set this to false to disable JEI integration functionality.")
				.translation("config.aoa3.integrations.jei")
				.define("jei", true);

		specBuilder.pop();
		specBuilder.comment("Integration options for Patchouli").push("Patchouli Settings");

		patchouliEnabled = specBuilder
				.comment("Set this to false to disable Patchouli integration functionality.")
				.translation("config.aoa3.integrations.patchouli")
				.define("patchouli", true);

		specBuilder.pop();
		specBuilder.comment("Integration options for TslatEntityStatus").push("TES Settings");

		tesEnabled = specBuilder
				.comment("Set this to false to disable TES integration functionality.")
				.translation("config.aoa3.integrations.tes")
				.define("tes", true);

		specBuilder.pop();
	}
}
