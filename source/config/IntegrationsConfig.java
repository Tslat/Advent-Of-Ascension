package net.tslat.aoa3.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class IntegrationsConfig {
	public final ForgeConfigSpec.BooleanValue jeiIntegrationEnabled;
	public final ForgeConfigSpec.BooleanValue jerIntegrationEnabled;
	public final ForgeConfigSpec.BooleanValue immersiveEngineeringEnabled;

	protected IntegrationsConfig(ForgeConfigSpec.Builder specBuilder) {
		specBuilder.comment("Integration options for JEI (Just Enough Items)").push("JEI Settings");

		jeiIntegrationEnabled = specBuilder
				.comment("Set this to false to disable JEI integration functionality.")
				.translation("config.aoa3.integrations.jei")
				.define("jei", true);

		specBuilder.pop();
		specBuilder.comment("Integration options for JER (Just Enough Resources)").push("JER Settings");

		jerIntegrationEnabled = specBuilder
				.comment("Set this to false to disable JER integration functionality.")
				.translation("config.aoa3.integrations.jer")
				.define("jer", true);

		specBuilder.pop();
		specBuilder.comment("Integration options for Immersive Engineering").push("Immersive Engineering Settings");

		immersiveEngineeringEnabled = specBuilder
				.comment("Set this to false to disable Immersive Engineering integration functionality.")
				.translation("config.aoa3.integrations.immersiveEngineering")
				.define("immersiveEngineering", true);

		specBuilder.pop();
	}
}
