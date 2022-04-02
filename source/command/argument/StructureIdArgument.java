package net.tslat.aoa3.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.commands.CommandSource;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class StructureIdArgument implements ArgumentType<ResourceLocation> {
	private static final Collection<String> EXAMPLES = Arrays.asList("minecraft:igloo/bottom", "aoa3:abyss/abyssal_lotto_hut/abyssal_lotto_hut");
	private static final DynamicCommandExceptionType UNKNOWN_STRUCTURE_EXCEPTION = new DynamicCommandExceptionType(arg -> new TranslatableComponent("command.aoa.structures.invalidStructure", arg));
	private static MinecraftServer server = null;

	public static StructureIdArgument instance() {
		return new StructureIdArgument();
	}

	public static ResourceLocation getStructureId(CommandContext<CommandSource> context, String name) throws CommandSyntaxException {
		return findStructure(context.getArgument(name, ResourceLocation.class));
	}

	private static ResourceLocation findStructure(ResourceLocation path) throws CommandSyntaxException {
		if (server == null)
			server = ServerLifecycleHooks.getCurrentServer();

		StructureManager templateManager = server.getLevel(Level.OVERWORLD).getStructureManager();
		Optional<StructureTemplate> template = templateManager.get(path);

		if (template.isEmpty())
			throw UNKNOWN_STRUCTURE_EXCEPTION.create(path);

		return path;
	}

	@Override
	public ResourceLocation parse(StringReader reader) throws CommandSyntaxException {
		return ResourceLocation.read(reader);
	}

	@Override
	public Collection<String> getExamples() {
		return EXAMPLES;
	}
}
