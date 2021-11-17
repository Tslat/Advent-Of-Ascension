package net.tslat.aoa3.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class IntegrationsConfig {
	public final ForgeConfigSpec.BooleanValue jeiIntegrationEnabled;
	public final ForgeConfigSpec.BooleanValue jerIntegrationEnabled;
	public final ForgeConfigSpec.BooleanValue immersiveEngineeringEnabled;
	public final ForgeConfigSpec.BooleanValue patchouliEnabled;
	public final ForgeConfigSpec.BooleanValue tinkersConstructEnabled;

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
		specBuilder.comment("Integration options for Patchouli").push("Patchouli Settings");

		patchouliEnabled = specBuilder
				.comment("Set this to false to disable Patchouli integration functionality.")
				.translation("config.aoa3.integrations.patchouli")
				.define("patchouli", true);

		specBuilder.pop();
		specBuilder.comment("Integration options for Tinker's Construct").push("Tinker's Construct Settings");

		tinkersConstructEnabled = specBuilder
				.comment("Set this to false to disable Tinker's Construct integration functionality.")
				.translation("config.aoa3.integrations.tinkersConstruct")
				.define("tinkersConstruct", true);

		specBuilder.pop();
	}
}
