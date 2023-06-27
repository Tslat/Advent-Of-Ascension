package net.tslat.aoa3.common.registration.block.group;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.block.BlockRegistrar;
import net.tslat.aoa3.content.block.generation.log.LogBlock;

import java.util.function.Supplier;

public final class LogBlockGroup {
	public final RegistryObject<LogBlock> log;
	public final RegistryObject<LogBlock> bark;
	public final RegistryObject<LogBlock> strippedLog;
	public final RegistryObject<LogBlock> strippedBark;

	public LogBlockGroup(String baseId, BlockRegistrarFactory registry, MapColor endColour, MapColor sideColour, LogBuilder logBuilder) {
		this.log = registry.register(baseId + "_log", registrar -> logBuilder.build(registrar, this::strippedLog, endColour, sideColour));
		this.bark = registry.register(baseId + "_wood", registrar -> registrar.basedOn(this.log).mapColour(sideColour).factory(properties -> new LogBlock(properties, this::strippedBark)).generationBlocksTab());
		this.strippedLog = registry.register("stripped_" + baseId + "_log", registrar -> registrar.basedOn(this.log).mapColour(endColour).factory(properties -> new LogBlock(properties, null)).generationBlocksTab());
		this.strippedBark = registry.register("stripped_" + baseId + "_wood", registrar -> registrar.basedOn(this.log).mapColour(endColour).factory(properties -> new LogBlock(properties, null)).generationBlocksTab());
	}

	public LogBlock log() {
		return this.log.get();
	}

	public LogBlock bark() {
		return this.bark.get();
	}

	public LogBlock strippedLog() {
		return this.strippedLog.get();
	}

	public LogBlock strippedBark() {
		return this.strippedBark.get();
	}

	@FunctionalInterface
	public interface LogBuilder {
		void build(BlockRegistrar<? extends Block> registrar, Supplier<? extends Block> strippedLog, MapColor endColour, MapColor sideColour);
	}
}
