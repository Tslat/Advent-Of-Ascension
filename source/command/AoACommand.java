package net.tslat.aoa3.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.tslat.aoa3.util.LocaleUtil;

public class AoACommand {
	protected static final SimpleCommandExceptionType NO_PERMISSION_EXCEPTION = new SimpleCommandExceptionType(new TranslationTextComponent("command.context.noPermission"));

	public static void registerSubCommands(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> cmd = Commands.literal("aoa")
				.then(EventCommand.register(dispatcher))
				.then(PortalResetCommand.register(dispatcher))
				.then(PlayerCommand.register(dispatcher))
				//.then(StructureCommand.register(dispatcher))
				.then(WikiCommand.register(dispatcher));

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

	protected static void feedback(CommandSource source, String commandName, String langKey, AoACommand.CommandFeedbackType type, String... args) {
		source.sendFeedback(AoACommand.getCmdPrefix(commandName).appendSibling(LocaleUtil.getLocaleMessage(langKey, args).setStyle(new Style().setColor(type.getColour()))), true);
	}

	protected static void error(CommandSource source, String commandName, String langKey, String... args) {
		source.sendErrorMessage(AoACommand.getCmdPrefix(commandName).appendSibling(LocaleUtil.getLocaleMessage(langKey, args).setStyle(new Style().setColor(AoACommand.CommandFeedbackType.ERROR.getColour()))));
	}
}
