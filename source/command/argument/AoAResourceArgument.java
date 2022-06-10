package net.tslat.aoa3.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.player.resource.AoAResource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AoAResourceArgument implements ArgumentType<AoAResource> {
	private static final List<String> EXAMPLES = Arrays.asList("aoa3:spirit");
	private static final DynamicCommandExceptionType UNKNOWN_RESOURCE_ERROR = new DynamicCommandExceptionType(input -> Component.translatable("argument.aoa.resource.notFound"));

	public AoAResourceArgument() {}

	public static AoAResourceArgument resource(CommandBuildContext context) {
		return new AoAResourceArgument();
	}

	public static AoAResource getResource(CommandContext<CommandSourceStack> context, String argName) {
		return context.getArgument(argName, AoAResource.class);
	}

	@Override
	public AoAResource parse(StringReader reader) throws CommandSyntaxException {
		int cursor = reader.getCursor();
		ResourceLocation id = ResourceLocation.read(reader);
		AoAResource resource = AoARegistries.AOA_RESOURCES.getEntry(id);

		if (resource == null) {
			reader.setCursor(cursor);

			throw UNKNOWN_RESOURCE_ERROR.createWithContext(reader, id.toString());
		}

		return resource;
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		StringReader reader = new StringReader(builder.getInput());

		reader.setCursor(builder.getStart());
		builder = builder.createOffset(reader.getCursor());

		SharedSuggestionProvider.suggestResource(AoARegistries.AOA_RESOURCES.getAllIds(), builder);

		return builder.buildFuture();
	}

	@Override
	public Collection<String> getExamples() {
		return EXAMPLES;
	}
}
