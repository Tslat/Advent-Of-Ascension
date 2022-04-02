package net.tslat.aoa3.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.tslat.aoa3.util.LocaleUtil;

public class AoACommand {
	protected static final SimpleCommandExceptionType NO_PERMISSION_EXCEPTION = new SimpleCommandExceptionType(new TranslatableComponent("command.context.noPermission"));

	public static void registerSubCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
		LiteralArgumentBuilder<CommandSourceStack> cmd = Commands.literal("aoa")
				.then(PortalResetCommand.register(dispatcher))
				.then(PlayerCommand.register(dispatcher))
				//.then(StructuresCommand.register(dispatcher))
				.then(WikiCommand.register(dispatcher))
				.then(VersionCommand.register(dispatcher));

		dispatcher.register(cmd);
	}

	protected static TextComponent getCmdPrefix(String cmdName) {
		return new TextComponent(ChatFormatting.DARK_RED + "[AoA" + ChatFormatting.GOLD + cmdName + ChatFormatting.DARK_RED + "] ");
	}

	protected enum CommandFeedbackType {
		INFO(ChatFormatting.GRAY),
		SUCCESS(ChatFormatting.GREEN),
		WARN(ChatFormatting.RED),
		ERROR(ChatFormatting.DARK_RED);

		private final ChatFormatting colour;

		CommandFeedbackType(ChatFormatting colour) {
			this.colour = colour;
		}

		public ChatFormatting getColour() {
			return colour;
		}
	}

	protected static void feedback(CommandSourceStack source, String commandName, String langKey, CommandFeedbackType type, Component... args) {
		source.sendSuccess(AoACommand.getCmdPrefix(commandName).append(LocaleUtil.getLocaleMessage(langKey, args).setStyle(Style.EMPTY.applyFormat(type.getColour()))), true);
	}

	protected static void error(CommandSourceStack source, String commandName, String langKey, Component... args) {
		source.sendFailure(AoACommand.getCmdPrefix(commandName).append(LocaleUtil.getLocaleMessage(langKey, args).setStyle(Style.EMPTY.applyFormat(CommandFeedbackType.ERROR.getColour()))));
	}
}