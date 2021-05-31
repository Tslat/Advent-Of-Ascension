package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.versions.forge.ForgeVersion;
import net.tslat.aoa3.advent.AdventOfAscension;

public class VersionCommand implements Command<CommandSource> {
	private static final VersionCommand CMD = new VersionCommand();

	public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("version").executes(CMD);

		return builder;
	}

	@Override
	public int run(CommandContext<CommandSource> context) {
		AoACommand.feedback(context.getSource(), "Version", "command.aoa.version.desc", AoACommand.CommandFeedbackType.INFO, new StringTextComponent(AdventOfAscension.VERSION).withStyle(TextFormatting.GREEN), new StringTextComponent(ForgeVersion.getVersion()).withStyle(TextFormatting.GREEN));

		return 1;
	}
}
