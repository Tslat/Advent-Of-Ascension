package net.tslat.aoa3.common.registration;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.FileUtils;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.config.ClientConfig;
import net.tslat.aoa3.config.CommonConfig;
import net.tslat.aoa3.config.IntegrationsConfig;
import net.tslat.aoa3.config.ServerConfig;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;

public final class AoAConfigs {
	public static void init() {
		FileUtils.getOrCreateDirectory(FMLPaths.CONFIGDIR.get().resolve(AdventOfAscension.MOD_ID), AdventOfAscension.MOD_ID);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, AoAConfigs.SERVER_CONFIG_SPEC, AdventOfAscension.MOD_ID + "_server_config.toml");
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AoAConfigs.COMMON_CONFIG_SPEC, AdventOfAscension.MOD_ID + File.separator + "common_config.toml");
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, AoAConfigs.CLIENT_CONFIG_SPEC, AdventOfAscension.MOD_ID + File.separator + "client_config.toml");
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AoAConfigs.INTEGRATIONS_CONFIG_SPEC, AdventOfAscension.MOD_ID + File.separator + "integrations_config.toml");
	}

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
