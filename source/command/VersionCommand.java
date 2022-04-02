package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.versions.forge.ForgeVersion;
import net.tslat.aoa3.advent.AdventOfAscension;

public class VersionCommand implements Command<CommandSourceStack> {
	private static final VersionCommand CMD = new VersionCommand();

	public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
		return Commands.literal("version").executes(CMD);
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) {
		AoACommand.feedback(context.getSource(), "Version", "command.aoa.version.desc", AoACommand.CommandFeedbackType.INFO, new TextComponent(AdventOfAscension.VERSION).withStyle(ChatFormatting.GREEN), new TextComponent(ForgeVersion.getVersion()).withStyle(ChatFormatting.GREEN));

		return 1;
	}
}