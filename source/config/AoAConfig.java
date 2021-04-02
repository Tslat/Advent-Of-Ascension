package net.tslat.aoa3.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class AoAConfig {
	public static final ClientConfig CLIENT;
	public static final ForgeConfigSpec CLIENT_CONFIG_SPEC;

	public static final CommonConfig COMMON;
	public static final ForgeConfigSpec COMMON_CONFIG_SPEC;

	public static final ServerConfig SERVER;
	public static final ForgeConfigSpec SERVER_CONFIG_SPEC;

	public static final IntegrationsConfig INTEGRATIONS;
	public static final ForgeConfigSpec INTEGRATIONS_CONFIG_SPEC;

	static {

		final Pair<ClientConfig, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
		final Pair<CommonConfig, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
		final Pair<ServerConfig, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
		final Pair<IntegrationsConfig, ForgeConfigSpec> integrationSpecPair = new ForgeConfigSpec.Builder().configure(IntegrationsConfig::new);

		CLIENT_CONFIG_SPEC = clientSpecPair.getRight();
		CLIENT = clientSpecPair.getLeft();
		COMMON_CONFIG_SPEC = commonSpecPair.getRight();
		COMMON = commonSpecPair.getLeft();
		SERVER_CONFIG_SPEC = serverSpecPair.getRight();
		SERVER = serverSpecPair.getLeft();
		INTEGRATIONS_CONFIG_SPEC = integrationSpecPair.getRight();
		INTEGRATIONS = integrationSpecPair.getLeft();
	}
}
