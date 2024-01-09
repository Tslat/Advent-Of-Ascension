package net.tslat.aoa3.common.registration;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.config.ClientConfig;
import net.tslat.aoa3.config.CommonConfig;
import net.tslat.aoa3.config.IntegrationsConfig;
import net.tslat.aoa3.config.ServerConfig;
import net.tslat.aoa3.util.ObjectUtil;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;

public final class AoAConfigs {
	public static void init() {
		try {
			ObjectUtil.getOrCreateDirectory(FMLPaths.CONFIGDIR.get().resolve(AdventOfAscension.MOD_ID), AdventOfAscension.MOD_ID);
			ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, AoAConfigs.SERVER_CONFIG_SPEC, AdventOfAscension.MOD_ID + "_server_config.toml");
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AoAConfigs.COMMON_CONFIG_SPEC, AdventOfAscension.MOD_ID + File.separator + "common_config.toml");
			ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, AoAConfigs.CLIENT_CONFIG_SPEC, AdventOfAscension.MOD_ID + File.separator + "client_config.toml");
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AoAConfigs.INTEGRATIONS_CONFIG_SPEC, AdventOfAscension.MOD_ID + File.separator + "integrations_config.toml");
		}
		catch (IOException ex) {
			Logging.error("Failed to create config directories.. this is not good", ex);
		}
	}

	public static final ClientConfig CLIENT;
	public static final ModConfigSpec CLIENT_CONFIG_SPEC;

	public static final CommonConfig COMMON;
	public static final ModConfigSpec COMMON_CONFIG_SPEC;

	public static final ServerConfig SERVER;
	public static final ModConfigSpec SERVER_CONFIG_SPEC;

	public static final IntegrationsConfig INTEGRATIONS;
	public static final ModConfigSpec INTEGRATIONS_CONFIG_SPEC;

	static {
		final Pair<ClientConfig, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(ClientConfig::new);
		final Pair<CommonConfig, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(CommonConfig::new);
		final Pair<ServerConfig, ModConfigSpec> serverSpecPair = new ModConfigSpec.Builder().configure(ServerConfig::new);
		final Pair<IntegrationsConfig, ModConfigSpec> integrationSpecPair = new ModConfigSpec.Builder().configure(IntegrationsConfig::new);

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
