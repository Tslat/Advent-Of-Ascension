package net.tslat.aoa3.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.*;
import net.tslat.aoa3.util.LocaleUtil;

public class AoACommand {
	protected static final SimpleCommandExceptionType NO_PERMISSION_EXCEPTION = new SimpleCommandExceptionType(new TranslationTextComponent("command.context.noPermission"));

	public static void registerSubCommands(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> cmd = Commands.literal("aoa")
				.then(PortalResetCommand.register(dispatcher))
				.then(PlayerCommand.register(dispatcher))
				.then(StructuresCommand.register(dispatcher))
				.then(WikiCommand.register(dispatcher))
				.then(VersionCommand.register(dispatcher));

		dispatcher.register(cmd);
	}

	protected static StringTextComponent getCmdPrefix(String cmdName) {
		return new StringTextComponent(TextFormatting.DARK_RED + "[AoA" + TextFormatting.GOLD + cmdName + TextFormatting.DARK_RED + "] ");
	}

	protected enum CommandFeedbackType {
		INFO(TextFormatting.GRAY),
		SUCCESS(TextFormatting.GREEN),
		WARN(TextFormatting.RED),
		ERROR(TextFormatting.DARK_RED);

		private final TextFormatting colour;

		CommandFeedbackType(TextFormatting colour) {
			this.colour = colour;
		}

		public TextFormatting getColour() {
			return colour;
		}
	}

	protected static void feedback(CommandSource source, String commandName, String langKey, AoACommand.CommandFeedbackType type, ITextComponent... args) {
		source.sendSuccess(AoACommand.getCmdPrefix(commandName).append(LocaleUtil.getLocaleMessage(langKey, args).setStyle(Style.EMPTY.applyFormat(type.getColour()))), true);
	}

	protected static void error(CommandSource source, String commandName, String langKey, ITextComponent... args) {
		source.sendFailure(AoACommand.getCmdPrefix(commandName).append(LocaleUtil.getLocaleMessage(langKey, args).setStyle(Style.EMPTY.applyFormat(AoACommand.CommandFeedbackType.ERROR.getColour()))));
	}
}
