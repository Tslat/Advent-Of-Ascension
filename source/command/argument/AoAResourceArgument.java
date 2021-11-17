package net.tslat.aoa3.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.resource.AoAResource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AoAResourceArgument implements ArgumentType<AoAResource> {
	private static final List<String> EXAMPLES = Arrays.asList("aoa3:spirit");

	private static final DynamicCommandExceptionType UNKNOWN_RESOURCE_ERROR = new DynamicCommandExceptionType(input -> new TranslationTextComponent("argument.aoa.resource.notFound"));

	public static AoAResourceArgument resource() {
		return new AoAResourceArgument();
	}

	@Override
	public AoAResource parse(StringReader reader) throws CommandSyntaxException {
		int cursor = reader.getCursor();
		ResourceLocation id = ResourceLocation.read(reader);
		AoAResource resource = AoAResources.getResource(id);

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

		ISuggestionProvider.suggestResource(AoAResources.getRegistry().getKeys(), builder);

		return builder.buildFuture();
	}

	@Override
	public Collection<String> getExamples() {
		return EXAMPLES;
	}
}
