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
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AoASkillArgument implements ArgumentType<AoASkill> {
	private static final List<String> EXAMPLES = Arrays.asList("aoa3:alchemy", "aoa3:creation", "aoa3:hauling", "aoa3:innervation");
	private static final DynamicCommandExceptionType UNKNOWN_SKILL_ERROR = new DynamicCommandExceptionType(input -> Component.translatable("argument.aoa.skill.notFound"));

	public AoASkillArgument() {}

	public static AoASkillArgument skill(CommandBuildContext context) {
		return new AoASkillArgument();
	}

	public static AoASkill getSkill(CommandContext<CommandSourceStack> context, String argName) {
		return context.getArgument(argName, AoASkill.class);
	}

	@Override
	public AoASkill parse(StringReader reader) throws CommandSyntaxException {
		int cursor = reader.getCursor();
		ResourceLocation id = ResourceLocation.read(reader);
		AoASkill skill = AoARegistries.AOA_SKILLS.getEntry(id);

		if (skill == null) {
			reader.setCursor(cursor);

			throw UNKNOWN_SKILL_ERROR.createWithContext(reader, id.toString());
		}

		return skill;
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		StringReader reader = new StringReader(builder.getInput());

		reader.setCursor(builder.getStart());

		builder = builder.createOffset(reader.getCursor());

		SharedSuggestionProvider.suggestResource(AoARegistries.AOA_SKILLS.getAllIds(), builder);

		return builder.buildFuture();
	}

	@Override
	public Collection<String> getExamples() {
		return EXAMPLES;
	}
}
